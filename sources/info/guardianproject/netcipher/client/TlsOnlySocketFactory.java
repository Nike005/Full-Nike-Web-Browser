package info.guardianproject.netcipher.client;

import android.net.SSLCertificateSocketFactory;
import android.net.SSLSessionCache;
import android.os.Build;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class TlsOnlySocketFactory extends SSLSocketFactory {
    private static final int HANDSHAKE_TIMEOUT = 0;
    private static final String TAG = "TlsOnlySocketFactory";
    private final boolean compatible;
    private final SSLSocketFactory delegate;

    public TlsOnlySocketFactory() {
        this.delegate = SSLCertificateSocketFactory.getDefault(0, (SSLSessionCache) null);
        this.compatible = false;
    }

    public TlsOnlySocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.delegate = sSLSocketFactory;
        this.compatible = false;
    }

    public TlsOnlySocketFactory(SSLSocketFactory sSLSocketFactory, boolean z) {
        this.delegate = sSLSocketFactory;
        this.compatible = z;
    }

    public String[] getDefaultCipherSuites() {
        return this.delegate.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return this.delegate.getSupportedCipherSuites();
    }

    private Socket makeSocketSafe(Socket socket, String str) {
        if (!(socket instanceof SSLSocket)) {
            return socket;
        }
        TlsOnlySSLSocket tlsOnlySSLSocket = new TlsOnlySSLSocket((SSLSocket) socket, this.compatible);
        if (!(this.delegate instanceof SSLCertificateSocketFactory) || Build.VERSION.SDK_INT < 17) {
            tlsOnlySSLSocket.setHostname(str);
        } else {
            ((SSLCertificateSocketFactory) this.delegate).setHostname(socket, str);
        }
        return tlsOnlySSLSocket;
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        return makeSocketSafe(this.delegate.createSocket(socket, str, i, z), str);
    }

    public Socket createSocket(String str, int i) throws IOException {
        return makeSocketSafe(this.delegate.createSocket(str, i), str);
    }

    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        return makeSocketSafe(this.delegate.createSocket(str, i, inetAddress, i2), str);
    }

    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return makeSocketSafe(this.delegate.createSocket(inetAddress, i), inetAddress.getHostName());
    }

    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return makeSocketSafe(this.delegate.createSocket(inetAddress, i, inetAddress2, i2), inetAddress.getHostName());
    }

    private class TlsOnlySSLSocket extends DelegateSSLSocket {
        final boolean compatible;

        private TlsOnlySSLSocket(SSLSocket sSLSocket, boolean z) {
            super(sSLSocket);
            this.compatible = z;
            int i = 0;
            if (z) {
                ArrayList arrayList = new ArrayList(Arrays.asList(sSLSocket.getEnabledProtocols()));
                arrayList.remove("SSLv2");
                arrayList.remove("SSLv3");
                super.setEnabledProtocols((String[]) arrayList.toArray(new String[arrayList.size()]));
                ArrayList arrayList2 = new ArrayList(10);
                Pattern compile = Pattern.compile(".*(EXPORT|NULL).*");
                String[] enabledCipherSuites = sSLSocket.getEnabledCipherSuites();
                int length = enabledCipherSuites.length;
                while (i < length) {
                    String str = enabledCipherSuites[i];
                    if (!compile.matcher(str).matches()) {
                        arrayList2.add(str);
                    }
                    i++;
                }
                super.setEnabledCipherSuites((String[]) arrayList2.toArray(new String[arrayList2.size()]));
                return;
            }
            ArrayList arrayList3 = new ArrayList(Arrays.asList(sSLSocket.getSupportedProtocols()));
            arrayList3.remove("SSLv2");
            arrayList3.remove("SSLv3");
            super.setEnabledProtocols((String[]) arrayList3.toArray(new String[arrayList3.size()]));
            ArrayList arrayList4 = new ArrayList(10);
            Pattern compile2 = Pattern.compile(".*(_DES|DH_|DSS|EXPORT|MD5|NULL|RC4).*");
            String[] supportedCipherSuites = sSLSocket.getSupportedCipherSuites();
            int length2 = supportedCipherSuites.length;
            while (i < length2) {
                String str2 = supportedCipherSuites[i];
                if (!compile2.matcher(str2).matches()) {
                    arrayList4.add(str2);
                }
                i++;
            }
            super.setEnabledCipherSuites((String[]) arrayList4.toArray(new String[arrayList4.size()]));
        }

        public void setEnabledProtocols(String[] strArr) {
            List list;
            if (strArr != null && strArr.length == 1 && "SSLv3".equals(strArr[0])) {
                if (this.compatible) {
                    list = Arrays.asList(this.delegate.getEnabledProtocols());
                } else {
                    list = Arrays.asList(this.delegate.getSupportedProtocols());
                }
                ArrayList arrayList = new ArrayList(list);
                if (arrayList.size() > 1) {
                    arrayList.remove("SSLv2");
                    arrayList.remove("SSLv3");
                } else {
                    Log.w(TlsOnlySocketFactory.TAG, "SSL stuck with protocol available for " + String.valueOf(arrayList));
                }
                strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
            }
            super.setEnabledProtocols(strArr);
        }
    }

    public class DelegateSSLSocket extends SSLSocket {
        protected final SSLSocket delegate;

        DelegateSSLSocket(SSLSocket sSLSocket) {
            this.delegate = sSLSocket;
        }

        public String[] getSupportedCipherSuites() {
            return this.delegate.getSupportedCipherSuites();
        }

        public String[] getEnabledCipherSuites() {
            return this.delegate.getEnabledCipherSuites();
        }

        public void setEnabledCipherSuites(String[] strArr) {
            this.delegate.setEnabledCipherSuites(strArr);
        }

        public String[] getSupportedProtocols() {
            return this.delegate.getSupportedProtocols();
        }

        public String[] getEnabledProtocols() {
            return this.delegate.getEnabledProtocols();
        }

        public void setEnabledProtocols(String[] strArr) {
            this.delegate.setEnabledProtocols(strArr);
        }

        public SSLSession getSession() {
            return this.delegate.getSession();
        }

        public void addHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
            this.delegate.addHandshakeCompletedListener(handshakeCompletedListener);
        }

        public void removeHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
            this.delegate.removeHandshakeCompletedListener(handshakeCompletedListener);
        }

        public void startHandshake() throws IOException {
            this.delegate.startHandshake();
        }

        public void setUseClientMode(boolean z) {
            this.delegate.setUseClientMode(z);
        }

        public boolean getUseClientMode() {
            return this.delegate.getUseClientMode();
        }

        public void setNeedClientAuth(boolean z) {
            this.delegate.setNeedClientAuth(z);
        }

        public void setWantClientAuth(boolean z) {
            this.delegate.setWantClientAuth(z);
        }

        public boolean getNeedClientAuth() {
            return this.delegate.getNeedClientAuth();
        }

        public boolean getWantClientAuth() {
            return this.delegate.getWantClientAuth();
        }

        public void setEnableSessionCreation(boolean z) {
            this.delegate.setEnableSessionCreation(z);
        }

        public boolean getEnableSessionCreation() {
            return this.delegate.getEnableSessionCreation();
        }

        public void bind(SocketAddress socketAddress) throws IOException {
            this.delegate.bind(socketAddress);
        }

        public synchronized void close() throws IOException {
            this.delegate.close();
        }

        public void connect(SocketAddress socketAddress) throws IOException {
            this.delegate.connect(socketAddress);
        }

        public void connect(SocketAddress socketAddress, int i) throws IOException {
            this.delegate.connect(socketAddress, i);
        }

        public SocketChannel getChannel() {
            return this.delegate.getChannel();
        }

        public InetAddress getInetAddress() {
            return this.delegate.getInetAddress();
        }

        public InputStream getInputStream() throws IOException {
            return this.delegate.getInputStream();
        }

        public boolean getKeepAlive() throws SocketException {
            return this.delegate.getKeepAlive();
        }

        public InetAddress getLocalAddress() {
            return this.delegate.getLocalAddress();
        }

        public int getLocalPort() {
            return this.delegate.getLocalPort();
        }

        public SocketAddress getLocalSocketAddress() {
            return this.delegate.getLocalSocketAddress();
        }

        public boolean getOOBInline() throws SocketException {
            return this.delegate.getOOBInline();
        }

        public OutputStream getOutputStream() throws IOException {
            return this.delegate.getOutputStream();
        }

        public int getPort() {
            return this.delegate.getPort();
        }

        public synchronized int getReceiveBufferSize() throws SocketException {
            return this.delegate.getReceiveBufferSize();
        }

        public SocketAddress getRemoteSocketAddress() {
            return this.delegate.getRemoteSocketAddress();
        }

        public boolean getReuseAddress() throws SocketException {
            return this.delegate.getReuseAddress();
        }

        public synchronized int getSendBufferSize() throws SocketException {
            return this.delegate.getSendBufferSize();
        }

        public int getSoLinger() throws SocketException {
            return this.delegate.getSoLinger();
        }

        public synchronized int getSoTimeout() throws SocketException {
            return this.delegate.getSoTimeout();
        }

        public boolean getTcpNoDelay() throws SocketException {
            return this.delegate.getTcpNoDelay();
        }

        public int getTrafficClass() throws SocketException {
            return this.delegate.getTrafficClass();
        }

        public boolean isBound() {
            return this.delegate.isBound();
        }

        public boolean isClosed() {
            return this.delegate.isClosed();
        }

        public boolean isConnected() {
            return this.delegate.isConnected();
        }

        public boolean isInputShutdown() {
            return this.delegate.isInputShutdown();
        }

        public boolean isOutputShutdown() {
            return this.delegate.isOutputShutdown();
        }

        public void sendUrgentData(int i) throws IOException {
            this.delegate.sendUrgentData(i);
        }

        public void setKeepAlive(boolean z) throws SocketException {
            this.delegate.setKeepAlive(z);
        }

        public void setOOBInline(boolean z) throws SocketException {
            this.delegate.setOOBInline(z);
        }

        public void setPerformancePreferences(int i, int i2, int i3) {
            this.delegate.setPerformancePreferences(i, i2, i3);
        }

        public synchronized void setReceiveBufferSize(int i) throws SocketException {
            this.delegate.setReceiveBufferSize(i);
        }

        public void setReuseAddress(boolean z) throws SocketException {
            this.delegate.setReuseAddress(z);
        }

        public synchronized void setSendBufferSize(int i) throws SocketException {
            this.delegate.setSendBufferSize(i);
        }

        public void setSoLinger(boolean z, int i) throws SocketException {
            this.delegate.setSoLinger(z, i);
        }

        public synchronized void setSoTimeout(int i) throws SocketException {
            this.delegate.setSoTimeout(i);
        }

        public void setTcpNoDelay(boolean z) throws SocketException {
            this.delegate.setTcpNoDelay(z);
        }

        public void setTrafficClass(int i) throws SocketException {
            this.delegate.setTrafficClass(i);
        }

        public void shutdownInput() throws IOException {
            this.delegate.shutdownInput();
        }

        public void shutdownOutput() throws IOException {
            this.delegate.shutdownOutput();
        }

        public DelegateSSLSocket setHostname(String str) {
            try {
                this.delegate.getClass().getMethod("setHostname", new Class[]{String.class}).invoke(this.delegate, new Object[]{str});
                return this;
            } catch (Exception e) {
                throw new IllegalStateException("Could not enable SNI", e);
            }
        }

        public String toString() {
            return this.delegate.toString();
        }

        public boolean equals(Object obj) {
            return this.delegate.equals(obj);
        }
    }
}

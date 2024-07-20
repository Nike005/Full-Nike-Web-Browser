package info.guardianproject.netcipher;

import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.mopub.common.Constants;
import info.guardianproject.netcipher.client.TlsOnlySocketFactory;
import info.guardianproject.netcipher.proxy.OrbotHelper;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URI;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

public class NetCipher {
    public static final Proxy ORBOT_HTTP_PROXY = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8118));
    private static final String TAG = "NetCipher";
    private static Proxy proxy;

    private NetCipher() {
    }

    public static void setProxy(String str, int i) {
        if (!TextUtils.isEmpty(str) && i > 0) {
            setProxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(str, i)));
        } else if (proxy != ORBOT_HTTP_PROXY) {
            setProxy((Proxy) null);
        }
    }

    public static void setProxy(Proxy proxy2) {
        if (proxy2 == null || proxy != ORBOT_HTTP_PROXY) {
            proxy = proxy2;
        } else {
            Log.w(TAG, "useTor is enabled, ignoring new proxy settings!");
        }
    }

    public static Proxy getProxy() {
        return proxy;
    }

    public static void clearProxy() {
        setProxy((Proxy) null);
    }

    public static void useTor() {
        setProxy(ORBOT_HTTP_PROXY);
    }

    public static TlsOnlySocketFactory getTlsOnlySocketFactory() {
        return getTlsOnlySocketFactory(false);
    }

    public static TlsOnlySocketFactory getTlsOnlySocketFactory(boolean z) {
        try {
            SSLContext instance = SSLContext.getInstance("TLSv1");
            instance.init((KeyManager[]) null, (TrustManager[]) null, (SecureRandom) null);
            return new TlsOnlySocketFactory(instance.getSocketFactory(), z);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        } catch (KeyManagementException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static HttpURLConnection getHttpURLConnection(URL url, boolean z) throws IOException {
        HttpURLConnection httpURLConnection;
        Proxy proxy2 = proxy;
        if (OrbotHelper.isOnionAddress(url)) {
            proxy2 = ORBOT_HTTP_PROXY;
        }
        if (proxy2 != null) {
            httpURLConnection = (HttpURLConnection) url.openConnection(proxy2);
        } else {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        }
        if (httpURLConnection instanceof HttpsURLConnection) {
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) httpURLConnection;
            httpsURLConnection.setSSLSocketFactory(getTlsOnlySocketFactory(z));
            if (Build.VERSION.SDK_INT < 16) {
                httpsURLConnection.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
            }
        }
        return httpURLConnection;
    }

    public static HttpsURLConnection getHttpsURLConnection(String str) throws IOException {
        return getHttpsURLConnection(new URL(str.replaceFirst("^[Hh][Tt][Tt][Pp]:", "https:")), false);
    }

    public static HttpsURLConnection getHttpsURLConnection(Uri uri) throws IOException {
        return getHttpsURLConnection(uri.toString());
    }

    public static HttpsURLConnection getHttpsURLConnection(URI uri) throws IOException {
        if (TextUtils.equals(uri.getScheme(), Constants.HTTPS)) {
            return getHttpsURLConnection(uri.toURL(), false);
        }
        return getHttpsURLConnection(uri.toString());
    }

    public static HttpsURLConnection getHttpsURLConnection(URL url) throws IOException {
        return getHttpsURLConnection(url, false);
    }

    public static HttpsURLConnection getCompatibleHttpsURLConnection(URL url) throws IOException {
        return getHttpsURLConnection(url, true);
    }

    public static HttpsURLConnection getHttpsURLConnection(URL url, boolean z) throws IOException {
        HttpURLConnection httpURLConnection = getHttpURLConnection(url, z);
        if (httpURLConnection instanceof HttpsURLConnection) {
            return (HttpsURLConnection) httpURLConnection;
        }
        throw new IllegalArgumentException("not an HTTPS connection!");
    }

    public static HttpURLConnection getCompatibleHttpURLConnection(URL url) throws IOException {
        return getHttpURLConnection(url, true);
    }

    public static HttpURLConnection getHttpURLConnection(String str) throws IOException {
        return getHttpURLConnection(new URL(str));
    }

    public static HttpURLConnection getHttpURLConnection(Uri uri) throws IOException {
        return getHttpURLConnection(uri.toString());
    }

    public static HttpURLConnection getHttpURLConnection(URI uri) throws IOException {
        return getHttpURLConnection(uri.toURL());
    }

    public static HttpURLConnection getHttpURLConnection(URL url) throws IOException {
        return getHttpURLConnection(url, false);
    }
}

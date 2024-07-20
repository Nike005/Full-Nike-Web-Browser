package info.guardianproject.netcipher.client;

import android.content.Context;
import android.content.Intent;
import info.guardianproject.netcipher.client.StrongBuilder;
import info.guardianproject.netcipher.client.StrongBuilderBase;
import info.guardianproject.netcipher.proxy.OrbotHelper;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import org.json.JSONObject;

public abstract class StrongBuilderBase<T extends StrongBuilderBase, C> implements StrongBuilder<T, C> {
    private static final String PROXY_HOST = "127.0.0.1";
    static final String TOR_CHECK_URL = "https://check.torproject.org/api/ip";
    protected final Context ctxt;
    protected Proxy.Type proxyType;
    protected SSLContext sslContext = null;
    protected boolean useWeakCiphers = false;
    protected boolean validateTor = false;

    /* access modifiers changed from: protected */
    public abstract String get(Intent intent, C c, String str) throws Exception;

    public boolean supportsHttpProxy() {
        return true;
    }

    public boolean supportsSocksProxy() {
        return false;
    }

    public StrongBuilderBase(Context context) {
        this.ctxt = context.getApplicationContext();
    }

    public StrongBuilderBase(StrongBuilderBase strongBuilderBase) {
        this.ctxt = strongBuilderBase.ctxt;
        this.proxyType = strongBuilderBase.proxyType;
        this.sslContext = strongBuilderBase.sslContext;
        this.useWeakCiphers = strongBuilderBase.useWeakCiphers;
    }

    public T withBestProxy() {
        if (supportsSocksProxy()) {
            return withSocksProxy();
        }
        return withHttpProxy();
    }

    public T withHttpProxy() {
        this.proxyType = Proxy.Type.HTTP;
        return this;
    }

    public T withSocksProxy() {
        this.proxyType = Proxy.Type.SOCKS;
        return this;
    }

    public T withTrustManagers(TrustManager[] trustManagerArr) throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext instance = SSLContext.getInstance("TLSv1");
        this.sslContext = instance;
        instance.init((KeyManager[]) null, trustManagerArr, (SecureRandom) null);
        return this;
    }

    public T withWeakCiphers() {
        this.useWeakCiphers = true;
        return this;
    }

    public T withTorValidation() {
        this.validateTor = true;
        return this;
    }

    public SSLContext getSSLContext() {
        return this.sslContext;
    }

    public int getSocksPort(Intent intent) {
        if (intent.getStringExtra(OrbotHelper.EXTRA_STATUS).equals("ON")) {
            return intent.getIntExtra(OrbotHelper.EXTRA_PROXY_PORT_SOCKS, 9050);
        }
        return -1;
    }

    public int getHttpPort(Intent intent) {
        if (intent.getStringExtra(OrbotHelper.EXTRA_STATUS).equals("ON")) {
            return intent.getIntExtra(OrbotHelper.EXTRA_PROXY_PORT_HTTP, 8118);
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public SSLSocketFactory buildSocketFactory() {
        if (this.sslContext == null) {
            return null;
        }
        return new TlsOnlySocketFactory(this.sslContext.getSocketFactory(), this.useWeakCiphers);
    }

    public Proxy buildProxy(Intent intent) {
        if (intent.getStringExtra(OrbotHelper.EXTRA_STATUS).equals("ON")) {
            if (this.proxyType == Proxy.Type.SOCKS) {
                return new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(PROXY_HOST, getSocksPort(intent)));
            }
            if (this.proxyType == Proxy.Type.HTTP) {
                return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(PROXY_HOST, getHttpPort(intent)));
            }
        }
        return null;
    }

    public void build(final StrongBuilder.Callback<C> callback) {
        OrbotHelper.get(this.ctxt).addStatusCallback(new OrbotHelper.SimpleStatusCallback() {
            public void onEnabled(Intent intent) {
                OrbotHelper.get(StrongBuilderBase.this.ctxt).removeStatusCallback(this);
                try {
                    Object build = StrongBuilderBase.this.build(intent);
                    if (StrongBuilderBase.this.validateTor) {
                        StrongBuilderBase.this.validateTor = false;
                        StrongBuilderBase.this.checkTor(callback, intent, build);
                        return;
                    }
                    callback.onConnected(build);
                } catch (Exception e) {
                    callback.onConnectionException(e);
                }
            }

            public void onNotYetInstalled() {
                OrbotHelper.get(StrongBuilderBase.this.ctxt).removeStatusCallback(this);
                callback.onTimeout();
            }

            public void onStatusTimeout() {
                OrbotHelper.get(StrongBuilderBase.this.ctxt).removeStatusCallback(this);
                callback.onTimeout();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void checkTor(final StrongBuilder.Callback<C> callback, final Intent intent, final C c) {
        new Thread() {
            public void run() {
                try {
                    if (new JSONObject(StrongBuilderBase.this.get(intent, c, StrongBuilderBase.TOR_CHECK_URL)).optBoolean("IsTor", false)) {
                        callback.onConnected(c);
                    } else {
                        callback.onInvalid();
                    }
                } catch (Exception e) {
                    callback.onConnectionException(e);
                }
            }
        }.start();
    }
}

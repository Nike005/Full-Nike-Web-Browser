package com.yandex.metrica.impl.p050ob;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* renamed from: com.yandex.metrica.impl.ob.fc */
class C2144fc {

    /* renamed from: a */
    private Collection<X509TrustManager> f3692a = new ArrayList();

    public C2144fc() throws NoSuchAlgorithmException, KeyStoreException {
        TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        instance.init((KeyStore) null);
        for (TrustManager trustManager : instance.getTrustManagers()) {
            if (trustManager instanceof X509TrustManager) {
                this.f3692a.add((X509TrustManager) trustManager);
            }
        }
    }

    /* renamed from: a */
    public boolean mo17738a(X509Certificate[] x509CertificateArr) {
        try {
            for (X509TrustManager checkServerTrusted : this.f3692a) {
                checkServerTrusted.checkServerTrusted(x509CertificateArr, "RSA");
            }
            return true;
        } catch (CertificateException unused) {
            return false;
        }
    }

    /* renamed from: a */
    public X509Certificate[] mo17739a() {
        ArrayList arrayList = new ArrayList();
        for (X509TrustManager acceptedIssuers : this.f3692a) {
            arrayList.addAll(Arrays.asList(acceptedIssuers.getAcceptedIssuers()));
        }
        return (X509Certificate[]) arrayList.toArray(new X509Certificate[arrayList.size()]);
    }
}

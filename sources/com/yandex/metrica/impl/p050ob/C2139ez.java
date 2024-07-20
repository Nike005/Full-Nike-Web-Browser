package com.yandex.metrica.impl.p050ob;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

/* renamed from: com.yandex.metrica.impl.ob.ez */
public class C2139ez {

    /* renamed from: a */
    private C2154fk f3681a;

    public C2139ez(C2154fk fkVar) {
        this.f3681a = fkVar;
    }

    /* renamed from: a */
    public SSLContext mo17732a() throws KeyManagementException, NoSuchAlgorithmException {
        SSLContext instance = SSLContext.getInstance("TLS");
        instance.init((KeyManager[]) null, new TrustManager[]{this.f3681a}, (SecureRandom) null);
        return instance;
    }
}

package com.yandex.metrica.impl.p050ob;

import java.io.IOException;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;

/* renamed from: com.yandex.metrica.impl.ob.cs */
public class C2048cs extends C2047cr {
    C2048cs(String str) {
        super(str);
    }

    /* renamed from: a */
    public HttpURLConnection mo17507a() throws IOException {
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) super.mo17507a();
        httpsURLConnection.setSSLSocketFactory(C2041co.m5269a().mo17504b());
        return httpsURLConnection;
    }

    /* renamed from: b */
    public boolean mo17508b() {
        return C2041co.m5269a().mo17505c();
    }
}

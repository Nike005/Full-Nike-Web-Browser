package com.yandex.metrica.impl.p050ob;

import com.yandex.metrica.impl.C1880bc;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/* renamed from: com.yandex.metrica.impl.ob.cq */
public abstract class C2045cq {

    /* renamed from: a */
    private final String f3438a;

    /* renamed from: com.yandex.metrica.impl.ob.cq$a */
    public static final class C2046a {

        /* renamed from: a */
        public static final int f3439a = ((int) TimeUnit.SECONDS.toMillis(30));
    }

    /* renamed from: b */
    public abstract boolean mo17508b();

    public C2045cq(String str) {
        this.f3438a = str;
    }

    /* renamed from: a */
    public HttpURLConnection mo17507a() throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.f3438a).openConnection();
        httpURLConnection.setConnectTimeout(C2046a.f3439a);
        httpURLConnection.setReadTimeout(C2046a.f3439a);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setRequestProperty("Accept", "application/json");
        httpURLConnection.setRequestProperty("User-Agent", C1880bc.m4537a("com.yandex.mobile.metrica.sdk"));
        return httpURLConnection;
    }
}

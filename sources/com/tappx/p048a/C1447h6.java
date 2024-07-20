package com.tappx.p048a;

import com.android.volley.toolbox.HttpClientStack;
import com.mopub.common.Constants;
import java.io.DataOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* renamed from: com.tappx.a.h6 */
public class C1447h6 extends C1331b6 {

    /* renamed from: a */
    private final C1449b f1923a;

    /* renamed from: b */
    private final SSLSocketFactory f1924b;

    /* renamed from: com.tappx.a.h6$a */
    static class C1448a extends FilterInputStream {

        /* renamed from: a */
        private final HttpURLConnection f1925a;

        C1448a(HttpURLConnection httpURLConnection) {
            super(C1447h6.m2814b(httpURLConnection));
            this.f1925a = httpURLConnection;
        }

        public void close() {
            super.close();
            this.f1925a.disconnect();
        }
    }

    /* renamed from: com.tappx.a.h6$b */
    public interface C1449b {
        /* renamed from: a */
        String mo15853a(String str);
    }

    public C1447h6() {
        this((C1449b) null);
    }

    /* renamed from: a */
    private static boolean m2813a(int i, int i2) {
        return (i == 4 || (100 <= i2 && i2 < 200) || i2 == 204 || i2 == 304) ? false : true;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public static InputStream m2814b(HttpURLConnection httpURLConnection) {
        try {
            return httpURLConnection.getInputStream();
        } catch (IOException unused) {
            return httpURLConnection.getErrorStream();
        }
    }

    public C1447h6(C1449b bVar) {
        this(bVar, (SSLSocketFactory) null);
    }

    /* renamed from: a */
    public C1429g6 mo15575a(C1615s5<?> s5Var, Map<String, String> map) {
        String r = s5Var.mo16163r();
        HashMap hashMap = new HashMap();
        hashMap.putAll(map);
        hashMap.putAll(s5Var.mo15860f());
        C1449b bVar = this.f1923a;
        if (bVar != null) {
            String a = bVar.mo15853a(r);
            if (a != null) {
                r = a;
            } else {
                throw new IOException("URL blocked by rewriter: " + r);
            }
        }
        HttpURLConnection a2 = m2809a(new URL(r), s5Var);
        boolean z = false;
        try {
            for (String str : hashMap.keySet()) {
                a2.setRequestProperty(str, (String) hashMap.get(str));
            }
            m2815b(a2, s5Var);
            int responseCode = a2.getResponseCode();
            if (responseCode == -1) {
                throw new IOException("Could not retrieve response code from HttpUrlConnection.");
            } else if (!m2813a(s5Var.mo16154g(), responseCode)) {
                C1429g6 g6Var = new C1429g6(responseCode, m2810a((Map<String, List<String>>) a2.getHeaderFields()));
                a2.disconnect();
                return g6Var;
            } else {
                z = true;
                return new C1429g6(responseCode, m2810a((Map<String, List<String>>) a2.getHeaderFields()), a2.getContentLength(), new C1448a(a2));
            }
        } catch (Throwable th) {
            if (!z) {
                a2.disconnect();
            }
            throw th;
        }
    }

    public C1447h6(C1449b bVar, SSLSocketFactory sSLSocketFactory) {
        this.f1923a = bVar;
        this.f1924b = sSLSocketFactory;
    }

    /* renamed from: b */
    static void m2815b(HttpURLConnection httpURLConnection, C1615s5<?> s5Var) {
        switch (s5Var.mo16154g()) {
            case -1:
                byte[] j = s5Var.mo15861j();
                if (j != null) {
                    httpURLConnection.setRequestMethod("POST");
                    m2812a(httpURLConnection, s5Var, j);
                    return;
                }
                return;
            case 0:
                httpURLConnection.setRequestMethod("GET");
                return;
            case 1:
                httpURLConnection.setRequestMethod("POST");
                m2811a(httpURLConnection, s5Var);
                return;
            case 2:
                httpURLConnection.setRequestMethod("PUT");
                m2811a(httpURLConnection, s5Var);
                return;
            case 3:
                httpURLConnection.setRequestMethod("DELETE");
                return;
            case 4:
                httpURLConnection.setRequestMethod("HEAD");
                return;
            case 5:
                httpURLConnection.setRequestMethod("OPTIONS");
                return;
            case 6:
                httpURLConnection.setRequestMethod("TRACE");
                return;
            case 7:
                httpURLConnection.setRequestMethod(HttpClientStack.HttpPatch.METHOD_NAME);
                m2811a(httpURLConnection, s5Var);
                return;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    /* renamed from: a */
    static List<C1528m5> m2810a(Map<String, List<String>> map) {
        ArrayList arrayList = new ArrayList(map.size());
        for (Map.Entry next : map.entrySet()) {
            if (next.getKey() != null) {
                for (String m5Var : (List) next.getValue()) {
                    arrayList.add(new C1528m5((String) next.getKey(), m5Var));
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public HttpURLConnection mo15851a(URL url) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
        return httpURLConnection;
    }

    /* renamed from: a */
    private HttpURLConnection m2809a(URL url, C1615s5<?> s5Var) {
        SSLSocketFactory sSLSocketFactory;
        HttpURLConnection a = mo15851a(url);
        int p = s5Var.mo16161p();
        a.setConnectTimeout(p);
        a.setReadTimeout(p);
        a.setUseCaches(false);
        a.setDoInput(true);
        if (Constants.HTTPS.equals(url.getProtocol()) && (sSLSocketFactory = this.f1924b) != null) {
            ((HttpsURLConnection) a).setSSLSocketFactory(sSLSocketFactory);
        }
        return a;
    }

    /* renamed from: a */
    private static void m2811a(HttpURLConnection httpURLConnection, C1615s5<?> s5Var) {
        byte[] b = s5Var.mo15859b();
        if (b != null) {
            m2812a(httpURLConnection, s5Var, b);
        }
    }

    /* renamed from: a */
    private static void m2812a(HttpURLConnection httpURLConnection, C1615s5<?> s5Var, byte[] bArr) {
        httpURLConnection.setDoOutput(true);
        if (!httpURLConnection.getRequestProperties().containsKey("Content-Type")) {
            httpURLConnection.setRequestProperty("Content-Type", s5Var.mo16150c());
        }
        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
        dataOutputStream.write(bArr);
        dataOutputStream.close();
    }
}

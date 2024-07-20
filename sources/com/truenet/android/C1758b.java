package com.truenet.android;

import acr.browser.lightning.constant.Constants;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appnext.core.p086a.C4944b;
import com.truenet.android.p049a.C1754i;
import java.io.BufferedReader;
import java.io.InputStream;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import p055a.p056a.C2937c;
import p055a.p056a.C2942d;
import p055a.p056a.C2969h;
import p055a.p056a.p057a.C2903g;
import p055a.p056a.p058b.p059a.C2918a;
import p055a.p056a.p058b.p060b.C2928h;
import p055a.p056a.p058b.p060b.C2929i;
import p055a.p056a.p058b.p060b.C2931k;
import p055a.p056a.p058b.p060b.C2932l;
import p055a.p056a.p058b.p060b.C2933m;
import p055a.p056a.p058b.p060b.C2935n;
import p055a.p056a.p062d.C2947e;
import p055a.p056a.p063e.C2951b;
import p055a.p056a.p063e.C2955c;

/* renamed from: com.truenet.android.b */
/* compiled from: StartAppSDK */
public final class C1758b {

    /* renamed from: a */
    static final /* synthetic */ C2947e[] f2672a;

    /* renamed from: b */
    public static final C1759a f2673b;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public static final String f2674n;
    /* access modifiers changed from: private */

    /* renamed from: o */
    public static final C2951b f2675o = new C2951b("^\\w+(://){1}.+$");

    /* renamed from: c */
    private Bitmap f2676c;

    /* renamed from: d */
    private long f2677d;

    /* renamed from: e */
    private String f2678e;

    /* renamed from: f */
    private String f2679f;

    /* renamed from: g */
    private final List<C1760b> f2680g = new ArrayList();

    /* renamed from: h */
    private final C2937c f2681h = C2942d.m6172a(C1765f.f2701a);

    /* renamed from: i */
    private final C2937c f2682i = C2942d.m6172a(new C1766g(this));
    /* access modifiers changed from: private */

    /* renamed from: j */
    public final Context f2683j;

    /* renamed from: k */
    private final String f2684k;

    /* renamed from: l */
    private final int f2685l;

    /* renamed from: m */
    private final long f2686m;

    /* access modifiers changed from: private */
    /* renamed from: j */
    public final SynchronousQueue<String> m3877j() {
        C2937c cVar = this.f2681h;
        C2947e eVar = f2672a[0];
        return (SynchronousQueue) cVar.mo24919a();
    }

    /* access modifiers changed from: private */
    /* renamed from: k */
    public final WebView m3878k() {
        C2937c cVar = this.f2682i;
        C2947e eVar = f2672a[1];
        return (WebView) cVar.mo24919a();
    }

    public C1758b(Context context, String str, int i, long j) {
        C2928h.m6157b(context, "context");
        C2928h.m6157b(str, "url");
        this.f2683j = context;
        this.f2684k = str;
        this.f2685l = i;
        this.f2686m = j;
        this.f2678e = str;
    }

    /* renamed from: a */
    public final Bitmap mo16522a() {
        return this.f2676c;
    }

    /* renamed from: b */
    public final int mo16523b() {
        return this.f2680g.size();
    }

    /* renamed from: c */
    public final long mo16524c() {
        return this.f2677d;
    }

    /* renamed from: d */
    public final List<C1760b> mo16525d() {
        return C2903g.m6123a(this.f2680g);
    }

    /* renamed from: e */
    public final String mo16526e() {
        return this.f2678e;
    }

    /* renamed from: f */
    public final String mo16527f() {
        return this.f2679f;
    }

    /* renamed from: com.truenet.android.b$f */
    /* compiled from: StartAppSDK */
    static final class C1765f extends C2929i implements C2918a<SynchronousQueue<String>> {

        /* renamed from: a */
        public static final C1765f f2701a = new C1765f();

        C1765f() {
            super(0);
        }

        /* renamed from: b */
        public final SynchronousQueue<String> mo16475a() {
            return new SynchronousQueue<>();
        }
    }

    /* renamed from: com.truenet.android.b$g */
    /* compiled from: StartAppSDK */
    static final class C1766g extends C2929i implements C2918a<WebView> {
        final /* synthetic */ C1758b this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        C1766g(C1758b bVar) {
            super(0);
            this.this$0 = bVar;
        }

        /* renamed from: b */
        public final WebView mo16475a() {
            try {
                WebView webView = new WebView(this.this$0.f2683j);
                if (Build.VERSION.SDK_INT >= 11) {
                    webView.setLayerType(1, (Paint) null);
                }
                WebSettings settings = webView.getSettings();
                C2928h.m6154a((Object) settings, C4944b.f4720hW);
                settings.setJavaScriptEnabled(true);
                webView.setWebChromeClient(new WebChromeClient());
                webView.setWebViewClient(new C1761c());
                return webView;
            } catch (Exception e) {
                Log.e(C1758b.f2674n, e.getMessage());
                return null;
            }
        }
    }

    /* renamed from: com.truenet.android.b$a */
    /* compiled from: StartAppSDK */
    public static final class C1759a {
        private C1759a() {
        }

        public /* synthetic */ C1759a(C2925e eVar) {
            this();
        }

        /* access modifiers changed from: private */
        /* renamed from: a */
        public final boolean m3887a(String str) {
            return C2955c.m6176a(str, "http://play.google.com", false, 2, (Object) null) || C2955c.m6176a(str, "https://play.google.com", false, 2, (Object) null) || C2955c.m6176a(str, "http://itunes.apple.com", false, 2, (Object) null) || C2955c.m6176a(str, "https://itunes.apple.com", false, 2, (Object) null) || (!C2955c.m6176a(str, Constants.HTTP, false, 2, (Object) null) && !C2955c.m6176a(str, Constants.HTTPS, false, 2, (Object) null) && C1758b.f2675o.mo24982a(str));
        }
    }

    static {
        Class<C1758b> cls = C1758b.class;
        f2672a = new C2947e[]{C2935n.m6164a((C2931k) new C2932l(C2935n.m6162a((Class) cls), "queue", "getQueue()Ljava/util/concurrent/SynchronousQueue;")), C2935n.m6164a((C2931k) new C2932l(C2935n.m6162a((Class) cls), "webView", "getWebView()Landroid/webkit/WebView;"))};
        C1759a aVar = new C1759a((C2925e) null);
        f2673b = aVar;
        f2674n = aVar.getClass().getSimpleName();
    }

    /* renamed from: com.truenet.android.b$b */
    /* compiled from: StartAppSDK */
    public static final class C1760b {

        /* renamed from: a */
        private final String f2687a;

        /* renamed from: b */
        private final long f2688b;

        /* renamed from: c */
        private final int f2689c;

        /* renamed from: d */
        private final List<String> f2690d;

        /* renamed from: e */
        private final String f2691e;

        /* renamed from: a */
        public static /* bridge */ /* synthetic */ C1760b m3888a(C1760b bVar, String str, long j, int i, List<String> list, String str2, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = bVar.f2687a;
            }
            if ((i2 & 2) != 0) {
                j = bVar.f2688b;
            }
            long j2 = j;
            if ((i2 & 4) != 0) {
                i = bVar.f2689c;
            }
            int i3 = i;
            if ((i2 & 8) != 0) {
                list = bVar.f2690d;
            }
            List<String> list2 = list;
            if ((i2 & 16) != 0) {
                str2 = bVar.f2691e;
            }
            return bVar.mo16529a(str, j2, i3, list2, str2);
        }

        /* renamed from: a */
        public final C1760b mo16529a(String str, long j, int i, List<String> list, String str2) {
            C2928h.m6157b(str, "url");
            return new C1760b(str, j, i, list, str2);
        }

        public boolean equals(Object obj) {
            if (this != obj) {
                if (obj instanceof C1760b) {
                    C1760b bVar = (C1760b) obj;
                    if (C2928h.m6156a((Object) this.f2687a, (Object) bVar.f2687a)) {
                        if (this.f2688b == bVar.f2688b) {
                            if (!(this.f2689c == bVar.f2689c) || !C2928h.m6156a((Object) this.f2690d, (Object) bVar.f2690d) || !C2928h.m6156a((Object) this.f2691e, (Object) bVar.f2691e)) {
                                return false;
                            }
                        }
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            String str = this.f2687a;
            int i = 0;
            int hashCode = str != null ? str.hashCode() : 0;
            long j = this.f2688b;
            int i2 = ((((hashCode * 31) + ((int) (j ^ (j >>> 32)))) * 31) + this.f2689c) * 31;
            List<String> list = this.f2690d;
            int hashCode2 = (i2 + (list != null ? list.hashCode() : 0)) * 31;
            String str2 = this.f2691e;
            if (str2 != null) {
                i = str2.hashCode();
            }
            return hashCode2 + i;
        }

        public String toString() {
            return "ConnectionInfo(url=" + this.f2687a + ", loadTime=" + this.f2688b + ", httpCode=" + this.f2689c + ", cookie=" + this.f2690d + ", redirectUrl=" + this.f2691e + ")";
        }

        public C1760b(String str, long j, int i, List<String> list, String str2) {
            C2928h.m6157b(str, "url");
            this.f2687a = str;
            this.f2688b = j;
            this.f2689c = i;
            this.f2690d = list;
            this.f2691e = str2;
        }

        /* renamed from: a */
        public final String mo16530a() {
            return this.f2687a;
        }

        /* renamed from: b */
        public final long mo16531b() {
            return this.f2688b;
        }

        /* renamed from: c */
        public final int mo16532c() {
            return this.f2689c;
        }

        /* renamed from: d */
        public final List<String> mo16533d() {
            return this.f2690d;
        }

        /* renamed from: e */
        public final String mo16534e() {
            return this.f2691e;
        }
    }

    /* renamed from: a */
    static /* bridge */ /* synthetic */ C1760b m3868a(C1758b bVar, String str, List list, int i, Object obj) {
        if ((i & 2) != 0) {
            list = null;
        }
        return bVar.m3869a(str, list);
    }

    /* renamed from: a */
    private final C1760b m3869a(String str, List<String> list) {
        String str2;
        String str3 = str;
        this.f2679f = null;
        if (f2673b.m3887a(str3)) {
            return new C1760b(str, 0, 200, (List<String>) null, (String) null);
        }
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(str3);
            URLConnection openConnection = url.openConnection();
            if (openConnection != null) {
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) openConnection;
                boolean z = false;
                httpURLConnection2.setInstanceFollowRedirects(false);
                httpURLConnection2.setConnectTimeout(((int) this.f2686m) * 1000);
                httpURLConnection2.setReadTimeout(((int) this.f2686m) * 1000);
                httpURLConnection2.addRequestProperty("User-Agent", C1754i.f2667a.mo16520a(this.f2683j));
                if (list != null) {
                    Iterable<String> iterable = list;
                    Collection arrayList = new ArrayList(C2903g.m6121a(iterable, 10));
                    for (String parse : iterable) {
                        List<HttpCookie> parse2 = HttpCookie.parse(parse);
                        C2928h.m6154a((Object) parse2, "HttpCookie.parse(it)");
                        arrayList.add((HttpCookie) C2903g.m6126c(parse2));
                    }
                    httpURLConnection2.setRequestProperty("Cookie", TextUtils.join(r3, (List) arrayList));
                }
                long currentTimeMillis = System.currentTimeMillis();
                httpURLConnection2.connect();
                long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                String headerField = httpURLConnection2.getHeaderField("Location");
                if (headerField != null) {
                    C2951b bVar = f2675o;
                    C2928h.m6154a((Object) headerField, "nextUrl");
                    if (!bVar.mo24982a(headerField)) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(url.getProtocol());
                        sb.append("://");
                        sb.append(url.getHost());
                        C2928h.m6154a((Object) headerField, "nextUrl");
                        if (!C2955c.m6176a(headerField, "/", false, 2, (Object) null)) {
                            headerField = '/' + headerField;
                        }
                        sb.append(headerField);
                        headerField = sb.toString();
                    }
                    str2 = headerField;
                } else {
                    str2 = null;
                }
                C1760b bVar2 = new C1760b(str, currentTimeMillis2, httpURLConnection2.getResponseCode(), (List) httpURLConnection2.getHeaderFields().get("Set-Cookie"), str2);
                int responseCode = httpURLConnection2.getResponseCode();
                if (200 <= responseCode) {
                    if (299 >= responseCode) {
                        InputStream inputStream = httpURLConnection2.getInputStream();
                        C2928h.m6154a((Object) inputStream, "inputStream");
                        this.f2679f = m3870a(inputStream);
                        long currentTimeMillis3 = System.currentTimeMillis() - currentTimeMillis;
                        new Handler(Looper.getMainLooper()).post(new C1764e(httpURLConnection2, this, list, url, str));
                        String take = m3877j().take();
                        C2928h.m6154a((Object) take, "jsRedirectUrl");
                        if (take.length() == 0) {
                            z = true;
                        }
                        if (z) {
                            return C1760b.m3888a(bVar2, (String) null, currentTimeMillis3, 0, (List) null, (String) null, 29, (Object) null);
                        }
                        return C1760b.m3888a(bVar2, (String) null, currentTimeMillis3, 0, (List) null, take, 13, (Object) null);
                    }
                }
                if (300 <= responseCode) {
                    if (399 >= responseCode) {
                        return bVar2;
                    }
                }
                return C1760b.m3888a(bVar2, (String) null, 0, 0, (List) null, (String) null, 15, (Object) null);
            }
            throw new C2969h("null cannot be cast to non-null type java.net.HttpURLConnection");
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: com.truenet.android.b$e */
    /* compiled from: StartAppSDK */
    static final class C1764e implements Runnable {

        /* renamed from: a */
        final /* synthetic */ HttpURLConnection f2696a;

        /* renamed from: b */
        final /* synthetic */ C1758b f2697b;

        /* renamed from: c */
        final /* synthetic */ List f2698c;

        /* renamed from: d */
        final /* synthetic */ URL f2699d;

        /* renamed from: e */
        final /* synthetic */ String f2700e;

        C1764e(HttpURLConnection httpURLConnection, C1758b bVar, List list, URL url, String str) {
            this.f2696a = httpURLConnection;
            this.f2697b = bVar;
            this.f2698c = list;
            this.f2699d = url;
            this.f2700e = str;
        }

        public final void run() {
            WebView b = this.f2697b.m3878k();
            if (b != null) {
                b.loadDataWithBaseURL(this.f2700e, this.f2697b.mo16527f(), this.f2696a.getContentType(), this.f2696a.getContentEncoding(), (String) null);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0076 A[Catch:{ all -> 0x007f }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x007b A[Catch:{ all -> 0x007f }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String m3870a(java.io.InputStream r8) {
        /*
            r7 = this;
            java.lang.String r0 = "stream closed with error!"
            r1 = 0
            r2 = r1
            java.io.BufferedInputStream r2 = (java.io.BufferedInputStream) r2
            a.a.b.b.m$a r3 = new a.a.b.b.m$a
            r3.<init>()
            r4 = r1
            java.io.BufferedReader r4 = (java.io.BufferedReader) r4
            r3.element = r4
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x006f }
            r4.<init>()     // Catch:{ all -> 0x006f }
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch:{ all -> 0x006f }
            r5.<init>(r8)     // Catch:{ all -> 0x006f }
            java.io.BufferedReader r8 = new java.io.BufferedReader     // Catch:{ all -> 0x006c }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ all -> 0x006c }
            r6 = r5
            java.io.InputStream r6 = (java.io.InputStream) r6     // Catch:{ all -> 0x006c }
            r2.<init>(r6)     // Catch:{ all -> 0x006c }
            java.io.Reader r2 = (java.io.Reader) r2     // Catch:{ all -> 0x006c }
            r8.<init>(r2)     // Catch:{ all -> 0x006c }
            r3.element = r8     // Catch:{ all -> 0x006c }
            a.a.b.b.m$a r8 = new a.a.b.b.m$a     // Catch:{ all -> 0x006c }
            r8.<init>()     // Catch:{ all -> 0x006c }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x006c }
            r8.element = r1     // Catch:{ all -> 0x006c }
        L_0x0034:
            com.truenet.android.b$d r1 = new com.truenet.android.b$d     // Catch:{ all -> 0x006c }
            r1.<init>(r8, r3)     // Catch:{ all -> 0x006c }
            a.a.b.a.a r1 = (p055a.p056a.p058b.p059a.C2918a) r1     // Catch:{ all -> 0x006c }
            java.lang.Object r1 = r1.mo16475a()     // Catch:{ all -> 0x006c }
            if (r1 == 0) goto L_0x0049
            T r1 = r8.element     // Catch:{ all -> 0x006c }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ all -> 0x006c }
            r4.append(r1)     // Catch:{ all -> 0x006c }
            goto L_0x0034
        L_0x0049:
            java.lang.String r8 = r4.toString()     // Catch:{ all -> 0x006c }
            java.lang.String r1 = "result.toString()"
            p055a.p056a.p058b.p060b.C2928h.m6154a((java.lang.Object) r8, (java.lang.String) r1)     // Catch:{ all -> 0x006c }
            T r1 = r3.element     // Catch:{ all -> 0x005f }
            java.io.BufferedReader r1 = (java.io.BufferedReader) r1     // Catch:{ all -> 0x005f }
            if (r1 == 0) goto L_0x005b
            r1.close()     // Catch:{ all -> 0x005f }
        L_0x005b:
            r5.close()     // Catch:{ all -> 0x005f }
            goto L_0x006b
        L_0x005f:
            r1 = move-exception
            java.lang.Class r2 = r7.getClass()
            java.lang.String r2 = r2.getCanonicalName()
            android.util.Log.e(r2, r0, r1)
        L_0x006b:
            return r8
        L_0x006c:
            r8 = move-exception
            r2 = r5
            goto L_0x0070
        L_0x006f:
            r8 = move-exception
        L_0x0070:
            T r1 = r3.element     // Catch:{ all -> 0x007f }
            java.io.BufferedReader r1 = (java.io.BufferedReader) r1     // Catch:{ all -> 0x007f }
            if (r1 == 0) goto L_0x0079
            r1.close()     // Catch:{ all -> 0x007f }
        L_0x0079:
            if (r2 == 0) goto L_0x008b
            r2.close()     // Catch:{ all -> 0x007f }
            goto L_0x008b
        L_0x007f:
            r1 = move-exception
            java.lang.Class r2 = r7.getClass()
            java.lang.String r2 = r2.getCanonicalName()
            android.util.Log.e(r2, r0, r1)
        L_0x008b:
            goto L_0x008d
        L_0x008c:
            throw r8
        L_0x008d:
            goto L_0x008c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.truenet.android.C1758b.m3870a(java.io.InputStream):java.lang.String");
    }

    /* renamed from: com.truenet.android.b$d */
    /* compiled from: StartAppSDK */
    static final class C1763d extends C2929i implements C2918a<String> {
        final /* synthetic */ C2933m.C2934a $line;
        final /* synthetic */ C2933m.C2934a $reader;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        C1763d(C2933m.C2934a aVar, C2933m.C2934a aVar2) {
            super(0);
            this.$line = aVar;
            this.$reader = aVar2;
        }

        /* renamed from: b */
        public final String mo16475a() {
            this.$line.element = ((BufferedReader) this.$reader.element).readLine();
            return (String) this.$line.element;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x0055  */
    /* renamed from: g */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void mo16528g() {
        /*
            r7 = this;
            long r0 = java.lang.System.currentTimeMillis()
            java.lang.String r2 = r7.f2684k
            r3 = 0
            r4 = 2
            com.truenet.android.b$b r2 = m3868a(r7, r2, r3, r4, r3)
            if (r2 == 0) goto L_0x0087
            java.util.List<com.truenet.android.b$b> r4 = r7.f2680g
            r4.add(r2)
        L_0x0013:
            if (r2 == 0) goto L_0x001a
            java.lang.String r4 = r2.mo16534e()
            goto L_0x001b
        L_0x001a:
            r4 = r3
        L_0x001b:
            if (r4 == 0) goto L_0x003c
            boolean r4 = r7.m3872a((long) r0)
            if (r4 == 0) goto L_0x003c
            java.lang.String r4 = r2.mo16534e()
            if (r4 != 0) goto L_0x002c
            p055a.p056a.p058b.p060b.C2928h.m6153a()
        L_0x002c:
            java.util.List r2 = r2.mo16533d()
            com.truenet.android.b$b r2 = r7.m3869a(r4, r2)
            if (r2 == 0) goto L_0x0013
            java.util.List<com.truenet.android.b$b> r4 = r7.f2680g
            r4.add(r2)
            goto L_0x0013
        L_0x003c:
            if (r2 == 0) goto L_0x005b
            r4 = 299(0x12b, float:4.19E-43)
            r5 = 200(0xc8, float:2.8E-43)
            int r2 = r2.mo16532c()
            if (r5 <= r2) goto L_0x0049
            goto L_0x005b
        L_0x0049:
            if (r4 < r2) goto L_0x005b
            java.lang.String r2 = r7.f2679f
            if (r2 == 0) goto L_0x005b
            android.webkit.WebView r2 = r7.m3878k()
            if (r2 == 0) goto L_0x0059
            android.graphics.Bitmap r3 = com.truenet.android.p049a.C1757j.m3867a(r2)
        L_0x0059:
            r7.f2676c = r3
        L_0x005b:
            java.util.List<com.truenet.android.b$b> r2 = r7.f2680g
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            r3 = 0
            java.util.Iterator r2 = r2.iterator()
        L_0x0065:
            boolean r5 = r2.hasNext()
            if (r5 == 0) goto L_0x0077
            java.lang.Object r5 = r2.next()
            com.truenet.android.b$b r5 = (com.truenet.android.C1758b.C1760b) r5
            long r5 = r5.mo16531b()
            long r3 = r3 + r5
            goto L_0x0065
        L_0x0077:
            r7.f2677d = r3
            java.util.List<com.truenet.android.b$b> r2 = r7.f2680g
            java.lang.Object r2 = p055a.p056a.p057a.C2903g.m6128e(r2)
            com.truenet.android.b$b r2 = (com.truenet.android.C1758b.C1760b) r2
            java.lang.String r2 = r2.mo16530a()
            r7.f2678e = r2
        L_0x0087:
            java.util.List<com.truenet.android.b$b> r2 = r7.f2680g
            boolean r2 = r2.isEmpty()
            if (r2 == 0) goto L_0x0096
            long r2 = java.lang.System.currentTimeMillis()
            long r2 = r2 - r0
            r7.f2677d = r2
        L_0x0096:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.truenet.android.C1758b.mo16528g():void");
    }

    /* renamed from: a */
    private final boolean m3872a(long j) {
        int size = this.f2680g.size();
        int i = this.f2685l;
        return (size < i || i == -1) && System.currentTimeMillis() - j < this.f2686m * ((long) 1000);
    }

    /* renamed from: com.truenet.android.b$c */
    /* compiled from: StartAppSDK */
    public final class C1761c extends WebViewClient {

        /* renamed from: b */
        private ScheduledExecutorService f2693b = Executors.newScheduledThreadPool(1);

        /* renamed from: c */
        private ScheduledFuture<?> f2694c;

        public C1761c() {
        }

        /* renamed from: a */
        private final void m3896a(WebView webView, String str) {
            m3895a();
            if (str != null) {
                if (webView != null) {
                    webView.stopLoading();
                }
                C1758b.this.m3877j().offer(str);
            }
        }

        public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
            m3896a(webView, String.valueOf(webResourceRequest != null ? webResourceRequest.getUrl() : null));
            return true;
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            m3896a(webView, str);
            return true;
        }

        public void onPageFinished(WebView webView, String str) {
            m3895a();
            this.f2694c = this.f2693b.schedule(new C1762a(this), 1, TimeUnit.SECONDS);
            super.onPageFinished(webView, str);
        }

        /* renamed from: com.truenet.android.b$c$a */
        /* compiled from: StartAppSDK */
        static final class C1762a implements Runnable {

            /* renamed from: a */
            final /* synthetic */ C1761c f2695a;

            C1762a(C1761c cVar) {
                this.f2695a = cVar;
            }

            public final void run() {
                C1758b.this.m3877j().offer("");
            }
        }

        public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
            m3895a();
            if (webView != null) {
                webView.stopLoading();
            }
            C1758b.this.m3877j().offer("");
            super.onReceivedError(webView, webResourceRequest, webResourceError);
        }

        /* renamed from: a */
        private final void m3895a() {
            ScheduledFuture<?> scheduledFuture = this.f2694c;
            if (scheduledFuture != null) {
                scheduledFuture.cancel(false);
            }
            this.f2694c = null;
        }
    }
}

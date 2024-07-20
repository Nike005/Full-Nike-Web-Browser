package com.tappx.p048a;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.lang.reflect.Constructor;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;

/* renamed from: com.tappx.a.p0 */
public final class C1559p0 {

    /* renamed from: a */
    public final String f2159a;

    /* renamed from: b */
    public final String f2160b;

    /* renamed from: c */
    public final String f2161c;

    /* renamed from: d */
    public final String f2162d;

    /* renamed from: e */
    public final String f2163e;

    /* renamed from: f */
    public final int f2164f;

    /* renamed from: g */
    public final int f2165g;

    /* renamed from: h */
    public final int f2166h;

    /* renamed from: i */
    public final String f2167i;

    /* renamed from: j */
    public final String f2168j;

    /* renamed from: k */
    public final String f2169k;

    /* renamed from: com.tappx.a.p0$b */
    public static class C1561b {

        /* renamed from: d */
        private static volatile C1561b f2170d;

        /* renamed from: a */
        private final Context f2171a;

        /* renamed from: b */
        private final C1357d<String> f2172b;

        /* renamed from: c */
        private final C1562a f2173c;

        /* renamed from: com.tappx.a.p0$b$a */
        static class C1562a {

            /* renamed from: a */
            private final Context f2174a;

            /* renamed from: com.tappx.a.p0$b$a$a */
            private static final class C1563a {
                /* access modifiers changed from: private */

                /* renamed from: a */
                public String f2175a;

                /* renamed from: b */
                private final Context f2176b;

                /* renamed from: com.tappx.a.p0$b$a$a$a */
                class C1564a implements Runnable {

                    /* renamed from: a */
                    final /* synthetic */ CountDownLatch f2177a;

                    C1564a(CountDownLatch countDownLatch) {
                        this.f2177a = countDownLatch;
                    }

                    public void run() {
                        C1563a aVar = C1563a.this;
                        String unused = aVar.f2175a = aVar.m3196b();
                        this.f2177a.countDown();
                    }
                }

                /* access modifiers changed from: private */
                /* renamed from: b */
                public String m3196b() {
                    WebView webView = new WebView(this.f2176b);
                    String userAgentString = webView.getSettings().getUserAgentString();
                    webView.destroy();
                    return userAgentString;
                }

                private C1563a(Context context) {
                    this.f2176b = context;
                }

                /* renamed from: a */
                public String mo16047a() {
                    if (C1426g3.m2744a()) {
                        return m3196b();
                    }
                    CountDownLatch countDownLatch = new CountDownLatch(1);
                    C1426g3.m2743a(new C1564a(countDownLatch));
                    try {
                        countDownLatch.await();
                        return this.f2175a;
                    } catch (InterruptedException unused) {
                        return null;
                    }
                }
            }

            public C1562a(Context context) {
                this.f2174a = context;
            }

            /* renamed from: b */
            private String m3190b() {
                return WebSettings.getDefaultUserAgent(this.f2174a);
            }

            /* renamed from: c */
            private String m3191c() {
                return new C1563a(this.f2174a).mo16047a();
            }

            /* renamed from: d */
            private String m3192d() {
                Constructor<WebSettings> declaredConstructor = WebSettings.class.getDeclaredConstructor(new Class[]{Context.class, WebView.class});
                boolean isAccessible = declaredConstructor.isAccessible();
                if (!isAccessible) {
                    declaredConstructor.setAccessible(true);
                }
                try {
                    return declaredConstructor.newInstance(new Object[]{this.f2174a, null}).getUserAgentString();
                } finally {
                    if (!isAccessible) {
                        declaredConstructor.setAccessible(false);
                    }
                }
            }

            /* JADX WARNING: Can't wrap try/catch for region: R(5:0|(3:2|3|4)|5|6|7) */
            /* JADX WARNING: Code restructure failed: missing block: B:10:0x0014, code lost:
                if (r0 == null) goto L_0x0016;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
                return java.lang.System.getProperty("http.agent");
             */
            /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
                return r0;
             */
            /* JADX WARNING: Code restructure failed: missing block: B:8:0x0010, code lost:
                r0 = m3191c();
             */
            /* JADX WARNING: Failed to process nested try/catch */
            /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x000b */
            /* renamed from: a */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.String mo16046a() {
                /*
                    r2 = this;
                    int r0 = android.os.Build.VERSION.SDK_INT
                    r1 = 17
                    if (r0 < r1) goto L_0x000b
                    java.lang.String r0 = r2.m3190b()     // Catch:{ Exception -> 0x000b }
                    return r0
                L_0x000b:
                    java.lang.String r0 = r2.m3192d()     // Catch:{ Exception -> 0x0010 }
                    return r0
                L_0x0010:
                    java.lang.String r0 = r2.m3191c()
                    if (r0 != 0) goto L_0x001c
                    java.lang.String r0 = "http.agent"
                    java.lang.String r0 = java.lang.System.getProperty(r0)
                L_0x001c:
                    return r0
                */
                throw new UnsupportedOperationException("Method not decompiled: com.tappx.p048a.C1559p0.C1561b.C1562a.mo16046a():java.lang.String");
            }
        }

        public C1561b(Context context) {
            this(context, new C1562a(context));
        }

        /* renamed from: a */
        private int m3185a(int i, int i2) {
            if (i == i2) {
                return 0;
            }
            return i < i2 ? 1 : 2;
        }

        /* renamed from: a */
        public static final C1561b m3186a(Context context) {
            if (f2170d == null) {
                synchronized (C1561b.class) {
                    if (f2170d == null) {
                        f2170d = new C1561b(context.getApplicationContext());
                    }
                }
            }
            return f2170d;
        }

        /* renamed from: b */
        private String m3187b() {
            Locale locale = Locale.getDefault();
            return locale != null ? locale.getLanguage() : "en-us";
        }

        /* renamed from: c */
        private String m3188c() {
            String a = this.f2172b.mo15646a();
            if (a != null) {
                return a;
            }
            String a2 = this.f2173c.mo16046a();
            this.f2172b.mo15647a(a2);
            return a2;
        }

        C1561b(Context context, C1562a aVar) {
            this.f2172b = new C1481k();
            this.f2171a = context;
            this.f2173c = aVar;
        }

        /* renamed from: a */
        public C1559p0 mo16045a() {
            String b = m3187b();
            String str = Build.MANUFACTURER;
            String str2 = Build.MODEL;
            String str3 = Build.PRODUCT;
            String str4 = Build.VERSION.RELEASE;
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((WindowManager) this.f2171a.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            int i2 = displayMetrics.heightPixels;
            return new C1559p0(b, str, str2, str3, SystemMediaRouteProvider.PACKAGE_NAME, str4, i, i2, m3185a(i, i2), String.valueOf(displayMetrics.scaledDensity), m3188c());
        }
    }

    public C1559p0(String str, String str2, String str3, String str4, String str5, String str6, int i, int i2, int i3, String str7, String str8) {
        this.f2169k = str;
        this.f2159a = str2;
        this.f2160b = str3;
        this.f2161c = str4;
        this.f2162d = str5;
        this.f2163e = str6;
        this.f2165g = i;
        this.f2166h = i2;
        this.f2164f = i3;
        this.f2167i = str7;
        this.f2168j = str8;
    }
}

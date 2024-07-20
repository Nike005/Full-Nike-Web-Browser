package com.tappx.p048a;

import android.content.Context;
import android.content.pm.PackageManager;
import android.provider.Settings;
import com.tappx.p048a.C1697y0;
import com.tappx.sdk.android.BuildConfig;

/* renamed from: com.tappx.a.m1 */
public final class C1522m1 {

    /* renamed from: a */
    public final String f2069a;

    /* renamed from: b */
    public final String f2070b;

    /* renamed from: c */
    public final String f2071c;

    /* renamed from: d */
    public final String f2072d;

    /* renamed from: e */
    public final String f2073e;

    /* renamed from: f */
    public final boolean f2074f;

    public C1522m1(String str, String str2, String str3, String str4, String str5, boolean z) {
        this.f2069a = str;
        this.f2072d = str2;
        this.f2070b = str4;
        this.f2071c = str5;
        this.f2073e = str3;
        this.f2074f = z;
    }

    /* renamed from: com.tappx.a.m1$a */
    public static class C1523a {

        /* renamed from: c */
        private static volatile C1523a f2075c;

        /* renamed from: a */
        private final Context f2076a;

        /* renamed from: b */
        private final C1697y0 f2077b;

        C1523a(Context context, C1697y0 y0Var) {
            this.f2076a = context;
            this.f2077b = y0Var;
        }

        /* renamed from: a */
        public static final C1523a m3023a(Context context) {
            if (f2075c == null) {
                synchronized (C1523a.class) {
                    if (f2075c == null) {
                        f2075c = new C1523a(context);
                    }
                }
            }
            return f2075c;
        }

        /* renamed from: c */
        private String m3024c() {
            String string = Settings.Secure.getString(this.f2076a.getContentResolver(), "android_id");
            if (string == null) {
                return null;
            }
            return C1392e3.m2563a(string);
        }

        /* renamed from: d */
        private String m3025d() {
            try {
                return String.valueOf(this.f2076a.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode);
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            }
        }

        /* renamed from: e */
        private String m3026e() {
            int identifier = this.f2076a.getResources().getIdentifier("google_play_services_version", "string", this.f2076a.getPackageName());
            if (identifier == 0) {
                return null;
            }
            return String.valueOf(this.f2076a.getResources().getInteger(identifier));
        }

        /* renamed from: a */
        public String mo15964a() {
            return BuildConfig.SDK_VERSION;
        }

        /* renamed from: b */
        public C1522m1 mo15965b() {
            boolean z;
            String str;
            String a = mo15964a();
            C1697y0.C1698a a2 = this.f2077b.mo16270a();
            if (a2 != null) {
                z = a2.mo16272b();
                str = a2.mo16271a();
            } else {
                C1467j0.m2871b(C1400f.m2603b("krJOYpdJwB0z9kroej+tvgvunIIlLf/GdGehIr+r2OSbd/1jAuDbW6Z7w8Rb+zP0p97z+Ss5rCSYnT4eKWDNHxv5azbxwwxG3XGQe+SC2+3s6Z9kUQ084l1qIWDEae3FGWLeg8k8luby4GoV6Q0RRg"), new Object[0]);
                str = null;
                z = false;
            }
            return new C1522m1(a, str, m3024c(), m3025d(), m3026e(), z);
        }

        public C1523a(Context context) {
            this(context, new C1697y0(context));
        }
    }
}

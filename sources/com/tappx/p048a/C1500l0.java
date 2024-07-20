package com.tappx.p048a;

import android.content.Context;
import java.util.Locale;

/* renamed from: com.tappx.a.l0 */
public final class C1500l0 {

    /* renamed from: a */
    public final String f2025a;

    /* renamed from: b */
    public final String f2026b;

    /* renamed from: c */
    public final String f2027c;

    public C1500l0(String str, String str2, String str3) {
        this.f2026b = str;
        this.f2025a = str2;
        this.f2027c = str3;
    }

    /* renamed from: com.tappx.a.l0$a */
    public static class C1501a {

        /* renamed from: b */
        private static volatile C1501a f2028b;

        /* renamed from: a */
        private final Context f2029a;

        public C1501a(Context context) {
            this.f2029a = context;
        }

        /* renamed from: a */
        public static final C1501a m2949a(Context context) {
            if (f2028b == null) {
                synchronized (C1501a.class) {
                    if (f2028b == null) {
                        f2028b = new C1501a(context);
                    }
                }
            }
            return f2028b;
        }

        /* renamed from: b */
        private String m2950b() {
            Locale locale = this.f2029a.getResources().getConfiguration().locale;
            if (locale == null) {
                locale = Locale.getDefault();
            }
            return locale != null ? locale.getLanguage() : "en-us";
        }

        /* renamed from: c */
        private String m2951c() {
            return this.f2029a.getApplicationInfo().loadLabel(this.f2029a.getPackageManager()).toString();
        }

        /* renamed from: a */
        public C1500l0 mo15927a() {
            return new C1500l0(m2951c(), this.f2029a.getPackageName(), m2950b());
        }
    }
}

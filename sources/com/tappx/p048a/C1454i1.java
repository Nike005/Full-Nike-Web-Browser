package com.tappx.p048a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

/* renamed from: com.tappx.a.i1 */
public final class C1454i1 {

    /* renamed from: a */
    public final String f1932a;

    /* renamed from: b */
    public final String f1933b;

    /* renamed from: c */
    public final String f1934c;

    /* renamed from: d */
    public final String f1935d;

    /* renamed from: e */
    public final String f1936e;

    /* renamed from: f */
    public final String f1937f;

    /* renamed from: g */
    public final String f1938g;

    /* renamed from: h */
    public final String f1939h;

    public C1454i1(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.f1932a = str;
        this.f1933b = str2;
        this.f1934c = str3;
        this.f1935d = str4;
        this.f1936e = str5;
        this.f1937f = str6;
        this.f1938g = str7;
        this.f1939h = str8;
    }

    /* renamed from: com.tappx.a.i1$a */
    public static class C1455a {

        /* renamed from: b */
        private static volatile C1455a f1940b;

        /* renamed from: a */
        private final Context f1941a;

        public C1455a(Context context) {
            this.f1941a = context;
        }

        /* renamed from: a */
        public static final C1455a m2837a(Context context) {
            if (f1940b == null) {
                synchronized (C1455a.class) {
                    if (f1940b == null) {
                        f1940b = new C1455a(context.getApplicationContext());
                    }
                }
            }
            return f1940b;
        }

        /* renamed from: b */
        private String m2840b(TelephonyManager telephonyManager) {
            try {
                return telephonyManager.getNetworkOperator();
            } catch (Exception unused) {
                return null;
            }
        }

        /* renamed from: c */
        private String m2841c(TelephonyManager telephonyManager) {
            try {
                return telephonyManager.getNetworkOperatorName();
            } catch (Exception unused) {
                return null;
            }
        }

        /* renamed from: d */
        private String m2842d(TelephonyManager telephonyManager) {
            try {
                return telephonyManager.getSimCountryIso();
            } catch (Exception unused) {
                return null;
            }
        }

        /* renamed from: e */
        private String m2843e(TelephonyManager telephonyManager) {
            try {
                return telephonyManager.getSimOperator();
            } catch (Exception unused) {
                return null;
            }
        }

        /* renamed from: f */
        private String m2844f(TelephonyManager telephonyManager) {
            try {
                return telephonyManager.getSimOperatorName();
            } catch (Exception unused) {
                return null;
            }
        }

        /* renamed from: a */
        public C1454i1 mo15864a() {
            String str;
            String str2;
            String str3;
            String str4;
            String str5;
            String str6;
            String str7;
            String str8;
            NetworkInfo activeNetworkInfo;
            if (!C1366d3.m2453a(this.f1941a, "android.permission.ACCESS_NETWORK_STATE") || (activeNetworkInfo = ((ConnectivityManager) this.f1941a.getSystemService("connectivity")).getActiveNetworkInfo()) == null) {
                str2 = null;
                str = null;
            } else {
                String typeName = activeNetworkInfo.getTypeName();
                str = activeNetworkInfo.getSubtypeName();
                str2 = typeName;
            }
            TelephonyManager telephonyManager = (TelephonyManager) this.f1941a.getSystemService("phone");
            if (telephonyManager != null) {
                String a = m2839a(m2843e(telephonyManager));
                String a2 = m2839a(m2844f(telephonyManager));
                String a3 = m2839a(m2842d(telephonyManager));
                String a4 = m2839a(m2840b(telephonyManager));
                String a5 = m2839a(m2841c(telephonyManager));
                str3 = m2839a(m2838a(telephonyManager));
                str6 = a3;
                str5 = a4;
                str4 = a5;
                str8 = a;
                str7 = a2;
            } else {
                str8 = null;
                str7 = null;
                str6 = null;
                str5 = null;
                str4 = null;
                str3 = null;
            }
            return new C1454i1(str2, str, str8, str7, str6, str5, str4, str3);
        }

        /* renamed from: a */
        private String m2838a(TelephonyManager telephonyManager) {
            try {
                return telephonyManager.getNetworkCountryIso();
            } catch (Exception unused) {
                return null;
            }
        }

        /* renamed from: a */
        private String m2839a(String str) {
            if (str == null || !str.trim().isEmpty()) {
                return str;
            }
            return null;
        }
    }
}

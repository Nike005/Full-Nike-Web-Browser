package com.moat.analytics.mobile.mpub;

import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.mopub.common.GpsHelper;
import java.lang.ref.WeakReference;

/* renamed from: com.moat.analytics.mobile.mpub.s */
class C0339s {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static String f178a;

    /* renamed from: b */
    private static C0341a f179b;

    /* renamed from: c */
    private static C0342b f180c;

    /* renamed from: com.moat.analytics.mobile.mpub.s$a */
    static class C0341a {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public boolean f182a;

        /* renamed from: b */
        private String f183b;

        /* renamed from: c */
        private String f184c;

        /* renamed from: d */
        private String f185d;

        private C0341a() {
            this.f182a = false;
            this.f183b = "_unknown_";
            this.f184c = "_unknown_";
            this.f185d = "_unknown_";
            try {
                Context b = C0339s.m239b();
                if (b != null) {
                    this.f182a = true;
                    PackageManager packageManager = b.getPackageManager();
                    this.f184c = b.getPackageName();
                    this.f183b = packageManager.getApplicationLabel(b.getApplicationInfo()).toString();
                    this.f185d = packageManager.getInstallerPackageName(this.f184c);
                    return;
                }
                C0336p.m228a(3, "Util", (Object) this, "Can't get app name, appContext is null.");
            } catch (Exception e) {
                C0330n.m214a(e);
            }
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public String mo10460a() {
            return this.f183b;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public String mo10461b() {
            return this.f184c;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: c */
        public String mo10462c() {
            String str = this.f185d;
            return str != null ? str : "_unknown_";
        }
    }

    /* renamed from: com.moat.analytics.mobile.mpub.s$b */
    static class C0342b {

        /* renamed from: a */
        String f186a;

        /* renamed from: b */
        String f187b;

        /* renamed from: c */
        Integer f188c;

        /* renamed from: d */
        boolean f189d;

        /* renamed from: e */
        boolean f190e;

        /* renamed from: f */
        boolean f191f;

        private C0342b() {
            this.f186a = "_unknown_";
            this.f187b = "_unknown_";
            this.f188c = -1;
            this.f189d = false;
            this.f190e = false;
            this.f191f = false;
            try {
                Context b = C0339s.m239b();
                if (b != null) {
                    this.f191f = true;
                    TelephonyManager telephonyManager = (TelephonyManager) b.getSystemService("phone");
                    this.f186a = telephonyManager.getSimOperatorName();
                    this.f187b = telephonyManager.getNetworkOperatorName();
                    this.f188c = Integer.valueOf(telephonyManager.getPhoneType());
                    this.f189d = C0339s.m245g();
                    this.f190e = C0339s.m240b(b);
                }
            } catch (Exception e) {
                C0330n.m214a(e);
            }
        }
    }

    C0339s() {
    }

    /* renamed from: a */
    static String m236a() {
        return f178a;
    }

    /* renamed from: a */
    static void m238a(final Context context) {
        try {
            AsyncTask.execute(new Runnable() {
                public void run() {
                    String str;
                    String str2;
                    try {
                        Class<?> cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
                        Class<?> cls2 = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
                        Object invoke = cls.getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke((Object) null, new Object[]{context});
                        if (!((Boolean) cls2.getMethod(GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, new Class[0]).invoke(invoke, new Object[0])).booleanValue()) {
                            String unused = C0339s.f178a = (String) cls2.getMethod("getId", new Class[0]).invoke(invoke, new Object[0]);
                            str2 = "Retrieved Advertising ID = " + C0339s.f178a;
                        } else {
                            str2 = "User has limited ad tracking";
                        }
                        C0336p.m228a(3, "Util", (Object) this, str2);
                    } catch (ClassNotFoundException e) {
                        e = e;
                        str = "ClassNotFoundException while retrieving Advertising ID";
                        C0336p.m230a("Util", (Object) this, str, e);
                    } catch (NoSuchMethodException e2) {
                        e = e2;
                        str = "NoSuchMethodException while retrieving Advertising ID";
                        C0336p.m230a("Util", (Object) this, str, e);
                    } catch (Exception e3) {
                        C0330n.m214a(e3);
                    }
                }
            });
        } catch (Exception e) {
            C0330n.m214a(e);
        }
    }

    /* renamed from: b */
    static Context m239b() {
        WeakReference<Context> weakReference = ((C0327k) MoatAnalytics.getInstance()).f151d;
        if (weakReference != null) {
            return (Context) weakReference.get();
        }
        return null;
    }

    /* renamed from: b */
    static boolean m240b(Context context) {
        return (context.getApplicationInfo().flags & 2) != 0;
    }

    /* renamed from: c */
    static C0341a m241c() {
        C0341a aVar = f179b;
        if (aVar == null || !aVar.f182a) {
            f179b = new C0341a();
        }
        return f179b;
    }

    /* renamed from: d */
    static C0342b m242d() {
        C0342b bVar = f180c;
        if (bVar == null || !bVar.f191f) {
            f180c = new C0342b();
        }
        return f180c;
    }

    /* access modifiers changed from: private */
    /* renamed from: g */
    public static boolean m245g() {
        int i;
        Context b = m239b();
        if (b != null) {
            int i2 = Build.VERSION.SDK_INT;
            ContentResolver contentResolver = b.getContentResolver();
            i = i2 >= 17 ? Settings.Global.getInt(contentResolver, "adb_enabled", 0) : Settings.Secure.getInt(contentResolver, "adb_enabled", 0);
        } else {
            i = 0;
        }
        return i == 1;
    }
}

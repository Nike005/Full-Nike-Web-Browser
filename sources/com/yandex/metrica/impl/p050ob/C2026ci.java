package com.yandex.metrica.impl.p050ob;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.LocalServerSocket;
import android.net.Uri;
import android.text.TextUtils;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.C1887be;
import com.yandex.metrica.impl.C1894bi;
import com.yandex.metrica.impl.C1897bk;
import com.yandex.metrica.impl.C2211r;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.yandex.metrica.impl.ob.ci */
public class C2026ci {

    /* renamed from: a */
    private final Object f3412a;

    /* renamed from: b */
    private final C2027a f3413b;

    /* renamed from: c */
    private final C2031ck f3414c;

    /* renamed from: d */
    private C2025ch f3415d;

    /* renamed from: com.yandex.metrica.impl.ob.ci$b */
    private static class C2028b {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public static final C2026ci f3418a = new C2026ci((byte) 0);
    }

    /* synthetic */ C2026ci(byte b) {
        this();
    }

    /* renamed from: a */
    public static C2026ci m5230a() {
        return C2028b.f3418a;
    }

    private C2026ci() {
        this.f3412a = new Object();
        this.f3413b = new C2027a(this, (byte) 0);
        this.f3414c = new C2031ck(this);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public C2027a mo17475b() {
        return this.f3413b;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public C2025ch mo17476c() {
        return this.f3415d;
    }

    /* renamed from: d */
    public String mo17478d() {
        C2025ch c = mo17476c();
        if (c == null) {
            return null;
        }
        return c.mo17467c();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C2025ch mo17472a(Context context, String str) {
        return m5229a(context, str, context.getFileStreamPath("credentials.dat"));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public C2025ch mo17474b(Context context, String str) {
        return m5229a(context, str, new File(context.getNoBackupFilesDir(), "credentials.dat"));
    }

    /* renamed from: a */
    private C2025ch m5229a(Context context, String str, File file) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 8192);
            if (applicationInfo == null) {
                return null;
            }
            return m5233h(context, file.getAbsolutePath().replace(context.getApplicationInfo().dataDir, applicationInfo.dataDir));
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    /* renamed from: h */
    private C2025ch m5233h(Context context, String str) {
        String a;
        try {
            File file = new File(str);
            if (file.exists()) {
                synchronized (this.f3412a) {
                    a = C2211r.m5892a(context, file);
                }
                if (a == null) {
                    return null;
                }
                return new C2025ch(new JSONObject(a), file.lastModified());
            }
        } catch (Exception | JSONException unused) {
        }
        return null;
    }

    /* renamed from: c */
    public String mo17477c(Context context, String str) {
        return m5234i(context, str);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public boolean mo17481e() {
        return C1897bk.m4650a(21);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public void mo17479d(Context context, String str) {
        try {
            synchronized (this.f3412a) {
                C2025ch chVar = new C2025ch(str, new C2030cj(context), System.currentTimeMillis());
                this.f3415d = chVar;
                String a = chVar.mo17464a();
                if (mo17481e()) {
                    mo17480e(context, a);
                }
                synchronized (this.f3412a) {
                    C2211r.m5895a(context, "credentials.dat", a);
                }
            }
        } catch (JSONException unused) {
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public void mo17480e(Context context, String str) {
        synchronized (this.f3412a) {
            C2211r.m5899b(context, "credentials.dat", str);
        }
    }

    /* renamed from: a */
    public String mo17473a(Context context) {
        return m5234i(context, (String) null);
    }

    /* renamed from: i */
    private String m5234i(Context context, String str) {
        synchronized (this.f3412a) {
            if (mo17476c() == null) {
                C2025ch a = mo17472a(context, context.getPackageName());
                if (a == null) {
                    String a2 = mo17475b().mo17485a(context, str);
                    return a2;
                } else if (mo17481e()) {
                    C2025ch b = mo17474b(context, context.getPackageName());
                    if (!a.mo17465a(b) || !b.mo17469e()) {
                        String a3 = mo17475b().mo17485a(context, a.mo17467c());
                        return a3;
                    }
                    this.f3415d = a;
                    String c = b.mo17467c();
                    return c;
                } else if (a.mo17469e()) {
                    this.f3415d = a;
                    String c2 = a.mo17467c();
                    return c2;
                } else {
                    String a4 = mo17475b().mo17485a(context, a.mo17467c());
                    return a4;
                }
            } else {
                String c3 = mo17476c().mo17467c();
                return c3;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.lang.String} */
    /* JADX WARNING: type inference failed for: r1v2 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r1v5, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* renamed from: f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String mo17483f(android.content.Context r9, java.lang.String r10) {
        /*
            r8 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r10)
            java.lang.String r1 = ".MetricaContentProvider"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            android.content.pm.PackageManager r1 = r9.getPackageManager()
            r2 = 0
            android.content.pm.ProviderInfo r0 = r1.resolveContentProvider(r0, r2)
            r1 = 0
            if (r0 == 0) goto L_0x005e
            boolean r0 = r0.enabled
            if (r0 != 0) goto L_0x0022
            goto L_0x005e
        L_0x0022:
            java.util.Locale r0 = java.util.Locale.US
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]
            r3[r2] = r10
            java.lang.String r10 = "content://%s.MetricaContentProvider/DEVICE_ID"
            java.lang.String r10 = java.lang.String.format(r0, r10, r3)
            android.content.ContentResolver r2 = r9.getContentResolver()     // Catch:{ Exception -> 0x005a, all -> 0x0055 }
            android.net.Uri r3 = android.net.Uri.parse(r10)     // Catch:{ Exception -> 0x005a, all -> 0x0055 }
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r9 = r2.query(r3, r4, r5, r6, r7)     // Catch:{ Exception -> 0x005a, all -> 0x0055 }
            if (r9 == 0) goto L_0x005b
            boolean r10 = r9.moveToFirst()     // Catch:{ Exception -> 0x005b, all -> 0x0052 }
            if (r10 == 0) goto L_0x005b
            java.lang.String r10 = "DEVICE_ID"
            int r10 = r9.getColumnIndex(r10)     // Catch:{ Exception -> 0x005b, all -> 0x0052 }
            java.lang.String r1 = r9.getString(r10)     // Catch:{ Exception -> 0x005b, all -> 0x0052 }
            goto L_0x005b
        L_0x0052:
            r10 = move-exception
            r1 = r9
            goto L_0x0056
        L_0x0055:
            r10 = move-exception
        L_0x0056:
            com.yandex.metrica.impl.C1897bk.m4643a((android.database.Cursor) r1)
            throw r10
        L_0x005a:
            r9 = r1
        L_0x005b:
            com.yandex.metrica.impl.C1897bk.m4643a((android.database.Cursor) r9)
        L_0x005e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.p050ob.C2026ci.mo17483f(android.content.Context, java.lang.String):java.lang.String");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public C2031ck mo17482f() {
        return this.f3414c;
    }

    /* renamed from: com.yandex.metrica.impl.ob.ci$a */
    static class C2027a {

        /* renamed from: a */
        C2026ci f3416a;

        /* renamed from: b */
        private LocalServerSocket f3417b;

        /* synthetic */ C2027a(C2026ci ciVar, byte b) {
            this(ciVar);
        }

        private C2027a(C2026ci ciVar) {
            this.f3416a = ciVar;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public C2026ci mo17484a() {
            return this.f3416a;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: b */
        public boolean mo17487b() {
            try {
                this.f3417b = new LocalServerSocket("com.yandex.metrica.synchronization.deviceid");
                return true;
            } catch (IOException unused) {
                return false;
            }
        }

        /* renamed from: a */
        public String mo17485a(Context context, String str) {
            TextUtils.isEmpty(str);
            mo17484a().mo17482f().mo17492a(context);
            C2071dn dnVar = new C2071dn(12);
            String str2 = null;
            do {
                if (mo17487b()) {
                    str2 = mo17486a(context, str, mo17484a().mo17482f().mo17492a(context));
                    LocalServerSocket localServerSocket = this.f3417b;
                    if (localServerSocket != null) {
                        try {
                            localServerSocket.close();
                            this.f3417b = null;
                        } catch (IOException unused) {
                        }
                    }
                } else {
                    dnVar.mo17608a();
                    dnVar.mo17610c();
                }
            } while (dnVar.mo17609b());
            return str2;
        }

        /* access modifiers changed from: package-private */
        /* renamed from: a */
        public String mo17486a(Context context, String str, String str2) {
            if (TextUtils.isEmpty(str)) {
                if (TextUtils.isEmpty(str2)) {
                    return null;
                }
                C2026ci.m5231a(mo17484a(), context, str2);
                return str2;
            } else if (str.equals(str2)) {
                C2026ci.m5231a(mo17484a(), context, str);
                YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("update_snapshot", (Map<String, Object>) new C2029c(context, str2, str));
                return str;
            } else if (TextUtils.isEmpty(str2)) {
                C2026ci.m5231a(mo17484a(), context, str);
                YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("wtf_situation. App has id and elector hasn't", (Map<String, Object>) new C2029c(context, str2, str));
                return str;
            } else {
                C2026ci.m5231a(mo17484a(), context, str2);
                YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("overlapping_device_id", (Map<String, Object>) new C2029c(context, str2, str));
                return str2;
            }
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.ci$c */
    private static class C2029c extends HashMap<String, Object> {
        public C2029c(Context context, String str) {
            String packageName = context.getPackageName();
            put("passed_id", str);
            put("package_name", packageName);
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
                put("version_code", Integer.valueOf(packageInfo.versionCode));
                put("version_name", packageInfo.versionName);
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }

        public C2029c(Context context, String str, String str2) {
            this(context, str);
            put("stored_device_id", str2);
        }
    }

    /* renamed from: g */
    public static String m5232g(Context context, String str) {
        String a = C2028b.f3418a.mo17473a(context);
        if (!C1894bi.m4622a(a)) {
            Intent a2 = C1887be.m4552a(context);
            a2.setPackage(str);
            for (ResolveInfo resolveInfo : C1887be.m4553a(context, a2)) {
                int a3 = C1887be.m4550a((PackageItemInfo) resolveInfo.serviceInfo);
                if (a3 > 0 && a3 < 29) {
                    try {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("DEVICE_ID", a);
                        if (!C1894bi.m4622a(a)) {
                            context.getContentResolver().update(Uri.parse(String.format(Locale.US, "content://%s.MetricaContentProvider/DEVICE_ID", new Object[]{str})), contentValues, (String) null, (String[]) null);
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        }
        return a;
    }

    /* renamed from: a */
    static /* synthetic */ void m5231a(C2026ci ciVar, Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("saving_empty_device_id", (Map<String, Object>) new C2029c(context, str));
        } else {
            ciVar.mo17479d(context, str);
        }
    }
}

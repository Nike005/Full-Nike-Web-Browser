package com.startapp.android.publish.adsCommon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Pair;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.android.publish.common.metaData.C1231d;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.common.C1302f;
import com.startapp.common.C1303g;
import com.startapp.common.Constants;
import com.startapp.common.p043a.C1261c;
import com.startapp.common.p043a.C1270g;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/* renamed from: com.startapp.android.publish.adsCommon.l */
/* compiled from: StartAppSDK */
public class C1168l {

    /* renamed from: a */
    private static List<PackageInfo> f1249a = null;

    /* renamed from: b */
    private static List<PackageInfo> f1250b = null;

    /* renamed from: c */
    private static long f1251c = 0;

    /* renamed from: d */
    private static Pair<C1173a, String> f1252d = null;

    /* renamed from: e */
    private static Pair<C1173a, String> f1253e = null;

    /* renamed from: f */
    private static boolean f1254f = true;

    /* renamed from: g */
    private static boolean f1255g = false;

    /* renamed from: h */
    private static C1173a f1256h = C1173a.UNDEFINED;

    /* renamed from: com.startapp.android.publish.adsCommon.l$a */
    /* compiled from: StartAppSDK */
    private enum C1173a {
        T1("token"),
        T2("token2"),
        UNDEFINED("");
        
        private final String text;

        private C1173a(String str) {
            this.text = str;
        }

        public String toString() {
            return this.text;
        }
    }

    /* renamed from: a */
    public static long m1627a() {
        return f1251c;
    }

    /* renamed from: a */
    public static void m1630a(final Context context) {
        m1638c(context);
        f1254f = true;
        f1255g = false;
        f1256h = C1173a.UNDEFINED;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        context.getApplicationContext().registerReceiver(new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                C1168l.m1635b();
                C1168l.m1638c(context);
            }
        }, intentFilter);
        MetaData.getInstance().addMetaDataListener(new C1231d() {
            /* renamed from: b */
            public void mo14205b() {
            }

            /* renamed from: a */
            public void mo14204a() {
                C1168l.m1635b();
                C1168l.m1638c(context);
            }
        });
    }

    /* renamed from: b */
    public static void m1636b(Context context) {
        m1631a(context, MetaData.getInstance().getSimpleTokenConfig().mo15265b(context));
    }

    /* renamed from: c */
    public static void m1638c(final Context context) {
        C1270g.m2078a("SimpleToken", 3, "initSimpleTokenAsync entered");
        try {
            if ((f1252d == null || f1253e == null) && MetaData.getInstance().getSimpleTokenConfig().mo15265b(context)) {
                C1303g.m2206a(C1303g.C1307a.HIGH, (Runnable) new Runnable() {
                    public void run() {
                        C1168l.m1636b(context);
                    }
                });
            }
        } catch (Exception e) {
            C1270g.m2079a("SimpleToken", 6, "initSimpleTokenAsync failed", e);
            C1132f.m1527a(context, C1130d.EXCEPTION, "initSimpleTokenAsync", e.getMessage(), "");
        }
    }

    /* renamed from: a */
    public static void m1631a(Context context, boolean z) {
        C1270g.m2078a("SimpleToken", 3, "initSimpleToken entered");
        if ((f1252d == null || f1253e == null) && z) {
            try {
                m1644g(context);
                f1252d = new Pair<>(C1173a.T1, C1302f.m2203a(m1629a(f1249a)));
                f1253e = new Pair<>(C1173a.T2, C1302f.m2203a(m1629a(f1250b)));
                C1270g.m2078a("SimpleToken", 3, "simpleToken : [" + f1252d + "]");
                C1270g.m2078a("SimpleToken", 3, "simpleToken2 : [" + f1253e + "]");
            } catch (Exception e) {
                C1270g.m2079a("SimpleToken", 6, "initSimpleToken failed", e);
                C1132f.m1527a(context, C1130d.EXCEPTION, "initSimpleToken", e.getMessage(), "");
            }
        }
    }

    /* renamed from: b */
    public static void m1635b() {
        f1252d = null;
        f1253e = null;
    }

    /* renamed from: d */
    public static Pair<String, String> m1641d(Context context) {
        return m1628a(context, MetaData.getInstance().getSimpleTokenConfig().mo15265b(context), MetaData.getInstance().isAlwaysSendToken(), MetaData.getInstance().isToken1Mandatory());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004d, code lost:
        if (com.startapp.android.publish.adsCommon.C1166k.m1610a(r4, "shared_prefs_simple_token", "").equals(r2.second) == false) goto L_0x004f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0032 A[Catch:{ Exception -> 0x0062 }] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0033 A[Catch:{ Exception -> 0x0062 }] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x003e A[Catch:{ Exception -> 0x0062 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x003f A[Catch:{ Exception -> 0x0062 }] */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized android.util.Pair<java.lang.String, java.lang.String> m1628a(android.content.Context r4, boolean r5, boolean r6, boolean r7) {
        /*
            java.lang.Class<com.startapp.android.publish.adsCommon.l> r0 = com.startapp.android.publish.adsCommon.C1168l.class
            monitor-enter(r0)
            java.lang.String r1 = "SimpleToken"
            r2 = 3
            java.lang.String r3 = "getSimpleToken entered"
            com.startapp.common.p043a.C1270g.m2078a((java.lang.String) r1, (int) r2, (java.lang.String) r3)     // Catch:{ all -> 0x007c }
            android.util.Pair r1 = new android.util.Pair     // Catch:{ all -> 0x007c }
            com.startapp.android.publish.adsCommon.l$a r2 = com.startapp.android.publish.adsCommon.C1168l.C1173a.T1     // Catch:{ all -> 0x007c }
            java.lang.String r3 = ""
            r1.<init>(r2, r3)     // Catch:{ all -> 0x007c }
            if (r5 == 0) goto L_0x006b
            com.startapp.android.publish.adsCommon.l$a r5 = f1256h     // Catch:{ Exception -> 0x0062 }
            com.startapp.android.publish.adsCommon.l$a r2 = com.startapp.android.publish.adsCommon.C1168l.C1173a.UNDEFINED     // Catch:{ Exception -> 0x0062 }
            if (r5 != r2) goto L_0x0051
            boolean r5 = f1254f     // Catch:{ Exception -> 0x0062 }
            boolean r2 = f1255g     // Catch:{ Exception -> 0x0062 }
            if (r2 == 0) goto L_0x002c
            boolean r2 = f1254f     // Catch:{ Exception -> 0x0062 }
            if (r2 == 0) goto L_0x0027
            goto L_0x002c
        L_0x0027:
            android.util.Pair r2 = m1643f(r4)     // Catch:{ Exception -> 0x0062 }
            goto L_0x0030
        L_0x002c:
            android.util.Pair r2 = m1642e(r4)     // Catch:{ Exception -> 0x0062 }
        L_0x0030:
            if (r7 == 0) goto L_0x0033
            goto L_0x003a
        L_0x0033:
            boolean r5 = f1255g     // Catch:{ Exception -> 0x0062 }
            if (r5 != 0) goto L_0x0039
            r5 = 1
            goto L_0x003a
        L_0x0039:
            r5 = 0
        L_0x003a:
            f1255g = r5     // Catch:{ Exception -> 0x0062 }
            if (r6 == 0) goto L_0x003f
            goto L_0x004f
        L_0x003f:
            java.lang.String r5 = "shared_prefs_simple_token"
            java.lang.String r6 = ""
            java.lang.String r4 = com.startapp.android.publish.adsCommon.C1166k.m1610a((android.content.Context) r4, (java.lang.String) r5, (java.lang.String) r6)     // Catch:{ Exception -> 0x0062 }
            java.lang.Object r5 = r2.second     // Catch:{ Exception -> 0x0062 }
            boolean r4 = r4.equals(r5)     // Catch:{ Exception -> 0x0062 }
            if (r4 != 0) goto L_0x006b
        L_0x004f:
            r1 = r2
            goto L_0x006b
        L_0x0051:
            com.startapp.android.publish.adsCommon.l$a r5 = f1256h     // Catch:{ Exception -> 0x0062 }
            com.startapp.android.publish.adsCommon.l$a r6 = com.startapp.android.publish.adsCommon.C1168l.C1173a.T1     // Catch:{ Exception -> 0x0062 }
            if (r5 != r6) goto L_0x005c
            android.util.Pair r4 = m1642e(r4)     // Catch:{ Exception -> 0x0062 }
            goto L_0x0060
        L_0x005c:
            android.util.Pair r4 = m1643f(r4)     // Catch:{ Exception -> 0x0062 }
        L_0x0060:
            r1 = r4
            goto L_0x006b
        L_0x0062:
            r4 = move-exception
            java.lang.String r5 = "SimpleToken"
            r6 = 6
            java.lang.String r7 = "failed to get simpleToken "
            com.startapp.common.p043a.C1270g.m2079a(r5, r6, r7, r4)     // Catch:{ all -> 0x007c }
        L_0x006b:
            android.util.Pair r4 = new android.util.Pair     // Catch:{ all -> 0x007c }
            java.lang.Object r5 = r1.first     // Catch:{ all -> 0x007c }
            com.startapp.android.publish.adsCommon.l$a r5 = (com.startapp.android.publish.adsCommon.C1168l.C1173a) r5     // Catch:{ all -> 0x007c }
            java.lang.String r5 = r5.toString()     // Catch:{ all -> 0x007c }
            java.lang.Object r6 = r1.second     // Catch:{ all -> 0x007c }
            r4.<init>(r5, r6)     // Catch:{ all -> 0x007c }
            monitor-exit(r0)
            return r4
        L_0x007c:
            r4 = move-exception
            monitor-exit(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.adsCommon.C1168l.m1628a(android.content.Context, boolean, boolean, boolean):android.util.Pair");
    }

    /* renamed from: a */
    public static void m1633a(Pair<String, String> pair) {
        C1270g.m2078a("SimpleToken", 3, "errorSendingToken entered");
        f1256h = C1173a.valueOf((String) pair.first);
    }

    /* renamed from: c */
    public static Pair<String, String> m1637c() {
        if (f1252d != null) {
            return new Pair<>(((C1173a) f1252d.first).toString(), f1252d.second);
        }
        return new Pair<>(C1173a.T1.toString(), "");
    }

    /* renamed from: d */
    public static Pair<String, String> m1640d() {
        if (f1253e != null) {
            return new Pair<>(((C1173a) f1253e.first).toString(), f1253e.second);
        }
        return new Pair<>(C1173a.T2.toString(), "");
    }

    /* renamed from: e */
    private static Pair<C1173a, String> m1642e(Context context) {
        if (f1252d == null) {
            m1636b(context);
        }
        C1166k.m1617b(context, "shared_prefs_simple_token", (String) f1252d.second);
        f1254f = false;
        f1256h = C1173a.UNDEFINED;
        return new Pair<>(C1173a.T1, f1252d.second);
    }

    /* renamed from: f */
    private static Pair<C1173a, String> m1643f(Context context) {
        if (f1253e == null) {
            m1636b(context);
        }
        C1166k.m1617b(context, "shared_prefs_simple_token2", (String) f1253e.second);
        f1254f = false;
        f1256h = C1173a.UNDEFINED;
        return new Pair<>(C1173a.T2, f1253e.second);
    }

    /* renamed from: g */
    private static synchronized void m1644g(Context context) {
        synchronized (C1168l.class) {
            C1270g.m2078a("SimpleToken", 3, "getPackages entered");
            PackageManager packageManager = context.getPackageManager();
            Set<String> installersList = MetaData.getInstance().getInstallersList();
            Set<String> preInstalledPackages = MetaData.getInstance().getPreInstalledPackages();
            f1249a = new CopyOnWriteArrayList();
            f1250b = new CopyOnWriteArrayList();
            try {
                List<PackageInfo> a = C1261c.m2019a(packageManager);
                f1251c = Build.VERSION.SDK_INT >= 9 ? Long.MAX_VALUE : 0;
                PackageInfo packageInfo = null;
                for (PackageInfo next : a) {
                    if (Build.VERSION.SDK_INT >= 9 && f1251c > next.firstInstallTime) {
                        f1251c = next.firstInstallTime;
                    }
                    if (!C1261c.m2033a(next)) {
                        f1249a.add(next);
                        m1632a(next, packageManager, installersList);
                    } else if (preInstalledPackages.contains(next.packageName)) {
                        f1249a.add(next);
                    } else if (next.packageName.equals(Constants.f1469a)) {
                        packageInfo = next;
                    }
                }
                f1249a = m1634b(f1249a);
                f1250b = m1634b(f1250b);
                if (packageInfo != null) {
                    f1249a.add(0, packageInfo);
                }
            } catch (Exception e) {
                C1270g.m2079a("SimpleToken", 6, "Could not complete getInstalledPackages", e);
            }
        }
    }

    /* renamed from: a */
    private static void m1632a(PackageInfo packageInfo, PackageManager packageManager, Set<String> set) {
        try {
            String installerPackageName = packageManager.getInstallerPackageName(packageInfo.packageName);
            if (set != null && set.contains(installerPackageName)) {
                f1250b.add(packageInfo);
            }
        } catch (Exception e) {
            C1270g.m2078a("SimpleToken", 6, "addToPackagesFromInstallers - can't add app to list " + e.getMessage());
        }
    }

    /* renamed from: a */
    private static List<String> m1629a(List<PackageInfo> list) {
        C1270g.m2078a("SimpleToken", 3, "getPackagesNames entered");
        ArrayList arrayList = new ArrayList();
        for (PackageInfo packageInfo : list) {
            arrayList.add(packageInfo.packageName);
        }
        return arrayList;
    }

    /* renamed from: b */
    private static List<PackageInfo> m1634b(List<PackageInfo> list) {
        if (list.size() <= 100) {
            return list;
        }
        ArrayList arrayList = new ArrayList(list);
        m1639c((List<PackageInfo>) arrayList);
        return arrayList.subList(0, 100);
    }

    /* renamed from: c */
    private static void m1639c(List<PackageInfo> list) {
        if (Build.VERSION.SDK_INT >= 9) {
            Collections.sort(list, new Comparator<PackageInfo>() {
                /* renamed from: a */
                public int compare(PackageInfo packageInfo, PackageInfo packageInfo2) {
                    long j = packageInfo.firstInstallTime;
                    long j2 = packageInfo2.firstInstallTime;
                    if (j > j2) {
                        return -1;
                    }
                    return j == j2 ? 0 : 1;
                }
            });
        }
    }
}

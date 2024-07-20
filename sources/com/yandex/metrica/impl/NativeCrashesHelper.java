package com.yandex.metrica.impl;

import android.content.Context;
import java.io.File;
import java.io.FilenameFilter;
import java.util.concurrent.ExecutorService;

class NativeCrashesHelper {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public String f2874a;

    /* renamed from: b */
    private final Context f2875b;

    /* renamed from: c */
    private boolean f2876c;

    /* renamed from: d */
    private boolean f2877d;

    private static native void cancelSetUpNativeUncaughtExceptionHandler();

    private static native void logsEnabled(boolean z);

    private static native void setUpNativeUncaughtExceptionHandler(String str);

    NativeCrashesHelper(Context context) {
        this.f2875b = context;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public synchronized void mo16749a(C1868ay ayVar, ExecutorService executorService) {
        if (m4120c()) {
            executorService.execute(new C1806a(ayVar, this));
            this.f2876c = false;
        }
    }

    /* renamed from: b */
    private boolean m4118b() {
        return this.f2874a != null;
    }

    /* renamed from: c */
    private boolean m4120c() {
        return m4118b() && this.f2876c;
    }

    /* renamed from: b */
    private static boolean m4119b(boolean z) {
        try {
            logsEnabled(z);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: com.yandex.metrica.impl.NativeCrashesHelper$a */
    private static class C1806a implements Runnable {

        /* renamed from: a */
        private final C1868ay f2878a;

        /* renamed from: b */
        private final NativeCrashesHelper f2879b;

        C1806a(C1868ay ayVar, NativeCrashesHelper nativeCrashesHelper) {
            this.f2879b = nativeCrashesHelper;
            this.f2878a = ayVar;
        }

        public void run() {
            File file;
            String a = this.f2879b.f2874a;
            for (String str : NativeCrashesHelper.m4117a(a)) {
                String str2 = a + "/" + str;
                try {
                    String b = C2211r.m5898b(C2211r.m5894a(str2));
                    if (b != null) {
                        this.f2878a.mo16910a(b);
                    }
                    file = new File(str2);
                } catch (Exception unused) {
                    file = new File(str2);
                } catch (Throwable th) {
                    new File(str2).delete();
                    throw th;
                }
                file.delete();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo16751a() {
        try {
            System.loadLibrary("YandexMetricaNativeModule");
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(7:19|20|(1:22)|23|24|25|26) */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r2.f2876c = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0043, code lost:
        return;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0040 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x004f */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:3:0x0004=Splitter:B:3:0x0004, B:23:0x004f=Splitter:B:23:0x004f} */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void mo16750a(boolean r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            r0 = 0
            if (r3 == 0) goto L_0x0046
            boolean r3 = r2.f2877d     // Catch:{ all -> 0x0040 }
            if (r3 != 0) goto L_0x002e
            boolean r3 = r2.mo16751a()     // Catch:{ all -> 0x0040 }
            if (r3 == 0) goto L_0x002e
            m4119b(r0)     // Catch:{ all -> 0x0040 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0040 }
            r3.<init>()     // Catch:{ all -> 0x0040 }
            android.content.Context r1 = r2.f2875b     // Catch:{ all -> 0x0040 }
            java.io.File r1 = r1.getFilesDir()     // Catch:{ all -> 0x0040 }
            java.lang.String r1 = r1.getAbsolutePath()     // Catch:{ all -> 0x0040 }
            r3.append(r1)     // Catch:{ all -> 0x0040 }
            java.lang.String r1 = "/YandexMetricaNativeCrashes"
            r3.append(r1)     // Catch:{ all -> 0x0040 }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x0040 }
            r2.f2874a = r3     // Catch:{ all -> 0x0040 }
        L_0x002e:
            r3 = 1
            r2.f2877d = r3     // Catch:{ all -> 0x0040 }
            boolean r1 = r2.m4118b()     // Catch:{ all -> 0x0040 }
            if (r1 == 0) goto L_0x003e
            java.lang.String r1 = r2.f2874a     // Catch:{ all -> 0x0040 }
            setUpNativeUncaughtExceptionHandler(r1)     // Catch:{ all -> 0x0040 }
            r2.f2876c = r3     // Catch:{ all -> 0x0040 }
        L_0x003e:
            monitor-exit(r2)
            return
        L_0x0040:
            r2.f2876c = r0     // Catch:{ all -> 0x0044 }
            monitor-exit(r2)
            return
        L_0x0044:
            r3 = move-exception
            goto L_0x0053
        L_0x0046:
            boolean r3 = r2.m4120c()     // Catch:{ all -> 0x004f }
            if (r3 == 0) goto L_0x004f
            cancelSetUpNativeUncaughtExceptionHandler()     // Catch:{ all -> 0x004f }
        L_0x004f:
            r2.f2876c = r0     // Catch:{ all -> 0x0044 }
            monitor-exit(r2)
            return
        L_0x0053:
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.NativeCrashesHelper.mo16750a(boolean):void");
    }

    /* renamed from: a */
    static /* synthetic */ String[] m4117a(String str) {
        File file = new File(str + "/");
        if (!file.mkdir() && !file.exists()) {
            return new String[0];
        }
        String[] list = file.list(new FilenameFilter() {
            public boolean accept(File file, String str) {
                return str.endsWith(".dmp");
            }
        });
        return list != null ? list : new String[0];
    }
}

package com.startapp.android.p015b;

import android.content.Context;
import android.os.Build;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/* renamed from: com.startapp.android.b.c */
/* compiled from: StartAppSDK */
public class C0835c {

    /* renamed from: a */
    private static C0833a f396a;

    /* renamed from: a */
    public static boolean m440a(Context context) {
        if (f396a == null) {
            f396a = new C0833a(context.getApplicationContext());
        }
        return f396a.mo13716a() || m439a() || m442b() || m442b() || m444c() || m445d() || m443b(context);
    }

    /* renamed from: a */
    private static boolean m439a() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    /* renamed from: b */
    private static boolean m442b() {
        String[] strArr = {"/system/app/Superuser.apk", "/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su", "/su/bin/su"};
        for (int i = 0; i < 10; i++) {
            if (new File(strArr[i]).exists()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: c */
    private static boolean m444c() {
        boolean z = false;
        Process process = null;
        try {
            Process exec = Runtime.getRuntime().exec(new String[]{"/system/xbin/which", "su"});
            if (new BufferedReader(new InputStreamReader(exec.getInputStream())).readLine() != null) {
                z = true;
            }
            if (exec != null) {
                exec.destroy();
            }
            return z;
        } catch (Throwable unused) {
            if (process != null) {
                process.destroy();
            }
            return false;
        }
    }

    /* renamed from: d */
    private static boolean m445d() {
        try {
            return new File("/system/app/Superuser.apk").exists();
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: b */
    private static boolean m443b(Context context) {
        String[] strArr = {"com.noshufou.android.su", "com.thirdparty.superuser", "eu.chainfire.supersu", "com.koushikdutta.superuser", "com.zachspong.temprootremovejb", "com.ramdroid.appquarantine"};
        for (int i = 0; i < 6; i++) {
            if (m441a(context, strArr[i])) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private static boolean m441a(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}

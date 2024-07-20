package com.startapp.android.p014a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.core.p067os.EnvironmentCompat;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.startapp.android.a.a */
/* compiled from: StartAppSDK */
public final class C0831a {

    /* renamed from: a */
    private static final String[] f372a = {"15555215554", "15555215556", "15555215558", "15555215560", "15555215562", "15555215564", "15555215566", "15555215568", "15555215570", "15555215572", "15555215574", "15555215576", "15555215578", "15555215580", "15555215582", "15555215584"};

    /* renamed from: b */
    private static final String[] f373b = {"000000000000000", "e21833235b6eef10", "012345678912345"};

    /* renamed from: c */
    private static final String[] f374c = {"310260000000000"};

    /* renamed from: d */
    private static final String[] f375d = {"/dev/socket/genyd", "/dev/socket/baseband_genyd"};

    /* renamed from: e */
    private static final String[] f376e = {"goldfish"};

    /* renamed from: f */
    private static final String[] f377f = {"/dev/socket/qemud", "/dev/qemu_pipe"};

    /* renamed from: g */
    private static final String[] f378g = {"ueventd.android_x86.rc", "x86.prop", "ueventd.ttVM_x86.rc", "init.ttVM_x86.rc", "fstab.ttVM_x86", "fstab.vbox86", "init.vbox86.rc", "ueventd.vbox86.rc"};

    /* renamed from: h */
    private static final String[] f379h = {"fstab.andy", "ueventd.andy.rc"};

    /* renamed from: i */
    private static final String[] f380i = {"fstab.nox", "init.nox.rc", "ueventd.nox.rc", "/BigNoxGameHD", "/YSLauncher"};

    /* renamed from: j */
    private static final C0832b[] f381j = {new C0832b("init.svc.qemud", (String) null), new C0832b("init.svc.qemu-props", (String) null), new C0832b("qemu.hw.mainkeys", (String) null), new C0832b("qemu.sf.fake_camera", (String) null), new C0832b("qemu.sf.lcd_density", (String) null), new C0832b("ro.bootloader", EnvironmentCompat.MEDIA_UNKNOWN), new C0832b("ro.bootmode", EnvironmentCompat.MEDIA_UNKNOWN), new C0832b("ro.hardware", "goldfish"), new C0832b("ro.kernel.android.qemud", (String) null), new C0832b("ro.kernel.qemu.gles", (String) null), new C0832b("ro.kernel.qemu", "1"), new C0832b("ro.product.device", "generic"), new C0832b("ro.product.model", "sdk"), new C0832b("ro.product.name", "sdk"), new C0832b("ro.serialno", (String) null), new C0832b("ro.build.description", "72656C656173652D6B657973"), new C0832b("ro.build.fingerprint", "3A757365722F72656C656173652D6B657973"), new C0832b("net.eth0.dns1", (String) null), new C0832b("rild.libpath", "2F73797374656D2F6C69622F6C69627265666572656E63652D72696C2E736F"), new C0832b("ro.radio.use-ppp", (String) null), new C0832b("gsm.version.baseband", (String) null), new C0832b("ro.build.tags", "72656C656173652D6B65"), new C0832b("ro.build.display.id", "746573742D"), new C0832b("init.svc.console", (String) null)};

    /* renamed from: o */
    private static C0831a f382o;

    /* renamed from: p */
    private static Boolean f383p;

    /* renamed from: k */
    private final Context f384k;

    /* renamed from: l */
    private boolean f385l = false;

    /* renamed from: m */
    private boolean f386m = true;

    /* renamed from: n */
    private List<String> f387n;

    /* renamed from: a */
    public static boolean m409a(Context context) {
        if (f383p == null) {
            f383p = Boolean.valueOf(m411b(context).m408a());
        }
        return f383p.booleanValue();
    }

    /* renamed from: b */
    private static C0831a m411b(Context context) {
        if (context != null) {
            if (f382o == null) {
                f382o = new C0831a(context.getApplicationContext());
            }
            return f382o;
        }
        throw new IllegalArgumentException("Context must not be null.");
    }

    private C0831a(Context context) {
        ArrayList arrayList = new ArrayList();
        this.f387n = arrayList;
        this.f384k = context;
        arrayList.add("com.google.android.launcher.layouts.genymotion");
        this.f387n.add("com.bluestacks");
        this.f387n.add("com.bignox.app");
        this.f387n.add("com.vphone.launcher");
    }

    /* renamed from: a */
    private boolean m408a() {
        boolean b = m412b();
        if (!b) {
            b = m414c();
        }
        return !b ? m415d() : b;
    }

    /* renamed from: b */
    private boolean m412b() {
        return Build.FINGERPRINT.startsWith("generic") || Build.MODEL.contains("google_sdk") || Build.MODEL.toLowerCase().contains("droid4x") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for") || Build.MANUFACTURER.contains("Genymotion") || Build.HARDWARE.equals("goldfish") || Build.HARDWARE.equals("vbox86") || Build.PRODUCT.equals("sdk") || Build.PRODUCT.equals("google_sdk") || Build.PRODUCT.equals("sdk_x86") || Build.PRODUCT.equals("vbox86p") || Build.BOARD.toLowerCase().contains("nox") || Build.BOOTLOADER.toLowerCase().contains("nox") || Build.HARDWARE.toLowerCase().contains("nox") || Build.PRODUCT.toLowerCase().contains("nox") || Build.SERIAL.toLowerCase().contains("nox") || Build.FINGERPRINT.startsWith(EnvironmentCompat.MEDIA_UNKNOWN) || Build.FINGERPRINT.contains("Andy") || Build.FINGERPRINT.contains("ttVM_Hdragon") || Build.FINGERPRINT.contains("vbox86p") || Build.HARDWARE.contains("ttVM_x86") || Build.MODEL.equals("sdk") || Build.MODEL.contains("Droid4X") || Build.MODEL.contains("TiantianVM") || Build.MODEL.contains("Andy") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic"));
    }

    /* renamed from: c */
    private boolean m414c() {
        return m416e() || m410a(f375d, "Geny") || m410a(f379h, "Andy") || m410a(f380i, "Nox") || m421j() || m410a(f377f, "Pipes") || m423l() || (m422k() && m410a(f378g, "X86"));
    }

    /* renamed from: d */
    private boolean m415d() {
        if (this.f386m && !this.f387n.isEmpty()) {
            PackageManager packageManager = this.f384k.getPackageManager();
            for (String launchIntentForPackage : this.f387n) {
                Intent launchIntentForPackage2 = packageManager.getLaunchIntentForPackage(launchIntentForPackage);
                if (launchIntentForPackage2 != null && !packageManager.queryIntentActivities(launchIntentForPackage2, 65536).isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: e */
    private boolean m416e() {
        if (!m413b(this.f384k, "android.permission.READ_PHONE_STATE") || !this.f385l || !m424m()) {
            return false;
        }
        if (m417f() || m418g() || m419h() || m420i()) {
            return true;
        }
        return false;
    }

    /* renamed from: f */
    private boolean m417f() {
        TelephonyManager telephonyManager = (TelephonyManager) this.f384k.getSystemService("phone");
        if (telephonyManager != null) {
            String line1Number = telephonyManager.getLine1Number();
            for (String equalsIgnoreCase : f372a) {
                if (equalsIgnoreCase.equalsIgnoreCase(line1Number)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: g */
    private boolean m418g() {
        TelephonyManager telephonyManager = (TelephonyManager) this.f384k.getSystemService("phone");
        if (telephonyManager != null) {
            String deviceId = telephonyManager.getDeviceId();
            for (String equalsIgnoreCase : f373b) {
                if (equalsIgnoreCase.equalsIgnoreCase(deviceId)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: h */
    private boolean m419h() {
        TelephonyManager telephonyManager = (TelephonyManager) this.f384k.getSystemService("phone");
        if (telephonyManager != null) {
            String subscriberId = telephonyManager.getSubscriberId();
            for (String equalsIgnoreCase : f374c) {
                if (equalsIgnoreCase.equalsIgnoreCase(subscriberId)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: i */
    private boolean m420i() {
        TelephonyManager telephonyManager = (TelephonyManager) this.f384k.getSystemService("phone");
        if (telephonyManager != null) {
            return telephonyManager.getNetworkOperatorName().equalsIgnoreCase(SystemMediaRouteProvider.PACKAGE_NAME);
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x006d A[SYNTHETIC, Splitter:B:28:0x006d] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0074 A[SYNTHETIC, Splitter:B:34:0x0074] */
    /* renamed from: j */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m421j() {
        /*
            r12 = this;
            r0 = 2
            java.io.File[] r1 = new java.io.File[r0]
            java.io.File r2 = new java.io.File
            java.lang.String r3 = "/proc/tty/drivers"
            r2.<init>(r3)
            r3 = 0
            r1[r3] = r2
            java.io.File r2 = new java.io.File
            java.lang.String r4 = "/proc/cpuinfo"
            r2.<init>(r4)
            r4 = 1
            r1[r4] = r2
            r2 = 0
        L_0x0018:
            if (r2 >= r0) goto L_0x007b
            r5 = r1[r2]
            boolean r6 = r5.exists()
            if (r6 == 0) goto L_0x0078
            boolean r6 = r5.canRead()
            if (r6 == 0) goto L_0x0078
            r6 = 1024(0x400, float:1.435E-42)
            char[] r6 = new char[r6]
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r8 = 0
            java.io.BufferedReader r9 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0071, all -> 0x006a }
            java.io.InputStreamReader r10 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x0071, all -> 0x006a }
            java.io.FileInputStream r11 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0071, all -> 0x006a }
            r11.<init>(r5)     // Catch:{ Exception -> 0x0071, all -> 0x006a }
            r10.<init>(r11)     // Catch:{ Exception -> 0x0071, all -> 0x006a }
            r9.<init>(r10)     // Catch:{ Exception -> 0x0071, all -> 0x006a }
        L_0x0041:
            int r5 = r9.read(r6)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            r8 = -1
            if (r5 == r8) goto L_0x004c
            r7.append(r6, r3, r5)     // Catch:{ Exception -> 0x0068, all -> 0x0065 }
            goto L_0x0041
        L_0x004c:
            r9.close()     // Catch:{ IOException -> 0x004f }
        L_0x004f:
            java.lang.String r5 = r7.toString()
            java.lang.String[] r6 = f376e
            int r7 = r6.length
            r8 = 0
        L_0x0057:
            if (r8 >= r7) goto L_0x0078
            r9 = r6[r8]
            boolean r9 = r5.contains(r9)
            if (r9 == 0) goto L_0x0062
            return r4
        L_0x0062:
            int r8 = r8 + 1
            goto L_0x0057
        L_0x0065:
            r0 = move-exception
            r8 = r9
            goto L_0x006b
        L_0x0068:
            r8 = r9
            goto L_0x0072
        L_0x006a:
            r0 = move-exception
        L_0x006b:
            if (r8 == 0) goto L_0x0070
            r8.close()     // Catch:{ IOException -> 0x0070 }
        L_0x0070:
            throw r0
        L_0x0071:
        L_0x0072:
            if (r8 == 0) goto L_0x0077
            r8.close()     // Catch:{ IOException -> 0x0077 }
        L_0x0077:
            return r3
        L_0x0078:
            int r2 = r2 + 1
            goto L_0x0018
        L_0x007b:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.p014a.C0831a.m421j():boolean");
    }

    /* renamed from: a */
    private boolean m410a(String[] strArr, String str) {
        File file;
        for (String str2 : strArr) {
            if (!m413b(this.f384k, "android.permission.READ_EXTERNAL_STORAGE") || !str2.contains("/") || !str.equals("Nox")) {
                file = new File(str2);
            } else {
                file = new File(Environment.getExternalStorageDirectory() + str2);
            }
            if (file.exists()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: k */
    private boolean m422k() {
        int i = 0;
        for (C0832b bVar : f381j) {
            String a = m407a(this.f384k, bVar.f388a);
            if (bVar.f389b == null && a != null) {
                i++;
            }
            if (!(bVar.f389b == null || a == null || !a.contains(bVar.f389b))) {
                i++;
            }
        }
        if (i >= 5) {
            return true;
        }
        return false;
    }

    /* renamed from: l */
    private boolean m423l() {
        if (!m413b(this.f384k, "android.permission.INTERNET")) {
            return false;
        }
        String[] strArr = {"/system/bin/netcfg"};
        StringBuilder sb = new StringBuilder();
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(strArr);
            processBuilder.directory(new File("/system/bin/"));
            processBuilder.redirectErrorStream(true);
            InputStream inputStream = processBuilder.start().getInputStream();
            byte[] bArr = new byte[1024];
            while (inputStream.read(bArr) != -1) {
                sb.append(new String(bArr));
            }
            inputStream.close();
        } catch (Exception unused) {
        }
        String sb2 = sb.toString();
        if (TextUtils.isEmpty(sb2)) {
            return false;
        }
        for (String str : sb2.split(StringUtils.f3949LF)) {
            if ((str.contains("wlan0") || str.contains("tunl0") || str.contains("eth0")) && str.contains("10.0.2.15")) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private String m407a(Context context, String str) {
        try {
            Class<?> loadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return (String) loadClass.getMethod("get", new Class[]{String.class}).invoke(loadClass, new Object[]{str});
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: m */
    private boolean m424m() {
        return this.f384k.getPackageManager().hasSystemFeature("android.hardware.telephony");
    }

    /* renamed from: b */
    private boolean m413b(Context context, String str) {
        try {
            if (Build.VERSION.SDK_INT >= 23) {
                if (context.checkSelfPermission(str) == 0) {
                    return true;
                }
                return false;
            } else if (context.checkCallingOrSelfPermission(str) == 0) {
                return true;
            } else {
                return false;
            }
        } catch (Throwable unused) {
            return false;
        }
    }
}

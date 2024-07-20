package com.startapp.android.p015b;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.startapp.android.b.a */
/* compiled from: StartAppSDK */
public class C0833a {

    /* renamed from: a */
    private final Context f390a;

    public C0833a(Context context) {
        this.f390a = context;
    }

    /* renamed from: a */
    public boolean mo13716a() {
        return mo13721c() || mo13722d() || mo13717a("su") || mo13717a("busybox") || mo13724f() || mo13725g() || mo13719b() || mo13726h() || mo13723e();
    }

    /* renamed from: b */
    public boolean mo13719b() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    /* renamed from: c */
    public boolean mo13721c() {
        return mo13718a((String[]) null);
    }

    /* renamed from: a */
    public boolean mo13718a(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(C0834b.f391a));
        if (strArr != null && strArr.length > 0) {
            arrayList.addAll(Arrays.asList(strArr));
        }
        return m425a((List<String>) arrayList);
    }

    /* renamed from: d */
    public boolean mo13722d() {
        return mo13720b((String[]) null);
    }

    /* renamed from: b */
    public boolean mo13720b(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Arrays.asList(C0834b.f392b));
        if (strArr != null && strArr.length > 0) {
            arrayList.addAll(Arrays.asList(strArr));
        }
        return m425a((List<String>) arrayList);
    }

    /* renamed from: e */
    public boolean mo13723e() {
        return mo13717a("magisk");
    }

    /* renamed from: a */
    public boolean mo13717a(String str) {
        boolean z = false;
        for (String file : C0834b.f394d) {
            if (new File(file, str).exists()) {
                z = true;
            }
        }
        return z;
    }

    /* renamed from: i */
    private String[] m426i() {
        String[] strArr = new String[0];
        try {
            return new Scanner(Runtime.getRuntime().exec("getprop").getInputStream()).useDelimiter("\\A").next().split(StringUtils.f3949LF);
        } catch (IOException | NoSuchElementException e) {
            e.printStackTrace();
            return strArr;
        }
    }

    /* renamed from: j */
    private String[] m427j() {
        String[] strArr = new String[0];
        try {
            return new Scanner(Runtime.getRuntime().exec("mount").getInputStream()).useDelimiter("\\A").next().split(StringUtils.f3949LF);
        } catch (IOException | NoSuchElementException e) {
            e.printStackTrace();
            return strArr;
        }
    }

    /* renamed from: a */
    private boolean m425a(List<String> list) {
        PackageManager packageManager = this.f390a.getPackageManager();
        boolean z = false;
        for (String packageInfo : list) {
            try {
                packageManager.getPackageInfo(packageInfo, 0);
                z = true;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return z;
    }

    /* renamed from: f */
    public boolean mo13724f() {
        HashMap hashMap = new HashMap();
        hashMap.put("ro.debuggable", "1");
        hashMap.put("ro.secure", "0");
        boolean z = false;
        for (String str : m426i()) {
            for (String str2 : hashMap.keySet()) {
                if (str.contains(str2)) {
                    if (str.contains("[" + ((String) hashMap.get(str2)) + "]")) {
                        z = true;
                    }
                }
            }
        }
        return z;
    }

    /* renamed from: g */
    public boolean mo13725g() {
        boolean z = false;
        for (String split : m427j()) {
            String[] split2 = split.split(StringUtils.SPACE);
            if (split2.length >= 4) {
                String str = split2[1];
                String str2 = split2[3];
                for (String equalsIgnoreCase : C0834b.f395e) {
                    if (str.equalsIgnoreCase(equalsIgnoreCase)) {
                        String[] split3 = str2.split(",");
                        int length = split3.length;
                        int i = 0;
                        while (true) {
                            if (i >= length) {
                                break;
                            } else if (split3[i].equalsIgnoreCase("rw")) {
                                z = true;
                                break;
                            } else {
                                i++;
                            }
                        }
                    }
                }
            }
        }
        return z;
    }

    /* renamed from: h */
    public boolean mo13726h() {
        boolean z = false;
        Process process = null;
        try {
            Process exec = Runtime.getRuntime().exec(new String[]{"which", "su"});
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
}

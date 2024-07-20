package com.yandex.metrica.impl;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.support.p064v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.io.File;
import java.lang.reflect.Method;
import java.util.Locale;

/* renamed from: com.yandex.metrica.impl.am */
public final class C1838am {

    /* renamed from: com.yandex.metrica.impl.am$a */
    public static final class C1839a {

        /* renamed from: a */
        private static final String[] f2954a = {"/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/"};

        /* renamed from: a */
        public static boolean m4254a() {
            try {
                return new File("/system/app/Superuser.apk").exists();
            } catch (Throwable unused) {
                return false;
            }
        }

        /* renamed from: b */
        public static boolean m4255b() {
            String[] strArr = f2954a;
            int length = strArr.length;
            int i = 0;
            while (i < length) {
                String str = strArr[i];
                try {
                    if (new File(str + "su").exists()) {
                        return true;
                    }
                    i++;
                } catch (Throwable unused) {
                }
            }
            return false;
        }

        /* renamed from: c */
        public static int m4256c() {
            return (m4254a() || m4255b()) ? 1 : 0;
        }
    }

    /* renamed from: a */
    public static Point m4250a(Context context) {
        int i;
        int i2;
        int i3;
        int i4;
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (Build.VERSION.SDK_INT >= 17) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            defaultDisplay.getRealMetrics(displayMetrics);
            i = displayMetrics.widthPixels;
            i2 = displayMetrics.heightPixels;
        } else if (Build.VERSION.SDK_INT >= 14) {
            try {
                Method method = Display.class.getMethod("getRawHeight", new Class[0]);
                int intValue = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                i2 = ((Integer) method.invoke(defaultDisplay, new Object[0])).intValue();
                i = intValue;
            } catch (Exception unused) {
                i4 = defaultDisplay.getWidth();
                i3 = defaultDisplay.getHeight();
            }
        } else {
            i4 = defaultDisplay.getWidth();
            i3 = defaultDisplay.getHeight();
            int i5 = i4;
            i2 = i3;
            i = i5;
        }
        return new Point(i, i2);
    }

    /* renamed from: b */
    public static String m4252b(Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        String country = locale.getCountry();
        StringBuilder sb = new StringBuilder(language);
        if (C1897bk.m4650a(21)) {
            String script = locale.getScript();
            if (!TextUtils.isEmpty(script)) {
                sb.append('-');
                sb.append(script);
            }
        }
        if (!TextUtils.isEmpty(country)) {
            sb.append('_');
            sb.append(country);
        }
        return sb.toString();
    }

    /* renamed from: a */
    public static long m4249a(boolean z) {
        try {
            StatFs b = m4251b(z);
            return (((long) b.getBlockCount()) * ((long) b.getBlockSize())) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        } catch (Throwable unused) {
            return 0;
        }
    }

    /* renamed from: b */
    public static StatFs m4251b(boolean z) {
        if (!z) {
            return new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        }
        return new StatFs(Environment.getRootDirectory().getAbsolutePath());
    }

    /* renamed from: c */
    public static long m4253c(boolean z) {
        try {
            StatFs b = m4251b(z);
            return (((long) b.getAvailableBlocks()) * ((long) b.getBlockSize())) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID;
        } catch (Throwable unused) {
            return 0;
        }
    }
}

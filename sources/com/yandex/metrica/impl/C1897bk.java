package com.yandex.metrica.impl;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.yandex.metrica.CounterConfiguration;
import java.io.Closeable;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

/* renamed from: com.yandex.metrica.impl.bk */
public class C1897bk {
    /* renamed from: a */
    public static String m4638a(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException unused) {
            return "0.0";
        }
    }

    /* renamed from: b */
    public static int m4655b(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            return 0;
        }
    }

    /* renamed from: a */
    public static String m4640a(String str, Throwable th) {
        String a = m4641a(th);
        if (TextUtils.isEmpty(str)) {
            return a;
        }
        return str + ":\n" + a;
    }

    /* renamed from: a */
    static String m4641a(Throwable th) {
        if (th == null) {
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        String obj = stringWriter.toString();
        printWriter.close();
        return obj;
    }

    /* renamed from: a */
    public static boolean m4650a(int i) {
        return Build.VERSION.SDK_INT >= i;
    }

    /* renamed from: b */
    public static boolean m4656b(int i) {
        return Build.VERSION.SDK_INT > i;
    }

    /* renamed from: a */
    public static void m4645a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    public static void m4649a(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    public static void m4643a(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }

    /* renamed from: a */
    public static void m4646a(Object obj, String str) throws IllegalArgumentException {
        if (obj == null) {
            throw new IllegalArgumentException(String.format(Locale.US, "Invalid %s. %s should not be null.", new Object[]{str, str}));
        }
    }

    /* renamed from: a */
    public static void m4648a(String str, String str2) throws IllegalArgumentException {
        if (C1894bi.m4622a(str)) {
            throw new IllegalArgumentException(String.format(Locale.US, "Invalid %s. %s should not be null/empty.", new Object[]{str2, str2}));
        }
    }

    /* renamed from: a */
    public static boolean m4651a(Object obj, Object obj2) {
        if (obj == null && obj2 == null) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return obj.equals(obj2);
    }

    /* renamed from: a */
    public static void m4647a(String str) {
        m4648a(str, "API Key");
        try {
            UUID.fromString(str);
        } catch (Exception unused) {
            throw new IllegalArgumentException(String.format(Locale.US, "Invalid %s = %s. Please, read official documentation how to obtain one: %s", new Object[]{"API Key", str, "https://tech.yandex.com/metrica-mobile-sdk/doc/mobile-sdk-dg/concepts/android-initialize-docpage/"}));
        }
    }

    /* renamed from: a */
    public static List<ResolveInfo> m4642a(Context context, String str, String str2) {
        try {
            Intent intent = new Intent(str, (Uri) null);
            intent.addCategory(str2);
            return context.getPackageManager().queryIntentActivities(intent, 0);
        } catch (Exception unused) {
            return new ArrayList();
        }
    }

    /* renamed from: c */
    public static PackageInfo m4658c(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0);
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static String m4639a(PackageManager packageManager, String str, String str2, String str3) {
        try {
            Bundle bundle = packageManager.getApplicationInfo(str, 128).metaData;
            Object obj = bundle != null ? bundle.get(str2) : null;
            if (obj != null) {
                return obj.toString();
            }
        } catch (Exception unused) {
        }
        return str3;
    }

    /* renamed from: a */
    public static void m4644a(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase != null) {
            try {
                sQLiteDatabase.endTransaction();
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: a */
    public static boolean m4653a(Map map) {
        return map == null || map.size() == 0;
    }

    /* renamed from: a */
    public static boolean m4652a(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    /* renamed from: a */
    public static String m4637a(Context context, CounterConfiguration counterConfiguration, String str) {
        return counterConfiguration.mo16555D() ? counterConfiguration.mo16597j() : m4639a(context.getPackageManager(), str, "metrica:api:key", (String) null);
    }

    /* renamed from: b */
    public static boolean m4657b(String str) {
        return !C1894bi.m4622a(str) && !"-1".equals(str);
    }

    /* renamed from: a */
    public static String[] m4654a(long[] jArr) {
        String[] strArr = new String[jArr.length];
        for (int i = 0; i < jArr.length; i++) {
            strArr[i] = String.valueOf(jArr[i]);
        }
        return strArr;
    }
}

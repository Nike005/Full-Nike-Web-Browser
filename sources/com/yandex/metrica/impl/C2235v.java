package com.yandex.metrica.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import com.yandex.metrica.MetricaEventHandler;
import com.yandex.metrica.MetricaService;
import com.yandex.metrica.YandexMetrica;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.yandex.metrica.impl.v */
public class C2235v {
    /* renamed from: a */
    public static boolean m5978a(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    /* renamed from: a */
    private static boolean m5977a(Context context, Intent intent) {
        List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        String name = MetricaEventHandler.class.getName();
        for (ResolveInfo next : queryBroadcastReceivers) {
            if (next.activityInfo.packageName.equals(context.getPackageName()) && next.activityInfo.name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: com.yandex.metrica.impl.v$a */
    static class C2236a extends RuntimeException {
        public C2236a(String str) {
            super(str);
        }

        public C2236a(String str, String str2) {
            super("\nPlease check " + str + " in AndroidManifest file.\n" + str2);
        }
    }

    /* renamed from: com.yandex.metrica.impl.v$c */
    static class C2238c extends C2236a {
        public C2238c(String str) {
            super(str, "Attribute metrica:api:level should be equal to " + YandexMetrica.getLibraryApiLevel() + ".\n");
        }
    }

    /* renamed from: com.yandex.metrica.impl.v$b */
    static class C2237b extends C2236a {
        public C2237b(String str, String str2) {
            super(str, "It should not include intent-filter with action " + str2 + StringUtils.f3949LF);
        }
    }

    /* renamed from: a */
    public static void m5976a(Context context) {
        if (m5978a("com.yandex.metrica.CounterConfiguration")) {
            boolean z = true;
            boolean z2 = false;
            if ((context.getApplicationInfo().flags & 2) != 0) {
                try {
                    Bundle bundle = context.getPackageManager().getServiceInfo(new ComponentName(context, MetricaService.class), 640).metaData;
                    if (bundle != null && bundle.containsKey("metrica:api:level")) {
                        if (bundle.getInt("metrica:api:level") != YandexMetrica.getLibraryApiLevel()) {
                            z = false;
                        }
                        z2 = z;
                    }
                } catch (Exception unused) {
                }
                if (z2) {
                    String str = MetricaEventHandler.class.getName() + " receiver";
                    Intent intent = new Intent("com.yandex.metrica.intent.action.SYNC");
                    Intent intent2 = new Intent((String) null, Uri.parse("package://fake.data"));
                    if (m5977a(context, intent)) {
                        throw new C2237b(str, "com.yandex.metrica.intent.action.SYNC");
                    } else if (m5977a(context, intent2.setAction("android.intent.action.PACKAGE_DATA_CLEARED"))) {
                        throw new C2237b(str, "android.intent.action.PACKAGE_DATA_CLEARED");
                    } else if (m5977a(context, intent2.setAction("android.intent.action.PACKAGE_ADDED"))) {
                        throw new C2237b(str, "android.intent.action.PACKAGE_ADDED");
                    }
                } else {
                    throw new C2238c(MetricaService.class.getName());
                }
            }
        } else {
            throw new C2236a("\nClass com.yandex.metrica.CounterConfiguration isn't found.\nPerhaps this is due to obfuscation.\nIf you build your application with ProGuard,\nyou need to keep the Metrica for Apps.\nPlease try to use the following lines of code:\n##########################################\n-keep class com.yandex.metrica.** { *; }\n-dontwarn com.yandex.metrica.**\n##########################################");
        }
    }
}

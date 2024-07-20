package com.startapp.android.publish.adsCommon.p032e;

import android.content.Context;
import android.text.TextUtils;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.android.publish.adsCommon.p033f.C1133g;
import com.startapp.common.p044b.C1279a;
import com.startapp.common.p044b.C1289b;
import com.startapp.common.p044b.p045a.C1284a;
import com.startapp.common.p044b.p045a.C1285b;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.Map;

/* renamed from: com.startapp.android.publish.adsCommon.e.a */
/* compiled from: StartAppSDK */
public class C1119a implements Thread.UncaughtExceptionHandler {

    /* renamed from: a */
    private final Thread.UncaughtExceptionHandler f1143a = Thread.getDefaultUncaughtExceptionHandler();

    /* renamed from: com.startapp.android.publish.adsCommon.e.a$a */
    /* compiled from: StartAppSDK */
    public static final class C1120a implements C1284a {
        public C1285b create(int i) {
            if (i != 868418937) {
                return null;
            }
            return new C1285b() {
                /* renamed from: a */
                public void mo14857a(Context context, int i, Map<String, String> map, final C1285b.C1287b bVar) {
                    String str = map.get("KEY_STACK_TRACE");
                    if (!TextUtils.isEmpty(str)) {
                        if (str.length() > 1000) {
                            str = str.substring(0, 1000);
                        }
                        Context context2 = context;
                        C1132f.m1528a(context2, C1130d.EXCEPTION, "uncaughtException", str, "", new C1133g.C1135a() {
                            /* renamed from: a */
                            public void mo14858a(boolean z) {
                                bVar.mo15139a(C1285b.C1286a.SUCCESS);
                            }
                        });
                    }
                }
            };
        }
    }

    /* renamed from: a */
    public static synchronized void m1473a(Context context) {
        synchronized (C1119a.class) {
            new C1119a(context);
        }
    }

    private C1119a(Context context) {
        Thread.setDefaultUncaughtExceptionHandler(this);
        C1279a.m2109a(context);
        C1279a.m2117a((C1284a) new C1120a());
    }

    public void uncaughtException(Thread thread, Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String stringWriter2 = stringWriter.toString();
        if (!TextUtils.isEmpty(stringWriter2) && (stringWriter2.contains("startapp") || stringWriter2.contains("truenet"))) {
            C1279a.m2122a(new C1289b.C1291a(868418937).mo15493a(1000).mo15494a("KEY_STACK_TRACE", stringWriter2).mo15497a());
        }
        this.f1143a.uncaughtException(thread, th);
    }
}

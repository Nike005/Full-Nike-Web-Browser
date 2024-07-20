package com.tappx.p048a;

import android.util.Log;
import java.util.Set;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.tappx.a.j0 */
public class C1467j0 {

    /* renamed from: a */
    private static final Logger f1962a = Logger.getLogger("com.tappx");

    /* renamed from: b */
    private static final C1470c f1963b = new C1470c();

    /* renamed from: c */
    private static boolean f1964c = false;

    /* renamed from: d */
    private static long f1965d;

    /* renamed from: e */
    private static Set<C1469b> f1966e;

    /* renamed from: f */
    private static C1400f f1967f;

    /* renamed from: com.tappx.a.j0$b */
    public interface C1469b {
        /* renamed from: a */
        void mo15880a(String str);
    }

    /* renamed from: com.tappx.a.j0$c */
    private static final class C1470c extends Handler {
        private C1470c() {
        }

        public void close() {
        }

        public void flush() {
        }

        public void publish(LogRecord logRecord) {
            String str = logRecord.getMessage() + StringUtils.f3949LF;
            Throwable thrown = logRecord.getThrown();
            if (thrown != null) {
                str = str + Log.getStackTraceString(thrown);
            }
            Log.println(2, "tappx_v3.1.8", str);
        }
    }

    static {
        f1962a.setLevel(Level.ALL);
        f1962a.addHandler(f1963b);
        LogManager.getLogManager().addLogger(f1962a);
    }

    /* renamed from: a */
    public static void m2869a(String str, Object... objArr) {
        m2870a(Level.FINE, str, objArr);
    }

    /* renamed from: b */
    public static void m2871b(String str, Object... objArr) {
        Level level = Level.SEVERE;
        m2870a(level, "Tappx Error: " + str, objArr);
    }

    /* renamed from: c */
    public static void m2872c(String str, Object... objArr) {
        if (f1964c) {
            long currentTimeMillis = System.currentTimeMillis() - f1965d;
            f1965d = System.currentTimeMillis();
            str = "(+" + currentTimeMillis + " ms) " + str;
            m2874e(str, objArr);
        }
        try {
            m2870a(Level.FINE, String.format(str, objArr), new Object[0]);
        } catch (Exception unused) {
        }
    }

    /* renamed from: d */
    public static void m2873d(String str, Object... objArr) {
        C1400f fVar = f1967f;
        if (fVar != null) {
            m2872c(fVar.mo15760a(str), objArr);
        }
    }

    /* renamed from: e */
    private static void m2874e(String str, Object[] objArr) {
        Set<C1469b> set = f1966e;
        if (set != null) {
            for (C1469b next : set) {
                if (next != null) {
                    try {
                        next.mo15880a(String.format(str, objArr));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /* renamed from: f */
    public static void m2875f(String str, Object... objArr) {
        m2870a(Level.WARNING, str, objArr);
    }

    /* renamed from: a */
    private static void m2870a(Level level, String str, Object... objArr) {
        try {
            f1962a.log(level, String.format(str, objArr));
        } catch (Exception unused) {
        }
    }

    /* renamed from: a */
    public static void m2868a(String str) {
        if (str == null) {
            f1967f = null;
        } else {
            f1967f = new C1400f(str);
        }
    }
}

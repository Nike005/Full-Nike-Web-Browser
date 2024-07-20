package com.tappx.p048a;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* renamed from: com.tappx.a.a6 */
public class C1318a6 {

    /* renamed from: a */
    public static String f1609a = "Volley";

    /* renamed from: b */
    public static boolean f1610b = Log.isLoggable("Volley", 2);

    /* renamed from: c */
    private static final String f1611c = C1318a6.class.getName();

    /* renamed from: a */
    public static void m2232a(Throwable th, String str, Object... objArr) {
        Log.e(f1609a, m2231a(str, objArr), th);
    }

    /* renamed from: b */
    public static void m2233b(String str, Object... objArr) {
        Log.d(f1609a, m2231a(str, objArr));
    }

    /* renamed from: c */
    public static void m2234c(String str, Object... objArr) {
        Log.e(f1609a, m2231a(str, objArr));
    }

    /* renamed from: d */
    public static void m2235d(String str, Object... objArr) {
        if (f1610b) {
            Log.v(f1609a, m2231a(str, objArr));
        }
    }

    /* renamed from: a */
    private static String m2231a(String str, Object... objArr) {
        String str2;
        if (objArr != null) {
            str = String.format(Locale.US, str, objArr);
        }
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        int i = 2;
        while (true) {
            if (i >= stackTrace.length) {
                str2 = "<unknown>";
                break;
            } else if (!stackTrace[i].getClassName().equals(f1611c)) {
                String className = stackTrace[i].getClassName();
                String substring = className.substring(className.lastIndexOf(46) + 1);
                str2 = substring.substring(substring.lastIndexOf(36) + 1) + "." + stackTrace[i].getMethodName();
                break;
            } else {
                i++;
            }
        }
        return String.format(Locale.US, "[%d] %s: %s", new Object[]{Long.valueOf(Thread.currentThread().getId()), str2, str});
    }

    /* renamed from: com.tappx.a.a6$a */
    static class C1319a {

        /* renamed from: c */
        public static final boolean f1612c = C1318a6.f1610b;

        /* renamed from: a */
        private final List<C1320a> f1613a = new ArrayList();

        /* renamed from: b */
        private boolean f1614b = false;

        /* renamed from: com.tappx.a.a6$a$a */
        private static class C1320a {

            /* renamed from: a */
            public final String f1615a;

            /* renamed from: b */
            public final long f1616b;

            /* renamed from: c */
            public final long f1617c;

            public C1320a(String str, long j, long j2) {
                this.f1615a = str;
                this.f1616b = j;
                this.f1617c = j2;
            }
        }

        C1319a() {
        }

        /* renamed from: a */
        public synchronized void mo15533a(String str, long j) {
            if (!this.f1614b) {
                this.f1613a.add(new C1320a(str, j, SystemClock.elapsedRealtime()));
            } else {
                throw new IllegalStateException("Marker added to finished log");
            }
        }

        /* access modifiers changed from: protected */
        public void finalize() {
            if (!this.f1614b) {
                mo15532a("Request on the loose");
                C1318a6.m2234c("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
            }
        }

        /* renamed from: a */
        public synchronized void mo15532a(String str) {
            this.f1614b = true;
            long a = m2236a();
            if (a > 0) {
                long j = this.f1613a.get(0).f1617c;
                C1318a6.m2233b("(%-4d ms) %s", Long.valueOf(a), str);
                for (C1320a next : this.f1613a) {
                    long j2 = next.f1617c;
                    C1318a6.m2233b("(+%-4d) [%2d] %s", Long.valueOf(j2 - j), Long.valueOf(next.f1616b), next.f1615a);
                    j = j2;
                }
            }
        }

        /* renamed from: a */
        private long m2236a() {
            if (this.f1613a.size() == 0) {
                return 0;
            }
            long j = this.f1613a.get(0).f1617c;
            List<C1320a> list = this.f1613a;
            return list.get(list.size() - 1).f1617c - j;
        }
    }
}

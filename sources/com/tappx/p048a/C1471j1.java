package com.tappx.p048a;

import android.content.Context;

/* renamed from: com.tappx.a.j1 */
public class C1471j1 {

    /* renamed from: a */
    public final String f1968a;

    /* renamed from: b */
    public final String f1969b;

    /* renamed from: c */
    public final int f1970c;

    /* renamed from: d */
    public final long f1971d;

    public C1471j1(String str, String str2, int i, long j) {
        this.f1968a = str;
        this.f1969b = str2;
        this.f1970c = i;
        this.f1971d = j;
    }

    /* renamed from: com.tappx.a.j1$a */
    public static class C1472a {

        /* renamed from: b */
        private static volatile C1472a f1972b;

        /* renamed from: a */
        private final Context f1973a;

        public C1472a(Context context) {
            this.f1973a = context;
        }

        /* renamed from: a */
        public static final C1472a m2877a(Context context) {
            if (f1972b == null) {
                synchronized (C1472a.class) {
                    if (f1972b == null) {
                        f1972b = new C1472a(context.getApplicationContext());
                    }
                }
            }
            return f1972b;
        }

        /* renamed from: a */
        public C1471j1 mo15884a() {
            long j;
            int i;
            C1609s2 c = C1552o2.m3165a(this.f1973a).mo16036c().mo15988c();
            Boolean a = c.mo16122a();
            C1566p2 c2 = c.mo16124c();
            long b = c.mo16123b();
            if (c2.mo16051a()) {
                j = b;
                i = 0;
            } else if (c2.mo16053c()) {
                j = b;
                i = 1;
            } else if (Boolean.FALSE.equals(a)) {
                j = 0;
                i = -1;
            } else if (Boolean.TRUE.equals(a)) {
                j = 0;
                i = -2;
            } else {
                j = 0;
                i = -3;
            }
            return new C1471j1(c.mo16125d(), c.mo16126e(), i, j);
        }
    }
}

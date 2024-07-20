package com.tappx.p048a;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* renamed from: com.tappx.a.h5 */
public interface C1445h5 {

    /* renamed from: com.tappx.a.h5$a */
    public static class C1446a {

        /* renamed from: a */
        public byte[] f1915a;

        /* renamed from: b */
        public String f1916b;

        /* renamed from: c */
        public long f1917c;

        /* renamed from: d */
        public long f1918d;

        /* renamed from: e */
        public long f1919e;

        /* renamed from: f */
        public long f1920f;

        /* renamed from: g */
        public Map<String, String> f1921g = Collections.emptyMap();

        /* renamed from: h */
        public List<C1528m5> f1922h;

        /* renamed from: a */
        public boolean mo15849a() {
            return this.f1919e < System.currentTimeMillis();
        }

        /* renamed from: b */
        public boolean mo15850b() {
            return this.f1920f < System.currentTimeMillis();
        }
    }

    /* renamed from: a */
    C1446a mo15747a(String str);

    /* renamed from: a */
    void mo15749a();

    /* renamed from: a */
    void mo15750a(String str, C1446a aVar);
}

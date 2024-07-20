package com.tappx.p048a;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.tappx.a.s4 */
public class C1613s4 {

    /* renamed from: com.tappx.a.s4$a */
    public static class C1614a {

        /* renamed from: a */
        private final Object f2271a;

        /* renamed from: b */
        private final String f2272b;

        /* renamed from: c */
        private Class<?> f2273c;

        /* renamed from: d */
        private List<Class<?>> f2274d = new ArrayList();

        /* renamed from: e */
        private List<Object> f2275e = new ArrayList();

        /* renamed from: f */
        private boolean f2276f;

        /* renamed from: g */
        private boolean f2277g;

        public C1614a(Object obj, String str) {
            this.f2271a = obj;
            this.f2272b = str;
            this.f2273c = obj != null ? obj.getClass() : null;
        }

        /* renamed from: a */
        public Object mo16132a() {
            List<Class<?>> list = this.f2274d;
            Method a = C1613s4.m3353a(this.f2273c, this.f2272b, (Class[]) list.toArray(new Class[this.f2274d.size()]));
            if (this.f2276f) {
                a.setAccessible(true);
            }
            Object[] array = this.f2275e.toArray();
            if (this.f2277g) {
                return a.invoke((Object) null, array);
            }
            return a.invoke(this.f2271a, array);
        }

        /* renamed from: b */
        public C1614a mo16133b() {
            this.f2276f = true;
            return this;
        }
    }

    /* renamed from: a */
    public static Method m3353a(Class<?> cls, String str, Class<?>... clsArr) {
        Class<? super Object> cls2;
        while (cls2 != null) {
            try {
                return cls2.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException unused) {
                Class<? super Object> superclass = cls2.getSuperclass();
                cls2 = cls;
                cls2 = superclass;
            }
        }
        throw new NoSuchMethodException();
    }
}

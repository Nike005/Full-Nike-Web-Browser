package com.moat.analytics.mobile.mpub;

import com.moat.analytics.mobile.mpub.C0351w;
import com.moat.analytics.mobile.mpub.p002a.p003a.C0300a;
import com.moat.analytics.mobile.mpub.p002a.p004b.C0301a;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/* renamed from: com.moat.analytics.mobile.mpub.x */
class C0361x<T> implements InvocationHandler {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static final Object[] f225a = new Object[0];

    /* renamed from: b */
    private final C0363a<T> f226b;

    /* renamed from: c */
    private final Class<T> f227c;

    /* renamed from: d */
    private final LinkedList<C0361x<T>.b> f228d = new LinkedList<>();

    /* renamed from: e */
    private boolean f229e;

    /* renamed from: f */
    private T f230f;

    /* renamed from: com.moat.analytics.mobile.mpub.x$a */
    interface C0363a<T> {
        /* renamed from: a */
        C0301a<T> mo10357a();
    }

    /* renamed from: com.moat.analytics.mobile.mpub.x$b */
    private class C0364b {
        /* access modifiers changed from: private */

        /* renamed from: b */
        public final WeakReference[] f233b;

        /* renamed from: c */
        private final LinkedList<Object> f234c;
        /* access modifiers changed from: private */

        /* renamed from: d */
        public final Method f235d;

        private C0364b(Method method, Object... objArr) {
            this.f234c = new LinkedList<>();
            objArr = objArr == null ? C0361x.f225a : objArr;
            WeakReference[] weakReferenceArr = new WeakReference[objArr.length];
            int length = objArr.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                Object obj = objArr[i];
                if ((obj instanceof Map) || (obj instanceof Integer) || (obj instanceof Double)) {
                    this.f234c.add(obj);
                }
                weakReferenceArr[i2] = new WeakReference(obj);
                i++;
                i2++;
            }
            this.f233b = weakReferenceArr;
            this.f235d = method;
        }
    }

    C0361x(C0363a<T> aVar, Class<T> cls) {
        C0300a.m79a(aVar);
        C0300a.m79a(cls);
        this.f226b = aVar;
        this.f227c = cls;
        C0351w.m264a().mo10463a((C0351w.C0357b) new C0351w.C0357b() {
            /* renamed from: b */
            public void mo10450b() {
                C0361x.this.m292c();
            }

            /* renamed from: c */
            public void mo10451c() {
            }
        });
    }

    /* renamed from: a */
    static <T> T m285a(C0363a<T> aVar, Class<T> cls) {
        ClassLoader classLoader = cls.getClassLoader();
        C0361x xVar = new C0361x(aVar, cls);
        return Proxy.newProxyInstance(classLoader, new Class[]{cls}, xVar);
    }

    /* renamed from: a */
    private Object m286a(Method method) {
        try {
            return Boolean.TYPE.equals(method.getReturnType()) ? true : null;
        } catch (Exception e) {
            C0330n.m214a(e);
            return null;
        }
    }

    /* renamed from: a */
    private Object m287a(Method method, Object[] objArr) {
        Class<?> declaringClass = method.getDeclaringClass();
        C0351w a = C0351w.m264a();
        if (Object.class.equals(declaringClass)) {
            String name = method.getName();
            if ("getClass".equals(name)) {
                return this.f227c;
            }
            boolean equals = "toString".equals(name);
            Object invoke = method.invoke(this, objArr);
            if (!equals) {
                return invoke;
            }
            String name2 = C0361x.class.getName();
            String name3 = this.f227c.getName();
            return (invoke + "").replace(name2, name3);
        } else if (!this.f229e || this.f230f != null) {
            if (a.f197a == C0351w.C0359d.ON) {
                m292c();
                T t = this.f230f;
                if (t != null) {
                    return method.invoke(t, objArr);
                }
            }
            if (a.f197a == C0351w.C0359d.OFF && (!this.f229e || this.f230f != null)) {
                m291b(method, objArr);
            }
            return m286a(method);
        } else {
            this.f228d.clear();
            return m286a(method);
        }
    }

    /* renamed from: b */
    private void m290b() {
        if (!this.f229e) {
            try {
                this.f230f = this.f226b.mo10357a().mo10375c(null);
            } catch (Exception e) {
                C0336p.m230a("OnOffTrackerProxy", (Object) this, "Could not create instance", (Throwable) e);
                C0330n.m214a(e);
            }
            this.f229e = true;
        }
    }

    /* renamed from: b */
    private void m291b(Method method, Object[] objArr) {
        if (this.f228d.size() >= 15) {
            this.f228d.remove(5);
        }
        this.f228d.add(new C0364b(method, objArr));
    }

    /* access modifiers changed from: private */
    /* renamed from: c */
    public void m292c() {
        m290b();
        if (this.f230f != null) {
            Iterator it = this.f228d.iterator();
            while (it.hasNext()) {
                C0364b bVar = (C0364b) it.next();
                try {
                    Object[] objArr = new Object[bVar.f233b.length];
                    WeakReference[] a = bVar.f233b;
                    int length = a.length;
                    int i = 0;
                    int i2 = 0;
                    while (i < length) {
                        objArr[i2] = a[i].get();
                        i++;
                        i2++;
                    }
                    bVar.f235d.invoke(this.f230f, objArr);
                } catch (Exception e) {
                    C0330n.m214a(e);
                }
            }
            this.f228d.clear();
        }
    }

    public Object invoke(Object obj, Method method, Object[] objArr) {
        try {
            return m287a(method, objArr);
        } catch (Exception e) {
            C0330n.m214a(e);
            return m286a(method);
        }
    }
}

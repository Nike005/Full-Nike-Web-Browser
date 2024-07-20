package p055a.p056a.p058b.p060b;

import java.io.Serializable;
import p055a.p056a.p062d.C2943a;
import p055a.p056a.p062d.C2945c;

/* renamed from: a.a.b.b.a */
/* compiled from: StartAppSDK */
public abstract class C2920a implements C2943a, Serializable {

    /* renamed from: a */
    public static final Object f4017a = C2921a.f4019a;

    /* renamed from: b */
    private transient C2943a f4018b;
    protected final Object receiver;

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public abstract C2943a mo24963d();

    /* renamed from: a.a.b.b.a$a */
    /* compiled from: StartAppSDK */
    private static class C2921a implements Serializable {
        /* access modifiers changed from: private */

        /* renamed from: a */
        public static final C2921a f4019a = new C2921a();

        private C2921a() {
        }

        private Object readResolve() {
            return f4019a;
        }
    }

    public C2920a() {
        this(f4017a);
    }

    protected C2920a(Object obj) {
        this.receiver = obj;
    }

    /* renamed from: e */
    public Object mo24964e() {
        return this.receiver;
    }

    /* renamed from: f */
    public C2943a mo24965f() {
        C2943a aVar = this.f4018b;
        if (aVar != null) {
            return aVar;
        }
        C2943a d = mo24963d();
        this.f4018b = d;
        return d;
    }

    /* renamed from: a */
    public C2945c mo16484a() {
        throw new AbstractMethodError();
    }

    /* renamed from: b */
    public String mo16486b() {
        throw new AbstractMethodError();
    }

    /* renamed from: c */
    public String mo16487c() {
        throw new AbstractMethodError();
    }
}

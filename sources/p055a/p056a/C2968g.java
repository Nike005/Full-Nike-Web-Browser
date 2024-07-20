package p055a.p056a;

import java.io.Serializable;
import p055a.p056a.p058b.p059a.C2918a;
import p055a.p056a.p058b.p060b.C2928h;

/* renamed from: a.a.g */
/* compiled from: StartAppSDK */
final class C2968g<T> implements C2937c<T>, Serializable {
    private volatile Object _value;
    private C2918a<? extends T> initializer;
    private final Object lock;

    public C2968g(C2918a<? extends T> aVar, Object obj) {
        C2928h.m6157b(aVar, "initializer");
        this.initializer = aVar;
        this._value = C2970i.f4033a;
        this.lock = obj == null ? this : obj;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C2968g(C2918a aVar, Object obj, int i, C2925e eVar) {
        this(aVar, (i & 2) != 0 ? null : obj);
    }

    /* renamed from: a */
    public T mo24919a() {
        T t;
        T t2 = this._value;
        if (t2 != C2970i.f4033a) {
            return t2;
        }
        synchronized (this.lock) {
            t = this._value;
            if (t == C2970i.f4033a) {
                C2918a aVar = this.initializer;
                if (aVar == null) {
                    C2928h.m6153a();
                }
                t = aVar.mo16475a();
                this._value = t;
                this.initializer = null;
            }
        }
        return t;
    }

    /* renamed from: b */
    public boolean mo24984b() {
        return this._value != C2970i.f4033a;
    }

    public String toString() {
        return mo24984b() ? String.valueOf(mo24919a()) : "Lazy value not initialized yet.";
    }

    private final Object writeReplace() {
        return new C2896a(mo24919a());
    }
}

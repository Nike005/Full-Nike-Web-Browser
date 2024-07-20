package p055a.p056a.p058b.p060b;

import p055a.p056a.p062d.C2943a;
import p055a.p056a.p062d.C2946d;

/* renamed from: a.a.b.b.g */
/* compiled from: StartAppSDK */
public class C2927g extends C2920a implements C2926f, C2946d {
    private final int arity;

    public C2927g(int i, Object obj) {
        super(obj);
        this.arity = i;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public C2943a mo24963d() {
        return C2935n.m6163a(this);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C2927g) {
            C2927g gVar = (C2927g) obj;
            if (mo16484a() != null ? mo16484a().equals(gVar.mo16484a()) : gVar.mo16484a() == null) {
                if (!mo16486b().equals(gVar.mo16486b()) || !mo16487c().equals(gVar.mo16487c()) || !C2928h.m6156a(mo24964e(), gVar.mo24964e())) {
                    return false;
                }
                return true;
            }
            return false;
        } else if (obj instanceof C2946d) {
            return obj.equals(mo24965f());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (((mo16484a() == null ? 0 : mo16484a().hashCode() * 31) + mo16486b().hashCode()) * 31) + mo16487c().hashCode();
    }

    public String toString() {
        C2943a f = mo24965f();
        if (f != this) {
            return f.toString();
        }
        if ("<init>".equals(mo16486b())) {
            return "constructor (Kotlin reflection is not available)";
        }
        return "function " + mo16486b() + " (Kotlin reflection is not available)";
    }
}

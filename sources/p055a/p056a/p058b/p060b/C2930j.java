package p055a.p056a.p058b.p060b;

import p055a.p056a.p062d.C2943a;
import p055a.p056a.p062d.C2947e;

/* renamed from: a.a.b.b.j */
/* compiled from: StartAppSDK */
public abstract class C2930j extends C2920a implements C2947e {
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C2930j) {
            C2930j jVar = (C2930j) obj;
            if (!mo16484a().equals(jVar.mo16484a()) || !mo16486b().equals(jVar.mo16486b()) || !mo16487c().equals(jVar.mo16487c()) || !C2928h.m6156a(mo24964e(), jVar.mo24964e())) {
                return false;
            }
            return true;
        } else if (obj instanceof C2947e) {
            return obj.equals(mo24965f());
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (((mo16484a().hashCode() * 31) + mo16486b().hashCode()) * 31) + mo16487c().hashCode();
    }

    public String toString() {
        C2943a f = mo24965f();
        if (f != this) {
            return f.toString();
        }
        return "property " + mo16486b() + " (Kotlin reflection is not available)";
    }
}

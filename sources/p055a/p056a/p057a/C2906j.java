package p055a.p056a.p057a;

import java.util.Collection;
import p055a.p056a.p058b.p060b.C2928h;

/* renamed from: a.a.a.j */
/* compiled from: StartAppSDK */
class C2906j extends C2905i {
    /* renamed from: a */
    public static final <T> int m6121a(Iterable<? extends T> iterable, int i) {
        C2928h.m6157b(iterable, "$receiver");
        return iterable instanceof Collection ? ((Collection) iterable).size() : i;
    }
}

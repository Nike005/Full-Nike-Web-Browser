package p055a.p056a.p057a;

import java.util.List;
import p055a.p056a.p058b.p060b.C2928h;

/* renamed from: a.a.a.i */
/* compiled from: StartAppSDK */
class C2905i extends C2904h {
    /* renamed from: a */
    public static final <T> List<T> m6118a() {
        return C2915s.f4016a;
    }

    /* renamed from: a */
    public static final <T> List<T> m6119a(T... tArr) {
        C2928h.m6157b(tArr, "elements");
        return tArr.length > 0 ? C2897a.m6114a(tArr) : C2903g.m6118a();
    }

    /* renamed from: a */
    public static final <T> int m6117a(List<? extends T> list) {
        C2928h.m6157b(list, "$receiver");
        return list.size() - 1;
    }

    /* renamed from: b */
    public static final <T> List<T> m6120b(List<? extends T> list) {
        C2928h.m6157b(list, "$receiver");
        int size = list.size();
        if (size == 0) {
            return C2903g.m6118a();
        }
        if (size != 1) {
            return list;
        }
        return C2903g.m6116a(list.get(0));
    }
}

package com.tappx.p048a;

import com.tappx.p048a.C1401f0;
import java.util.Map;
import java.util.TreeMap;

/* renamed from: com.tappx.a.d0 */
public abstract class C1358d0<T> {

    /* renamed from: a */
    private C1401f0.C1403b<T> f1685a;

    /* renamed from: b */
    private C1401f0.C1402a f1686b;

    /* renamed from: c */
    private boolean f1687c;

    /* renamed from: d */
    private C1418g0 f1688d;

    /* renamed from: e */
    private C1360b f1689e = C1360b.NORMAL;

    /* renamed from: com.tappx.a.d0$a */
    public enum C1359a {
        POST,
        GET,
        PUT,
        DELETE,
        HEAD,
        OPTIONS,
        TRACE,
        PATCH
    }

    /* renamed from: com.tappx.a.d0$b */
    public enum C1360b {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    protected C1358d0(C1401f0.C1403b<T> bVar, C1401f0.C1402a aVar) {
        this.f1685a = bVar;
        this.f1686b = aVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract C1401f0<T> mo15648a(C1337c0 c0Var);

    /* renamed from: a */
    public void mo15650a(C1401f0.C1402a aVar) {
        this.f1686b = aVar;
    }

    /* renamed from: a */
    public abstract byte[] mo15654a();

    /* renamed from: b */
    public C1401f0.C1402a mo15655b() {
        return this.f1686b;
    }

    /* renamed from: c */
    public abstract Map<String, String> mo15656c();

    /* renamed from: d */
    public abstract C1359a mo15657d();

    /* renamed from: e */
    public C1360b mo15658e() {
        return this.f1689e;
    }

    /* renamed from: f */
    public C1418g0 mo15659f() {
        return this.f1688d;
    }

    /* renamed from: g */
    public abstract String mo15660g();

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public Map<String, String> mo15661h() {
        return new TreeMap(String.CASE_INSENSITIVE_ORDER);
    }

    /* renamed from: i */
    public boolean mo15662i() {
        return this.f1687c;
    }

    /* renamed from: a */
    public void mo15651a(C1418g0 g0Var) {
        this.f1688d = g0Var;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo15652a(T t) {
        C1401f0.C1403b<T> bVar = this.f1685a;
        if (bVar != null) {
            bVar.mo15763a(t);
        }
    }

    /* renamed from: a */
    public void mo15653a(boolean z) {
        this.f1687c = z;
    }

    /* renamed from: a */
    public void mo15649a(C1360b bVar) {
        this.f1689e = bVar;
    }
}

package com.yandex.metrica.impl.p050ob;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;
import kotlin.text.Typography;

/* renamed from: com.yandex.metrica.impl.ob.fu */
public abstract class C2169fu<T> {

    /* renamed from: a */
    private final int f3741a;

    /* renamed from: b */
    private final String f3742b;

    /* renamed from: c */
    private C2173fw f3743c;

    /* renamed from: d */
    private volatile C2171b<T> f3744d;

    /* renamed from: e */
    private volatile C2170a f3745e;

    /* renamed from: com.yandex.metrica.impl.ob.fu$a */
    public interface C2170a {
        /* renamed from: a */
        void mo17763a(C2162fr frVar);
    }

    /* renamed from: com.yandex.metrica.impl.ob.fu$b */
    public interface C2171b<T> {
        /* renamed from: a */
        void mo17761a(T t);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public abstract T mo17770b(C2168ft ftVar) throws C2162fr;

    /* access modifiers changed from: protected */
    /* renamed from: k */
    public Map<String, String> mo17788k() throws C2162fr {
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: l */
    public String mo17789l() {
        return "UTF-8";
    }

    public C2169fu(int i, String str) {
        this.f3741a = i;
        this.f3742b = str;
        mo17778a(new C2173fw());
    }

    /* renamed from: d */
    public int mo17781d() {
        return this.f3741a;
    }

    /* renamed from: a */
    public C2169fu<?> mo17778a(C2173fw fwVar) {
        this.f3743c = fwVar;
        return this;
    }

    /* renamed from: a */
    public String mo17764a() {
        return this.f3742b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public C2171b<T> mo17782e() {
        return this.f3744d;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo17780a(C2171b<T> bVar) {
        this.f3744d = bVar;
    }

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public C2170a mo17783f() {
        return this.f3745e;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo17779a(C2170a aVar) {
        this.f3745e = aVar;
    }

    /* renamed from: b */
    public Map<String, String> mo17765b() throws C2162fr {
        return Collections.emptyMap();
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public Map<String, String> mo17784g() throws C2162fr {
        return mo17788k();
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public String mo17785h() {
        return mo17789l();
    }

    /* renamed from: i */
    public String mo17786i() {
        return mo17790m();
    }

    /* renamed from: j */
    public byte[] mo17787j() throws C2162fr {
        Map<String, String> g = mo17784g();
        if (g == null || g.size() <= 0) {
            return null;
        }
        return m5724a(g, mo17785h());
    }

    /* renamed from: m */
    public String mo17790m() {
        return "application/x-www-form-urlencoded; charset=" + mo17789l();
    }

    /* renamed from: c */
    public byte[] mo17771c() throws C2162fr {
        Map<String, String> k = mo17788k();
        if (k == null || k.size() <= 0) {
            return null;
        }
        return m5724a(k, mo17789l());
    }

    /* renamed from: a */
    private static byte[] m5724a(Map<String, String> map, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry next : map.entrySet()) {
                sb.append(URLEncoder.encode((String) next.getKey(), str));
                sb.append('=');
                sb.append(URLEncoder.encode((String) next.getValue(), str));
                sb.append(Typography.amp);
            }
            return sb.toString().getBytes(str);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Encoding not supported: " + str, e);
        }
    }

    /* renamed from: n */
    public final int mo17791n() {
        return this.f3743c.mo17798a();
    }

    /* renamed from: o */
    public C2173fw mo17792o() {
        return this.f3743c;
    }
}

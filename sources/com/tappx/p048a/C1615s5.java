package com.tappx.p048a;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.tappx.p048a.C1318a6;
import com.tappx.p048a.C1445h5;
import com.tappx.p048a.C1647u5;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Map;
import kotlin.text.Typography;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.tappx.a.s5 */
public abstract class C1615s5<T> implements Comparable<C1615s5<T>> {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public final C1318a6.C1319a f2278a;

    /* renamed from: b */
    private final int f2279b;

    /* renamed from: c */
    private final String f2280c;

    /* renamed from: d */
    private final int f2281d;

    /* renamed from: e */
    private final Object f2282e;

    /* renamed from: f */
    private C1647u5.C1648a f2283f;

    /* renamed from: g */
    private Integer f2284g;

    /* renamed from: h */
    private C1629t5 f2285h;

    /* renamed from: i */
    private boolean f2286i;

    /* renamed from: j */
    private boolean f2287j;

    /* renamed from: k */
    private boolean f2288k;

    /* renamed from: l */
    private boolean f2289l;

    /* renamed from: m */
    private C1678w5 f2290m;

    /* renamed from: n */
    private C1445h5.C1446a f2291n;

    /* renamed from: o */
    private Object f2292o;

    /* renamed from: p */
    private C1617b f2293p;

    /* renamed from: com.tappx.a.s5$a */
    class C1616a implements Runnable {

        /* renamed from: a */
        final /* synthetic */ String f2294a;

        /* renamed from: b */
        final /* synthetic */ long f2295b;

        C1616a(String str, long j) {
            this.f2294a = str;
            this.f2295b = j;
        }

        public void run() {
            C1615s5.this.f2278a.mo15533a(this.f2294a, this.f2295b);
            C1615s5.this.f2278a.mo15532a(C1615s5.this.toString());
        }
    }

    /* renamed from: com.tappx.a.s5$b */
    interface C1617b {
        /* renamed from: a */
        void mo15874a(C1615s5<?> s5Var);

        /* renamed from: a */
        void mo15875a(C1615s5<?> s5Var, C1647u5<?> u5Var);
    }

    /* renamed from: com.tappx.a.s5$c */
    public enum C1618c {
        LOW,
        NORMAL,
        HIGH,
        IMMEDIATE
    }

    public C1615s5(int i, String str, C1647u5.C1648a aVar) {
        this.f2278a = C1318a6.C1319a.f1612c ? new C1318a6.C1319a() : null;
        this.f2282e = new Object();
        this.f2286i = true;
        this.f2287j = false;
        this.f2288k = false;
        this.f2289l = false;
        this.f2291n = null;
        this.f2279b = i;
        this.f2280c = str;
        this.f2283f = aVar;
        mo16137a((C1678w5) new C1497k5());
        this.f2281d = m3358c(str);
    }

    /* renamed from: c */
    private static int m3358c(String str) {
        Uri parse;
        String host;
        if (TextUtils.isEmpty(str) || (parse = Uri.parse(str)) == null || (host = parse.getHost()) == null) {
            return 0;
        }
        return host.hashCode();
    }

    /* renamed from: a */
    public C1615s5<?> mo16137a(C1678w5 w5Var) {
        this.f2290m = w5Var;
        return this;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract C1647u5<T> mo15856a(C1590q5 q5Var);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract void mo15858a(T t);

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public C1718z5 mo16148b(C1718z5 z5Var) {
        return z5Var;
    }

    /* renamed from: d */
    public C1445h5.C1446a mo16152d() {
        return this.f2291n;
    }

    /* renamed from: e */
    public String mo16153e() {
        String r = mo16163r();
        int g = mo16154g();
        if (g == 0 || g == -1) {
            return r;
        }
        return Integer.toString(g) + '-' + r;
    }

    /* renamed from: f */
    public Map<String, String> mo15860f() {
        return Collections.emptyMap();
    }

    /* renamed from: g */
    public int mo16154g() {
        return this.f2279b;
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public Map<String, String> mo16155h() {
        return null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public String mo16156i() {
        return "UTF-8";
    }

    @Deprecated
    /* renamed from: j */
    public byte[] mo15861j() {
        Map<String, String> k = mo16157k();
        if (k == null || k.size() <= 0) {
            return null;
        }
        return m3356a(k, mo16158l());
    }

    /* access modifiers changed from: protected */
    @Deprecated
    /* renamed from: k */
    public Map<String, String> mo16157k() {
        return mo16155h();
    }

    /* access modifiers changed from: protected */
    @Deprecated
    /* renamed from: l */
    public String mo16158l() {
        return mo16156i();
    }

    /* renamed from: m */
    public C1618c mo15862m() {
        return C1618c.NORMAL;
    }

    /* renamed from: n */
    public C1678w5 mo16159n() {
        return this.f2290m;
    }

    /* renamed from: o */
    public Object mo16160o() {
        return this.f2292o;
    }

    /* renamed from: p */
    public final int mo16161p() {
        return mo16159n().mo15922a();
    }

    /* renamed from: q */
    public int mo16162q() {
        return this.f2281d;
    }

    /* renamed from: r */
    public String mo16163r() {
        return this.f2280c;
    }

    /* renamed from: s */
    public boolean mo16164s() {
        boolean z;
        synchronized (this.f2282e) {
            z = this.f2288k;
        }
        return z;
    }

    /* renamed from: t */
    public boolean mo16165t() {
        boolean z;
        synchronized (this.f2282e) {
            z = this.f2287j;
        }
        return z;
    }

    public String toString() {
        String str = "0x" + Integer.toHexString(mo16162q());
        StringBuilder sb = new StringBuilder();
        sb.append(mo16165t() ? "[X] " : "[ ] ");
        sb.append(mo16163r());
        sb.append(StringUtils.SPACE);
        sb.append(str);
        sb.append(StringUtils.SPACE);
        sb.append(mo15862m());
        sb.append(StringUtils.SPACE);
        sb.append(this.f2284g);
        return sb.toString();
    }

    /* renamed from: u */
    public void mo16167u() {
        synchronized (this.f2282e) {
            this.f2288k = true;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: v */
    public void mo16168v() {
        C1617b bVar;
        synchronized (this.f2282e) {
            bVar = this.f2293p;
        }
        if (bVar != null) {
            bVar.mo15874a(this);
        }
    }

    /* renamed from: w */
    public final boolean mo16169w() {
        return this.f2286i;
    }

    /* renamed from: x */
    public final boolean mo16170x() {
        return this.f2289l;
    }

    /* renamed from: a */
    public void mo16144a(String str) {
        if (C1318a6.C1319a.f1612c) {
            this.f2278a.mo15533a(str, Thread.currentThread().getId());
        }
    }

    /* renamed from: b */
    public C1615s5<?> mo16146b(Object obj) {
        this.f2292o = obj;
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo16149b(String str) {
        C1629t5 t5Var = this.f2285h;
        if (t5Var != null) {
            t5Var.mo16191b(this);
        }
        if (C1318a6.C1319a.f1612c) {
            long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new C1616a(str, id));
                return;
            }
            this.f2278a.mo15533a(str, id);
            this.f2278a.mo15532a(toString());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16140a(int i) {
        C1629t5 t5Var = this.f2285h;
        if (t5Var != null) {
            t5Var.mo16187a(this, i);
        }
    }

    /* renamed from: a */
    public C1615s5<?> mo16136a(C1629t5 t5Var) {
        this.f2285h = t5Var;
        return this;
    }

    /* renamed from: a */
    public C1615s5<?> mo16135a(C1445h5.C1446a aVar) {
        this.f2291n = aVar;
        return this;
    }

    /* renamed from: c */
    public String mo16150c() {
        return "application/x-www-form-urlencoded; charset=" + mo16156i();
    }

    /* renamed from: a */
    public void mo16139a() {
        synchronized (this.f2282e) {
            this.f2287j = true;
            this.f2283f = null;
        }
    }

    /* renamed from: a */
    private byte[] m3356a(Map<String, String> map, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            for (Map.Entry next : map.entrySet()) {
                if (next.getKey() == null || next.getValue() == null) {
                    throw new IllegalArgumentException(String.format("Request#getParams() or Request#getPostParams() returned a map containing a null key or value: (%s, %s). All keys and values must be non-null.", new Object[]{next.getKey(), next.getValue()}));
                }
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

    /* renamed from: b */
    public final C1615s5<?> mo16145b(int i) {
        this.f2284g = Integer.valueOf(i);
        return this;
    }

    /* renamed from: b */
    public byte[] mo15859b() {
        Map<String, String> h = mo16155h();
        if (h == null || h.size() <= 0) {
            return null;
        }
        return m3356a(h, mo16156i());
    }

    /* renamed from: b */
    public final C1615s5<?> mo16147b(boolean z) {
        this.f2289l = z;
        return this;
    }

    /* renamed from: a */
    public final C1615s5<?> mo16138a(boolean z) {
        this.f2286i = z;
        return this;
    }

    /* renamed from: a */
    public void mo16143a(C1718z5 z5Var) {
        C1647u5.C1648a aVar;
        synchronized (this.f2282e) {
            aVar = this.f2283f;
        }
        if (aVar != null) {
            aVar.mo15863a(z5Var);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16141a(C1617b bVar) {
        synchronized (this.f2282e) {
            this.f2293p = bVar;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo16142a(C1647u5<?> u5Var) {
        C1617b bVar;
        synchronized (this.f2282e) {
            bVar = this.f2293p;
        }
        if (bVar != null) {
            bVar.mo15875a(this, u5Var);
        }
    }

    /* renamed from: a */
    public int compareTo(C1615s5<T> s5Var) {
        C1618c m = mo15862m();
        C1618c m2 = s5Var.mo15862m();
        return m == m2 ? this.f2284g.intValue() - s5Var.f2284g.intValue() : m2.ordinal() - m.ordinal();
    }
}

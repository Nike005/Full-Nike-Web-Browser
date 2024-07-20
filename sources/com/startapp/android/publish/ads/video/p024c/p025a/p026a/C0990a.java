package com.startapp.android.publish.ads.video.p024c.p025a.p026a;

import com.startapp.common.p043a.C1270g;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.startapp.android.publish.ads.video.c.a.a.a */
/* compiled from: StartAppSDK */
public class C0990a {

    /* renamed from: a */
    private String f766a;

    /* renamed from: b */
    private Integer f767b;

    /* renamed from: c */
    private Integer f768c;

    /* renamed from: d */
    private Integer f769d;

    /* renamed from: e */
    private Integer f770e;

    /* renamed from: f */
    private Integer f771f;

    /* renamed from: g */
    private Integer f772g;

    /* renamed from: h */
    private String f773h;

    /* renamed from: i */
    private Integer f774i;

    /* renamed from: j */
    private List<C0993d> f775j;

    /* renamed from: k */
    private String f776k;

    /* renamed from: l */
    private List<String> f777l;

    /* renamed from: m */
    private List<String> f778m;

    /* renamed from: a */
    private boolean m910a(int i) {
        return i > 0;
    }

    /* renamed from: a */
    public void mo14301a(String str) {
        this.f766a = str;
    }

    /* renamed from: a */
    public Integer mo14299a() {
        return this.f767b;
    }

    /* renamed from: a */
    public void mo14300a(Integer num) {
        this.f767b = num;
    }

    /* renamed from: b */
    public Integer mo14302b() {
        return this.f768c;
    }

    /* renamed from: b */
    public void mo14303b(Integer num) {
        this.f768c = num;
    }

    /* renamed from: c */
    public Integer mo14305c() {
        return this.f769d;
    }

    /* renamed from: c */
    public void mo14306c(Integer num) {
        this.f769d = num;
    }

    /* renamed from: d */
    public Integer mo14308d() {
        return this.f770e;
    }

    /* renamed from: d */
    public void mo14309d(Integer num) {
        this.f770e = num;
    }

    /* renamed from: e */
    public void mo14311e(Integer num) {
        this.f771f = num;
    }

    /* renamed from: f */
    public void mo14313f(Integer num) {
        this.f772g = num;
    }

    /* renamed from: b */
    public void mo14304b(String str) {
        this.f773h = str;
    }

    /* renamed from: g */
    public void mo14315g(Integer num) {
        this.f774i = num;
    }

    /* renamed from: e */
    public List<C0993d> mo14310e() {
        if (this.f775j == null) {
            this.f775j = new ArrayList();
        }
        return this.f775j;
    }

    /* renamed from: c */
    public void mo14307c(String str) {
        this.f776k = str;
    }

    /* renamed from: f */
    public List<String> mo14312f() {
        if (this.f777l == null) {
            this.f777l = new ArrayList();
        }
        return this.f777l;
    }

    /* renamed from: g */
    public List<String> mo14314g() {
        if (this.f778m == null) {
            this.f778m = new ArrayList();
        }
        return this.f778m;
    }

    /* renamed from: h */
    public boolean mo14316h() {
        Integer b = mo14302b();
        Integer a = mo14299a();
        if (b == null || a == null || !m910a(b.intValue()) || !m910a(a.intValue())) {
            C1270g.m2078a("VASTIcon", 3, "Validator error: VASTIcon invalid size");
            return false;
        }
        Integer c = mo14305c();
        Integer d = mo14308d();
        if (c == null || d == null || !m910a(c.intValue()) || !m910a(d.intValue())) {
            C1270g.m2078a("VASTIcon", 3, "Validator error: VASTIcon invalid position");
            return false;
        } else if (mo14310e().size() != 0) {
            return true;
        } else {
            C1270g.m2078a("VASTIcon", 3, "Validator error: VASTIcon no resources");
            return false;
        }
    }
}

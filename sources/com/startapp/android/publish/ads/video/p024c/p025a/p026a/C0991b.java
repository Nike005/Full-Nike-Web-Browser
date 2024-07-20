package com.startapp.android.publish.ads.video.p024c.p025a.p026a;

import android.text.TextUtils;
import com.startapp.common.p043a.C1270g;

/* renamed from: com.startapp.android.publish.ads.video.c.a.a.b */
/* compiled from: StartAppSDK */
public class C0991b {

    /* renamed from: a */
    private String f779a;

    /* renamed from: b */
    private String f780b;

    /* renamed from: c */
    private String f781c;

    /* renamed from: d */
    private String f782d;

    /* renamed from: e */
    private Integer f783e;

    /* renamed from: f */
    private Integer f784f;

    /* renamed from: g */
    private Integer f785g;

    /* renamed from: h */
    private Boolean f786h;

    /* renamed from: i */
    private Boolean f787i;

    /* renamed from: j */
    private String f788j;

    /* renamed from: a */
    private boolean m929a(int i) {
        return i > 0 && i < 5000;
    }

    /* renamed from: a */
    public String mo14317a() {
        return this.f779a;
    }

    /* renamed from: a */
    public void mo14320a(String str) {
        this.f779a = str;
    }

    /* renamed from: b */
    public void mo14324b(String str) {
        this.f780b = str;
    }

    /* renamed from: c */
    public void mo14327c(String str) {
        this.f781c = str;
    }

    /* renamed from: b */
    public String mo14321b() {
        return this.f782d;
    }

    /* renamed from: d */
    public void mo14329d(String str) {
        this.f782d = str;
    }

    /* renamed from: c */
    public Integer mo14325c() {
        return this.f783e;
    }

    /* renamed from: a */
    public void mo14319a(Integer num) {
        this.f783e = num;
    }

    /* renamed from: d */
    public Integer mo14328d() {
        return this.f784f;
    }

    /* renamed from: b */
    public void mo14323b(Integer num) {
        this.f784f = num;
    }

    /* renamed from: e */
    public Integer mo14330e() {
        return this.f785g;
    }

    /* renamed from: c */
    public void mo14326c(Integer num) {
        this.f785g = num;
    }

    /* renamed from: a */
    public void mo14318a(Boolean bool) {
        this.f786h = bool;
    }

    /* renamed from: b */
    public void mo14322b(Boolean bool) {
        this.f787i = bool;
    }

    /* renamed from: e */
    public void mo14331e(String str) {
        this.f788j = str;
    }

    /* renamed from: f */
    public boolean mo14332f() {
        if (TextUtils.isEmpty(mo14321b())) {
            C1270g.m2078a("VASTMediaFile", 3, "Validator error: mediaFile type empty");
            return false;
        }
        Integer e = mo14330e();
        Integer d = mo14328d();
        if (e == null || d == null || !m929a(e.intValue()) || !m929a(d.intValue())) {
            C1270g.m2078a("VASTMediaFile", 3, "Validator error: mediaFile invalid size");
            return false;
        } else if (!TextUtils.isEmpty(mo14317a())) {
            return true;
        } else {
            C1270g.m2078a("VASTMediaFile", 3, "Validator error: mediaFile url empty");
            return false;
        }
    }

    public String toString() {
        return "MediaFile [url=" + this.f779a + ", id=" + this.f780b + ", delivery=" + this.f781c + ", type=" + this.f782d + ", bitrate=" + this.f783e + ", width=" + this.f784f + ", height=" + this.f785g + ", scalable=" + this.f786h + ", maintainAspectRatio=" + this.f787i + ", apiFramework=" + this.f788j + "]";
    }
}

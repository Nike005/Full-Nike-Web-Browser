package com.appnext.base.p078a.p080b;

import android.text.TextUtils;
import org.json.JSONObject;

/* renamed from: com.appnext.base.a.b.c */
public class C4887c extends C4888d {

    /* renamed from: dK */
    private String f4572dK;

    /* renamed from: dL */
    private String f4573dL;

    /* renamed from: dM */
    private String f4574dM;

    /* renamed from: dN */
    private String f4575dN;

    /* renamed from: dO */
    private String f4576dO;

    /* renamed from: dP */
    private String f4577dP;

    /* renamed from: dQ */
    private String f4578dQ;

    /* renamed from: dR */
    private JSONObject f4579dR;

    public C4887c(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        this.f4572dK = str;
        this.f4573dL = str2;
        this.f4574dM = str3;
        this.f4575dN = str4;
        this.f4576dO = str5;
        this.f4577dP = str6;
        this.f4578dQ = str7;
        if (TextUtils.isEmpty(str8)) {
            this.f4579dR = null;
            return;
        }
        try {
            this.f4579dR = new JSONObject(str8);
        } catch (Throwable unused) {
            this.f4579dR = null;
        }
    }

    /* renamed from: ak */
    public final String mo40958ak() {
        return this.f4572dK;
    }

    /* renamed from: al */
    public final String mo40959al() {
        return this.f4573dL;
    }

    /* renamed from: am */
    public final String mo40960am() {
        return this.f4574dM;
    }

    /* renamed from: an */
    public final String mo40961an() {
        return this.f4575dN;
    }

    /* renamed from: ao */
    public final String mo40962ao() {
        return this.f4576dO;
    }

    public final String getKey() {
        return this.f4577dP;
    }

    /* renamed from: ap */
    public final String mo40963ap() {
        return this.f4578dQ;
    }

    /* renamed from: aq */
    public final JSONObject mo40964aq() {
        return this.f4579dR;
    }

    /* renamed from: p */
    private boolean m6488p(String str) {
        JSONObject jSONObject = this.f4579dR;
        return jSONObject != null && jSONObject.has(str) && !this.f4579dR.isNull(str);
    }

    /* renamed from: d */
    public final String mo40965d(String str, String str2) {
        if (!m6488p(str)) {
            return str2;
        }
        try {
            return this.f4579dR.getString(str);
        } catch (Throwable unused) {
            return str2;
        }
    }

    /* renamed from: a */
    public final long mo40956a(String str, long j) {
        if (!m6488p(str)) {
            return 1;
        }
        try {
            return this.f4579dR.getLong(str);
        } catch (Throwable unused) {
            return 1;
        }
    }

    /* renamed from: a */
    public final int mo40955a(String str, int i) {
        if (!m6488p(str)) {
            return i;
        }
        try {
            return this.f4579dR.getInt(str);
        } catch (Throwable unused) {
            return i;
        }
    }

    /* renamed from: a */
    public final boolean mo40957a(String str, boolean z) {
        if (!m6488p(str)) {
            return z;
        }
        try {
            return this.f4579dR.getBoolean(str);
        } catch (Throwable unused) {
            return z;
        }
    }
}

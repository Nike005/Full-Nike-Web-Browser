package com.moat.analytics.mobile.mpub;

import android.os.Build;
import com.appnext.base.p082b.C4899d;
import com.moat.analytics.mobile.mpub.C0351w;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.moat.analytics.mobile.mpub.m */
class C0329m {

    /* renamed from: a */
    private boolean f160a = false;

    /* renamed from: b */
    private boolean f161b = false;

    /* renamed from: c */
    private boolean f162c = false;

    /* renamed from: d */
    private int f163d = 200;

    /* renamed from: e */
    private int f164e = 10;

    C0329m(String str) {
        m204a(str);
    }

    /* renamed from: a */
    private void m204a(String str) {
        int i;
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString("sa");
                boolean equals = string.equals("422d7e65812d34458dfd0c5f14e8141470b6e2ae");
                boolean equals2 = string.equals("8f1d08a2d6496191a5ebae8f0590f513e2619489");
                if ((string.equals(C4899d.f4618fe) || equals || equals2) && !m205a(jSONObject) && !m206b(jSONObject)) {
                    this.f160a = true;
                    this.f161b = equals;
                    this.f162c = equals2;
                    if (equals2) {
                        this.f161b = true;
                    }
                }
                if (jSONObject.has("in") && (i = jSONObject.getInt("in")) >= 100 && i <= 1000) {
                    this.f163d = i;
                }
                if (jSONObject.has("es")) {
                    this.f164e = jSONObject.getInt("es");
                }
            } catch (Exception e) {
                this.f160a = false;
                this.f161b = false;
                this.f163d = 200;
                C0330n.m214a(e);
            }
        }
    }

    /* renamed from: a */
    private boolean m205a(JSONObject jSONObject) {
        try {
            if (16 > Build.VERSION.SDK_INT) {
                return true;
            }
            if (jSONObject.has("ob")) {
                JSONArray jSONArray = jSONObject.getJSONArray("ob");
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    if (jSONArray.getInt(i) == Build.VERSION.SDK_INT) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    /* renamed from: b */
    private boolean m206b(JSONObject jSONObject) {
        try {
            if (jSONObject.has("ap")) {
                String b = C0339s.m241c().mo10461b();
                JSONArray jSONArray = jSONObject.getJSONArray("ap");
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    if (jSONArray.getString(i).contentEquals(b)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            C0330n.m214a(e);
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo10453a() {
        return this.f161b;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean mo10454b() {
        return this.f162c;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public int mo10455c() {
        return this.f163d;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public int mo10456d() {
        return this.f164e;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public C0351w.C0359d mo10457e() {
        return this.f160a ? C0351w.C0359d.ON : C0351w.C0359d.OFF;
    }
}

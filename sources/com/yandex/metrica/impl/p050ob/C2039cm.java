package com.yandex.metrica.impl.p050ob;

import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.yandex.metrica.impl.ob.cm */
public final class C2039cm {

    /* renamed from: a */
    private final String f3429a;

    /* renamed from: b */
    private final int f3430b;

    /* renamed from: c */
    private final boolean f3431c;

    public C2039cm(JSONObject jSONObject) throws JSONException {
        this.f3429a = jSONObject.getString("name");
        this.f3431c = jSONObject.getBoolean("required");
        this.f3430b = jSONObject.optInt("version", -1);
    }

    public C2039cm(String str, int i, boolean z) {
        this.f3429a = str;
        this.f3430b = i;
        this.f3431c = z;
    }

    public C2039cm(String str, boolean z) {
        this(str, -1, z);
    }

    /* renamed from: a */
    public JSONObject mo17499a() throws JSONException {
        JSONObject put = new JSONObject().put("name", this.f3429a).put("required", this.f3431c);
        int i = this.f3430b;
        if (i != -1) {
            put.put("version", i);
        }
        return put;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            C2039cm cmVar = (C2039cm) obj;
            if (this.f3430b != cmVar.f3430b || this.f3431c != cmVar.f3431c) {
                return false;
            }
            String str = this.f3429a;
            String str2 = cmVar.f3429a;
            if (str != null) {
                return str.equals(str2);
            }
            if (str2 == null) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.f3429a;
        return ((((str != null ? str.hashCode() : 0) * 31) + this.f3430b) * 31) + (this.f3431c ? 1 : 0);
    }
}

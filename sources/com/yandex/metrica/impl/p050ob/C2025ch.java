package com.yandex.metrica.impl.p050ob;

import java.util.Calendar;
import java.util.GregorianCalendar;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.yandex.metrica.impl.ob.ch */
public class C2025ch {

    /* renamed from: a */
    private final String f3407a;

    /* renamed from: b */
    private final C2030cj f3408b;

    /* renamed from: c */
    private final long f3409c;

    /* renamed from: d */
    private final boolean f3410d;

    /* renamed from: e */
    private final long f3411e;

    public C2025ch(JSONObject jSONObject, long j) throws JSONException {
        this.f3407a = jSONObject.getString("device_id");
        if (jSONObject.has("device_snapshot_key")) {
            this.f3408b = new C2030cj(jSONObject.getString("device_snapshot_key"));
        } else {
            this.f3408b = null;
        }
        this.f3409c = jSONObject.optLong("last_elections_time", -1);
        this.f3410d = m5222f();
        this.f3411e = j;
    }

    public C2025ch(String str, C2030cj cjVar, long j) {
        this.f3407a = str;
        this.f3408b = cjVar;
        this.f3409c = j;
        this.f3410d = m5222f();
        this.f3411e = -1;
    }

    /* renamed from: a */
    public String mo17464a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("device_id", this.f3407a);
        C2030cj cjVar = this.f3408b;
        if (cjVar != null) {
            jSONObject.put("device_snapshot_key", cjVar.mo17488a());
        }
        jSONObject.put("last_elections_time", this.f3409c);
        return jSONObject.toString();
    }

    /* renamed from: b */
    public boolean mo17466b() {
        if (this.f3411e > -1) {
            Calendar instance = GregorianCalendar.getInstance();
            instance.setTimeInMillis(this.f3411e);
            if (instance.get(1) == 1970) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: c */
    public String mo17467c() {
        return this.f3407a;
    }

    /* renamed from: d */
    public C2030cj mo17468d() {
        return this.f3408b;
    }

    /* renamed from: e */
    public boolean mo17469e() {
        return this.f3410d;
    }

    /* renamed from: f */
    private boolean m5222f() {
        if (this.f3409c <= -1 || System.currentTimeMillis() - this.f3409c >= 604800000) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public boolean mo17465a(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            C2025ch chVar = (C2025ch) obj;
            if (this.f3410d != chVar.f3410d || !this.f3407a.equals(chVar.f3407a)) {
                return false;
            }
            C2030cj cjVar = this.f3408b;
            C2030cj cjVar2 = chVar.f3408b;
            if (cjVar != null) {
                return cjVar.equals(cjVar2);
            }
            if (cjVar2 == null) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.f3407a.hashCode() * 31;
        C2030cj cjVar = this.f3408b;
        return ((hashCode + (cjVar != null ? cjVar.hashCode() : 0)) * 31) + (this.f3410d ? 1 : 0);
    }

    public String toString() {
        return "Credentials{mFresh=" + this.f3410d + ", mLastElectionsTime=" + this.f3409c + ", mDeviceSnapshot=" + this.f3408b + ", mDeviceID='" + this.f3407a + '\'' + '}';
    }
}

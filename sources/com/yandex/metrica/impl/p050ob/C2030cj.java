package com.yandex.metrica.impl.p050ob;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import com.yandex.metrica.impl.C1838am;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.yandex.metrica.impl.ob.cj */
public class C2030cj {

    /* renamed from: a */
    private final String f3419a;

    /* renamed from: b */
    private final String f3420b;

    /* renamed from: c */
    private final String f3421c;

    /* renamed from: d */
    private final Point f3422d;

    public C2030cj(Context context) {
        this.f3419a = Build.MANUFACTURER;
        this.f3420b = Build.MODEL;
        this.f3421c = Build.VERSION.SDK_INT > 8 ? Build.SERIAL : "";
        int i = C1838am.m4250a(context).y;
        int i2 = C1838am.m4250a(context).x;
        this.f3422d = new Point(Math.min(i, i2), Math.max(i, i2));
    }

    public C2030cj(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str);
        this.f3419a = jSONObject.getString("manufacturer");
        this.f3420b = jSONObject.getString("model");
        this.f3421c = jSONObject.getString("serial");
        this.f3422d = new Point(jSONObject.getInt("width"), jSONObject.getInt("height"));
    }

    /* renamed from: a */
    public JSONObject mo17488a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("manufacturer", this.f3419a);
        jSONObject.put("model", this.f3420b);
        jSONObject.put("serial", this.f3421c);
        jSONObject.put("width", this.f3422d.x);
        jSONObject.put("height", this.f3422d.y);
        return jSONObject;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            C2030cj cjVar = (C2030cj) obj;
            String str = this.f3419a;
            if (str == null ? cjVar.f3419a != null : !str.equals(cjVar.f3419a)) {
                return false;
            }
            String str2 = this.f3420b;
            if (str2 == null ? cjVar.f3420b != null : !str2.equals(cjVar.f3420b)) {
                return false;
            }
            String str3 = this.f3421c;
            if (str3 == null ? cjVar.f3421c != null : !str3.equals(cjVar.f3421c)) {
                return false;
            }
            Point point = this.f3422d;
            Point point2 = cjVar.f3422d;
            if (point != null) {
                return point.equals(point2);
            }
            if (point2 == null) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        String str = this.f3419a;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.f3420b;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.f3421c;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        Point point = this.f3422d;
        if (point != null) {
            i = point.hashCode();
        }
        return hashCode3 + i;
    }

    public String toString() {
        return "DeviceShapshot{mManufacturer='" + this.f3419a + '\'' + ", mModel='" + this.f3420b + '\'' + ", mSerial='" + this.f3421c + '\'' + ", mScreenSize=" + this.f3422d + '}';
    }
}

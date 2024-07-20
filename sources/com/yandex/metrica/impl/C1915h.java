package com.yandex.metrica.impl;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.yandex.metrica.impl.C2208p;
import com.yandex.metrica.impl.p050ob.C2052cw;
import com.yandex.metrica.impl.p050ob.C2198t;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.yandex.metrica.impl.h */
public class C1915h {

    /* renamed from: a */
    String f3168a;

    /* renamed from: b */
    String f3169b;

    /* renamed from: c */
    int f3170c;

    /* renamed from: d */
    int f3171d;

    /* renamed from: e */
    int f3172e;

    /* renamed from: f */
    private C1916a f3173f = new C1916a((byte) 0);

    /* renamed from: g */
    private String f3174g;

    /* renamed from: h */
    private String f3175h;

    /* renamed from: i */
    private String f3176i;

    /* renamed from: j */
    private Bundle f3177j;

    /* renamed from: k */
    private int f3178k = 2;

    /* renamed from: l */
    private String f3179l;

    public C1915h() {
    }

    public C1915h(C1915h hVar) {
        if (hVar != null) {
            this.f3168a = hVar.mo17115a();
            this.f3169b = hVar.mo17117b();
            this.f3170c = hVar.mo17118c();
            this.f3171d = hVar.mo17120d();
            this.f3174g = hVar.mo17132k();
            this.f3176i = hVar.mo17133l();
            this.f3175h = hVar.mo17130i();
            this.f3173f.f3180a = hVar.mo17123e();
            this.f3173f.f3181b = hVar.mo17126f();
            this.f3173f.f3182c = hVar.mo17129h();
            this.f3177j = hVar.mo17131j();
            this.f3172e = hVar.mo17136o();
            this.f3178k = hVar.mo17137p();
            this.f3179l = hVar.mo17138q();
        }
    }

    public C1915h(String str, String str2, int i) {
        this.f3168a = str2;
        this.f3170c = i;
        this.f3169b = str;
    }

    /* renamed from: a */
    public String mo17115a() {
        return this.f3168a;
    }

    /* renamed from: b */
    public C1915h mo17106b(String str) {
        this.f3168a = str;
        return this;
    }

    /* renamed from: b */
    public String mo17117b() {
        return this.f3169b;
    }

    /* renamed from: c */
    public C1915h mo17107c(String str) {
        this.f3169b = str;
        return this;
    }

    /* renamed from: c */
    public int mo17118c() {
        return this.f3170c;
    }

    /* renamed from: a */
    public C1915h mo17111a(int i) {
        this.f3170c = i;
        return this;
    }

    /* renamed from: d */
    public int mo17120d() {
        return this.f3171d;
    }

    /* renamed from: b */
    public C1915h mo17116b(int i) {
        this.f3171d = i;
        return this;
    }

    /* renamed from: e */
    public Location mo17123e() {
        return this.f3173f.f3180a;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C1915h mo17112a(Location location) {
        this.f3173f.f3180a = location;
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: f */
    public String mo17126f() {
        return this.f3173f.f3181b;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: g */
    public JSONArray mo17128g() {
        try {
            return new JSONArray(this.f3173f.f3181b);
        } catch (Exception unused) {
            return new JSONArray();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public C1915h mo17122d(String str) {
        this.f3173f.f3181b = str;
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: h */
    public Integer mo17129h() {
        return this.f3173f.f3182c;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C1915h mo17113a(Integer num) {
        this.f3173f.f3182c = num;
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: i */
    public String mo17130i() {
        return this.f3175h;
    }

    /* renamed from: j */
    public Bundle mo17131j() {
        return this.f3177j;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public C1915h mo17124e(String str) {
        this.f3175h = str;
        return this;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public C1915h mo17114a(String str, String str2) {
        if (this.f3177j == null) {
            this.f3177j = new Bundle();
        }
        this.f3177j.putString(str, str2);
        return this;
    }

    /* renamed from: k */
    public String mo17132k() {
        return this.f3174g;
    }

    /* renamed from: a */
    public C1915h mo17105a(String str) {
        this.f3174g = str;
        return this;
    }

    /* renamed from: l */
    public String mo17133l() {
        return this.f3176i;
    }

    /* renamed from: f */
    public C1915h mo17125f(String str) {
        this.f3176i = str;
        return this;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public C1915h mo17119c(int i) {
        this.f3172e = i;
        return this;
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public C1915h mo17121d(int i) {
        this.f3178k = i;
        return this;
    }

    /* access modifiers changed from: protected */
    /* renamed from: g */
    public C1915h mo17127g(String str) {
        this.f3179l = str;
        return this;
    }

    /* renamed from: m */
    public boolean mo17134m() {
        return this.f3168a == null;
    }

    /* renamed from: n */
    public boolean mo17135n() {
        return C2208p.C2209a.EVENT_TYPE_UNDEFINED.mo17884a() == this.f3170c;
    }

    /* renamed from: o */
    public int mo17136o() {
        return this.f3172e;
    }

    /* renamed from: p */
    public int mo17137p() {
        return this.f3178k;
    }

    /* renamed from: q */
    public String mo17138q() {
        return this.f3179l;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public Bundle mo17110a(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putString("CounterReport.Event", this.f3168a);
        bundle2.putString("CounterReport.Value", this.f3169b);
        bundle2.putInt("CounterReport.Type", this.f3170c);
        bundle2.putInt("CounterReport.CustomType", this.f3171d);
        bundle2.putString("CounterReport.Wifi", this.f3173f.f3181b);
        bundle2.putByteArray("CounterReport.GeoLocation", C2241y.m5988b(this.f3173f.f3180a));
        bundle2.putInt("CounterReport.TRUNCATED", this.f3172e);
        bundle2.putInt("CounterReport.ConnectionType", this.f3178k);
        bundle2.putString("CounterReport.CellularConnectionType", this.f3179l);
        if (this.f3173f.f3182c != null) {
            bundle2.putInt("CounterReport.CellId", this.f3173f.f3182c.intValue());
        }
        String str = this.f3175h;
        if (str != null) {
            bundle2.putString("CounterReport.Environment", str);
        }
        String str2 = this.f3174g;
        if (str2 != null) {
            bundle2.putString("CounterReport.UserInfo", str2);
        }
        String str3 = this.f3176i;
        if (str3 != null) {
            bundle2.putString("CounterReport.PackageName", str3);
        }
        Bundle bundle3 = this.f3177j;
        if (bundle3 != null) {
            bundle2.putBundle("CounterReport.AppEnvironmentDiff", bundle3);
        }
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putBundle("CounterReport.Object", bundle2);
        return bundle;
    }

    /* renamed from: b */
    public static C1915h m4731b(Bundle bundle) {
        Bundle bundle2 = bundle.containsKey("CounterReport.Object") ? bundle.getBundle("CounterReport.Object") : new Bundle();
        Object obj = bundle2.get("CounterReport.TRUNCATED");
        int i = 0;
        if (obj != null) {
            if (obj instanceof Boolean) {
                i = ((Boolean) obj).booleanValue();
            } else if (obj instanceof Integer) {
                i = ((Integer) obj).intValue();
            }
        }
        C1915h f = new C1915h().mo17111a(bundle2.getInt("CounterReport.Type", C2208p.C2209a.EVENT_TYPE_UNDEFINED.mo17884a())).mo17116b(bundle2.getInt("CounterReport.CustomType")).mo17112a(C2241y.m5984a(bundle2.getByteArray("CounterReport.GeoLocation"))).mo17107c(C1894bi.m4626b(bundle2.getString("CounterReport.Value"), "")).mo17105a(bundle2.getString("CounterReport.UserInfo")).mo17124e(bundle2.getString("CounterReport.Environment")).mo17122d(bundle2.getString("CounterReport.Wifi")).mo17113a((Integer) bundle2.get("CounterReport.CellId")).mo17106b(bundle2.getString("CounterReport.Event")).mo17125f(bundle2.getString("CounterReport.PackageName"));
        f.f3177j = bundle2.getBundle("CounterReport.AppEnvironmentDiff");
        return f.mo17119c(i).mo17121d(bundle2.getInt("CounterReport.ConnectionType")).mo17127g(bundle2.getString("CounterReport.CellularConnectionType"));
    }

    /* renamed from: a */
    public static C1915h m4728a(C1915h hVar, C2208p.C2209a aVar) {
        C1915h hVar2 = new C1915h(hVar);
        hVar2.mo17106b(aVar.mo17885b());
        hVar2.mo17111a(aVar.mo17884a());
        return hVar2;
    }

    /* renamed from: a */
    public static C1915h m4730a(C2198t tVar, C1915h hVar) {
        Context m = tVar.mo17866m();
        C2214t a = new C2214t(hVar.mo17117b()).mo17889a();
        try {
            if (tVar.mo17835G()) {
                a.mo17890a(m);
            }
            if (tVar.mo17860h().mo16962H()) {
                a.mo17891a(m, tVar.mo17860h().mo16963I());
            }
        } catch (Exception unused) {
        }
        C1915h hVar2 = new C1915h(hVar);
        hVar2.mo17111a(C2208p.C2209a.EVENT_TYPE_IDENTITY.mo17884a()).mo17107c(a.mo17894d());
        return hVar2;
    }

    /* renamed from: a */
    public static C1915h m4729a(C1915h hVar, List<C2052cw> list) {
        String str;
        C1915h hVar2 = new C1915h(hVar);
        try {
            JSONArray jSONArray = new JSONArray();
            for (C2052cw next : list) {
                jSONArray.put(new JSONObject().put("name", next.mo17513b()).put("granted", next.mo17512a()));
            }
            str = new JSONObject().put("permissions", jSONArray).toString();
        } catch (JSONException unused) {
            str = "";
        }
        return hVar2.mo17111a(C2208p.C2209a.EVENT_TYPE_PERMISSIONS.mo17884a()).mo17107c(str);
    }

    public String toString() {
        return String.format(Locale.US, "[event: %s, type: %d, value: %s]", new Object[]{this.f3168a, Integer.valueOf(this.f3170c), this.f3169b});
    }

    /* renamed from: com.yandex.metrica.impl.h$a */
    private static final class C1916a {

        /* renamed from: a */
        Location f3180a;

        /* renamed from: b */
        String f3181b;

        /* renamed from: c */
        Integer f3182c;

        private C1916a() {
        }

        /* synthetic */ C1916a(byte b) {
            this();
        }
    }
}

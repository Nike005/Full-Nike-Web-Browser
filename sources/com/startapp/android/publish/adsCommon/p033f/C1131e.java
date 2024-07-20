package com.startapp.android.publish.adsCommon.p033f;

import android.content.Context;
import android.telephony.CellInfo;
import android.telephony.TelephonyManager;
import android.util.Pair;
import com.startapp.android.mediation.admob.StartAppNative;
import com.startapp.android.publish.adsCommon.BaseRequest;
import com.startapp.android.publish.adsCommon.C1168l;
import com.startapp.android.publish.adsCommon.Utils.C1054c;
import com.startapp.android.publish.adsCommon.Utils.C1056e;
import com.startapp.common.p043a.C1253a;
import com.startapp.common.p043a.C1261c;
import com.startapp.common.p043a.C1270g;
import java.util.List;
import org.json.JSONArray;

/* renamed from: com.startapp.android.publish.adsCommon.f.e */
/* compiled from: StartAppSDK */
public class C1131e extends BaseRequest {

    /* renamed from: a */
    private C1130d f1180a;

    /* renamed from: b */
    private String f1181b;

    /* renamed from: c */
    private String f1182c;

    /* renamed from: d */
    private String f1183d;

    /* renamed from: e */
    private String f1184e;

    /* renamed from: f */
    private String f1185f;

    /* renamed from: g */
    private String f1186g;

    /* renamed from: h */
    private Long f1187h;

    /* renamed from: i */
    private String f1188i;

    /* renamed from: j */
    private String f1189j;

    /* renamed from: k */
    private JSONArray f1190k;

    public C1131e(C1130d dVar) {
        this(dVar, "", "");
    }

    public C1131e(C1130d dVar, String str, String str2) {
        this.f1180a = dVar;
        this.f1181b = str;
        this.f1182c = str2;
    }

    public C1056e getNameValueJson() {
        C1056e nameValueJson = super.getNameValueJson();
        if (nameValueJson == null) {
            nameValueJson = new C1054c();
        }
        m1507a(nameValueJson);
        return nameValueJson;
    }

    /* renamed from: a */
    private void m1507a(C1056e eVar) {
        String d = C1253a.m1989d();
        eVar.mo14631a(C1253a.m1981a(), (Object) d, true);
        eVar.mo14631a(C1253a.m1985b(), (Object) C1253a.m1986b(d), true);
        eVar.mo14631a(StartAppNative.EXTRAS_CATEGORY, (Object) mo14888e().mo14884a(), true);
        eVar.mo14631a("value", (Object) mo14890f(), false);
        eVar.mo14631a("d", (Object) mo14894h(), false);
        eVar.mo14631a("orientation", (Object) mo14896i(), false);
        eVar.mo14631a("usedRam", (Object) mo14898j(), false);
        eVar.mo14631a("freeRam", (Object) mo14899k(), false);
        eVar.mo14631a("sessionTime", (Object) mo14900l(), false);
        eVar.mo14631a("appActivity", (Object) mo14901m(), false);
        eVar.mo14631a("details", (Object) mo14892g(), false);
        eVar.mo14631a("details_json", (Object) mo14902n(), false);
        eVar.mo14631a("cellScanRes", (Object) mo14903o(), false);
        Pair<String, String> c = C1168l.m1637c();
        Pair<String, String> d2 = C1168l.m1640d();
        eVar.mo14631a((String) c.first, c.second, false);
        eVar.mo14631a((String) d2.first, d2.second, false);
    }

    /* renamed from: e */
    public C1130d mo14888e() {
        return this.f1180a;
    }

    /* renamed from: f */
    public String mo14890f() {
        return this.f1181b;
    }

    /* renamed from: d */
    public void mo14887d(String str) {
        this.f1181b = str;
    }

    /* renamed from: g */
    public String mo14892g() {
        return this.f1182c;
    }

    /* renamed from: h */
    public String mo14894h() {
        return this.f1183d;
    }

    /* renamed from: e */
    public void mo14889e(String str) {
        this.f1183d = str;
    }

    /* renamed from: i */
    public String mo14896i() {
        return this.f1184e;
    }

    /* renamed from: f */
    public void mo14891f(String str) {
        this.f1184e = str;
    }

    /* renamed from: j */
    public String mo14898j() {
        return this.f1185f;
    }

    /* renamed from: g */
    public void mo14893g(String str) {
        this.f1185f = str;
    }

    /* renamed from: k */
    public String mo14899k() {
        return this.f1186g;
    }

    /* renamed from: h */
    public void mo14895h(String str) {
        this.f1186g = str;
    }

    /* renamed from: l */
    public Long mo14900l() {
        return this.f1187h;
    }

    /* renamed from: m */
    public String mo14901m() {
        return this.f1188i;
    }

    /* renamed from: n */
    public JSONArray mo14902n() {
        return this.f1190k;
    }

    /* renamed from: a */
    public void mo14886a(JSONArray jSONArray) {
        this.f1190k = jSONArray;
    }

    /* renamed from: o */
    public String mo14903o() {
        return this.f1189j;
    }

    /* renamed from: i */
    public void mo14897i(String str) {
        this.f1189j = str;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo14885a(Context context) {
        List<CellInfo> a;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager != null && (a = C1261c.m2018a(context, telephonyManager)) != null && a.size() > 0) {
                mo14897i(C1253a.m1988c(a.toString()));
            }
        } catch (Exception e) {
            C1270g.m2076a(6, "Cannot fillCellDetails " + e.getMessage());
        }
    }

    public String toString() {
        return "InfoEventRequest [category=" + this.f1180a.mo14884a() + ", value=" + this.f1181b + ", details=" + this.f1182c + ", d=" + this.f1183d + ", orientation=" + this.f1184e + ", usedRam=" + this.f1185f + ", freeRam=" + this.f1186g + ", sessionTime=" + this.f1187h + ", appActivity=" + this.f1188i + ", details_json=" + this.f1190k + ", cellScanRes=" + this.f1189j + "]";
    }
}

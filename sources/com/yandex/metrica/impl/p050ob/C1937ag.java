package com.yandex.metrica.impl.p050ob;

import android.content.pm.FeatureInfo;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.yandex.metrica.impl.C1897bk;
import com.yandex.metrica.impl.C1915h;
import com.yandex.metrica.impl.C2208p;
import com.yandex.metrica.impl.p050ob.C2036cl;
import com.yandex.metrica.impl.utils.C2219d;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.yandex.metrica.impl.ob.ag */
public class C1937ag extends C1936af {
    protected C1937ag(C2198t tVar) {
        super(tVar);
    }

    /* renamed from: a */
    public boolean mo17175a(C1915h hVar) {
        C2198t a = mo17176a();
        if (!a.mo17831C().mo17322d() || !a.mo17830B()) {
            return false;
        }
        C2016ca I = a.mo17837I();
        HashSet<C2039cm> b = mo17177b();
        try {
            ArrayList<C2039cm> c = mo17178c();
            if (C2219d.m5933a(b, c)) {
                a.mo17871r();
                return false;
            }
            JSONArray jSONArray = new JSONArray();
            Iterator<C2039cm> it = c.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().mo17499a());
            }
            a.mo17859g(new C1915h(hVar).mo17111a(C2208p.C2209a.EVENT_TYPE_APP_FEATURES.mo17884a()).mo17107c(new JSONObject().put("features", jSONArray).toString()));
            I.mo17366c(jSONArray.toString());
            return false;
        } catch (JSONException unused) {
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public HashSet<C2039cm> mo17177b() {
        String b = mo17176a().mo17837I().mo17363b();
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        try {
            HashSet<C2039cm> hashSet = new HashSet<>();
            JSONArray jSONArray = new JSONArray(b);
            for (int i = 0; i < jSONArray.length(); i++) {
                hashSet.add(new C2039cm(jSONArray.getJSONObject(i)));
            }
            return hashSet;
        } catch (JSONException unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public ArrayList<C2039cm> mo17178c() {
        C2036cl clVar;
        try {
            C2198t a = mo17176a();
            PackageInfo packageInfo = a.mo17866m().getPackageManager().getPackageInfo(a.mo17866m().getPackageName(), 16384);
            ArrayList<C2039cm> arrayList = new ArrayList<>();
            if (C1897bk.m4650a(24)) {
                clVar = new C2036cl.C2037a();
            } else {
                clVar = new C2036cl.C2038b();
            }
            if (packageInfo.reqFeatures != null) {
                for (FeatureInfo b : packageInfo.reqFeatures) {
                    arrayList.add(clVar.mo17497b(b));
                }
            }
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }
}

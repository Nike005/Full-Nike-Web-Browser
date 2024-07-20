package com.yandex.metrica.impl.interact;

import android.content.Context;
import android.text.TextUtils;
import com.yandex.metrica.impl.p050ob.C2100dz;
import com.yandex.metrica.impl.p050ob.C2117ef;
import com.yandex.metrica.impl.p050ob.C2118eg;
import com.yandex.metrica.impl.p050ob.C2119eh;
import java.util.HashMap;
import java.util.Map;

public class CellularNetworkInfo {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public String f3184a = "";

    public CellularNetworkInfo(Context context) {
        C2117ef.m5566a(context).mo17675a((C2119eh) new C2119eh() {
            /* renamed from: a */
            public void mo16879a(C2118eg egVar) {
                C2100dz b = egVar.mo17706b();
                String g = b.mo17683g();
                String f = b.mo17682f();
                Integer c = b.mo17679c();
                Integer b2 = b.mo17678b();
                Integer e = b.mo17681e();
                Integer d = b.mo17680d();
                Integer a = b.mo17676a();
                HashMap hashMap = new HashMap();
                hashMap.put("network_type", g);
                hashMap.put("operator_name", f);
                String str = null;
                hashMap.put("country_code", b2 != null ? String.valueOf(b2) : null);
                hashMap.put("operator_id", c != null ? String.valueOf(c) : null);
                hashMap.put("cell_id", e != null ? String.valueOf(e) : null);
                hashMap.put("lac", d != null ? String.valueOf(d) : null);
                if (a != null) {
                    str = String.valueOf(a);
                }
                hashMap.put("signal_strength", str);
                StringBuilder sb = new StringBuilder();
                String str2 = "";
                for (Map.Entry entry : hashMap.entrySet()) {
                    String str3 = (String) entry.getValue();
                    if (!TextUtils.isEmpty(str3)) {
                        sb.append(str2);
                        sb.append((String) entry.getKey());
                        sb.append("=");
                        sb.append(str3);
                        str2 = "&";
                    }
                }
                String unused = CellularNetworkInfo.this.f3184a = sb.toString();
            }
        });
    }

    public String getCelluralInfo() {
        return this.f3184a;
    }
}

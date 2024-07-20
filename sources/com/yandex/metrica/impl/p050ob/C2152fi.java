package com.yandex.metrica.impl.p050ob;

import android.net.Uri;
import android.os.Build;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.yandex.metrica.impl.ob.fi */
class C2152fi extends C2159fo {

    /* renamed from: a */
    private Map<String, String> f3717a;

    public C2152fi(String str, Map<String, String> map) {
        super(0, str, (JSONObject) null);
        this.f3717a = map;
    }

    /* renamed from: a */
    public String mo17764a() {
        String a = super.mo17764a();
        Map<String, String> map = this.f3717a;
        Uri.Builder buildUpon = Uri.parse(a).buildUpon();
        for (Map.Entry next : map.entrySet()) {
            buildUpon.appendQueryParameter((String) next.getKey(), (String) next.getValue());
        }
        return buildUpon.build().toString();
    }

    /* renamed from: b */
    public Map<String, String> mo17765b() {
        HashMap hashMap = new HashMap();
        String format = String.format(Locale.US, "%s.%s.%s", new Object[]{2, 12, 20});
        String str = Build.DEVICE;
        String str2 = Build.VERSION.RELEASE;
        hashMap.put("User-agent", String.format(Locale.US, "com.yandex.mobile.pinning/%s (%s; Android %s)", new Object[]{format, str, str2}));
        return hashMap;
    }
}

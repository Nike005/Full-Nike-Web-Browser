package com.startapp.android.publish.adsCommon.p029b;

import android.content.Context;
import com.startapp.android.publish.adsCommon.C1103c;
import com.startapp.android.publish.adsCommon.p031d.C1117b;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.common.C1303g;
import com.startapp.common.p043a.C1270g;
import java.util.ArrayList;
import java.util.List;
import org.htmlcleaner.CleanerProperties;

/* renamed from: com.startapp.android.publish.adsCommon.b.b */
/* compiled from: StartAppSDK */
public class C1100b {

    /* renamed from: a */
    private List<C1099a> f1093a;

    /* renamed from: b */
    private Context f1094b;

    /* renamed from: c */
    private List<String> f1095c = new ArrayList();

    public C1100b(Context context, List<C1099a> list) {
        this.f1093a = list;
        this.f1094b = context;
    }

    /* renamed from: a */
    public void mo14795a() {
        C1303g.m2206a(C1303g.C1307a.DEFAULT, (Runnable) new Runnable() {
            public void run() {
                C1100b.this.mo14796b();
            }
        });
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public Boolean mo14796b() {
        boolean z;
        C1270g.m2076a(3, "in doInBackground handler");
        try {
            m1356c();
            z = true;
        } catch (Exception e) {
            C1132f.m1527a(this.f1094b, C1130d.EXCEPTION, "AppPresenceHandler.doInBackground - sendAdImpressions failed", e.getMessage(), "");
            z = false;
        }
        return Boolean.valueOf(z);
    }

    /* renamed from: c */
    private void m1356c() {
        m1355a(this.f1093a, this.f1095c);
        for (int i = 0; i < this.f1095c.size(); i++) {
            String str = this.f1095c.get(i);
            if (str.length() != 0) {
                C1103c.m1372a(this.f1094b, str, new C1117b().setNonImpressionReason("APP_PRESENCE"));
            }
        }
    }

    /* renamed from: a */
    private void m1355a(List<C1099a> list, List<String> list2) {
        C1270g.m2076a(3, "in getAppPresenceDParameter()");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (C1099a next : list) {
            if (!next.mo14792c()) {
                String a = m1354a(next.mo14787a());
                if (next.mo14793d()) {
                    arrayList.add("d=" + a);
                } else {
                    arrayList2.add("d=" + a);
                }
            }
        }
        C1270g.m2076a(3, "appPresence tracking size = " + arrayList.size() + " normal size = " + arrayList2.size());
        if (!arrayList.isEmpty()) {
            list2.addAll(C1103c.m1369a((List<String>) arrayList, "false", CleanerProperties.BOOL_ATT_TRUE));
        }
        if (!arrayList2.isEmpty()) {
            list2.addAll(C1103c.m1369a((List<String>) arrayList2, "false", "false"));
        }
    }

    /* renamed from: a */
    private String m1354a(String str) {
        return str.split("tracking/adImpression[?]d=")[1];
    }
}

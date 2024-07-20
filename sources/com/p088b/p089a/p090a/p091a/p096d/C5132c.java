package com.p088b.p089a.p090a.p091a.p096d;

import android.view.View;
import com.p088b.p089a.p090a.p091a.p093b.C5118i;
import com.p088b.p089a.p090a.p091a.p095c.C5120a;
import com.p088b.p089a.p090a.p091a.p096d.C5129a;
import com.p088b.p089a.p090a.p091a.p097e.C5135b;
import com.p088b.p089a.p090a.p091a.p097e.C5140f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.Iterator;
import org.json.JSONObject;

/* renamed from: com.b.a.a.a.d.c */
public class C5132c implements C5129a {

    /* renamed from: a */
    private final C5129a f4996a;

    public C5132c(C5129a aVar) {
        this.f4996a = aVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public ArrayList<View> mo41902a() {
        View rootView;
        ArrayList<View> arrayList = new ArrayList<>();
        C5120a a = C5120a.m7050a();
        if (a != null) {
            Collection<C5118i> c = a.mo41868c();
            IdentityHashMap identityHashMap = new IdentityHashMap((c.size() * 2) + 3);
            for (C5118i h : c) {
                View h2 = h.mo41853h();
                if (h2 != null && C5140f.m7148c(h2) && (rootView = h2.getRootView()) != null && !identityHashMap.containsKey(rootView)) {
                    identityHashMap.put(rootView, rootView);
                    float a2 = C5140f.m7146a(rootView);
                    int size = arrayList.size();
                    while (size > 0 && C5140f.m7146a(arrayList.get(size - 1)) > a2) {
                        size--;
                    }
                    arrayList.add(size, rootView);
                }
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public JSONObject mo41898a(View view) {
        return C5135b.m7116a(0, 0, 0, 0);
    }

    /* renamed from: a */
    public void mo41899a(View view, JSONObject jSONObject, C5129a.C5130a aVar, boolean z) {
        Iterator<View> it = mo41902a().iterator();
        while (it.hasNext()) {
            aVar.mo41900a(it.next(), this.f4996a, jSONObject);
        }
    }
}

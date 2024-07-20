package com.p088b.p089a.p090a.p091a.p096d;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import com.p088b.p089a.p090a.p091a.p096d.C5129a;
import com.p088b.p089a.p090a.p091a.p097e.C5135b;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

/* renamed from: com.b.a.a.a.d.d */
public class C5133d implements C5129a {

    /* renamed from: a */
    private final int[] f4997a = new int[2];

    /* renamed from: a */
    private void m7107a(ViewGroup viewGroup, JSONObject jSONObject, C5129a.C5130a aVar) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            aVar.mo41900a(viewGroup.getChildAt(i), this, jSONObject);
        }
    }

    /* renamed from: b */
    private void m7108b(ViewGroup viewGroup, JSONObject jSONObject, C5129a.C5130a aVar) {
        HashMap hashMap = new HashMap();
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            ArrayList arrayList = (ArrayList) hashMap.get(Float.valueOf(childAt.getZ()));
            if (arrayList == null) {
                arrayList = new ArrayList();
                hashMap.put(Float.valueOf(childAt.getZ()), arrayList);
            }
            arrayList.add(childAt);
        }
        ArrayList arrayList2 = new ArrayList(hashMap.keySet());
        Collections.sort(arrayList2);
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((ArrayList) hashMap.get((Float) it.next())).iterator();
            while (it2.hasNext()) {
                aVar.mo41900a((View) it2.next(), this, jSONObject);
            }
        }
    }

    /* renamed from: a */
    public JSONObject mo41898a(View view) {
        int width = view.getWidth();
        int height = view.getHeight();
        view.getLocationOnScreen(this.f4997a);
        int[] iArr = this.f4997a;
        return C5135b.m7116a(iArr[0], iArr[1], width, height);
    }

    /* renamed from: a */
    public void mo41899a(View view, JSONObject jSONObject, C5129a.C5130a aVar, boolean z) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            if (!z || Build.VERSION.SDK_INT < 21) {
                m7107a(viewGroup, jSONObject, aVar);
            } else {
                m7108b(viewGroup, jSONObject, aVar);
            }
        }
    }
}
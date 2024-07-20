package com.appnext.base.operations.imp;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.appnext.base.operations.C4913c;
import com.appnext.base.p078a.p080b.C4886b;
import com.appnext.base.p078a.p080b.C4887c;
import com.appnext.base.p082b.C4899d;
import com.appnext.base.p082b.C4901e;
import com.appnext.base.p082b.C4902f;
import com.appnext.base.p082b.C4904h;
import com.appnext.base.p082b.C4906j;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class acap extends C4913c {

    /* renamed from: et */
    private static final long f4659et = 1;

    /* renamed from: eu */
    private static final String f4660eu = "ft";

    /* renamed from: ev */
    private static final String f4661ev = "nfga";

    /* renamed from: ew */
    private static final String f4662ew = "android";

    public acap(C4887c cVar, Bundle bundle, Object obj) {
        super(cVar, bundle, obj);
    }

    /* renamed from: aF */
    public final boolean mo41039aF() {
        if (Build.VERSION.SDK_INT < 21) {
            return C4902f.m6573a(C4901e.getContext(), "android.permission.GET_TASKS");
        }
        return C4906j.m6604f(C4901e.getContext().getApplicationContext());
    }

    /* access modifiers changed from: protected */
    public List<C4886b> getData() {
        try {
            List<String> a = C4906j.m6595a(C4901e.getContext(), (long) C4906j.m6605g(mo41044ay().mo40959al(), mo41044ay().mo40960am()), mo41044ay().mo40956a(f4660eu, 1));
            if (a == null) {
                a = null;
            } else {
                Iterator<String> it = a.iterator();
                while (it.hasNext()) {
                    if (it.next().equalsIgnoreCase("android")) {
                        it.remove();
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            if (a == null || a.isEmpty()) {
                arrayList.add(new C4886b(getKey(), f4661ev, C4899d.C4900a.String.toString()));
            } else {
                for (String bVar : a) {
                    arrayList.add(new C4886b(getKey(), bVar, C4899d.C4900a.String.toString()));
                }
            }
            return arrayList;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public List<C4886b> mo41045b(List<C4886b> list) {
        if (list == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (C4886b next : list) {
            String L = C4904h.m6584aO().mo41008L(next.mo40951ai());
            if (!TextUtils.isEmpty(L) && !L.equals(f4661ev) && !hashMap.containsKey(L)) {
                hashMap.put(L, next);
            }
        }
        if (hashMap.size() == 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList(hashMap.values());
        if (arrayList.isEmpty()) {
            return null;
        }
        Collections.sort(arrayList, new CollectedDataModelByDateComparator());
        return arrayList;
    }

    /* renamed from: f */
    private static List<String> m6652f(List<String> list) {
        if (list == null) {
            return null;
        }
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().equalsIgnoreCase("android")) {
                it.remove();
            }
        }
        return list;
    }

    private class CollectedDataModelByDateComparator implements Comparator<C4886b> {
        private CollectedDataModelByDateComparator() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return ((C4886b) obj).mo40952aj().getTime() > ((C4886b) obj2).mo40952aj().getTime() ? 1 : 0;
        }

        /* renamed from: a */
        public static int m6655a(C4886b bVar, C4886b bVar2) {
            return bVar.mo40952aj().getTime() > bVar2.mo40952aj().getTime() ? 1 : 0;
        }
    }

    /* access modifiers changed from: protected */
    public String getKey() {
        return acap.class.getSimpleName();
    }
}

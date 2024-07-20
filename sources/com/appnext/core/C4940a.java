package com.appnext.core;

import android.os.Handler;
import com.appnext.core.C4948d;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.appnext.core.a */
public final class C4940a {
    private ArrayList<?> ads = null;

    /* renamed from: fH */
    private Long f4710fH = 0L;
    /* access modifiers changed from: private */

    /* renamed from: fI */
    public ArrayList<C4948d.C4955a> f4711fI = new ArrayList<>();

    /* renamed from: fJ */
    private String f4712fJ = "";
    private String placementID;
    private int state = 0;

    /* renamed from: a */
    public final void mo41209a(C4948d.C4955a aVar) {
        if (aVar != null) {
            this.f4711fI.add(aVar);
        }
    }

    /* renamed from: a */
    public final void mo41208a(C4940a aVar) {
        ArrayList<C4948d.C4955a> arrayList;
        if (aVar != null && (arrayList = aVar.f4711fI) != null) {
            this.f4711fI.addAll(arrayList);
        }
    }

    /* renamed from: b */
    public final void mo41213b(C4948d.C4955a aVar) {
        if (aVar != null) {
            this.f4711fI.remove(aVar);
        }
    }

    /* renamed from: aU */
    public final Long mo41212aU() {
        return this.f4710fH;
    }

    public final ArrayList<?> getAds() {
        if (this.ads == null) {
            return null;
        }
        ArrayList<?> arrayList = new ArrayList<>();
        Iterator<?> it = this.ads.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    public final void setPlacementID(String str) {
        this.placementID = str;
    }

    public final int getState() {
        return this.state;
    }

    public final String getPlacementID() {
        return this.placementID;
    }

    public final void setState(int i) {
        this.state = i;
    }

    /* renamed from: a */
    public final void mo41210a(Long l) {
        this.f4710fH = l;
    }

    /* renamed from: d */
    public final void mo41214d(ArrayList<?> arrayList) {
        mo41211a(arrayList, true);
    }

    /* renamed from: a */
    public final void mo41211a(ArrayList<?> arrayList, boolean z) {
        this.ads = arrayList;
        if (z) {
            this.f4710fH = Long.valueOf(System.currentTimeMillis());
        }
    }

    /* renamed from: A */
    public final String mo41205A() {
        return this.f4712fJ;
    }

    /* renamed from: N */
    public final void mo41206N(String str) {
        this.f4712fJ = str;
    }

    /* renamed from: e */
    public final synchronized void mo41215e(final ArrayList<?> arrayList) {
        new Handler().post(new Runnable() {
            public final void run() {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(C4940a.this.f4711fI);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    C4948d.C4955a aVar = (C4948d.C4955a) it.next();
                    if (aVar != null) {
                        aVar.mo40599a(arrayList);
                    }
                }
                C4940a.this.f4711fI.clear();
            }
        });
    }

    /* renamed from: O */
    public final synchronized void mo41207O(final String str) {
        new Handler().post(new Runnable() {
            public final void run() {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(C4940a.this.f4711fI);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    C4948d.C4955a aVar = (C4948d.C4955a) it.next();
                    if (aVar != null) {
                        aVar.error(str);
                    }
                }
                C4940a.this.f4711fI.clear();
            }
        });
    }
}

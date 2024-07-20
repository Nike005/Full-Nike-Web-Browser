package com.tappx.p048a;

import android.view.MotionEvent;

/* renamed from: com.tappx.a.o3 */
class C1553o3 {

    /* renamed from: a */
    private C1554a f2149a;

    /* renamed from: com.tappx.a.o3$a */
    public interface C1554a {
        /* renamed from: a */
        void mo15627a();
    }

    /* renamed from: a */
    public void mo16037a(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            C1554a aVar = this.f2149a;
            if (aVar != null) {
                aVar.mo15627a();
            } else {
                C1475j4.m2885a("No listener, click ignored");
            }
        }
    }

    /* renamed from: a */
    public void mo16038a(C1554a aVar) {
        this.f2149a = aVar;
    }
}

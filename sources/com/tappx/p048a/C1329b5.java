package com.tappx.p048a;

import android.content.Context;
import android.view.View;
import com.tappx.p048a.C1354c5;
import com.tappx.p048a.C1394e5;
import com.tappx.p048a.C1625t3;

/* renamed from: com.tappx.a.b5 */
public class C1329b5 {

    /* renamed from: a */
    private C1394e5 f1637a;

    /* renamed from: b */
    private int f1638b;

    /* renamed from: com.tappx.a.b5$a */
    class C1330a implements C1625t3.C1627b {

        /* renamed from: a */
        final /* synthetic */ C1354c5.C1355a f1639a;

        C1330a(C1329b5 b5Var, C1354c5.C1355a aVar) {
            this.f1639a = aVar;
        }

        /* renamed from: a */
        public void mo15521a() {
        }

        /* renamed from: a */
        public void mo15522a(View view) {
            C1354c5.C1355a aVar = this.f1639a;
            if (aVar != null) {
                aVar.mo15600e();
            }
        }

        /* renamed from: a */
        public void mo15523a(boolean z) {
        }

        /* renamed from: b */
        public void mo15524b() {
        }

        /* renamed from: c */
        public void mo15525c() {
        }

        /* renamed from: d */
        public void mo15526d() {
            C1354c5.C1355a aVar = this.f1639a;
            if (aVar != null) {
                aVar.mo15596a();
            }
        }
    }

    public C1329b5() {
        this(C1394e5.m2567a());
    }

    /* renamed from: a */
    public void mo15574a(int i, C1354c5 c5Var, Context context, String str, C1354c5.C1355a aVar, C1414f5 f5Var) {
        if (C1417g.f1851a) {
            aVar.mo15600e();
            return;
        }
        this.f1638b = i;
        C1625t3 a = C1675w3.m3547a(context, str);
        a.mo16179a((C1625t3.C1627b) new C1330a(this, aVar));
        a.mo16177a(C1328b4.INTERSTITIAL, str, new C1625t3.C1626a().mo16182a(f5Var.mo15810g()));
        this.f1637a.mo15744a(i, c5Var, a);
    }

    public C1329b5(C1394e5 e5Var) {
        this.f1637a = e5Var;
    }

    /* renamed from: a */
    public void mo15573a() {
        C1394e5.C1395a a;
        int i = this.f1638b;
        if (i != 0 && (a = this.f1637a.mo15743a(i)) != null) {
            a.mo15745a().destroy();
        }
    }
}

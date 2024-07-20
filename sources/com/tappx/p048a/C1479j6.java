package com.tappx.p048a;

import android.content.Context;
import com.tappx.p048a.C1396e6;
import java.io.File;

/* renamed from: com.tappx.a.j6 */
public class C1479j6 {

    /* renamed from: com.tappx.a.j6$a */
    static class C1480a implements C1396e6.C1399c {

        /* renamed from: a */
        private File f1985a = null;

        /* renamed from: b */
        final /* synthetic */ Context f1986b;

        C1480a(Context context) {
            this.f1986b = context;
        }

        /* renamed from: a */
        public File mo15759a() {
            if (this.f1985a == null) {
                this.f1985a = new File(this.f1986b.getCacheDir(), "volley");
            }
            return this.f1985a;
        }
    }

    /* renamed from: a */
    public static C1629t5 m2895a(Context context, C1331b6 b6Var) {
        C1356c6 c6Var;
        if (b6Var == null) {
            c6Var = new C1356c6(new C1447h6());
        } else {
            c6Var = new C1356c6(b6Var);
        }
        return m2896a(context, (C1546n5) c6Var);
    }

    /* renamed from: a */
    private static C1629t5 m2896a(Context context, C1546n5 n5Var) {
        C1629t5 t5Var = new C1629t5(new C1396e6(new C1480a(context.getApplicationContext())), n5Var);
        t5Var.mo16190b();
        return t5Var;
    }

    /* renamed from: a */
    public static C1629t5 m2894a(Context context) {
        return m2895a(context, (C1331b6) null);
    }
}

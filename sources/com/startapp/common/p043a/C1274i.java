package com.startapp.common.p043a;

import android.content.Context;
import android.widget.Toast;

/* renamed from: com.startapp.common.a.i */
/* compiled from: StartAppSDK */
public class C1274i {

    /* renamed from: a */
    private static C1274i f1506a = new C1274i();

    /* renamed from: b */
    private Toast f1507b;

    private C1274i() {
    }

    /* renamed from: a */
    public void mo15477a(Context context, String str) {
        Toast toast = this.f1507b;
        if (toast == null) {
            this.f1507b = Toast.makeText(context, str, 0);
        } else {
            toast.setText(str);
            this.f1507b.setDuration(0);
        }
        this.f1507b.show();
    }

    /* renamed from: a */
    public static C1274i m2100a() {
        return f1506a;
    }
}

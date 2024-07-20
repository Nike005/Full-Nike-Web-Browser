package com.yandex.metrica.impl.p050ob;

import android.content.Context;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.yandex.metrica.impl.ob.cz */
class C2055cz implements C2051cv {

    /* renamed from: a */
    private final Context f3444a;

    public C2055cz(Context context) {
        this.f3444a = context;
    }

    /* renamed from: a */
    public List<C2052cw> mo17511a() {
        ArrayList arrayList = new ArrayList();
        try {
            for (String cwVar : this.f3444a.getPackageManager().getPackageInfo(this.f3444a.getPackageName(), 4096).requestedPermissions) {
                arrayList.add(new C2052cw(cwVar, true));
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return arrayList;
    }
}

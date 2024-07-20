package com.yandex.metrica.impl.p050ob;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.yandex.metrica.impl.ob.cy */
class C2054cy implements C2051cv {

    /* renamed from: a */
    private final Context f3443a;

    public C2054cy(Context context) {
        this.f3443a = context;
    }

    /* renamed from: a */
    public List<C2052cw> mo17511a() {
        ArrayList arrayList = new ArrayList();
        try {
            PackageInfo packageInfo = this.f3443a.getPackageManager().getPackageInfo(this.f3443a.getPackageName(), 4096);
            for (int i = 0; i < packageInfo.requestedPermissions.length; i++) {
                String str = packageInfo.requestedPermissions[i];
                if ((packageInfo.requestedPermissionsFlags[i] & 2) != 0) {
                    arrayList.add(new C2052cw(str, true));
                } else {
                    arrayList.add(new C2052cw(str, false));
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return arrayList;
    }
}

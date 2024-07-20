package com.appnext.base.operations.imp;

import android.os.Bundle;
import com.appnext.base.operations.C4913c;
import com.appnext.base.p078a.C4880a;
import com.appnext.base.p078a.p080b.C4886b;
import com.appnext.base.p078a.p080b.C4887c;
import com.appnext.base.p082b.C4899d;
import com.appnext.base.p082b.C4901e;
import com.appnext.base.services.p084b.C4922a;
import java.util.List;

public class scdle extends C4913c {
    public scdle(C4887c cVar, Bundle bundle, Object obj) {
        super(cVar, bundle, obj);
    }

    /* access modifiers changed from: protected */
    public final String getKey() {
        return scdle.class.getSimpleName();
    }

    /* access modifiers changed from: protected */
    public List<C4886b> getData() {
        try {
            List<C4887c> as = C4880a.m6472X().mo40942ab().mo40977as();
            if (as == null) {
                return null;
            }
            for (C4887c next : as) {
                if (!next.getKey().equals(scdle.class.getSimpleName()) && next.mo40962ao().equals(C4899d.f4626fm)) {
                    C4922a.m6693d(C4901e.getContext()).mo41071c(next);
                    C4922a.m6693d(C4901e.getContext()).mo41070a(next, true);
                }
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }
}

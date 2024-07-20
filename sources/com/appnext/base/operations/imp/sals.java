package com.appnext.base.operations.imp;

import acr.browser.lightning.activity.BrowserActivity;
import android.os.Bundle;
import com.appnext.base.operations.C4913c;
import com.appnext.base.p078a.C4880a;
import com.appnext.base.p078a.p080b.C4886b;
import com.appnext.base.p078a.p080b.C4887c;
import com.appnext.base.p082b.C4899d;
import com.appnext.core.C4967f;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;

public class sals extends C4913c {
    /* access modifiers changed from: protected */
    /* renamed from: aA */
    public final boolean mo41035aA() {
        return false;
    }

    /* renamed from: aF */
    public final boolean mo41039aF() {
        return true;
    }

    public sals(C4887c cVar, Bundle bundle, Object obj) {
        super(cVar, bundle, obj);
    }

    /* access modifiers changed from: protected */
    public final String getKey() {
        return sals.class.getSimpleName();
    }

    /* access modifiers changed from: protected */
    public List<C4886b> getData() {
        try {
            String a = C4967f.m6818a(C4899d.f4614fa, (HashMap<String, String>) null, true, (int) BrowserActivity.ONE_MINUTE_BY_MILLISECONDS);
            C4880a.m6472X().mo40940Z().delete();
            C4880a.m6472X().mo40940Z().mo40968a(new JSONArray(a));
        } catch (Throwable th) {
            th.getMessage();
        }
        return null;
    }
}

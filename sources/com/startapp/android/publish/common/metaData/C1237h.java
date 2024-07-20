package com.startapp.android.publish.common.metaData;

import android.content.Context;
import com.startapp.android.publish.adsCommon.C1166k;
import java.io.Serializable;

/* renamed from: com.startapp.android.publish.common.metaData.h */
/* compiled from: StartAppSDK */
public class C1237h implements Serializable {
    private static final long serialVersionUID = 1;
    private boolean enabled = false;

    /* renamed from: a */
    public boolean mo15263a() {
        return this.enabled;
    }

    /* renamed from: a */
    public boolean mo15264a(Context context) {
        return C1166k.m1606a(context, "userDisabledSimpleToken", (Boolean) false).booleanValue();
    }

    /* renamed from: b */
    public boolean mo15265b(Context context) {
        return !mo15264a(context) && mo15263a();
    }

    /* renamed from: a */
    public void mo15262a(Context context, boolean z) {
        C1166k.m1613b(context, "userDisabledSimpleToken", Boolean.valueOf(!z));
    }
}

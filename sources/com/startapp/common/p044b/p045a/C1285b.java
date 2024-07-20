package com.startapp.common.p044b.p045a;

import android.content.Context;
import java.util.Map;

/* renamed from: com.startapp.common.b.a.b */
/* compiled from: StartAppSDK */
public interface C1285b {

    /* renamed from: com.startapp.common.b.a.b$a */
    /* compiled from: StartAppSDK */
    public enum C1286a {
        SUCCESS,
        FAILED,
        RESCHEDULE
    }

    /* renamed from: com.startapp.common.b.a.b$b */
    /* compiled from: StartAppSDK */
    public interface C1287b {
        /* renamed from: a */
        void mo15139a(C1286a aVar);
    }

    /* renamed from: a */
    void mo14857a(Context context, int i, Map<String, String> map, C1287b bVar);
}

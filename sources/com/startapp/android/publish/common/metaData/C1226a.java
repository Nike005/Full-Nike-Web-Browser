package com.startapp.android.publish.common.metaData;

import java.io.Serializable;

/* renamed from: com.startapp.android.publish.common.metaData.a */
/* compiled from: StartAppSDK */
public class C1226a implements Serializable {
    private static final long serialVersionUID = 1;
    private int delay = 3;
    private boolean enabled = true;
    private int minApiLevel = 18;

    public C1226a() {
    }

    public C1226a(int i) {
        this.minApiLevel = i;
    }

    /* renamed from: a */
    public int mo15239a() {
        return this.delay;
    }

    /* renamed from: b */
    public int mo15240b() {
        return this.minApiLevel;
    }

    /* renamed from: c */
    public boolean mo15241c() {
        return this.enabled;
    }
}

package com.startapp.android.publish.common.metaData;

import com.onesignal.OneSignalRemoteParams;
import java.io.Serializable;

/* renamed from: com.startapp.android.publish.common.metaData.b */
/* compiled from: StartAppSDK */
public class C1227b implements Serializable {
    private static final long serialVersionUID = 1;
    private int discoveryIntervalInMinutes = OneSignalRemoteParams.DEFAULT_INDIRECT_ATTRIBUTION_WINDOW;
    private boolean enabled = false;
    private int timeoutInSec = 20;

    /* renamed from: a */
    public int mo15242a() {
        return this.timeoutInSec;
    }

    /* renamed from: b */
    public boolean mo15243b() {
        return this.enabled;
    }

    /* renamed from: c */
    public int mo15244c() {
        return this.discoveryIntervalInMinutes;
    }
}

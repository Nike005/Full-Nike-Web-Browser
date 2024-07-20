package com.startapp.android.publish.adsCommon.adinformation;

import com.startapp.android.publish.adsCommon.adinformation.AdInformationPositions;
import com.startapp.common.p046c.C5303f;
import java.io.Serializable;

/* renamed from: com.startapp.android.publish.adsCommon.adinformation.c */
/* compiled from: StartAppSDK */
public class C1093c implements Serializable {
    private static final long serialVersionUID = 1;
    private boolean enable = true;
    private boolean enableOverride = false;
    @C5303f(mo45478b = AdInformationPositions.Position.class)
    private AdInformationPositions.Position position = AdInformationPositions.Position.getByName(AdInformationPositions.f1039a);
    private boolean positionOverride = false;

    private C1093c() {
    }

    /* renamed from: a */
    public static C1093c m1277a() {
        return new C1093c();
    }

    /* renamed from: b */
    public boolean mo14727b() {
        return this.enable;
    }

    /* renamed from: a */
    public void mo14726a(boolean z) {
        this.enable = z;
        this.enableOverride = true;
    }

    /* renamed from: c */
    public AdInformationPositions.Position mo14728c() {
        return this.position;
    }

    /* renamed from: a */
    public void mo14725a(AdInformationPositions.Position position2) {
        this.position = position2;
        if (position2 != null) {
            this.positionOverride = true;
        } else {
            this.positionOverride = false;
        }
    }

    /* renamed from: d */
    public boolean mo14729d() {
        return this.positionOverride;
    }

    /* renamed from: e */
    public boolean mo14730e() {
        return this.enableOverride;
    }
}

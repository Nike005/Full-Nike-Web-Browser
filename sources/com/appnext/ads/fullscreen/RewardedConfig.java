package com.appnext.ads.fullscreen;

import com.appnext.core.C4986p;
import java.io.Serializable;

public class RewardedConfig extends VideoConfig implements Serializable {
    private static final long serialVersionUID = 1;
    private String mode = RewardedVideo.VIDEO_MODE_DEFAULT;
    private int multiTimerLength = 3;

    public String getMode() {
        return this.mode;
    }

    public void setMode(String str) {
        this.mode = str;
    }

    public int getMultiTimerLength() {
        return this.multiTimerLength;
    }

    public void setMultiTimerLength(int i) {
        this.multiTimerLength = i;
    }

    /* access modifiers changed from: protected */
    /* renamed from: l */
    public final C4986p mo40535l() {
        return C4745f.m6314q();
    }
}

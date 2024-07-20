package com.appnext.ads.interstitial;

import android.graphics.Color;
import com.appnext.core.C4986p;
import com.appnext.core.Configuration;
import java.io.Serializable;

public class InterstitialConfig extends Configuration implements Serializable {
    private static final long serialVersionUID = 1;
    public Boolean autoPlay;
    public String buttonColor = "";

    /* renamed from: cm */
    protected boolean f4384cm = false;
    public String creativeType = Interstitial.TYPE_MANAGED;
    public String skipText = "";

    @Deprecated
    public void setOrientation(String str) {
    }

    public void setCreativeType(String str) {
        if (str.equals(Interstitial.TYPE_MANAGED) || str.equals("static") || str.equals("video")) {
            this.creativeType = str;
            return;
        }
        throw new IllegalArgumentException("Wrong creative type");
    }

    public String getCreativeType() {
        return this.creativeType;
    }

    /* access modifiers changed from: protected */
    /* renamed from: l */
    public final C4986p mo40535l() {
        return C4795c.m6424K();
    }

    public boolean isAutoPlay() {
        Boolean bool = this.autoPlay;
        return bool == null ? Boolean.parseBoolean(C4795c.m6424K().get("auto_play")) : bool.booleanValue();
    }

    public void setAutoPlay(boolean z) {
        this.autoPlay = Boolean.valueOf(z);
    }

    public void setSkipText(String str) {
        if (str == null) {
            str = "";
        }
        this.skipText = str;
    }

    public String getSkipText() {
        return this.skipText;
    }

    /* access modifiers changed from: protected */
    /* renamed from: t */
    public final boolean mo40774t() {
        return this.mute != null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: H */
    public final boolean mo40762H() {
        return this.autoPlay != null;
    }

    /* access modifiers changed from: protected */
    /* renamed from: I */
    public final boolean mo40763I() {
        return this.backButtonCanClose != null;
    }

    public void setButtonColor(String str) {
        if (str == null) {
            this.buttonColor = "";
            return;
        }
        if (!str.startsWith("#")) {
            str = "#" + str;
        }
        try {
            Color.parseColor(str);
            this.buttonColor = str;
            this.f4384cm = true;
        } catch (Throwable unused) {
            throw new IllegalArgumentException("Unknown color");
        }
    }

    public String getButtonColor() {
        return this.buttonColor;
    }

    @Deprecated
    public void setBackButtonCanClose(boolean z) {
        this.backButtonCanClose = Boolean.valueOf(z);
    }
}

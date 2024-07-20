package com.appnext.ads.fullscreen;

import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;

/* renamed from: com.appnext.ads.fullscreen.a */
public final class C4724a extends Animation {

    /* renamed from: aA */
    private float f4250aA;

    /* renamed from: ay */
    private Circle f4251ay;

    /* renamed from: az */
    private float f4252az;

    public C4724a(Circle circle, float f) {
        setInterpolator(new LinearInterpolator());
        this.f4252az = circle.getAngle();
        this.f4250aA = f;
        this.f4251ay = circle;
    }

    /* access modifiers changed from: protected */
    public final void applyTransformation(float f, Transformation transformation) {
        float f2 = this.f4252az;
        this.f4251ay.setAngle(f2 - ((f2 - this.f4250aA) * f));
        this.f4251ay.invalidate();
        this.f4251ay.requestLayout();
    }
}

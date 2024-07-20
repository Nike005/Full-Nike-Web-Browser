package com.anthonycr.progress;

import android.view.animation.Interpolator;
import androidx.core.view.animation.PathInterpolatorCompat;

final class BezierEaseInterpolator implements Interpolator {
    private static final Interpolator sBezierInterpolator = PathInterpolatorCompat.create(0.25f, 0.1f, 0.25f, 1.0f);

    BezierEaseInterpolator() {
    }

    public float getInterpolation(float f) {
        return sBezierInterpolator.getInterpolation(f);
    }
}

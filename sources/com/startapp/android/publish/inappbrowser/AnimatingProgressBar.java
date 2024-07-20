package com.startapp.android.publish.inappbrowser;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ProgressBar;

/* compiled from: StartAppSDK */
public class AnimatingProgressBar extends ProgressBar {

    /* renamed from: a */
    private static final Interpolator f1428a = new AccelerateDecelerateInterpolator();

    /* renamed from: b */
    private ValueAnimator f1429b;

    /* renamed from: c */
    private boolean f1430c = false;

    public AnimatingProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        boolean z = false;
        this.f1430c = Build.VERSION.SDK_INT >= 11 ? true : z;
    }

    public void setProgress(int i) {
        if (!this.f1430c) {
            super.setProgress(i);
            return;
        }
        ValueAnimator valueAnimator = this.f1429b;
        if (valueAnimator != null) {
            valueAnimator.cancel();
            if (getProgress() >= i) {
                return;
            }
        } else {
            ValueAnimator ofInt = ValueAnimator.ofInt(new int[]{getProgress(), i});
            this.f1429b = ofInt;
            ofInt.setInterpolator(f1428a);
            this.f1429b.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                /* renamed from: a */
                Integer f1431a;

                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    Integer num = (Integer) valueAnimator.getAnimatedValue();
                    this.f1431a = num;
                    AnimatingProgressBar.super.setProgress(num.intValue());
                }
            });
        }
        this.f1429b.setIntValues(new int[]{getProgress(), i});
        this.f1429b.start();
    }

    /* access modifiers changed from: protected */
    public ValueAnimator getAnimator() {
        return this.f1429b;
    }

    /* renamed from: a */
    public void mo15408a() {
        super.setProgress(0);
        ValueAnimator valueAnimator = this.f1429b;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ValueAnimator valueAnimator = this.f1429b;
        if (valueAnimator != null) {
            valueAnimator.cancel();
        }
    }
}

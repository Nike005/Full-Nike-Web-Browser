package com.anthonycr.progress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import androidx.core.internal.view.SupportMenu;
import java.util.ArrayDeque;
import java.util.Queue;

public class AnimatedProgressBar extends View {
    private static final long ALPHA_DURATION = 200;
    private static final long PROGRESS_DURATION = 500;
    private final Interpolator mAlphaInterpolator = new LinearInterpolator();
    /* access modifiers changed from: private */
    public final Queue<Animation> mAnimationQueue = new ArrayDeque();
    private boolean mBidirectionalAnimate = true;
    /* access modifiers changed from: private */
    public int mDrawWidth = 0;
    private final Paint mPaint = new Paint();
    /* access modifiers changed from: private */
    public int mProgress = 0;
    private int mProgressColor;
    private final Interpolator mProgressInterpolator = new BezierEaseInterpolator();
    private final Rect mRect = new Rect();

    public AnimatedProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet);
    }

    public AnimatedProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet);
    }

    private void init(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, C4699R.styleable.AnimatedProgressBar, 0, 0);
        try {
            this.mProgressColor = obtainStyledAttributes.getColor(C4699R.styleable.AnimatedProgressBar_progressColor, SupportMenu.CATEGORY_MASK);
            this.mBidirectionalAnimate = obtainStyledAttributes.getBoolean(C4699R.styleable.AnimatedProgressBar_bidirectionalAnimate, false);
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public int getProgress() {
        return this.mProgress;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        this.mPaint.setColor(this.mProgressColor);
        this.mPaint.setStrokeWidth(10.0f);
        Rect rect = this.mRect;
        rect.right = rect.left + this.mDrawWidth;
        canvas.drawRect(this.mRect, this.mPaint);
    }

    public void setProgress(int i) {
        if (i > 100) {
            i = 100;
        } else if (i < 0) {
            i = 0;
        }
        if (getAlpha() < 1.0f) {
            fadeIn();
        }
        int measuredWidth = getMeasuredWidth();
        this.mRect.left = 0;
        this.mRect.top = 0;
        this.mRect.bottom = getBottom() - getTop();
        if (i < this.mProgress && !this.mBidirectionalAnimate) {
            this.mDrawWidth = 0;
        } else if (i == this.mProgress && i == 100) {
            fadeOut();
        }
        this.mProgress = i;
        int i2 = this.mDrawWidth;
        int i3 = ((i * measuredWidth) / 100) - i2;
        if (i3 != 0) {
            animateView(i2, i3, measuredWidth);
        }
    }

    private void animateView(int i, int i2, int i3) {
        ProgressAnimation progressAnimation = new ProgressAnimation(i, i2, i3);
        progressAnimation.setDuration(PROGRESS_DURATION);
        progressAnimation.setInterpolator(this.mProgressInterpolator);
        if (!this.mAnimationQueue.isEmpty()) {
            this.mAnimationQueue.add(progressAnimation);
        } else {
            startAnimation(progressAnimation);
        }
    }

    private void fadeIn() {
        animate().alpha(1.0f).setDuration(ALPHA_DURATION).setInterpolator(this.mAlphaInterpolator).start();
    }

    /* access modifiers changed from: private */
    public void fadeOut() {
        animate().alpha(0.0f).setDuration(ALPHA_DURATION).setInterpolator(this.mAlphaInterpolator).start();
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.mProgress = bundle.getInt("progressState");
            parcelable = bundle.getParcelable("instanceState");
        }
        super.onRestoreInstanceState(parcelable);
    }

    /* access modifiers changed from: protected */
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putInt("progressState", this.mProgress);
        return bundle;
    }

    private class ProgressAnimation extends Animation {
        private int mDeltaWidth;
        private int mInitialWidth;
        private int mMaxWidth;

        public boolean willChangeBounds() {
            return false;
        }

        public boolean willChangeTransformationMatrix() {
            return false;
        }

        ProgressAnimation(int i, int i2, int i3) {
            this.mInitialWidth = i;
            this.mDeltaWidth = i2;
            this.mMaxWidth = i3;
        }

        /* access modifiers changed from: protected */
        public void applyTransformation(float f, Transformation transformation) {
            int i = this.mInitialWidth + ((int) (((float) this.mDeltaWidth) * f));
            if (i <= this.mMaxWidth) {
                int unused = AnimatedProgressBar.this.mDrawWidth = i;
                AnimatedProgressBar.this.invalidate();
            }
            if (((double) Math.abs(1.0f - f)) < 1.0E-5d) {
                if (AnimatedProgressBar.this.mProgress >= 100) {
                    AnimatedProgressBar.this.fadeOut();
                }
                if (!AnimatedProgressBar.this.mAnimationQueue.isEmpty()) {
                    AnimatedProgressBar animatedProgressBar = AnimatedProgressBar.this;
                    animatedProgressBar.startAnimation((Animation) animatedProgressBar.mAnimationQueue.poll());
                }
            }
        }
    }
}

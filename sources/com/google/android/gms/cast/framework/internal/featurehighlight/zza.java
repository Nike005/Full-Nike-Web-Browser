package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.GestureDetectorCompat;
import com.google.android.gms.cast.framework.C0069R;
import com.google.android.gms.internal.cast.zzej;
import com.google.android.gms.internal.cast.zzes;
import com.google.android.gms.internal.cast.zzev;

public final class zza extends ViewGroup {
    private View targetView;
    private final int[] zzjd = new int[2];
    private final Rect zzje = new Rect();
    private final Rect zzjf = new Rect();
    /* access modifiers changed from: private */
    public final OuterHighlightDrawable zzjg;
    private final InnerZoneDrawable zzjh;
    private zzi zzji;
    private View zzjj;
    /* access modifiers changed from: private */
    public Animator zzjk;
    private final zzj zzjl;
    private final GestureDetectorCompat zzjm;
    private GestureDetectorCompat zzjn;
    /* access modifiers changed from: private */
    public zzh zzjo;
    private boolean zzjp;

    public zza(Context context) {
        super(context);
        setId(C0069R.C0071id.cast_featurehighlight_view);
        setWillNotDraw(false);
        InnerZoneDrawable innerZoneDrawable = new InnerZoneDrawable(context);
        this.zzjh = innerZoneDrawable;
        innerZoneDrawable.setCallback(this);
        OuterHighlightDrawable outerHighlightDrawable = new OuterHighlightDrawable(context);
        this.zzjg = outerHighlightDrawable;
        outerHighlightDrawable.setCallback(this);
        this.zzjl = new zzj(this);
        GestureDetectorCompat gestureDetectorCompat = new GestureDetectorCompat(context, new zzb(this));
        this.zzjm = gestureDetectorCompat;
        gestureDetectorCompat.setIsLongpressEnabled(false);
        setVisibility(8);
    }

    private final void zza(Animator animator) {
        Animator animator2 = this.zzjk;
        if (animator2 != null) {
            animator2.cancel();
        }
        this.zzjk = animator;
        animator.start();
    }

    /* access modifiers changed from: private */
    public final boolean zza(float f, float f2) {
        return this.zzjf.contains(Math.round(f), Math.round(f2));
    }

    /* access modifiers changed from: private */
    public final Animator zzat() {
        InnerZoneDrawable innerZoneDrawable = this.zzjh;
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator duration = ObjectAnimator.ofFloat(innerZoneDrawable, "scale", new float[]{1.0f, 1.1f}).setDuration(500);
        ObjectAnimator duration2 = ObjectAnimator.ofFloat(innerZoneDrawable, "scale", new float[]{1.1f, 1.0f}).setDuration(500);
        ObjectAnimator duration3 = ObjectAnimator.ofPropertyValuesHolder(innerZoneDrawable, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("pulseScale", new float[]{1.1f, 2.0f}), PropertyValuesHolder.ofFloat("pulseAlpha", new float[]{1.0f, 0.0f})}).setDuration(500);
        animatorSet.play(duration);
        animatorSet.play(duration2).with(duration3).after(duration);
        animatorSet.setInterpolator(zzes.zzdk());
        animatorSet.setStartDelay(500);
        zzej.zza(animatorSet, -1, (Runnable) null);
        return animatorSet;
    }

    /* access modifiers changed from: protected */
    public final boolean checkLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return layoutParams instanceof ViewGroup.MarginLayoutParams;
    }

    /* access modifiers changed from: protected */
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-2, -2);
    }

    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    /* access modifiers changed from: protected */
    public final ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        return new ViewGroup.MarginLayoutParams(layoutParams);
    }

    /* access modifiers changed from: protected */
    public final void onDraw(Canvas canvas) {
        canvas.save();
        this.zzjg.draw(canvas);
        this.zzjh.draw(canvas);
        View view = this.targetView;
        if (view != null) {
            if (view.getParent() != null) {
                Bitmap createBitmap = Bitmap.createBitmap(this.targetView.getWidth(), this.targetView.getHeight(), Bitmap.Config.ARGB_8888);
                this.targetView.draw(new Canvas(createBitmap));
                int color = this.zzjg.getColor();
                int red = Color.red(color);
                int green = Color.green(color);
                int blue = Color.blue(color);
                for (int i = 0; i < createBitmap.getHeight(); i++) {
                    for (int i2 = 0; i2 < createBitmap.getWidth(); i2++) {
                        int pixel = createBitmap.getPixel(i2, i);
                        if (Color.alpha(pixel) != 0) {
                            createBitmap.setPixel(i2, i, Color.argb(Color.alpha(pixel), red, green, blue));
                        }
                    }
                }
                canvas.drawBitmap(createBitmap, (float) this.zzje.left, (float) this.zzje.top, (Paint) null);
            }
            canvas.restore();
            return;
        }
        throw new IllegalStateException("Neither target view nor drawable was set");
    }

    /* access modifiers changed from: protected */
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        View view = this.targetView;
        if (view != null) {
            if (view.getParent() != null) {
                int[] iArr = this.zzjd;
                View view2 = this.targetView;
                getLocationInWindow(iArr);
                int i5 = iArr[0];
                int i6 = iArr[1];
                view2.getLocationInWindow(iArr);
                iArr[0] = iArr[0] - i5;
                iArr[1] = iArr[1] - i6;
            }
            Rect rect = this.zzje;
            int[] iArr2 = this.zzjd;
            rect.set(iArr2[0], iArr2[1], iArr2[0] + this.targetView.getWidth(), this.zzjd[1] + this.targetView.getHeight());
            this.zzjf.set(i, i2, i3, i4);
            this.zzjg.setBounds(this.zzjf);
            this.zzjh.setBounds(this.zzjf);
            this.zzjl.zza(this.zzje, this.zzjf);
            return;
        }
        throw new IllegalStateException("Target view must be set before layout");
    }

    /* access modifiers changed from: protected */
    public final void onMeasure(int i, int i2) {
        setMeasuredDimension(resolveSize(View.MeasureSpec.getSize(i), i), resolveSize(View.MeasureSpec.getSize(i2), i2));
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.zzjp = this.zzje.contains((int) motionEvent.getX(), (int) motionEvent.getY());
        }
        if (this.zzjp) {
            GestureDetectorCompat gestureDetectorCompat = this.zzjn;
            if (gestureDetectorCompat != null) {
                gestureDetectorCompat.onTouchEvent(motionEvent);
                if (actionMasked == 1) {
                    motionEvent = MotionEvent.obtain(motionEvent);
                    motionEvent.setAction(3);
                }
            }
            if (this.targetView.getParent() != null) {
                this.targetView.onTouchEvent(motionEvent);
            }
        } else {
            this.zzjm.onTouchEvent(motionEvent);
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public final boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || drawable == this.zzjg || drawable == this.zzjh || drawable == null;
    }

    public final void zza(View view, View view2, boolean z, zzh zzh) {
        this.targetView = (View) zzev.checkNotNull(view);
        this.zzjj = null;
        this.zzjo = (zzh) zzev.checkNotNull(zzh);
        GestureDetectorCompat gestureDetectorCompat = new GestureDetectorCompat(getContext(), new zzc(this, view, true, zzh));
        this.zzjn = gestureDetectorCompat;
        gestureDetectorCompat.setIsLongpressEnabled(false);
        setVisibility(4);
    }

    public final void zza(zzi zzi) {
        this.zzji = (zzi) zzev.checkNotNull(zzi);
        addView(zzi.asView(), 0);
    }

    public final void zza(Runnable runnable) {
        addOnLayoutChangeListener(new zzd(this, (Runnable) null));
    }

    public final void zzap() {
        if (this.targetView != null) {
            setVisibility(0);
            ObjectAnimator duration = ObjectAnimator.ofFloat(this.zzji.asView(), "alpha", new float[]{0.0f, 1.0f}).setDuration(350);
            duration.setInterpolator(zzes.zzdi());
            Animator zzc = this.zzjg.zzc(this.zzje.exactCenterX() - this.zzjg.getCenterX(), this.zzje.exactCenterY() - this.zzjg.getCenterY());
            ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.zzjh, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("scale", new float[]{0.0f, 1.0f}), PropertyValuesHolder.ofInt("alpha", new int[]{0, 255})});
            ofPropertyValuesHolder.setInterpolator(zzes.zzdi());
            Animator duration2 = ofPropertyValuesHolder.setDuration(350);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(new Animator[]{duration, zzc, duration2});
            animatorSet.addListener(new zze(this));
            zza((Animator) animatorSet);
            return;
        }
        throw new IllegalStateException("Target view must be set before animation");
    }

    /* access modifiers changed from: package-private */
    public final View zzaq() {
        return this.zzji.asView();
    }

    /* access modifiers changed from: package-private */
    public final OuterHighlightDrawable zzar() {
        return this.zzjg;
    }

    /* access modifiers changed from: package-private */
    public final InnerZoneDrawable zzas() {
        return this.zzjh;
    }

    public final void zzb(Runnable runnable) {
        ObjectAnimator duration = ObjectAnimator.ofFloat(this.zzji.asView(), "alpha", new float[]{0.0f}).setDuration(200);
        duration.setInterpolator(zzes.zzdj());
        float exactCenterX = this.zzje.exactCenterX() - this.zzjg.getCenterX();
        float exactCenterY = this.zzje.exactCenterY() - this.zzjg.getCenterY();
        OuterHighlightDrawable outerHighlightDrawable = this.zzjg;
        PropertyValuesHolder ofFloat = PropertyValuesHolder.ofFloat("scale", new float[]{0.0f});
        PropertyValuesHolder ofInt = PropertyValuesHolder.ofInt("alpha", new int[]{0});
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(outerHighlightDrawable, new PropertyValuesHolder[]{ofFloat, PropertyValuesHolder.ofFloat("translationX", new float[]{0.0f, exactCenterX}), PropertyValuesHolder.ofFloat("translationY", new float[]{0.0f, exactCenterY}), ofInt});
        ofPropertyValuesHolder.setInterpolator(zzes.zzdj());
        Animator duration2 = ofPropertyValuesHolder.setDuration(200);
        Animator zzau = this.zzjh.zzau();
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{duration, duration2, zzau});
        animatorSet.addListener(new zzg(this, runnable));
        zza((Animator) animatorSet);
    }

    public final void zzc(Runnable runnable) {
        ObjectAnimator duration = ObjectAnimator.ofFloat(this.zzji.asView(), "alpha", new float[]{0.0f}).setDuration(200);
        duration.setInterpolator(zzes.zzdj());
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this.zzjg, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("scale", new float[]{1.125f}), PropertyValuesHolder.ofInt("alpha", new int[]{0})});
        ofPropertyValuesHolder.setInterpolator(zzes.zzdj());
        Animator duration2 = ofPropertyValuesHolder.setDuration(200);
        Animator zzau = this.zzjh.zzau();
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{duration, duration2, zzau});
        animatorSet.addListener(new zzf(this, runnable));
        zza((Animator) animatorSet);
    }

    public final void zzg(int i) {
        this.zzjg.setColor(i);
    }
}

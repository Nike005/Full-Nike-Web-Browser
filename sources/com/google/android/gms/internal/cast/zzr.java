package com.google.android.gms.internal.cast;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.cast.framework.C0069R;
import com.google.android.gms.cast.framework.IntroductoryOverlay;

public final class zzr extends RelativeLayout implements IntroductoryOverlay {
    private Activity zzhv;
    private IntroductoryOverlay.OnOverlayDismissedListener zzhz;
    private final boolean zziq;
    private boolean zzis;
    private int zziv;
    private final zzu zziw;

    public zzr(IntroductoryOverlay.Builder builder) {
        this(builder, (AttributeSet) null, C0069R.attr.castIntroOverlayStyle);
    }

    private zzr(IntroductoryOverlay.Builder builder, AttributeSet attributeSet, int i) {
        super(builder.getActivity(), (AttributeSet) null, i);
        this.zzhv = builder.getActivity();
        this.zziq = builder.zzae();
        this.zzhz = builder.zzac();
        TypedArray obtainStyledAttributes = this.zzhv.getTheme().obtainStyledAttributes((AttributeSet) null, C0069R.styleable.CastIntroOverlay, i, C0069R.style.CastIntroOverlay);
        if (builder.zzab() != null) {
            Rect rect = new Rect();
            builder.zzab().getGlobalVisibleRect(rect);
            zzu zzu = new zzu((zzs) null);
            this.zziw = zzu;
            zzu.f24x = rect.centerX();
            this.zziw.f25y = rect.centerY();
            zzu zzu2 = this.zziw;
            PorterDuffXfermode porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY);
            Paint paint = new Paint();
            paint.setColor(-1);
            paint.setAlpha(0);
            paint.setXfermode(porterDuffXfermode);
            paint.setAntiAlias(true);
            zzu2.zziz = paint;
            this.zziw.zzja = builder.zzah();
            if (this.zziw.zzja == 0.0f) {
                this.zziw.zzja = obtainStyledAttributes.getDimension(C0069R.styleable.CastIntroOverlay_castFocusRadius, 0.0f);
            }
        } else {
            this.zziw = null;
        }
        LayoutInflater.from(this.zzhv).inflate(C0069R.layout.cast_intro_overlay, this);
        int zzad = builder.zzad();
        this.zziv = zzad;
        if (zzad == 0) {
            this.zziv = obtainStyledAttributes.getColor(C0069R.styleable.CastIntroOverlay_castBackgroundColor, Color.argb(0, 0, 0, 0));
        }
        TextView textView = (TextView) findViewById(C0069R.C0071id.textTitle);
        if (!TextUtils.isEmpty(builder.zzaf())) {
            textView.setText(builder.zzaf());
            int resourceId = obtainStyledAttributes.getResourceId(C0069R.styleable.CastIntroOverlay_castTitleTextAppearance, 0);
            if (resourceId != 0) {
                textView.setTextAppearance(this.zzhv, resourceId);
            }
        }
        String zzag = builder.zzag();
        zzag = TextUtils.isEmpty(zzag) ? obtainStyledAttributes.getString(C0069R.styleable.CastIntroOverlay_castButtonText) : zzag;
        int color = obtainStyledAttributes.getColor(C0069R.styleable.CastIntroOverlay_castButtonBackgroundColor, Color.argb(0, 0, 0, 0));
        Button button = (Button) findViewById(C0069R.C0071id.button);
        button.setText(zzag);
        button.getBackground().setColorFilter(color, PorterDuff.Mode.MULTIPLY);
        int resourceId2 = obtainStyledAttributes.getResourceId(C0069R.styleable.CastIntroOverlay_castButtonTextAppearance, 0);
        if (resourceId2 != 0) {
            button.setTextAppearance(this.zzhv, resourceId2);
        }
        button.setOnClickListener(new zzs(this));
        obtainStyledAttributes.recycle();
        setFitsSystemWindows(true);
    }

    /* access modifiers changed from: private */
    public final void zzao() {
        IntroductoryOverlay.zza.zzd(this.zzhv);
        IntroductoryOverlay.OnOverlayDismissedListener onOverlayDismissedListener = this.zzhz;
        if (onOverlayDismissedListener != null) {
            onOverlayDismissedListener.onOverlayDismissed();
            this.zzhz = null;
        }
        remove();
    }

    /* access modifiers changed from: protected */
    public final void dispatchDraw(Canvas canvas) {
        Bitmap createBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(createBitmap);
        canvas2.drawColor(this.zziv);
        zzu zzu = this.zziw;
        if (zzu != null) {
            canvas2.drawCircle((float) zzu.f24x, (float) this.zziw.f25y, this.zziw.zzja, this.zziw.zziz);
        }
        canvas.drawBitmap(createBitmap, 0.0f, 0.0f, (Paint) null);
        createBitmap.recycle();
        super.dispatchDraw(canvas);
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        if (this.zzhv != null) {
            this.zzhv = null;
        }
        super.onDetachedFromWindow();
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public final void remove() {
        Activity activity = this.zzhv;
        if (activity != null) {
            ((ViewGroup) activity.getWindow().getDecorView()).removeView(this);
            this.zzhv = null;
        }
        this.zzhz = null;
    }

    public final void show() {
        Activity activity = this.zzhv;
        if (activity == null || zzn.zzg(activity)) {
            return;
        }
        if (this.zziq && IntroductoryOverlay.zza.zze(this.zzhv)) {
            this.zzhv = null;
            this.zzhz = null;
        } else if (!this.zzis) {
            this.zzis = true;
            ((ViewGroup) this.zzhv.getWindow().getDecorView()).addView(this);
        }
    }
}

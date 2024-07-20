package com.google.android.gms.internal.cast;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.RelativeLayout;
import com.google.android.gms.cast.framework.C0069R;
import com.google.android.gms.cast.framework.IntroductoryOverlay;
import com.google.android.gms.cast.framework.internal.featurehighlight.zza;
import com.google.android.gms.cast.framework.internal.featurehighlight.zzi;

public final class zzn extends RelativeLayout implements IntroductoryOverlay {
    private int color;
    /* access modifiers changed from: private */
    public Activity zzhv;
    private View zzhw;
    private String zzhy;
    /* access modifiers changed from: private */
    public IntroductoryOverlay.OnOverlayDismissedListener zzhz;
    private final boolean zziq;
    /* access modifiers changed from: private */
    public zza zzir;
    /* access modifiers changed from: private */
    public boolean zzis;

    public zzn(IntroductoryOverlay.Builder builder) {
        super(builder.getActivity());
        this.zzhv = builder.getActivity();
        this.zziq = builder.zzae();
        this.zzhz = builder.zzac();
        this.zzhw = builder.zzab();
        this.zzhy = builder.zzaf();
        this.color = builder.zzad();
    }

    /* access modifiers changed from: private */
    public final void reset() {
        removeAllViews();
        this.zzhv = null;
        this.zzhz = null;
        this.zzhw = null;
        this.zzir = null;
        this.zzhy = null;
        this.color = 0;
        this.zzis = false;
    }

    static boolean zzg(Context context) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        return accessibilityManager != null && accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled();
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        reset();
        super.onDetachedFromWindow();
    }

    public final void remove() {
        if (this.zzis) {
            ((ViewGroup) this.zzhv.getWindow().getDecorView()).removeView(this);
            reset();
        }
    }

    public final void show() {
        Activity activity = this.zzhv;
        if (activity != null && this.zzhw != null && !this.zzis && !zzg(activity)) {
            if (!this.zziq || !IntroductoryOverlay.zza.zze(this.zzhv)) {
                zza zza = new zza(this.zzhv);
                this.zzir = zza;
                int i = this.color;
                if (i != 0) {
                    zza.zzg(i);
                }
                addView(this.zzir);
                zzi zzi = (zzi) this.zzhv.getLayoutInflater().inflate(C0069R.layout.cast_help_text, this.zzir, false);
                zzi.setText(this.zzhy, (CharSequence) null);
                this.zzir.zza(zzi);
                this.zzir.zza(this.zzhw, (View) null, true, new zzo(this));
                this.zzis = true;
                ((ViewGroup) this.zzhv.getWindow().getDecorView()).addView(this);
                this.zzir.zza((Runnable) null);
                return;
            }
            reset();
        }
    }
}

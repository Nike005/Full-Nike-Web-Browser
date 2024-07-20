package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.view.View;

final class zzd implements View.OnLayoutChangeListener {
    private final /* synthetic */ zza zzjq;
    private final /* synthetic */ Runnable zzju = null;

    zzd(zza zza, Runnable runnable) {
        this.zzjq = zza;
    }

    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        Runnable runnable = this.zzju;
        if (runnable != null) {
            runnable.run();
        }
        this.zzjq.zzap();
        this.zzjq.removeOnLayoutChangeListener(this);
    }
}

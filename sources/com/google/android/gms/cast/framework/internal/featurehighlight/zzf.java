package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

final class zzf extends AnimatorListenerAdapter {
    private final /* synthetic */ zza zzjq;
    private final /* synthetic */ Runnable zzjv;

    zzf(zza zza, Runnable runnable) {
        this.zzjq = zza;
        this.zzjv = runnable;
    }

    public final void onAnimationEnd(Animator animator) {
        this.zzjq.setVisibility(8);
        Animator unused = this.zzjq.zzjk = null;
        Runnable runnable = this.zzjv;
        if (runnable != null) {
            runnable.run();
        }
    }
}

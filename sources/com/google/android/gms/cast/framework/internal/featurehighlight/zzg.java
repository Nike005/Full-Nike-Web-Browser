package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

final class zzg extends AnimatorListenerAdapter {
    private final /* synthetic */ zza zzjq;
    private final /* synthetic */ Runnable zzjw;

    zzg(zza zza, Runnable runnable) {
        this.zzjq = zza;
        this.zzjw = runnable;
    }

    public final void onAnimationEnd(Animator animator) {
        this.zzjq.setVisibility(8);
        Animator unused = this.zzjq.zzjk = null;
        Runnable runnable = this.zzjw;
        if (runnable != null) {
            runnable.run();
        }
    }
}

package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

final class zze extends AnimatorListenerAdapter {
    private final /* synthetic */ zza zzjq;

    zze(zza zza) {
        this.zzjq = zza;
    }

    public final void onAnimationEnd(Animator animator) {
        zza zza = this.zzjq;
        Animator unused = zza.zzjk = zza.zzat();
        this.zzjq.zzjk.start();
    }
}

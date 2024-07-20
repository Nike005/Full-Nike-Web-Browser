package com.google.android.gms.internal.cast;

import android.animation.Animator;

public final class zzej extends zzei {
    protected final Animator animator;
    /* access modifiers changed from: private */
    public final Runnable zzyd;
    private final int zzye;
    private int zzyf;
    private zzen zzyg = new zzek(this);

    private zzej(Animator animator2, int i, Runnable runnable) {
        this.animator = animator2;
        this.zzye = -1;
        this.zzyd = null;
    }

    static /* synthetic */ int zza(zzej zzej) {
        int i = zzej.zzyf;
        zzej.zzyf = i + 1;
        return i;
    }

    public static void zza(Animator animator2, int i, Runnable runnable) {
        animator2.addListener(new zzej(animator2, -1, (Runnable) null));
    }

    /* access modifiers changed from: private */
    public final boolean zzde() {
        int i = this.zzye;
        return i != -1 && this.zzyf >= i;
    }

    public final void onAnimationEnd(Animator animator2) {
        if (!zzb(animator2)) {
            zzel.zzdf().zza(this.zzyg);
        }
    }
}

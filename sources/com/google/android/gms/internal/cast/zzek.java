package com.google.android.gms.internal.cast;

final class zzek extends zzen {
    private final /* synthetic */ zzej zzyh;

    zzek(zzej zzej) {
        this.zzyh = zzej;
    }

    public final void doFrame(long j) {
        zzej.zza(this.zzyh);
        zzej zzej = this.zzyh;
        if (!zzej.zzb(zzej.animator) && !this.zzyh.animator.isStarted() && !this.zzyh.zzde()) {
            if (this.zzyh.zzyd != null) {
                this.zzyh.zzyd.run();
            }
            this.zzyh.animator.start();
        }
    }
}

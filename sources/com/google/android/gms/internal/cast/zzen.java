package com.google.android.gms.internal.cast;

import android.view.Choreographer;

public abstract class zzen {
    private Runnable zzyj;
    private Choreographer.FrameCallback zzyk;

    public abstract void doFrame(long j);

    /* access modifiers changed from: package-private */
    public final Choreographer.FrameCallback zzdg() {
        if (this.zzyk == null) {
            this.zzyk = new zzeo(this);
        }
        return this.zzyk;
    }

    /* access modifiers changed from: package-private */
    public final Runnable zzdh() {
        if (this.zzyj == null) {
            this.zzyj = new zzep(this);
        }
        return this.zzyj;
    }
}

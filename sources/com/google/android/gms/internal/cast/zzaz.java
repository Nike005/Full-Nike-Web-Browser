package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.Cast;

final class zzaz extends Cast.Listener {
    private final /* synthetic */ zzay zzqj;

    zzaz(zzay zzay) {
        this.zzqj = zzay;
    }

    public final void onVolumeChanged() {
        this.zzqj.zzby();
    }
}

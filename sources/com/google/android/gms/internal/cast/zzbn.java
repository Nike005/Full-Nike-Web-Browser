package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;

final class zzbn implements Cast.MessageReceivedCallback {
    private final /* synthetic */ zzbm zzti;

    zzbn(zzbm zzbm) {
        this.zzti = zzbm;
    }

    public final void onMessageReceived(CastDevice castDevice, String str, String str2) {
        this.zzti.zzth.zzn(str2);
    }
}

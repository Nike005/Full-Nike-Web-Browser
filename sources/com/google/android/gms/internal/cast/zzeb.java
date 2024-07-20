package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

final class zzeb extends zzeh {
    private final /* synthetic */ zzeg zzya;
    private final /* synthetic */ zzea zzyb;

    zzeb(zzea zzea, zzeg zzeg) {
        this.zzyb = zzea;
        this.zzya = zzeg;
    }

    public final void zzr(int i) throws RemoteException {
        zzea.zzbd.mo6870d("onRemoteDisplayEnded", new Object[0]);
        zzeg zzeg = this.zzya;
        if (zzeg != null) {
            zzeg.zzr(i);
        }
        if (this.zzyb.zzxy != null) {
            this.zzyb.zzxy.onRemoteDisplayEnded(new Status(i));
        }
    }
}

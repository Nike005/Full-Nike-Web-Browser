package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public abstract class zzbu extends zzbt<GameManagerClient.GameManagerInstanceResult> {
    final /* synthetic */ zzbl zzth;
    /* access modifiers changed from: private */
    public GameManagerClient zztq;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzbu(zzbl zzbl, GameManagerClient gameManagerClient) {
        super(zzbl);
        this.zzth = zzbl;
        this.zztq = gameManagerClient;
        this.zztp = new zzbv(this, zzbl);
    }

    public static GameManagerClient.GameManagerInstanceResult zzc(Status status) {
        return new zzbw(status, (GameManagerClient) null);
    }

    public /* synthetic */ Result createFailedResult(Status status) {
        return zzc(status);
    }
}

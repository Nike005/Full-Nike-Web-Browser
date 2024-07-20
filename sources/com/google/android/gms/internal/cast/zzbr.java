package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import org.json.JSONObject;

public abstract class zzbr extends zzbt<GameManagerClient.GameManagerResult> {
    final /* synthetic */ zzbl zzth;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzbr(zzbl zzbl) {
        super(zzbl);
        this.zzth = zzbl;
        this.zztp = new zzbs(this, zzbl);
    }

    public static GameManagerClient.GameManagerResult zzb(Status status) {
        return new zzbx(status, (String) null, -1, (JSONObject) null);
    }

    public /* synthetic */ Result createFailedResult(Status status) {
        return zzb(status);
    }
}

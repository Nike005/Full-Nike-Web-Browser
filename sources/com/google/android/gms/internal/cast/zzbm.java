package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.GameManagerClient;
import java.io.IOException;
import org.json.JSONObject;

final class zzbm extends zzbu {
    final /* synthetic */ zzbl zzth;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbm(zzbl zzbl, GameManagerClient gameManagerClient) {
        super(zzbl, gameManagerClient);
        this.zzth = zzbl;
    }

    public final void execute() {
        try {
            this.zzth.zzhl.setMessageReceivedCallbacks(this.zzth.zznm, this.zzth.getNamespace(), new zzbn(this));
            this.zzth.zzci();
            this.zzth.zzch();
            this.zzth.zza((String) null, 1100, (JSONObject) null, this.zztp);
        } catch (IOException | IllegalStateException unused) {
            this.zztp.zza(-1, 8, (Object) null);
        }
    }
}

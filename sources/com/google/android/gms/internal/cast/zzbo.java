package com.google.android.gms.internal.cast;

import org.json.JSONObject;

final class zzbo extends zzbr {
    private final /* synthetic */ zzbl zzth;
    private final /* synthetic */ int zztj;
    private final /* synthetic */ String zztk;
    private final /* synthetic */ JSONObject zztl;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbo(zzbl zzbl, int i, String str, JSONObject jSONObject) {
        super(zzbl);
        this.zzth = zzbl;
        this.zztj = i;
        this.zztk = str;
        this.zztl = jSONObject;
    }

    public final void execute() {
        int i = this.zztj;
        int i2 = 5;
        if (i != 2) {
            i2 = i != 3 ? i != 4 ? i != 5 ? i != 6 ? 0 : 4 : 3 : 2 : 1;
        }
        if (i2 == 0) {
            this.zztp.zza(-1, 2001, (Object) null);
            zzbl.zzbd.mo6873w("sendPlayerRequest for unsupported playerState: %d", Integer.valueOf(this.zztj));
            return;
        }
        this.zzth.zza(this.zztk, i2, this.zztl, this.zztp);
    }
}

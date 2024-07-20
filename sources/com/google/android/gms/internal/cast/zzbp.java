package com.google.android.gms.internal.cast;

import org.json.JSONObject;

final class zzbp extends zzbr {
    private final /* synthetic */ zzbl zzth;
    private final /* synthetic */ String zztk;
    private final /* synthetic */ JSONObject zztl;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzbp(zzbl zzbl, String str, JSONObject jSONObject) {
        super(zzbl);
        this.zzth = zzbl;
        this.zztk = str;
        this.zztl = jSONObject;
    }

    public final void execute() {
        this.zzth.zza(this.zztk, 6, this.zztl, this.zztp);
    }
}

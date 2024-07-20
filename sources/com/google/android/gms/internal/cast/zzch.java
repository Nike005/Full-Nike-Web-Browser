package com.google.android.gms.internal.cast;

import android.content.Context;
import com.google.android.gms.cast.zzbp;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import java.util.List;

public final class zzch extends GoogleApi<Api.ApiOptions.NoOptions> {
    private static final Api<Api.ApiOptions.NoOptions> API;
    private static final Api.AbstractClientBuilder<zzcl, Api.ApiOptions.NoOptions> CLIENT_BUILDER;
    private static final Api.ClientKey<zzcl> CLIENT_KEY = new Api.ClientKey<>();

    static {
        zzci zzci = new zzci();
        CLIENT_BUILDER = zzci;
        API = new Api<>("CastApi.API", zzci, CLIENT_KEY);
    }

    public zzch(Context context) {
        super(context, API, null, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public final Task<Void> zza(String[] strArr, String str, List<zzbp> list) {
        return doWrite(new zzcj(this, strArr, str, (List) null));
    }
}

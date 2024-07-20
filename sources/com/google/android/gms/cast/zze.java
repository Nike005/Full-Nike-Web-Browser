package com.google.android.gms.cast;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzcn;

final class zze extends Api.AbstractClientBuilder<zzcn, Cast.CastOptions> {
    zze() {
    }

    public final /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Cast.CastOptions castOptions = (Cast.CastOptions) obj;
        Preconditions.checkNotNull(castOptions, "Setting the API options is required.");
        return new zzcn(context, looper, clientSettings, castOptions.zzai, (long) castOptions.zzak, castOptions.zzaj, castOptions.extras, connectionCallbacks, onConnectionFailedListener);
    }
}

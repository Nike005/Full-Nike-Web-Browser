package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

public abstract class zzcf<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zzcn> {
    public zzcf(GoogleApiClient googleApiClient) {
        super((Api<?>) Cast.API, googleApiClient);
    }

    public final void zzk(int i) {
        setResult(createFailedResult(new Status(2001)));
    }
}

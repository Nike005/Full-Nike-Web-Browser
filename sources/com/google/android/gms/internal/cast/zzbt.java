package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;

public abstract class zzbt<R extends Result> extends zzcf<R> {
    protected zzdm zztp;

    public zzbt(zzbl zzbl) {
        super(zzbl.zznm);
    }

    /* access modifiers changed from: protected */
    public /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        execute();
    }

    public abstract void execute();
}

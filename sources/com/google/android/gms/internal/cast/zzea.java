package com.google.android.gms.internal.cast;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;

@Deprecated
public final class zzea extends GmsClient<zzee> implements IBinder.DeathRecipient {
    /* access modifiers changed from: private */
    public static final zzdg zzbd = new zzdg("CastRemoteDisplayClientImpl");
    private CastDevice zzai;
    /* access modifiers changed from: private */
    public CastRemoteDisplay.CastRemoteDisplaySessionCallbacks zzxy;
    private Bundle zzxz;

    public zzea(Context context, Looper looper, ClientSettings clientSettings, CastDevice castDevice, Bundle bundle, CastRemoteDisplay.CastRemoteDisplaySessionCallbacks castRemoteDisplaySessionCallbacks, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 83, clientSettings, connectionCallbacks, onConnectionFailedListener);
        zzbd.mo6870d("instance created", new Object[0]);
        this.zzxy = castRemoteDisplaySessionCallbacks;
        this.zzai = castDevice;
        this.zzxz = bundle;
    }

    public final void binderDied() {
    }

    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
        return queryLocalInterface instanceof zzee ? (zzee) queryLocalInterface : new zzef(iBinder);
    }

    public final void disconnect() {
        zzbd.mo6870d("disconnect", new Object[0]);
        this.zzxy = null;
        this.zzai = null;
        try {
            ((zzee) getService()).disconnect();
        } catch (RemoteException | IllegalStateException unused) {
        } finally {
            super.disconnect();
        }
    }

    public final int getMinApkVersion() {
        return GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    /* access modifiers changed from: protected */
    public final String getServiceDescriptor() {
        return "com.google.android.gms.cast.remote_display.ICastRemoteDisplayService";
    }

    /* access modifiers changed from: protected */
    public final String getStartServiceAction() {
        return "com.google.android.gms.cast.remote_display.service.START";
    }

    public final void zza(zzec zzec) throws RemoteException {
        zzbd.mo6870d("stopRemoteDisplay", new Object[0]);
        ((zzee) getService()).zza(zzec);
    }

    public final void zza(zzec zzec, zzeg zzeg, String str) throws RemoteException {
        zzbd.mo6870d("startRemoteDisplay", new Object[0]);
        zzec zzec2 = zzec;
        ((zzee) getService()).zza(zzec2, (zzeg) new zzeb(this, zzeg), this.zzai.getDeviceId(), str, this.zzxz);
    }
}

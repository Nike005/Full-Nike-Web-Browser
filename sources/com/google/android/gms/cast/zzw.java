package com.google.android.gms.cast;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.cast.CastRemoteDisplayLocalService;
import com.google.android.gms.common.api.Status;

final class zzw implements ServiceConnection {
    private final /* synthetic */ String zzaf;
    private final /* synthetic */ CastDevice zzch;
    private final /* synthetic */ CastRemoteDisplayLocalService.Options zzci;
    private final /* synthetic */ CastRemoteDisplayLocalService.NotificationSettings zzcj;
    private final /* synthetic */ Context zzck;
    private final /* synthetic */ CastRemoteDisplayLocalService.Callbacks zzcl;

    zzw(String str, CastDevice castDevice, CastRemoteDisplayLocalService.Options options, CastRemoteDisplayLocalService.NotificationSettings notificationSettings, Context context, CastRemoteDisplayLocalService.Callbacks callbacks) {
        this.zzaf = str;
        this.zzch = castDevice;
        this.zzci = options;
        this.zzcj = notificationSettings;
        this.zzck = context;
        this.zzcl = callbacks;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        CastRemoteDisplayLocalService castRemoteDisplayLocalService = CastRemoteDisplayLocalService.this;
        if (castRemoteDisplayLocalService != null) {
            if (castRemoteDisplayLocalService.zza(this.zzaf, this.zzch, this.zzci, this.zzcj, this.zzck, this, this.zzcl)) {
                return;
            }
        }
        CastRemoteDisplayLocalService.zzbd.mo6871e("Connected but unable to get the service instance", new Object[0]);
        this.zzcl.onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_SERVICE_CREATION_FAILED));
        CastRemoteDisplayLocalService.zzbp.set(false);
        try {
            this.zzck.unbindService(this);
        } catch (IllegalArgumentException unused) {
            CastRemoteDisplayLocalService.zzbd.mo6870d("No need to unbind service, already unbound", new Object[0]);
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        CastRemoteDisplayLocalService.zzbd.mo6870d("onServiceDisconnected", new Object[0]);
        this.zzcl.onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_SERVICE_DISCONNECTED, "Service Disconnected"));
        CastRemoteDisplayLocalService.zzbp.set(false);
        try {
            this.zzck.unbindService(this);
        } catch (IllegalArgumentException unused) {
            CastRemoteDisplayLocalService.zzbd.mo6870d("No need to unbind service, already unbound", new Object[0]);
        }
    }
}

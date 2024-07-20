package com.google.android.gms.cast.framework;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.cast.zzdg;
import com.google.android.gms.internal.cast.zze;

public class ReconnectionService extends Service {
    private static final zzdg zzbd = new zzdg("ReconnectionService");
    private zzr zzie;

    public IBinder onBind(Intent intent) {
        try {
            return this.zzie.onBind(intent);
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "onBind", zzr.class.getSimpleName());
            return null;
        }
    }

    public void onCreate() {
        CastContext sharedInstance = CastContext.getSharedInstance(this);
        zzr zza = zze.zza(this, sharedInstance.getSessionManager().zzr(), sharedInstance.zzq().zzr());
        this.zzie = zza;
        try {
            zza.onCreate();
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "onCreate", zzr.class.getSimpleName());
        }
        super.onCreate();
    }

    public void onDestroy() {
        try {
            this.zzie.onDestroy();
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "onDestroy", zzr.class.getSimpleName());
        }
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        try {
            return this.zzie.onStartCommand(intent, i, i2);
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "onStartCommand", zzr.class.getSimpleName());
            return 1;
        }
    }
}

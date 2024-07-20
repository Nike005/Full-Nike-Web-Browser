package com.google.android.gms.cast;

import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.os.RemoteException;
import android.view.Display;
import android.view.Surface;
import com.google.android.gms.cast.CastRemoteDisplayClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.internal.cast.zzdz;
import com.google.android.gms.internal.cast.zzee;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.messaging.Constants;

final class zzr extends CastRemoteDisplayClient.zza {
    private final /* synthetic */ TaskCompletionSource zzbj;
    private final /* synthetic */ zzdz zzbk;
    private final /* synthetic */ zzq zzbl;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    zzr(zzq zzq, TaskCompletionSource taskCompletionSource, zzdz zzdz) {
        super((zzp) null);
        this.zzbl = zzq;
        this.zzbj = taskCompletionSource;
        this.zzbk = zzdz;
    }

    public final void onError(int i) throws RemoteException {
        this.zzbl.zzbi.zzbd.mo6870d("onError: %d", Integer.valueOf(i));
        this.zzbl.zzbi.m19a_();
        TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, this.zzbj);
    }

    public final void zza(int i, int i2, Surface surface) throws RemoteException {
        this.zzbl.zzbi.zzbd.mo6870d("onConnected", new Object[0]);
        DisplayManager displayManager = (DisplayManager) this.zzbl.zzbi.getApplicationContext().getSystemService(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION);
        if (displayManager == null) {
            this.zzbl.zzbi.zzbd.mo6871e("Unable to get the display manager", new Object[0]);
        } else {
            this.zzbl.zzbi.m19a_();
            VirtualDisplay unused = this.zzbl.zzbi.zzbe = displayManager.createVirtualDisplay("private_display", i, i2, CastRemoteDisplayClient.zza(i, i2), surface, 2);
            if (this.zzbl.zzbi.zzbe == null) {
                this.zzbl.zzbi.zzbd.mo6871e("Unable to create virtual display", new Object[0]);
            } else {
                Display display = this.zzbl.zzbi.zzbe.getDisplay();
                if (display == null) {
                    this.zzbl.zzbi.zzbd.mo6871e("Virtual display does not have a display", new Object[0]);
                } else {
                    try {
                        ((zzee) this.zzbk.getService()).zza(this, display.getDisplayId());
                        return;
                    } catch (RemoteException | IllegalStateException unused2) {
                        this.zzbl.zzbi.zzbd.mo6871e("Unable to provision the route's new virtual Display", new Object[0]);
                    }
                }
            }
        }
        TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, this.zzbj);
    }

    public final void zzc() {
        this.zzbl.zzbi.zzbd.mo6870d("onConnectedWithDisplay", new Object[0]);
        if (this.zzbl.zzbi.zzbe == null) {
            this.zzbl.zzbi.zzbd.mo6871e("There is no virtual display", new Object[0]);
            TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, this.zzbj);
            return;
        }
        Display display = this.zzbl.zzbi.zzbe.getDisplay();
        if (display != null) {
            TaskUtil.setResultOrApiException(Status.RESULT_SUCCESS, display, this.zzbj);
            return;
        }
        this.zzbl.zzbi.zzbd.mo6871e("Virtual display no longer has a display", new Object[0]);
        TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, null, this.zzbj);
    }
}

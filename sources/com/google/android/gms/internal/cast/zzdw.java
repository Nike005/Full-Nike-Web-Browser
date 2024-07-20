package com.google.android.gms.internal.cast;

import android.hardware.display.DisplayManager;
import android.hardware.display.VirtualDisplay;
import android.os.RemoteException;
import android.view.Display;
import android.view.Surface;
import com.google.android.gms.common.api.Status;
import com.google.firebase.messaging.Constants;
import info.guardianproject.netcipher.proxy.PsiphonHelper;

public final class zzdw extends zzdu {
    private final zzea zzxw;
    private final /* synthetic */ zzdv zzxx;

    public zzdw(zzdv zzdv, zzea zzea) {
        this.zzxx = zzdv;
        this.zzxw = zzea;
    }

    public final void onError(int i) throws RemoteException {
        zzdq.zzbd.mo6870d("onError: %d", Integer.valueOf(i));
        this.zzxx.zzxu.m51a_();
        this.zzxx.setResult(new zzdy(Status.RESULT_INTERNAL_ERROR));
    }

    public final void zza(int i, int i2, Surface surface) {
        zzdv zzdv;
        zzdy zzdy;
        zzdq.zzbd.mo6870d("onConnected", new Object[0]);
        DisplayManager displayManager = (DisplayManager) this.zzxw.getContext().getSystemService(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION);
        if (displayManager == null) {
            zzdq.zzbd.mo6871e("Unable to get the display manager", new Object[0]);
            zzdv = this.zzxx;
            zzdy = new zzdy(Status.RESULT_INTERNAL_ERROR);
        } else {
            this.zzxx.zzxu.m51a_();
            VirtualDisplay unused = this.zzxx.zzxu.zzbe = displayManager.createVirtualDisplay("private_display", i, i2, ((i < i2 ? i : i2) * 320) / PsiphonHelper.DEFAULT_SOCKS_PORT, surface, 2);
            if (this.zzxx.zzxu.zzbe == null) {
                zzdq.zzbd.mo6871e("Unable to create virtual display", new Object[0]);
                zzdv = this.zzxx;
                zzdy = new zzdy(Status.RESULT_INTERNAL_ERROR);
            } else if (this.zzxx.zzxu.zzbe.getDisplay() == null) {
                zzdq.zzbd.mo6871e("Virtual display does not have a display", new Object[0]);
                zzdv = this.zzxx;
                zzdy = new zzdy(Status.RESULT_INTERNAL_ERROR);
            } else {
                try {
                    ((zzee) this.zzxw.getService()).zza(this, this.zzxx.zzxu.zzbe.getDisplay().getDisplayId());
                    return;
                } catch (RemoteException | IllegalStateException unused2) {
                    zzdq.zzbd.mo6871e("Unable to provision the route's new virtual Display", new Object[0]);
                    zzdv = this.zzxx;
                    zzdy = new zzdy(Status.RESULT_INTERNAL_ERROR);
                }
            }
        }
        zzdv.setResult(zzdy);
    }

    public final void zzc() {
        zzdq.zzbd.mo6870d("onConnectedWithDisplay", new Object[0]);
        if (this.zzxx.zzxu.zzbe == null) {
            zzdq.zzbd.mo6871e("There is no virtual display", new Object[0]);
            this.zzxx.setResult(new zzdy(Status.RESULT_INTERNAL_ERROR));
            return;
        }
        Display display = this.zzxx.zzxu.zzbe.getDisplay();
        if (display != null) {
            this.zzxx.setResult(new zzdy(display));
            return;
        }
        zzdq.zzbd.mo6871e("Virtual display no longer has a display", new Object[0]);
        this.zzxx.setResult(new zzdy(Status.RESULT_INTERNAL_ERROR));
    }
}

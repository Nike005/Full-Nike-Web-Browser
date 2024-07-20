package com.google.android.gms.cast;

import android.app.PendingIntent;
import android.content.Context;
import android.hardware.display.VirtualDisplay;
import android.os.RemoteException;
import android.view.Display;
import android.view.Surface;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.internal.cast.zzdf;
import com.google.android.gms.internal.cast.zzdg;
import com.google.android.gms.internal.cast.zzdz;
import com.google.android.gms.internal.cast.zzed;
import com.google.android.gms.tasks.Task;
import info.guardianproject.netcipher.proxy.PsiphonHelper;

public class CastRemoteDisplayClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    private static final Api<Api.ApiOptions.NoOptions> API;
    private static final Api.AbstractClientBuilder<zzdz, Api.ApiOptions.NoOptions> CLIENT_BUILDER;
    /* access modifiers changed from: private */
    public final zzdg zzbd = new zzdg("CastRemoteDisplay");
    /* access modifiers changed from: private */
    public VirtualDisplay zzbe;

    private static class zza extends zzed {
        private zza() {
        }

        /* synthetic */ zza(zzp zzp) {
            this();
        }

        public void onDisconnected() throws RemoteException {
            throw new UnsupportedOperationException();
        }

        public void onError(int i) throws RemoteException {
            throw new UnsupportedOperationException();
        }

        public void zza(int i, int i2, Surface surface) throws RemoteException {
            throw new UnsupportedOperationException();
        }

        public void zzc() throws RemoteException {
            throw new UnsupportedOperationException();
        }
    }

    static {
        zzp zzp = new zzp();
        CLIENT_BUILDER = zzp;
        API = new Api<>("CastRemoteDisplay.API", zzp, zzdf.zzwf);
    }

    CastRemoteDisplayClient(Context context) {
        super(context, API, null, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    /* access modifiers changed from: private */
    /* renamed from: a_ */
    public final void m19a_() {
        VirtualDisplay virtualDisplay = this.zzbe;
        if (virtualDisplay != null) {
            if (virtualDisplay.getDisplay() != null) {
                zzdg zzdg = this.zzbd;
                int displayId = this.zzbe.getDisplay().getDisplayId();
                StringBuilder sb = new StringBuilder(38);
                sb.append("releasing virtual display: ");
                sb.append(displayId);
                zzdg.mo6870d(sb.toString(), new Object[0]);
            }
            this.zzbe.release();
            this.zzbe = null;
        }
    }

    /* access modifiers changed from: private */
    public static int zza(int i, int i2) {
        return (Math.min(i, i2) * 320) / PsiphonHelper.DEFAULT_SOCKS_PORT;
    }

    public Task<Display> startRemoteDisplay(CastDevice castDevice, String str, int i, PendingIntent pendingIntent) {
        return doWrite(new zzq(this, i, pendingIntent, castDevice, str));
    }

    public Task<Void> stopRemoteDisplay() {
        return doWrite(new zzs(this));
    }
}

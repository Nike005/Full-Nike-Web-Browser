package com.google.android.gms.internal.cast;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.zzad;
import com.google.android.gms.cast.zzaf;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.BinderWrapper;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public final class zzcn extends GmsClient<zzcz> {
    /* access modifiers changed from: private */
    public static final zzdg zzbd = new zzdg("CastClientImpl");
    /* access modifiers changed from: private */
    public static final Object zzvp = new Object();
    private static final Object zzvq = new Object();
    private final Bundle extras;
    /* access modifiers changed from: private */
    public final Cast.Listener zzaj;
    private double zzei;
    private boolean zzej;
    /* access modifiers changed from: private */
    public final CastDevice zzhq;
    /* access modifiers changed from: private */
    public ApplicationMetadata zzux;
    /* access modifiers changed from: private */
    public final Map<String, Cast.MessageReceivedCallback> zzuy = new HashMap();
    private final long zzuz;
    private zzcp zzva;
    /* access modifiers changed from: private */
    public String zzvb;
    private boolean zzvc;
    private boolean zzvd;
    private boolean zzve;
    private zzad zzvf;
    private int zzvg;
    private int zzvh;
    private final AtomicLong zzvi = new AtomicLong(0);
    /* access modifiers changed from: private */
    public String zzvj;
    /* access modifiers changed from: private */
    public String zzvk;
    private Bundle zzvl;
    private final Map<Long, BaseImplementation.ResultHolder<Status>> zzvm = new HashMap();
    /* access modifiers changed from: private */
    public BaseImplementation.ResultHolder<Cast.ApplicationConnectionResult> zzvn;
    private BaseImplementation.ResultHolder<Status> zzvo;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzcn(Context context, Looper looper, ClientSettings clientSettings, CastDevice castDevice, long j, Cast.Listener listener, Bundle bundle, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 10, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zzhq = castDevice;
        this.zzaj = listener;
        this.zzuz = j;
        this.extras = bundle;
        zzcp();
    }

    private final void zza(BaseImplementation.ResultHolder<Cast.ApplicationConnectionResult> resultHolder) {
        synchronized (zzvp) {
            if (this.zzvn != null) {
                this.zzvn.setResult(new zzco(new Status(2002)));
            }
            this.zzvn = resultHolder;
        }
    }

    /* access modifiers changed from: private */
    public final void zza(zzcd zzcd) {
        boolean z;
        String zzcl = zzcd.zzcl();
        if (!zzcu.zza(zzcl, this.zzvb)) {
            this.zzvb = zzcl;
            z = true;
        } else {
            z = false;
        }
        zzbd.mo6870d("hasChanged=%b, mFirstApplicationStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(this.zzvc));
        if (this.zzaj != null && (z || this.zzvc)) {
            this.zzaj.onApplicationStatusChanged();
        }
        this.zzvc = false;
    }

    /* access modifiers changed from: private */
    public final void zza(zzcv zzcv) {
        boolean z;
        boolean z2;
        boolean z3;
        ApplicationMetadata applicationMetadata = zzcv.getApplicationMetadata();
        if (!zzcu.zza(applicationMetadata, this.zzux)) {
            this.zzux = applicationMetadata;
            this.zzaj.onApplicationMetadataChanged(applicationMetadata);
        }
        double volume = zzcv.getVolume();
        if (Double.isNaN(volume) || Math.abs(volume - this.zzei) <= 1.0E-7d) {
            z = false;
        } else {
            this.zzei = volume;
            z = true;
        }
        boolean zzcv2 = zzcv.zzcv();
        if (zzcv2 != this.zzej) {
            this.zzej = zzcv2;
            z = true;
        }
        zzbd.mo6870d("hasVolumeChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(this.zzvd));
        if (this.zzaj != null && (z || this.zzvd)) {
            this.zzaj.onVolumeChanged();
        }
        int activeInputState = zzcv.getActiveInputState();
        if (activeInputState != this.zzvg) {
            this.zzvg = activeInputState;
            z2 = true;
        } else {
            z2 = false;
        }
        zzbd.mo6870d("hasActiveInputChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z2), Boolean.valueOf(this.zzvd));
        if (this.zzaj != null && (z2 || this.zzvd)) {
            this.zzaj.onActiveInputStateChanged(this.zzvg);
        }
        int standbyState = zzcv.getStandbyState();
        if (standbyState != this.zzvh) {
            this.zzvh = standbyState;
            z3 = true;
        } else {
            z3 = false;
        }
        zzbd.mo6870d("hasStandbyStateChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z3), Boolean.valueOf(this.zzvd));
        if (this.zzaj != null && (z3 || this.zzvd)) {
            this.zzaj.onStandbyStateChanged(this.zzvh);
        }
        if (!zzcu.zza(this.zzvf, zzcv.zzcw())) {
            this.zzvf = zzcv.zzcw();
        }
        this.zzvd = false;
    }

    /* access modifiers changed from: private */
    public final void zzb(long j, int i) {
        BaseImplementation.ResultHolder remove;
        synchronized (this.zzvm) {
            remove = this.zzvm.remove(Long.valueOf(j));
        }
        if (remove != null) {
            remove.setResult(new Status(i));
        }
    }

    private final void zzc(BaseImplementation.ResultHolder<Status> resultHolder) {
        synchronized (zzvq) {
            if (this.zzvo != null) {
                resultHolder.setResult(new Status(2001));
            } else {
                this.zzvo = resultHolder;
            }
        }
    }

    /* access modifiers changed from: private */
    public final void zzcp() {
        this.zzve = false;
        this.zzvg = -1;
        this.zzvh = -1;
        this.zzux = null;
        this.zzvb = null;
        this.zzei = 0.0d;
        this.zzej = false;
        this.zzvf = null;
    }

    private final void zzcq() {
        zzbd.mo6870d("removing all MessageReceivedCallbacks", new Object[0]);
        synchronized (this.zzuy) {
            this.zzuy.clear();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r1.zzva;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzcr() {
        /*
            r1 = this;
            boolean r0 = r1.zzve
            if (r0 == 0) goto L_0x0010
            com.google.android.gms.internal.cast.zzcp r0 = r1.zzva
            if (r0 == 0) goto L_0x0010
            boolean r0 = r0.isDisposed()
            if (r0 != 0) goto L_0x0010
            r0 = 1
            return r0
        L_0x0010:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.zzcn.zzcr():boolean");
    }

    /* access modifiers changed from: private */
    public final void zzm(int i) {
        synchronized (zzvq) {
            if (this.zzvo != null) {
                this.zzvo.setResult(new Status(i));
                this.zzvo = null;
            }
        }
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        return queryLocalInterface instanceof zzcz ? (zzcz) queryLocalInterface : new zzda(iBinder);
    }

    public final void disconnect() {
        zzbd.mo6870d("disconnect(); ServiceListener=%s, isConnected=%b", this.zzva, Boolean.valueOf(isConnected()));
        zzcp zzcp = this.zzva;
        this.zzva = null;
        if (zzcp == null || zzcp.zzcu() == null) {
            zzbd.mo6870d("already disposed, so short-circuiting", new Object[0]);
            return;
        }
        zzcq();
        try {
            ((zzcz) getService()).disconnect();
        } catch (RemoteException | IllegalStateException e) {
            zzbd.zza(e, "Error while disconnecting the controller interface: %s", e.getMessage());
        } finally {
            super.disconnect();
        }
    }

    public final int getActiveInputState() throws IllegalStateException {
        checkConnected();
        return this.zzvg;
    }

    public final ApplicationMetadata getApplicationMetadata() throws IllegalStateException {
        checkConnected();
        return this.zzux;
    }

    public final String getApplicationStatus() throws IllegalStateException {
        checkConnected();
        return this.zzvb;
    }

    public final Bundle getConnectionHint() {
        Bundle bundle = this.zzvl;
        if (bundle == null) {
            return super.getConnectionHint();
        }
        this.zzvl = null;
        return bundle;
    }

    /* access modifiers changed from: protected */
    public final Bundle getGetServiceRequestExtraArgs() {
        Bundle bundle = new Bundle();
        zzbd.mo6870d("getRemoteService(): mLastApplicationId=%s, mLastSessionId=%s", this.zzvj, this.zzvk);
        this.zzhq.putInBundle(bundle);
        bundle.putLong("com.google.android.gms.cast.EXTRA_CAST_FLAGS", this.zzuz);
        Bundle bundle2 = this.extras;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        zzcp zzcp = new zzcp(this);
        this.zzva = zzcp;
        bundle.putParcelable(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, new BinderWrapper(zzcp.asBinder()));
        String str = this.zzvj;
        if (str != null) {
            bundle.putString("last_application_id", str);
            String str2 = this.zzvk;
            if (str2 != null) {
                bundle.putString("last_session_id", str2);
            }
        }
        return bundle;
    }

    public final int getMinApkVersion() {
        return GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
    }

    /* access modifiers changed from: protected */
    public final String getServiceDescriptor() {
        return "com.google.android.gms.cast.internal.ICastDeviceController";
    }

    public final int getStandbyState() throws IllegalStateException {
        checkConnected();
        return this.zzvh;
    }

    /* access modifiers changed from: protected */
    public final String getStartServiceAction() {
        return "com.google.android.gms.cast.service.BIND_CAST_DEVICE_CONTROLLER_SERVICE";
    }

    public final double getVolume() throws IllegalStateException {
        checkConnected();
        return this.zzei;
    }

    public final boolean isMute() throws IllegalStateException {
        checkConnected();
        return this.zzej;
    }

    public final void onConnectionFailed(ConnectionResult connectionResult) {
        super.onConnectionFailed(connectionResult);
        zzcq();
    }

    /* access modifiers changed from: protected */
    public final void onPostInitHandler(int i, IBinder iBinder, Bundle bundle, int i2) {
        zzbd.mo6870d("in onPostInitHandler; statusCode=%d", Integer.valueOf(i));
        if (i == 0 || i == 1001) {
            this.zzve = true;
            this.zzvc = true;
            this.zzvd = true;
        } else {
            this.zzve = false;
        }
        if (i == 1001) {
            Bundle bundle2 = new Bundle();
            this.zzvl = bundle2;
            bundle2.putBoolean(Cast.EXTRA_APP_NO_LONGER_RUNNING, true);
            i = 0;
        }
        super.onPostInitHandler(i, iBinder, bundle, i2);
    }

    public final void removeMessageReceivedCallbacks(String str) throws IllegalArgumentException, RemoteException {
        Cast.MessageReceivedCallback remove;
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.zzuy) {
                remove = this.zzuy.remove(str);
            }
            if (remove != null) {
                try {
                    ((zzcz) getService()).zzs(str);
                } catch (IllegalStateException e) {
                    zzbd.zza(e, "Error unregistering namespace (%s): %s", str, e.getMessage());
                }
            }
        } else {
            throw new IllegalArgumentException("Channel namespace cannot be null or empty");
        }
    }

    public final void requestStatus() throws IllegalStateException, RemoteException {
        zzcz zzcz = (zzcz) getService();
        if (zzcr()) {
            zzcz.requestStatus();
        }
    }

    public final void setMessageReceivedCallbacks(String str, Cast.MessageReceivedCallback messageReceivedCallback) throws IllegalArgumentException, IllegalStateException, RemoteException {
        zzcu.zzo(str);
        removeMessageReceivedCallbacks(str);
        if (messageReceivedCallback != null) {
            synchronized (this.zzuy) {
                this.zzuy.put(str, messageReceivedCallback);
            }
            zzcz zzcz = (zzcz) getService();
            if (zzcr()) {
                zzcz.zzr(str);
            }
        }
    }

    public final void setMute(boolean z) throws IllegalStateException, RemoteException {
        zzcz zzcz = (zzcz) getService();
        if (zzcr()) {
            zzcz.zza(z, this.zzei, this.zzej);
        }
    }

    public final void setVolume(double d) throws IllegalArgumentException, IllegalStateException, RemoteException {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            StringBuilder sb = new StringBuilder(41);
            sb.append("Volume cannot be ");
            sb.append(d);
            throw new IllegalArgumentException(sb.toString());
        }
        zzcz zzcz = (zzcz) getService();
        if (zzcr()) {
            zzcz.zza(d, this.zzei, this.zzej);
        }
    }

    public final void zza(String str, LaunchOptions launchOptions, BaseImplementation.ResultHolder<Cast.ApplicationConnectionResult> resultHolder) throws IllegalStateException, RemoteException {
        zza(resultHolder);
        zzcz zzcz = (zzcz) getService();
        if (zzcr()) {
            zzcz.zzb(str, launchOptions);
        } else {
            zzl(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
        }
    }

    public final void zza(String str, BaseImplementation.ResultHolder<Status> resultHolder) throws IllegalStateException, RemoteException {
        zzc(resultHolder);
        zzcz zzcz = (zzcz) getService();
        if (zzcr()) {
            zzcz.zzi(str);
        } else {
            zzm(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
        }
    }

    public final void zza(String str, String str2, zzaf zzaf, BaseImplementation.ResultHolder<Cast.ApplicationConnectionResult> resultHolder) throws IllegalStateException, RemoteException {
        zza(resultHolder);
        if (zzaf == null) {
            zzaf = new zzaf();
        }
        zzcz zzcz = (zzcz) getService();
        if (zzcr()) {
            zzcz.zza(str, str2, zzaf);
        } else {
            zzl(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
        }
    }

    public final void zza(String str, String str2, BaseImplementation.ResultHolder<Status> resultHolder) throws IllegalArgumentException, IllegalStateException, RemoteException {
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("The message payload cannot be null or empty");
        } else if (str2.length() <= 524288) {
            zzcu.zzo(str);
            long incrementAndGet = this.zzvi.incrementAndGet();
            try {
                this.zzvm.put(Long.valueOf(incrementAndGet), resultHolder);
                zzcz zzcz = (zzcz) getService();
                if (zzcr()) {
                    zzcz.zza(str, str2, incrementAndGet);
                } else {
                    zzb(incrementAndGet, (int) CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
                }
            } catch (Throwable th) {
                this.zzvm.remove(Long.valueOf(incrementAndGet));
                throw th;
            }
        } else {
            zzbd.mo6873w("Message send failed. Message exceeds maximum size", new Object[0]);
            throw new IllegalArgumentException("Message exceeds maximum size");
        }
    }

    public final void zzb(BaseImplementation.ResultHolder<Status> resultHolder) throws IllegalStateException, RemoteException {
        zzc(resultHolder);
        zzcz zzcz = (zzcz) getService();
        if (zzcr()) {
            zzcz.zzcx();
        } else {
            zzm(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
        }
    }

    public final void zzl(int i) {
        synchronized (zzvp) {
            if (this.zzvn != null) {
                this.zzvn.setResult(new zzco(new Status(i)));
                this.zzvn = null;
            }
        }
    }
}

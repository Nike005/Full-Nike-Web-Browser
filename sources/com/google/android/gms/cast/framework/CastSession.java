package com.google.android.gms.cast.framework;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzai;
import com.google.android.gms.internal.cast.zzdg;
import com.google.android.gms.internal.cast.zzdh;
import com.google.android.gms.internal.cast.zze;
import com.google.android.gms.internal.cast.zzg;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class CastSession extends Session {
    /* access modifiers changed from: private */
    public static final zzdg zzbd = new zzdg("CastSession");
    private final Context zzgs;
    private final CastOptions zzgy;
    /* access modifiers changed from: private */
    public final Set<Cast.Listener> zzhj = new HashSet();
    /* access modifiers changed from: private */
    public final zzl zzhk;
    /* access modifiers changed from: private */
    public final Cast.CastApi zzhl;
    private final zzg zzhm;
    /* access modifiers changed from: private */
    public final zzai zzhn;
    /* access modifiers changed from: private */
    public GoogleApiClient zzho;
    /* access modifiers changed from: private */
    public RemoteMediaClient zzhp;
    private CastDevice zzhq;
    /* access modifiers changed from: private */
    public Cast.ApplicationConnectionResult zzhr;

    private class zza implements ResultCallback<Cast.ApplicationConnectionResult> {
        private String command;

        zza(String str) {
            this.command = str;
        }

        public final /* synthetic */ void onResult(Result result) {
            Cast.ApplicationConnectionResult applicationConnectionResult = (Cast.ApplicationConnectionResult) result;
            Cast.ApplicationConnectionResult unused = CastSession.this.zzhr = applicationConnectionResult;
            try {
                if (applicationConnectionResult.getStatus().isSuccess()) {
                    CastSession.zzbd.mo6870d("%s() -> success result", this.command);
                    RemoteMediaClient unused2 = CastSession.this.zzhp = new RemoteMediaClient(new zzdh((String) null), CastSession.this.zzhl);
                    try {
                        CastSession.this.zzhp.zzb(CastSession.this.zzho);
                        CastSession.this.zzhp.zzbl();
                        CastSession.this.zzhp.requestStatus();
                        CastSession.this.zzhn.zza(CastSession.this.zzhp, CastSession.this.getCastDevice());
                    } catch (IOException e) {
                        CastSession.zzbd.zzc(e, "Exception when setting GoogleApiClient.", new Object[0]);
                        RemoteMediaClient unused3 = CastSession.this.zzhp = null;
                    }
                    CastSession.this.zzhk.zza(applicationConnectionResult.getApplicationMetadata(), applicationConnectionResult.getApplicationStatus(), applicationConnectionResult.getSessionId(), applicationConnectionResult.getWasLaunched());
                    return;
                }
                CastSession.zzbd.mo6870d("%s() -> failure result", this.command);
                CastSession.this.zzhk.zzf(applicationConnectionResult.getStatus().getStatusCode());
            } catch (RemoteException e2) {
                CastSession.zzbd.zza(e2, "Unable to call %s on %s.", "methods", zzl.class.getSimpleName());
            }
        }
    }

    private class zzb extends zzi {
        private zzb() {
        }

        public final void zza(String str, LaunchOptions launchOptions) {
            if (CastSession.this.zzho != null) {
                CastSession.this.zzhl.launchApplication(CastSession.this.zzho, str, launchOptions).setResultCallback(new zza("launchApplication"));
            }
        }

        public final void zza(String str, String str2) {
            if (CastSession.this.zzho != null) {
                CastSession.this.zzhl.joinApplication(CastSession.this.zzho, str, str2).setResultCallback(new zza("joinApplication"));
            }
        }

        public final void zze(int i) {
            CastSession.this.zze(i);
        }

        public final void zzi(String str) {
            if (CastSession.this.zzho != null) {
                CastSession.this.zzhl.stopApplication(CastSession.this.zzho, str);
            }
        }

        public final int zzm() {
            return 12451009;
        }
    }

    private class zzc extends Cast.Listener {
        private zzc() {
        }

        public final void onActiveInputStateChanged(int i) {
            for (Cast.Listener onActiveInputStateChanged : new HashSet(CastSession.this.zzhj)) {
                onActiveInputStateChanged.onActiveInputStateChanged(i);
            }
        }

        public final void onApplicationDisconnected(int i) {
            CastSession.this.zze(i);
            CastSession.this.notifySessionEnded(i);
            for (Cast.Listener onApplicationDisconnected : new HashSet(CastSession.this.zzhj)) {
                onApplicationDisconnected.onApplicationDisconnected(i);
            }
        }

        public final void onApplicationMetadataChanged(ApplicationMetadata applicationMetadata) {
            for (Cast.Listener onApplicationMetadataChanged : new HashSet(CastSession.this.zzhj)) {
                onApplicationMetadataChanged.onApplicationMetadataChanged(applicationMetadata);
            }
        }

        public final void onApplicationStatusChanged() {
            for (Cast.Listener onApplicationStatusChanged : new HashSet(CastSession.this.zzhj)) {
                onApplicationStatusChanged.onApplicationStatusChanged();
            }
        }

        public final void onStandbyStateChanged(int i) {
            for (Cast.Listener onStandbyStateChanged : new HashSet(CastSession.this.zzhj)) {
                onStandbyStateChanged.onStandbyStateChanged(i);
            }
        }

        public final void onVolumeChanged() {
            for (Cast.Listener onVolumeChanged : new HashSet(CastSession.this.zzhj)) {
                onVolumeChanged.onVolumeChanged();
            }
        }
    }

    private class zzd implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
        private zzd() {
        }

        public final void onConnected(Bundle bundle) {
            try {
                if (CastSession.this.zzhp != null) {
                    try {
                        CastSession.this.zzhp.zzbl();
                        CastSession.this.zzhp.requestStatus();
                    } catch (IOException e) {
                        CastSession.zzbd.zzc(e, "Exception when setting GoogleApiClient.", new Object[0]);
                        RemoteMediaClient unused = CastSession.this.zzhp = null;
                    }
                }
                CastSession.this.zzhk.onConnected(bundle);
            } catch (RemoteException e2) {
                CastSession.zzbd.zza(e2, "Unable to call %s on %s.", "onConnected", zzl.class.getSimpleName());
            }
        }

        public final void onConnectionFailed(ConnectionResult connectionResult) {
            try {
                CastSession.this.zzhk.onConnectionFailed(connectionResult);
            } catch (RemoteException e) {
                CastSession.zzbd.zza(e, "Unable to call %s on %s.", "onConnectionFailed", zzl.class.getSimpleName());
            }
        }

        public final void onConnectionSuspended(int i) {
            try {
                CastSession.this.zzhk.onConnectionSuspended(i);
            } catch (RemoteException e) {
                CastSession.zzbd.zza(e, "Unable to call %s on %s.", "onConnectionSuspended", zzl.class.getSimpleName());
            }
        }
    }

    public CastSession(Context context, String str, String str2, CastOptions castOptions, Cast.CastApi castApi, zzg zzg, zzai zzai) {
        super(context, str, str2);
        this.zzgs = context.getApplicationContext();
        this.zzgy = castOptions;
        this.zzhl = castApi;
        this.zzhm = zzg;
        this.zzhn = zzai;
        this.zzhk = zze.zza(context, castOptions, zzy(), (zzh) new zzb());
    }

    private final void zzb(Bundle bundle) {
        CastDevice fromBundle = CastDevice.getFromBundle(bundle);
        this.zzhq = fromBundle;
        if (fromBundle != null) {
            GoogleApiClient googleApiClient = this.zzho;
            if (googleApiClient != null) {
                googleApiClient.disconnect();
                this.zzho = null;
            }
            boolean z = true;
            zzbd.mo6870d("Acquiring a connection to Google Play Services for %s", this.zzhq);
            zzd zzd2 = new zzd();
            Context context = this.zzgs;
            CastDevice castDevice = this.zzhq;
            CastOptions castOptions = this.zzgy;
            zzc zzc2 = new zzc();
            Bundle bundle2 = new Bundle();
            bundle2.putBoolean("com.google.android.gms.cast.EXTRA_CAST_FRAMEWORK_NOTIFICATION_ENABLED", (castOptions == null || castOptions.getCastMediaOptions() == null || castOptions.getCastMediaOptions().getNotificationOptions() == null) ? false : true);
            if (castOptions == null || castOptions.getCastMediaOptions() == null || !castOptions.getCastMediaOptions().zzav()) {
                z = false;
            }
            bundle2.putBoolean("com.google.android.gms.cast.EXTRA_CAST_REMOTE_CONTROL_NOTIFICATION_ENABLED", z);
            GoogleApiClient build = new GoogleApiClient.Builder(context).addApi(Cast.API, new Cast.CastOptions.Builder(castDevice, zzc2).zza(bundle2).build()).addConnectionCallbacks(zzd2).addOnConnectionFailedListener(zzd2).build();
            this.zzho = build;
            build.connect();
        } else if (isResuming()) {
            notifyFailedToResumeSession(8);
        } else {
            notifyFailedToStartSession(8);
        }
    }

    /* access modifiers changed from: private */
    public final void zze(int i) {
        this.zzhn.zzi(i);
        GoogleApiClient googleApiClient = this.zzho;
        if (googleApiClient != null) {
            googleApiClient.disconnect();
            this.zzho = null;
        }
        this.zzhq = null;
        RemoteMediaClient remoteMediaClient = this.zzhp;
        if (remoteMediaClient != null) {
            remoteMediaClient.zzb((GoogleApiClient) null);
            this.zzhp = null;
        }
        this.zzhr = null;
    }

    public void addCastListener(Cast.Listener listener) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (listener != null) {
            this.zzhj.add(listener);
        }
    }

    /* access modifiers changed from: protected */
    public void end(boolean z) {
        try {
            this.zzhk.zza(z, 0);
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "disconnectFromDevice", zzl.class.getSimpleName());
        }
        notifySessionEnded(0);
    }

    public int getActiveInputState() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzho;
        if (googleApiClient != null) {
            return this.zzhl.getActiveInputState(googleApiClient);
        }
        return -1;
    }

    public Cast.ApplicationConnectionResult getApplicationConnectionResult() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzhr;
    }

    public ApplicationMetadata getApplicationMetadata() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzho;
        if (googleApiClient != null) {
            return this.zzhl.getApplicationMetadata(googleApiClient);
        }
        return null;
    }

    public String getApplicationStatus() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzho;
        if (googleApiClient != null) {
            return this.zzhl.getApplicationStatus(googleApiClient);
        }
        return null;
    }

    public CastDevice getCastDevice() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzhq;
    }

    public RemoteMediaClient getRemoteMediaClient() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzhp;
    }

    public long getSessionRemainingTimeMs() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        RemoteMediaClient remoteMediaClient = this.zzhp;
        if (remoteMediaClient == null) {
            return 0;
        }
        return remoteMediaClient.getStreamDuration() - this.zzhp.getApproximateStreamPosition();
    }

    public int getStandbyState() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzho;
        if (googleApiClient != null) {
            return this.zzhl.getStandbyState(googleApiClient);
        }
        return -1;
    }

    public double getVolume() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzho;
        if (googleApiClient != null) {
            return this.zzhl.getVolume(googleApiClient);
        }
        return 0.0d;
    }

    public boolean isMute() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzho;
        if (googleApiClient != null) {
            return this.zzhl.isMute(googleApiClient);
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onResuming(Bundle bundle) {
        this.zzhq = CastDevice.getFromBundle(bundle);
    }

    /* access modifiers changed from: protected */
    public void onStarting(Bundle bundle) {
        this.zzhq = CastDevice.getFromBundle(bundle);
    }

    public void removeCastListener(Cast.Listener listener) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (listener != null) {
            this.zzhj.remove(listener);
        }
    }

    public void removeMessageReceivedCallbacks(String str) throws IOException, IllegalArgumentException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzho;
        if (googleApiClient != null) {
            this.zzhl.removeMessageReceivedCallbacks(googleApiClient, str);
        }
    }

    public void requestStatus() throws IOException, IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzho;
        if (googleApiClient != null) {
            this.zzhl.requestStatus(googleApiClient);
        }
    }

    /* access modifiers changed from: protected */
    public void resume(Bundle bundle) {
        zzb(bundle);
    }

    public PendingResult<Status> sendMessage(String str, String str2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzho;
        if (googleApiClient != null) {
            return this.zzhl.sendMessage(googleApiClient, str, str2);
        }
        return null;
    }

    public void setMessageReceivedCallbacks(String str, Cast.MessageReceivedCallback messageReceivedCallback) throws IOException, IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzho;
        if (googleApiClient != null) {
            this.zzhl.setMessageReceivedCallbacks(googleApiClient, str, messageReceivedCallback);
        }
    }

    public void setMute(boolean z) throws IOException, IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzho;
        if (googleApiClient != null) {
            this.zzhl.setMute(googleApiClient, z);
        }
    }

    public void setVolume(double d) throws IOException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzho;
        if (googleApiClient != null) {
            this.zzhl.setVolume(googleApiClient, d);
        }
    }

    /* access modifiers changed from: protected */
    public void start(Bundle bundle) {
        zzb(bundle);
    }

    public final zzai zzs() {
        return this.zzhn;
    }
}

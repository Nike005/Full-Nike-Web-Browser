package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzcf;
import com.google.android.gms.internal.cast.zzcn;
import com.google.android.gms.internal.cast.zzdf;
import java.io.IOException;

public final class Cast {
    public static final int ACTIVE_INPUT_STATE_NO = 0;
    public static final int ACTIVE_INPUT_STATE_UNKNOWN = -1;
    public static final int ACTIVE_INPUT_STATE_YES = 1;
    public static final Api<CastOptions> API;
    private static final Api.AbstractClientBuilder<zzcn, CastOptions> CLIENT_BUILDER;
    public static final CastApi CastApi = new CastApi.zza();
    public static final String EXTRA_APP_NO_LONGER_RUNNING = "com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING";
    public static final int MAX_MESSAGE_LENGTH = 65536;
    public static final int MAX_NAMESPACE_LENGTH = 128;
    public static final int STANDBY_STATE_NO = 0;
    public static final int STANDBY_STATE_UNKNOWN = -1;
    public static final int STANDBY_STATE_YES = 1;

    public interface ApplicationConnectionResult extends Result {
        ApplicationMetadata getApplicationMetadata();

        String getApplicationStatus();

        String getSessionId();

        boolean getWasLaunched();
    }

    @Deprecated
    public interface CastApi {

        public static final class zza implements CastApi {
            private final PendingResult<ApplicationConnectionResult> zza(GoogleApiClient googleApiClient, String str, String str2, zzaf zzaf) {
                return googleApiClient.execute(new zzi(this, googleApiClient, str, str2, (zzaf) null));
            }

            public final int getActiveInputState(GoogleApiClient googleApiClient) throws IllegalStateException {
                return ((zzcn) googleApiClient.getClient(zzdf.zzwd)).getActiveInputState();
            }

            public final ApplicationMetadata getApplicationMetadata(GoogleApiClient googleApiClient) throws IllegalStateException {
                return ((zzcn) googleApiClient.getClient(zzdf.zzwd)).getApplicationMetadata();
            }

            public final String getApplicationStatus(GoogleApiClient googleApiClient) throws IllegalStateException {
                return ((zzcn) googleApiClient.getClient(zzdf.zzwd)).getApplicationStatus();
            }

            public final int getStandbyState(GoogleApiClient googleApiClient) throws IllegalStateException {
                return ((zzcn) googleApiClient.getClient(zzdf.zzwd)).getStandbyState();
            }

            public final double getVolume(GoogleApiClient googleApiClient) throws IllegalStateException {
                return ((zzcn) googleApiClient.getClient(zzdf.zzwd)).getVolume();
            }

            public final boolean isMute(GoogleApiClient googleApiClient) throws IllegalStateException {
                return ((zzcn) googleApiClient.getClient(zzdf.zzwd)).isMute();
            }

            public final PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient) {
                return zza(googleApiClient, (String) null, (String) null, (zzaf) null);
            }

            public final PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient, String str) {
                return zza(googleApiClient, str, (String) null, (zzaf) null);
            }

            public final PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient, String str, String str2) {
                return zza(googleApiClient, str, str2, (zzaf) null);
            }

            public final PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str) {
                return googleApiClient.execute(new zzg(this, googleApiClient, str));
            }

            public final PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str, LaunchOptions launchOptions) {
                return googleApiClient.execute(new zzh(this, googleApiClient, str, launchOptions));
            }

            @Deprecated
            public final PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str, boolean z) {
                return launchApplication(googleApiClient, str, new LaunchOptions.Builder().setRelaunchIfRunning(z).build());
            }

            public final PendingResult<Status> leaveApplication(GoogleApiClient googleApiClient) {
                return googleApiClient.execute(new zzj(this, googleApiClient));
            }

            public final void removeMessageReceivedCallbacks(GoogleApiClient googleApiClient, String str) throws IOException, IllegalArgumentException {
                try {
                    ((zzcn) googleApiClient.getClient(zzdf.zzwd)).removeMessageReceivedCallbacks(str);
                } catch (RemoteException unused) {
                    throw new IOException("service error");
                }
            }

            public final void requestStatus(GoogleApiClient googleApiClient) throws IOException, IllegalStateException {
                try {
                    ((zzcn) googleApiClient.getClient(zzdf.zzwd)).requestStatus();
                } catch (RemoteException unused) {
                    throw new IOException("service error");
                }
            }

            public final PendingResult<Status> sendMessage(GoogleApiClient googleApiClient, String str, String str2) {
                return googleApiClient.execute(new zzf(this, googleApiClient, str, str2));
            }

            public final void setMessageReceivedCallbacks(GoogleApiClient googleApiClient, String str, MessageReceivedCallback messageReceivedCallback) throws IOException, IllegalStateException {
                try {
                    ((zzcn) googleApiClient.getClient(zzdf.zzwd)).setMessageReceivedCallbacks(str, messageReceivedCallback);
                } catch (RemoteException unused) {
                    throw new IOException("service error");
                }
            }

            public final void setMute(GoogleApiClient googleApiClient, boolean z) throws IOException, IllegalStateException {
                try {
                    ((zzcn) googleApiClient.getClient(zzdf.zzwd)).setMute(z);
                } catch (RemoteException unused) {
                    throw new IOException("service error");
                }
            }

            public final void setVolume(GoogleApiClient googleApiClient, double d) throws IOException, IllegalArgumentException, IllegalStateException {
                try {
                    ((zzcn) googleApiClient.getClient(zzdf.zzwd)).setVolume(d);
                } catch (RemoteException unused) {
                    throw new IOException("service error");
                }
            }

            public final PendingResult<Status> stopApplication(GoogleApiClient googleApiClient) {
                return googleApiClient.execute(new zzk(this, googleApiClient));
            }

            public final PendingResult<Status> stopApplication(GoogleApiClient googleApiClient, String str) {
                return googleApiClient.execute(new zzl(this, googleApiClient, str));
            }
        }

        int getActiveInputState(GoogleApiClient googleApiClient) throws IllegalStateException;

        ApplicationMetadata getApplicationMetadata(GoogleApiClient googleApiClient) throws IllegalStateException;

        String getApplicationStatus(GoogleApiClient googleApiClient) throws IllegalStateException;

        int getStandbyState(GoogleApiClient googleApiClient) throws IllegalStateException;

        double getVolume(GoogleApiClient googleApiClient) throws IllegalStateException;

        boolean isMute(GoogleApiClient googleApiClient) throws IllegalStateException;

        PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient);

        PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient, String str);

        PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient, String str, String str2);

        PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str);

        PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str, LaunchOptions launchOptions);

        @Deprecated
        PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str, boolean z);

        PendingResult<Status> leaveApplication(GoogleApiClient googleApiClient);

        void removeMessageReceivedCallbacks(GoogleApiClient googleApiClient, String str) throws IOException, IllegalArgumentException;

        void requestStatus(GoogleApiClient googleApiClient) throws IOException, IllegalStateException;

        PendingResult<Status> sendMessage(GoogleApiClient googleApiClient, String str, String str2);

        void setMessageReceivedCallbacks(GoogleApiClient googleApiClient, String str, MessageReceivedCallback messageReceivedCallback) throws IOException, IllegalStateException;

        void setMute(GoogleApiClient googleApiClient, boolean z) throws IOException, IllegalStateException;

        void setVolume(GoogleApiClient googleApiClient, double d) throws IOException, IllegalArgumentException, IllegalStateException;

        PendingResult<Status> stopApplication(GoogleApiClient googleApiClient);

        PendingResult<Status> stopApplication(GoogleApiClient googleApiClient, String str);
    }

    public static final class CastOptions implements Api.ApiOptions.HasOptions {
        final Bundle extras;
        final CastDevice zzai;
        final Listener zzaj;
        /* access modifiers changed from: private */
        public final int zzak;

        public static final class Builder {
            /* access modifiers changed from: private */
            public Bundle extras;
            CastDevice zzai;
            Listener zzaj;
            /* access modifiers changed from: private */
            public int zzak = 0;

            public Builder(CastDevice castDevice, Listener listener) {
                Preconditions.checkNotNull(castDevice, "CastDevice parameter cannot be null");
                Preconditions.checkNotNull(listener, "CastListener parameter cannot be null");
                this.zzai = castDevice;
                this.zzaj = listener;
            }

            public final CastOptions build() {
                return new CastOptions(this, (zze) null);
            }

            public final Builder setVerboseLoggingEnabled(boolean z) {
                this.zzak = z ? this.zzak | 1 : this.zzak & -2;
                return this;
            }

            public final Builder zza(Bundle bundle) {
                this.extras = bundle;
                return this;
            }
        }

        private CastOptions(Builder builder) {
            this.zzai = builder.zzai;
            this.zzaj = builder.zzaj;
            this.zzak = builder.zzak;
            this.extras = builder.extras;
        }

        /* synthetic */ CastOptions(Builder builder, zze zze) {
            this(builder);
        }

        @Deprecated
        public static Builder builder(CastDevice castDevice, Listener listener) {
            return new Builder(castDevice, listener);
        }
    }

    public static class Listener {
        public void onActiveInputStateChanged(int i) {
        }

        public void onApplicationDisconnected(int i) {
        }

        public void onApplicationMetadataChanged(ApplicationMetadata applicationMetadata) {
        }

        public void onApplicationStatusChanged() {
        }

        public void onStandbyStateChanged(int i) {
        }

        public void onVolumeChanged() {
        }
    }

    public interface MessageReceivedCallback {
        void onMessageReceived(CastDevice castDevice, String str, String str2);
    }

    static abstract class zza extends zzcf<ApplicationConnectionResult> {
        public zza(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public /* synthetic */ Result createFailedResult(Status status) {
            return new zzm(this, status);
        }

        /* renamed from: zza */
        public void doExecute(zzcn zzcn) throws RemoteException {
        }
    }

    static {
        zze zze = new zze();
        CLIENT_BUILDER = zze;
        API = new Api<>("Cast.API", zze, zzdf.zzwd);
    }

    private Cast() {
    }
}

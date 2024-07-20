package com.google.android.gms.cast;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.cast.zzcf;
import com.google.android.gms.internal.cast.zzcn;
import com.google.android.gms.internal.cast.zzdh;
import com.google.android.gms.internal.cast.zzdj;
import com.google.android.gms.internal.cast.zzdl;
import com.google.android.gms.internal.cast.zzdm;
import org.json.JSONObject;

@Deprecated
public class RemoteMediaPlayer implements Cast.MessageReceivedCallback {
    public static final String NAMESPACE = zzdh.NAMESPACE;
    public static final int RESUME_STATE_PAUSE = 2;
    public static final int RESUME_STATE_PLAY = 1;
    public static final int RESUME_STATE_UNCHANGED = 0;
    public static final int STATUS_CANCELED = 2101;
    public static final int STATUS_FAILED = 2100;
    public static final int STATUS_REPLACED = 2103;
    public static final int STATUS_SUCCEEDED = 0;
    public static final int STATUS_TIMED_OUT = 2102;
    /* access modifiers changed from: private */
    public final Object lock;
    /* access modifiers changed from: private */
    public final zzdh zzeu;
    /* access modifiers changed from: private */
    public final zza zzev;
    private OnPreloadStatusUpdatedListener zzew;
    private OnQueueStatusUpdatedListener zzex;
    private OnMetadataUpdatedListener zzey;
    private OnStatusUpdatedListener zzez;

    @Deprecated
    public interface MediaChannelResult extends Result {
        JSONObject getCustomData();
    }

    @Deprecated
    public interface OnMetadataUpdatedListener {
        void onMetadataUpdated();
    }

    @Deprecated
    public interface OnPreloadStatusUpdatedListener {
        void onPreloadStatusUpdated();
    }

    @Deprecated
    public interface OnQueueStatusUpdatedListener {
        void onQueueStatusUpdated();
    }

    @Deprecated
    public interface OnStatusUpdatedListener {
        void onStatusUpdated();
    }

    private class zza implements zzdl {
        private GoogleApiClient zzfy;
        private long zzfz = 0;

        public zza() {
        }

        public final void zza(GoogleApiClient googleApiClient) {
            this.zzfy = googleApiClient;
        }

        public final void zza(String str, String str2, long j, String str3) {
            if (this.zzfy != null) {
                Cast.CastApi.sendMessage(this.zzfy, str, str2).setResultCallback(new zzbm(this, j));
                return;
            }
            throw new IllegalStateException("No GoogleApiClient available");
        }

        public final long zzl() {
            long j = this.zzfz + 1;
            this.zzfz = j;
            return j;
        }
    }

    static abstract class zzb extends zzcf<MediaChannelResult> {
        zzdm zzgc = new zzbn(this);

        zzb(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public /* synthetic */ Result createFailedResult(Status status) {
            return new zzbo(this, status);
        }

        /* access modifiers changed from: protected */
        /* renamed from: zza */
        public void doExecute(zzcn zzcn) {
        }
    }

    private static final class zzc implements MediaChannelResult {
        private final Status zzge;
        private final JSONObject zzp;

        zzc(Status status, JSONObject jSONObject) {
            this.zzge = status;
            this.zzp = jSONObject;
        }

        public final JSONObject getCustomData() {
            return this.zzp;
        }

        public final Status getStatus() {
            return this.zzge;
        }
    }

    public RemoteMediaPlayer() {
        this(new zzdh((String) null));
    }

    private RemoteMediaPlayer(zzdh zzdh) {
        this.lock = new Object();
        this.zzeu = zzdh;
        zzdh.zza((zzdj) new zzap(this));
        zza zza2 = new zza();
        this.zzev = zza2;
        this.zzeu.zza(zza2);
    }

    /* access modifiers changed from: private */
    public final void onMetadataUpdated() {
        OnMetadataUpdatedListener onMetadataUpdatedListener = this.zzey;
        if (onMetadataUpdatedListener != null) {
            onMetadataUpdatedListener.onMetadataUpdated();
        }
    }

    /* access modifiers changed from: private */
    public final void onPreloadStatusUpdated() {
        OnPreloadStatusUpdatedListener onPreloadStatusUpdatedListener = this.zzew;
        if (onPreloadStatusUpdatedListener != null) {
            onPreloadStatusUpdatedListener.onPreloadStatusUpdated();
        }
    }

    /* access modifiers changed from: private */
    public final void onQueueStatusUpdated() {
        OnQueueStatusUpdatedListener onQueueStatusUpdatedListener = this.zzex;
        if (onQueueStatusUpdatedListener != null) {
            onQueueStatusUpdatedListener.onQueueStatusUpdated();
        }
    }

    /* access modifiers changed from: private */
    public final void onStatusUpdated() {
        OnStatusUpdatedListener onStatusUpdatedListener = this.zzez;
        if (onStatusUpdatedListener != null) {
            onStatusUpdatedListener.onStatusUpdated();
        }
    }

    /* access modifiers changed from: private */
    public final int zzc(int i) {
        MediaStatus mediaStatus = getMediaStatus();
        for (int i2 = 0; i2 < mediaStatus.getQueueItemCount(); i2++) {
            if (mediaStatus.getQueueItem(i2).getItemId() == i) {
                return i2;
            }
        }
        return -1;
    }

    public long getApproximateStreamPosition() {
        long approximateStreamPosition;
        synchronized (this.lock) {
            approximateStreamPosition = this.zzeu.getApproximateStreamPosition();
        }
        return approximateStreamPosition;
    }

    public MediaInfo getMediaInfo() {
        MediaInfo mediaInfo;
        synchronized (this.lock) {
            mediaInfo = this.zzeu.getMediaInfo();
        }
        return mediaInfo;
    }

    public MediaStatus getMediaStatus() {
        MediaStatus mediaStatus;
        synchronized (this.lock) {
            mediaStatus = this.zzeu.getMediaStatus();
        }
        return mediaStatus;
    }

    public String getNamespace() {
        return this.zzeu.getNamespace();
    }

    public long getStreamDuration() {
        long streamDuration;
        synchronized (this.lock) {
            streamDuration = this.zzeu.getStreamDuration();
        }
        return streamDuration;
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient googleApiClient, MediaInfo mediaInfo) {
        return load(googleApiClient, mediaInfo, true, 0, (long[]) null, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient googleApiClient, MediaInfo mediaInfo, boolean z) {
        return load(googleApiClient, mediaInfo, z, 0, (long[]) null, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient googleApiClient, MediaInfo mediaInfo, boolean z, long j) {
        return load(googleApiClient, mediaInfo, z, j, (long[]) null, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient googleApiClient, MediaInfo mediaInfo, boolean z, long j, JSONObject jSONObject) {
        return load(googleApiClient, mediaInfo, z, j, (long[]) null, jSONObject);
    }

    public PendingResult<MediaChannelResult> load(GoogleApiClient googleApiClient, MediaInfo mediaInfo, boolean z, long j, long[] jArr, JSONObject jSONObject) {
        GoogleApiClient googleApiClient2 = googleApiClient;
        return googleApiClient.execute(new zzba(this, googleApiClient, googleApiClient, z, j, jArr, jSONObject, mediaInfo));
    }

    public void onMessageReceived(CastDevice castDevice, String str, String str2) {
        this.zzeu.zzn(str2);
    }

    public PendingResult<MediaChannelResult> pause(GoogleApiClient googleApiClient) {
        return pause(googleApiClient, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> pause(GoogleApiClient googleApiClient, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbf(this, googleApiClient, googleApiClient, jSONObject));
    }

    public PendingResult<MediaChannelResult> play(GoogleApiClient googleApiClient) {
        return play(googleApiClient, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> play(GoogleApiClient googleApiClient, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbh(this, googleApiClient, googleApiClient, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueAppendItem(GoogleApiClient googleApiClient, MediaQueueItem mediaQueueItem, JSONObject jSONObject) throws IllegalArgumentException {
        return queueInsertItems(googleApiClient, new MediaQueueItem[]{mediaQueueItem}, 0, jSONObject);
    }

    public PendingResult<MediaChannelResult> queueInsertAndPlayItem(GoogleApiClient googleApiClient, MediaQueueItem mediaQueueItem, int i, long j, JSONObject jSONObject) {
        GoogleApiClient googleApiClient2 = googleApiClient;
        return googleApiClient.execute(new zzau(this, googleApiClient, googleApiClient, mediaQueueItem, i, j, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueInsertAndPlayItem(GoogleApiClient googleApiClient, MediaQueueItem mediaQueueItem, int i, JSONObject jSONObject) {
        return queueInsertAndPlayItem(googleApiClient, mediaQueueItem, i, -1, jSONObject);
    }

    public PendingResult<MediaChannelResult> queueInsertItems(GoogleApiClient googleApiClient, MediaQueueItem[] mediaQueueItemArr, int i, JSONObject jSONObject) throws IllegalArgumentException {
        return googleApiClient.execute(new zzat(this, googleApiClient, googleApiClient, mediaQueueItemArr, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueJumpToItem(GoogleApiClient googleApiClient, int i, long j, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbd(this, googleApiClient, i, googleApiClient, j, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueJumpToItem(GoogleApiClient googleApiClient, int i, JSONObject jSONObject) {
        return queueJumpToItem(googleApiClient, i, -1, jSONObject);
    }

    public PendingResult<MediaChannelResult> queueLoad(GoogleApiClient googleApiClient, MediaQueueItem[] mediaQueueItemArr, int i, int i2, long j, JSONObject jSONObject) throws IllegalArgumentException {
        GoogleApiClient googleApiClient2 = googleApiClient;
        return googleApiClient.execute(new zzas(this, googleApiClient, googleApiClient, mediaQueueItemArr, i, i2, j, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueLoad(GoogleApiClient googleApiClient, MediaQueueItem[] mediaQueueItemArr, int i, int i2, JSONObject jSONObject) throws IllegalArgumentException {
        return queueLoad(googleApiClient, mediaQueueItemArr, i, i2, -1, jSONObject);
    }

    public PendingResult<MediaChannelResult> queueMoveItemToNewIndex(GoogleApiClient googleApiClient, int i, int i2, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbe(this, googleApiClient, i, i2, googleApiClient, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueNext(GoogleApiClient googleApiClient, JSONObject jSONObject) {
        return googleApiClient.execute(new zzaz(this, googleApiClient, googleApiClient, jSONObject));
    }

    public PendingResult<MediaChannelResult> queuePrev(GoogleApiClient googleApiClient, JSONObject jSONObject) {
        return googleApiClient.execute(new zzay(this, googleApiClient, googleApiClient, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueRemoveItem(GoogleApiClient googleApiClient, int i, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbc(this, googleApiClient, i, googleApiClient, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueRemoveItems(GoogleApiClient googleApiClient, int[] iArr, JSONObject jSONObject) throws IllegalArgumentException {
        return googleApiClient.execute(new zzaw(this, googleApiClient, googleApiClient, iArr, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueReorderItems(GoogleApiClient googleApiClient, int[] iArr, int i, JSONObject jSONObject) throws IllegalArgumentException {
        return googleApiClient.execute(new zzax(this, googleApiClient, googleApiClient, iArr, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueSetRepeatMode(GoogleApiClient googleApiClient, int i, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbb(this, googleApiClient, googleApiClient, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueUpdateItems(GoogleApiClient googleApiClient, MediaQueueItem[] mediaQueueItemArr, JSONObject jSONObject) {
        return googleApiClient.execute(new zzav(this, googleApiClient, googleApiClient, mediaQueueItemArr, jSONObject));
    }

    public PendingResult<MediaChannelResult> requestStatus(GoogleApiClient googleApiClient) {
        return googleApiClient.execute(new zzbl(this, googleApiClient, googleApiClient));
    }

    public PendingResult<MediaChannelResult> seek(GoogleApiClient googleApiClient, long j) {
        return seek(googleApiClient, j, 0, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> seek(GoogleApiClient googleApiClient, long j, int i) {
        return seek(googleApiClient, j, i, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> seek(GoogleApiClient googleApiClient, long j, int i, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbi(this, googleApiClient, googleApiClient, j, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> setActiveMediaTracks(GoogleApiClient googleApiClient, long[] jArr) {
        if (jArr != null) {
            return googleApiClient.execute(new zzaq(this, googleApiClient, googleApiClient, jArr));
        }
        throw new IllegalArgumentException("trackIds cannot be null");
    }

    public void setOnMetadataUpdatedListener(OnMetadataUpdatedListener onMetadataUpdatedListener) {
        this.zzey = onMetadataUpdatedListener;
    }

    public void setOnPreloadStatusUpdatedListener(OnPreloadStatusUpdatedListener onPreloadStatusUpdatedListener) {
        this.zzew = onPreloadStatusUpdatedListener;
    }

    public void setOnQueueStatusUpdatedListener(OnQueueStatusUpdatedListener onQueueStatusUpdatedListener) {
        this.zzex = onQueueStatusUpdatedListener;
    }

    public void setOnStatusUpdatedListener(OnStatusUpdatedListener onStatusUpdatedListener) {
        this.zzez = onStatusUpdatedListener;
    }

    public PendingResult<MediaChannelResult> setStreamMute(GoogleApiClient googleApiClient, boolean z) {
        return setStreamMute(googleApiClient, z, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> setStreamMute(GoogleApiClient googleApiClient, boolean z, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbk(this, googleApiClient, googleApiClient, z, jSONObject));
    }

    public PendingResult<MediaChannelResult> setStreamVolume(GoogleApiClient googleApiClient, double d) throws IllegalArgumentException {
        return setStreamVolume(googleApiClient, d, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> setStreamVolume(GoogleApiClient googleApiClient, double d, JSONObject jSONObject) throws IllegalArgumentException {
        if (!Double.isInfinite(d) && !Double.isNaN(d)) {
            return googleApiClient.execute(new zzbj(this, googleApiClient, googleApiClient, d, jSONObject));
        }
        StringBuilder sb = new StringBuilder(41);
        sb.append("Volume cannot be ");
        sb.append(d);
        throw new IllegalArgumentException(sb.toString());
    }

    public PendingResult<MediaChannelResult> setTextTrackStyle(GoogleApiClient googleApiClient, TextTrackStyle textTrackStyle) {
        if (textTrackStyle != null) {
            return googleApiClient.execute(new zzar(this, googleApiClient, googleApiClient, textTrackStyle));
        }
        throw new IllegalArgumentException("trackStyle cannot be null");
    }

    public PendingResult<MediaChannelResult> stop(GoogleApiClient googleApiClient) {
        return stop(googleApiClient, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> stop(GoogleApiClient googleApiClient, JSONObject jSONObject) {
        return googleApiClient.execute(new zzbg(this, googleApiClient, googleApiClient, jSONObject));
    }
}

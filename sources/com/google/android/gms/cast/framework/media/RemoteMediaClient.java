package com.google.android.gms.cast.framework.media;

import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.cast.AdBreakInfo;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaLoadOptions;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.TextTrackStyle;
import com.google.android.gms.cast.zzbp;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzcf;
import com.google.android.gms.internal.cast.zzcn;
import com.google.android.gms.internal.cast.zzdh;
import com.google.android.gms.internal.cast.zzdj;
import com.google.android.gms.internal.cast.zzdl;
import com.google.android.gms.internal.cast.zzdm;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

public class RemoteMediaClient implements Cast.MessageReceivedCallback {
    public static final String NAMESPACE = zzdh.NAMESPACE;
    public static final int RESUME_STATE_PAUSE = 2;
    public static final int RESUME_STATE_PLAY = 1;
    public static final int RESUME_STATE_UNCHANGED = 0;
    public static final int STATUS_FAILED = 2100;
    public static final int STATUS_REPLACED = 2103;
    public static final int STATUS_SUCCEEDED = 0;
    /* access modifiers changed from: private */
    public final Handler handler = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public final Object lock = new Object();
    /* access modifiers changed from: private */
    public final zzdh zzeu;
    /* access modifiers changed from: private */
    public final Cast.CastApi zzhl;
    private final MediaQueue zzlt;
    private final zza zznl = new zza();
    private GoogleApiClient zznm;
    /* access modifiers changed from: private */
    public final List<Listener> zznn = new CopyOnWriteArrayList();
    final List<Callback> zzno = new CopyOnWriteArrayList();
    private final Map<ProgressListener, zze> zznp = new ConcurrentHashMap();
    private final Map<Long, zze> zznq = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public ParseAdsInfoCallback zznr;

    public static abstract class Callback {
        public void onAdBreakStatusUpdated() {
        }

        public void onMetadataUpdated() {
        }

        public void onPreloadStatusUpdated() {
        }

        public void onQueueStatusUpdated() {
        }

        public void onSendingRemoteMediaRequest() {
        }

        public void onStatusUpdated() {
        }

        public void zza(int[] iArr) {
        }

        public void zza(int[] iArr, int i) {
        }

        public void zzb(int[] iArr) {
        }

        public void zzb(MediaQueueItem[] mediaQueueItemArr) {
        }

        public void zzc(int[] iArr) {
        }
    }

    @Deprecated
    public interface Listener {
        void onAdBreakStatusUpdated();

        void onMetadataUpdated();

        void onPreloadStatusUpdated();

        void onQueueStatusUpdated();

        void onSendingRemoteMediaRequest();

        void onStatusUpdated();
    }

    public interface MediaChannelResult extends Result {
        JSONObject getCustomData();
    }

    public interface ParseAdsInfoCallback {
        List<AdBreakInfo> parseAdBreaksFromMediaStatus(MediaStatus mediaStatus);

        boolean parseIsPlayingAdFromMediaStatus(MediaStatus mediaStatus);
    }

    public interface ProgressListener {
        void onProgressUpdated(long j, long j2);
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
                RemoteMediaClient.this.zzhl.sendMessage(this.zzfy, str, str2).setResultCallback(new zzau(this, j));
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

    private static class zzb extends BasePendingResult<MediaChannelResult> {
        zzb() {
            super((GoogleApiClient) null);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a_ */
        public final MediaChannelResult createFailedResult(Status status) {
            return new zzav(this, status);
        }
    }

    abstract class zzc extends zzcf<MediaChannelResult> {
        zzdm zzgc;
        private final boolean zzoc;

        zzc(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient) {
            this(googleApiClient, false);
        }

        zzc(GoogleApiClient googleApiClient, boolean z) {
            super(googleApiClient);
            this.zzoc = z;
            this.zzgc = new zzaw(this, RemoteMediaClient.this);
        }

        public /* synthetic */ Result createFailedResult(Status status) {
            return new zzax(this, status);
        }

        /* access modifiers changed from: protected */
        public /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
            zzcn zzcn = (zzcn) anyClient;
            if (!this.zzoc) {
                for (Listener onSendingRemoteMediaRequest : RemoteMediaClient.this.zznn) {
                    onSendingRemoteMediaRequest.onSendingRemoteMediaRequest();
                }
                for (Callback onSendingRemoteMediaRequest2 : RemoteMediaClient.this.zzno) {
                    onSendingRemoteMediaRequest2.onSendingRemoteMediaRequest();
                }
            }
            zzb(zzcn);
        }

        /* access modifiers changed from: package-private */
        public abstract void zzb(zzcn zzcn);
    }

    private static final class zzd implements MediaChannelResult {
        private final Status zzge;
        private final JSONObject zzp;

        zzd(Status status, JSONObject jSONObject) {
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

    private class zze {
        /* access modifiers changed from: private */
        public final Set<ProgressListener> zzof = new HashSet();
        /* access modifiers changed from: private */
        public final long zzog;
        private final Runnable zzoh;
        private boolean zzoi;

        public zze(long j) {
            this.zzog = j;
            this.zzoh = new zzay(this, RemoteMediaClient.this);
        }

        public final boolean hasListener() {
            return !this.zzof.isEmpty();
        }

        public final boolean isStarted() {
            return this.zzoi;
        }

        public final void start() {
            RemoteMediaClient.this.handler.removeCallbacks(this.zzoh);
            this.zzoi = true;
            RemoteMediaClient.this.handler.postDelayed(this.zzoh, this.zzog);
        }

        public final void stop() {
            RemoteMediaClient.this.handler.removeCallbacks(this.zzoh);
            this.zzoi = false;
        }

        public final void zza(ProgressListener progressListener) {
            this.zzof.add(progressListener);
        }

        public final void zzb(ProgressListener progressListener) {
            this.zzof.remove(progressListener);
        }

        public final long zzbq() {
            return this.zzog;
        }
    }

    public RemoteMediaClient(zzdh zzdh, Cast.CastApi castApi) {
        this.zzhl = castApi;
        zzdh zzdh2 = (zzdh) Preconditions.checkNotNull(zzdh);
        this.zzeu = zzdh2;
        zzdh2.zza((zzdj) new zzr(this));
        this.zzeu.zza(this.zznl);
        this.zzlt = new MediaQueue(this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:?, code lost:
        r3.setResult((com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r3.createFailedResult(new com.google.android.gms.common.api.Status(2100)));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0016, code lost:
        return r3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0006 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.cast.framework.media.RemoteMediaClient.zzc zza(com.google.android.gms.cast.framework.media.RemoteMediaClient.zzc r3) {
        /*
            r2 = this;
            com.google.android.gms.common.api.GoogleApiClient r0 = r2.zznm     // Catch:{ IllegalStateException -> 0x0006 }
            r0.execute(r3)     // Catch:{ IllegalStateException -> 0x0006 }
            return r3
        L_0x0006:
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status     // Catch:{ all -> 0x0016 }
            r1 = 2100(0x834, float:2.943E-42)
            r0.<init>(r1)     // Catch:{ all -> 0x0016 }
            com.google.android.gms.common.api.Result r0 = r3.createFailedResult(r0)     // Catch:{ all -> 0x0016 }
            com.google.android.gms.cast.framework.media.RemoteMediaClient$MediaChannelResult r0 = (com.google.android.gms.cast.framework.media.RemoteMediaClient.MediaChannelResult) r0     // Catch:{ all -> 0x0016 }
            r3.setResult(r0)     // Catch:{ all -> 0x0016 }
        L_0x0016:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.media.RemoteMediaClient.zza(com.google.android.gms.cast.framework.media.RemoteMediaClient$zzc):com.google.android.gms.cast.framework.media.RemoteMediaClient$zzc");
    }

    public static PendingResult<MediaChannelResult> zza(int i, String str) {
        zzb zzb2 = new zzb();
        zzb2.setResult(zzb2.createFailedResult(new Status(i, str)));
        return zzb2;
    }

    /* access modifiers changed from: private */
    public final void zza(Set<ProgressListener> set) {
        HashSet<ProgressListener> hashSet = new HashSet<>(set);
        if (isPlaying() || isPaused() || isBuffering()) {
            for (ProgressListener onProgressUpdated : hashSet) {
                onProgressUpdated.onProgressUpdated(getApproximateStreamPosition(), getStreamDuration());
            }
        } else if (isLoadingNextItem()) {
            MediaQueueItem loadingItem = getLoadingItem();
            if (loadingItem != null && loadingItem.getMedia() != null) {
                for (ProgressListener onProgressUpdated2 : hashSet) {
                    onProgressUpdated2.onProgressUpdated(0, loadingItem.getMedia().getStreamDuration());
                }
            }
        } else {
            for (ProgressListener onProgressUpdated3 : hashSet) {
                onProgressUpdated3.onProgressUpdated(0, 0);
            }
        }
    }

    private final boolean zzbn() {
        return this.zznm != null;
    }

    /* access modifiers changed from: private */
    public final void zzbo() {
        for (zze next : this.zznq.values()) {
            if (hasMediaSession() && !next.isStarted()) {
                next.start();
            } else if (!hasMediaSession() && next.isStarted()) {
                next.stop();
            }
            if (next.isStarted() && (isBuffering() || isPaused() || isLoadingNextItem())) {
                zza((Set<ProgressListener>) next.zzof);
            }
        }
    }

    /* access modifiers changed from: private */
    public final int zzc(int i) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        for (int i2 = 0; i2 < mediaStatus.getQueueItemCount(); i2++) {
            if (mediaStatus.getQueueItem(i2).getItemId() == i) {
                return i2;
            }
        }
        return -1;
    }

    @Deprecated
    public void addListener(Listener listener) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (listener != null) {
            this.zznn.add(listener);
        }
    }

    public boolean addProgressListener(ProgressListener progressListener, long j) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (progressListener == null || this.zznp.containsKey(progressListener)) {
            return false;
        }
        zze zze2 = this.zznq.get(Long.valueOf(j));
        if (zze2 == null) {
            zze2 = new zze(j);
            this.zznq.put(Long.valueOf(j), zze2);
        }
        zze2.zza(progressListener);
        this.zznp.put(progressListener, zze2);
        if (!hasMediaSession()) {
            return true;
        }
        zze2.start();
        return true;
    }

    public long getApproximateAdBreakClipPositionMs() {
        long approximateAdBreakClipPositionMs;
        synchronized (this.lock) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            approximateAdBreakClipPositionMs = this.zzeu.getApproximateAdBreakClipPositionMs();
        }
        return approximateAdBreakClipPositionMs;
    }

    public long getApproximateStreamPosition() {
        long approximateStreamPosition;
        synchronized (this.lock) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            approximateStreamPosition = this.zzeu.getApproximateStreamPosition();
        }
        return approximateStreamPosition;
    }

    public MediaQueueItem getCurrentItem() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        if (mediaStatus == null) {
            return null;
        }
        return mediaStatus.getQueueItemById(mediaStatus.getCurrentItemId());
    }

    public int getIdleReason() {
        int idleReason;
        synchronized (this.lock) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            MediaStatus mediaStatus = getMediaStatus();
            idleReason = mediaStatus != null ? mediaStatus.getIdleReason() : 0;
        }
        return idleReason;
    }

    public MediaQueueItem getLoadingItem() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        if (mediaStatus == null) {
            return null;
        }
        return mediaStatus.getQueueItemById(mediaStatus.getLoadingItemId());
    }

    public MediaInfo getMediaInfo() {
        MediaInfo mediaInfo;
        synchronized (this.lock) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            mediaInfo = this.zzeu.getMediaInfo();
        }
        return mediaInfo;
    }

    public MediaQueue getMediaQueue() {
        MediaQueue mediaQueue;
        synchronized (this.lock) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            mediaQueue = this.zzlt;
        }
        return mediaQueue;
    }

    public MediaStatus getMediaStatus() {
        MediaStatus mediaStatus;
        synchronized (this.lock) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            mediaStatus = this.zzeu.getMediaStatus();
        }
        return mediaStatus;
    }

    public String getNamespace() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzeu.getNamespace();
    }

    public int getPlayerState() {
        int playerState;
        synchronized (this.lock) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            MediaStatus mediaStatus = getMediaStatus();
            playerState = mediaStatus != null ? mediaStatus.getPlayerState() : 1;
        }
        return playerState;
    }

    public MediaQueueItem getPreloadedItem() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        if (mediaStatus == null) {
            return null;
        }
        return mediaStatus.getQueueItemById(mediaStatus.getPreloadedItemId());
    }

    public long getStreamDuration() {
        long streamDuration;
        synchronized (this.lock) {
            Preconditions.checkMainThread("Must be called from the main thread.");
            streamDuration = this.zzeu.getStreamDuration();
        }
        return streamDuration;
    }

    public boolean hasMediaSession() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return isBuffering() || isPlaying() || isPaused() || isLoadingNextItem();
    }

    public boolean isBuffering() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        return mediaStatus != null && mediaStatus.getPlayerState() == 4;
    }

    public boolean isLiveStream() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaInfo mediaInfo = getMediaInfo();
        return mediaInfo != null && mediaInfo.getStreamType() == 2;
    }

    public boolean isLoadingNextItem() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        return (mediaStatus == null || mediaStatus.getLoadingItemId() == 0) ? false : true;
    }

    public boolean isPaused() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        if (mediaStatus == null) {
            return false;
        }
        if (mediaStatus.getPlayerState() != 3) {
            return isLiveStream() && getIdleReason() == 2;
        }
        return true;
    }

    public boolean isPlaying() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        return mediaStatus != null && mediaStatus.getPlayerState() == 2;
    }

    public boolean isPlayingAd() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaStatus mediaStatus = getMediaStatus();
        return mediaStatus != null && mediaStatus.isPlayingAd();
    }

    @Deprecated
    public PendingResult<MediaChannelResult> load(MediaInfo mediaInfo) {
        return load(mediaInfo, new MediaLoadOptions.Builder().build());
    }

    public PendingResult<MediaChannelResult> load(MediaInfo mediaInfo, MediaLoadOptions mediaLoadOptions) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return !zzbn() ? zza(17, (String) null) : zza((zzc) new zzac(this, this.zznm, mediaInfo, mediaLoadOptions));
    }

    @Deprecated
    public PendingResult<MediaChannelResult> load(MediaInfo mediaInfo, boolean z) {
        return load(mediaInfo, new MediaLoadOptions.Builder().setAutoplay(z).build());
    }

    @Deprecated
    public PendingResult<MediaChannelResult> load(MediaInfo mediaInfo, boolean z, long j) {
        return load(mediaInfo, new MediaLoadOptions.Builder().setAutoplay(z).setPlayPosition(j).build());
    }

    @Deprecated
    public PendingResult<MediaChannelResult> load(MediaInfo mediaInfo, boolean z, long j, JSONObject jSONObject) {
        return load(mediaInfo, new MediaLoadOptions.Builder().setAutoplay(z).setPlayPosition(j).setCustomData(jSONObject).build());
    }

    @Deprecated
    public PendingResult<MediaChannelResult> load(MediaInfo mediaInfo, boolean z, long j, long[] jArr, JSONObject jSONObject) {
        return load(mediaInfo, new MediaLoadOptions.Builder().setAutoplay(z).setPlayPosition(j).setActiveTrackIds(jArr).setCustomData(jSONObject).build());
    }

    public void onMessageReceived(CastDevice castDevice, String str, String str2) {
        this.zzeu.zzn(str2);
    }

    public PendingResult<MediaChannelResult> pause() {
        return pause((JSONObject) null);
    }

    public PendingResult<MediaChannelResult> pause(JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return !zzbn() ? zza(17, (String) null) : zza((zzc) new zzan(this, this.zznm, jSONObject));
    }

    public PendingResult<MediaChannelResult> play() {
        return play((JSONObject) null);
    }

    public PendingResult<MediaChannelResult> play(JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return !zzbn() ? zza(17, (String) null) : zza((zzc) new zzap(this, this.zznm, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueAppendItem(MediaQueueItem mediaQueueItem, JSONObject jSONObject) throws IllegalArgumentException {
        return queueInsertItems(new MediaQueueItem[]{mediaQueueItem}, 0, jSONObject);
    }

    public PendingResult<MediaChannelResult> queueInsertAndPlayItem(MediaQueueItem mediaQueueItem, int i, long j, JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzbn()) {
            return zza(17, (String) null);
        }
        return zza((zzc) new zzy(this, this.zznm, mediaQueueItem, i, j, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueInsertAndPlayItem(MediaQueueItem mediaQueueItem, int i, JSONObject jSONObject) {
        return queueInsertAndPlayItem(mediaQueueItem, i, -1, jSONObject);
    }

    public PendingResult<MediaChannelResult> queueInsertItems(MediaQueueItem[] mediaQueueItemArr, int i, JSONObject jSONObject) throws IllegalArgumentException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzbn()) {
            return zza(17, (String) null);
        }
        return zza((zzc) new zzx(this, this.zznm, mediaQueueItemArr, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueJumpToItem(int i, long j, JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzbn()) {
            return zza(17, (String) null);
        }
        return zza((zzc) new zzah(this, this.zznm, i, j, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueJumpToItem(int i, JSONObject jSONObject) {
        return queueJumpToItem(i, -1, jSONObject);
    }

    public PendingResult<MediaChannelResult> queueLoad(MediaQueueItem[] mediaQueueItemArr, int i, int i2, long j, JSONObject jSONObject) throws IllegalArgumentException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzbn()) {
            return zza(17, (String) null);
        }
        return zza((zzc) new zzw(this, this.zznm, mediaQueueItemArr, i, i2, j, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueLoad(MediaQueueItem[] mediaQueueItemArr, int i, int i2, JSONObject jSONObject) throws IllegalArgumentException {
        return queueLoad(mediaQueueItemArr, i, i2, -1, jSONObject);
    }

    public PendingResult<MediaChannelResult> queueMoveItemToNewIndex(int i, int i2, JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzbn()) {
            return zza(17, (String) null);
        }
        return zza((zzc) new zzai(this, this.zznm, i, i2, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueNext(JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return !zzbn() ? zza(17, (String) null) : zza((zzc) new zzae(this, this.zznm, jSONObject));
    }

    public PendingResult<MediaChannelResult> queuePrev(JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return !zzbn() ? zza(17, (String) null) : zza((zzc) new zzad(this, this.zznm, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueRemoveItem(int i, JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return !zzbn() ? zza(17, (String) null) : zza((zzc) new zzag(this, this.zznm, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueRemoveItems(int[] iArr, JSONObject jSONObject) throws IllegalArgumentException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return !zzbn() ? zza(17, (String) null) : zza((zzc) new zzaa(this, this.zznm, iArr, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueReorderItems(int[] iArr, int i, JSONObject jSONObject) throws IllegalArgumentException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzbn()) {
            return zza(17, (String) null);
        }
        return zza((zzc) new zzab(this, this.zznm, iArr, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueSetRepeatMode(int i, JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return !zzbn() ? zza(17, (String) null) : zza((zzc) new zzaf(this, this.zznm, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> queueUpdateItems(MediaQueueItem[] mediaQueueItemArr, JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return !zzbn() ? zza(17, (String) null) : zza((zzc) new zzz(this, this.zznm, mediaQueueItemArr, jSONObject));
    }

    public void registerCallback(Callback callback) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (callback != null) {
            this.zzno.add(callback);
        }
    }

    @Deprecated
    public void removeListener(Listener listener) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (listener != null) {
            this.zznn.remove(listener);
        }
    }

    public void removeProgressListener(ProgressListener progressListener) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zze remove = this.zznp.remove(progressListener);
        if (remove != null) {
            remove.zzb(progressListener);
            if (!remove.hasListener()) {
                this.zznq.remove(Long.valueOf(remove.zzbq()));
                remove.stop();
            }
        }
    }

    public PendingResult<MediaChannelResult> requestStatus() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return !zzbn() ? zza(17, (String) null) : zza((zzc) new zzt(this, this.zznm));
    }

    public PendingResult<MediaChannelResult> seek(long j) {
        return seek(j, 0, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> seek(long j, int i) {
        return seek(j, i, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> seek(long j, int i, JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzbn()) {
            return zza(17, (String) null);
        }
        return zza((zzc) new zzaq(this, this.zznm, j, i, jSONObject));
    }

    public PendingResult<MediaChannelResult> setActiveMediaTracks(long[] jArr) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzbn()) {
            return zza(17, (String) null);
        }
        if (jArr != null) {
            return zza((zzc) new zzu(this, this.zznm, jArr));
        }
        throw new IllegalArgumentException("trackIds cannot be null");
    }

    public void setParseAdsInfoCallback(ParseAdsInfoCallback parseAdsInfoCallback) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        this.zznr = parseAdsInfoCallback;
    }

    public PendingResult<MediaChannelResult> setPlaybackRate(double d) {
        return setPlaybackRate(d, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> setPlaybackRate(double d, JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzbn()) {
            return zza(17, (String) null);
        }
        if (Double.compare(d, 2.0d) > 0 || Double.compare(d, 0.5d) < 0) {
            throw new IllegalArgumentException("playbackRate must be between PLAYBACK_RATE_MIN and PLAYBACK_RATE_MAX");
        }
        return zza((zzc) new zzat(this, this.zznm, d, jSONObject));
    }

    public PendingResult<MediaChannelResult> setStreamMute(boolean z) {
        return setStreamMute(z, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> setStreamMute(boolean z, JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return !zzbn() ? zza(17, (String) null) : zza((zzc) new zzas(this, this.zznm, z, jSONObject));
    }

    public PendingResult<MediaChannelResult> setStreamVolume(double d) throws IllegalArgumentException {
        return setStreamVolume(d, (JSONObject) null);
    }

    public PendingResult<MediaChannelResult> setStreamVolume(double d, JSONObject jSONObject) throws IllegalArgumentException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzbn()) {
            return zza(17, (String) null);
        }
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            StringBuilder sb = new StringBuilder(41);
            sb.append("Volume cannot be ");
            sb.append(d);
            throw new IllegalArgumentException(sb.toString());
        }
        return zza((zzc) new zzar(this, this.zznm, d, jSONObject));
    }

    public PendingResult<MediaChannelResult> setTextTrackStyle(TextTrackStyle textTrackStyle) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzbn()) {
            return zza(17, (String) null);
        }
        if (textTrackStyle != null) {
            return zza((zzc) new zzv(this, this.zznm, textTrackStyle));
        }
        throw new IllegalArgumentException("trackStyle cannot be null");
    }

    public PendingResult<MediaChannelResult> skipAd() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return !zzbn() ? zza(17, (String) null) : zza((zzc) new zzs(this, this.zznm));
    }

    public PendingResult<MediaChannelResult> stop() {
        return stop((JSONObject) null);
    }

    public PendingResult<MediaChannelResult> stop(JSONObject jSONObject) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return !zzbn() ? zza(17, (String) null) : zza((zzc) new zzao(this, this.zznm, jSONObject));
    }

    public void togglePlayback() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        int playerState = getPlayerState();
        if (playerState == 4 || playerState == 2) {
            pause();
        } else {
            play();
        }
    }

    public void unregisterCallback(Callback callback) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (callback != null) {
            this.zzno.remove(callback);
        }
    }

    public final PendingResult<MediaChannelResult> zza(int i, int i2, int i3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzbn()) {
            return zza(17, (String) null);
        }
        return zza((zzc) new zzal(this, this.zznm, true, i, i2, i3));
    }

    public final PendingResult<MediaChannelResult> zza(String str, List<zzbp> list) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!zzbn()) {
            return zza(17, (String) null);
        }
        return zza((zzc) new zzam(this, this.zznm, true, str, (List) null));
    }

    public final void zzb(GoogleApiClient googleApiClient) {
        GoogleApiClient googleApiClient2 = this.zznm;
        if (googleApiClient2 != googleApiClient) {
            if (googleApiClient2 != null) {
                this.zzeu.zzcm();
                try {
                    this.zzhl.removeMessageReceivedCallbacks(this.zznm, getNamespace());
                } catch (IOException unused) {
                }
                this.zznl.zza((GoogleApiClient) null);
                this.handler.removeCallbacksAndMessages((Object) null);
            }
            this.zznm = googleApiClient;
            if (googleApiClient != null) {
                this.zznl.zza(googleApiClient);
            }
        }
    }

    public final void zzbl() throws IOException {
        GoogleApiClient googleApiClient = this.zznm;
        if (googleApiClient != null) {
            this.zzhl.setMessageReceivedCallbacks(googleApiClient, getNamespace(), this);
        }
    }

    public final PendingResult<MediaChannelResult> zzbm() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return !zzbn() ? zza(17, (String) null) : zza((zzc) new zzaj(this, this.zznm, true));
    }

    public final PendingResult<MediaChannelResult> zzf(int[] iArr) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return !zzbn() ? zza(17, (String) null) : zza((zzc) new zzak(this, this.zznm, true, iArr));
    }
}

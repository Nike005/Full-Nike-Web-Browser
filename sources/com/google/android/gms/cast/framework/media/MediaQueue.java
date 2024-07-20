package com.google.android.gms.cast.framework.media;

import android.os.Handler;
import android.os.Looper;
import android.util.LruCache;
import android.util.SparseIntArray;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzcu;
import com.google.android.gms.internal.cast.zzdg;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimerTask;

public class MediaQueue {
    private final Handler handler;
    long zzec;
    /* access modifiers changed from: private */
    public final RemoteMediaClient zzhp;
    /* access modifiers changed from: private */
    public final zzdg zzlc;
    private boolean zzld;
    List<Integer> zzle;
    final SparseIntArray zzlf;
    LruCache<Integer, MediaQueueItem> zzlg;
    final List<Integer> zzlh;
    final Deque<Integer> zzli;
    private final int zzlj;
    private TimerTask zzlk;
    PendingResult<RemoteMediaClient.MediaChannelResult> zzll;
    PendingResult<RemoteMediaClient.MediaChannelResult> zzlm;
    private ResultCallback<RemoteMediaClient.MediaChannelResult> zzln;
    private ResultCallback<RemoteMediaClient.MediaChannelResult> zzlo;
    private zzd zzlp;
    private SessionManagerListener<CastSession> zzlq;
    private Set<Callback> zzlr;

    public static abstract class Callback {
        public void itemsInsertedInRange(int i, int i2) {
        }

        public void itemsReloaded() {
        }

        public void itemsRemovedAtIndexes(int[] iArr) {
        }

        public void itemsUpdatedAtIndexes(int[] iArr) {
        }

        public void mediaQueueChanged() {
        }

        public void mediaQueueWillChange() {
        }
    }

    private class zza implements ResultCallback<RemoteMediaClient.MediaChannelResult> {
        private zza() {
        }

        /* synthetic */ zza(MediaQueue mediaQueue, zzk zzk) {
            this();
        }

        public final /* synthetic */ void onResult(Result result) {
            Status status = ((RemoteMediaClient.MediaChannelResult) result).getStatus();
            int statusCode = status.getStatusCode();
            if (statusCode != 0) {
                MediaQueue.this.zzlc.mo6873w(String.format("Error fetching queue item ids, statusCode=%s, statusMessage=%s", new Object[]{Integer.valueOf(statusCode), status.getStatusMessage()}), new Object[0]);
            }
            MediaQueue.this.zzlm = null;
            if (!MediaQueue.this.zzli.isEmpty()) {
                MediaQueue.this.zzay();
            }
        }
    }

    private class zzb implements ResultCallback<RemoteMediaClient.MediaChannelResult> {
        private zzb() {
        }

        /* synthetic */ zzb(MediaQueue mediaQueue, zzk zzk) {
            this();
        }

        public final /* synthetic */ void onResult(Result result) {
            Status status = ((RemoteMediaClient.MediaChannelResult) result).getStatus();
            int statusCode = status.getStatusCode();
            if (statusCode != 0) {
                MediaQueue.this.zzlc.mo6873w(String.format("Error fetching queue items, statusCode=%s, statusMessage=%s", new Object[]{Integer.valueOf(statusCode), status.getStatusMessage()}), new Object[0]);
            }
            MediaQueue.this.zzll = null;
            if (!MediaQueue.this.zzli.isEmpty()) {
                MediaQueue.this.zzay();
            }
        }
    }

    private class zzc implements SessionManagerListener<CastSession> {
        private zzc() {
        }

        /* synthetic */ zzc(MediaQueue mediaQueue, zzk zzk) {
            this();
        }

        public final /* synthetic */ void onSessionEnded(Session session, int i) {
            MediaQueue.this.zzbc();
            MediaQueue.this.clear();
        }

        public final /* synthetic */ void onSessionEnding(Session session) {
            MediaQueue.this.zzbc();
            MediaQueue.this.clear();
        }

        public final /* bridge */ /* synthetic */ void onSessionResumeFailed(Session session, int i) {
        }

        public final /* synthetic */ void onSessionResumed(Session session, boolean z) {
            CastSession castSession = (CastSession) session;
            if (castSession.getRemoteMediaClient() != null) {
                MediaQueue.this.zza(castSession.getRemoteMediaClient());
            }
        }

        public final /* bridge */ /* synthetic */ void onSessionResuming(Session session, String str) {
        }

        public final /* bridge */ /* synthetic */ void onSessionStartFailed(Session session, int i) {
        }

        public final /* synthetic */ void onSessionStarted(Session session, String str) {
            MediaQueue.this.zza(((CastSession) session).getRemoteMediaClient());
        }

        public final /* bridge */ /* synthetic */ void onSessionStarting(Session session) {
        }

        public final /* synthetic */ void onSessionSuspended(Session session, int i) {
            MediaQueue.this.zzbc();
        }
    }

    class zzd extends RemoteMediaClient.Callback {
        zzd() {
        }

        public final void onStatusUpdated() {
            MediaQueue mediaQueue = MediaQueue.this;
            long zza = MediaQueue.zzb(mediaQueue.zzhp);
            if (zza != MediaQueue.this.zzec) {
                MediaQueue.this.zzec = zza;
                MediaQueue.this.clear();
                if (MediaQueue.this.zzec != 0) {
                    MediaQueue.this.reload();
                }
            }
        }

        public final void zza(int[] iArr) {
            List<Integer> zzg = zzcu.zzg(iArr);
            if (!MediaQueue.this.zzle.equals(zzg)) {
                MediaQueue.this.zzbf();
                MediaQueue.this.zzlg.evictAll();
                MediaQueue.this.zzlh.clear();
                MediaQueue.this.zzle = zzg;
                MediaQueue.this.zzbe();
                MediaQueue.this.zzbh();
                MediaQueue.this.zzbg();
            }
        }

        public final void zza(int[] iArr, int i) {
            int i2;
            int length = iArr.length;
            if (i == 0) {
                i2 = MediaQueue.this.zzle.size();
            } else {
                i2 = MediaQueue.this.zzlf.get(i, -1);
                if (i2 == -1) {
                    MediaQueue.this.reload();
                    return;
                }
            }
            MediaQueue.this.zzbf();
            MediaQueue.this.zzle.addAll(i2, zzcu.zzg(iArr));
            MediaQueue.this.zzbe();
            MediaQueue.this.zzb(i2, length);
            MediaQueue.this.zzbg();
        }

        public final void zzb(int[] iArr) {
            ArrayList arrayList = new ArrayList();
            int length = iArr.length;
            int i = 0;
            while (i < length) {
                int i2 = iArr[i];
                MediaQueue.this.zzlg.remove(Integer.valueOf(i2));
                int i3 = MediaQueue.this.zzlf.get(i2, -1);
                if (i3 == -1) {
                    MediaQueue.this.reload();
                    return;
                } else {
                    arrayList.add(Integer.valueOf(i3));
                    i++;
                }
            }
            Collections.sort(arrayList);
            MediaQueue.this.zzbf();
            MediaQueue.this.zzd(zzcu.zza((Collection<Integer>) arrayList));
            MediaQueue.this.zzbg();
        }

        public final void zzb(MediaQueueItem[] mediaQueueItemArr) {
            HashSet hashSet = new HashSet();
            MediaQueue.this.zzlh.clear();
            int length = mediaQueueItemArr.length;
            int i = 0;
            while (i < length) {
                MediaQueueItem mediaQueueItem = mediaQueueItemArr[i];
                int itemId = mediaQueueItem.getItemId();
                MediaQueue.this.zzlg.put(Integer.valueOf(itemId), mediaQueueItem);
                int i2 = MediaQueue.this.zzlf.get(itemId, -1);
                if (i2 == -1) {
                    MediaQueue.this.reload();
                    return;
                } else {
                    hashSet.add(Integer.valueOf(i2));
                    i++;
                }
            }
            for (Integer intValue : MediaQueue.this.zzlh) {
                int i3 = MediaQueue.this.zzlf.get(intValue.intValue(), -1);
                if (i3 != -1) {
                    hashSet.add(Integer.valueOf(i3));
                }
            }
            MediaQueue.this.zzlh.clear();
            ArrayList arrayList = new ArrayList(hashSet);
            Collections.sort(arrayList);
            MediaQueue.this.zzbf();
            MediaQueue.this.zzd(zzcu.zza((Collection<Integer>) arrayList));
            MediaQueue.this.zzbg();
        }

        public final void zzc(int[] iArr) {
            ArrayList arrayList = new ArrayList();
            for (int i : iArr) {
                MediaQueue.this.zzlg.remove(Integer.valueOf(i));
                int i2 = MediaQueue.this.zzlf.get(i, -1);
                if (i2 == -1) {
                    MediaQueue.this.reload();
                    return;
                }
                MediaQueue.this.zzlf.delete(i);
                arrayList.add(Integer.valueOf(i2));
            }
            if (!arrayList.isEmpty()) {
                Collections.sort(arrayList);
                MediaQueue.this.zzbf();
                MediaQueue.this.zzle.removeAll(zzcu.zzg(iArr));
                MediaQueue.this.zzbe();
                MediaQueue.this.zze(zzcu.zza((Collection<Integer>) arrayList));
                MediaQueue.this.zzbg();
            }
        }
    }

    MediaQueue(RemoteMediaClient remoteMediaClient) {
        this(remoteMediaClient, 20, 20);
    }

    private MediaQueue(RemoteMediaClient remoteMediaClient, int i, int i2) {
        this.zzlr = new HashSet();
        this.zzlc = new zzdg("MediaQueue");
        this.zzhp = remoteMediaClient;
        this.zzlj = Math.max(20, 1);
        CastSession currentCastSession = CastContext.getSharedInstance().getSessionManager().getCurrentCastSession();
        this.zzle = new ArrayList();
        this.zzlf = new SparseIntArray();
        this.zzlh = new ArrayList();
        this.zzli = new ArrayDeque(20);
        this.handler = new Handler(Looper.getMainLooper());
        zzh(20);
        this.zzlk = new zzk(this);
        this.zzln = new zzb(this, (zzk) null);
        this.zzlo = new zza(this, (zzk) null);
        this.zzlp = new zzd();
        this.zzlq = new zzc(this, (zzk) null);
        CastContext.getSharedInstance().getSessionManager().addSessionManagerListener(this.zzlq, CastSession.class);
        if (currentCastSession != null && currentCastSession.isConnected()) {
            zza(currentCastSession.getRemoteMediaClient());
        }
    }

    private final void zzaz() {
        this.handler.removeCallbacks(this.zzlk);
    }

    /* access modifiers changed from: private */
    public static long zzb(RemoteMediaClient remoteMediaClient) {
        MediaStatus mediaStatus = remoteMediaClient.getMediaStatus();
        if (mediaStatus == null || mediaStatus.zzk()) {
            return 0;
        }
        return mediaStatus.zzj();
    }

    /* access modifiers changed from: private */
    public final void zzb(int i, int i2) {
        for (Callback itemsInsertedInRange : this.zzlr) {
            itemsInsertedInRange.itemsInsertedInRange(i, i2);
        }
    }

    private final void zzba() {
        PendingResult<RemoteMediaClient.MediaChannelResult> pendingResult = this.zzlm;
        if (pendingResult != null) {
            pendingResult.cancel();
            this.zzlm = null;
        }
    }

    private final void zzbb() {
        PendingResult<RemoteMediaClient.MediaChannelResult> pendingResult = this.zzll;
        if (pendingResult != null) {
            pendingResult.cancel();
            this.zzll = null;
        }
    }

    /* access modifiers changed from: private */
    public final void zzbe() {
        this.zzlf.clear();
        for (int i = 0; i < this.zzle.size(); i++) {
            this.zzlf.put(this.zzle.get(i).intValue(), i);
        }
    }

    /* access modifiers changed from: private */
    public final void zzbf() {
        for (Callback mediaQueueWillChange : this.zzlr) {
            mediaQueueWillChange.mediaQueueWillChange();
        }
    }

    /* access modifiers changed from: private */
    public final void zzbg() {
        for (Callback mediaQueueChanged : this.zzlr) {
            mediaQueueChanged.mediaQueueChanged();
        }
    }

    /* access modifiers changed from: private */
    public final void zzbh() {
        for (Callback itemsReloaded : this.zzlr) {
            itemsReloaded.itemsReloaded();
        }
    }

    /* access modifiers changed from: private */
    public final void zzd(int[] iArr) {
        for (Callback itemsUpdatedAtIndexes : this.zzlr) {
            itemsUpdatedAtIndexes.itemsUpdatedAtIndexes(iArr);
        }
    }

    /* access modifiers changed from: private */
    public final void zze(int[] iArr) {
        for (Callback itemsRemovedAtIndexes : this.zzlr) {
            itemsRemovedAtIndexes.itemsRemovedAtIndexes(iArr);
        }
    }

    private final void zzh(int i) {
        this.zzlg = new zzl(this, i);
    }

    public final void clear() {
        zzbf();
        this.zzle.clear();
        this.zzlf.clear();
        this.zzlg.evictAll();
        this.zzlh.clear();
        zzaz();
        this.zzli.clear();
        zzba();
        zzbb();
        zzbh();
        zzbg();
    }

    public PendingResult<RemoteMediaClient.MediaChannelResult> fetchMoreItemsRelativeToIndex(int i, int i2, int i3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!this.zzld || this.zzec == 0) {
            return RemoteMediaClient.zza(2100, "No active media session");
        }
        int itemIdAtIndex = itemIdAtIndex(i);
        return itemIdAtIndex == 0 ? RemoteMediaClient.zza(2001, "index out of bound") : this.zzhp.zza(itemIdAtIndex, i2, i3);
    }

    public MediaQueueItem getItemAtIndex(int i) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return getItemAtIndex(i, true);
    }

    public MediaQueueItem getItemAtIndex(int i, boolean z) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (i < 0 || i >= this.zzle.size()) {
            return null;
        }
        int intValue = this.zzle.get(i).intValue();
        MediaQueueItem mediaQueueItem = this.zzlg.get(Integer.valueOf(intValue));
        if (mediaQueueItem == null && z && !this.zzli.contains(Integer.valueOf(intValue))) {
            while (this.zzli.size() >= this.zzlj) {
                this.zzli.removeFirst();
            }
            this.zzli.add(Integer.valueOf(intValue));
            zzay();
        }
        return mediaQueueItem;
    }

    public int getItemCount() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzle.size();
    }

    public int[] getItemIds() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return zzcu.zza((Collection<Integer>) this.zzle);
    }

    public int indexOfItemWithId(int i) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzlf.get(i, -1);
    }

    public int itemIdAtIndex(int i) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (i < 0 || i >= this.zzle.size()) {
            return 0;
        }
        return this.zzle.get(i).intValue();
    }

    public void registerCallback(Callback callback) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        this.zzlr.add(callback);
    }

    public final void reload() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (this.zzld && this.zzec != 0 && this.zzlm == null) {
            zzba();
            zzbb();
            PendingResult<RemoteMediaClient.MediaChannelResult> zzbm = this.zzhp.zzbm();
            this.zzlm = zzbm;
            zzbm.setResultCallback(this.zzlo);
        }
    }

    public void setCacheCapacity(int i) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        LruCache<Integer, MediaQueueItem> lruCache = this.zzlg;
        ArrayList arrayList = new ArrayList();
        zzh(i);
        int size = lruCache.size();
        for (Map.Entry next : lruCache.snapshot().entrySet()) {
            if (size > i) {
                int i2 = this.zzlf.get(((Integer) next.getKey()).intValue(), -1);
                if (i2 != -1) {
                    arrayList.add(Integer.valueOf(i2));
                }
            } else {
                this.zzlg.put((Integer) next.getKey(), (MediaQueueItem) next.getValue());
            }
            size--;
        }
        if (!arrayList.isEmpty()) {
            Collections.sort(arrayList);
            zzbf();
            zzd(zzcu.zza((Collection<Integer>) arrayList));
            zzbg();
        }
    }

    public void unregisterCallback(Callback callback) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        this.zzlr.remove(callback);
    }

    /* access modifiers changed from: package-private */
    public final void zza(RemoteMediaClient remoteMediaClient) {
        if (remoteMediaClient != null && this.zzhp == remoteMediaClient) {
            this.zzld = true;
            remoteMediaClient.registerCallback(this.zzlp);
            long zzb2 = zzb(remoteMediaClient);
            this.zzec = zzb2;
            if (zzb2 != 0) {
                reload();
            }
        }
    }

    public final void zzay() {
        zzaz();
        this.handler.postDelayed(this.zzlk, 500);
    }

    /* access modifiers changed from: package-private */
    public final void zzbc() {
        this.zzhp.unregisterCallback(this.zzlp);
        this.zzld = false;
    }

    /* access modifiers changed from: package-private */
    public final void zzbd() {
        if (!this.zzli.isEmpty() && this.zzll == null && this.zzld && this.zzec != 0) {
            PendingResult<RemoteMediaClient.MediaChannelResult> zzf = this.zzhp.zzf(zzcu.zza((Collection<Integer>) this.zzli));
            this.zzll = zzf;
            zzf.setResultCallback(this.zzln);
            this.zzli.clear();
        }
    }
}

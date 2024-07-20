package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.AdBreakInfo;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.internal.cast.zzdj;
import java.util.List;

final class zzr implements zzdj {
    private final /* synthetic */ RemoteMediaClient zzns;

    zzr(RemoteMediaClient remoteMediaClient) {
        this.zzns = remoteMediaClient;
    }

    private final void zzbp() {
        MediaStatus mediaStatus;
        if (this.zzns.zznr != null && (mediaStatus = this.zzns.getMediaStatus()) != null) {
            mediaStatus.zzf(this.zzns.zznr.parseIsPlayingAdFromMediaStatus(mediaStatus));
            List<AdBreakInfo> parseAdBreaksFromMediaStatus = this.zzns.zznr.parseAdBreaksFromMediaStatus(mediaStatus);
            MediaInfo mediaInfo = this.zzns.getMediaInfo();
            if (mediaInfo != null) {
                mediaInfo.zzb(parseAdBreaksFromMediaStatus);
            }
        }
    }

    public final void onAdBreakStatusUpdated() {
        for (RemoteMediaClient.Listener onAdBreakStatusUpdated : this.zzns.zznn) {
            onAdBreakStatusUpdated.onAdBreakStatusUpdated();
        }
        for (RemoteMediaClient.Callback onAdBreakStatusUpdated2 : this.zzns.zzno) {
            onAdBreakStatusUpdated2.onAdBreakStatusUpdated();
        }
    }

    public final void onMetadataUpdated() {
        zzbp();
        for (RemoteMediaClient.Listener onMetadataUpdated : this.zzns.zznn) {
            onMetadataUpdated.onMetadataUpdated();
        }
        for (RemoteMediaClient.Callback onMetadataUpdated2 : this.zzns.zzno) {
            onMetadataUpdated2.onMetadataUpdated();
        }
    }

    public final void onPreloadStatusUpdated() {
        for (RemoteMediaClient.Listener onPreloadStatusUpdated : this.zzns.zznn) {
            onPreloadStatusUpdated.onPreloadStatusUpdated();
        }
        for (RemoteMediaClient.Callback onPreloadStatusUpdated2 : this.zzns.zzno) {
            onPreloadStatusUpdated2.onPreloadStatusUpdated();
        }
    }

    public final void onQueueStatusUpdated() {
        for (RemoteMediaClient.Listener onQueueStatusUpdated : this.zzns.zznn) {
            onQueueStatusUpdated.onQueueStatusUpdated();
        }
        for (RemoteMediaClient.Callback onQueueStatusUpdated2 : this.zzns.zzno) {
            onQueueStatusUpdated2.onQueueStatusUpdated();
        }
    }

    public final void onStatusUpdated() {
        zzbp();
        this.zzns.zzbo();
        for (RemoteMediaClient.Listener onStatusUpdated : this.zzns.zznn) {
            onStatusUpdated.onStatusUpdated();
        }
        for (RemoteMediaClient.Callback onStatusUpdated2 : this.zzns.zzno) {
            onStatusUpdated2.onStatusUpdated();
        }
    }

    public final void zza(int[] iArr) {
        for (RemoteMediaClient.Callback zza : this.zzns.zzno) {
            zza.zza(iArr);
        }
    }

    public final void zza(int[] iArr, int i) {
        for (RemoteMediaClient.Callback zza : this.zzns.zzno) {
            zza.zza(iArr, i);
        }
    }

    public final void zzb(int[] iArr) {
        for (RemoteMediaClient.Callback zzb : this.zzns.zzno) {
            zzb.zzb(iArr);
        }
    }

    public final void zzb(MediaQueueItem[] mediaQueueItemArr) {
        for (RemoteMediaClient.Callback zzb : this.zzns.zzno) {
            zzb.zzb(mediaQueueItemArr);
        }
    }

    public final void zzc(int[] iArr) {
        for (RemoteMediaClient.Callback zzc : this.zzns.zzno) {
            zzc.zzc(iArr);
        }
    }
}

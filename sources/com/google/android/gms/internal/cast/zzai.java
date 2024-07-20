package com.google.android.gms.internal.cast;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.net.Uri;
import android.support.p064v4.media.MediaMetadataCompat;
import android.support.p064v4.media.session.MediaSessionCompat;
import android.support.p064v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.C0069R;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.ReconnectionService;
import com.google.android.gms.cast.framework.media.MediaNotificationService;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.util.PlatformVersion;

public final class zzai implements RemoteMediaClient.Listener {
    private CastDevice zzai;
    private final Context zzgs;
    /* access modifiers changed from: private */
    public RemoteMediaClient zzhp;
    private final zzw zzip;
    private boolean zzld;
    private final CastOptions zzpg;
    private final ComponentName zzph;
    private final zzx zzpi;
    private final zzx zzpj;
    private MediaSessionCompat zzpk;
    private MediaSessionCompat.Callback zzpl;

    public zzai(Context context, CastOptions castOptions, zzw zzw) {
        this.zzgs = context;
        this.zzpg = castOptions;
        this.zzip = zzw;
        this.zzph = (castOptions.getCastMediaOptions() == null || TextUtils.isEmpty(this.zzpg.getCastMediaOptions().getExpandedControllerActivityClassName())) ? null : new ComponentName(this.zzgs, this.zzpg.getCastMediaOptions().getExpandedControllerActivityClassName());
        zzx zzx = new zzx(this.zzgs);
        this.zzpi = zzx;
        zzx.zza((zzy) new zzaj(this));
        zzx zzx2 = new zzx(this.zzgs);
        this.zzpj = zzx2;
        zzx2.zza((zzy) new zzak(this));
    }

    private final Uri zza(MediaMetadata mediaMetadata, int i) {
        WebImage onPickImage = this.zzpg.getCastMediaOptions().getImagePicker() != null ? this.zzpg.getCastMediaOptions().getImagePicker().onPickImage(mediaMetadata, i) : mediaMetadata.hasImages() ? mediaMetadata.getImages().get(0) : null;
        if (onPickImage == null) {
            return null;
        }
        return onPickImage.getUrl();
    }

    private final void zza(int i, MediaInfo mediaInfo) {
        PendingIntent pendingIntent;
        if (i == 0) {
            this.zzpk.setPlaybackState(new PlaybackStateCompat.Builder().setState(0, 0, 1.0f).build());
            this.zzpk.setMetadata(new MediaMetadataCompat.Builder().build());
            return;
        }
        this.zzpk.setPlaybackState(new PlaybackStateCompat.Builder().setState(i, 0, 1.0f).setActions(mediaInfo.getStreamType() == 2 ? 5 : 512).build());
        MediaSessionCompat mediaSessionCompat = this.zzpk;
        if (this.zzph == null) {
            pendingIntent = null;
        } else {
            Intent intent = new Intent();
            intent.setComponent(this.zzph);
            pendingIntent = PendingIntent.getActivity(this.zzgs, 0, intent, 134217728);
        }
        mediaSessionCompat.setSessionActivity(pendingIntent);
        MediaMetadata metadata = mediaInfo.getMetadata();
        this.zzpk.setMetadata(zzbt().putString("android.media.metadata.TITLE", metadata.getString(MediaMetadata.KEY_TITLE)).putString("android.media.metadata.DISPLAY_TITLE", metadata.getString(MediaMetadata.KEY_TITLE)).putString("android.media.metadata.DISPLAY_SUBTITLE", metadata.getString(MediaMetadata.KEY_SUBTITLE)).putLong("android.media.metadata.DURATION", mediaInfo.getStreamDuration()).build());
        Uri zza = zza(metadata, 0);
        if (zza != null) {
            this.zzpi.zza(zza);
        } else {
            zza((Bitmap) null, 0);
        }
        Uri zza2 = zza(metadata, 3);
        if (zza2 != null) {
            this.zzpj.zza(zza2);
        } else {
            zza((Bitmap) null, 3);
        }
    }

    /* access modifiers changed from: private */
    public final void zza(Bitmap bitmap, int i) {
        if (i == 0) {
            if (bitmap == null) {
                bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
                bitmap.eraseColor(0);
            }
            this.zzpk.setMetadata(zzbt().putBitmap("android.media.metadata.DISPLAY_ICON", bitmap).build());
        } else if (i == 3) {
            this.zzpk.setMetadata(zzbt().putBitmap("android.media.metadata.ALBUM_ART", bitmap).build());
        }
    }

    private final MediaMetadataCompat.Builder zzbt() {
        MediaMetadataCompat metadata = this.zzpk.getController().getMetadata();
        return metadata == null ? new MediaMetadataCompat.Builder() : new MediaMetadataCompat.Builder(metadata);
    }

    private final void zzbu() {
        if (this.zzpg.getCastMediaOptions().getNotificationOptions() != null) {
            Intent intent = new Intent(this.zzgs, MediaNotificationService.class);
            intent.setPackage(this.zzgs.getPackageName());
            intent.setAction(MediaNotificationService.ACTION_UPDATE_NOTIFICATION);
            this.zzgs.stopService(intent);
        }
    }

    private final void zzbv() {
        if (this.zzpg.getEnableReconnectionService()) {
            Intent intent = new Intent(this.zzgs, ReconnectionService.class);
            intent.setPackage(this.zzgs.getPackageName());
            this.zzgs.stopService(intent);
        }
    }

    public final void onAdBreakStatusUpdated() {
        zzg(false);
    }

    public final void onMetadataUpdated() {
        zzg(false);
    }

    public final void onPreloadStatusUpdated() {
        zzg(false);
    }

    public final void onQueueStatusUpdated() {
        zzg(false);
    }

    public final void onSendingRemoteMediaRequest() {
    }

    public final void onStatusUpdated() {
        zzg(false);
    }

    public final void zza(RemoteMediaClient remoteMediaClient, CastDevice castDevice) {
        CastOptions castOptions;
        if (!this.zzld && (castOptions = this.zzpg) != null && castOptions.getCastMediaOptions() != null && remoteMediaClient != null && castDevice != null) {
            this.zzhp = remoteMediaClient;
            remoteMediaClient.addListener(this);
            this.zzai = castDevice;
            if (!PlatformVersion.isAtLeastLollipop()) {
                ((AudioManager) this.zzgs.getSystemService("audio")).requestAudioFocus((AudioManager.OnAudioFocusChangeListener) null, 3, 3);
            }
            ComponentName componentName = new ComponentName(this.zzgs, this.zzpg.getCastMediaOptions().getMediaIntentReceiverClassName());
            Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
            intent.setComponent(componentName);
            MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(this.zzgs, "CastMediaSession", componentName, PendingIntent.getBroadcast(this.zzgs, 0, intent, 0));
            this.zzpk = mediaSessionCompat;
            mediaSessionCompat.setFlags(3);
            zza(0, (MediaInfo) null);
            CastDevice castDevice2 = this.zzai;
            if (castDevice2 != null && !TextUtils.isEmpty(castDevice2.getFriendlyName())) {
                this.zzpk.setMetadata(new MediaMetadataCompat.Builder().putString("android.media.metadata.ALBUM_ARTIST", this.zzgs.getResources().getString(C0069R.string.cast_casting_to_device, new Object[]{this.zzai.getFriendlyName()})).build());
            }
            zzal zzal = new zzal(this);
            this.zzpl = zzal;
            this.zzpk.setCallback(zzal);
            this.zzpk.setActive(true);
            this.zzip.setMediaSessionCompat(this.zzpk);
            this.zzld = true;
            zzg(false);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00fd, code lost:
        if (r1.intValue() < (r12.getQueueItemCount() - 1)) goto L_0x0103;
     */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0071  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0078  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zzg(boolean r12) {
        /*
            r11 = this;
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r11.zzhp
            if (r0 != 0) goto L_0x0005
            return
        L_0x0005:
            com.google.android.gms.cast.MediaStatus r0 = r0.getMediaStatus()
            r1 = 0
            if (r0 != 0) goto L_0x000e
            r2 = r1
            goto L_0x0012
        L_0x000e:
            com.google.android.gms.cast.MediaInfo r2 = r0.getMediaInfo()
        L_0x0012:
            if (r2 != 0) goto L_0x0016
            r3 = r1
            goto L_0x001a
        L_0x0016:
            com.google.android.gms.cast.MediaMetadata r3 = r2.getMetadata()
        L_0x001a:
            r4 = 6
            r5 = 3
            r6 = 2
            r7 = 0
            r8 = 1
            if (r0 == 0) goto L_0x006a
            if (r2 == 0) goto L_0x006a
            if (r3 != 0) goto L_0x0026
            goto L_0x006a
        L_0x0026:
            com.google.android.gms.cast.framework.media.RemoteMediaClient r3 = r11.zzhp
            int r3 = r3.getPlayerState()
            if (r3 == r8) goto L_0x003e
            if (r3 == r6) goto L_0x003b
            if (r3 == r5) goto L_0x0038
            r0 = 4
            if (r3 == r0) goto L_0x0036
            goto L_0x006a
        L_0x0036:
            r3 = 0
            goto L_0x006c
        L_0x0038:
            r3 = 0
        L_0x0039:
            r4 = 2
            goto L_0x006c
        L_0x003b:
            r3 = 0
            r4 = 3
            goto L_0x006c
        L_0x003e:
            int r3 = r0.getIdleReason()
            com.google.android.gms.cast.framework.media.RemoteMediaClient r9 = r11.zzhp
            boolean r9 = r9.isLiveStream()
            if (r9 == 0) goto L_0x004e
            if (r3 != r6) goto L_0x004e
            r9 = 1
            goto L_0x004f
        L_0x004e:
            r9 = 0
        L_0x004f:
            int r10 = r0.getLoadingItemId()
            if (r10 == 0) goto L_0x005b
            if (r3 == r8) goto L_0x0059
            if (r3 != r5) goto L_0x005b
        L_0x0059:
            r3 = 1
            goto L_0x005c
        L_0x005b:
            r3 = 0
        L_0x005c:
            if (r9 == 0) goto L_0x005f
            goto L_0x0039
        L_0x005f:
            com.google.android.gms.cast.MediaQueueItem r0 = r0.getQueueItemById(r10)
            if (r0 == 0) goto L_0x006b
            com.google.android.gms.cast.MediaInfo r2 = r0.getMedia()
            goto L_0x006c
        L_0x006a:
            r3 = 0
        L_0x006b:
            r4 = 0
        L_0x006c:
            r11.zza((int) r4, (com.google.android.gms.cast.MediaInfo) r2)
            if (r4 != 0) goto L_0x0078
            r11.zzbu()
            r11.zzbv()
            return
        L_0x0078:
            com.google.android.gms.cast.framework.CastOptions r0 = r11.zzpg
            com.google.android.gms.cast.framework.media.CastMediaOptions r0 = r0.getCastMediaOptions()
            com.google.android.gms.cast.framework.media.NotificationOptions r0 = r0.getNotificationOptions()
            if (r0 == 0) goto L_0x0113
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r11.zzhp
            if (r0 == 0) goto L_0x0113
            android.content.Intent r0 = new android.content.Intent
            android.content.Context r2 = r11.zzgs
            java.lang.Class<com.google.android.gms.cast.framework.media.MediaNotificationService> r4 = com.google.android.gms.cast.framework.media.MediaNotificationService.class
            r0.<init>(r2, r4)
            java.lang.String r2 = "extra_media_notification_force_update"
            r0.putExtra(r2, r12)
            android.content.Context r12 = r11.zzgs
            java.lang.String r12 = r12.getPackageName()
            r0.setPackage(r12)
            java.lang.String r12 = "com.google.android.gms.cast.framework.action.UPDATE_NOTIFICATION"
            r0.setAction(r12)
            com.google.android.gms.cast.framework.media.RemoteMediaClient r12 = r11.zzhp
            com.google.android.gms.cast.MediaInfo r12 = r12.getMediaInfo()
            java.lang.String r2 = "extra_media_info"
            r0.putExtra(r2, r12)
            com.google.android.gms.cast.framework.media.RemoteMediaClient r12 = r11.zzhp
            int r12 = r12.getPlayerState()
            java.lang.String r2 = "extra_remote_media_client_player_state"
            r0.putExtra(r2, r12)
            com.google.android.gms.cast.CastDevice r12 = r11.zzai
            java.lang.String r2 = "extra_cast_device"
            r0.putExtra(r2, r12)
            android.support.v4.media.session.MediaSessionCompat r12 = r11.zzpk
            if (r12 != 0) goto L_0x00c6
            goto L_0x00ca
        L_0x00c6:
            android.support.v4.media.session.MediaSessionCompat$Token r1 = r12.getSessionToken()
        L_0x00ca:
            java.lang.String r12 = "extra_media_session_token"
            r0.putExtra(r12, r1)
            com.google.android.gms.cast.framework.media.RemoteMediaClient r12 = r11.zzhp
            com.google.android.gms.cast.MediaStatus r12 = r12.getMediaStatus()
            if (r12 == 0) goto L_0x010e
            int r1 = r12.getQueueRepeatMode()
            if (r1 == r8) goto L_0x0102
            if (r1 == r6) goto L_0x0102
            if (r1 == r5) goto L_0x0102
            int r1 = r12.getCurrentItemId()
            java.lang.Integer r1 = r12.getIndexById(r1)
            if (r1 == 0) goto L_0x0100
            int r2 = r1.intValue()
            if (r2 <= 0) goto L_0x00f3
            r2 = 1
            goto L_0x00f4
        L_0x00f3:
            r2 = 0
        L_0x00f4:
            int r1 = r1.intValue()
            int r12 = r12.getQueueItemCount()
            int r12 = r12 - r8
            if (r1 >= r12) goto L_0x0104
            goto L_0x0103
        L_0x0100:
            r2 = 0
            goto L_0x0104
        L_0x0102:
            r2 = 1
        L_0x0103:
            r7 = 1
        L_0x0104:
            java.lang.String r12 = "extra_can_skip_next"
            r0.putExtra(r12, r7)
            java.lang.String r12 = "extra_can_skip_prev"
            r0.putExtra(r12, r2)
        L_0x010e:
            android.content.Context r12 = r11.zzgs
            r12.startService(r0)
        L_0x0113:
            if (r3 != 0) goto L_0x0134
            com.google.android.gms.cast.framework.CastOptions r12 = r11.zzpg
            boolean r12 = r12.getEnableReconnectionService()
            if (r12 == 0) goto L_0x0134
            android.content.Intent r12 = new android.content.Intent
            android.content.Context r0 = r11.zzgs
            java.lang.Class<com.google.android.gms.cast.framework.ReconnectionService> r1 = com.google.android.gms.cast.framework.ReconnectionService.class
            r12.<init>(r0, r1)
            android.content.Context r0 = r11.zzgs
            java.lang.String r0 = r0.getPackageName()
            r12.setPackage(r0)
            android.content.Context r0 = r11.zzgs
            r0.startService(r12)
        L_0x0134:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.zzai.zzg(boolean):void");
    }

    public final void zzi(int i) {
        if (this.zzld) {
            this.zzld = false;
            RemoteMediaClient remoteMediaClient = this.zzhp;
            if (remoteMediaClient != null) {
                remoteMediaClient.removeListener(this);
            }
            if (!PlatformVersion.isAtLeastLollipop()) {
                ((AudioManager) this.zzgs.getSystemService("audio")).abandonAudioFocus((AudioManager.OnAudioFocusChangeListener) null);
            }
            this.zzip.setMediaSessionCompat((MediaSessionCompat) null);
            zzx zzx = this.zzpi;
            if (zzx != null) {
                zzx.clear();
            }
            zzx zzx2 = this.zzpj;
            if (zzx2 != null) {
                zzx2.clear();
            }
            MediaSessionCompat mediaSessionCompat = this.zzpk;
            if (mediaSessionCompat != null) {
                mediaSessionCompat.setSessionActivity((PendingIntent) null);
                this.zzpk.setCallback((MediaSessionCompat.Callback) null);
                this.zzpk.setMetadata(new MediaMetadataCompat.Builder().build());
                zza(0, (MediaInfo) null);
                this.zzpk.setActive(false);
                this.zzpk.release();
                this.zzpk = null;
            }
            this.zzhp = null;
            this.zzai = null;
            this.zzpl = null;
            zzbu();
            if (i == 0) {
                zzbv();
            }
        }
    }
}

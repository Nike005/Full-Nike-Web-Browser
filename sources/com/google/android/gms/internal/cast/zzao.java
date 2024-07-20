package com.google.android.gms.internal.cast;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzao extends UIController {
    private final ImagePicker zzkw;
    private final ImageHints zzow;
    /* access modifiers changed from: private */
    public final ImageView zzpw;
    private final Bitmap zzpx;
    private final zzx zzpy;

    public zzao(ImageView imageView, Context context, ImageHints imageHints, int i) {
        CastMediaOptions castMediaOptions;
        this.zzpw = imageView;
        this.zzow = imageHints;
        this.zzpx = BitmapFactory.decodeResource(context.getResources(), i);
        CastContext zzb = CastContext.zzb(context);
        ImagePicker imagePicker = null;
        if (!(zzb == null || (castMediaOptions = zzb.getCastOptions().getCastMediaOptions()) == null)) {
            imagePicker = castMediaOptions.getImagePicker();
        }
        this.zzkw = imagePicker;
        this.zzpy = new zzx(context.getApplicationContext());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0020, code lost:
        r1 = r1.onPickImage(r0.getMetadata(), r4.zzow);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzby() {
        /*
            r4 = this;
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r4.getRemoteMediaClient()
            if (r0 == 0) goto L_0x004c
            boolean r1 = r0.hasMediaSession()
            if (r1 != 0) goto L_0x000d
            goto L_0x004c
        L_0x000d:
            com.google.android.gms.cast.MediaQueueItem r0 = r0.getPreloadedItem()
            r1 = 0
            if (r0 != 0) goto L_0x0015
            goto L_0x003c
        L_0x0015:
            com.google.android.gms.cast.MediaInfo r0 = r0.getMedia()
            if (r0 != 0) goto L_0x001c
            goto L_0x003c
        L_0x001c:
            com.google.android.gms.cast.framework.media.ImagePicker r1 = r4.zzkw
            if (r1 == 0) goto L_0x0037
            com.google.android.gms.cast.MediaMetadata r2 = r0.getMetadata()
            com.google.android.gms.cast.framework.media.ImageHints r3 = r4.zzow
            com.google.android.gms.common.images.WebImage r1 = r1.onPickImage((com.google.android.gms.cast.MediaMetadata) r2, (com.google.android.gms.cast.framework.media.ImageHints) r3)
            if (r1 == 0) goto L_0x0037
            android.net.Uri r2 = r1.getUrl()
            if (r2 == 0) goto L_0x0037
            android.net.Uri r1 = r1.getUrl()
            goto L_0x003c
        L_0x0037:
            r1 = 0
            android.net.Uri r1 = com.google.android.gms.cast.framework.media.MediaUtils.getImageUri(r0, r1)
        L_0x003c:
            if (r1 != 0) goto L_0x0046
            android.widget.ImageView r0 = r4.zzpw
            android.graphics.Bitmap r1 = r4.zzpx
            r0.setImageBitmap(r1)
            return
        L_0x0046:
            com.google.android.gms.internal.cast.zzx r0 = r4.zzpy
            r0.zza((android.net.Uri) r1)
            return
        L_0x004c:
            android.widget.ImageView r0 = r4.zzpw
            android.graphics.Bitmap r1 = r4.zzpx
            r0.setImageBitmap(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.zzao.zzby():void");
    }

    public final void onMediaStatusUpdated() {
        zzby();
    }

    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        this.zzpy.zza((zzy) new zzap(this));
        this.zzpw.setImageBitmap(this.zzpx);
        zzby();
    }

    public final void onSessionEnded() {
        this.zzpy.clear();
        this.zzpw.setImageBitmap(this.zzpx);
        super.onSessionEnded();
    }
}

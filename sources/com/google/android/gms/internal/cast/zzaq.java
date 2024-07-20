package com.google.android.gms.internal.cast;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

public final class zzaq extends UIController {
    private final ImagePicker zzkw;
    private final ImageHints zzow;
    /* access modifiers changed from: private */
    public final ImageView zzpw;
    private final zzx zzpy;
    private final Bitmap zzqa;
    /* access modifiers changed from: private */
    public final View zzqb;

    public zzaq(ImageView imageView, Context context, ImageHints imageHints, int i, View view) {
        CastMediaOptions castMediaOptions;
        this.zzpw = imageView;
        this.zzow = imageHints;
        ImagePicker imagePicker = null;
        this.zzqa = i != 0 ? BitmapFactory.decodeResource(context.getResources(), i) : null;
        this.zzqb = view;
        CastContext zzb = CastContext.zzb(context);
        if (!(zzb == null || (castMediaOptions = zzb.getCastOptions().getCastMediaOptions()) == null)) {
            imagePicker = castMediaOptions.getImagePicker();
        }
        this.zzkw = imagePicker;
        this.zzpy = new zzx(context.getApplicationContext());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0019, code lost:
        r1 = r1.onPickImage(r0.getMetadata(), r4.zzow);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void zzby() {
        /*
            r4 = this;
            com.google.android.gms.cast.framework.media.RemoteMediaClient r0 = r4.getRemoteMediaClient()
            if (r0 == 0) goto L_0x0041
            boolean r1 = r0.hasMediaSession()
            if (r1 != 0) goto L_0x000d
            goto L_0x0041
        L_0x000d:
            com.google.android.gms.cast.MediaInfo r0 = r0.getMediaInfo()
            if (r0 != 0) goto L_0x0015
            r0 = 0
            goto L_0x0035
        L_0x0015:
            com.google.android.gms.cast.framework.media.ImagePicker r1 = r4.zzkw
            if (r1 == 0) goto L_0x0030
            com.google.android.gms.cast.MediaMetadata r2 = r0.getMetadata()
            com.google.android.gms.cast.framework.media.ImageHints r3 = r4.zzow
            com.google.android.gms.common.images.WebImage r1 = r1.onPickImage((com.google.android.gms.cast.MediaMetadata) r2, (com.google.android.gms.cast.framework.media.ImageHints) r3)
            if (r1 == 0) goto L_0x0030
            android.net.Uri r2 = r1.getUrl()
            if (r2 == 0) goto L_0x0030
            android.net.Uri r0 = r1.getUrl()
            goto L_0x0035
        L_0x0030:
            r1 = 0
            android.net.Uri r0 = com.google.android.gms.cast.framework.media.MediaUtils.getImageUri(r0, r1)
        L_0x0035:
            if (r0 != 0) goto L_0x003b
            r4.zzbz()
            return
        L_0x003b:
            com.google.android.gms.internal.cast.zzx r1 = r4.zzpy
            r1.zza((android.net.Uri) r0)
            return
        L_0x0041:
            r4.zzbz()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.zzaq.zzby():void");
    }

    private final void zzbz() {
        View view = this.zzqb;
        if (view != null) {
            view.setVisibility(0);
            this.zzpw.setVisibility(4);
        }
        Bitmap bitmap = this.zzqa;
        if (bitmap != null) {
            this.zzpw.setImageBitmap(bitmap);
        }
    }

    public final void onMediaStatusUpdated() {
        zzby();
    }

    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        this.zzpy.zza((zzy) new zzar(this));
        zzbz();
        zzby();
    }

    public final void onSessionEnded() {
        this.zzpy.clear();
        zzbz();
        super.onSessionEnded();
    }
}

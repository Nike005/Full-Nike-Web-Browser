package com.google.android.gms.internal.cast;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import com.google.android.gms.cast.framework.media.ImageHints;

public final class zzx implements zzab {
    private final Context zzgs;
    private final ImageHints zzow;
    private Uri zzox;
    private zzz zzoy;
    private zzac zzoz;
    private Bitmap zzpa;
    private boolean zzpb;
    private zzy zzpc;

    public zzx(Context context) {
        this(context, new ImageHints(-1, 0, 0));
    }

    public zzx(Context context, ImageHints imageHints) {
        this.zzgs = context;
        this.zzow = imageHints;
        this.zzoz = new zzac();
        reset();
    }

    private final void reset() {
        zzz zzz = this.zzoy;
        if (zzz != null) {
            zzz.cancel(true);
            this.zzoy = null;
        }
        this.zzox = null;
        this.zzpa = null;
        this.zzpb = false;
    }

    public final void clear() {
        reset();
        this.zzpc = null;
    }

    public final void onPostExecute(Bitmap bitmap) {
        this.zzpa = bitmap;
        this.zzpb = true;
        zzy zzy = this.zzpc;
        if (zzy != null) {
            zzy.zza(bitmap);
        }
        this.zzoy = null;
    }

    public final void zza(zzy zzy) {
        this.zzpc = zzy;
    }

    public final boolean zza(Uri uri) {
        if (uri == null) {
            reset();
            return true;
        } else if (uri.equals(this.zzox)) {
            return this.zzpb;
        } else {
            reset();
            this.zzox = uri;
            if (this.zzow.getWidthInPixels() == 0 || this.zzow.getHeightInPixels() == 0) {
                this.zzoy = new zzz(this.zzgs, this);
            } else {
                this.zzoy = new zzz(this.zzgs, this.zzow.getWidthInPixels(), this.zzow.getHeightInPixels(), false, this);
            }
            this.zzoy.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Uri[]{this.zzox});
            return false;
        }
    }
}

package com.google.android.gms.internal.cast;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.support.p064v4.media.session.PlaybackStateCompat;

public final class zzz extends AsyncTask<Uri, Long, Bitmap> {
    private static final zzdg zzbd = new zzdg("FetchBitmapTask");
    private final zzae zzpd;
    private final zzab zzpe;

    private zzz(Context context, int i, int i2, boolean z, long j, int i3, int i4, int i5, zzab zzab) {
        this.zzpd = zze.zza(context.getApplicationContext(), this, new zzad(this), i, i2, z, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE, 5, 333, 10000);
        this.zzpe = zzab;
    }

    public zzz(Context context, int i, int i2, boolean z, zzab zzab) {
        this(context, i, i2, false, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE, 5, 333, 10000, zzab);
    }

    public zzz(Context context, zzab zzab) {
        this(context, 0, 0, false, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE, 5, 333, 10000, zzab);
    }

    /* access modifiers changed from: private */
    public final Bitmap doInBackground(Uri... uriArr) {
        if (uriArr.length == 1 && uriArr[0] != null) {
            try {
                return this.zzpd.zzb(uriArr[0]);
            } catch (RemoteException e) {
                zzbd.zza(e, "Unable to call %s on %s.", "doFetch", zzae.class.getSimpleName());
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final /* synthetic */ void onPostExecute(Object obj) {
        Bitmap bitmap = (Bitmap) obj;
        zzab zzab = this.zzpe;
        if (zzab != null) {
            zzab.onPostExecute(bitmap);
        }
    }
}

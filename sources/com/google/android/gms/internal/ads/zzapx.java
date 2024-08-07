package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;
import java.util.concurrent.TimeUnit;

@zzadh
public final class zzapx {
    private final Context mContext;
    private final String zzchp;
    private final zznx zzcxo;
    private boolean zzcxs;
    private final zznv zzdad;
    private final zzalp zzdae = new zzals().zza("min_1", Double.MIN_VALUE, 1.0d).zza("1_5", 1.0d, 5.0d).zza("5_10", 5.0d, 10.0d).zza("10_20", 10.0d, 20.0d).zza("20_30", 20.0d, 30.0d).zza("30_max", 30.0d, Double.MAX_VALUE).zzrz();
    private final long[] zzdaf;
    private final String[] zzdag;
    private boolean zzdah = false;
    private boolean zzdai = false;
    private boolean zzdaj = false;
    private boolean zzdak = false;
    private zzapg zzdal;
    private boolean zzdam;
    private boolean zzdan;
    private long zzdao = -1;
    private final zzang zzzw;

    public zzapx(Context context, zzang zzang, String str, zznx zznx, zznv zznv) {
        this.mContext = context;
        this.zzzw = zzang;
        this.zzchp = str;
        this.zzcxo = zznx;
        this.zzdad = zznv;
        String str2 = (String) zzkb.zzik().zzd(zznk.zzave);
        if (str2 == null) {
            this.zzdag = new String[0];
            this.zzdaf = new long[0];
            return;
        }
        String[] split = TextUtils.split(str2, ",");
        this.zzdag = new String[split.length];
        this.zzdaf = new long[split.length];
        for (int i = 0; i < split.length; i++) {
            try {
                this.zzdaf[i] = Long.parseLong(split[i]);
            } catch (NumberFormatException e) {
                zzakb.zzc("Unable to parse frame hash target time number.", e);
                this.zzdaf[i] = -1;
            }
        }
    }

    public final void onStop() {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzavd)).booleanValue() && !this.zzdam) {
            Bundle bundle = new Bundle();
            bundle.putString("type", "native-player-metrics");
            bundle.putString("request", this.zzchp);
            bundle.putString("player", this.zzdal.zzsp());
            for (zzalr next : this.zzdae.zzry()) {
                String valueOf = String.valueOf(next.name);
                bundle.putString(valueOf.length() != 0 ? "fps_c_".concat(valueOf) : new String("fps_c_"), Integer.toString(next.count));
                String valueOf2 = String.valueOf(next.name);
                bundle.putString(valueOf2.length() != 0 ? "fps_p_".concat(valueOf2) : new String("fps_p_"), Double.toString(next.zzctb));
            }
            int i = 0;
            while (true) {
                long[] jArr = this.zzdaf;
                if (i < jArr.length) {
                    String str = this.zzdag[i];
                    if (str != null) {
                        String valueOf3 = String.valueOf(Long.valueOf(jArr[i]));
                        StringBuilder sb = new StringBuilder(String.valueOf(valueOf3).length() + 3);
                        sb.append("fh_");
                        sb.append(valueOf3);
                        bundle.putString(sb.toString(), str);
                    }
                    i++;
                } else {
                    zzbv.zzek().zza(this.mContext, this.zzzw.zzcw, "gmob-apps", bundle, true);
                    this.zzdam = true;
                    return;
                }
            }
        }
    }

    public final void zzb(zzapg zzapg) {
        zznq.zza(this.zzcxo, this.zzdad, "vpc2");
        this.zzdah = true;
        zznx zznx = this.zzcxo;
        if (zznx != null) {
            zznx.zze("vpn", zzapg.zzsp());
        }
        this.zzdal = zzapg;
    }

    public final void zzc(zzapg zzapg) {
        if (this.zzdaj && !this.zzdak) {
            if (zzakb.zzqp() && !this.zzdak) {
                zzakb.m43v("VideoMetricsMixin first frame");
            }
            zznq.zza(this.zzcxo, this.zzdad, "vff2");
            this.zzdak = true;
        }
        long nanoTime = zzbv.zzer().nanoTime();
        if (this.zzcxs && this.zzdan && this.zzdao != -1) {
            double nanos = (double) TimeUnit.SECONDS.toNanos(1);
            double d = (double) (nanoTime - this.zzdao);
            Double.isNaN(nanos);
            Double.isNaN(d);
            this.zzdae.zza(nanos / d);
        }
        this.zzdan = this.zzcxs;
        this.zzdao = nanoTime;
        long longValue = ((Long) zzkb.zzik().zzd(zznk.zzavf)).longValue();
        long currentPosition = (long) zzapg.getCurrentPosition();
        int i = 0;
        while (true) {
            String[] strArr = this.zzdag;
            if (i >= strArr.length) {
                return;
            }
            if (strArr[i] != null || longValue <= Math.abs(currentPosition - this.zzdaf[i])) {
                zzapg zzapg2 = zzapg;
                i++;
            } else {
                String[] strArr2 = this.zzdag;
                int i2 = 8;
                Bitmap bitmap = zzapg.getBitmap(8, 8);
                long j = 63;
                int i3 = 0;
                long j2 = 0;
                while (i3 < i2) {
                    int i4 = 0;
                    while (i4 < i2) {
                        int pixel = bitmap.getPixel(i4, i3);
                        j2 |= ((Color.blue(pixel) + Color.red(pixel)) + Color.green(pixel) > 128 ? 1 : 0) << ((int) j);
                        i4++;
                        j--;
                        i2 = 8;
                    }
                    i3++;
                    i2 = 8;
                }
                strArr2[i] = String.format("%016X", new Object[]{Long.valueOf(j2)});
                return;
            }
        }
    }

    public final void zzsv() {
        if (this.zzdah && !this.zzdai) {
            zznq.zza(this.zzcxo, this.zzdad, "vfr2");
            this.zzdai = true;
        }
    }

    public final void zztt() {
        this.zzcxs = true;
        if (this.zzdai && !this.zzdaj) {
            zznq.zza(this.zzcxo, this.zzdad, "vfp2");
            this.zzdaj = true;
        }
    }

    public final void zztu() {
        this.zzcxs = false;
    }
}

package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.List;

@zzadh
public final class zzru extends UnifiedNativeAd {
    private final zzrr zzbkw;
    private final List<NativeAd.Image> zzbkx = new ArrayList();
    private final zzpz zzbky;
    private final VideoController zzbkz = new VideoController();
    private final NativeAd.AdChoicesInfo zzbla;

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0074 A[Catch:{ RemoteException -> 0x0081 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public zzru(com.google.android.gms.internal.ads.zzrr r6) {
        /*
            r5 = this;
            java.lang.String r0 = ""
            r5.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            r5.zzbkx = r1
            com.google.android.gms.ads.VideoController r1 = new com.google.android.gms.ads.VideoController
            r1.<init>()
            r5.zzbkz = r1
            r5.zzbkw = r6
            r1 = 0
            java.util.List r6 = r6.getImages()     // Catch:{ RemoteException -> 0x0053 }
            if (r6 == 0) goto L_0x0057
            java.util.Iterator r6 = r6.iterator()     // Catch:{ RemoteException -> 0x0053 }
        L_0x0020:
            boolean r2 = r6.hasNext()     // Catch:{ RemoteException -> 0x0053 }
            if (r2 == 0) goto L_0x0057
            java.lang.Object r2 = r6.next()     // Catch:{ RemoteException -> 0x0053 }
            boolean r3 = r2 instanceof android.os.IBinder     // Catch:{ RemoteException -> 0x0053 }
            if (r3 == 0) goto L_0x0045
            android.os.IBinder r2 = (android.os.IBinder) r2     // Catch:{ RemoteException -> 0x0053 }
            if (r2 == 0) goto L_0x0045
            java.lang.String r3 = "com.google.android.gms.ads.internal.formats.client.INativeAdImage"
            android.os.IInterface r3 = r2.queryLocalInterface(r3)     // Catch:{ RemoteException -> 0x0053 }
            boolean r4 = r3 instanceof com.google.android.gms.internal.ads.zzpw     // Catch:{ RemoteException -> 0x0053 }
            if (r4 == 0) goto L_0x003f
            com.google.android.gms.internal.ads.zzpw r3 = (com.google.android.gms.internal.ads.zzpw) r3     // Catch:{ RemoteException -> 0x0053 }
            goto L_0x0046
        L_0x003f:
            com.google.android.gms.internal.ads.zzpy r3 = new com.google.android.gms.internal.ads.zzpy     // Catch:{ RemoteException -> 0x0053 }
            r3.<init>(r2)     // Catch:{ RemoteException -> 0x0053 }
            goto L_0x0046
        L_0x0045:
            r3 = r1
        L_0x0046:
            if (r3 == 0) goto L_0x0020
            java.util.List<com.google.android.gms.ads.formats.NativeAd$Image> r2 = r5.zzbkx     // Catch:{ RemoteException -> 0x0053 }
            com.google.android.gms.internal.ads.zzpz r4 = new com.google.android.gms.internal.ads.zzpz     // Catch:{ RemoteException -> 0x0053 }
            r4.<init>(r3)     // Catch:{ RemoteException -> 0x0053 }
            r2.add(r4)     // Catch:{ RemoteException -> 0x0053 }
            goto L_0x0020
        L_0x0053:
            r6 = move-exception
            com.google.android.gms.internal.ads.zzane.zzb(r0, r6)
        L_0x0057:
            com.google.android.gms.internal.ads.zzrr r6 = r5.zzbkw     // Catch:{ RemoteException -> 0x0065 }
            com.google.android.gms.internal.ads.zzpw r6 = r6.zzjz()     // Catch:{ RemoteException -> 0x0065 }
            if (r6 == 0) goto L_0x0069
            com.google.android.gms.internal.ads.zzpz r2 = new com.google.android.gms.internal.ads.zzpz     // Catch:{ RemoteException -> 0x0065 }
            r2.<init>(r6)     // Catch:{ RemoteException -> 0x0065 }
            goto L_0x006a
        L_0x0065:
            r6 = move-exception
            com.google.android.gms.internal.ads.zzane.zzb(r0, r6)
        L_0x0069:
            r2 = r1
        L_0x006a:
            r5.zzbky = r2
            com.google.android.gms.internal.ads.zzrr r6 = r5.zzbkw     // Catch:{ RemoteException -> 0x0081 }
            com.google.android.gms.internal.ads.zzps r6 = r6.zzkf()     // Catch:{ RemoteException -> 0x0081 }
            if (r6 == 0) goto L_0x0085
            com.google.android.gms.internal.ads.zzpv r6 = new com.google.android.gms.internal.ads.zzpv     // Catch:{ RemoteException -> 0x0081 }
            com.google.android.gms.internal.ads.zzrr r2 = r5.zzbkw     // Catch:{ RemoteException -> 0x0081 }
            com.google.android.gms.internal.ads.zzps r2 = r2.zzkf()     // Catch:{ RemoteException -> 0x0081 }
            r6.<init>(r2)     // Catch:{ RemoteException -> 0x0081 }
            r1 = r6
            goto L_0x0085
        L_0x0081:
            r6 = move-exception
            com.google.android.gms.internal.ads.zzane.zzb(r0, r6)
        L_0x0085:
            r5.zzbla = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzru.<init>(com.google.android.gms.internal.ads.zzrr):void");
    }

    /* access modifiers changed from: private */
    /* renamed from: zzka */
    public final IObjectWrapper zzbe() {
        try {
            return this.zzbkw.zzka();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final void cancelUnconfirmedClick() {
        try {
            this.zzbkw.cancelUnconfirmedClick();
        } catch (RemoteException e) {
            zzane.zzb("Failed to cancelUnconfirmedClick", e);
        }
    }

    public final void destroy() {
        try {
            this.zzbkw.destroy();
        } catch (RemoteException e) {
            zzane.zzb("", e);
        }
    }

    public final NativeAd.AdChoicesInfo getAdChoicesInfo() {
        return this.zzbla;
    }

    public final String getAdvertiser() {
        try {
            return this.zzbkw.getAdvertiser();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final String getBody() {
        try {
            return this.zzbkw.getBody();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final String getCallToAction() {
        try {
            return this.zzbkw.getCallToAction();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final Bundle getExtras() {
        try {
            Bundle extras = this.zzbkw.getExtras();
            if (extras != null) {
                return extras;
            }
        } catch (RemoteException e) {
            zzane.zzb("", e);
        }
        return new Bundle();
    }

    public final String getHeadline() {
        try {
            return this.zzbkw.getHeadline();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final NativeAd.Image getIcon() {
        return this.zzbky;
    }

    public final List<NativeAd.Image> getImages() {
        return this.zzbkx;
    }

    public final String getMediationAdapterClassName() {
        try {
            return this.zzbkw.getMediationAdapterClassName();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final String getPrice() {
        try {
            return this.zzbkw.getPrice();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final Double getStarRating() {
        try {
            double starRating = this.zzbkw.getStarRating();
            if (starRating == -1.0d) {
                return null;
            }
            return Double.valueOf(starRating);
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final String getStore() {
        try {
            return this.zzbkw.getStore();
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }

    public final VideoController getVideoController() {
        try {
            if (this.zzbkw.getVideoController() != null) {
                this.zzbkz.zza(this.zzbkw.getVideoController());
            }
        } catch (RemoteException e) {
            zzane.zzb("Exception occurred while getting video controller", e);
        }
        return this.zzbkz;
    }

    public final void performClick(Bundle bundle) {
        try {
            this.zzbkw.performClick(bundle);
        } catch (RemoteException e) {
            zzane.zzb("", e);
        }
    }

    public final boolean recordImpression(Bundle bundle) {
        try {
            return this.zzbkw.recordImpression(bundle);
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return false;
        }
    }

    public final void reportTouchEvent(Bundle bundle) {
        try {
            this.zzbkw.reportTouchEvent(bundle);
        } catch (RemoteException e) {
            zzane.zzb("", e);
        }
    }

    public final void setUnconfirmedClickListener(UnifiedNativeAd.UnconfirmedClickListener unconfirmedClickListener) {
        try {
            this.zzbkw.zza(new zzse(unconfirmedClickListener));
        } catch (RemoteException e) {
            zzane.zzb("Failed to setUnconfirmedClickListener", e);
        }
    }

    public final Object zzbh() {
        try {
            IObjectWrapper zzke = this.zzbkw.zzke();
            if (zzke != null) {
                return ObjectWrapper.unwrap(zzke);
            }
            return null;
        } catch (RemoteException e) {
            zzane.zzb("", e);
            return null;
        }
    }
}

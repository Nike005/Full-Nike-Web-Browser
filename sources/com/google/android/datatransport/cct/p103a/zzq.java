package com.google.android.datatransport.cct.p103a;

import com.google.android.datatransport.cct.p103a.zzg;
import com.google.firebase.messaging.Constants;

/* renamed from: com.google.android.datatransport.cct.a.zzq */
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
public abstract class zzq {

    /* renamed from: com.google.android.datatransport.cct.a.zzq$zza */
    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    public static abstract class zza {
        public abstract zza zza(zza zza);

        public abstract zza zza(zzb zzb);

        public abstract zzq zza();
    }

    /* renamed from: com.google.android.datatransport.cct.a.zzq$zzb */
    /* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
    public enum zzb {
        UNKNOWN(0),
        zzb(4);

        static {
            UNKNOWN = new zzb("UNKNOWN", 0, 0);
            zzb = new zzb(Constants.FirelogAnalytics.SDK_PLATFORM_ANDROID, 1, 4);
        }

        private zzb(int i) {
        }
    }

    public static zza zza() {
        return new zzg.zza();
    }
}

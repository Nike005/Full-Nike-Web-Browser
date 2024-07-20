package com.google.android.datatransport.cct.p103a;

import com.google.firebase.encoders.DataEncoder;
import com.google.firebase.encoders.ObjectEncoder;
import com.google.firebase.encoders.json.JsonDataEncoderBuilder;

/* renamed from: com.google.android.datatransport.cct.a.zzs */
/* compiled from: com.google.android.datatransport:transport-backend-cct@@2.2.0 */
public class zzs {
    public static DataEncoder zza() {
        return new JsonDataEncoderBuilder().registerEncoder(zze.class, (ObjectEncoder) new zzp()).registerEncoder(zzk.class, (ObjectEncoder) new zzw()).registerEncoder(zzg.class, (ObjectEncoder) new zzr()).registerEncoder(zzi.class, (ObjectEncoder) new zzu()).registerEncoder(zzd.class, (ObjectEncoder) new zzb()).registerEncoder(zzn.class, (ObjectEncoder) new zzz()).build();
    }
}

package com.google.android.gms.internal.cast;

import android.os.Parcelable;

public final class zzcw implements Parcelable.Creator<zzcv> {
    /* JADX WARNING: type inference failed for: r1v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r1v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r14) {
        /*
            r13 = this;
            int r0 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r14)
            r1 = 0
            r2 = 0
            r3 = 0
            r10 = r1
            r12 = r10
            r6 = r3
            r8 = 0
            r9 = 0
            r11 = 0
        L_0x000e:
            int r1 = r14.dataPosition()
            if (r1 >= r0) goto L_0x004b
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r14)
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r1)
            switch(r2) {
                case 2: goto L_0x0046;
                case 3: goto L_0x0041;
                case 4: goto L_0x003c;
                case 5: goto L_0x0032;
                case 6: goto L_0x002d;
                case 7: goto L_0x0023;
                default: goto L_0x001f;
            }
        L_0x001f:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r14, r1)
            goto L_0x000e
        L_0x0023:
            android.os.Parcelable$Creator<com.google.android.gms.cast.zzad> r2 = com.google.android.gms.cast.zzad.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r14, r1, r2)
            r12 = r1
            com.google.android.gms.cast.zzad r12 = (com.google.android.gms.cast.zzad) r12
            goto L_0x000e
        L_0x002d:
            int r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r14, r1)
            goto L_0x000e
        L_0x0032:
            android.os.Parcelable$Creator<com.google.android.gms.cast.ApplicationMetadata> r2 = com.google.android.gms.cast.ApplicationMetadata.CREATOR
            android.os.Parcelable r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r14, r1, r2)
            r10 = r1
            com.google.android.gms.cast.ApplicationMetadata r10 = (com.google.android.gms.cast.ApplicationMetadata) r10
            goto L_0x000e
        L_0x003c:
            int r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r14, r1)
            goto L_0x000e
        L_0x0041:
            boolean r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r14, r1)
            goto L_0x000e
        L_0x0046:
            double r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readDouble(r14, r1)
            goto L_0x000e
        L_0x004b:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r14, r0)
            com.google.android.gms.internal.cast.zzcv r14 = new com.google.android.gms.internal.cast.zzcv
            r5 = r14
            r5.<init>(r6, r8, r9, r10, r11, r12)
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.zzcw.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzcv[i];
    }
}

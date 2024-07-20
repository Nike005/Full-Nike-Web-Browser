package com.google.android.gms.cast;

import android.os.Parcelable;

public final class zza implements Parcelable.Creator<AdBreakClipInfo> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r21) {
        /*
            r20 = this;
            r0 = r21
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r21)
            r2 = 0
            r4 = 0
            r8 = r2
            r16 = r8
            r6 = r4
            r7 = r6
            r10 = r7
            r11 = r10
            r12 = r11
            r13 = r12
            r14 = r13
            r15 = r14
            r18 = r15
            r19 = r18
        L_0x0018:
            int r2 = r21.dataPosition()
            if (r2 >= r1) goto L_0x006f
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r21)
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r2)
            switch(r3) {
                case 2: goto L_0x006a;
                case 3: goto L_0x0065;
                case 4: goto L_0x0060;
                case 5: goto L_0x005b;
                case 6: goto L_0x0056;
                case 7: goto L_0x0051;
                case 8: goto L_0x004c;
                case 9: goto L_0x0047;
                case 10: goto L_0x0042;
                case 11: goto L_0x003d;
                case 12: goto L_0x0038;
                case 13: goto L_0x002d;
                default: goto L_0x0029;
            }
        L_0x0029:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r0, r2)
            goto L_0x0018
        L_0x002d:
            android.os.Parcelable$Creator<com.google.android.gms.cast.VastAdsRequest> r3 = com.google.android.gms.cast.VastAdsRequest.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r19 = r2
            com.google.android.gms.cast.VastAdsRequest r19 = (com.google.android.gms.cast.VastAdsRequest) r19
            goto L_0x0018
        L_0x0038:
            java.lang.String r18 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0018
        L_0x003d:
            long r16 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r0, r2)
            goto L_0x0018
        L_0x0042:
            java.lang.String r15 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0018
        L_0x0047:
            java.lang.String r14 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0018
        L_0x004c:
            java.lang.String r13 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0018
        L_0x0051:
            java.lang.String r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0018
        L_0x0056:
            java.lang.String r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0018
        L_0x005b:
            java.lang.String r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0018
        L_0x0060:
            long r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r0, r2)
            goto L_0x0018
        L_0x0065:
            java.lang.String r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0018
        L_0x006a:
            java.lang.String r6 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0018
        L_0x006f:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r0, r1)
            com.google.android.gms.cast.AdBreakClipInfo r0 = new com.google.android.gms.cast.AdBreakClipInfo
            r5 = r0
            r5.<init>(r6, r7, r8, r10, r11, r12, r13, r14, r15, r16, r18, r19)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zza.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new AdBreakClipInfo[i];
    }
}

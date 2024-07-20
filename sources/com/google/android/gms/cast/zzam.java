package com.google.android.gms.cast;

import android.os.Parcelable;

public final class zzam implements Parcelable.Creator<MediaQueueItem> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r19) {
        /*
            r18 = this;
            r0 = r19
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r19)
            r2 = 0
            r3 = 0
            r5 = 0
            r10 = r3
            r12 = r10
            r14 = r12
            r7 = r5
            r16 = r7
            r17 = r16
            r8 = 0
            r9 = 0
        L_0x0014:
            int r2 = r19.dataPosition()
            if (r2 >= r1) goto L_0x0056
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r19)
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r2)
            switch(r3) {
                case 2: goto L_0x004c;
                case 3: goto L_0x0047;
                case 4: goto L_0x0042;
                case 5: goto L_0x003d;
                case 6: goto L_0x0038;
                case 7: goto L_0x0033;
                case 8: goto L_0x002e;
                case 9: goto L_0x0029;
                default: goto L_0x0025;
            }
        L_0x0025:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r0, r2)
            goto L_0x0014
        L_0x0029:
            java.lang.String r17 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0014
        L_0x002e:
            long[] r16 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createLongArray(r0, r2)
            goto L_0x0014
        L_0x0033:
            double r14 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readDouble(r0, r2)
            goto L_0x0014
        L_0x0038:
            double r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readDouble(r0, r2)
            goto L_0x0014
        L_0x003d:
            double r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readDouble(r0, r2)
            goto L_0x0014
        L_0x0042:
            boolean r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0014
        L_0x0047:
            int r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0014
        L_0x004c:
            android.os.Parcelable$Creator<com.google.android.gms.cast.MediaInfo> r3 = com.google.android.gms.cast.MediaInfo.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r7 = r2
            com.google.android.gms.cast.MediaInfo r7 = (com.google.android.gms.cast.MediaInfo) r7
            goto L_0x0014
        L_0x0056:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r0, r1)
            com.google.android.gms.cast.MediaQueueItem r0 = new com.google.android.gms.cast.MediaQueueItem
            r6 = r0
            r6.<init>(r7, r8, r9, r10, r12, r14, r16, r17)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzam.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new MediaQueueItem[i];
    }
}

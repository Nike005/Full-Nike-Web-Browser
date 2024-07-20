package com.google.android.gms.cast;

import android.os.Parcelable;

public final class zzan implements Parcelable.Creator<MediaStatus> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v5, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r34) {
        /*
            r33 = this;
            r0 = r34
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r34)
            r2 = 0
            r4 = 0
            r6 = 0
            r7 = 0
            r13 = r2
            r21 = r13
            r10 = r4
            r17 = r10
            r19 = r17
            r9 = r6
            r24 = r9
            r27 = r24
            r29 = r27
            r31 = r29
            r32 = r31
            r12 = 0
            r15 = 0
            r16 = 0
            r23 = 0
            r25 = 0
            r26 = 0
            r28 = 0
            r30 = 0
        L_0x002d:
            int r2 = r34.dataPosition()
            if (r2 >= r1) goto L_0x00b5
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r34)
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r2)
            switch(r3) {
                case 2: goto L_0x00aa;
                case 3: goto L_0x00a5;
                case 4: goto L_0x00a0;
                case 5: goto L_0x009b;
                case 6: goto L_0x0096;
                case 7: goto L_0x0091;
                case 8: goto L_0x008c;
                case 9: goto L_0x0087;
                case 10: goto L_0x0082;
                case 11: goto L_0x007d;
                case 12: goto L_0x0078;
                case 13: goto L_0x0073;
                case 14: goto L_0x006e;
                case 15: goto L_0x0069;
                case 16: goto L_0x0064;
                case 17: goto L_0x005d;
                case 18: goto L_0x0058;
                case 19: goto L_0x004d;
                case 20: goto L_0x0042;
                default: goto L_0x003e;
            }
        L_0x003e:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r0, r2)
            goto L_0x002d
        L_0x0042:
            android.os.Parcelable$Creator<com.google.android.gms.cast.VideoInfo> r3 = com.google.android.gms.cast.VideoInfo.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r32 = r2
            com.google.android.gms.cast.VideoInfo r32 = (com.google.android.gms.cast.VideoInfo) r32
            goto L_0x002d
        L_0x004d:
            android.os.Parcelable$Creator<com.google.android.gms.cast.AdBreakStatus> r3 = com.google.android.gms.cast.AdBreakStatus.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r31 = r2
            com.google.android.gms.cast.AdBreakStatus r31 = (com.google.android.gms.cast.AdBreakStatus) r31
            goto L_0x002d
        L_0x0058:
            boolean r30 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x002d
        L_0x005d:
            android.os.Parcelable$Creator<com.google.android.gms.cast.MediaQueueItem> r3 = com.google.android.gms.cast.MediaQueueItem.CREATOR
            java.util.ArrayList r29 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r0, r2, r3)
            goto L_0x002d
        L_0x0064:
            int r28 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x002d
        L_0x0069:
            java.lang.String r27 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x002d
        L_0x006e:
            int r26 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x002d
        L_0x0073:
            int r25 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x002d
        L_0x0078:
            long[] r24 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createLongArray(r0, r2)
            goto L_0x002d
        L_0x007d:
            boolean r23 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x002d
        L_0x0082:
            double r21 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readDouble(r0, r2)
            goto L_0x002d
        L_0x0087:
            long r19 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r0, r2)
            goto L_0x002d
        L_0x008c:
            long r17 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r0, r2)
            goto L_0x002d
        L_0x0091:
            int r16 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x002d
        L_0x0096:
            int r15 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x002d
        L_0x009b:
            double r13 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readDouble(r0, r2)
            goto L_0x002d
        L_0x00a0:
            int r12 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x002d
        L_0x00a5:
            long r10 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r0, r2)
            goto L_0x002d
        L_0x00aa:
            android.os.Parcelable$Creator<com.google.android.gms.cast.MediaInfo> r3 = com.google.android.gms.cast.MediaInfo.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r9 = r2
            com.google.android.gms.cast.MediaInfo r9 = (com.google.android.gms.cast.MediaInfo) r9
            goto L_0x002d
        L_0x00b5:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r0, r1)
            com.google.android.gms.cast.MediaStatus r0 = new com.google.android.gms.cast.MediaStatus
            r8 = r0
            r8.<init>(r9, r10, r12, r13, r15, r16, r17, r19, r21, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzan.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new MediaStatus[i];
    }
}

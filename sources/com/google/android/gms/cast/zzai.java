package com.google.android.gms.cast;

import android.os.Parcelable;

public final class zzai implements Parcelable.Creator<MediaInfo> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r20) {
        /*
            r19 = this;
            r0 = r20
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r20)
            r2 = 0
            r3 = 0
            r4 = 0
            r7 = r2
            r9 = r7
            r10 = r9
            r13 = r10
            r14 = r13
            r15 = r14
            r16 = r15
            r17 = r16
            r18 = r17
            r11 = r4
            r8 = 0
        L_0x0018:
            int r2 = r20.dataPosition()
            if (r2 >= r1) goto L_0x0074
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r20)
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r2)
            switch(r3) {
                case 2: goto L_0x006f;
                case 3: goto L_0x006a;
                case 4: goto L_0x0065;
                case 5: goto L_0x005b;
                case 6: goto L_0x0056;
                case 7: goto L_0x004f;
                case 8: goto L_0x0045;
                case 9: goto L_0x0040;
                case 10: goto L_0x0039;
                case 11: goto L_0x0032;
                case 12: goto L_0x002d;
                default: goto L_0x0029;
            }
        L_0x0029:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r0, r2)
            goto L_0x0018
        L_0x002d:
            java.lang.String r18 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0018
        L_0x0032:
            android.os.Parcelable$Creator<com.google.android.gms.cast.AdBreakClipInfo> r3 = com.google.android.gms.cast.AdBreakClipInfo.CREATOR
            java.util.ArrayList r17 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r0, r2, r3)
            goto L_0x0018
        L_0x0039:
            android.os.Parcelable$Creator<com.google.android.gms.cast.AdBreakInfo> r3 = com.google.android.gms.cast.AdBreakInfo.CREATOR
            java.util.ArrayList r16 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r0, r2, r3)
            goto L_0x0018
        L_0x0040:
            java.lang.String r15 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0018
        L_0x0045:
            android.os.Parcelable$Creator<com.google.android.gms.cast.TextTrackStyle> r3 = com.google.android.gms.cast.TextTrackStyle.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r14 = r2
            com.google.android.gms.cast.TextTrackStyle r14 = (com.google.android.gms.cast.TextTrackStyle) r14
            goto L_0x0018
        L_0x004f:
            android.os.Parcelable$Creator<com.google.android.gms.cast.MediaTrack> r3 = com.google.android.gms.cast.MediaTrack.CREATOR
            java.util.ArrayList r13 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createTypedList(r0, r2, r3)
            goto L_0x0018
        L_0x0056:
            long r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readLong(r0, r2)
            goto L_0x0018
        L_0x005b:
            android.os.Parcelable$Creator<com.google.android.gms.cast.MediaMetadata> r3 = com.google.android.gms.cast.MediaMetadata.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r10 = r2
            com.google.android.gms.cast.MediaMetadata r10 = (com.google.android.gms.cast.MediaMetadata) r10
            goto L_0x0018
        L_0x0065:
            java.lang.String r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0018
        L_0x006a:
            int r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readInt(r0, r2)
            goto L_0x0018
        L_0x006f:
            java.lang.String r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0018
        L_0x0074:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r0, r1)
            com.google.android.gms.cast.MediaInfo r0 = new com.google.android.gms.cast.MediaInfo
            r6 = r0
            r6.<init>(r7, r8, r9, r10, r11, r13, r14, r15, r16, r17, r18)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.zzai.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new MediaInfo[i];
    }
}

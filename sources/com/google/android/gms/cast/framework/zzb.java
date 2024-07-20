package com.google.android.gms.cast.framework;

import android.os.Parcelable;

public final class zzb implements Parcelable.Creator<CastOptions> {
    /* JADX WARNING: type inference failed for: r2v3, types: [android.os.Parcelable] */
    /* JADX WARNING: type inference failed for: r2v4, types: [android.os.Parcelable] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final /* synthetic */ java.lang.Object createFromParcel(android.os.Parcel r17) {
        /*
            r16 = this;
            r0 = r17
            int r1 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.validateObjectHeader(r17)
            r2 = 0
            r3 = 0
            r4 = 0
            r7 = r3
            r8 = r7
            r10 = r8
            r12 = r10
            r14 = r4
            r9 = 0
            r11 = 0
            r13 = 0
        L_0x0012:
            int r2 = r17.dataPosition()
            if (r2 >= r1) goto L_0x0059
            int r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readHeader(r17)
            int r3 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.getFieldId(r2)
            switch(r3) {
                case 2: goto L_0x0054;
                case 3: goto L_0x004f;
                case 4: goto L_0x004a;
                case 5: goto L_0x0040;
                case 6: goto L_0x003b;
                case 7: goto L_0x0031;
                case 8: goto L_0x002c;
                case 9: goto L_0x0027;
                default: goto L_0x0023;
            }
        L_0x0023:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.skipUnknownField(r0, r2)
            goto L_0x0012
        L_0x0027:
            double r14 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readDouble(r0, r2)
            goto L_0x0012
        L_0x002c:
            boolean r13 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0012
        L_0x0031:
            android.os.Parcelable$Creator<com.google.android.gms.cast.framework.media.CastMediaOptions> r3 = com.google.android.gms.cast.framework.media.CastMediaOptions.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r12 = r2
            com.google.android.gms.cast.framework.media.CastMediaOptions r12 = (com.google.android.gms.cast.framework.media.CastMediaOptions) r12
            goto L_0x0012
        L_0x003b:
            boolean r11 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0012
        L_0x0040:
            android.os.Parcelable$Creator<com.google.android.gms.cast.LaunchOptions> r3 = com.google.android.gms.cast.LaunchOptions.CREATOR
            android.os.Parcelable r2 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createParcelable(r0, r2, r3)
            r10 = r2
            com.google.android.gms.cast.LaunchOptions r10 = (com.google.android.gms.cast.LaunchOptions) r10
            goto L_0x0012
        L_0x004a:
            boolean r9 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.readBoolean(r0, r2)
            goto L_0x0012
        L_0x004f:
            java.util.ArrayList r8 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createStringList(r0, r2)
            goto L_0x0012
        L_0x0054:
            java.lang.String r7 = com.google.android.gms.common.internal.safeparcel.SafeParcelReader.createString(r0, r2)
            goto L_0x0012
        L_0x0059:
            com.google.android.gms.common.internal.safeparcel.SafeParcelReader.ensureAtEnd(r0, r1)
            com.google.android.gms.cast.framework.CastOptions r0 = new com.google.android.gms.cast.framework.CastOptions
            r6 = r0
            r6.<init>(r7, r8, r9, r10, r11, r12, r13, r14)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.framework.zzb.createFromParcel(android.os.Parcel):java.lang.Object");
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new CastOptions[i];
    }
}

package androidx.media2.exoplayer.external;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.media2.exoplayer.external.drm.DrmInitData;
import androidx.media2.exoplayer.external.metadata.Metadata;
import androidx.media2.exoplayer.external.util.Util;
import androidx.media2.exoplayer.external.video.ColorInfo;
import com.integralads.avid.library.mopub.utils.AvidJSONUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Format implements Parcelable {
    public static final Parcelable.Creator<Format> CREATOR = new Parcelable.Creator<Format>() {
        public Format createFromParcel(Parcel parcel) {
            return new Format(parcel);
        }

        public Format[] newArray(int i) {
            return new Format[i];
        }
    };
    public static final int NO_VALUE = -1;
    public static final long OFFSET_SAMPLE_RELATIVE = Long.MAX_VALUE;
    public final int accessibilityChannel;
    public final int bitrate;
    public final int channelCount;
    public final String codecs;
    public final ColorInfo colorInfo;
    public final String containerMimeType;
    public final DrmInitData drmInitData;
    public final int encoderDelay;
    public final int encoderPadding;
    public final float frameRate;
    private int hashCode;
    public final int height;

    /* renamed from: id */
    public final String f4078id;
    public final List<byte[]> initializationData;
    public final String label;
    public final String language;
    public final int maxInputSize;
    public final Metadata metadata;
    public final int pcmEncoding;
    public final float pixelWidthHeightRatio;
    public final byte[] projectionData;
    public final int roleFlags;
    public final int rotationDegrees;
    public final String sampleMimeType;
    public final int sampleRate;
    public final int selectionFlags;
    public final int stereoMode;
    public final long subsampleOffsetUs;
    public final int width;

    public int describeContents() {
        return 0;
    }

    @Deprecated
    public static Format createVideoContainerFormat(String str, String str2, String str3, String str4, int i, int i2, int i3, float f, List<byte[]> list, int i4) {
        return createVideoContainerFormat(str, (String) null, str2, str3, str4, i, i2, i3, f, list, i4, 0);
    }

    public static Format createVideoContainerFormat(String str, String str2, String str3, String str4, String str5, int i, int i2, int i3, float f, List<byte[]> list, int i4, int i5) {
        return new Format(str, str2, i4, i5, i, str5, (Metadata) null, str3, str4, -1, list, (DrmInitData) null, Long.MAX_VALUE, i2, i3, f, -1, -1.0f, (byte[]) null, -1, (ColorInfo) null, -1, -1, -1, -1, -1, (String) null, -1);
    }

    public static Format createVideoSampleFormat(String str, String str2, String str3, int i, int i2, int i3, int i4, float f, List<byte[]> list, DrmInitData drmInitData2) {
        return createVideoSampleFormat(str, str2, str3, i, i2, i3, i4, f, list, -1, -1.0f, drmInitData2);
    }

    public static Format createVideoSampleFormat(String str, String str2, String str3, int i, int i2, int i3, int i4, float f, List<byte[]> list, int i5, float f2, DrmInitData drmInitData2) {
        return createVideoSampleFormat(str, str2, str3, i, i2, i3, i4, f, list, i5, f2, (byte[]) null, -1, (ColorInfo) null, drmInitData2);
    }

    public static Format createVideoSampleFormat(String str, String str2, String str3, int i, int i2, int i3, int i4, float f, List<byte[]> list, int i5, float f2, byte[] bArr, int i6, ColorInfo colorInfo2, DrmInitData drmInitData2) {
        return new Format(str, (String) null, 0, 0, i, str3, (Metadata) null, (String) null, str2, i2, list, drmInitData2, Long.MAX_VALUE, i3, i4, f, i5, f2, bArr, i6, colorInfo2, -1, -1, -1, -1, -1, (String) null, -1);
    }

    @Deprecated
    public static Format createAudioContainerFormat(String str, String str2, String str3, String str4, int i, int i2, int i3, List<byte[]> list, int i4, String str5) {
        return createAudioContainerFormat(str, (String) null, str2, str3, str4, i, i2, i3, list, i4, 0, str5);
    }

    public static Format createAudioContainerFormat(String str, String str2, String str3, String str4, String str5, int i, int i2, int i3, List<byte[]> list, int i4, int i5, String str6) {
        return new Format(str, str2, i4, i5, i, str5, (Metadata) null, str3, str4, -1, list, (DrmInitData) null, Long.MAX_VALUE, -1, -1, -1.0f, -1, -1.0f, (byte[]) null, -1, (ColorInfo) null, i2, i3, -1, -1, -1, str6, -1);
    }

    public static Format createAudioSampleFormat(String str, String str2, String str3, int i, int i2, int i3, int i4, List<byte[]> list, DrmInitData drmInitData2, int i5, String str4) {
        return createAudioSampleFormat(str, str2, str3, i, i2, i3, i4, -1, list, drmInitData2, i5, str4);
    }

    public static Format createAudioSampleFormat(String str, String str2, String str3, int i, int i2, int i3, int i4, int i5, List<byte[]> list, DrmInitData drmInitData2, int i6, String str4) {
        return createAudioSampleFormat(str, str2, str3, i, i2, i3, i4, i5, -1, -1, list, drmInitData2, i6, str4, (Metadata) null);
    }

    public static Format createAudioSampleFormat(String str, String str2, String str3, int i, int i2, int i3, int i4, int i5, int i6, int i7, List<byte[]> list, DrmInitData drmInitData2, int i8, String str4, Metadata metadata2) {
        return new Format(str, (String) null, i8, 0, i, str3, metadata2, (String) null, str2, i2, list, drmInitData2, Long.MAX_VALUE, -1, -1, -1.0f, -1, -1.0f, (byte[]) null, -1, (ColorInfo) null, i3, i4, i5, i6, i7, str4, -1);
    }

    public static Format createTextContainerFormat(String str, String str2, String str3, String str4, String str5, int i, int i2, int i3, String str6) {
        return createTextContainerFormat(str, str2, str3, str4, str5, i, i2, i3, str6, -1);
    }

    public static Format createTextContainerFormat(String str, String str2, String str3, String str4, String str5, int i, int i2, int i3, String str6, int i4) {
        return new Format(str, str2, i2, i3, i, str5, (Metadata) null, str3, str4, -1, (List<byte[]>) null, (DrmInitData) null, Long.MAX_VALUE, -1, -1, -1.0f, -1, -1.0f, (byte[]) null, -1, (ColorInfo) null, -1, -1, -1, -1, -1, str6, i4);
    }

    public static Format createTextSampleFormat(String str, String str2, int i, String str3) {
        return createTextSampleFormat(str, str2, i, str3, (DrmInitData) null);
    }

    public static Format createTextSampleFormat(String str, String str2, int i, String str3, DrmInitData drmInitData2) {
        return createTextSampleFormat(str, str2, (String) null, -1, i, str3, -1, drmInitData2, Long.MAX_VALUE, Collections.emptyList());
    }

    public static Format createTextSampleFormat(String str, String str2, String str3, int i, int i2, String str4, int i3, DrmInitData drmInitData2) {
        return createTextSampleFormat(str, str2, str3, i, i2, str4, i3, drmInitData2, Long.MAX_VALUE, Collections.emptyList());
    }

    public static Format createTextSampleFormat(String str, String str2, String str3, int i, int i2, String str4, DrmInitData drmInitData2, long j) {
        return createTextSampleFormat(str, str2, str3, i, i2, str4, -1, drmInitData2, j, Collections.emptyList());
    }

    public static Format createTextSampleFormat(String str, String str2, String str3, int i, int i2, String str4, int i3, DrmInitData drmInitData2, long j, List<byte[]> list) {
        return new Format(str, (String) null, i2, 0, i, str3, (Metadata) null, (String) null, str2, -1, list, drmInitData2, j, -1, -1, -1.0f, -1, -1.0f, (byte[]) null, -1, (ColorInfo) null, -1, -1, -1, -1, -1, str4, i3);
    }

    public static Format createImageSampleFormat(String str, String str2, String str3, int i, int i2, List<byte[]> list, String str4, DrmInitData drmInitData2) {
        return new Format(str, (String) null, i2, 0, i, str3, (Metadata) null, (String) null, str2, -1, list, drmInitData2, Long.MAX_VALUE, -1, -1, -1.0f, -1, -1.0f, (byte[]) null, -1, (ColorInfo) null, -1, -1, -1, -1, -1, str4, -1);
    }

    @Deprecated
    public static Format createContainerFormat(String str, String str2, String str3, String str4, int i, int i2, String str5) {
        return createContainerFormat(str, (String) null, str2, str3, str4, i, i2, 0, str5);
    }

    public static Format createContainerFormat(String str, String str2, String str3, String str4, String str5, int i, int i2, int i3, String str6) {
        return new Format(str, str2, i2, i3, i, str5, (Metadata) null, str3, str4, -1, (List<byte[]>) null, (DrmInitData) null, Long.MAX_VALUE, -1, -1, -1.0f, -1, -1.0f, (byte[]) null, -1, (ColorInfo) null, -1, -1, -1, -1, -1, str6, -1);
    }

    public static Format createSampleFormat(String str, String str2, long j) {
        return new Format(str, (String) null, 0, 0, -1, (String) null, (Metadata) null, (String) null, str2, -1, (List<byte[]>) null, (DrmInitData) null, j, -1, -1, -1.0f, -1, -1.0f, (byte[]) null, -1, (ColorInfo) null, -1, -1, -1, -1, -1, (String) null, -1);
    }

    public static Format createSampleFormat(String str, String str2, String str3, int i, DrmInitData drmInitData2) {
        return new Format(str, (String) null, 0, 0, i, str3, (Metadata) null, (String) null, str2, -1, (List<byte[]>) null, drmInitData2, Long.MAX_VALUE, -1, -1, -1.0f, -1, -1.0f, (byte[]) null, -1, (ColorInfo) null, -1, -1, -1, -1, -1, (String) null, -1);
    }

    Format(String str, String str2, int i, int i2, int i3, String str3, Metadata metadata2, String str4, String str5, int i4, List<byte[]> list, DrmInitData drmInitData2, long j, int i5, int i6, float f, int i7, float f2, byte[] bArr, int i8, ColorInfo colorInfo2, int i9, int i10, int i11, int i12, int i13, String str6, int i14) {
        this.f4078id = str;
        this.label = str2;
        this.selectionFlags = i;
        this.roleFlags = i2;
        this.bitrate = i3;
        this.codecs = str3;
        this.metadata = metadata2;
        this.containerMimeType = str4;
        this.sampleMimeType = str5;
        this.maxInputSize = i4;
        this.initializationData = list == null ? Collections.emptyList() : list;
        this.drmInitData = drmInitData2;
        this.subsampleOffsetUs = j;
        this.width = i5;
        this.height = i6;
        this.frameRate = f;
        int i15 = i7;
        this.rotationDegrees = i15 == -1 ? 0 : i15;
        this.pixelWidthHeightRatio = f2 == -1.0f ? 1.0f : f2;
        this.projectionData = bArr;
        this.stereoMode = i8;
        this.colorInfo = colorInfo2;
        this.channelCount = i9;
        this.sampleRate = i10;
        this.pcmEncoding = i11;
        int i16 = i12;
        this.encoderDelay = i16 == -1 ? 0 : i16;
        int i17 = i13;
        this.encoderPadding = i17 == -1 ? 0 : i17;
        this.language = Util.normalizeLanguageCode(str6);
        this.accessibilityChannel = i14;
    }

    Format(Parcel parcel) {
        this.f4078id = parcel.readString();
        this.label = parcel.readString();
        this.selectionFlags = parcel.readInt();
        this.roleFlags = parcel.readInt();
        this.bitrate = parcel.readInt();
        this.codecs = parcel.readString();
        this.metadata = (Metadata) parcel.readParcelable(Metadata.class.getClassLoader());
        this.containerMimeType = parcel.readString();
        this.sampleMimeType = parcel.readString();
        this.maxInputSize = parcel.readInt();
        int readInt = parcel.readInt();
        this.initializationData = new ArrayList(readInt);
        for (int i = 0; i < readInt; i++) {
            this.initializationData.add(parcel.createByteArray());
        }
        this.drmInitData = (DrmInitData) parcel.readParcelable(DrmInitData.class.getClassLoader());
        this.subsampleOffsetUs = parcel.readLong();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.frameRate = parcel.readFloat();
        this.rotationDegrees = parcel.readInt();
        this.pixelWidthHeightRatio = parcel.readFloat();
        this.projectionData = Util.readBoolean(parcel) ? parcel.createByteArray() : null;
        this.stereoMode = parcel.readInt();
        this.colorInfo = (ColorInfo) parcel.readParcelable(ColorInfo.class.getClassLoader());
        this.channelCount = parcel.readInt();
        this.sampleRate = parcel.readInt();
        this.pcmEncoding = parcel.readInt();
        this.encoderDelay = parcel.readInt();
        this.encoderPadding = parcel.readInt();
        this.language = parcel.readString();
        this.accessibilityChannel = parcel.readInt();
    }

    public Format copyWithMaxInputSize(int i) {
        return new Format(this.f4078id, this.label, this.selectionFlags, this.roleFlags, this.bitrate, this.codecs, this.metadata, this.containerMimeType, this.sampleMimeType, i, this.initializationData, this.drmInitData, this.subsampleOffsetUs, this.width, this.height, this.frameRate, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.language, this.accessibilityChannel);
    }

    public Format copyWithSubsampleOffsetUs(long j) {
        return new Format(this.f4078id, this.label, this.selectionFlags, this.roleFlags, this.bitrate, this.codecs, this.metadata, this.containerMimeType, this.sampleMimeType, this.maxInputSize, this.initializationData, this.drmInitData, j, this.width, this.height, this.frameRate, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.language, this.accessibilityChannel);
    }

    public Format copyWithContainerInfo(String str, String str2, String str3, String str4, Metadata metadata2, int i, int i2, int i3, int i4, int i5, String str5) {
        Metadata metadata3 = this.metadata;
        Metadata metadata4 = metadata2;
        return new Format(str, str2, i5, this.roleFlags, i, str4, metadata3 != null ? metadata3.copyWithAppendedEntriesFrom(metadata4) : metadata4, this.containerMimeType, str3, this.maxInputSize, this.initializationData, this.drmInitData, this.subsampleOffsetUs, i2, i3, this.frameRate, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, i4, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, str5, this.accessibilityChannel);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001f, code lost:
        r6 = r1.language;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0048  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x005d  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0062  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.media2.exoplayer.external.Format copyWithManifestFormatInfo(androidx.media2.exoplayer.external.Format r34) {
        /*
            r33 = this;
            r0 = r33
            r1 = r34
            if (r0 != r1) goto L_0x0007
            return r0
        L_0x0007:
            java.lang.String r2 = r0.sampleMimeType
            int r2 = androidx.media2.exoplayer.external.util.MimeTypes.getTrackType(r2)
            java.lang.String r4 = r1.f4078id
            java.lang.String r3 = r1.label
            if (r3 == 0) goto L_0x0014
            goto L_0x0016
        L_0x0014:
            java.lang.String r3 = r0.label
        L_0x0016:
            r5 = r3
            java.lang.String r3 = r0.language
            r6 = 3
            r7 = 1
            if (r2 == r6) goto L_0x001f
            if (r2 != r7) goto L_0x0026
        L_0x001f:
            java.lang.String r6 = r1.language
            if (r6 == 0) goto L_0x0026
            r31 = r6
            goto L_0x0028
        L_0x0026:
            r31 = r3
        L_0x0028:
            int r3 = r0.bitrate
            r6 = -1
            if (r3 != r6) goto L_0x002f
            int r3 = r1.bitrate
        L_0x002f:
            r8 = r3
            java.lang.String r3 = r0.codecs
            if (r3 != 0) goto L_0x0043
            java.lang.String r6 = r1.codecs
            java.lang.String r6 = androidx.media2.exoplayer.external.util.Util.getCodecsOfType(r6, r2)
            java.lang.String[] r9 = androidx.media2.exoplayer.external.util.Util.splitCodecs(r6)
            int r9 = r9.length
            if (r9 != r7) goto L_0x0043
            r9 = r6
            goto L_0x0044
        L_0x0043:
            r9 = r3
        L_0x0044:
            androidx.media2.exoplayer.external.metadata.Metadata r3 = r0.metadata
            if (r3 != 0) goto L_0x004b
            androidx.media2.exoplayer.external.metadata.Metadata r3 = r1.metadata
            goto L_0x0051
        L_0x004b:
            androidx.media2.exoplayer.external.metadata.Metadata r6 = r1.metadata
            androidx.media2.exoplayer.external.metadata.Metadata r3 = r3.copyWithAppendedEntriesFrom(r6)
        L_0x0051:
            r10 = r3
            float r3 = r0.frameRate
            r6 = -1082130432(0xffffffffbf800000, float:-1.0)
            int r6 = (r3 > r6 ? 1 : (r3 == r6 ? 0 : -1))
            if (r6 != 0) goto L_0x0062
            r6 = 2
            if (r2 != r6) goto L_0x0062
            float r2 = r1.frameRate
            r20 = r2
            goto L_0x0064
        L_0x0062:
            r20 = r3
        L_0x0064:
            int r2 = r0.selectionFlags
            int r3 = r1.selectionFlags
            r6 = r2 | r3
            int r2 = r0.roleFlags
            int r3 = r1.roleFlags
            r7 = r2 | r3
            androidx.media2.exoplayer.external.drm.DrmInitData r1 = r1.drmInitData
            androidx.media2.exoplayer.external.drm.DrmInitData r2 = r0.drmInitData
            androidx.media2.exoplayer.external.drm.DrmInitData r15 = androidx.media2.exoplayer.external.drm.DrmInitData.createSessionCreationData(r1, r2)
            androidx.media2.exoplayer.external.Format r1 = new androidx.media2.exoplayer.external.Format
            r3 = r1
            java.lang.String r11 = r0.containerMimeType
            java.lang.String r12 = r0.sampleMimeType
            int r13 = r0.maxInputSize
            java.util.List<byte[]> r14 = r0.initializationData
            r34 = r1
            long r1 = r0.subsampleOffsetUs
            r16 = r1
            int r1 = r0.width
            r18 = r1
            int r1 = r0.height
            r19 = r1
            int r1 = r0.rotationDegrees
            r21 = r1
            float r1 = r0.pixelWidthHeightRatio
            r22 = r1
            byte[] r1 = r0.projectionData
            r23 = r1
            int r1 = r0.stereoMode
            r24 = r1
            androidx.media2.exoplayer.external.video.ColorInfo r1 = r0.colorInfo
            r25 = r1
            int r1 = r0.channelCount
            r26 = r1
            int r1 = r0.sampleRate
            r27 = r1
            int r1 = r0.pcmEncoding
            r28 = r1
            int r1 = r0.encoderDelay
            r29 = r1
            int r1 = r0.encoderPadding
            r30 = r1
            int r1 = r0.accessibilityChannel
            r32 = r1
            r3.<init>(r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32)
            return r34
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.Format.copyWithManifestFormatInfo(androidx.media2.exoplayer.external.Format):androidx.media2.exoplayer.external.Format");
    }

    public Format copyWithGaplessInfo(int i, int i2) {
        return new Format(this.f4078id, this.label, this.selectionFlags, this.roleFlags, this.bitrate, this.codecs, this.metadata, this.containerMimeType, this.sampleMimeType, this.maxInputSize, this.initializationData, this.drmInitData, this.subsampleOffsetUs, this.width, this.height, this.frameRate, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, i, i2, this.language, this.accessibilityChannel);
    }

    public Format copyWithFrameRate(float f) {
        return new Format(this.f4078id, this.label, this.selectionFlags, this.roleFlags, this.bitrate, this.codecs, this.metadata, this.containerMimeType, this.sampleMimeType, this.maxInputSize, this.initializationData, this.drmInitData, this.subsampleOffsetUs, this.width, this.height, f, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.language, this.accessibilityChannel);
    }

    public Format copyWithDrmInitData(DrmInitData drmInitData2) {
        return new Format(this.f4078id, this.label, this.selectionFlags, this.roleFlags, this.bitrate, this.codecs, this.metadata, this.containerMimeType, this.sampleMimeType, this.maxInputSize, this.initializationData, drmInitData2, this.subsampleOffsetUs, this.width, this.height, this.frameRate, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.language, this.accessibilityChannel);
    }

    public Format copyWithMetadata(Metadata metadata2) {
        return new Format(this.f4078id, this.label, this.selectionFlags, this.roleFlags, this.bitrate, this.codecs, metadata2, this.containerMimeType, this.sampleMimeType, this.maxInputSize, this.initializationData, this.drmInitData, this.subsampleOffsetUs, this.width, this.height, this.frameRate, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.language, this.accessibilityChannel);
    }

    public Format copyWithRotationDegrees(int i) {
        return new Format(this.f4078id, this.label, this.selectionFlags, this.roleFlags, this.bitrate, this.codecs, this.metadata, this.containerMimeType, this.sampleMimeType, this.maxInputSize, this.initializationData, this.drmInitData, this.subsampleOffsetUs, this.width, this.height, this.frameRate, i, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.language, this.accessibilityChannel);
    }

    public Format copyWithBitrate(int i) {
        return new Format(this.f4078id, this.label, this.selectionFlags, this.roleFlags, i, this.codecs, this.metadata, this.containerMimeType, this.sampleMimeType, this.maxInputSize, this.initializationData, this.drmInitData, this.subsampleOffsetUs, this.width, this.height, this.frameRate, this.rotationDegrees, this.pixelWidthHeightRatio, this.projectionData, this.stereoMode, this.colorInfo, this.channelCount, this.sampleRate, this.pcmEncoding, this.encoderDelay, this.encoderPadding, this.language, this.accessibilityChannel);
    }

    public int getPixelCount() {
        int i;
        int i2 = this.width;
        if (i2 == -1 || (i = this.height) == -1) {
            return -1;
        }
        return i2 * i;
    }

    public String toString() {
        String str = this.f4078id;
        String str2 = this.label;
        String str3 = this.containerMimeType;
        String str4 = this.sampleMimeType;
        String str5 = this.codecs;
        int i = this.bitrate;
        String str6 = this.language;
        int i2 = this.width;
        int i3 = this.height;
        float f = this.frameRate;
        int i4 = this.channelCount;
        int i5 = this.sampleRate;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 104 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length() + String.valueOf(str5).length() + String.valueOf(str6).length());
        sb.append("Format(");
        sb.append(str);
        sb.append(", ");
        sb.append(str2);
        sb.append(", ");
        sb.append(str3);
        sb.append(", ");
        sb.append(str4);
        sb.append(", ");
        sb.append(str5);
        sb.append(", ");
        sb.append(i);
        sb.append(", ");
        sb.append(str6);
        sb.append(", [");
        sb.append(i2);
        sb.append(", ");
        sb.append(i3);
        sb.append(", ");
        sb.append(f);
        sb.append("], [");
        sb.append(i4);
        sb.append(", ");
        sb.append(i5);
        sb.append("])");
        return sb.toString();
    }

    public int hashCode() {
        if (this.hashCode == 0) {
            String str = this.f4078id;
            int i = 0;
            int hashCode2 = (527 + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.label;
            int hashCode3 = (((((((hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + this.selectionFlags) * 31) + this.roleFlags) * 31) + this.bitrate) * 31;
            String str3 = this.codecs;
            int hashCode4 = (hashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
            Metadata metadata2 = this.metadata;
            int hashCode5 = (hashCode4 + (metadata2 == null ? 0 : metadata2.hashCode())) * 31;
            String str4 = this.containerMimeType;
            int hashCode6 = (hashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
            String str5 = this.sampleMimeType;
            int hashCode7 = (((((((((((((((((((((((((((hashCode6 + (str5 == null ? 0 : str5.hashCode())) * 31) + this.maxInputSize) * 31) + ((int) this.subsampleOffsetUs)) * 31) + this.width) * 31) + this.height) * 31) + Float.floatToIntBits(this.frameRate)) * 31) + this.rotationDegrees) * 31) + Float.floatToIntBits(this.pixelWidthHeightRatio)) * 31) + this.stereoMode) * 31) + this.channelCount) * 31) + this.sampleRate) * 31) + this.pcmEncoding) * 31) + this.encoderDelay) * 31) + this.encoderPadding) * 31;
            String str6 = this.language;
            if (str6 != null) {
                i = str6.hashCode();
            }
            this.hashCode = ((hashCode7 + i) * 31) + this.accessibilityChannel;
        }
        return this.hashCode;
    }

    public boolean equals(Object obj) {
        int i;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Format format = (Format) obj;
        int i2 = this.hashCode;
        if ((i2 == 0 || (i = format.hashCode) == 0 || i2 == i) && this.selectionFlags == format.selectionFlags && this.roleFlags == format.roleFlags && this.bitrate == format.bitrate && this.maxInputSize == format.maxInputSize && this.subsampleOffsetUs == format.subsampleOffsetUs && this.width == format.width && this.height == format.height && this.rotationDegrees == format.rotationDegrees && this.stereoMode == format.stereoMode && this.channelCount == format.channelCount && this.sampleRate == format.sampleRate && this.pcmEncoding == format.pcmEncoding && this.encoderDelay == format.encoderDelay && this.encoderPadding == format.encoderPadding && this.accessibilityChannel == format.accessibilityChannel && Float.compare(this.frameRate, format.frameRate) == 0 && Float.compare(this.pixelWidthHeightRatio, format.pixelWidthHeightRatio) == 0 && Util.areEqual(this.f4078id, format.f4078id) && Util.areEqual(this.label, format.label) && Util.areEqual(this.codecs, format.codecs) && Util.areEqual(this.containerMimeType, format.containerMimeType) && Util.areEqual(this.sampleMimeType, format.sampleMimeType) && Util.areEqual(this.language, format.language) && Arrays.equals(this.projectionData, format.projectionData) && Util.areEqual(this.metadata, format.metadata) && Util.areEqual(this.colorInfo, format.colorInfo) && Util.areEqual(this.drmInitData, format.drmInitData) && initializationDataEquals(format)) {
            return true;
        }
        return false;
    }

    public boolean initializationDataEquals(Format format) {
        if (this.initializationData.size() != format.initializationData.size()) {
            return false;
        }
        for (int i = 0; i < this.initializationData.size(); i++) {
            if (!Arrays.equals(this.initializationData.get(i), format.initializationData.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static String toLogString(Format format) {
        if (format == null) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("id=");
        sb.append(format.f4078id);
        sb.append(", mimeType=");
        sb.append(format.sampleMimeType);
        if (format.bitrate != -1) {
            sb.append(", bitrate=");
            sb.append(format.bitrate);
        }
        if (format.codecs != null) {
            sb.append(", codecs=");
            sb.append(format.codecs);
        }
        if (!(format.width == -1 || format.height == -1)) {
            sb.append(", res=");
            sb.append(format.width);
            sb.append(AvidJSONUtil.KEY_X);
            sb.append(format.height);
        }
        if (format.frameRate != -1.0f) {
            sb.append(", fps=");
            sb.append(format.frameRate);
        }
        if (format.channelCount != -1) {
            sb.append(", channels=");
            sb.append(format.channelCount);
        }
        if (format.sampleRate != -1) {
            sb.append(", sample_rate=");
            sb.append(format.sampleRate);
        }
        if (format.language != null) {
            sb.append(", language=");
            sb.append(format.language);
        }
        if (format.label != null) {
            sb.append(", label=");
            sb.append(format.label);
        }
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4078id);
        parcel.writeString(this.label);
        parcel.writeInt(this.selectionFlags);
        parcel.writeInt(this.roleFlags);
        parcel.writeInt(this.bitrate);
        parcel.writeString(this.codecs);
        boolean z = false;
        parcel.writeParcelable(this.metadata, 0);
        parcel.writeString(this.containerMimeType);
        parcel.writeString(this.sampleMimeType);
        parcel.writeInt(this.maxInputSize);
        int size = this.initializationData.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            parcel.writeByteArray(this.initializationData.get(i2));
        }
        parcel.writeParcelable(this.drmInitData, 0);
        parcel.writeLong(this.subsampleOffsetUs);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeFloat(this.frameRate);
        parcel.writeInt(this.rotationDegrees);
        parcel.writeFloat(this.pixelWidthHeightRatio);
        if (this.projectionData != null) {
            z = true;
        }
        Util.writeBoolean(parcel, z);
        byte[] bArr = this.projectionData;
        if (bArr != null) {
            parcel.writeByteArray(bArr);
        }
        parcel.writeInt(this.stereoMode);
        parcel.writeParcelable(this.colorInfo, i);
        parcel.writeInt(this.channelCount);
        parcel.writeInt(this.sampleRate);
        parcel.writeInt(this.pcmEncoding);
        parcel.writeInt(this.encoderDelay);
        parcel.writeInt(this.encoderPadding);
        parcel.writeString(this.language);
        parcel.writeInt(this.accessibilityChannel);
    }
}

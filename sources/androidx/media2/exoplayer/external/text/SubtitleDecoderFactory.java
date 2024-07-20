package androidx.media2.exoplayer.external.text;

import androidx.media2.exoplayer.external.Format;

public interface SubtitleDecoderFactory {
    public static final SubtitleDecoderFactory DEFAULT = new SubtitleDecoderFactory() {
        public boolean supportsFormat(Format format) {
            String str = format.sampleMimeType;
            return "text/vtt".equals(str) || "text/x-ssa".equals(str) || "application/ttml+xml".equals(str) || "application/x-mp4-vtt".equals(str) || "application/x-subrip".equals(str) || "application/x-quicktime-tx3g".equals(str) || "application/cea-608".equals(str) || "application/x-mp4-cea-608".equals(str) || "application/cea-708".equals(str) || "application/dvbsubs".equals(str) || "application/pgs".equals(str);
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public androidx.media2.exoplayer.external.text.SubtitleDecoder createDecoder(androidx.media2.exoplayer.external.Format r3) {
            /*
                r2 = this;
                java.lang.String r0 = r3.sampleMimeType
                int r1 = r0.hashCode()
                switch(r1) {
                    case -1351681404: goto L_0x0072;
                    case -1248334819: goto L_0x0067;
                    case -1026075066: goto L_0x005d;
                    case -1004728940: goto L_0x0053;
                    case 691401887: goto L_0x0049;
                    case 822864842: goto L_0x003f;
                    case 930165504: goto L_0x0035;
                    case 1566015601: goto L_0x002b;
                    case 1566016562: goto L_0x0020;
                    case 1668750253: goto L_0x0016;
                    case 1693976202: goto L_0x000b;
                    default: goto L_0x0009;
                }
            L_0x0009:
                goto L_0x007d
            L_0x000b:
                java.lang.String r1 = "application/ttml+xml"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x007d
                r0 = 3
                goto L_0x007e
            L_0x0016:
                java.lang.String r1 = "application/x-subrip"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x007d
                r0 = 4
                goto L_0x007e
            L_0x0020:
                java.lang.String r1 = "application/cea-708"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x007d
                r0 = 8
                goto L_0x007e
            L_0x002b:
                java.lang.String r1 = "application/cea-608"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x007d
                r0 = 6
                goto L_0x007e
            L_0x0035:
                java.lang.String r1 = "application/x-mp4-cea-608"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x007d
                r0 = 7
                goto L_0x007e
            L_0x003f:
                java.lang.String r1 = "text/x-ssa"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x007d
                r0 = 1
                goto L_0x007e
            L_0x0049:
                java.lang.String r1 = "application/x-quicktime-tx3g"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x007d
                r0 = 5
                goto L_0x007e
            L_0x0053:
                java.lang.String r1 = "text/vtt"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x007d
                r0 = 0
                goto L_0x007e
            L_0x005d:
                java.lang.String r1 = "application/x-mp4-vtt"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x007d
                r0 = 2
                goto L_0x007e
            L_0x0067:
                java.lang.String r1 = "application/pgs"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x007d
                r0 = 10
                goto L_0x007e
            L_0x0072:
                java.lang.String r1 = "application/dvbsubs"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x007d
                r0 = 9
                goto L_0x007e
            L_0x007d:
                r0 = -1
            L_0x007e:
                switch(r0) {
                    case 0: goto L_0x00cd;
                    case 1: goto L_0x00c5;
                    case 2: goto L_0x00bf;
                    case 3: goto L_0x00b9;
                    case 4: goto L_0x00b3;
                    case 5: goto L_0x00ab;
                    case 6: goto L_0x00a1;
                    case 7: goto L_0x00a1;
                    case 8: goto L_0x0097;
                    case 9: goto L_0x008f;
                    case 10: goto L_0x0089;
                    default: goto L_0x0081;
                }
            L_0x0081:
                java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
                java.lang.String r0 = "Attempted to create decoder for unsupported format"
                r3.<init>(r0)
                throw r3
            L_0x0089:
                androidx.media2.exoplayer.external.text.pgs.PgsDecoder r3 = new androidx.media2.exoplayer.external.text.pgs.PgsDecoder
                r3.<init>()
                return r3
            L_0x008f:
                androidx.media2.exoplayer.external.text.dvb.DvbDecoder r0 = new androidx.media2.exoplayer.external.text.dvb.DvbDecoder
                java.util.List<byte[]> r3 = r3.initializationData
                r0.<init>(r3)
                return r0
            L_0x0097:
                androidx.media2.exoplayer.external.text.cea.Cea708Decoder r0 = new androidx.media2.exoplayer.external.text.cea.Cea708Decoder
                int r1 = r3.accessibilityChannel
                java.util.List<byte[]> r3 = r3.initializationData
                r0.<init>(r1, r3)
                return r0
            L_0x00a1:
                androidx.media2.exoplayer.external.text.cea.Cea608Decoder r0 = new androidx.media2.exoplayer.external.text.cea.Cea608Decoder
                java.lang.String r1 = r3.sampleMimeType
                int r3 = r3.accessibilityChannel
                r0.<init>(r1, r3)
                return r0
            L_0x00ab:
                androidx.media2.exoplayer.external.text.tx3g.Tx3gDecoder r0 = new androidx.media2.exoplayer.external.text.tx3g.Tx3gDecoder
                java.util.List<byte[]> r3 = r3.initializationData
                r0.<init>(r3)
                return r0
            L_0x00b3:
                androidx.media2.exoplayer.external.text.subrip.SubripDecoder r3 = new androidx.media2.exoplayer.external.text.subrip.SubripDecoder
                r3.<init>()
                return r3
            L_0x00b9:
                androidx.media2.exoplayer.external.text.ttml.TtmlDecoder r3 = new androidx.media2.exoplayer.external.text.ttml.TtmlDecoder
                r3.<init>()
                return r3
            L_0x00bf:
                androidx.media2.exoplayer.external.text.webvtt.Mp4WebvttDecoder r3 = new androidx.media2.exoplayer.external.text.webvtt.Mp4WebvttDecoder
                r3.<init>()
                return r3
            L_0x00c5:
                androidx.media2.exoplayer.external.text.ssa.SsaDecoder r0 = new androidx.media2.exoplayer.external.text.ssa.SsaDecoder
                java.util.List<byte[]> r3 = r3.initializationData
                r0.<init>(r3)
                return r0
            L_0x00cd:
                androidx.media2.exoplayer.external.text.webvtt.WebvttDecoder r3 = new androidx.media2.exoplayer.external.text.webvtt.WebvttDecoder
                r3.<init>()
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.text.SubtitleDecoderFactory.C39391.createDecoder(androidx.media2.exoplayer.external.Format):androidx.media2.exoplayer.external.text.SubtitleDecoder");
        }
    };

    SubtitleDecoder createDecoder(Format format);

    boolean supportsFormat(Format format);
}

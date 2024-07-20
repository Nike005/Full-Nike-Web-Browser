package androidx.media2.exoplayer.external.text.webvtt;

import androidx.media2.exoplayer.external.text.Cue;
import androidx.media2.exoplayer.external.text.SimpleSubtitleDecoder;
import androidx.media2.exoplayer.external.text.SubtitleDecoderException;
import androidx.media2.exoplayer.external.text.webvtt.WebvttCue;
import androidx.media2.exoplayer.external.util.ParsableByteArray;
import androidx.media2.exoplayer.external.util.Util;
import java.util.ArrayList;
import java.util.Collections;

public final class Mp4WebvttDecoder extends SimpleSubtitleDecoder {
    private static final int BOX_HEADER_SIZE = 8;
    private static final int TYPE_payl = 1885436268;
    private static final int TYPE_sttg = 1937011815;
    private static final int TYPE_vttc = 1987343459;
    private final WebvttCue.Builder builder = new WebvttCue.Builder();
    private final ParsableByteArray sampleData = new ParsableByteArray();

    public Mp4WebvttDecoder() {
        super("Mp4WebvttDecoder");
    }

    /* access modifiers changed from: protected */
    public Mp4WebvttSubtitle decode(byte[] bArr, int i, boolean z) throws SubtitleDecoderException {
        this.sampleData.reset(bArr, i);
        ArrayList arrayList = new ArrayList();
        while (this.sampleData.bytesLeft() > 0) {
            if (this.sampleData.bytesLeft() >= 8) {
                int readInt = this.sampleData.readInt();
                if (this.sampleData.readInt() == TYPE_vttc) {
                    arrayList.add(parseVttCueBox(this.sampleData, this.builder, readInt - 8));
                } else {
                    this.sampleData.skipBytes(readInt - 8);
                }
            } else {
                throw new SubtitleDecoderException("Incomplete Mp4Webvtt Top Level box header found.");
            }
        }
        return new Mp4WebvttSubtitle(arrayList);
    }

    private static Cue parseVttCueBox(ParsableByteArray parsableByteArray, WebvttCue.Builder builder2, int i) throws SubtitleDecoderException {
        builder2.reset();
        while (i > 0) {
            if (i >= 8) {
                int readInt = parsableByteArray.readInt();
                int readInt2 = parsableByteArray.readInt();
                int i2 = readInt - 8;
                String fromUtf8Bytes = Util.fromUtf8Bytes(parsableByteArray.data, parsableByteArray.getPosition(), i2);
                parsableByteArray.skipBytes(i2);
                i = (i - 8) - i2;
                if (readInt2 == TYPE_sttg) {
                    WebvttCueParser.parseCueSettingsList(fromUtf8Bytes, builder2);
                } else if (readInt2 == TYPE_payl) {
                    WebvttCueParser.parseCueText((String) null, fromUtf8Bytes.trim(), builder2, Collections.emptyList());
                }
            } else {
                throw new SubtitleDecoderException("Incomplete vtt cue box header found.");
            }
        }
        return builder2.build();
    }
}

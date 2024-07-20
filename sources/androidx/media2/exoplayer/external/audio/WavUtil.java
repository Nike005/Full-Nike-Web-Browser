package androidx.media2.exoplayer.external.audio;

import androidx.media2.exoplayer.external.util.Util;

public final class WavUtil {
    public static final int DATA_FOURCC = 1684108385;
    public static final int FMT_FOURCC = 1718449184;
    public static final int RIFF_FOURCC = 1380533830;
    private static final int TYPE_A_LAW = 6;
    private static final int TYPE_FLOAT = 3;
    private static final int TYPE_MU_LAW = 7;
    private static final int TYPE_PCM = 1;
    private static final int TYPE_WAVE_FORMAT_EXTENSIBLE = 65534;
    public static final int WAVE_FOURCC = 1463899717;

    public static int getTypeForEncoding(int i) {
        if (i == Integer.MIN_VALUE) {
            return 1;
        }
        if (i == 268435456) {
            return 7;
        }
        if (i == 536870912) {
            return 6;
        }
        if (i == 1073741824 || i == 2 || i == 3) {
            return 1;
        }
        if (i == 4) {
            return 3;
        }
        throw new IllegalArgumentException();
    }

    public static int getEncodingForType(int i, int i2) {
        if (i != 1) {
            if (i == 3) {
                return i2 == 32 ? 4 : 0;
            }
            if (i != TYPE_WAVE_FORMAT_EXTENSIBLE) {
                if (i != 6) {
                    return i != 7 ? 0 : 268435456;
                }
                return 536870912;
            }
        }
        return Util.getPcmEncoding(i2);
    }

    private WavUtil() {
    }
}

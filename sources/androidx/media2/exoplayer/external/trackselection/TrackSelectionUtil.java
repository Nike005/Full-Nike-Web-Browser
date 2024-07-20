package androidx.media2.exoplayer.external.trackselection;

import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.source.TrackGroupArray;
import androidx.media2.exoplayer.external.source.chunk.MediaChunk;
import androidx.media2.exoplayer.external.source.chunk.MediaChunkIterator;
import androidx.media2.exoplayer.external.source.chunk.MediaChunkListIterator;
import androidx.media2.exoplayer.external.trackselection.DefaultTrackSelector;
import androidx.media2.exoplayer.external.trackselection.TrackSelection;
import androidx.media2.exoplayer.external.util.Assertions;
import java.util.Arrays;
import java.util.List;

public final class TrackSelectionUtil {

    public interface AdaptiveTrackSelectionFactory {
        TrackSelection createAdaptiveTrackSelection(TrackSelection.Definition definition);
    }

    private TrackSelectionUtil() {
    }

    public static TrackSelection[] createTrackSelectionsForDefinitions(TrackSelection.Definition[] definitionArr, AdaptiveTrackSelectionFactory adaptiveTrackSelectionFactory) {
        TrackSelection[] trackSelectionArr = new TrackSelection[definitionArr.length];
        boolean z = false;
        for (int i = 0; i < definitionArr.length; i++) {
            TrackSelection.Definition definition = definitionArr[i];
            if (definition != null) {
                if (definition.tracks.length <= 1 || z) {
                    trackSelectionArr[i] = new FixedTrackSelection(definition.group, definition.tracks[0], definition.reason, definition.data);
                } else {
                    trackSelectionArr[i] = adaptiveTrackSelectionFactory.createAdaptiveTrackSelection(definition);
                    z = true;
                }
            }
        }
        return trackSelectionArr;
    }

    public static DefaultTrackSelector.Parameters updateParametersWithOverride(DefaultTrackSelector.Parameters parameters, int i, TrackGroupArray trackGroupArray, boolean z, DefaultTrackSelector.SelectionOverride selectionOverride) {
        DefaultTrackSelector.ParametersBuilder rendererDisabled = parameters.buildUpon().clearSelectionOverrides(i).setRendererDisabled(i, z);
        if (selectionOverride != null) {
            rendererDisabled.setSelectionOverride(i, trackGroupArray, selectionOverride);
        }
        return rendererDisabled.build();
    }

    public static int getAverageBitrate(MediaChunkIterator mediaChunkIterator, long j) {
        long j2 = 0;
        long j3 = 0;
        while (true) {
            if (!mediaChunkIterator.next()) {
                break;
            }
            long j4 = mediaChunkIterator.getDataSpec().length;
            if (j4 == -1) {
                break;
            }
            long chunkEndTimeUs = mediaChunkIterator.getChunkEndTimeUs() - mediaChunkIterator.getChunkStartTimeUs();
            long j5 = j2 + chunkEndTimeUs;
            if (j5 >= j) {
                j3 += (j4 * (j - j2)) / chunkEndTimeUs;
                break;
            }
            j3 += j4;
            j2 = j5;
        }
        j = j2;
        if (j == 0) {
            return -1;
        }
        return (int) (((j3 * 8) * 1000000) / j);
    }

    static int[] getBitratesUsingFutureInfo(MediaChunkIterator[] mediaChunkIteratorArr, Format[] formatArr, long j, int[] iArr) {
        int length = mediaChunkIteratorArr.length;
        Assertions.checkArgument(length == formatArr.length);
        if (length == 0) {
            return new int[0];
        }
        if (iArr == null) {
            iArr = new int[length];
        }
        if (j == 0) {
            Arrays.fill(iArr, -1);
            return iArr;
        }
        int[] iArr2 = new int[length];
        float[] fArr = new float[length];
        boolean z = false;
        boolean z2 = false;
        for (int i = 0; i < length; i++) {
            int averageBitrate = getAverageBitrate(mediaChunkIteratorArr[i], j);
            if (averageBitrate != -1) {
                int i2 = formatArr[i].bitrate;
                iArr2[i] = i2;
                if (i2 != -1) {
                    fArr[i] = ((float) averageBitrate) / ((float) i2);
                    z2 = true;
                }
            } else {
                iArr2[i] = -1;
                z = true;
            }
            iArr[i] = averageBitrate;
        }
        if (z && z2) {
            estimateBitrates(iArr, formatArr, iArr2, fArr);
        }
        return iArr;
    }

    static int[] getBitratesUsingPastInfo(List<? extends MediaChunk> list, Format[] formatArr, long j, int[] iArr) {
        int averageQueueBitrate;
        int i;
        if (iArr == null) {
            iArr = new int[formatArr.length];
            Arrays.fill(iArr, -1);
        }
        if (!(j == 0 || (averageQueueBitrate = getAverageQueueBitrate(list, j)) == -1 || (i = ((MediaChunk) list.get(list.size() - 1)).trackFormat.bitrate) == -1)) {
            estimateBitrates(iArr, formatArr, new int[]{i}, new float[]{((float) averageQueueBitrate) / ((float) i)});
        }
        return iArr;
    }

    public static int[] getBitratesUsingPastAndFutureInfo(Format[] formatArr, List<? extends MediaChunk> list, long j, MediaChunkIterator[] mediaChunkIteratorArr, long j2, boolean z, int[] iArr) {
        int[] bitratesUsingFutureInfo = getBitratesUsingFutureInfo(mediaChunkIteratorArr, formatArr, j2, iArr);
        getBitratesUsingPastInfo(list, formatArr, j, bitratesUsingFutureInfo);
        for (int i = 0; i < bitratesUsingFutureInfo.length; i++) {
            int i2 = bitratesUsingFutureInfo[i];
            if (i2 == -1 || (z && formatArr[i].bitrate != -1 && i2 < formatArr[i].bitrate)) {
                bitratesUsingFutureInfo[i] = formatArr[i].bitrate;
            }
        }
        return bitratesUsingFutureInfo;
    }

    public static int[] getFormatBitrates(Format[] formatArr, int[] iArr) {
        int length = formatArr.length;
        if (iArr == null) {
            iArr = new int[length];
        }
        for (int i = 0; i < length; i++) {
            iArr[i] = formatArr[i].bitrate;
        }
        return iArr;
    }

    private static void estimateBitrates(int[] iArr, Format[] formatArr, int[] iArr2, float[] fArr) {
        int i;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] == -1 && (i = formatArr[i2].bitrate) != -1) {
                iArr[i2] = (int) (fArr[getClosestBitrateIndex(i, iArr2)] * ((float) i));
            }
        }
    }

    private static int getAverageQueueBitrate(List<? extends MediaChunk> list, long j) {
        if (list.isEmpty()) {
            return -1;
        }
        return getAverageBitrate(new MediaChunkListIterator(getSingleFormatSubQueue(list), true), j);
    }

    private static List<? extends MediaChunk> getSingleFormatSubQueue(List<? extends MediaChunk> list) {
        Format format = ((MediaChunk) list.get(list.size() - 1)).trackFormat;
        int size = list.size();
        for (int i = size - 2; i >= 0; i--) {
            if (!((MediaChunk) list.get(i)).trackFormat.equals(format)) {
                return list.subList(i + 1, size);
            }
        }
        return list;
    }

    private static int getClosestBitrateIndex(int i, int[] iArr) {
        int abs;
        int i2 = Integer.MAX_VALUE;
        int i3 = -1;
        for (int i4 = 0; i4 < iArr.length; i4++) {
            if (iArr[i4] != -1 && (abs = Math.abs(iArr[i4] - i)) < i2) {
                i3 = i4;
                i2 = abs;
            }
        }
        return i3;
    }
}

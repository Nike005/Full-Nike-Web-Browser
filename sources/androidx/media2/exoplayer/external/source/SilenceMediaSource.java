package androidx.media2.exoplayer.external.source;

import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.FormatHolder;
import androidx.media2.exoplayer.external.SeekParameters;
import androidx.media2.exoplayer.external.decoder.DecoderInputBuffer;
import androidx.media2.exoplayer.external.drm.DrmInitData;
import androidx.media2.exoplayer.external.source.MediaPeriod;
import androidx.media2.exoplayer.external.source.MediaSource;
import androidx.media2.exoplayer.external.upstream.Allocator;
import androidx.media2.exoplayer.external.upstream.TransferListener;
import androidx.media2.exoplayer.external.util.Assertions;
import androidx.media2.exoplayer.external.util.Util;
import java.util.ArrayList;
import java.util.List;

public final class SilenceMediaSource extends BaseMediaSource {
    private static final int CHANNEL_COUNT = 2;
    private static final int ENCODING = 2;
    /* access modifiers changed from: private */
    public static final Format FORMAT = Format.createAudioSampleFormat((String) null, "audio/raw", (String) null, -1, -1, 2, SAMPLE_RATE_HZ, 2, (List<byte[]>) null, (DrmInitData) null, 0, (String) null);
    private static final int SAMPLE_RATE_HZ = 44100;
    /* access modifiers changed from: private */
    public static final byte[] SILENCE_SAMPLE = new byte[(Util.getPcmFrameSize(2, 2) * 1024)];
    private final long durationUs;

    public void maybeThrowSourceInfoRefreshError() {
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
    }

    public void releaseSourceInternal() {
    }

    public SilenceMediaSource(long j) {
        Assertions.checkArgument(j >= 0);
        this.durationUs = j;
    }

    public void prepareSourceInternal(TransferListener transferListener) {
        refreshSourceInfo(new SinglePeriodTimeline(this.durationUs, true, false), (Object) null);
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        return new SilenceMediaPeriod(this.durationUs);
    }

    private static final class SilenceMediaPeriod implements MediaPeriod {
        private static final TrackGroupArray TRACKS = new TrackGroupArray(new TrackGroup(SilenceMediaSource.FORMAT));
        private final long durationUs;
        private final ArrayList<SampleStream> sampleStreams = new ArrayList<>();

        public boolean continueLoading(long j) {
            return false;
        }

        public void discardBuffer(long j, boolean z) {
        }

        public long getAdjustedSeekPositionUs(long j, SeekParameters seekParameters) {
            return j;
        }

        public long getBufferedPositionUs() {
            return Long.MIN_VALUE;
        }

        public long getNextLoadPositionUs() {
            return Long.MIN_VALUE;
        }

        public List getStreamKeys(List list) {
            return MediaPeriod$$CC.getStreamKeys$$dflt$$(this, list);
        }

        public void maybeThrowPrepareError() {
        }

        public long readDiscontinuity() {
            return -9223372036854775807L;
        }

        public void reevaluateBuffer(long j) {
        }

        public SilenceMediaPeriod(long j) {
            this.durationUs = j;
        }

        public void prepare(MediaPeriod.Callback callback, long j) {
            callback.onPrepared(this);
        }

        public TrackGroupArray getTrackGroups() {
            return TRACKS;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v1, resolved type: java.lang.Object[]} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v2, resolved type: java.lang.Object[]} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v2, resolved type: java.lang.Object[]} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public long selectTracks(androidx.media2.exoplayer.external.trackselection.TrackSelection[] r5, boolean[] r6, androidx.media2.exoplayer.external.source.SampleStream[] r7, boolean[] r8, long r9) {
            /*
                r4 = this;
                r0 = 0
            L_0x0001:
                int r1 = r5.length
                if (r0 >= r1) goto L_0x0039
                r1 = r7[r0]
                if (r1 == 0) goto L_0x001a
                r1 = r5[r0]
                if (r1 == 0) goto L_0x0010
                boolean r1 = r6[r0]
                if (r1 != 0) goto L_0x001a
            L_0x0010:
                java.util.ArrayList<androidx.media2.exoplayer.external.source.SampleStream> r1 = r4.sampleStreams
                r2 = r7[r0]
                r1.remove(r2)
                r1 = 0
                r7[r0] = r1
            L_0x001a:
                r1 = r7[r0]
                if (r1 != 0) goto L_0x0036
                r1 = r5[r0]
                if (r1 == 0) goto L_0x0036
                androidx.media2.exoplayer.external.source.SilenceMediaSource$SilenceSampleStream r1 = new androidx.media2.exoplayer.external.source.SilenceMediaSource$SilenceSampleStream
                long r2 = r4.durationUs
                r1.<init>(r2)
                r1.seekTo(r9)
                java.util.ArrayList<androidx.media2.exoplayer.external.source.SampleStream> r2 = r4.sampleStreams
                r2.add(r1)
                r7[r0] = r1
                r1 = 1
                r8[r0] = r1
            L_0x0036:
                int r0 = r0 + 1
                goto L_0x0001
            L_0x0039:
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.source.SilenceMediaSource.SilenceMediaPeriod.selectTracks(androidx.media2.exoplayer.external.trackselection.TrackSelection[], boolean[], androidx.media2.exoplayer.external.source.SampleStream[], boolean[], long):long");
        }

        public long seekToUs(long j) {
            for (int i = 0; i < this.sampleStreams.size(); i++) {
                ((SilenceSampleStream) this.sampleStreams.get(i)).seekTo(j);
            }
            return j;
        }
    }

    private static final class SilenceSampleStream implements SampleStream {
        private final long durationBytes;
        private long positionBytes;
        private boolean sentFormat;

        public boolean isReady() {
            return true;
        }

        public void maybeThrowError() {
        }

        public SilenceSampleStream(long j) {
            this.durationBytes = SilenceMediaSource.getAudioByteCount(j);
            seekTo(0);
        }

        public void seekTo(long j) {
            this.positionBytes = SilenceMediaSource.getAudioByteCount(j);
        }

        public int readData(FormatHolder formatHolder, DecoderInputBuffer decoderInputBuffer, boolean z) {
            if (!this.sentFormat || z) {
                formatHolder.format = SilenceMediaSource.FORMAT;
                this.sentFormat = true;
                return -5;
            }
            long j = this.durationBytes - this.positionBytes;
            if (j == 0) {
                decoderInputBuffer.addFlag(4);
                return -4;
            }
            int min = (int) Math.min((long) SilenceMediaSource.SILENCE_SAMPLE.length, j);
            decoderInputBuffer.ensureSpaceForWrite(min);
            decoderInputBuffer.addFlag(1);
            decoderInputBuffer.data.put(SilenceMediaSource.SILENCE_SAMPLE, 0, min);
            decoderInputBuffer.timeUs = SilenceMediaSource.getAudioPositionUs(this.positionBytes);
            this.positionBytes += (long) min;
            return -4;
        }

        public int skipData(long j) {
            long j2 = this.positionBytes;
            seekTo(j);
            return (int) ((this.positionBytes - j2) / ((long) SilenceMediaSource.SILENCE_SAMPLE.length));
        }
    }

    /* access modifiers changed from: private */
    public static long getAudioByteCount(long j) {
        return ((long) Util.getPcmFrameSize(2, 2)) * ((j * 44100) / 1000000);
    }

    /* access modifiers changed from: private */
    public static long getAudioPositionUs(long j) {
        return ((j / ((long) Util.getPcmFrameSize(2, 2))) * 1000000) / 44100;
    }
}

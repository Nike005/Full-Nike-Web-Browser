package androidx.media2.exoplayer.external.source.chunk;

import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.extractor.DefaultExtractorInput;
import androidx.media2.exoplayer.external.extractor.Extractor;
import androidx.media2.exoplayer.external.extractor.PositionHolder;
import androidx.media2.exoplayer.external.source.chunk.ChunkExtractorWrapper;
import androidx.media2.exoplayer.external.upstream.DataSource;
import androidx.media2.exoplayer.external.upstream.DataSpec;
import androidx.media2.exoplayer.external.util.Assertions;
import androidx.media2.exoplayer.external.util.Util;
import java.io.IOException;

public class ContainerMediaChunk extends BaseMediaChunk {
    private static final PositionHolder DUMMY_POSITION_HOLDER = new PositionHolder();
    private final int chunkCount;
    private final ChunkExtractorWrapper extractorWrapper;
    private volatile boolean loadCanceled;
    private boolean loadCompleted;
    private long nextLoadPosition;
    private final long sampleOffsetUs;

    /* access modifiers changed from: protected */
    public ChunkExtractorWrapper.TrackOutputProvider getTrackOutputProvider(BaseMediaChunkOutput baseMediaChunkOutput) {
        return baseMediaChunkOutput;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ContainerMediaChunk(DataSource dataSource, DataSpec dataSpec, Format format, int i, Object obj, long j, long j2, long j3, long j4, long j5, int i2, long j6, ChunkExtractorWrapper chunkExtractorWrapper) {
        super(dataSource, dataSpec, format, i, obj, j, j2, j3, j4, j5);
        this.chunkCount = i2;
        this.sampleOffsetUs = j6;
        this.extractorWrapper = chunkExtractorWrapper;
    }

    public long getNextChunkIndex() {
        return this.chunkIndex + ((long) this.chunkCount);
    }

    public boolean isLoadCompleted() {
        return this.loadCompleted;
    }

    public final void cancelLoad() {
        this.loadCanceled = true;
    }

    public final void load() throws IOException, InterruptedException {
        DefaultExtractorInput defaultExtractorInput;
        long j;
        DataSpec subrange = this.dataSpec.subrange(this.nextLoadPosition);
        try {
            defaultExtractorInput = new DefaultExtractorInput(this.dataSource, subrange.absoluteStreamPosition, this.dataSource.open(subrange));
            if (this.nextLoadPosition == 0) {
                BaseMediaChunkOutput output = getOutput();
                output.setSampleOffsetUs(this.sampleOffsetUs);
                ChunkExtractorWrapper chunkExtractorWrapper = this.extractorWrapper;
                ChunkExtractorWrapper.TrackOutputProvider trackOutputProvider = getTrackOutputProvider(output);
                if (this.clippedStartTimeUs == -9223372036854775807L) {
                    j = -9223372036854775807L;
                } else {
                    j = this.clippedStartTimeUs - this.sampleOffsetUs;
                }
                chunkExtractorWrapper.init(trackOutputProvider, j, this.clippedEndTimeUs == -9223372036854775807L ? -9223372036854775807L : this.clippedEndTimeUs - this.sampleOffsetUs);
            }
            Extractor extractor = this.extractorWrapper.extractor;
            boolean z = false;
            int i = 0;
            while (i == 0 && !this.loadCanceled) {
                i = extractor.read(defaultExtractorInput, DUMMY_POSITION_HOLDER);
            }
            if (i != 1) {
                z = true;
            }
            Assertions.checkState(z);
            this.nextLoadPosition = defaultExtractorInput.getPosition() - this.dataSpec.absoluteStreamPosition;
            Util.closeQuietly((DataSource) this.dataSource);
            this.loadCompleted = true;
        } catch (Throwable th) {
            Util.closeQuietly((DataSource) this.dataSource);
            throw th;
        }
    }
}

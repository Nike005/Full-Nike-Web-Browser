package androidx.media2.exoplayer.external.source.chunk;

import androidx.media2.exoplayer.external.upstream.DataSpec;
import java.util.NoSuchElementException;

public interface MediaChunkIterator {
    public static final MediaChunkIterator EMPTY = new MediaChunkIterator() {
        public boolean isEnded() {
            return true;
        }

        public boolean next() {
            return false;
        }

        public void reset() {
        }

        public DataSpec getDataSpec() {
            throw new NoSuchElementException();
        }

        public long getChunkStartTimeUs() {
            throw new NoSuchElementException();
        }

        public long getChunkEndTimeUs() {
            throw new NoSuchElementException();
        }
    };

    long getChunkEndTimeUs();

    long getChunkStartTimeUs();

    DataSpec getDataSpec();

    boolean isEnded();

    boolean next();

    void reset();
}

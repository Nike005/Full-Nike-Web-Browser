package androidx.media2;

public abstract class DataSourceDesc2 {
    static final long LONG_MAX = 576460752303423487L;
    public static final long POSITION_UNKNOWN = 576460752303423487L;
    public static final int TYPE_CALLBACK = 1;
    public static final int TYPE_FD = 2;
    public static final int TYPE_URI = 3;
    long mEndPositionMs = 576460752303423487L;
    String mMediaId;
    long mStartPositionMs = 0;

    public abstract int getType();

    DataSourceDesc2(Builder builder) {
        if (builder.mStartPositionMs <= builder.mEndPositionMs) {
            this.mMediaId = builder.mMediaId;
            this.mStartPositionMs = builder.mStartPositionMs;
            this.mEndPositionMs = builder.mEndPositionMs;
            return;
        }
        throw new IllegalStateException("Illegal start/end position: " + builder.mStartPositionMs + " : " + builder.mEndPositionMs);
    }

    public String getMediaId() {
        return this.mMediaId;
    }

    public long getStartPosition() {
        return this.mStartPositionMs;
    }

    public long getEndPosition() {
        return this.mEndPositionMs;
    }

    public static abstract class Builder<T extends Builder> {
        long mEndPositionMs = 576460752303423487L;
        String mMediaId;
        long mStartPositionMs = 0;

        Builder() {
        }

        Builder(DataSourceDesc2 dataSourceDesc2) {
            this.mMediaId = dataSourceDesc2.mMediaId;
            this.mStartPositionMs = dataSourceDesc2.mStartPositionMs;
            this.mEndPositionMs = dataSourceDesc2.mEndPositionMs;
        }

        public T setMediaId(String str) {
            this.mMediaId = str;
            return this;
        }

        public T setStartPosition(long j) {
            if (j < 0) {
                j = 0;
            }
            this.mStartPositionMs = j;
            return this;
        }

        public T setEndPosition(long j) {
            if (j < 0) {
                j = 576460752303423487L;
            }
            this.mEndPositionMs = j;
            return this;
        }
    }
}

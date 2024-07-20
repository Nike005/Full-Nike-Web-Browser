package androidx.media2;

import android.media.MediaTimestamp;

public final class MediaTimestamp2 {
    public static final MediaTimestamp2 TIMESTAMP_UNKNOWN = new MediaTimestamp2(-1, -1, 0.0f);
    private final float mClockRate;
    private final long mMediaTimeUs;
    private final long mNanoTime;

    public long getAnchorMediaTimeUs() {
        return this.mMediaTimeUs;
    }

    public long getAnchorSystemNanoTime() {
        return this.mNanoTime;
    }

    public float getMediaClockRate() {
        return this.mClockRate;
    }

    MediaTimestamp2(MediaTimestamp mediaTimestamp) {
        this.mMediaTimeUs = mediaTimestamp.getAnchorMediaTimeUs();
        this.mNanoTime = mediaTimestamp.getAnchorSytemNanoTime();
        this.mClockRate = mediaTimestamp.getMediaClockRate();
    }

    MediaTimestamp2(long j, long j2, float f) {
        this.mMediaTimeUs = j;
        this.mNanoTime = j2;
        this.mClockRate = f;
    }

    MediaTimestamp2() {
        this.mMediaTimeUs = 0;
        this.mNanoTime = 0;
        this.mClockRate = 1.0f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MediaTimestamp2 mediaTimestamp2 = (MediaTimestamp2) obj;
        if (this.mMediaTimeUs == mediaTimestamp2.mMediaTimeUs && this.mNanoTime == mediaTimestamp2.mNanoTime && this.mClockRate == mediaTimestamp2.mClockRate) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (int) (((float) (((int) (((long) (Long.valueOf(this.mMediaTimeUs).hashCode() * 31)) + this.mNanoTime)) * 31)) + this.mClockRate);
    }

    public String toString() {
        return getClass().getName() + "{AnchorMediaTimeUs=" + this.mMediaTimeUs + " AnchorSystemNanoTime=" + this.mNanoTime + " ClockRate=" + this.mClockRate + "}";
    }
}

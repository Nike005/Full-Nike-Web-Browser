package androidx.media2.player;

public class TimedMetaData {
    private static final String TAG = "TimedMetaData";
    private byte[] mMetaData;
    private long mTimestampUs;

    public TimedMetaData(android.media.TimedMetaData timedMetaData) {
        this.mTimestampUs = timedMetaData.getTimestamp();
        this.mMetaData = timedMetaData.getMetaData();
    }

    public TimedMetaData(long j, byte[] bArr) {
        this.mTimestampUs = j;
        this.mMetaData = bArr;
    }

    public long getTimestamp() {
        return this.mTimestampUs;
    }

    public byte[] getMetaData() {
        return this.mMetaData;
    }
}

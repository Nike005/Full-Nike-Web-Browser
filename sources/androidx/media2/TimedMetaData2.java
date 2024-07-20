package androidx.media2;

import android.media.TimedMetaData;

public class TimedMetaData2 {
    private static final String TAG = "TimedMetaData";
    private byte[] mMetaData;
    private long mTimestampUs;

    public TimedMetaData2(TimedMetaData timedMetaData) {
        this.mTimestampUs = timedMetaData.getTimestamp();
        this.mMetaData = timedMetaData.getMetaData();
    }

    public TimedMetaData2(long j, byte[] bArr) {
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

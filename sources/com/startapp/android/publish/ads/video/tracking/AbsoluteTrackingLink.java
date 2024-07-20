package com.startapp.android.publish.ads.video.tracking;

import com.startapp.common.p046c.C5302e;
import java.io.Serializable;

@C5302e(mo45476c = true)
/* compiled from: StartAppSDK */
public class AbsoluteTrackingLink extends VideoTrackingLink implements Serializable {
    private static final long serialVersionUID = 1;
    private int videoOffsetMillis;

    public int getVideoOffsetMillis() {
        return this.videoOffsetMillis;
    }

    public void setVideoOffsetMillis(int i) {
        this.videoOffsetMillis = i;
    }

    public String toString() {
        return super.toString() + ", videoOffsetMillis=" + this.videoOffsetMillis;
    }
}

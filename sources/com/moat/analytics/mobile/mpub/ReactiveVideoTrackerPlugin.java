package com.moat.analytics.mobile.mpub;

import android.app.Activity;
import android.view.View;
import com.moat.analytics.mobile.mpub.C0361x;
import com.moat.analytics.mobile.mpub.p002a.p004b.C0301a;
import java.util.Map;

public class ReactiveVideoTrackerPlugin implements MoatPlugin<ReactiveVideoTracker> {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public final String f51a;

    /* renamed from: com.moat.analytics.mobile.mpub.ReactiveVideoTrackerPlugin$a */
    static class C0297a implements ReactiveVideoTracker {
        C0297a() {
        }

        public void changeTargetView(View view) {
        }

        public void dispatchEvent(MoatAdEvent moatAdEvent) {
        }

        public void removeListener() {
        }

        public void removeVideoListener() {
        }

        public void setActivity(Activity activity) {
        }

        public void setListener(TrackerListener trackerListener) {
        }

        public void setPlayerVolume(Double d) {
        }

        public void setVideoListener(VideoTrackerListener videoTrackerListener) {
        }

        public void stopTracking() {
        }

        public boolean trackVideoAd(Map<String, String> map, Integer num, View view) {
            return false;
        }
    }

    public ReactiveVideoTrackerPlugin(String str) {
        this.f51a = str;
    }

    /* renamed from: c */
    public ReactiveVideoTracker mo10327a() {
        return (ReactiveVideoTracker) C0361x.m285a(new C0361x.C0363a<ReactiveVideoTracker>() {
            /* renamed from: a */
            public C0301a<ReactiveVideoTracker> mo10357a() {
                C0336p.m231a("[INFO] ", "Attempting to create ReactiveVideoTracker");
                return C0301a.m81a(new C0365y(ReactiveVideoTrackerPlugin.this.f51a));
            }
        }, ReactiveVideoTracker.class);
    }

    /* renamed from: d */
    public ReactiveVideoTracker mo10328b() {
        return new C0297a();
    }
}

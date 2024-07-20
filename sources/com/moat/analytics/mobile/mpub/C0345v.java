package com.moat.analytics.mobile.mpub;

import android.app.Activity;
import android.app.Application;
import android.media.MediaPlayer;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.mpub.NativeDisplayTracker;
import java.util.Map;

/* renamed from: com.moat.analytics.mobile.mpub.v */
abstract class C0345v {

    /* renamed from: com.moat.analytics.mobile.mpub.v$a */
    public static class C0346a extends MoatAnalytics {
        public void prepareNativeDisplayTracking(String str) {
        }

        public void start(Application application) {
        }

        public void start(MoatOptions moatOptions, Application application) {
        }
    }

    /* renamed from: com.moat.analytics.mobile.mpub.v$b */
    public static class C0347b extends MoatFactory {
        public <T> T createCustomTracker(MoatPlugin<T> moatPlugin) {
            return moatPlugin.mo10328b();
        }

        public NativeDisplayTracker createNativeDisplayTracker(View view, Map<String, String> map) {
            return new C0348c();
        }

        public NativeVideoTracker createNativeVideoTracker(String str) {
            return new C0349d();
        }

        public WebAdTracker createWebAdTracker(ViewGroup viewGroup) {
            return new C0350e();
        }

        public WebAdTracker createWebAdTracker(WebView webView) {
            return new C0350e();
        }
    }

    /* renamed from: com.moat.analytics.mobile.mpub.v$c */
    static class C0348c implements NativeDisplayTracker {
        C0348c() {
        }

        public void removeListener() {
        }

        public void reportUserInteractionEvent(NativeDisplayTracker.MoatUserInteractionType moatUserInteractionType) {
        }

        public void setActivity(Activity activity) {
        }

        public void setListener(TrackerListener trackerListener) {
        }

        public void startTracking() {
        }

        public void stopTracking() {
        }
    }

    /* renamed from: com.moat.analytics.mobile.mpub.v$d */
    static class C0349d implements NativeVideoTracker {
        C0349d() {
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

        public boolean trackVideoAd(Map<String, String> map, MediaPlayer mediaPlayer, View view) {
            return false;
        }
    }

    /* renamed from: com.moat.analytics.mobile.mpub.v$e */
    static class C0350e implements WebAdTracker {
        C0350e() {
        }

        public void removeListener() {
        }

        public void setActivity(Activity activity) {
        }

        public void setListener(TrackerListener trackerListener) {
        }

        public void startTracking() {
        }

        public void stopTracking() {
        }
    }

    C0345v() {
    }
}

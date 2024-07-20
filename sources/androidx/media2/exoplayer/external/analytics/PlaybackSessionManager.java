package androidx.media2.exoplayer.external.analytics;

import androidx.media2.exoplayer.external.Timeline;
import androidx.media2.exoplayer.external.analytics.AnalyticsListener;
import androidx.media2.exoplayer.external.source.MediaSource;

public interface PlaybackSessionManager {

    public interface Listener {
        void onAdPlaybackStarted(AnalyticsListener.EventTime eventTime, String str, String str2);

        void onSessionActive(AnalyticsListener.EventTime eventTime, String str);

        void onSessionCreated(AnalyticsListener.EventTime eventTime, String str);

        void onSessionFinished(AnalyticsListener.EventTime eventTime, String str, boolean z);
    }

    boolean belongsToSession(AnalyticsListener.EventTime eventTime, String str);

    String getSessionForMediaPeriodId(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId);

    void handlePositionDiscontinuity(AnalyticsListener.EventTime eventTime, int i);

    void handleTimelineUpdate(AnalyticsListener.EventTime eventTime);

    void setListener(Listener listener);

    void updateSessions(AnalyticsListener.EventTime eventTime);
}

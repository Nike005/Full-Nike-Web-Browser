package androidx.media2.exoplayer.external.source;

import androidx.media2.exoplayer.external.source.MediaSource;
import androidx.media2.exoplayer.external.source.MediaSourceEventListener;
import java.io.IOException;

public abstract class DefaultMediaSourceEventListener implements MediaSourceEventListener {
    public void onDownstreamFormatChanged(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaSourceEventListener.MediaLoadData mediaLoadData) {
    }

    public void onLoadCanceled(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaSourceEventListener.LoadEventInfo loadEventInfo, MediaSourceEventListener.MediaLoadData mediaLoadData) {
    }

    public void onLoadCompleted(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaSourceEventListener.LoadEventInfo loadEventInfo, MediaSourceEventListener.MediaLoadData mediaLoadData) {
    }

    public void onLoadError(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaSourceEventListener.LoadEventInfo loadEventInfo, MediaSourceEventListener.MediaLoadData mediaLoadData, IOException iOException, boolean z) {
    }

    public void onLoadStarted(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaSourceEventListener.LoadEventInfo loadEventInfo, MediaSourceEventListener.MediaLoadData mediaLoadData) {
    }

    public void onMediaPeriodCreated(int i, MediaSource.MediaPeriodId mediaPeriodId) {
    }

    public void onMediaPeriodReleased(int i, MediaSource.MediaPeriodId mediaPeriodId) {
    }

    public void onReadingStarted(int i, MediaSource.MediaPeriodId mediaPeriodId) {
    }

    public void onUpstreamDiscarded(int i, MediaSource.MediaPeriodId mediaPeriodId, MediaSourceEventListener.MediaLoadData mediaLoadData) {
    }
}
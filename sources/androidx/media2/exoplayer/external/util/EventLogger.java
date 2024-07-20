package androidx.media2.exoplayer.external.util;

import android.os.SystemClock;
import android.view.Surface;
import androidx.media2.exoplayer.external.ExoPlaybackException;
import androidx.media2.exoplayer.external.Format;
import androidx.media2.exoplayer.external.PlaybackParameters;
import androidx.media2.exoplayer.external.Timeline;
import androidx.media2.exoplayer.external.analytics.AnalyticsListener;
import androidx.media2.exoplayer.external.analytics.AnalyticsListener$$CC;
import androidx.media2.exoplayer.external.audio.AudioAttributes;
import androidx.media2.exoplayer.external.decoder.DecoderCounters;
import androidx.media2.exoplayer.external.metadata.Metadata;
import androidx.media2.exoplayer.external.source.MediaSourceEventListener;
import androidx.media2.exoplayer.external.source.TrackGroup;
import androidx.media2.exoplayer.external.source.TrackGroupArray;
import androidx.media2.exoplayer.external.trackselection.MappingTrackSelector;
import androidx.media2.exoplayer.external.trackselection.TrackSelection;
import androidx.media2.exoplayer.external.trackselection.TrackSelectionArray;
import com.appnext.ads.fullscreen.RewardedVideo;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

public class EventLogger implements AnalyticsListener {
    private static final String DEFAULT_TAG = "EventLogger";
    private static final int MAX_TIMELINE_ITEM_LINES = 3;
    private static final NumberFormat TIME_FORMAT;
    private final Timeline.Period period;
    private final long startTimeMs;
    private final String tag;
    private final MappingTrackSelector trackSelector;
    private final Timeline.Window window;

    private static String getAdaptiveSupportString(int i, int i2) {
        return i < 2 ? "N/A" : i2 != 0 ? i2 != 8 ? i2 != 16 ? "?" : "YES" : "YES_NOT_SEAMLESS" : "NO";
    }

    private static String getDiscontinuityReasonString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? "?" : "INTERNAL" : "AD_INSERTION" : "SEEK_ADJUSTMENT" : "SEEK" : "PERIOD_TRANSITION";
    }

    private static String getFormatSupportString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? "?" : "YES" : "NO_EXCEEDS_CAPABILITIES" : "NO_UNSUPPORTED_DRM" : "NO_UNSUPPORTED_TYPE" : "NO";
    }

    private static String getRepeatModeString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "?" : "ALL" : "ONE" : "OFF";
    }

    private static String getStateString(int i) {
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "?" : "ENDED" : "READY" : "BUFFERING" : "IDLE";
    }

    private static String getTimelineChangeReasonString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? "?" : "DYNAMIC" : "RESET" : "PREPARED";
    }

    private static String getTrackStatusString(boolean z) {
        return z ? "[X]" : "[ ]";
    }

    public void onAudioAttributesChanged(AnalyticsListener.EventTime eventTime, AudioAttributes audioAttributes) {
        AnalyticsListener$$CC.onAudioAttributesChanged$$dflt$$(this, eventTime, audioAttributes);
    }

    public void onBandwidthEstimate(AnalyticsListener.EventTime eventTime, int i, long j, long j2) {
    }

    public void onLoadCanceled(AnalyticsListener.EventTime eventTime, MediaSourceEventListener.LoadEventInfo loadEventInfo, MediaSourceEventListener.MediaLoadData mediaLoadData) {
    }

    public void onLoadCompleted(AnalyticsListener.EventTime eventTime, MediaSourceEventListener.LoadEventInfo loadEventInfo, MediaSourceEventListener.MediaLoadData mediaLoadData) {
    }

    public void onLoadStarted(AnalyticsListener.EventTime eventTime, MediaSourceEventListener.LoadEventInfo loadEventInfo, MediaSourceEventListener.MediaLoadData mediaLoadData) {
    }

    public void onVolumeChanged(AnalyticsListener.EventTime eventTime, float f) {
        AnalyticsListener$$CC.onVolumeChanged$$dflt$$(this, eventTime, f);
    }

    static {
        NumberFormat instance = NumberFormat.getInstance(Locale.US);
        TIME_FORMAT = instance;
        instance.setMinimumFractionDigits(2);
        TIME_FORMAT.setMaximumFractionDigits(2);
        TIME_FORMAT.setGroupingUsed(false);
    }

    public EventLogger(MappingTrackSelector mappingTrackSelector) {
        this(mappingTrackSelector, DEFAULT_TAG);
    }

    public EventLogger(MappingTrackSelector mappingTrackSelector, String str) {
        this.trackSelector = mappingTrackSelector;
        this.tag = str;
        this.window = new Timeline.Window();
        this.period = new Timeline.Period();
        this.startTimeMs = SystemClock.elapsedRealtime();
    }

    public void onLoadingChanged(AnalyticsListener.EventTime eventTime, boolean z) {
        logd(eventTime, "loading", Boolean.toString(z));
    }

    public void onPlayerStateChanged(AnalyticsListener.EventTime eventTime, boolean z, int i) {
        String stateString = getStateString(i);
        StringBuilder sb = new StringBuilder(String.valueOf(stateString).length() + 7);
        sb.append(z);
        sb.append(", ");
        sb.append(stateString);
        logd(eventTime, "state", sb.toString());
    }

    public void onRepeatModeChanged(AnalyticsListener.EventTime eventTime, int i) {
        logd(eventTime, "repeatMode", getRepeatModeString(i));
    }

    public void onShuffleModeChanged(AnalyticsListener.EventTime eventTime, boolean z) {
        logd(eventTime, "shuffleModeEnabled", Boolean.toString(z));
    }

    public void onPositionDiscontinuity(AnalyticsListener.EventTime eventTime, int i) {
        logd(eventTime, "positionDiscontinuity", getDiscontinuityReasonString(i));
    }

    public void onSeekStarted(AnalyticsListener.EventTime eventTime) {
        logd(eventTime, "seekStarted");
    }

    public void onPlaybackParametersChanged(AnalyticsListener.EventTime eventTime, PlaybackParameters playbackParameters) {
        logd(eventTime, "playbackParameters", Util.formatInvariant("speed=%.2f, pitch=%.2f, skipSilence=%s", Float.valueOf(playbackParameters.speed), Float.valueOf(playbackParameters.pitch), Boolean.valueOf(playbackParameters.skipSilence)));
    }

    public void onTimelineChanged(AnalyticsListener.EventTime eventTime, int i) {
        int periodCount = eventTime.timeline.getPeriodCount();
        int windowCount = eventTime.timeline.getWindowCount();
        String eventTimeString = getEventTimeString(eventTime);
        String timelineChangeReasonString = getTimelineChangeReasonString(i);
        StringBuilder sb = new StringBuilder(String.valueOf(eventTimeString).length() + 76 + String.valueOf(timelineChangeReasonString).length());
        sb.append("timelineChanged [");
        sb.append(eventTimeString);
        sb.append(", periodCount=");
        sb.append(periodCount);
        sb.append(", windowCount=");
        sb.append(windowCount);
        sb.append(", reason=");
        sb.append(timelineChangeReasonString);
        logd(sb.toString());
        for (int i2 = 0; i2 < Math.min(periodCount, 3); i2++) {
            eventTime.timeline.getPeriod(i2, this.period);
            String timeString = getTimeString(this.period.getDurationMs());
            StringBuilder sb2 = new StringBuilder(String.valueOf(timeString).length() + 11);
            sb2.append("  period [");
            sb2.append(timeString);
            sb2.append("]");
            logd(sb2.toString());
        }
        if (periodCount > 3) {
            logd("  ...");
        }
        for (int i3 = 0; i3 < Math.min(windowCount, 3); i3++) {
            eventTime.timeline.getWindow(i3, this.window);
            String timeString2 = getTimeString(this.window.getDurationMs());
            boolean z = this.window.isSeekable;
            boolean z2 = this.window.isDynamic;
            StringBuilder sb3 = new StringBuilder(String.valueOf(timeString2).length() + 25);
            sb3.append("  window [");
            sb3.append(timeString2);
            sb3.append(", ");
            sb3.append(z);
            sb3.append(", ");
            sb3.append(z2);
            sb3.append("]");
            logd(sb3.toString());
        }
        if (windowCount > 3) {
            logd("  ...");
        }
        logd("]");
    }

    public void onPlayerError(AnalyticsListener.EventTime eventTime, ExoPlaybackException exoPlaybackException) {
        loge(eventTime, "playerFailed", exoPlaybackException);
    }

    public void onTracksChanged(AnalyticsListener.EventTime eventTime, TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
        String str;
        String str2;
        String str3;
        int i;
        MappingTrackSelector mappingTrackSelector = this.trackSelector;
        MappingTrackSelector.MappedTrackInfo currentMappedTrackInfo = mappingTrackSelector != null ? mappingTrackSelector.getCurrentMappedTrackInfo() : null;
        if (currentMappedTrackInfo == null) {
            logd(eventTime, "tracksChanged", "[]");
            return;
        }
        AnalyticsListener.EventTime eventTime2 = eventTime;
        String eventTimeString = getEventTimeString(eventTime);
        StringBuilder sb = new StringBuilder(String.valueOf(eventTimeString).length() + 17);
        sb.append("tracksChanged [");
        sb.append(eventTimeString);
        sb.append(", ");
        logd(sb.toString());
        int rendererCount = currentMappedTrackInfo.getRendererCount();
        int i2 = 0;
        while (true) {
            str = "    Group:";
            str2 = "  ]";
            str3 = " [";
            if (i2 >= rendererCount) {
                break;
            }
            TrackGroupArray trackGroups = currentMappedTrackInfo.getTrackGroups(i2);
            TrackSelection trackSelection = trackSelectionArray.get(i2);
            if (trackGroups.length > 0) {
                i = rendererCount;
                StringBuilder sb2 = new StringBuilder(24);
                sb2.append("  Renderer:");
                sb2.append(i2);
                sb2.append(str3);
                logd(sb2.toString());
                int i3 = 0;
                while (i3 < trackGroups.length) {
                    TrackGroup trackGroup = trackGroups.get(i3);
                    TrackGroupArray trackGroupArray2 = trackGroups;
                    String str4 = str2;
                    String adaptiveSupportString = getAdaptiveSupportString(trackGroup.length, currentMappedTrackInfo.getAdaptiveSupport(i2, i3, false));
                    StringBuilder sb3 = new StringBuilder(String.valueOf(adaptiveSupportString).length() + 44);
                    sb3.append(str);
                    sb3.append(i3);
                    sb3.append(", adaptive_supported=");
                    sb3.append(adaptiveSupportString);
                    sb3.append(str3);
                    logd(sb3.toString());
                    int i4 = 0;
                    while (i4 < trackGroup.length) {
                        String trackStatusString = getTrackStatusString(trackSelection, trackGroup, i4);
                        String formatSupportString = getFormatSupportString(currentMappedTrackInfo.getTrackSupport(i2, i3, i4));
                        TrackGroup trackGroup2 = trackGroup;
                        String logString = Format.toLogString(trackGroup.getFormat(i4));
                        String str5 = str;
                        StringBuilder sb4 = new StringBuilder(String.valueOf(trackStatusString).length() + 38 + String.valueOf(logString).length() + String.valueOf(formatSupportString).length());
                        sb4.append("      ");
                        sb4.append(trackStatusString);
                        sb4.append(" Track:");
                        sb4.append(i4);
                        sb4.append(", ");
                        sb4.append(logString);
                        sb4.append(", supported=");
                        sb4.append(formatSupportString);
                        logd(sb4.toString());
                        i4++;
                        str = str5;
                        trackGroup = trackGroup2;
                        str3 = str3;
                    }
                    String str6 = str;
                    String str7 = str3;
                    logd("    ]");
                    i3++;
                    TrackSelectionArray trackSelectionArray2 = trackSelectionArray;
                    trackGroups = trackGroupArray2;
                    str2 = str4;
                }
                String str8 = str2;
                if (trackSelection != null) {
                    int i5 = 0;
                    while (true) {
                        if (i5 >= trackSelection.length()) {
                            break;
                        }
                        Metadata metadata = trackSelection.getFormat(i5).metadata;
                        if (metadata != null) {
                            logd("    Metadata [");
                            printMetadata(metadata, "      ");
                            logd("    ]");
                            break;
                        }
                        i5++;
                    }
                }
                logd(str8);
            } else {
                i = rendererCount;
            }
            i2++;
            rendererCount = i;
        }
        String str9 = str;
        String str10 = str2;
        String str11 = str3;
        TrackGroupArray unmappedTrackGroups = currentMappedTrackInfo.getUnmappedTrackGroups();
        if (unmappedTrackGroups.length > 0) {
            logd("  Renderer:None [");
            int i6 = 0;
            while (i6 < unmappedTrackGroups.length) {
                StringBuilder sb5 = new StringBuilder(23);
                String str12 = str9;
                sb5.append(str12);
                sb5.append(i6);
                String str13 = str11;
                sb5.append(str13);
                logd(sb5.toString());
                TrackGroup trackGroup3 = unmappedTrackGroups.get(i6);
                int i7 = 0;
                while (i7 < trackGroup3.length) {
                    String trackStatusString2 = getTrackStatusString(false);
                    String formatSupportString2 = getFormatSupportString(0);
                    String logString2 = Format.toLogString(trackGroup3.getFormat(i7));
                    String str14 = str12;
                    StringBuilder sb6 = new StringBuilder(String.valueOf(trackStatusString2).length() + 38 + String.valueOf(logString2).length() + String.valueOf(formatSupportString2).length());
                    sb6.append("      ");
                    sb6.append(trackStatusString2);
                    sb6.append(" Track:");
                    sb6.append(i7);
                    sb6.append(", ");
                    sb6.append(logString2);
                    sb6.append(", supported=");
                    sb6.append(formatSupportString2);
                    logd(sb6.toString());
                    i7++;
                    unmappedTrackGroups = unmappedTrackGroups;
                    str12 = str14;
                }
                TrackGroupArray trackGroupArray3 = unmappedTrackGroups;
                str9 = str12;
                logd("    ]");
                i6++;
                str11 = str13;
            }
            logd(str10);
        }
        logd("]");
    }

    public void onSeekProcessed(AnalyticsListener.EventTime eventTime) {
        logd(eventTime, "seekProcessed");
    }

    public void onMetadata(AnalyticsListener.EventTime eventTime, Metadata metadata) {
        String eventTimeString = getEventTimeString(eventTime);
        StringBuilder sb = new StringBuilder(String.valueOf(eventTimeString).length() + 12);
        sb.append("metadata [");
        sb.append(eventTimeString);
        sb.append(", ");
        logd(sb.toString());
        printMetadata(metadata, "  ");
        logd("]");
    }

    public void onDecoderEnabled(AnalyticsListener.EventTime eventTime, int i, DecoderCounters decoderCounters) {
        logd(eventTime, "decoderEnabled", getTrackTypeString(i));
    }

    public void onAudioSessionId(AnalyticsListener.EventTime eventTime, int i) {
        logd(eventTime, "audioSessionId", Integer.toString(i));
    }

    public void onDecoderInitialized(AnalyticsListener.EventTime eventTime, int i, String str, long j) {
        String trackTypeString = getTrackTypeString(i);
        StringBuilder sb = new StringBuilder(String.valueOf(trackTypeString).length() + 2 + String.valueOf(str).length());
        sb.append(trackTypeString);
        sb.append(", ");
        sb.append(str);
        logd(eventTime, "decoderInitialized", sb.toString());
    }

    public void onDecoderInputFormatChanged(AnalyticsListener.EventTime eventTime, int i, Format format) {
        String trackTypeString = getTrackTypeString(i);
        String logString = Format.toLogString(format);
        StringBuilder sb = new StringBuilder(String.valueOf(trackTypeString).length() + 2 + String.valueOf(logString).length());
        sb.append(trackTypeString);
        sb.append(", ");
        sb.append(logString);
        logd(eventTime, "decoderInputFormatChanged", sb.toString());
    }

    public void onDecoderDisabled(AnalyticsListener.EventTime eventTime, int i, DecoderCounters decoderCounters) {
        logd(eventTime, "decoderDisabled", getTrackTypeString(i));
    }

    public void onAudioUnderrun(AnalyticsListener.EventTime eventTime, int i, long j, long j2) {
        StringBuilder sb = new StringBuilder(56);
        sb.append(i);
        sb.append(", ");
        sb.append(j);
        sb.append(", ");
        sb.append(j2);
        sb.append("]");
        loge(eventTime, "audioTrackUnderrun", sb.toString(), (Throwable) null);
    }

    public void onDroppedVideoFrames(AnalyticsListener.EventTime eventTime, int i, long j) {
        logd(eventTime, "droppedFrames", Integer.toString(i));
    }

    public void onVideoSizeChanged(AnalyticsListener.EventTime eventTime, int i, int i2, int i3, float f) {
        StringBuilder sb = new StringBuilder(24);
        sb.append(i);
        sb.append(", ");
        sb.append(i2);
        logd(eventTime, "videoSizeChanged", sb.toString());
    }

    public void onRenderedFirstFrame(AnalyticsListener.EventTime eventTime, Surface surface) {
        logd(eventTime, "renderedFirstFrame", String.valueOf(surface));
    }

    public void onMediaPeriodCreated(AnalyticsListener.EventTime eventTime) {
        logd(eventTime, "mediaPeriodCreated");
    }

    public void onMediaPeriodReleased(AnalyticsListener.EventTime eventTime) {
        logd(eventTime, "mediaPeriodReleased");
    }

    public void onLoadError(AnalyticsListener.EventTime eventTime, MediaSourceEventListener.LoadEventInfo loadEventInfo, MediaSourceEventListener.MediaLoadData mediaLoadData, IOException iOException, boolean z) {
        printInternalError(eventTime, "loadError", iOException);
    }

    public void onReadingStarted(AnalyticsListener.EventTime eventTime) {
        logd(eventTime, "mediaPeriodReadingStarted");
    }

    public void onSurfaceSizeChanged(AnalyticsListener.EventTime eventTime, int i, int i2) {
        StringBuilder sb = new StringBuilder(24);
        sb.append(i);
        sb.append(", ");
        sb.append(i2);
        logd(eventTime, "surfaceSizeChanged", sb.toString());
    }

    public void onUpstreamDiscarded(AnalyticsListener.EventTime eventTime, MediaSourceEventListener.MediaLoadData mediaLoadData) {
        logd(eventTime, "upstreamDiscarded", Format.toLogString(mediaLoadData.trackFormat));
    }

    public void onDownstreamFormatChanged(AnalyticsListener.EventTime eventTime, MediaSourceEventListener.MediaLoadData mediaLoadData) {
        logd(eventTime, "downstreamFormatChanged", Format.toLogString(mediaLoadData.trackFormat));
    }

    public void onDrmSessionAcquired(AnalyticsListener.EventTime eventTime) {
        logd(eventTime, "drmSessionAcquired");
    }

    public void onDrmSessionManagerError(AnalyticsListener.EventTime eventTime, Exception exc) {
        printInternalError(eventTime, "drmSessionManagerError", exc);
    }

    public void onDrmKeysRestored(AnalyticsListener.EventTime eventTime) {
        logd(eventTime, "drmKeysRestored");
    }

    public void onDrmKeysRemoved(AnalyticsListener.EventTime eventTime) {
        logd(eventTime, "drmKeysRemoved");
    }

    public void onDrmKeysLoaded(AnalyticsListener.EventTime eventTime) {
        logd(eventTime, "drmKeysLoaded");
    }

    public void onDrmSessionReleased(AnalyticsListener.EventTime eventTime) {
        logd(eventTime, "drmSessionReleased");
    }

    /* access modifiers changed from: protected */
    public void logd(String str) {
        Log.m6204d(this.tag, str);
    }

    /* access modifiers changed from: protected */
    public void loge(String str, Throwable th) {
        Log.m6207e(this.tag, str, th);
    }

    private void logd(AnalyticsListener.EventTime eventTime, String str) {
        logd(getEventString(eventTime, str));
    }

    private void logd(AnalyticsListener.EventTime eventTime, String str, String str2) {
        logd(getEventString(eventTime, str, str2));
    }

    private void loge(AnalyticsListener.EventTime eventTime, String str, Throwable th) {
        loge(getEventString(eventTime, str), th);
    }

    private void loge(AnalyticsListener.EventTime eventTime, String str, String str2, Throwable th) {
        loge(getEventString(eventTime, str, str2), th);
    }

    private void printInternalError(AnalyticsListener.EventTime eventTime, String str, Exception exc) {
        loge(eventTime, "internalError", str, exc);
    }

    private void printMetadata(Metadata metadata, String str) {
        for (int i = 0; i < metadata.length(); i++) {
            String valueOf = String.valueOf(metadata.get(i));
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + String.valueOf(valueOf).length());
            sb.append(str);
            sb.append(valueOf);
            logd(sb.toString());
        }
    }

    private String getEventString(AnalyticsListener.EventTime eventTime, String str) {
        String eventTimeString = getEventTimeString(eventTime);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 3 + String.valueOf(eventTimeString).length());
        sb.append(str);
        sb.append(" [");
        sb.append(eventTimeString);
        sb.append("]");
        return sb.toString();
    }

    private String getEventString(AnalyticsListener.EventTime eventTime, String str, String str2) {
        String eventTimeString = getEventTimeString(eventTime);
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 5 + String.valueOf(eventTimeString).length() + String.valueOf(str2).length());
        sb.append(str);
        sb.append(" [");
        sb.append(eventTimeString);
        sb.append(", ");
        sb.append(str2);
        sb.append("]");
        return sb.toString();
    }

    private String getEventTimeString(AnalyticsListener.EventTime eventTime) {
        int i = eventTime.windowIndex;
        StringBuilder sb = new StringBuilder(18);
        sb.append("window=");
        sb.append(i);
        String sb2 = sb.toString();
        if (eventTime.mediaPeriodId != null) {
            String valueOf = String.valueOf(sb2);
            int indexOfPeriod = eventTime.timeline.getIndexOfPeriod(eventTime.mediaPeriodId.periodUid);
            StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf).length() + 20);
            sb3.append(valueOf);
            sb3.append(", period=");
            sb3.append(indexOfPeriod);
            sb2 = sb3.toString();
            if (eventTime.mediaPeriodId.isAd()) {
                String valueOf2 = String.valueOf(sb2);
                int i2 = eventTime.mediaPeriodId.adGroupIndex;
                StringBuilder sb4 = new StringBuilder(String.valueOf(valueOf2).length() + 21);
                sb4.append(valueOf2);
                sb4.append(", adGroup=");
                sb4.append(i2);
                String valueOf3 = String.valueOf(sb4.toString());
                int i3 = eventTime.mediaPeriodId.adIndexInAdGroup;
                StringBuilder sb5 = new StringBuilder(String.valueOf(valueOf3).length() + 16);
                sb5.append(valueOf3);
                sb5.append(", ad=");
                sb5.append(i3);
                sb2 = sb5.toString();
            }
        }
        String timeString = getTimeString(eventTime.realtimeMs - this.startTimeMs);
        String timeString2 = getTimeString(eventTime.currentPlaybackPositionMs);
        StringBuilder sb6 = new StringBuilder(String.valueOf(timeString).length() + 4 + String.valueOf(timeString2).length() + String.valueOf(sb2).length());
        sb6.append(timeString);
        sb6.append(", ");
        sb6.append(timeString2);
        sb6.append(", ");
        sb6.append(sb2);
        return sb6.toString();
    }

    private static String getTimeString(long j) {
        return j == -9223372036854775807L ? "?" : TIME_FORMAT.format((double) (((float) j) / 1000.0f));
    }

    private static String getTrackStatusString(TrackSelection trackSelection, TrackGroup trackGroup, int i) {
        return getTrackStatusString((trackSelection == null || trackSelection.getTrackGroup() != trackGroup || trackSelection.indexOf(i) == -1) ? false : true);
    }

    private static String getTrackTypeString(int i) {
        switch (i) {
            case 0:
                return RewardedVideo.VIDEO_MODE_DEFAULT;
            case 1:
                return "audio";
            case 2:
                return "video";
            case 3:
                return "text";
            case 4:
                return "metadata";
            case 5:
                return "camera motion";
            case 6:
                return "none";
            default:
                if (i < 10000) {
                    return "?";
                }
                StringBuilder sb = new StringBuilder(20);
                sb.append("custom (");
                sb.append(i);
                sb.append(")");
                return sb.toString();
        }
    }
}

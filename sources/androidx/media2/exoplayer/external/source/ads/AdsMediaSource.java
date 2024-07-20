package androidx.media2.exoplayer.external.source.ads;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import androidx.media2.exoplayer.external.Timeline;
import androidx.media2.exoplayer.external.source.CompositeMediaSource;
import androidx.media2.exoplayer.external.source.DeferredMediaPeriod;
import androidx.media2.exoplayer.external.source.MediaPeriod;
import androidx.media2.exoplayer.external.source.MediaSource;
import androidx.media2.exoplayer.external.source.MediaSourceFactory;
import androidx.media2.exoplayer.external.source.ProgressiveMediaSource;
import androidx.media2.exoplayer.external.source.ads.AdsLoader;
import androidx.media2.exoplayer.external.upstream.Allocator;
import androidx.media2.exoplayer.external.upstream.DataSource;
import androidx.media2.exoplayer.external.upstream.DataSpec;
import androidx.media2.exoplayer.external.upstream.TransferListener;
import androidx.media2.exoplayer.external.util.Assertions;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class AdsMediaSource extends CompositeMediaSource<MediaSource.MediaPeriodId> {
    private static final MediaSource.MediaPeriodId DUMMY_CONTENT_MEDIA_PERIOD_ID = new MediaSource.MediaPeriodId(new Object());
    private MediaSource[][] adGroupMediaSources;
    private Timeline[][] adGroupTimelines;
    private final MediaSourceFactory adMediaSourceFactory;
    private AdPlaybackState adPlaybackState;
    private final AdsLoader.AdViewProvider adViewProvider;
    /* access modifiers changed from: private */
    public final AdsLoader adsLoader;
    private ComponentListener componentListener;
    private Object contentManifest;
    private final MediaSource contentMediaSource;
    private Timeline contentTimeline;
    private final Map<MediaSource, List<DeferredMediaPeriod>> deferredMediaPeriodByAdMediaSource;
    /* access modifiers changed from: private */
    public final Handler mainHandler;
    private final Timeline.Period period;

    public static final class AdLoadException extends IOException {
        public static final int TYPE_AD = 0;
        public static final int TYPE_AD_GROUP = 1;
        public static final int TYPE_ALL_ADS = 2;
        public static final int TYPE_UNEXPECTED = 3;
        public final int type;

        @Documented
        @Retention(RetentionPolicy.SOURCE)
        public @interface Type {
        }

        public static AdLoadException createForAd(Exception exc) {
            return new AdLoadException(0, exc);
        }

        public static AdLoadException createForAdGroup(Exception exc, int i) {
            StringBuilder sb = new StringBuilder(35);
            sb.append("Failed to load ad group ");
            sb.append(i);
            return new AdLoadException(1, new IOException(sb.toString(), exc));
        }

        public static AdLoadException createForAllAds(Exception exc) {
            return new AdLoadException(2, exc);
        }

        public static AdLoadException createForUnexpected(RuntimeException runtimeException) {
            return new AdLoadException(3, runtimeException);
        }

        private AdLoadException(int i, Exception exc) {
            super(exc);
            this.type = i;
        }

        public RuntimeException getRuntimeExceptionForUnexpected() {
            Assertions.checkState(this.type == 3);
            return (RuntimeException) Assertions.checkNotNull(getCause());
        }
    }

    public AdsMediaSource(MediaSource mediaSource, DataSource.Factory factory, AdsLoader adsLoader2, AdsLoader.AdViewProvider adViewProvider2) {
        this(mediaSource, (MediaSourceFactory) new ProgressiveMediaSource.Factory(factory), adsLoader2, adViewProvider2);
    }

    public AdsMediaSource(MediaSource mediaSource, MediaSourceFactory mediaSourceFactory, AdsLoader adsLoader2, AdsLoader.AdViewProvider adViewProvider2) {
        this.contentMediaSource = mediaSource;
        this.adMediaSourceFactory = mediaSourceFactory;
        this.adsLoader = adsLoader2;
        this.adViewProvider = adViewProvider2;
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.deferredMediaPeriodByAdMediaSource = new HashMap();
        this.period = new Timeline.Period();
        this.adGroupMediaSources = new MediaSource[0][];
        this.adGroupTimelines = new Timeline[0][];
        adsLoader2.setSupportedContentTypes(mediaSourceFactory.getSupportedTypes());
    }

    public Object getTag() {
        return this.contentMediaSource.getTag();
    }

    public void prepareSourceInternal(TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        ComponentListener componentListener2 = new ComponentListener();
        this.componentListener = componentListener2;
        prepareChildSource(DUMMY_CONTENT_MEDIA_PERIOD_ID, this.contentMediaSource);
        this.mainHandler.post(new AdsMediaSource$$Lambda$0(this, componentListener2));
    }

    /* access modifiers changed from: package-private */
    public final /* synthetic */ void lambda$prepareSourceInternal$0$AdsMediaSource(ComponentListener componentListener2) {
        this.adsLoader.start(componentListener2, this.adViewProvider);
    }

    public MediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        MediaSource.MediaPeriodId mediaPeriodId2 = mediaPeriodId;
        AdPlaybackState adPlaybackState2 = (AdPlaybackState) Assertions.checkNotNull(this.adPlaybackState);
        if (adPlaybackState2.adGroupCount <= 0 || !mediaPeriodId.isAd()) {
            DeferredMediaPeriod deferredMediaPeriod = new DeferredMediaPeriod(this.contentMediaSource, mediaPeriodId, allocator, j);
            deferredMediaPeriod.createPeriod(mediaPeriodId);
            return deferredMediaPeriod;
        }
        int i = mediaPeriodId2.adGroupIndex;
        int i2 = mediaPeriodId2.adIndexInAdGroup;
        Uri uri = (Uri) Assertions.checkNotNull(adPlaybackState2.adGroups[i].uris[i2]);
        MediaSource[][] mediaSourceArr = this.adGroupMediaSources;
        if (mediaSourceArr[i].length <= i2) {
            int i3 = i2 + 1;
            mediaSourceArr[i] = (MediaSource[]) Arrays.copyOf(mediaSourceArr[i], i3);
            Timeline[][] timelineArr = this.adGroupTimelines;
            timelineArr[i] = (Timeline[]) Arrays.copyOf(timelineArr[i], i3);
        }
        MediaSource mediaSource = this.adGroupMediaSources[i][i2];
        if (mediaSource == null) {
            mediaSource = this.adMediaSourceFactory.createMediaSource(uri);
            this.adGroupMediaSources[i][i2] = mediaSource;
            this.deferredMediaPeriodByAdMediaSource.put(mediaSource, new ArrayList());
            prepareChildSource(mediaPeriodId, mediaSource);
        }
        MediaSource mediaSource2 = mediaSource;
        DeferredMediaPeriod deferredMediaPeriod2 = new DeferredMediaPeriod(mediaSource2, mediaPeriodId, allocator, j);
        deferredMediaPeriod2.setPrepareErrorListener(new AdPrepareErrorListener(uri, i, i2));
        List list = this.deferredMediaPeriodByAdMediaSource.get(mediaSource2);
        if (list == null) {
            deferredMediaPeriod2.createPeriod(new MediaSource.MediaPeriodId(((Timeline) Assertions.checkNotNull(this.adGroupTimelines[i][i2])).getUidOfPeriod(0), mediaPeriodId2.windowSequenceNumber));
        } else {
            list.add(deferredMediaPeriod2);
        }
        return deferredMediaPeriod2;
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        DeferredMediaPeriod deferredMediaPeriod = (DeferredMediaPeriod) mediaPeriod;
        List list = this.deferredMediaPeriodByAdMediaSource.get(deferredMediaPeriod.mediaSource);
        if (list != null) {
            list.remove(deferredMediaPeriod);
        }
        deferredMediaPeriod.releasePeriod();
    }

    public void releaseSourceInternal() {
        super.releaseSourceInternal();
        ((ComponentListener) Assertions.checkNotNull(this.componentListener)).release();
        this.componentListener = null;
        this.deferredMediaPeriodByAdMediaSource.clear();
        this.contentTimeline = null;
        this.contentManifest = null;
        this.adPlaybackState = null;
        this.adGroupMediaSources = new MediaSource[0][];
        this.adGroupTimelines = new Timeline[0][];
        Handler handler = this.mainHandler;
        AdsLoader adsLoader2 = this.adsLoader;
        adsLoader2.getClass();
        handler.post(AdsMediaSource$$Lambda$1.get$Lambda(adsLoader2));
    }

    /* access modifiers changed from: protected */
    public void onChildSourceInfoRefreshed(MediaSource.MediaPeriodId mediaPeriodId, MediaSource mediaSource, Timeline timeline, Object obj) {
        if (mediaPeriodId.isAd()) {
            onAdSourceInfoRefreshed(mediaSource, mediaPeriodId.adGroupIndex, mediaPeriodId.adIndexInAdGroup, timeline);
        } else {
            onContentSourceInfoRefreshed(timeline, obj);
        }
    }

    /* access modifiers changed from: protected */
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(MediaSource.MediaPeriodId mediaPeriodId, MediaSource.MediaPeriodId mediaPeriodId2) {
        return mediaPeriodId.isAd() ? mediaPeriodId : mediaPeriodId2;
    }

    /* access modifiers changed from: private */
    public void onAdPlaybackState(AdPlaybackState adPlaybackState2) {
        if (this.adPlaybackState == null) {
            MediaSource[][] mediaSourceArr = new MediaSource[adPlaybackState2.adGroupCount][];
            this.adGroupMediaSources = mediaSourceArr;
            Arrays.fill(mediaSourceArr, new MediaSource[0]);
            Timeline[][] timelineArr = new Timeline[adPlaybackState2.adGroupCount][];
            this.adGroupTimelines = timelineArr;
            Arrays.fill(timelineArr, new Timeline[0]);
        }
        this.adPlaybackState = adPlaybackState2;
        maybeUpdateSourceInfo();
    }

    private void onContentSourceInfoRefreshed(Timeline timeline, Object obj) {
        boolean z = true;
        if (timeline.getPeriodCount() != 1) {
            z = false;
        }
        Assertions.checkArgument(z);
        this.contentTimeline = timeline;
        this.contentManifest = obj;
        maybeUpdateSourceInfo();
    }

    private void onAdSourceInfoRefreshed(MediaSource mediaSource, int i, int i2, Timeline timeline) {
        boolean z = true;
        if (timeline.getPeriodCount() != 1) {
            z = false;
        }
        Assertions.checkArgument(z);
        this.adGroupTimelines[i][i2] = timeline;
        List remove = this.deferredMediaPeriodByAdMediaSource.remove(mediaSource);
        if (remove != null) {
            Object uidOfPeriod = timeline.getUidOfPeriod(0);
            for (int i3 = 0; i3 < remove.size(); i3++) {
                DeferredMediaPeriod deferredMediaPeriod = (DeferredMediaPeriod) remove.get(i3);
                deferredMediaPeriod.createPeriod(new MediaSource.MediaPeriodId(uidOfPeriod, deferredMediaPeriod.f4096id.windowSequenceNumber));
            }
        }
        maybeUpdateSourceInfo();
    }

    private void maybeUpdateSourceInfo() {
        Timeline timeline = this.contentTimeline;
        AdPlaybackState adPlaybackState2 = this.adPlaybackState;
        if (adPlaybackState2 != null && timeline != null) {
            AdPlaybackState withAdDurationsUs = adPlaybackState2.withAdDurationsUs(getAdDurations(this.adGroupTimelines, this.period));
            this.adPlaybackState = withAdDurationsUs;
            if (withAdDurationsUs.adGroupCount != 0) {
                timeline = new SinglePeriodAdTimeline(timeline, this.adPlaybackState);
            }
            refreshSourceInfo(timeline, this.contentManifest);
        }
    }

    private static long[][] getAdDurations(Timeline[][] timelineArr, Timeline.Period period2) {
        long j;
        long[][] jArr = new long[timelineArr.length][];
        for (int i = 0; i < timelineArr.length; i++) {
            jArr[i] = new long[timelineArr[i].length];
            for (int i2 = 0; i2 < timelineArr[i].length; i2++) {
                long[] jArr2 = jArr[i];
                if (timelineArr[i][i2] == null) {
                    j = -9223372036854775807L;
                } else {
                    j = timelineArr[i][i2].getPeriod(0, period2).getDurationUs();
                }
                jArr2[i2] = j;
            }
        }
        return jArr;
    }

    private final class ComponentListener implements AdsLoader.EventListener {
        private final Handler playerHandler = new Handler();
        private volatile boolean released;

        public void onAdClicked() {
            AdsLoader$EventListener$$CC.onAdClicked$$dflt$$(this);
        }

        public void onAdTapped() {
            AdsLoader$EventListener$$CC.onAdTapped$$dflt$$(this);
        }

        public ComponentListener() {
        }

        public void release() {
            this.released = true;
            this.playerHandler.removeCallbacksAndMessages((Object) null);
        }

        public void onAdPlaybackState(AdPlaybackState adPlaybackState) {
            if (!this.released) {
                this.playerHandler.post(new AdsMediaSource$ComponentListener$$Lambda$0(this, adPlaybackState));
            }
        }

        /* access modifiers changed from: package-private */
        public final /* synthetic */ void lambda$onAdPlaybackState$0$AdsMediaSource$ComponentListener(AdPlaybackState adPlaybackState) {
            if (!this.released) {
                AdsMediaSource.this.onAdPlaybackState(adPlaybackState);
            }
        }

        public void onAdLoadError(AdLoadException adLoadException, DataSpec dataSpec) {
            if (!this.released) {
                AdsMediaSource.this.createEventDispatcher((MediaSource.MediaPeriodId) null).loadError(dataSpec, dataSpec.uri, Collections.emptyMap(), 6, -1, 0, 0, adLoadException, true);
            }
        }
    }

    private final class AdPrepareErrorListener implements DeferredMediaPeriod.PrepareErrorListener {
        private final int adGroupIndex;
        private final int adIndexInAdGroup;
        private final Uri adUri;

        public AdPrepareErrorListener(Uri uri, int i, int i2) {
            this.adUri = uri;
            this.adGroupIndex = i;
            this.adIndexInAdGroup = i2;
        }

        public void onPrepareError(MediaSource.MediaPeriodId mediaPeriodId, IOException iOException) {
            AdsMediaSource.this.createEventDispatcher(mediaPeriodId).loadError(new DataSpec(this.adUri), this.adUri, Collections.emptyMap(), 6, -1, 0, 0, AdLoadException.createForAd(iOException), true);
            AdsMediaSource.this.mainHandler.post(new AdsMediaSource$AdPrepareErrorListener$$Lambda$0(this, iOException));
        }

        /* access modifiers changed from: package-private */
        public final /* synthetic */ void lambda$onPrepareError$0$AdsMediaSource$AdPrepareErrorListener(IOException iOException) {
            AdsMediaSource.this.adsLoader.handlePrepareError(this.adGroupIndex, this.adIndexInAdGroup, iOException);
        }
    }
}

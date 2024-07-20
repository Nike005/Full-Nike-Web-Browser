package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;

public final class MaskingMediaSource extends CompositeMediaSource<Void> {
    private boolean hasStartedPreparing;
    private boolean isPrepared;
    private final MediaSource mediaSource;
    private final Timeline.Period period = new Timeline.Period();
    private MaskingTimeline timeline;
    private MaskingMediaPeriod unpreparedMaskingMediaPeriod;
    private MediaSourceEventListener.EventDispatcher unpreparedMaskingMediaPeriodEventDispatcher;
    private final boolean useLazyPreparation;
    private final Timeline.Window window = new Timeline.Window();

    public void maybeThrowSourceInfoRefreshError() throws IOException {
    }

    public MaskingMediaSource(MediaSource mediaSource2, boolean z) {
        this.mediaSource = mediaSource2;
        this.useLazyPreparation = z;
        this.timeline = MaskingTimeline.createWithDummyTimeline(mediaSource2.getTag());
    }

    public Timeline getTimeline() {
        return this.timeline;
    }

    public void prepareSourceInternal(TransferListener transferListener) {
        super.prepareSourceInternal(transferListener);
        if (!this.useLazyPreparation) {
            this.hasStartedPreparing = true;
            prepareChildSource(null, this.mediaSource);
        }
    }

    public Object getTag() {
        return this.mediaSource.getTag();
    }

    public MaskingMediaPeriod createPeriod(MediaSource.MediaPeriodId mediaPeriodId, Allocator allocator, long j) {
        MaskingMediaPeriod maskingMediaPeriod = new MaskingMediaPeriod(this.mediaSource, mediaPeriodId, allocator, j);
        if (this.isPrepared) {
            maskingMediaPeriod.createPeriod(mediaPeriodId.copyWithPeriodUid(getInternalPeriodUid(mediaPeriodId.periodUid)));
        } else {
            this.unpreparedMaskingMediaPeriod = maskingMediaPeriod;
            MediaSourceEventListener.EventDispatcher createEventDispatcher = createEventDispatcher(0, mediaPeriodId, 0);
            this.unpreparedMaskingMediaPeriodEventDispatcher = createEventDispatcher;
            createEventDispatcher.mediaPeriodCreated();
            if (!this.hasStartedPreparing) {
                this.hasStartedPreparing = true;
                prepareChildSource(null, this.mediaSource);
            }
        }
        return maskingMediaPeriod;
    }

    public void releasePeriod(MediaPeriod mediaPeriod) {
        ((MaskingMediaPeriod) mediaPeriod).releasePeriod();
        if (mediaPeriod == this.unpreparedMaskingMediaPeriod) {
            ((MediaSourceEventListener.EventDispatcher) Assertions.checkNotNull(this.unpreparedMaskingMediaPeriodEventDispatcher)).mediaPeriodReleased();
            this.unpreparedMaskingMediaPeriodEventDispatcher = null;
            this.unpreparedMaskingMediaPeriod = null;
        }
    }

    public void releaseSourceInternal() {
        this.isPrepared = false;
        this.hasStartedPreparing = false;
        super.releaseSourceInternal();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x005d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onChildSourceInfoRefreshed(java.lang.Void r12, com.google.android.exoplayer2.source.MediaSource r13, com.google.android.exoplayer2.Timeline r14) {
        /*
            r11 = this;
            boolean r12 = r11.isPrepared
            if (r12 == 0) goto L_0x000d
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r12 = r11.timeline
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r12 = r12.cloneWithUpdatedTimeline(r14)
            r11.timeline = r12
            goto L_0x0071
        L_0x000d:
            boolean r12 = r14.isEmpty()
            if (r12 == 0) goto L_0x001e
            java.lang.Object r12 = com.google.android.exoplayer2.Timeline.Window.SINGLE_WINDOW_UID
            java.lang.Object r13 = com.google.android.exoplayer2.source.MaskingMediaSource.MaskingTimeline.DUMMY_EXTERNAL_PERIOD_UID
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r12 = com.google.android.exoplayer2.source.MaskingMediaSource.MaskingTimeline.createWithRealTimeline(r14, r12, r13)
            r11.timeline = r12
            goto L_0x0071
        L_0x001e:
            r12 = 0
            com.google.android.exoplayer2.Timeline$Window r13 = r11.window
            r14.getWindow(r12, r13)
            com.google.android.exoplayer2.Timeline$Window r12 = r11.window
            long r12 = r12.getDefaultPositionUs()
            com.google.android.exoplayer2.source.MaskingMediaPeriod r0 = r11.unpreparedMaskingMediaPeriod
            if (r0 == 0) goto L_0x003a
            long r0 = r0.getPreparePositionUs()
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x003a
            r9 = r0
            goto L_0x003b
        L_0x003a:
            r9 = r12
        L_0x003b:
            com.google.android.exoplayer2.Timeline$Window r12 = r11.window
            java.lang.Object r12 = r12.uid
            com.google.android.exoplayer2.Timeline$Window r6 = r11.window
            com.google.android.exoplayer2.Timeline$Period r7 = r11.period
            r8 = 0
            r5 = r14
            android.util.Pair r13 = r5.getPeriodPosition(r6, r7, r8, r9)
            java.lang.Object r0 = r13.first
            java.lang.Object r13 = r13.second
            java.lang.Long r13 = (java.lang.Long) r13
            long r1 = r13.longValue()
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r12 = com.google.android.exoplayer2.source.MaskingMediaSource.MaskingTimeline.createWithRealTimeline(r14, r12, r0)
            r11.timeline = r12
            com.google.android.exoplayer2.source.MaskingMediaPeriod r12 = r11.unpreparedMaskingMediaPeriod
            if (r12 == 0) goto L_0x0071
            r12.overridePreparePositionUs(r1)
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r13 = r12.f5075id
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r14 = r12.f5075id
            java.lang.Object r14 = r14.periodUid
            java.lang.Object r14 = r11.getInternalPeriodUid(r14)
            com.google.android.exoplayer2.source.MediaSource$MediaPeriodId r13 = r13.copyWithPeriodUid(r14)
            r12.createPeriod(r13)
        L_0x0071:
            r12 = 1
            r11.isPrepared = r12
            com.google.android.exoplayer2.source.MaskingMediaSource$MaskingTimeline r12 = r11.timeline
            r11.refreshSourceInfo(r12)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.MaskingMediaSource.onChildSourceInfoRefreshed(java.lang.Void, com.google.android.exoplayer2.source.MediaSource, com.google.android.exoplayer2.Timeline):void");
    }

    /* access modifiers changed from: protected */
    public MediaSource.MediaPeriodId getMediaPeriodIdForChildMediaPeriodId(Void voidR, MediaSource.MediaPeriodId mediaPeriodId) {
        return mediaPeriodId.copyWithPeriodUid(getExternalPeriodUid(mediaPeriodId.periodUid));
    }

    /* access modifiers changed from: protected */
    public boolean shouldDispatchCreateOrReleaseEvent(MediaSource.MediaPeriodId mediaPeriodId) {
        MaskingMediaPeriod maskingMediaPeriod = this.unpreparedMaskingMediaPeriod;
        return maskingMediaPeriod == null || !mediaPeriodId.equals(maskingMediaPeriod.f5075id);
    }

    private Object getInternalPeriodUid(Object obj) {
        return obj.equals(MaskingTimeline.DUMMY_EXTERNAL_PERIOD_UID) ? this.timeline.replacedInternalPeriodUid : obj;
    }

    private Object getExternalPeriodUid(Object obj) {
        return this.timeline.replacedInternalPeriodUid.equals(obj) ? MaskingTimeline.DUMMY_EXTERNAL_PERIOD_UID : obj;
    }

    private static final class MaskingTimeline extends ForwardingTimeline {
        public static final Object DUMMY_EXTERNAL_PERIOD_UID = new Object();
        /* access modifiers changed from: private */
        public final Object replacedInternalPeriodUid;
        private final Object replacedInternalWindowUid;

        public static MaskingTimeline createWithDummyTimeline(Object obj) {
            return new MaskingTimeline(new DummyTimeline(obj), Timeline.Window.SINGLE_WINDOW_UID, DUMMY_EXTERNAL_PERIOD_UID);
        }

        public static MaskingTimeline createWithRealTimeline(Timeline timeline, Object obj, Object obj2) {
            return new MaskingTimeline(timeline, obj, obj2);
        }

        private MaskingTimeline(Timeline timeline, Object obj, Object obj2) {
            super(timeline);
            this.replacedInternalWindowUid = obj;
            this.replacedInternalPeriodUid = obj2;
        }

        public MaskingTimeline cloneWithUpdatedTimeline(Timeline timeline) {
            return new MaskingTimeline(timeline, this.replacedInternalWindowUid, this.replacedInternalPeriodUid);
        }

        public Timeline getTimeline() {
            return this.timeline;
        }

        public Timeline.Window getWindow(int i, Timeline.Window window, long j) {
            this.timeline.getWindow(i, window, j);
            if (Util.areEqual(window.uid, this.replacedInternalWindowUid)) {
                window.uid = Timeline.Window.SINGLE_WINDOW_UID;
            }
            return window;
        }

        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            this.timeline.getPeriod(i, period, z);
            if (Util.areEqual(period.uid, this.replacedInternalPeriodUid)) {
                period.uid = DUMMY_EXTERNAL_PERIOD_UID;
            }
            return period;
        }

        public int getIndexOfPeriod(Object obj) {
            Timeline timeline = this.timeline;
            if (DUMMY_EXTERNAL_PERIOD_UID.equals(obj)) {
                obj = this.replacedInternalPeriodUid;
            }
            return timeline.getIndexOfPeriod(obj);
        }

        public Object getUidOfPeriod(int i) {
            Object uidOfPeriod = this.timeline.getUidOfPeriod(i);
            return Util.areEqual(uidOfPeriod, this.replacedInternalPeriodUid) ? DUMMY_EXTERNAL_PERIOD_UID : uidOfPeriod;
        }
    }

    private static final class DummyTimeline extends Timeline {
        private final Object tag;

        public int getPeriodCount() {
            return 1;
        }

        public int getWindowCount() {
            return 1;
        }

        public DummyTimeline(Object obj) {
            this.tag = obj;
        }

        public Timeline.Window getWindow(int i, Timeline.Window window, long j) {
            return window.set(Timeline.Window.SINGLE_WINDOW_UID, this.tag, (Object) null, -9223372036854775807L, -9223372036854775807L, false, true, false, 0, -9223372036854775807L, 0, 0, 0);
        }

        public Timeline.Period getPeriod(int i, Timeline.Period period, boolean z) {
            return period.set(0, MaskingTimeline.DUMMY_EXTERNAL_PERIOD_UID, 0, -9223372036854775807L, 0);
        }

        public int getIndexOfPeriod(Object obj) {
            return obj == MaskingTimeline.DUMMY_EXTERNAL_PERIOD_UID ? 0 : -1;
        }

        public Object getUidOfPeriod(int i) {
            return MaskingTimeline.DUMMY_EXTERNAL_PERIOD_UID;
        }
    }
}

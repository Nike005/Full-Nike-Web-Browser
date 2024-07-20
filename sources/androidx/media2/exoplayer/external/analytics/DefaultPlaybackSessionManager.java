package androidx.media2.exoplayer.external.analytics;

import android.util.Base64;
import androidx.media2.exoplayer.external.Timeline;
import androidx.media2.exoplayer.external.analytics.AnalyticsListener;
import androidx.media2.exoplayer.external.analytics.PlaybackSessionManager;
import androidx.media2.exoplayer.external.source.MediaSource;
import androidx.media2.exoplayer.external.util.Assertions;
import androidx.media2.exoplayer.external.util.Util;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

public final class DefaultPlaybackSessionManager implements PlaybackSessionManager {
    private static final Random RANDOM = new Random();
    private static final int SESSION_ID_LENGTH = 12;
    private String activeSessionId;
    private MediaSource.MediaPeriodId currentMediaPeriodId;
    private Timeline currentTimeline = Timeline.EMPTY;
    private PlaybackSessionManager.Listener listener;
    /* access modifiers changed from: private */
    public final Timeline.Period period = new Timeline.Period();
    private final HashMap<String, SessionDescriptor> sessions = new HashMap<>();
    /* access modifiers changed from: private */
    public final Timeline.Window window = new Timeline.Window();

    public void setListener(PlaybackSessionManager.Listener listener2) {
        this.listener = listener2;
    }

    public synchronized String getSessionForMediaPeriodId(Timeline timeline, MediaSource.MediaPeriodId mediaPeriodId) {
        return getOrAddSession(timeline.getPeriodByUid(mediaPeriodId.periodUid, this.period).windowIndex, mediaPeriodId).sessionId;
    }

    public synchronized boolean belongsToSession(AnalyticsListener.EventTime eventTime, String str) {
        SessionDescriptor sessionDescriptor = this.sessions.get(str);
        if (sessionDescriptor == null) {
            return false;
        }
        sessionDescriptor.maybeSetWindowSequenceNumber(eventTime.windowIndex, eventTime.mediaPeriodId);
        return sessionDescriptor.belongsToSession(eventTime.windowIndex, eventTime.mediaPeriodId);
    }

    public synchronized void updateSessions(AnalyticsListener.EventTime eventTime) {
        if (!((eventTime.mediaPeriodId == null || this.currentMediaPeriodId == null || eventTime.mediaPeriodId.windowSequenceNumber >= this.currentMediaPeriodId.windowSequenceNumber) ? false : true)) {
            SessionDescriptor orAddSession = getOrAddSession(eventTime.windowIndex, eventTime.mediaPeriodId);
            if (!orAddSession.isCreated) {
                boolean unused = orAddSession.isCreated = true;
                ((PlaybackSessionManager.Listener) Assertions.checkNotNull(this.listener)).onSessionCreated(eventTime, orAddSession.sessionId);
                if (this.activeSessionId == null) {
                    updateActiveSession(eventTime, orAddSession);
                }
            }
        }
    }

    public synchronized void handleTimelineUpdate(AnalyticsListener.EventTime eventTime) {
        Assertions.checkNotNull(this.listener);
        Timeline timeline = this.currentTimeline;
        this.currentTimeline = eventTime.timeline;
        Iterator<SessionDescriptor> it = this.sessions.values().iterator();
        while (it.hasNext()) {
            SessionDescriptor next = it.next();
            if (!next.tryResolvingToNewTimeline(timeline, this.currentTimeline)) {
                it.remove();
                if (next.isCreated) {
                    if (next.sessionId.equals(this.activeSessionId)) {
                        this.activeSessionId = null;
                    }
                    this.listener.onSessionFinished(eventTime, next.sessionId, false);
                }
            }
        }
        handlePositionDiscontinuity(eventTime, 4);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0021  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void handlePositionDiscontinuity(androidx.media2.exoplayer.external.analytics.AnalyticsListener.EventTime r7, int r8) {
        /*
            r6 = this;
            monitor-enter(r6)
            androidx.media2.exoplayer.external.analytics.PlaybackSessionManager$Listener r0 = r6.listener     // Catch:{ all -> 0x00bf }
            androidx.media2.exoplayer.external.util.Assertions.checkNotNull(r0)     // Catch:{ all -> 0x00bf }
            r0 = 0
            r1 = 1
            if (r8 == 0) goto L_0x0010
            r2 = 3
            if (r8 != r2) goto L_0x000e
            goto L_0x0010
        L_0x000e:
            r8 = 0
            goto L_0x0011
        L_0x0010:
            r8 = 1
        L_0x0011:
            java.util.HashMap<java.lang.String, androidx.media2.exoplayer.external.analytics.DefaultPlaybackSessionManager$SessionDescriptor> r2 = r6.sessions     // Catch:{ all -> 0x00bf }
            java.util.Collection r2 = r2.values()     // Catch:{ all -> 0x00bf }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x00bf }
        L_0x001b:
            boolean r3 = r2.hasNext()     // Catch:{ all -> 0x00bf }
            if (r3 == 0) goto L_0x0056
            java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x00bf }
            androidx.media2.exoplayer.external.analytics.DefaultPlaybackSessionManager$SessionDescriptor r3 = (androidx.media2.exoplayer.external.analytics.DefaultPlaybackSessionManager.SessionDescriptor) r3     // Catch:{ all -> 0x00bf }
            boolean r4 = r3.isFinishedAtEventTime(r7)     // Catch:{ all -> 0x00bf }
            if (r4 == 0) goto L_0x001b
            r2.remove()     // Catch:{ all -> 0x00bf }
            boolean r4 = r3.isCreated     // Catch:{ all -> 0x00bf }
            if (r4 == 0) goto L_0x001b
            java.lang.String r4 = r3.sessionId     // Catch:{ all -> 0x00bf }
            java.lang.String r5 = r6.activeSessionId     // Catch:{ all -> 0x00bf }
            boolean r4 = r4.equals(r5)     // Catch:{ all -> 0x00bf }
            if (r8 == 0) goto L_0x0046
            if (r4 == 0) goto L_0x0046
            r5 = 1
            goto L_0x0047
        L_0x0046:
            r5 = 0
        L_0x0047:
            if (r4 == 0) goto L_0x004c
            r4 = 0
            r6.activeSessionId = r4     // Catch:{ all -> 0x00bf }
        L_0x004c:
            androidx.media2.exoplayer.external.analytics.PlaybackSessionManager$Listener r4 = r6.listener     // Catch:{ all -> 0x00bf }
            java.lang.String r3 = r3.sessionId     // Catch:{ all -> 0x00bf }
            r4.onSessionFinished(r7, r3, r5)     // Catch:{ all -> 0x00bf }
            goto L_0x001b
        L_0x0056:
            int r8 = r7.windowIndex     // Catch:{ all -> 0x00bf }
            androidx.media2.exoplayer.external.source.MediaSource$MediaPeriodId r0 = r7.mediaPeriodId     // Catch:{ all -> 0x00bf }
            androidx.media2.exoplayer.external.analytics.DefaultPlaybackSessionManager$SessionDescriptor r8 = r6.getOrAddSession(r8, r0)     // Catch:{ all -> 0x00bf }
            androidx.media2.exoplayer.external.source.MediaSource$MediaPeriodId r0 = r7.mediaPeriodId     // Catch:{ all -> 0x00bf }
            if (r0 == 0) goto L_0x00ba
            androidx.media2.exoplayer.external.source.MediaSource$MediaPeriodId r0 = r7.mediaPeriodId     // Catch:{ all -> 0x00bf }
            boolean r0 = r0.isAd()     // Catch:{ all -> 0x00bf }
            if (r0 == 0) goto L_0x00ba
            androidx.media2.exoplayer.external.source.MediaSource$MediaPeriodId r0 = r6.currentMediaPeriodId     // Catch:{ all -> 0x00bf }
            if (r0 == 0) goto L_0x008e
            androidx.media2.exoplayer.external.source.MediaSource$MediaPeriodId r0 = r6.currentMediaPeriodId     // Catch:{ all -> 0x00bf }
            long r0 = r0.windowSequenceNumber     // Catch:{ all -> 0x00bf }
            androidx.media2.exoplayer.external.source.MediaSource$MediaPeriodId r2 = r7.mediaPeriodId     // Catch:{ all -> 0x00bf }
            long r2 = r2.windowSequenceNumber     // Catch:{ all -> 0x00bf }
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x008e
            androidx.media2.exoplayer.external.source.MediaSource$MediaPeriodId r0 = r6.currentMediaPeriodId     // Catch:{ all -> 0x00bf }
            int r0 = r0.adGroupIndex     // Catch:{ all -> 0x00bf }
            androidx.media2.exoplayer.external.source.MediaSource$MediaPeriodId r1 = r7.mediaPeriodId     // Catch:{ all -> 0x00bf }
            int r1 = r1.adGroupIndex     // Catch:{ all -> 0x00bf }
            if (r0 != r1) goto L_0x008e
            androidx.media2.exoplayer.external.source.MediaSource$MediaPeriodId r0 = r6.currentMediaPeriodId     // Catch:{ all -> 0x00bf }
            int r0 = r0.adIndexInAdGroup     // Catch:{ all -> 0x00bf }
            androidx.media2.exoplayer.external.source.MediaSource$MediaPeriodId r1 = r7.mediaPeriodId     // Catch:{ all -> 0x00bf }
            int r1 = r1.adIndexInAdGroup     // Catch:{ all -> 0x00bf }
            if (r0 == r1) goto L_0x00ba
        L_0x008e:
            androidx.media2.exoplayer.external.source.MediaSource$MediaPeriodId r0 = new androidx.media2.exoplayer.external.source.MediaSource$MediaPeriodId     // Catch:{ all -> 0x00bf }
            androidx.media2.exoplayer.external.source.MediaSource$MediaPeriodId r1 = r7.mediaPeriodId     // Catch:{ all -> 0x00bf }
            java.lang.Object r1 = r1.periodUid     // Catch:{ all -> 0x00bf }
            androidx.media2.exoplayer.external.source.MediaSource$MediaPeriodId r2 = r7.mediaPeriodId     // Catch:{ all -> 0x00bf }
            long r2 = r2.windowSequenceNumber     // Catch:{ all -> 0x00bf }
            r0.<init>(r1, r2)     // Catch:{ all -> 0x00bf }
            int r1 = r7.windowIndex     // Catch:{ all -> 0x00bf }
            androidx.media2.exoplayer.external.analytics.DefaultPlaybackSessionManager$SessionDescriptor r0 = r6.getOrAddSession(r1, r0)     // Catch:{ all -> 0x00bf }
            boolean r1 = r0.isCreated     // Catch:{ all -> 0x00bf }
            if (r1 == 0) goto L_0x00ba
            boolean r1 = r8.isCreated     // Catch:{ all -> 0x00bf }
            if (r1 == 0) goto L_0x00ba
            androidx.media2.exoplayer.external.analytics.PlaybackSessionManager$Listener r1 = r6.listener     // Catch:{ all -> 0x00bf }
            java.lang.String r0 = r0.sessionId     // Catch:{ all -> 0x00bf }
            java.lang.String r2 = r8.sessionId     // Catch:{ all -> 0x00bf }
            r1.onAdPlaybackStarted(r7, r0, r2)     // Catch:{ all -> 0x00bf }
        L_0x00ba:
            r6.updateActiveSession(r7, r8)     // Catch:{ all -> 0x00bf }
            monitor-exit(r6)
            return
        L_0x00bf:
            r7 = move-exception
            monitor-exit(r6)
            goto L_0x00c3
        L_0x00c2:
            throw r7
        L_0x00c3:
            goto L_0x00c2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.analytics.DefaultPlaybackSessionManager.handlePositionDiscontinuity(androidx.media2.exoplayer.external.analytics.AnalyticsListener$EventTime, int):void");
    }

    private SessionDescriptor getOrAddSession(int i, MediaSource.MediaPeriodId mediaPeriodId) {
        SessionDescriptor sessionDescriptor = null;
        long j = Long.MAX_VALUE;
        for (SessionDescriptor next : this.sessions.values()) {
            next.maybeSetWindowSequenceNumber(i, mediaPeriodId);
            if (next.belongsToSession(i, mediaPeriodId)) {
                long access$200 = next.windowSequenceNumber;
                if (access$200 == -1 || access$200 < j) {
                    sessionDescriptor = next;
                    j = access$200;
                } else if (!(access$200 != j || ((SessionDescriptor) Util.castNonNull(sessionDescriptor)).adMediaPeriodId == null || next.adMediaPeriodId == null)) {
                    sessionDescriptor = next;
                }
            }
        }
        if (sessionDescriptor != null) {
            return sessionDescriptor;
        }
        String generateSessionId = generateSessionId();
        SessionDescriptor sessionDescriptor2 = new SessionDescriptor(generateSessionId, i, mediaPeriodId);
        this.sessions.put(generateSessionId, sessionDescriptor2);
        return sessionDescriptor2;
    }

    @RequiresNonNull({"listener"})
    private void updateActiveSession(AnalyticsListener.EventTime eventTime, SessionDescriptor sessionDescriptor) {
        this.currentMediaPeriodId = eventTime.mediaPeriodId;
        if (sessionDescriptor.isCreated) {
            this.activeSessionId = sessionDescriptor.sessionId;
            if (!sessionDescriptor.isActive) {
                boolean unused = sessionDescriptor.isActive = true;
                this.listener.onSessionActive(eventTime, sessionDescriptor.sessionId);
            }
        }
    }

    private static String generateSessionId() {
        byte[] bArr = new byte[12];
        RANDOM.nextBytes(bArr);
        return Base64.encodeToString(bArr, 10);
    }

    private final class SessionDescriptor {
        /* access modifiers changed from: private */
        public MediaSource.MediaPeriodId adMediaPeriodId;
        /* access modifiers changed from: private */
        public boolean isActive;
        /* access modifiers changed from: private */
        public boolean isCreated;
        /* access modifiers changed from: private */
        public final String sessionId;
        private int windowIndex;
        /* access modifiers changed from: private */
        public long windowSequenceNumber;

        public SessionDescriptor(String str, int i, MediaSource.MediaPeriodId mediaPeriodId) {
            long j;
            this.sessionId = str;
            this.windowIndex = i;
            if (mediaPeriodId == null) {
                j = -1;
            } else {
                j = mediaPeriodId.windowSequenceNumber;
            }
            this.windowSequenceNumber = j;
            if (mediaPeriodId != null && mediaPeriodId.isAd()) {
                this.adMediaPeriodId = mediaPeriodId;
            }
        }

        public boolean tryResolvingToNewTimeline(Timeline timeline, Timeline timeline2) {
            int resolveWindowIndexToNewTimeline = resolveWindowIndexToNewTimeline(timeline, timeline2, this.windowIndex);
            this.windowIndex = resolveWindowIndexToNewTimeline;
            if (resolveWindowIndexToNewTimeline == -1) {
                return false;
            }
            MediaSource.MediaPeriodId mediaPeriodId = this.adMediaPeriodId;
            if (mediaPeriodId != null && timeline2.getIndexOfPeriod(mediaPeriodId.periodUid) == -1) {
                return false;
            }
            return true;
        }

        public boolean belongsToSession(int i, MediaSource.MediaPeriodId mediaPeriodId) {
            if (mediaPeriodId == null) {
                return i == this.windowIndex;
            }
            if (this.adMediaPeriodId == null) {
                if (mediaPeriodId.isAd() || mediaPeriodId.windowSequenceNumber != this.windowSequenceNumber) {
                    return false;
                }
                return true;
            } else if (mediaPeriodId.windowSequenceNumber == this.adMediaPeriodId.windowSequenceNumber && mediaPeriodId.adGroupIndex == this.adMediaPeriodId.adGroupIndex && mediaPeriodId.adIndexInAdGroup == this.adMediaPeriodId.adIndexInAdGroup) {
                return true;
            } else {
                return false;
            }
        }

        public void maybeSetWindowSequenceNumber(int i, MediaSource.MediaPeriodId mediaPeriodId) {
            if (this.windowSequenceNumber == -1 && i == this.windowIndex && mediaPeriodId != null && !mediaPeriodId.isAd()) {
                this.windowSequenceNumber = mediaPeriodId.windowSequenceNumber;
            }
        }

        public boolean isFinishedAtEventTime(AnalyticsListener.EventTime eventTime) {
            if (this.windowSequenceNumber == -1) {
                return false;
            }
            if (eventTime.mediaPeriodId == null) {
                if (this.windowIndex != eventTime.windowIndex) {
                    return true;
                }
                return false;
            } else if (eventTime.mediaPeriodId.windowSequenceNumber > this.windowSequenceNumber) {
                return true;
            } else {
                if (this.adMediaPeriodId == null) {
                    return false;
                }
                int indexOfPeriod = eventTime.timeline.getIndexOfPeriod(eventTime.mediaPeriodId.periodUid);
                int indexOfPeriod2 = eventTime.timeline.getIndexOfPeriod(this.adMediaPeriodId.periodUid);
                if (eventTime.mediaPeriodId.windowSequenceNumber < this.adMediaPeriodId.windowSequenceNumber || indexOfPeriod < indexOfPeriod2) {
                    return false;
                }
                if (indexOfPeriod > indexOfPeriod2) {
                    return true;
                }
                if (eventTime.mediaPeriodId.isAd()) {
                    int i = eventTime.mediaPeriodId.adGroupIndex;
                    int i2 = eventTime.mediaPeriodId.adIndexInAdGroup;
                    if (i > this.adMediaPeriodId.adGroupIndex || (i == this.adMediaPeriodId.adGroupIndex && i2 > this.adMediaPeriodId.adIndexInAdGroup)) {
                        return true;
                    }
                    return false;
                } else if (eventTime.mediaPeriodId.nextAdGroupIndex == -1 || eventTime.mediaPeriodId.nextAdGroupIndex > this.adMediaPeriodId.adGroupIndex) {
                    return true;
                } else {
                    return false;
                }
            }
        }

        private int resolveWindowIndexToNewTimeline(Timeline timeline, Timeline timeline2, int i) {
            if (i < timeline.getWindowCount()) {
                timeline.getWindow(i, DefaultPlaybackSessionManager.this.window);
                for (int i2 = DefaultPlaybackSessionManager.this.window.firstPeriodIndex; i2 <= DefaultPlaybackSessionManager.this.window.lastPeriodIndex; i2++) {
                    int indexOfPeriod = timeline2.getIndexOfPeriod(timeline.getUidOfPeriod(i2));
                    if (indexOfPeriod != -1) {
                        return timeline2.getPeriod(indexOfPeriod, DefaultPlaybackSessionManager.this.period).windowIndex;
                    }
                }
                return -1;
            } else if (i < timeline2.getWindowCount()) {
                return i;
            } else {
                return -1;
            }
        }
    }
}

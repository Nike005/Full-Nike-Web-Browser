package androidx.media2.widget;

import android.graphics.Canvas;
import android.media.MediaFormat;
import android.os.Handler;
import android.util.Log;
import android.util.LongSparseArray;
import android.util.Pair;
import androidx.media2.common.SubtitleData;
import androidx.media2.widget.MediaTimeProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;

abstract class SubtitleTrack implements MediaTimeProvider.OnMediaTimeListener {
    private static final String TAG = "SubtitleTrack";
    public boolean DEBUG = false;
    final ArrayList<Cue> mActiveCues = new ArrayList<>();
    private CueList mCues;
    private MediaFormat mFormat;
    protected Handler mHandler = new Handler();
    private long mLastTimeMs;
    private long mLastUpdateTimeMs;
    private long mNextScheduledTimeMs = -1;
    Runnable mRunnable;
    private final LongSparseArray<Run> mRunsByEndTime = new LongSparseArray<>();
    private final LongSparseArray<Run> mRunsByID = new LongSparseArray<>();
    protected MediaTimeProvider mTimeProvider;
    protected boolean mVisible;

    public static class Cue {
        public long mEndTimeMs;
        public long[] mInnerTimesMs;
        public Cue mNextInRun;
        public long mRunID;
        public long mStartTimeMs;

        public void onTime(long j) {
        }
    }

    interface RenderingWidget {

        public interface OnChangedListener {
            void onChanged(RenderingWidget renderingWidget);
        }

        void draw(Canvas canvas);

        void onAttachedToWindow();

        void onDetachedFromWindow();

        void setOnChangedListener(OnChangedListener onChangedListener);

        void setSize(int i, int i2);

        void setVisible(boolean z);
    }

    public abstract RenderingWidget getRenderingWidget();

    /* access modifiers changed from: protected */
    public abstract void onData(byte[] bArr, boolean z, long j);

    public abstract void updateView(ArrayList<Cue> arrayList);

    SubtitleTrack(MediaFormat mediaFormat) {
        this.mFormat = mediaFormat;
        this.mCues = new CueList();
        clearActiveCues();
        this.mLastTimeMs = -1;
    }

    public final MediaFormat getFormat() {
        return this.mFormat;
    }

    public void onData(SubtitleData subtitleData) {
        long startTimeUs = subtitleData.getStartTimeUs() + 1;
        onData(subtitleData.getData(), true, startTimeUs);
        setRunDiscardTimeMs(startTimeUs, (subtitleData.getStartTimeUs() + subtitleData.getDurationUs()) / 1000);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0007, code lost:
        if (r7.mLastUpdateTimeMs > r9) goto L_0x0009;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void updateActiveCues(boolean r8, long r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            if (r8 != 0) goto L_0x0009
            long r0 = r7.mLastUpdateTimeMs     // Catch:{ all -> 0x00ba }
            int r8 = (r0 > r9 ? 1 : (r0 == r9 ? 0 : -1))
            if (r8 <= 0) goto L_0x000c
        L_0x0009:
            r7.clearActiveCues()     // Catch:{ all -> 0x00ba }
        L_0x000c:
            androidx.media2.widget.SubtitleTrack$CueList r8 = r7.mCues     // Catch:{ all -> 0x00ba }
            long r0 = r7.mLastUpdateTimeMs     // Catch:{ all -> 0x00ba }
            java.lang.Iterable r8 = r8.entriesBetween(r0, r9)     // Catch:{ all -> 0x00ba }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ all -> 0x00ba }
        L_0x0018:
            boolean r0 = r8.hasNext()     // Catch:{ all -> 0x00ba }
            if (r0 == 0) goto L_0x009f
            java.lang.Object r0 = r8.next()     // Catch:{ all -> 0x00ba }
            android.util.Pair r0 = (android.util.Pair) r0     // Catch:{ all -> 0x00ba }
            java.lang.Object r1 = r0.second     // Catch:{ all -> 0x00ba }
            androidx.media2.widget.SubtitleTrack$Cue r1 = (androidx.media2.widget.SubtitleTrack.Cue) r1     // Catch:{ all -> 0x00ba }
            long r2 = r1.mEndTimeMs     // Catch:{ all -> 0x00ba }
            java.lang.Object r4 = r0.first     // Catch:{ all -> 0x00ba }
            java.lang.Long r4 = (java.lang.Long) r4     // Catch:{ all -> 0x00ba }
            long r4 = r4.longValue()     // Catch:{ all -> 0x00ba }
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 != 0) goto L_0x0061
            boolean r0 = r7.DEBUG     // Catch:{ all -> 0x00ba }
            if (r0 == 0) goto L_0x0050
            java.lang.String r0 = "SubtitleTrack"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ba }
            r2.<init>()     // Catch:{ all -> 0x00ba }
            java.lang.String r3 = "Removing "
            r2.append(r3)     // Catch:{ all -> 0x00ba }
            r2.append(r1)     // Catch:{ all -> 0x00ba }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00ba }
            android.util.Log.v(r0, r2)     // Catch:{ all -> 0x00ba }
        L_0x0050:
            java.util.ArrayList<androidx.media2.widget.SubtitleTrack$Cue> r0 = r7.mActiveCues     // Catch:{ all -> 0x00ba }
            r0.remove(r1)     // Catch:{ all -> 0x00ba }
            long r0 = r1.mRunID     // Catch:{ all -> 0x00ba }
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x0018
            r8.remove()     // Catch:{ all -> 0x00ba }
            goto L_0x0018
        L_0x0061:
            long r2 = r1.mStartTimeMs     // Catch:{ all -> 0x00ba }
            java.lang.Object r0 = r0.first     // Catch:{ all -> 0x00ba }
            java.lang.Long r0 = (java.lang.Long) r0     // Catch:{ all -> 0x00ba }
            long r4 = r0.longValue()     // Catch:{ all -> 0x00ba }
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 != 0) goto L_0x0096
            boolean r0 = r7.DEBUG     // Catch:{ all -> 0x00ba }
            if (r0 == 0) goto L_0x0089
            java.lang.String r0 = "SubtitleTrack"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00ba }
            r2.<init>()     // Catch:{ all -> 0x00ba }
            java.lang.String r3 = "Adding "
            r2.append(r3)     // Catch:{ all -> 0x00ba }
            r2.append(r1)     // Catch:{ all -> 0x00ba }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00ba }
            android.util.Log.v(r0, r2)     // Catch:{ all -> 0x00ba }
        L_0x0089:
            long[] r0 = r1.mInnerTimesMs     // Catch:{ all -> 0x00ba }
            if (r0 == 0) goto L_0x0090
            r1.onTime(r9)     // Catch:{ all -> 0x00ba }
        L_0x0090:
            java.util.ArrayList<androidx.media2.widget.SubtitleTrack$Cue> r0 = r7.mActiveCues     // Catch:{ all -> 0x00ba }
            r0.add(r1)     // Catch:{ all -> 0x00ba }
            goto L_0x0018
        L_0x0096:
            long[] r0 = r1.mInnerTimesMs     // Catch:{ all -> 0x00ba }
            if (r0 == 0) goto L_0x0018
            r1.onTime(r9)     // Catch:{ all -> 0x00ba }
            goto L_0x0018
        L_0x009f:
            android.util.LongSparseArray<androidx.media2.widget.SubtitleTrack$Run> r8 = r7.mRunsByEndTime     // Catch:{ all -> 0x00ba }
            int r8 = r8.size()     // Catch:{ all -> 0x00ba }
            if (r8 <= 0) goto L_0x00b6
            android.util.LongSparseArray<androidx.media2.widget.SubtitleTrack$Run> r8 = r7.mRunsByEndTime     // Catch:{ all -> 0x00ba }
            r0 = 0
            long r1 = r8.keyAt(r0)     // Catch:{ all -> 0x00ba }
            int r8 = (r1 > r9 ? 1 : (r1 == r9 ? 0 : -1))
            if (r8 > 0) goto L_0x00b6
            r7.removeRunsByEndTimeIndex(r0)     // Catch:{ all -> 0x00ba }
            goto L_0x009f
        L_0x00b6:
            r7.mLastUpdateTimeMs = r9     // Catch:{ all -> 0x00ba }
            monitor-exit(r7)
            return
        L_0x00ba:
            r8 = move-exception
            monitor-exit(r7)
            goto L_0x00be
        L_0x00bd:
            throw r8
        L_0x00be:
            goto L_0x00bd
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.widget.SubtitleTrack.updateActiveCues(boolean, long):void");
    }

    private void removeRunsByEndTimeIndex(int i) {
        Run valueAt = this.mRunsByEndTime.valueAt(i);
        while (valueAt != null) {
            Cue cue = valueAt.mFirstCue;
            while (cue != null) {
                this.mCues.remove(cue);
                Cue cue2 = cue.mNextInRun;
                cue.mNextInRun = null;
                cue = cue2;
            }
            this.mRunsByID.remove(valueAt.mRunID);
            Run run = valueAt.mNextRunAtEndTimeMs;
            valueAt.mPrevRunAtEndTimeMs = null;
            valueAt.mNextRunAtEndTimeMs = null;
            valueAt = run;
        }
        this.mRunsByEndTime.removeAt(i);
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        for (int size = this.mRunsByEndTime.size() - 1; size >= 0; size--) {
            removeRunsByEndTimeIndex(size);
        }
        super.finalize();
    }

    private synchronized void takeTime(long j) {
        this.mLastTimeMs = j;
    }

    /* access modifiers changed from: protected */
    public synchronized void clearActiveCues() {
        if (this.DEBUG) {
            Log.v(TAG, "Clearing " + this.mActiveCues.size() + " active cues");
        }
        this.mActiveCues.clear();
        this.mLastUpdateTimeMs = -1;
    }

    /* access modifiers changed from: protected */
    public void scheduleTimedEvents() {
        if (this.mTimeProvider != null) {
            this.mNextScheduledTimeMs = this.mCues.nextTimeAfter(this.mLastTimeMs);
            if (this.DEBUG) {
                Log.d(TAG, "sched @" + this.mNextScheduledTimeMs + " after " + this.mLastTimeMs);
            }
            MediaTimeProvider mediaTimeProvider = this.mTimeProvider;
            long j = this.mNextScheduledTimeMs;
            mediaTimeProvider.notifyAt(j >= 0 ? j * 1000 : -1, this);
        }
    }

    public void onTimedEvent(long j) {
        if (this.DEBUG) {
            Log.d(TAG, "onTimedEvent " + j);
        }
        synchronized (this) {
            long j2 = j / 1000;
            updateActiveCues(false, j2);
            takeTime(j2);
        }
        updateView(this.mActiveCues);
        scheduleTimedEvents();
    }

    public void onSeek(long j) {
        if (this.DEBUG) {
            Log.d(TAG, "onSeek " + j);
        }
        synchronized (this) {
            long j2 = j / 1000;
            updateActiveCues(true, j2);
            takeTime(j2);
        }
        updateView(this.mActiveCues);
        scheduleTimedEvents();
    }

    public void onStop() {
        synchronized (this) {
            if (this.DEBUG) {
                Log.d(TAG, "onStop");
            }
            clearActiveCues();
            this.mLastTimeMs = -1;
        }
        updateView(this.mActiveCues);
        this.mNextScheduledTimeMs = -1;
        this.mTimeProvider.notifyAt(-1, this);
    }

    public void show() {
        if (!this.mVisible) {
            this.mVisible = true;
            RenderingWidget renderingWidget = getRenderingWidget();
            if (renderingWidget != null) {
                renderingWidget.setVisible(true);
            }
            MediaTimeProvider mediaTimeProvider = this.mTimeProvider;
            if (mediaTimeProvider != null) {
                mediaTimeProvider.scheduleUpdate(this);
            }
        }
    }

    public void hide() {
        if (this.mVisible) {
            MediaTimeProvider mediaTimeProvider = this.mTimeProvider;
            if (mediaTimeProvider != null) {
                mediaTimeProvider.cancelNotifications(this);
            }
            RenderingWidget renderingWidget = getRenderingWidget();
            if (renderingWidget != null) {
                renderingWidget.setVisible(false);
            }
            this.mVisible = false;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00d7, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00f6, code lost:
        return false;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:16:0x004f */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0053  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00d8 A[DONT_GENERATE, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean addCue(androidx.media2.widget.SubtitleTrack.Cue r12) {
        /*
            r11 = this;
            monitor-enter(r11)
            androidx.media2.widget.SubtitleTrack$CueList r0 = r11.mCues     // Catch:{ all -> 0x00f7 }
            r0.add(r12)     // Catch:{ all -> 0x00f7 }
            long r0 = r12.mRunID     // Catch:{ all -> 0x00f7 }
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x003d
            android.util.LongSparseArray<androidx.media2.widget.SubtitleTrack$Run> r0 = r11.mRunsByID     // Catch:{ all -> 0x00f7 }
            long r4 = r12.mRunID     // Catch:{ all -> 0x00f7 }
            java.lang.Object r0 = r0.get(r4)     // Catch:{ all -> 0x00f7 }
            androidx.media2.widget.SubtitleTrack$Run r0 = (androidx.media2.widget.SubtitleTrack.Run) r0     // Catch:{ all -> 0x00f7 }
            if (r0 != 0) goto L_0x002b
            androidx.media2.widget.SubtitleTrack$Run r0 = new androidx.media2.widget.SubtitleTrack$Run     // Catch:{ all -> 0x00f7 }
            r0.<init>()     // Catch:{ all -> 0x00f7 }
            android.util.LongSparseArray<androidx.media2.widget.SubtitleTrack$Run> r1 = r11.mRunsByID     // Catch:{ all -> 0x00f7 }
            long r4 = r12.mRunID     // Catch:{ all -> 0x00f7 }
            r1.put(r4, r0)     // Catch:{ all -> 0x00f7 }
            long r4 = r12.mEndTimeMs     // Catch:{ all -> 0x00f7 }
            r0.mEndTimeMs = r4     // Catch:{ all -> 0x00f7 }
            goto L_0x0037
        L_0x002b:
            long r4 = r0.mEndTimeMs     // Catch:{ all -> 0x00f7 }
            long r6 = r12.mEndTimeMs     // Catch:{ all -> 0x00f7 }
            int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r1 >= 0) goto L_0x0037
            long r4 = r12.mEndTimeMs     // Catch:{ all -> 0x00f7 }
            r0.mEndTimeMs = r4     // Catch:{ all -> 0x00f7 }
        L_0x0037:
            androidx.media2.widget.SubtitleTrack$Cue r1 = r0.mFirstCue     // Catch:{ all -> 0x00f7 }
            r12.mNextInRun = r1     // Catch:{ all -> 0x00f7 }
            r0.mFirstCue = r12     // Catch:{ all -> 0x00f7 }
        L_0x003d:
            r0 = -1
            androidx.media2.widget.MediaTimeProvider r4 = r11.mTimeProvider     // Catch:{ all -> 0x00f7 }
            r5 = 1
            r6 = 0
            if (r4 == 0) goto L_0x004f
            androidx.media2.widget.MediaTimeProvider r4 = r11.mTimeProvider     // Catch:{ IllegalStateException -> 0x004f }
            long r7 = r4.getCurrentTimeUs(r6, r5)     // Catch:{ IllegalStateException -> 0x004f }
            r9 = 1000(0x3e8, double:4.94E-321)
            long r0 = r7 / r9
        L_0x004f:
            boolean r4 = r11.DEBUG     // Catch:{ all -> 0x00f7 }
            if (r4 == 0) goto L_0x0091
            java.lang.String r4 = "SubtitleTrack"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f7 }
            r7.<init>()     // Catch:{ all -> 0x00f7 }
            java.lang.String r8 = "mVisible="
            r7.append(r8)     // Catch:{ all -> 0x00f7 }
            boolean r8 = r11.mVisible     // Catch:{ all -> 0x00f7 }
            r7.append(r8)     // Catch:{ all -> 0x00f7 }
            java.lang.String r8 = ", "
            r7.append(r8)     // Catch:{ all -> 0x00f7 }
            long r8 = r12.mStartTimeMs     // Catch:{ all -> 0x00f7 }
            r7.append(r8)     // Catch:{ all -> 0x00f7 }
            java.lang.String r8 = " <= "
            r7.append(r8)     // Catch:{ all -> 0x00f7 }
            r7.append(r0)     // Catch:{ all -> 0x00f7 }
            java.lang.String r8 = ", "
            r7.append(r8)     // Catch:{ all -> 0x00f7 }
            long r8 = r12.mEndTimeMs     // Catch:{ all -> 0x00f7 }
            r7.append(r8)     // Catch:{ all -> 0x00f7 }
            java.lang.String r8 = " >= "
            r7.append(r8)     // Catch:{ all -> 0x00f7 }
            long r8 = r11.mLastTimeMs     // Catch:{ all -> 0x00f7 }
            r7.append(r8)     // Catch:{ all -> 0x00f7 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x00f7 }
            android.util.Log.v(r4, r7)     // Catch:{ all -> 0x00f7 }
        L_0x0091:
            boolean r4 = r11.mVisible     // Catch:{ all -> 0x00f7 }
            if (r4 == 0) goto L_0x00d8
            long r7 = r12.mStartTimeMs     // Catch:{ all -> 0x00f7 }
            int r4 = (r7 > r0 ? 1 : (r7 == r0 ? 0 : -1))
            if (r4 > 0) goto L_0x00d8
            long r7 = r12.mEndTimeMs     // Catch:{ all -> 0x00f7 }
            long r9 = r11.mLastTimeMs     // Catch:{ all -> 0x00f7 }
            int r4 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1))
            if (r4 < 0) goto L_0x00d8
            java.lang.Runnable r12 = r11.mRunnable     // Catch:{ all -> 0x00f7 }
            if (r12 == 0) goto L_0x00ae
            android.os.Handler r12 = r11.mHandler     // Catch:{ all -> 0x00f7 }
            java.lang.Runnable r2 = r11.mRunnable     // Catch:{ all -> 0x00f7 }
            r12.removeCallbacks(r2)     // Catch:{ all -> 0x00f7 }
        L_0x00ae:
            androidx.media2.widget.SubtitleTrack$1 r12 = new androidx.media2.widget.SubtitleTrack$1     // Catch:{ all -> 0x00f7 }
            r12.<init>(r11, r0)     // Catch:{ all -> 0x00f7 }
            r11.mRunnable = r12     // Catch:{ all -> 0x00f7 }
            android.os.Handler r0 = r11.mHandler     // Catch:{ all -> 0x00f7 }
            r1 = 10
            boolean r12 = r0.postDelayed(r12, r1)     // Catch:{ all -> 0x00f7 }
            if (r12 == 0) goto L_0x00cb
            boolean r12 = r11.DEBUG     // Catch:{ all -> 0x00f7 }
            if (r12 == 0) goto L_0x00d6
            java.lang.String r12 = "SubtitleTrack"
            java.lang.String r0 = "scheduling update"
            android.util.Log.v(r12, r0)     // Catch:{ all -> 0x00f7 }
            goto L_0x00d6
        L_0x00cb:
            boolean r12 = r11.DEBUG     // Catch:{ all -> 0x00f7 }
            if (r12 == 0) goto L_0x00d6
            java.lang.String r12 = "SubtitleTrack"
            java.lang.String r0 = "failed to schedule subtitle view update"
            android.util.Log.w(r12, r0)     // Catch:{ all -> 0x00f7 }
        L_0x00d6:
            monitor-exit(r11)
            return r5
        L_0x00d8:
            boolean r0 = r11.mVisible     // Catch:{ all -> 0x00f7 }
            if (r0 == 0) goto L_0x00f5
            long r0 = r12.mEndTimeMs     // Catch:{ all -> 0x00f7 }
            long r4 = r11.mLastTimeMs     // Catch:{ all -> 0x00f7 }
            int r7 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r7 < 0) goto L_0x00f5
            long r0 = r12.mStartTimeMs     // Catch:{ all -> 0x00f7 }
            long r4 = r11.mNextScheduledTimeMs     // Catch:{ all -> 0x00f7 }
            int r12 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r12 < 0) goto L_0x00f2
            long r0 = r11.mNextScheduledTimeMs     // Catch:{ all -> 0x00f7 }
            int r12 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r12 >= 0) goto L_0x00f5
        L_0x00f2:
            r11.scheduleTimedEvents()     // Catch:{ all -> 0x00f7 }
        L_0x00f5:
            monitor-exit(r11)
            return r6
        L_0x00f7:
            r12 = move-exception
            monitor-exit(r11)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.widget.SubtitleTrack.addCue(androidx.media2.widget.SubtitleTrack$Cue):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0018, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void setTimeProvider(androidx.media2.widget.MediaTimeProvider r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            androidx.media2.widget.MediaTimeProvider r0 = r1.mTimeProvider     // Catch:{ all -> 0x0019 }
            if (r0 != r2) goto L_0x0007
            monitor-exit(r1)
            return
        L_0x0007:
            androidx.media2.widget.MediaTimeProvider r0 = r1.mTimeProvider     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x0010
            androidx.media2.widget.MediaTimeProvider r0 = r1.mTimeProvider     // Catch:{ all -> 0x0019 }
            r0.cancelNotifications(r1)     // Catch:{ all -> 0x0019 }
        L_0x0010:
            r1.mTimeProvider = r2     // Catch:{ all -> 0x0019 }
            if (r2 == 0) goto L_0x0017
            r2.scheduleUpdate(r1)     // Catch:{ all -> 0x0019 }
        L_0x0017:
            monitor-exit(r1)
            return
        L_0x0019:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.widget.SubtitleTrack.setTimeProvider(androidx.media2.widget.MediaTimeProvider):void");
    }

    static class CueList {
        private static final String TAG = "CueList";
        public boolean DEBUG = false;
        SortedMap<Long, ArrayList<Cue>> mCues = new TreeMap();

        private boolean addEvent(Cue cue, long j) {
            ArrayList arrayList = (ArrayList) this.mCues.get(Long.valueOf(j));
            if (arrayList == null) {
                arrayList = new ArrayList(2);
                this.mCues.put(Long.valueOf(j), arrayList);
            } else if (arrayList.contains(cue)) {
                return false;
            }
            arrayList.add(cue);
            return true;
        }

        /* access modifiers changed from: package-private */
        public void removeEvent(Cue cue, long j) {
            ArrayList arrayList = (ArrayList) this.mCues.get(Long.valueOf(j));
            if (arrayList != null) {
                arrayList.remove(cue);
                if (arrayList.size() == 0) {
                    this.mCues.remove(Long.valueOf(j));
                }
            }
        }

        public void add(Cue cue) {
            if (cue.mStartTimeMs < cue.mEndTimeMs && addEvent(cue, cue.mStartTimeMs)) {
                long j = cue.mStartTimeMs;
                if (cue.mInnerTimesMs != null) {
                    for (long j2 : cue.mInnerTimesMs) {
                        if (j2 > j && j2 < cue.mEndTimeMs) {
                            addEvent(cue, j2);
                            j = j2;
                        }
                    }
                }
                addEvent(cue, cue.mEndTimeMs);
            }
        }

        public void remove(Cue cue) {
            removeEvent(cue, cue.mStartTimeMs);
            if (cue.mInnerTimesMs != null) {
                for (long removeEvent : cue.mInnerTimesMs) {
                    removeEvent(cue, removeEvent);
                }
            }
            removeEvent(cue, cue.mEndTimeMs);
        }

        public Iterable<Pair<Long, Cue>> entriesBetween(long j, long j2) {
            final long j3 = j;
            final long j4 = j2;
            return new Iterable<Pair<Long, Cue>>() {
                public Iterator<Pair<Long, Cue>> iterator() {
                    if (CueList.this.DEBUG) {
                        Log.d(CueList.TAG, "slice (" + j3 + ", " + j4 + "]=");
                    }
                    try {
                        return new EntryIterator(CueList.this.mCues.subMap(Long.valueOf(j3 + 1), Long.valueOf(j4 + 1)));
                    } catch (IllegalArgumentException unused) {
                        return new EntryIterator((SortedMap<Long, ArrayList<Cue>>) null);
                    }
                }
            };
        }

        public long nextTimeAfter(long j) {
            try {
                SortedMap<Long, ArrayList<Cue>> tailMap = this.mCues.tailMap(Long.valueOf(j + 1));
                if (tailMap != null) {
                    return tailMap.firstKey().longValue();
                }
            } catch (IllegalArgumentException | NoSuchElementException unused) {
            }
            return -1;
        }

        class EntryIterator implements Iterator<Pair<Long, Cue>> {
            private long mCurrentTimeMs;
            private boolean mDone;
            private Pair<Long, Cue> mLastEntry;
            private Iterator<Cue> mLastListIterator;
            private Iterator<Cue> mListIterator;
            private SortedMap<Long, ArrayList<Cue>> mRemainingCues;

            public boolean hasNext() {
                return !this.mDone;
            }

            public Pair<Long, Cue> next() {
                if (!this.mDone) {
                    this.mLastEntry = new Pair<>(Long.valueOf(this.mCurrentTimeMs), this.mListIterator.next());
                    Iterator<Cue> it = this.mListIterator;
                    this.mLastListIterator = it;
                    if (!it.hasNext()) {
                        nextKey();
                    }
                    return this.mLastEntry;
                }
                throw new NoSuchElementException("");
            }

            public void remove() {
                if (this.mLastListIterator == null || ((Cue) this.mLastEntry.second).mEndTimeMs != ((Long) this.mLastEntry.first).longValue()) {
                    throw new IllegalStateException("");
                }
                this.mLastListIterator.remove();
                this.mLastListIterator = null;
                if (((ArrayList) CueList.this.mCues.get(this.mLastEntry.first)).size() == 0) {
                    CueList.this.mCues.remove(this.mLastEntry.first);
                }
                Cue cue = (Cue) this.mLastEntry.second;
                CueList.this.removeEvent(cue, cue.mStartTimeMs);
                if (cue.mInnerTimesMs != null) {
                    for (long removeEvent : cue.mInnerTimesMs) {
                        CueList.this.removeEvent(cue, removeEvent);
                    }
                }
            }

            EntryIterator(SortedMap<Long, ArrayList<Cue>> sortedMap) {
                if (CueList.this.DEBUG) {
                    Log.v(CueList.TAG, sortedMap + "");
                }
                this.mRemainingCues = sortedMap;
                this.mLastListIterator = null;
                nextKey();
            }

            /* JADX WARNING: Can't wrap try/catch for region: R(7:4|5|6|7|8|9|10) */
            /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0037 */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            private void nextKey() {
                /*
                    r6 = this;
                L_0x0000:
                    r0 = 0
                    java.util.SortedMap<java.lang.Long, java.util.ArrayList<androidx.media2.widget.SubtitleTrack$Cue>> r1 = r6.mRemainingCues     // Catch:{ NoSuchElementException -> 0x004d }
                    if (r1 == 0) goto L_0x0045
                    java.util.SortedMap<java.lang.Long, java.util.ArrayList<androidx.media2.widget.SubtitleTrack$Cue>> r1 = r6.mRemainingCues     // Catch:{ NoSuchElementException -> 0x004d }
                    java.lang.Object r1 = r1.firstKey()     // Catch:{ NoSuchElementException -> 0x004d }
                    java.lang.Long r1 = (java.lang.Long) r1     // Catch:{ NoSuchElementException -> 0x004d }
                    long r1 = r1.longValue()     // Catch:{ NoSuchElementException -> 0x004d }
                    r6.mCurrentTimeMs = r1     // Catch:{ NoSuchElementException -> 0x004d }
                    java.util.SortedMap<java.lang.Long, java.util.ArrayList<androidx.media2.widget.SubtitleTrack$Cue>> r3 = r6.mRemainingCues     // Catch:{ NoSuchElementException -> 0x004d }
                    java.lang.Long r1 = java.lang.Long.valueOf(r1)     // Catch:{ NoSuchElementException -> 0x004d }
                    java.lang.Object r1 = r3.get(r1)     // Catch:{ NoSuchElementException -> 0x004d }
                    java.util.ArrayList r1 = (java.util.ArrayList) r1     // Catch:{ NoSuchElementException -> 0x004d }
                    java.util.Iterator r1 = r1.iterator()     // Catch:{ NoSuchElementException -> 0x004d }
                    r6.mListIterator = r1     // Catch:{ NoSuchElementException -> 0x004d }
                    java.util.SortedMap<java.lang.Long, java.util.ArrayList<androidx.media2.widget.SubtitleTrack$Cue>> r1 = r6.mRemainingCues     // Catch:{ IllegalArgumentException -> 0x0037 }
                    long r2 = r6.mCurrentTimeMs     // Catch:{ IllegalArgumentException -> 0x0037 }
                    r4 = 1
                    long r2 = r2 + r4
                    java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ IllegalArgumentException -> 0x0037 }
                    java.util.SortedMap r1 = r1.tailMap(r2)     // Catch:{ IllegalArgumentException -> 0x0037 }
                    r6.mRemainingCues = r1     // Catch:{ IllegalArgumentException -> 0x0037 }
                    goto L_0x0039
                L_0x0037:
                    r6.mRemainingCues = r0     // Catch:{ NoSuchElementException -> 0x004d }
                L_0x0039:
                    r1 = 0
                    r6.mDone = r1     // Catch:{ NoSuchElementException -> 0x004d }
                    java.util.Iterator<androidx.media2.widget.SubtitleTrack$Cue> r0 = r6.mListIterator
                    boolean r0 = r0.hasNext()
                    if (r0 == 0) goto L_0x0000
                    return
                L_0x0045:
                    java.util.NoSuchElementException r1 = new java.util.NoSuchElementException     // Catch:{ NoSuchElementException -> 0x004d }
                    java.lang.String r2 = ""
                    r1.<init>(r2)     // Catch:{ NoSuchElementException -> 0x004d }
                    throw r1     // Catch:{ NoSuchElementException -> 0x004d }
                L_0x004d:
                    r1 = 1
                    r6.mDone = r1
                    r6.mRemainingCues = r0
                    r6.mListIterator = r0
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.media2.widget.SubtitleTrack.CueList.EntryIterator.nextKey():void");
            }
        }

        CueList() {
        }
    }

    /* access modifiers changed from: protected */
    public void finishedRun(long j) {
        Run run;
        if (j != 0 && j != -1 && (run = this.mRunsByID.get(j)) != null) {
            run.storeByEndTimeMs(this.mRunsByEndTime);
        }
    }

    public void setRunDiscardTimeMs(long j, long j2) {
        Run run;
        if (j != 0 && j != -1 && (run = this.mRunsByID.get(j)) != null) {
            run.mEndTimeMs = j2;
            run.storeByEndTimeMs(this.mRunsByEndTime);
        }
    }

    public int getTrackType() {
        return getRenderingWidget() == null ? 3 : 4;
    }

    private static class Run {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        public long mEndTimeMs = -1;
        public Cue mFirstCue;
        public Run mNextRunAtEndTimeMs;
        public Run mPrevRunAtEndTimeMs;
        public long mRunID = 0;
        private long mStoredEndTimeMs = -1;

        static {
            Class<SubtitleTrack> cls = SubtitleTrack.class;
        }

        Run() {
        }

        public void storeByEndTimeMs(LongSparseArray<Run> longSparseArray) {
            int indexOfKey = longSparseArray.indexOfKey(this.mStoredEndTimeMs);
            if (indexOfKey >= 0) {
                if (this.mPrevRunAtEndTimeMs == null) {
                    Run run = this.mNextRunAtEndTimeMs;
                    if (run == null) {
                        longSparseArray.removeAt(indexOfKey);
                    } else {
                        longSparseArray.setValueAt(indexOfKey, run);
                    }
                }
                removeAtEndTimeMs();
            }
            long j = this.mEndTimeMs;
            if (j >= 0) {
                this.mPrevRunAtEndTimeMs = null;
                Run run2 = longSparseArray.get(j);
                this.mNextRunAtEndTimeMs = run2;
                if (run2 != null) {
                    run2.mPrevRunAtEndTimeMs = this;
                }
                longSparseArray.put(this.mEndTimeMs, this);
                this.mStoredEndTimeMs = this.mEndTimeMs;
            }
        }

        public void removeAtEndTimeMs() {
            Run run = this.mPrevRunAtEndTimeMs;
            if (run != null) {
                run.mNextRunAtEndTimeMs = this.mNextRunAtEndTimeMs;
                this.mPrevRunAtEndTimeMs = null;
            }
            Run run2 = this.mNextRunAtEndTimeMs;
            if (run2 != null) {
                run2.mPrevRunAtEndTimeMs = run;
                this.mNextRunAtEndTimeMs = null;
            }
        }
    }
}

package androidx.media2.common;

import androidx.core.util.Pair;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class MediaItem extends CustomVersionedParcelable {
    static final long LONG_MAX = 576460752303423487L;
    public static final long POSITION_UNKNOWN = 576460752303423487L;
    private static final String TAG = "MediaItem";
    long mEndPositionMs;
    private final List<Pair<OnMetadataChangedListener, Executor>> mListeners;
    private final Object mLock;
    MediaMetadata mMetadata;
    long mStartPositionMs;

    public interface OnMetadataChangedListener {
        void onMetadataChanged(MediaItem mediaItem);
    }

    MediaItem() {
        this.mLock = new Object();
        this.mStartPositionMs = 0;
        this.mEndPositionMs = 576460752303423487L;
        this.mListeners = new ArrayList();
    }

    MediaItem(Builder builder) {
        this(builder.mMetadata, builder.mStartPositionMs, builder.mEndPositionMs);
    }

    MediaItem(MediaItem mediaItem) {
        this(mediaItem.mMetadata, mediaItem.mStartPositionMs, mediaItem.mEndPositionMs);
    }

    MediaItem(MediaMetadata mediaMetadata, long j, long j2) {
        this.mLock = new Object();
        this.mStartPositionMs = 0;
        this.mEndPositionMs = 576460752303423487L;
        this.mListeners = new ArrayList();
        if (j <= j2) {
            if (mediaMetadata != null && mediaMetadata.containsKey("android.media.metadata.DURATION")) {
                long j3 = mediaMetadata.getLong("android.media.metadata.DURATION");
                if (!(j3 == Long.MIN_VALUE || j2 == 576460752303423487L || j2 <= j3)) {
                    throw new IllegalStateException("endPositionMs shouldn't be greater than duration in the metdata, endPositionMs=" + j2 + ", durationMs=" + j3);
                }
            }
            this.mMetadata = mediaMetadata;
            this.mStartPositionMs = j;
            this.mEndPositionMs = j2;
            return;
        }
        throw new IllegalStateException("Illegal start/end position: " + j + " : " + j2);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName());
        synchronized (this.mLock) {
            sb.append("{mMetadata=");
            sb.append(this.mMetadata);
            sb.append(", mStartPositionMs=");
            sb.append(this.mStartPositionMs);
            sb.append(", mEndPositionMs=");
            sb.append(this.mEndPositionMs);
            sb.append('}');
        }
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        r5 = r0.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0035, code lost:
        if (r5.hasNext() == false) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0037, code lost:
        r0 = (androidx.core.util.Pair) r5.next();
        r1 = (androidx.media2.common.MediaItem.OnMetadataChangedListener) r0.first;
        ((java.util.concurrent.Executor) r0.second).execute(new androidx.media2.common.MediaItem.C38671(r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x004e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setMetadata(androidx.media2.common.MediaMetadata r5) {
        /*
            r4 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.Object r1 = r4.mLock
            monitor-enter(r1)
            androidx.media2.common.MediaMetadata r2 = r4.mMetadata     // Catch:{ all -> 0x004f }
            if (r2 == 0) goto L_0x0025
            if (r5 == 0) goto L_0x0025
            java.lang.String r2 = r4.getMediaId()     // Catch:{ all -> 0x004f }
            java.lang.String r3 = r5.getMediaId()     // Catch:{ all -> 0x004f }
            boolean r2 = android.text.TextUtils.equals(r2, r3)     // Catch:{ all -> 0x004f }
            if (r2 != 0) goto L_0x0025
            java.lang.String r5 = "MediaItem"
            java.lang.String r0 = "MediaItem's media ID shouldn't be changed"
            android.util.Log.d(r5, r0)     // Catch:{ all -> 0x004f }
            monitor-exit(r1)     // Catch:{ all -> 0x004f }
            return
        L_0x0025:
            r4.mMetadata = r5     // Catch:{ all -> 0x004f }
            java.util.List<androidx.core.util.Pair<androidx.media2.common.MediaItem$OnMetadataChangedListener, java.util.concurrent.Executor>> r5 = r4.mListeners     // Catch:{ all -> 0x004f }
            r0.addAll(r5)     // Catch:{ all -> 0x004f }
            monitor-exit(r1)     // Catch:{ all -> 0x004f }
            java.util.Iterator r5 = r0.iterator()
        L_0x0031:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L_0x004e
            java.lang.Object r0 = r5.next()
            androidx.core.util.Pair r0 = (androidx.core.util.Pair) r0
            F r1 = r0.first
            androidx.media2.common.MediaItem$OnMetadataChangedListener r1 = (androidx.media2.common.MediaItem.OnMetadataChangedListener) r1
            S r0 = r0.second
            java.util.concurrent.Executor r0 = (java.util.concurrent.Executor) r0
            androidx.media2.common.MediaItem$1 r2 = new androidx.media2.common.MediaItem$1
            r2.<init>(r1)
            r0.execute(r2)
            goto L_0x0031
        L_0x004e:
            return
        L_0x004f:
            r5 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x004f }
            goto L_0x0053
        L_0x0052:
            throw r5
        L_0x0053:
            goto L_0x0052
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.common.MediaItem.setMetadata(androidx.media2.common.MediaMetadata):void");
    }

    public MediaMetadata getMetadata() {
        MediaMetadata mediaMetadata;
        synchronized (this.mLock) {
            mediaMetadata = this.mMetadata;
        }
        return mediaMetadata;
    }

    public long getStartPosition() {
        return this.mStartPositionMs;
    }

    public long getEndPosition() {
        return this.mEndPositionMs;
    }

    public String getMediaId() {
        String string;
        synchronized (this.mLock) {
            string = this.mMetadata != null ? this.mMetadata.getString("android.media.metadata.MEDIA_ID") : null;
        }
        return string;
    }

    public void addOnMetadataChangedListener(Executor executor, OnMetadataChangedListener onMetadataChangedListener) {
        synchronized (this.mLock) {
            for (Pair<OnMetadataChangedListener, Executor> pair : this.mListeners) {
                if (pair.first == onMetadataChangedListener) {
                    return;
                }
            }
            this.mListeners.add(new Pair(onMetadataChangedListener, executor));
        }
    }

    public void removeOnMetadataChangedListener(OnMetadataChangedListener onMetadataChangedListener) {
        synchronized (this.mLock) {
            for (int size = this.mListeners.size() - 1; size >= 0; size--) {
                if (this.mListeners.get(size).first == onMetadataChangedListener) {
                    this.mListeners.remove(size);
                    return;
                }
            }
        }
    }

    public static class Builder {
        long mEndPositionMs = 576460752303423487L;
        MediaMetadata mMetadata;
        long mStartPositionMs = 0;

        public Builder setMetadata(MediaMetadata mediaMetadata) {
            this.mMetadata = mediaMetadata;
            return this;
        }

        public Builder setStartPosition(long j) {
            if (j < 0) {
                j = 0;
            }
            this.mStartPositionMs = j;
            return this;
        }

        public Builder setEndPosition(long j) {
            if (j < 0) {
                j = 576460752303423487L;
            }
            this.mEndPositionMs = j;
            return this;
        }

        public MediaItem build() {
            return new MediaItem(this);
        }
    }

    public void onPreParceling(boolean z) {
        if (getClass() == MediaItem.class) {
            super.onPreParceling(z);
            return;
        }
        throw new RuntimeException("MediaItem's subclasses shouldn't be parcelized.");
    }
}

package androidx.media2.session;

import android.util.Log;
import androidx.collection.ArrayMap;
import androidx.media2.session.futures.AbstractResolvableFuture;
import java.util.ArrayList;

class SequencedFutureManager implements AutoCloseable {
    private static final boolean DEBUG = false;
    private static final String TAG = "SequencedFutureManager";
    private final Object mLock = new Object();
    private int mNextSequenceNumber;
    private ArrayMap<Integer, SequencedFuture> mSeqToFutureMap = new ArrayMap<>();

    SequencedFutureManager() {
    }

    public int obtainNextSequenceNumber() {
        int i;
        synchronized (this.mLock) {
            i = this.mNextSequenceNumber;
            this.mNextSequenceNumber = i + 1;
        }
        return i;
    }

    public <T> SequencedFuture<T> createSequencedFuture(T t) {
        SequencedFuture<T> create;
        synchronized (this.mLock) {
            int obtainNextSequenceNumber = obtainNextSequenceNumber();
            create = SequencedFuture.create(obtainNextSequenceNumber, t);
            this.mSeqToFutureMap.put(Integer.valueOf(obtainNextSequenceNumber), create);
        }
        return create;
    }

    public <T> void setFutureResult(int i, T t) {
        synchronized (this.mLock) {
            SequencedFuture remove = this.mSeqToFutureMap.remove(Integer.valueOf(i));
            if (remove != null) {
                if (t != null) {
                    if (remove.getResultWhenClosed().getClass() != t.getClass()) {
                        Log.w(TAG, "Type mismatch, expected " + remove.getResultWhenClosed().getClass() + ", but was " + t.getClass());
                    }
                }
                remove.set(t);
            }
        }
    }

    public void close() {
        ArrayList<SequencedFuture> arrayList = new ArrayList<>();
        synchronized (this.mLock) {
            arrayList.addAll(this.mSeqToFutureMap.values());
            this.mSeqToFutureMap.clear();
        }
        for (SequencedFuture sequencedFuture : arrayList) {
            sequencedFuture.set(sequencedFuture.getResultWhenClosed());
        }
    }

    static final class SequencedFuture<T> extends AbstractResolvableFuture<T> {
        private final T mResultWhenClosed;
        private final int mSequenceNumber;

        static <T> SequencedFuture<T> create(int i, T t) {
            return new SequencedFuture<>(i, t);
        }

        public boolean set(T t) {
            return super.set(t);
        }

        public int getSequenceNumber() {
            return this.mSequenceNumber;
        }

        public T getResultWhenClosed() {
            return this.mResultWhenClosed;
        }

        private SequencedFuture(int i, T t) {
            this.mSequenceNumber = i;
            this.mResultWhenClosed = t;
        }
    }
}

package com.danikula.videocache;

import java.lang.Thread;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ProxyCache {
    private static final Logger LOG = LoggerFactory.getLogger("ProxyCache");
    private static final int MAX_READ_SOURCE_ATTEMPTS = 1;
    private final Cache cache;
    private volatile int percentsAvailable = -1;
    private final AtomicInteger readSourceErrorsCount;
    private final Source source;
    private volatile Thread sourceReaderThread;
    private final Object stopLock = new Object();
    private volatile boolean stopped;

    /* renamed from: wc */
    private final Object f5050wc = new Object();

    /* access modifiers changed from: protected */
    public void onCachePercentsAvailableChanged(int i) {
    }

    public ProxyCache(Source source2, Cache cache2) {
        this.source = (Source) Preconditions.checkNotNull(source2);
        this.cache = (Cache) Preconditions.checkNotNull(cache2);
        this.readSourceErrorsCount = new AtomicInteger();
    }

    public int read(byte[] bArr, long j, int i) throws ProxyCacheException {
        ProxyCacheUtils.assertBuffer(bArr, j, i);
        while (!this.cache.isCompleted() && this.cache.available() < ((long) i) + j && !this.stopped) {
            readSourceAsync();
            waitForSourceData();
            checkReadSourceErrorsCount();
        }
        int read = this.cache.read(bArr, j, i);
        if (this.cache.isCompleted() && this.percentsAvailable != 100) {
            this.percentsAvailable = 100;
            onCachePercentsAvailableChanged(100);
        }
        return read;
    }

    private void checkReadSourceErrorsCount() throws ProxyCacheException {
        int i = this.readSourceErrorsCount.get();
        if (i >= 1) {
            this.readSourceErrorsCount.set(0);
            throw new ProxyCacheException("Error reading source " + i + " times");
        }
    }

    public void shutdown() {
        synchronized (this.stopLock) {
            Logger logger = LOG;
            logger.debug("Shutdown proxy for " + this.source);
            try {
                this.stopped = true;
                if (this.sourceReaderThread != null) {
                    this.sourceReaderThread.interrupt();
                }
                this.cache.close();
            } catch (ProxyCacheException e) {
                onError(e);
            }
        }
    }

    private synchronized void readSourceAsync() throws ProxyCacheException {
        boolean z = (this.sourceReaderThread == null || this.sourceReaderThread.getState() == Thread.State.TERMINATED) ? false : true;
        if (!this.stopped && !this.cache.isCompleted() && !z) {
            SourceReaderRunnable sourceReaderRunnable = new SourceReaderRunnable();
            this.sourceReaderThread = new Thread(sourceReaderRunnable, "Source reader for " + this.source);
            this.sourceReaderThread.start();
        }
    }

    private void waitForSourceData() throws ProxyCacheException {
        synchronized (this.f5050wc) {
            try {
                this.f5050wc.wait(1000);
            } catch (InterruptedException e) {
                throw new ProxyCacheException("Waiting source data is interrupted!", e);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void notifyNewCacheDataAvailable(long j, long j2) {
        onCacheAvailable(j, j2);
        synchronized (this.f5050wc) {
            this.f5050wc.notifyAll();
        }
    }

    /* access modifiers changed from: protected */
    public void onCacheAvailable(long j, long j2) {
        boolean z = true;
        int i = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1)) == 0 ? 100 : (int) ((((float) j) / ((float) j2)) * 100.0f);
        boolean z2 = i != this.percentsAvailable;
        if (j2 < 0) {
            z = false;
        }
        if (z && z2) {
            onCachePercentsAvailableChanged(i);
        }
        this.percentsAvailable = i;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0039, code lost:
        r2 = r2 + ((long) r5);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void readSource() {
        /*
            r8 = this;
            r0 = -1
            r2 = 0
            com.danikula.videocache.Cache r4 = r8.cache     // Catch:{ all -> 0x0049 }
            long r2 = r4.available()     // Catch:{ all -> 0x0049 }
            com.danikula.videocache.Source r4 = r8.source     // Catch:{ all -> 0x0049 }
            r4.open(r2)     // Catch:{ all -> 0x0049 }
            com.danikula.videocache.Source r4 = r8.source     // Catch:{ all -> 0x0049 }
            long r0 = r4.length()     // Catch:{ all -> 0x0049 }
            r4 = 8192(0x2000, float:1.14794E-41)
            byte[] r4 = new byte[r4]     // Catch:{ all -> 0x0049 }
        L_0x0019:
            com.danikula.videocache.Source r5 = r8.source     // Catch:{ all -> 0x0049 }
            int r5 = r5.read(r4)     // Catch:{ all -> 0x0049 }
            r6 = -1
            if (r5 == r6) goto L_0x0042
            java.lang.Object r6 = r8.stopLock     // Catch:{ all -> 0x0049 }
            monitor-enter(r6)     // Catch:{ all -> 0x0049 }
            boolean r7 = r8.isStopped()     // Catch:{ all -> 0x003f }
            if (r7 == 0) goto L_0x0033
            monitor-exit(r6)     // Catch:{ all -> 0x003f }
            r8.closeSource()
            r8.notifyNewCacheDataAvailable(r2, r0)
            return
        L_0x0033:
            com.danikula.videocache.Cache r7 = r8.cache     // Catch:{ all -> 0x003f }
            r7.append(r4, r5)     // Catch:{ all -> 0x003f }
            monitor-exit(r6)     // Catch:{ all -> 0x003f }
            long r5 = (long) r5
            long r2 = r2 + r5
            r8.notifyNewCacheDataAvailable(r2, r0)     // Catch:{ all -> 0x0049 }
            goto L_0x0019
        L_0x003f:
            r4 = move-exception
            monitor-exit(r6)     // Catch:{ all -> 0x003f }
            throw r4     // Catch:{ all -> 0x0049 }
        L_0x0042:
            r8.tryComplete()     // Catch:{ all -> 0x0049 }
            r8.onSourceRead()     // Catch:{ all -> 0x0049 }
            goto L_0x0052
        L_0x0049:
            r4 = move-exception
            java.util.concurrent.atomic.AtomicInteger r5 = r8.readSourceErrorsCount     // Catch:{ all -> 0x0059 }
            r5.incrementAndGet()     // Catch:{ all -> 0x0059 }
            r8.onError(r4)     // Catch:{ all -> 0x0059 }
        L_0x0052:
            r8.closeSource()
            r8.notifyNewCacheDataAvailable(r2, r0)
            return
        L_0x0059:
            r4 = move-exception
            r8.closeSource()
            r8.notifyNewCacheDataAvailable(r2, r0)
            goto L_0x0062
        L_0x0061:
            throw r4
        L_0x0062:
            goto L_0x0061
        */
        throw new UnsupportedOperationException("Method not decompiled: com.danikula.videocache.ProxyCache.readSource():void");
    }

    private void onSourceRead() {
        this.percentsAvailable = 100;
        onCachePercentsAvailableChanged(this.percentsAvailable);
    }

    private void tryComplete() throws ProxyCacheException {
        synchronized (this.stopLock) {
            if (!isStopped() && this.cache.available() == this.source.length()) {
                this.cache.complete();
            }
        }
    }

    private boolean isStopped() {
        return Thread.currentThread().isInterrupted() || this.stopped;
    }

    private void closeSource() {
        try {
            this.source.close();
        } catch (ProxyCacheException e) {
            onError(new ProxyCacheException("Error closing source " + this.source, e));
        }
    }

    /* access modifiers changed from: protected */
    public final void onError(Throwable th) {
        if (th instanceof InterruptedProxyCacheException) {
            LOG.debug("ProxyCache is interrupted");
        } else {
            LOG.error("ProxyCache error", th);
        }
    }

    private class SourceReaderRunnable implements Runnable {
        private SourceReaderRunnable() {
        }

        public void run() {
            ProxyCache.this.readSource();
        }
    }
}

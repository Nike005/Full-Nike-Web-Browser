package com.mopub.volley;

import android.os.Process;
import com.mopub.volley.Cache;
import com.mopub.volley.Request;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class CacheDispatcher extends Thread {
    private static final boolean DEBUG = VolleyLog.DEBUG;
    private final Cache mCache;
    private final BlockingQueue<Request<?>> mCacheQueue;
    /* access modifiers changed from: private */
    public final ResponseDelivery mDelivery;
    /* access modifiers changed from: private */
    public final BlockingQueue<Request<?>> mNetworkQueue;
    private volatile boolean mQuit = false;
    private final WaitingRequestManager mWaitingRequestManager;

    public CacheDispatcher(BlockingQueue<Request<?>> blockingQueue, BlockingQueue<Request<?>> blockingQueue2, Cache cache, ResponseDelivery responseDelivery) {
        this.mCacheQueue = blockingQueue;
        this.mNetworkQueue = blockingQueue2;
        this.mCache = cache;
        this.mDelivery = responseDelivery;
        this.mWaitingRequestManager = new WaitingRequestManager(this);
    }

    public void quit() {
        this.mQuit = true;
        interrupt();
    }

    public void run() {
        if (DEBUG) {
            VolleyLog.m345v("start new dispatcher", new Object[0]);
        }
        Process.setThreadPriority(10);
        this.mCache.initialize();
        while (true) {
            try {
                processRequest();
            } catch (InterruptedException unused) {
                if (this.mQuit) {
                    Thread.currentThread().interrupt();
                    return;
                }
                VolleyLog.m343e("Ignoring spurious interrupt of CacheDispatcher thread; use quit() to terminate it", new Object[0]);
            }
        }
    }

    private void processRequest() throws InterruptedException {
        processRequest(this.mCacheQueue.take());
    }

    /* access modifiers changed from: package-private */
    public void processRequest(final Request<?> request) throws InterruptedException {
        request.addMarker("cache-queue-take");
        request.sendEvent(1);
        try {
            if (request.isCanceled()) {
                request.finish("cache-discard-canceled");
                return;
            }
            Cache.Entry entry = this.mCache.get(request.getCacheKey());
            if (entry == null) {
                request.addMarker("cache-miss");
                if (!this.mWaitingRequestManager.maybeAddToWaitingRequests(request)) {
                    this.mNetworkQueue.put(request);
                }
                request.sendEvent(2);
            } else if (entry.isExpired()) {
                request.addMarker("cache-hit-expired");
                request.setCacheEntry(entry);
                if (!this.mWaitingRequestManager.maybeAddToWaitingRequests(request)) {
                    this.mNetworkQueue.put(request);
                }
                request.sendEvent(2);
            } else {
                request.addMarker("cache-hit");
                Response<?> parseNetworkResponse = request.parseNetworkResponse(new NetworkResponse(entry.data, entry.responseHeaders));
                request.addMarker("cache-hit-parsed");
                if (!entry.refreshNeeded()) {
                    this.mDelivery.postResponse(request, parseNetworkResponse);
                } else {
                    request.addMarker("cache-hit-refresh-needed");
                    request.setCacheEntry(entry);
                    parseNetworkResponse.intermediate = true;
                    if (!this.mWaitingRequestManager.maybeAddToWaitingRequests(request)) {
                        this.mDelivery.postResponse(request, parseNetworkResponse, new Runnable() {
                            public void run() {
                                try {
                                    CacheDispatcher.this.mNetworkQueue.put(request);
                                } catch (InterruptedException unused) {
                                    Thread.currentThread().interrupt();
                                }
                            }
                        });
                    } else {
                        this.mDelivery.postResponse(request, parseNetworkResponse);
                    }
                }
                request.sendEvent(2);
            }
        } finally {
            request.sendEvent(2);
        }
    }

    private static class WaitingRequestManager implements Request.NetworkRequestCompleteListener {
        private final CacheDispatcher mCacheDispatcher;
        private final Map<String, List<Request<?>>> mWaitingRequests = new HashMap();

        WaitingRequestManager(CacheDispatcher cacheDispatcher) {
            this.mCacheDispatcher = cacheDispatcher;
        }

        public void onResponseReceived(Request<?> request, Response<?> response) {
            List<Request> remove;
            if (response.cacheEntry == null || response.cacheEntry.isExpired()) {
                onNoUsableResponseReceived(request);
                return;
            }
            String cacheKey = request.getCacheKey();
            synchronized (this) {
                remove = this.mWaitingRequests.remove(cacheKey);
            }
            if (remove != null) {
                if (VolleyLog.DEBUG) {
                    VolleyLog.m345v("Releasing %d waiting requests for cacheKey=%s.", Integer.valueOf(remove.size()), cacheKey);
                }
                for (Request postResponse : remove) {
                    this.mCacheDispatcher.mDelivery.postResponse(postResponse, response);
                }
            }
        }

        public synchronized void onNoUsableResponseReceived(Request<?> request) {
            String cacheKey = request.getCacheKey();
            List remove = this.mWaitingRequests.remove(cacheKey);
            if (remove != null && !remove.isEmpty()) {
                if (VolleyLog.DEBUG) {
                    VolleyLog.m345v("%d waiting requests for cacheKey=%s; resend to network", Integer.valueOf(remove.size()), cacheKey);
                }
                Request request2 = (Request) remove.remove(0);
                this.mWaitingRequests.put(cacheKey, remove);
                request2.setNetworkRequestCompleteListener(this);
                try {
                    this.mCacheDispatcher.mNetworkQueue.put(request2);
                } catch (InterruptedException e) {
                    VolleyLog.m343e("Couldn't add request to queue. %s", e.toString());
                    Thread.currentThread().interrupt();
                    this.mCacheDispatcher.quit();
                }
            }
            return;
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Code restructure failed: missing block: B:11:0x0039, code lost:
            return true;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0051, code lost:
            return false;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public synchronized boolean maybeAddToWaitingRequests(com.mopub.volley.Request<?> r6) {
            /*
                r5 = this;
                monitor-enter(r5)
                java.lang.String r0 = r6.getCacheKey()     // Catch:{ all -> 0x0052 }
                java.util.Map<java.lang.String, java.util.List<com.mopub.volley.Request<?>>> r1 = r5.mWaitingRequests     // Catch:{ all -> 0x0052 }
                boolean r1 = r1.containsKey(r0)     // Catch:{ all -> 0x0052 }
                r2 = 1
                r3 = 0
                if (r1 == 0) goto L_0x003a
                java.util.Map<java.lang.String, java.util.List<com.mopub.volley.Request<?>>> r1 = r5.mWaitingRequests     // Catch:{ all -> 0x0052 }
                java.lang.Object r1 = r1.get(r0)     // Catch:{ all -> 0x0052 }
                java.util.List r1 = (java.util.List) r1     // Catch:{ all -> 0x0052 }
                if (r1 != 0) goto L_0x001e
                java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ all -> 0x0052 }
                r1.<init>()     // Catch:{ all -> 0x0052 }
            L_0x001e:
                java.lang.String r4 = "waiting-for-response"
                r6.addMarker(r4)     // Catch:{ all -> 0x0052 }
                r1.add(r6)     // Catch:{ all -> 0x0052 }
                java.util.Map<java.lang.String, java.util.List<com.mopub.volley.Request<?>>> r6 = r5.mWaitingRequests     // Catch:{ all -> 0x0052 }
                r6.put(r0, r1)     // Catch:{ all -> 0x0052 }
                boolean r6 = com.mopub.volley.VolleyLog.DEBUG     // Catch:{ all -> 0x0052 }
                if (r6 == 0) goto L_0x0038
                java.lang.String r6 = "Request for cacheKey=%s is in flight, putting on hold."
                java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ all -> 0x0052 }
                r1[r3] = r0     // Catch:{ all -> 0x0052 }
                com.mopub.volley.VolleyLog.m342d(r6, r1)     // Catch:{ all -> 0x0052 }
            L_0x0038:
                monitor-exit(r5)
                return r2
            L_0x003a:
                java.util.Map<java.lang.String, java.util.List<com.mopub.volley.Request<?>>> r1 = r5.mWaitingRequests     // Catch:{ all -> 0x0052 }
                r4 = 0
                r1.put(r0, r4)     // Catch:{ all -> 0x0052 }
                r6.setNetworkRequestCompleteListener(r5)     // Catch:{ all -> 0x0052 }
                boolean r6 = com.mopub.volley.VolleyLog.DEBUG     // Catch:{ all -> 0x0052 }
                if (r6 == 0) goto L_0x0050
                java.lang.String r6 = "new request, sending to network %s"
                java.lang.Object[] r1 = new java.lang.Object[r2]     // Catch:{ all -> 0x0052 }
                r1[r3] = r0     // Catch:{ all -> 0x0052 }
                com.mopub.volley.VolleyLog.m342d(r6, r1)     // Catch:{ all -> 0x0052 }
            L_0x0050:
                monitor-exit(r5)
                return r3
            L_0x0052:
                r6 = move-exception
                monitor-exit(r5)
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mopub.volley.CacheDispatcher.WaitingRequestManager.maybeAddToWaitingRequests(com.mopub.volley.Request):boolean");
        }
    }
}

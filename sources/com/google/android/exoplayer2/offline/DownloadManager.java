package com.google.android.exoplayer2.offline;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.google.android.exoplayer2.database.DatabaseProvider;
import com.google.android.exoplayer2.offline.Downloader;
import com.google.android.exoplayer2.scheduler.Requirements;
import com.google.android.exoplayer2.scheduler.RequirementsWatcher;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.cache.Cache;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.Util;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArraySet;

public final class DownloadManager {
    public static final int DEFAULT_MAX_PARALLEL_DOWNLOADS = 3;
    public static final int DEFAULT_MIN_RETRY_COUNT = 5;
    public static final Requirements DEFAULT_REQUIREMENTS = new Requirements(1);
    private static final int MSG_ADD_DOWNLOAD = 6;
    private static final int MSG_CONTENT_LENGTH_CHANGED = 10;
    private static final int MSG_DOWNLOAD_UPDATE = 2;
    private static final int MSG_INITIALIZE = 0;
    private static final int MSG_INITIALIZED = 0;
    private static final int MSG_PROCESSED = 1;
    private static final int MSG_RELEASE = 12;
    private static final int MSG_REMOVE_ALL_DOWNLOADS = 8;
    private static final int MSG_REMOVE_DOWNLOAD = 7;
    private static final int MSG_SET_DOWNLOADS_PAUSED = 1;
    private static final int MSG_SET_MAX_PARALLEL_DOWNLOADS = 4;
    private static final int MSG_SET_MIN_RETRY_COUNT = 5;
    private static final int MSG_SET_NOT_MET_REQUIREMENTS = 2;
    private static final int MSG_SET_STOP_REASON = 3;
    private static final int MSG_TASK_STOPPED = 9;
    private static final int MSG_UPDATE_PROGRESS = 11;
    private static final String TAG = "DownloadManager";
    private int activeTaskCount;
    private final Context context;
    private final WritableDownloadIndex downloadIndex;
    private List<Download> downloads;
    private boolean downloadsPaused;
    private boolean initialized;
    private final InternalHandler internalHandler;
    private final CopyOnWriteArraySet<Listener> listeners;
    private final Handler mainHandler;
    private int maxParallelDownloads;
    private int minRetryCount;
    private int notMetRequirements;
    private int pendingMessages;
    private final RequirementsWatcher.Listener requirementsListener;
    private RequirementsWatcher requirementsWatcher;

    public interface Listener {

        /* renamed from: com.google.android.exoplayer2.offline.DownloadManager$Listener$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$onDownloadChanged(Listener listener, DownloadManager downloadManager, Download download) {
            }

            public static void $default$onDownloadRemoved(Listener listener, DownloadManager downloadManager, Download download) {
            }

            public static void $default$onIdle(Listener listener, DownloadManager downloadManager) {
            }

            public static void $default$onInitialized(Listener listener, DownloadManager downloadManager) {
            }

            public static void $default$onRequirementsStateChanged(Listener listener, DownloadManager downloadManager, Requirements requirements, int i) {
            }
        }

        void onDownloadChanged(DownloadManager downloadManager, Download download);

        void onDownloadRemoved(DownloadManager downloadManager, Download download);

        void onIdle(DownloadManager downloadManager);

        void onInitialized(DownloadManager downloadManager);

        void onRequirementsStateChanged(DownloadManager downloadManager, Requirements requirements, int i);
    }

    public DownloadManager(Context context2, DatabaseProvider databaseProvider, Cache cache, DataSource.Factory factory) {
        this(context2, new DefaultDownloadIndex(databaseProvider), new DefaultDownloaderFactory(new DownloaderConstructorHelper(cache, factory)));
    }

    public DownloadManager(Context context2, WritableDownloadIndex writableDownloadIndex, DownloaderFactory downloaderFactory) {
        this.context = context2.getApplicationContext();
        this.downloadIndex = writableDownloadIndex;
        this.maxParallelDownloads = 3;
        this.minRetryCount = 5;
        this.downloadsPaused = true;
        this.downloads = Collections.emptyList();
        this.listeners = new CopyOnWriteArraySet<>();
        Handler createHandler = Util.createHandler(new Handler.Callback() {
            public final boolean handleMessage(Message message) {
                return DownloadManager.this.handleMainMessage(message);
            }
        });
        this.mainHandler = createHandler;
        HandlerThread handlerThread = new HandlerThread("DownloadManager file i/o");
        handlerThread.start();
        this.internalHandler = new InternalHandler(handlerThread, writableDownloadIndex, downloaderFactory, createHandler, this.maxParallelDownloads, this.minRetryCount, this.downloadsPaused);
        $$Lambda$DownloadManager$9oihGmKoXEDrfeODE3DbaHprOHM r12 = new RequirementsWatcher.Listener() {
            public final void onRequirementsStateChanged(RequirementsWatcher requirementsWatcher, int i) {
                DownloadManager.this.onRequirementsStateChanged(requirementsWatcher, i);
            }
        };
        this.requirementsListener = r12;
        RequirementsWatcher requirementsWatcher2 = new RequirementsWatcher(context2, r12, DEFAULT_REQUIREMENTS);
        this.requirementsWatcher = requirementsWatcher2;
        int start = requirementsWatcher2.start();
        this.notMetRequirements = start;
        this.pendingMessages = 1;
        this.internalHandler.obtainMessage(0, start, 0).sendToTarget();
    }

    public boolean isInitialized() {
        return this.initialized;
    }

    public boolean isIdle() {
        return this.activeTaskCount == 0 && this.pendingMessages == 0;
    }

    public boolean isWaitingForRequirements() {
        if (!this.downloadsPaused && this.notMetRequirements != 0) {
            for (int i = 0; i < this.downloads.size(); i++) {
                if (this.downloads.get(i).state == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        this.listeners.remove(listener);
    }

    public Requirements getRequirements() {
        return this.requirementsWatcher.getRequirements();
    }

    public int getNotMetRequirements() {
        return getRequirements().getNotMetRequirements(this.context);
    }

    public void setRequirements(Requirements requirements) {
        if (!requirements.equals(this.requirementsWatcher.getRequirements())) {
            this.requirementsWatcher.stop();
            RequirementsWatcher requirementsWatcher2 = new RequirementsWatcher(this.context, this.requirementsListener, requirements);
            this.requirementsWatcher = requirementsWatcher2;
            onRequirementsStateChanged(this.requirementsWatcher, requirementsWatcher2.start());
        }
    }

    public int getMaxParallelDownloads() {
        return this.maxParallelDownloads;
    }

    public void setMaxParallelDownloads(int i) {
        Assertions.checkArgument(i > 0);
        if (this.maxParallelDownloads != i) {
            this.maxParallelDownloads = i;
            this.pendingMessages++;
            this.internalHandler.obtainMessage(4, i, 0).sendToTarget();
        }
    }

    public int getMinRetryCount() {
        return this.minRetryCount;
    }

    public void setMinRetryCount(int i) {
        Assertions.checkArgument(i >= 0);
        if (this.minRetryCount != i) {
            this.minRetryCount = i;
            this.pendingMessages++;
            this.internalHandler.obtainMessage(5, i, 0).sendToTarget();
        }
    }

    public DownloadIndex getDownloadIndex() {
        return this.downloadIndex;
    }

    public List<Download> getCurrentDownloads() {
        return this.downloads;
    }

    public boolean getDownloadsPaused() {
        return this.downloadsPaused;
    }

    public void resumeDownloads() {
        if (this.downloadsPaused) {
            this.downloadsPaused = false;
            this.pendingMessages++;
            this.internalHandler.obtainMessage(1, 0, 0).sendToTarget();
        }
    }

    public void pauseDownloads() {
        if (!this.downloadsPaused) {
            this.downloadsPaused = true;
            this.pendingMessages++;
            this.internalHandler.obtainMessage(1, 1, 0).sendToTarget();
        }
    }

    public void setStopReason(String str, int i) {
        this.pendingMessages++;
        this.internalHandler.obtainMessage(3, i, 0, str).sendToTarget();
    }

    public void addDownload(DownloadRequest downloadRequest) {
        addDownload(downloadRequest, 0);
    }

    public void addDownload(DownloadRequest downloadRequest, int i) {
        this.pendingMessages++;
        this.internalHandler.obtainMessage(6, i, 0, downloadRequest).sendToTarget();
    }

    public void removeDownload(String str) {
        this.pendingMessages++;
        this.internalHandler.obtainMessage(7, str).sendToTarget();
    }

    public void removeAllDownloads() {
        this.pendingMessages++;
        this.internalHandler.obtainMessage(8).sendToTarget();
    }

    public void release() {
        synchronized (this.internalHandler) {
            if (!this.internalHandler.released) {
                this.internalHandler.sendEmptyMessage(12);
                boolean z = false;
                while (!this.internalHandler.released) {
                    try {
                        this.internalHandler.wait();
                    } catch (InterruptedException unused) {
                        z = true;
                    }
                }
                if (z) {
                    Thread.currentThread().interrupt();
                }
                this.mainHandler.removeCallbacksAndMessages((Object) null);
                this.downloads = Collections.emptyList();
                this.pendingMessages = 0;
                this.activeTaskCount = 0;
                this.initialized = false;
            }
        }
    }

    /* access modifiers changed from: private */
    public void onRequirementsStateChanged(RequirementsWatcher requirementsWatcher2, int i) {
        Requirements requirements = requirementsWatcher2.getRequirements();
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onRequirementsStateChanged(this, requirements, i);
        }
        if (this.notMetRequirements != i) {
            this.notMetRequirements = i;
            this.pendingMessages++;
            this.internalHandler.obtainMessage(2, i, 0).sendToTarget();
        }
    }

    /* access modifiers changed from: private */
    public boolean handleMainMessage(Message message) {
        int i = message.what;
        if (i == 0) {
            onInitialized((List) message.obj);
        } else if (i == 1) {
            onMessageProcessed(message.arg1, message.arg2);
        } else if (i == 2) {
            onDownloadUpdate((DownloadUpdate) message.obj);
        } else {
            throw new IllegalStateException();
        }
        return true;
    }

    private void onInitialized(List<Download> list) {
        this.initialized = true;
        this.downloads = Collections.unmodifiableList(list);
        Iterator<Listener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onInitialized(this);
        }
    }

    private void onDownloadUpdate(DownloadUpdate downloadUpdate) {
        this.downloads = Collections.unmodifiableList(downloadUpdate.downloads);
        Download download = downloadUpdate.download;
        if (downloadUpdate.isRemove) {
            Iterator<Listener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().onDownloadRemoved(this, download);
            }
            return;
        }
        Iterator<Listener> it2 = this.listeners.iterator();
        while (it2.hasNext()) {
            it2.next().onDownloadChanged(this, download);
        }
    }

    private void onMessageProcessed(int i, int i2) {
        this.pendingMessages -= i;
        this.activeTaskCount = i2;
        if (isIdle()) {
            Iterator<Listener> it = this.listeners.iterator();
            while (it.hasNext()) {
                it.next().onIdle(this);
            }
        }
    }

    static Download mergeRequest(Download download, DownloadRequest downloadRequest, int i, long j) {
        Download download2 = download;
        int i2 = download2.state;
        return new Download(download2.request.copyWithMergedRequest(downloadRequest), (i2 == 5 || i2 == 7) ? 7 : i != 0 ? 1 : 0, (i2 == 5 || download.isTerminalState()) ? j : download2.startTimeMs, j, -1, i, 0);
    }

    private static final class InternalHandler extends Handler {
        private static final int UPDATE_PROGRESS_INTERVAL_MS = 5000;
        private int activeDownloadTaskCount;
        private final HashMap<String, Task> activeTasks = new HashMap<>();
        private final WritableDownloadIndex downloadIndex;
        private final DownloaderFactory downloaderFactory;
        private final ArrayList<Download> downloads = new ArrayList<>();
        private boolean downloadsPaused;
        private final Handler mainHandler;
        private int maxParallelDownloads;
        private int minRetryCount;
        private int notMetRequirements;
        public boolean released;
        private final HandlerThread thread;

        public InternalHandler(HandlerThread handlerThread, WritableDownloadIndex writableDownloadIndex, DownloaderFactory downloaderFactory2, Handler handler, int i, int i2, boolean z) {
            super(handlerThread.getLooper());
            this.thread = handlerThread;
            this.downloadIndex = writableDownloadIndex;
            this.downloaderFactory = downloaderFactory2;
            this.mainHandler = handler;
            this.maxParallelDownloads = i;
            this.minRetryCount = i2;
            this.downloadsPaused = z;
        }

        public void handleMessage(Message message) {
            boolean z = false;
            switch (message.what) {
                case 0:
                    initialize(message.arg1);
                    break;
                case 1:
                    if (message.arg1 != 0) {
                        z = true;
                    }
                    setDownloadsPaused(z);
                    break;
                case 2:
                    setNotMetRequirements(message.arg1);
                    break;
                case 3:
                    setStopReason((String) message.obj, message.arg1);
                    break;
                case 4:
                    setMaxParallelDownloads(message.arg1);
                    break;
                case 5:
                    setMinRetryCount(message.arg1);
                    break;
                case 6:
                    addDownload((DownloadRequest) message.obj, message.arg1);
                    break;
                case 7:
                    removeDownload((String) message.obj);
                    break;
                case 8:
                    removeAllDownloads();
                    break;
                case 9:
                    onTaskStopped((Task) message.obj);
                    break;
                case 10:
                    onContentLengthChanged((Task) message.obj);
                    return;
                case 11:
                    updateProgress();
                    return;
                case 12:
                    release();
                    return;
                default:
                    throw new IllegalStateException();
            }
            z = true;
            this.mainHandler.obtainMessage(1, z ? 1 : 0, this.activeTasks.size()).sendToTarget();
        }

        private void initialize(int i) {
            this.notMetRequirements = i;
            DownloadCursor downloadCursor = null;
            try {
                this.downloadIndex.setDownloadingStatesToQueued();
                downloadCursor = this.downloadIndex.getDownloads(0, 1, 2, 5, 7);
                while (downloadCursor.moveToNext()) {
                    this.downloads.add(downloadCursor.getDownload());
                }
            } catch (IOException e) {
                Log.m6e(DownloadManager.TAG, "Failed to load index.", e);
                this.downloads.clear();
            } catch (Throwable th) {
                Util.closeQuietly((Closeable) null);
                throw th;
            }
            Util.closeQuietly((Closeable) downloadCursor);
            this.mainHandler.obtainMessage(0, new ArrayList(this.downloads)).sendToTarget();
            syncTasks();
        }

        private void setDownloadsPaused(boolean z) {
            this.downloadsPaused = z;
            syncTasks();
        }

        private void setNotMetRequirements(int i) {
            this.notMetRequirements = i;
            syncTasks();
        }

        private void setStopReason(String str, int i) {
            if (str == null) {
                for (int i2 = 0; i2 < this.downloads.size(); i2++) {
                    setStopReason(this.downloads.get(i2), i);
                }
                try {
                    this.downloadIndex.setStopReason(i);
                } catch (IOException e) {
                    Log.m6e(DownloadManager.TAG, "Failed to set manual stop reason", e);
                }
            } else {
                Download download = getDownload(str, false);
                if (download != null) {
                    setStopReason(download, i);
                } else {
                    try {
                        this.downloadIndex.setStopReason(str, i);
                    } catch (IOException e2) {
                        Log.m6e(DownloadManager.TAG, "Failed to set manual stop reason: " + str, e2);
                    }
                }
            }
            syncTasks();
        }

        private void setStopReason(Download download, int i) {
            Download download2 = download;
            int i2 = i;
            if (i2 == 0) {
                if (download2.state == 1) {
                    putDownloadWithState(download, 0);
                }
            } else if (i2 != download2.stopReason) {
                int i3 = download2.state;
                if (i3 == 0 || i3 == 2) {
                    i3 = 1;
                }
                putDownload(new Download(download2.request, i3, download2.startTimeMs, System.currentTimeMillis(), download2.contentLength, i, 0, download2.progress));
            }
        }

        private void setMaxParallelDownloads(int i) {
            this.maxParallelDownloads = i;
            syncTasks();
        }

        private void setMinRetryCount(int i) {
            this.minRetryCount = i;
        }

        private void addDownload(DownloadRequest downloadRequest, int i) {
            Download download = getDownload(downloadRequest.f5073id, true);
            long currentTimeMillis = System.currentTimeMillis();
            if (download != null) {
                putDownload(DownloadManager.mergeRequest(download, downloadRequest, i, currentTimeMillis));
            } else {
                putDownload(new Download(downloadRequest, i != 0 ? 1 : 0, currentTimeMillis, currentTimeMillis, -1, i, 0));
            }
            syncTasks();
        }

        private void removeDownload(String str) {
            Download download = getDownload(str, true);
            if (download == null) {
                Log.m5e(DownloadManager.TAG, "Failed to remove nonexistent download: " + str);
                return;
            }
            putDownloadWithState(download, 5);
            syncTasks();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:13:0x002e, code lost:
            r6 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:14:0x002f, code lost:
            if (r4 != null) goto L_0x0031;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
            r4.close();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0039, code lost:
            throw r6;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void removeAllDownloads() {
            /*
                r8 = this;
                java.lang.String r0 = "DownloadManager"
                java.util.ArrayList r1 = new java.util.ArrayList
                r1.<init>()
                r2 = 2
                r3 = 0
                com.google.android.exoplayer2.offline.WritableDownloadIndex r4 = r8.downloadIndex     // Catch:{ IOException -> 0x003a }
                int[] r5 = new int[r2]     // Catch:{ IOException -> 0x003a }
                r6 = 3
                r5[r3] = r6     // Catch:{ IOException -> 0x003a }
                r6 = 4
                r7 = 1
                r5[r7] = r6     // Catch:{ IOException -> 0x003a }
                com.google.android.exoplayer2.offline.DownloadCursor r4 = r4.getDownloads(r5)     // Catch:{ IOException -> 0x003a }
            L_0x0018:
                boolean r5 = r4.moveToNext()     // Catch:{ all -> 0x002c }
                if (r5 == 0) goto L_0x0026
                com.google.android.exoplayer2.offline.Download r5 = r4.getDownload()     // Catch:{ all -> 0x002c }
                r1.add(r5)     // Catch:{ all -> 0x002c }
                goto L_0x0018
            L_0x0026:
                if (r4 == 0) goto L_0x003f
                r4.close()     // Catch:{ IOException -> 0x003a }
                goto L_0x003f
            L_0x002c:
                r5 = move-exception
                throw r5     // Catch:{ all -> 0x002e }
            L_0x002e:
                r6 = move-exception
                if (r4 == 0) goto L_0x0039
                r4.close()     // Catch:{ all -> 0x0035 }
                goto L_0x0039
            L_0x0035:
                r4 = move-exception
                r5.addSuppressed(r4)     // Catch:{ IOException -> 0x003a }
            L_0x0039:
                throw r6     // Catch:{ IOException -> 0x003a }
            L_0x003a:
                java.lang.String r4 = "Failed to load downloads."
                com.google.android.exoplayer2.util.Log.m5e(r0, r4)
            L_0x003f:
                r4 = 0
            L_0x0040:
                java.util.ArrayList<com.google.android.exoplayer2.offline.Download> r5 = r8.downloads
                int r5 = r5.size()
                r6 = 5
                if (r4 >= r5) goto L_0x005b
                java.util.ArrayList<com.google.android.exoplayer2.offline.Download> r5 = r8.downloads
                java.lang.Object r7 = r5.get(r4)
                com.google.android.exoplayer2.offline.Download r7 = (com.google.android.exoplayer2.offline.Download) r7
                com.google.android.exoplayer2.offline.Download r6 = copyDownloadWithState(r7, r6)
                r5.set(r4, r6)
                int r4 = r4 + 1
                goto L_0x0040
            L_0x005b:
                r4 = 0
            L_0x005c:
                int r5 = r1.size()
                if (r4 >= r5) goto L_0x0074
                java.util.ArrayList<com.google.android.exoplayer2.offline.Download> r5 = r8.downloads
                java.lang.Object r7 = r1.get(r4)
                com.google.android.exoplayer2.offline.Download r7 = (com.google.android.exoplayer2.offline.Download) r7
                com.google.android.exoplayer2.offline.Download r7 = copyDownloadWithState(r7, r6)
                r5.add(r7)
                int r4 = r4 + 1
                goto L_0x005c
            L_0x0074:
                java.util.ArrayList<com.google.android.exoplayer2.offline.Download> r1 = r8.downloads
                com.google.android.exoplayer2.offline.-$$Lambda$DownloadManager$InternalHandler$NXQcmC9peGeDWV0s_8pBfzdJpS0 r4 = com.google.android.exoplayer2.offline.C5276x7c64c816.INSTANCE
                java.util.Collections.sort(r1, r4)
                com.google.android.exoplayer2.offline.WritableDownloadIndex r1 = r8.downloadIndex     // Catch:{ IOException -> 0x0081 }
                r1.setStatesToRemoving()     // Catch:{ IOException -> 0x0081 }
                goto L_0x0087
            L_0x0081:
                r1 = move-exception
                java.lang.String r4 = "Failed to update index."
                com.google.android.exoplayer2.util.Log.m6e(r0, r4, r1)
            L_0x0087:
                java.util.ArrayList r0 = new java.util.ArrayList
                java.util.ArrayList<com.google.android.exoplayer2.offline.Download> r1 = r8.downloads
                r0.<init>(r1)
                r1 = 0
            L_0x008f:
                java.util.ArrayList<com.google.android.exoplayer2.offline.Download> r4 = r8.downloads
                int r4 = r4.size()
                if (r1 >= r4) goto L_0x00b0
                com.google.android.exoplayer2.offline.DownloadManager$DownloadUpdate r4 = new com.google.android.exoplayer2.offline.DownloadManager$DownloadUpdate
                java.util.ArrayList<com.google.android.exoplayer2.offline.Download> r5 = r8.downloads
                java.lang.Object r5 = r5.get(r1)
                com.google.android.exoplayer2.offline.Download r5 = (com.google.android.exoplayer2.offline.Download) r5
                r4.<init>(r5, r3, r0)
                android.os.Handler r5 = r8.mainHandler
                android.os.Message r4 = r5.obtainMessage(r2, r4)
                r4.sendToTarget()
                int r1 = r1 + 1
                goto L_0x008f
            L_0x00b0:
                r8.syncTasks()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.offline.DownloadManager.InternalHandler.removeAllDownloads():void");
        }

        private void release() {
            for (Task cancel : this.activeTasks.values()) {
                cancel.cancel(true);
            }
            try {
                this.downloadIndex.setDownloadingStatesToQueued();
            } catch (IOException e) {
                Log.m6e(DownloadManager.TAG, "Failed to update index.", e);
            }
            this.downloads.clear();
            this.thread.quit();
            synchronized (this) {
                this.released = true;
                notifyAll();
            }
        }

        private void syncTasks() {
            int i = 0;
            for (int i2 = 0; i2 < this.downloads.size(); i2++) {
                Download download = this.downloads.get(i2);
                Task task = this.activeTasks.get(download.request.f5073id);
                int i3 = download.state;
                if (i3 == 0) {
                    task = syncQueuedDownload(task, download);
                } else if (i3 == 1) {
                    syncStoppedDownload(task);
                } else if (i3 == 2) {
                    Assertions.checkNotNull(task);
                    syncDownloadingDownload(task, download, i);
                } else if (i3 == 5 || i3 == 7) {
                    syncRemovingDownload(task, download);
                } else {
                    throw new IllegalStateException();
                }
                if (task != null && !task.isRemove) {
                    i++;
                }
            }
        }

        private void syncStoppedDownload(Task task) {
            if (task != null) {
                Assertions.checkState(!task.isRemove);
                task.cancel(false);
            }
        }

        private Task syncQueuedDownload(Task task, Download download) {
            if (task != null) {
                Assertions.checkState(!task.isRemove);
                task.cancel(false);
                return task;
            } else if (!canDownloadsRun() || this.activeDownloadTaskCount >= this.maxParallelDownloads) {
                return null;
            } else {
                Download putDownloadWithState = putDownloadWithState(download, 2);
                Task task2 = new Task(putDownloadWithState.request, this.downloaderFactory.createDownloader(putDownloadWithState.request), putDownloadWithState.progress, false, this.minRetryCount, this);
                this.activeTasks.put(putDownloadWithState.request.f5073id, task2);
                int i = this.activeDownloadTaskCount;
                this.activeDownloadTaskCount = i + 1;
                if (i == 0) {
                    sendEmptyMessageDelayed(11, 5000);
                }
                task2.start();
                return task2;
            }
        }

        private void syncDownloadingDownload(Task task, Download download, int i) {
            Assertions.checkState(!task.isRemove);
            if (!canDownloadsRun() || i >= this.maxParallelDownloads) {
                putDownloadWithState(download, 0);
                task.cancel(false);
            }
        }

        private void syncRemovingDownload(Task task, Download download) {
            if (task == null) {
                Task task2 = new Task(download.request, this.downloaderFactory.createDownloader(download.request), download.progress, true, this.minRetryCount, this);
                this.activeTasks.put(download.request.f5073id, task2);
                task2.start();
            } else if (!task.isRemove) {
                task.cancel(false);
            }
        }

        private void onContentLengthChanged(Task task) {
            String str = task.request.f5073id;
            long access$300 = task.contentLength;
            Download download = (Download) Assertions.checkNotNull(getDownload(str, false));
            if (access$300 != download.contentLength && access$300 != -1) {
                putDownload(new Download(download.request, download.state, download.startTimeMs, System.currentTimeMillis(), access$300, download.stopReason, download.failureReason, download.progress));
            }
        }

        private void onTaskStopped(Task task) {
            String str = task.request.f5073id;
            this.activeTasks.remove(str);
            boolean access$000 = task.isRemove;
            if (!access$000) {
                int i = this.activeDownloadTaskCount - 1;
                this.activeDownloadTaskCount = i;
                if (i == 0) {
                    removeMessages(11);
                }
            }
            if (task.isCanceled) {
                syncTasks();
                return;
            }
            Throwable access$500 = task.finalError;
            if (access$500 != null) {
                Log.m6e(DownloadManager.TAG, "Task failed: " + task.request + ", " + access$000, access$500);
            }
            Download download = (Download) Assertions.checkNotNull(getDownload(str, false));
            int i2 = download.state;
            if (i2 == 2) {
                Assertions.checkState(!access$000);
                onDownloadTaskStopped(download, access$500);
            } else if (i2 == 5 || i2 == 7) {
                Assertions.checkState(access$000);
                onRemoveTaskStopped(download);
            } else {
                throw new IllegalStateException();
            }
            syncTasks();
        }

        private void onDownloadTaskStopped(Download download, Throwable th) {
            Download download2 = download;
            Download download3 = new Download(download2.request, th == null ? 3 : 4, download2.startTimeMs, System.currentTimeMillis(), download2.contentLength, download2.stopReason, th == null ? 0 : 1, download2.progress);
            this.downloads.remove(getDownloadIndex(download3.request.f5073id));
            try {
                this.downloadIndex.putDownload(download3);
            } catch (IOException e) {
                Log.m6e(DownloadManager.TAG, "Failed to update index.", e);
            }
            this.mainHandler.obtainMessage(2, new DownloadUpdate(download3, false, new ArrayList(this.downloads))).sendToTarget();
        }

        private void onRemoveTaskStopped(Download download) {
            int i = 1;
            if (download.state == 7) {
                if (download.stopReason == 0) {
                    i = 0;
                }
                putDownloadWithState(download, i);
                syncTasks();
                return;
            }
            this.downloads.remove(getDownloadIndex(download.request.f5073id));
            try {
                this.downloadIndex.removeDownload(download.request.f5073id);
            } catch (IOException unused) {
                Log.m5e(DownloadManager.TAG, "Failed to remove from database");
            }
            this.mainHandler.obtainMessage(2, new DownloadUpdate(download, true, new ArrayList(this.downloads))).sendToTarget();
        }

        private void updateProgress() {
            for (int i = 0; i < this.downloads.size(); i++) {
                Download download = this.downloads.get(i);
                if (download.state == 2) {
                    try {
                        this.downloadIndex.putDownload(download);
                    } catch (IOException e) {
                        Log.m6e(DownloadManager.TAG, "Failed to update index.", e);
                    }
                }
            }
            sendEmptyMessageDelayed(11, 5000);
        }

        private boolean canDownloadsRun() {
            return !this.downloadsPaused && this.notMetRequirements == 0;
        }

        private Download putDownloadWithState(Download download, int i) {
            boolean z = true;
            if (i == 3 || i == 4 || i == 1) {
                z = false;
            }
            Assertions.checkState(z);
            return putDownload(copyDownloadWithState(download, i));
        }

        private Download putDownload(Download download) {
            boolean z = true;
            Assertions.checkState((download.state == 3 || download.state == 4) ? false : true);
            int downloadIndex2 = getDownloadIndex(download.request.f5073id);
            if (downloadIndex2 == -1) {
                this.downloads.add(download);
                Collections.sort(this.downloads, C5276x7c64c816.INSTANCE);
            } else {
                if (download.startTimeMs == this.downloads.get(downloadIndex2).startTimeMs) {
                    z = false;
                }
                this.downloads.set(downloadIndex2, download);
                if (z) {
                    Collections.sort(this.downloads, C5276x7c64c816.INSTANCE);
                }
            }
            try {
                this.downloadIndex.putDownload(download);
            } catch (IOException e) {
                Log.m6e(DownloadManager.TAG, "Failed to update index.", e);
            }
            this.mainHandler.obtainMessage(2, new DownloadUpdate(download, false, new ArrayList(this.downloads))).sendToTarget();
            return download;
        }

        private Download getDownload(String str, boolean z) {
            int downloadIndex2 = getDownloadIndex(str);
            if (downloadIndex2 != -1) {
                return this.downloads.get(downloadIndex2);
            }
            if (!z) {
                return null;
            }
            try {
                return this.downloadIndex.getDownload(str);
            } catch (IOException e) {
                Log.m6e(DownloadManager.TAG, "Failed to load download: " + str, e);
                return null;
            }
        }

        private int getDownloadIndex(String str) {
            for (int i = 0; i < this.downloads.size(); i++) {
                if (this.downloads.get(i).request.f5073id.equals(str)) {
                    return i;
                }
            }
            return -1;
        }

        private static Download copyDownloadWithState(Download download, int i) {
            return new Download(download.request, i, download.startTimeMs, System.currentTimeMillis(), download.contentLength, 0, 0, download.progress);
        }

        /* access modifiers changed from: private */
        public static int compareStartTimes(Download download, Download download2) {
            return Util.compareLong(download.startTimeMs, download2.startTimeMs);
        }
    }

    private static class Task extends Thread implements Downloader.ProgressListener {
        /* access modifiers changed from: private */
        public long contentLength;
        private final DownloadProgress downloadProgress;
        private final Downloader downloader;
        /* access modifiers changed from: private */
        public Throwable finalError;
        private volatile InternalHandler internalHandler;
        /* access modifiers changed from: private */
        public volatile boolean isCanceled;
        /* access modifiers changed from: private */
        public final boolean isRemove;
        private final int minRetryCount;
        /* access modifiers changed from: private */
        public final DownloadRequest request;

        private Task(DownloadRequest downloadRequest, Downloader downloader2, DownloadProgress downloadProgress2, boolean z, int i, InternalHandler internalHandler2) {
            this.request = downloadRequest;
            this.downloader = downloader2;
            this.downloadProgress = downloadProgress2;
            this.isRemove = z;
            this.minRetryCount = i;
            this.internalHandler = internalHandler2;
            this.contentLength = -1;
        }

        public void cancel(boolean z) {
            if (z) {
                this.internalHandler = null;
            }
            if (!this.isCanceled) {
                this.isCanceled = true;
                this.downloader.cancel();
                interrupt();
            }
        }

        public void run() {
            long j;
            int i;
            try {
                if (this.isRemove) {
                    this.downloader.remove();
                } else {
                    j = -1;
                    i = 0;
                    while (!this.isCanceled) {
                        this.downloader.download(this);
                    }
                }
            } catch (IOException e) {
                if (!this.isCanceled) {
                    long j2 = this.downloadProgress.bytesDownloaded;
                    if (j2 != j) {
                        j = j2;
                        i = 0;
                    }
                    i++;
                    if (i <= this.minRetryCount) {
                        Thread.sleep((long) getRetryDelayMillis(i));
                    } else {
                        throw e;
                    }
                }
            } catch (Throwable th) {
                this.finalError = th;
            }
            InternalHandler internalHandler2 = this.internalHandler;
            if (internalHandler2 != null) {
                internalHandler2.obtainMessage(9, this).sendToTarget();
            }
        }

        public void onProgress(long j, long j2, float f) {
            this.downloadProgress.bytesDownloaded = j2;
            this.downloadProgress.percentDownloaded = f;
            if (j != this.contentLength) {
                this.contentLength = j;
                InternalHandler internalHandler2 = this.internalHandler;
                if (internalHandler2 != null) {
                    internalHandler2.obtainMessage(10, this).sendToTarget();
                }
            }
        }

        private static int getRetryDelayMillis(int i) {
            return Math.min((i - 1) * 1000, 5000);
        }
    }

    private static final class DownloadUpdate {
        public final Download download;
        public final List<Download> downloads;
        public final boolean isRemove;

        public DownloadUpdate(Download download2, boolean z, List<Download> list) {
            this.download = download2;
            this.isRemove = z;
            this.downloads = list;
        }
    }
}

package androidx.media2.session;

import android.util.Log;
import androidx.collection.ArrayMap;
import androidx.media2.session.MediaSession;
import java.util.ArrayList;
import java.util.List;

class ConnectedControllersManager<T> {
    static final boolean DEBUG = Log.isLoggable(TAG, 3);
    static final String TAG = "MS2ControllerMgr";
    private final ArrayMap<T, MediaSession.ControllerInfo> mControllerInfoMap = new ArrayMap<>();
    private final ArrayMap<MediaSession.ControllerInfo, ConnectedControllersManager<T>.ConnectedControllerRecord> mControllerRecords = new ArrayMap<>();
    private final Object mLock = new Object();
    final MediaSession.MediaSessionImpl mSessionImpl;

    ConnectedControllersManager(MediaSession.MediaSessionImpl mediaSessionImpl) {
        this.mSessionImpl = mediaSessionImpl;
    }

    public void addController(T t, MediaSession.ControllerInfo controllerInfo, SessionCommandGroup sessionCommandGroup) {
        if (t != null && controllerInfo != null) {
            synchronized (this.mLock) {
                MediaSession.ControllerInfo controller = getController(t);
                if (controller == null) {
                    this.mControllerInfoMap.put(t, controllerInfo);
                    this.mControllerRecords.put(controllerInfo, new ConnectedControllerRecord(t, new SequencedFutureManager(), sessionCommandGroup));
                } else {
                    this.mControllerRecords.get(controller).allowedCommands = sessionCommandGroup;
                }
            }
        } else if (DEBUG) {
            throw new IllegalArgumentException("controllerKey and controllerInfo shouldn't be null");
        }
    }

    public void updateAllowedCommands(MediaSession.ControllerInfo controllerInfo, SessionCommandGroup sessionCommandGroup) {
        if (controllerInfo != null) {
            synchronized (this.mLock) {
                ConnectedControllerRecord connectedControllerRecord = this.mControllerRecords.get(controllerInfo);
                if (connectedControllerRecord != null) {
                    connectedControllerRecord.allowedCommands = sessionCommandGroup;
                }
            }
        }
    }

    public void removeController(T t) {
        if (t != null) {
            removeController(getController(t));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001c, code lost:
        if (DEBUG == false) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001e, code lost:
        android.util.Log.d(TAG, "Controller " + r5 + " is disconnected");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0039, code lost:
        r1.sequencedFutureManager.close();
        r4.mSessionImpl.getCallbackExecutor().execute(new androidx.media2.session.ConnectedControllersManager.C40821(r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x004c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void removeController(final androidx.media2.session.MediaSession.ControllerInfo r5) {
        /*
            r4 = this;
            if (r5 != 0) goto L_0x0003
            return
        L_0x0003:
            java.lang.Object r0 = r4.mLock
            monitor-enter(r0)
            androidx.collection.ArrayMap<androidx.media2.session.MediaSession$ControllerInfo, androidx.media2.session.ConnectedControllersManager<T>$ConnectedControllerRecord> r1 = r4.mControllerRecords     // Catch:{ all -> 0x004d }
            java.lang.Object r1 = r1.remove(r5)     // Catch:{ all -> 0x004d }
            androidx.media2.session.ConnectedControllersManager$ConnectedControllerRecord r1 = (androidx.media2.session.ConnectedControllersManager.ConnectedControllerRecord) r1     // Catch:{ all -> 0x004d }
            if (r1 != 0) goto L_0x0012
            monitor-exit(r0)     // Catch:{ all -> 0x004d }
            return
        L_0x0012:
            androidx.collection.ArrayMap<T, androidx.media2.session.MediaSession$ControllerInfo> r2 = r4.mControllerInfoMap     // Catch:{ all -> 0x004d }
            T r3 = r1.controllerKey     // Catch:{ all -> 0x004d }
            r2.remove(r3)     // Catch:{ all -> 0x004d }
            monitor-exit(r0)     // Catch:{ all -> 0x004d }
            boolean r0 = DEBUG
            if (r0 == 0) goto L_0x0039
            java.lang.String r0 = "MS2ControllerMgr"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Controller "
            r2.append(r3)
            r2.append(r5)
            java.lang.String r3 = " is disconnected"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            android.util.Log.d(r0, r2)
        L_0x0039:
            androidx.media2.session.SequencedFutureManager r0 = r1.sequencedFutureManager
            r0.close()
            androidx.media2.session.MediaSession$MediaSessionImpl r0 = r4.mSessionImpl
            java.util.concurrent.Executor r0 = r0.getCallbackExecutor()
            androidx.media2.session.ConnectedControllersManager$1 r1 = new androidx.media2.session.ConnectedControllersManager$1
            r1.<init>(r5)
            r0.execute(r1)
            return
        L_0x004d:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x004d }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.session.ConnectedControllersManager.removeController(androidx.media2.session.MediaSession$ControllerInfo):void");
    }

    public final List<MediaSession.ControllerInfo> getConnectedControllers() {
        ArrayList arrayList = new ArrayList();
        synchronized (this.mLock) {
            arrayList.addAll(this.mControllerInfoMap.values());
        }
        return arrayList;
    }

    public final boolean isConnected(MediaSession.ControllerInfo controllerInfo) {
        boolean z;
        synchronized (this.mLock) {
            z = this.mControllerRecords.get(controllerInfo) != null;
        }
        return z;
    }

    public final SequencedFutureManager getSequencedFutureManager(MediaSession.ControllerInfo controllerInfo) {
        ConnectedControllerRecord connectedControllerRecord;
        synchronized (this.mLock) {
            connectedControllerRecord = this.mControllerRecords.get(controllerInfo);
        }
        if (connectedControllerRecord != null) {
            return connectedControllerRecord.sequencedFutureManager;
        }
        return null;
    }

    public SequencedFutureManager getSequencedFutureManager(T t) {
        ConnectedControllerRecord connectedControllerRecord;
        synchronized (this.mLock) {
            connectedControllerRecord = this.mControllerRecords.get(getController(t));
        }
        if (connectedControllerRecord != null) {
            return connectedControllerRecord.sequencedFutureManager;
        }
        return null;
    }

    public boolean isAllowedCommand(MediaSession.ControllerInfo controllerInfo, SessionCommand sessionCommand) {
        ConnectedControllerRecord connectedControllerRecord;
        synchronized (this.mLock) {
            connectedControllerRecord = this.mControllerRecords.get(controllerInfo);
        }
        return connectedControllerRecord != null && connectedControllerRecord.allowedCommands.hasCommand(sessionCommand);
    }

    public boolean isAllowedCommand(MediaSession.ControllerInfo controllerInfo, int i) {
        ConnectedControllerRecord connectedControllerRecord;
        synchronized (this.mLock) {
            connectedControllerRecord = this.mControllerRecords.get(controllerInfo);
        }
        return connectedControllerRecord != null && connectedControllerRecord.allowedCommands.hasCommand(i);
    }

    public MediaSession.ControllerInfo getController(T t) {
        MediaSession.ControllerInfo controllerInfo;
        synchronized (this.mLock) {
            controllerInfo = this.mControllerInfoMap.get(t);
        }
        return controllerInfo;
    }

    private class ConnectedControllerRecord {
        public SessionCommandGroup allowedCommands;
        public final T controllerKey;
        public final SequencedFutureManager sequencedFutureManager;

        ConnectedControllerRecord(T t, SequencedFutureManager sequencedFutureManager2, SessionCommandGroup sessionCommandGroup) {
            this.controllerKey = t;
            this.sequencedFutureManager = sequencedFutureManager2;
            this.allowedCommands = sessionCommandGroup;
            if (sessionCommandGroup == null) {
                this.allowedCommands = new SessionCommandGroup();
            }
        }
    }
}

package androidx.media2;

import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import androidx.media.MediaBrowserServiceCompat;
import androidx.media2.MediaSessionService2;

class MediaSessionService2ImplBase implements MediaSessionService2.MediaSessionService2Impl {
    private static final boolean DEBUG = true;
    private static final String TAG = "MSS2ImplBase";
    private final Object mLock = new Object();
    private MediaSession2 mSession;

    public int getSessionType() {
        return 1;
    }

    public MediaSessionService2.MediaNotification onUpdateNotification() {
        return null;
    }

    MediaSessionService2ImplBase() {
    }

    public void onCreate(MediaSessionService2 mediaSessionService2) {
        SessionToken2 sessionToken2 = new SessionToken2(mediaSessionService2, new ComponentName(mediaSessionService2, mediaSessionService2.getClass().getName()));
        if (sessionToken2.getType() == getSessionType()) {
            MediaSession2 onCreateSession = mediaSessionService2.onCreateSession(sessionToken2.getId());
            synchronized (this.mLock) {
                this.mSession = onCreateSession;
                if (onCreateSession == null || !sessionToken2.getId().equals(this.mSession.getToken().getId()) || this.mSession.getToken().getType() != getSessionType()) {
                    this.mSession = null;
                    throw new RuntimeException("Expected session with id " + sessionToken2.getId() + " and type " + sessionToken2.getType() + ", but got " + this.mSession);
                }
            }
            return;
        }
        throw new RuntimeException("Expected session type " + getSessionType() + " but was " + sessionToken2.getType());
    }

    public IBinder onBind(Intent intent) {
        MediaSession2 session = getSession();
        if (session == null) {
            Log.w(TAG, "Session hasn't created");
            return null;
        }
        String action = intent.getAction();
        char c = 65535;
        int hashCode = action.hashCode();
        if (hashCode != 1665850838) {
            if (hashCode == 1829288728 && action.equals(MediaSessionService2.SERVICE_INTERFACE)) {
                c = 0;
            }
        } else if (action.equals(MediaBrowserServiceCompat.SERVICE_INTERFACE)) {
            c = 1;
        }
        if (c == 0) {
            return session.getSessionBinder();
        }
        if (c != 1) {
            return null;
        }
        return session.getLegacyBrowerServiceBinder();
    }

    public MediaSession2 getSession() {
        MediaSession2 mediaSession2;
        synchronized (this.mLock) {
            mediaSession2 = this.mSession;
        }
        return mediaSession2;
    }
}

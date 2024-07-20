package androidx.media2.session;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.media2.session.MediaSession;
import java.util.List;

public abstract class MediaSessionService extends Service {
    public static final String SERVICE_INTERFACE = "androidx.media2.session.MediaSessionService";
    private final MediaSessionServiceImpl mImpl = createImpl();

    interface MediaSessionServiceImpl {
        void addSession(MediaSession mediaSession);

        List<MediaSession> getSessions();

        IBinder onBind(Intent intent);

        void onCreate(MediaSessionService mediaSessionService);

        void onDestroy();

        int onStartCommand(Intent intent, int i, int i2);

        MediaNotification onUpdateNotification(MediaSession mediaSession);

        void removeSession(MediaSession mediaSession);
    }

    public abstract MediaSession onGetSession(MediaSession.ControllerInfo controllerInfo);

    /* access modifiers changed from: package-private */
    public MediaSessionServiceImpl createImpl() {
        return new MediaSessionServiceImplBase();
    }

    public void onCreate() {
        super.onCreate();
        this.mImpl.onCreate(this);
    }

    public final void addSession(MediaSession mediaSession) {
        if (mediaSession == null) {
            throw new NullPointerException("session shouldn't be null");
        } else if (!mediaSession.isClosed()) {
            this.mImpl.addSession(mediaSession);
        } else {
            throw new IllegalArgumentException("session is already closed");
        }
    }

    public final void removeSession(MediaSession mediaSession) {
        if (mediaSession != null) {
            this.mImpl.removeSession(mediaSession);
            return;
        }
        throw new NullPointerException("session shouldn't be null");
    }

    public MediaNotification onUpdateNotification(MediaSession mediaSession) {
        if (mediaSession != null) {
            return this.mImpl.onUpdateNotification(mediaSession);
        }
        throw new NullPointerException("session shouldn't be null");
    }

    public final List<MediaSession> getSessions() {
        return this.mImpl.getSessions();
    }

    public IBinder onBind(Intent intent) {
        return this.mImpl.onBind(intent);
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return this.mImpl.onStartCommand(intent, i, i2);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mImpl.onDestroy();
    }

    public static class MediaNotification {
        private final Notification mNotification;
        private final int mNotificationId;

        public MediaNotification(int i, Notification notification) {
            if (notification != null) {
                this.mNotificationId = i;
                this.mNotification = notification;
                return;
            }
            throw new NullPointerException("notification shouldn't be null");
        }

        public int getNotificationId() {
            return this.mNotificationId;
        }

        public Notification getNotification() {
            return this.mNotification;
        }
    }
}

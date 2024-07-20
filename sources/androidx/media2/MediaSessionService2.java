package androidx.media2;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public abstract class MediaSessionService2 extends Service {
    public static final String SERVICE_INTERFACE = "android.media.MediaSessionService2";
    public static final String SERVICE_META_DATA_SESSION_ID = "android.media.session2.SESSION_ID";
    private final MediaSessionService2Impl mImpl = createImpl();

    interface MediaSessionService2Impl {
        MediaSession2 getSession();

        int getSessionType();

        IBinder onBind(Intent intent);

        void onCreate(MediaSessionService2 mediaSessionService2);

        MediaNotification onUpdateNotification();
    }

    public abstract MediaSession2 onCreateSession(String str);

    /* access modifiers changed from: package-private */
    public MediaSessionService2Impl createImpl() {
        return new MediaSessionService2ImplBase();
    }

    public void onCreate() {
        super.onCreate();
        this.mImpl.onCreate(this);
    }

    public MediaNotification onUpdateNotification() {
        return this.mImpl.onUpdateNotification();
    }

    public final MediaSession2 getSession() {
        return this.mImpl.getSession();
    }

    public IBinder onBind(Intent intent) {
        return this.mImpl.onBind(intent);
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
            throw new IllegalArgumentException("notification shouldn't be null");
        }

        public int getNotificationId() {
            return this.mNotificationId;
        }

        public Notification getNotification() {
            return this.mNotification;
        }
    }
}

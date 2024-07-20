package androidx.media2;

import android.content.Intent;
import android.os.IBinder;
import androidx.media2.MediaLibraryService2;

class MediaLibraryService2ImplBase extends MediaSessionService2ImplBase {
    public int getSessionType() {
        return 2;
    }

    MediaLibraryService2ImplBase() {
    }

    public IBinder onBind(Intent intent) {
        if (MediaLibraryService2.SERVICE_INTERFACE.equals(intent.getAction())) {
            return getSession().getSessionBinder();
        }
        return super.onBind(intent);
    }

    public MediaLibraryService2.MediaLibrarySession getSession() {
        return (MediaLibraryService2.MediaLibrarySession) super.getSession();
    }
}

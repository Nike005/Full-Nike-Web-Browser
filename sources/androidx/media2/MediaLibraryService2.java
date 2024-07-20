package androidx.media2;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import androidx.media2.MediaSession2;
import androidx.media2.MediaSessionService2;
import java.util.List;
import java.util.concurrent.Executor;

public abstract class MediaLibraryService2 extends MediaSessionService2 {
    public static final String SERVICE_INTERFACE = "android.media.MediaLibraryService2";

    public abstract MediaLibrarySession onCreateSession(String str);

    public static final class MediaLibrarySession extends MediaSession2 {

        public static class MediaLibrarySessionCallback extends MediaSession2.SessionCallback {
            public List<MediaItem2> onGetChildren(MediaLibrarySession mediaLibrarySession, MediaSession2.ControllerInfo controllerInfo, String str, int i, int i2, Bundle bundle) {
                return null;
            }

            public MediaItem2 onGetItem(MediaLibrarySession mediaLibrarySession, MediaSession2.ControllerInfo controllerInfo, String str) {
                return null;
            }

            public LibraryRoot onGetLibraryRoot(MediaLibrarySession mediaLibrarySession, MediaSession2.ControllerInfo controllerInfo, Bundle bundle) {
                return null;
            }

            public List<MediaItem2> onGetSearchResult(MediaLibrarySession mediaLibrarySession, MediaSession2.ControllerInfo controllerInfo, String str, int i, int i2, Bundle bundle) {
                return null;
            }

            public void onSearch(MediaLibrarySession mediaLibrarySession, MediaSession2.ControllerInfo controllerInfo, String str, Bundle bundle) {
            }

            public void onSubscribe(MediaLibrarySession mediaLibrarySession, MediaSession2.ControllerInfo controllerInfo, String str, Bundle bundle) {
            }

            public void onUnsubscribe(MediaLibrarySession mediaLibrarySession, MediaSession2.ControllerInfo controllerInfo, String str) {
            }
        }

        interface MediaLibrarySessionImpl extends MediaSession2.MediaSession2Impl {
            MediaLibrarySessionCallback getCallback();

            MediaLibrarySession getInstance();

            void notifyChildrenChanged(MediaSession2.ControllerInfo controllerInfo, String str, int i, Bundle bundle);

            void notifyChildrenChanged(String str, int i, Bundle bundle);

            void notifySearchResultChanged(MediaSession2.ControllerInfo controllerInfo, String str, int i, Bundle bundle);

            void onGetChildrenOnExecutor(MediaSession2.ControllerInfo controllerInfo, String str, int i, int i2, Bundle bundle);

            void onGetItemOnExecutor(MediaSession2.ControllerInfo controllerInfo, String str);

            void onGetLibraryRootOnExecutor(MediaSession2.ControllerInfo controllerInfo, Bundle bundle);

            void onGetSearchResultOnExecutor(MediaSession2.ControllerInfo controllerInfo, String str, int i, int i2, Bundle bundle);

            void onSearchOnExecutor(MediaSession2.ControllerInfo controllerInfo, String str, Bundle bundle);

            void onSubscribeOnExecutor(MediaSession2.ControllerInfo controllerInfo, String str, Bundle bundle);

            void onUnsubscribeOnExecutor(MediaSession2.ControllerInfo controllerInfo, String str);
        }

        public static final class Builder extends MediaSession2.BuilderBase<MediaLibrarySession, Builder, MediaLibrarySessionCallback> {
            public Builder(MediaLibraryService2 mediaLibraryService2, Executor executor, MediaLibrarySessionCallback mediaLibrarySessionCallback) {
                super(mediaLibraryService2);
                setSessionCallback(executor, mediaLibrarySessionCallback);
            }

            public Builder setPlayer(MediaPlayerConnector mediaPlayerConnector) {
                return (Builder) super.setPlayer(mediaPlayerConnector);
            }

            public Builder setPlaylistAgent(MediaPlaylistAgent mediaPlaylistAgent) {
                return (Builder) super.setPlaylistAgent(mediaPlaylistAgent);
            }

            public Builder setSessionActivity(PendingIntent pendingIntent) {
                return (Builder) super.setSessionActivity(pendingIntent);
            }

            public Builder setId(String str) {
                return (Builder) super.setId(str);
            }

            public MediaLibrarySession build() {
                if (this.mCallbackExecutor == null) {
                    this.mCallbackExecutor = new MediaSession2.MainHandlerExecutor(this.mContext);
                }
                if (this.mCallback == null) {
                    this.mCallback = new MediaLibrarySessionCallback() {
                    };
                }
                return new MediaLibrarySession(this.mContext, this.mId, this.mPlayer, this.mPlaylistAgent, this.mSessionActivity, this.mCallbackExecutor, this.mCallback);
            }
        }

        MediaLibrarySession(Context context, String str, MediaPlayerConnector mediaPlayerConnector, MediaPlaylistAgent mediaPlaylistAgent, PendingIntent pendingIntent, Executor executor, MediaSession2.SessionCallback sessionCallback) {
            super(context, str, mediaPlayerConnector, mediaPlaylistAgent, pendingIntent, executor, sessionCallback);
        }

        /* access modifiers changed from: package-private */
        public MediaLibrarySessionImpl createImpl(Context context, String str, MediaPlayerConnector mediaPlayerConnector, MediaPlaylistAgent mediaPlaylistAgent, PendingIntent pendingIntent, Executor executor, MediaSession2.SessionCallback sessionCallback) {
            return new MediaLibrarySessionImplBase(this, context, str, mediaPlayerConnector, mediaPlaylistAgent, pendingIntent, executor, sessionCallback);
        }

        /* access modifiers changed from: package-private */
        public MediaLibrarySessionImpl getImpl() {
            return (MediaLibrarySessionImpl) super.getImpl();
        }

        public void notifyChildrenChanged(MediaSession2.ControllerInfo controllerInfo, String str, int i, Bundle bundle) {
            getImpl().notifyChildrenChanged(controllerInfo, str, i, bundle);
        }

        public void notifyChildrenChanged(String str, int i, Bundle bundle) {
            getImpl().notifyChildrenChanged(str, i, bundle);
        }

        public void notifySearchResultChanged(MediaSession2.ControllerInfo controllerInfo, String str, int i, Bundle bundle) {
            getImpl().notifySearchResultChanged(controllerInfo, str, i, bundle);
        }

        /* access modifiers changed from: package-private */
        public MediaLibrarySessionCallback getCallback() {
            return (MediaLibrarySessionCallback) super.getCallback();
        }
    }

    /* access modifiers changed from: package-private */
    public MediaSessionService2.MediaSessionService2Impl createImpl() {
        return new MediaLibraryService2ImplBase();
    }

    public void onCreate() {
        super.onCreate();
        if (!(getSession() instanceof MediaLibrarySession)) {
            throw new RuntimeException("Expected MediaLibrarySession, but returned MediaSession2");
        }
    }

    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    public static final class LibraryRoot {
        public static final String EXTRA_OFFLINE = "android.media.extra.OFFLINE";
        public static final String EXTRA_RECENT = "android.media.extra.RECENT";
        public static final String EXTRA_SUGGESTED = "android.media.extra.SUGGESTED";
        private final Bundle mExtras;
        private final String mRootId;

        public LibraryRoot(String str, Bundle bundle) {
            if (str != null) {
                this.mRootId = str;
                this.mExtras = bundle;
                return;
            }
            throw new IllegalArgumentException("rootId shouldn't be null");
        }

        public String getRootId() {
            return this.mRootId;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }
    }
}

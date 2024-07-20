package androidx.media2.session;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import androidx.media2.common.SessionPlayer;
import androidx.media2.session.MediaSession;
import androidx.media2.session.MediaSessionService;
import androidx.versionedparcelable.VersionedParcelable;
import java.util.concurrent.Executor;

public abstract class MediaLibraryService extends MediaSessionService {
    public static final String SERVICE_INTERFACE = "androidx.media2.session.MediaLibraryService";

    public abstract MediaLibrarySession onGetSession(MediaSession.ControllerInfo controllerInfo);

    public static final class MediaLibrarySession extends MediaSession {

        interface MediaLibrarySessionImpl extends MediaSession.MediaSessionImpl {
            MediaLibrarySessionCallback getCallback();

            MediaLibrarySession getInstance();

            void notifyChildrenChanged(MediaSession.ControllerInfo controllerInfo, String str, int i, LibraryParams libraryParams);

            void notifyChildrenChanged(String str, int i, LibraryParams libraryParams);

            void notifySearchResultChanged(MediaSession.ControllerInfo controllerInfo, String str, int i, LibraryParams libraryParams);

            LibraryResult onGetChildrenOnExecutor(MediaSession.ControllerInfo controllerInfo, String str, int i, int i2, LibraryParams libraryParams);

            LibraryResult onGetItemOnExecutor(MediaSession.ControllerInfo controllerInfo, String str);

            LibraryResult onGetLibraryRootOnExecutor(MediaSession.ControllerInfo controllerInfo, LibraryParams libraryParams);

            LibraryResult onGetSearchResultOnExecutor(MediaSession.ControllerInfo controllerInfo, String str, int i, int i2, LibraryParams libraryParams);

            int onSearchOnExecutor(MediaSession.ControllerInfo controllerInfo, String str, LibraryParams libraryParams);

            int onSubscribeOnExecutor(MediaSession.ControllerInfo controllerInfo, String str, LibraryParams libraryParams);

            int onUnsubscribeOnExecutor(MediaSession.ControllerInfo controllerInfo, String str);
        }

        public static class MediaLibrarySessionCallback extends MediaSession.SessionCallback {
            public int onSearch(MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, String str, LibraryParams libraryParams) {
                return -6;
            }

            public int onSubscribe(MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, String str, LibraryParams libraryParams) {
                return -6;
            }

            public int onUnsubscribe(MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, String str) {
                return -6;
            }

            public LibraryResult onGetLibraryRoot(MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, LibraryParams libraryParams) {
                return new LibraryResult(-6);
            }

            public LibraryResult onGetItem(MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, String str) {
                return new LibraryResult(-6);
            }

            public LibraryResult onGetChildren(MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, String str, int i, int i2, LibraryParams libraryParams) {
                return new LibraryResult(-6);
            }

            public LibraryResult onGetSearchResult(MediaLibrarySession mediaLibrarySession, MediaSession.ControllerInfo controllerInfo, String str, int i, int i2, LibraryParams libraryParams) {
                return new LibraryResult(-6);
            }
        }

        public static final class Builder extends MediaSession.BuilderBase<MediaLibrarySession, Builder, MediaLibrarySessionCallback> {
            public Builder(MediaLibraryService mediaLibraryService, SessionPlayer sessionPlayer, Executor executor, MediaLibrarySessionCallback mediaLibrarySessionCallback) {
                super(mediaLibraryService, sessionPlayer);
                setSessionCallback(executor, mediaLibrarySessionCallback);
            }

            public Builder setSessionActivity(PendingIntent pendingIntent) {
                return (Builder) super.setSessionActivity(pendingIntent);
            }

            public Builder setId(String str) {
                return (Builder) super.setId(str);
            }

            public Builder setExtras(Bundle bundle) {
                return (Builder) super.setExtras(bundle);
            }

            public MediaLibrarySession build() {
                if (this.mCallbackExecutor == null) {
                    this.mCallbackExecutor = ContextCompat.getMainExecutor(this.mContext);
                }
                if (this.mCallback == null) {
                    this.mCallback = new MediaLibrarySessionCallback() {
                    };
                }
                return new MediaLibrarySession(this.mContext, this.mId, this.mPlayer, this.mSessionActivity, this.mCallbackExecutor, this.mCallback, this.mExtras);
            }
        }

        MediaLibrarySession(Context context, String str, SessionPlayer sessionPlayer, PendingIntent pendingIntent, Executor executor, MediaSession.SessionCallback sessionCallback, Bundle bundle) {
            super(context, str, sessionPlayer, pendingIntent, executor, sessionCallback, bundle);
        }

        /* access modifiers changed from: package-private */
        public MediaLibrarySessionImpl createImpl(Context context, String str, SessionPlayer sessionPlayer, PendingIntent pendingIntent, Executor executor, MediaSession.SessionCallback sessionCallback, Bundle bundle) {
            return new MediaLibrarySessionImplBase(this, context, str, sessionPlayer, pendingIntent, executor, sessionCallback, bundle);
        }

        /* access modifiers changed from: package-private */
        public MediaLibrarySessionImpl getImpl() {
            return (MediaLibrarySessionImpl) super.getImpl();
        }

        public void notifyChildrenChanged(MediaSession.ControllerInfo controllerInfo, String str, int i, LibraryParams libraryParams) {
            if (controllerInfo == null) {
                throw new NullPointerException("controller shouldn't be null");
            } else if (str == null) {
                throw new NullPointerException("parentId shouldn't be null");
            } else if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("parentId shouldn't be empty");
            } else if (i >= 0) {
                getImpl().notifyChildrenChanged(controllerInfo, str, i, libraryParams);
            } else {
                throw new IllegalArgumentException("itemCount shouldn't be negative");
            }
        }

        public void notifyChildrenChanged(String str, int i, LibraryParams libraryParams) {
            if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("parentId shouldn't be empty");
            } else if (i >= 0) {
                getImpl().notifyChildrenChanged(str, i, libraryParams);
            } else {
                throw new IllegalArgumentException("itemCount shouldn't be negative");
            }
        }

        public void notifySearchResultChanged(MediaSession.ControllerInfo controllerInfo, String str, int i, LibraryParams libraryParams) {
            if (controllerInfo == null) {
                throw new NullPointerException("controller shouldn't be null");
            } else if (str == null) {
                throw new NullPointerException("query shouldn't be null");
            } else if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("query shouldn't be empty");
            } else if (i >= 0) {
                getImpl().notifySearchResultChanged(controllerInfo, str, i, libraryParams);
            } else {
                throw new IllegalArgumentException("itemCount shouldn't be negative");
            }
        }

        /* access modifiers changed from: package-private */
        public MediaLibrarySessionCallback getCallback() {
            return (MediaLibrarySessionCallback) super.getCallback();
        }
    }

    /* access modifiers changed from: package-private */
    public MediaSessionService.MediaSessionServiceImpl createImpl() {
        return new MediaLibraryServiceImplBase();
    }

    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    public static final class LibraryParams implements VersionedParcelable {
        Bundle mBundle;
        int mOffline;
        int mRecent;
        int mSuggested;

        private static boolean convertToBoolean(int i) {
            return i != 0;
        }

        private static int convertToInteger(boolean z) {
            return z ? 1 : 0;
        }

        LibraryParams() {
        }

        LibraryParams(Bundle bundle, boolean z, boolean z2, boolean z3) {
            this(bundle, convertToInteger(z), convertToInteger(z2), convertToInteger(z3));
        }

        private LibraryParams(Bundle bundle, int i, int i2, int i3) {
            this.mBundle = bundle;
            this.mRecent = i;
            this.mOffline = i2;
            this.mSuggested = i3;
        }

        public boolean isRecent() {
            return convertToBoolean(this.mRecent);
        }

        public boolean isOffline() {
            return convertToBoolean(this.mOffline);
        }

        public boolean isSuggested() {
            return convertToBoolean(this.mSuggested);
        }

        public Bundle getExtras() {
            return this.mBundle;
        }

        public static final class Builder {
            private Bundle mBundle;
            private boolean mOffline;
            private boolean mRecent;
            private boolean mSuggested;

            public Builder setRecent(boolean z) {
                this.mRecent = z;
                return this;
            }

            public Builder setOffline(boolean z) {
                this.mOffline = z;
                return this;
            }

            public Builder setSuggested(boolean z) {
                this.mSuggested = z;
                return this;
            }

            public Builder setExtras(Bundle bundle) {
                this.mBundle = bundle;
                return this;
            }

            public LibraryParams build() {
                return new LibraryParams(this.mBundle, this.mRecent, this.mOffline, this.mSuggested);
            }
        }
    }
}

package androidx.media2.session;

import android.content.Context;
import android.os.Bundle;
import android.support.p064v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.Log;
import androidx.media2.session.MediaController;
import androidx.media2.session.MediaLibraryService;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.Executor;

public class MediaBrowser extends MediaController {
    static final boolean DEBUG = Log.isLoggable(TAG, 3);
    static final String TAG = "MediaBrowser";

    public static class BrowserCallback extends MediaController.ControllerCallback {
        public void onChildrenChanged(MediaBrowser mediaBrowser, String str, int i, MediaLibraryService.LibraryParams libraryParams) {
        }

        public void onSearchResultChanged(MediaBrowser mediaBrowser, String str, int i, MediaLibraryService.LibraryParams libraryParams) {
        }
    }

    interface BrowserCallbackRunnable {
        void run(BrowserCallback browserCallback);
    }

    interface MediaBrowserImpl extends MediaController.MediaControllerImpl {
        ListenableFuture<LibraryResult> getChildren(String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams);

        ListenableFuture<LibraryResult> getItem(String str);

        ListenableFuture<LibraryResult> getLibraryRoot(MediaLibraryService.LibraryParams libraryParams);

        ListenableFuture<LibraryResult> getSearchResult(String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams);

        ListenableFuture<LibraryResult> search(String str, MediaLibraryService.LibraryParams libraryParams);

        ListenableFuture<LibraryResult> subscribe(String str, MediaLibraryService.LibraryParams libraryParams);

        ListenableFuture<LibraryResult> unsubscribe(String str);
    }

    MediaBrowser(Context context, SessionToken sessionToken, Bundle bundle, Executor executor, BrowserCallback browserCallback) {
        super(context, sessionToken, bundle, executor, (MediaController.ControllerCallback) browserCallback);
    }

    MediaBrowser(Context context, MediaSessionCompat.Token token, Bundle bundle, Executor executor, BrowserCallback browserCallback) {
        super(context, token, bundle, executor, (MediaController.ControllerCallback) browserCallback);
    }

    /* access modifiers changed from: package-private */
    public MediaBrowserImpl createImpl(Context context, SessionToken sessionToken, Bundle bundle) {
        if (sessionToken.isLegacySession()) {
            return new MediaBrowserImplLegacy(context, this, sessionToken);
        }
        return new MediaBrowserImplBase(context, this, sessionToken, bundle);
    }

    /* access modifiers changed from: package-private */
    public MediaBrowserImpl getImpl() {
        return (MediaBrowserImpl) super.getImpl();
    }

    public ListenableFuture<LibraryResult> getLibraryRoot(MediaLibraryService.LibraryParams libraryParams) {
        if (isConnected()) {
            return getImpl().getLibraryRoot(libraryParams);
        }
        return createDisconnectedFuture();
    }

    public ListenableFuture<LibraryResult> subscribe(String str, MediaLibraryService.LibraryParams libraryParams) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId shouldn't be empty");
        } else if (isConnected()) {
            return getImpl().subscribe(str, libraryParams);
        } else {
            return createDisconnectedFuture();
        }
    }

    public ListenableFuture<LibraryResult> unsubscribe(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId shouldn't be empty");
        } else if (isConnected()) {
            return getImpl().unsubscribe(str);
        } else {
            return createDisconnectedFuture();
        }
    }

    public ListenableFuture<LibraryResult> getChildren(String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("parentId shouldn't be empty");
        } else if (i < 0) {
            throw new IllegalArgumentException("page shouldn't be negative");
        } else if (i2 < 1) {
            throw new IllegalArgumentException("pageSize shouldn't be less than 1");
        } else if (isConnected()) {
            return getImpl().getChildren(str, i, i2, libraryParams);
        } else {
            return createDisconnectedFuture();
        }
    }

    public ListenableFuture<LibraryResult> getItem(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("mediaId shouldn't be empty");
        } else if (isConnected()) {
            return getImpl().getItem(str);
        } else {
            return createDisconnectedFuture();
        }
    }

    public ListenableFuture<LibraryResult> search(String str, MediaLibraryService.LibraryParams libraryParams) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("query shouldn't be empty");
        } else if (isConnected()) {
            return getImpl().search(str, libraryParams);
        } else {
            return createDisconnectedFuture();
        }
    }

    public ListenableFuture<LibraryResult> getSearchResult(String str, int i, int i2, MediaLibraryService.LibraryParams libraryParams) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("query shouldn't be empty");
        } else if (i < 0) {
            throw new IllegalArgumentException("page shouldn't be negative");
        } else if (i2 < 1) {
            throw new IllegalArgumentException("pageSize shouldn't be less than 1");
        } else if (isConnected()) {
            return getImpl().getSearchResult(str, i, i2, libraryParams);
        } else {
            return createDisconnectedFuture();
        }
    }

    /* access modifiers changed from: package-private */
    public void notifyBrowserCallback(final BrowserCallbackRunnable browserCallbackRunnable) {
        if (this.mCallback != null && this.mCallbackExecutor != null) {
            this.mCallbackExecutor.execute(new Runnable() {
                public void run() {
                    browserCallbackRunnable.run((BrowserCallback) MediaBrowser.this.mCallback);
                }
            });
        }
    }

    public static final class Builder extends MediaController.BuilderBase<MediaBrowser, Builder, BrowserCallback> {
        public Builder(Context context) {
            super(context);
        }

        public Builder setSessionToken(SessionToken sessionToken) {
            return (Builder) super.setSessionToken(sessionToken);
        }

        public Builder setSessionCompatToken(MediaSessionCompat.Token token) {
            return (Builder) super.setSessionCompatToken(token);
        }

        public Builder setControllerCallback(Executor executor, BrowserCallback browserCallback) {
            return (Builder) super.setControllerCallback(executor, browserCallback);
        }

        public Builder setConnectionHints(Bundle bundle) {
            return (Builder) super.setConnectionHints(bundle);
        }

        public MediaBrowser build() {
            if (this.mToken == null && this.mCompatToken == null) {
                throw new IllegalArgumentException("token and compat token shouldn't be both null");
            } else if (this.mToken != null) {
                return new MediaBrowser(this.mContext, this.mToken, this.mConnectionHints, this.mCallbackExecutor, (BrowserCallback) this.mCallback);
            } else {
                return new MediaBrowser(this.mContext, this.mCompatToken, this.mConnectionHints, this.mCallbackExecutor, (BrowserCallback) this.mCallback);
            }
        }
    }

    private static ListenableFuture<LibraryResult> createDisconnectedFuture() {
        return LibraryResult.createFutureWithResult(-100);
    }
}

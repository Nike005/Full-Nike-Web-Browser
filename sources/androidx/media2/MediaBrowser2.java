package androidx.media2;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.media2.MediaController2;
import java.util.List;
import java.util.concurrent.Executor;

public class MediaBrowser2 extends MediaController2 {
    static final boolean DEBUG = Log.isLoggable(TAG, 3);
    static final String TAG = "MediaBrowser2";

    public static class BrowserCallback extends MediaController2.ControllerCallback {
        public void onChildrenChanged(MediaBrowser2 mediaBrowser2, String str, int i, Bundle bundle) {
        }

        public void onGetChildrenDone(MediaBrowser2 mediaBrowser2, String str, int i, int i2, List<MediaItem2> list, Bundle bundle) {
        }

        public void onGetItemDone(MediaBrowser2 mediaBrowser2, String str, MediaItem2 mediaItem2) {
        }

        public void onGetLibraryRootDone(MediaBrowser2 mediaBrowser2, Bundle bundle, String str, Bundle bundle2) {
        }

        public void onGetSearchResultDone(MediaBrowser2 mediaBrowser2, String str, int i, int i2, List<MediaItem2> list, Bundle bundle) {
        }

        public void onSearchResultChanged(MediaBrowser2 mediaBrowser2, String str, int i, Bundle bundle) {
        }
    }

    interface MediaBrowser2Impl extends MediaController2.MediaController2Impl {
        void getChildren(String str, int i, int i2, Bundle bundle);

        void getItem(String str);

        void getLibraryRoot(Bundle bundle);

        void getSearchResult(String str, int i, int i2, Bundle bundle);

        void search(String str, Bundle bundle);

        void subscribe(String str, Bundle bundle);

        void unsubscribe(String str);
    }

    public MediaBrowser2(Context context, SessionToken2 sessionToken2, Executor executor, BrowserCallback browserCallback) {
        super(context, sessionToken2, executor, browserCallback);
    }

    /* access modifiers changed from: package-private */
    public MediaBrowser2Impl createImpl(Context context, SessionToken2 sessionToken2, Executor executor, MediaController2.ControllerCallback controllerCallback) {
        if (sessionToken2.isLegacySession()) {
            return new MediaBrowser2ImplLegacy(context, this, sessionToken2, executor, (BrowserCallback) controllerCallback);
        }
        return new MediaBrowser2ImplBase(context, this, sessionToken2, executor, (BrowserCallback) controllerCallback);
    }

    /* access modifiers changed from: package-private */
    public MediaBrowser2Impl getImpl() {
        return (MediaBrowser2Impl) super.getImpl();
    }

    /* access modifiers changed from: package-private */
    public BrowserCallback getCallback() {
        return (BrowserCallback) super.getCallback();
    }

    public void getLibraryRoot(Bundle bundle) {
        getImpl().getLibraryRoot(bundle);
    }

    public void subscribe(String str, Bundle bundle) {
        getImpl().subscribe(str, bundle);
    }

    public void unsubscribe(String str) {
        getImpl().unsubscribe(str);
    }

    public void getChildren(String str, int i, int i2, Bundle bundle) {
        getImpl().getChildren(str, i, i2, bundle);
    }

    public void getItem(String str) {
        getImpl().getItem(str);
    }

    public void search(String str, Bundle bundle) {
        getImpl().search(str, bundle);
    }

    public void getSearchResult(String str, int i, int i2, Bundle bundle) {
        getImpl().getSearchResult(str, i, i2, bundle);
    }
}

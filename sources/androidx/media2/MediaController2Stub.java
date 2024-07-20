package androidx.media2;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.text.TextUtils;
import android.util.Log;
import androidx.media2.IMediaController2;
import androidx.media2.MediaController2;
import androidx.media2.MediaSession2;
import androidx.versionedparcelable.ParcelImpl;
import androidx.versionedparcelable.ParcelUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

class MediaController2Stub extends IMediaController2.Stub {
    private static final boolean DEBUG = true;
    private static final String TAG = "MediaController2Stub";
    private final WeakReference<MediaController2ImplBase> mController;

    MediaController2Stub(MediaController2ImplBase mediaController2ImplBase) {
        this.mController = new WeakReference<>(mediaController2ImplBase);
    }

    public void onCurrentMediaItemChanged(ParcelImpl parcelImpl) {
        try {
            getController().notifyCurrentMediaItemChanged((MediaItem2) ParcelUtils.fromParcelable(parcelImpl));
        } catch (IllegalStateException unused) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    public void onPlayerStateChanged(long j, long j2, int i) {
        try {
            getController().notifyPlayerStateChanges(j, j2, i);
        } catch (IllegalStateException unused) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    public void onPlaybackSpeedChanged(long j, long j2, float f) {
        try {
            getController().notifyPlaybackSpeedChanges(j, j2, f);
        } catch (IllegalStateException unused) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    public void onBufferingStateChanged(ParcelImpl parcelImpl, int i, long j) {
        try {
            getController().notifyBufferingStateChanged((MediaItem2) ParcelUtils.fromParcelable(parcelImpl), i, j);
        } catch (IllegalStateException unused) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    public void onPlaylistChanged(List<ParcelImpl> list, Bundle bundle) {
        try {
            MediaController2ImplBase controller = getController();
            if (list == null) {
                Log.w(TAG, "onPlaylistChanged(): Ignoring null playlist from " + controller);
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (ParcelImpl fromParcelable : list) {
                MediaItem2 mediaItem2 = (MediaItem2) ParcelUtils.fromParcelable(fromParcelable);
                if (mediaItem2 == null) {
                    Log.w(TAG, "onPlaylistChanged(): Ignoring null item in playlist");
                } else {
                    arrayList.add(mediaItem2);
                }
            }
            controller.notifyPlaylistChanges(arrayList, MediaMetadata2.fromBundle(bundle));
        } catch (IllegalStateException unused) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    public void onPlaylistMetadataChanged(Bundle bundle) throws RuntimeException {
        try {
            getController().notifyPlaylistMetadataChanges(MediaMetadata2.fromBundle(bundle));
        } catch (IllegalStateException unused) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    public void onRepeatModeChanged(int i) {
        try {
            getController().notifyRepeatModeChanges(i);
        } catch (IllegalStateException unused) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    public void onShuffleModeChanged(int i) {
        try {
            getController().notifyShuffleModeChanges(i);
        } catch (IllegalStateException unused) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    public void onPlaybackInfoChanged(ParcelImpl parcelImpl) throws RuntimeException {
        Log.d(TAG, "onPlaybackInfoChanged");
        try {
            MediaController2ImplBase controller = getController();
            MediaController2.PlaybackInfo playbackInfo = (MediaController2.PlaybackInfo) ParcelUtils.fromParcelable(parcelImpl);
            if (playbackInfo == null) {
                Log.w(TAG, "onPlaybackInfoChanged(): Ignoring null playbackInfo");
            } else {
                controller.notifyPlaybackInfoChanges(playbackInfo);
            }
        } catch (IllegalStateException unused) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    public void onSeekCompleted(long j, long j2, long j3) {
        try {
            getController().notifySeekCompleted(j, j2, j3);
        } catch (IllegalStateException unused) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    public void onError(int i, Bundle bundle) {
        try {
            getController().notifyError(i, bundle);
        } catch (IllegalStateException unused) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    public void onRoutesInfoChanged(List<Bundle> list) {
        try {
            MediaController2ImplBase controller = getController();
            MediaUtils2.keepUnparcelableBundlesOnly(list);
            controller.notifyRoutesInfoChanged(list);
        } catch (IllegalStateException unused) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    public void onConnected(IMediaSession2 iMediaSession2, ParcelImpl parcelImpl, int i, ParcelImpl parcelImpl2, long j, long j2, float f, long j3, ParcelImpl parcelImpl3, int i2, int i3, List<ParcelImpl> list, PendingIntent pendingIntent) {
        List<ParcelImpl> list2 = list;
        MediaController2ImplBase mediaController2ImplBase = (MediaController2ImplBase) this.mController.get();
        if (mediaController2ImplBase == null) {
            Log.d(TAG, "onConnected after MediaController2.close()");
            return;
        }
        ArrayList arrayList = null;
        if (list2 != null) {
            arrayList = new ArrayList();
            for (int i4 = 0; i4 < list.size(); i4++) {
                MediaItem2 mediaItem2 = (MediaItem2) ParcelUtils.fromParcelable(list2.get(i4));
                if (mediaItem2 != null) {
                    arrayList.add(mediaItem2);
                }
            }
        }
        IMediaSession2 iMediaSession22 = iMediaSession2;
        int i5 = i;
        long j4 = j;
        long j5 = j2;
        float f2 = f;
        long j6 = j3;
        mediaController2ImplBase.onConnectedNotLocked(iMediaSession22, (SessionCommandGroup2) ParcelUtils.fromParcelable(parcelImpl), i5, (MediaItem2) ParcelUtils.fromParcelable(parcelImpl2), j4, j5, f2, j6, (MediaController2.PlaybackInfo) ParcelUtils.fromParcelable(parcelImpl3), i3, i2, arrayList, pendingIntent);
    }

    public void onDisconnected() {
        MediaController2ImplBase mediaController2ImplBase = (MediaController2ImplBase) this.mController.get();
        if (mediaController2ImplBase == null) {
            Log.d(TAG, "onDisconnected after MediaController2.close()");
        } else {
            mediaController2ImplBase.getInstance().close();
        }
    }

    public void onCustomLayoutChanged(List<ParcelImpl> list) {
        if (list == null) {
            Log.w(TAG, "onCustomLayoutChanged(): Ignoring null commandButtonlist");
            return;
        }
        try {
            MediaController2ImplBase controller = getController();
            if (controller != null) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    MediaSession2.CommandButton commandButton = (MediaSession2.CommandButton) ParcelUtils.fromParcelable(list.get(i));
                    if (commandButton != null) {
                        arrayList.add(commandButton);
                    }
                }
                controller.onCustomLayoutChanged(arrayList);
            }
        } catch (IllegalStateException unused) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    public void onAllowedCommandsChanged(ParcelImpl parcelImpl) {
        try {
            MediaController2ImplBase controller = getController();
            if (controller != null) {
                SessionCommandGroup2 sessionCommandGroup2 = (SessionCommandGroup2) ParcelUtils.fromParcelable(parcelImpl);
                if (sessionCommandGroup2 == null) {
                    Log.w(TAG, "onAllowedCommandsChanged(): Ignoring null commands");
                } else {
                    controller.onAllowedCommandsChanged(sessionCommandGroup2);
                }
            }
        } catch (IllegalStateException unused) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    public void onCustomCommand(ParcelImpl parcelImpl, Bundle bundle, ResultReceiver resultReceiver) {
        try {
            MediaController2ImplBase controller = getController();
            SessionCommand2 sessionCommand2 = (SessionCommand2) ParcelUtils.fromParcelable(parcelImpl);
            if (sessionCommand2 == null) {
                Log.w(TAG, "onCustomCommand(): Ignoring null command");
            } else {
                controller.onCustomCommand(sessionCommand2, bundle, resultReceiver);
            }
        } catch (IllegalStateException unused) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    public void onGetLibraryRootDone(Bundle bundle, String str, Bundle bundle2) throws RuntimeException {
        try {
            final MediaBrowser2 browser = getBrowser();
            if (browser != null) {
                final Bundle bundle3 = bundle;
                final String str2 = str;
                final Bundle bundle4 = bundle2;
                browser.getCallbackExecutor().execute(new Runnable() {
                    public void run() {
                        browser.getCallback().onGetLibraryRootDone(browser, bundle3, str2, bundle4);
                    }
                });
            }
        } catch (IllegalStateException unused) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    public void onGetItemDone(final String str, final ParcelImpl parcelImpl) throws RuntimeException {
        if (str == null) {
            Log.w(TAG, "onGetItemDone(): Ignoring null mediaId");
            return;
        }
        try {
            final MediaBrowser2 browser = getBrowser();
            if (browser != null) {
                browser.getCallbackExecutor().execute(new Runnable() {
                    public void run() {
                        browser.getCallback().onGetItemDone(browser, str, (MediaItem2) ParcelUtils.fromParcelable(parcelImpl));
                    }
                });
            }
        } catch (IllegalStateException unused) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    public void onGetChildrenDone(String str, int i, int i2, List<ParcelImpl> list, Bundle bundle) throws RuntimeException {
        if (str == null) {
            Log.w(TAG, "onGetChildrenDone(): Ignoring null parentId");
            return;
        }
        try {
            final MediaBrowser2 browser = getBrowser();
            if (browser != null) {
                final String str2 = str;
                final int i3 = i;
                final int i4 = i2;
                final List<ParcelImpl> list2 = list;
                final Bundle bundle2 = bundle;
                browser.getCallbackExecutor().execute(new Runnable() {
                    public void run() {
                        browser.getCallback().onGetChildrenDone(browser, str2, i3, i4, MediaUtils2.convertParcelImplListToMediaItem2List(list2), bundle2);
                    }
                });
            }
        } catch (IllegalStateException unused) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    public void onSearchResultChanged(String str, int i, Bundle bundle) throws RuntimeException {
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "onSearchResultChanged(): Ignoring empty query");
            return;
        }
        try {
            final MediaBrowser2 browser = getBrowser();
            if (browser != null) {
                final String str2 = str;
                final int i2 = i;
                final Bundle bundle2 = bundle;
                browser.getCallbackExecutor().execute(new Runnable() {
                    public void run() {
                        browser.getCallback().onSearchResultChanged(browser, str2, i2, bundle2);
                    }
                });
            }
        } catch (IllegalStateException unused) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    public void onGetSearchResultDone(String str, int i, int i2, List<ParcelImpl> list, Bundle bundle) throws RuntimeException {
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "onGetSearchResultDone(): Ignoring empty query");
            return;
        }
        try {
            final MediaBrowser2 browser = getBrowser();
            if (browser != null) {
                final String str2 = str;
                final int i3 = i;
                final int i4 = i2;
                final List<ParcelImpl> list2 = list;
                final Bundle bundle2 = bundle;
                browser.getCallbackExecutor().execute(new Runnable() {
                    public void run() {
                        browser.getCallback().onGetSearchResultDone(browser, str2, i3, i4, MediaUtils2.convertParcelImplListToMediaItem2List(list2), bundle2);
                    }
                });
            }
        } catch (IllegalStateException unused) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    public void onChildrenChanged(String str, int i, Bundle bundle) {
        if (str == null) {
            Log.w(TAG, "onChildrenChanged(): Ignoring null parentId");
            return;
        }
        try {
            final MediaBrowser2 browser = getBrowser();
            if (browser != null) {
                final String str2 = str;
                final int i2 = i;
                final Bundle bundle2 = bundle;
                browser.getCallbackExecutor().execute(new Runnable() {
                    public void run() {
                        browser.getCallback().onChildrenChanged(browser, str2, i2, bundle2);
                    }
                });
            }
        } catch (IllegalStateException unused) {
            Log.w(TAG, "Don't fail silently here. Highly likely a bug");
        }
    }

    public void destroy() {
        this.mController.clear();
    }

    private MediaController2ImplBase getController() throws IllegalStateException {
        MediaController2ImplBase mediaController2ImplBase = (MediaController2ImplBase) this.mController.get();
        if (mediaController2ImplBase != null) {
            return mediaController2ImplBase;
        }
        throw new IllegalStateException("Controller is released");
    }

    private MediaBrowser2 getBrowser() throws IllegalStateException {
        MediaController2ImplBase controller = getController();
        if (controller.getInstance() instanceof MediaBrowser2) {
            return (MediaBrowser2) controller.getInstance();
        }
        return null;
    }
}

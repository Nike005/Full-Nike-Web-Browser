package androidx.media2;

import android.content.Context;
import android.os.Bundle;
import android.support.p064v4.media.MediaBrowserCompat;
import android.support.p064v4.media.session.MediaSessionCompat;
import androidx.media.MediaBrowserServiceCompat;
import androidx.media.MediaSessionManager;
import androidx.media2.MediaSession2;
import java.util.List;

class MediaSessionService2LegacyStub extends MediaBrowserServiceCompat {
    private final ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> mConnectedControllersManager;
    final MediaSessionManager mManager;
    private final MediaSession2.MediaSession2Impl mSessionImpl;

    MediaSessionService2LegacyStub(Context context, MediaSession2.MediaSession2Impl mediaSession2Impl, MediaSessionCompat.Token token) {
        attachToBaseContext(context);
        onCreate();
        setSessionToken(token);
        this.mManager = MediaSessionManager.getSessionManager(context);
        this.mSessionImpl = mediaSession2Impl;
        this.mConnectedControllersManager = new ConnectedControllersManager<>(mediaSession2Impl);
    }

    public MediaBrowserServiceCompat.BrowserRoot onGetRoot(String str, int i, Bundle bundle) {
        MediaSessionManager.RemoteUserInfo currentBrowserInfo = getCurrentBrowserInfo();
        MediaSession2.ControllerInfo createControllerInfo = createControllerInfo(currentBrowserInfo);
        SessionCommandGroup2 onConnect = this.mSessionImpl.getCallback().onConnect(this.mSessionImpl.getInstance(), createControllerInfo);
        if (onConnect == null) {
            return null;
        }
        this.mConnectedControllersManager.addController(currentBrowserInfo, createControllerInfo, onConnect);
        return MediaUtils2.sDefaultBrowserRoot;
    }

    public void onLoadChildren(String str, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result) {
        result.sendResult(null);
    }

    /* access modifiers changed from: package-private */
    public MediaSession2.ControllerInfo createControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo) {
        return new MediaSession2.ControllerInfo(remoteUserInfo, this.mManager.isTrustedForMediaControl(remoteUserInfo), (MediaSession2.ControllerCb) null);
    }

    /* access modifiers changed from: package-private */
    public ConnectedControllersManager<MediaSessionManager.RemoteUserInfo> getConnectedControllersManager() {
        return this.mConnectedControllersManager;
    }
}

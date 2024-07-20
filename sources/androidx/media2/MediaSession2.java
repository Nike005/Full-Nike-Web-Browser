package androidx.media2;

import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.support.p064v4.media.session.MediaSessionCompat;
import android.support.p064v4.media.session.PlaybackStateCompat;
import androidx.core.util.ObjectsCompat;
import androidx.media.MediaSessionManager;
import androidx.media2.MediaController2;
import androidx.media2.MediaInterface2;
import androidx.media2.SessionCommandGroup2;
import androidx.versionedparcelable.VersionedParcelable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

public class MediaSession2 implements MediaInterface2.SessionPlayer, AutoCloseable {
    public static final int ERROR_CODE_ACTION_ABORTED = 10;
    public static final int ERROR_CODE_APP_ERROR = 1;
    public static final int ERROR_CODE_AUTHENTICATION_EXPIRED = 3;
    public static final int ERROR_CODE_CONCURRENT_STREAM_LIMIT = 5;
    public static final int ERROR_CODE_CONTENT_ALREADY_PLAYING = 8;
    public static final int ERROR_CODE_END_OF_QUEUE = 11;
    public static final int ERROR_CODE_NOT_AVAILABLE_IN_REGION = 7;
    public static final int ERROR_CODE_NOT_SUPPORTED = 2;
    public static final int ERROR_CODE_PARENTAL_CONTROL_RESTRICTED = 6;
    public static final int ERROR_CODE_PREMIUM_ACCOUNT_REQUIRED = 4;
    public static final int ERROR_CODE_SETUP_REQUIRED = 12;
    public static final int ERROR_CODE_SKIP_LIMIT_REACHED = 9;
    public static final int ERROR_CODE_UNKNOWN_ERROR = 0;
    static final String TAG = "MediaSession2";
    private final MediaSession2Impl mImpl;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ErrorCode {
    }

    interface MediaSession2Impl extends MediaInterface2.SessionPlayer, AutoCloseable {
        PlaybackStateCompat createPlaybackStateCompat();

        AudioFocusHandler getAudioFocusHandler();

        SessionCallback getCallback();

        Executor getCallbackExecutor();

        List<ControllerInfo> getConnectedControllers();

        Context getContext();

        MediaSession2 getInstance();

        IBinder getLegacyBrowserServiceBinder();

        MediaController2.PlaybackInfo getPlaybackInfo();

        MediaPlayerConnector getPlayer();

        MediaPlaylistAgent getPlaylistAgent();

        PendingIntent getSessionActivity();

        IBinder getSessionBinder();

        MediaSessionCompat getSessionCompat();

        SessionToken2 getToken();

        boolean isClosed();

        void notifyRoutesInfoChanged(ControllerInfo controllerInfo, List<Bundle> list);

        void sendCustomCommand(ControllerInfo controllerInfo, SessionCommand2 sessionCommand2, Bundle bundle, ResultReceiver resultReceiver);

        void sendCustomCommand(SessionCommand2 sessionCommand2, Bundle bundle);

        void setAllowedCommands(ControllerInfo controllerInfo, SessionCommandGroup2 sessionCommandGroup2);

        void setCustomLayout(ControllerInfo controllerInfo, List<CommandButton> list);

        void updatePlayer(MediaPlayerConnector mediaPlayerConnector, MediaPlaylistAgent mediaPlaylistAgent);
    }

    public interface OnDataSourceMissingHelper {
        DataSourceDesc2 onDataSourceMissing(MediaSession2 mediaSession2, MediaItem2 mediaItem2);
    }

    MediaSession2(Context context, String str, MediaPlayerConnector mediaPlayerConnector, MediaPlaylistAgent mediaPlaylistAgent, PendingIntent pendingIntent, Executor executor, SessionCallback sessionCallback) {
        this.mImpl = createImpl(context, str, mediaPlayerConnector, mediaPlaylistAgent, pendingIntent, executor, sessionCallback);
    }

    /* access modifiers changed from: package-private */
    public MediaSession2Impl createImpl(Context context, String str, MediaPlayerConnector mediaPlayerConnector, MediaPlaylistAgent mediaPlaylistAgent, PendingIntent pendingIntent, Executor executor, SessionCallback sessionCallback) {
        return new MediaSession2ImplBase(this, context, str, mediaPlayerConnector, mediaPlaylistAgent, pendingIntent, executor, sessionCallback);
    }

    /* access modifiers changed from: package-private */
    public MediaSession2Impl getImpl() {
        return this.mImpl;
    }

    public void updatePlayerConnector(MediaPlayerConnector mediaPlayerConnector, MediaPlaylistAgent mediaPlaylistAgent) {
        this.mImpl.updatePlayer(mediaPlayerConnector, mediaPlaylistAgent);
    }

    public void close() {
        try {
            this.mImpl.close();
        } catch (Exception unused) {
        }
    }

    public MediaPlayerConnector getPlayerConnector() {
        return this.mImpl.getPlayer();
    }

    public MediaPlaylistAgent getPlaylistAgent() {
        return this.mImpl.getPlaylistAgent();
    }

    public SessionToken2 getToken() {
        return this.mImpl.getToken();
    }

    /* access modifiers changed from: package-private */
    public Context getContext() {
        return this.mImpl.getContext();
    }

    /* access modifiers changed from: package-private */
    public Executor getCallbackExecutor() {
        return this.mImpl.getCallbackExecutor();
    }

    /* access modifiers changed from: package-private */
    public SessionCallback getCallback() {
        return this.mImpl.getCallback();
    }

    public AudioFocusHandler getAudioFocusHandler() {
        return this.mImpl.getAudioFocusHandler();
    }

    public List<ControllerInfo> getConnectedControllers() {
        return this.mImpl.getConnectedControllers();
    }

    public void setCustomLayout(ControllerInfo controllerInfo, List<CommandButton> list) {
        this.mImpl.setCustomLayout(controllerInfo, list);
    }

    public void setAllowedCommands(ControllerInfo controllerInfo, SessionCommandGroup2 sessionCommandGroup2) {
        this.mImpl.setAllowedCommands(controllerInfo, sessionCommandGroup2);
    }

    public void sendCustomCommand(SessionCommand2 sessionCommand2, Bundle bundle) {
        this.mImpl.sendCustomCommand(sessionCommand2, bundle);
    }

    public void sendCustomCommand(ControllerInfo controllerInfo, SessionCommand2 sessionCommand2, Bundle bundle, ResultReceiver resultReceiver) {
        this.mImpl.sendCustomCommand(controllerInfo, sessionCommand2, bundle, resultReceiver);
    }

    public void play() {
        this.mImpl.play();
    }

    public void pause() {
        this.mImpl.pause();
    }

    public void reset() {
        this.mImpl.reset();
    }

    public void prepare() {
        this.mImpl.prepare();
    }

    public void seekTo(long j) {
        this.mImpl.seekTo(j);
    }

    public void skipForward() {
        this.mImpl.skipForward();
    }

    public void skipBackward() {
        this.mImpl.skipBackward();
    }

    public void notifyError(int i, Bundle bundle) {
        this.mImpl.notifyError(i, bundle);
    }

    public void notifyRoutesInfoChanged(ControllerInfo controllerInfo, List<Bundle> list) {
        this.mImpl.notifyRoutesInfoChanged(controllerInfo, list);
    }

    public int getPlayerState() {
        return this.mImpl.getPlayerState();
    }

    public long getCurrentPosition() {
        return this.mImpl.getCurrentPosition();
    }

    public long getDuration() {
        return this.mImpl.getDuration();
    }

    public long getBufferedPosition() {
        return this.mImpl.getBufferedPosition();
    }

    public int getBufferingState() {
        return this.mImpl.getBufferingState();
    }

    public float getPlaybackSpeed() {
        return this.mImpl.getPlaybackSpeed();
    }

    public void setPlaybackSpeed(float f) {
        this.mImpl.setPlaybackSpeed(f);
    }

    public void setOnDataSourceMissingHelper(OnDataSourceMissingHelper onDataSourceMissingHelper) {
        this.mImpl.setOnDataSourceMissingHelper(onDataSourceMissingHelper);
    }

    public void clearOnDataSourceMissingHelper() {
        this.mImpl.clearOnDataSourceMissingHelper();
    }

    public List<MediaItem2> getPlaylist() {
        return this.mImpl.getPlaylist();
    }

    public void setPlaylist(List<MediaItem2> list, MediaMetadata2 mediaMetadata2) {
        this.mImpl.setPlaylist(list, mediaMetadata2);
    }

    public void skipToPlaylistItem(MediaItem2 mediaItem2) {
        this.mImpl.skipToPlaylistItem(mediaItem2);
    }

    public void skipToPreviousItem() {
        this.mImpl.skipToPreviousItem();
    }

    public void skipToNextItem() {
        this.mImpl.skipToNextItem();
    }

    public MediaMetadata2 getPlaylistMetadata() {
        return this.mImpl.getPlaylistMetadata();
    }

    public void addPlaylistItem(int i, MediaItem2 mediaItem2) {
        this.mImpl.addPlaylistItem(i, mediaItem2);
    }

    public void removePlaylistItem(MediaItem2 mediaItem2) {
        this.mImpl.removePlaylistItem(mediaItem2);
    }

    public void replacePlaylistItem(int i, MediaItem2 mediaItem2) {
        this.mImpl.replacePlaylistItem(i, mediaItem2);
    }

    public MediaItem2 getCurrentMediaItem() {
        return this.mImpl.getCurrentMediaItem();
    }

    public void updatePlaylistMetadata(MediaMetadata2 mediaMetadata2) {
        this.mImpl.updatePlaylistMetadata(mediaMetadata2);
    }

    public int getRepeatMode() {
        return this.mImpl.getRepeatMode();
    }

    public void setRepeatMode(int i) {
        this.mImpl.setRepeatMode(i);
    }

    public int getShuffleMode() {
        return this.mImpl.getShuffleMode();
    }

    public void setShuffleMode(int i) {
        this.mImpl.setShuffleMode(i);
    }

    public MediaSessionCompat getSessionCompat() {
        return this.mImpl.getSessionCompat();
    }

    /* access modifiers changed from: package-private */
    public IBinder getSessionBinder() {
        return this.mImpl.getSessionBinder();
    }

    /* access modifiers changed from: package-private */
    public IBinder getLegacyBrowerServiceBinder() {
        return this.mImpl.getLegacyBrowserServiceBinder();
    }

    public static abstract class SessionCallback {
        public void onBufferingStateChanged(MediaSession2 mediaSession2, MediaPlayerConnector mediaPlayerConnector, MediaItem2 mediaItem2, int i) {
        }

        public boolean onCommandRequest(MediaSession2 mediaSession2, ControllerInfo controllerInfo, SessionCommand2 sessionCommand2) {
            return true;
        }

        public void onCurrentMediaItemChanged(MediaSession2 mediaSession2, MediaPlayerConnector mediaPlayerConnector, MediaItem2 mediaItem2) {
        }

        public void onCustomCommand(MediaSession2 mediaSession2, ControllerInfo controllerInfo, SessionCommand2 sessionCommand2, Bundle bundle, ResultReceiver resultReceiver) {
        }

        public void onDisconnected(MediaSession2 mediaSession2, ControllerInfo controllerInfo) {
        }

        public void onFastForward(MediaSession2 mediaSession2, ControllerInfo controllerInfo) {
        }

        public void onMediaPrepared(MediaSession2 mediaSession2, MediaPlayerConnector mediaPlayerConnector, MediaItem2 mediaItem2) {
        }

        public void onPlayFromMediaId(MediaSession2 mediaSession2, ControllerInfo controllerInfo, String str, Bundle bundle) {
        }

        public void onPlayFromSearch(MediaSession2 mediaSession2, ControllerInfo controllerInfo, String str, Bundle bundle) {
        }

        public void onPlayFromUri(MediaSession2 mediaSession2, ControllerInfo controllerInfo, Uri uri, Bundle bundle) {
        }

        public void onPlaybackSpeedChanged(MediaSession2 mediaSession2, MediaPlayerConnector mediaPlayerConnector, float f) {
        }

        public void onPlayerStateChanged(MediaSession2 mediaSession2, MediaPlayerConnector mediaPlayerConnector, int i) {
        }

        public void onPlaylistChanged(MediaSession2 mediaSession2, MediaPlaylistAgent mediaPlaylistAgent, List<MediaItem2> list, MediaMetadata2 mediaMetadata2) {
        }

        public void onPlaylistMetadataChanged(MediaSession2 mediaSession2, MediaPlaylistAgent mediaPlaylistAgent, MediaMetadata2 mediaMetadata2) {
        }

        public void onPrepareFromMediaId(MediaSession2 mediaSession2, ControllerInfo controllerInfo, String str, Bundle bundle) {
        }

        public void onPrepareFromSearch(MediaSession2 mediaSession2, ControllerInfo controllerInfo, String str, Bundle bundle) {
        }

        public void onPrepareFromUri(MediaSession2 mediaSession2, ControllerInfo controllerInfo, Uri uri, Bundle bundle) {
        }

        public void onRepeatModeChanged(MediaSession2 mediaSession2, MediaPlaylistAgent mediaPlaylistAgent, int i) {
        }

        public void onRewind(MediaSession2 mediaSession2, ControllerInfo controllerInfo) {
        }

        public void onSeekCompleted(MediaSession2 mediaSession2, MediaPlayerConnector mediaPlayerConnector, long j) {
        }

        public void onSelectRoute(MediaSession2 mediaSession2, ControllerInfo controllerInfo, Bundle bundle) {
        }

        public void onSetRating(MediaSession2 mediaSession2, ControllerInfo controllerInfo, String str, Rating2 rating2) {
        }

        public void onShuffleModeChanged(MediaSession2 mediaSession2, MediaPlaylistAgent mediaPlaylistAgent, int i) {
        }

        public void onSubscribeRoutesInfo(MediaSession2 mediaSession2, ControllerInfo controllerInfo) {
        }

        public void onUnsubscribeRoutesInfo(MediaSession2 mediaSession2, ControllerInfo controllerInfo) {
        }

        public SessionCommandGroup2 onConnect(MediaSession2 mediaSession2, ControllerInfo controllerInfo) {
            return new SessionCommandGroup2.Builder().addAllPredefinedCommands().build();
        }
    }

    public static final class Builder extends BuilderBase<MediaSession2, Builder, SessionCallback> {
        public Builder(Context context) {
            super(context);
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

        public Builder setSessionCallback(Executor executor, SessionCallback sessionCallback) {
            return (Builder) super.setSessionCallback(executor, sessionCallback);
        }

        public MediaSession2 build() {
            if (this.mCallbackExecutor == null) {
                this.mCallbackExecutor = new MainHandlerExecutor(this.mContext);
            }
            if (this.mCallback == null) {
                this.mCallback = new SessionCallback() {
                };
            }
            return new MediaSession2(this.mContext, this.mId, this.mPlayer, this.mPlaylistAgent, this.mSessionActivity, this.mCallbackExecutor, this.mCallback);
        }
    }

    public static final class ControllerInfo {
        private final ControllerCb mControllerCb;
        private final boolean mIsTrusted;
        private final MediaSessionManager.RemoteUserInfo mRemoteUserInfo;

        ControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo, boolean z, ControllerCb controllerCb) {
            this.mRemoteUserInfo = remoteUserInfo;
            this.mIsTrusted = z;
            this.mControllerCb = controllerCb;
        }

        public MediaSessionManager.RemoteUserInfo getRemoteUserInfo() {
            return this.mRemoteUserInfo;
        }

        public String getPackageName() {
            return this.mRemoteUserInfo.getPackageName();
        }

        public int getUid() {
            return this.mRemoteUserInfo.getUid();
        }

        public boolean isTrusted() {
            return this.mIsTrusted;
        }

        public int hashCode() {
            ControllerCb controllerCb = this.mControllerCb;
            if (controllerCb != null) {
                return controllerCb.hashCode();
            }
            return 0;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ControllerInfo)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            ControllerInfo controllerInfo = (ControllerInfo) obj;
            if (this.mControllerCb == null && controllerInfo.mControllerCb == null) {
                return this.mRemoteUserInfo.equals(controllerInfo.mRemoteUserInfo);
            }
            return ObjectsCompat.equals(this.mControllerCb, controllerInfo.mControllerCb);
        }

        public String toString() {
            return "ControllerInfo {pkg=" + this.mRemoteUserInfo.getPackageName() + ", uid=" + this.mRemoteUserInfo.getUid() + "})";
        }

        /* access modifiers changed from: package-private */
        public ControllerCb getControllerCb() {
            return this.mControllerCb;
        }
    }

    public static final class CommandButton implements VersionedParcelable {
        private static final String KEY_COMMAND = "android.media.session2.command_button.command";
        private static final String KEY_DISPLAY_NAME = "android.media.session2.command_button.display_name";
        private static final String KEY_ENABLED = "android.media.session2.command_button.enabled";
        private static final String KEY_EXTRAS = "android.media.session2.command_button.extras";
        private static final String KEY_ICON_RES_ID = "android.media.session2.command_button.icon_res_id";
        SessionCommand2 mCommand;
        String mDisplayName;
        boolean mEnabled;
        Bundle mExtras;
        int mIconResId;

        CommandButton() {
        }

        CommandButton(SessionCommand2 sessionCommand2, int i, String str, Bundle bundle, boolean z) {
            this.mCommand = sessionCommand2;
            this.mIconResId = i;
            this.mDisplayName = str;
            this.mExtras = bundle;
            this.mEnabled = z;
        }

        public SessionCommand2 getCommand() {
            return this.mCommand;
        }

        public int getIconResId() {
            return this.mIconResId;
        }

        public String getDisplayName() {
            return this.mDisplayName;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        public boolean isEnabled() {
            return this.mEnabled;
        }

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putBundle(KEY_COMMAND, this.mCommand.toBundle());
            bundle.putInt(KEY_ICON_RES_ID, this.mIconResId);
            bundle.putString(KEY_DISPLAY_NAME, this.mDisplayName);
            bundle.putBundle(KEY_EXTRAS, this.mExtras);
            bundle.putBoolean(KEY_ENABLED, this.mEnabled);
            return bundle;
        }

        public static CommandButton fromBundle(Bundle bundle) {
            if (bundle == null) {
                return null;
            }
            Builder builder = new Builder();
            builder.setCommand(SessionCommand2.fromBundle(bundle.getBundle(KEY_COMMAND)));
            builder.setIconResId(bundle.getInt(KEY_ICON_RES_ID, 0));
            builder.setDisplayName(bundle.getString(KEY_DISPLAY_NAME));
            builder.setExtras(bundle.getBundle(KEY_EXTRAS));
            builder.setEnabled(bundle.getBoolean(KEY_ENABLED));
            try {
                return builder.build();
            } catch (IllegalStateException unused) {
                return null;
            }
        }

        public static final class Builder {
            private SessionCommand2 mCommand;
            private String mDisplayName;
            private boolean mEnabled;
            private Bundle mExtras;
            private int mIconResId;

            public Builder setCommand(SessionCommand2 sessionCommand2) {
                this.mCommand = sessionCommand2;
                return this;
            }

            public Builder setIconResId(int i) {
                this.mIconResId = i;
                return this;
            }

            public Builder setDisplayName(String str) {
                this.mDisplayName = str;
                return this;
            }

            public Builder setEnabled(boolean z) {
                this.mEnabled = z;
                return this;
            }

            public Builder setExtras(Bundle bundle) {
                this.mExtras = bundle;
                return this;
            }

            public CommandButton build() {
                return new CommandButton(this.mCommand, this.mIconResId, this.mDisplayName, this.mExtras, this.mEnabled);
            }
        }
    }

    static abstract class ControllerCb {
        /* access modifiers changed from: package-private */
        public abstract void onAllowedCommandsChanged(SessionCommandGroup2 sessionCommandGroup2) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onBufferingStateChanged(MediaItem2 mediaItem2, int i, long j) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onChildrenChanged(String str, int i, Bundle bundle) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onCurrentMediaItemChanged(MediaItem2 mediaItem2) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onCustomCommand(SessionCommand2 sessionCommand2, Bundle bundle, ResultReceiver resultReceiver) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onCustomLayoutChanged(List<CommandButton> list) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onDisconnected() throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onError(int i, Bundle bundle) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onGetChildrenDone(String str, int i, int i2, List<MediaItem2> list, Bundle bundle) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onGetItemDone(String str, MediaItem2 mediaItem2) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onGetLibraryRootDone(Bundle bundle, String str, Bundle bundle2) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onGetSearchResultDone(String str, int i, int i2, List<MediaItem2> list, Bundle bundle) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onPlaybackInfoChanged(MediaController2.PlaybackInfo playbackInfo) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onPlaybackSpeedChanged(long j, long j2, float f) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onPlayerStateChanged(long j, long j2, int i) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onPlaylistChanged(List<MediaItem2> list, MediaMetadata2 mediaMetadata2) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onPlaylistMetadataChanged(MediaMetadata2 mediaMetadata2) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onRepeatModeChanged(int i) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onRoutesInfoChanged(List<Bundle> list) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onSearchResultChanged(String str, int i, Bundle bundle) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onSeekCompleted(long j, long j2, long j3) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onShuffleModeChanged(int i) throws RemoteException;

        ControllerCb() {
        }
    }

    static abstract class BuilderBase<T extends MediaSession2, U extends BuilderBase<T, U, C>, C extends SessionCallback> {
        C mCallback;
        Executor mCallbackExecutor;
        final Context mContext;
        String mId;
        MediaPlayerConnector mPlayer;
        MediaPlaylistAgent mPlaylistAgent;
        PendingIntent mSessionActivity;

        /* access modifiers changed from: package-private */
        public abstract T build();

        BuilderBase(Context context) {
            if (context != null) {
                this.mContext = context;
                this.mId = "";
                return;
            }
            throw new IllegalArgumentException("context shouldn't be null");
        }

        /* access modifiers changed from: package-private */
        public U setPlayer(MediaPlayerConnector mediaPlayerConnector) {
            if (mediaPlayerConnector != null) {
                this.mPlayer = mediaPlayerConnector;
                return this;
            }
            throw new IllegalArgumentException("player shouldn't be null");
        }

        /* access modifiers changed from: package-private */
        public U setPlaylistAgent(MediaPlaylistAgent mediaPlaylistAgent) {
            if (mediaPlaylistAgent != null) {
                this.mPlaylistAgent = mediaPlaylistAgent;
                return this;
            }
            throw new IllegalArgumentException("playlistAgent shouldn't be null");
        }

        /* access modifiers changed from: package-private */
        public U setSessionActivity(PendingIntent pendingIntent) {
            this.mSessionActivity = pendingIntent;
            return this;
        }

        /* access modifiers changed from: package-private */
        public U setId(String str) {
            if (str != null) {
                this.mId = str;
                return this;
            }
            throw new IllegalArgumentException("id shouldn't be null");
        }

        /* access modifiers changed from: package-private */
        public U setSessionCallback(Executor executor, C c) {
            if (executor == null) {
                throw new IllegalArgumentException("executor shouldn't be null");
            } else if (c != null) {
                this.mCallbackExecutor = executor;
                this.mCallback = c;
                return this;
            } else {
                throw new IllegalArgumentException("callback shouldn't be null");
            }
        }
    }

    static class MainHandlerExecutor implements Executor {
        private final Handler mHandler;

        MainHandlerExecutor(Context context) {
            this.mHandler = new Handler(context.getMainLooper());
        }

        public void execute(Runnable runnable) {
            if (!this.mHandler.post(runnable)) {
                throw new RejectedExecutionException(this.mHandler + " is shutting down");
            }
        }
    }
}

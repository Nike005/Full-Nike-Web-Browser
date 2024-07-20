package androidx.media2.session;

import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.p064v4.media.session.MediaSessionCompat;
import android.support.p064v4.media.session.PlaybackStateCompat;
import androidx.core.content.ContextCompat;
import androidx.core.util.ObjectsCompat;
import androidx.media.MediaSessionManager;
import androidx.media2.common.MediaItem;
import androidx.media2.common.MediaMetadata;
import androidx.media2.common.Rating;
import androidx.media2.common.SessionPlayer;
import androidx.media2.common.SubtitleData;
import androidx.media2.common.VideoSize;
import androidx.media2.session.MediaController;
import androidx.media2.session.MediaInterface;
import androidx.media2.session.MediaLibraryService;
import androidx.media2.session.SessionCommandGroup;
import androidx.versionedparcelable.VersionedParcelable;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;

public class MediaSession implements AutoCloseable {
    private static final HashMap<String, MediaSession> SESSION_ID_TO_SESSION_MAP = new HashMap<>();
    private static final Object STATIC_LOCK = new Object();
    static final String TAG = "MediaSession";
    private final MediaSessionImpl mImpl;

    interface MediaSessionImpl extends MediaInterface.SessionPlayer, AutoCloseable {
        void broadcastCustomCommand(SessionCommand sessionCommand, Bundle bundle);

        void connectFromService(IMediaController iMediaController, String str, int i, int i2, Bundle bundle);

        PlaybackStateCompat createPlaybackStateCompat();

        SessionCallback getCallback();

        Executor getCallbackExecutor();

        List<ControllerInfo> getConnectedControllers();

        Context getContext();

        String getId();

        MediaSession getInstance();

        IBinder getLegacyBrowserServiceBinder();

        MediaController.PlaybackInfo getPlaybackInfo();

        SessionPlayer getPlayer();

        PendingIntent getSessionActivity();

        MediaSessionCompat getSessionCompat();

        SessionToken getToken();

        Uri getUri();

        boolean isClosed();

        boolean isConnected(ControllerInfo controllerInfo);

        ListenableFuture<SessionResult> sendCustomCommand(ControllerInfo controllerInfo, SessionCommand sessionCommand, Bundle bundle);

        void setAllowedCommands(ControllerInfo controllerInfo, SessionCommandGroup sessionCommandGroup);

        ListenableFuture<SessionResult> setCustomLayout(ControllerInfo controllerInfo, List<CommandButton> list);

        void updatePlayer(SessionPlayer sessionPlayer);

        void updatePlayer(SessionPlayer sessionPlayer, SessionPlayer sessionPlayer2);
    }

    MediaSession(Context context, String str, SessionPlayer sessionPlayer, PendingIntent pendingIntent, Executor executor, SessionCallback sessionCallback, Bundle bundle) {
        synchronized (STATIC_LOCK) {
            if (!SESSION_ID_TO_SESSION_MAP.containsKey(str)) {
                SESSION_ID_TO_SESSION_MAP.put(str, this);
            } else {
                throw new IllegalStateException("Session ID must be unique. ID=" + str);
            }
        }
        this.mImpl = createImpl(context, str, sessionPlayer, pendingIntent, executor, sessionCallback, bundle);
    }

    /* access modifiers changed from: package-private */
    public MediaSessionImpl createImpl(Context context, String str, SessionPlayer sessionPlayer, PendingIntent pendingIntent, Executor executor, SessionCallback sessionCallback, Bundle bundle) {
        return new MediaSessionImplBase(this, context, str, sessionPlayer, pendingIntent, executor, sessionCallback, bundle);
    }

    /* access modifiers changed from: package-private */
    public MediaSessionImpl getImpl() {
        return this.mImpl;
    }

    static MediaSession getSession(Uri uri) {
        synchronized (STATIC_LOCK) {
            for (MediaSession next : SESSION_ID_TO_SESSION_MAP.values()) {
                if (ObjectsCompat.equals(next.getUri(), uri)) {
                    return next;
                }
            }
            return null;
        }
    }

    public void updatePlayer(SessionPlayer sessionPlayer) {
        if (sessionPlayer != null) {
            this.mImpl.updatePlayer(sessionPlayer);
            return;
        }
        throw new NullPointerException("player shouldn't be null");
    }

    public void close() {
        try {
            synchronized (STATIC_LOCK) {
                SESSION_ID_TO_SESSION_MAP.remove(this.mImpl.getId());
            }
            this.mImpl.close();
        } catch (Exception unused) {
        }
    }

    public boolean isClosed() {
        return this.mImpl.isClosed();
    }

    public SessionPlayer getPlayer() {
        return this.mImpl.getPlayer();
    }

    public String getId() {
        return this.mImpl.getId();
    }

    public SessionToken getToken() {
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

    public List<ControllerInfo> getConnectedControllers() {
        return this.mImpl.getConnectedControllers();
    }

    public ListenableFuture<SessionResult> setCustomLayout(ControllerInfo controllerInfo, List<CommandButton> list) {
        if (controllerInfo == null) {
            throw new NullPointerException("controller shouldn't be null");
        } else if (list != null) {
            return this.mImpl.setCustomLayout(controllerInfo, list);
        } else {
            throw new NullPointerException("layout shouldn't be null");
        }
    }

    public void setAllowedCommands(ControllerInfo controllerInfo, SessionCommandGroup sessionCommandGroup) {
        if (controllerInfo == null) {
            throw new NullPointerException("controller shouldn't be null");
        } else if (sessionCommandGroup != null) {
            this.mImpl.setAllowedCommands(controllerInfo, sessionCommandGroup);
        } else {
            throw new NullPointerException("commands shouldn't be null");
        }
    }

    public void broadcastCustomCommand(SessionCommand sessionCommand, Bundle bundle) {
        if (sessionCommand == null) {
            throw new NullPointerException("command shouldn't be null");
        } else if (sessionCommand.getCommandCode() == 0) {
            this.mImpl.broadcastCustomCommand(sessionCommand, bundle);
        } else {
            throw new IllegalArgumentException("command should be a custom command");
        }
    }

    public ListenableFuture<SessionResult> sendCustomCommand(ControllerInfo controllerInfo, SessionCommand sessionCommand, Bundle bundle) {
        if (controllerInfo == null) {
            throw new NullPointerException("controller shouldn't be null");
        } else if (sessionCommand == null) {
            throw new NullPointerException("command shouldn't be null");
        } else if (sessionCommand.getCommandCode() == 0) {
            return this.mImpl.sendCustomCommand(controllerInfo, sessionCommand, bundle);
        } else {
            throw new IllegalArgumentException("command should be a custom command");
        }
    }

    public MediaSessionCompat getSessionCompat() {
        return this.mImpl.getSessionCompat();
    }

    /* access modifiers changed from: package-private */
    public void handleControllerConnectionFromService(IMediaController iMediaController, String str, int i, int i2, Bundle bundle) {
        this.mImpl.connectFromService(iMediaController, str, i, i2, bundle);
    }

    /* access modifiers changed from: package-private */
    public IBinder getLegacyBrowerServiceBinder() {
        return this.mImpl.getLegacyBrowserServiceBinder();
    }

    private Uri getUri() {
        return this.mImpl.getUri();
    }

    public static abstract class SessionCallback {
        ForegroundServiceEventCallback mForegroundServiceEventCallback;

        public int onCommandRequest(MediaSession mediaSession, ControllerInfo controllerInfo, SessionCommand sessionCommand) {
            return 0;
        }

        public MediaItem onCreateMediaItem(MediaSession mediaSession, ControllerInfo controllerInfo, String str) {
            return null;
        }

        public void onDisconnected(MediaSession mediaSession, ControllerInfo controllerInfo) {
        }

        public int onFastForward(MediaSession mediaSession, ControllerInfo controllerInfo) {
            return -6;
        }

        public int onPlayFromMediaId(MediaSession mediaSession, ControllerInfo controllerInfo, String str, Bundle bundle) {
            return -6;
        }

        public int onPlayFromSearch(MediaSession mediaSession, ControllerInfo controllerInfo, String str, Bundle bundle) {
            return -6;
        }

        public int onPlayFromUri(MediaSession mediaSession, ControllerInfo controllerInfo, Uri uri, Bundle bundle) {
            return -6;
        }

        public void onPostConnect(MediaSession mediaSession, ControllerInfo controllerInfo) {
        }

        public int onPrepareFromMediaId(MediaSession mediaSession, ControllerInfo controllerInfo, String str, Bundle bundle) {
            return -6;
        }

        public int onPrepareFromSearch(MediaSession mediaSession, ControllerInfo controllerInfo, String str, Bundle bundle) {
            return -6;
        }

        public int onPrepareFromUri(MediaSession mediaSession, ControllerInfo controllerInfo, Uri uri, Bundle bundle) {
            return -6;
        }

        public int onRewind(MediaSession mediaSession, ControllerInfo controllerInfo) {
            return -6;
        }

        public int onSetRating(MediaSession mediaSession, ControllerInfo controllerInfo, String str, Rating rating) {
            return -6;
        }

        public int onSkipBackward(MediaSession mediaSession, ControllerInfo controllerInfo) {
            return -6;
        }

        public int onSkipForward(MediaSession mediaSession, ControllerInfo controllerInfo) {
            return -6;
        }

        public SessionCommandGroup onConnect(MediaSession mediaSession, ControllerInfo controllerInfo) {
            return new SessionCommandGroup.Builder().addAllPredefinedCommands(1).build();
        }

        public SessionResult onCustomCommand(MediaSession mediaSession, ControllerInfo controllerInfo, SessionCommand sessionCommand, Bundle bundle) {
            return new SessionResult(-6, (Bundle) null);
        }

        /* access modifiers changed from: package-private */
        public final void onPlayerStateChanged(MediaSession mediaSession, int i) {
            ForegroundServiceEventCallback foregroundServiceEventCallback = this.mForegroundServiceEventCallback;
            if (foregroundServiceEventCallback != null) {
                foregroundServiceEventCallback.onPlayerStateChanged(mediaSession, i);
            }
        }

        /* access modifiers changed from: package-private */
        public final void onSessionClosed(MediaSession mediaSession) {
            ForegroundServiceEventCallback foregroundServiceEventCallback = this.mForegroundServiceEventCallback;
            if (foregroundServiceEventCallback != null) {
                foregroundServiceEventCallback.onSessionClosed(mediaSession);
            }
        }

        /* access modifiers changed from: package-private */
        public void setForegroundServiceEventCallback(ForegroundServiceEventCallback foregroundServiceEventCallback) {
            this.mForegroundServiceEventCallback = foregroundServiceEventCallback;
        }

        static abstract class ForegroundServiceEventCallback {
            public void onPlayerStateChanged(MediaSession mediaSession, int i) {
            }

            public void onSessionClosed(MediaSession mediaSession) {
            }

            ForegroundServiceEventCallback() {
            }
        }
    }

    public static final class Builder extends BuilderBase<MediaSession, Builder, SessionCallback> {
        public Builder(Context context, SessionPlayer sessionPlayer) {
            super(context, sessionPlayer);
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

        public Builder setExtras(Bundle bundle) {
            return (Builder) super.setExtras(bundle);
        }

        public MediaSession build() {
            if (this.mCallbackExecutor == null) {
                this.mCallbackExecutor = ContextCompat.getMainExecutor(this.mContext);
            }
            if (this.mCallback == null) {
                this.mCallback = new SessionCallback() {
                };
            }
            return new MediaSession(this.mContext, this.mId, this.mPlayer, this.mSessionActivity, this.mCallbackExecutor, this.mCallback, this.mExtras);
        }
    }

    public static final class ControllerInfo {
        private final Bundle mConnectionHints;
        private final ControllerCb mControllerCb;
        private final boolean mIsTrusted;
        private final MediaSessionManager.RemoteUserInfo mRemoteUserInfo;

        ControllerInfo(MediaSessionManager.RemoteUserInfo remoteUserInfo, boolean z, ControllerCb controllerCb, Bundle bundle) {
            this.mRemoteUserInfo = remoteUserInfo;
            this.mIsTrusted = z;
            this.mControllerCb = controllerCb;
            this.mConnectionHints = bundle;
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

        public Bundle getConnectionHints() {
            return this.mConnectionHints == null ? Bundle.EMPTY : new Bundle(this.mConnectionHints);
        }

        public boolean isTrusted() {
            return this.mIsTrusted;
        }

        public int hashCode() {
            return ObjectsCompat.hash(this.mControllerCb, this.mRemoteUserInfo);
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
        SessionCommand mCommand;
        CharSequence mDisplayName;
        boolean mEnabled;
        Bundle mExtras;
        int mIconResId;

        CommandButton() {
        }

        CommandButton(SessionCommand sessionCommand, int i, CharSequence charSequence, Bundle bundle, boolean z) {
            this.mCommand = sessionCommand;
            this.mIconResId = i;
            this.mDisplayName = charSequence;
            this.mExtras = bundle;
            this.mEnabled = z;
        }

        public SessionCommand getCommand() {
            return this.mCommand;
        }

        public int getIconResId() {
            return this.mIconResId;
        }

        public CharSequence getDisplayName() {
            return this.mDisplayName;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        public boolean isEnabled() {
            return this.mEnabled;
        }

        public static final class Builder {
            private SessionCommand mCommand;
            private CharSequence mDisplayName;
            private boolean mEnabled;
            private Bundle mExtras;
            private int mIconResId;

            public Builder setCommand(SessionCommand sessionCommand) {
                this.mCommand = sessionCommand;
                return this;
            }

            public Builder setIconResId(int i) {
                this.mIconResId = i;
                return this;
            }

            public Builder setDisplayName(CharSequence charSequence) {
                this.mDisplayName = charSequence;
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
        public abstract void onAllowedCommandsChanged(int i, SessionCommandGroup sessionCommandGroup) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onBufferingStateChanged(int i, MediaItem mediaItem, int i2, long j, long j2, long j3) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onChildrenChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onCurrentMediaItemChanged(int i, MediaItem mediaItem, int i2, int i3, int i4) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onDisconnected(int i) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onLibraryResult(int i, LibraryResult libraryResult) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onPlaybackCompleted(int i) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onPlaybackInfoChanged(int i, MediaController.PlaybackInfo playbackInfo) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onPlaybackSpeedChanged(int i, long j, long j2, float f) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onPlayerResult(int i, SessionPlayer.PlayerResult playerResult) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onPlayerStateChanged(int i, long j, long j2, int i2) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onPlaylistChanged(int i, List<MediaItem> list, MediaMetadata mediaMetadata, int i2, int i3, int i4) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onPlaylistMetadataChanged(int i, MediaMetadata mediaMetadata) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onRepeatModeChanged(int i, int i2, int i3, int i4, int i5) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onSearchResultChanged(int i, String str, int i2, MediaLibraryService.LibraryParams libraryParams) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onSeekCompleted(int i, long j, long j2, long j3) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onSessionResult(int i, SessionResult sessionResult) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onShuffleModeChanged(int i, int i2, int i3, int i4, int i5) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onSubtitleData(int i, MediaItem mediaItem, SessionPlayer.TrackInfo trackInfo, SubtitleData subtitleData) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onTrackDeselected(int i, SessionPlayer.TrackInfo trackInfo) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onTrackInfoChanged(int i, List<SessionPlayer.TrackInfo> list, SessionPlayer.TrackInfo trackInfo, SessionPlayer.TrackInfo trackInfo2, SessionPlayer.TrackInfo trackInfo3, SessionPlayer.TrackInfo trackInfo4) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onTrackSelected(int i, SessionPlayer.TrackInfo trackInfo) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void onVideoSizeChanged(int i, MediaItem mediaItem, VideoSize videoSize) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void sendCustomCommand(int i, SessionCommand sessionCommand, Bundle bundle) throws RemoteException;

        /* access modifiers changed from: package-private */
        public abstract void setCustomLayout(int i, List<CommandButton> list) throws RemoteException;

        ControllerCb() {
        }
    }

    static abstract class BuilderBase<T extends MediaSession, U extends BuilderBase<T, U, C>, C extends SessionCallback> {
        C mCallback;
        Executor mCallbackExecutor;
        final Context mContext;
        Bundle mExtras;
        String mId;
        SessionPlayer mPlayer;
        PendingIntent mSessionActivity;

        /* access modifiers changed from: package-private */
        public abstract T build();

        BuilderBase(Context context, SessionPlayer sessionPlayer) {
            if (context == null) {
                throw new NullPointerException("context shouldn't be null");
            } else if (sessionPlayer != null) {
                this.mContext = context;
                this.mPlayer = sessionPlayer;
                this.mId = "";
            } else {
                throw new NullPointerException("player shouldn't be null");
            }
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
            throw new NullPointerException("id shouldn't be null");
        }

        /* access modifiers changed from: package-private */
        public U setSessionCallback(Executor executor, C c) {
            if (executor == null) {
                throw new NullPointerException("executor shouldn't be null");
            } else if (c != null) {
                this.mCallbackExecutor = executor;
                this.mCallback = c;
                return this;
            } else {
                throw new NullPointerException("callback shouldn't be null");
            }
        }

        public U setExtras(Bundle bundle) {
            if (bundle != null) {
                this.mExtras = bundle;
                return this;
            }
            throw new NullPointerException("extras shouldn't be null");
        }
    }
}

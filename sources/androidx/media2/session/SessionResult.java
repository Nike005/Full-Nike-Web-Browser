package androidx.media2.session;

import android.os.Bundle;
import android.os.SystemClock;
import androidx.media2.common.MediaItem;
import androidx.media2.common.SessionPlayer;
import androidx.media2.session.futures.ResolvableFuture;
import androidx.versionedparcelable.CustomVersionedParcelable;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class SessionResult extends CustomVersionedParcelable implements RemoteResult {
    public static final int RESULT_SUCCESS = 0;
    long mCompletionTime;
    Bundle mCustomCommandResult;
    MediaItem mItem;
    MediaItem mParcelableItem;
    int mResultCode;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ResultCode {
    }

    public SessionResult(int i, Bundle bundle) {
        this(i, bundle, (MediaItem) null, SystemClock.elapsedRealtime());
    }

    SessionResult() {
    }

    SessionResult(int i) {
        this(i, (Bundle) null);
    }

    SessionResult(int i, Bundle bundle, MediaItem mediaItem) {
        this(i, bundle, mediaItem, SystemClock.elapsedRealtime());
    }

    SessionResult(int i, Bundle bundle, MediaItem mediaItem, long j) {
        this.mResultCode = i;
        this.mCustomCommandResult = bundle;
        this.mItem = mediaItem;
        this.mCompletionTime = j;
    }

    static SessionResult from(SessionPlayer.PlayerResult playerResult) {
        if (playerResult == null) {
            return null;
        }
        return new SessionResult(playerResult.getResultCode(), (Bundle) null, playerResult.getMediaItem(), playerResult.getCompletionTime());
    }

    static ListenableFuture<SessionResult> createFutureWithResult(int i) {
        ResolvableFuture create = ResolvableFuture.create();
        create.set(new SessionResult(i));
        return create;
    }

    public int getResultCode() {
        return this.mResultCode;
    }

    public Bundle getCustomCommandResult() {
        return this.mCustomCommandResult;
    }

    public long getCompletionTime() {
        return this.mCompletionTime;
    }

    public MediaItem getMediaItem() {
        return this.mItem;
    }

    public void onPreParceling(boolean z) {
        this.mParcelableItem = MediaUtils.upcastForPreparceling(this.mItem);
    }

    public void onPostParceling() {
        this.mItem = this.mParcelableItem;
        this.mParcelableItem = null;
    }
}

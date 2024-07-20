package androidx.media2.exoplayer.external.source;

import androidx.media2.exoplayer.external.ExoPlayer;
import androidx.media2.exoplayer.external.source.MediaSource;
import androidx.media2.exoplayer.external.upstream.TransferListener;

public abstract /* synthetic */ class MediaSource$$CC {
    public static Object getTag$$dflt$$(MediaSource mediaSource) {
        return null;
    }

    @Deprecated
    public static void prepareSource$$dflt$$(MediaSource mediaSource, ExoPlayer exoPlayer, boolean z, MediaSource.SourceInfoRefreshListener sourceInfoRefreshListener, TransferListener transferListener) {
        mediaSource.prepareSource(sourceInfoRefreshListener, transferListener);
    }
}

package androidx.media2.exoplayer.external.source;

import android.net.Uri;
import androidx.media2.exoplayer.external.offline.StreamKey;
import java.util.List;

public interface MediaSourceFactory {
    MediaSource createMediaSource(Uri uri);

    int[] getSupportedTypes();

    MediaSourceFactory setStreamKeys(List<StreamKey> list);
}

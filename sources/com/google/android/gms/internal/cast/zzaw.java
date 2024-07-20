package com.google.android.gms.internal.cast;

import android.widget.TextView;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import java.util.ArrayList;
import java.util.List;

public final class zzaw extends UIController {
    private final TextView zzqf;
    private final List<String> zzqg;

    public zzaw(TextView textView, List<String> list) {
        ArrayList arrayList = new ArrayList();
        this.zzqg = arrayList;
        this.zzqf = textView;
        arrayList.addAll(list);
    }

    public final void onMediaStatusUpdated() {
        MediaQueueItem preloadedItem;
        MediaInfo media;
        MediaMetadata metadata;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession() && (preloadedItem = remoteMediaClient.getPreloadedItem()) != null && (media = preloadedItem.getMedia()) != null && (metadata = media.getMetadata()) != null) {
            for (String next : this.zzqg) {
                if (metadata.containsKey(next)) {
                    this.zzqf.setText(metadata.getString(next));
                    return;
                }
            }
            this.zzqf.setText("");
        }
    }
}

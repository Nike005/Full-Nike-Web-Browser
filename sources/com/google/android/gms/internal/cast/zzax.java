package com.google.android.gms.internal.cast;

import android.widget.TextView;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import java.util.ArrayList;
import java.util.List;

public final class zzax extends UIController {
    private final TextView zzqf;
    private final List<String> zzqg;

    public zzax(TextView textView, List<String> list) {
        ArrayList arrayList = new ArrayList();
        this.zzqg = arrayList;
        this.zzqf = textView;
        arrayList.addAll(list);
    }

    public final void onMediaStatusUpdated() {
        MediaInfo mediaInfo;
        MediaMetadata metadata;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession() && (mediaInfo = remoteMediaClient.getMediaStatus().getMediaInfo()) != null && (metadata = mediaInfo.getMetadata()) != null) {
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

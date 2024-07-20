package com.google.android.exoplayer2.offline;

import com.google.android.exoplayer2.offline.DownloadManager;
import java.util.Comparator;

/* renamed from: com.google.android.exoplayer2.offline.-$$Lambda$DownloadManager$InternalHandler$NXQcmC9peGeDWV0s_8pBfzdJpS0 */
/* compiled from: lambda */
public final /* synthetic */ class C5276x7c64c816 implements Comparator {
    public static final /* synthetic */ C5276x7c64c816 INSTANCE = new C5276x7c64c816();

    private /* synthetic */ C5276x7c64c816() {
    }

    public final int compare(Object obj, Object obj2) {
        return DownloadManager.InternalHandler.compareStartTimes((Download) obj, (Download) obj2);
    }
}

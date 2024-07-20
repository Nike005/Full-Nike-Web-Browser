package com.danikula.videocache.file;

import java.io.File;

public class TotalCountLruDiskUsage extends LruDiskUsage {
    private final int maxCount;

    public TotalCountLruDiskUsage(int i) {
        if (i > 0) {
            this.maxCount = i;
            return;
        }
        throw new IllegalArgumentException("Max count must be positive number!");
    }

    /* access modifiers changed from: protected */
    public boolean accept(File file, long j, int i) {
        return i <= this.maxCount;
    }
}

package com.danikula.videocache.file;

import java.io.File;

public class TotalSizeLruDiskUsage extends LruDiskUsage {
    private final long maxSize;

    public TotalSizeLruDiskUsage(long j) {
        if (j > 0) {
            this.maxSize = j;
            return;
        }
        throw new IllegalArgumentException("Max size must be positive number!");
    }

    /* access modifiers changed from: protected */
    public boolean accept(File file, long j, int i) {
        return j <= this.maxSize;
    }
}

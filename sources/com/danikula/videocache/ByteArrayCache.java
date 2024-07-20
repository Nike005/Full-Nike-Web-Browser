package com.danikula.videocache;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

public class ByteArrayCache implements Cache {
    private volatile boolean completed;
    private volatile byte[] data;

    public void close() throws ProxyCacheException {
    }

    public ByteArrayCache() {
        this(new byte[0]);
    }

    public ByteArrayCache(byte[] bArr) {
        this.data = (byte[]) Preconditions.checkNotNull(bArr);
    }

    public int read(byte[] bArr, long j, int i) throws ProxyCacheException {
        if (j >= ((long) this.data.length)) {
            return -1;
        }
        if (j <= 2147483647L) {
            return new ByteArrayInputStream(this.data).read(bArr, (int) j, i);
        }
        throw new IllegalArgumentException("Too long offset for memory cache " + j);
    }

    public long available() throws ProxyCacheException {
        return (long) this.data.length;
    }

    public void append(byte[] bArr, int i) throws ProxyCacheException {
        Preconditions.checkNotNull(this.data);
        Preconditions.checkArgument(i >= 0 && i <= bArr.length);
        byte[] copyOf = Arrays.copyOf(this.data, this.data.length + i);
        System.arraycopy(bArr, 0, copyOf, this.data.length, i);
        this.data = copyOf;
    }

    public void complete() {
        this.completed = true;
    }

    public boolean isCompleted() {
        return this.completed;
    }
}

package com.danikula.videocache;

import java.io.ByteArrayInputStream;

public class ByteArraySource implements Source {
    private ByteArrayInputStream arrayInputStream;
    private final byte[] data;

    public void close() throws ProxyCacheException {
    }

    public ByteArraySource(byte[] bArr) {
        this.data = bArr;
    }

    public int read(byte[] bArr) throws ProxyCacheException {
        return this.arrayInputStream.read(bArr, 0, bArr.length);
    }

    public long length() throws ProxyCacheException {
        return (long) this.data.length;
    }

    public void open(long j) throws ProxyCacheException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.data);
        this.arrayInputStream = byteArrayInputStream;
        byteArrayInputStream.skip(j);
    }
}

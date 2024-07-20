package androidx.media2;

import java.io.Closeable;
import java.io.IOException;

public abstract class CallbackDataSource2 implements Closeable {
    public abstract long getSize() throws IOException;

    public abstract int readAt(long j, byte[] bArr, int i, int i2) throws IOException;
}

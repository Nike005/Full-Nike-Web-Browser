package androidx.media2.exoplayer.external.upstream;

import android.net.Uri;
import androidx.media2.exoplayer.external.upstream.DataSource;
import java.io.IOException;
import java.util.Map;

public final class DummyDataSource implements DataSource {
    public static final DataSource.Factory FACTORY = DummyDataSource$$Lambda$0.$instance;
    public static final DummyDataSource INSTANCE = new DummyDataSource();

    static final /* bridge */ /* synthetic */ DummyDataSource bridge$lambda$0$DummyDataSource() {
        return new DummyDataSource();
    }

    public void addTransferListener(TransferListener transferListener) {
    }

    public void close() {
    }

    public Map getResponseHeaders() {
        return DataSource$$CC.getResponseHeaders$$dflt$$(this);
    }

    public Uri getUri() {
        return null;
    }

    private DummyDataSource() {
    }

    public long open(DataSpec dataSpec) throws IOException {
        throw new IOException("Dummy source");
    }

    public int read(byte[] bArr, int i, int i2) {
        throw new UnsupportedOperationException();
    }
}

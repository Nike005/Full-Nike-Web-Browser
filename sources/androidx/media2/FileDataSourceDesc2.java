package androidx.media2;

import androidx.core.util.Preconditions;
import androidx.media2.DataSourceDesc2;
import java.io.FileDescriptor;

public class FileDataSourceDesc2 extends DataSourceDesc2 {
    public static final long FD_LENGTH_UNKNOWN = 576460752303423487L;
    FileDescriptor mFD;
    long mFDLength = 576460752303423487L;
    long mFDOffset = 0;

    public int getType() {
        return 2;
    }

    FileDataSourceDesc2(Builder builder) {
        super(builder);
        this.mFD = builder.mFD;
        this.mFDOffset = builder.mFDOffset;
        this.mFDLength = builder.mFDLength;
    }

    public FileDescriptor getFileDescriptor() {
        return this.mFD;
    }

    public long getFileDescriptorOffset() {
        return this.mFDOffset;
    }

    public long getFileDescriptorLength() {
        return this.mFDLength;
    }

    public static final class Builder extends DataSourceDesc2.Builder<Builder> {
        FileDescriptor mFD;
        long mFDLength = 576460752303423487L;
        long mFDOffset = 0;

        public Builder(FileDescriptor fileDescriptor) {
            Preconditions.checkNotNull(fileDescriptor);
            this.mFD = fileDescriptor;
            this.mFDOffset = 0;
            this.mFDLength = 576460752303423487L;
        }

        public Builder(FileDescriptor fileDescriptor, long j, long j2) {
            Preconditions.checkNotNull(fileDescriptor);
            j = j < 0 ? 0 : j;
            j2 = j2 < 0 ? 576460752303423487L : j2;
            this.mFD = fileDescriptor;
            this.mFDOffset = j;
            this.mFDLength = j2;
        }

        public FileDataSourceDesc2 build() {
            return new FileDataSourceDesc2(this);
        }
    }
}

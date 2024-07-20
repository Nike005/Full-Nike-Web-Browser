package androidx.media2.common;

import androidx.core.util.Preconditions;
import androidx.media2.common.MediaItem;

public class CallbackMediaItem extends MediaItem {
    DataSourceCallback mDataSourceCallback;

    CallbackMediaItem() {
    }

    CallbackMediaItem(Builder builder) {
        super((MediaItem.Builder) builder);
        this.mDataSourceCallback = builder.mDataSourceCallback;
    }

    public DataSourceCallback getDataSourceCallback() {
        return this.mDataSourceCallback;
    }

    public static final class Builder extends MediaItem.Builder {
        DataSourceCallback mDataSourceCallback;

        public Builder(DataSourceCallback dataSourceCallback) {
            Preconditions.checkNotNull(dataSourceCallback);
            this.mDataSourceCallback = dataSourceCallback;
        }

        public Builder setMetadata(MediaMetadata mediaMetadata) {
            return (Builder) super.setMetadata(mediaMetadata);
        }

        public Builder setStartPosition(long j) {
            return (Builder) super.setStartPosition(j);
        }

        public Builder setEndPosition(long j) {
            return (Builder) super.setEndPosition(j);
        }

        public CallbackMediaItem build() {
            return new CallbackMediaItem(this);
        }
    }
}

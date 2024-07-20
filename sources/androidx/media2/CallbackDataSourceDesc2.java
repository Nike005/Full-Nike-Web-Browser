package androidx.media2;

import androidx.core.util.Preconditions;
import androidx.media2.DataSourceDesc2;

public class CallbackDataSourceDesc2 extends DataSourceDesc2 {
    CallbackDataSource2 mCallbackDataSource2;

    public int getType() {
        return 1;
    }

    CallbackDataSourceDesc2(Builder builder) {
        super(builder);
        this.mCallbackDataSource2 = builder.mCallbackDataSource2;
    }

    public CallbackDataSource2 getCallbackDataSource2() {
        return this.mCallbackDataSource2;
    }

    public static final class Builder extends DataSourceDesc2.Builder<Builder> {
        CallbackDataSource2 mCallbackDataSource2;

        public Builder(CallbackDataSource2 callbackDataSource2) {
            Preconditions.checkNotNull(callbackDataSource2);
            this.mCallbackDataSource2 = callbackDataSource2;
        }

        public CallbackDataSourceDesc2 build() {
            return new CallbackDataSourceDesc2(this);
        }
    }
}

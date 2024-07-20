package com.android.billingclient.api;

public final class ConsumeParams {
    /* access modifiers changed from: private */
    public String developerPayload;
    /* access modifiers changed from: private */
    public String purchaseToken;

    private ConsumeParams() {
    }

    public String getDeveloperPayload() {
        return this.developerPayload;
    }

    public String getPurchaseToken() {
        return this.purchaseToken;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private String developerPayload;
        private String purchaseToken;

        private Builder() {
        }

        public Builder setPurchaseToken(String str) {
            this.purchaseToken = str;
            return this;
        }

        public Builder setDeveloperPayload(String str) {
            this.developerPayload = str;
            return this;
        }

        public ConsumeParams build() {
            ConsumeParams consumeParams = new ConsumeParams();
            String unused = consumeParams.purchaseToken = this.purchaseToken;
            String unused2 = consumeParams.developerPayload = this.developerPayload;
            return consumeParams;
        }
    }
}

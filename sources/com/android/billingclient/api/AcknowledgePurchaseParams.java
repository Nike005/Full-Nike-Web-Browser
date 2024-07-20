package com.android.billingclient.api;

public final class AcknowledgePurchaseParams {
    /* access modifiers changed from: private */
    public String developerPayload;
    /* access modifiers changed from: private */
    public String purchaseToken;

    private AcknowledgePurchaseParams() {
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

        public AcknowledgePurchaseParams build() {
            AcknowledgePurchaseParams acknowledgePurchaseParams = new AcknowledgePurchaseParams();
            String unused = acknowledgePurchaseParams.developerPayload = this.developerPayload;
            String unused2 = acknowledgePurchaseParams.purchaseToken = this.purchaseToken;
            return acknowledgePurchaseParams;
        }
    }
}

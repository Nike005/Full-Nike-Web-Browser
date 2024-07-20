package com.android.billingclient.api;

import android.text.TextUtils;
import com.android.billingclient.util.BillingHelper;
import com.google.android.gms.plus.PlusShare;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class SkuDetails {
    private final String mOriginalJson;
    private final JSONObject mParsedJson = new JSONObject(this.mOriginalJson);

    public SkuDetails(String str) throws JSONException {
        this.mOriginalJson = str;
    }

    public String getOriginalJson() {
        return this.mOriginalJson;
    }

    public String getSku() {
        return this.mParsedJson.optString("productId");
    }

    public String getType() {
        return this.mParsedJson.optString("type");
    }

    public String getPrice() {
        return this.mParsedJson.optString("price");
    }

    public long getPriceAmountMicros() {
        return this.mParsedJson.optLong("price_amount_micros");
    }

    public String getPriceCurrencyCode() {
        return this.mParsedJson.optString("price_currency_code");
    }

    public String getOriginalPrice() {
        if (this.mParsedJson.has("original_price")) {
            return this.mParsedJson.optString("original_price");
        }
        return getPrice();
    }

    public long getOriginalPriceAmountMicros() {
        if (this.mParsedJson.has("original_price_micros")) {
            return this.mParsedJson.optLong("original_price_micros");
        }
        return getPriceAmountMicros();
    }

    public String getTitle() {
        return this.mParsedJson.optString("title");
    }

    public String getDescription() {
        return this.mParsedJson.optString(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION);
    }

    public String getSubscriptionPeriod() {
        return this.mParsedJson.optString("subscriptionPeriod");
    }

    public String getFreeTrialPeriod() {
        return this.mParsedJson.optString("freeTrialPeriod");
    }

    public String getIntroductoryPrice() {
        return this.mParsedJson.optString("introductoryPrice");
    }

    public long getIntroductoryPriceAmountMicros() {
        return this.mParsedJson.optLong("introductoryPriceAmountMicros");
    }

    public String getIntroductoryPricePeriod() {
        return this.mParsedJson.optString("introductoryPricePeriod");
    }

    public String getIntroductoryPriceCycles() {
        return this.mParsedJson.optString("introductoryPriceCycles");
    }

    public String getIconUrl() {
        return this.mParsedJson.optString("iconUrl");
    }

    public boolean isRewarded() {
        return this.mParsedJson.has(BillingFlowParams.EXTRA_PARAM_KEY_RSKU);
    }

    /* access modifiers changed from: package-private */
    public String getSkuDetailsToken() {
        return this.mParsedJson.optString(BillingHelper.EXTRA_PARAM_KEY_SKU_DETAILS_TOKEN);
    }

    /* access modifiers changed from: package-private */
    public String rewardToken() {
        return this.mParsedJson.optString(BillingFlowParams.EXTRA_PARAM_KEY_RSKU);
    }

    public String toString() {
        return "SkuDetails: " + this.mOriginalJson;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return TextUtils.equals(this.mOriginalJson, ((SkuDetails) obj).mOriginalJson);
    }

    public int hashCode() {
        return this.mOriginalJson.hashCode();
    }

    public static class SkuDetailsResult {
        private String mDebugMessage;
        private int mResponseCode;
        private List<SkuDetails> mSkuDetailsList;

        public SkuDetailsResult(int i, String str, List<SkuDetails> list) {
            this.mResponseCode = i;
            this.mDebugMessage = str;
            this.mSkuDetailsList = list;
        }

        public List<SkuDetails> getSkuDetailsList() {
            return this.mSkuDetailsList;
        }

        public int getResponseCode() {
            return this.mResponseCode;
        }

        public String getDebugMessage() {
            return this.mDebugMessage;
        }
    }
}

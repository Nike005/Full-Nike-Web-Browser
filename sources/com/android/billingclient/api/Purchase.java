package com.android.billingclient.api;

import android.text.TextUtils;
import com.android.billingclient.util.BillingHelper;
import com.google.firebase.messaging.Constants;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class Purchase {
    private final String mOriginalJson;
    private final JSONObject mParsedJson = new JSONObject(this.mOriginalJson);
    private final String mSignature;

    @Retention(RetentionPolicy.SOURCE)
    public @interface PurchaseState {
        public static final int PENDING = 2;
        public static final int PURCHASED = 1;
        public static final int UNSPECIFIED_STATE = 0;
    }

    public Purchase(String str, String str2) throws JSONException {
        this.mOriginalJson = str;
        this.mSignature = str2;
    }

    public String getOrderId() {
        return this.mParsedJson.optString("orderId");
    }

    public String getPackageName() {
        return this.mParsedJson.optString(Constants.FirelogAnalytics.PARAM_PACKAGE_NAME);
    }

    public String getSku() {
        return this.mParsedJson.optString("productId");
    }

    public long getPurchaseTime() {
        return this.mParsedJson.optLong("purchaseTime");
    }

    public String getPurchaseToken() {
        JSONObject jSONObject = this.mParsedJson;
        return jSONObject.optString("token", jSONObject.optString("purchaseToken"));
    }

    public int getPurchaseState() {
        return this.mParsedJson.optInt("purchaseState", 1) != 4 ? 1 : 2;
    }

    public String getDeveloperPayload() {
        return this.mParsedJson.optString(BillingHelper.EXTRA_PARAMS_DEVELOPER_PAYLOAD);
    }

    public boolean isAcknowledged() {
        return this.mParsedJson.optBoolean("acknowledged", true);
    }

    public boolean isAutoRenewing() {
        return this.mParsedJson.optBoolean("autoRenewing");
    }

    public String getOriginalJson() {
        return this.mOriginalJson;
    }

    public String getSignature() {
        return this.mSignature;
    }

    public String toString() {
        return "Purchase. Json: " + this.mOriginalJson;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Purchase)) {
            return false;
        }
        Purchase purchase = (Purchase) obj;
        if (!TextUtils.equals(this.mOriginalJson, purchase.getOriginalJson()) || !TextUtils.equals(this.mSignature, purchase.getSignature())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.mOriginalJson.hashCode();
    }

    public static class PurchasesResult {
        private BillingResult mBillingResult;
        private List<Purchase> mPurchaseList;

        public PurchasesResult(BillingResult billingResult, List<Purchase> list) {
            this.mPurchaseList = list;
            this.mBillingResult = billingResult;
        }

        public BillingResult getBillingResult() {
            return this.mBillingResult;
        }

        public int getResponseCode() {
            return getBillingResult().getResponseCode();
        }

        public List<Purchase> getPurchasesList() {
            return this.mPurchaseList;
        }
    }
}

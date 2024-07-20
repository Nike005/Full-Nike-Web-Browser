package com.android.billingclient.api;

import android.text.TextUtils;
import com.android.billingclient.util.BillingHelper;
import org.json.JSONException;
import org.json.JSONObject;

public class PurchaseHistoryRecord {
    private final String mOriginalJson;
    private final JSONObject mParsedJson = new JSONObject(this.mOriginalJson);
    private final String mSignature;

    public PurchaseHistoryRecord(String str, String str2) throws JSONException {
        this.mOriginalJson = str;
        this.mSignature = str2;
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

    public String getDeveloperPayload() {
        return this.mParsedJson.optString(BillingHelper.EXTRA_PARAMS_DEVELOPER_PAYLOAD);
    }

    public String getOriginalJson() {
        return this.mOriginalJson;
    }

    public String getSignature() {
        return this.mSignature;
    }

    public String toString() {
        return "PurchaseHistoryRecord. Json: " + this.mOriginalJson;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PurchaseHistoryRecord)) {
            return false;
        }
        PurchaseHistoryRecord purchaseHistoryRecord = (PurchaseHistoryRecord) obj;
        if (!TextUtils.equals(this.mOriginalJson, purchaseHistoryRecord.getOriginalJson()) || !TextUtils.equals(this.mSignature, purchaseHistoryRecord.getSignature())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.mOriginalJson.hashCode();
    }
}

package com.android.billingclient.api;

import android.os.Bundle;
import com.android.billingclient.util.BillingHelper;
import java.util.ArrayList;

final class PurchaseApiResponseChecker {
    PurchaseApiResponseChecker() {
    }

    static BillingResult checkPurchasesBundleValidity(Bundle bundle, String str, String str2) {
        BillingResult billingResult = BillingResults.INTERNAL_ERROR;
        if (bundle == null) {
            BillingHelper.logWarn(str, String.format("%s got null owned items list", new Object[]{str2}));
            return billingResult;
        }
        int responseCodeFromBundle = BillingHelper.getResponseCodeFromBundle(bundle, str);
        BillingResult build = BillingResult.newBuilder().setResponseCode(responseCodeFromBundle).setDebugMessage(BillingHelper.getDebugMessageFromBundle(bundle, str)).build();
        if (responseCodeFromBundle != 0) {
            BillingHelper.logWarn(str, String.format("%s failed. Response code: %s", new Object[]{str2, Integer.valueOf(responseCodeFromBundle)}));
            return build;
        } else if (!bundle.containsKey(BillingHelper.RESPONSE_INAPP_ITEM_LIST) || !bundle.containsKey(BillingHelper.RESPONSE_INAPP_PURCHASE_DATA_LIST) || !bundle.containsKey(BillingHelper.RESPONSE_INAPP_SIGNATURE_LIST)) {
            BillingHelper.logWarn(str, String.format("Bundle returned from %s doesn't contain required fields.", new Object[]{str2}));
            return billingResult;
        } else {
            ArrayList<String> stringArrayList = bundle.getStringArrayList(BillingHelper.RESPONSE_INAPP_ITEM_LIST);
            ArrayList<String> stringArrayList2 = bundle.getStringArrayList(BillingHelper.RESPONSE_INAPP_PURCHASE_DATA_LIST);
            ArrayList<String> stringArrayList3 = bundle.getStringArrayList(BillingHelper.RESPONSE_INAPP_SIGNATURE_LIST);
            if (stringArrayList == null) {
                BillingHelper.logWarn(str, String.format("Bundle returned from %s contains null SKUs list.", new Object[]{str2}));
                return billingResult;
            } else if (stringArrayList2 == null) {
                BillingHelper.logWarn(str, String.format("Bundle returned from %s contains null purchases list.", new Object[]{str2}));
                return billingResult;
            } else if (stringArrayList3 != null) {
                return BillingResults.f4136OK;
            } else {
                BillingHelper.logWarn(str, String.format("Bundle returned from %s contains null signatures list.", new Object[]{str2}));
                return billingResult;
            }
        }
    }
}

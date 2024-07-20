package com.appsgeyser.sdk.inapp;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.appsgeyser.sdk.configuration.Constants;
import com.appsgeyser.sdk.inapp.InAppPurchaseController;
import com.appsgeyser.sdk.server.StatController;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class InAppPurchaseController implements PurchasesUpdatedListener {
    public static final String inAppPurchaseLogTag = "inAppPurchaseTag";
    private static InAppPurchaseController instance;
    /* access modifiers changed from: private */
    public BillingClient billingClient;
    private Context context;
    /* access modifiers changed from: private */
    public boolean disableAdsPurchased;
    private SkuDetails disableAdsSkudetails;
    Handler handler;
    /* access modifiers changed from: private */
    public boolean isDisableAdsPurchaseButtonHidden;
    final HashMap<String, String> reportingDetails = new HashMap<>();

    private InAppPurchaseController() {
    }

    public static synchronized InAppPurchaseController getInstance() {
        InAppPurchaseController inAppPurchaseController;
        synchronized (InAppPurchaseController.class) {
            if (instance == null) {
                instance = new InAppPurchaseController();
            }
            inAppPurchaseController = instance;
        }
        return inAppPurchaseController;
    }

    public void init(Context context2) {
        this.context = context2;
        this.handler = new Handler(context2.getMainLooper());
        this.billingClient = BillingClient.newBuilder(context2).setListener(this).enablePendingPurchases().build();
        Log.d(inAppPurchaseLogTag, "init GP billing client");
        connectToBilling();
    }

    /* access modifiers changed from: private */
    public void connectToBilling() {
        this.billingClient.startConnection(new BillingClientStateListener() {
            public void onBillingSetupFinished(BillingResult billingResult) {
                if (billingResult.getResponseCode() == 0) {
                    boolean unused = InAppPurchaseController.this.isDisableAdsPurchaseButtonHidden = false;
                    Log.d(InAppPurchaseController.inAppPurchaseLogTag, "GP billing client successfully connected");
                    InAppPurchaseController.this.queryDisableAdsInAppProductDetails();
                    for (Purchase next : InAppPurchaseController.this.billingClient.queryPurchases(BillingClient.SkuType.INAPP).getPurchasesList()) {
                        if (next.getSku().equals(Constants.INAPP_PURCHASES_ID_DISABLE_ADS) && next.getPurchaseState() == 1) {
                            boolean unused2 = InAppPurchaseController.this.disableAdsPurchased = true;
                            boolean unused3 = InAppPurchaseController.this.isDisableAdsPurchaseButtonHidden = true;
                            if (!next.isAcknowledged()) {
                                InAppPurchaseController.this.acknowledgeDisableAdsPurchase(next);
                            }
                        }
                    }
                    return;
                }
                Log.d(InAppPurchaseController.inAppPurchaseLogTag, "GP billing client init fucked up, response code: " + billingResult.getResponseCode());
                InAppPurchaseController.this.handler.postDelayed(new Runnable() {
                    public final void run() {
                        InAppPurchaseController.C50791.this.lambda$onBillingSetupFinished$0$InAppPurchaseController$1();
                    }
                }, 60000);
            }

            public /* synthetic */ void lambda$onBillingSetupFinished$0$InAppPurchaseController$1() {
                InAppPurchaseController.this.connectToBilling();
            }

            public void onBillingServiceDisconnected() {
                boolean unused = InAppPurchaseController.this.isDisableAdsPurchaseButtonHidden = true;
                Log.d(InAppPurchaseController.inAppPurchaseLogTag, "GP billing client disconnected");
                InAppPurchaseController.this.handler.postDelayed(new Runnable() {
                    public final void run() {
                        InAppPurchaseController.C50791.this.lambda$onBillingServiceDisconnected$1$InAppPurchaseController$1();
                    }
                }, 60000);
            }

            public /* synthetic */ void lambda$onBillingServiceDisconnected$1$InAppPurchaseController$1() {
                InAppPurchaseController.this.connectToBilling();
            }
        });
    }

    /* access modifiers changed from: private */
    public void queryDisableAdsInAppProductDetails() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Constants.INAPP_PURCHASES_ID_DISABLE_ADS);
        SkuDetailsParams.Builder newBuilder = SkuDetailsParams.newBuilder();
        newBuilder.setSkusList(arrayList).setType(BillingClient.SkuType.INAPP);
        this.billingClient.querySkuDetailsAsync(newBuilder.build(), new SkuDetailsResponseListener() {
            public final void onSkuDetailsResponse(BillingResult billingResult, List list) {
                InAppPurchaseController.this.mo41705xc215909d(billingResult, list);
            }
        });
    }

    /* renamed from: lambda$queryDisableAdsInAppProductDetails$0$InAppPurchaseController */
    public /* synthetic */ void mo41705xc215909d(BillingResult billingResult, List list) {
        if (billingResult.getResponseCode() == 0) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                SkuDetails skuDetails = (SkuDetails) it.next();
                if (skuDetails.getSku().equals(Constants.INAPP_PURCHASES_ID_DISABLE_ADS)) {
                    this.disableAdsSkudetails = skuDetails;
                }
            }
        }
    }

    public void launchDisableAdsBillingFlow(Activity activity) {
        if (this.billingClient != null) {
            this.billingClient.launchBillingFlow(activity, BillingFlowParams.newBuilder().setSkuDetails(this.disableAdsSkudetails).build());
        }
    }

    public void onPurchasesUpdated(BillingResult billingResult, List<Purchase> list) {
        Log.d(inAppPurchaseLogTag, "onPurchasesUpdated fired");
        if (billingResult.getResponseCode() == 0 && list != null) {
            for (Purchase handlePurchase : list) {
                handlePurchase(handlePurchase);
            }
        } else if (billingResult.getResponseCode() == 1) {
            Log.d(inAppPurchaseLogTag, "user cancelled purchase");
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_INAPP_DISABLE_ADS_CANCELED, this.reportingDetails, this.context, true);
        } else {
            Log.d(inAppPurchaseLogTag, "some weird shit with purchase: code " + billingResult.getResponseCode());
        }
    }

    private void handlePurchase(Purchase purchase) {
        if (purchase.getSku().equals(Constants.INAPP_PURCHASES_ID_DISABLE_ADS) && purchase.getPurchaseState() == 1) {
            this.disableAdsPurchased = true;
            this.isDisableAdsPurchaseButtonHidden = true;
            Log.d(inAppPurchaseLogTag, "purchase successfull");
            if (!purchase.isAcknowledged()) {
                acknowledgeDisableAdsPurchase(purchase);
            }
        } else if (purchase.getPurchaseState() == 2) {
            this.reportingDetails.put("item", purchase.getSku());
            Calendar instance2 = Calendar.getInstance();
            instance2.setTimeInMillis(purchase.getPurchaseTime());
            this.reportingDetails.put("date", instance2.getTime().toString());
            Log.d(inAppPurchaseLogTag, "purchase pending");
            StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_INAPP_DISABLE_ADS_PENDING, this.reportingDetails, this.context, true);
        }
    }

    /* access modifiers changed from: private */
    public void acknowledgeDisableAdsPurchase(Purchase purchase) {
        Log.d(inAppPurchaseLogTag, "attempt to acknowledge purchase");
        this.billingClient.acknowledgePurchase(AcknowledgePurchaseParams.newBuilder().setPurchaseToken(purchase.getPurchaseToken()).build(), new AcknowledgePurchaseResponseListener(purchase) {
            public final /* synthetic */ Purchase f$1;

            {
                this.f$1 = r2;
            }

            public final void onAcknowledgePurchaseResponse(BillingResult billingResult) {
                InAppPurchaseController.this.lambda$acknowledgeDisableAdsPurchase$1$InAppPurchaseController(this.f$1, billingResult);
            }
        });
    }

    public /* synthetic */ void lambda$acknowledgeDisableAdsPurchase$1$InAppPurchaseController(Purchase purchase, BillingResult billingResult) {
        this.reportingDetails.put("item", purchase.getSku());
        Calendar instance2 = Calendar.getInstance();
        instance2.setTimeInMillis(purchase.getPurchaseTime());
        this.reportingDetails.put("date", instance2.getTime().toString());
        Log.d(inAppPurchaseLogTag, "purchase successfully acknowledged");
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_INAPP_DISABLE_ADS_ACKNOWLEDGED, this.reportingDetails, this.context, true);
    }

    public boolean isDisableAdsPurchased() {
        return this.disableAdsPurchased;
    }

    public boolean isDisableAdsPurchaseButtonHidden() {
        return this.isDisableAdsPurchaseButtonHidden;
    }
}

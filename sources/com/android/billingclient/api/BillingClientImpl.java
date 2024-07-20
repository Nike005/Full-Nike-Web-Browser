package com.android.billingclient.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.ResultReceiver;
import android.text.TextUtils;
import com.android.billingclient.BuildConfig;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.util.BillingHelper;
import com.android.vending.billing.IInAppBillingService;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;

class BillingClientImpl extends BillingClient {
    private static final long ASYNCHRONOUS_TIMEOUT_IN_MILLISECONDS = 30000;
    private static final String GET_SKU_DETAILS_ITEM_LIST = "ITEM_ID_LIST";
    private static final int MAX_IAP_VERSION = 10;
    private static final int MAX_SKU_DETAILS_ITEMS_PER_REQUEST = 20;
    private static final int MIN_IAP_VERSION = 3;
    private static final long SYNCHRONOUS_TIMEOUT_IN_MILLISECONDS = 5000;
    private static final String TAG = "BillingClient";
    /* access modifiers changed from: private */
    public final Context mApplicationContext;
    /* access modifiers changed from: private */
    public final BillingBroadcastManager mBroadcastManager;
    /* access modifiers changed from: private */
    public final int mChildDirected;
    /* access modifiers changed from: private */
    public int mClientState;
    private final boolean mEnablePendingPurchases;
    private ExecutorService mExecutorService;
    /* access modifiers changed from: private */
    public boolean mIABv10Supported;
    /* access modifiers changed from: private */
    public boolean mIABv6Supported;
    /* access modifiers changed from: private */
    public boolean mIABv8Supported;
    /* access modifiers changed from: private */
    public boolean mIABv9Supported;
    /* access modifiers changed from: private */
    public final String mQualifiedVersionNumber;
    /* access modifiers changed from: private */
    public IInAppBillingService mService;
    private BillingServiceConnection mServiceConnection;
    /* access modifiers changed from: private */
    public boolean mSubscriptionUpdateSupported;
    /* access modifiers changed from: private */
    public boolean mSubscriptionsSupported;
    private final Handler mUiThreadHandler;
    /* access modifiers changed from: private */
    public final int mUnderAgeOfConsent;
    private final ResultReceiver onPurchaseFinishedReceiver;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ClientState {
        public static final int CLOSED = 3;
        public static final int CONNECTED = 2;
        public static final int CONNECTING = 1;
        public static final int DISCONNECTED = 0;
    }

    /* access modifiers changed from: package-private */
    public void setExecutorService(ExecutorService executorService) {
        this.mExecutorService = executorService;
    }

    BillingClientImpl(Context context, int i, int i2, boolean z, PurchasesUpdatedListener purchasesUpdatedListener) {
        this(context, i, i2, z, purchasesUpdatedListener, BuildConfig.VERSION_NAME);
    }

    private BillingClientImpl(Activity activity, int i, int i2, boolean z, String str) {
        this(activity.getApplicationContext(), i, i2, z, new BillingClientNativeCallback(), str);
    }

    private BillingClientImpl(Context context, int i, int i2, boolean z, PurchasesUpdatedListener purchasesUpdatedListener, String str) {
        this.mClientState = 0;
        this.mUiThreadHandler = new Handler(Looper.getMainLooper());
        this.onPurchaseFinishedReceiver = new ResultReceiver(this.mUiThreadHandler) {
            public void onReceiveResult(int i, Bundle bundle) {
                PurchasesUpdatedListener listener = BillingClientImpl.this.mBroadcastManager.getListener();
                if (listener == null) {
                    BillingHelper.logWarn(BillingClientImpl.TAG, "PurchasesUpdatedListener is null - no way to return the response.");
                    return;
                }
                listener.onPurchasesUpdated(BillingResult.newBuilder().setResponseCode(i).setDebugMessage(BillingHelper.getDebugMessageFromBundle(bundle, BillingClientImpl.TAG)).build(), BillingHelper.extractPurchases(bundle));
            }
        };
        Context applicationContext = context.getApplicationContext();
        this.mApplicationContext = applicationContext;
        this.mChildDirected = i;
        this.mUnderAgeOfConsent = i2;
        this.mEnablePendingPurchases = z;
        this.mBroadcastManager = new BillingBroadcastManager(applicationContext, purchasesUpdatedListener);
        this.mQualifiedVersionNumber = str;
    }

    public BillingResult isFeatureSupported(String str) {
        if (!isReady()) {
            return BillingResults.SERVICE_DISCONNECTED;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -422092961:
                if (str.equals(BillingClient.FeatureType.SUBSCRIPTIONS_UPDATE)) {
                    c = 1;
                    break;
                }
                break;
            case 207616302:
                if (str.equals(BillingClient.FeatureType.PRICE_CHANGE_CONFIRMATION)) {
                    c = 4;
                    break;
                }
                break;
            case 292218239:
                if (str.equals(BillingClient.FeatureType.IN_APP_ITEMS_ON_VR)) {
                    c = 2;
                    break;
                }
                break;
            case 1219490065:
                if (str.equals(BillingClient.FeatureType.SUBSCRIPTIONS_ON_VR)) {
                    c = 3;
                    break;
                }
                break;
            case 1987365622:
                if (str.equals(BillingClient.FeatureType.SUBSCRIPTIONS)) {
                    c = 0;
                    break;
                }
                break;
        }
        if (c == 0) {
            return this.mSubscriptionsSupported ? BillingResults.f4136OK : BillingResults.FEATURE_NOT_SUPPORTED;
        }
        if (c == 1) {
            return this.mSubscriptionUpdateSupported ? BillingResults.f4136OK : BillingResults.FEATURE_NOT_SUPPORTED;
        }
        if (c == 2) {
            return isBillingSupportedOnVr(BillingClient.SkuType.INAPP);
        }
        if (c == 3) {
            return isBillingSupportedOnVr(BillingClient.SkuType.SUBS);
        }
        if (c == 4) {
            return this.mIABv8Supported ? BillingResults.f4136OK : BillingResults.FEATURE_NOT_SUPPORTED;
        }
        BillingHelper.logWarn(TAG, "Unsupported feature: " + str);
        return BillingResults.UNKNOWN_FEATURE;
    }

    public boolean isReady() {
        return (this.mClientState != 2 || this.mService == null || this.mServiceConnection == null) ? false : true;
    }

    public void startConnection(BillingClientStateListener billingClientStateListener) {
        if (isReady()) {
            BillingHelper.logVerbose(TAG, "Service connection is valid. No need to re-initialize.");
            billingClientStateListener.onBillingSetupFinished(BillingResults.f4136OK);
            return;
        }
        int i = this.mClientState;
        if (i == 1) {
            BillingHelper.logWarn(TAG, "Client is already in the process of connecting to billing service.");
            billingClientStateListener.onBillingSetupFinished(BillingResults.CLIENT_CONNECTING);
        } else if (i == 3) {
            BillingHelper.logWarn(TAG, "Client was already closed and can't be reused. Please create another instance.");
            billingClientStateListener.onBillingSetupFinished(BillingResults.SERVICE_DISCONNECTED);
        } else {
            this.mClientState = 1;
            this.mBroadcastManager.registerReceiver();
            BillingHelper.logVerbose(TAG, "Starting in-app billing setup.");
            this.mServiceConnection = new BillingServiceConnection(billingClientStateListener);
            Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            intent.setPackage("com.android.vending");
            List<ResolveInfo> queryIntentServices = this.mApplicationContext.getPackageManager().queryIntentServices(intent, 0);
            if (queryIntentServices != null && !queryIntentServices.isEmpty()) {
                ResolveInfo resolveInfo = queryIntentServices.get(0);
                if (resolveInfo.serviceInfo != null) {
                    String str = resolveInfo.serviceInfo.packageName;
                    String str2 = resolveInfo.serviceInfo.name;
                    if (!"com.android.vending".equals(str) || str2 == null) {
                        BillingHelper.logWarn(TAG, "The device doesn't have valid Play Store.");
                    } else {
                        ComponentName componentName = new ComponentName(str, str2);
                        Intent intent2 = new Intent(intent);
                        intent2.setComponent(componentName);
                        intent2.putExtra(BillingHelper.LIBRARY_VERSION_KEY, this.mQualifiedVersionNumber);
                        if (this.mApplicationContext.bindService(intent2, this.mServiceConnection, 1)) {
                            BillingHelper.logVerbose(TAG, "Service was bonded successfully.");
                            return;
                        }
                        BillingHelper.logWarn(TAG, "Connection to Billing service is blocked.");
                    }
                }
            }
            this.mClientState = 0;
            BillingHelper.logVerbose(TAG, "Billing service unavailable on device.");
            billingClientStateListener.onBillingSetupFinished(BillingResults.BILLING_UNAVAILABLE);
        }
    }

    private void startConnection(long j) {
        startConnection((BillingClientStateListener) new BillingClientNativeCallback(j));
    }

    public void endConnection() {
        try {
            this.mBroadcastManager.destroy();
            if (this.mServiceConnection != null) {
                this.mServiceConnection.markDisconnectedAndCleanUp();
            }
            if (!(this.mServiceConnection == null || this.mService == null)) {
                BillingHelper.logVerbose(TAG, "Unbinding from service.");
                this.mApplicationContext.unbindService(this.mServiceConnection);
                this.mServiceConnection = null;
            }
            this.mService = null;
            if (this.mExecutorService != null) {
                this.mExecutorService.shutdownNow();
                this.mExecutorService = null;
            }
        } catch (Exception e) {
            BillingHelper.logWarn(TAG, "There was an exception while ending connection: " + e);
        } catch (Throwable th) {
            this.mClientState = 3;
            throw th;
        }
        this.mClientState = 3;
    }

    private void launchPriceChangeConfirmationFlow(Activity activity, PriceChangeFlowParams priceChangeFlowParams, long j) {
        launchPriceChangeConfirmationFlow(activity, priceChangeFlowParams, (PriceChangeConfirmationListener) new BillingClientNativeCallback(j));
    }

    public void launchPriceChangeConfirmationFlow(Activity activity, PriceChangeFlowParams priceChangeFlowParams, final PriceChangeConfirmationListener priceChangeConfirmationListener) {
        if (!isReady()) {
            priceChangeConfirmationListener.onPriceChangeConfirmationResult(BillingResults.SERVICE_DISCONNECTED);
        } else if (priceChangeFlowParams == null || priceChangeFlowParams.getSkuDetails() == null) {
            BillingHelper.logWarn(TAG, "Please fix the input params. priceChangeFlowParams must contain valid sku.");
            priceChangeConfirmationListener.onPriceChangeConfirmationResult(BillingResults.NULL_SKU);
        } else {
            final String sku = priceChangeFlowParams.getSkuDetails().getSku();
            if (sku == null) {
                BillingHelper.logWarn(TAG, "Please fix the input params. priceChangeFlowParams must contain valid sku.");
                priceChangeConfirmationListener.onPriceChangeConfirmationResult(BillingResults.NULL_SKU);
            } else if (!this.mIABv8Supported) {
                BillingHelper.logWarn(TAG, "Current client doesn't support price change confirmation flow.");
                priceChangeConfirmationListener.onPriceChangeConfirmationResult(BillingResults.FEATURE_NOT_SUPPORTED);
            } else {
                final Bundle bundle = new Bundle();
                bundle.putString(BillingHelper.LIBRARY_VERSION_KEY, this.mQualifiedVersionNumber);
                bundle.putBoolean(BillingHelper.EXTRA_PARAM_KEY_SUBS_PRICE_CHANGE, true);
                try {
                    Bundle bundle2 = (Bundle) executeAsync(new Callable<Bundle>() {
                        public Bundle call() throws Exception {
                            return BillingClientImpl.this.mService.getSubscriptionManagementIntent(8, BillingClientImpl.this.mApplicationContext.getPackageName(), sku, BillingClient.SkuType.SUBS, bundle);
                        }
                    }, 5000, (Runnable) null).get(5000, TimeUnit.MILLISECONDS);
                    int responseCodeFromBundle = BillingHelper.getResponseCodeFromBundle(bundle2, TAG);
                    BillingResult build = BillingResult.newBuilder().setResponseCode(responseCodeFromBundle).setDebugMessage(BillingHelper.getDebugMessageFromBundle(bundle2, TAG)).build();
                    if (responseCodeFromBundle != 0) {
                        BillingHelper.logWarn(TAG, "Unable to launch price change flow, error response code: " + responseCodeFromBundle);
                        priceChangeConfirmationListener.onPriceChangeConfirmationResult(build);
                        return;
                    }
                    C46553 r4 = new ResultReceiver(this.mUiThreadHandler) {
                        public void onReceiveResult(int i, Bundle bundle) {
                            priceChangeConfirmationListener.onPriceChangeConfirmationResult(BillingResult.newBuilder().setResponseCode(i).setDebugMessage(BillingHelper.getDebugMessageFromBundle(bundle, BillingClientImpl.TAG)).build());
                        }
                    };
                    Intent intent = new Intent(activity, ProxyBillingActivity.class);
                    intent.putExtra(BillingHelper.RESPONSE_SUBS_MANAGEMENT_INTENT_KEY, (PendingIntent) bundle2.getParcelable(BillingHelper.RESPONSE_SUBS_MANAGEMENT_INTENT_KEY));
                    intent.putExtra("result_receiver", r4);
                    activity.startActivity(intent);
                } catch (CancellationException | TimeoutException unused) {
                    BillingHelper.logWarn(TAG, "Time out while launching Price Change Flow for sku: " + sku + "; try to reconnect");
                    priceChangeConfirmationListener.onPriceChangeConfirmationResult(BillingResults.SERVICE_TIMEOUT);
                } catch (Exception unused2) {
                    BillingHelper.logWarn(TAG, "Exception caught while launching Price Change Flow for sku: " + sku + "; try to reconnect");
                    priceChangeConfirmationListener.onPriceChangeConfirmationResult(BillingResults.SERVICE_DISCONNECTED);
                }
            }
        }
    }

    public BillingResult launchBillingFlow(Activity activity, final BillingFlowParams billingFlowParams) {
        Future future;
        final int i;
        if (!isReady()) {
            return broadcastFailureAndReturnBillingResponse(BillingResults.SERVICE_DISCONNECTED);
        }
        final String skuType = billingFlowParams.getSkuType();
        final String sku = billingFlowParams.getSku();
        SkuDetails skuDetails = billingFlowParams.getSkuDetails();
        boolean z = true;
        boolean z2 = skuDetails != null && skuDetails.isRewarded();
        if (sku == null) {
            BillingHelper.logWarn(TAG, "Please fix the input params. SKU can't be null.");
            return broadcastFailureAndReturnBillingResponse(BillingResults.NULL_SKU);
        } else if (skuType == null) {
            BillingHelper.logWarn(TAG, "Please fix the input params. SkuType can't be null.");
            return broadcastFailureAndReturnBillingResponse(BillingResults.NULL_SKU_TYPE);
        } else if (!skuType.equals(BillingClient.SkuType.SUBS) || this.mSubscriptionsSupported) {
            if (billingFlowParams.getOldSku() == null) {
                z = false;
            }
            if (z && !this.mSubscriptionUpdateSupported) {
                BillingHelper.logWarn(TAG, "Current client doesn't support subscriptions update.");
                return broadcastFailureAndReturnBillingResponse(BillingResults.SUBSCRIPTIONS_UPDATE_NOT_SUPPORTED);
            } else if (billingFlowParams.hasExtraParams() && !this.mIABv6Supported) {
                BillingHelper.logWarn(TAG, "Current client doesn't support extra params for buy intent.");
                return broadcastFailureAndReturnBillingResponse(BillingResults.EXTRA_PARAMS_NOT_SUPPORTED);
            } else if (!z2 || this.mIABv6Supported) {
                BillingHelper.logVerbose(TAG, "Constructing buy intent for " + sku + ", item type: " + skuType);
                if (this.mIABv6Supported) {
                    final Bundle constructExtraParamsForLaunchBillingFlow = BillingHelper.constructExtraParamsForLaunchBillingFlow(billingFlowParams, this.mIABv9Supported, this.mEnablePendingPurchases, this.mQualifiedVersionNumber);
                    if (!skuDetails.getSkuDetailsToken().isEmpty()) {
                        constructExtraParamsForLaunchBillingFlow.putString(BillingHelper.EXTRA_PARAM_KEY_SKU_DETAILS_TOKEN, skuDetails.getSkuDetailsToken());
                    }
                    if (z2) {
                        constructExtraParamsForLaunchBillingFlow.putString(BillingFlowParams.EXTRA_PARAM_KEY_RSKU, skuDetails.rewardToken());
                        int i2 = this.mChildDirected;
                        if (i2 != 0) {
                            constructExtraParamsForLaunchBillingFlow.putInt(BillingFlowParams.EXTRA_PARAM_CHILD_DIRECTED, i2);
                        }
                        int i3 = this.mUnderAgeOfConsent;
                        if (i3 != 0) {
                            constructExtraParamsForLaunchBillingFlow.putInt(BillingFlowParams.EXTRA_PARAM_UNDER_AGE_OF_CONSENT, i3);
                        }
                    }
                    if (this.mIABv9Supported) {
                        i = 9;
                    } else {
                        i = billingFlowParams.getVrPurchaseFlow() ? 7 : 6;
                    }
                    final String str = sku;
                    future = executeAsync(new Callable<Bundle>() {
                        public Bundle call() throws Exception {
                            return BillingClientImpl.this.mService.getBuyIntentExtraParams(i, BillingClientImpl.this.mApplicationContext.getPackageName(), str, skuType, (String) null, constructExtraParamsForLaunchBillingFlow);
                        }
                    }, 5000, (Runnable) null);
                } else if (z) {
                    future = executeAsync(new Callable<Bundle>() {
                        public Bundle call() throws Exception {
                            return BillingClientImpl.this.mService.getBuyIntentToReplaceSkus(5, BillingClientImpl.this.mApplicationContext.getPackageName(), Arrays.asList(new String[]{billingFlowParams.getOldSku()}), sku, BillingClient.SkuType.SUBS, (String) null);
                        }
                    }, 5000, (Runnable) null);
                } else {
                    future = executeAsync(new Callable<Bundle>() {
                        public Bundle call() throws Exception {
                            return BillingClientImpl.this.mService.getBuyIntent(3, BillingClientImpl.this.mApplicationContext.getPackageName(), sku, skuType, (String) null);
                        }
                    }, 5000, (Runnable) null);
                }
                try {
                    Bundle bundle = (Bundle) future.get(5000, TimeUnit.MILLISECONDS);
                    int responseCodeFromBundle = BillingHelper.getResponseCodeFromBundle(bundle, TAG);
                    String debugMessageFromBundle = BillingHelper.getDebugMessageFromBundle(bundle, TAG);
                    if (responseCodeFromBundle != 0) {
                        BillingHelper.logWarn(TAG, "Unable to buy item, Error response code: " + responseCodeFromBundle);
                        return broadcastFailureAndReturnBillingResponse(BillingResult.newBuilder().setResponseCode(responseCodeFromBundle).setDebugMessage(debugMessageFromBundle).build());
                    }
                    Intent intent = new Intent(activity, ProxyBillingActivity.class);
                    intent.putExtra("result_receiver", this.onPurchaseFinishedReceiver);
                    intent.putExtra(BillingHelper.RESPONSE_BUY_INTENT_KEY, (PendingIntent) bundle.getParcelable(BillingHelper.RESPONSE_BUY_INTENT_KEY));
                    activity.startActivity(intent);
                    return BillingResults.f4136OK;
                } catch (CancellationException | TimeoutException unused) {
                    BillingHelper.logWarn(TAG, "Time out while launching billing flow: ; for sku: " + sku + "; try to reconnect");
                    return broadcastFailureAndReturnBillingResponse(BillingResults.SERVICE_TIMEOUT);
                } catch (Exception unused2) {
                    BillingHelper.logWarn(TAG, "Exception while launching billing flow: ; for sku: " + sku + "; try to reconnect");
                    return broadcastFailureAndReturnBillingResponse(BillingResults.SERVICE_DISCONNECTED);
                }
            } else {
                BillingHelper.logWarn(TAG, "Current client doesn't support extra params for buy intent.");
                return broadcastFailureAndReturnBillingResponse(BillingResults.EXTRA_PARAMS_NOT_SUPPORTED);
            }
        } else {
            BillingHelper.logWarn(TAG, "Current client doesn't support subscriptions.");
            return broadcastFailureAndReturnBillingResponse(BillingResults.SUBSCRIPTIONS_NOT_SUPPORTED);
        }
    }

    private BillingResult broadcastFailureAndReturnBillingResponse(BillingResult billingResult) {
        this.mBroadcastManager.getListener().onPurchasesUpdated(billingResult, (List<Purchase>) null);
        return billingResult;
    }

    private int launchBillingFlowCpp(Activity activity, BillingFlowParams billingFlowParams) {
        return launchBillingFlow(activity, billingFlowParams).getResponseCode();
    }

    public Purchase.PurchasesResult queryPurchases(final String str) {
        if (!isReady()) {
            return new Purchase.PurchasesResult(BillingResults.SERVICE_DISCONNECTED, (List<Purchase>) null);
        }
        if (TextUtils.isEmpty(str)) {
            BillingHelper.logWarn(TAG, "Please provide a valid SKU type.");
            return new Purchase.PurchasesResult(BillingResults.EMPTY_SKU_TYPE, (List<Purchase>) null);
        }
        try {
            return (Purchase.PurchasesResult) executeAsync(new Callable<Purchase.PurchasesResult>() {
                public Purchase.PurchasesResult call() throws Exception {
                    return BillingClientImpl.this.queryPurchasesInternal(str);
                }
            }, 5000, (Runnable) null).get(5000, TimeUnit.MILLISECONDS);
        } catch (CancellationException | TimeoutException unused) {
            return new Purchase.PurchasesResult(BillingResults.SERVICE_TIMEOUT, (List<Purchase>) null);
        } catch (Exception unused2) {
            return new Purchase.PurchasesResult(BillingResults.INTERNAL_ERROR, (List<Purchase>) null);
        }
    }

    private void queryPurchases(final String str, long j) {
        final BillingClientNativeCallback billingClientNativeCallback = new BillingClientNativeCallback(j);
        if (!isReady()) {
            billingClientNativeCallback.onQueryPurchasesResponse(BillingResults.SERVICE_DISCONNECTED, (List<Purchase>) null);
        }
        if (executeAsync(new Callable<Void>() {
            public Void call() {
                final Purchase.PurchasesResult access$400 = BillingClientImpl.this.queryPurchasesInternal(str);
                BillingClientImpl.this.postToUiThread(new Runnable() {
                    public void run() {
                        billingClientNativeCallback.onQueryPurchasesResponse(access$400.getBillingResult(), access$400.getPurchasesList());
                    }
                });
                return null;
            }
        }, 30000, new Runnable() {
            public void run() {
                billingClientNativeCallback.onQueryPurchasesResponse(BillingResults.SERVICE_TIMEOUT, (List<Purchase>) null);
            }
        }) == null) {
            billingClientNativeCallback.onQueryPurchasesResponse(getBillingResultForNullFutureResult(), (List<Purchase>) null);
        }
    }

    public void querySkuDetailsAsync(SkuDetailsParams skuDetailsParams, final SkuDetailsResponseListener skuDetailsResponseListener) {
        if (!isReady()) {
            skuDetailsResponseListener.onSkuDetailsResponse(BillingResults.SERVICE_DISCONNECTED, (List<SkuDetails>) null);
            return;
        }
        final String skuType = skuDetailsParams.getSkuType();
        final List<String> skusList = skuDetailsParams.getSkusList();
        if (TextUtils.isEmpty(skuType)) {
            BillingHelper.logWarn(TAG, "Please fix the input params. SKU type can't be empty.");
            skuDetailsResponseListener.onSkuDetailsResponse(BillingResults.EMPTY_SKU_TYPE, (List<SkuDetails>) null);
        } else if (skusList == null) {
            BillingHelper.logWarn(TAG, "Please fix the input params. The list of SKUs can't be empty.");
            skuDetailsResponseListener.onSkuDetailsResponse(BillingResults.EMPTY_SKU_LIST, (List<SkuDetails>) null);
        } else if (executeAsync(new Callable<Void>() {
            public Void call() {
                final SkuDetails.SkuDetailsResult querySkuDetailsInternal = BillingClientImpl.this.querySkuDetailsInternal(skuType, skusList);
                BillingClientImpl.this.postToUiThread(new Runnable() {
                    public void run() {
                        skuDetailsResponseListener.onSkuDetailsResponse(BillingResult.newBuilder().setResponseCode(querySkuDetailsInternal.getResponseCode()).setDebugMessage(querySkuDetailsInternal.getDebugMessage()).build(), querySkuDetailsInternal.getSkuDetailsList());
                    }
                });
                return null;
            }
        }, 30000, new Runnable() {
            public void run() {
                skuDetailsResponseListener.onSkuDetailsResponse(BillingResults.SERVICE_TIMEOUT, (List<SkuDetails>) null);
            }
        }) == null) {
            skuDetailsResponseListener.onSkuDetailsResponse(getBillingResultForNullFutureResult(), (List<SkuDetails>) null);
        }
    }

    private void querySkuDetailsAsync(String str, String[] strArr, long j) {
        querySkuDetailsAsync(SkuDetailsParams.newBuilder().setType(str).setSkusList(Arrays.asList(strArr)).build(), new BillingClientNativeCallback(j));
    }

    public void consumeAsync(final ConsumeParams consumeParams, final ConsumeResponseListener consumeResponseListener) {
        if (!isReady()) {
            consumeResponseListener.onConsumeResponse(BillingResults.SERVICE_DISCONNECTED, (String) null);
        } else if (executeAsync(new Callable<Void>() {
            public Void call() {
                BillingClientImpl.this.consumeInternal(consumeParams, consumeResponseListener);
                return null;
            }
        }, 30000, new Runnable() {
            public void run() {
                consumeResponseListener.onConsumeResponse(BillingResults.SERVICE_TIMEOUT, (String) null);
            }
        }) == null) {
            consumeResponseListener.onConsumeResponse(getBillingResultForNullFutureResult(), (String) null);
        }
    }

    private void consumeAsync(ConsumeParams consumeParams, long j) {
        consumeAsync(consumeParams, (ConsumeResponseListener) new BillingClientNativeCallback(j));
    }

    public void queryPurchaseHistoryAsync(final String str, final PurchaseHistoryResponseListener purchaseHistoryResponseListener) {
        if (!isReady()) {
            purchaseHistoryResponseListener.onPurchaseHistoryResponse(BillingResults.SERVICE_DISCONNECTED, (List<PurchaseHistoryRecord>) null);
        } else if (executeAsync(new Callable<Void>() {
            public Void call() {
                final PurchaseHistoryResult access$700 = BillingClientImpl.this.queryPurchaseHistoryInternal(str);
                BillingClientImpl.this.postToUiThread(new Runnable() {
                    public void run() {
                        purchaseHistoryResponseListener.onPurchaseHistoryResponse(access$700.getBillingResult(), access$700.getPurchaseHistoryRecordList());
                    }
                });
                return null;
            }
        }, 30000, new Runnable() {
            public void run() {
                purchaseHistoryResponseListener.onPurchaseHistoryResponse(BillingResults.SERVICE_TIMEOUT, (List<PurchaseHistoryRecord>) null);
            }
        }) == null) {
            purchaseHistoryResponseListener.onPurchaseHistoryResponse(getBillingResultForNullFutureResult(), (List<PurchaseHistoryRecord>) null);
        }
    }

    /* access modifiers changed from: private */
    public PurchaseHistoryResult queryPurchaseHistoryInternal(String str) {
        BillingHelper.logVerbose(TAG, "Querying purchase history, item type: " + str);
        ArrayList arrayList = new ArrayList();
        Bundle constructExtraParamsForQueryPurchases = BillingHelper.constructExtraParamsForQueryPurchases(this.mIABv9Supported, this.mEnablePendingPurchases, this.mQualifiedVersionNumber);
        String str2 = null;
        do {
            try {
                if (!this.mIABv6Supported) {
                    BillingHelper.logWarn(TAG, "getPurchaseHistory is not supported on current device");
                    return new PurchaseHistoryResult(BillingResults.GET_PURCHASE_HISTORY_NOT_SUPPORTED, (List<PurchaseHistoryRecord>) null);
                }
                Bundle purchaseHistory = this.mService.getPurchaseHistory(6, this.mApplicationContext.getPackageName(), str, str2, constructExtraParamsForQueryPurchases);
                BillingResult checkPurchasesBundleValidity = PurchaseApiResponseChecker.checkPurchasesBundleValidity(purchaseHistory, TAG, "getPurchaseHistory()");
                if (checkPurchasesBundleValidity != BillingResults.f4136OK) {
                    return new PurchaseHistoryResult(checkPurchasesBundleValidity, (List<PurchaseHistoryRecord>) null);
                }
                ArrayList<String> stringArrayList = purchaseHistory.getStringArrayList(BillingHelper.RESPONSE_INAPP_ITEM_LIST);
                ArrayList<String> stringArrayList2 = purchaseHistory.getStringArrayList(BillingHelper.RESPONSE_INAPP_PURCHASE_DATA_LIST);
                ArrayList<String> stringArrayList3 = purchaseHistory.getStringArrayList(BillingHelper.RESPONSE_INAPP_SIGNATURE_LIST);
                int i = 0;
                while (i < stringArrayList2.size()) {
                    String str3 = stringArrayList2.get(i);
                    String str4 = stringArrayList3.get(i);
                    BillingHelper.logVerbose(TAG, "Purchase record found for sku : " + stringArrayList.get(i));
                    try {
                        PurchaseHistoryRecord purchaseHistoryRecord = new PurchaseHistoryRecord(str3, str4);
                        if (TextUtils.isEmpty(purchaseHistoryRecord.getPurchaseToken())) {
                            BillingHelper.logWarn(TAG, "BUG: empty/null token!");
                        }
                        arrayList.add(purchaseHistoryRecord);
                        i++;
                    } catch (JSONException e) {
                        BillingHelper.logWarn(TAG, "Got an exception trying to decode the purchase: " + e);
                        return new PurchaseHistoryResult(BillingResults.INTERNAL_ERROR, (List<PurchaseHistoryRecord>) null);
                    }
                }
                str2 = purchaseHistory.getString(BillingHelper.INAPP_CONTINUATION_TOKEN);
                BillingHelper.logVerbose(TAG, "Continuation token: " + str2);
            } catch (RemoteException e2) {
                BillingHelper.logWarn(TAG, "Got exception trying to get purchase history: " + e2 + "; try to reconnect");
                return new PurchaseHistoryResult(BillingResults.SERVICE_DISCONNECTED, (List<PurchaseHistoryRecord>) null);
            }
        } while (!TextUtils.isEmpty(str2));
        return new PurchaseHistoryResult(BillingResults.f4136OK, arrayList);
    }

    private void queryPurchaseHistoryAsync(String str, long j) {
        queryPurchaseHistoryAsync(str, (PurchaseHistoryResponseListener) new BillingClientNativeCallback(j));
    }

    public void loadRewardedSku(final RewardLoadParams rewardLoadParams, final RewardResponseListener rewardResponseListener) {
        if (!this.mIABv6Supported) {
            rewardResponseListener.onRewardResponse(BillingResults.ITEM_UNAVAILABLE);
        } else if (executeAsync(new Callable<Void>() {
            public Void call() {
                try {
                    Bundle buyIntentExtraParams = BillingClientImpl.this.mService.getBuyIntentExtraParams(6, BillingClientImpl.this.mApplicationContext.getPackageName(), rewardLoadParams.getSkuDetails().getSku(), rewardLoadParams.getSkuDetails().getType(), (String) null, BillingHelper.constructExtraParamsForLoadRewardedSku(rewardLoadParams.getSkuDetails().rewardToken(), BillingClientImpl.this.mChildDirected, BillingClientImpl.this.mUnderAgeOfConsent, BillingClientImpl.this.mQualifiedVersionNumber));
                    final BillingResult build = BillingResult.newBuilder().setResponseCode(BillingHelper.getResponseCodeFromBundle(buyIntentExtraParams, BillingClientImpl.TAG)).setDebugMessage(BillingHelper.getDebugMessageFromBundle(buyIntentExtraParams, BillingClientImpl.TAG)).build();
                    BillingClientImpl.this.postToUiThread(new Runnable() {
                        public void run() {
                            rewardResponseListener.onRewardResponse(build);
                        }
                    });
                    return null;
                } catch (Exception unused) {
                    BillingClientImpl.this.postToUiThread(new Runnable() {
                        public void run() {
                            rewardResponseListener.onRewardResponse(BillingResults.INTERNAL_ERROR);
                        }
                    });
                    return null;
                }
            }
        }, 30000, new Runnable() {
            public void run() {
                rewardResponseListener.onRewardResponse(BillingResults.SERVICE_TIMEOUT);
            }
        }) == null) {
            rewardResponseListener.onRewardResponse(getBillingResultForNullFutureResult());
        }
    }

    private void loadRewardedSku(RewardLoadParams rewardLoadParams, long j) {
        loadRewardedSku(rewardLoadParams, (RewardResponseListener) new BillingClientNativeCallback(j));
    }

    public void acknowledgePurchase(final AcknowledgePurchaseParams acknowledgePurchaseParams, final AcknowledgePurchaseResponseListener acknowledgePurchaseResponseListener) {
        if (!isReady()) {
            acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(BillingResults.SERVICE_DISCONNECTED);
        } else if (TextUtils.isEmpty(acknowledgePurchaseParams.getPurchaseToken())) {
            BillingHelper.logWarn(TAG, "Please provide a valid purchase token.");
            acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(BillingResults.INVALID_PURCHASE_TOKEN);
        } else if (!this.mIABv9Supported) {
            acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(BillingResults.API_VERSION_NOT_V9);
        } else if (executeAsync(new Callable<Void>() {
            public Void call() {
                try {
                    Bundle acknowledgePurchaseExtraParams = BillingClientImpl.this.mService.acknowledgePurchaseExtraParams(9, BillingClientImpl.this.mApplicationContext.getPackageName(), acknowledgePurchaseParams.getPurchaseToken(), BillingHelper.constructExtraParamsForAcknowledgePurchase(acknowledgePurchaseParams, BillingClientImpl.this.mQualifiedVersionNumber));
                    final int responseCodeFromBundle = BillingHelper.getResponseCodeFromBundle(acknowledgePurchaseExtraParams, BillingClientImpl.TAG);
                    final String debugMessageFromBundle = BillingHelper.getDebugMessageFromBundle(acknowledgePurchaseExtraParams, BillingClientImpl.TAG);
                    BillingClientImpl.this.postToUiThread(new Runnable() {
                        public void run() {
                            acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(BillingResult.newBuilder().setResponseCode(responseCodeFromBundle).setDebugMessage(debugMessageFromBundle).build());
                        }
                    });
                    return null;
                } catch (Exception e) {
                    BillingClientImpl.this.postToUiThread(new Runnable() {
                        public void run() {
                            BillingHelper.logWarn(BillingClientImpl.TAG, "Error acknowledge purchase; ex: " + e);
                            acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(BillingResults.SERVICE_DISCONNECTED);
                        }
                    });
                    return null;
                }
            }
        }, 30000, new Runnable() {
            public void run() {
                acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(BillingResults.SERVICE_TIMEOUT);
            }
        }) == null) {
            acknowledgePurchaseResponseListener.onAcknowledgePurchaseResponse(getBillingResultForNullFutureResult());
        }
    }

    private void acknowledgePurchase(AcknowledgePurchaseParams acknowledgePurchaseParams, long j) {
        acknowledgePurchase(acknowledgePurchaseParams, (AcknowledgePurchaseResponseListener) new BillingClientNativeCallback(j));
    }

    /* access modifiers changed from: private */
    public <T> Future<T> executeAsync(Callable<T> callable, long j, final Runnable runnable) {
        double d = (double) j;
        Double.isNaN(d);
        long j2 = (long) (d * 0.95d);
        if (this.mExecutorService == null) {
            this.mExecutorService = Executors.newFixedThreadPool(BillingHelper.NUMBER_OF_CORES);
        }
        try {
            final Future<T> submit = this.mExecutorService.submit(callable);
            this.mUiThreadHandler.postDelayed(new Runnable() {
                public void run() {
                    if (!submit.isDone() && !submit.isCancelled()) {
                        submit.cancel(true);
                        BillingHelper.logWarn(BillingClientImpl.TAG, "Async task is taking too long, cancel it!");
                        Runnable runnable = runnable;
                        if (runnable != null) {
                            runnable.run();
                        }
                    }
                }
            }, j2);
            return submit;
        } catch (Exception e) {
            BillingHelper.logWarn(TAG, "Async task throws exception " + e);
            return null;
        }
    }

    private BillingResult isBillingSupportedOnVr(final String str) {
        try {
            return ((Integer) executeAsync(new Callable<Integer>() {
                public Integer call() throws Exception {
                    return Integer.valueOf(BillingClientImpl.this.mService.isBillingSupportedExtraParams(7, BillingClientImpl.this.mApplicationContext.getPackageName(), str, BillingClientImpl.this.generateVrBundle()));
                }
            }, 5000, (Runnable) null).get(5000, TimeUnit.MILLISECONDS)).intValue() == 0 ? BillingResults.f4136OK : BillingResults.FEATURE_NOT_SUPPORTED;
        } catch (Exception unused) {
            BillingHelper.logWarn(TAG, "Exception while checking if billing is supported; try to reconnect");
            return BillingResults.SERVICE_DISCONNECTED;
        }
    }

    /* access modifiers changed from: private */
    public Bundle generateVrBundle() {
        Bundle bundle = new Bundle();
        bundle.putBoolean(BillingFlowParams.EXTRA_PARAM_KEY_VR, true);
        return bundle;
    }

    /* access modifiers changed from: package-private */
    public SkuDetails.SkuDetailsResult querySkuDetailsInternal(String str, List<String> list) {
        Bundle bundle;
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        int i = 0;
        while (i < size) {
            int i2 = i + 20;
            ArrayList arrayList2 = new ArrayList(list.subList(i, i2 > size ? size : i2));
            Bundle bundle2 = new Bundle();
            bundle2.putStringArrayList(GET_SKU_DETAILS_ITEM_LIST, arrayList2);
            bundle2.putString(BillingHelper.LIBRARY_VERSION_KEY, this.mQualifiedVersionNumber);
            try {
                if (this.mIABv10Supported) {
                    bundle = this.mService.getSkuDetailsExtraParams(10, this.mApplicationContext.getPackageName(), str, bundle2, BillingHelper.constructExtraParamsForGetSkuDetails(this.mIABv9Supported, this.mEnablePendingPurchases, this.mQualifiedVersionNumber));
                    String str2 = str;
                } else {
                    bundle = this.mService.getSkuDetails(3, this.mApplicationContext.getPackageName(), str, bundle2);
                }
                if (bundle == null) {
                    BillingHelper.logWarn(TAG, "querySkuDetailsAsync got null sku details list");
                    return new SkuDetails.SkuDetailsResult(4, "Null sku details list", (List<SkuDetails>) null);
                } else if (!bundle.containsKey(BillingHelper.RESPONSE_GET_SKU_DETAILS_LIST)) {
                    int responseCodeFromBundle = BillingHelper.getResponseCodeFromBundle(bundle, TAG);
                    String debugMessageFromBundle = BillingHelper.getDebugMessageFromBundle(bundle, TAG);
                    if (responseCodeFromBundle != 0) {
                        BillingHelper.logWarn(TAG, "getSkuDetails() failed. Response code: " + responseCodeFromBundle);
                        return new SkuDetails.SkuDetailsResult(responseCodeFromBundle, debugMessageFromBundle, arrayList);
                    }
                    BillingHelper.logWarn(TAG, "getSkuDetails() returned a bundle with neither an error nor a detail list.");
                    return new SkuDetails.SkuDetailsResult(6, debugMessageFromBundle, arrayList);
                } else {
                    ArrayList<String> stringArrayList = bundle.getStringArrayList(BillingHelper.RESPONSE_GET_SKU_DETAILS_LIST);
                    if (stringArrayList == null) {
                        BillingHelper.logWarn(TAG, "querySkuDetailsAsync got null response list");
                        return new SkuDetails.SkuDetailsResult(4, "querySkuDetailsAsync got null response list", (List<SkuDetails>) null);
                    }
                    int i3 = 0;
                    while (i3 < stringArrayList.size()) {
                        try {
                            SkuDetails skuDetails = new SkuDetails(stringArrayList.get(i3));
                            BillingHelper.logVerbose(TAG, "Got sku details: " + skuDetails);
                            arrayList.add(skuDetails);
                            i3++;
                        } catch (JSONException unused) {
                            BillingHelper.logWarn(TAG, "Got a JSON exception trying to decode SkuDetails.");
                            return new SkuDetails.SkuDetailsResult(6, "Error trying to decode SkuDetails.", (List<SkuDetails>) null);
                        }
                    }
                    i = i2;
                }
            } catch (Exception e) {
                BillingHelper.logWarn(TAG, "querySkuDetailsAsync got a remote exception (try to reconnect)." + e);
                return new SkuDetails.SkuDetailsResult(-1, "Service connection is disconnected.", (List<SkuDetails>) null);
            }
        }
        return new SkuDetails.SkuDetailsResult(0, "", arrayList);
    }

    /* access modifiers changed from: private */
    public Purchase.PurchasesResult queryPurchasesInternal(String str) {
        Bundle bundle;
        BillingHelper.logVerbose(TAG, "Querying owned items, item type: " + str);
        ArrayList arrayList = new ArrayList();
        Bundle constructExtraParamsForQueryPurchases = BillingHelper.constructExtraParamsForQueryPurchases(this.mIABv9Supported, this.mEnablePendingPurchases, this.mQualifiedVersionNumber);
        String str2 = null;
        do {
            try {
                if (this.mIABv9Supported) {
                    bundle = this.mService.getPurchasesExtraParams(9, this.mApplicationContext.getPackageName(), str, str2, constructExtraParamsForQueryPurchases);
                } else {
                    bundle = this.mService.getPurchases(3, this.mApplicationContext.getPackageName(), str, str2);
                }
                BillingResult checkPurchasesBundleValidity = PurchaseApiResponseChecker.checkPurchasesBundleValidity(bundle, TAG, "getPurchase()");
                if (checkPurchasesBundleValidity != BillingResults.f4136OK) {
                    return new Purchase.PurchasesResult(checkPurchasesBundleValidity, (List<Purchase>) null);
                }
                ArrayList<String> stringArrayList = bundle.getStringArrayList(BillingHelper.RESPONSE_INAPP_ITEM_LIST);
                ArrayList<String> stringArrayList2 = bundle.getStringArrayList(BillingHelper.RESPONSE_INAPP_PURCHASE_DATA_LIST);
                ArrayList<String> stringArrayList3 = bundle.getStringArrayList(BillingHelper.RESPONSE_INAPP_SIGNATURE_LIST);
                int i = 0;
                while (i < stringArrayList2.size()) {
                    String str3 = stringArrayList2.get(i);
                    String str4 = stringArrayList3.get(i);
                    BillingHelper.logVerbose(TAG, "Sku is owned: " + stringArrayList.get(i));
                    try {
                        Purchase purchase = new Purchase(str3, str4);
                        if (TextUtils.isEmpty(purchase.getPurchaseToken())) {
                            BillingHelper.logWarn(TAG, "BUG: empty/null token!");
                        }
                        arrayList.add(purchase);
                        i++;
                    } catch (JSONException e) {
                        BillingHelper.logWarn(TAG, "Got an exception trying to decode the purchase: " + e);
                        return new Purchase.PurchasesResult(BillingResults.INTERNAL_ERROR, (List<Purchase>) null);
                    }
                }
                str2 = bundle.getString(BillingHelper.INAPP_CONTINUATION_TOKEN);
                BillingHelper.logVerbose(TAG, "Continuation token: " + str2);
            } catch (Exception e2) {
                BillingHelper.logWarn(TAG, "Got exception trying to get purchases: " + e2 + "; try to reconnect");
                return new Purchase.PurchasesResult(BillingResults.SERVICE_DISCONNECTED, (List<Purchase>) null);
            }
        } while (!TextUtils.isEmpty(str2));
        return new Purchase.PurchasesResult(BillingResults.f4136OK, arrayList);
    }

    /* access modifiers changed from: private */
    public void postToUiThread(Runnable runnable) {
        if (!Thread.interrupted()) {
            this.mUiThreadHandler.post(runnable);
        }
    }

    /* access modifiers changed from: private */
    public void consumeInternal(ConsumeParams consumeParams, final ConsumeResponseListener consumeResponseListener) {
        String str;
        final int i;
        final String purchaseToken = consumeParams.getPurchaseToken();
        try {
            BillingHelper.logVerbose(TAG, "Consuming purchase with token: " + purchaseToken);
            if (this.mIABv9Supported) {
                Bundle consumePurchaseExtraParams = this.mService.consumePurchaseExtraParams(9, this.mApplicationContext.getPackageName(), purchaseToken, BillingHelper.constructExtraParamsForConsume(consumeParams, this.mIABv9Supported, this.mQualifiedVersionNumber));
                int i2 = consumePurchaseExtraParams.getInt(BillingHelper.RESPONSE_CODE);
                str = BillingHelper.getDebugMessageFromBundle(consumePurchaseExtraParams, TAG);
                i = i2;
            } else {
                i = this.mService.consumePurchase(3, this.mApplicationContext.getPackageName(), purchaseToken);
                str = "";
            }
            final BillingResult build = BillingResult.newBuilder().setResponseCode(i).setDebugMessage(str).build();
            if (i == 0) {
                postToUiThread(new Runnable() {
                    public void run() {
                        BillingHelper.logVerbose(BillingClientImpl.TAG, "Successfully consumed purchase.");
                        consumeResponseListener.onConsumeResponse(build, purchaseToken);
                    }
                });
                return;
            }
            final ConsumeResponseListener consumeResponseListener2 = consumeResponseListener;
            final String str2 = purchaseToken;
            postToUiThread(new Runnable() {
                public void run() {
                    BillingHelper.logWarn(BillingClientImpl.TAG, "Error consuming purchase with token. Response code: " + i);
                    consumeResponseListener2.onConsumeResponse(build, str2);
                }
            });
        } catch (Exception e) {
            postToUiThread(new Runnable() {
                public void run() {
                    BillingHelper.logWarn(BillingClientImpl.TAG, "Error consuming purchase; ex: " + e);
                    consumeResponseListener.onConsumeResponse(BillingResults.SERVICE_DISCONNECTED, purchaseToken);
                }
            });
        }
    }

    private final class BillingServiceConnection implements ServiceConnection {
        /* access modifiers changed from: private */
        public boolean disconnected;
        /* access modifiers changed from: private */
        public final Object lock;
        /* access modifiers changed from: private */
        public BillingClientStateListener mListener;

        private BillingServiceConnection(BillingClientStateListener billingClientStateListener) {
            this.lock = new Object();
            this.disconnected = false;
            this.mListener = billingClientStateListener;
        }

        public void onServiceDisconnected(ComponentName componentName) {
            BillingHelper.logWarn(BillingClientImpl.TAG, "Billing service disconnected.");
            IInAppBillingService unused = BillingClientImpl.this.mService = null;
            int unused2 = BillingClientImpl.this.mClientState = 0;
            synchronized (this.lock) {
                if (this.mListener != null) {
                    this.mListener.onBillingServiceDisconnected();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void markDisconnectedAndCleanUp() {
            synchronized (this.lock) {
                this.mListener = null;
                this.disconnected = true;
            }
        }

        /* access modifiers changed from: private */
        public void notifySetupResult(final BillingResult billingResult) {
            BillingClientImpl.this.postToUiThread(new Runnable() {
                public void run() {
                    synchronized (BillingServiceConnection.this.lock) {
                        if (BillingServiceConnection.this.mListener != null) {
                            BillingServiceConnection.this.mListener.onBillingSetupFinished(billingResult);
                        }
                    }
                }
            });
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BillingHelper.logVerbose(BillingClientImpl.TAG, "Billing service connected.");
            IInAppBillingService unused = BillingClientImpl.this.mService = IInAppBillingService.Stub.asInterface(iBinder);
            if (BillingClientImpl.this.executeAsync(new Callable<Void>() {
                /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
                    r3 = com.android.billingclient.api.BillingClientImpl.access$200(r10.this$1.this$0).getPackageName();
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:11:0x0021, code lost:
                    r5 = 10;
                    r6 = 3;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:12:0x0026, code lost:
                    if (r5 < 3) goto L_0x003c;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
                    r6 = com.android.billingclient.api.BillingClientImpl.access$300(r10.this$1.this$0).isBillingSupported(r5, r3, com.android.billingclient.api.BillingClient.SkuType.SUBS);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:15:0x0036, code lost:
                    if (r6 != 0) goto L_0x0039;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:17:0x0039, code lost:
                    r5 = r5 - 1;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:18:0x003c, code lost:
                    r5 = 0;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:19:0x003d, code lost:
                    r7 = r10.this$1.this$0;
                    r9 = true;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:20:0x0043, code lost:
                    if (r5 < 5) goto L_0x0047;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:21:0x0045, code lost:
                    r8 = true;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:22:0x0047, code lost:
                    r8 = false;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:23:0x0048, code lost:
                    com.android.billingclient.api.BillingClientImpl.access$1602(r7, r8);
                    r7 = r10.this$1.this$0;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:24:0x004f, code lost:
                    if (r5 < 3) goto L_0x0053;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:25:0x0051, code lost:
                    r8 = true;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:26:0x0053, code lost:
                    r8 = false;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:27:0x0054, code lost:
                    com.android.billingclient.api.BillingClientImpl.access$1702(r7, r8);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:28:0x0057, code lost:
                    if (r5 >= 3) goto L_0x0060;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:29:0x0059, code lost:
                    com.android.billingclient.util.BillingHelper.logVerbose(com.android.billingclient.api.BillingClientImpl.TAG, "In-app billing API does not support subscription on this device.");
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:30:0x0060, code lost:
                    r5 = 10;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:31:0x0062, code lost:
                    if (r5 < 3) goto L_0x0078;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:32:0x0064, code lost:
                    r6 = com.android.billingclient.api.BillingClientImpl.access$300(r10.this$1.this$0).isBillingSupported(r5, r3, com.android.billingclient.api.BillingClient.SkuType.INAPP);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:33:0x0072, code lost:
                    if (r6 != 0) goto L_0x0075;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:35:0x0075, code lost:
                    r5 = r5 - 1;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:36:0x0078, code lost:
                    r5 = 0;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:37:0x0079, code lost:
                    r3 = r10.this$1.this$0;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:38:0x007d, code lost:
                    if (r5 < 10) goto L_0x0081;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:39:0x007f, code lost:
                    r4 = true;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:40:0x0081, code lost:
                    r4 = false;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:41:0x0082, code lost:
                    com.android.billingclient.api.BillingClientImpl.access$1802(r3, r4);
                    r3 = r10.this$1.this$0;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:42:0x008b, code lost:
                    if (r5 < 9) goto L_0x008f;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:43:0x008d, code lost:
                    r4 = true;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:44:0x008f, code lost:
                    r4 = false;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:45:0x0090, code lost:
                    com.android.billingclient.api.BillingClientImpl.access$1902(r3, r4);
                    r3 = r10.this$1.this$0;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:46:0x0099, code lost:
                    if (r5 < 8) goto L_0x009d;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:47:0x009b, code lost:
                    r4 = true;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:48:0x009d, code lost:
                    r4 = false;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:49:0x009e, code lost:
                    com.android.billingclient.api.BillingClientImpl.access$2002(r3, r4);
                    r3 = r10.this$1.this$0;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:50:0x00a6, code lost:
                    if (r5 < 6) goto L_0x00a9;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:52:0x00a9, code lost:
                    r9 = false;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:53:0x00aa, code lost:
                    com.android.billingclient.api.BillingClientImpl.access$2102(r3, r9);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:54:0x00ad, code lost:
                    if (r5 >= 3) goto L_0x00b6;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:55:0x00af, code lost:
                    com.android.billingclient.util.BillingHelper.logWarn(com.android.billingclient.api.BillingClientImpl.TAG, "In-app billing API version 3 is not supported on this device.");
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:56:0x00b6, code lost:
                    if (r6 != 0) goto L_0x00c1;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:57:0x00b8, code lost:
                    com.android.billingclient.api.BillingClientImpl.access$1202(r10.this$1.this$0, 2);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:58:0x00c1, code lost:
                    com.android.billingclient.api.BillingClientImpl.access$1202(r10.this$1.this$0, 0);
                    com.android.billingclient.api.BillingClientImpl.access$302(r10.this$1.this$0, (com.android.vending.billing.IInAppBillingService) null);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:59:0x00d0, code lost:
                    r0 = r6;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:61:0x00d1, code lost:
                    com.android.billingclient.util.BillingHelper.logWarn(com.android.billingclient.api.BillingClientImpl.TAG, "Exception while checking if billing is supported; try to reconnect");
                    com.android.billingclient.api.BillingClientImpl.access$1202(r10.this$1.this$0, 0);
                    com.android.billingclient.api.BillingClientImpl.access$302(r10.this$1.this$0, (com.android.vending.billing.IInAppBillingService) null);
                    r6 = r0;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:63:0x00e7, code lost:
                    if (r6 != 0) goto L_0x00f1;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:64:0x00e9, code lost:
                    com.android.billingclient.api.BillingClientImpl.BillingServiceConnection.access$2200(r10.this$1, com.android.billingclient.api.BillingResults.f4136OK);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:65:0x00f1, code lost:
                    com.android.billingclient.api.BillingClientImpl.BillingServiceConnection.access$2200(r10.this$1, com.android.billingclient.api.BillingResults.API_VERSION_NOT_V3);
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:66:0x00f8, code lost:
                    return null;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
                    r0 = 3;
                 */
                /* JADX WARNING: Removed duplicated region for block: B:64:0x00e9  */
                /* JADX WARNING: Removed duplicated region for block: B:65:0x00f1  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public java.lang.Void call() {
                    /*
                        r10 = this;
                        com.android.billingclient.api.BillingClientImpl$BillingServiceConnection r0 = com.android.billingclient.api.BillingClientImpl.BillingServiceConnection.this
                        java.lang.Object r0 = r0.lock
                        monitor-enter(r0)
                        com.android.billingclient.api.BillingClientImpl$BillingServiceConnection r1 = com.android.billingclient.api.BillingClientImpl.BillingServiceConnection.this     // Catch:{ all -> 0x00f9 }
                        boolean r1 = r1.disconnected     // Catch:{ all -> 0x00f9 }
                        r2 = 0
                        if (r1 == 0) goto L_0x0012
                        monitor-exit(r0)     // Catch:{ all -> 0x00f9 }
                        return r2
                    L_0x0012:
                        monitor-exit(r0)     // Catch:{ all -> 0x00f9 }
                        r0 = 3
                        r1 = 0
                        com.android.billingclient.api.BillingClientImpl$BillingServiceConnection r3 = com.android.billingclient.api.BillingClientImpl.BillingServiceConnection.this     // Catch:{ Exception -> 0x00d1 }
                        com.android.billingclient.api.BillingClientImpl r3 = com.android.billingclient.api.BillingClientImpl.this     // Catch:{ Exception -> 0x00d1 }
                        android.content.Context r3 = r3.mApplicationContext     // Catch:{ Exception -> 0x00d1 }
                        java.lang.String r3 = r3.getPackageName()     // Catch:{ Exception -> 0x00d1 }
                        r4 = 10
                        r5 = 10
                        r6 = 3
                    L_0x0026:
                        if (r5 < r0) goto L_0x003c
                        com.android.billingclient.api.BillingClientImpl$BillingServiceConnection r7 = com.android.billingclient.api.BillingClientImpl.BillingServiceConnection.this     // Catch:{ Exception -> 0x00d0 }
                        com.android.billingclient.api.BillingClientImpl r7 = com.android.billingclient.api.BillingClientImpl.this     // Catch:{ Exception -> 0x00d0 }
                        com.android.vending.billing.IInAppBillingService r7 = r7.mService     // Catch:{ Exception -> 0x00d0 }
                        java.lang.String r8 = "subs"
                        int r6 = r7.isBillingSupported(r5, r3, r8)     // Catch:{ Exception -> 0x00d0 }
                        if (r6 != 0) goto L_0x0039
                        goto L_0x003d
                    L_0x0039:
                        int r5 = r5 + -1
                        goto L_0x0026
                    L_0x003c:
                        r5 = 0
                    L_0x003d:
                        com.android.billingclient.api.BillingClientImpl$BillingServiceConnection r7 = com.android.billingclient.api.BillingClientImpl.BillingServiceConnection.this     // Catch:{ Exception -> 0x00d0 }
                        com.android.billingclient.api.BillingClientImpl r7 = com.android.billingclient.api.BillingClientImpl.this     // Catch:{ Exception -> 0x00d0 }
                        r8 = 5
                        r9 = 1
                        if (r5 < r8) goto L_0x0047
                        r8 = 1
                        goto L_0x0048
                    L_0x0047:
                        r8 = 0
                    L_0x0048:
                        boolean unused = r7.mSubscriptionUpdateSupported = r8     // Catch:{ Exception -> 0x00d0 }
                        com.android.billingclient.api.BillingClientImpl$BillingServiceConnection r7 = com.android.billingclient.api.BillingClientImpl.BillingServiceConnection.this     // Catch:{ Exception -> 0x00d0 }
                        com.android.billingclient.api.BillingClientImpl r7 = com.android.billingclient.api.BillingClientImpl.this     // Catch:{ Exception -> 0x00d0 }
                        if (r5 < r0) goto L_0x0053
                        r8 = 1
                        goto L_0x0054
                    L_0x0053:
                        r8 = 0
                    L_0x0054:
                        boolean unused = r7.mSubscriptionsSupported = r8     // Catch:{ Exception -> 0x00d0 }
                        if (r5 >= r0) goto L_0x0060
                        java.lang.String r5 = "BillingClient"
                        java.lang.String r7 = "In-app billing API does not support subscription on this device."
                        com.android.billingclient.util.BillingHelper.logVerbose(r5, r7)     // Catch:{ Exception -> 0x00d0 }
                    L_0x0060:
                        r5 = 10
                    L_0x0062:
                        if (r5 < r0) goto L_0x0078
                        com.android.billingclient.api.BillingClientImpl$BillingServiceConnection r7 = com.android.billingclient.api.BillingClientImpl.BillingServiceConnection.this     // Catch:{ Exception -> 0x00d0 }
                        com.android.billingclient.api.BillingClientImpl r7 = com.android.billingclient.api.BillingClientImpl.this     // Catch:{ Exception -> 0x00d0 }
                        com.android.vending.billing.IInAppBillingService r7 = r7.mService     // Catch:{ Exception -> 0x00d0 }
                        java.lang.String r8 = "inapp"
                        int r6 = r7.isBillingSupported(r5, r3, r8)     // Catch:{ Exception -> 0x00d0 }
                        if (r6 != 0) goto L_0x0075
                        goto L_0x0079
                    L_0x0075:
                        int r5 = r5 + -1
                        goto L_0x0062
                    L_0x0078:
                        r5 = 0
                    L_0x0079:
                        com.android.billingclient.api.BillingClientImpl$BillingServiceConnection r3 = com.android.billingclient.api.BillingClientImpl.BillingServiceConnection.this     // Catch:{ Exception -> 0x00d0 }
                        com.android.billingclient.api.BillingClientImpl r3 = com.android.billingclient.api.BillingClientImpl.this     // Catch:{ Exception -> 0x00d0 }
                        if (r5 < r4) goto L_0x0081
                        r4 = 1
                        goto L_0x0082
                    L_0x0081:
                        r4 = 0
                    L_0x0082:
                        boolean unused = r3.mIABv10Supported = r4     // Catch:{ Exception -> 0x00d0 }
                        com.android.billingclient.api.BillingClientImpl$BillingServiceConnection r3 = com.android.billingclient.api.BillingClientImpl.BillingServiceConnection.this     // Catch:{ Exception -> 0x00d0 }
                        com.android.billingclient.api.BillingClientImpl r3 = com.android.billingclient.api.BillingClientImpl.this     // Catch:{ Exception -> 0x00d0 }
                        r4 = 9
                        if (r5 < r4) goto L_0x008f
                        r4 = 1
                        goto L_0x0090
                    L_0x008f:
                        r4 = 0
                    L_0x0090:
                        boolean unused = r3.mIABv9Supported = r4     // Catch:{ Exception -> 0x00d0 }
                        com.android.billingclient.api.BillingClientImpl$BillingServiceConnection r3 = com.android.billingclient.api.BillingClientImpl.BillingServiceConnection.this     // Catch:{ Exception -> 0x00d0 }
                        com.android.billingclient.api.BillingClientImpl r3 = com.android.billingclient.api.BillingClientImpl.this     // Catch:{ Exception -> 0x00d0 }
                        r4 = 8
                        if (r5 < r4) goto L_0x009d
                        r4 = 1
                        goto L_0x009e
                    L_0x009d:
                        r4 = 0
                    L_0x009e:
                        boolean unused = r3.mIABv8Supported = r4     // Catch:{ Exception -> 0x00d0 }
                        com.android.billingclient.api.BillingClientImpl$BillingServiceConnection r3 = com.android.billingclient.api.BillingClientImpl.BillingServiceConnection.this     // Catch:{ Exception -> 0x00d0 }
                        com.android.billingclient.api.BillingClientImpl r3 = com.android.billingclient.api.BillingClientImpl.this     // Catch:{ Exception -> 0x00d0 }
                        r4 = 6
                        if (r5 < r4) goto L_0x00a9
                        goto L_0x00aa
                    L_0x00a9:
                        r9 = 0
                    L_0x00aa:
                        boolean unused = r3.mIABv6Supported = r9     // Catch:{ Exception -> 0x00d0 }
                        if (r5 >= r0) goto L_0x00b6
                        java.lang.String r0 = "BillingClient"
                        java.lang.String r3 = "In-app billing API version 3 is not supported on this device."
                        com.android.billingclient.util.BillingHelper.logWarn(r0, r3)     // Catch:{ Exception -> 0x00d0 }
                    L_0x00b6:
                        if (r6 != 0) goto L_0x00c1
                        com.android.billingclient.api.BillingClientImpl$BillingServiceConnection r0 = com.android.billingclient.api.BillingClientImpl.BillingServiceConnection.this     // Catch:{ Exception -> 0x00d0 }
                        com.android.billingclient.api.BillingClientImpl r0 = com.android.billingclient.api.BillingClientImpl.this     // Catch:{ Exception -> 0x00d0 }
                        r3 = 2
                        int unused = r0.mClientState = r3     // Catch:{ Exception -> 0x00d0 }
                        goto L_0x00e7
                    L_0x00c1:
                        com.android.billingclient.api.BillingClientImpl$BillingServiceConnection r0 = com.android.billingclient.api.BillingClientImpl.BillingServiceConnection.this     // Catch:{ Exception -> 0x00d0 }
                        com.android.billingclient.api.BillingClientImpl r0 = com.android.billingclient.api.BillingClientImpl.this     // Catch:{ Exception -> 0x00d0 }
                        int unused = r0.mClientState = r1     // Catch:{ Exception -> 0x00d0 }
                        com.android.billingclient.api.BillingClientImpl$BillingServiceConnection r0 = com.android.billingclient.api.BillingClientImpl.BillingServiceConnection.this     // Catch:{ Exception -> 0x00d0 }
                        com.android.billingclient.api.BillingClientImpl r0 = com.android.billingclient.api.BillingClientImpl.this     // Catch:{ Exception -> 0x00d0 }
                        com.android.vending.billing.IInAppBillingService unused = r0.mService = r2     // Catch:{ Exception -> 0x00d0 }
                        goto L_0x00e7
                    L_0x00d0:
                        r0 = r6
                    L_0x00d1:
                        java.lang.String r3 = "BillingClient"
                        java.lang.String r4 = "Exception while checking if billing is supported; try to reconnect"
                        com.android.billingclient.util.BillingHelper.logWarn(r3, r4)
                        com.android.billingclient.api.BillingClientImpl$BillingServiceConnection r3 = com.android.billingclient.api.BillingClientImpl.BillingServiceConnection.this
                        com.android.billingclient.api.BillingClientImpl r3 = com.android.billingclient.api.BillingClientImpl.this
                        int unused = r3.mClientState = r1
                        com.android.billingclient.api.BillingClientImpl$BillingServiceConnection r1 = com.android.billingclient.api.BillingClientImpl.BillingServiceConnection.this
                        com.android.billingclient.api.BillingClientImpl r1 = com.android.billingclient.api.BillingClientImpl.this
                        com.android.vending.billing.IInAppBillingService unused = r1.mService = r2
                        r6 = r0
                    L_0x00e7:
                        if (r6 != 0) goto L_0x00f1
                        com.android.billingclient.api.BillingClientImpl$BillingServiceConnection r0 = com.android.billingclient.api.BillingClientImpl.BillingServiceConnection.this
                        com.android.billingclient.api.BillingResult r1 = com.android.billingclient.api.BillingResults.f4136OK
                        r0.notifySetupResult(r1)
                        goto L_0x00f8
                    L_0x00f1:
                        com.android.billingclient.api.BillingClientImpl$BillingServiceConnection r0 = com.android.billingclient.api.BillingClientImpl.BillingServiceConnection.this
                        com.android.billingclient.api.BillingResult r1 = com.android.billingclient.api.BillingResults.API_VERSION_NOT_V3
                        r0.notifySetupResult(r1)
                    L_0x00f8:
                        return r2
                    L_0x00f9:
                        r1 = move-exception
                        monitor-exit(r0)     // Catch:{ all -> 0x00f9 }
                        goto L_0x00fd
                    L_0x00fc:
                        throw r1
                    L_0x00fd:
                        goto L_0x00fc
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.android.billingclient.api.BillingClientImpl.BillingServiceConnection.C46642.call():java.lang.Void");
                }
            }, 30000, new Runnable() {
                public void run() {
                    int unused = BillingClientImpl.this.mClientState = 0;
                    IInAppBillingService unused2 = BillingClientImpl.this.mService = null;
                    BillingServiceConnection.this.notifySetupResult(BillingResults.SERVICE_TIMEOUT);
                }
            }) == null) {
                notifySetupResult(BillingClientImpl.this.getBillingResultForNullFutureResult());
            }
        }
    }

    /* access modifiers changed from: private */
    public BillingResult getBillingResultForNullFutureResult() {
        int i = this.mClientState;
        return (i == 0 || i == 3) ? BillingResults.SERVICE_DISCONNECTED : BillingResults.INTERNAL_ERROR;
    }

    private static class PurchaseHistoryResult {
        private BillingResult mBillingResult;
        private List<PurchaseHistoryRecord> mPurchaseHistoryRecordList;

        PurchaseHistoryResult(BillingResult billingResult, List<PurchaseHistoryRecord> list) {
            this.mPurchaseHistoryRecordList = list;
            this.mBillingResult = billingResult;
        }

        /* access modifiers changed from: package-private */
        public BillingResult getBillingResult() {
            return this.mBillingResult;
        }

        /* access modifiers changed from: package-private */
        public List<PurchaseHistoryRecord> getPurchaseHistoryRecordList() {
            return this.mPurchaseHistoryRecordList;
        }
    }
}

package com.appsgeyser.sdk;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackAdsController;
import com.appsgeyser.sdk.analytics.Analytics;
import com.appsgeyser.sdk.configuration.Configuration;
import com.appsgeyser.sdk.configuration.Constants;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.deviceidparser.DeviceIdParameters;
import com.appsgeyser.sdk.deviceidparser.DeviceIdParser;
import com.appsgeyser.sdk.deviceidparser.IDeviceIdParserListener;
import com.appsgeyser.sdk.inapp.InAppPurchaseController;
import com.appsgeyser.sdk.p087ui.AboutDialogActivity;
import com.appsgeyser.sdk.p087ui.AdvertisingTermsDialog;
import com.appsgeyser.sdk.server.implementation.AppsgeyserServerClient;
import com.appsgeyser.sdk.server.network.NetworkAvailableReceiver;
import com.appsgeyser.sdk.server.network.NetworkManager;
import com.appsgeyser.sdk.server.network.OnNetworkStateChangedListener;
import com.google.gson.JsonSyntaxException;
import com.yandex.metrica.YandexMetrica;
import java.util.Locale;

public final class InternalEntryPoint implements IDeviceIdParserListener {
    private static final String CONFIG_PHP_KEY = "config_php_key";
    private static final InternalEntryPoint INSTANCE = new InternalEntryPoint();
    private static final String METRICA_ON_START_EVENT = "on_start_event";
    /* access modifiers changed from: private */
    public AppsgeyserSDK.OnAboutDialogEnableListener aboutDialogEnabledListener;
    private String additionalJsCode = "";
    /* access modifiers changed from: private */
    public AdvertisingTermsDialog advertisingTermsDialog;
    private AfterConsentRequestListener afterConsentRequestCompletedListener;
    private Application application;
    private Configuration configuration = null;
    /* access modifiers changed from: private */
    public boolean customHtmlAbout;
    private boolean doneDeviceParser;
    private boolean enablePull = false;
    /* access modifiers changed from: private */
    public DeviceIdParameters idParameters;
    /* access modifiers changed from: private */
    public AppsgeyserSDK.OnInAppPurchasesEnableListener inAppPurchasesEnableListener;
    private boolean isApplicationVisible = true;
    private boolean isConsentRequestProcessActive;
    private boolean isConsentRequestProcessCompleted;
    private OnNetworkStateChangedListener networkAvailableListener;
    private NetworkAvailableReceiver networkReceiver;
    /* access modifiers changed from: private */
    public boolean saveDialogEnableListener;
    /* access modifiers changed from: private */
    public boolean saveInAppPurchasesEnableListener;
    private float selectedRating;

    public interface AfterConsentRequestListener {
        void onConsentRequestCompleted();
    }

    public static InternalEntryPoint getInstance() {
        return INSTANCE;
    }

    /* access modifiers changed from: package-private */
    public void takeOff(Activity activity, String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str)) {
            if (checkPermissions(activity)) {
                init(activity, str3, str, str2);
                YandexMetrica.activate((Context) activity, Constants.APP_METRICA_ID);
                YandexMetrica.enableActivityAutoTracking(activity.getApplication());
                try {
                    String metricaOnStartEvent = this.configuration.getMetricaOnStartEvent();
                    if (metricaOnStartEvent != null) {
                        YandexMetrica.reportEvent(METRICA_ON_START_EVENT, metricaOnStartEvent);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                return;
            }
        }
        if (NetworkManager.isOnline(activity)) {
            DeviceIdParser.getInstance().rescan(activity, this);
            this.doneDeviceParser = true;
        }
    }

    private void init(Activity activity, String str, String str2, String str3) {
        Configuration instance = Configuration.getInstance(activity);
        this.configuration = instance;
        instance.setTemplateVersion(str);
        this.configuration.loadConfiguration();
        if (!(this.configuration.getApplicationId() != null ? this.configuration.getApplicationId() : "").equals(str2)) {
            this.configuration.clearApplicationSettings();
            this.configuration.setApplicationId(str2);
            this.configuration.setMetricaOnStartEvent(str3, str);
        }
        Analytics.getInstance(activity).activityStarted(activity);
        this.advertisingTermsDialog = new AdvertisingTermsDialog(activity);
        if (this.networkReceiver == null) {
            this.networkReceiver = NetworkAvailableReceiver.createAndRegisterNetworkReceiver(activity);
        }
        OnNetworkStateChangedListener createNetworkAvailableListener = this.networkReceiver.createNetworkAvailableListener(activity);
        this.networkAvailableListener = createNetworkAvailableListener;
        this.networkReceiver.addListener(createNetworkAvailableListener);
    }

    public void retryParsers(Context context) {
        if (!this.doneDeviceParser) {
            DeviceIdParser.getInstance().rescan(context, this);
            this.doneDeviceParser = true;
        }
    }

    public boolean pullEnabled() {
        return this.enablePull;
    }

    public void enablePull() {
        this.enablePull = true;
    }

    private boolean checkPermissions(Activity activity) {
        if (activity.checkCallingOrSelfPermission("android.permission.ACCESS_NETWORK_STATE") == 0 && activity.checkCallingOrSelfPermission("android.permission.INTERNET") == 0) {
            return true;
        }
        ExceptionHandler.handleException(new Exception("Invalid permission. You have to grant ACCESS_NETWORK_STATE and INTERNET permissions to work properly"));
        return false;
    }

    /* access modifiers changed from: package-private */
    public void onPause(Context context) {
        if (context instanceof Activity) {
            ((Activity) context).getLocalClassName();
        }
        NetworkAvailableReceiver networkAvailableReceiver = this.networkReceiver;
        if (networkAvailableReceiver != null) {
            try {
                context.unregisterReceiver(networkAvailableReceiver);
            } catch (IllegalArgumentException unused) {
            }
        }
        FastTrackAdsController.getInstance().onPause();
        INSTANCE.setApplicationVisible(false);
    }

    /* access modifiers changed from: package-private */
    public void onResume(Context context) {
        if (context instanceof Activity) {
            ((Activity) context).getLocalClassName();
        }
        this.configuration = Configuration.getInstance(context);
        if (this.networkReceiver == null) {
            NetworkAvailableReceiver createAndRegisterNetworkReceiver = NetworkAvailableReceiver.createAndRegisterNetworkReceiver(context);
            this.networkReceiver = createAndRegisterNetworkReceiver;
            OnNetworkStateChangedListener createNetworkAvailableListener = createAndRegisterNetworkReceiver.createNetworkAvailableListener(context);
            this.networkAvailableListener = createNetworkAvailableListener;
            this.networkReceiver.addListener(createNetworkAvailableListener);
        } else {
            context.registerReceiver(this.networkReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
        FastTrackAdsController.getInstance().onResume(context);
        INSTANCE.setApplicationVisible(true);
    }

    public void onDeviceIdParametersObtained(Context context, DeviceIdParameters deviceIdParameters) {
        this.idParameters = deviceIdParameters;
        AppsgeyserServerClient.getInstance().getConfigPhp(context, this.idParameters, new AppsgeyserServerClient.ConfigPhpRequestListener() {
            public void receivedConfigPhp(ConfigPhp configPhp) {
                if (configPhp != null) {
                    if (InternalEntryPoint.this.advertisingTermsDialog != null) {
                        InternalEntryPoint.this.advertisingTermsDialog.show(configPhp.isAdvertisingTermsDialog());
                    }
                    if (InternalEntryPoint.this.saveDialogEnableListener) {
                        InternalEntryPoint.this.aboutDialogEnabledListener.onDialogEnableReceived(configPhp.isAboutScreenEnabled());
                    }
                    if (InternalEntryPoint.this.saveInAppPurchasesEnableListener) {
                        InternalEntryPoint.this.inAppPurchasesEnableListener.onInAppPurchasesEnableReceived(configPhp.isInAppPurchasesActive());
                    }
                    Constants.setFullScreenDelay(configPhp.getFullScreenDelay());
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void showAboutDialog(Activity activity) {
        final Intent intent = new Intent(activity, AboutDialogActivity.class);
        intent.setFlags(268435456);
        if (this.idParameters != null) {
            AppsgeyserServerClient.getInstance().getConfigPhp(activity, this.idParameters, new AppsgeyserServerClient.ConfigPhpRequestListener() {
                public void receivedConfigPhp(ConfigPhp configPhp) {
                    intent.putExtra(InternalEntryPoint.CONFIG_PHP_KEY, configPhp);
                    boolean unused = InternalEntryPoint.this.customHtmlAbout = configPhp.isCustomAboutActive();
                }
            });
        } else {
            String prefString = Configuration.getInstance(activity).getSettingsCoder().getPrefString(Constants.PREFS_SERVER_RESPONSE, "");
            if (!TextUtils.isEmpty(prefString)) {
                try {
                    ConfigPhp parseFromJson = ConfigPhp.parseFromJson(prefString);
                    intent.putExtra(CONFIG_PHP_KEY, parseFromJson);
                    this.customHtmlAbout = parseFromJson.isCustomAboutActive();
                } catch (JsonSyntaxException unused) {
                }
            } else {
                intent.putExtra(CONFIG_PHP_KEY, "");
            }
        }
        if (!this.customHtmlAbout) {
            activity.startActivity(intent);
        } else {
            PausedContentInfoActivity.startPausedContentInfoActivity(activity, true);
        }
    }

    /* access modifiers changed from: package-private */
    public void launchDisableAdsBillingFlow(Activity activity) {
        InAppPurchaseController.getInstance().launchDisableAdsBillingFlow(activity);
    }

    /* access modifiers changed from: package-private */
    public void getNewConfigPhp(Context context, final AppsgeyserSDK.OnAboutDialogEnableListener onAboutDialogEnableListener) {
        this.aboutDialogEnabledListener = onAboutDialogEnableListener;
        if (this.idParameters != null) {
            AppsgeyserServerClient.getInstance().getConfigPhp(context, this.idParameters, new AppsgeyserServerClient.ConfigPhpRequestListener() {
                public void receivedConfigPhp(ConfigPhp configPhp) {
                    onAboutDialogEnableListener.onDialogEnableReceived(configPhp.isAboutScreenEnabled());
                    boolean unused = InternalEntryPoint.this.saveDialogEnableListener = false;
                }
            });
            return;
        }
        String prefString = Configuration.getInstance(context).getSettingsCoder().getPrefString(Constants.PREFS_SERVER_RESPONSE, "");
        if (!TextUtils.isEmpty(prefString)) {
            try {
                onAboutDialogEnableListener.onDialogEnableReceived(ConfigPhp.parseFromJson(prefString).isAboutScreenEnabled());
                this.saveDialogEnableListener = false;
            } catch (JsonSyntaxException unused) {
                this.saveDialogEnableListener = true;
            }
        } else {
            this.saveDialogEnableListener = true;
        }
    }

    /* access modifiers changed from: package-private */
    public void getNewConfigPhp(Context context, AppsgeyserSDK.OnInAppPurchasesEnableListener onInAppPurchasesEnableListener) {
        this.inAppPurchasesEnableListener = onInAppPurchasesEnableListener;
        if (this.idParameters != null) {
            AppsgeyserServerClient.getInstance().getConfigPhp(context, this.idParameters, new AppsgeyserServerClient.ConfigPhpRequestListener(onInAppPurchasesEnableListener) {
                public final /* synthetic */ AppsgeyserSDK.OnInAppPurchasesEnableListener f$1;

                {
                    this.f$1 = r2;
                }

                public final void receivedConfigPhp(ConfigPhp configPhp) {
                    InternalEntryPoint.this.lambda$getNewConfigPhp$0$InternalEntryPoint(this.f$1, configPhp);
                }
            });
            return;
        }
        String prefString = Configuration.getInstance(context).getSettingsCoder().getPrefString(Constants.PREFS_SERVER_RESPONSE, "");
        if (!TextUtils.isEmpty(prefString)) {
            try {
                onInAppPurchasesEnableListener.onInAppPurchasesEnableReceived(ConfigPhp.parseFromJson(prefString).isInAppPurchasesActive() && !InAppPurchaseController.getInstance().isDisableAdsPurchaseButtonHidden());
                this.saveInAppPurchasesEnableListener = false;
            } catch (JsonSyntaxException unused) {
                this.saveInAppPurchasesEnableListener = true;
            }
        } else {
            this.saveInAppPurchasesEnableListener = true;
        }
    }

    public /* synthetic */ void lambda$getNewConfigPhp$0$InternalEntryPoint(AppsgeyserSDK.OnInAppPurchasesEnableListener onInAppPurchasesEnableListener, ConfigPhp configPhp) {
        onInAppPurchasesEnableListener.onInAppPurchasesEnableReceived(configPhp.isInAppPurchasesActive() && !InAppPurchaseController.getInstance().isDisableAdsPurchaseButtonHidden());
        this.saveInAppPurchasesEnableListener = false;
    }

    public void setAdditionalJsCode(String str) {
        this.additionalJsCode = str;
    }

    public String getAdditionalJsCode() {
        return this.additionalJsCode;
    }

    public String getUserCountrySIM(Context context) {
        String networkCountryIso;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            String simCountryIso = telephonyManager.getSimCountryIso();
            if (simCountryIso != null && simCountryIso.length() == 2) {
                return simCountryIso.toLowerCase(Locale.US);
            }
            if (telephonyManager.getPhoneType() == 2 || (networkCountryIso = telephonyManager.getNetworkCountryIso()) == null || networkCountryIso.length() != 2) {
                return null;
            }
            return networkCountryIso.toLowerCase(Locale.US);
        } catch (Exception unused) {
            return null;
        }
    }

    public String getUserCountryLocale(Context context) {
        return context.getResources().getConfiguration().locale.getDisplayCountry();
    }

    public void addNetworkListener(OnNetworkStateChangedListener onNetworkStateChangedListener, Context context) {
        NetworkAvailableReceiver networkAvailableReceiver = this.networkReceiver;
        if (networkAvailableReceiver != null) {
            networkAvailableReceiver.addListener(onNetworkStateChangedListener);
            return;
        }
        NetworkAvailableReceiver createAndRegisterNetworkReceiver = NetworkAvailableReceiver.createAndRegisterNetworkReceiver(context);
        this.networkReceiver = createAndRegisterNetworkReceiver;
        createAndRegisterNetworkReceiver.addListener(onNetworkStateChangedListener);
    }

    public void removeNetworkListener(OnNetworkStateChangedListener onNetworkStateChangedListener) {
        NetworkAvailableReceiver networkAvailableReceiver = this.networkReceiver;
        if (networkAvailableReceiver != null) {
            networkAvailableReceiver.removeListener(onNetworkStateChangedListener);
        }
    }

    /* access modifiers changed from: package-private */
    public void checkIsOfferWallEnabled(Context context, final AppsgeyserSDK.OfferWallEnabledListener offerWallEnabledListener) {
        if (this.idParameters != null) {
            AppsgeyserServerClient.getInstance().getConfigPhp(context, this.idParameters, new AppsgeyserServerClient.ConfigPhpRequestListener() {
                public void receivedConfigPhp(ConfigPhp configPhp) {
                    offerWallEnabledListener.isOfferWallEnabled(configPhp.isOfferWallEnabled());
                }
            });
        } else {
            DeviceIdParser.getInstance().rescan(context, new IDeviceIdParserListener() {
                public void onDeviceIdParametersObtained(Context context, DeviceIdParameters deviceIdParameters) {
                    DeviceIdParameters unused = InternalEntryPoint.this.idParameters = deviceIdParameters;
                    AppsgeyserServerClient.getInstance().getConfigPhp(context, deviceIdParameters, new AppsgeyserServerClient.ConfigPhpRequestListener() {
                        public void receivedConfigPhp(ConfigPhp configPhp) {
                            offerWallEnabledListener.isOfferWallEnabled(configPhp.isOfferWallEnabled());
                        }
                    });
                }
            });
        }
    }

    public void onDestroy(Context context) {
        FastTrackAdsController.getInstance().onDestroy();
    }

    /* access modifiers changed from: package-private */
    public FastTrackAdsController getFastTrackAdsController() {
        return FastTrackAdsController.getInstance();
    }

    public Application getApplication() {
        return this.application;
    }

    public void setApplication(Application application2) {
        this.application = application2;
    }

    public boolean isApplicationVisible() {
        return this.isApplicationVisible;
    }

    public void setApplicationVisible(boolean z) {
        this.isApplicationVisible = z;
    }

    public boolean isConsentRequestProcessActive() {
        return this.isConsentRequestProcessActive;
    }

    public void setAfterConsentRequestCompletedListener(AfterConsentRequestListener afterConsentRequestListener) {
        if (this.isConsentRequestProcessCompleted) {
            afterConsentRequestListener.onConsentRequestCompleted();
        } else {
            this.afterConsentRequestCompletedListener = afterConsentRequestListener;
        }
    }

    public void setConsentRequestProcessActive(boolean z) {
        if (!this.isConsentRequestProcessCompleted) {
            this.isConsentRequestProcessActive = z;
            if (!z) {
                this.isConsentRequestProcessCompleted = true;
                AfterConsentRequestListener afterConsentRequestListener = this.afterConsentRequestCompletedListener;
                if (afterConsentRequestListener != null) {
                    afterConsentRequestListener.onConsentRequestCompleted();
                    this.afterConsentRequestCompletedListener = null;
                    return;
                }
                return;
            }
            return;
        }
        this.isConsentRequestProcessActive = false;
    }

    public float getSelectedRating() {
        return this.selectedRating;
    }

    public void setSelectedRating(float f) {
        this.selectedRating = f;
    }
}

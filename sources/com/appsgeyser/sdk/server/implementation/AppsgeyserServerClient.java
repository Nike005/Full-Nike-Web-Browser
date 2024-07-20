package com.appsgeyser.sdk.server.implementation;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.appsgeyser.sdk.C5051R;
import com.appsgeyser.sdk.InternalEntryPoint;
import com.appsgeyser.sdk.PausedContentInfoActivity;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackAdsController;
import com.appsgeyser.sdk.configuration.Configuration;
import com.appsgeyser.sdk.configuration.Constants;
import com.appsgeyser.sdk.configuration.PreferencesCoder;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.datasdk.DataSdkController;
import com.appsgeyser.sdk.deviceidparser.DeviceIdParameters;
import com.appsgeyser.sdk.deviceidparser.DeviceIdParser;
import com.appsgeyser.sdk.inapp.InAppPurchaseController;
import com.appsgeyser.sdk.push.OneSignalCreator;
import com.appsgeyser.sdk.rateme.RatingDialog;
import com.appsgeyser.sdk.server.StatController;
import com.appsgeyser.sdk.server.network.NetworkManager;
import com.appsgeyser.sdk.utils.DeviceInfoGetter;
import com.appsgeyser.sdk.utils.ReminderAlarmManager;
import com.appsgeyser.sdk.utils.VersionManager;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppsgeyserServerClient {
    private static final String INSTALL_ACTION_URL_TAG = "install";
    private static final String UPDATE_ACTION_URL_TAG = "update";
    private static final String USAGE_ACTION_URL_TAG = "usage";
    /* access modifiers changed from: private */
    public ConfigPhp configPhpModel;
    List<ConfigPhpRequestListener> listenerList;
    private final NetworkManager networkManager;
    /* access modifiers changed from: private */
    public boolean ratingDialogActivated;

    public interface ConfigPhpRequestListener {
        void receivedConfigPhp(ConfigPhp configPhp);
    }

    private static class SingletonHolder {
        static final AppsgeyserServerClient HOLDER_INSTANCE = new AppsgeyserServerClient();

        private SingletonHolder() {
        }
    }

    private AppsgeyserServerClient() {
        this.listenerList = new ArrayList();
        this.networkManager = NetworkManager.getInstance();
    }

    public static AppsgeyserServerClient getInstance() {
        return SingletonHolder.HOLDER_INSTANCE;
    }

    private String getInstallerMarket(Context context) {
        try {
            String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
            if (installerPackageName == null) {
                return "";
            }
            return installerPackageName;
        } catch (Exception e) {
            if (TextUtils.isEmpty(e.getMessage())) {
                return "ERROR";
            }
            return "ERROR" + ":" + e.getMessage();
        }
    }

    public void sendAfterInstallInfo(Context context) {
        sendRequestWithAllArgs(INSTALL_ACTION_URL_TAG, NetworkManager.RequestType.AFTERINSTALL.ordinal(), context);
    }

    public void sendUsageInfo(Context context) {
        sendRequestWithAllArgs(USAGE_ACTION_URL_TAG, NetworkManager.RequestType.USAGE.ordinal(), context);
    }

    public void sendAboutDialogImpression(Context context) {
        sendRequestSdkStatisticsWithArgs(StatController.KEY_ABOUT_DIALOG_IMPRESSION, 987263278, context);
    }

    public void sendAboutDialogVisitSite(Context context) {
        sendRequestSdkStatisticsWithArgs(StatController.KEY_ABOUT_DIALOG_VISIT_SITE, -980696864, context);
    }

    public void sendUpdateInfo(Context context) {
        int currentVersion = VersionManager.getCurrentVersion(context);
        int previousVersion = VersionManager.getPreviousVersion(context);
        if (previousVersion == -1) {
            VersionManager.updateVersion(context, currentVersion);
        } else if (currentVersion > previousVersion) {
            VersionManager.updateVersion(context, currentVersion);
            sendRequestWithAllArgs(UPDATE_ACTION_URL_TAG, NetworkManager.RequestType.UPDATE.ordinal(), context);
        }
    }

    public void getConfigPhp(final Context context, DeviceIdParameters deviceIdParameters, ConfigPhpRequestListener configPhpRequestListener) {
        ConfigPhp configPhp = this.configPhpModel;
        if (configPhp != null) {
            configPhpRequestListener.receivedConfigPhp(configPhp);
        } else if (this.listenerList.size() > 0) {
            this.listenerList.add(configPhpRequestListener);
        } else {
            Configuration instance = Configuration.getInstance(context);
            this.listenerList.add(configPhpRequestListener);
            String str = "";
            String advId = deviceIdParameters != null ? deviceIdParameters.getAdvId() : str;
            if (!TextUtils.isEmpty(advId)) {
                str = "&advid=" + advId;
            }
            instance.loadConfiguration();
            String str2 = "http://config.appsgeyser.com/?widgetId=" + instance.getApplicationId() + "&guid=" + instance.getAppGuid() + "&v=" + Constants.PLATFORM_VERSION + "&market=" + getInstallerMarket(context) + str;
            final PreferencesCoder preferencesCoder = new PreferencesCoder(context);
            preferencesCoder.savePrefString(Constants.PREFS_CONFIG_PHP_URL, str2);
            this.networkManager.sendRequestAsync(str2, Integer.valueOf(NetworkManager.RequestType.CONFIG_PHP.ordinal()), context, (OnRequestDoneListener) new OnRequestDoneListener() {
                public void onRequestDone(String str, int i, String str2) {
                    preferencesCoder.savePrefString(Constants.PREFS_SERVER_RESPONSE, str2);
                    try {
                        ConfigPhp unused = AppsgeyserServerClient.this.configPhpModel = ConfigPhp.parseFromJson(str2);
                        if (AppsgeyserServerClient.this.configPhpModel.getStatUrls() != null) {
                            StatController.getInstance().init(new HashMap(AppsgeyserServerClient.this.configPhpModel.getStatUrls()));
                        }
                        if (AppsgeyserServerClient.this.configPhpModel.isAppBanActive()) {
                            PausedContentInfoActivity.startPausedContentInfoActivity(context, false);
                            return;
                        }
                        DataSdkController.startDataSdkController(context, AppsgeyserServerClient.this.configPhpModel);
                        if (AppsgeyserServerClient.this.configPhpModel.isInAppPurchasesActive()) {
                            InAppPurchaseController.getInstance().init(context);
                        }
                        if (!FastTrackAdsController.getInstance().isActive()) {
                            FastTrackAdsController.getInstance().requestInit(AppsgeyserServerClient.this.configPhpModel, context);
                        }
                        for (ConfigPhpRequestListener receivedConfigPhp : AppsgeyserServerClient.this.listenerList) {
                            receivedConfigPhp.receivedConfigPhp(AppsgeyserServerClient.this.configPhpModel);
                        }
                        if (AppsgeyserServerClient.this.configPhpModel.isPushNotificationsActive()) {
                            AppsgeyserServerClient.this.initPush(context);
                        }
                        if (!AppsgeyserServerClient.this.ratingDialogActivated) {
                            AppsgeyserServerClient.this.initRatingDialog(context, AppsgeyserServerClient.this.configPhpModel);
                            boolean unused2 = AppsgeyserServerClient.this.ratingDialogActivated = true;
                        }
                        if (AppsgeyserServerClient.this.configPhpModel.getAdditionalJsCode() != null) {
                            InternalEntryPoint.getInstance().setAdditionalJsCode(AppsgeyserServerClient.this.configPhpModel.getAdditionalJsCode());
                        }
                        if (AppsgeyserServerClient.this.configPhpModel.isInactivityReminderEnabled()) {
                            ReminderAlarmManager.startReminderAlarm(context, AppsgeyserServerClient.this.configPhpModel.getInactivityDaysPeriod(), true);
                        }
                        AppsgeyserServerClient.this.listenerList.clear();
                    } catch (JsonSyntaxException unused3) {
                        for (ConfigPhpRequestListener access$500 : AppsgeyserServerClient.this.listenerList) {
                            AppsgeyserServerClient.this.onGetConfigErrorResponse(context, access$500, preferencesCoder);
                        }
                    }
                }
            }, (Response.ErrorListener) new Response.ErrorListener() {
                public void onErrorResponse(VolleyError volleyError) {
                    for (ConfigPhpRequestListener access$500 : AppsgeyserServerClient.this.listenerList) {
                        AppsgeyserServerClient.this.onGetConfigErrorResponse(context, access$500, preferencesCoder);
                    }
                    AppsgeyserServerClient.this.listenerList.clear();
                    InternalEntryPoint.getInstance().setConsentRequestProcessActive(false);
                }
            });
        }
    }

    public void sendClickInfo(String str, Context context) {
        this.networkManager.sendRequestAsync(str, Integer.valueOf(NetworkManager.RequestType.CLICK.ordinal()), context, this.networkManager.getEmptyRequestDoneListener(context), this.networkManager.getDefaultErrorListener(Integer.valueOf(NetworkManager.RequestType.CLICK.ordinal()), context));
    }

    public void sendImpression(String str, Context context) {
        this.networkManager.sendRequestAsync(str, Integer.valueOf(NetworkManager.RequestType.IMPRESSION.ordinal()), context, this.networkManager.getEmptyRequestDoneListener(context), this.networkManager.getDefaultErrorListener(Integer.valueOf(NetworkManager.RequestType.IMPRESSION.ordinal()), context));
    }

    public void sendApplicationMode(Context context) {
        Configuration instance = Configuration.getInstance(context);
        this.networkManager.sendRequestAsync("http://ads.appsgeyser.com/checkstatus.php?wid=" + instance.getApplicationId(), Integer.valueOf(NetworkManager.RequestType.APPMODE.ordinal()), context, this.networkManager.getEmptyRequestDoneListener(context), this.networkManager.getDefaultErrorListener(Integer.valueOf(NetworkManager.RequestType.APPMODE.ordinal()), context));
    }

    private void sendRequestWithAllArgs(String str, int i, Context context) {
        String str2;
        Configuration instance = Configuration.getInstance(context);
        String advId = DeviceIdParser.getInstance().getDeviceIdParameters().getAdvId();
        if (!TextUtils.isEmpty(advId)) {
            str2 = "http://stat.appioapp.com/statistics.php?action=" + str + "&name=" + instance.getApplicationId() + "&id=" + instance.getAppGuid() + "&v=" + Constants.PLATFORM_VERSION + "&p=android&advid=" + advId + "&market=" + getInstallerMarket(context) + DeviceInfoGetter.getDeviceInfo(context) + "&templateversion=" + instance.getTemplateVersion();
        } else {
            str2 = "http://stat.appioapp.com/statistics.php?action=" + str + "&name=" + instance.getApplicationId() + "&id=" + instance.getAppGuid() + "&v=" + Constants.PLATFORM_VERSION + "&p=android&market=" + getInstallerMarket(context) + DeviceInfoGetter.getDeviceInfo(context) + "&templateversion=" + instance.getTemplateVersion();
        }
        this.networkManager.sendRequestAsync(str2, Integer.valueOf(i), context, this.networkManager.getEmptyRequestDoneListener(context), this.networkManager.getDefaultErrorListener(Integer.valueOf(i), context));
    }

    public void sendRMARequest(Context context, Map<String, String> map) {
        this.networkManager.sendRequestAsyncPost(Constants.RMA_STATISTICS_URL, Integer.valueOf(NetworkManager.RequestType.RMA.ordinal()), context, this.networkManager.getEmptyRequestDoneListener(context), this.networkManager.getDefaultErrorListener(Integer.valueOf(NetworkManager.RequestType.RMA.ordinal()), context), map);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001c, code lost:
        if (r1 != 4) goto L_0x0033;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void sendReferrerStatsRequest(android.content.Context r10, com.appsgeyser.sdk.configuration.Constants.ReferrerInfoStatus r11, com.android.installreferrer.api.ReferrerDetails r12) {
        /*
            r9 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "http://analytics.appsgeyser.com/ref_stats.php"
            r0.append(r1)
            int[] r1 = com.appsgeyser.sdk.server.implementation.AppsgeyserServerClient.C50874.f4925xdf95f0f6
            int r2 = r11.ordinal()
            r1 = r1[r2]
            r2 = 1
            if (r1 == r2) goto L_0x001f
            r2 = 2
            if (r1 == r2) goto L_0x0024
            r2 = 3
            if (r1 == r2) goto L_0x0029
            r2 = 4
            if (r1 == r2) goto L_0x002e
            goto L_0x0033
        L_0x001f:
            java.lang.String r1 = "?action=add&status=ok"
            r0.append(r1)
        L_0x0024:
            java.lang.String r1 = "?action=add&status=feature_not_supported"
            r0.append(r1)
        L_0x0029:
            java.lang.String r1 = "?action=add&status=unavailable"
            r0.append(r1)
        L_0x002e:
            java.lang.String r1 = "?action=add&status=remote_exception"
            r0.append(r1)
        L_0x0033:
            java.lang.String r3 = r0.toString()
            java.util.HashMap r8 = new java.util.HashMap
            r8.<init>()
            com.appsgeyser.sdk.configuration.Constants$ReferrerInfoStatus r0 = com.appsgeyser.sdk.configuration.Constants.ReferrerInfoStatus.OK
            if (r11 != r0) goto L_0x0072
            if (r12 == 0) goto L_0x0072
            java.lang.String r11 = r12.getInstallReferrer()
            java.lang.String r0 = "referrerUrl"
            r8.put(r0, r11)
            long r0 = r12.getReferrerClickTimestampSeconds()
            java.lang.String r11 = java.lang.String.valueOf(r0)
            java.lang.String r0 = "referrerClickTime"
            r8.put(r0, r11)
            long r0 = r12.getInstallBeginTimestampSeconds()
            java.lang.String r11 = java.lang.String.valueOf(r0)
            java.lang.String r0 = "appInstallTime"
            r8.put(r0, r11)
            boolean r11 = r12.getGooglePlayInstantParam()
            java.lang.String r11 = java.lang.String.valueOf(r11)
            java.lang.String r12 = "instantExperienceLaunched"
            r8.put(r12, r11)
        L_0x0072:
            com.appsgeyser.sdk.server.network.NetworkManager r2 = r9.networkManager
            com.appsgeyser.sdk.server.network.NetworkManager$RequestType r11 = com.appsgeyser.sdk.server.network.NetworkManager.RequestType.REFERRER
            int r11 = r11.ordinal()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r11)
            com.appsgeyser.sdk.server.network.NetworkManager r11 = r9.networkManager
            com.appsgeyser.sdk.server.implementation.OnRequestDoneListener r6 = r11.getEmptyRequestDoneListener(r10)
            com.appsgeyser.sdk.server.network.NetworkManager r11 = r9.networkManager
            com.appsgeyser.sdk.server.network.NetworkManager$RequestType r12 = com.appsgeyser.sdk.server.network.NetworkManager.RequestType.REFERRER
            int r12 = r12.ordinal()
            java.lang.Integer r12 = java.lang.Integer.valueOf(r12)
            com.android.volley.Response$ErrorListener r7 = r11.getDefaultErrorListener(r12, r10)
            r5 = r10
            r2.sendRequestAsyncPost((java.lang.String) r3, (java.lang.Integer) r4, (android.content.Context) r5, (com.appsgeyser.sdk.server.implementation.OnRequestDoneListener) r6, (com.android.volley.Response.ErrorListener) r7, (java.util.Map<java.lang.String, java.lang.String>) r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsgeyser.sdk.server.implementation.AppsgeyserServerClient.sendReferrerStatsRequest(android.content.Context, com.appsgeyser.sdk.configuration.Constants$ReferrerInfoStatus, com.android.installreferrer.api.ReferrerDetails):void");
    }

    /* renamed from: com.appsgeyser.sdk.server.implementation.AppsgeyserServerClient$4 */
    static /* synthetic */ class C50874 {

        /* renamed from: $SwitchMap$com$appsgeyser$sdk$configuration$Constants$ReferrerInfoStatus */
        static final /* synthetic */ int[] f4925xdf95f0f6;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.appsgeyser.sdk.configuration.Constants$ReferrerInfoStatus[] r0 = com.appsgeyser.sdk.configuration.Constants.ReferrerInfoStatus.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f4925xdf95f0f6 = r0
                com.appsgeyser.sdk.configuration.Constants$ReferrerInfoStatus r1 = com.appsgeyser.sdk.configuration.Constants.ReferrerInfoStatus.OK     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f4925xdf95f0f6     // Catch:{ NoSuchFieldError -> 0x001d }
                com.appsgeyser.sdk.configuration.Constants$ReferrerInfoStatus r1 = com.appsgeyser.sdk.configuration.Constants.ReferrerInfoStatus.FEATURE_NOT_SUPPORTED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f4925xdf95f0f6     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.appsgeyser.sdk.configuration.Constants$ReferrerInfoStatus r1 = com.appsgeyser.sdk.configuration.Constants.ReferrerInfoStatus.UNAVAILABLE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f4925xdf95f0f6     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.appsgeyser.sdk.configuration.Constants$ReferrerInfoStatus r1 = com.appsgeyser.sdk.configuration.Constants.ReferrerInfoStatus.REMOTE_EXCEPTION     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.appsgeyser.sdk.server.implementation.AppsgeyserServerClient.C50874.<clinit>():void");
        }
    }

    private void sendRequestSdkStatisticsWithArgs(String str, int i, Context context) {
        Configuration instance = Configuration.getInstance(context);
        this.networkManager.sendRequestAsync("http://stat.appsgeyser.com/sdk_statistics.php?action=" + str + "&wdid=" + instance.getApplicationId() + "&guid=" + instance.getAppGuid() + "&v=" + Constants.PLATFORM_VERSION + "&p=android" + DeviceInfoGetter.getDeviceInfo(context) + "&templateversion=" + instance.getTemplateVersion(), Integer.valueOf(i), context, this.networkManager.getEmptyRequestDoneListener(context), this.networkManager.getDefaultErrorListener(Integer.valueOf(i), context));
    }

    /* access modifiers changed from: private */
    public void onGetConfigErrorResponse(Context context, ConfigPhpRequestListener configPhpRequestListener, PreferencesCoder preferencesCoder) {
        String prefString = preferencesCoder.getPrefString(Constants.PREFS_SERVER_RESPONSE, "");
        if (!prefString.equals("")) {
            try {
                ConfigPhp parseFromJson = ConfigPhp.parseFromJson(prefString);
                this.configPhpModel = parseFromJson;
                configPhpRequestListener.receivedConfigPhp(parseFromJson);
            } catch (JsonParseException e) {
                Log.d("JsonParseException", e.toString());
            }
        }
        DataSdkController.onGetConfigErrorResponse(context);
    }

    /* access modifiers changed from: private */
    public void initPush(Context context) {
        OneSignalCreator.init(context.getApplicationContext());
    }

    /* access modifiers changed from: private */
    public void initRatingDialog(final Context context, ConfigPhp configPhp) {
        if (configPhp.isRateMyAppActive()) {
            new RatingDialog.Builder(context).threshold(configPhp.getRateMyAppThreshold() > 0 ? (float) configPhp.getRateMyAppThreshold() : 3.0f).session(configPhp.getRateMyAppSessionsCount() > 0 ? configPhp.getRateMyAppSessionsCount() : 3).onThresholdFailed($$Lambda$AppsgeyserServerClient$JTuO0Q45p1yPOFkSNl1jHXqmso.INSTANCE).onThresholdCleared(new RatingDialog.Builder.RatingThresholdClearedListener(context) {
                public final /* synthetic */ Context f$0;

                {
                    this.f$0 = r1;
                }

                public final void onThresholdCleared(RatingDialog ratingDialog, float f, boolean z) {
                    AppsgeyserServerClient.lambda$initRatingDialog$2(this.f$0, ratingDialog, f, z);
                }
            }).onRatingBarFormSumbit(new RatingDialog.Builder.RatingDialogFormListener(context) {
                public final /* synthetic */ Context f$0;

                {
                    this.f$0 = r1;
                }

                public final void onFormSubmitted(String str) {
                    AppsgeyserServerClient.lambda$initRatingDialog$3(this.f$0, str);
                }
            }).dialogListener(new RatingDialog.Builder.RatingDialogListener() {
                public void onRatingDialogShown() {
                    StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_RMA_IMPRESSIONS, (HashMap<String, String>) null, context, true);
                }

                public void onRatingSelected(float f, boolean z) {
                    InternalEntryPoint.getInstance().setSelectedRating(f);
                }
            }).build().show();
        }
    }

    static /* synthetic */ void lambda$initRatingDialog$0(RatingDialog ratingDialog, float f, boolean z) {
        ratingDialog.switchToForm();
        ratingDialog.getEtFeedback().setVisibility(0);
    }

    static /* synthetic */ void lambda$initRatingDialog$2(Context context, RatingDialog ratingDialog, float f, boolean z) {
        ratingDialog.switchToForm();
        ratingDialog.getFormTitleTextView().setText(C5051R.string.rating_dialog_rate_title);
        ratingDialog.getFormSumbitTextView().setText(C5051R.string.rating_dialog_rate);
        ratingDialog.getFormSumbitTextView().setOnClickListener(new View.OnClickListener(context, ratingDialog) {
            public final /* synthetic */ Context f$0;
            public final /* synthetic */ RatingDialog f$1;

            {
                this.f$0 = r1;
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                AppsgeyserServerClient.lambda$null$1(this.f$0, this.f$1, view);
            }
        });
    }

    static /* synthetic */ void lambda$null$1(Context context, RatingDialog ratingDialog, View view) {
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_RMA_RATES, (HashMap<String, String>) null, context, true);
        HashMap hashMap = new HashMap();
        hashMap.put("widgetId", Configuration.getInstance(context).getApplicationId());
        hashMap.put("rate", Float.toString(InternalEntryPoint.getInstance().getSelectedRating()));
        getInstance().sendRMARequest(context, hashMap);
        ratingDialog.openPlaystore(ratingDialog.getContext());
        ratingDialog.showNever();
        ratingDialog.dismiss();
    }

    static /* synthetic */ void lambda$initRatingDialog$3(Context context, String str) {
        StatController.getInstance().sendRequestAsyncByKey(StatController.KEY_RMA_FEEDBACKS, (HashMap<String, String>) null, context, true);
        HashMap hashMap = new HashMap();
        hashMap.put("widgetId", Configuration.getInstance(context).getApplicationId());
        hashMap.put("rate", Float.toString(InternalEntryPoint.getInstance().getSelectedRating()));
        hashMap.put("feedback", str);
        getInstance().sendRMARequest(context, hashMap);
    }

    public void setConfigPhpModel(ConfigPhp configPhp) {
        this.configPhpModel = configPhp;
    }
}

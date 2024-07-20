package com.appsgeyser.sdk.server;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import com.appsgeyser.sdk.configuration.Configuration;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.appsgeyser.sdk.utils.DeviceInfoGetter;
import com.yandex.metrica.YandexMetrica;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class StatController {
    public static final String FT_NETWORK_ADMOB = "ft_admob";
    public static final String FT_NETWORK_ADMOB_CUSTOM = "ft_admob_custom";
    public static final String FT_NETWORK_FACEBOOK = "ft_facebook";
    public static final String FT_NETWORK_FACEBOOK_CUSTOM = "ft_facebook_custom";
    public static final String FT_NETWORK_MOPUB = "ft_mopub";
    public static final String FT_NETWORK_MOPUB_CUSTOM = "ft_mopub_custom";
    public static final String KEY_ABOUT_DIALOG_IMPRESSION = "about_dialog_impression";
    public static final String KEY_ABOUT_DIALOG_VISIT_SITE = "about_dialog_visit_site";
    public static final String KEY_ADMOB = "admobSdk";
    public static final String KEY_CLICK_ACCEPT_PERMISSION_ACCESS_COARSE_LOCATION = "click_accept_permission_access_coarse_location";
    public static final String KEY_CLICK_ACCEPT_PERMISSION_ACCESS_FINE_LOCATION = "click_accept_permission_access_fine_location";
    public static final String KEY_CLICK_ACCEPT_PERMISSION_GET_ACCOUNTS = "click_accept_permission_get_accounts";
    public static final String KEY_CLICK_ACCEPT_PERMISSION_READ_EXTERNAL_STORAGE = "click_accept_permission_read_external_storage";
    public static final String KEY_CLICK_ACCEPT_PERMISSION_READ_PHONE_STATE = "click_accept_permission_read_phone_state";
    public static final String KEY_CLICK_ACCEPT_PERMISSION_WRITE_EXTERNAL_STORAGE = "click_accept_permission_write_external_storage";
    public static final String KEY_CLICK_ACCEPT_SDK_DIALOG = "click_accept_sdk_dialog";
    public static final String KEY_CLICK_DECLINE_PERMISSION_ACCESS_COARSE_LOCATION = "click_decline_permission_access_coarse_location";
    public static final String KEY_CLICK_DECLINE_PERMISSION_ACCESS_FINE_LOCATION = "click_decline_permission_access_fine_location";
    public static final String KEY_CLICK_DECLINE_PERMISSION_GET_ACCOUNTS = "click_decline_permission_get_accounts";
    public static final String KEY_CLICK_DECLINE_PERMISSION_READ_EXTERNAL_STORAGE = "click_decline_permission_read_external_storage";
    public static final String KEY_CLICK_DECLINE_PERMISSION_READ_PHONE_STATE = "click_decline_permission_read_phone_state";
    public static final String KEY_CLICK_DECLINE_PERMISSION_WRITE_EXTERNAL_STORAGE = "click_decline_permission_write_external_storage";
    public static final String KEY_CLICK_DECLINE_SDK_DIALOG = "click_decline_sdk_dialog";
    public static final String KEY_FACEBOOK = "facebookSdk";
    public static final String KEY_FT_BANNER_SDK_CLICK = "ft_banner_sdk_click";
    public static final String KEY_FT_BANNER_SDK_ERROR = "ft_banner_sdk_error";
    public static final String KEY_FT_BANNER_SDK_IMPRESSION = "ft_banner_sdk_impression";
    public static final String KEY_FT_BANNER_SDK_NOFILL = "ft_banner_sdk_nofill";
    public static final String KEY_FT_BANNER_SDK_REQUEST = "ft_banner_sdk_request";
    public static final String KEY_FT_INTERSTITIAL_SDK_ATTEMPT = "ft_interstitial_sdk_attempt";
    public static final String KEY_FT_INTERSTITIAL_SDK_CLICK = "ft_interstitial_sdk_click";
    public static final String KEY_FT_INTERSTITIAL_SDK_ERROR = "ft_interstitial_sdk_error";
    public static final String KEY_FT_INTERSTITIAL_SDK_IMPRESSION = "ft_interstitial_sdk_impression";
    public static final String KEY_FT_INTERSTITIAL_SDK_NOFILL = "ft_interstitial_sdk_nofill";
    public static final String KEY_FT_INTERSTITIAL_SDK_REQUEST = "ft_interstitial_sdk_request";
    public static final String KEY_FT_REWARDED_SDK_ATTEMPT = "ft_rewarded_sdk_attempt";
    public static final String KEY_FT_REWARDED_SDK_CLICK = "ft_rewarded_sdk_click";
    public static final String KEY_FT_REWARDED_SDK_COMPLETION = "ft_rewarded_sdk_completion";
    public static final String KEY_FT_REWARDED_SDK_ERROR = "ft_rewarded_sdk_error";
    public static final String KEY_FT_REWARDED_SDK_IMPRESSION = "ft_rewarded_sdk_impression";
    public static final String KEY_FT_REWARDED_SDK_NOFILL = "ft_rewarded_sdk_nofill";
    public static final String KEY_FT_REWARDED_SDK_REQUEST = "ft_rewarded_sdk_request";
    public static final String KEY_INAPP_DISABLE_ADS_ACKNOWLEDGED = "inapp_disable_ads_acknowledged";
    public static final String KEY_INAPP_DISABLE_ADS_CANCELED = "inapp_disable_ads_canceled";
    public static final String KEY_INAPP_DISABLE_ADS_PENDING = "inapp_disable_ads_pending";
    public static final String KEY_INIT_ERROR = "init_error";
    public static final String KEY_MOPUB = "mopubSdk";
    public static final String KEY_RMA_FEEDBACKS = "rma_dialog_feedback_clicks";
    public static final String KEY_RMA_IMPRESSIONS = "rma_dialog_impressions";
    public static final String KEY_RMA_RATES = "rma_dialog_rate_clicks";
    private static final String LOG = "StatController";
    private static final String TEMPLATE_VERSION_URL_KEY = "templateversion";
    private static StatController instance;
    private HashMap<String, String> items;

    public enum AdsType {
        NATIVE,
        REWARDED,
        FULLSCREEN
    }

    private StatController() {
    }

    public static synchronized StatController getInstance() {
        StatController statController;
        synchronized (StatController.class) {
            if (instance == null) {
                instance = new StatController();
            }
            statController = instance;
        }
        return statController;
    }

    public void init(HashMap<String, String> hashMap) {
        this.items = hashMap;
    }

    public void sendRequestAsyncByKey(String str) {
        sendRequestAsyncByKey(str, (HashMap<String, String>) null, (Context) null, false);
    }

    public void sendRequestAsyncByKey(String str, final HashMap<String, String> hashMap, Context context, boolean z) {
        if (!(!z || context == null || hashMap == null)) {
            HashMap<String, String> deviceInfoMap = DeviceInfoGetter.getDeviceInfoMap(context);
            if (deviceInfoMap != null) {
                hashMap.putAll(deviceInfoMap);
            }
            String templateVersion = Configuration.getInstance(context).getTemplateVersion();
            if (templateVersion != null) {
                hashMap.put(TEMPLATE_VERSION_URL_KEY, templateVersion);
            }
        }
        sendQueryToYandexMetrica(str, hashMap);
        if (!isInitialized()) {
            Log.d(LOG, "StatController not initialized, skipping stat request on: " + str);
            return;
        }
        final String str2 = this.items.get(str);
        if (str2 == null) {
            Log.d(LOG, "Stat url not set, skipping stat request on: " + str);
            return;
        }
        new Thread() {
            public void run() {
                String str = str2;
                if (hashMap != null) {
                    Uri.Builder buildUpon = Uri.parse(str).buildUpon();
                    for (Map.Entry entry : hashMap.entrySet()) {
                        buildUpon.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
                    }
                    str = buildUpon.build().toString();
                }
                RequestQueueHolder.addUrl(str);
            }
        }.start();
    }

    private void sendQueryToYandexMetrica(String str, HashMap<String, String> hashMap) {
        String str2 = null;
        if (hashMap != null) {
            try {
                str2 = new JSONObject(hashMap).toString();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        YandexMetrica.reportEvent(str, str2);
    }

    private boolean isInitialized() {
        return this.items != null;
    }

    public static HashMap<String, String> generateQueryParametersForSdk(ConfigPhp configPhp, Context context, AdsType adsType, String str, String str2) {
        String str3;
        HashMap<String, String> hashMap = new HashMap<>();
        Configuration instance2 = Configuration.getInstance(context);
        hashMap.put("wdid", instance2.getApplicationId());
        hashMap.put("guid", instance2.getAppGuid());
        hashMap.put("details", str);
        String str4 = "";
        if (adsType == AdsType.NATIVE) {
            str4 = configPhp.getAdsNetworkSdk().get(str2).getUniqueId();
            str3 = configPhp.getAdsNetworkSdk().get(str2).getBannerId();
        } else if (adsType == AdsType.REWARDED) {
            str4 = configPhp.getRewardedVideoSdk().get(str2).getUniqueId();
            str3 = configPhp.getRewardedVideoSdk().get(str2).getBannerId();
        } else if (adsType == AdsType.FULLSCREEN) {
            str4 = configPhp.getFullscreenSdk().get(str2).getUniqueId();
            str3 = configPhp.getFullscreenSdk().get(str2).getBannerId();
        } else {
            str3 = str4;
        }
        hashMap.put("uniqid", str4);
        hashMap.put("bannerid", str3);
        return hashMap;
    }
}

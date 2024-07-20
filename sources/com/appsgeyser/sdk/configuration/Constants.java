package com.appsgeyser.sdk.configuration;

public class Constants {
    public static final String AD_SERVER_DOMAIN_URL = "http://ads.appsgeyser.com/";
    public static final String APP_METRICA_ID = "34e75064-5ba5-4fac-b092-dc10aa167be0";
    public static final String CHECK_STATUS_URL = "http://ads.appsgeyser.com/checkstatus.php";
    public static final String CONFIG_DOMAIN_URL = "http://config.appsgeyser.com/";
    public static final String CUSTOM_HTML_ABOUT_URL = "http://www.appsgeyser.com/branding/";
    public static final String DOMAIN_URL = "http://www.appsgeyser.com";
    public static final int FULLSCREEN_BANNER_DEFAULT_FREQUENCY_TIMER = 120000;
    public static final int FULLSCREEN_BANNER_DEFAULT_INTENSITY_POINTS = 100;
    public static final int FULLSCREEN_BANNER_DEFAULT_PENDING_REQUEST_TIMER = 15000;
    public static final int FULLSCREEN_BANNER_DELAY = 2000;
    public static final int FULLSCREEN_BANNER_MIN_FREQUENCY_TIMER = 15000;
    public static final int FULLSCREEN_BANNER_MIN_PENDING_REQUEST_TIMER = 10000;
    public static final String INAPP_PURCHASES_ID_DISABLE_ADS = "disable_ads";
    public static final boolean LOG_DEBUG_ENABLED = true;
    public static final String LOG_DEBUG_TAG = "*** AppsgeyserSDK Debug";
    public static final boolean LOG_ERROR_ENABLED = true;
    public static final String LOG_ERROR_TAG = "AppsgeyserSDK";
    public static final String LOG_FS_BANNER_JS_ERRORS_TAG = "FSBannerJsError";
    public static final boolean LOG_INFO_ENABLED = true;
    public static final String LOG_INFO_TAG = "AppsgeyserSDK Info:";
    public static final String LOG_SMALL_BANNER_JS_ERRORS_TAG = "SmallBannerJsError";
    public static final String PAUSED_CONTENT_INFO_URL = "https://www.appsgeyser.com/paused/";
    public static final String PLATFORM_VERSION = "2.19.s";
    public static final String PREFS_APPSGEYSER_EULA_ACCEPTED = "appsgeyserSdk_eulaAccepted";
    public static final String PREFS_APPSGEYSER_FULLSCREEN_LAST_REQUEST_TIMING = "appsgeyserSdk_lastRequestTiming";
    public static final String PREFS_APPSGEYSER_REMINDER_LAST_SET_TIMING = "appsgeyserSdk_lastReminderSetTiming";
    public static final String PREFS_APPSGEYSER_SDK_ACTIVATED = "appsgeyserSdk_isActive";
    public static final String PREFS_CONFIG_PHP_URL = "ConfigPhpURLWithParams";
    public static final String PREFS_SERVER_RESPONSE = "ServerResponse";
    public static final String REFERRER_STATISTICS_STATUS_FEATURE_NOT_SUPPORTED_URL = "?action=add&status=feature_not_supported";
    public static final String REFERRER_STATISTICS_STATUS_OK_URL = "?action=add&status=ok";
    public static final String REFERRER_STATISTICS_STATUS_REMOTE_EXCEPTION = "?action=add&status=remote_exception";
    public static final String REFERRER_STATISTICS_STATUS_UNAVAILABLE_URL = "?action=add&status=unavailable";
    public static final String REFERRER_STATISTICS_URL = "http://analytics.appsgeyser.com/ref_stats.php";
    public static final String RMA_STATISTICS_URL = "http://analytics.appsgeyser.com/hypo/rma_dialog_feedback.php?action=add";
    public static final String SDK_STATISTICS_URL = "http://stat.appsgeyser.com/sdk_statistics.php";
    public static final int SMALL_BANNER_DEFAULT_REFRESH_RATE = 15000;
    public static final int SMALL_BANNER_DEFAULT_REQUEST_RATE = 15000;
    public static final String SPLASH_SERVER_DOMAIN_URL = "http://splash.appsgeyser.com/";
    public static final String SSL_ERROR_DIALOG_BUTTON_NEGATIVE = "Back to safety";
    public static final String SSL_ERROR_DIALOG_BUTTON_POSITIVE = "Proceed anyway";
    public static final String SSL_ERROR_DIALOG_MESSAGE = "The site's security certificate is not trusted!";
    public static final String SSL_ERROR_DIALOG_TITLE = "SSL connection error!";
    public static final String STATISTICS_URL = "http://stat.appioapp.com/statistics.php";
    private static long fullScreenDelay = -1;

    public enum ReferrerInfoStatus {
        OK,
        FEATURE_NOT_SUPPORTED,
        UNAVAILABLE,
        REMOTE_EXCEPTION
    }

    public class BannerLoadTags {
        public static final String ON_EXIT = "on_exit";
        public static final String ON_RESUME = "on_resume";
        public static final String ON_START = "on_start";
        public static final String ON_TAKE_OFF = "on_take_off";
        public static final String ON_TIMEOUT_PASSED = "on_timeout";

        public BannerLoadTags() {
        }
    }

    public static long getFullScreenDelay() {
        long j = fullScreenDelay;
        if (j != -1) {
            return j;
        }
        return 2000;
    }

    public static void setFullScreenDelay(long j) {
        fullScreenDelay = j;
    }
}

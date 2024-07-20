package acr.browser.lightning.preference;

import acr.browser.lightning.constant.Constants;
import acr.browser.lightning.domain.GeoData;
import acr.browser.lightning.domain.WeatherData;
import acr.browser.lightning.download.DownloadHandler;
import android.content.Context;
import android.content.SharedPreferences;
import com.appnext.ads.fullscreen.RewardedVideo;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferenceManager {
    private static final String PREFERENCES = "settings";
    private final SharedPreferences mPrefs;

    public enum Suggestion {
        SUGGESTION_GOOGLE,
        SUGGESTION_DUCK,
        SUGGESTION_BAIDU,
        SUGGESTION_NONE
    }

    public boolean getColorModeEnabled() {
        return false;
    }

    private static class Name {
        static final String ADOBE_FLASH_SUPPORT = "enableflash";
        static final String ADS_NEW_INCOGNITO_TAB = "newIncognitoTab";
        static final String ADS_NEW_TAB_IN_MINUTES = "newTabInMinutes";
        static final String ADS_ON_FIRST_PAGE_LOAD_FINISHED = "onFirstPageFinishLoad";
        static final String ADS_ON_HOME_PAGE_PRESSED = "onHomePagePressed";
        static final String ADV_ID = "advertisingId";
        static final String BACKGROUND_URL = "backgroundurl";
        static final String BLACK_STATUS_BAR = "blackStatusBar";
        static final String BLOCK_ADS = "AdBlock";
        static final String BLOCK_IMAGES = "blockimages";
        static final String BLOCK_THIRD_PARTY = "thirdParty";
        static final String BOOKMARKS_APPLIED = "bookmarksApplies";
        static final String CITY_NAME = "cityName";
        static final String CLEAR_CACHE_EXIT = "cache";
        static final String CLEAR_COOKIES_EXIT = "clearCookiesExit";
        static final String CLEAR_HISTORY_EXIT = "clearHistoryExit";
        static final String CLEAR_WEBSTORAGE_EXIT = "clearWebStorageExit";
        static final String COOKIES = "cookies";
        static final String DOWNLOAD_DIRECTORY = "downloadLocation";
        static final String DO_NOT_TRACK = "doNotTrack";
        static final String ENABLE_COLOR_MODE = "colorMode";
        static final String FULL_SCREEN = "fullscreen";
        static final String GEO_DATA_CITY_NAME = "geoDataCityName";
        static final String GEO_DATA_COUNTRY_CODE = "geoDataCountryCode";
        static final String GEO_DATA_LAST_UPDATE_TIME = "geoDataUpdateTime";
        static final String HIDE_STATUS_BAR = "hidestatus";
        static final String HOMEPAGE = "home";
        static final String IDENTIFYING_HEADERS = "removeIdentifyingHeaders";
        static final String INCOGNITO_COOKIES = "incognitocookies";
        static final String INITIAL_CHECK_FOR_TOR = "checkForTor";
        static final String INVERT_COLORS = "invertColors";
        static final String JAVASCRIPT = "java";
        static final String LAST_BANNER_SHOWN_TIME = "lastBannerShownTime";
        static final String LEAK_CANARY = "leakCanary";
        static final String LOCATION = "location";
        static final String NOTIFICATION_SEARCH_BAR = "notificationsearchbar";
        static final String NOTIFICATION_WEATHER = "notificationweather";
        static final String OVERVIEW_MODE = "overviewmode";
        static final String POPUPS = "newwindows";
        static final String PROXY_CHOICE = "proxyChoice";
        static final String READING_TEXT_SIZE = "readingTextSize";
        static final String RENDERING_MODE = "renderMode";
        static final String RESTORE_LOST_TABS = "restoreclosed";
        static final String REWARDED_VIDEO_CHANGE_THEME = "changeTheme";
        static final String REWARDED_VIDEO_CLICK_NEWS = "onClickNews";
        static final String REWARDED_VIDEO_INCOGNITO_TAB = "onIncognitoTab";
        static final String REWARDED_VIDEO_INTERVAL_IN_MINUTES = "showAdIntervalInMinutes";
        static final String REWARDED_VIDEO_LAST_VIEW_TIME = "lastVideoViewTime";
        static final String REWARDED_VIDEO_PROXY_SET_UP = "onProxySetUp";
        static final String REWARDED_VIDEO_SEARCH_ENGINE_CHANGE = "onSearchEngineChanging";
        static final String REWARDED_VIDEO_TEXT_SIZE_CHANGE = "onSizeTextChanging";
        static final String SAVE_PASSWORDS = "passwords";
        static final String SAVE_URL = "saveUrl";
        static final String SEARCH = "search";
        static final String SEARCH_APPLIED = "searchApplies";
        static final String SEARCH_SUGGESTIONS = "searchSuggestions";
        static final String SEARCH_URL = "searchurl";
        static final String SHOW_TABS_IN_DRAWER = "showTabsInDrawer";
        static final String SWAP_BOOKMARKS_AND_TABS = "swapBookmarksAndTabs";
        static final String TEXT_ENCODING = "textEncoding";
        static final String TEXT_REFLOW = "textreflow";
        static final String TEXT_SIZE = "textsize";
        static final String THEME = "Theme";
        static final String TOOL_BAR_STYLE = "toolBarStyle";
        static final String URL_BOX_CONTENTS = "urlContent";
        static final String USER_AGENT = "agentchoose";
        static final String USER_AGENT_STRING = "userAgentString";
        static final String USE_PROXY = "useProxy";
        static final String USE_PROXY_HOST = "useProxyHost";
        static final String USE_PROXY_PORT = "useProxyPort";
        static final String USE_WIDE_VIEWPORT = "wideviewport";
        static final String WEATHER_CODE = "weatherCode";
        static final String WEATHER_DEGREE_SYSTEM = "isCelsius";
        static final String WEATHER_LOCATION = "weatherLocation";
        static final String WEATHER_TEMP = "weatherTemp";
        static final String WEATHER_TEXT = "weatherText";
        static final String WEATHER_UPDATE_TIME = "weatherUpdateTime";

        private Name() {
        }
    }

    @Inject
    public PreferenceManager(Context context) {
        this.mPrefs = context.getSharedPreferences("settings", 0);
    }

    public Suggestion getSearchSuggestionChoice() {
        try {
            return Suggestion.valueOf(this.mPrefs.getString("searchSuggestions", Suggestion.SUGGESTION_GOOGLE.name()));
        } catch (IllegalArgumentException unused) {
            return Suggestion.SUGGESTION_NONE;
        }
    }

    public String getCity() {
        return this.mPrefs.getString("cityName", "New-York");
    }

    public void setCity(String str) {
        putString("cityName", str);
    }

    public boolean hasCity() {
        return this.mPrefs.contains("cityName");
    }

    public boolean removeCity() {
        return this.mPrefs.edit().remove("cityName").commit();
    }

    public void setWeatherDataData(WeatherData weatherData) {
        if (weatherData == null) {
            this.mPrefs.edit().remove("weatherTemp").remove("weatherCode").remove("weatherText").remove("weatherLocation").remove("isCelsius").remove("weatherUpdateTime").apply();
            return;
        }
        putInt("weatherTemp", weatherData.getTemp());
        putInt("weatherCode", weatherData.getCode());
        putString("weatherText", weatherData.getText());
        putString("weatherLocation", weatherData.getLocation());
        putBoolean("isCelsius", weatherData.isCecius());
        putInt("weatherUpdateTime", (int) (weatherData.getLastUpdateTime() / 1000));
    }

    public WeatherData getWeatherData() {
        WeatherData weatherData = new WeatherData();
        weatherData.setCode(this.mPrefs.getInt("weatherCode", 0));
        weatherData.setTemp(this.mPrefs.getInt("weatherTemp", 0));
        weatherData.setText(this.mPrefs.getString("weatherText", ""));
        weatherData.setLocation(this.mPrefs.getString("weatherLocation", ""));
        weatherData.setCecius(this.mPrefs.getBoolean("isCelsius", false));
        weatherData.setLastUpdateTime(((long) this.mPrefs.getInt("weatherUpdateTime", 0)) * 1000);
        return weatherData;
    }

    public void setGeoData(GeoData geoData) {
        putString("geoDataCityName", geoData.getCityName());
        putString("geoDataCountryCode", geoData.getCountryCode());
        putInt("geoDataUpdateTime", (int) (geoData.getLastUpdateTime() / 1000));
    }

    public GeoData getGeoData() {
        GeoData geoData = new GeoData();
        geoData.setCityName(this.mPrefs.getString("geoDataCityName", "New-York"));
        geoData.setCountryCode(this.mPrefs.getString("geoDataCountryCode", "en"));
        geoData.setLastUpdateTime(((long) this.mPrefs.getInt("geoDataUpdateTime", 0)) * 1000);
        return geoData;
    }

    public void setSearchSuggestionChoice(Suggestion suggestion) {
        putString("searchSuggestions", suggestion.name());
    }

    public boolean getBookmarksAndTabsSwapped() {
        return this.mPrefs.getBoolean("swapBookmarksAndTabs", false);
    }

    public String getAdvertisingId() {
        return this.mPrefs.getString("advertisingId", "");
    }

    public void setAdvertisingId(String str) {
        this.mPrefs.edit().putString("advertisingId", str);
    }

    public void setBookmarkAndTabsSwapped(boolean z) {
        putBoolean("swapBookmarksAndTabs", z);
    }

    public boolean getAdBlockEnabled() {
        return this.mPrefs.getBoolean("AdBlock", false);
    }

    public boolean getNotificationSearchBarEnabled() {
        return this.mPrefs.getBoolean("notificationsearchbar", true);
    }

    public boolean getNotificationWeatherEnabled() {
        return this.mPrefs.getBoolean("notificationweather", true);
    }

    public boolean getBlockImagesEnabled() {
        return this.mPrefs.getBoolean("blockimages", false);
    }

    public boolean getBlockThirdPartyCookiesEnabled() {
        return this.mPrefs.getBoolean("thirdParty", false);
    }

    public boolean getCheckedForTor() {
        return this.mPrefs.getBoolean("checkForTor", false);
    }

    public boolean getClearCacheExit() {
        return this.mPrefs.getBoolean("cache", false);
    }

    public boolean getClearCookiesExitEnabled() {
        return this.mPrefs.getBoolean("clearCookiesExit", false);
    }

    public boolean getClearWebStorageExitEnabled() {
        return this.mPrefs.getBoolean("clearWebStorageExit", false);
    }

    public boolean getClearHistoryExitEnabled() {
        return this.mPrefs.getBoolean("clearHistoryExit", false);
    }

    public boolean getCookiesEnabled() {
        return this.mPrefs.getBoolean("cookies", true);
    }

    public boolean getBookmarksApplies() {
        return this.mPrefs.getBoolean("bookmarksApplies", false);
    }

    public boolean getSearchEngineApplies() {
        return this.mPrefs.getBoolean("searchApplies", false);
    }

    public String getDownloadDirectory() {
        return this.mPrefs.getString("downloadLocation", DownloadHandler.DEFAULT_DOWNLOAD_PATH);
    }

    public int getFlashSupport() {
        return this.mPrefs.getInt("enableflash", 0);
    }

    public boolean getFullScreenEnabled() {
        return this.mPrefs.getBoolean("fullscreen", true);
    }

    public boolean getHideStatusBarEnabled() {
        return this.mPrefs.getBoolean("hidestatus", false);
    }

    public String getHomepage() {
        return this.mPrefs.getString("home", Constants.SCHEME_HOMEPAGE);
    }

    public boolean getIncognitoCookiesEnabled() {
        return this.mPrefs.getBoolean("incognitocookies", true);
    }

    public boolean getInvertColors() {
        return this.mPrefs.getBoolean("invertColors", false);
    }

    public boolean getJavaScriptEnabled() {
        return this.mPrefs.getBoolean("java", true);
    }

    public boolean getLocationEnabled() {
        return this.mPrefs.getBoolean("location", false);
    }

    public boolean getOverviewModeEnabled() {
        return this.mPrefs.getBoolean("overviewmode", true);
    }

    public boolean getPopupsEnabled() {
        return this.mPrefs.getBoolean("newwindows", true);
    }

    public String getProxyHost() {
        return this.mPrefs.getString("useProxyHost", "localhost");
    }

    public int getProxyPort() {
        return this.mPrefs.getInt("useProxyPort", 8118);
    }

    public int getReadingTextSize() {
        return this.mPrefs.getInt("readingTextSize", 2);
    }

    public int getRenderingMode() {
        return this.mPrefs.getInt("renderMode", 0);
    }

    public boolean getRestoreLostTabsEnabled() {
        return this.mPrefs.getBoolean("restoreclosed", true);
    }

    public String getSavedUrl() {
        return this.mPrefs.getString("saveUrl", (String) null);
    }

    public boolean getSavePasswordsEnabled() {
        return this.mPrefs.getBoolean("passwords", true);
    }

    public int getSearchChoice() {
        return this.mPrefs.getInt("search", 1);
    }

    public String getSearchUrl() {
        return this.mPrefs.getString("searchurl", Constants.GOOGLE_SEARCH);
    }

    public String getBackgroundUrl() {
        return this.mPrefs.getString("backgroundurl", (String) null);
    }

    public boolean getTextReflowEnabled() {
        return this.mPrefs.getBoolean("textreflow", false);
    }

    public int getTextSize() {
        return this.mPrefs.getInt("textsize", 3);
    }

    public int getUrlBoxContentChoice() {
        return this.mPrefs.getInt("urlContent", 0);
    }

    public int getUseTheme() {
        return this.mPrefs.getInt("Theme", 0);
    }

    public boolean getUseProxy() {
        return this.mPrefs.getBoolean("useProxy", false);
    }

    public int getProxyChoice() {
        int i = this.mPrefs.getInt("proxyChoice", 0);
        if (i == 0 || i == 1 || i == 2) {
            return i;
        }
        return 0;
    }

    public int getUserAgentChoice() {
        return this.mPrefs.getInt("agentchoose", 1);
    }

    public String getUserAgentString(String str) {
        return this.mPrefs.getString("userAgentString", str);
    }

    public boolean getUseWideViewportEnabled() {
        return this.mPrefs.getBoolean("wideviewport", true);
    }

    public String getTextEncoding() {
        return this.mPrefs.getString("textEncoding", "UTF-8");
    }

    public boolean getShowTabsInDrawer(boolean z) {
        return this.mPrefs.getBoolean("showTabsInDrawer", z);
    }

    public boolean getDoNotTrackEnabled() {
        return this.mPrefs.getBoolean("doNotTrack", false);
    }

    public boolean getRemoveIdentifyingHeadersEnabled() {
        return this.mPrefs.getBoolean("removeIdentifyingHeaders", false);
    }

    public boolean getUseBlackStatusBar() {
        return this.mPrefs.getBoolean("blackStatusBar", false);
    }

    public long getLastBannerShownTime() {
        return this.mPrefs.getLong("lastBannerShownTime", 0);
    }

    public String getToolBarStyle() {
        return this.mPrefs.getString("toolBarStyle", RewardedVideo.VIDEO_MODE_DEFAULT);
    }

    public void setLastBannerShownTime(long j) {
        this.mPrefs.edit().putLong("lastBannerShownTime", j).apply();
    }

    private void putBoolean(String str, boolean z) {
        this.mPrefs.edit().putBoolean(str, z).apply();
    }

    private void putInt(String str, int i) {
        this.mPrefs.edit().putInt(str, i).apply();
    }

    private void putLong(String str, long j) {
        this.mPrefs.edit().putLong(str, j).apply();
    }

    private void putString(String str, String str2) {
        this.mPrefs.edit().putString(str, str2).apply();
    }

    public void setUseBlackStatusBar(boolean z) {
        putBoolean("blackStatusBar", z);
    }

    public void setAdsNewIncognitoTab(boolean z) {
        putBoolean("newIncognitoTab", z);
    }

    public boolean getAdsNewIncognitoTab() {
        return this.mPrefs.getBoolean("newIncognitoTab", false);
    }

    public void setAdsNewTabInMinutes(int i) {
        putInt("newTabInMinutes", i);
    }

    public int getAdsNewTabInMinutes() {
        return this.mPrefs.getInt("newTabInMinutes", 1);
    }

    public void setAdsOnFirstPageLoadFinished(boolean z) {
        putBoolean("onFirstPageFinishLoad", z);
    }

    public boolean getAdsOnFirstPageLoadFinished() {
        return this.mPrefs.getBoolean("onFirstPageFinishLoad", false);
    }

    public void setAdsOnHomePagePressed(boolean z) {
        putBoolean("onHomePagePressed", z);
    }

    public boolean getAdsOnHomePagePressed() {
        return this.mPrefs.getBoolean("onHomePagePressed", false);
    }

    public void setRemoveIdentifyingHeadersEnabled(boolean z) {
        putBoolean("removeIdentifyingHeaders", z);
    }

    public void setDoNotTrackEnabled(boolean z) {
        putBoolean("doNotTrack", z);
    }

    public void setShowTabsInDrawer(boolean z) {
        putBoolean("showTabsInDrawer", z);
    }

    public void setTextEncoding(String str) {
        putString("textEncoding", str);
    }

    public void setAdBlockEnabled(boolean z) {
        putBoolean("AdBlock", z);
    }

    public void setNotificationSearchEnabled(boolean z) {
        putBoolean("notificationsearchbar", z);
    }

    public void setNotificationWeatherEnabled(boolean z) {
        putBoolean("notificationweather", z);
    }

    public void setBlockImagesEnabled(boolean z) {
        putBoolean("blockimages", z);
    }

    public void setBlockThirdPartyCookiesEnabled(boolean z) {
        putBoolean("thirdParty", z);
    }

    public void setCheckedForTor(boolean z) {
        putBoolean("checkForTor", z);
    }

    public void setBookmarksApplied(boolean z) {
        putBoolean("bookmarksApplies", z);
    }

    public void setSearchEngineApplied(boolean z) {
        putBoolean("searchApplies", z);
    }

    public void setClearCacheExit(boolean z) {
        putBoolean("cache", z);
    }

    public void setClearCookiesExitEnabled(boolean z) {
        putBoolean("clearCookiesExit", z);
    }

    public void setClearWebStorageExitEnabled(boolean z) {
        putBoolean("clearWebStorageExit", z);
    }

    public void setClearHistoryExitEnabled(boolean z) {
        putBoolean("clearHistoryExit", z);
    }

    public void setColorModeEnabled(boolean z) {
        putBoolean("colorMode", z);
    }

    public void setCookiesEnabled(boolean z) {
        putBoolean("cookies", z);
    }

    public void setDownloadDirectory(String str) {
        putString("downloadLocation", str);
    }

    public void setFlashSupport(int i) {
        putInt("enableflash", i);
    }

    public void setFullScreenEnabled(boolean z) {
        putBoolean("fullscreen", z);
    }

    public void setHideStatusBarEnabled(boolean z) {
        putBoolean("hidestatus", z);
    }

    public void setHomepage(String str) {
        putString("home", str);
    }

    public void setIncognitoCookiesEnabled(boolean z) {
        putBoolean("incognitocookies", z);
    }

    public void setInvertColors(boolean z) {
        putBoolean("invertColors", z);
    }

    public void setJavaScriptEnabled(boolean z) {
        putBoolean("java", z);
    }

    public void setLocationEnabled(boolean z) {
        putBoolean("location", z);
    }

    public void setOverviewModeEnabled(boolean z) {
        putBoolean("overviewmode", z);
    }

    public void setPopupsEnabled(boolean z) {
        putBoolean("newwindows", z);
    }

    public void setReadingTextSize(int i) {
        putInt("readingTextSize", i);
    }

    public void setRenderingMode(int i) {
        putInt("renderMode", i);
    }

    public void setRestoreLostTabsEnabled(boolean z) {
        putBoolean("restoreclosed", z);
    }

    public void setSavedUrl(String str) {
        putString("saveUrl", str);
    }

    public void setSavePasswordsEnabled(boolean z) {
        putBoolean("passwords", z);
    }

    public void setSearchChoice(int i) {
        putInt("search", i);
    }

    public void setSearchUrl(String str) {
        putString("searchurl", str);
    }

    public void setBackgroundUrl(String str) {
        putString("backgroundurl", str);
    }

    public void setTextReflowEnabled(boolean z) {
        putBoolean("textreflow", z);
    }

    public void setTextSize(int i) {
        putInt("textsize", i);
    }

    public void setUrlBoxContentChoice(int i) {
        putInt("urlContent", i);
    }

    public void setUseTheme(int i) {
        putInt("Theme", i);
    }

    public void setUseLeakCanary(boolean z) {
        putBoolean("leakCanary", z);
    }

    public boolean getUseLeakCanary() {
        return this.mPrefs.getBoolean("leakCanary", false);
    }

    public void setRewardedVideoOnChangeTheme(boolean z) {
        putBoolean("changeTheme", z);
    }

    public boolean getRewardedVideoOnChangeTheme() {
        return this.mPrefs.getBoolean("changeTheme", true);
    }

    public void setRewardedVideoOnIncognitoTab(boolean z) {
        putBoolean("onIncognitoTab", z);
    }

    public boolean getRewardedVideoOnIncognitoTab() {
        return this.mPrefs.getBoolean("onIncognitoTab", true);
    }

    public void setRewardedVideoInterval(int i) {
        putInt("showAdIntervalInMinutes", i);
    }

    public int getRewardedVideoInterval() {
        return this.mPrefs.getInt("showAdIntervalInMinutes", 1);
    }

    public void setRewardedVideoOnSearchEngine(boolean z) {
        putBoolean("onSearchEngineChanging", z);
    }

    public boolean getRewardedVideoOnSearchEngine() {
        return this.mPrefs.getBoolean("onSearchEngineChanging", false);
    }

    public void setRewardedVideoOnProxuSetUp(boolean z) {
        putBoolean("onProxySetUp", z);
    }

    public boolean getRewardedVideoOnProxuSetUp() {
        return this.mPrefs.getBoolean("onProxySetUp", false);
    }

    public void setRewardedVideoOnTextSizeChangin(boolean z) {
        putBoolean("onSizeTextChanging", z);
    }

    public boolean getRewardedVideoOnTextSizeChangin() {
        return this.mPrefs.getBoolean("onSizeTextChanging", false);
    }

    public void setRewardedVideoOnClickNews(boolean z) {
        putBoolean("onClickNews", z);
    }

    public boolean getRewardedVideoOnClickNews() {
        return this.mPrefs.getBoolean("onClickNews", false);
    }

    public void setRewardedVideoLastViewTime(long j) {
        putLong("lastVideoViewTime", j);
    }

    public long getRewardedVideoLastViewTime() {
        Long l = 0L;
        return this.mPrefs.getLong("lastVideoViewTime", l.longValue());
    }

    public void setProxyChoice(int i) {
        putBoolean("useProxy", i != 0);
        putInt("proxyChoice", i);
    }

    public void setProxyHost(String str) {
        putString("useProxyHost", str);
    }

    public void setProxyPort(int i) {
        putInt("useProxyPort", i);
    }

    public void setUserAgentChoice(int i) {
        putInt("agentchoose", i);
    }

    public void setUserAgentString(String str) {
        putString("userAgentString", str);
    }

    public void setUseWideViewportEnabled(boolean z) {
        putBoolean("wideviewport", z);
    }

    public void setToolBarStyle(String str) {
        putString("toolBarStyle", str);
    }
}

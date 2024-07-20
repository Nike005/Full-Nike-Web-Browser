package acr.browser.lightning.config;

import acr.browser.lightning.activity.BrowserActivity;
import acr.browser.lightning.database.HistoryItem;
import acr.browser.lightning.preference.PreferenceManager;
import acr.browser.lightning.view.BookmarkWidget;
import acr.browser.lightning.view.DownloadsWidget;
import acr.browser.lightning.view.HistoryWidget;
import acr.browser.lightning.view.NewsWidget;
import acr.browser.lightning.view.WeatherWidget;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.appnext.ads.fullscreen.RewardedVideo;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Config {
    public static final String CONFIG_PREFERENCES = "ConfigPref";
    private int accentColor;
    private String appName;
    private Drawable background;
    private String backgroundUrl;
    private int bookmarkWidgetColor;
    private boolean bookmarkWidgetEnabled;
    private int bookmarkWidgetOrderId;
    private String bookmarkWidgetType;
    private List<HistoryItem> bookmarksList;
    private Context context;
    private int downloadsWidgetColor;
    private boolean downloadsWidgetEnabled;
    private int downloadsWidgetOrderId;
    private String downloadsWidgetType;
    private int historyWidgetColor;
    private boolean historyWidgetEnabled;
    private int historyWidgetOrderId;
    private String historyWidgetType;
    private String homePageUrl;
    private String homepageTabs;
    private Drawable icon;
    private Map<String, String> iconMap;
    private String newsTabsPosition;
    private int newsWidgetColor;
    private int newsWidgetOrderId;
    private String newsWidgetType;
    private int primaryColor;
    private int primaryDarkColor;
    private boolean searchBarNotificationEnabled;
    private String startPageUrl;
    private String toolbarPosition;
    private boolean weatherNotificationEnabled;
    private int weatherWidgetColor;
    private boolean weatherWidgetEnabled;
    private int weatherWidgetOrderId;
    private String weatherWidgetType;
    private boolean widgetsMargins;

    private void requestAdsSettings() {
    }

    private void initIconMap() {
        HashMap hashMap = new HashMap();
        this.iconMap = hashMap;
        hashMap.put("https://www.google.ru/", "img/google-search-icon.png");
        this.iconMap.put("https://www.amazon.com/", "img/amazon.png");
        this.iconMap.put("https://www.reddit.com/", "img/reddit-icon.png");
        this.iconMap.put("https://vimeo.com/", "img/vimeo-icon.png");
        this.iconMap.put("http://coub.com/", "img/coub-icon.png");
        this.iconMap.put("https://www.facebook.com/", "img/facebook-icon.png");
        this.iconMap.put("https://twitter.com/", "img/twitter-icon.png");
        this.iconMap.put("https://pinterest.com/", "img/pinterest-icon.png");
        this.iconMap.put("http://www.tumblr.com/", "img/tumblr-icon.png");
        this.iconMap.put("https://www.linkedin.com/", "img/linkedin.png");
        this.iconMap.put("https://www.instagram.com/", "img/instagram-icon.png");
    }

    public Config(Context context2, PreferenceManager preferenceManager) {
        this.context = context2;
        initIconMap();
        requestAdsSettings();
        try {
            JSONObject jSONObject = new JSONObject(loadSettings(context2));
            this.appName = jSONObject.getString("name");
            this.bookmarksList = new ArrayList();
            JSONObject jSONObject2 = jSONObject.getJSONObject("viewSettings");
            preferenceManager.setFullScreenEnabled(jSONObject2.getBoolean("fullScreenMode"));
            preferenceManager.setHideStatusBarEnabled(jSONObject2.getBoolean("hidedStatusBar"));
            preferenceManager.setBookmarkAndTabsSwapped(jSONObject2.getBoolean("swapDrawers"));
            preferenceManager.setShowTabsInDrawer(jSONObject2.getBoolean("tabsInNavigationDrawer"));
            preferenceManager.setToolBarStyle(jSONObject2.getString("toolBarStyle").isEmpty() ? RewardedVideo.VIDEO_MODE_DEFAULT : jSONObject2.getString("toolBarStyle"));
            preferenceManager.setNotificationWeatherEnabled(jSONObject2.getBoolean("weatherNotificationEnabled"));
            preferenceManager.setNotificationSearchEnabled(jSONObject2.getBoolean("searchBarNotificationEnabled"));
            this.toolbarPosition = ifEmpty(jSONObject2.getString("toolbarPosition"), "top");
            this.backgroundUrl = jSONObject.getString("backgroundImage");
            JSONObject jSONObject3 = jSONObject.getJSONObject("homepageTabs");
            JSONObject jSONObject4 = jSONObject3.getJSONObject("widgets");
            this.homepageTabs = ifEmpty(jSONObject3.getString("homepageTabs"), "widgets");
            this.widgetsMargins = jSONObject3.getBoolean("widgetsMargins");
            if (this.homepageTabs.equals("custom")) {
                this.homePageUrl = jSONObject3.getString("homePageUrl");
            } else if (this.homepageTabs.equals("website")) {
                this.startPageUrl = jSONObject3.getString("startPageUrl");
            }
            if (this.startPageUrl != null && !this.startPageUrl.equals("") && !this.startPageUrl.isEmpty()) {
                preferenceManager.setHomepage(this.startPageUrl);
            }
            JSONObject jSONObject5 = jSONObject4.getJSONObject("weatherWidget");
            this.weatherWidgetOrderId = jSONObject5.getInt("sortPosition");
            this.weatherWidgetEnabled = jSONObject5.getBoolean("weatherWidgetEnabled");
            this.weatherWidgetType = ifEmpty(jSONObject5.getString("weatherWidgetType"), WeatherWidget.THIN);
            this.weatherWidgetColor = readColor(jSONObject5, "weatherWidgetColor").intValue();
            JSONObject jSONObject6 = jSONObject4.getJSONObject("bookmarkWidget");
            this.bookmarkWidgetOrderId = jSONObject6.getInt("sortPosition");
            this.bookmarkWidgetEnabled = jSONObject6.getBoolean("bookmarkWidgetEnabled");
            this.bookmarkWidgetType = ifEmpty(jSONObject6.getString("bookmarkWidgetType"), BookmarkWidget.GRID);
            this.bookmarkWidgetColor = readColor(jSONObject6, "bookmarkWidgetColor").intValue();
            JSONObject jSONObject7 = jSONObject4.getJSONObject("downloadsWidget");
            this.downloadsWidgetOrderId = jSONObject7.getInt("sortPosition");
            this.downloadsWidgetEnabled = jSONObject7.getBoolean("downloadsWidgetEnabled");
            this.downloadsWidgetType = ifEmpty(jSONObject7.getString("downloadsWidgetType"), DownloadsWidget.GRID);
            this.downloadsWidgetColor = readColor(jSONObject7, "downloadsWidgetColor").intValue();
            JSONObject jSONObject8 = jSONObject4.getJSONObject("historyWidget");
            this.historyWidgetOrderId = jSONObject8.getInt("sortPosition");
            this.historyWidgetEnabled = jSONObject8.getBoolean("historyWidgetEnabled");
            this.historyWidgetType = ifEmpty(jSONObject8.getString("historyWidgetType"), HistoryWidget.LIST);
            this.historyWidgetColor = readColor(jSONObject8, "historyWidgetColor").intValue();
            JSONObject jSONObject9 = jSONObject4.getJSONObject("newsWidget");
            this.newsWidgetOrderId = jSONObject9.getInt("sortPosition");
            this.newsWidgetType = ifEmpty(jSONObject9.getString("newsWidgetType"), NewsWidget.LIST);
            if (!jSONObject9.getString("newsTabsPosition").equals(BrowserActivity.TAB_POSITION_WIDGET)) {
                this.newsTabsPosition = jSONObject2.getString("toolbarPosition");
            } else {
                this.newsTabsPosition = ifEmpty(jSONObject9.getString("newsTabsPosition"), "top");
            }
            this.newsWidgetColor = readColor(jSONObject9, "newsWidgetColor").intValue();
            this.background = createDrawable(this.backgroundUrl);
            this.bookmarksList = readBookmarksList(jSONObject.getJSONArray("browserLinks"));
            JSONObject jSONObject10 = jSONObject.getJSONObject("themeColors");
            this.primaryColor = readColor(jSONObject10, "colorPrimary").intValue();
            this.primaryDarkColor = readColor(jSONObject10, "colorPrimaryDark").intValue();
            this.accentColor = readColor(jSONObject10, "colorAccent").intValue();
        } catch (JSONException e) {
            Log.e("Config", "Json parse error: " + e.getMessage());
        } catch (IOException e2) {
            Log.e("Config", "Json read error: " + e2.getMessage());
        }
    }

    private Drawable createDrawable(String str) {
        if (str.equals("")) {
            return null;
        }
        try {
            Bitmap decodeStream = BitmapFactory.decodeStream(this.context.getAssets().open(str));
            decodeStream.setDensity(0);
            return new BitmapDrawable(this.context.getResources(), decodeStream);
        } catch (FileNotFoundException unused) {
            Log.d("Config", "Image " + str + " not found");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<HistoryItem> readBookmarksList(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            HistoryItem historyItem = new HistoryItem();
            historyItem.setUrl(jSONArray.getJSONObject(i).getString("url"));
            historyItem.setTitle(jSONArray.getJSONObject(i).getString("title"));
            historyItem.setImageUrl(jSONArray.getJSONObject(i).getString("icon"));
            historyItem.setShowOnMainScreen(true);
            arrayList.add(historyItem);
        }
        return arrayList;
    }

    private Integer readColor(JSONObject jSONObject, String str) throws JSONException {
        String string = jSONObject.getString(str);
        if (string == null || string.equals("")) {
            return null;
        }
        if (!string.startsWith("#")) {
            string = "#" + string;
        }
        return Integer.valueOf(Color.parseColor(string));
    }

    public String getBackgroundUrl() {
        return this.backgroundUrl;
    }

    public String loadSettings(Context context2) throws IOException {
        try {
            InputStream open = context2.getAssets().open("settings.json");
            byte[] bArr = new byte[open.available()];
            open.read(bArr);
            open.close();
            return new String(bArr, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String ifEmpty(String str, String str2) {
        return str.isEmpty() ? str2 : str;
    }

    public Drawable getBackground() {
        return this.background;
    }

    public Integer getPrimaryDarkColor() {
        return Integer.valueOf(this.primaryDarkColor);
    }

    public Integer getAccentColor() {
        return Integer.valueOf(this.accentColor);
    }

    public Integer getPrimaryColor() {
        return Integer.valueOf(this.primaryColor);
    }

    public String getHomePageUrl() {
        String str = this.homePageUrl;
        return str == null ? "" : str;
    }

    public String getAppName() {
        return this.appName;
    }

    public List<HistoryItem> getBookmarksList() {
        return this.bookmarksList;
    }

    public Drawable getIcon() {
        return this.icon;
    }

    public String getWeatherWidgetType() {
        return this.weatherWidgetType;
    }

    public int getWeatherWidgetColor() {
        return this.weatherWidgetColor;
    }

    public String getDownloadsWidgetType() {
        return this.downloadsWidgetType;
    }

    public String getBookmarkWidgetType() {
        return this.bookmarkWidgetType;
    }

    public String getHistoryWidgetType() {
        return this.historyWidgetType;
    }

    public boolean isWidgetsMargins() {
        return this.widgetsMargins;
    }

    public int getWeatherWidgetOrderId() {
        return this.weatherWidgetOrderId;
    }

    public int getBookmarkWidgetOrderId() {
        return this.bookmarkWidgetOrderId;
    }

    public int getDownloadsWidgetOrderId() {
        return this.downloadsWidgetOrderId;
    }

    public boolean isHistoryWidgetEnabled() {
        return this.historyWidgetEnabled;
    }

    public boolean isDownloadsWidgetEnabled() {
        return this.downloadsWidgetEnabled;
    }

    public boolean isBookmarkWidgetEnabled() {
        return this.bookmarkWidgetEnabled;
    }

    public boolean isWeatherWidgetEnabled() {
        return this.weatherWidgetEnabled;
    }

    public int getHistoryWidgetOrderId() {
        return this.historyWidgetOrderId;
    }

    public int getBookmarkWidgetColor() {
        return this.bookmarkWidgetColor;
    }

    public int getDownloadsWidgetColor() {
        return this.downloadsWidgetColor;
    }

    public int getHistoryWidgetColor() {
        return this.historyWidgetColor;
    }

    public boolean isSearchBarNotificationEnabled() {
        return this.searchBarNotificationEnabled;
    }

    public void setSearchBarNotificationEnabled(boolean z) {
        this.searchBarNotificationEnabled = z;
    }

    public boolean isWeatherNotificationEnabled() {
        return this.weatherNotificationEnabled;
    }

    public void setWeatherNotificationEnabled(boolean z) {
        this.weatherNotificationEnabled = z;
    }

    public String getToolbarPosition() {
        return this.toolbarPosition;
    }

    public String getNewsTabsPosition() {
        return this.newsTabsPosition;
    }

    public int getNewsWidgetColor() {
        return this.newsWidgetColor;
    }

    public int getNewsWidgetOrderId() {
        return this.newsWidgetOrderId;
    }

    public String getNewsWidgetType() {
        return this.newsWidgetType;
    }
}

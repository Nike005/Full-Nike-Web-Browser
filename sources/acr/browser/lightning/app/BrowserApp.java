package acr.browser.lightning.app;

import acr.browser.lightning.config.Config;
import acr.browser.lightning.config.ThemeManager;
import acr.browser.lightning.database.HistoryItem;
import acr.browser.lightning.database.bookmark.BookmarkModel;
import acr.browser.lightning.database.bookmark.legacy.LegacyBookmarkManager;
import acr.browser.lightning.preference.PreferenceManager;
import acr.browser.lightning.service.WeatherJobService;
import acr.browser.lightning.utils.MemoryLeakUtils;
import acr.browser.lightning.utils.Preconditions;
import acr.browser.lightning.utils.StartPageLoader;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;
import com.anthonycr.bonsai.Schedulers;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;
import com.mopub.common.MoPubBrowser;
import com.wnikebrow_13999769.R;
import java.io.IOException;
import java.lang.Thread;
import java.util.List;
import javax.inject.Inject;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class BrowserApp extends MultiDexApplication {
    private static final String TAG = "BrowserApp";
    /* access modifiers changed from: private */
    public static volatile Config config;
    private static AppComponent sAppComponent;
    private static volatile ThemeManager themeManager;
    @Inject
    BookmarkModel mBookmarkModel;
    @Inject
    PreferenceManager mPreferenceManager;

    public static boolean isRelease() {
        return true;
    }

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(Build.VERSION.SDK_INT <= 19);
    }

    public static AppComponent getAppComponent() {
        Preconditions.checkNonNull(sAppComponent);
        return sAppComponent;
    }

    public static void copyToClipboard(Context context, String str) {
        ((ClipboardManager) context.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(MoPubBrowser.DESTINATION_URL_KEY, str));
    }

    public static Config getConfig() {
        return config;
    }

    public static ThemeManager getThemeManager() {
        return themeManager;
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    public void onCreate() {
        super.onCreate();
        AppsgeyserSDK.setApplicationInstance(this);
        final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            public void uncaughtException(Thread thread, Throwable th) {
                Thread.UncaughtExceptionHandler uncaughtExceptionHandler = defaultUncaughtExceptionHandler;
                if (uncaughtExceptionHandler != null) {
                    uncaughtExceptionHandler.uncaughtException(thread, th);
                } else {
                    System.exit(2);
                }
            }
        });
        AppComponent build = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        sAppComponent = build;
        build.inject(this);
        loadConfig();
        Context applicationContext = getApplicationContext();
        final String str = "wid=" + applicationContext.getResources().getString(R.string.widgetID) + "&advid=" + this.mPreferenceManager.getAdvertisingId() + "$pn=" + applicationContext.getPackageName() + "&v=" + applicationContext.getResources().getString(R.string.platformVersion) + "apiKey=" + applicationContext.getResources().getString(R.string.api_key);
        loadAdSettings(str);
        Schedulers.worker().execute(new Runnable() {
            public void run() {
                List<HistoryItem> destructiveGetBookmarks = LegacyBookmarkManager.destructiveGetBookmarks(BrowserApp.this);
                if (!destructiveGetBookmarks.isEmpty()) {
                    BrowserApp.this.mBookmarkModel.addBookmarkList(destructiveGetBookmarks).subscribeOn(Schedulers.m6232io()).subscribe();
                } else if (!BrowserApp.this.mPreferenceManager.getBookmarksApplies()) {
                    BrowserApp.this.mBookmarkModel.addBookmarkList(BrowserApp.config.getBookmarksList()).subscribeOn(Schedulers.m6232io()).subscribe();
                    BrowserApp.this.mPreferenceManager.setBookmarksApplied(true);
                }
                StartPageLoader.requestBookmarks(new StartPageLoader.BoormarksHandler() {
                    public void onResult(List<HistoryItem> list) {
                        for (HistoryItem addBookmarkIfNotExists : list) {
                            BrowserApp.this.mBookmarkModel.addBookmarkIfNotExists(addBookmarkIfNotExists, false).subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe();
                        }
                    }
                }, str);
                if (!BrowserApp.this.mPreferenceManager.getSearchEngineApplies()) {
                    StartPageLoader.requestSearchEngine(new StartPageLoader.SearchEngineHandler() {
                        public void onResult(Integer num, String str) {
                            if (num != null) {
                                BrowserApp.this.mPreferenceManager.setSearchChoice(num.intValue());
                                if (num.equals(0) && str != null && !str.equals("")) {
                                    BrowserApp.this.mPreferenceManager.setSearchUrl(str);
                                }
                            }
                        }
                    }, str);
                }
            }
        });
        registerActivityLifecycleCallbacks(new MemoryLeakUtils.LifecycleAdapter() {
            public void onActivityDestroyed(Activity activity) {
                Log.d(BrowserApp.TAG, "Cleaning up after the Android framework");
                MemoryLeakUtils.clearNextServedView(activity, BrowserApp.this);
            }
        });
        themeManager = new ThemeManager(getApplicationContext());
        if (this.mPreferenceManager.getNotificationWeatherEnabled()) {
            FirebaseJobDispatcher firebaseJobDispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
            firebaseJobDispatcher.mustSchedule(firebaseJobDispatcher.newJobBuilder().setService(WeatherJobService.class).setTag("proxy-weather-job").setRecurring(true).setLifetime(2).setTrigger(Trigger.executionWindow(90000, 126000)).setReplaceCurrent(true).setRetryStrategy(RetryStrategy.DEFAULT_LINEAR).build());
        }
    }

    private void loadAdSettings(String str) {
        OkHttpClient okHttpClient = new OkHttpClient();
        Request.Builder builder = new Request.Builder();
        okHttpClient.newCall(builder.url("http://frame.appsgeyser.com/api/configuration/json.php?" + str).build()).enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                iOException.printStackTrace();
                Log.e(BrowserApp.TAG, "Request ads settings failed http://frame.appsgeyser.com/api/configuration/json.php");
            }

            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject jSONObject = new JSONObject(response.body().string());
                    response.body().close();
                    JSONObject jSONObject2 = jSONObject.getJSONObject("showAds");
                    JSONObject jSONObject3 = jSONObject.getJSONObject("showRewardedVideoAds");
                    try {
                        BrowserApp.this.mPreferenceManager.setAdsNewIncognitoTab(jSONObject2.getBoolean("newIncognitoTab"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e(BrowserApp.TAG, "Can't cast newIncognitoTab to boolean " + jSONObject2);
                    }
                    try {
                        BrowserApp.this.mPreferenceManager.setAdsOnFirstPageLoadFinished(jSONObject2.getBoolean("onFirstPageFinishLoad"));
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                        Log.e(BrowserApp.TAG, "Can't cast newIncognitoTab to boolean " + jSONObject2);
                    }
                    try {
                        BrowserApp.this.mPreferenceManager.setAdsOnHomePagePressed(jSONObject2.getBoolean("onHomePagePressed"));
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                        Log.e(BrowserApp.TAG, "Can't cast newIncognitoTab to boolean " + jSONObject2);
                    }
                    try {
                        BrowserApp.this.mPreferenceManager.setAdsNewTabInMinutes(jSONObject2.getInt("newTabInMinutes"));
                    } catch (JSONException e4) {
                        e4.printStackTrace();
                        Log.e(BrowserApp.TAG, "Can't cast newIncognitoTab to int " + jSONObject2);
                    }
                    try {
                        BrowserApp.this.mPreferenceManager.setRewardedVideoInterval(jSONObject3.getInt("showAdIntervalInMinutes"));
                    } catch (JSONException e5) {
                        e5.printStackTrace();
                        Log.e(BrowserApp.TAG, "Can't cast newIncognitoTab to int " + jSONObject3);
                    }
                    try {
                        BrowserApp.this.mPreferenceManager.setRewardedVideoOnChangeTheme(jSONObject3.getBoolean("changeTheme"));
                    } catch (JSONException e6) {
                        e6.printStackTrace();
                        Log.e(BrowserApp.TAG, "Can't cast newIncognitoTab to int " + jSONObject3);
                    }
                    try {
                        BrowserApp.this.mPreferenceManager.setRewardedVideoOnIncognitoTab(jSONObject3.getBoolean("onIncognitoTab"));
                    } catch (JSONException e7) {
                        e7.printStackTrace();
                        Log.e(BrowserApp.TAG, "Can't cast newIncognitoTab to int " + jSONObject3);
                    }
                    try {
                        BrowserApp.this.mPreferenceManager.setRewardedVideoOnProxuSetUp(jSONObject3.getBoolean("onProxySetUp"));
                    } catch (JSONException e8) {
                        e8.printStackTrace();
                        Log.e(BrowserApp.TAG, "Can't cast newIncognitoTab to int " + jSONObject3);
                    }
                    try {
                        BrowserApp.this.mPreferenceManager.setRewardedVideoOnSearchEngine(jSONObject3.getBoolean("onSearchEngineChanging"));
                    } catch (JSONException e9) {
                        e9.printStackTrace();
                        Log.e(BrowserApp.TAG, "Can't cast newIncognitoTab to int " + jSONObject3);
                    }
                    try {
                        BrowserApp.this.mPreferenceManager.setRewardedVideoOnTextSizeChangin(jSONObject3.getBoolean("onSizeTextChanging"));
                    } catch (JSONException e10) {
                        e10.printStackTrace();
                        Log.e(BrowserApp.TAG, "Can't cast newIncognitoTab to int " + jSONObject3);
                    }
                    try {
                        BrowserApp.this.mPreferenceManager.setRewardedVideoOnClickNews(jSONObject3.getBoolean("onClickNews"));
                    } catch (JSONException e11) {
                        e11.printStackTrace();
                        Log.e(BrowserApp.TAG, "Can't cast newIncognitoTab to int " + jSONObject3);
                    }
                } catch (JSONException e12) {
                    e12.printStackTrace();
                    Log.e(BrowserApp.TAG, "Request ads settings failed http://frame.appsgeyser.com/api/configuration/json.php response = " + response);
                }
            }
        });
    }

    private void loadConfig() {
        config = new Config(getApplicationContext(), this.mPreferenceManager);
    }
}

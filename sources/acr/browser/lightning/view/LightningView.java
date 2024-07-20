package acr.browser.lightning.view;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.constant.BookmarkPage;
import acr.browser.lightning.constant.Constants;
import acr.browser.lightning.constant.DownloadsPage;
import acr.browser.lightning.constant.HistoryPage;
import acr.browser.lightning.constant.StartPage;
import acr.browser.lightning.controller.UIController;
import acr.browser.lightning.database.bookmark.BookmarkModel;
import acr.browser.lightning.dialog.LightningDialogBuilder;
import acr.browser.lightning.download.LightningDownloadListener;
import acr.browser.lightning.preference.PreferenceManager;
import acr.browser.lightning.utils.C3245Utils;
import acr.browser.lightning.utils.Preconditions;
import acr.browser.lightning.utils.ProxyUtils;
import acr.browser.lightning.utils.UrlUtils;
import acr.browser.lightning.view.HomepageView;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;
import androidx.collection.ArrayMap;
import androidx.fragment.app.FragmentActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.anthonycr.bonsai.Schedulers;
import com.anthonycr.bonsai.Single;
import com.anthonycr.bonsai.SingleAction;
import com.anthonycr.bonsai.SingleOnSubscribe;
import com.anthonycr.bonsai.SingleSubscriber;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.Map;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;

public class LightningView {
    /* access modifiers changed from: private */
    public static final int API = Build.VERSION.SDK_INT;
    private static final String HEADER_DNT = "DNT";
    public static final String HEADER_REQUESTED_WITH = "X-Requested-With";
    public static final String HEADER_WAP_PROFILE = "X-Wap-Profile";
    /* access modifiers changed from: private */
    public static final int SCROLL_UP_THRESHOLD = C3245Utils.dpToPx(10.0f);
    private static final String TAG = "LightningView";
    private static String sDefaultUserAgent;
    private static String sHomepage;
    private static final float[] sIncreaseContrastColorArray = {2.0f, 0.0f, 0.0f, 0.0f, -160.0f, 0.0f, 2.0f, 0.0f, 0.0f, -160.0f, 0.0f, 0.0f, 2.0f, 0.0f, -160.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    /* access modifiers changed from: private */
    public static float sMaxFling;
    private static final float[] sNegativeColorArray = {-1.0f, 0.0f, 0.0f, 0.0f, 255.0f, 0.0f, -1.0f, 0.0f, 0.0f, 255.0f, 0.0f, 0.0f, -1.0f, 0.0f, 255.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f};
    private FrameLayout container;
    private View homePage;
    private boolean isHomePage;
    /* access modifiers changed from: private */
    public final FragmentActivity mActivity;
    @Inject
    BookmarkModel mBookmarkManager;
    @Inject
    LightningDialogBuilder mBookmarksDialogBuilder;
    /* access modifiers changed from: private */
    public final GestureDetector mGestureDetector;
    private boolean mInvertPage = false;
    private boolean mIsForegroundTab;
    private final boolean mIsIncognitoTab;
    private boolean mIsNewTab;
    private final Paint mPaint = new Paint();
    @Inject
    PreferenceManager mPreferences;
    @Inject
    ProxyUtils mProxyUtils;
    private final Map<String, String> mRequestHeaders = new ArrayMap();
    private final LightningViewTitle mTitle;
    private boolean mToggleDesktop = false;
    /* access modifiers changed from: private */
    public final UIController mUIController;
    /* access modifiers changed from: private */
    public WebView mWebView;
    /* access modifiers changed from: private */
    public final WebViewHandler mWebViewHandler = new WebViewHandler(this);
    private SwipeRefreshLayout swipeRefreshLayout;
    private float translationY;
    private String url = null;

    public LightningView(FragmentActivity fragmentActivity, String str, boolean z) {
        BrowserApp.getAppComponent().inject(this);
        this.mActivity = fragmentActivity;
        this.mUIController = (UIController) fragmentActivity;
        this.mWebView = new WebView(fragmentActivity);
        if (Build.VERSION.SDK_INT > 16) {
            this.mWebView.setId(View.generateViewId());
        }
        SwipeRefreshLayout swipeRefreshLayout2 = new SwipeRefreshLayout(fragmentActivity);
        this.swipeRefreshLayout = swipeRefreshLayout2;
        swipeRefreshLayout2.setColorSchemeColors(BrowserApp.getThemeManager().getAccentColor(this.mPreferences.getUseTheme()));
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            public void onRefresh() {
                LightningView.this.reload();
            }
        });
        this.swipeRefreshLayout.addView(this.mWebView);
        FrameLayout frameLayout = new FrameLayout(fragmentActivity);
        this.container = frameLayout;
        frameLayout.addView(this.swipeRefreshLayout);
        this.mIsIncognitoTab = z;
        this.mTitle = new LightningViewTitle(fragmentActivity);
        sMaxFling = (float) ViewConfiguration.get(fragmentActivity).getScaledMaximumFlingVelocity();
        this.mWebView.setDrawingCacheBackgroundColor(-1);
        this.mWebView.setFocusableInTouchMode(true);
        this.mWebView.setFocusable(true);
        this.mWebView.setDrawingCacheEnabled(false);
        this.mWebView.setWillNotCacheDrawing(true);
        if (Build.VERSION.SDK_INT <= 22) {
            this.mWebView.setAnimationCacheEnabled(false);
            this.mWebView.setAlwaysDrawnWithCacheEnabled(false);
        }
        this.mWebView.setBackgroundColor(-1);
        this.mWebView.setScrollbarFadingEnabled(true);
        this.mWebView.setSaveEnabled(true);
        this.mWebView.setNetworkAvailable(true);
        this.mWebView.setWebChromeClient(new LightningChromeClient(fragmentActivity, this));
        this.mWebView.setWebViewClient(new LightningWebClient(fragmentActivity, this));
        this.mWebView.addJavascriptInterface(new JavaScriptHandler(fragmentActivity), "Android");
        this.mWebView.setDownloadListener(new LightningDownloadListener(fragmentActivity));
        this.mGestureDetector = new GestureDetector(fragmentActivity, new CustomGestureListener());
        this.mWebView.setOnTouchListener(new TouchListener());
        sDefaultUserAgent = this.mWebView.getSettings().getUserAgentString();
        initializeSettings();
        initializePreferences(fragmentActivity);
        if (str != null) {
            if (!str.trim().isEmpty()) {
                this.mWebView.loadUrl(str, this.mRequestHeaders);
            }
            this.isHomePage = false;
            return;
        }
        loadHomepage();
    }

    public void setView(String str) {
        boolean z = (str.endsWith(StartPage.FILENAME) || (!BrowserApp.getConfig().getHomePageUrl().equals("") && str.endsWith(BrowserApp.getConfig().getHomePageUrl()))) && this.homePage != null;
        this.isHomePage = z;
        if (z) {
            this.container.removeAllViews();
            this.container.addView(this.homePage);
            this.container.setTranslationY(this.translationY);
            this.container.setPadding(0, 0, 0, (int) this.translationY);
        } else {
            this.container.removeAllViews();
            this.container.addView(this.swipeRefreshLayout);
            this.container.setTranslationY(this.translationY);
            this.container.setPadding(0, 0, 0, 0);
        }
        this.url = str;
    }

    public boolean isHomePage() {
        return this.isHomePage;
    }

    public void setRefreshing(boolean z) {
        this.swipeRefreshLayout.setRefreshing(z);
    }

    public void setIsNewTab(boolean z) {
        this.mIsNewTab = z;
    }

    public boolean isNewTab() {
        return this.mIsNewTab;
    }

    public void loadHomepage() {
        if (this.mWebView != null) {
            Preconditions.checkNonNull(sHomepage);
            String str = sHomepage;
            char c = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -1145275824) {
                if (hashCode == 1396069548 && str.equals(Constants.SCHEME_HOMEPAGE)) {
                    c = 0;
                }
            } else if (str.equals(Constants.SCHEME_BOOKMARKS)) {
                c = 1;
            }
            if (c == 0) {
                loadStartpage();
            } else if (c != 1) {
                this.mWebView.loadUrl(sHomepage, this.mRequestHeaders);
            } else {
                loadBookmarkpage();
            }
            this.isHomePage = true;
        }
    }

    public void loadStartpage() {
        if (BrowserApp.getConfig().getHomePageUrl().equals("")) {
            HomepageView homepageView = new HomepageView(this.mActivity, this.mIsIncognitoTab, this.mPreferences);
            this.homePage = homepageView.getView();
            homepageView.setUrlClickedListener(new HomepageView.UrlClickedListener() {
                public void onUrlClicked(String str) {
                    Preconditions.checkNonNull(str);
                    LightningView.this.loadUrl(str);
                }
            });
            new StartPage().getHomepage().subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<String>() {
                public void onItem(String str) {
                    Preconditions.checkNonNull(str);
                    LightningView.this.loadUrl(str);
                }
            });
            return;
        }
        loadUrl("file:///android_asset/" + BrowserApp.getConfig().getHomePageUrl());
    }

    public void setTranslationY(float f) {
        this.translationY = f;
        String str = this.url;
        if (str == null) {
            return;
        }
        if ((str.endsWith(StartPage.FILENAME) || (!BrowserApp.getConfig().getHomePageUrl().equals("") && this.url.endsWith(BrowserApp.getConfig().getHomePageUrl()))) && this.homePage != null) {
            this.container.setTranslationY(f);
            this.container.setPadding(0, 0, 0, (int) f);
            return;
        }
        this.container.setTranslationY(f);
        this.container.setPadding(0, 0, 0, 0);
    }

    public void loadBookmarkpage() {
        new BookmarkPage(this.mActivity).getBookmarkPage().subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<String>() {
            public void onItem(String str) {
                Preconditions.checkNonNull(str);
                LightningView.this.loadUrl(str);
            }
        });
    }

    public void loadDownloadspage() {
        new DownloadsPage().getDownloadsPage().subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<String>() {
            public void onItem(String str) {
                Preconditions.checkNonNull(str);
                LightningView.this.loadUrl(str);
            }
        });
    }

    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0184, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void initializePreferences(android.content.Context r7) {
        /*
            r6 = this;
            monitor-enter(r6)
            android.webkit.WebView r0 = r6.mWebView     // Catch:{ all -> 0x0185 }
            if (r0 != 0) goto L_0x0007
            monitor-exit(r6)
            return
        L_0x0007:
            android.webkit.WebView r0 = r6.mWebView     // Catch:{ all -> 0x0185 }
            android.webkit.WebSettings r0 = r0.getSettings()     // Catch:{ all -> 0x0185 }
            acr.browser.lightning.preference.PreferenceManager r1 = r6.mPreferences     // Catch:{ all -> 0x0185 }
            boolean r1 = r1.getDoNotTrackEnabled()     // Catch:{ all -> 0x0185 }
            if (r1 == 0) goto L_0x001f
            java.util.Map<java.lang.String, java.lang.String> r1 = r6.mRequestHeaders     // Catch:{ all -> 0x0185 }
            java.lang.String r2 = "DNT"
            java.lang.String r3 = "1"
            r1.put(r2, r3)     // Catch:{ all -> 0x0185 }
            goto L_0x0026
        L_0x001f:
            java.util.Map<java.lang.String, java.lang.String> r1 = r6.mRequestHeaders     // Catch:{ all -> 0x0185 }
            java.lang.String r2 = "DNT"
            r1.remove(r2)     // Catch:{ all -> 0x0185 }
        L_0x0026:
            acr.browser.lightning.preference.PreferenceManager r1 = r6.mPreferences     // Catch:{ all -> 0x0185 }
            boolean r1 = r1.getRemoveIdentifyingHeadersEnabled()     // Catch:{ all -> 0x0185 }
            if (r1 == 0) goto L_0x0041
            java.util.Map<java.lang.String, java.lang.String> r1 = r6.mRequestHeaders     // Catch:{ all -> 0x0185 }
            java.lang.String r2 = "X-Requested-With"
            java.lang.String r3 = ""
            r1.put(r2, r3)     // Catch:{ all -> 0x0185 }
            java.util.Map<java.lang.String, java.lang.String> r1 = r6.mRequestHeaders     // Catch:{ all -> 0x0185 }
            java.lang.String r2 = "X-Wap-Profile"
            java.lang.String r3 = ""
            r1.put(r2, r3)     // Catch:{ all -> 0x0185 }
            goto L_0x004f
        L_0x0041:
            java.util.Map<java.lang.String, java.lang.String> r1 = r6.mRequestHeaders     // Catch:{ all -> 0x0185 }
            java.lang.String r2 = "X-Requested-With"
            r1.remove(r2)     // Catch:{ all -> 0x0185 }
            java.util.Map<java.lang.String, java.lang.String> r1 = r6.mRequestHeaders     // Catch:{ all -> 0x0185 }
            java.lang.String r2 = "X-Wap-Profile"
            r1.remove(r2)     // Catch:{ all -> 0x0185 }
        L_0x004f:
            acr.browser.lightning.preference.PreferenceManager r1 = r6.mPreferences     // Catch:{ all -> 0x0185 }
            java.lang.String r1 = r1.getTextEncoding()     // Catch:{ all -> 0x0185 }
            r0.setDefaultTextEncodingName(r1)     // Catch:{ all -> 0x0185 }
            acr.browser.lightning.preference.PreferenceManager r1 = r6.mPreferences     // Catch:{ all -> 0x0185 }
            java.lang.String r1 = r1.getHomepage()     // Catch:{ all -> 0x0185 }
            sHomepage = r1     // Catch:{ all -> 0x0185 }
            acr.browser.lightning.preference.PreferenceManager r1 = r6.mPreferences     // Catch:{ all -> 0x0185 }
            int r1 = r1.getRenderingMode()     // Catch:{ all -> 0x0185 }
            r6.setColorMode(r1)     // Catch:{ all -> 0x0185 }
            boolean r1 = r6.mIsIncognitoTab     // Catch:{ all -> 0x0185 }
            r2 = 0
            if (r1 != 0) goto L_0x0078
            acr.browser.lightning.preference.PreferenceManager r1 = r6.mPreferences     // Catch:{ all -> 0x0185 }
            boolean r1 = r1.getLocationEnabled()     // Catch:{ all -> 0x0185 }
            r0.setGeolocationEnabled(r1)     // Catch:{ all -> 0x0185 }
            goto L_0x007b
        L_0x0078:
            r0.setGeolocationEnabled(r2)     // Catch:{ all -> 0x0185 }
        L_0x007b:
            int r1 = API     // Catch:{ all -> 0x0185 }
            r3 = 2
            r4 = 19
            r5 = 1
            if (r1 >= r4) goto L_0x00a1
            acr.browser.lightning.preference.PreferenceManager r1 = r6.mPreferences     // Catch:{ all -> 0x0185 }
            int r1 = r1.getFlashSupport()     // Catch:{ all -> 0x0185 }
            if (r1 == 0) goto L_0x009c
            if (r1 == r5) goto L_0x0096
            if (r1 == r3) goto L_0x0090
            goto L_0x00a1
        L_0x0090:
            android.webkit.WebSettings$PluginState r1 = android.webkit.WebSettings.PluginState.ON     // Catch:{ all -> 0x0185 }
            r0.setPluginState(r1)     // Catch:{ all -> 0x0185 }
            goto L_0x00a1
        L_0x0096:
            android.webkit.WebSettings$PluginState r1 = android.webkit.WebSettings.PluginState.ON_DEMAND     // Catch:{ all -> 0x0185 }
            r0.setPluginState(r1)     // Catch:{ all -> 0x0185 }
            goto L_0x00a1
        L_0x009c:
            android.webkit.WebSettings$PluginState r1 = android.webkit.WebSettings.PluginState.OFF     // Catch:{ all -> 0x0185 }
            r0.setPluginState(r1)     // Catch:{ all -> 0x0185 }
        L_0x00a1:
            acr.browser.lightning.preference.PreferenceManager r1 = r6.mPreferences     // Catch:{ all -> 0x0185 }
            int r1 = r1.getUserAgentChoice()     // Catch:{ all -> 0x0185 }
            r6.setUserAgent(r7, r1)     // Catch:{ all -> 0x0185 }
            acr.browser.lightning.preference.PreferenceManager r7 = r6.mPreferences     // Catch:{ all -> 0x0185 }
            boolean r7 = r7.getSavePasswordsEnabled()     // Catch:{ all -> 0x0185 }
            r1 = 18
            if (r7 == 0) goto L_0x00c3
            boolean r7 = r6.mIsIncognitoTab     // Catch:{ all -> 0x0185 }
            if (r7 != 0) goto L_0x00c3
            int r7 = API     // Catch:{ all -> 0x0185 }
            if (r7 >= r1) goto L_0x00bf
            r0.setSavePassword(r5)     // Catch:{ all -> 0x0185 }
        L_0x00bf:
            r0.setSaveFormData(r5)     // Catch:{ all -> 0x0185 }
            goto L_0x00cd
        L_0x00c3:
            int r7 = API     // Catch:{ all -> 0x0185 }
            if (r7 >= r1) goto L_0x00ca
            r0.setSavePassword(r2)     // Catch:{ all -> 0x0185 }
        L_0x00ca:
            r0.setSaveFormData(r2)     // Catch:{ all -> 0x0185 }
        L_0x00cd:
            acr.browser.lightning.preference.PreferenceManager r7 = r6.mPreferences     // Catch:{ all -> 0x0185 }
            boolean r7 = r7.getJavaScriptEnabled()     // Catch:{ all -> 0x0185 }
            if (r7 == 0) goto L_0x00dc
            r0.setJavaScriptEnabled(r5)     // Catch:{ all -> 0x0185 }
            r0.setJavaScriptCanOpenWindowsAutomatically(r5)     // Catch:{ all -> 0x0185 }
            goto L_0x00e2
        L_0x00dc:
            r0.setJavaScriptEnabled(r2)     // Catch:{ all -> 0x0185 }
            r0.setJavaScriptCanOpenWindowsAutomatically(r2)     // Catch:{ all -> 0x0185 }
        L_0x00e2:
            acr.browser.lightning.preference.PreferenceManager r7 = r6.mPreferences     // Catch:{ all -> 0x0185 }
            boolean r7 = r7.getTextReflowEnabled()     // Catch:{ all -> 0x0185 }
            if (r7 == 0) goto L_0x0101
            android.webkit.WebSettings$LayoutAlgorithm r7 = android.webkit.WebSettings.LayoutAlgorithm.NARROW_COLUMNS     // Catch:{ all -> 0x0185 }
            r0.setLayoutAlgorithm(r7)     // Catch:{ all -> 0x0185 }
            int r7 = API     // Catch:{ all -> 0x0185 }
            if (r7 < r4) goto L_0x0106
            android.webkit.WebSettings$LayoutAlgorithm r7 = android.webkit.WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING     // Catch:{ Exception -> 0x00f9 }
            r0.setLayoutAlgorithm(r7)     // Catch:{ Exception -> 0x00f9 }
            goto L_0x0106
        L_0x00f9:
            java.lang.String r7 = "LightningView"
            java.lang.String r1 = "Problem setting LayoutAlgorithm to TEXT_AUTOSIZING"
            android.util.Log.e(r7, r1)     // Catch:{ all -> 0x0185 }
            goto L_0x0106
        L_0x0101:
            android.webkit.WebSettings$LayoutAlgorithm r7 = android.webkit.WebSettings.LayoutAlgorithm.NORMAL     // Catch:{ all -> 0x0185 }
            r0.setLayoutAlgorithm(r7)     // Catch:{ all -> 0x0185 }
        L_0x0106:
            acr.browser.lightning.preference.PreferenceManager r7 = r6.mPreferences     // Catch:{ all -> 0x0185 }
            boolean r7 = r7.getBlockImagesEnabled()     // Catch:{ all -> 0x0185 }
            r0.setBlockNetworkImage(r7)     // Catch:{ all -> 0x0185 }
            boolean r7 = r6.mIsIncognitoTab     // Catch:{ all -> 0x0185 }
            if (r7 != 0) goto L_0x011d
            acr.browser.lightning.preference.PreferenceManager r7 = r6.mPreferences     // Catch:{ all -> 0x0185 }
            boolean r7 = r7.getPopupsEnabled()     // Catch:{ all -> 0x0185 }
            r0.setSupportMultipleWindows(r7)     // Catch:{ all -> 0x0185 }
            goto L_0x0120
        L_0x011d:
            r0.setSupportMultipleWindows(r2)     // Catch:{ all -> 0x0185 }
        L_0x0120:
            acr.browser.lightning.preference.PreferenceManager r7 = r6.mPreferences     // Catch:{ all -> 0x0185 }
            boolean r7 = r7.getUseWideViewportEnabled()     // Catch:{ all -> 0x0185 }
            r0.setUseWideViewPort(r7)     // Catch:{ all -> 0x0185 }
            acr.browser.lightning.preference.PreferenceManager r7 = r6.mPreferences     // Catch:{ all -> 0x0185 }
            boolean r7 = r7.getOverviewModeEnabled()     // Catch:{ all -> 0x0185 }
            r0.setLoadWithOverviewMode(r7)     // Catch:{ all -> 0x0185 }
            acr.browser.lightning.preference.PreferenceManager r7 = r6.mPreferences     // Catch:{ all -> 0x0185 }
            int r7 = r7.getTextSize()     // Catch:{ all -> 0x0185 }
            if (r7 == 0) goto L_0x0166
            if (r7 == r5) goto L_0x0160
            if (r7 == r3) goto L_0x015a
            r1 = 3
            if (r7 == r1) goto L_0x0154
            r1 = 4
            if (r7 == r1) goto L_0x014e
            r1 = 5
            if (r7 == r1) goto L_0x0148
            goto L_0x016b
        L_0x0148:
            r7 = 50
            r0.setTextZoom(r7)     // Catch:{ all -> 0x0185 }
            goto L_0x016b
        L_0x014e:
            r7 = 75
            r0.setTextZoom(r7)     // Catch:{ all -> 0x0185 }
            goto L_0x016b
        L_0x0154:
            r7 = 100
            r0.setTextZoom(r7)     // Catch:{ all -> 0x0185 }
            goto L_0x016b
        L_0x015a:
            r7 = 125(0x7d, float:1.75E-43)
            r0.setTextZoom(r7)     // Catch:{ all -> 0x0185 }
            goto L_0x016b
        L_0x0160:
            r7 = 150(0x96, float:2.1E-43)
            r0.setTextZoom(r7)     // Catch:{ all -> 0x0185 }
            goto L_0x016b
        L_0x0166:
            r7 = 200(0xc8, float:2.8E-43)
            r0.setTextZoom(r7)     // Catch:{ all -> 0x0185 }
        L_0x016b:
            int r7 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0185 }
            r0 = 21
            if (r7 < r0) goto L_0x0183
            android.webkit.CookieManager r7 = android.webkit.CookieManager.getInstance()     // Catch:{ all -> 0x0185 }
            android.webkit.WebView r0 = r6.mWebView     // Catch:{ all -> 0x0185 }
            acr.browser.lightning.preference.PreferenceManager r1 = r6.mPreferences     // Catch:{ all -> 0x0185 }
            boolean r1 = r1.getBlockThirdPartyCookiesEnabled()     // Catch:{ all -> 0x0185 }
            if (r1 != 0) goto L_0x0180
            r2 = 1
        L_0x0180:
            r7.setAcceptThirdPartyCookies(r0, r2)     // Catch:{ all -> 0x0185 }
        L_0x0183:
            monitor-exit(r6)
            return
        L_0x0185:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.view.LightningView.initializePreferences(android.content.Context):void");
    }

    private void initializeSettings() {
        WebView webView = this.mWebView;
        if (webView != null) {
            final WebSettings settings = webView.getSettings();
            if (API < 18) {
                settings.setAppCacheMaxSize(Long.MAX_VALUE);
            }
            if (API < 17) {
                settings.setEnableSmoothTransition(true);
            }
            if (API > 16) {
                settings.setMediaPlaybackRequiresUserGesture(true);
            }
            if (API >= 21 && !this.mIsIncognitoTab) {
                settings.setMixedContentMode(2);
            } else if (API >= 21) {
                settings.setMixedContentMode(1);
            }
            if (!this.mIsIncognitoTab) {
                settings.setDomStorageEnabled(true);
                settings.setAppCacheEnabled(true);
                settings.setCacheMode(-1);
                settings.setDatabaseEnabled(true);
            } else {
                settings.setDomStorageEnabled(false);
                settings.setAppCacheEnabled(false);
                settings.setDatabaseEnabled(false);
                settings.setCacheMode(2);
            }
            settings.setSupportZoom(true);
            settings.setBuiltInZoomControls(true);
            settings.setDisplayZoomControls(false);
            settings.setAllowContentAccess(true);
            settings.setAllowFileAccess(true);
            if (API >= 16) {
                settings.setAllowFileAccessFromFileURLs(false);
                settings.setAllowUniversalAccessFromFileURLs(false);
            }
            getPathObservable("appcache").subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<File>() {
                public void onItem(File file) {
                    Preconditions.checkNonNull(file);
                    settings.setAppCachePath(file.getPath());
                }
            });
            if (Build.VERSION.SDK_INT < 24) {
                getPathObservable("geolocation").subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<File>() {
                    public void onItem(File file) {
                        Preconditions.checkNonNull(file);
                        settings.setGeolocationDatabasePath(file.getPath());
                    }
                });
            }
            getPathObservable("databases").subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<File>() {
                public void onComplete() {
                }

                public void onItem(File file) {
                    if (LightningView.API < 19) {
                        Preconditions.checkNonNull(file);
                        settings.setDatabasePath(file.getPath());
                    }
                }
            });
        }
    }

    private Single<File> getPathObservable(final String str) {
        return Single.create(new SingleAction<File>() {
            public void onSubscribe(SingleSubscriber<File> singleSubscriber) {
                singleSubscriber.onItem(LightningView.this.mActivity.getDir(str, 0));
                singleSubscriber.onComplete();
            }
        });
    }

    public LightningViewTitle getTitleInfo() {
        return this.mTitle;
    }

    public boolean isIncognito() {
        return this.mIsIncognitoTab;
    }

    public void toggleDesktopUA(Context context) {
        WebView webView = this.mWebView;
        if (webView != null) {
            if (!this.mToggleDesktop) {
                webView.getSettings().setUserAgentString(Constants.DESKTOP_USER_AGENT);
            } else {
                setUserAgent(context, this.mPreferences.getUserAgentChoice());
            }
            this.mToggleDesktop = !this.mToggleDesktop;
        }
    }

    private void setUserAgent(Context context, int i) {
        WebView webView = this.mWebView;
        if (webView != null) {
            WebSettings settings = webView.getSettings();
            if (i != 1) {
                if (i == 2) {
                    settings.setUserAgentString(Constants.DESKTOP_USER_AGENT);
                } else if (i == 3) {
                    settings.setUserAgentString(Constants.MOBILE_USER_AGENT);
                } else if (i == 4) {
                    String userAgentString = this.mPreferences.getUserAgentString(sDefaultUserAgent);
                    if (userAgentString == null || userAgentString.isEmpty()) {
                        userAgentString = StringUtils.SPACE;
                    }
                    settings.setUserAgentString(userAgentString);
                }
            } else if (API >= 17) {
                settings.setUserAgentString(WebSettings.getDefaultUserAgent(context));
            } else {
                settings.setUserAgentString(sDefaultUserAgent);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public Map<String, String> getRequestHeaders() {
        return this.mRequestHeaders;
    }

    public boolean isShown() {
        WebView webView = this.mWebView;
        return webView != null && webView.isShown();
    }

    public synchronized void onPause() {
        if (this.mWebView != null) {
            this.mWebView.onPause();
            Log.d(TAG, "WebView onPause: " + this.mWebView.getId());
        }
    }

    public synchronized void onResume() {
        if (this.mWebView != null) {
            this.mWebView.onResume();
            Log.d(TAG, "WebView onResume: " + this.mWebView.getId());
        }
    }

    @Deprecated
    public synchronized void freeMemory() {
        if (this.mWebView != null && Build.VERSION.SDK_INT < 19) {
            this.mWebView.freeMemory();
        }
    }

    public void setIsForegroundTab(boolean z) {
        this.mIsForegroundTab = z;
        this.mUIController.tabChanged(this);
    }

    public boolean isForegroundTab() {
        return this.mIsForegroundTab;
    }

    public int getProgress() {
        WebView webView = this.mWebView;
        if (webView != null) {
            return webView.getProgress();
        }
        return 100;
    }

    public synchronized void stopLoading() {
        if (this.mWebView != null) {
            this.mWebView.stopLoading();
        }
    }

    private void setHardwareRendering() {
        WebView webView = this.mWebView;
        if (webView != null) {
            webView.setLayerType(2, this.mPaint);
        }
    }

    private void setNormalRendering() {
        WebView webView = this.mWebView;
        if (webView != null) {
            webView.setLayerType(0, (Paint) null);
        }
    }

    public void setSoftwareRendering() {
        WebView webView = this.mWebView;
        if (webView != null) {
            webView.setLayerType(1, (Paint) null);
        }
    }

    private void setColorMode(int i) {
        this.mInvertPage = false;
        if (i == 0) {
            this.mPaint.setColorFilter((ColorFilter) null);
            setNormalRendering();
            this.mInvertPage = false;
        } else if (i == 1) {
            this.mPaint.setColorFilter(new ColorMatrixColorFilter(sNegativeColorArray));
            setHardwareRendering();
            this.mInvertPage = true;
        } else if (i == 2) {
            ColorMatrix colorMatrix = new ColorMatrix();
            colorMatrix.setSaturation(0.0f);
            this.mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
            setHardwareRendering();
        } else if (i == 3) {
            ColorMatrix colorMatrix2 = new ColorMatrix();
            colorMatrix2.set(sNegativeColorArray);
            ColorMatrix colorMatrix3 = new ColorMatrix();
            colorMatrix3.setSaturation(0.0f);
            ColorMatrix colorMatrix4 = new ColorMatrix();
            colorMatrix4.setConcat(colorMatrix2, colorMatrix3);
            this.mPaint.setColorFilter(new ColorMatrixColorFilter(colorMatrix4));
            setHardwareRendering();
            this.mInvertPage = true;
        } else if (i == 4) {
            this.mPaint.setColorFilter(new ColorMatrixColorFilter(sIncreaseContrastColorArray));
            setHardwareRendering();
        }
    }

    public synchronized void pauseTimers() {
    }

    public synchronized void resumeTimers() {
        if (this.mWebView != null) {
            this.mWebView.resumeTimers();
            Log.d(TAG, "Resuming JS timers");
        }
    }

    public void requestFocus() {
        WebView webView = this.mWebView;
        if (webView != null && !webView.hasFocus()) {
            this.mWebView.requestFocus();
        }
    }

    public void setVisibility(int i) {
        WebView webView = this.mWebView;
        if (webView != null) {
            webView.setVisibility(i);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0017, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void reload() {
        /*
            r2 = this;
            monitor-enter(r2)
            acr.browser.lightning.utils.ProxyUtils r0 = r2.mProxyUtils     // Catch:{ all -> 0x0018 }
            androidx.fragment.app.FragmentActivity r1 = r2.mActivity     // Catch:{ all -> 0x0018 }
            boolean r0 = r0.isProxyReady(r1)     // Catch:{ all -> 0x0018 }
            if (r0 != 0) goto L_0x000d
            monitor-exit(r2)
            return
        L_0x000d:
            android.webkit.WebView r0 = r2.mWebView     // Catch:{ all -> 0x0018 }
            if (r0 == 0) goto L_0x0016
            android.webkit.WebView r0 = r2.mWebView     // Catch:{ all -> 0x0018 }
            r0.reload()     // Catch:{ all -> 0x0018 }
        L_0x0016:
            monitor-exit(r2)
            return
        L_0x0018:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.view.LightningView.reload():void");
    }

    public synchronized void find(String str) {
        if (this.mWebView != null) {
            if (API >= 17) {
                this.mWebView.findAllAsync(str);
            } else {
                this.mWebView.findAll(str);
            }
        }
    }

    public synchronized void onDestroy() {
        if (this.mWebView != null) {
            ViewGroup viewGroup = (ViewGroup) this.mWebView.getParent();
            if (viewGroup != null) {
                Log.e(TAG, "WebView was not detached from window before onDestroy");
                viewGroup.removeView(this.mWebView);
            }
            this.mWebView.stopLoading();
            this.mWebView.onPause();
            this.mWebView.clearHistory();
            this.mWebView.setVisibility(8);
            this.mWebView.removeAllViews();
            this.mWebView.destroyDrawingCache();
            if (Build.VERSION.SDK_INT >= 18) {
                this.mWebView.destroy();
            }
            this.mWebView = null;
        }
    }

    public synchronized void goBack() {
        if (this.mWebView != null) {
            this.mWebView.goBack();
        }
    }

    public synchronized void goForward() {
        if (this.mWebView != null) {
            this.mWebView.goForward();
        }
    }

    private String getUserAgent() {
        WebView webView = this.mWebView;
        return webView != null ? webView.getSettings().getUserAgentString() : "";
    }

    public class JavaScriptHandler {
        Context mContext;

        @JavascriptInterface
        public void downloadVideoByUrl(String[] strArr) {
        }

        public JavaScriptHandler(Context context) {
            this.mContext = context;
        }
    }

    public synchronized void findNext() {
        if (this.mWebView != null) {
            this.mWebView.findNext(true);
        }
    }

    public synchronized void findPrevious() {
        if (this.mWebView != null) {
            this.mWebView.findNext(false);
        }
    }

    public synchronized void clearFindMatches() {
        if (this.mWebView != null) {
            this.mWebView.clearMatches();
        }
    }

    public boolean getInvertePage() {
        return this.mInvertPage;
    }

    /* access modifiers changed from: private */
    public void longClickPage(String str) {
        WebView webView = this.mWebView;
        if (webView != null) {
            WebView.HitTestResult hitTestResult = webView.getHitTestResult();
            String url2 = this.mWebView.getUrl();
            if (url2 == null || !UrlUtils.isSpecialUrl(url2)) {
                if (str != null) {
                    if (hitTestResult == null) {
                        this.mBookmarksDialogBuilder.showLongPressLinkDialog(this.mActivity, this.mUIController, str);
                    } else if (hitTestResult.getType() == 8 || hitTestResult.getType() == 5) {
                        this.mBookmarksDialogBuilder.showLongPressImageDialog(this.mActivity, this.mUIController, str, getUserAgent());
                    } else {
                        this.mBookmarksDialogBuilder.showLongPressLinkDialog(this.mActivity, this.mUIController, str);
                    }
                } else if (hitTestResult != null && hitTestResult.getExtra() != null) {
                    String extra = hitTestResult.getExtra();
                    if (hitTestResult.getType() == 8 || hitTestResult.getType() == 5) {
                        this.mBookmarksDialogBuilder.showLongPressImageDialog(this.mActivity, this.mUIController, extra, getUserAgent());
                    } else {
                        this.mBookmarksDialogBuilder.showLongPressLinkDialog(this.mActivity, this.mUIController, extra);
                    }
                }
            } else if (url2.endsWith(HistoryPage.FILENAME)) {
                if (str != null) {
                    this.mBookmarksDialogBuilder.showLongPressedHistoryLinkDialog(this.mActivity, this.mUIController, str);
                } else if (hitTestResult != null && hitTestResult.getExtra() != null) {
                    this.mBookmarksDialogBuilder.showLongPressedHistoryLinkDialog(this.mActivity, this.mUIController, hitTestResult.getExtra());
                }
            } else if (url2.endsWith(BookmarkPage.FILENAME)) {
                if (str != null) {
                    this.mBookmarksDialogBuilder.showLongPressedDialogForBookmarkUrl((Activity) this.mActivity, this.mUIController, str);
                } else if (hitTestResult != null && hitTestResult.getExtra() != null) {
                    this.mBookmarksDialogBuilder.showLongPressedDialogForBookmarkUrl((Activity) this.mActivity, this.mUIController, hitTestResult.getExtra());
                }
            } else if (!url2.endsWith(DownloadsPage.FILENAME)) {
            } else {
                if (str != null) {
                    this.mBookmarksDialogBuilder.showLongPressedDialogForDownloadUrl(this.mActivity, this.mUIController, str);
                } else if (hitTestResult != null && hitTestResult.getExtra() != null) {
                    this.mBookmarksDialogBuilder.showLongPressedDialogForDownloadUrl(this.mActivity, this.mUIController, hitTestResult.getExtra());
                }
            }
        }
    }

    public boolean canGoBack() {
        WebView webView = this.mWebView;
        return webView != null && webView.canGoBack();
    }

    public boolean canGoForward() {
        WebView webView = this.mWebView;
        return webView != null && webView.canGoForward();
    }

    public synchronized WebView getWebView() {
        return this.mWebView;
    }

    public synchronized View getView() {
        return this.container;
    }

    public Bitmap getFavicon() {
        return this.mTitle.getFavicon(this.mUIController.getThemeNum());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void loadUrl(java.lang.String r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            r2.url = r3     // Catch:{ all -> 0x001c }
            acr.browser.lightning.utils.ProxyUtils r0 = r2.mProxyUtils     // Catch:{ all -> 0x001c }
            androidx.fragment.app.FragmentActivity r1 = r2.mActivity     // Catch:{ all -> 0x001c }
            boolean r0 = r0.isProxyReady(r1)     // Catch:{ all -> 0x001c }
            if (r0 != 0) goto L_0x000f
            monitor-exit(r2)
            return
        L_0x000f:
            android.webkit.WebView r0 = r2.mWebView     // Catch:{ all -> 0x001c }
            if (r0 == 0) goto L_0x001a
            android.webkit.WebView r0 = r2.mWebView     // Catch:{ all -> 0x001c }
            java.util.Map<java.lang.String, java.lang.String> r1 = r2.mRequestHeaders     // Catch:{ all -> 0x001c }
            r0.loadUrl(r3, r1)     // Catch:{ all -> 0x001c }
        L_0x001a:
            monitor-exit(r2)
            return
        L_0x001c:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.view.LightningView.loadUrl(java.lang.String):void");
    }

    public String getTitle() {
        return this.mTitle.getTitle();
    }

    public String getUrl() {
        WebView webView = this.mWebView;
        return (webView == null || webView.getUrl() == null) ? "" : this.mWebView.getUrl();
    }

    private class TouchListener implements View.OnTouchListener {
        int mAction;
        float mLocation;

        /* renamed from: mY */
        float f4058mY;

        private TouchListener() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (view == null) {
                return false;
            }
            if (!view.hasFocus()) {
                view.requestFocus();
            }
            this.mAction = motionEvent.getAction();
            float y = motionEvent.getY();
            this.f4058mY = y;
            int i = this.mAction;
            if (i == 0) {
                this.mLocation = y;
            } else if (i == 1) {
                float f = y - this.mLocation;
                if (f > ((float) LightningView.SCROLL_UP_THRESHOLD) && view.getScrollY() < LightningView.SCROLL_UP_THRESHOLD) {
                    LightningView.this.mUIController.showActionBar();
                } else if (f < ((float) (-LightningView.SCROLL_UP_THRESHOLD))) {
                    LightningView.this.mUIController.hideActionBar();
                }
                this.mLocation = 0.0f;
            }
            LightningView.this.mGestureDetector.onTouchEvent(motionEvent);
            return false;
        }
    }

    private class CustomGestureListener extends GestureDetector.SimpleOnGestureListener {
        private boolean mCanTriggerLongPress;

        private CustomGestureListener() {
            this.mCanTriggerLongPress = true;
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            int access$700 = (int) ((100.0f * f2) / LightningView.sMaxFling);
            if (access$700 < -10) {
                LightningView.this.mUIController.hideActionBar();
            } else if (access$700 > 15) {
                LightningView.this.mUIController.showActionBar();
            }
            return super.onFling(motionEvent, motionEvent2, f, f2);
        }

        public void onLongPress(MotionEvent motionEvent) {
            Message obtainMessage;
            if (this.mCanTriggerLongPress && (obtainMessage = LightningView.this.mWebViewHandler.obtainMessage()) != null) {
                obtainMessage.setTarget(LightningView.this.mWebViewHandler);
                if (LightningView.this.mWebView != null) {
                    LightningView.this.mWebView.requestFocusNodeHref(obtainMessage);
                }
            }
        }

        public boolean onDoubleTapEvent(MotionEvent motionEvent) {
            this.mCanTriggerLongPress = false;
            return false;
        }

        public void onShowPress(MotionEvent motionEvent) {
            this.mCanTriggerLongPress = true;
        }
    }

    private static class WebViewHandler extends Handler {
        private final WeakReference<LightningView> mReference;

        WebViewHandler(LightningView lightningView) {
            this.mReference = new WeakReference<>(lightningView);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            String string = message.getData().getString("url");
            LightningView lightningView = (LightningView) this.mReference.get();
            if (lightningView != null) {
                lightningView.longClickPage(string);
            }
        }
    }
}

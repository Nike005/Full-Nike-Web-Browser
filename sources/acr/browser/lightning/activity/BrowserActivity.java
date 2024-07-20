package acr.browser.lightning.activity;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.browser.BookmarksView;
import acr.browser.lightning.browser.BrowserPresenter;
import acr.browser.lightning.browser.BrowserView;
import acr.browser.lightning.browser.TabsView;
import acr.browser.lightning.constant.BookmarkPage;
import acr.browser.lightning.constant.Constants;
import acr.browser.lightning.constant.DownloadsPage;
import acr.browser.lightning.constant.HistoryPage;
import acr.browser.lightning.controller.UIController;
import acr.browser.lightning.database.HistoryItem;
import acr.browser.lightning.database.bookmark.BookmarkModel;
import acr.browser.lightning.database.history.HistoryModel;
import acr.browser.lightning.dialog.BrowserDialog;
import acr.browser.lightning.dialog.LightningDialogBuilder;
import acr.browser.lightning.fragment.BookmarksFragment;
import acr.browser.lightning.fragment.TabsFragment;
import acr.browser.lightning.interpolator.BezierDecelerateInterpolator;
import acr.browser.lightning.receiver.NetworkReceiver;
import acr.browser.lightning.search.SuggestionsAdapter;
import acr.browser.lightning.utils.C3245Utils;
import acr.browser.lightning.utils.DrawableUtils;
import acr.browser.lightning.utils.IntentUtils;
import acr.browser.lightning.utils.Preconditions;
import acr.browser.lightning.utils.ProxyUtils;
import acr.browser.lightning.utils.UrlUtils;
import acr.browser.lightning.utils.WebUtils;
import acr.browser.lightning.view.Handlers;
import acr.browser.lightning.view.LightningView;
import acr.browser.lightning.view.SearchView;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebIconDatabase;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.PointerIconCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.palette.graphics.Palette;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.anthonycr.bonsai.Completable;
import com.anthonycr.bonsai.CompletableOnSubscribe;
import com.anthonycr.bonsai.Schedulers;
import com.anthonycr.bonsai.SingleOnSubscribe;
import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.progress.AnimatedProgressBar;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.appsgeyser.sdk.ads.fastTrack.adapters.FastTrackBaseAdapter;
import com.appsgeyser.sdk.configuration.Constants;
import com.google.android.material.snackbar.Snackbar;
import com.onesignal.OneSignalDbContract;
import com.wnikebrow_13999769.R;
import javax.inject.Inject;

public abstract class BrowserActivity extends ThemableBrowserActivity implements BrowserView, UIController, View.OnClickListener {
    private static final int API = Build.VERSION.SDK_INT;
    private static final FrameLayout.LayoutParams COVER_SCREEN_PARAMS = new FrameLayout.LayoutParams(-1, -1);
    private static final String INTENT_PANIC_TRIGGER = "info.guardianproject.panic.action.TRIGGER";
    private static final ViewGroup.LayoutParams MATCH_PARENT = new ViewGroup.LayoutParams(-1, -1);
    private static final String NETWORK_BROADCAST_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    public static final int ONE_MINUTE_BY_MILLISECONDS = 60000;
    public static final String TAB_POSITION_BOTTOM = "bottom";
    public static final String TAB_POSITION_WIDGET = "widget";
    private static final String TAG = "BrowserActivity";
    private static final String TAG_BOOKMARK_FRAGMENT = "TAG_BOOKMARK_FRAGMENT";
    private static final String TAG_TABS_FRAGMENT = "TAG_TABS_FRAGMENT";
    private boolean backPressedBefore = false;
    private boolean firstLaunch = true;
    private ImageView mArrowImage;
    private MenuItem mBackMenuItem;
    /* access modifiers changed from: private */
    public final ColorDrawable mBackground = new ColorDrawable();
    private int mBackgroundColor;
    @Inject
    BookmarkModel mBookmarkManager;
    @Inject
    LightningDialogBuilder mBookmarksDialogBuilder;
    /* access modifiers changed from: private */
    public BookmarksView mBookmarksView;
    @BindView(2131296449)
    FrameLayout mBrowserFrame;
    private String mCameraPhotoPath;
    /* access modifiers changed from: private */
    public Drawable mClearIcon;
    /* access modifiers changed from: private */
    public int mCurrentTheme;
    /* access modifiers changed from: private */
    public int mCurrentUiColor = -16777216;
    private View mCurrentView;
    private View mCustomView;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;
    private Drawable mDeleteIcon;
    private int mDisabledIconColor;
    @BindView(2131296488)
    DrawerLayout mDrawerLayout;
    @BindView(2131296579)
    ViewGroup mDrawerLeft;
    @BindView(2131296719)
    ViewGroup mDrawerRight;
    private ValueCallback<Uri[]> mFilePathCallback;
    private MenuItem mForwardMenuItem;
    private boolean mFullScreen;
    private FrameLayout mFullscreenContainer;
    /* access modifiers changed from: private */
    public Drawable mIcon;
    private int mIconColor;
    private boolean mIsFullScreen = false;
    private boolean mIsImmersive = false;
    private long mKeyDownStartTime;
    private final Runnable mLongPressBackRunnable = new Runnable() {
        public void run() {
            LightningView currentTab = BrowserActivity.this.mTabsManager.getCurrentTab();
            BrowserActivity browserActivity = BrowserActivity.this;
            browserActivity.showCloseDialog(browserActivity.mTabsManager.positionOf(currentTab));
        }
    };
    private final NetworkReceiver mNetworkReceiver = new NetworkReceiver() {
        public void onConnectivityChange(boolean z) {
            Log.d(BrowserActivity.TAG, "Network Connected: " + z);
            BrowserActivity.this.mTabsManager.notifyConnectionStatus(z);
        }
    };
    private int mOriginalOrientation;
    /* access modifiers changed from: private */
    public BrowserPresenter mPresenter;
    @BindView(2131296710)
    AnimatedProgressBar mProgressBar;
    @Inject
    ProxyUtils mProxyUtils;
    private Drawable mRefreshIcon;
    public SearchView mSearch;
    /* access modifiers changed from: private */
    public View mSearchBackground;
    @BindView(2131296734)
    RelativeLayout mSearchBar;
    private String mSearchText;
    /* access modifiers changed from: private */
    public boolean mShowTabsInDrawer;
    /* access modifiers changed from: private */
    public SuggestionsAdapter mSuggestionsAdapter;
    private boolean mSwapBookmarksAndTabs;
    /* access modifiers changed from: private */
    public TabsManager mTabsManager;
    private TabsView mTabsView;
    private Preference mTheme;
    private String[] mThemeOptions;
    @BindView(2131296842)
    Toolbar mToolbar;
    @BindView(2131296843)
    ViewGroup mToolbarLayout;
    @BindView(2131296852)
    ViewGroup mUiLayout;
    private String mUntitledTitle;
    private ValueCallback<Uri> mUploadMessage;
    private VideoView mVideoView;
    private Bitmap mWebpageBitmap;
    private int theme;

    public abstract void closeActivity();

    public void closeEmptyTab() {
    }

    /* access modifiers changed from: package-private */
    public abstract Completable updateCookiePreference();

    public abstract void updateHistory(String str, String str2);

    static boolean isPanicTrigger(Intent intent) {
        return intent != null && INTENT_PANIC_TRIGGER.equals(intent.getAction());
    }

    private static void removeViewFromParent(View view) {
        if (view != null) {
            ViewParent parent = view.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view);
            }
        }
    }

    private static void doOnLayout(final View view, final Runnable runnable) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= 16) {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                runnable.run();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BrowserApp.getAppComponent().inject(this);
        if (BrowserApp.getConfig().getToolbarPosition().equals(TAB_POSITION_BOTTOM)) {
            setContentView((int) R.layout.activity_main_bottom_toolbar);
        } else {
            setContentView((int) R.layout.activity_main);
        }
        ButterKnife.bind((Activity) this);
        this.mTabsManager = new TabsManager();
        this.mPresenter = new BrowserPresenter(this, isIncognito());
        initialize(bundle);
    }

    private synchronized void initialize(Bundle bundle) {
        initializeToolbarHeight(getResources().getConfiguration());
        setSupportActionBar(this.mToolbar);
        ActionBar supportActionBar = getSupportActionBar();
        this.theme = this.mPreferences.getUseTheme();
        if (isIncognito()) {
            this.theme = 2;
        }
        this.mIconColor = BrowserApp.getThemeManager().getIconColor(this.theme);
        this.mDisabledIconColor = BrowserApp.getThemeManager().getDisabledIconColor(this.theme);
        boolean z = true;
        this.mShowTabsInDrawer = this.mPreferences.getShowTabsInDrawer(!isTablet());
        this.mSwapBookmarksAndTabs = this.mPreferences.getBookmarksAndTabsSwapped();
        int backgroundColor = BrowserApp.getThemeManager().getBackgroundColor(this.theme);
        this.mBackground.setColor(backgroundColor);
        this.mDrawerLeft.setLayerType(0, (Paint) null);
        this.mDrawerRight.setLayerType(0, (Paint) null);
        this.mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            public void onDrawerClosed(View view) {
            }

            public void onDrawerOpened(View view) {
            }

            public void onDrawerSlide(View view, float f) {
            }

            public void onDrawerStateChanged(int i) {
                if (i == 1) {
                    BrowserActivity.this.mDrawerLeft.setLayerType(2, (Paint) null);
                    BrowserActivity.this.mDrawerRight.setLayerType(2, (Paint) null);
                } else if (i == 0) {
                    BrowserActivity.this.mDrawerLeft.setLayerType(0, (Paint) null);
                    BrowserActivity.this.mDrawerRight.setLayerType(0, (Paint) null);
                }
            }
        });
        int i = -16777216;
        if (Build.VERSION.SDK_INT >= 21 && !this.mShowTabsInDrawer) {
            getWindow().setStatusBarColor(-16777216);
        }
        setNavigationDrawerWidth();
        this.mDrawerLayout.addDrawerListener(new DrawerLocker());
        this.mWebpageBitmap = BrowserApp.getThemeManager().getThemedBitmap(this, R.drawable.bookmark_outline, this.theme);
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        TabsFragment tabsFragment = (TabsFragment) supportFragmentManager.findFragmentByTag(TAG_TABS_FRAGMENT);
        BookmarksFragment bookmarksFragment = (BookmarksFragment) supportFragmentManager.findFragmentByTag(TAG_BOOKMARK_FRAGMENT);
        if (tabsFragment != null) {
            supportFragmentManager.beginTransaction().remove(tabsFragment).commit();
        }
        TabsFragment createTabsFragment = TabsFragment.createTabsFragment(isIncognito(), this.mShowTabsInDrawer);
        this.mTabsView = createTabsFragment;
        if (bookmarksFragment != null) {
            supportFragmentManager.beginTransaction().remove(bookmarksFragment).commit();
        }
        BookmarksFragment createFragment = BookmarksFragment.createFragment(isIncognito());
        this.mBookmarksView = createFragment;
        supportFragmentManager.executePendingTransactions();
        supportFragmentManager.beginTransaction().replace(getTabsFragmentViewId(), createTabsFragment, TAG_TABS_FRAGMENT).replace(getBookmarksFragmentViewId(), createFragment, TAG_BOOKMARK_FRAGMENT).commit();
        if (this.mShowTabsInDrawer) {
            this.mToolbarLayout.removeView(findViewById(R.id.tabs_toolbar_container));
        }
        Preconditions.checkNonNull(supportActionBar);
        supportActionBar.setDisplayShowTitleEnabled(false);
        supportActionBar.setDisplayShowHomeEnabled(false);
        supportActionBar.setDisplayShowCustomEnabled(true);
        if (this.mPreferences.getToolBarStyle().equals("transparent")) {
            supportActionBar.setCustomView((int) R.layout.toolbar_content_transparent);
        } else {
            supportActionBar.setCustomView((int) R.layout.toolbar_content);
        }
        View customView = supportActionBar.getCustomView();
        ViewGroup.LayoutParams layoutParams = customView.getLayoutParams();
        layoutParams.width = -1;
        layoutParams.height = -1;
        customView.setLayoutParams(layoutParams);
        this.mArrowImage = (ImageView) customView.findViewById(R.id.arrow);
        FrameLayout frameLayout = (FrameLayout) customView.findViewById(R.id.arrow_button);
        if (this.mShowTabsInDrawer) {
            if (this.mArrowImage.getWidth() <= 0) {
                this.mArrowImage.measure(0, 0);
            }
            updateTabNumber(0);
            Handlers.MAIN.post(new Runnable() {
                public void run() {
                    BrowserActivity.this.mDrawerLayout.setDrawerLockMode(0, BrowserActivity.this.getTabDrawer());
                }
            });
        } else {
            Handlers.MAIN.post(new Runnable() {
                public void run() {
                    BrowserActivity.this.mDrawerLayout.setDrawerLockMode(1, BrowserActivity.this.getTabDrawer());
                }
            });
            this.mArrowImage.setImageResource(R.drawable.ic_action_home);
            this.mArrowImage.setColorFilter(this.mIconColor, PorterDuff.Mode.SRC_IN);
        }
        Handlers.MAIN.post(new Runnable() {
            public void run() {
                BrowserActivity.this.mDrawerLayout.setDrawerLockMode(0, BrowserActivity.this.getBookmarkDrawer());
            }
        });
        frameLayout.setOnClickListener(this);
        this.mSearch = (SearchView) customView.findViewById(R.id.search);
        this.mSearchBackground = customView.findViewById(R.id.search_container);
        if (this.mPreferences.getToolBarStyle().equals("transparent")) {
            this.mSearchBackground.setBackgroundResource(R.drawable.card_bg_transparent);
        } else if (this.mPreferences.getToolBarStyle().equals("rounded")) {
            this.mSearchBackground.setBackgroundResource(R.drawable.card_bg_rounded);
        } else {
            this.mSearchBackground.setBackgroundResource(R.drawable.card_bg);
        }
        this.mSearchBackground.getBackground().setColorFilter(getSearchBarColor(BrowserApp.getThemeManager().getPrimaryColor(this.theme), backgroundColor), PorterDuff.Mode.SRC_IN);
        this.mSearch.setHintTextColor(-2130706433 & (this.theme > 1 ? -1 : -16777216));
        SearchView searchView = this.mSearch;
        if (this.theme > 1) {
            i = -1;
        }
        searchView.setTextColor(i);
        this.mUntitledTitle = getString(R.string.untitled);
        this.mBackgroundColor = BrowserApp.getThemeManager().getBackgroundColor(this.theme);
        this.mDeleteIcon = BrowserApp.getThemeManager().getThemedDrawable(this, R.drawable.ic_action_delete, this.theme);
        this.mRefreshIcon = BrowserApp.getThemeManager().getThemedDrawable(this, R.drawable.ic_action_refresh, this.theme);
        this.mClearIcon = BrowserApp.getThemeManager().getThemedDrawable(this, R.drawable.ic_action_delete, this.theme);
        int dpToPx = C3245Utils.dpToPx(24.0f);
        this.mDeleteIcon.setBounds(0, 0, dpToPx, dpToPx);
        this.mRefreshIcon.setBounds(0, 0, dpToPx, dpToPx);
        this.mClearIcon.setBounds(0, 0, dpToPx, dpToPx);
        this.mIcon = this.mRefreshIcon;
        SearchListenerClass searchListenerClass = new SearchListenerClass();
        this.mSearch.setCompoundDrawablePadding(C3245Utils.dpToPx(3.0f));
        this.mSearch.setCompoundDrawables((Drawable) null, (Drawable) null, this.mRefreshIcon, (Drawable) null);
        this.mSearch.setOnKeyListener(searchListenerClass);
        this.mSearch.setOnFocusChangeListener(searchListenerClass);
        this.mSearch.setOnEditorActionListener(searchListenerClass);
        this.mSearch.setOnTouchListener(searchListenerClass);
        this.mSearch.setOnPreFocusListener(searchListenerClass);
        initializeSearchSuggestions(this.mSearch);
        this.mDrawerLayout.setDrawerShadow((int) R.drawable.drawer_right_shadow, (int) GravityCompat.END);
        this.mDrawerLayout.setDrawerShadow((int) R.drawable.drawer_left_shadow, 8388611);
        if (API <= 18) {
            WebIconDatabase.getInstance().open(getDir("icons", 0).getPath());
        }
        Intent intent = bundle == null ? getIntent() : null;
        if (intent == null || (intent.getFlags() & 1048576) == 0) {
            z = false;
        }
        if (isPanicTrigger(intent)) {
            setIntent((Intent) null);
            panicClean();
        } else {
            if (z) {
                intent = null;
            }
            this.mPresenter.setupTabs(intent);
            setIntent((Intent) null);
            this.mProxyUtils.checkForProxy(this);
        }
    }

    private int getBookmarksFragmentViewId() {
        return this.mSwapBookmarksAndTabs ? R.id.left_drawer : R.id.right_drawer;
    }

    private int getTabsFragmentViewId() {
        if (this.mShowTabsInDrawer) {
            return this.mSwapBookmarksAndTabs ? R.id.right_drawer : R.id.left_drawer;
        }
        return R.id.tabs_toolbar_container;
    }

    /* access modifiers changed from: package-private */
    public void panicClean() {
        Log.d(TAG, "Closing browser");
        this.mTabsManager.newTab(this, "", false);
        this.mTabsManager.switchToTab(0);
        this.mTabsManager.clearSavedState();
        HistoryPage.deleteHistoryPage(getApplication()).subscribe();
        closeBrowser();
        System.exit(1);
    }

    private void setNavigationDrawerWidth() {
        int i;
        int dpToPx = getResources().getDisplayMetrics().widthPixels - C3245Utils.dpToPx(56.0f);
        if (isTablet()) {
            i = C3245Utils.dpToPx(320.0f);
        } else {
            i = C3245Utils.dpToPx(300.0f);
        }
        if (dpToPx > i) {
            DrawerLayout.LayoutParams layoutParams = (DrawerLayout.LayoutParams) this.mDrawerLeft.getLayoutParams();
            layoutParams.width = i;
            this.mDrawerLeft.setLayoutParams(layoutParams);
            this.mDrawerLeft.requestLayout();
            DrawerLayout.LayoutParams layoutParams2 = (DrawerLayout.LayoutParams) this.mDrawerRight.getLayoutParams();
            layoutParams2.width = i;
            this.mDrawerRight.setLayoutParams(layoutParams2);
            this.mDrawerRight.requestLayout();
            return;
        }
        DrawerLayout.LayoutParams layoutParams3 = (DrawerLayout.LayoutParams) this.mDrawerLeft.getLayoutParams();
        layoutParams3.width = dpToPx;
        this.mDrawerLeft.setLayoutParams(layoutParams3);
        this.mDrawerLeft.requestLayout();
        DrawerLayout.LayoutParams layoutParams4 = (DrawerLayout.LayoutParams) this.mDrawerRight.getLayoutParams();
        layoutParams4.width = dpToPx;
        this.mDrawerRight.setLayoutParams(layoutParams4);
        this.mDrawerRight.requestLayout();
    }

    private void initializePreferences() {
        Bitmap bitmap;
        Bitmap bitmap2;
        LightningView currentTab = this.mTabsManager.getCurrentTab();
        this.mFullScreen = this.mPreferences.getFullScreenEnabled();
        boolean colorModeEnabled = this.mPreferences.getColorModeEnabled();
        boolean z = true;
        if (this.theme <= 1) {
            z = false;
        }
        boolean z2 = colorModeEnabled & (!z);
        if (!isIncognito() && !z2 && !z && (bitmap2 = this.mWebpageBitmap) != null) {
            changeToolbarBackground(bitmap2, (Drawable) null);
        } else if (!isIncognito() && currentTab != null && !z) {
            changeToolbarBackground(currentTab.getFavicon(), (Drawable) null);
        } else if (!isIncognito() && !z && (bitmap = this.mWebpageBitmap) != null) {
            changeToolbarBackground(bitmap, (Drawable) null);
        }
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag(TAG_TABS_FRAGMENT);
        if (findFragmentByTag instanceof TabsFragment) {
            ((TabsFragment) findFragmentByTag).reinitializePreferences();
        }
        Fragment findFragmentByTag2 = supportFragmentManager.findFragmentByTag(TAG_BOOKMARK_FRAGMENT);
        if (findFragmentByTag2 instanceof BookmarksFragment) {
            ((BookmarksFragment) findFragmentByTag2).reinitializePreferences();
        }
        setFullscreen(this.mPreferences.getHideStatusBarEnabled(), false);
        switch (this.mPreferences.getSearchChoice()) {
            case 0:
                String searchUrl = this.mPreferences.getSearchUrl();
                this.mSearchText = searchUrl;
                if (!searchUrl.startsWith(Constants.HTTP) && !this.mSearchText.startsWith(Constants.HTTPS)) {
                    this.mSearchText = Constants.GOOGLE_SEARCH;
                    break;
                }
            case 1:
                this.mSearchText = Constants.GOOGLE_SEARCH;
                break;
            case 2:
                this.mSearchText = Constants.ASK_SEARCH;
                break;
            case 3:
                this.mSearchText = Constants.BING_SEARCH;
                break;
            case 4:
                this.mSearchText = Constants.YAHOO_SEARCH;
                break;
            case 5:
                this.mSearchText = Constants.STARTPAGE_SEARCH;
                break;
            case 6:
                this.mSearchText = Constants.STARTPAGE_MOBILE_SEARCH;
                break;
            case 7:
                this.mSearchText = Constants.DUCK_SEARCH;
                break;
            case 8:
                this.mSearchText = Constants.DUCK_LITE_SEARCH;
                break;
            case 9:
                this.mSearchText = Constants.BAIDU_SEARCH;
                break;
            case 10:
                this.mSearchText = Constants.YANDEX_SEARCH;
                break;
        }
        updateCookiePreference().subscribeOn(Schedulers.worker()).subscribe();
        this.mProxyUtils.updateProxySettings(this);
    }

    public void onWindowVisibleToUserAfterResume() {
        super.onWindowVisibleToUserAfterResume();
        this.mToolbarLayout.setTranslationY(0.0f);
        setWebViewTranslation((float) this.mToolbarLayout.getHeight());
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 66) {
            if (this.mSearch.hasFocus()) {
                searchTheWeb(this.mSearch.getText().toString());
            }
        } else if (i == 82 && Build.VERSION.SDK_INT <= 16 && Build.MANUFACTURER.compareTo("LGE") == 0) {
            return true;
        } else {
            if (i == 4) {
                this.mKeyDownStartTime = System.currentTimeMillis();
                Handlers.MAIN.postDelayed(this.mLongPressBackRunnable, (long) ViewConfiguration.getLongPressTimeout());
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i == 82 && Build.VERSION.SDK_INT <= 16 && Build.MANUFACTURER.compareTo("LGE") == 0) {
            openOptionsMenu();
            return true;
        }
        if (i == 4) {
            Handlers.MAIN.removeCallbacks(this.mLongPressBackRunnable);
            if (System.currentTimeMillis() - this.mKeyDownStartTime > ((long) ViewConfiguration.getLongPressTimeout())) {
                return true;
            }
        }
        return super.onKeyUp(i, keyEvent);
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        int i;
        if (keyEvent.isCtrlPressed() && keyEvent.getAction() == 0) {
            int keyCode = keyEvent.getKeyCode();
            if (keyCode == 45) {
                closeBrowser();
                return true;
            } else if (keyCode == 46) {
                LightningView currentTab = this.mTabsManager.getCurrentTab();
                if (currentTab != null) {
                    currentTab.reload();
                }
                return true;
            } else if (keyCode == 48) {
                newTab((String) null, true);
                return true;
            } else if (keyCode == 51) {
                this.mPresenter.deleteTab(this.mTabsManager.indexOfCurrentTab());
                return true;
            } else if (keyCode == 61) {
                if (keyEvent.isShiftPressed()) {
                    i = this.mTabsManager.indexOfCurrentTab() > 0 ? this.mTabsManager.indexOfCurrentTab() - 1 : this.mTabsManager.last();
                } else {
                    i = this.mTabsManager.indexOfCurrentTab() < this.mTabsManager.last() ? this.mTabsManager.indexOfCurrentTab() + 1 : 0;
                }
                this.mPresenter.tabChanged(i);
                return true;
            }
        } else if (keyEvent.getAction() == 0 && keyEvent.getKeyCode() == 84) {
            this.mSearch.requestFocus();
            this.mSearch.selectAll();
            return true;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        LightningView currentTab = this.mTabsManager.getCurrentTab();
        String str = null;
        String url = currentTab != null ? currentTab.getUrl() : null;
        switch (menuItem.getItemId()) {
            case 16908332:
                if (this.mDrawerLayout.isDrawerOpen(getBookmarkDrawer())) {
                    this.mDrawerLayout.closeDrawer(getBookmarkDrawer());
                }
                return true;
            case R.id.action_add_bookmark:
                if (url != null && !UrlUtils.isSpecialUrl(url)) {
                    addBookmark(currentTab.getTitle(), url);
                }
                return true;
            case R.id.action_add_to_homescreen:
                if (currentTab != null) {
                    HistoryItem historyItem = new HistoryItem(currentTab.getUrl(), currentTab.getTitle());
                    historyItem.setBitmap(currentTab.getFavicon());
                    C3245Utils.createShortcut(this, historyItem);
                }
                return true;
            case R.id.action_app_theme:
                appThemeChanging();
                break;
            case R.id.action_back:
                if (currentTab != null && currentTab.canGoBack()) {
                    currentTab.goBack();
                }
                return true;
            case R.id.action_bookmarks:
                openBookmarks();
                return true;
            case R.id.action_copy:
                if (url != null && !UrlUtils.isSpecialUrl(url)) {
                    ((ClipboardManager) getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("label", url));
                    C3245Utils.showSnackbar((Activity) this, (int) R.string.message_link_copied);
                }
                return true;
            case R.id.action_downloads:
                openDownloads();
                return true;
            case R.id.action_find:
                findInPage();
                return true;
            case R.id.action_forward:
                if (currentTab != null && currentTab.canGoForward()) {
                    currentTab.goForward();
                }
                return true;
            case R.id.action_history:
                openHistory();
                return true;
            case R.id.action_incognito:
                onIncognitoTabChanging();
                return true;
            case R.id.action_new_tab:
                newTab((String) null, true);
                return true;
            case R.id.action_reading_mode:
                if (url != null) {
                    Intent intent = new Intent(this, ReadingActivity.class);
                    intent.putExtra(Constants.LOAD_READING_URL, url);
                    startActivity(intent);
                }
                return true;
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            case R.id.action_share:
                IntentUtils intentUtils = new IntentUtils(this);
                if (currentTab != null) {
                    str = currentTab.getTitle();
                }
                intentUtils.shareUrl(url, str);
                return true;
            case R.id.main_menu_about_dialog:
                AppsgeyserSDK.showAboutDialog(this);
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* access modifiers changed from: private */
    public void addBookmark(String str, final String str2) {
        this.mBookmarkManager.addBookmarkIfNotExists(new HistoryItem(str2, str), true).subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<Boolean>() {
            public void onItem(Boolean bool) {
                if (Boolean.TRUE.equals(bool)) {
                    BrowserActivity.this.mSuggestionsAdapter.refreshBookmarks();
                    BrowserActivity.this.mBookmarksView.handleUpdatedUrl(str2);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void deleteBookmark(String str, final String str2) {
        this.mBookmarkManager.deleteBookmark(new HistoryItem(str2, str)).subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<Boolean>() {
            public void onItem(Boolean bool) {
                if (Boolean.TRUE.equals(bool)) {
                    BrowserActivity.this.mSuggestionsAdapter.refreshBookmarks();
                    BrowserActivity.this.mBookmarksView.handleUpdatedUrl(str2);
                }
            }
        });
    }

    private void putToolbarInRoot() {
        if (this.mToolbarLayout.getParent() != this.mUiLayout) {
            if (this.mToolbarLayout.getParent() != null) {
                ((ViewGroup) this.mToolbarLayout.getParent()).removeView(this.mToolbarLayout);
            }
            this.mUiLayout.addView(this.mToolbarLayout, 0);
            this.mUiLayout.requestLayout();
        }
        setWebViewTranslation(0.0f);
    }

    private void overlayToolbarOnWebView() {
        if (this.mToolbarLayout.getParent() != this.mBrowserFrame) {
            if (this.mToolbarLayout.getParent() != null) {
                ((ViewGroup) this.mToolbarLayout.getParent()).removeView(this.mToolbarLayout);
            }
            this.mBrowserFrame.addView(this.mToolbarLayout);
            this.mBrowserFrame.requestLayout();
        }
        setWebViewTranslation((float) this.mToolbarLayout.getHeight());
    }

    /* access modifiers changed from: private */
    public void setWebViewTranslation(float f) {
        View view;
        if (!this.mFullScreen || BrowserApp.getConfig().getToolbarPosition().equals(TAB_POSITION_BOTTOM) || (view = this.mCurrentView) == null) {
            View view2 = this.mCurrentView;
            if (view2 != null) {
                view2.setTranslationY(0.0f);
                return;
            }
            return;
        }
        view.setTranslationY(f);
    }

    private void findInPage() {
        BrowserDialog.showEditText(this, R.string.action_find, R.string.search_hint, R.string.search_hint, new BrowserDialog.EditorListener() {
            public void onClick(String str) {
                if (!TextUtils.isEmpty(str)) {
                    BrowserActivity.this.mPresenter.findInPage(str);
                    BrowserActivity.this.showFindInPageControls(str);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void showFindInPageControls(String str) {
        this.mSearchBar.setVisibility(0);
        ((TextView) findViewById(R.id.search_query)).setText('\'' + str + '\'');
        ((ImageButton) findViewById(R.id.button_next)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.button_back)).setOnClickListener(this);
        ((ImageButton) findViewById(R.id.button_quit)).setOnClickListener(this);
    }

    public TabsManager getTabModel() {
        return this.mTabsManager;
    }

    public void showCloseDialog(final int i) {
        if (i >= 0) {
            BrowserDialog.show((Activity) this, (int) R.string.dialog_title_close_browser, new BrowserDialog.Item(R.string.close_tab) {
                public void onClick() {
                    BrowserActivity.this.mPresenter.deleteTab(i);
                }
            }, new BrowserDialog.Item(R.string.close_other_tabs) {
                public void onClick() {
                    BrowserActivity.this.mPresenter.closeAllOtherTabs();
                }
            }, new BrowserDialog.Item(R.string.close_all_tabs) {
                public void onClick() {
                    BrowserActivity.this.closeAllTabs();
                }
            });
        }
    }

    public void closeAllTabs() {
        ImageView imageView = (ImageView) findViewById(R.id.content_frame_background);
        imageView.setImageDrawable(BrowserApp.getConfig().getBackground());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        removeViewFromParent(this.mCurrentView);
        performExitCleanUp();
        int size = this.mTabsManager.size();
        this.mTabsManager.shutdown();
        this.mCurrentView = null;
        for (int i = 0; i < size; i++) {
            this.mTabsView.tabRemoved(0);
            this.mTabsManager.deleteTab(0);
        }
        this.mPresenter.newTab((String) null, true);
    }

    public void notifyTabViewRemoved(int i) {
        Log.d(TAG, "Notify Tab Removed: " + i);
        this.mTabsView.tabRemoved(i);
    }

    public void notifyTabClosed(String str, View.OnClickListener onClickListener) {
        if (getTabModel().getCurrentTab() != null && getTabModel().getCurrentTab().getView() != null) {
            Snackbar make = Snackbar.make(getTabModel().getCurrentTab().getView(), (CharSequence) getResources().getString(R.string.tab_deleted, new Object[]{str}), -1);
            make.setAction(17039360, onClickListener);
            make.show();
        }
    }

    public void notifyTabViewAdded() {
        Log.d(TAG, "Notify Tab Added");
        this.mTabsView.tabAdded();
        this.mToolbar.setVisibility(0);
    }

    public void notifyTabViewChanged(int i) {
        Log.d(TAG, "Notify Tab Changed: " + i);
        this.mTabsView.tabChanged(i);
    }

    public void notifyTabViewInitialized() {
        Log.d(TAG, "Notify Tabs Initialized");
        this.mTabsView.tabsInitialized();
    }

    public void tabChanged(LightningView lightningView) {
        this.mPresenter.tabChangeOccurred(lightningView);
    }

    public void removeTabView() {
        Log.d(TAG, "Remove the tab view");
        this.mBrowserFrame.setBackgroundColor(this.mBackgroundColor);
        removeViewFromParent(this.mCurrentView);
        this.mCurrentView = null;
        Handlers.MAIN.postDelayed(new Runnable() {
            public void run() {
                BrowserActivity.this.mDrawerLayout.closeDrawers();
            }
        }, 200);
    }

    public void setTabView(LightningView lightningView) {
        View view = lightningView.getView();
        if (this.mCurrentView != view) {
            Log.d(TAG, "Setting the tab view");
            this.mBrowserFrame.setBackgroundColor(this.mBackgroundColor);
            removeViewFromParent(view);
            removeViewFromParent(this.mCurrentView);
            showActionBar();
            this.mBrowserFrame.addView(view, MATCH_PARENT);
            if (!this.mFullScreen || BrowserApp.getConfig().getToolbarPosition().equals(TAB_POSITION_BOTTOM)) {
                lightningView.setTranslationY(0.0f);
            } else {
                lightningView.setTranslationY(((float) this.mToolbarLayout.getHeight()) + this.mToolbarLayout.getTranslationY());
            }
            view.requestFocus();
            this.mCurrentView = view;
            Handlers.MAIN.postDelayed(new Runnable() {
                public void run() {
                    BrowserActivity.this.mDrawerLayout.closeDrawers();
                }
            }, 200);
        }
    }

    public void showBlockedLocalFileDialog(DialogInterface.OnClickListener onClickListener) {
        BrowserDialog.setDialogSize(this, new AlertDialog.Builder(this).setCancelable(true).setTitle((int) R.string.title_warning).setMessage((int) R.string.message_blocked_local).setNegativeButton(17039360, (DialogInterface.OnClickListener) null).setPositiveButton((int) R.string.action_open, onClickListener).show());
    }

    public void showSnackbar(int i) {
        C3245Utils.showSnackbar((Activity) this, i);
    }

    public void tabCloseClicked(int i) {
        this.mPresenter.deleteTab(i);
    }

    public void tabClicked(int i) {
        showTab(i);
    }

    public void newTabButtonClicked() {
        this.mPresenter.newTab((String) null, true);
    }

    public void newTabButtonLongClicked() {
        String savedUrl = this.mPreferences.getSavedUrl();
        if (savedUrl != null) {
            newTab(savedUrl, true);
            C3245Utils.showSnackbar((Activity) this, (int) R.string.deleted_tab);
        }
        this.mPreferences.setSavedUrl((String) null);
    }

    public void bookmarkButtonClicked() {
        LightningView currentTab = this.mTabsManager.getCurrentTab();
        final String str = null;
        final String url = currentTab != null ? currentTab.getUrl() : null;
        if (currentTab != null) {
            str = currentTab.getTitle();
        }
        if (url != null && !UrlUtils.isSpecialUrl(url)) {
            this.mBookmarkManager.isBookmark(url).subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<Boolean>() {
                public void onItem(Boolean bool) {
                    if (Boolean.TRUE.equals(bool)) {
                        BrowserActivity.this.deleteBookmark(str, url);
                    } else {
                        BrowserActivity.this.addBookmark(str, url);
                    }
                }
            });
        }
    }

    public void bookmarkItemClicked(HistoryItem historyItem) {
        this.mPresenter.loadUrlInCurrentView(historyItem.getUrl());
        Handlers.MAIN.postDelayed(new Runnable() {
            public void run() {
                BrowserActivity.this.closeDrawers((Runnable) null);
            }
        }, 150);
    }

    public void loadUrl(String str) {
        this.mPresenter.loadUrlInCurrentView(str);
    }

    public void handleHistoryChange() {
        openHistory();
    }

    private synchronized void showTab(int i) {
        this.mPresenter.tabChanged(i);
    }

    /* access modifiers changed from: package-private */
    public void handleNewIntent(Intent intent) {
        this.mPresenter.onNewIntent(intent);
    }

    public void onTrimMemory(int i) {
        if (i > 60 && Build.VERSION.SDK_INT < 19) {
            Log.d(TAG, "Low Memory, Free Memory");
            this.mPresenter.onAppLowMemory();
        }
    }

    private synchronized boolean newTab(String str, boolean z) {
        return this.mPresenter.newTab(str, z);
    }

    /* access modifiers changed from: package-private */
    public void performExitCleanUp() {
        LightningView currentTab = this.mTabsManager.getCurrentTab();
        if (this.mPreferences.getClearCacheExit() && currentTab != null && !isIncognito()) {
            WebUtils.clearCache(currentTab.getWebView());
            Log.d(TAG, "Cache Cleared");
        }
        if (this.mPreferences.getClearHistoryExitEnabled() && !isIncognito()) {
            WebUtils.clearHistory(this);
            Log.d(TAG, "History Cleared");
        }
        if (this.mPreferences.getClearCookiesExitEnabled() && !isIncognito()) {
            WebUtils.clearCookies(this);
            Log.d(TAG, "Cookies Cleared");
        }
        if (this.mPreferences.getClearWebStorageExitEnabled() && !isIncognito()) {
            WebUtils.clearWebStorage();
            Log.d(TAG, "WebStorage Cleared");
        } else if (isIncognito()) {
            WebUtils.clearWebStorage();
        }
        this.mSuggestionsAdapter.clearCache();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Log.d(TAG, "onConfigurationChanged");
        initializeToolbarHeight(configuration);
        if (this.mFullScreen && !BrowserApp.getConfig().getToolbarPosition().equals(TAB_POSITION_BOTTOM)) {
            showActionBar();
            this.mToolbarLayout.setTranslationY(0.0f);
            setWebViewTranslation((float) this.mToolbarLayout.getHeight());
        }
        supportInvalidateOptionsMenu();
    }

    private void initializeToolbarHeight(Configuration configuration) {
        int dpToPx = C3245Utils.dpToPx(56.0f);
        this.mToolbar.setLayoutParams(new LinearLayout.LayoutParams(-1, dpToPx));
        this.mToolbar.setMinimumHeight(dpToPx);
        doOnLayout(this.mToolbar, new Runnable() {
            public void run() {
                BrowserActivity browserActivity = BrowserActivity.this;
                browserActivity.setWebViewTranslation((float) browserActivity.mToolbarLayout.getHeight());
            }
        });
        this.mToolbar.requestLayout();
    }

    public void closeBrowser() {
        this.mBrowserFrame.setBackgroundColor(this.mBackgroundColor);
        removeViewFromParent(this.mCurrentView);
        performExitCleanUp();
        int size = this.mTabsManager.size();
        this.mTabsManager.shutdown();
        this.mCurrentView = null;
        for (int i = 0; i < size; i++) {
            this.mTabsView.tabRemoved(0);
        }
        finish();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00aa, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onBackPressed() {
        /*
            r3 = this;
            monitor-enter(r3)
            acr.browser.lightning.activity.TabsManager r0 = r3.mTabsManager     // Catch:{ all -> 0x00ab }
            acr.browser.lightning.view.LightningView r0 = r0.getCurrentTab()     // Catch:{ all -> 0x00ab }
            androidx.drawerlayout.widget.DrawerLayout r1 = r3.mDrawerLayout     // Catch:{ all -> 0x00ab }
            android.view.View r2 = r3.getTabDrawer()     // Catch:{ all -> 0x00ab }
            boolean r1 = r1.isDrawerOpen((android.view.View) r2)     // Catch:{ all -> 0x00ab }
            if (r1 == 0) goto L_0x001e
            androidx.drawerlayout.widget.DrawerLayout r0 = r3.mDrawerLayout     // Catch:{ all -> 0x00ab }
            android.view.View r1 = r3.getTabDrawer()     // Catch:{ all -> 0x00ab }
            r0.closeDrawer((android.view.View) r1)     // Catch:{ all -> 0x00ab }
            goto L_0x00a9
        L_0x001e:
            androidx.drawerlayout.widget.DrawerLayout r1 = r3.mDrawerLayout     // Catch:{ all -> 0x00ab }
            android.view.View r2 = r3.getBookmarkDrawer()     // Catch:{ all -> 0x00ab }
            boolean r1 = r1.isDrawerOpen((android.view.View) r2)     // Catch:{ all -> 0x00ab }
            if (r1 == 0) goto L_0x0031
            acr.browser.lightning.browser.BookmarksView r0 = r3.mBookmarksView     // Catch:{ all -> 0x00ab }
            r0.navigateBack()     // Catch:{ all -> 0x00ab }
            goto L_0x00a9
        L_0x0031:
            if (r0 == 0) goto L_0x009f
            java.lang.String r1 = "BrowserActivity"
            java.lang.String r2 = "onBackPressed"
            android.util.Log.d(r1, r2)     // Catch:{ all -> 0x00ab }
            acr.browser.lightning.activity.TabsManager r1 = r3.mTabsManager     // Catch:{ all -> 0x00ab }
            int r1 = r1.size()     // Catch:{ all -> 0x00ab }
            r2 = 1
            if (r1 != r2) goto L_0x0066
            boolean r1 = r0.isHomePage()     // Catch:{ all -> 0x00ab }
            if (r1 == 0) goto L_0x0066
            boolean r1 = r3.backPressedBefore     // Catch:{ all -> 0x00ab }
            if (r1 != 0) goto L_0x0063
            android.content.res.Resources r0 = r3.getResources()     // Catch:{ all -> 0x00ab }
            r1 = 2131755449(0x7f1001b9, float:1.9141778E38)
            java.lang.String r0 = r0.getString(r1)     // Catch:{ all -> 0x00ab }
            android.widget.Toast r0 = android.widget.Toast.makeText(r3, r0, r2)     // Catch:{ all -> 0x00ab }
            r0.show()     // Catch:{ all -> 0x00ab }
            r3.backPressedBefore = r2     // Catch:{ all -> 0x00ab }
            monitor-exit(r3)
            return
        L_0x0063:
            r3.closeBrowser()     // Catch:{ all -> 0x00ab }
        L_0x0066:
            acr.browser.lightning.view.SearchView r1 = r3.mSearch     // Catch:{ all -> 0x00ab }
            boolean r1 = r1.hasFocus()     // Catch:{ all -> 0x00ab }
            if (r1 == 0) goto L_0x0072
            r0.requestFocus()     // Catch:{ all -> 0x00ab }
            goto L_0x00a9
        L_0x0072:
            boolean r1 = r0.canGoBack()     // Catch:{ all -> 0x00ab }
            if (r1 == 0) goto L_0x0086
            boolean r1 = r0.isShown()     // Catch:{ all -> 0x00ab }
            if (r1 != 0) goto L_0x0082
            r3.onHideCustomView()     // Catch:{ all -> 0x00ab }
            goto L_0x00a9
        L_0x0082:
            r0.goBack()     // Catch:{ all -> 0x00ab }
            goto L_0x00a9
        L_0x0086:
            android.view.View r1 = r3.mCustomView     // Catch:{ all -> 0x00ab }
            if (r1 != 0) goto L_0x009b
            android.webkit.WebChromeClient$CustomViewCallback r1 = r3.mCustomViewCallback     // Catch:{ all -> 0x00ab }
            if (r1 == 0) goto L_0x008f
            goto L_0x009b
        L_0x008f:
            acr.browser.lightning.browser.BrowserPresenter r1 = r3.mPresenter     // Catch:{ all -> 0x00ab }
            acr.browser.lightning.activity.TabsManager r2 = r3.mTabsManager     // Catch:{ all -> 0x00ab }
            int r0 = r2.positionOf(r0)     // Catch:{ all -> 0x00ab }
            r1.deleteTab(r0)     // Catch:{ all -> 0x00ab }
            goto L_0x00a9
        L_0x009b:
            r3.onHideCustomView()     // Catch:{ all -> 0x00ab }
            goto L_0x00a9
        L_0x009f:
            java.lang.String r0 = "BrowserActivity"
            java.lang.String r1 = "This shouldn't happen ever"
            android.util.Log.e(r0, r1)     // Catch:{ all -> 0x00ab }
            super.onBackPressed()     // Catch:{ all -> 0x00ab }
        L_0x00a9:
            monitor-exit(r3)
            return
        L_0x00ab:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.activity.BrowserActivity.onBackPressed():void");
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
        this.mTabsManager.pauseAll();
        try {
            getApplication().unregisterReceiver(this.mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            Log.e(TAG, "Receiver was not registered", e);
        }
        if (isIncognito() && isFinishing()) {
            overridePendingTransition(R.anim.fade_in_scale, R.anim.slide_down_out);
        }
        AppsgeyserSDK.onPause(this);
    }

    /* access modifiers changed from: package-private */
    public void saveOpenTabs() {
        if (this.mPreferences.getRestoreLostTabsEnabled()) {
            this.mTabsManager.saveState();
        }
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        Handlers.MAIN.removeCallbacksAndMessages((Object) null);
        this.mPresenter.shutdown();
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.mTabsManager.shutdown();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        if (this.mSwapBookmarksAndTabs != this.mPreferences.getBookmarksAndTabsSwapped()) {
            restart();
        }
        SuggestionsAdapter suggestionsAdapter = this.mSuggestionsAdapter;
        if (suggestionsAdapter != null) {
            suggestionsAdapter.refreshPreferences();
            this.mSuggestionsAdapter.refreshBookmarks();
        }
        this.mTabsManager.resumeAll(this);
        initializePreferences();
        supportInvalidateOptionsMenu();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(NETWORK_BROADCAST_ACTION);
        getApplication().registerReceiver(this.mNetworkReceiver, intentFilter);
        if (!this.mFullScreen || BrowserApp.getConfig().getToolbarPosition().equals(TAB_POSITION_BOTTOM)) {
            putToolbarInRoot();
        } else {
            overlayToolbarOnWebView();
        }
        AppsgeyserSDK.onResume(this);
        AppsgeyserSDK.getFastTrackAdsController().setBannerViewContainer((ViewGroup) findViewById(R.id.adView), Constants.TAG_BANNER_MAIN);
    }

    /* access modifiers changed from: private */
    public void searchTheWeb(String str) {
        LightningView currentTab = this.mTabsManager.getCurrentTab();
        if (!str.isEmpty()) {
            String str2 = this.mSearchText + UrlUtils.QUERY_PLACE_HOLDER;
            String trim = str.trim();
            if (currentTab != null) {
                currentTab.stopLoading();
                this.mPresenter.loadUrlInCurrentView(UrlUtils.smartUrlFilter(trim, true, str2));
            }
        }
    }

    public void changeToolbarBackground(Bitmap bitmap, final Drawable drawable) {
        final int primaryColor = BrowserApp.getThemeManager().getPrimaryColor(this.theme);
        if (this.mCurrentUiColor == -16777216) {
            this.mCurrentUiColor = primaryColor;
        }
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette palette) {
                int vibrantColor = palette.getVibrantColor(primaryColor) | -16777216;
                if (!BrowserActivity.this.mShowTabsInDrawer || C3245Utils.isColorTooDark(vibrantColor)) {
                    vibrantColor = C3245Utils.mixTwoColors(primaryColor, vibrantColor, 0.25f);
                }
                final int i = vibrantColor;
                final Window window = BrowserActivity.this.getWindow();
                if (!BrowserActivity.this.mShowTabsInDrawer) {
                    window.setBackgroundDrawable(new ColorDrawable(-16777216));
                }
                BrowserActivity browserActivity = BrowserActivity.this;
                final int access$1400 = browserActivity.getSearchBarColor(browserActivity.mCurrentUiColor, primaryColor);
                final int access$14002 = BrowserActivity.this.getSearchBarColor(i, primaryColor);
                C29891 r1 = new Animation() {
                    /* access modifiers changed from: protected */
                    public void applyTransformation(float f, Transformation transformation) {
                        int mixColor = DrawableUtils.mixColor(f, BrowserActivity.this.mCurrentUiColor, i);
                        if (BrowserActivity.this.mShowTabsInDrawer) {
                            BrowserActivity.this.mBackground.setColor(mixColor);
                            Handlers.MAIN.post(new Runnable() {
                                public void run() {
                                    window.setBackgroundDrawable(BrowserActivity.this.mBackground);
                                }
                            });
                        } else if (drawable != null) {
                            drawable.setColorFilter(mixColor, PorterDuff.Mode.SRC_IN);
                        }
                        int unused = BrowserActivity.this.mCurrentUiColor = mixColor;
                        BrowserActivity.this.mToolbarLayout.setBackgroundColor(mixColor);
                        BrowserActivity.this.mSearchBackground.getBackground().setColorFilter(DrawableUtils.mixColor(f, access$1400, access$14002), PorterDuff.Mode.SRC_IN);
                    }
                };
                r1.setDuration(300);
                BrowserActivity.this.mToolbarLayout.startAnimation(r1);
            }
        });
    }

    /* access modifiers changed from: private */
    public int getSearchBarColor(int i, int i2) {
        if (i != i2) {
            return DrawableUtils.mixColor(0.25f, i, -1);
        }
        if (this.theme > 1) {
            return DrawableUtils.mixColor(0.25f, i2, -1);
        }
        return -1;
    }

    public int getThemeNum() {
        return this.theme;
    }

    public int getUiColor() {
        return this.mCurrentUiColor;
    }

    public void updateUrl(String str, boolean z) {
        SearchView searchView;
        if (str != null && (searchView = this.mSearch) != null && !searchView.hasFocus()) {
            LightningView currentTab = this.mTabsManager.getCurrentTab();
            this.mBookmarksView.handleUpdatedUrl(str);
            if (!z || UrlUtils.isSpecialUrl(str)) {
                if (UrlUtils.isSpecialUrl(str)) {
                    str = "";
                }
                this.mSearch.setText(str);
                return;
            }
            int urlBoxContentChoice = this.mPreferences.getUrlBoxContentChoice();
            if (urlBoxContentChoice == 0) {
                this.mSearch.setText(C3245Utils.getDomainName(str.replaceFirst(Constants.HTTP, "")));
            } else if (urlBoxContentChoice == 1) {
                this.mSearch.setText(str);
            } else if (urlBoxContentChoice == 2) {
                if (currentTab == null || currentTab.getTitle().isEmpty()) {
                    this.mSearch.setText(this.mUntitledTitle);
                } else {
                    this.mSearch.setText(currentTab.getTitle());
                }
            }
        }
    }

    public void updateTabNumber(int i) {
        ImageView imageView = this.mArrowImage;
        if (imageView != null && this.mShowTabsInDrawer) {
            imageView.setImageBitmap(DrawableUtils.getRoundedNumberImage(i, C3245Utils.dpToPx(24.0f), C3245Utils.dpToPx(24.0f), BrowserApp.getThemeManager().getIconColor(this.theme), C3245Utils.dpToPx(2.5f)));
        }
    }

    public void updateProgress(int i) {
        setIsLoading(i < 100);
        this.mProgressBar.setProgress(i);
    }

    /* access modifiers changed from: package-private */
    public void addItemToHistory(String str, String str2) {
        if (!UrlUtils.isSpecialUrl(str2)) {
            HistoryModel.visitHistoryItem(str2, str).subscribeOn(Schedulers.m6232io()).subscribe(new CompletableOnSubscribe() {
                public void onError(Throwable th) {
                    Log.e(BrowserActivity.TAG, "Exception while updating history", th);
                }
            });
        }
    }

    private void initializeSearchSuggestions(final AutoCompleteTextView autoCompleteTextView) {
        this.mSuggestionsAdapter = new SuggestionsAdapter(this, this.theme > 1, isIncognito());
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setDropDownWidth(-1);
        autoCompleteTextView.setDropDownAnchor(R.id.toolbar_layout);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                CharSequence text;
                CharSequence text2 = ((TextView) view.findViewById(R.id.url)).getText();
                String charSequence = text2 != null ? text2.toString() : null;
                if ((charSequence == null || charSequence.startsWith(BrowserActivity.this.getString(R.string.suggestion))) && (text = ((TextView) view.findViewById(R.id.title)).getText()) != null) {
                    charSequence = text.toString();
                }
                if (charSequence != null) {
                    autoCompleteTextView.setText(charSequence);
                    BrowserActivity.this.searchTheWeb(charSequence);
                    ((InputMethodManager) BrowserActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(autoCompleteTextView.getWindowToken(), 0);
                    BrowserActivity.this.mPresenter.onAutoCompleteItemPressed();
                }
            }
        });
        autoCompleteTextView.setSelectAllOnFocus(true);
        autoCompleteTextView.setAdapter(this.mSuggestionsAdapter);
    }

    public void openHistory() {
        new HistoryPage().getHistoryPage().subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<String>() {
            public void onItem(String str) {
                Preconditions.checkNonNull(str);
                LightningView currentTab = BrowserActivity.this.mTabsManager.getCurrentTab();
                if (currentTab != null) {
                    currentTab.loadUrl(str);
                }
            }
        });
    }

    public void openDownloads() {
        new DownloadsPage().getDownloadsPage().subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<String>() {
            public void onItem(String str) {
                Preconditions.checkNonNull(str);
                LightningView currentTab = BrowserActivity.this.mTabsManager.getCurrentTab();
                if (currentTab != null) {
                    currentTab.loadUrl(str);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public View getBookmarkDrawer() {
        return this.mSwapBookmarksAndTabs ? this.mDrawerLeft : this.mDrawerRight;
    }

    /* access modifiers changed from: private */
    public View getTabDrawer() {
        return this.mSwapBookmarksAndTabs ? this.mDrawerRight : this.mDrawerLeft;
    }

    private void openBookmarks() {
        if (this.mDrawerLayout.isDrawerOpen(getTabDrawer())) {
            this.mDrawerLayout.closeDrawers();
        }
        this.mDrawerLayout.openDrawer(getBookmarkDrawer());
    }

    /* access modifiers changed from: package-private */
    public void closeDrawers(final Runnable runnable) {
        if (this.mDrawerLayout.isDrawerOpen((View) this.mDrawerLeft) || this.mDrawerLayout.isDrawerOpen((View) this.mDrawerRight) || runnable == null) {
            this.mDrawerLayout.closeDrawers();
            this.mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
                public void onDrawerOpened(View view) {
                }

                public void onDrawerSlide(View view, float f) {
                }

                public void onDrawerStateChanged(int i) {
                }

                public void onDrawerClosed(View view) {
                    Runnable runnable = runnable;
                    if (runnable != null) {
                        runnable.run();
                    }
                    BrowserActivity.this.mDrawerLayout.removeDrawerListener(this);
                }
            });
            return;
        }
        runnable.run();
    }

    public void setForwardButtonEnabled(boolean z) {
        int i;
        MenuItem menuItem = this.mForwardMenuItem;
        if (menuItem != null && menuItem.getIcon() != null) {
            if (z) {
                i = this.mIconColor;
            } else {
                i = this.mDisabledIconColor;
            }
            this.mForwardMenuItem.getIcon().setColorFilter(i, PorterDuff.Mode.SRC_IN);
            MenuItem menuItem2 = this.mForwardMenuItem;
            menuItem2.setIcon(menuItem2.getIcon());
        }
    }

    public void setBackButtonEnabled(boolean z) {
        int i;
        MenuItem menuItem = this.mBackMenuItem;
        if (menuItem != null && menuItem.getIcon() != null) {
            if (z) {
                i = this.mIconColor;
            } else {
                i = this.mDisabledIconColor;
            }
            this.mBackMenuItem.getIcon().setColorFilter(i, PorterDuff.Mode.SRC_IN);
            MenuItem menuItem2 = this.mBackMenuItem;
            menuItem2.setIcon(menuItem2.getIcon());
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        this.mBackMenuItem = menu.findItem(R.id.action_back);
        this.mForwardMenuItem = menu.findItem(R.id.action_forward);
        MenuItem menuItem = this.mBackMenuItem;
        if (!(menuItem == null || menuItem.getIcon() == null)) {
            this.mBackMenuItem.getIcon().setColorFilter(this.mIconColor, PorterDuff.Mode.SRC_IN);
        }
        MenuItem menuItem2 = this.mForwardMenuItem;
        if (!(menuItem2 == null || menuItem2.getIcon() == null)) {
            this.mForwardMenuItem.getIcon().setColorFilter(this.mIconColor, PorterDuff.Mode.SRC_IN);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback) {
        this.mUploadMessage = valueCallback;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        startActivityForResult(Intent.createChooser(intent, getString(R.string.title_file_chooser)), 1);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        Uri[] uriArr;
        if (API < 21 && i == 1) {
            if (this.mUploadMessage != null) {
                this.mUploadMessage.onReceiveValue((intent == null || i2 != -1) ? null : intent.getData());
                this.mUploadMessage = null;
            } else {
                return;
            }
        }
        if (i != 1 || this.mFilePathCallback == null) {
            super.onActivityResult(i, i2, intent);
            return;
        }
        if (i2 == -1) {
            if (intent == null) {
                String str = this.mCameraPhotoPath;
                if (str != null) {
                    uriArr = new Uri[]{Uri.parse(str)};
                    this.mFilePathCallback.onReceiveValue(uriArr);
                    this.mFilePathCallback = null;
                }
            } else {
                String dataString = intent.getDataString();
                if (dataString != null) {
                    uriArr = new Uri[]{Uri.parse(dataString)};
                    this.mFilePathCallback.onReceiveValue(uriArr);
                    this.mFilePathCallback = null;
                }
            }
        }
        uriArr = null;
        this.mFilePathCallback.onReceiveValue(uriArr);
        this.mFilePathCallback = null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x006f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void showFileChooser(android.webkit.ValueCallback<android.net.Uri[]> r6) {
        /*
            r5 = this;
            android.webkit.ValueCallback<android.net.Uri[]> r0 = r5.mFilePathCallback
            r1 = 0
            if (r0 == 0) goto L_0x0008
            r0.onReceiveValue(r1)
        L_0x0008:
            r5.mFilePathCallback = r6
            android.content.Intent r6 = new android.content.Intent
            java.lang.String r0 = "android.media.action.IMAGE_CAPTURE"
            r6.<init>(r0)
            android.content.pm.PackageManager r0 = r5.getPackageManager()
            android.content.ComponentName r0 = r6.resolveActivity(r0)
            if (r0 == 0) goto L_0x0054
            java.io.File r0 = acr.browser.lightning.utils.C3245Utils.createImageFile()     // Catch:{ IOException -> 0x0029 }
            java.lang.String r2 = "PhotoPath"
            java.lang.String r3 = r5.mCameraPhotoPath     // Catch:{ IOException -> 0x0027 }
            r6.putExtra(r2, r3)     // Catch:{ IOException -> 0x0027 }
            goto L_0x0032
        L_0x0027:
            r2 = move-exception
            goto L_0x002b
        L_0x0029:
            r2 = move-exception
            r0 = r1
        L_0x002b:
            java.lang.String r3 = "BrowserActivity"
            java.lang.String r4 = "Unable to create Image File"
            android.util.Log.e(r3, r4, r2)
        L_0x0032:
            if (r0 == 0) goto L_0x0055
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "file:"
            r1.append(r2)
            java.lang.String r2 = r0.getAbsolutePath()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r5.mCameraPhotoPath = r1
            android.net.Uri r0 = android.net.Uri.fromFile(r0)
            java.lang.String r1 = "output"
            r6.putExtra(r1, r0)
        L_0x0054:
            r1 = r6
        L_0x0055:
            android.content.Intent r6 = new android.content.Intent
            java.lang.String r0 = "android.intent.action.GET_CONTENT"
            r6.<init>(r0)
            java.lang.String r0 = "android.intent.category.OPENABLE"
            r6.addCategory(r0)
            java.lang.String r0 = "*/*"
            r6.setType(r0)
            r0 = 1
            r2 = 0
            if (r1 == 0) goto L_0x006f
            android.content.Intent[] r3 = new android.content.Intent[r0]
            r3[r2] = r1
            goto L_0x0071
        L_0x006f:
            android.content.Intent[] r3 = new android.content.Intent[r2]
        L_0x0071:
            android.content.Intent r1 = new android.content.Intent
            java.lang.String r2 = "android.intent.action.CHOOSER"
            r1.<init>(r2)
            java.lang.String r2 = "android.intent.extra.INTENT"
            r1.putExtra(r2, r6)
            java.lang.String r6 = "android.intent.extra.TITLE"
            java.lang.String r2 = "Image Chooser"
            r1.putExtra(r6, r2)
            java.lang.String r6 = "android.intent.extra.INITIAL_INTENTS"
            r1.putExtra(r6, r3)
            r5.startActivityForResult(r1, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.activity.BrowserActivity.showFileChooser(android.webkit.ValueCallback):void");
    }

    public synchronized void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        int requestedOrientation = getRequestedOrientation();
        this.mOriginalOrientation = requestedOrientation;
        onShowCustomView(view, customViewCallback, requestedOrientation);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x00a7, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onShowCustomView(android.view.View r5, android.webkit.WebChromeClient.CustomViewCallback r6, int r7) {
        /*
            r4 = this;
            monitor-enter(r4)
            acr.browser.lightning.activity.TabsManager r0 = r4.mTabsManager     // Catch:{ all -> 0x00b8 }
            acr.browser.lightning.view.LightningView r0 = r0.getCurrentTab()     // Catch:{ all -> 0x00b8 }
            if (r5 == 0) goto L_0x00a8
            android.view.View r1 = r4.mCustomView     // Catch:{ all -> 0x00b8 }
            if (r1 == 0) goto L_0x000f
            goto L_0x00a8
        L_0x000f:
            r1 = 1
            r5.setKeepScreenOn(r1)     // Catch:{ SecurityException -> 0x0014 }
            goto L_0x001b
        L_0x0014:
            java.lang.String r2 = "BrowserActivity"
            java.lang.String r3 = "WebView is not allowed to keep the screen on"
            android.util.Log.e(r2, r3)     // Catch:{ all -> 0x00b8 }
        L_0x001b:
            int r2 = r4.getRequestedOrientation()     // Catch:{ all -> 0x00b8 }
            r4.mOriginalOrientation = r2     // Catch:{ all -> 0x00b8 }
            r4.mCustomViewCallback = r6     // Catch:{ all -> 0x00b8 }
            r4.mCustomView = r5     // Catch:{ all -> 0x00b8 }
            r4.setRequestedOrientation(r7)     // Catch:{ all -> 0x00b8 }
            android.view.Window r6 = r4.getWindow()     // Catch:{ all -> 0x00b8 }
            android.view.View r6 = r6.getDecorView()     // Catch:{ all -> 0x00b8 }
            android.widget.FrameLayout r6 = (android.widget.FrameLayout) r6     // Catch:{ all -> 0x00b8 }
            android.widget.FrameLayout r7 = new android.widget.FrameLayout     // Catch:{ all -> 0x00b8 }
            r7.<init>(r4)     // Catch:{ all -> 0x00b8 }
            r4.mFullscreenContainer = r7     // Catch:{ all -> 0x00b8 }
            r2 = 17170444(0x106000c, float:2.4611947E-38)
            int r2 = androidx.core.content.ContextCompat.getColor(r4, r2)     // Catch:{ all -> 0x00b8 }
            r7.setBackgroundColor(r2)     // Catch:{ all -> 0x00b8 }
            boolean r7 = r5 instanceof android.widget.FrameLayout     // Catch:{ all -> 0x00b8 }
            r2 = 0
            if (r7 == 0) goto L_0x0070
            r7 = r5
            android.widget.FrameLayout r7 = (android.widget.FrameLayout) r7     // Catch:{ all -> 0x00b8 }
            android.view.View r7 = r7.getFocusedChild()     // Catch:{ all -> 0x00b8 }
            boolean r7 = r7 instanceof android.widget.VideoView     // Catch:{ all -> 0x00b8 }
            if (r7 == 0) goto L_0x008a
            android.widget.FrameLayout r5 = (android.widget.FrameLayout) r5     // Catch:{ all -> 0x00b8 }
            android.view.View r5 = r5.getFocusedChild()     // Catch:{ all -> 0x00b8 }
            android.widget.VideoView r5 = (android.widget.VideoView) r5     // Catch:{ all -> 0x00b8 }
            r4.mVideoView = r5     // Catch:{ all -> 0x00b8 }
            acr.browser.lightning.activity.BrowserActivity$VideoCompletionListener r7 = new acr.browser.lightning.activity.BrowserActivity$VideoCompletionListener     // Catch:{ all -> 0x00b8 }
            r7.<init>()     // Catch:{ all -> 0x00b8 }
            r5.setOnErrorListener(r7)     // Catch:{ all -> 0x00b8 }
            android.widget.VideoView r5 = r4.mVideoView     // Catch:{ all -> 0x00b8 }
            acr.browser.lightning.activity.BrowserActivity$VideoCompletionListener r7 = new acr.browser.lightning.activity.BrowserActivity$VideoCompletionListener     // Catch:{ all -> 0x00b8 }
            r7.<init>()     // Catch:{ all -> 0x00b8 }
            r5.setOnCompletionListener(r7)     // Catch:{ all -> 0x00b8 }
            goto L_0x008a
        L_0x0070:
            boolean r7 = r5 instanceof android.widget.VideoView     // Catch:{ all -> 0x00b8 }
            if (r7 == 0) goto L_0x008a
            android.widget.VideoView r5 = (android.widget.VideoView) r5     // Catch:{ all -> 0x00b8 }
            r4.mVideoView = r5     // Catch:{ all -> 0x00b8 }
            acr.browser.lightning.activity.BrowserActivity$VideoCompletionListener r7 = new acr.browser.lightning.activity.BrowserActivity$VideoCompletionListener     // Catch:{ all -> 0x00b8 }
            r7.<init>()     // Catch:{ all -> 0x00b8 }
            r5.setOnErrorListener(r7)     // Catch:{ all -> 0x00b8 }
            android.widget.VideoView r5 = r4.mVideoView     // Catch:{ all -> 0x00b8 }
            acr.browser.lightning.activity.BrowserActivity$VideoCompletionListener r7 = new acr.browser.lightning.activity.BrowserActivity$VideoCompletionListener     // Catch:{ all -> 0x00b8 }
            r7.<init>()     // Catch:{ all -> 0x00b8 }
            r5.setOnCompletionListener(r7)     // Catch:{ all -> 0x00b8 }
        L_0x008a:
            android.widget.FrameLayout r5 = r4.mFullscreenContainer     // Catch:{ all -> 0x00b8 }
            android.widget.FrameLayout$LayoutParams r7 = COVER_SCREEN_PARAMS     // Catch:{ all -> 0x00b8 }
            r6.addView(r5, r7)     // Catch:{ all -> 0x00b8 }
            android.widget.FrameLayout r5 = r4.mFullscreenContainer     // Catch:{ all -> 0x00b8 }
            android.view.View r7 = r4.mCustomView     // Catch:{ all -> 0x00b8 }
            android.widget.FrameLayout$LayoutParams r2 = COVER_SCREEN_PARAMS     // Catch:{ all -> 0x00b8 }
            r5.addView(r7, r2)     // Catch:{ all -> 0x00b8 }
            r6.requestLayout()     // Catch:{ all -> 0x00b8 }
            r4.setFullscreen(r1, r1)     // Catch:{ all -> 0x00b8 }
            if (r0 == 0) goto L_0x00a6
            r5 = 4
            r0.setVisibility(r5)     // Catch:{ all -> 0x00b8 }
        L_0x00a6:
            monitor-exit(r4)
            return
        L_0x00a8:
            if (r6 == 0) goto L_0x00b6
            r6.onCustomViewHidden()     // Catch:{ Exception -> 0x00ae }
            goto L_0x00b6
        L_0x00ae:
            r5 = move-exception
            java.lang.String r6 = "BrowserActivity"
            java.lang.String r7 = "Error hiding custom view"
            android.util.Log.e(r6, r7, r5)     // Catch:{ all -> 0x00b8 }
        L_0x00b6:
            monitor-exit(r4)
            return
        L_0x00b8:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.activity.BrowserActivity.onShowCustomView(android.view.View, android.webkit.WebChromeClient$CustomViewCallback, int):void");
    }

    public void closeBookmarksDrawer() {
        this.mDrawerLayout.closeDrawer(getBookmarkDrawer());
    }

    public void onHideCustomView() {
        LightningView currentTab = this.mTabsManager.getCurrentTab();
        if (this.mCustomView == null || this.mCustomViewCallback == null || currentTab == null) {
            WebChromeClient.CustomViewCallback customViewCallback = this.mCustomViewCallback;
            if (customViewCallback != null) {
                try {
                    customViewCallback.onCustomViewHidden();
                } catch (Exception e) {
                    Log.e(TAG, "Error hiding custom view", e);
                }
                this.mCustomViewCallback = null;
                return;
            }
            return;
        }
        Log.d(TAG, "onHideCustomView");
        currentTab.setVisibility(0);
        try {
            this.mCustomView.setKeepScreenOn(false);
        } catch (SecurityException unused) {
            Log.e(TAG, "WebView is not allowed to keep the screen on");
        }
        setFullscreen(this.mPreferences.getHideStatusBarEnabled(), false);
        FrameLayout frameLayout = this.mFullscreenContainer;
        if (frameLayout != null) {
            ViewGroup viewGroup = (ViewGroup) frameLayout.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.mFullscreenContainer);
            }
            this.mFullscreenContainer.removeAllViews();
        }
        this.mFullscreenContainer = null;
        this.mCustomView = null;
        if (this.mVideoView != null) {
            Log.d(TAG, "VideoView is being stopped");
            this.mVideoView.stopPlayback();
            this.mVideoView.setOnErrorListener((MediaPlayer.OnErrorListener) null);
            this.mVideoView.setOnCompletionListener((MediaPlayer.OnCompletionListener) null);
            this.mVideoView = null;
        }
        WebChromeClient.CustomViewCallback customViewCallback2 = this.mCustomViewCallback;
        if (customViewCallback2 != null) {
            try {
                customViewCallback2.onCustomViewHidden();
            } catch (Exception e2) {
                Log.e(TAG, "Error hiding custom view", e2);
            }
        }
        this.mCustomViewCallback = null;
        setRequestedOrientation(this.mOriginalOrientation);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        Log.d(TAG, "onWindowFocusChanged");
        if (z) {
            setFullscreen(this.mIsFullScreen, this.mIsImmersive);
        }
    }

    public void onBackButtonPressed() {
        LightningView currentTab = this.mTabsManager.getCurrentTab();
        if (currentTab == null) {
            return;
        }
        if (currentTab.canGoBack()) {
            currentTab.goBack();
            closeDrawers((Runnable) null);
            return;
        }
        this.mPresenter.deleteTab(this.mTabsManager.positionOf(currentTab));
    }

    public void onForwardButtonPressed() {
        LightningView currentTab = this.mTabsManager.getCurrentTab();
        if (currentTab != null && currentTab.canGoForward()) {
            currentTab.goForward();
            closeDrawers((Runnable) null);
        }
    }

    public void onHomeButtonPressed() {
        if (this.mPreferences.getAdsOnHomePagePressed()) {
            showInterstitialAd(Constants.TAG_FULLSCREEN_HOME_BUTTON);
        }
        LightningView currentTab = this.mTabsManager.getCurrentTab();
        if (currentTab != null) {
            currentTab.loadHomepage();
            closeDrawers((Runnable) null);
        }
    }

    private void setFullscreen(boolean z, boolean z2) {
        this.mIsFullScreen = z;
        this.mIsImmersive = z2;
        Window window = getWindow();
        View decorView = window.getDecorView();
        if (z) {
            if (z2) {
                decorView.setSystemUiVisibility(5894);
            } else {
                decorView.setSystemUiVisibility(0);
            }
            window.setFlags(1024, 1024);
            return;
        }
        window.clearFlags(1024);
        decorView.setSystemUiVisibility(0);
    }

    public void showInterstitialAd(String str) {
        if (System.currentTimeMillis() - this.mPreferences.getLastBannerShownTime() >= ((long) (this.mPreferences.getAdsNewTabInMinutes() * ONE_MINUTE_BY_MILLISECONDS))) {
            this.mPreferences.setLastBannerShownTime(System.currentTimeMillis());
            AppsgeyserSDK.getFastTrackAdsController().showFullscreen(Constants.BannerLoadTags.ON_TIMEOUT_PASSED, this, str);
            this.mPreferences.setLastBannerShownTime(System.currentTimeMillis());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onCreateWindow(android.os.Message r4) {
        /*
            r3 = this;
            monitor-enter(r3)
            if (r4 != 0) goto L_0x0005
            monitor-exit(r3)
            return
        L_0x0005:
            java.lang.String r0 = ""
            r1 = 1
            boolean r0 = r3.newTab(r0, r1)     // Catch:{ all -> 0x002f }
            if (r0 == 0) goto L_0x002d
            acr.browser.lightning.activity.TabsManager r0 = r3.mTabsManager     // Catch:{ all -> 0x002f }
            acr.browser.lightning.activity.TabsManager r2 = r3.mTabsManager     // Catch:{ all -> 0x002f }
            int r2 = r2.size()     // Catch:{ all -> 0x002f }
            int r2 = r2 - r1
            acr.browser.lightning.view.LightningView r0 = r0.getTabAtPosition(r2)     // Catch:{ all -> 0x002f }
            if (r0 == 0) goto L_0x002d
            android.webkit.WebView r0 = r0.getWebView()     // Catch:{ all -> 0x002f }
            if (r0 == 0) goto L_0x002d
            java.lang.Object r1 = r4.obj     // Catch:{ all -> 0x002f }
            android.webkit.WebView$WebViewTransport r1 = (android.webkit.WebView.WebViewTransport) r1     // Catch:{ all -> 0x002f }
            r1.setWebView(r0)     // Catch:{ all -> 0x002f }
            r4.sendToTarget()     // Catch:{ all -> 0x002f }
        L_0x002d:
            monitor-exit(r3)
            return
        L_0x002f:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.activity.BrowserActivity.onCreateWindow(android.os.Message):void");
    }

    public void onCloseWindow(LightningView lightningView) {
        this.mPresenter.deleteTab(this.mTabsManager.positionOf(lightningView));
    }

    public void hideActionBar() {
        ViewGroup viewGroup;
        if (this.mFullScreen && !BrowserApp.getConfig().getToolbarPosition().equals(TAB_POSITION_BOTTOM) && (viewGroup = this.mToolbarLayout) != null && this.mBrowserFrame != null) {
            final int height = viewGroup.getHeight();
            if (this.mToolbarLayout.getTranslationY() > -0.01f) {
                C299725 r1 = new Animation() {
                    /* access modifiers changed from: protected */
                    public void applyTransformation(float f, Transformation transformation) {
                        float f2 = f * ((float) height);
                        BrowserActivity.this.mToolbarLayout.setTranslationY(-f2);
                        BrowserActivity.this.setWebViewTranslation(((float) height) - f2);
                    }
                };
                r1.setDuration(250);
                r1.setInterpolator(new BezierDecelerateInterpolator());
                this.mBrowserFrame.startAnimation(r1);
            }
        }
    }

    public void showActionBar() {
        if (this.mFullScreen && !BrowserApp.getConfig().getToolbarPosition().equals(TAB_POSITION_BOTTOM)) {
            Log.d(TAG, "showActionBar");
            ViewGroup viewGroup = this.mToolbarLayout;
            if (viewGroup != null) {
                final int height = viewGroup.getHeight();
                if (height == 0) {
                    this.mToolbarLayout.measure(0, 0);
                    height = this.mToolbarLayout.getMeasuredHeight();
                }
                if (this.mToolbarLayout.getTranslationY() < (-(((float) height) - 0.01f))) {
                    C299826 r1 = new Animation() {
                        /* access modifiers changed from: protected */
                        public void applyTransformation(float f, Transformation transformation) {
                            float f2 = f * ((float) height);
                            BrowserActivity.this.mToolbarLayout.setTranslationY(f2 - ((float) height));
                            BrowserActivity.this.setWebViewTranslation(f2);
                        }
                    };
                    r1.setDuration(250);
                    r1.setInterpolator(new BezierDecelerateInterpolator());
                    this.mBrowserFrame.startAnimation(r1);
                }
            }
        }
    }

    public void handleBookmarksChange() {
        LightningView currentTab = this.mTabsManager.getCurrentTab();
        if (currentTab != null && currentTab.getUrl().startsWith(acr.browser.lightning.constant.Constants.FILE) && currentTab.getUrl().endsWith(BookmarkPage.FILENAME)) {
            currentTab.loadBookmarkpage();
        }
        if (currentTab != null) {
            this.mBookmarksView.handleUpdatedUrl(currentTab.getUrl());
        }
    }

    public void handleDownloadDeleted() {
        LightningView currentTab = this.mTabsManager.getCurrentTab();
        if (currentTab != null && currentTab.getUrl().startsWith(acr.browser.lightning.constant.Constants.FILE) && currentTab.getUrl().endsWith(DownloadsPage.FILENAME)) {
            currentTab.loadDownloadspage();
        }
        if (currentTab != null) {
            this.mBookmarksView.handleUpdatedUrl(currentTab.getUrl());
        }
    }

    public void handleBookmarkDeleted(HistoryItem historyItem) {
        this.mBookmarksView.handleBookmarkDeleted(historyItem);
        handleBookmarksChange();
    }

    /* renamed from: acr.browser.lightning.activity.BrowserActivity$38 */
    static /* synthetic */ class C301138 {

        /* renamed from: $SwitchMap$acr$browser$lightning$dialog$LightningDialogBuilder$NewTab */
        static final /* synthetic */ int[] f4050x2f0645fe;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                acr.browser.lightning.dialog.LightningDialogBuilder$NewTab[] r0 = acr.browser.lightning.dialog.LightningDialogBuilder.NewTab.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f4050x2f0645fe = r0
                acr.browser.lightning.dialog.LightningDialogBuilder$NewTab r1 = acr.browser.lightning.dialog.LightningDialogBuilder.NewTab.FOREGROUND     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f4050x2f0645fe     // Catch:{ NoSuchFieldError -> 0x001d }
                acr.browser.lightning.dialog.LightningDialogBuilder$NewTab r1 = acr.browser.lightning.dialog.LightningDialogBuilder.NewTab.BACKGROUND     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f4050x2f0645fe     // Catch:{ NoSuchFieldError -> 0x0028 }
                acr.browser.lightning.dialog.LightningDialogBuilder$NewTab r1 = acr.browser.lightning.dialog.LightningDialogBuilder.NewTab.INCOGNITO     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.activity.BrowserActivity.C301138.<clinit>():void");
        }
    }

    public void handleNewTab(LightningDialogBuilder.NewTab newTab, String str) {
        this.mDrawerLayout.closeDrawers();
        int i = C301138.f4050x2f0645fe[newTab.ordinal()];
        if (i == 1) {
            newTab(str, true);
        } else if (i == 2) {
            newTab(str, false);
        } else if (i == 3) {
            if (this.mPreferences.getAdsNewIncognitoTab()) {
                showInterstitialAd(acr.browser.lightning.constant.Constants.TAG_FULLSCREEN_NEWTAB_INCOGNITO);
            }
            Intent intent = new Intent(this, IncognitoActivity.class);
            intent.setData(Uri.parse(str));
            startActivity(intent);
            overridePendingTransition(R.anim.slide_up_in, R.anim.fade_out_scale);
        }
    }

    /* access modifiers changed from: private */
    public void setIsLoading(boolean z) {
        if (!this.mSearch.hasFocus()) {
            Drawable drawable = z ? this.mDeleteIcon : this.mRefreshIcon;
            this.mIcon = drawable;
            this.mSearch.setCompoundDrawables((Drawable) null, (Drawable) null, drawable, (Drawable) null);
        }
    }

    /* access modifiers changed from: private */
    public void refreshOrStop() {
        LightningView currentTab = this.mTabsManager.getCurrentTab();
        if (currentTab == null) {
            return;
        }
        if (currentTab.getProgress() < 100) {
            currentTab.stopLoading();
        } else {
            currentTab.reload();
        }
    }

    public void onClick(View view) {
        LightningView currentTab = this.mTabsManager.getCurrentTab();
        if (currentTab != null) {
            switch (view.getId()) {
                case R.id.action_reading:
                    Intent intent = new Intent(this, ReadingActivity.class);
                    intent.putExtra(acr.browser.lightning.constant.Constants.LOAD_READING_URL, currentTab.getUrl());
                    startActivity(intent);
                    return;
                case R.id.action_toggle_desktop:
                    currentTab.toggleDesktopUA(this);
                    currentTab.reload();
                    closeDrawers((Runnable) null);
                    return;
                case R.id.arrow_button:
                    SearchView searchView = this.mSearch;
                    if (searchView != null && searchView.hasFocus()) {
                        currentTab.requestFocus();
                        return;
                    } else if (this.mShowTabsInDrawer) {
                        this.mDrawerLayout.openDrawer(getTabDrawer());
                        return;
                    } else {
                        currentTab.loadHomepage();
                        return;
                    }
                case R.id.button_back:
                    currentTab.findPrevious();
                    return;
                case R.id.button_next:
                    currentTab.findNext();
                    return;
                case R.id.button_quit:
                    currentTab.clearFindMatches();
                    this.mSearchBar.setVisibility(8);
                    return;
                default:
                    return;
            }
        }
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        PermissionsManager.getInstance().notifyPermissionsChange(strArr, iArr);
        super.onRequestPermissionsResult(i, strArr, iArr);
    }

    public void onPageLoadFinish() {
        if (this.mPreferences.getAdsOnFirstPageLoadFinished() && this.firstLaunch) {
            this.firstLaunch = false;
            showInterstitialAd(acr.browser.lightning.constant.Constants.TAG_FULLSCREEN_PAGELOADED);
        }
    }

    public void createNotice() {
        NotificationCompat.Builder ongoing = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_search_white_24dp).setContent(new RemoteViews(getPackageName(), R.layout.notification_search_bar)).setOngoing(true);
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(603979776);
        intent.putExtra("focus", true);
        ongoing.setContentIntent(PendingIntent.getActivity(getApplicationContext(), PointerIconCompat.TYPE_NO_DROP, intent, 0));
        ((NotificationManager) getSystemService(OneSignalDbContract.NotificationTable.TABLE_NAME)).notify(MainActivity.SEARCH_BAR_NOTIFICATION_ID, ongoing.build());
    }

    private class SearchListenerClass implements View.OnKeyListener, TextView.OnEditorActionListener, View.OnFocusChangeListener, View.OnTouchListener, SearchView.PreFocusListener {
        private SearchListenerClass() {
        }

        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if (i != 66) {
                return false;
            }
            ((InputMethodManager) BrowserActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(BrowserActivity.this.mSearch.getWindowToken(), 0);
            BrowserActivity browserActivity = BrowserActivity.this;
            browserActivity.searchTheWeb(browserActivity.mSearch.getText().toString());
            LightningView currentTab = BrowserActivity.this.mTabsManager.getCurrentTab();
            if (currentTab == null) {
                return true;
            }
            currentTab.requestFocus();
            return true;
        }

        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i != 2 && i != 6 && i != 5 && i != 4 && i != 3 && keyEvent.getAction() != 66) {
                return false;
            }
            ((InputMethodManager) BrowserActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(BrowserActivity.this.mSearch.getWindowToken(), 0);
            BrowserActivity browserActivity = BrowserActivity.this;
            browserActivity.searchTheWeb(browserActivity.mSearch.getText().toString());
            LightningView currentTab = BrowserActivity.this.mTabsManager.getCurrentTab();
            if (currentTab == null) {
                return true;
            }
            currentTab.requestFocus();
            return true;
        }

        public void onFocusChange(View view, boolean z) {
            LightningView currentTab = BrowserActivity.this.mTabsManager.getCurrentTab();
            if (!z && currentTab != null) {
                BrowserActivity.this.setIsLoading(currentTab.getProgress() < 100);
                BrowserActivity.this.updateUrl(currentTab.getUrl(), true);
            } else if (z && currentTab != null) {
                ((SearchView) view).selectAll();
                BrowserActivity browserActivity = BrowserActivity.this;
                Drawable unused = browserActivity.mIcon = browserActivity.mClearIcon;
                BrowserActivity.this.mSearch.setCompoundDrawables((Drawable) null, (Drawable) null, BrowserActivity.this.mClearIcon, (Drawable) null);
            }
            if (!z) {
                ((InputMethodManager) BrowserActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(BrowserActivity.this.mSearch.getWindowToken(), 0);
            }
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (BrowserActivity.this.mSearch.getCompoundDrawables()[2] != null) {
                if (motionEvent.getX() > ((float) ((BrowserActivity.this.mSearch.getWidth() - BrowserActivity.this.mSearch.getPaddingRight()) - BrowserActivity.this.mIcon.getIntrinsicWidth()))) {
                    if (motionEvent.getAction() == 1) {
                        if (BrowserActivity.this.mSearch.hasFocus()) {
                            BrowserActivity.this.mSearch.setText("");
                        } else {
                            BrowserActivity.this.refreshOrStop();
                        }
                    }
                    return true;
                }
            }
            return false;
        }

        public void onPreFocus() {
            LightningView currentTab = BrowserActivity.this.mTabsManager.getCurrentTab();
            if (currentTab != null) {
                String url = currentTab.getUrl();
                if (!UrlUtils.isSpecialUrl(url) && !BrowserActivity.this.mSearch.hasFocus()) {
                    BrowserActivity.this.mSearch.setText(url);
                }
            }
        }
    }

    private class DrawerLocker implements DrawerLayout.DrawerListener {
        public void onDrawerSlide(View view, float f) {
        }

        public void onDrawerStateChanged(int i) {
        }

        private DrawerLocker() {
        }

        public void onDrawerClosed(View view) {
            View access$200 = BrowserActivity.this.getTabDrawer();
            View access$300 = BrowserActivity.this.getBookmarkDrawer();
            if (view == access$200) {
                BrowserActivity.this.mDrawerLayout.setDrawerLockMode(0, access$300);
            } else if (BrowserActivity.this.mShowTabsInDrawer) {
                BrowserActivity.this.mDrawerLayout.setDrawerLockMode(0, access$200);
            }
        }

        public void onDrawerOpened(View view) {
            View access$200 = BrowserActivity.this.getTabDrawer();
            View access$300 = BrowserActivity.this.getBookmarkDrawer();
            if (view == access$200) {
                BrowserActivity.this.mDrawerLayout.setDrawerLockMode(1, access$300);
            } else {
                BrowserActivity.this.mDrawerLayout.setDrawerLockMode(1, access$200);
            }
        }
    }

    private class VideoCompletionListener implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener {
        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            return false;
        }

        private VideoCompletionListener() {
        }

        public void onCompletion(MediaPlayer mediaPlayer) {
            BrowserActivity.this.onHideCustomView();
        }
    }

    private void appThemeChanging() {
        boolean z = System.currentTimeMillis() - this.mPreferences.getRewardedVideoLastViewTime() > ((long) ((this.mPreferences.getRewardedVideoInterval() * 60) * 1000));
        if (!this.mPreferences.getRewardedVideoOnChangeTheme() || !z) {
            themePicker();
            return;
        }
        final C299927 r0 = new FastTrackBaseAdapter.RewardedVideoListener() {
            boolean isVideoFinished;

            public void onVideoClicked() {
            }

            public void onVideoOpened() {
            }

            public void onVideoClosed() {
                BrowserActivity.this.mPreferences.setRewardedVideoLastViewTime(System.currentTimeMillis());
                if (this.isVideoFinished) {
                    BrowserActivity.this.themePicker();
                }
            }

            public void onVideoError(String str) {
                BrowserActivity.this.themePicker();
            }

            public void onVideoFinished() {
                this.isVideoFinished = true;
            }

            public void onVideoDeactivated() {
                BrowserActivity.this.themePicker();
            }
        };
        if (AppsgeyserSDK.getFastTrackAdsController().getRewardedPlacementActivationStatus(acr.browser.lightning.constant.Constants.TAG_REWARDED_APPLY_THEME)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle((CharSequence) getResources().getString(R.string.to_applay_change_watch_video));
            builder.setPositiveButton((CharSequence) getResources().getString(R.string.action_ok), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    AppsgeyserSDK.getFastTrackAdsController().showRewardedVideo(r0, acr.browser.lightning.constant.Constants.TAG_REWARDED_APPLY_THEME);
                }
            });
            builder.setNegativeButton((CharSequence) "cancel", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    BrowserActivity.this.onBackPressed();
                }
            });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    BrowserActivity.this.onBackPressed();
                }
            });
            builder.show();
            return;
        }
        themePicker();
    }

    private void onIncognitoTabChanging() {
        boolean z = System.currentTimeMillis() - this.mPreferences.getRewardedVideoLastViewTime() > ((long) ((this.mPreferences.getRewardedVideoInterval() * 60) * 1000));
        if (!this.mPreferences.getRewardedVideoOnIncognitoTab() || !z) {
            if (this.mPreferences.getAdsNewIncognitoTab()) {
                showInterstitialAd(acr.browser.lightning.constant.Constants.TAG_FULLSCREEN_NEWTAB_INCOGNITO);
            }
            startActivity(new Intent(this, IncognitoActivity.class));
            overridePendingTransition(R.anim.slide_up_in, R.anim.fade_out_scale);
            return;
        }
        final C300431 r0 = new FastTrackBaseAdapter.RewardedVideoListener() {
            boolean isVideoFinished;

            public void onVideoClicked() {
            }

            public void onVideoOpened() {
            }

            public void onVideoClosed() {
                if (this.isVideoFinished) {
                    BrowserActivity.this.startActivity(new Intent(this, IncognitoActivity.class));
                    BrowserActivity.this.overridePendingTransition(R.anim.slide_up_in, R.anim.fade_out_scale);
                }
            }

            public void onVideoError(String str) {
                if (BrowserActivity.this.mPreferences.getAdsNewIncognitoTab()) {
                    BrowserActivity.this.showInterstitialAd(acr.browser.lightning.constant.Constants.TAG_FULLSCREEN_NEWTAB_INCOGNITO);
                }
                BrowserActivity.this.startActivity(new Intent(this, IncognitoActivity.class));
                BrowserActivity.this.overridePendingTransition(R.anim.slide_up_in, R.anim.fade_out_scale);
            }

            public void onVideoFinished() {
                this.isVideoFinished = true;
            }

            public void onVideoDeactivated() {
                if (BrowserActivity.this.mPreferences.getAdsNewIncognitoTab()) {
                    BrowserActivity.this.showInterstitialAd(acr.browser.lightning.constant.Constants.TAG_FULLSCREEN_NEWTAB_INCOGNITO);
                }
                BrowserActivity.this.startActivity(new Intent(this, IncognitoActivity.class));
                BrowserActivity.this.overridePendingTransition(R.anim.slide_up_in, R.anim.fade_out_scale);
            }
        };
        if (AppsgeyserSDK.getFastTrackAdsController().getRewardedPlacementActivationStatus(acr.browser.lightning.constant.Constants.TAG_REWARDED_INCOGNITO_TAB)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle((CharSequence) getResources().getString(R.string.to_applay_change_watch_video));
            builder.setPositiveButton((CharSequence) getResources().getString(R.string.action_ok), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    AppsgeyserSDK.getFastTrackAdsController().showRewardedVideo(r0, acr.browser.lightning.constant.Constants.TAG_REWARDED_INCOGNITO_TAB);
                }
            });
            builder.setNegativeButton((CharSequence) "cancel", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    BrowserActivity.this.onBackPressed();
                }
            });
            builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    BrowserActivity.this.onBackPressed();
                }
            });
            builder.show();
            return;
        }
        if (this.mPreferences.getAdsNewIncognitoTab()) {
            showInterstitialAd(acr.browser.lightning.constant.Constants.TAG_FULLSCREEN_NEWTAB_INCOGNITO);
        }
        startActivity(new Intent(this, IncognitoActivity.class));
        overridePendingTransition(R.anim.slide_up_in, R.anim.fade_out_scale);
    }

    /* access modifiers changed from: private */
    public void themePicker() {
        this.mThemeOptions = getResources().getStringArray(R.array.themes);
        this.mCurrentTheme = this.mPreferences.getUseTheme();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle((CharSequence) getResources().getString(R.string.theme));
        builder.setSingleChoiceItems((CharSequence[]) this.mThemeOptions, this.mPreferences.getUseTheme(), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                BrowserActivity.this.mPreferences.setUseTheme(i);
            }
        });
        builder.setPositiveButton((CharSequence) getResources().getString(R.string.action_ok), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (BrowserActivity.this.mCurrentTheme != BrowserActivity.this.mPreferences.getUseTheme()) {
                    BrowserActivity.this.restart();
                }
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                if (BrowserActivity.this.mCurrentTheme != BrowserActivity.this.mPreferences.getUseTheme()) {
                    BrowserActivity.this.restart();
                }
            }
        });
        BrowserDialog.setDialogSize(this, builder.show());
    }

    public void updateBookmarks() {
        this.mSuggestionsAdapter.refreshBookmarks();
        Fragment findFragmentByTag = getSupportFragmentManager().findFragmentByTag(TAG_BOOKMARK_FRAGMENT);
        if (findFragmentByTag instanceof BookmarksFragment) {
            ((BookmarksFragment) findFragmentByTag).update();
        }
    }
}

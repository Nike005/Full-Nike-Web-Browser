package acr.browser.lightning.app;

import acr.browser.lightning.activity.BrowserActivity;
import acr.browser.lightning.activity.BrowserActivity_MembersInjector;
import acr.browser.lightning.activity.ReadingActivity;
import acr.browser.lightning.activity.ReadingActivity_MembersInjector;
import acr.browser.lightning.activity.TabsManager;
import acr.browser.lightning.activity.TabsManager_MembersInjector;
import acr.browser.lightning.activity.ThemableBrowserActivity;
import acr.browser.lightning.activity.ThemableBrowserActivity_MembersInjector;
import acr.browser.lightning.activity.ThemableSettingsActivity;
import acr.browser.lightning.activity.ThemableSettingsActivity_MembersInjector;
import acr.browser.lightning.browser.BrowserPresenter;
import acr.browser.lightning.browser.BrowserPresenter_MembersInjector;
import acr.browser.lightning.constant.BookmarkPage;
import acr.browser.lightning.constant.BookmarkPage_MembersInjector;
import acr.browser.lightning.constant.DownloadsPage;
import acr.browser.lightning.constant.DownloadsPage_MembersInjector;
import acr.browser.lightning.constant.HistoryPage;
import acr.browser.lightning.constant.HistoryPage_MembersInjector;
import acr.browser.lightning.constant.StartPage;
import acr.browser.lightning.constant.StartPage_MembersInjector;
import acr.browser.lightning.database.bookmark.BookmarkModel;
import acr.browser.lightning.database.downloads.DownloadsModel;
import acr.browser.lightning.database.history.HistoryDatabase;
import acr.browser.lightning.database.history.HistoryDatabase_Factory;
import acr.browser.lightning.dialog.LightningDialogBuilder;
import acr.browser.lightning.dialog.LightningDialogBuilder_Factory;
import acr.browser.lightning.dialog.LightningDialogBuilder_MembersInjector;
import acr.browser.lightning.download.DownloadHandler;
import acr.browser.lightning.download.LightningDownloadListener;
import acr.browser.lightning.download.LightningDownloadListener_MembersInjector;
import acr.browser.lightning.favicon.FaviconModel;
import acr.browser.lightning.favicon.FaviconModel_Factory;
import acr.browser.lightning.fragment.BookmarkSettingsFragment;
import acr.browser.lightning.fragment.BookmarkSettingsFragment_MembersInjector;
import acr.browser.lightning.fragment.BookmarksFragment;
import acr.browser.lightning.fragment.BookmarksFragment_MembersInjector;
import acr.browser.lightning.fragment.DebugSettingsFragment;
import acr.browser.lightning.fragment.DebugSettingsFragment_MembersInjector;
import acr.browser.lightning.fragment.LightningPreferenceFragment;
import acr.browser.lightning.fragment.LightningPreferenceFragment_MembersInjector;
import acr.browser.lightning.fragment.PrivacySettingsFragment;
import acr.browser.lightning.fragment.TabsFragment;
import acr.browser.lightning.fragment.TabsFragment_MembersInjector;
import acr.browser.lightning.preference.PreferenceManager;
import acr.browser.lightning.preference.PreferenceManager_Factory;
import acr.browser.lightning.search.SuggestionsAdapter;
import acr.browser.lightning.search.SuggestionsAdapter_MembersInjector;
import acr.browser.lightning.utils.AdBlock;
import acr.browser.lightning.utils.AdBlock_Factory;
import acr.browser.lightning.utils.ProxyUtils;
import acr.browser.lightning.utils.ProxyUtils_Factory;
import acr.browser.lightning.utils.ProxyUtils_MembersInjector;
import acr.browser.lightning.view.HomepageView;
import acr.browser.lightning.view.HomepageView_HomePageViewPagerFragment_MembersInjector;
import acr.browser.lightning.view.LightningChromeClient;
import acr.browser.lightning.view.LightningChromeClient_MembersInjector;
import acr.browser.lightning.view.LightningView;
import acr.browser.lightning.view.LightningView_MembersInjector;
import acr.browser.lightning.view.LightningWebClient;
import acr.browser.lightning.view.LightningWebClient_MembersInjector;
import dagger.internal.DoubleCheck;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class DaggerAppComponent implements AppComponent {
    private Provider<AdBlock> adBlockProvider;
    private AppModule appModule;
    private Provider<FaviconModel> faviconModelProvider;
    private Provider<HistoryDatabase> historyDatabaseProvider;
    private Provider<PreferenceManager> preferenceManagerProvider;
    private AppModule_ProvideApplicationFactory provideApplicationProvider;
    private Provider<BookmarkModel> provideBookmarkModeProvider;
    private AppModule_ProvideContextFactory provideContextProvider;
    private Provider<DownloadsModel> provideDownloadsModeProvider;
    private Provider<ProxyUtils> proxyUtilsProvider;

    public void inject(DownloadHandler downloadHandler) {
    }

    private DaggerAppComponent(Builder builder) {
        initialize(builder);
    }

    public static Builder builder() {
        return new Builder();
    }

    private LightningDialogBuilder getLightningDialogBuilder() {
        return injectLightningDialogBuilder(LightningDialogBuilder_Factory.newLightningDialogBuilder());
    }

    private void initialize(Builder builder) {
        AppModule_ProvideContextFactory create = AppModule_ProvideContextFactory.create(builder.appModule);
        this.provideContextProvider = create;
        this.preferenceManagerProvider = DoubleCheck.provider(PreferenceManager_Factory.create(create));
        this.provideBookmarkModeProvider = DoubleCheck.provider(AppModule_ProvideBookmarkModeFactory.create(builder.appModule));
        this.provideDownloadsModeProvider = DoubleCheck.provider(AppModule_ProvideDownloadsModeFactory.create(builder.appModule));
        this.proxyUtilsProvider = DoubleCheck.provider(ProxyUtils_Factory.create(this.preferenceManagerProvider));
        AppModule_ProvideApplicationFactory create2 = AppModule_ProvideApplicationFactory.create(builder.appModule);
        this.provideApplicationProvider = create2;
        this.faviconModelProvider = DoubleCheck.provider(FaviconModel_Factory.create(create2));
        this.appModule = builder.appModule;
        this.adBlockProvider = DoubleCheck.provider(AdBlock_Factory.create(this.provideApplicationProvider, this.preferenceManagerProvider));
        this.historyDatabaseProvider = DoubleCheck.provider(HistoryDatabase_Factory.create(this.provideApplicationProvider));
    }

    public void inject(BrowserActivity browserActivity) {
        injectBrowserActivity(browserActivity);
    }

    public void inject(BookmarksFragment bookmarksFragment) {
        injectBookmarksFragment(bookmarksFragment);
    }

    public void inject(HomepageView.HomePageViewPagerFragment homePageViewPagerFragment) {
        injectHomePageViewPagerFragment(homePageViewPagerFragment);
    }

    public void inject(BookmarkSettingsFragment bookmarkSettingsFragment) {
        injectBookmarkSettingsFragment(bookmarkSettingsFragment);
    }

    public void inject(LightningDialogBuilder lightningDialogBuilder) {
        injectLightningDialogBuilder(lightningDialogBuilder);
    }

    public void inject(TabsFragment tabsFragment) {
        injectTabsFragment(tabsFragment);
    }

    public void inject(LightningView lightningView) {
        injectLightningView(lightningView);
    }

    public void inject(ThemableBrowserActivity themableBrowserActivity) {
        injectThemableBrowserActivity(themableBrowserActivity);
    }

    public void inject(LightningPreferenceFragment lightningPreferenceFragment) {
        injectLightningPreferenceFragment(lightningPreferenceFragment);
    }

    public void inject(BrowserApp browserApp) {
        injectBrowserApp(browserApp);
    }

    public void inject(ProxyUtils proxyUtils) {
        injectProxyUtils(proxyUtils);
    }

    public void inject(ReadingActivity readingActivity) {
        injectReadingActivity(readingActivity);
    }

    public void inject(LightningWebClient lightningWebClient) {
        injectLightningWebClient(lightningWebClient);
    }

    public void inject(ThemableSettingsActivity themableSettingsActivity) {
        injectThemableSettingsActivity(themableSettingsActivity);
    }

    public void inject(LightningDownloadListener lightningDownloadListener) {
        injectLightningDownloadListener(lightningDownloadListener);
    }

    public void inject(PrivacySettingsFragment privacySettingsFragment) {
        injectPrivacySettingsFragment(privacySettingsFragment);
    }

    public void inject(StartPage startPage) {
        injectStartPage(startPage);
    }

    public void inject(HistoryPage historyPage) {
        injectHistoryPage(historyPage);
    }

    public void inject(BookmarkPage bookmarkPage) {
        injectBookmarkPage(bookmarkPage);
    }

    public void inject(DownloadsPage downloadsPage) {
        injectDownloadsPage(downloadsPage);
    }

    public void inject(BrowserPresenter browserPresenter) {
        injectBrowserPresenter(browserPresenter);
    }

    public void inject(TabsManager tabsManager) {
        injectTabsManager(tabsManager);
    }

    public void inject(DebugSettingsFragment debugSettingsFragment) {
        injectDebugSettingsFragment(debugSettingsFragment);
    }

    public void inject(SuggestionsAdapter suggestionsAdapter) {
        injectSuggestionsAdapter(suggestionsAdapter);
    }

    public void inject(LightningChromeClient lightningChromeClient) {
        injectLightningChromeClient(lightningChromeClient);
    }

    public HistoryDatabase historyDatabase() {
        return this.historyDatabaseProvider.get();
    }

    private LightningDialogBuilder injectLightningDialogBuilder(LightningDialogBuilder lightningDialogBuilder) {
        LightningDialogBuilder_MembersInjector.injectMBookmarkManager(lightningDialogBuilder, this.provideBookmarkModeProvider.get());
        LightningDialogBuilder_MembersInjector.injectMDownloadsModel(lightningDialogBuilder, this.provideDownloadsModeProvider.get());
        LightningDialogBuilder_MembersInjector.injectMPreferenceManager(lightningDialogBuilder, this.preferenceManagerProvider.get());
        return lightningDialogBuilder;
    }

    private BrowserActivity injectBrowserActivity(BrowserActivity browserActivity) {
        ThemableBrowserActivity_MembersInjector.injectMPreferences(browserActivity, this.preferenceManagerProvider.get());
        BrowserActivity_MembersInjector.injectMBookmarkManager(browserActivity, this.provideBookmarkModeProvider.get());
        BrowserActivity_MembersInjector.injectMBookmarksDialogBuilder(browserActivity, getLightningDialogBuilder());
        BrowserActivity_MembersInjector.injectMProxyUtils(browserActivity, this.proxyUtilsProvider.get());
        return browserActivity;
    }

    private BookmarksFragment injectBookmarksFragment(BookmarksFragment bookmarksFragment) {
        BookmarksFragment_MembersInjector.injectMBookmarkManager(bookmarksFragment, this.provideBookmarkModeProvider.get());
        BookmarksFragment_MembersInjector.injectMBookmarksDialogBuilder(bookmarksFragment, getLightningDialogBuilder());
        BookmarksFragment_MembersInjector.injectMPreferenceManager(bookmarksFragment, this.preferenceManagerProvider.get());
        BookmarksFragment_MembersInjector.injectMFaviconModel(bookmarksFragment, this.faviconModelProvider.get());
        return bookmarksFragment;
    }

    private HomepageView.HomePageViewPagerFragment injectHomePageViewPagerFragment(HomepageView.HomePageViewPagerFragment homePageViewPagerFragment) {
        HomepageView_HomePageViewPagerFragment_MembersInjector.injectMBookmarkManager(homePageViewPagerFragment, this.provideBookmarkModeProvider.get());
        HomepageView_HomePageViewPagerFragment_MembersInjector.injectDownloadsModel(homePageViewPagerFragment, this.provideDownloadsModeProvider.get());
        return homePageViewPagerFragment;
    }

    private BookmarkSettingsFragment injectBookmarkSettingsFragment(BookmarkSettingsFragment bookmarkSettingsFragment) {
        BookmarkSettingsFragment_MembersInjector.injectMBookmarkManager(bookmarkSettingsFragment, this.provideBookmarkModeProvider.get());
        BookmarkSettingsFragment_MembersInjector.injectMApplication(bookmarkSettingsFragment, AppModule_ProvideApplicationFactory.proxyProvideApplication(this.appModule));
        return bookmarkSettingsFragment;
    }

    private TabsFragment injectTabsFragment(TabsFragment tabsFragment) {
        TabsFragment_MembersInjector.injectMPreferences(tabsFragment, this.preferenceManagerProvider.get());
        return tabsFragment;
    }

    private LightningView injectLightningView(LightningView lightningView) {
        LightningView_MembersInjector.injectMPreferences(lightningView, this.preferenceManagerProvider.get());
        LightningView_MembersInjector.injectMBookmarksDialogBuilder(lightningView, getLightningDialogBuilder());
        LightningView_MembersInjector.injectMProxyUtils(lightningView, this.proxyUtilsProvider.get());
        LightningView_MembersInjector.injectMBookmarkManager(lightningView, this.provideBookmarkModeProvider.get());
        return lightningView;
    }

    private ThemableBrowserActivity injectThemableBrowserActivity(ThemableBrowserActivity themableBrowserActivity) {
        ThemableBrowserActivity_MembersInjector.injectMPreferences(themableBrowserActivity, this.preferenceManagerProvider.get());
        return themableBrowserActivity;
    }

    private LightningPreferenceFragment injectLightningPreferenceFragment(LightningPreferenceFragment lightningPreferenceFragment) {
        LightningPreferenceFragment_MembersInjector.injectMPreferenceManager(lightningPreferenceFragment, this.preferenceManagerProvider.get());
        return lightningPreferenceFragment;
    }

    private BrowserApp injectBrowserApp(BrowserApp browserApp) {
        BrowserApp_MembersInjector.injectMPreferenceManager(browserApp, this.preferenceManagerProvider.get());
        BrowserApp_MembersInjector.injectMBookmarkModel(browserApp, this.provideBookmarkModeProvider.get());
        return browserApp;
    }

    private ProxyUtils injectProxyUtils(ProxyUtils proxyUtils) {
        ProxyUtils_MembersInjector.injectMPreferences(proxyUtils, this.preferenceManagerProvider.get());
        return proxyUtils;
    }

    private ReadingActivity injectReadingActivity(ReadingActivity readingActivity) {
        ReadingActivity_MembersInjector.injectMPreferences(readingActivity, this.preferenceManagerProvider.get());
        return readingActivity;
    }

    private LightningWebClient injectLightningWebClient(LightningWebClient lightningWebClient) {
        LightningWebClient_MembersInjector.injectMProxyUtils(lightningWebClient, this.proxyUtilsProvider.get());
        LightningWebClient_MembersInjector.injectMAdBlock(lightningWebClient, this.adBlockProvider.get());
        return lightningWebClient;
    }

    private ThemableSettingsActivity injectThemableSettingsActivity(ThemableSettingsActivity themableSettingsActivity) {
        ThemableSettingsActivity_MembersInjector.injectMPreferences(themableSettingsActivity, this.preferenceManagerProvider.get());
        return themableSettingsActivity;
    }

    private LightningDownloadListener injectLightningDownloadListener(LightningDownloadListener lightningDownloadListener) {
        LightningDownloadListener_MembersInjector.injectMPreferenceManager(lightningDownloadListener, this.preferenceManagerProvider.get());
        LightningDownloadListener_MembersInjector.injectDownloadsModel(lightningDownloadListener, this.provideDownloadsModeProvider.get());
        return lightningDownloadListener;
    }

    private PrivacySettingsFragment injectPrivacySettingsFragment(PrivacySettingsFragment privacySettingsFragment) {
        LightningPreferenceFragment_MembersInjector.injectMPreferenceManager(privacySettingsFragment, this.preferenceManagerProvider.get());
        return privacySettingsFragment;
    }

    private StartPage injectStartPage(StartPage startPage) {
        StartPage_MembersInjector.injectMApp(startPage, AppModule_ProvideApplicationFactory.proxyProvideApplication(this.appModule));
        StartPage_MembersInjector.injectMPreferenceManager(startPage, this.preferenceManagerProvider.get());
        return startPage;
    }

    private HistoryPage injectHistoryPage(HistoryPage historyPage) {
        HistoryPage_MembersInjector.injectMApp(historyPage, AppModule_ProvideApplicationFactory.proxyProvideApplication(this.appModule));
        return historyPage;
    }

    private BookmarkPage injectBookmarkPage(BookmarkPage bookmarkPage) {
        BookmarkPage_MembersInjector.injectMApp(bookmarkPage, AppModule_ProvideApplicationFactory.proxyProvideApplication(this.appModule));
        BookmarkPage_MembersInjector.injectMManager(bookmarkPage, this.provideBookmarkModeProvider.get());
        return bookmarkPage;
    }

    private DownloadsPage injectDownloadsPage(DownloadsPage downloadsPage) {
        DownloadsPage_MembersInjector.injectMApp(downloadsPage, AppModule_ProvideApplicationFactory.proxyProvideApplication(this.appModule));
        DownloadsPage_MembersInjector.injectMPreferenceManager(downloadsPage, this.preferenceManagerProvider.get());
        DownloadsPage_MembersInjector.injectMManager(downloadsPage, this.provideDownloadsModeProvider.get());
        return downloadsPage;
    }

    private BrowserPresenter injectBrowserPresenter(BrowserPresenter browserPresenter) {
        BrowserPresenter_MembersInjector.injectMPreferences(browserPresenter, this.preferenceManagerProvider.get());
        return browserPresenter;
    }

    private TabsManager injectTabsManager(TabsManager tabsManager) {
        TabsManager_MembersInjector.injectMPreferenceManager(tabsManager, this.preferenceManagerProvider.get());
        TabsManager_MembersInjector.injectMApp(tabsManager, AppModule_ProvideApplicationFactory.proxyProvideApplication(this.appModule));
        return tabsManager;
    }

    private DebugSettingsFragment injectDebugSettingsFragment(DebugSettingsFragment debugSettingsFragment) {
        DebugSettingsFragment_MembersInjector.injectMPreferenceManager(debugSettingsFragment, this.preferenceManagerProvider.get());
        return debugSettingsFragment;
    }

    private SuggestionsAdapter injectSuggestionsAdapter(SuggestionsAdapter suggestionsAdapter) {
        SuggestionsAdapter_MembersInjector.injectMBookmarkManager(suggestionsAdapter, this.provideBookmarkModeProvider.get());
        SuggestionsAdapter_MembersInjector.injectMPreferenceManager(suggestionsAdapter, this.preferenceManagerProvider.get());
        SuggestionsAdapter_MembersInjector.injectMApplication(suggestionsAdapter, AppModule_ProvideApplicationFactory.proxyProvideApplication(this.appModule));
        return suggestionsAdapter;
    }

    private LightningChromeClient injectLightningChromeClient(LightningChromeClient lightningChromeClient) {
        LightningChromeClient_MembersInjector.injectMFaviconModel(lightningChromeClient, this.faviconModelProvider.get());
        return lightningChromeClient;
    }

    public static final class Builder {
        /* access modifiers changed from: private */
        public AppModule appModule;

        private Builder() {
        }

        public AppComponent build() {
            if (this.appModule != null) {
                return new DaggerAppComponent(this);
            }
            throw new IllegalStateException(AppModule.class.getCanonicalName() + " must be set");
        }

        public Builder appModule(AppModule appModule2) {
            this.appModule = (AppModule) Preconditions.checkNotNull(appModule2);
            return this;
        }
    }
}

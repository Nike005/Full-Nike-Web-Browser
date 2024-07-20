package acr.browser.lightning.app;

import acr.browser.lightning.activity.BrowserActivity;
import acr.browser.lightning.activity.ReadingActivity;
import acr.browser.lightning.activity.TabsManager;
import acr.browser.lightning.activity.ThemableBrowserActivity;
import acr.browser.lightning.activity.ThemableSettingsActivity;
import acr.browser.lightning.browser.BrowserPresenter;
import acr.browser.lightning.constant.BookmarkPage;
import acr.browser.lightning.constant.DownloadsPage;
import acr.browser.lightning.constant.HistoryPage;
import acr.browser.lightning.constant.StartPage;
import acr.browser.lightning.database.history.HistoryDatabase;
import acr.browser.lightning.dialog.LightningDialogBuilder;
import acr.browser.lightning.download.DownloadHandler;
import acr.browser.lightning.download.LightningDownloadListener;
import acr.browser.lightning.fragment.BookmarkSettingsFragment;
import acr.browser.lightning.fragment.BookmarksFragment;
import acr.browser.lightning.fragment.DebugSettingsFragment;
import acr.browser.lightning.fragment.LightningPreferenceFragment;
import acr.browser.lightning.fragment.PrivacySettingsFragment;
import acr.browser.lightning.fragment.TabsFragment;
import acr.browser.lightning.search.SuggestionsAdapter;
import acr.browser.lightning.utils.ProxyUtils;
import acr.browser.lightning.view.HomepageView;
import acr.browser.lightning.view.LightningChromeClient;
import acr.browser.lightning.view.LightningView;
import acr.browser.lightning.view.LightningWebClient;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    HistoryDatabase historyDatabase();

    void inject(BrowserActivity browserActivity);

    void inject(ReadingActivity readingActivity);

    void inject(TabsManager tabsManager);

    void inject(ThemableBrowserActivity themableBrowserActivity);

    void inject(ThemableSettingsActivity themableSettingsActivity);

    void inject(BrowserApp browserApp);

    void inject(BrowserPresenter browserPresenter);

    void inject(BookmarkPage bookmarkPage);

    void inject(DownloadsPage downloadsPage);

    void inject(HistoryPage historyPage);

    void inject(StartPage startPage);

    void inject(LightningDialogBuilder lightningDialogBuilder);

    void inject(DownloadHandler downloadHandler);

    void inject(LightningDownloadListener lightningDownloadListener);

    void inject(BookmarkSettingsFragment bookmarkSettingsFragment);

    void inject(BookmarksFragment bookmarksFragment);

    void inject(DebugSettingsFragment debugSettingsFragment);

    void inject(LightningPreferenceFragment lightningPreferenceFragment);

    void inject(PrivacySettingsFragment privacySettingsFragment);

    void inject(TabsFragment tabsFragment);

    void inject(SuggestionsAdapter suggestionsAdapter);

    void inject(ProxyUtils proxyUtils);

    void inject(HomepageView.HomePageViewPagerFragment homePageViewPagerFragment);

    void inject(LightningChromeClient lightningChromeClient);

    void inject(LightningView lightningView);

    void inject(LightningWebClient lightningWebClient);
}

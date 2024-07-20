package acr.browser.lightning.activity;

import acr.browser.lightning.database.bookmark.BookmarkModel;
import acr.browser.lightning.dialog.LightningDialogBuilder;
import acr.browser.lightning.preference.PreferenceManager;
import acr.browser.lightning.utils.ProxyUtils;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class BrowserActivity_MembersInjector implements MembersInjector<BrowserActivity> {
    private final Provider<BookmarkModel> mBookmarkManagerProvider;
    private final Provider<LightningDialogBuilder> mBookmarksDialogBuilderProvider;
    private final Provider<PreferenceManager> mPreferencesProvider;
    private final Provider<ProxyUtils> mProxyUtilsProvider;

    public BrowserActivity_MembersInjector(Provider<PreferenceManager> provider, Provider<BookmarkModel> provider2, Provider<LightningDialogBuilder> provider3, Provider<ProxyUtils> provider4) {
        this.mPreferencesProvider = provider;
        this.mBookmarkManagerProvider = provider2;
        this.mBookmarksDialogBuilderProvider = provider3;
        this.mProxyUtilsProvider = provider4;
    }

    public static MembersInjector<BrowserActivity> create(Provider<PreferenceManager> provider, Provider<BookmarkModel> provider2, Provider<LightningDialogBuilder> provider3, Provider<ProxyUtils> provider4) {
        return new BrowserActivity_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(BrowserActivity browserActivity) {
        ThemableBrowserActivity_MembersInjector.injectMPreferences(browserActivity, this.mPreferencesProvider.get());
        injectMBookmarkManager(browserActivity, this.mBookmarkManagerProvider.get());
        injectMBookmarksDialogBuilder(browserActivity, this.mBookmarksDialogBuilderProvider.get());
        injectMProxyUtils(browserActivity, this.mProxyUtilsProvider.get());
    }

    public static void injectMBookmarkManager(BrowserActivity browserActivity, BookmarkModel bookmarkModel) {
        browserActivity.mBookmarkManager = bookmarkModel;
    }

    public static void injectMBookmarksDialogBuilder(BrowserActivity browserActivity, LightningDialogBuilder lightningDialogBuilder) {
        browserActivity.mBookmarksDialogBuilder = lightningDialogBuilder;
    }

    public static void injectMProxyUtils(BrowserActivity browserActivity, ProxyUtils proxyUtils) {
        browserActivity.mProxyUtils = proxyUtils;
    }
}

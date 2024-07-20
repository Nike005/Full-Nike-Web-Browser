package acr.browser.lightning.view;

import acr.browser.lightning.database.bookmark.BookmarkModel;
import acr.browser.lightning.dialog.LightningDialogBuilder;
import acr.browser.lightning.preference.PreferenceManager;
import acr.browser.lightning.utils.ProxyUtils;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class LightningView_MembersInjector implements MembersInjector<LightningView> {
    private final Provider<BookmarkModel> mBookmarkManagerProvider;
    private final Provider<LightningDialogBuilder> mBookmarksDialogBuilderProvider;
    private final Provider<PreferenceManager> mPreferencesProvider;
    private final Provider<ProxyUtils> mProxyUtilsProvider;

    public LightningView_MembersInjector(Provider<PreferenceManager> provider, Provider<LightningDialogBuilder> provider2, Provider<ProxyUtils> provider3, Provider<BookmarkModel> provider4) {
        this.mPreferencesProvider = provider;
        this.mBookmarksDialogBuilderProvider = provider2;
        this.mProxyUtilsProvider = provider3;
        this.mBookmarkManagerProvider = provider4;
    }

    public static MembersInjector<LightningView> create(Provider<PreferenceManager> provider, Provider<LightningDialogBuilder> provider2, Provider<ProxyUtils> provider3, Provider<BookmarkModel> provider4) {
        return new LightningView_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(LightningView lightningView) {
        injectMPreferences(lightningView, this.mPreferencesProvider.get());
        injectMBookmarksDialogBuilder(lightningView, this.mBookmarksDialogBuilderProvider.get());
        injectMProxyUtils(lightningView, this.mProxyUtilsProvider.get());
        injectMBookmarkManager(lightningView, this.mBookmarkManagerProvider.get());
    }

    public static void injectMPreferences(LightningView lightningView, PreferenceManager preferenceManager) {
        lightningView.mPreferences = preferenceManager;
    }

    public static void injectMBookmarksDialogBuilder(LightningView lightningView, LightningDialogBuilder lightningDialogBuilder) {
        lightningView.mBookmarksDialogBuilder = lightningDialogBuilder;
    }

    public static void injectMProxyUtils(LightningView lightningView, ProxyUtils proxyUtils) {
        lightningView.mProxyUtils = proxyUtils;
    }

    public static void injectMBookmarkManager(LightningView lightningView, BookmarkModel bookmarkModel) {
        lightningView.mBookmarkManager = bookmarkModel;
    }
}

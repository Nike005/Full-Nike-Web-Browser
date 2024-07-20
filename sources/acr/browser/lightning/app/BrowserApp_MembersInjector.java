package acr.browser.lightning.app;

import acr.browser.lightning.database.bookmark.BookmarkModel;
import acr.browser.lightning.preference.PreferenceManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class BrowserApp_MembersInjector implements MembersInjector<BrowserApp> {
    private final Provider<BookmarkModel> mBookmarkModelProvider;
    private final Provider<PreferenceManager> mPreferenceManagerProvider;

    public BrowserApp_MembersInjector(Provider<PreferenceManager> provider, Provider<BookmarkModel> provider2) {
        this.mPreferenceManagerProvider = provider;
        this.mBookmarkModelProvider = provider2;
    }

    public static MembersInjector<BrowserApp> create(Provider<PreferenceManager> provider, Provider<BookmarkModel> provider2) {
        return new BrowserApp_MembersInjector(provider, provider2);
    }

    public void injectMembers(BrowserApp browserApp) {
        injectMPreferenceManager(browserApp, this.mPreferenceManagerProvider.get());
        injectMBookmarkModel(browserApp, this.mBookmarkModelProvider.get());
    }

    public static void injectMPreferenceManager(BrowserApp browserApp, PreferenceManager preferenceManager) {
        browserApp.mPreferenceManager = preferenceManager;
    }

    public static void injectMBookmarkModel(BrowserApp browserApp, BookmarkModel bookmarkModel) {
        browserApp.mBookmarkModel = bookmarkModel;
    }
}

package acr.browser.lightning.search;

import acr.browser.lightning.database.bookmark.BookmarkModel;
import acr.browser.lightning.preference.PreferenceManager;
import android.app.Application;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class SuggestionsAdapter_MembersInjector implements MembersInjector<SuggestionsAdapter> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<BookmarkModel> mBookmarkManagerProvider;
    private final Provider<PreferenceManager> mPreferenceManagerProvider;

    public SuggestionsAdapter_MembersInjector(Provider<BookmarkModel> provider, Provider<PreferenceManager> provider2, Provider<Application> provider3) {
        this.mBookmarkManagerProvider = provider;
        this.mPreferenceManagerProvider = provider2;
        this.mApplicationProvider = provider3;
    }

    public static MembersInjector<SuggestionsAdapter> create(Provider<BookmarkModel> provider, Provider<PreferenceManager> provider2, Provider<Application> provider3) {
        return new SuggestionsAdapter_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(SuggestionsAdapter suggestionsAdapter) {
        injectMBookmarkManager(suggestionsAdapter, this.mBookmarkManagerProvider.get());
        injectMPreferenceManager(suggestionsAdapter, this.mPreferenceManagerProvider.get());
        injectMApplication(suggestionsAdapter, this.mApplicationProvider.get());
    }

    public static void injectMBookmarkManager(SuggestionsAdapter suggestionsAdapter, BookmarkModel bookmarkModel) {
        suggestionsAdapter.mBookmarkManager = bookmarkModel;
    }

    public static void injectMPreferenceManager(SuggestionsAdapter suggestionsAdapter, PreferenceManager preferenceManager) {
        suggestionsAdapter.mPreferenceManager = preferenceManager;
    }

    public static void injectMApplication(SuggestionsAdapter suggestionsAdapter, Application application) {
        suggestionsAdapter.mApplication = application;
    }
}

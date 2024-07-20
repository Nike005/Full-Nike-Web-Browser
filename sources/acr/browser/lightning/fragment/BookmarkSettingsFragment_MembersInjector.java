package acr.browser.lightning.fragment;

import acr.browser.lightning.database.bookmark.BookmarkModel;
import android.app.Application;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class BookmarkSettingsFragment_MembersInjector implements MembersInjector<BookmarkSettingsFragment> {
    private final Provider<Application> mApplicationProvider;
    private final Provider<BookmarkModel> mBookmarkManagerProvider;

    public BookmarkSettingsFragment_MembersInjector(Provider<BookmarkModel> provider, Provider<Application> provider2) {
        this.mBookmarkManagerProvider = provider;
        this.mApplicationProvider = provider2;
    }

    public static MembersInjector<BookmarkSettingsFragment> create(Provider<BookmarkModel> provider, Provider<Application> provider2) {
        return new BookmarkSettingsFragment_MembersInjector(provider, provider2);
    }

    public void injectMembers(BookmarkSettingsFragment bookmarkSettingsFragment) {
        injectMBookmarkManager(bookmarkSettingsFragment, this.mBookmarkManagerProvider.get());
        injectMApplication(bookmarkSettingsFragment, this.mApplicationProvider.get());
    }

    public static void injectMBookmarkManager(BookmarkSettingsFragment bookmarkSettingsFragment, BookmarkModel bookmarkModel) {
        bookmarkSettingsFragment.mBookmarkManager = bookmarkModel;
    }

    public static void injectMApplication(BookmarkSettingsFragment bookmarkSettingsFragment, Application application) {
        bookmarkSettingsFragment.mApplication = application;
    }
}

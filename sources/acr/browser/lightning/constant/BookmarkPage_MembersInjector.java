package acr.browser.lightning.constant;

import acr.browser.lightning.database.bookmark.BookmarkModel;
import android.app.Application;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class BookmarkPage_MembersInjector implements MembersInjector<BookmarkPage> {
    private final Provider<Application> mAppProvider;
    private final Provider<BookmarkModel> mManagerProvider;

    public BookmarkPage_MembersInjector(Provider<Application> provider, Provider<BookmarkModel> provider2) {
        this.mAppProvider = provider;
        this.mManagerProvider = provider2;
    }

    public static MembersInjector<BookmarkPage> create(Provider<Application> provider, Provider<BookmarkModel> provider2) {
        return new BookmarkPage_MembersInjector(provider, provider2);
    }

    public void injectMembers(BookmarkPage bookmarkPage) {
        injectMApp(bookmarkPage, this.mAppProvider.get());
        injectMManager(bookmarkPage, this.mManagerProvider.get());
    }

    public static void injectMApp(BookmarkPage bookmarkPage, Application application) {
        bookmarkPage.mApp = application;
    }

    public static void injectMManager(BookmarkPage bookmarkPage, BookmarkModel bookmarkModel) {
        bookmarkPage.mManager = bookmarkModel;
    }
}

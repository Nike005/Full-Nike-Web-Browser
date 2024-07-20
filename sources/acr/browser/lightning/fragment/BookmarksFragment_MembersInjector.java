package acr.browser.lightning.fragment;

import acr.browser.lightning.database.bookmark.BookmarkModel;
import acr.browser.lightning.dialog.LightningDialogBuilder;
import acr.browser.lightning.favicon.FaviconModel;
import acr.browser.lightning.preference.PreferenceManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class BookmarksFragment_MembersInjector implements MembersInjector<BookmarksFragment> {
    private final Provider<BookmarkModel> mBookmarkManagerProvider;
    private final Provider<LightningDialogBuilder> mBookmarksDialogBuilderProvider;
    private final Provider<FaviconModel> mFaviconModelProvider;
    private final Provider<PreferenceManager> mPreferenceManagerProvider;

    public BookmarksFragment_MembersInjector(Provider<BookmarkModel> provider, Provider<LightningDialogBuilder> provider2, Provider<PreferenceManager> provider3, Provider<FaviconModel> provider4) {
        this.mBookmarkManagerProvider = provider;
        this.mBookmarksDialogBuilderProvider = provider2;
        this.mPreferenceManagerProvider = provider3;
        this.mFaviconModelProvider = provider4;
    }

    public static MembersInjector<BookmarksFragment> create(Provider<BookmarkModel> provider, Provider<LightningDialogBuilder> provider2, Provider<PreferenceManager> provider3, Provider<FaviconModel> provider4) {
        return new BookmarksFragment_MembersInjector(provider, provider2, provider3, provider4);
    }

    public void injectMembers(BookmarksFragment bookmarksFragment) {
        injectMBookmarkManager(bookmarksFragment, this.mBookmarkManagerProvider.get());
        injectMBookmarksDialogBuilder(bookmarksFragment, this.mBookmarksDialogBuilderProvider.get());
        injectMPreferenceManager(bookmarksFragment, this.mPreferenceManagerProvider.get());
        injectMFaviconModel(bookmarksFragment, this.mFaviconModelProvider.get());
    }

    public static void injectMBookmarkManager(BookmarksFragment bookmarksFragment, BookmarkModel bookmarkModel) {
        bookmarksFragment.mBookmarkManager = bookmarkModel;
    }

    public static void injectMBookmarksDialogBuilder(BookmarksFragment bookmarksFragment, LightningDialogBuilder lightningDialogBuilder) {
        bookmarksFragment.mBookmarksDialogBuilder = lightningDialogBuilder;
    }

    public static void injectMPreferenceManager(BookmarksFragment bookmarksFragment, PreferenceManager preferenceManager) {
        bookmarksFragment.mPreferenceManager = preferenceManager;
    }

    public static void injectMFaviconModel(BookmarksFragment bookmarksFragment, FaviconModel faviconModel) {
        bookmarksFragment.mFaviconModel = faviconModel;
    }
}

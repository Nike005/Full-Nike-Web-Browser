package acr.browser.lightning.view;

import acr.browser.lightning.database.bookmark.BookmarkModel;
import acr.browser.lightning.database.downloads.DownloadsModel;
import acr.browser.lightning.view.HomepageView;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class HomepageView_HomePageViewPagerFragment_MembersInjector implements MembersInjector<HomepageView.HomePageViewPagerFragment> {
    private final Provider<DownloadsModel> downloadsModelProvider;
    private final Provider<BookmarkModel> mBookmarkManagerProvider;

    public HomepageView_HomePageViewPagerFragment_MembersInjector(Provider<BookmarkModel> provider, Provider<DownloadsModel> provider2) {
        this.mBookmarkManagerProvider = provider;
        this.downloadsModelProvider = provider2;
    }

    public static MembersInjector<HomepageView.HomePageViewPagerFragment> create(Provider<BookmarkModel> provider, Provider<DownloadsModel> provider2) {
        return new HomepageView_HomePageViewPagerFragment_MembersInjector(provider, provider2);
    }

    public void injectMembers(HomepageView.HomePageViewPagerFragment homePageViewPagerFragment) {
        injectMBookmarkManager(homePageViewPagerFragment, this.mBookmarkManagerProvider.get());
        injectDownloadsModel(homePageViewPagerFragment, this.downloadsModelProvider.get());
    }

    public static void injectMBookmarkManager(HomepageView.HomePageViewPagerFragment homePageViewPagerFragment, BookmarkModel bookmarkModel) {
        homePageViewPagerFragment.mBookmarkManager = bookmarkModel;
    }

    public static void injectDownloadsModel(HomepageView.HomePageViewPagerFragment homePageViewPagerFragment, DownloadsModel downloadsModel) {
        homePageViewPagerFragment.downloadsModel = downloadsModel;
    }
}

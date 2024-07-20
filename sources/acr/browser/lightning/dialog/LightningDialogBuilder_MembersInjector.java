package acr.browser.lightning.dialog;

import acr.browser.lightning.database.bookmark.BookmarkModel;
import acr.browser.lightning.database.downloads.DownloadsModel;
import acr.browser.lightning.preference.PreferenceManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class LightningDialogBuilder_MembersInjector implements MembersInjector<LightningDialogBuilder> {
    private final Provider<BookmarkModel> mBookmarkManagerProvider;
    private final Provider<DownloadsModel> mDownloadsModelProvider;
    private final Provider<PreferenceManager> mPreferenceManagerProvider;

    public LightningDialogBuilder_MembersInjector(Provider<BookmarkModel> provider, Provider<DownloadsModel> provider2, Provider<PreferenceManager> provider3) {
        this.mBookmarkManagerProvider = provider;
        this.mDownloadsModelProvider = provider2;
        this.mPreferenceManagerProvider = provider3;
    }

    public static MembersInjector<LightningDialogBuilder> create(Provider<BookmarkModel> provider, Provider<DownloadsModel> provider2, Provider<PreferenceManager> provider3) {
        return new LightningDialogBuilder_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(LightningDialogBuilder lightningDialogBuilder) {
        injectMBookmarkManager(lightningDialogBuilder, this.mBookmarkManagerProvider.get());
        injectMDownloadsModel(lightningDialogBuilder, this.mDownloadsModelProvider.get());
        injectMPreferenceManager(lightningDialogBuilder, this.mPreferenceManagerProvider.get());
    }

    public static void injectMBookmarkManager(LightningDialogBuilder lightningDialogBuilder, BookmarkModel bookmarkModel) {
        lightningDialogBuilder.mBookmarkManager = bookmarkModel;
    }

    public static void injectMDownloadsModel(LightningDialogBuilder lightningDialogBuilder, DownloadsModel downloadsModel) {
        lightningDialogBuilder.mDownloadsModel = downloadsModel;
    }

    public static void injectMPreferenceManager(LightningDialogBuilder lightningDialogBuilder, PreferenceManager preferenceManager) {
        lightningDialogBuilder.mPreferenceManager = preferenceManager;
    }
}

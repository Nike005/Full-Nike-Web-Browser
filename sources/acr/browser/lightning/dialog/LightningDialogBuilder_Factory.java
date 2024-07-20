package acr.browser.lightning.dialog;

import acr.browser.lightning.database.bookmark.BookmarkModel;
import acr.browser.lightning.database.downloads.DownloadsModel;
import acr.browser.lightning.preference.PreferenceManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class LightningDialogBuilder_Factory implements Factory<LightningDialogBuilder> {
    private final Provider<BookmarkModel> mBookmarkManagerProvider;
    private final Provider<DownloadsModel> mDownloadsModelProvider;
    private final Provider<PreferenceManager> mPreferenceManagerProvider;

    public LightningDialogBuilder_Factory(Provider<BookmarkModel> provider, Provider<DownloadsModel> provider2, Provider<PreferenceManager> provider3) {
        this.mBookmarkManagerProvider = provider;
        this.mDownloadsModelProvider = provider2;
        this.mPreferenceManagerProvider = provider3;
    }

    public LightningDialogBuilder get() {
        return provideInstance(this.mBookmarkManagerProvider, this.mDownloadsModelProvider, this.mPreferenceManagerProvider);
    }

    public static LightningDialogBuilder provideInstance(Provider<BookmarkModel> provider, Provider<DownloadsModel> provider2, Provider<PreferenceManager> provider3) {
        LightningDialogBuilder lightningDialogBuilder = new LightningDialogBuilder();
        LightningDialogBuilder_MembersInjector.injectMBookmarkManager(lightningDialogBuilder, provider.get());
        LightningDialogBuilder_MembersInjector.injectMDownloadsModel(lightningDialogBuilder, provider2.get());
        LightningDialogBuilder_MembersInjector.injectMPreferenceManager(lightningDialogBuilder, provider3.get());
        return lightningDialogBuilder;
    }

    public static LightningDialogBuilder_Factory create(Provider<BookmarkModel> provider, Provider<DownloadsModel> provider2, Provider<PreferenceManager> provider3) {
        return new LightningDialogBuilder_Factory(provider, provider2, provider3);
    }

    public static LightningDialogBuilder newLightningDialogBuilder() {
        return new LightningDialogBuilder();
    }
}

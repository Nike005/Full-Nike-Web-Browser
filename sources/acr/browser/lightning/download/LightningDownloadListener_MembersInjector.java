package acr.browser.lightning.download;

import acr.browser.lightning.database.downloads.DownloadsModel;
import acr.browser.lightning.preference.PreferenceManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class LightningDownloadListener_MembersInjector implements MembersInjector<LightningDownloadListener> {
    private final Provider<DownloadsModel> downloadsModelProvider;
    private final Provider<PreferenceManager> mPreferenceManagerProvider;

    public LightningDownloadListener_MembersInjector(Provider<PreferenceManager> provider, Provider<DownloadsModel> provider2) {
        this.mPreferenceManagerProvider = provider;
        this.downloadsModelProvider = provider2;
    }

    public static MembersInjector<LightningDownloadListener> create(Provider<PreferenceManager> provider, Provider<DownloadsModel> provider2) {
        return new LightningDownloadListener_MembersInjector(provider, provider2);
    }

    public void injectMembers(LightningDownloadListener lightningDownloadListener) {
        injectMPreferenceManager(lightningDownloadListener, this.mPreferenceManagerProvider.get());
        injectDownloadsModel(lightningDownloadListener, this.downloadsModelProvider.get());
    }

    public static void injectMPreferenceManager(LightningDownloadListener lightningDownloadListener, PreferenceManager preferenceManager) {
        lightningDownloadListener.mPreferenceManager = preferenceManager;
    }

    public static void injectDownloadsModel(LightningDownloadListener lightningDownloadListener, DownloadsModel downloadsModel) {
        lightningDownloadListener.downloadsModel = downloadsModel;
    }
}

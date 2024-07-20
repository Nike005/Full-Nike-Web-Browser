package acr.browser.lightning.constant;

import acr.browser.lightning.database.downloads.DownloadsModel;
import acr.browser.lightning.preference.PreferenceManager;
import android.app.Application;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class DownloadsPage_MembersInjector implements MembersInjector<DownloadsPage> {
    private final Provider<Application> mAppProvider;
    private final Provider<DownloadsModel> mManagerProvider;
    private final Provider<PreferenceManager> mPreferenceManagerProvider;

    public DownloadsPage_MembersInjector(Provider<Application> provider, Provider<PreferenceManager> provider2, Provider<DownloadsModel> provider3) {
        this.mAppProvider = provider;
        this.mPreferenceManagerProvider = provider2;
        this.mManagerProvider = provider3;
    }

    public static MembersInjector<DownloadsPage> create(Provider<Application> provider, Provider<PreferenceManager> provider2, Provider<DownloadsModel> provider3) {
        return new DownloadsPage_MembersInjector(provider, provider2, provider3);
    }

    public void injectMembers(DownloadsPage downloadsPage) {
        injectMApp(downloadsPage, this.mAppProvider.get());
        injectMPreferenceManager(downloadsPage, this.mPreferenceManagerProvider.get());
        injectMManager(downloadsPage, this.mManagerProvider.get());
    }

    public static void injectMApp(DownloadsPage downloadsPage, Application application) {
        downloadsPage.mApp = application;
    }

    public static void injectMPreferenceManager(DownloadsPage downloadsPage, PreferenceManager preferenceManager) {
        downloadsPage.mPreferenceManager = preferenceManager;
    }

    public static void injectMManager(DownloadsPage downloadsPage, DownloadsModel downloadsModel) {
        downloadsPage.mManager = downloadsModel;
    }
}

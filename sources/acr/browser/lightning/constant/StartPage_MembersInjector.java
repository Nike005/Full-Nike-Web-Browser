package acr.browser.lightning.constant;

import acr.browser.lightning.preference.PreferenceManager;
import android.app.Application;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class StartPage_MembersInjector implements MembersInjector<StartPage> {
    private final Provider<Application> mAppProvider;
    private final Provider<PreferenceManager> mPreferenceManagerProvider;

    public StartPage_MembersInjector(Provider<Application> provider, Provider<PreferenceManager> provider2) {
        this.mAppProvider = provider;
        this.mPreferenceManagerProvider = provider2;
    }

    public static MembersInjector<StartPage> create(Provider<Application> provider, Provider<PreferenceManager> provider2) {
        return new StartPage_MembersInjector(provider, provider2);
    }

    public void injectMembers(StartPage startPage) {
        injectMApp(startPage, this.mAppProvider.get());
        injectMPreferenceManager(startPage, this.mPreferenceManagerProvider.get());
    }

    public static void injectMApp(StartPage startPage, Application application) {
        startPage.mApp = application;
    }

    public static void injectMPreferenceManager(StartPage startPage, PreferenceManager preferenceManager) {
        startPage.mPreferenceManager = preferenceManager;
    }
}

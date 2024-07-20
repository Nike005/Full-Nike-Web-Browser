package acr.browser.lightning.activity;

import acr.browser.lightning.preference.PreferenceManager;
import android.app.Application;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class TabsManager_MembersInjector implements MembersInjector<TabsManager> {
    private final Provider<Application> mAppProvider;
    private final Provider<PreferenceManager> mPreferenceManagerProvider;

    public TabsManager_MembersInjector(Provider<PreferenceManager> provider, Provider<Application> provider2) {
        this.mPreferenceManagerProvider = provider;
        this.mAppProvider = provider2;
    }

    public static MembersInjector<TabsManager> create(Provider<PreferenceManager> provider, Provider<Application> provider2) {
        return new TabsManager_MembersInjector(provider, provider2);
    }

    public void injectMembers(TabsManager tabsManager) {
        injectMPreferenceManager(tabsManager, this.mPreferenceManagerProvider.get());
        injectMApp(tabsManager, this.mAppProvider.get());
    }

    public static void injectMPreferenceManager(TabsManager tabsManager, PreferenceManager preferenceManager) {
        tabsManager.mPreferenceManager = preferenceManager;
    }

    public static void injectMApp(TabsManager tabsManager, Application application) {
        tabsManager.mApp = application;
    }
}

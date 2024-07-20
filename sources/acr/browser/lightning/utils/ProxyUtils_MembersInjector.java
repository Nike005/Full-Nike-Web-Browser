package acr.browser.lightning.utils;

import acr.browser.lightning.preference.PreferenceManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ProxyUtils_MembersInjector implements MembersInjector<ProxyUtils> {
    private final Provider<PreferenceManager> mPreferencesProvider;

    public ProxyUtils_MembersInjector(Provider<PreferenceManager> provider) {
        this.mPreferencesProvider = provider;
    }

    public static MembersInjector<ProxyUtils> create(Provider<PreferenceManager> provider) {
        return new ProxyUtils_MembersInjector(provider);
    }

    public void injectMembers(ProxyUtils proxyUtils) {
        injectMPreferences(proxyUtils, this.mPreferencesProvider.get());
    }

    public static void injectMPreferences(ProxyUtils proxyUtils, PreferenceManager preferenceManager) {
        proxyUtils.mPreferences = preferenceManager;
    }
}

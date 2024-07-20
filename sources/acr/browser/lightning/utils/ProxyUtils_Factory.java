package acr.browser.lightning.utils;

import acr.browser.lightning.preference.PreferenceManager;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ProxyUtils_Factory implements Factory<ProxyUtils> {
    private final Provider<PreferenceManager> mPreferencesProvider;

    public ProxyUtils_Factory(Provider<PreferenceManager> provider) {
        this.mPreferencesProvider = provider;
    }

    public ProxyUtils get() {
        return provideInstance(this.mPreferencesProvider);
    }

    public static ProxyUtils provideInstance(Provider<PreferenceManager> provider) {
        ProxyUtils proxyUtils = new ProxyUtils();
        ProxyUtils_MembersInjector.injectMPreferences(proxyUtils, provider.get());
        return proxyUtils;
    }

    public static ProxyUtils_Factory create(Provider<PreferenceManager> provider) {
        return new ProxyUtils_Factory(provider);
    }

    public static ProxyUtils newProxyUtils() {
        return new ProxyUtils();
    }
}

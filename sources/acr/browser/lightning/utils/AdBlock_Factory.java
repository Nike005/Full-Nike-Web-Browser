package acr.browser.lightning.utils;

import acr.browser.lightning.preference.PreferenceManager;
import android.app.Application;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AdBlock_Factory implements Factory<AdBlock> {
    private final Provider<Application> applicationProvider;
    private final Provider<PreferenceManager> preferenceManagerProvider;

    public AdBlock_Factory(Provider<Application> provider, Provider<PreferenceManager> provider2) {
        this.applicationProvider = provider;
        this.preferenceManagerProvider = provider2;
    }

    public AdBlock get() {
        return provideInstance(this.applicationProvider, this.preferenceManagerProvider);
    }

    public static AdBlock provideInstance(Provider<Application> provider, Provider<PreferenceManager> provider2) {
        return new AdBlock(provider.get(), provider2.get());
    }

    public static AdBlock_Factory create(Provider<Application> provider, Provider<PreferenceManager> provider2) {
        return new AdBlock_Factory(provider, provider2);
    }

    public static AdBlock newAdBlock(Application application, PreferenceManager preferenceManager) {
        return new AdBlock(application, preferenceManager);
    }
}

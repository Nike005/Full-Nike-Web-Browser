package acr.browser.lightning.preference;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class PreferenceManager_Factory implements Factory<PreferenceManager> {
    private final Provider<Context> contextProvider;

    public PreferenceManager_Factory(Provider<Context> provider) {
        this.contextProvider = provider;
    }

    public PreferenceManager get() {
        return provideInstance(this.contextProvider);
    }

    public static PreferenceManager provideInstance(Provider<Context> provider) {
        return new PreferenceManager(provider.get());
    }

    public static PreferenceManager_Factory create(Provider<Context> provider) {
        return new PreferenceManager_Factory(provider);
    }

    public static PreferenceManager newPreferenceManager(Context context) {
        return new PreferenceManager(context);
    }
}

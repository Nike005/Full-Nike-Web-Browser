package acr.browser.lightning.favicon;

import android.app.Application;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class FaviconModel_Factory implements Factory<FaviconModel> {
    private final Provider<Application> applicationProvider;

    public FaviconModel_Factory(Provider<Application> provider) {
        this.applicationProvider = provider;
    }

    public FaviconModel get() {
        return provideInstance(this.applicationProvider);
    }

    public static FaviconModel provideInstance(Provider<Application> provider) {
        return new FaviconModel(provider.get());
    }

    public static FaviconModel_Factory create(Provider<Application> provider) {
        return new FaviconModel_Factory(provider);
    }

    public static FaviconModel newFaviconModel(Application application) {
        return new FaviconModel(application);
    }
}

package acr.browser.lightning.database.downloads;

import android.app.Application;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class DownloadsDatabase_Factory implements Factory<DownloadsDatabase> {
    private final Provider<Application> applicationProvider;

    public DownloadsDatabase_Factory(Provider<Application> provider) {
        this.applicationProvider = provider;
    }

    public DownloadsDatabase get() {
        return provideInstance(this.applicationProvider);
    }

    public static DownloadsDatabase provideInstance(Provider<Application> provider) {
        return new DownloadsDatabase(provider.get());
    }

    public static DownloadsDatabase_Factory create(Provider<Application> provider) {
        return new DownloadsDatabase_Factory(provider);
    }

    public static DownloadsDatabase newDownloadsDatabase(Application application) {
        return new DownloadsDatabase(application);
    }
}

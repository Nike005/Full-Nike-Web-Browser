package acr.browser.lightning.database.history;

import android.app.Application;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class HistoryDatabase_Factory implements Factory<HistoryDatabase> {
    private final Provider<Application> applicationProvider;

    public HistoryDatabase_Factory(Provider<Application> provider) {
        this.applicationProvider = provider;
    }

    public HistoryDatabase get() {
        return provideInstance(this.applicationProvider);
    }

    public static HistoryDatabase provideInstance(Provider<Application> provider) {
        return new HistoryDatabase(provider.get());
    }

    public static HistoryDatabase_Factory create(Provider<Application> provider) {
        return new HistoryDatabase_Factory(provider);
    }

    public static HistoryDatabase newHistoryDatabase(Application application) {
        return new HistoryDatabase(application);
    }
}

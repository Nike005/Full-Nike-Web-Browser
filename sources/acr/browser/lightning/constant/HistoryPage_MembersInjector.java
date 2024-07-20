package acr.browser.lightning.constant;

import android.app.Application;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class HistoryPage_MembersInjector implements MembersInjector<HistoryPage> {
    private final Provider<Application> mAppProvider;

    public HistoryPage_MembersInjector(Provider<Application> provider) {
        this.mAppProvider = provider;
    }

    public static MembersInjector<HistoryPage> create(Provider<Application> provider) {
        return new HistoryPage_MembersInjector(provider);
    }

    public void injectMembers(HistoryPage historyPage) {
        injectMApp(historyPage, this.mAppProvider.get());
    }

    public static void injectMApp(HistoryPage historyPage, Application application) {
        historyPage.mApp = application;
    }
}

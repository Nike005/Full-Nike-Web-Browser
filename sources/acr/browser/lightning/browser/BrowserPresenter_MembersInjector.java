package acr.browser.lightning.browser;

import acr.browser.lightning.preference.PreferenceManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class BrowserPresenter_MembersInjector implements MembersInjector<BrowserPresenter> {
    private final Provider<PreferenceManager> mPreferencesProvider;

    public BrowserPresenter_MembersInjector(Provider<PreferenceManager> provider) {
        this.mPreferencesProvider = provider;
    }

    public static MembersInjector<BrowserPresenter> create(Provider<PreferenceManager> provider) {
        return new BrowserPresenter_MembersInjector(provider);
    }

    public void injectMembers(BrowserPresenter browserPresenter) {
        injectMPreferences(browserPresenter, this.mPreferencesProvider.get());
    }

    public static void injectMPreferences(BrowserPresenter browserPresenter, PreferenceManager preferenceManager) {
        browserPresenter.mPreferences = preferenceManager;
    }
}

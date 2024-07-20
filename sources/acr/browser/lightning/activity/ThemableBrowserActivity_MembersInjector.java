package acr.browser.lightning.activity;

import acr.browser.lightning.preference.PreferenceManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ThemableBrowserActivity_MembersInjector implements MembersInjector<ThemableBrowserActivity> {
    private final Provider<PreferenceManager> mPreferencesProvider;

    public ThemableBrowserActivity_MembersInjector(Provider<PreferenceManager> provider) {
        this.mPreferencesProvider = provider;
    }

    public static MembersInjector<ThemableBrowserActivity> create(Provider<PreferenceManager> provider) {
        return new ThemableBrowserActivity_MembersInjector(provider);
    }

    public void injectMembers(ThemableBrowserActivity themableBrowserActivity) {
        injectMPreferences(themableBrowserActivity, this.mPreferencesProvider.get());
    }

    public static void injectMPreferences(ThemableBrowserActivity themableBrowserActivity, PreferenceManager preferenceManager) {
        themableBrowserActivity.mPreferences = preferenceManager;
    }
}

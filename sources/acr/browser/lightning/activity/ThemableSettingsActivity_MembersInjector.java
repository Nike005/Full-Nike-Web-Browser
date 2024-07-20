package acr.browser.lightning.activity;

import acr.browser.lightning.preference.PreferenceManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ThemableSettingsActivity_MembersInjector implements MembersInjector<ThemableSettingsActivity> {
    private final Provider<PreferenceManager> mPreferencesProvider;

    public ThemableSettingsActivity_MembersInjector(Provider<PreferenceManager> provider) {
        this.mPreferencesProvider = provider;
    }

    public static MembersInjector<ThemableSettingsActivity> create(Provider<PreferenceManager> provider) {
        return new ThemableSettingsActivity_MembersInjector(provider);
    }

    public void injectMembers(ThemableSettingsActivity themableSettingsActivity) {
        injectMPreferences(themableSettingsActivity, this.mPreferencesProvider.get());
    }

    public static void injectMPreferences(ThemableSettingsActivity themableSettingsActivity, PreferenceManager preferenceManager) {
        themableSettingsActivity.mPreferences = preferenceManager;
    }
}

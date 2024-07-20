package acr.browser.lightning.activity;

import acr.browser.lightning.preference.PreferenceManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ReadingActivity_MembersInjector implements MembersInjector<ReadingActivity> {
    private final Provider<PreferenceManager> mPreferencesProvider;

    public ReadingActivity_MembersInjector(Provider<PreferenceManager> provider) {
        this.mPreferencesProvider = provider;
    }

    public static MembersInjector<ReadingActivity> create(Provider<PreferenceManager> provider) {
        return new ReadingActivity_MembersInjector(provider);
    }

    public void injectMembers(ReadingActivity readingActivity) {
        injectMPreferences(readingActivity, this.mPreferencesProvider.get());
    }

    public static void injectMPreferences(ReadingActivity readingActivity, PreferenceManager preferenceManager) {
        readingActivity.mPreferences = preferenceManager;
    }
}

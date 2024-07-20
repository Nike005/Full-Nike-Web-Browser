package acr.browser.lightning.fragment;

import acr.browser.lightning.preference.PreferenceManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class PrivacySettingsFragment_MembersInjector implements MembersInjector<PrivacySettingsFragment> {
    private final Provider<PreferenceManager> mPreferenceManagerProvider;

    public PrivacySettingsFragment_MembersInjector(Provider<PreferenceManager> provider) {
        this.mPreferenceManagerProvider = provider;
    }

    public static MembersInjector<PrivacySettingsFragment> create(Provider<PreferenceManager> provider) {
        return new PrivacySettingsFragment_MembersInjector(provider);
    }

    public void injectMembers(PrivacySettingsFragment privacySettingsFragment) {
        LightningPreferenceFragment_MembersInjector.injectMPreferenceManager(privacySettingsFragment, this.mPreferenceManagerProvider.get());
    }
}

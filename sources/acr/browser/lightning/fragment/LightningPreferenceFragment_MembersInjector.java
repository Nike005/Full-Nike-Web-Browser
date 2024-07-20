package acr.browser.lightning.fragment;

import acr.browser.lightning.preference.PreferenceManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class LightningPreferenceFragment_MembersInjector implements MembersInjector<LightningPreferenceFragment> {
    private final Provider<PreferenceManager> mPreferenceManagerProvider;

    public LightningPreferenceFragment_MembersInjector(Provider<PreferenceManager> provider) {
        this.mPreferenceManagerProvider = provider;
    }

    public static MembersInjector<LightningPreferenceFragment> create(Provider<PreferenceManager> provider) {
        return new LightningPreferenceFragment_MembersInjector(provider);
    }

    public void injectMembers(LightningPreferenceFragment lightningPreferenceFragment) {
        injectMPreferenceManager(lightningPreferenceFragment, this.mPreferenceManagerProvider.get());
    }

    public static void injectMPreferenceManager(LightningPreferenceFragment lightningPreferenceFragment, PreferenceManager preferenceManager) {
        lightningPreferenceFragment.mPreferenceManager = preferenceManager;
    }
}

package acr.browser.lightning.fragment;

import acr.browser.lightning.preference.PreferenceManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class DebugSettingsFragment_MembersInjector implements MembersInjector<DebugSettingsFragment> {
    private final Provider<PreferenceManager> mPreferenceManagerProvider;

    public DebugSettingsFragment_MembersInjector(Provider<PreferenceManager> provider) {
        this.mPreferenceManagerProvider = provider;
    }

    public static MembersInjector<DebugSettingsFragment> create(Provider<PreferenceManager> provider) {
        return new DebugSettingsFragment_MembersInjector(provider);
    }

    public void injectMembers(DebugSettingsFragment debugSettingsFragment) {
        injectMPreferenceManager(debugSettingsFragment, this.mPreferenceManagerProvider.get());
    }

    public static void injectMPreferenceManager(DebugSettingsFragment debugSettingsFragment, PreferenceManager preferenceManager) {
        debugSettingsFragment.mPreferenceManager = preferenceManager;
    }
}

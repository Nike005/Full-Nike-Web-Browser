package acr.browser.lightning.fragment;

import acr.browser.lightning.preference.PreferenceManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class TabsFragment_MembersInjector implements MembersInjector<TabsFragment> {
    private final Provider<PreferenceManager> mPreferencesProvider;

    public TabsFragment_MembersInjector(Provider<PreferenceManager> provider) {
        this.mPreferencesProvider = provider;
    }

    public static MembersInjector<TabsFragment> create(Provider<PreferenceManager> provider) {
        return new TabsFragment_MembersInjector(provider);
    }

    public void injectMembers(TabsFragment tabsFragment) {
        injectMPreferences(tabsFragment, this.mPreferencesProvider.get());
    }

    public static void injectMPreferences(TabsFragment tabsFragment, PreferenceManager preferenceManager) {
        tabsFragment.mPreferences = preferenceManager;
    }
}

package acr.browser.lightning.view;

import acr.browser.lightning.favicon.FaviconModel;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class LightningChromeClient_MembersInjector implements MembersInjector<LightningChromeClient> {
    private final Provider<FaviconModel> mFaviconModelProvider;

    public LightningChromeClient_MembersInjector(Provider<FaviconModel> provider) {
        this.mFaviconModelProvider = provider;
    }

    public static MembersInjector<LightningChromeClient> create(Provider<FaviconModel> provider) {
        return new LightningChromeClient_MembersInjector(provider);
    }

    public void injectMembers(LightningChromeClient lightningChromeClient) {
        injectMFaviconModel(lightningChromeClient, this.mFaviconModelProvider.get());
    }

    public static void injectMFaviconModel(LightningChromeClient lightningChromeClient, FaviconModel faviconModel) {
        lightningChromeClient.mFaviconModel = faviconModel;
    }
}

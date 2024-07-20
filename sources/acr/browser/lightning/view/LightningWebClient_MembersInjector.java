package acr.browser.lightning.view;

import acr.browser.lightning.utils.AdBlock;
import acr.browser.lightning.utils.ProxyUtils;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class LightningWebClient_MembersInjector implements MembersInjector<LightningWebClient> {
    private final Provider<AdBlock> mAdBlockProvider;
    private final Provider<ProxyUtils> mProxyUtilsProvider;

    public LightningWebClient_MembersInjector(Provider<ProxyUtils> provider, Provider<AdBlock> provider2) {
        this.mProxyUtilsProvider = provider;
        this.mAdBlockProvider = provider2;
    }

    public static MembersInjector<LightningWebClient> create(Provider<ProxyUtils> provider, Provider<AdBlock> provider2) {
        return new LightningWebClient_MembersInjector(provider, provider2);
    }

    public void injectMembers(LightningWebClient lightningWebClient) {
        injectMProxyUtils(lightningWebClient, this.mProxyUtilsProvider.get());
        injectMAdBlock(lightningWebClient, this.mAdBlockProvider.get());
    }

    public static void injectMProxyUtils(LightningWebClient lightningWebClient, ProxyUtils proxyUtils) {
        lightningWebClient.mProxyUtils = proxyUtils;
    }

    public static void injectMAdBlock(LightningWebClient lightningWebClient, AdBlock adBlock) {
        lightningWebClient.mAdBlock = adBlock;
    }
}

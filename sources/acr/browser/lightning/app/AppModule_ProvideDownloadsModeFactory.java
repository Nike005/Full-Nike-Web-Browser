package acr.browser.lightning.app;

import acr.browser.lightning.database.downloads.DownloadsModel;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AppModule_ProvideDownloadsModeFactory implements Factory<DownloadsModel> {
    private final AppModule module;

    public AppModule_ProvideDownloadsModeFactory(AppModule appModule) {
        this.module = appModule;
    }

    public DownloadsModel get() {
        return provideInstance(this.module);
    }

    public static DownloadsModel provideInstance(AppModule appModule) {
        return proxyProvideDownloadsMode(appModule);
    }

    public static AppModule_ProvideDownloadsModeFactory create(AppModule appModule) {
        return new AppModule_ProvideDownloadsModeFactory(appModule);
    }

    public static DownloadsModel proxyProvideDownloadsMode(AppModule appModule) {
        return (DownloadsModel) Preconditions.checkNotNull(appModule.provideDownloadsMode(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

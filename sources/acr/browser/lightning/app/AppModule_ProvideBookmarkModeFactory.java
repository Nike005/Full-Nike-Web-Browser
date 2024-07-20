package acr.browser.lightning.app;

import acr.browser.lightning.database.bookmark.BookmarkModel;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class AppModule_ProvideBookmarkModeFactory implements Factory<BookmarkModel> {
    private final AppModule module;

    public AppModule_ProvideBookmarkModeFactory(AppModule appModule) {
        this.module = appModule;
    }

    public BookmarkModel get() {
        return provideInstance(this.module);
    }

    public static BookmarkModel provideInstance(AppModule appModule) {
        return proxyProvideBookmarkMode(appModule);
    }

    public static AppModule_ProvideBookmarkModeFactory create(AppModule appModule) {
        return new AppModule_ProvideBookmarkModeFactory(appModule);
    }

    public static BookmarkModel proxyProvideBookmarkMode(AppModule appModule) {
        return (BookmarkModel) Preconditions.checkNotNull(appModule.provideBookmarkMode(), "Cannot return null from a non-@Nullable @Provides method");
    }
}

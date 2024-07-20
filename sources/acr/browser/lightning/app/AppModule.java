package acr.browser.lightning.app;

import acr.browser.lightning.database.bookmark.BookmarkDatabase;
import acr.browser.lightning.database.bookmark.BookmarkModel;
import acr.browser.lightning.database.downloads.DownloadsDatabase;
import acr.browser.lightning.database.downloads.DownloadsModel;
import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public class AppModule {
    private final BrowserApp mApp;

    public AppModule(BrowserApp browserApp) {
        this.mApp = browserApp;
    }

    @Provides
    public Application provideApplication() {
        return this.mApp;
    }

    @Provides
    public Context provideContext() {
        return this.mApp.getApplicationContext();
    }

    @Singleton
    @Provides
    public BookmarkModel provideBookmarkMode() {
        return new BookmarkDatabase(this.mApp);
    }

    @Singleton
    @Provides
    public DownloadsModel provideDownloadsMode() {
        return new DownloadsDatabase(this.mApp);
    }
}

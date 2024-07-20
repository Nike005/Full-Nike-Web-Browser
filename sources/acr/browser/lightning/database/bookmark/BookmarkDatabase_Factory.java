package acr.browser.lightning.database.bookmark;

import android.app.Application;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class BookmarkDatabase_Factory implements Factory<BookmarkDatabase> {
    private final Provider<Application> applicationProvider;

    public BookmarkDatabase_Factory(Provider<Application> provider) {
        this.applicationProvider = provider;
    }

    public BookmarkDatabase get() {
        return provideInstance(this.applicationProvider);
    }

    public static BookmarkDatabase provideInstance(Provider<Application> provider) {
        return new BookmarkDatabase(provider.get());
    }

    public static BookmarkDatabase_Factory create(Provider<Application> provider) {
        return new BookmarkDatabase_Factory(provider);
    }

    public static BookmarkDatabase newBookmarkDatabase(Application application) {
        return new BookmarkDatabase(application);
    }
}

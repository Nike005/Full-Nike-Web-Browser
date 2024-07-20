package androidx.media2;

import android.content.Context;
import android.net.Uri;
import androidx.core.util.Preconditions;
import androidx.media2.DataSourceDesc2;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UriDataSourceDesc2 extends DataSourceDesc2 {
    Uri mUri;
    Context mUriContext;
    List<HttpCookie> mUriCookies;
    Map<String, String> mUriHeader;

    public int getType() {
        return 3;
    }

    UriDataSourceDesc2(Builder builder) {
        super(builder);
        this.mUri = builder.mUri;
        this.mUriHeader = builder.mUriHeader;
        this.mUriCookies = builder.mUriCookies;
        this.mUriContext = builder.mUriContext;
    }

    public Uri getUri() {
        return this.mUri;
    }

    public Map<String, String> getUriHeaders() {
        if (this.mUriHeader == null) {
            return null;
        }
        return new HashMap(this.mUriHeader);
    }

    public List<HttpCookie> getUriCookies() {
        if (this.mUriCookies == null) {
            return null;
        }
        return new ArrayList(this.mUriCookies);
    }

    public Context getUriContext() {
        return this.mUriContext;
    }

    public static final class Builder extends DataSourceDesc2.Builder<Builder> {
        Uri mUri;
        Context mUriContext;
        List<HttpCookie> mUriCookies;
        Map<String, String> mUriHeader;

        public Builder(Context context, Uri uri) {
            Preconditions.checkNotNull(context, "context cannot be null");
            Preconditions.checkNotNull(uri, "uri cannot be null");
            this.mUri = uri;
            this.mUriContext = context;
        }

        public Builder(Context context, Uri uri, Map<String, String> map, List<HttpCookie> list) {
            CookieHandler cookieHandler;
            Preconditions.checkNotNull(context, "context cannot be null");
            Preconditions.checkNotNull(uri);
            if (list == null || (cookieHandler = CookieHandler.getDefault()) == null || (cookieHandler instanceof CookieManager)) {
                this.mUri = uri;
                if (map != null) {
                    this.mUriHeader = new HashMap(map);
                }
                if (list != null) {
                    this.mUriCookies = new ArrayList(list);
                }
                this.mUriContext = context;
                return;
            }
            throw new IllegalArgumentException("The cookie handler has to be of CookieManager type when cookies are provided.");
        }

        public UriDataSourceDesc2 build() {
            return new UriDataSourceDesc2(this);
        }
    }
}

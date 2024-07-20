package acr.browser.lightning.search;

import acr.browser.lightning.database.HistoryItem;
import acr.browser.lightning.utils.C3245Utils;
import acr.browser.lightning.utils.FileUtils;
import android.app.Application;
import android.text.TextUtils;
import android.util.Log;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

abstract class BaseSuggestionsModel {
    private static final String DEFAULT_LANGUAGE = "en";
    /* access modifiers changed from: private */
    public static final long INTERVAL_DAY = TimeUnit.DAYS.toSeconds(1);
    static final int MAX_RESULTS = 5;
    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Response.Builder newBuilder = chain.proceed(chain.request()).newBuilder();
            return newBuilder.header("cache-control", "max-age=" + BaseSuggestionsModel.INTERVAL_DAY + ", max-stale=" + BaseSuggestionsModel.INTERVAL_DAY).build();
        }
    };
    private static final String TAG = "BaseSuggestionsModel";
    private final CacheControl mCacheControl;
    private final String mEncoding;
    private final OkHttpClient mHttpClient;
    private final String mLanguage = getLanguage();

    /* access modifiers changed from: protected */
    public abstract String createQueryUrl(String str, String str2);

    /* access modifiers changed from: protected */
    public abstract void parseResults(InputStream inputStream, List<HistoryItem> list) throws Exception;

    BaseSuggestionsModel(Application application, String str) {
        this.mEncoding = str;
        this.mHttpClient = new OkHttpClient.Builder().cache(new Cache(new File(application.getCacheDir(), "suggestion_responses"), FileUtils.megabytesToBytes(1))).addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR).build();
        this.mCacheControl = new CacheControl.Builder().maxStale(1, TimeUnit.DAYS).build();
    }

    private static String getLanguage() {
        String language = Locale.getDefault().getLanguage();
        return TextUtils.isEmpty(language) ? DEFAULT_LANGUAGE : language;
    }

    /* access modifiers changed from: package-private */
    public List<HistoryItem> getResults(String str) {
        ArrayList arrayList = new ArrayList(5);
        try {
            str = URLEncoder.encode(str, this.mEncoding);
        } catch (UnsupportedEncodingException e) {
            Log.e(TAG, "Unable to encode the URL", e);
        }
        InputStream downloadSuggestionsForQuery = downloadSuggestionsForQuery(str, this.mLanguage);
        if (downloadSuggestionsForQuery == null) {
            return arrayList;
        }
        try {
            parseResults(downloadSuggestionsForQuery, arrayList);
            return arrayList;
        } catch (Exception e2) {
            Log.e(TAG, "Unable to parse results", e2);
            return arrayList;
        } finally {
            C3245Utils.close((Closeable) downloadSuggestionsForQuery);
        }
    }

    private InputStream downloadSuggestionsForQuery(String str, String str2) {
        try {
            ResponseBody body = this.mHttpClient.newCall(new Request.Builder().url(new URL(createQueryUrl(str, str2))).addHeader("Accept-Charset", this.mEncoding).cacheControl(this.mCacheControl).build()).execute().body();
            if (body != null) {
                return body.byteStream();
            }
            return null;
        } catch (IOException e) {
            Log.e(TAG, "Problem getting search suggestions", e);
            return null;
        }
    }
}

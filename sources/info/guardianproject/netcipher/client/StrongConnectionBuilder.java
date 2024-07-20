package info.guardianproject.netcipher.client;

import android.content.Context;
import android.content.Intent;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import javax.net.ssl.HttpsURLConnection;

public class StrongConnectionBuilder extends StrongBuilderBase<StrongConnectionBuilder, HttpURLConnection> {
    private URL url;

    public static StrongConnectionBuilder forMaxSecurity(Context context) throws Exception {
        return (StrongConnectionBuilder) new StrongConnectionBuilder(context).withBestProxy();
    }

    public StrongConnectionBuilder(Context context) {
        super(context);
    }

    public StrongConnectionBuilder(StrongConnectionBuilder strongConnectionBuilder) {
        super((StrongBuilderBase) strongConnectionBuilder);
        this.url = strongConnectionBuilder.url;
    }

    public StrongConnectionBuilder connectTo(String str) throws MalformedURLException {
        connectTo(new URL(str));
        return this;
    }

    public StrongConnectionBuilder connectTo(URL url2) {
        this.url = url2;
        return this;
    }

    public HttpURLConnection build(Intent intent) throws IOException {
        return buildForUrl(intent, this.url);
    }

    /* access modifiers changed from: protected */
    public String get(Intent intent, HttpURLConnection httpURLConnection, String str) throws Exception {
        return slurp(buildForUrl(intent, new URL(str)).getInputStream());
    }

    private HttpURLConnection buildForUrl(Intent intent, URL url2) throws IOException {
        URLConnection uRLConnection;
        Proxy buildProxy = buildProxy(intent);
        if (buildProxy == null) {
            uRLConnection = url2.openConnection();
        } else {
            uRLConnection = url2.openConnection(buildProxy);
        }
        if ((uRLConnection instanceof HttpsURLConnection) && this.sslContext != null) {
            ((HttpsURLConnection) uRLConnection).setSSLSocketFactory(buildSocketFactory());
        }
        return (HttpURLConnection) uRLConnection;
    }

    public static String slurp(InputStream inputStream) throws IOException {
        char[] cArr = new char[128];
        StringBuilder sb = new StringBuilder();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
        while (true) {
            int read = inputStreamReader.read(cArr, 0, 128);
            if (read < 0) {
                inputStreamReader.close();
                return sb.toString();
            }
            sb.append(cArr, 0, read);
        }
    }
}

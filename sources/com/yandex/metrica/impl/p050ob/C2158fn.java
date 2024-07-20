package com.yandex.metrica.impl.p050ob;

import com.android.volley.toolbox.HttpClientStack;
import com.mopub.common.Constants;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

/* renamed from: com.yandex.metrica.impl.ob.fn */
public class C2158fn implements C2157fm {

    /* renamed from: a */
    private final SSLSocketFactory f3719a;

    public C2158fn() {
        this((SSLSocketFactory) null);
    }

    public C2158fn(SSLSocketFactory sSLSocketFactory) {
        this.f3719a = sSLSocketFactory;
    }

    /* renamed from: a */
    public HttpResponse mo17766a(C2169fu<?> fuVar) throws IOException, C2162fr {
        SSLSocketFactory sSLSocketFactory;
        String a = fuVar.mo17764a();
        HashMap hashMap = new HashMap();
        hashMap.putAll(fuVar.mo17765b());
        URL url = new URL(a);
        HttpURLConnection a2 = mo17768a(url);
        int n = fuVar.mo17791n();
        a2.setConnectTimeout(n);
        a2.setReadTimeout(n);
        a2.setUseCaches(false);
        a2.setDoInput(true);
        if (Constants.HTTPS.equals(url.getProtocol()) && (sSLSocketFactory = this.f3719a) != null) {
            ((HttpsURLConnection) a2).setSSLSocketFactory(sSLSocketFactory);
        }
        for (String str : hashMap.keySet()) {
            a2.addRequestProperty(str, (String) hashMap.get(str));
        }
        switch (fuVar.mo17781d()) {
            case -1:
                byte[] j = fuVar.mo17787j();
                if (j != null) {
                    a2.setDoOutput(true);
                    a2.setRequestMethod("POST");
                    a2.addRequestProperty("Content-Type", fuVar.mo17786i());
                    DataOutputStream dataOutputStream = new DataOutputStream(a2.getOutputStream());
                    dataOutputStream.write(j);
                    dataOutputStream.close();
                    break;
                }
                break;
            case 0:
                a2.setRequestMethod("GET");
                break;
            case 1:
                a2.setRequestMethod("POST");
                m5708a(a2, fuVar);
                break;
            case 2:
                a2.setRequestMethod("PUT");
                m5708a(a2, fuVar);
                break;
            case 3:
                a2.setRequestMethod("DELETE");
                break;
            case 4:
                a2.setRequestMethod("HEAD");
                break;
            case 5:
                a2.setRequestMethod("OPTIONS");
                break;
            case 6:
                a2.setRequestMethod("TRACE");
                break;
            case 7:
                a2.setRequestMethod(HttpClientStack.HttpPatch.METHOD_NAME);
                m5708a(a2, fuVar);
                break;
            default:
                throw new IllegalStateException("Unknown method type.");
        }
        ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        if (a2.getResponseCode() != -1) {
            BasicHttpResponse basicHttpResponse = new BasicHttpResponse(new BasicStatusLine(protocolVersion, a2.getResponseCode(), a2.getResponseMessage()));
            basicHttpResponse.setEntity(m5707a(a2));
            for (Map.Entry entry : a2.getHeaderFields().entrySet()) {
                if (entry.getKey() != null) {
                    basicHttpResponse.addHeader(new BasicHeader((String) entry.getKey(), (String) ((List) entry.getValue()).get(0)));
                }
            }
            return basicHttpResponse;
        }
        throw new IOException("Could not retrieve response code from HttpUrlConnection.");
    }

    /* renamed from: a */
    private static HttpEntity m5707a(HttpURLConnection httpURLConnection) {
        InputStream inputStream;
        BasicHttpEntity basicHttpEntity = new BasicHttpEntity();
        try {
            inputStream = httpURLConnection.getInputStream();
        } catch (IOException unused) {
            inputStream = httpURLConnection.getErrorStream();
        }
        basicHttpEntity.setContent(inputStream);
        basicHttpEntity.setContentLength((long) httpURLConnection.getContentLength());
        basicHttpEntity.setContentEncoding(httpURLConnection.getContentEncoding());
        basicHttpEntity.setContentType(httpURLConnection.getContentType());
        return basicHttpEntity;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public HttpURLConnection mo17768a(URL url) throws IOException {
        return (HttpURLConnection) url.openConnection();
    }

    /* renamed from: a */
    private static void m5708a(HttpURLConnection httpURLConnection, C2169fu<?> fuVar) throws IOException, C2162fr {
        byte[] c = fuVar.mo17771c();
        if (c != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", fuVar.mo17790m());
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(c);
            dataOutputStream.close();
        }
    }
}

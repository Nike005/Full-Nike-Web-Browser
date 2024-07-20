package com.yandex.metrica.impl.p050ob;

import com.android.volley.toolbox.HttpClientStack;
import java.io.IOException;
import java.net.URI;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpTrace;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/* renamed from: com.yandex.metrica.impl.ob.fl */
public class C2155fl implements C2157fm {

    /* renamed from: a */
    protected final HttpClient f3718a;

    public C2155fl(HttpClient httpClient) {
        this.f3718a = httpClient;
    }

    /* renamed from: a */
    private static void m5704a(HttpUriRequest httpUriRequest, Map<String, String> map) {
        for (String next : map.keySet()) {
            httpUriRequest.setHeader(next, map.get(next));
        }
    }

    /* renamed from: a */
    private static void m5703a(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, C2169fu<?> fuVar) throws C2162fr {
        byte[] c = fuVar.mo17771c();
        if (c != null) {
            httpEntityEnclosingRequestBase.setEntity(new ByteArrayEntity(c));
        }
    }

    /* renamed from: com.yandex.metrica.impl.ob.fl$a */
    public static final class C2156a extends HttpEntityEnclosingRequestBase {
        public String getMethod() {
            return HttpClientStack.HttpPatch.METHOD_NAME;
        }

        public C2156a() {
        }

        public C2156a(String str) {
            setURI(URI.create(str));
        }
    }

    /* renamed from: a */
    public HttpResponse mo17766a(C2169fu<?> fuVar) throws IOException, C2162fr {
        C2156a aVar;
        switch (fuVar.mo17781d()) {
            case -1:
                byte[] j = fuVar.mo17787j();
                if (j == null) {
                    aVar = new HttpGet(fuVar.mo17764a());
                    break;
                } else {
                    C2156a httpPost = new HttpPost(fuVar.mo17764a());
                    httpPost.addHeader("Content-Type", fuVar.mo17786i());
                    httpPost.setEntity(new ByteArrayEntity(j));
                    aVar = httpPost;
                    break;
                }
            case 0:
                aVar = new HttpGet(fuVar.mo17764a());
                break;
            case 1:
                aVar = new HttpPost(fuVar.mo17764a());
                aVar.addHeader("Content-Type", fuVar.mo17790m());
                m5703a((HttpEntityEnclosingRequestBase) aVar, fuVar);
                break;
            case 2:
                aVar = new HttpPut(fuVar.mo17764a());
                aVar.addHeader("Content-Type", fuVar.mo17790m());
                m5703a((HttpEntityEnclosingRequestBase) aVar, fuVar);
                break;
            case 3:
                aVar = new HttpDelete(fuVar.mo17764a());
                break;
            case 4:
                aVar = new HttpHead(fuVar.mo17764a());
                break;
            case 5:
                aVar = new HttpOptions(fuVar.mo17764a());
                break;
            case 6:
                aVar = new HttpTrace(fuVar.mo17764a());
                break;
            case 7:
                aVar = new C2156a(fuVar.mo17764a());
                aVar.addHeader("Content-Type", fuVar.mo17790m());
                m5703a((HttpEntityEnclosingRequestBase) aVar, fuVar);
                break;
            default:
                throw new IllegalStateException("Unknown request method.");
        }
        m5704a((HttpUriRequest) aVar, fuVar.mo17765b());
        HttpParams params = aVar.getParams();
        int n = fuVar.mo17791n();
        HttpConnectionParams.setConnectionTimeout(params, 5000);
        HttpConnectionParams.setSoTimeout(params, n);
        return this.f3718a.execute(aVar);
    }
}

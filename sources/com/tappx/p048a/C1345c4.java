package com.tappx.p048a;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appnext.core.C4924Ad;
import com.mopub.common.AdType;
import com.tappx.p048a.C1493k4;
import com.tappx.p048a.C1510l3;
import com.tappx.p048a.C1553o3;
import com.tappx.p048a.C1568p3;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import org.htmlcleaner.CleanerProperties;
import org.json.JSONObject;

/* renamed from: com.tappx.a.c4 */
public class C1345c4 {

    /* renamed from: a */
    private final C1328b4 f1666a;

    /* renamed from: b */
    private final C1493k4 f1667b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public C1353h f1668c;

    /* renamed from: d */
    private C1589q4 f1669d;

    /* renamed from: e */
    private C1427g4 f1670e;
    /* access modifiers changed from: private */

    /* renamed from: f */
    public boolean f1671f;

    /* renamed from: g */
    private boolean f1672g;

    /* renamed from: h */
    private final WebViewClient f1673h;

    /* renamed from: com.tappx.a.c4$a */
    class C1346a extends WebChromeClient {
        C1346a() {
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            if (C1345c4.this.f1668c != null) {
                return C1345c4.this.f1668c.mo15640a(consoleMessage);
            }
            return super.onConsoleMessage(consoleMessage);
        }

        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            if (C1345c4.this.f1668c != null) {
                return C1345c4.this.f1668c.mo15641a(str2, jsResult);
            }
            return super.onJsAlert(webView, str, str2, jsResult);
        }

        public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
            super.onShowCustomView(view, customViewCallback);
        }
    }

    /* renamed from: com.tappx.a.c4$b */
    class C1347b implements C1553o3.C1554a {
        C1347b() {
        }

        /* renamed from: a */
        public void mo15627a() {
            boolean unused = C1345c4.this.f1671f = true;
        }
    }

    /* renamed from: com.tappx.a.c4$c */
    class C1348c implements View.OnTouchListener {

        /* renamed from: a */
        final /* synthetic */ C1553o3 f1676a;

        C1348c(C1345c4 c4Var, C1553o3 o3Var) {
            this.f1676a = o3Var;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            this.f1676a.mo16037a(motionEvent);
            int action = motionEvent.getAction();
            if ((action != 0 && action != 1) || view.hasFocus()) {
                return false;
            }
            view.requestFocus();
            return false;
        }
    }

    /* renamed from: com.tappx.a.c4$d */
    class C1349d implements C1510l3.C1511a {
        C1349d() {
        }

        /* renamed from: b */
        public void mo15629b(boolean z) {
            if (C1345c4.this.f1668c != null) {
                C1345c4.this.f1668c.mo15643b(z);
            }
        }
    }

    /* renamed from: com.tappx.a.c4$e */
    class C1350e extends WebViewClient {
        C1350e() {
        }

        public void onPageFinished(WebView webView, String str) {
            C1345c4.this.m2365f();
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            C1475j4.m2885a("Error: " + str);
            super.onReceivedError(webView, i, str, str2);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            return C1345c4.this.mo15616a(str);
        }
    }

    /* renamed from: com.tappx.a.c4$f */
    class C1351f implements C1493k4.C1496c {

        /* renamed from: a */
        final /* synthetic */ C1438h4 f1679a;

        C1351f(C1438h4 h4Var) {
            this.f1679a = h4Var;
        }

        /* renamed from: a */
        public void mo15633a(C1413f4 f4Var) {
            C1345c4.this.m2358a(this.f1679a, f4Var.getMessage());
        }
    }

    /* renamed from: com.tappx.a.c4$g */
    static /* synthetic */ class C1352g {

        /* renamed from: a */
        static final /* synthetic */ int[] f1681a;

        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|(3:19|20|22)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.tappx.a.h4[] r0 = com.tappx.p048a.C1438h4.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f1681a = r0
                com.tappx.a.h4 r1 = com.tappx.p048a.C1438h4.CLOSE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f1681a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.tappx.a.h4 r1 = com.tappx.p048a.C1438h4.RESIZE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f1681a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.tappx.a.h4 r1 = com.tappx.p048a.C1438h4.EXPAND     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f1681a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.tappx.a.h4 r1 = com.tappx.p048a.C1438h4.USE_CUSTOM_CLOSE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f1681a     // Catch:{ NoSuchFieldError -> 0x003e }
                com.tappx.a.h4 r1 = com.tappx.p048a.C1438h4.SET_ORIENTATION_PROPERTIES     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = f1681a     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.tappx.a.h4 r1 = com.tappx.p048a.C1438h4.CREATE_CALENDAR_EVENT     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = f1681a     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.tappx.a.h4 r1 = com.tappx.p048a.C1438h4.OPEN     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = f1681a     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.tappx.a.h4 r1 = com.tappx.p048a.C1438h4.STORE_PICTURE     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = f1681a     // Catch:{ NoSuchFieldError -> 0x006c }
                com.tappx.a.h4 r1 = com.tappx.p048a.C1438h4.PLAY_VIDEO     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = f1681a     // Catch:{ NoSuchFieldError -> 0x0078 }
                com.tappx.a.h4 r1 = com.tappx.p048a.C1438h4.UNSPECIFIED     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.tappx.p048a.C1345c4.C1352g.<clinit>():void");
        }
    }

    /* renamed from: com.tappx.a.c4$h */
    public interface C1353h {
        /* renamed from: a */
        void mo15634a();

        /* renamed from: a */
        void mo15635a(int i, int i2, int i3, int i4, C1568p3.C1572d dVar, boolean z);

        /* renamed from: a */
        void mo15636a(URI uri);

        /* renamed from: a */
        void mo15637a(URI uri, boolean z);

        /* renamed from: a */
        void mo15638a(boolean z);

        /* renamed from: a */
        void mo15639a(boolean z, C1512l4 l4Var);

        /* renamed from: a */
        boolean mo15640a(ConsoleMessage consoleMessage);

        /* renamed from: a */
        boolean mo15641a(String str, JsResult jsResult);

        /* renamed from: b */
        void mo15642b();

        /* renamed from: b */
        void mo15643b(boolean z);

        /* renamed from: c */
        void mo15644c();
    }

    C1345c4(C1328b4 b4Var) {
        this(b4Var, new C1493k4());
    }

    /* renamed from: e */
    private boolean m2363e(String str) {
        if (CleanerProperties.BOOL_ATT_TRUE.equals(str)) {
            return true;
        }
        if ("false".equals(str)) {
            return false;
        }
        throw new C1413f4("Invalid boolean parameter: " + str);
    }

    /* access modifiers changed from: private */
    /* renamed from: f */
    public void m2365f() {
        if (!this.f1672g) {
            this.f1672g = true;
            C1353h hVar = this.f1668c;
            if (hVar != null) {
                hVar.mo15644c();
            }
        }
    }

    /* renamed from: g */
    private int m2366g(String str) {
        try {
            return Integer.parseInt(str, 10);
        } catch (NumberFormatException unused) {
            throw new C1413f4("Invalid param: " + str);
        }
    }

    /* renamed from: c */
    public void mo15619c(String str) {
        if (this.f1669d == null) {
            C1475j4.m2885a("MRAID bridge called setContentHtml before WebView was attached");
            return;
        }
        C1427g4 g4Var = this.f1670e;
        if (g4Var != null) {
            str = g4Var.mo15833a(str);
        }
        this.f1672g = false;
        this.f1669d.loadDataWithBaseURL((String) null, str, "text/html", "UTF-8", (String) null);
    }

    /* renamed from: d */
    public void mo15621d(String str) {
        C1589q4 q4Var = this.f1669d;
        if (q4Var == null) {
            C1475j4.m2885a("MRAID bridge called setContentHtml while WebView was not attached");
            return;
        }
        this.f1672g = false;
        q4Var.loadUrl(str);
    }

    C1345c4(C1328b4 b4Var, C1493k4 k4Var) {
        this.f1673h = new C1350e();
        this.f1666a = b4Var;
        this.f1667b = k4Var;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public void mo15617b(String str) {
        if (this.f1669d == null) {
            C1475j4.m2885a("Attempted to inject Javascript into MRAID WebView while was not attached:\n\t" + str);
            return;
        }
        C1475j4.m2890c("Injecting Javascript into MRAID WebView:\t" + str);
        C1589q4 q4Var = this.f1669d;
        q4Var.loadUrl("javascript:" + str);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo15609a(C1353h hVar) {
        this.f1668c = hVar;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo15612a(C1589q4 q4Var) {
        this.f1669d = q4Var;
        this.f1670e = new C1427g4(q4Var.getContext());
        this.f1669d.getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= 17 && (this.f1666a == C1328b4.INTERSTITIAL || C1547o.f2136b)) {
            q4Var.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        this.f1670e.mo15834a((WebView) q4Var);
        this.f1669d.setScrollContainer(false);
        this.f1669d.setVerticalScrollBarEnabled(false);
        this.f1669d.setHorizontalScrollBarEnabled(false);
        this.f1669d.setBackgroundColor(-16777216);
        this.f1669d.setWebViewClient(this.f1673h);
        this.f1669d.setWebChromeClient(new C1346a());
        C1553o3 o3Var = new C1553o3();
        o3Var.mo16038a((C1553o3.C1554a) new C1347b());
        this.f1669d.setOnTouchListener(new C1348c(this, o3Var));
        this.f1669d.setVisibilityChangedListener(new C1349d());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: e */
    public void mo15623e() {
        mo15617b("mraidbridge.notifyReadyEvent();");
    }

    /* renamed from: f */
    private C1512l4 m2364f(String str) {
        if (C4924Ad.ORIENTATION_PORTRAIT.equals(str)) {
            return C1512l4.PORTRAIT;
        }
        if (C4924Ad.ORIENTATION_LANDSCAPE.equals(str)) {
            return C1512l4.LANDSCAPE;
        }
        if ("none".equals(str)) {
            return C1512l4.NONE;
        }
        throw new C1413f4("Invalid orientation '" + str + "'");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public boolean mo15622d() {
        C1589q4 q4Var = this.f1669d;
        return q4Var != null && q4Var.mo15941b();
    }

    /* renamed from: b */
    private String m2361b(Rect rect) {
        return rect.width() + "," + rect.height();
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean mo15618b() {
        return this.f1669d != null;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public boolean mo15620c() {
        return this.f1672g;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo15607a() {
        this.f1669d = null;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m2358a(C1438h4 h4Var, String str) {
        mo15617b("window.mraidbridge.notifyErrorEvent(" + JSONObject.quote(h4Var.mo15847a()) + ", " + JSONObject.quote(str) + ")");
    }

    /* renamed from: a */
    private void m2357a(C1438h4 h4Var) {
        mo15617b("window.mraidbridge.nativeCallComplete(" + JSONObject.quote(h4Var.mo15847a()) + ")");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo15616a(String str) {
        try {
            new URI(str);
            Uri parse = Uri.parse(str);
            String scheme = parse.getScheme();
            String host = parse.getHost();
            if ("tappx".equalsIgnoreCase(scheme)) {
                if ("loadFailed".equalsIgnoreCase(host)) {
                    this.f1668c.mo15642b();
                }
                return true;
            } else if (AdType.MRAID.equals(scheme)) {
                C1438h4 a = C1438h4.m2794a(host);
                try {
                    mo15610a(a, m2355a(parse));
                } catch (C1413f4 e) {
                    m2358a(a, e.getMessage());
                }
                m2357a(a);
                return true;
            } else {
                if (this.f1671f) {
                    this.f1671f = false;
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(Uri.parse(str));
                    intent.addFlags(268435456);
                    try {
                        if (this.f1669d == null) {
                            C1475j4.m2885a("WebView was detached. Unable to load a URL");
                            return true;
                        }
                        this.f1669d.getContext().startActivity(intent);
                        this.f1668c.mo15636a((URI) null);
                        return true;
                    } catch (ActivityNotFoundException unused) {
                        C1475j4.m2885a("No activity found to handle this URL " + str);
                    }
                }
                return false;
            }
        } catch (URISyntaxException unused2) {
            C1475j4.m2892d("Invalid MRAID URL: " + str);
            m2358a(C1438h4.UNSPECIFIED, "Mraid command sent an invalid URL");
            return true;
        }
    }

    /* renamed from: a */
    private Map<String, String> m2355a(Uri uri) {
        HashMap hashMap = new HashMap();
        for (String next : uri.getQueryParameterNames()) {
            hashMap.put(next, TextUtils.join(",", uri.getQueryParameters(next)));
        }
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo15610a(C1438h4 h4Var, Map<String, String> map) {
        if (h4Var.mo15848a(this.f1666a) && !this.f1671f) {
            throw new C1413f4("Click required");
        } else if (this.f1668c == null) {
            throw new C1413f4("Invalid state");
        } else if (this.f1669d != null) {
            switch (C1352g.f1681a[h4Var.ordinal()]) {
                case 1:
                    this.f1668c.mo15634a();
                    return;
                case 2:
                    this.f1668c.mo15635a(m2351a(m2366g(map.get("width")), 0, (int) DefaultOggSeeker.MATCH_BYTE_RANGE), m2351a(m2366g(map.get("height")), 0, (int) DefaultOggSeeker.MATCH_BYTE_RANGE), m2351a(m2366g(map.get("offsetX")), -100000, (int) DefaultOggSeeker.MATCH_BYTE_RANGE), m2351a(m2366g(map.get("offsetY")), -100000, (int) DefaultOggSeeker.MATCH_BYTE_RANGE), m2353a(map.get("customClosePosition"), C1568p3.C1572d.TOP_RIGHT), m2360a(map.get("allowOffscreen"), true));
                    return;
                case 3:
                    this.f1668c.mo15637a(C1527m4.m3038a(map.get("url"), (URI) null), m2360a(map.get("shouldUseCustomClose"), false));
                    return;
                case 4:
                    this.f1668c.mo15638a(m2360a(map.get("shouldUseCustomClose"), false));
                    return;
                case 5:
                    this.f1668c.mo15639a(m2363e(map.get("allowOrientationChange")), m2364f(map.get("forceOrientation")));
                    return;
                case 6:
                    this.f1667b.mo15912a(this.f1669d.getContext(), map);
                    return;
                case 7:
                    URI b = C1527m4.m3043b(map.get("url"));
                    this.f1667b.mo15911a(this.f1669d.getContext(), b);
                    this.f1668c.mo15636a(b);
                    return;
                case 8:
                    this.f1667b.mo15915b(this.f1669d.getContext(), C1527m4.m3043b(map.get("uri")).toString(), new C1351f(h4Var));
                    return;
                case 9:
                    this.f1667b.mo15909a(this.f1669d.getContext(), C1527m4.m3043b(map.get("uri")).toString());
                    return;
                case 10:
                    throw new C1413f4("Unspecified command");
                default:
                    return;
            }
        } else {
            throw new C1413f4("Destroyed");
        }
    }

    /* renamed from: a */
    private C1568p3.C1572d m2353a(String str, C1568p3.C1572d dVar) {
        if (TextUtils.isEmpty(str)) {
            return dVar;
        }
        if (str.equals("top-left")) {
            return C1568p3.C1572d.TOP_LEFT;
        }
        if (str.equals("top-right")) {
            return C1568p3.C1572d.TOP_RIGHT;
        }
        if (str.equals("center")) {
            return C1568p3.C1572d.CENTER;
        }
        if (str.equals("bottom-left")) {
            return C1568p3.C1572d.BOTTOM_LEFT;
        }
        if (str.equals("bottom-right")) {
            return C1568p3.C1572d.BOTTOM_RIGHT;
        }
        if (str.equals("top-center")) {
            return C1568p3.C1572d.TOP_CENTER;
        }
        if (str.equals("bottom-center")) {
            return C1568p3.C1572d.BOTTOM_CENTER;
        }
        throw new C1413f4("Invalid position '" + str + "'");
    }

    /* renamed from: a */
    private int m2351a(int i, int i2, int i3) {
        if (i >= i2 && i <= i3) {
            return i;
        }
        throw new C1413f4("param out of range: " + i);
    }

    /* renamed from: a */
    private boolean m2360a(String str, boolean z) {
        return str == null ? z : m2363e(str);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo15614a(boolean z) {
        mo15617b("mraidbridge.setIsViewable(" + z + ")");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo15608a(C1328b4 b4Var) {
        mo15617b("mraidbridge.setPlacementType(" + JSONObject.quote(b4Var.mo15572a()) + ")");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo15613a(C1659v4 v4Var) {
        mo15617b("mraidbridge.setState(" + JSONObject.quote(v4Var.mo16229a()) + ")");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo15615a(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        mo15617b("mraidbridge.setSupports(" + z + "," + z2 + "," + z3 + "," + z4 + "," + z5 + ")");
    }

    /* renamed from: a */
    private String m2354a(Rect rect) {
        return rect.left + "," + rect.top + "," + rect.width() + "," + rect.height();
    }

    /* renamed from: a */
    public void mo15611a(C1545n4 n4Var) {
        mo15617b("mraidbridge.setScreenSize(" + m2361b(n4Var.mo16027g()) + ");mraidbridge.setMaxSize(" + m2361b(n4Var.mo16026f()) + ");mraidbridge.setCurrentPosition(" + m2354a(n4Var.mo16020b()) + ");mraidbridge.setDefaultPosition(" + m2354a(n4Var.mo16024d()) + ")");
        StringBuilder sb = new StringBuilder();
        sb.append("mraidbridge.notifySizeChangeEvent(");
        sb.append(m2361b(n4Var.mo16017a()));
        sb.append(")");
        mo15617b(sb.toString());
    }
}

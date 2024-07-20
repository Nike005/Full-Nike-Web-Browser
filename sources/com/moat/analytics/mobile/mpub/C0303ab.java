package com.moat.analytics.mobile.mpub;

import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.moat.analytics.mobile.mpub.p002a.p004b.C0301a;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

/* renamed from: com.moat.analytics.mobile.mpub.ab */
class C0303ab {

    /* renamed from: a */
    private static final LinkedHashSet<String> f60a = new LinkedHashSet<>();

    C0303ab() {
    }

    /* renamed from: a */
    static C0301a<WebView> m87a(ViewGroup viewGroup, boolean z) {
        if (viewGroup == null) {
            try {
                return C0301a.m80a();
            } catch (Exception unused) {
                return C0301a.m80a();
            }
        } else if (viewGroup instanceof WebView) {
            return C0301a.m81a((WebView) viewGroup);
        } else {
            LinkedList linkedList = new LinkedList();
            linkedList.add(viewGroup);
            WebView webView = null;
            int i = 0;
            while (!linkedList.isEmpty() && i < 100) {
                i++;
                ViewGroup viewGroup2 = (ViewGroup) linkedList.poll();
                int childCount = viewGroup2.getChildCount();
                int i2 = 0;
                while (true) {
                    if (i2 >= childCount) {
                        break;
                    }
                    View childAt = viewGroup2.getChildAt(i2);
                    if (childAt instanceof WebView) {
                        C0336p.m228a(3, "WebViewHound", (Object) childAt, "Found WebView");
                        if (z || m88a(String.valueOf(childAt.hashCode()))) {
                            if (webView != null) {
                                C0336p.m228a(3, "WebViewHound", (Object) childAt, "Ambiguous ad container: multiple WebViews reside within it.");
                                C0336p.m231a("[ERROR] ", "WebAdTracker not created, ambiguous ad container: multiple WebViews reside within it");
                                webView = null;
                                break;
                            }
                            webView = (WebView) childAt;
                        }
                    }
                    if (childAt instanceof ViewGroup) {
                        linkedList.add((ViewGroup) childAt);
                    }
                    i2++;
                }
            }
            return C0301a.m82b(webView);
        }
    }

    /* renamed from: a */
    private static boolean m88a(String str) {
        try {
            boolean add = f60a.add(str);
            if (f60a.size() > 50) {
                Iterator it = f60a.iterator();
                for (int i = 0; i < 25 && it.hasNext(); i++) {
                    it.next();
                    it.remove();
                }
            }
            C0336p.m228a(3, "WebViewHound", (Object) null, add ? "Newly Found WebView" : "Already Found WebView");
            return add;
        } catch (Exception e) {
            C0330n.m214a(e);
            return false;
        }
    }
}

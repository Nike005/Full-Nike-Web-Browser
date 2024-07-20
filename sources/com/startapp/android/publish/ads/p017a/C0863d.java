package com.startapp.android.publish.ads.p017a;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.mopub.common.AdType;
import com.p088b.p089a.p090a.p091a.p093b.C5109a;
import com.startapp.android.publish.adsCommon.Utils.C1060h;
import com.startapp.android.publish.adsCommon.p033f.C1130d;
import com.startapp.android.publish.adsCommon.p033f.C1132f;
import com.startapp.android.publish.adsCommon.p034g.p035a.C1137a;
import com.startapp.android.publish.adsCommon.p034g.p035a.C1139b;
import com.startapp.android.publish.adsCommon.p034g.p035a.C1140c;
import com.startapp.android.publish.adsCommon.p034g.p035a.C1141d;
import com.startapp.android.publish.adsCommon.p034g.p035a.C1142e;
import com.startapp.android.publish.adsCommon.p034g.p036b.C1143a;
import com.startapp.android.publish.adsCommon.p034g.p036b.C1144b;
import com.startapp.android.publish.adsCommon.p034g.p037c.C1145a;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.common.p043a.C1265d;
import com.startapp.common.p043a.C1270g;
import java.util.Map;

/* renamed from: com.startapp.android.publish.ads.a.d */
/* compiled from: StartAppSDK */
public class C0863d extends C0855c {
    /* access modifiers changed from: private */

    /* renamed from: i */
    public C1141d f445i = C1141d.LOADING;

    /* renamed from: j */
    private DisplayMetrics f446j;
    /* access modifiers changed from: private */

    /* renamed from: k */
    public C0868b f447k;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public C1144b f448l;
    /* access modifiers changed from: private */

    /* renamed from: m */
    public C1145a f449m;

    /* renamed from: n */
    private ImageButton f450n;
    /* access modifiers changed from: private */

    /* renamed from: o */
    public boolean f451o = false;
    /* access modifiers changed from: private */

    /* renamed from: p */
    public boolean f452p = false;

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public boolean mo13806b(String str) {
        return false;
    }

    /* renamed from: a */
    public void mo13760a(Bundle bundle) {
        super.mo13760a(bundle);
        if (this.f446j == null) {
            this.f446j = new DisplayMetrics();
        }
        if (this.f448l == null) {
            this.f448l = new C1144b(mo13768b());
        }
        if (this.f449m == null) {
            this.f449m = new C1145a();
        }
        if (this.f447k == null) {
            this.f447k = new C0868b(new C1137a.C1138a() {
                /* renamed from: a */
                public boolean mo13819a(String str) {
                    return C0863d.this.mo13804a(str, true);
                }
            });
        }
    }

    /* renamed from: u */
    public void mo13791u() {
        super.mo13791u();
        this.f452p = true;
        if (this.f445i == C1141d.DEFAULT) {
            this.f447k.fireViewableChangeEvent();
        }
    }

    /* renamed from: a */
    public void mo13759a(Configuration configuration) {
        m530G();
    }

    /* renamed from: s */
    public void mo13789s() {
        this.f452p = false;
        if (this.f445i == C1141d.DEFAULT) {
            this.f447k.fireViewableChangeEvent();
        }
        super.mo13789s();
    }

    /* access modifiers changed from: protected */
    /* renamed from: x */
    public void mo13808x() {
        this.f426d.setWebViewClient(new C0867a(this.f447k));
        this.f426d.setWebChromeClient(new WebChromeClient() {
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                try {
                    if (consoleMessage.messageLevel() == ConsoleMessage.MessageLevel.ERROR) {
                        C1270g.m2078a("MraidMode", 6, "WebChromeClient console error: " + consoleMessage.message());
                        if (consoleMessage.message().contains(AdType.MRAID)) {
                            C1132f.m1527a(C0863d.this.mo13768b(), C1130d.EXCEPTION, "MraidMode.ConsoleError", consoleMessage.message(), "");
                        }
                    } else {
                        C1270g.m2078a("MraidMode", 3, "WebChromeClient console log: " + consoleMessage.message());
                    }
                } catch (Exception e) {
                    C1270g.m2078a("MraidMode", 6, "WebChromeClient onConsoleMessage Exception: " + e.getMessage());
                }
                return super.onConsoleMessage(consoleMessage);
            }
        });
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public boolean mo13804a(String str, boolean z) {
        C1270g.m2078a("MraidMode", 3, "adClicked with url: " + str);
        C1141d dVar = C1141d.HIDDEN;
        this.f445i = dVar;
        C1140c.m1543a(dVar, this.f426d);
        try {
            return super.mo13804a(str, z);
        } catch (Exception e) {
            Activity b = mo13768b();
            C1130d dVar2 = C1130d.EXCEPTION;
            C1132f.m1527a(b, dVar2, "MraidMode.adClicked", "url = [" + str + "], " + e.getMessage(), "");
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: G */
    public void m530G() {
        try {
            mo13768b().getWindowManager().getDefaultDisplay().getMetrics(this.f446j);
            C1140c.m1539a(mo13768b(), this.f446j.widthPixels, this.f446j.heightPixels, this.f426d);
            C1140c.m1546b(mo13768b(), this.f446j.widthPixels, this.f446j.heightPixels, this.f426d);
            C1140c.m1538a(mo13768b(), 0, 0, this.f446j.widthPixels, this.f446j.heightPixels, this.f426d);
            C1140c.m1545b(mo13768b(), 0, 0, this.f446j.widthPixels, this.f446j.heightPixels, this.f426d);
        } catch (Exception e) {
            C1132f.m1527a(mo13768b(), C1130d.EXCEPTION, "MraidMode.updateDisplayMetrics", e.getMessage(), "");
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: H */
    public void m531H() {
        try {
            ImageButton imageButton = new ImageButton(mo13768b());
            this.f450n = imageButton;
            imageButton.setBackgroundColor(0);
            this.f450n.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    C0863d.this.f447k.close();
                }
            });
            if (!this.f451o) {
                m532I();
            }
            int a = C1060h.m1162a((Context) mo13768b(), 50);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(a, a);
            layoutParams.addRule(10);
            layoutParams.addRule(11);
            this.f428f.addView(this.f450n, layoutParams);
        } catch (Exception e) {
            C1132f.m1527a(mo13768b(), C1130d.EXCEPTION, "MraidMode.addCloseRegion", e.getMessage(), "");
        }
    }

    /* renamed from: com.startapp.android.publish.ads.a.d$b */
    /* compiled from: StartAppSDK */
    private class C0868b extends C1137a {
        public C0868b(C1137a.C1138a aVar) {
            super(aVar);
        }

        public void close() {
            C1270g.m2078a("MraidMode", 3, "close");
            C1141d unused = C0863d.this.f445i = C1141d.HIDDEN;
            C1140c.m1543a(C0863d.this.f445i, C0863d.this.f426d);
            C0863d.this.f429g.run();
        }

        public void useCustomClose(String str) {
            C1270g.m2078a("MraidMode", 3, "useCustomClose: " + str);
            boolean parseBoolean = Boolean.parseBoolean(str);
            if (C0863d.this.f451o != parseBoolean) {
                boolean unused = C0863d.this.f451o = parseBoolean;
                if (parseBoolean) {
                    C0863d.this.m533J();
                } else {
                    C0863d.this.m532I();
                }
            }
        }

        public void setOrientationProperties(Map<String, String> map) {
            C1270g.m2078a("MraidMode", 3, "setOrientationProperties: " + map);
            boolean parseBoolean = Boolean.parseBoolean(map.get("allowOrientationChange"));
            String str = map.get("forceOrientation");
            if (C0863d.this.f449m.f1207a != parseBoolean || C0863d.this.f449m.f1208b != C1145a.m1559a(str)) {
                C0863d.this.f449m.f1207a = parseBoolean;
                C0863d.this.f449m.f1208b = C1145a.m1559a(str);
                applyOrientationProperties(C0863d.this.mo13768b(), C0863d.this.f449m);
            }
        }

        public boolean isFeatureSupported(String str) {
            return C0863d.this.f448l.mo14930a(str);
        }

        public void fireViewableChangeEvent() {
            C1140c.m1542a(C0863d.this.f426d, C0863d.this.f452p);
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: I */
    public void m532I() {
        try {
            if (this.f450n != null) {
                this.f450n.setImageDrawable(C1265d.m2055a(mo13768b().getResources(), "iVBORw0KGgoAAAANSUhEUgAAACAAAAAgCAYAAABzenr0AAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAA39pVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDY3IDc5LjE1Nzc0NywgMjAxNS8wMy8zMC0yMzo0MDo0MiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOk9yaWdpbmFsRG9jdW1lbnRJRD0ieG1wLmRpZDozODRkZTAxYi00OWRkLWM4NDYtYThkNC0wZWRiMDMwYTZlODAiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6QkE0Q0U2MUY2QzA0MTFFNUE3MkJGQjQ1MTkzOEYxQUUiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6QkE0Q0U2MUU2QzA0MTFFNUE3MkJGQjQ1MTkzOEYxQUUiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChXaW5kb3dzKSI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjlkZjAyMGU0LTNlYmUtZTY0ZC04YjRiLWM5ZWY4MTU4ZjFhYyIgc3RSZWY6ZG9jdW1lbnRJRD0iYWRvYmU6ZG9jaWQ6cGhvdG9zaG9wOmU1MzEzNDdlLTZjMDEtMTFlNS1hZGZlLThmMTBjZWYxMGRiZSIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PngNsEEAAANeSURBVHjatFfNS1tBEH+pUZOQ0B4i3sTSxHMRFNQoFBEP7dHgvyDiKWgguQra9F+oxqNiwOTQ+oFI1ZM3jSf1YK5FL41ooaKZzu+x+4gv2bx9Rgd+JNn5zO7s7IzH0CQiCvLHZ8YnxkfGe8ZbwS4zSowTxi/GT4/Hc2u8BLHjCOM745b06VboRJpx7GN8ZfyDxUqlQgcHB5RMJmloaIg6Ozupra3NBL5jDTzIQFYQdDOw5db5B8YxLDw+PtLKygr19PQQWDqIRqOUzWZNXUHH2rvBgr2M39C6uLig/v5+bcd2QLdUKskgYLNX57yvIL2zs0OhUOjZziU6Ojro8PBQBnGl3Alm+BknkMI54mybdS4BW3t7ezKIInzVCwDJYm4Zon4p5xLYzfPzcxlEpl7S3SNpmjlznZwQiXn/5CjEnTUzt5GBsbExamlpUfLBg0wjG8vLy3IXlqTzEAoH7m4kElEqTk1Nmfd7bW2tbhBYAw8ykFXZgQ9RJ1CsQghgEr/29/eVStPT09XFhdbX18nr9Vr81tZWyuVyFh+yMzMzSnvwJWjyDS+MYic2NzeV17O7u9vg2m79jsfjBv9bg7PbxOrqqjExMWHxIdvV1aW0V+VrFDtwhFCGh4cbnl0mk6kp+BsbGybsBNlGtkZGRqToEQK4xjfUc6csXlhYcHyFFhcXHe3Al6BrQz427e3tWldpfn5e6Rw83cIkHyvXAUAZb4SdsKZbPe0BaB+Bz+cjTiDlDmxtbZkybo9AKwn9fj9tb2875gBkINvIFnzJJMQ1PMV9GBgYUF6bQCBgFAoFY3x8/Ml6KpUy0un0kzXIQBY6KqrydapViPL5fM0/Rfcj+fhuJw5CqxBpleJYLEY3NzeW8dnZ2RoZrEmCLHQcSvGdWYrFe7CEFTwUqqjR85XLZUokEkoZ8CADWe3HqKoTcnyOdW5KI5m+vj56eHiQz3G0bkNyeXn5ag3J2dmZ/PffVC1Z8bVast3d3eqWLKDVlAaDwaadh8Nhvaa0XluOHg7n9lzn0MWRarfltp0oysEErRqGDTeDCbK9ajApuh7TxGiWERlrjWZzc3M0ODhYM5phDTzbaHb/rNHMFkhUNK13LobTv6K2RJ3se1yO519s4/k7wf5jG89/6I7n/wUYAGo3YtcprD4sAAAAAElFTkSuQmCC"));
                this.f450n.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
        } catch (Exception e) {
            C1132f.m1527a(mo13768b(), C1130d.EXCEPTION, "MraidMode.showDefaultCloseButton", e.getMessage(), "");
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: J */
    public void m533J() {
        try {
            if (this.f450n != null) {
                this.f450n.setImageResource(17170445);
            }
        } catch (Exception e) {
            C1132f.m1527a(mo13768b(), C1130d.EXCEPTION, "MraidMode.removeDefaultCloseButton", e.getMessage(), "");
        }
    }

    /* renamed from: com.startapp.android.publish.ads.a.d$a */
    /* compiled from: StartAppSDK */
    private class C0867a extends C1142e {
        public C0867a(C1139b bVar) {
            super(bVar);
        }

        public void onPageFinished(WebView webView, String str) {
            View a;
            super.onPageFinished(webView, str);
            if (C0863d.this.f445i == C1141d.LOADING) {
                C1140c.m1544a(AdType.INTERSTITIAL, webView);
                C1143a.m1551a(C0863d.this.mo13768b(), webView, C0863d.this.f448l);
                C0863d.this.m530G();
                C0863d.this.m531H();
                C1141d unused = C0863d.this.f445i = C1141d.DEFAULT;
                C1140c.m1543a(C0863d.this.f445i, webView);
                C1140c.m1540a(webView);
                if (C0863d.this.f452p) {
                    C0863d.this.f447k.fireViewableChangeEvent();
                }
                if (MetaData.getInstance().isOmsdkEnabled()) {
                    C0863d.this.f427e = null;
                    if (C0863d.this.f427e != null) {
                        if (!(C0863d.this.f403a == null || (a = C0863d.this.f403a.mo14712a()) == null)) {
                            C0863d.this.f427e.mo41831b(a);
                        }
                        C0863d.this.f427e.mo41829a(webView);
                        C0863d.this.f427e.mo41828a();
                        C5109a.m6983a(C0863d.this.f427e).mo41817a();
                    }
                }
            }
        }
    }
}

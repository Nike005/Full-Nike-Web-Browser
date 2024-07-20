package com.tappx.p048a;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import com.mopub.mraid.MraidNativeCommandHandler;
import com.tappx.p048a.C1598r3;
import com.tappx.sdk.android.VideoAdActivity;
import java.net.URI;
import java.util.Map;

/* renamed from: com.tappx.a.k4 */
public class C1493k4 {

    /* renamed from: a */
    private final C1714z3 f2012a;

    /* renamed from: com.tappx.a.k4$a */
    class C1494a implements DialogInterface.OnClickListener {

        /* renamed from: a */
        final /* synthetic */ Context f2013a;

        /* renamed from: b */
        final /* synthetic */ String f2014b;

        /* renamed from: c */
        final /* synthetic */ C1496c f2015c;

        C1494a(Context context, String str, C1496c cVar) {
            this.f2013a = context;
            this.f2014b = str;
            this.f2015c = cVar;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            C1493k4.this.mo15910a(this.f2013a, this.f2014b, this.f2015c);
        }
    }

    /* renamed from: com.tappx.a.k4$b */
    class C1495b implements C1598r3.C1600b {

        /* renamed from: a */
        final /* synthetic */ Context f2017a;

        /* renamed from: b */
        final /* synthetic */ C1496c f2018b;

        C1495b(C1493k4 k4Var, Context context, C1496c cVar) {
            this.f2017a = context;
            this.f2018b = cVar;
        }

        /* renamed from: a */
        public void mo15920a() {
            Toast.makeText(this.f2017a, "Image downloaded", 0).show();
        }

        /* renamed from: b */
        public void mo15921b() {
            Toast.makeText(this.f2017a, "Image download failed", 0).show();
            this.f2018b.mo15633a(new C1413f4("Download error"));
        }
    }

    /* renamed from: com.tappx.a.k4$c */
    interface C1496c {
        /* renamed from: a */
        void mo15633a(C1413f4 f4Var);
    }

    public C1493k4(C1714z3 z3Var) {
        this.f2012a = z3Var;
    }

    /* renamed from: a */
    public void mo15909a(Context context, String str) {
        VideoAdActivity.startMraid(context, str);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: b */
    public boolean mo15916b(Context context) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("sms:"));
        return C1588q3.m3285a(context, intent);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: c */
    public boolean mo15917c(Context context) {
        return "mounted".equals(Environment.getExternalStorageState()) && C1366d3.m2453a(context, "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: d */
    public boolean mo15918d(Context context) {
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:"));
        return C1588q3.m3285a(context, intent);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo15914a(Context context) {
        Intent type = new Intent("android.intent.action.INSERT").setType(MraidNativeCommandHandler.ANDROID_CALENDAR_CONTENT_TYPE);
        if (!(Build.VERSION.SDK_INT >= 14) || !C1588q3.m3285a(context, type)) {
            return false;
        }
        return true;
    }

    public C1493k4() {
        this(new C1714z3());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public boolean mo15913a(Activity activity, View view) {
        if (Build.VERSION.SDK_INT < 12) {
            return false;
        }
        while (view.isHardwareAccelerated() && !C1646u4.m3481a(view.getLayerType(), 1)) {
            if (!(view.getParent() instanceof View)) {
                Window window = activity.getWindow();
                if (window == null || !C1646u4.m3481a(window.getAttributes().flags, 16777216)) {
                    return false;
                }
                return true;
            }
            view = (View) view.getParent();
        }
        return false;
    }

    /* renamed from: b */
    public void mo15915b(Context context, String str, C1496c cVar) {
        if (!mo15917c(context)) {
            throw new C1413f4("Unsupported action");
        } else if (context instanceof Activity) {
            new AlertDialog.Builder(context).setTitle("Save Image").setMessage("Download image to Gallery?").setNegativeButton("Cancel", (DialogInterface.OnClickListener) null).setPositiveButton("Ok", new C1494a(context, str, cVar)).setCancelable(true).show();
        } else {
            Toast.makeText(context, "Downloading image ...", 0).show();
            mo15910a(context, str, cVar);
        }
    }

    /* renamed from: a */
    public void mo15912a(Context context, Map<String, String> map) {
        if (mo15914a(context)) {
            try {
                Map<String, Object> a = C1527m4.m3040a(map);
                Intent type = new Intent("android.intent.action.INSERT").setType(MraidNativeCommandHandler.ANDROID_CALENDAR_CONTENT_TYPE);
                for (String next : a.keySet()) {
                    Object obj = a.get(next);
                    if (obj instanceof Long) {
                        type.putExtra(next, ((Long) obj).longValue());
                    } else if (obj instanceof Integer) {
                        type.putExtra(next, ((Integer) obj).intValue());
                    } else {
                        type.putExtra(next, (String) obj);
                    }
                }
                type.setFlags(268435456);
                context.startActivity(type);
            } catch (ActivityNotFoundException unused) {
                throw new C1413f4("App not found");
            } catch (IllegalArgumentException e) {
                C1475j4.m2885a("Invalid params " + e.getMessage());
                throw new C1413f4((Throwable) e);
            } catch (Exception e2) {
                throw new C1413f4((Throwable) e2);
            }
        } else {
            throw new C1413f4("Unsupported action");
        }
    }

    /* renamed from: a */
    public void mo15911a(Context context, URI uri) {
        this.f2012a.mo16305a(context, uri.toString());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: a */
    public void mo15910a(Context context, String str, C1496c cVar) {
        C1646u4.m3480a(new C1598r3(context, new C1495b(this, context, cVar)), (P[]) new String[]{str});
    }
}

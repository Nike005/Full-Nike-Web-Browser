package com.startapp.android.publish.ads.list3d;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.startapp.android.publish.adsCommon.C1098b;
import com.startapp.android.publish.adsCommon.p031d.C1117b;
import com.startapp.android.publish.common.metaData.MetaData;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* renamed from: com.startapp.android.publish.ads.list3d.b */
/* compiled from: StartAppSDK */
public class C0917b extends ArrayAdapter<ListItem> {

    /* renamed from: a */
    private String f554a;

    /* renamed from: b */
    private String f555b;

    public C0917b(Context context, List<ListItem> list, String str, String str2, String str3) {
        super(context, 0, list);
        this.f554a = str2;
        this.f555b = str3;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        C0923d dVar;
        if (view == null) {
            dVar = new C0923d(getContext());
            view2 = dVar.mo14078a();
        } else {
            view2 = view;
            dVar = (C0923d) view.getTag();
        }
        ListItem listItem = (ListItem) getItem(i);
        dVar.mo14079a(C1098b.m1303a().mo14761a(listItem.mo14022n()));
        dVar.mo14082c().setText(listItem.mo14015g());
        dVar.mo14083d().setText(listItem.mo14016h());
        Bitmap a = C0926f.m744a().mo14097a(this.f555b).mo14086a(i, listItem.mo14008a(), listItem.mo14017i());
        if (a == null) {
            dVar.mo14081b().setImageResource(17301651);
            dVar.mo14081b().setTag("tag_error");
        } else {
            dVar.mo14081b().setImageBitmap(a);
            dVar.mo14081b().setTag("tag_ok");
        }
        dVar.mo14084e().setRating(listItem.mo14019k());
        dVar.mo14080a(listItem.mo14025q());
        C0926f.m744a().mo14097a(this.f555b).mo14088a(getContext(), listItem.mo14008a(), listItem.mo14010c(), new C1117b(this.f554a), m697a(listItem));
        return view2;
    }

    /* renamed from: a */
    private long m697a(ListItem listItem) {
        if (listItem.mo14026r() != null) {
            return TimeUnit.SECONDS.toMillis(listItem.mo14026r().longValue());
        }
        return TimeUnit.SECONDS.toMillis(MetaData.getInstance().getIABDisplayImpressionDelayInSeconds());
    }
}

package com.startapp.android.publish.ads.banner.banner3d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.startapp.android.publish.ads.banner.BannerOptions;
import com.startapp.android.publish.adsCommon.C1098b;
import com.startapp.android.publish.adsCommon.C1103c;
import com.startapp.android.publish.adsCommon.C1155i;
import com.startapp.android.publish.adsCommon.Utils.C1060h;
import com.startapp.android.publish.adsCommon.p031d.C1117b;
import com.startapp.android.publish.common.metaData.MetaData;
import com.startapp.android.publish.common.model.AdDetails;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.C1249a;
import com.startapp.common.p043a.C1270g;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: StartAppSDK */
public class Banner3DFace implements Parcelable, C1249a.C1252a {
    public static final Parcelable.Creator<Banner3DFace> CREATOR = new Parcelable.Creator<Banner3DFace>() {
        /* renamed from: a */
        public Banner3DFace createFromParcel(Parcel parcel) {
            return new Banner3DFace(parcel);
        }

        /* renamed from: a */
        public Banner3DFace[] newArray(int i) {
            return new Banner3DFace[i];
        }
    };

    /* renamed from: a */
    private AdDetails f468a;

    /* renamed from: b */
    private Point f469b;

    /* renamed from: c */
    private Bitmap f470c = null;

    /* renamed from: d */
    private Bitmap f471d = null;

    /* renamed from: e */
    private AtomicBoolean f472e = new AtomicBoolean(false);

    /* renamed from: f */
    private C1117b f473f;

    /* renamed from: g */
    private C1155i f474g = null;

    /* renamed from: h */
    private C0889b f475h = null;

    public int describeContents() {
        return 0;
    }

    public Banner3DFace(Context context, ViewGroup viewGroup, AdDetails adDetails, BannerOptions bannerOptions, C1117b bVar) {
        this.f468a = adDetails;
        this.f473f = bVar;
        mo13932a(context, bannerOptions, viewGroup);
    }

    /* renamed from: a */
    public AdDetails mo13931a() {
        return this.f468a;
    }

    /* renamed from: b */
    public Bitmap mo13934b() {
        return this.f471d;
    }

    /* renamed from: a */
    public void mo13932a(Context context, BannerOptions bannerOptions, ViewGroup viewGroup) {
        int a = C1060h.m1162a(context, bannerOptions.mo13882e() - 5);
        this.f469b = new Point((int) (((float) C1060h.m1162a(context, bannerOptions.mo13881d())) * bannerOptions.mo13888j()), (int) (((float) C1060h.m1162a(context, bannerOptions.mo13882e())) * bannerOptions.mo13889k()));
        C0889b bVar = new C0889b(context, new Point(bannerOptions.mo13881d(), bannerOptions.mo13882e()));
        this.f475h = bVar;
        bVar.setText(mo13931a().getTitle());
        this.f475h.setRating(mo13931a().getRating());
        this.f475h.setDescription(mo13931a().getDescription());
        this.f475h.setButtonText(this.f468a.isCPE());
        Bitmap bitmap = this.f470c;
        if (bitmap != null) {
            this.f475h.mo13950a(bitmap, a, a);
        } else {
            this.f475h.mo13949a(17301651, a, a);
            new C1249a(mo13931a().getImageUrl(), this, 0).mo15438a();
            C1270g.m2078a("Banner3DFace", 3, " Banner Face Image Async Request: [" + mo13931a().getTitle() + "]");
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.f469b.x, this.f469b.y);
        layoutParams.addRule(13);
        viewGroup.addView(this.f475h, layoutParams);
        this.f475h.setVisibility(8);
        m593f();
    }

    /* renamed from: f */
    private void m593f() {
        this.f471d = m591a((View) this.f475h);
        if (this.f469b.x > 0 && this.f469b.y > 0) {
            this.f471d = Bitmap.createScaledBitmap(this.f471d, this.f469b.x, this.f469b.y, false);
        }
    }

    /* renamed from: a */
    private Bitmap m591a(View view) {
        view.measure(view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap createBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.draw(canvas);
        return createBitmap;
    }

    /* renamed from: a */
    public void mo13933a(Bitmap bitmap, int i) {
        C0889b bVar;
        if (bitmap != null && (bVar = this.f475h) != null) {
            this.f470c = bitmap;
            bVar.setImage(bitmap);
            m593f();
        }
    }

    /* renamed from: a */
    public C1155i mo13930a(Context context) {
        if (mo13931a().getTrackingUrl() == null || !this.f472e.compareAndSet(false, true)) {
            return null;
        }
        Context context2 = context;
        C1155i iVar = new C1155i(context2, new String[]{mo13931a().getTrackingUrl()}, this.f473f, m594g());
        this.f474g = iVar;
        return iVar;
    }

    /* renamed from: c */
    public void mo13936c() {
        C1155i iVar = this.f474g;
        if (iVar != null) {
            iVar.mo14946a(false);
        }
    }

    /* renamed from: b */
    public void mo13935b(Context context) {
        String intentPackageName = mo13931a().getIntentPackageName();
        boolean a = C1103c.m1389a(context, AdPreferences.Placement.INAPP_BANNER);
        C1155i iVar = this.f474g;
        if (iVar != null) {
            iVar.mo14946a(true);
        }
        if (intentPackageName != null && !"null".equals(intentPackageName) && !TextUtils.isEmpty(intentPackageName)) {
            C1103c.m1385a(intentPackageName, mo13931a().getIntentDetails(), mo13931a().getClickUrl(), context, this.f473f);
        } else if (!mo13931a().isSmartRedirect() || a) {
            C1103c.m1376a(context, mo13931a().getClickUrl(), mo13931a().getTrackingClickUrl(), this.f473f, mo13931a().isStartappBrowserEnabled() && !a, false);
        } else {
            C1103c.m1378a(context, mo13931a().getClickUrl(), mo13931a().getTrackingClickUrl(), mo13931a().getPackageName(), this.f473f, C1098b.m1303a().mo14747A(), C1098b.m1303a().mo14748B(), mo13931a().isStartappBrowserEnabled(), mo13931a().shouldSendRedirectHops(), false);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(mo13931a(), i);
        parcel.writeInt(this.f469b.x);
        parcel.writeInt(this.f469b.y);
        parcel.writeParcelable(this.f470c, i);
        parcel.writeBooleanArray(new boolean[]{this.f472e.get()});
        parcel.writeSerializable(this.f473f);
    }

    public Banner3DFace(Parcel parcel) {
        this.f468a = (AdDetails) parcel.readParcelable(AdDetails.class.getClassLoader());
        Point point = new Point(1, 1);
        this.f469b = point;
        point.x = parcel.readInt();
        this.f469b.y = parcel.readInt();
        this.f470c = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        boolean[] zArr = new boolean[1];
        parcel.readBooleanArray(zArr);
        this.f472e.set(zArr[0]);
        this.f473f = (C1117b) parcel.readSerializable();
    }

    /* renamed from: d */
    public void mo13937d() {
        m592a(this.f470c);
        m592a(this.f471d);
        this.f470c = null;
        this.f471d = null;
    }

    /* renamed from: e */
    public void mo13939e() {
        mo13937d();
        C1155i iVar = this.f474g;
        if (iVar != null) {
            iVar.mo14946a(false);
        }
        C0889b bVar = this.f475h;
        if (bVar != null) {
            bVar.removeAllViews();
            this.f475h = null;
        }
    }

    /* renamed from: a */
    private void m592a(Bitmap bitmap) {
        if (bitmap != null) {
            bitmap.recycle();
        }
    }

    /* renamed from: g */
    private long m594g() {
        if (mo13931a().getDelayImpressionInSeconds() != null) {
            return TimeUnit.SECONDS.toMillis(mo13931a().getDelayImpressionInSeconds().longValue());
        }
        return TimeUnit.SECONDS.toMillis(MetaData.getInstance().getIABDisplayImpressionDelayInSeconds());
    }
}

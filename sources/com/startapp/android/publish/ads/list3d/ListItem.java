package com.startapp.android.publish.ads.list3d;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import com.startapp.android.publish.common.model.AdDetails;

/* compiled from: StartAppSDK */
public class ListItem implements Parcelable {
    public static final Parcelable.Creator<ListItem> CREATOR = new Parcelable.Creator<ListItem>() {
        /* renamed from: a */
        public ListItem createFromParcel(Parcel parcel) {
            return new ListItem(parcel);
        }

        /* renamed from: a */
        public ListItem[] newArray(int i) {
            return new ListItem[i];
        }
    };

    /* renamed from: a */
    private String f520a;

    /* renamed from: b */
    private String f521b;

    /* renamed from: c */
    private String f522c;

    /* renamed from: d */
    private String f523d;

    /* renamed from: e */
    private String f524e;

    /* renamed from: f */
    private String f525f;

    /* renamed from: g */
    private String f526g;

    /* renamed from: h */
    private String f527h;

    /* renamed from: i */
    private String f528i;

    /* renamed from: j */
    private float f529j;

    /* renamed from: k */
    private boolean f530k;

    /* renamed from: l */
    private boolean f531l;

    /* renamed from: m */
    private Drawable f532m;

    /* renamed from: n */
    private String f533n;

    /* renamed from: o */
    private String f534o;

    /* renamed from: p */
    private Long f535p;

    /* renamed from: q */
    private Boolean f536q;

    /* renamed from: r */
    private String f537r;

    public int describeContents() {
        return 0;
    }

    public ListItem(AdDetails adDetails) {
        this.f520a = "";
        this.f521b = "";
        this.f522c = "";
        this.f523d = "";
        this.f524e = "";
        this.f525f = "";
        this.f526g = "";
        this.f527h = "";
        this.f528i = "";
        this.f529j = 0.0f;
        this.f530k = false;
        this.f531l = true;
        this.f532m = null;
        this.f536q = null;
        this.f537r = "";
        this.f520a = adDetails.getAdId();
        this.f521b = adDetails.getClickUrl();
        this.f522c = adDetails.getTrackingUrl();
        this.f523d = adDetails.getTrackingClickUrl();
        this.f524e = adDetails.getTrackingCloseUrl();
        this.f525f = adDetails.getPackageName();
        this.f526g = adDetails.getTitle();
        this.f527h = adDetails.getDescription();
        this.f528i = adDetails.getImageUrl();
        this.f529j = adDetails.getRating();
        this.f530k = adDetails.isSmartRedirect();
        this.f531l = adDetails.isStartappBrowserEnabled();
        this.f532m = null;
        this.f537r = adDetails.getTemplate();
        this.f533n = adDetails.getIntentDetails();
        this.f534o = adDetails.getIntentPackageName();
        this.f535p = adDetails.getDelayImpressionInSeconds();
        this.f536q = adDetails.shouldSendRedirectHops();
    }

    public ListItem(Parcel parcel) {
        this.f520a = "";
        this.f521b = "";
        this.f522c = "";
        this.f523d = "";
        this.f524e = "";
        this.f525f = "";
        this.f526g = "";
        this.f527h = "";
        this.f528i = "";
        this.f529j = 0.0f;
        boolean z = false;
        this.f530k = false;
        this.f531l = true;
        this.f532m = null;
        this.f536q = null;
        this.f537r = "";
        if (parcel.readInt() == 1) {
            this.f532m = new BitmapDrawable((Bitmap) Bitmap.CREATOR.createFromParcel(parcel));
        } else {
            this.f532m = null;
        }
        this.f520a = parcel.readString();
        this.f521b = parcel.readString();
        this.f522c = parcel.readString();
        this.f523d = parcel.readString();
        this.f524e = parcel.readString();
        this.f525f = parcel.readString();
        this.f526g = parcel.readString();
        this.f527h = parcel.readString();
        this.f528i = parcel.readString();
        this.f529j = parcel.readFloat();
        if (parcel.readInt() == 1) {
            this.f530k = true;
        } else {
            this.f530k = false;
        }
        if (parcel.readInt() == 0) {
            this.f531l = false;
        } else {
            this.f531l = true;
        }
        this.f537r = parcel.readString();
        this.f534o = parcel.readString();
        this.f533n = parcel.readString();
        Long valueOf = Long.valueOf(parcel.readLong());
        this.f535p = valueOf;
        if (valueOf.longValue() == -1) {
            this.f535p = null;
        }
        int readInt = parcel.readInt();
        if (readInt == 0) {
            this.f536q = null;
        } else {
            this.f536q = Boolean.valueOf(readInt == 1 ? true : z);
        }
    }

    /* renamed from: a */
    public String mo14008a() {
        return this.f520a;
    }

    /* renamed from: b */
    public String mo14009b() {
        return this.f521b;
    }

    /* renamed from: c */
    public String mo14010c() {
        return this.f522c;
    }

    /* renamed from: d */
    public String mo14011d() {
        return this.f524e;
    }

    /* renamed from: e */
    public String mo14013e() {
        return this.f523d;
    }

    /* renamed from: f */
    public String mo14014f() {
        return this.f525f;
    }

    /* renamed from: g */
    public String mo14015g() {
        return this.f526g;
    }

    /* renamed from: h */
    public String mo14016h() {
        return this.f527h;
    }

    /* renamed from: i */
    public String mo14017i() {
        return this.f528i;
    }

    /* renamed from: j */
    public Drawable mo14018j() {
        return this.f532m;
    }

    /* renamed from: k */
    public float mo14019k() {
        return this.f529j;
    }

    /* renamed from: l */
    public boolean mo14020l() {
        return this.f530k;
    }

    /* renamed from: m */
    public boolean mo14021m() {
        return this.f531l;
    }

    /* renamed from: n */
    public String mo14022n() {
        return this.f537r;
    }

    /* renamed from: o */
    public String mo14023o() {
        return this.f533n;
    }

    /* renamed from: p */
    public String mo14024p() {
        return this.f534o;
    }

    /* renamed from: q */
    public boolean mo14025q() {
        return this.f534o != null;
    }

    /* renamed from: r */
    public Long mo14026r() {
        return this.f535p;
    }

    /* renamed from: s */
    public Boolean mo14027s() {
        return this.f536q;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 1;
        if (mo14018j() != null) {
            parcel.writeParcelable(((BitmapDrawable) mo14018j()).getBitmap(), i);
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeString(this.f520a);
        parcel.writeString(this.f521b);
        parcel.writeString(this.f522c);
        parcel.writeString(this.f523d);
        parcel.writeString(this.f524e);
        parcel.writeString(this.f525f);
        parcel.writeString(this.f526g);
        parcel.writeString(this.f527h);
        parcel.writeString(this.f528i);
        parcel.writeFloat(this.f529j);
        parcel.writeInt(this.f530k ? 1 : 0);
        parcel.writeInt(this.f531l ? 1 : 0);
        parcel.writeString(this.f537r);
        parcel.writeString(this.f534o);
        parcel.writeString(this.f533n);
        Long l = this.f535p;
        if (l == null) {
            parcel.writeLong(-1);
        } else {
            parcel.writeLong(l.longValue());
        }
        Boolean bool = this.f536q;
        if (bool == null) {
            parcel.writeInt(0);
            return;
        }
        if (!bool.booleanValue()) {
            i2 = -1;
        }
        parcel.writeInt(i2);
    }
}

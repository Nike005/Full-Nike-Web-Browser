package com.startapp.android.publish.ads.banner.banner3d;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.EmbossMaskFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.startapp.android.publish.ads.banner.banner3d.Banner3DSize;
import com.startapp.android.publish.adsCommon.C1098b;
import com.startapp.android.publish.adsCommon.Utils.C1060h;
import com.startapp.android.publish.p016a.C0848b;
import com.startapp.common.p043a.C1261c;
import org.apache.commons.lang3.StringUtils;

/* renamed from: com.startapp.android.publish.ads.banner.banner3d.b */
/* compiled from: StartAppSDK */
public class C0889b extends RelativeLayout {

    /* renamed from: a */
    private TextView f476a;

    /* renamed from: b */
    private TextView f477b;

    /* renamed from: c */
    private ImageView f478c;

    /* renamed from: d */
    private C0848b f479d;

    /* renamed from: e */
    private TextView f480e;

    /* renamed from: f */
    private Point f481f;

    /* renamed from: com.startapp.android.publish.ads.banner.banner3d.b$a */
    /* compiled from: StartAppSDK */
    private enum C0892a {
        XS,
        S,
        M,
        L,
        XL
    }

    public C0889b(Context context, Point point) {
        super(context);
        this.f481f = point;
        m614a();
    }

    /* renamed from: a */
    private void m614a() {
        Context context = getContext();
        C0892a templateBySize = getTemplateBySize();
        setBackgroundDrawable(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{C1098b.m1303a().mo14774n(), C1098b.m1303a().mo14775o()}));
        setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
        int a = C1060h.m1162a(context, 2);
        int a2 = C1060h.m1162a(context, 3);
        C1060h.m1162a(context, 4);
        int a3 = C1060h.m1162a(context, 5);
        int a4 = C1060h.m1162a(context, 6);
        int a5 = C1060h.m1162a(context, 8);
        C1060h.m1162a(context, 10);
        int a6 = C1060h.m1162a(context, 20);
        C1060h.m1162a(context, 84);
        int a7 = C1060h.m1162a(context, 90);
        setPadding(a3, 0, a3, 0);
        setTag(this);
        ImageView imageView = new ImageView(context);
        this.f478c = imageView;
        imageView.setId(1);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(a7, a7);
        layoutParams.addRule(15);
        this.f478c.setLayoutParams(layoutParams);
        TextView textView = new TextView(context);
        this.f476a = textView;
        textView.setId(2);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(C1261c.m2011a(17), 1);
        layoutParams2.addRule(14);
        this.f476a.setLayoutParams(layoutParams2);
        this.f476a.setTextColor(C1098b.m1303a().mo14777q().intValue());
        this.f476a.setGravity(C1261c.m2011a(8388611));
        this.f476a.setBackgroundColor(0);
        int i = C08912.f483a[templateBySize.ordinal()];
        if (i == 1 || i == 2) {
            this.f476a.setTextSize(17.0f);
            this.f476a.setPadding(a2, 0, 0, a);
            Context context2 = getContext();
            double d = (double) this.f481f.x;
            Double.isNaN(d);
            layoutParams2.width = C1060h.m1162a(context2, (int) (d * 0.55d));
        } else if (i == 3) {
            this.f476a.setTextSize(17.0f);
            this.f476a.setPadding(a2, 0, 0, a);
            Context context3 = getContext();
            double d2 = (double) this.f481f.x;
            Double.isNaN(d2);
            layoutParams2.width = C1060h.m1162a(context3, (int) (d2 * 0.65d));
        } else if (i == 4 || i == 5) {
            this.f476a.setTextSize(22.0f);
            this.f476a.setPadding(a2, 0, 0, a3);
        }
        this.f476a.setSingleLine(true);
        this.f476a.setEllipsize(TextUtils.TruncateAt.END);
        C1060h.m1170a(this.f476a, C1098b.m1303a().mo14778r());
        TextView textView2 = new TextView(context);
        this.f477b = textView2;
        textView2.setId(3);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(C1261c.m2011a(17), 1);
        layoutParams3.addRule(3, 2);
        layoutParams3.setMargins(0, 0, 0, a3);
        this.f477b.setLayoutParams(layoutParams3);
        this.f477b.setTextColor(C1098b.m1303a().mo14780t().intValue());
        this.f477b.setTextSize(18.0f);
        this.f477b.setMaxLines(2);
        this.f477b.setLines(2);
        this.f477b.setSingleLine(false);
        this.f477b.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        this.f477b.setHorizontallyScrolling(true);
        this.f477b.setPadding(a2, 0, 0, 0);
        C0848b bVar = new C0848b(getContext());
        this.f479d = bVar;
        bVar.setId(5);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        int i2 = C08912.f483a[templateBySize.ordinal()];
        if (i2 == 1 || i2 == 2 || i2 == 3) {
            layoutParams4.addRule(C1261c.m2011a(17), 1);
            layoutParams4.addRule(8, 1);
        } else if (i2 == 4 || i2 == 5) {
            layoutParams4.addRule(C1261c.m2011a(17), 2);
            Context context4 = getContext();
            double d3 = (double) this.f481f.x;
            Double.isNaN(d3);
            layoutParams3.width = C1060h.m1162a(context4, (int) (d3 * 0.6d));
        }
        layoutParams4.setMargins(a2, a5, a2, 0);
        this.f479d.setLayoutParams(layoutParams4);
        this.f480e = new TextView(context);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
        int i3 = C08912.f483a[templateBySize.ordinal()];
        if (i3 == 1 || i3 == 2 || i3 == 3) {
            this.f480e.setTextSize(13.0f);
            layoutParams5.addRule(C1261c.m2011a(17), 2);
            layoutParams5.addRule(15);
        } else if (i3 == 4) {
            layoutParams5.addRule(C1261c.m2011a(17), 3);
            layoutParams5.addRule(15);
            layoutParams5.setMargins(a6, 0, 0, 0);
            this.f480e.setTextSize(26.0f);
        } else if (i3 == 5) {
            layoutParams5.addRule(C1261c.m2011a(17), 3);
            layoutParams5.addRule(15);
            layoutParams5.setMargins(a6 * 7, 0, 0, 0);
            this.f480e.setTextSize(26.0f);
        }
        this.f480e.setPadding(a4, a4, a4, a4);
        this.f480e.setLayoutParams(layoutParams5);
        setButtonText(false);
        this.f480e.setTextColor(-1);
        this.f480e.setTypeface((Typeface) null, 1);
        this.f480e.setId(4);
        this.f480e.setShadowLayer(2.5f, -3.0f, 3.0f, -9013642);
        this.f480e.setBackgroundDrawable(new ShapeDrawable(new RoundRectShape(new float[]{10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f, 10.0f}, (RectF) null, (float[]) null)) {
            /* access modifiers changed from: protected */
            public void onDraw(Shape shape, Canvas canvas, Paint paint) {
                paint.setColor(-11363070);
                paint.setMaskFilter(new EmbossMaskFilter(new float[]{1.0f, 1.0f, 1.0f}, 0.4f, 5.0f, 3.0f));
                super.onDraw(shape, canvas, paint);
            }
        });
        addView(this.f478c);
        addView(this.f476a);
        int i4 = C08912.f483a[templateBySize.ordinal()];
        if (i4 == 1 || i4 == 2 || i4 == 3) {
            addView(this.f480e);
        } else if (i4 == 4 || i4 == 5) {
            addView(this.f480e);
            addView(this.f477b);
        }
        addView(this.f479d);
    }

    /* renamed from: com.startapp.android.publish.ads.banner.banner3d.b$2 */
    /* compiled from: StartAppSDK */
    static /* synthetic */ class C08912 {

        /* renamed from: a */
        static final /* synthetic */ int[] f483a;

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.startapp.android.publish.ads.banner.banner3d.b$a[] r0 = com.startapp.android.publish.ads.banner.banner3d.C0889b.C0892a.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f483a = r0
                com.startapp.android.publish.ads.banner.banner3d.b$a r1 = com.startapp.android.publish.ads.banner.banner3d.C0889b.C0892a.XS     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f483a     // Catch:{ NoSuchFieldError -> 0x001d }
                com.startapp.android.publish.ads.banner.banner3d.b$a r1 = com.startapp.android.publish.ads.banner.banner3d.C0889b.C0892a.S     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f483a     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.startapp.android.publish.ads.banner.banner3d.b$a r1 = com.startapp.android.publish.ads.banner.banner3d.C0889b.C0892a.M     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f483a     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.startapp.android.publish.ads.banner.banner3d.b$a r1 = com.startapp.android.publish.ads.banner.banner3d.C0889b.C0892a.L     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = f483a     // Catch:{ NoSuchFieldError -> 0x003e }
                com.startapp.android.publish.ads.banner.banner3d.b$a r1 = com.startapp.android.publish.ads.banner.banner3d.C0889b.C0892a.XL     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.startapp.android.publish.ads.banner.banner3d.C0889b.C08912.<clinit>():void");
        }
    }

    public void setText(String str) {
        this.f476a.setText(str);
    }

    public void setImage(Bitmap bitmap) {
        this.f478c.setImageBitmap(bitmap);
    }

    /* renamed from: a */
    public void mo13949a(int i, int i2, int i3) {
        this.f478c.setImageResource(i);
        ViewGroup.LayoutParams layoutParams = this.f478c.getLayoutParams();
        layoutParams.width = i2;
        layoutParams.height = i3;
        this.f478c.setLayoutParams(layoutParams);
    }

    public void setRating(float f) {
        try {
            this.f479d.setRating(f);
        } catch (NullPointerException unused) {
        }
    }

    /* renamed from: a */
    public void mo13950a(Bitmap bitmap, int i, int i2) {
        this.f478c.setImageBitmap(bitmap);
        ViewGroup.LayoutParams layoutParams = this.f478c.getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i2;
        this.f478c.setLayoutParams(layoutParams);
    }

    public void setDescription(String str) {
        if (str != null) {
            String str2 = "";
            if (str.compareTo(str2) != 0) {
                String[] a = m615a(str);
                String str3 = a[0];
                if (a[1] != null) {
                    str2 = m615a(a[1])[0];
                }
                if (str.length() >= 110) {
                    str2 = str2 + "...";
                }
                this.f477b.setText(str3 + StringUtils.f3949LF + str2);
            }
        }
    }

    /* renamed from: a */
    private String[] m615a(String str) {
        boolean z;
        String[] strArr = new String[2];
        int i = 55;
        if (str.length() > 55) {
            char[] charArray = str.substring(0, 55).toCharArray();
            int length = charArray.length - 1;
            int i2 = length - 1;
            while (true) {
                if (i2 <= 0) {
                    z = false;
                    break;
                } else if (charArray[i2] == ' ') {
                    length = i2;
                    z = true;
                    break;
                } else {
                    i2--;
                }
            }
            if (z) {
                i = length;
            }
            strArr[0] = str.substring(0, i);
            strArr[1] = str.substring(i + 1, str.length());
        } else {
            strArr[0] = str;
            strArr[1] = null;
        }
        return strArr;
    }

    private C0892a getTemplateBySize() {
        C0892a aVar = C0892a.S;
        if (this.f481f.x > Banner3DSize.Size.SMALL.getSize().mo13975a() || this.f481f.y > Banner3DSize.Size.SMALL.getSize().mo13978b()) {
            aVar = C0892a.M;
        }
        if (this.f481f.x > Banner3DSize.Size.MEDIUM.getSize().mo13975a() || this.f481f.y > Banner3DSize.Size.MEDIUM.getSize().mo13978b()) {
            aVar = C0892a.L;
        }
        return (this.f481f.x > Banner3DSize.Size.LARGE.getSize().mo13975a() || this.f481f.y > Banner3DSize.Size.LARGE.getSize().mo13978b()) ? C0892a.XL : aVar;
    }

    public void setButtonText(boolean z) {
        if (z) {
            this.f480e.setText("OPEN");
        } else {
            this.f480e.setText("DOWNLOAD");
        }
    }
}

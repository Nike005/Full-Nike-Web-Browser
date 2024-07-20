package com.appnext.ads.fullscreen;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import com.appnext.core.C4967f;

public class Circle extends View {

    /* renamed from: au */
    private static final int f4220au = 180;

    /* renamed from: av */
    private Paint f4221av;

    /* renamed from: aw */
    private RectF f4222aw;

    /* renamed from: ax */
    private float f4223ax;

    public Circle(Context context) {
        super(context);
        init(context);
    }

    public Circle(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(this.f4222aw, 180.0f, this.f4223ax, false, this.f4221av);
    }

    public float getAngle() {
        return this.f4223ax;
    }

    public void setAngle(float f) {
        this.f4223ax = f;
    }

    /* renamed from: a */
    private static int m6237a(Context context, float f) {
        return C4967f.m6811a(context, f);
    }

    private void init(Context context) {
        float a = (float) C4967f.m6811a(context, 5.0f);
        Paint paint = new Paint();
        this.f4221av = paint;
        paint.setAntiAlias(true);
        this.f4221av.setStyle(Paint.Style.STROKE);
        this.f4221av.setStrokeWidth(a);
        this.f4221av.setColor(-1);
        this.f4221av.setShadowLayer(2.0f, 2.0f, 2.0f, Color.argb(128, 0, 0, 0));
        setLayerType(1, this.f4221av);
        this.f4222aw = new RectF(a, a, ((float) C4967f.m6811a(context, 20.0f)) + a, ((float) C4967f.m6811a(context, 20.0f)) + a);
        this.f4223ax = 360.0f;
    }
}

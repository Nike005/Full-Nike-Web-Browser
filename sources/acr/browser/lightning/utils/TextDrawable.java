package acr.browser.lightning.utils;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

public class TextDrawable extends Drawable {
    private final Paint paint;
    private final String text;

    public int getOpacity() {
        return -3;
    }

    public TextDrawable(String str) {
        this.text = str;
        Paint paint2 = new Paint();
        this.paint = paint2;
        paint2.setColor(-1);
        this.paint.setTextSize(22.0f);
        this.paint.setAntiAlias(true);
        this.paint.setFakeBoldText(true);
        this.paint.setShadowLayer(6.0f, 0.0f, 0.0f, -16777216);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setTextAlign(Paint.Align.LEFT);
    }

    public TextDrawable(String str, float f) {
        this.text = str;
        Paint paint2 = new Paint();
        this.paint = paint2;
        paint2.setColor(-1);
        this.paint.setTextSize(f);
        this.paint.setAntiAlias(true);
        this.paint.setFakeBoldText(true);
        this.paint.setShadowLayer(6.0f, 0.0f, 0.0f, -16777216);
        this.paint.setStyle(Paint.Style.FILL);
        this.paint.setTextAlign(Paint.Align.LEFT);
    }

    public void draw(Canvas canvas) {
        canvas.drawText(this.text, 0.0f, 0.0f, this.paint);
    }

    public void setAlpha(int i) {
        this.paint.setAlpha(i);
    }

    public void setColorFilter(ColorFilter colorFilter) {
        this.paint.setColorFilter(colorFilter);
    }
}

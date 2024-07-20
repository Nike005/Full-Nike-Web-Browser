package acr.browser.lightning.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

public class DrawableUtils {
    public static int mixColor(float f, int i, int i2) {
        int i3 = (i >> 24) & 255;
        int i4 = (i >> 16) & 255;
        int i5 = (i >> 8) & 255;
        int i6 = i & 255;
        return ((i3 + ((int) (((float) (((i2 >> 24) & 255) - i3)) * f))) << 24) | ((i4 + ((int) (((float) (((i2 >> 16) & 255) - i4)) * f))) << 16) | ((i5 + ((int) (((float) (((i2 >> 8) & 255) - i5)) * f))) << 8) | (i6 + ((int) (f * ((float) ((i2 & 255) - i6)))));
    }

    public static Bitmap getRoundedNumberImage(int i, int i2, int i3, int i4, int i5) {
        String valueOf = i > 99 ? "∞" : String.valueOf(i);
        Bitmap createBitmap = Bitmap.createBitmap(i2, i3, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        paint.setColor(i4);
        paint.setTypeface(Typeface.create(Typeface.SANS_SERIF, 1));
        paint.setTextSize((float) C3245Utils.dpToPx(14.0f));
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        int dpToPx = C3245Utils.dpToPx(2.0f);
        float f = (float) dpToPx;
        canvas.drawRoundRect(new RectF(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight()), f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        float f2 = (float) i5;
        RectF rectF = new RectF(f2, f2, (float) (canvas.getWidth() - i5), (float) (canvas.getHeight() - i5));
        float f3 = (float) (dpToPx - 1);
        canvas.drawRoundRect(rectF, f3, f3, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
        canvas.drawText(String.valueOf(valueOf), (float) (canvas.getWidth() / 2), (float) ((int) (((float) (canvas.getHeight() / 2)) - ((paint.descent() + paint.ascent()) / 2.0f))), paint);
        return createBitmap;
    }

    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }
}

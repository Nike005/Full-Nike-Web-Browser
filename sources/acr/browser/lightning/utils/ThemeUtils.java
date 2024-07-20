package acr.browser.lightning.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.wnikebrow_13999769.R;

public class ThemeUtils {
    private static final TypedValue sTypedValue = new TypedValue();

    public static int getPrimaryColorDark(Context context) {
        return getColor(context, R.attr.colorPrimaryDark);
    }

    public static int getColor(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(sTypedValue.data, new int[]{i});
        int color = obtainStyledAttributes.getColor(0, 0);
        obtainStyledAttributes.recycle();
        return color;
    }

    public static int getIconLightThemeColor(Context context) {
        return ContextCompat.getColor(context, R.color.icon_light_theme);
    }

    public static int getIconDarkThemeColor(Context context) {
        return ContextCompat.getColor(context, R.color.icon_dark_theme);
    }

    public static int getIconThemeColor(Context context, boolean z) {
        return z ? getIconDarkThemeColor(context) : getIconLightThemeColor(context);
    }

    private static Drawable getVectorDrawable(Context context, int i) {
        Drawable drawable = ContextCompat.getDrawable(context, i);
        Preconditions.checkNonNull(drawable);
        return Build.VERSION.SDK_INT < 21 ? DrawableCompat.wrap(drawable).mutate() : drawable;
    }

    private static Bitmap getBitmapFromVectorDrawable(Context context, int i) {
        Drawable vectorDrawable = getVectorDrawable(context, i);
        Bitmap createBitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return createBitmap;
    }

    public static Bitmap getThemedBitmap(Context context, int i, boolean z) {
        int iconDarkThemeColor = z ? getIconDarkThemeColor(context) : getIconLightThemeColor(context);
        Bitmap bitmapFromVectorDrawable = getBitmapFromVectorDrawable(context, i);
        Bitmap createBitmap = Bitmap.createBitmap(bitmapFromVectorDrawable.getWidth(), bitmapFromVectorDrawable.getHeight(), Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(iconDarkThemeColor, PorterDuff.Mode.SRC_IN));
        new Canvas(createBitmap).drawBitmap(bitmapFromVectorDrawable, 0.0f, 0.0f, paint);
        bitmapFromVectorDrawable.recycle();
        return createBitmap;
    }

    public static Drawable getThemedDrawable(Context context, int i, boolean z) {
        int iconDarkThemeColor = z ? getIconDarkThemeColor(context) : getIconLightThemeColor(context);
        Drawable vectorDrawable = getVectorDrawable(context, i);
        vectorDrawable.mutate();
        vectorDrawable.setColorFilter(iconDarkThemeColor, PorterDuff.Mode.SRC_IN);
        return vectorDrawable;
    }

    public static int getTextColor(Context context) {
        return getColor(context, 16843601);
    }
}

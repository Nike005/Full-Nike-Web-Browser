package acr.browser.lightning.config;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.utils.Preconditions;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import com.wnikebrow_13999769.R;
import java.util.ArrayList;
import java.util.List;

public class ThemeManager {
    private List<Theme> themeList;

    public ThemeManager(Context context) {
        Config config = BrowserApp.getConfig();
        ArrayList arrayList = new ArrayList();
        this.themeList = arrayList;
        arrayList.add(new Theme(config.getPrimaryColor().intValue(), config.getPrimaryDarkColor().intValue(), config.getAccentColor().intValue(), context.getResources().getColor(R.color.drawer_background), context.getResources().getColor(R.color.divider_light), config.getPrimaryDarkColor().intValue(), context.getResources().getColor(R.color.primary_color), context.getResources().getColor(R.color.icon_dark_theme), context.getResources().getColor(R.color.icon_dark_theme_disabled)));
        this.themeList.add(new Theme(context.getResources().getColor(R.color.primary_color), context.getResources().getColor(R.color.transparent), context.getResources().getColor(R.color.accent_color), context.getResources().getColor(R.color.drawer_background), context.getResources().getColor(R.color.divider_light), context.getResources().getColor(R.color.black), context.getResources().getColor(R.color.primary_color), context.getResources().getColor(R.color.icon_light_theme), context.getResources().getColor(R.color.icon_light_theme_disabled)));
        this.themeList.add(new Theme(context.getResources().getColor(R.color.primary_color_dark), context.getResources().getColor(R.color.transparent), context.getResources().getColor(R.color.accent_color), context.getResources().getColor(R.color.drawer_background_dark), context.getResources().getColor(R.color.divider_dark), context.getResources().getColor(R.color.black), context.getResources().getColor(R.color.primary_color_dark), context.getResources().getColor(R.color.icon_dark_theme), context.getResources().getColor(R.color.icon_dark_theme_disabled)));
        this.themeList.add(new Theme(context.getResources().getColor(R.color.black), context.getResources().getColor(R.color.black), context.getResources().getColor(R.color.accent_color), context.getResources().getColor(R.color.black), context.getResources().getColor(R.color.black), context.getResources().getColor(R.color.black), context.getResources().getColor(R.color.primary_color_dark), context.getResources().getColor(R.color.icon_dark_theme), context.getResources().getColor(R.color.icon_dark_theme_disabled)));
    }

    public int getPrimaryColor(int i) {
        if (i > this.themeList.size()) {
            return 0;
        }
        return this.themeList.get(i).getColorPrimary();
    }

    public int getPrimarydarkColor(int i) {
        if (i > this.themeList.size()) {
            return 0;
        }
        return this.themeList.get(i).getColorPrimaryDark();
    }

    public int getAccentColor(int i) {
        if (i > this.themeList.size()) {
            return 0;
        }
        return this.themeList.get(i).getColorAccent();
    }

    public int getDrawerBackgroundColor(int i) {
        if (i > this.themeList.size()) {
            return 0;
        }
        return this.themeList.get(i).getColorPrimary();
    }

    public int getDividerColor(int i) {
        if (i > this.themeList.size()) {
            return 0;
        }
        return this.themeList.get(i).getDividerColor();
    }

    public int getStatusBarColor(int i) {
        if (i > this.themeList.size()) {
            return 0;
        }
        return this.themeList.get(i).getStatusBarColor();
    }

    public int getBackgroundColor(int i) {
        if (i > this.themeList.size()) {
            return 0;
        }
        return this.themeList.get(i).getBackgroundColor();
    }

    public int getIconColor(int i) {
        if (i > this.themeList.size()) {
            return 0;
        }
        return this.themeList.get(i).getIconColor();
    }

    public int getDisabledIconColor(int i) {
        if (i > this.themeList.size()) {
            return 0;
        }
        return this.themeList.get(i).getDisabledIconColor();
    }

    public int getTransparentPrimaryColor(int i) {
        return adjustAlpha(getPrimaryColor(i), 0.7f);
    }

    public int getTransparentColor(int i) {
        return adjustAlpha(i, 0.7f);
    }

    private int adjustAlpha(int i, float f) {
        return Color.argb(Math.round(((float) Color.alpha(i)) * f), Color.red(i), Color.green(i), Color.blue(i));
    }

    public Bitmap getThemedBitmap(Context context, int i, int i2) {
        int iconColor = this.themeList.get(i2).getIconColor();
        Bitmap bitmapFromVectorDrawable = getBitmapFromVectorDrawable(context, i);
        Bitmap createBitmap = Bitmap.createBitmap(bitmapFromVectorDrawable.getWidth(), bitmapFromVectorDrawable.getHeight(), Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setColorFilter(new PorterDuffColorFilter(iconColor, PorterDuff.Mode.SRC_IN));
        new Canvas(createBitmap).drawBitmap(bitmapFromVectorDrawable, 0.0f, 0.0f, paint);
        bitmapFromVectorDrawable.recycle();
        return createBitmap;
    }

    private static Bitmap getBitmapFromVectorDrawable(Context context, int i) {
        Drawable vectorDrawable = getVectorDrawable(context, i);
        Bitmap createBitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        vectorDrawable.draw(canvas);
        return createBitmap;
    }

    private static Drawable getVectorDrawable(Context context, int i) {
        Drawable drawable = ContextCompat.getDrawable(context, i);
        Preconditions.checkNonNull(drawable);
        return Build.VERSION.SDK_INT < 21 ? DrawableCompat.wrap(drawable).mutate() : drawable;
    }

    public Drawable getThemedDrawable(Context context, int i, int i2) {
        int iconColor = this.themeList.get(i2).getIconColor();
        Drawable vectorDrawable = getVectorDrawable(context, i);
        vectorDrawable.mutate();
        vectorDrawable.setColorFilter(iconColor, PorterDuff.Mode.SRC_IN);
        return vectorDrawable;
    }
}

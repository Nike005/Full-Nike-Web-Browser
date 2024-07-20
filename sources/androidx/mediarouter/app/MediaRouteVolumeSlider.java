package androidx.mediarouter.app;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import androidx.appcompat.C3333R;
import androidx.appcompat.widget.AppCompatSeekBar;

class MediaRouteVolumeSlider extends AppCompatSeekBar {
    private static final String TAG = "MediaRouteVolumeSlider";
    private int mColor;
    private final float mDisabledAlpha;
    private boolean mHideThumb;
    private Drawable mThumb;

    public MediaRouteVolumeSlider(Context context) {
        this(context, (AttributeSet) null);
    }

    public MediaRouteVolumeSlider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, C3333R.attr.seekBarStyle);
    }

    public MediaRouteVolumeSlider(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDisabledAlpha = MediaRouterThemeHelper.getDisabledAlpha(context);
    }

    /* access modifiers changed from: protected */
    public void drawableStateChanged() {
        super.drawableStateChanged();
        int i = isEnabled() ? 255 : (int) (this.mDisabledAlpha * 255.0f);
        this.mThumb.setColorFilter(this.mColor, PorterDuff.Mode.SRC_IN);
        this.mThumb.setAlpha(i);
        getProgressDrawable().setColorFilter(this.mColor, PorterDuff.Mode.SRC_IN);
        getProgressDrawable().setAlpha(i);
    }

    public void setThumb(Drawable drawable) {
        this.mThumb = drawable;
        if (this.mHideThumb) {
            drawable = null;
        }
        super.setThumb(drawable);
    }

    public void setHideThumb(boolean z) {
        Drawable drawable;
        if (this.mHideThumb != z) {
            this.mHideThumb = z;
            if (z) {
                drawable = null;
            } else {
                drawable = this.mThumb;
            }
            super.setThumb(drawable);
        }
    }

    public void setColor(int i) {
        if (this.mColor != i) {
            if (Color.alpha(i) != 255) {
                Log.e(TAG, "Volume slider color cannot be translucent: #" + Integer.toHexString(i));
            }
            this.mColor = i;
        }
    }
}

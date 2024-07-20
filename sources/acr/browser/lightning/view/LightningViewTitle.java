package acr.browser.lightning.view;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.utils.C3245Utils;
import android.content.Context;
import android.graphics.Bitmap;
import com.wnikebrow_13999769.R;

class LightningViewTitle {
    private static Bitmap DEFAULT_DARK_ICON;
    private static Bitmap DEFAULT_LIGHT_ICON;
    private final Context mContext;
    private Bitmap mFavicon = null;
    private String mTitle;

    public LightningViewTitle(Context context) {
        this.mContext = context;
        this.mTitle = context.getString(R.string.action_new_tab);
    }

    public void setFavicon(Bitmap bitmap) {
        if (bitmap == null) {
            this.mFavicon = null;
        } else {
            this.mFavicon = C3245Utils.padFavicon(bitmap);
        }
    }

    private static Bitmap getDefaultIcon(Context context, int i) {
        return BrowserApp.getThemeManager().getThemedBitmap(context, R.drawable.ic_webpage, i);
    }

    public void setTitle(String str) {
        if (str == null) {
            this.mTitle = "";
        } else {
            this.mTitle = str;
        }
    }

    public String getTitle() {
        return this.mTitle;
    }

    public Bitmap getFavicon(int i) {
        Bitmap bitmap = this.mFavicon;
        return bitmap == null ? getDefaultIcon(this.mContext, i) : bitmap;
    }
}

package acr.browser.lightning.utils;

import android.content.Context;

public final class ResourceUtils {
    private ResourceUtils() {
    }

    public static int dimen(Context context, int i) {
        return Math.round(context.getResources().getDimension(i));
    }
}

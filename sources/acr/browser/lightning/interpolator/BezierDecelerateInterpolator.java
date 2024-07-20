package acr.browser.lightning.interpolator;

import android.os.Build;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

public class BezierDecelerateInterpolator implements Interpolator {
    private static final Interpolator PATH_INTERPOLATOR;

    static {
        if (Build.VERSION.SDK_INT >= 21) {
            PATH_INTERPOLATOR = new PathInterpolator(0.25f, 0.1f, 0.25f, 1.0f);
        } else {
            PATH_INTERPOLATOR = new DecelerateInterpolator();
        }
    }

    public float getInterpolation(float f) {
        return PATH_INTERPOLATOR.getInterpolation(f);
    }
}

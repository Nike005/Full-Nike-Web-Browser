package acr.browser.lightning.animation;

import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;

public class AnimationUtils {
    public static Animation createRotationTransitionAnimation(final ImageView imageView, final int i) {
        C30351 r0 = new Animation() {
            private boolean mSetFinalDrawable;

            /* access modifiers changed from: protected */
            public void applyTransformation(float f, Transformation transformation) {
                if (f < 0.5f) {
                    imageView.setRotationY(f * 90.0f * 2.0f);
                    return;
                }
                if (!this.mSetFinalDrawable) {
                    this.mSetFinalDrawable = true;
                    imageView.setImageResource(i);
                }
                imageView.setRotationY((((f - 0.5f) * 90.0f) * 2.0f) - 0.049804688f);
            }
        };
        r0.setDuration(300);
        r0.setInterpolator(new AccelerateDecelerateInterpolator());
        return r0;
    }
}

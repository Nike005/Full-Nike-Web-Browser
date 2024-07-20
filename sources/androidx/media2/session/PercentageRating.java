package androidx.media2.session;

import androidx.core.util.ObjectsCompat;
import androidx.media2.common.Rating;

public final class PercentageRating implements Rating {
    private static final float RATING_NOT_RATED = -1.0f;
    float mPercent;

    public PercentageRating() {
        this.mPercent = -1.0f;
    }

    public PercentageRating(float f) {
        if (f < 0.0f || f > 100.0f) {
            throw new IllegalArgumentException("percent should be in the rage of [0, 100]");
        }
        this.mPercent = f;
    }

    public boolean isRated() {
        return this.mPercent != -1.0f;
    }

    public int hashCode() {
        return ObjectsCompat.hash(Float.valueOf(this.mPercent));
    }

    public boolean equals(Object obj) {
        if ((obj instanceof PercentageRating) && this.mPercent == ((PercentageRating) obj).mPercent) {
            return true;
        }
        return false;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("PercentageRating: ");
        if (isRated()) {
            str = "percentage=" + this.mPercent;
        } else {
            str = "unrated";
        }
        sb.append(str);
        return sb.toString();
    }

    public float getPercentRating() {
        return this.mPercent;
    }
}

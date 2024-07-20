package androidx.media2.session;

import androidx.core.util.ObjectsCompat;
import androidx.media2.common.Rating;

public final class HeartRating implements Rating {
    boolean mHasHeart;
    boolean mIsRated = false;

    public HeartRating() {
    }

    public HeartRating(boolean z) {
        this.mHasHeart = z;
    }

    public boolean isRated() {
        return this.mIsRated;
    }

    public int hashCode() {
        return ObjectsCompat.hash(Boolean.valueOf(this.mIsRated), Boolean.valueOf(this.mHasHeart));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof HeartRating)) {
            return false;
        }
        HeartRating heartRating = (HeartRating) obj;
        if (this.mHasHeart == heartRating.mHasHeart && this.mIsRated == heartRating.mIsRated) {
            return true;
        }
        return false;
    }

    public String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("HeartRating: ");
        if (this.mIsRated) {
            str = "hasHeart=" + this.mHasHeart;
        } else {
            str = "unrated";
        }
        sb.append(str);
        return sb.toString();
    }

    public boolean hasHeart() {
        return this.mHasHeart;
    }
}

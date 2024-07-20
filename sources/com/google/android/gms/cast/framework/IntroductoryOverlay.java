package com.google.android.gms.cast.framework;

import android.app.Activity;
import android.content.Context;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import androidx.mediarouter.app.MediaRouteButton;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.cast.zzn;
import com.google.android.gms.internal.cast.zzr;

public interface IntroductoryOverlay {

    public static class Builder {
        private final Activity zzhv;
        private final View zzhw;
        private int zzhx;
        private String zzhy;
        private OnOverlayDismissedListener zzhz;
        private boolean zzia;
        private float zzib;
        private String zzic;

        public Builder(Activity activity, MenuItem menuItem) {
            this.zzhv = (Activity) Preconditions.checkNotNull(activity);
            this.zzhw = ((MenuItem) Preconditions.checkNotNull(menuItem)).getActionView();
        }

        public Builder(Activity activity, MediaRouteButton mediaRouteButton) {
            this.zzhv = (Activity) Preconditions.checkNotNull(activity);
            this.zzhw = (View) Preconditions.checkNotNull(mediaRouteButton);
        }

        public IntroductoryOverlay build() {
            return PlatformVersion.isAtLeastJellyBean() ? new zzn(this) : new zzr(this);
        }

        public final Activity getActivity() {
            return this.zzhv;
        }

        public Builder setButtonText(int i) {
            this.zzic = this.zzhv.getResources().getString(i);
            return this;
        }

        public Builder setButtonText(String str) {
            this.zzic = str;
            return this;
        }

        public Builder setFocusRadius(float f) {
            this.zzib = f;
            return this;
        }

        public Builder setFocusRadiusId(int i) {
            this.zzib = this.zzhv.getResources().getDimension(i);
            return this;
        }

        public Builder setOnOverlayDismissedListener(OnOverlayDismissedListener onOverlayDismissedListener) {
            this.zzhz = onOverlayDismissedListener;
            return this;
        }

        public Builder setOverlayColor(int i) {
            this.zzhx = this.zzhv.getResources().getColor(i);
            return this;
        }

        public Builder setSingleTime() {
            this.zzia = true;
            return this;
        }

        public Builder setTitleText(int i) {
            this.zzhy = this.zzhv.getResources().getString(i);
            return this;
        }

        public Builder setTitleText(String str) {
            this.zzhy = str;
            return this;
        }

        public final View zzab() {
            return this.zzhw;
        }

        public final OnOverlayDismissedListener zzac() {
            return this.zzhz;
        }

        public final int zzad() {
            return this.zzhx;
        }

        public final boolean zzae() {
            return this.zzia;
        }

        public final String zzaf() {
            return this.zzhy;
        }

        public final String zzag() {
            return this.zzic;
        }

        public final float zzah() {
            return this.zzib;
        }
    }

    public interface OnOverlayDismissedListener {
        void onOverlayDismissed();
    }

    public static class zza {
        public static void zzd(Context context) {
            PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("googlecast-introOverlayShown", true).apply();
        }

        public static boolean zze(Context context) {
            return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("googlecast-introOverlayShown", false);
        }
    }

    void remove();

    void show();
}

package com.google.android.gms.cast.framework.media.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.C0069R;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.uicontroller.UIMediaController;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzdg;

public class MiniControllerFragment extends Fragment implements ControlButtonsContainer {
    private static final zzdg zzbd = new zzdg("MiniControllerFragment");
    private int zzre;
    private int zzrf;
    private int zzrg;
    private int zzrh;
    private int zzri;
    private int zzrj;
    private int zzrk;
    private int zzrl;
    private int zzrm;
    private int zzrn;
    private int[] zzrs;
    private ImageView[] zzrt = new ImageView[3];
    private UIMediaController zzsb;
    private boolean zzsj;
    private int zzsk;
    private int zzsl;
    private TextView zzsm;
    private int zzsn;
    private int zzso;
    private int zzsp;
    private int zzsq;
    private int zzsr;
    private int zzss;

    private final void zza(RelativeLayout relativeLayout, int i, int i2) {
        ImageView imageView = (ImageView) relativeLayout.findViewById(i);
        int i3 = this.zzrs[i2];
        if (i3 == C0069R.C0071id.cast_button_type_empty) {
            imageView.setVisibility(4);
        } else if (i3 == C0069R.C0071id.cast_button_type_custom) {
        } else {
            if (i3 == C0069R.C0071id.cast_button_type_play_pause_toggle) {
                int i4 = this.zzre;
                int i5 = this.zzrf;
                int i6 = this.zzrg;
                if (this.zzsp == 1) {
                    i4 = this.zzsq;
                    i5 = this.zzsr;
                    i6 = this.zzss;
                }
                Drawable zza = zze.zza(getContext(), this.zzrn, i4);
                Drawable zza2 = zze.zza(getContext(), this.zzrn, i5);
                Drawable zza3 = zze.zza(getContext(), this.zzrn, i6);
                imageView.setImageDrawable(zza2);
                ProgressBar progressBar = new ProgressBar(getContext());
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(8, i);
                layoutParams.addRule(6, i);
                layoutParams.addRule(5, i);
                layoutParams.addRule(7, i);
                layoutParams.addRule(15);
                progressBar.setLayoutParams(layoutParams);
                progressBar.setVisibility(8);
                Drawable indeterminateDrawable = progressBar.getIndeterminateDrawable();
                int i7 = this.zzso;
                if (!(i7 == 0 || indeterminateDrawable == null)) {
                    indeterminateDrawable.setColorFilter(i7, PorterDuff.Mode.SRC_IN);
                }
                relativeLayout.addView(progressBar);
                this.zzsb.bindImageViewToPlayPauseToggle(imageView, zza, zza2, zza3, progressBar, true);
            } else if (i3 == C0069R.C0071id.cast_button_type_skip_previous) {
                imageView.setImageDrawable(zze.zza(getContext(), this.zzrn, this.zzrh));
                imageView.setContentDescription(getResources().getString(C0069R.string.cast_skip_prev));
                this.zzsb.bindViewToSkipPrev(imageView, 0);
            } else if (i3 == C0069R.C0071id.cast_button_type_skip_next) {
                imageView.setImageDrawable(zze.zza(getContext(), this.zzrn, this.zzri));
                imageView.setContentDescription(getResources().getString(C0069R.string.cast_skip_next));
                this.zzsb.bindViewToSkipNext(imageView, 0);
            } else if (i3 == C0069R.C0071id.cast_button_type_rewind_30_seconds) {
                imageView.setImageDrawable(zze.zza(getContext(), this.zzrn, this.zzrj));
                imageView.setContentDescription(getResources().getString(C0069R.string.cast_rewind_30));
                this.zzsb.bindViewToRewind(imageView, 30000);
            } else if (i3 == C0069R.C0071id.cast_button_type_forward_30_seconds) {
                imageView.setImageDrawable(zze.zza(getContext(), this.zzrn, this.zzrk));
                imageView.setContentDescription(getResources().getString(C0069R.string.cast_forward_30));
                this.zzsb.bindViewToForward(imageView, 30000);
            } else if (i3 == C0069R.C0071id.cast_button_type_mute_toggle) {
                imageView.setImageDrawable(zze.zza(getContext(), this.zzrn, this.zzrl));
                this.zzsb.bindImageViewToMuteToggle(imageView);
            } else if (i3 == C0069R.C0071id.cast_button_type_closed_caption) {
                imageView.setImageDrawable(zze.zza(getContext(), this.zzrn, this.zzrm));
                this.zzsb.bindViewToClosedCaption(imageView);
            }
        }
    }

    public final ImageView getButtonImageViewAt(int i) throws IndexOutOfBoundsException {
        return this.zzrt[i];
    }

    public final int getButtonSlotCount() {
        return 3;
    }

    public final int getButtonTypeAt(int i) throws IndexOutOfBoundsException {
        return this.zzrs[i];
    }

    public UIMediaController getUIMediaController() {
        return this.zzsb;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.zzsb = new UIMediaController(getActivity());
        View inflate = layoutInflater.inflate(C0069R.layout.cast_mini_controller, viewGroup);
        inflate.setVisibility(8);
        this.zzsb.bindViewVisibilityToMediaSession(inflate, 8);
        RelativeLayout relativeLayout = (RelativeLayout) inflate.findViewById(C0069R.C0071id.container_current);
        int i = this.zzsn;
        if (i != 0) {
            relativeLayout.setBackgroundResource(i);
        }
        ImageView imageView = (ImageView) inflate.findViewById(C0069R.C0071id.icon_view);
        TextView textView = (TextView) inflate.findViewById(C0069R.C0071id.title_view);
        if (this.zzsk != 0) {
            textView.setTextAppearance(getActivity(), this.zzsk);
        }
        TextView textView2 = (TextView) inflate.findViewById(C0069R.C0071id.subtitle_view);
        this.zzsm = textView2;
        if (this.zzsl != 0) {
            textView2.setTextAppearance(getActivity(), this.zzsl);
        }
        ProgressBar progressBar = (ProgressBar) inflate.findViewById(C0069R.C0071id.progressBar);
        if (this.zzso != 0) {
            ((LayerDrawable) progressBar.getProgressDrawable()).setColorFilter(this.zzso, PorterDuff.Mode.SRC_IN);
        }
        this.zzsb.bindTextViewToMetadataOfCurrentItem(textView, MediaMetadata.KEY_TITLE);
        this.zzsb.bindTextViewToSmartSubtitle(this.zzsm);
        this.zzsb.bindProgressBar(progressBar);
        this.zzsb.bindViewToLaunchExpandedController(relativeLayout);
        if (this.zzsj) {
            this.zzsb.bindImageViewToImageOfCurrentItem(imageView, new ImageHints(2, getResources().getDimensionPixelSize(C0069R.dimen.cast_mini_controller_icon_width), getResources().getDimensionPixelSize(C0069R.dimen.cast_mini_controller_icon_height)), C0069R.C0070drawable.cast_album_art_placeholder);
        } else {
            imageView.setVisibility(8);
        }
        this.zzrt[0] = (ImageView) relativeLayout.findViewById(C0069R.C0071id.button_0);
        this.zzrt[1] = (ImageView) relativeLayout.findViewById(C0069R.C0071id.button_1);
        this.zzrt[2] = (ImageView) relativeLayout.findViewById(C0069R.C0071id.button_2);
        zza(relativeLayout, C0069R.C0071id.button_0, 0);
        zza(relativeLayout, C0069R.C0071id.button_1, 1);
        zza(relativeLayout, C0069R.C0071id.button_2, 2);
        return inflate;
    }

    public void onDestroy() {
        UIMediaController uIMediaController = this.zzsb;
        if (uIMediaController != null) {
            uIMediaController.dispose();
            this.zzsb = null;
        }
        super.onDestroy();
    }

    public void onInflate(Context context, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(context, attributeSet, bundle);
        if (this.zzrs == null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0069R.styleable.CastMiniController, C0069R.attr.castMiniControllerStyle, C0069R.style.CastMiniController);
            this.zzsj = obtainStyledAttributes.getBoolean(C0069R.styleable.CastMiniController_castShowImageThumbnail, true);
            this.zzsk = obtainStyledAttributes.getResourceId(C0069R.styleable.CastMiniController_castTitleTextAppearance, 0);
            this.zzsl = obtainStyledAttributes.getResourceId(C0069R.styleable.CastMiniController_castSubtitleTextAppearance, 0);
            this.zzsn = obtainStyledAttributes.getResourceId(C0069R.styleable.CastMiniController_castBackground, 0);
            this.zzso = obtainStyledAttributes.getColor(C0069R.styleable.CastMiniController_castProgressBarColor, 0);
            this.zzrn = obtainStyledAttributes.getResourceId(C0069R.styleable.CastMiniController_castButtonColor, 0);
            this.zzre = obtainStyledAttributes.getResourceId(C0069R.styleable.CastMiniController_castPlayButtonDrawable, 0);
            this.zzrf = obtainStyledAttributes.getResourceId(C0069R.styleable.CastMiniController_castPauseButtonDrawable, 0);
            this.zzrg = obtainStyledAttributes.getResourceId(C0069R.styleable.CastMiniController_castStopButtonDrawable, 0);
            this.zzsq = obtainStyledAttributes.getResourceId(C0069R.styleable.CastMiniController_castPlayButtonDrawable, 0);
            this.zzsr = obtainStyledAttributes.getResourceId(C0069R.styleable.CastMiniController_castPauseButtonDrawable, 0);
            this.zzss = obtainStyledAttributes.getResourceId(C0069R.styleable.CastMiniController_castStopButtonDrawable, 0);
            this.zzrh = obtainStyledAttributes.getResourceId(C0069R.styleable.CastMiniController_castSkipPreviousButtonDrawable, 0);
            this.zzri = obtainStyledAttributes.getResourceId(C0069R.styleable.CastMiniController_castSkipNextButtonDrawable, 0);
            this.zzrj = obtainStyledAttributes.getResourceId(C0069R.styleable.CastMiniController_castRewind30ButtonDrawable, 0);
            this.zzrk = obtainStyledAttributes.getResourceId(C0069R.styleable.CastMiniController_castForward30ButtonDrawable, 0);
            this.zzrl = obtainStyledAttributes.getResourceId(C0069R.styleable.CastMiniController_castMuteToggleButtonDrawable, 0);
            this.zzrm = obtainStyledAttributes.getResourceId(C0069R.styleable.CastMiniController_castClosedCaptionsButtonDrawable, 0);
            int resourceId = obtainStyledAttributes.getResourceId(C0069R.styleable.CastMiniController_castControlButtons, 0);
            if (resourceId != 0) {
                TypedArray obtainTypedArray = context.getResources().obtainTypedArray(resourceId);
                Preconditions.checkArgument(obtainTypedArray.length() == 3);
                this.zzrs = new int[obtainTypedArray.length()];
                for (int i = 0; i < obtainTypedArray.length(); i++) {
                    this.zzrs[i] = obtainTypedArray.getResourceId(i, 0);
                }
                obtainTypedArray.recycle();
                if (this.zzsj) {
                    this.zzrs[0] = C0069R.C0071id.cast_button_type_empty;
                }
                this.zzsp = 0;
                for (int i2 : this.zzrs) {
                    if (i2 != C0069R.C0071id.cast_button_type_empty) {
                        this.zzsp++;
                    }
                }
            } else {
                zzbd.mo6873w("Unable to read attribute castControlButtons.", new Object[0]);
                this.zzrs = new int[]{C0069R.C0071id.cast_button_type_empty, C0069R.C0071id.cast_button_type_empty, C0069R.C0071id.cast_button_type_empty};
            }
            obtainStyledAttributes.recycle();
        }
    }
}

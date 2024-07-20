package com.google.android.gms.cast.framework.media.widget;

import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.C3333R;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import com.google.android.gms.cast.AdBreakClipInfo;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.C0069R;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIMediaController;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.cast.zzam;
import com.google.android.gms.internal.cast.zzat;
import com.google.android.gms.internal.cast.zzx;
import com.google.android.gms.internal.cast.zzy;
import java.util.Timer;

public class ExpandedControllerActivity extends AppCompatActivity implements ControlButtonsContainer {
    private SessionManager zzgu;
    private final SessionManagerListener<CastSession> zzlq = new zzb(this, (zza) null);
    private final RemoteMediaClient.Listener zzps = new zza(this, (zza) null);
    private SeekBar zzqe;
    private int zzra;
    private int zzrb;
    private int zzrc;
    private int zzrd;
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
    /* access modifiers changed from: private */
    public TextView zzro;
    private ImageView zzrp;
    private ImageView zzrq;
    private zzam zzrr;
    private int[] zzrs;
    private ImageView[] zzrt = new ImageView[4];
    private View zzru;
    /* access modifiers changed from: private */
    public ImageView zzrv;
    /* access modifiers changed from: private */
    public TextView zzrw;
    private TextView zzrx;
    /* access modifiers changed from: private */
    public TextView zzry;
    private TextView zzrz;
    private zzx zzsa;
    private UIMediaController zzsb;
    /* access modifiers changed from: private */
    public boolean zzsc;
    private boolean zzsd;
    private Timer zzse;

    private class zza implements RemoteMediaClient.Listener {
        private zza() {
        }

        /* synthetic */ zza(ExpandedControllerActivity expandedControllerActivity, zza zza) {
            this();
        }

        public final void onAdBreakStatusUpdated() {
            ExpandedControllerActivity.this.zzcf();
        }

        public final void onMetadataUpdated() {
            ExpandedControllerActivity.this.zzcd();
        }

        public final void onPreloadStatusUpdated() {
        }

        public final void onQueueStatusUpdated() {
        }

        public final void onSendingRemoteMediaRequest() {
            ExpandedControllerActivity.this.zzro.setText(ExpandedControllerActivity.this.getResources().getString(C0069R.string.cast_expanded_controller_loading));
        }

        public final void onStatusUpdated() {
            RemoteMediaClient zzd = ExpandedControllerActivity.this.getRemoteMediaClient();
            if (zzd != null && zzd.hasMediaSession()) {
                boolean unused = ExpandedControllerActivity.this.zzsc = false;
                ExpandedControllerActivity.this.zzce();
                ExpandedControllerActivity.this.zzcf();
            } else if (!ExpandedControllerActivity.this.zzsc) {
                ExpandedControllerActivity.this.finish();
            }
        }
    }

    private class zzb implements SessionManagerListener<CastSession> {
        private zzb() {
        }

        /* synthetic */ zzb(ExpandedControllerActivity expandedControllerActivity, zza zza) {
            this();
        }

        public final /* synthetic */ void onSessionEnded(Session session, int i) {
            ExpandedControllerActivity.this.finish();
        }

        public final /* bridge */ /* synthetic */ void onSessionEnding(Session session) {
        }

        public final /* bridge */ /* synthetic */ void onSessionResumeFailed(Session session, int i) {
        }

        public final /* bridge */ /* synthetic */ void onSessionResumed(Session session, boolean z) {
        }

        public final /* bridge */ /* synthetic */ void onSessionResuming(Session session, String str) {
        }

        public final /* bridge */ /* synthetic */ void onSessionStartFailed(Session session, int i) {
        }

        public final /* bridge */ /* synthetic */ void onSessionStarted(Session session, String str) {
        }

        public final /* bridge */ /* synthetic */ void onSessionStarting(Session session) {
        }

        public final /* bridge */ /* synthetic */ void onSessionSuspended(Session session, int i) {
        }
    }

    /* access modifiers changed from: private */
    public final RemoteMediaClient getRemoteMediaClient() {
        CastSession currentCastSession = this.zzgu.getCurrentCastSession();
        if (currentCastSession == null || !currentCastSession.isConnected()) {
            return null;
        }
        return currentCastSession.getRemoteMediaClient();
    }

    private final void zza(View view, int i, int i2, UIMediaController uIMediaController) {
        ImageView imageView = (ImageView) view.findViewById(i);
        if (i2 == C0069R.C0071id.cast_button_type_empty) {
            imageView.setVisibility(4);
        } else if (i2 == C0069R.C0071id.cast_button_type_custom) {
        } else {
            if (i2 == C0069R.C0071id.cast_button_type_play_pause_toggle) {
                imageView.setBackgroundResource(this.zzra);
                Drawable zzb2 = zze.zzb(this, this.zzrn, this.zzrf);
                Drawable zzb3 = zze.zzb(this, this.zzrn, this.zzre);
                Drawable zzb4 = zze.zzb(this, this.zzrn, this.zzrg);
                imageView.setImageDrawable(zzb3);
                uIMediaController.bindImageViewToPlayPauseToggle(imageView, zzb3, zzb2, zzb4, (View) null, false);
            } else if (i2 == C0069R.C0071id.cast_button_type_skip_previous) {
                imageView.setBackgroundResource(this.zzra);
                imageView.setImageDrawable(zze.zzb(this, this.zzrn, this.zzrh));
                imageView.setContentDescription(getResources().getString(C0069R.string.cast_skip_prev));
                uIMediaController.bindViewToSkipPrev(imageView, 0);
            } else if (i2 == C0069R.C0071id.cast_button_type_skip_next) {
                imageView.setBackgroundResource(this.zzra);
                imageView.setImageDrawable(zze.zzb(this, this.zzrn, this.zzri));
                imageView.setContentDescription(getResources().getString(C0069R.string.cast_skip_next));
                uIMediaController.bindViewToSkipNext(imageView, 0);
            } else if (i2 == C0069R.C0071id.cast_button_type_rewind_30_seconds) {
                imageView.setBackgroundResource(this.zzra);
                imageView.setImageDrawable(zze.zzb(this, this.zzrn, this.zzrj));
                imageView.setContentDescription(getResources().getString(C0069R.string.cast_rewind_30));
                uIMediaController.bindViewToRewind(imageView, 30000);
            } else if (i2 == C0069R.C0071id.cast_button_type_forward_30_seconds) {
                imageView.setBackgroundResource(this.zzra);
                imageView.setImageDrawable(zze.zzb(this, this.zzrn, this.zzrk));
                imageView.setContentDescription(getResources().getString(C0069R.string.cast_forward_30));
                uIMediaController.bindViewToForward(imageView, 30000);
            } else if (i2 == C0069R.C0071id.cast_button_type_mute_toggle) {
                imageView.setBackgroundResource(this.zzra);
                imageView.setImageDrawable(zze.zzb(this, this.zzrn, this.zzrl));
                uIMediaController.bindImageViewToMuteToggle(imageView);
            } else if (i2 == C0069R.C0071id.cast_button_type_closed_caption) {
                imageView.setBackgroundResource(this.zzra);
                imageView.setImageDrawable(zze.zzb(this, this.zzrn, this.zzrm));
                uIMediaController.bindViewToClosedCaption(imageView);
            }
        }
    }

    /* access modifiers changed from: private */
    public final void zza(AdBreakClipInfo adBreakClipInfo, RemoteMediaClient remoteMediaClient) {
        if (!this.zzsc && !remoteMediaClient.isBuffering()) {
            this.zzry.setVisibility(8);
            if (adBreakClipInfo != null && adBreakClipInfo.getWhenSkippableInMs() != -1) {
                if (!this.zzsd) {
                    zzc zzc = new zzc(this, adBreakClipInfo, remoteMediaClient);
                    Timer timer = new Timer();
                    this.zzse = timer;
                    timer.scheduleAtFixedRate(zzc, 0, 500);
                    this.zzsd = true;
                }
                float whenSkippableInMs = (float) (adBreakClipInfo.getWhenSkippableInMs() - remoteMediaClient.getApproximateAdBreakClipPositionMs());
                if (whenSkippableInMs <= 0.0f) {
                    this.zzrz.setVisibility(8);
                    if (this.zzsd) {
                        this.zzse.cancel();
                        this.zzsd = false;
                    }
                    this.zzry.setVisibility(0);
                    this.zzry.setClickable(true);
                    return;
                }
                this.zzrz.setVisibility(0);
                this.zzrz.setText(String.format(getResources().getString(C0069R.string.cast_expanded_controller_skip_ad_text), new Object[]{Integer.valueOf(((int) whenSkippableInMs) / 1000)}));
                this.zzry.setClickable(false);
            }
        }
    }

    private final ColorStateList zzcc() {
        int color = getResources().getColor(this.zzrb);
        TypedValue typedValue = new TypedValue();
        getResources().getValue(C0069R.dimen.cast_expanded_controller_seekbar_disabled_alpha, typedValue, true);
        int[] iArr = {color, Color.argb((int) (typedValue.getFloat() * ((float) Color.alpha(color))), Color.red(color), Color.green(color), Color.blue(color))};
        return new ColorStateList(new int[][]{new int[]{16842910}, new int[]{-16842910}}, iArr);
    }

    /* access modifiers changed from: private */
    public final void zzcd() {
        MediaInfo mediaInfo;
        MediaMetadata metadata;
        ActionBar supportActionBar;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession() && (mediaInfo = remoteMediaClient.getMediaInfo()) != null && (metadata = mediaInfo.getMetadata()) != null && (supportActionBar = getSupportActionBar()) != null) {
            supportActionBar.setTitle((CharSequence) metadata.getString(MediaMetadata.KEY_TITLE));
        }
    }

    /* access modifiers changed from: private */
    public final void zzce() {
        CastDevice castDevice;
        CastSession currentCastSession = this.zzgu.getCurrentCastSession();
        if (!(currentCastSession == null || (castDevice = currentCastSession.getCastDevice()) == null)) {
            String friendlyName = castDevice.getFriendlyName();
            if (!TextUtils.isEmpty(friendlyName)) {
                this.zzro.setText(getResources().getString(C0069R.string.cast_casting_to_device, new Object[]{friendlyName}));
                return;
            }
        }
        this.zzro.setText("");
    }

    /* access modifiers changed from: private */
    public final void zzcf() {
        String str;
        Drawable drawable;
        Bitmap bitmap;
        Bitmap zza2;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        String str2 = null;
        MediaInfo mediaInfo = remoteMediaClient == null ? null : remoteMediaClient.getMediaInfo();
        MediaStatus mediaStatus = remoteMediaClient == null ? null : remoteMediaClient.getMediaStatus();
        if (mediaStatus != null && mediaStatus.isPlayingAd()) {
            this.zzqe.setEnabled(false);
            if (PlatformVersion.isAtLeastJellyBeanMR1() && this.zzrq.getVisibility() == 8 && (drawable = this.zzrp.getDrawable()) != null && (drawable instanceof BitmapDrawable) && (bitmap = ((BitmapDrawable) drawable).getBitmap()) != null && (zza2 = zze.zza(this, bitmap, 0.25f, 7.5f)) != null) {
                this.zzrq.setImageBitmap(zza2);
                this.zzrq.setVisibility(0);
            }
            AdBreakClipInfo currentAdBreakClip = mediaStatus.getCurrentAdBreakClip();
            if (currentAdBreakClip != null) {
                str = currentAdBreakClip.getTitle();
                str2 = currentAdBreakClip.getImageUrl();
            } else {
                str = null;
            }
            this.zzrw.setVisibility(0);
            if (!TextUtils.isEmpty(str2)) {
                this.zzsa.zza(Uri.parse(str2));
            } else {
                this.zzrv.setVisibility(8);
            }
            TextView textView = this.zzrx;
            if (TextUtils.isEmpty(str)) {
                str = getResources().getString(C0069R.string.cast_ad_label);
            }
            textView.setText(str);
            this.zzqe.setEnabled(false);
            this.zzru.setVisibility(0);
            zza(currentAdBreakClip, remoteMediaClient);
        } else {
            this.zzqe.setEnabled(true);
            this.zzrz.setVisibility(8);
            this.zzry.setVisibility(8);
            this.zzru.setVisibility(8);
            if (PlatformVersion.isAtLeastJellyBeanMR1()) {
                this.zzrq.setVisibility(8);
                this.zzrq.setImageBitmap((Bitmap) null);
            }
        }
        if (mediaInfo != null) {
            this.zzrr.zzj(this.zzqe.getMax());
            this.zzrr.zzb(mediaInfo.getAdBreaks(), -1);
        }
    }

    public final ImageView getButtonImageViewAt(int i) throws IndexOutOfBoundsException {
        return this.zzrt[i];
    }

    public final int getButtonSlotCount() {
        return 4;
    }

    public final int getButtonTypeAt(int i) throws IndexOutOfBoundsException {
        return this.zzrs[i];
    }

    public SeekBar getSeekBar() {
        return this.zzqe;
    }

    public TextView getStatusTextView() {
        return this.zzro;
    }

    public UIMediaController getUIMediaController() {
        return this.zzsb;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SessionManager sessionManager = CastContext.getSharedInstance(this).getSessionManager();
        this.zzgu = sessionManager;
        if (sessionManager.getCurrentCastSession() == null) {
            finish();
        }
        UIMediaController uIMediaController = new UIMediaController(this);
        this.zzsb = uIMediaController;
        uIMediaController.setPostRemoteMediaClientListener(this.zzps);
        setContentView(C0069R.layout.cast_expanded_controller_activity);
        TypedArray obtainStyledAttributes = obtainStyledAttributes(new int[]{C3333R.attr.selectableItemBackgroundBorderless, C3333R.attr.colorControlActivated});
        this.zzra = obtainStyledAttributes.getResourceId(0, 0);
        this.zzrb = obtainStyledAttributes.getResourceId(1, 0);
        obtainStyledAttributes.recycle();
        ColorStateList colorStateList = null;
        TypedArray obtainStyledAttributes2 = obtainStyledAttributes((AttributeSet) null, C0069R.styleable.CastExpandedController, C0069R.attr.castExpandedControllerStyle, C0069R.style.CastExpandedController);
        this.zzrn = obtainStyledAttributes2.getResourceId(C0069R.styleable.CastExpandedController_castButtonColor, 0);
        this.zzrc = obtainStyledAttributes2.getResourceId(C0069R.styleable.CastExpandedController_castSeekBarProgressDrawable, 0);
        this.zzrd = obtainStyledAttributes2.getResourceId(C0069R.styleable.CastExpandedController_castSeekBarThumbDrawable, 0);
        this.zzre = obtainStyledAttributes2.getResourceId(C0069R.styleable.CastExpandedController_castPlayButtonDrawable, 0);
        this.zzrf = obtainStyledAttributes2.getResourceId(C0069R.styleable.CastExpandedController_castPauseButtonDrawable, 0);
        this.zzrg = obtainStyledAttributes2.getResourceId(C0069R.styleable.CastExpandedController_castStopButtonDrawable, 0);
        this.zzrh = obtainStyledAttributes2.getResourceId(C0069R.styleable.CastExpandedController_castSkipPreviousButtonDrawable, 0);
        this.zzri = obtainStyledAttributes2.getResourceId(C0069R.styleable.CastExpandedController_castSkipNextButtonDrawable, 0);
        this.zzrj = obtainStyledAttributes2.getResourceId(C0069R.styleable.CastExpandedController_castRewind30ButtonDrawable, 0);
        this.zzrk = obtainStyledAttributes2.getResourceId(C0069R.styleable.CastExpandedController_castForward30ButtonDrawable, 0);
        this.zzrl = obtainStyledAttributes2.getResourceId(C0069R.styleable.CastExpandedController_castMuteToggleButtonDrawable, 0);
        this.zzrm = obtainStyledAttributes2.getResourceId(C0069R.styleable.CastExpandedController_castClosedCaptionsButtonDrawable, 0);
        int resourceId = obtainStyledAttributes2.getResourceId(C0069R.styleable.CastExpandedController_castControlButtons, 0);
        if (resourceId != 0) {
            TypedArray obtainTypedArray = getResources().obtainTypedArray(resourceId);
            Preconditions.checkArgument(obtainTypedArray.length() == 4);
            this.zzrs = new int[obtainTypedArray.length()];
            for (int i = 0; i < obtainTypedArray.length(); i++) {
                this.zzrs[i] = obtainTypedArray.getResourceId(i, 0);
            }
            obtainTypedArray.recycle();
        } else {
            this.zzrs = new int[]{C0069R.C0071id.cast_button_type_empty, C0069R.C0071id.cast_button_type_empty, C0069R.C0071id.cast_button_type_empty, C0069R.C0071id.cast_button_type_empty};
        }
        obtainStyledAttributes2.recycle();
        View findViewById = findViewById(C0069R.C0071id.expanded_controller_layout);
        UIMediaController uIMediaController2 = this.zzsb;
        this.zzrp = (ImageView) findViewById.findViewById(C0069R.C0071id.background_image_view);
        this.zzrq = (ImageView) findViewById.findViewById(C0069R.C0071id.blurred_background_image_view);
        View findViewById2 = findViewById.findViewById(C0069R.C0071id.background_place_holder_image_view);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        uIMediaController2.bindImageViewToImageOfCurrentItem(this.zzrp, new ImageHints(4, displayMetrics.widthPixels, displayMetrics.heightPixels), findViewById2);
        this.zzro = (TextView) findViewById.findViewById(C0069R.C0071id.status_text);
        uIMediaController2.bindViewToLoadingIndicator((ProgressBar) findViewById.findViewById(C0069R.C0071id.loading_indicator));
        TextView textView = (TextView) findViewById.findViewById(C0069R.C0071id.start_text);
        TextView textView2 = (TextView) findViewById.findViewById(C0069R.C0071id.end_text);
        ImageView imageView = (ImageView) findViewById.findViewById(C0069R.C0071id.live_stream_indicator);
        this.zzqe = (SeekBar) findViewById.findViewById(C0069R.C0071id.seek_bar);
        Drawable drawable = getResources().getDrawable(this.zzrc);
        if (drawable != null) {
            if (this.zzrc == C0069R.C0070drawable.cast_expanded_controller_seekbar_track) {
                colorStateList = zzcc();
                LayerDrawable layerDrawable = (LayerDrawable) drawable;
                Drawable wrap = DrawableCompat.wrap(layerDrawable.findDrawableByLayerId(16908301));
                DrawableCompat.setTintList(wrap, colorStateList);
                layerDrawable.setDrawableByLayerId(16908301, wrap);
                layerDrawable.findDrawableByLayerId(16908288).setColorFilter(getResources().getColor(C0069R.color.cast_expanded_controller_seek_bar_progress_background_tint_color), PorterDuff.Mode.SRC_IN);
            }
            this.zzqe.setProgressDrawable(drawable);
        }
        Drawable drawable2 = getResources().getDrawable(this.zzrd);
        if (drawable2 != null) {
            if (this.zzrd == C0069R.C0070drawable.cast_expanded_controller_seekbar_thumb) {
                if (colorStateList == null) {
                    colorStateList = zzcc();
                }
                drawable2 = DrawableCompat.wrap(drawable2);
                DrawableCompat.setTintList(drawable2, colorStateList);
            }
            this.zzqe.setThumb(drawable2);
        }
        if (PlatformVersion.isAtLeastLollipop()) {
            this.zzqe.setSplitTrack(false);
        }
        SeekBar seekBar = (SeekBar) findViewById.findViewById(C0069R.C0071id.live_stream_seek_bar);
        uIMediaController2.bindTextViewToStreamPosition(textView, true);
        uIMediaController2.bindTextViewToStreamDuration(textView2, imageView);
        uIMediaController2.bindSeekBar(this.zzqe);
        uIMediaController2.bindViewToUIController(seekBar, new zzat(seekBar, this.zzqe));
        this.zzrt[0] = (ImageView) findViewById.findViewById(C0069R.C0071id.button_0);
        this.zzrt[1] = (ImageView) findViewById.findViewById(C0069R.C0071id.button_1);
        this.zzrt[2] = (ImageView) findViewById.findViewById(C0069R.C0071id.button_2);
        this.zzrt[3] = (ImageView) findViewById.findViewById(C0069R.C0071id.button_3);
        zza(findViewById, C0069R.C0071id.button_0, this.zzrs[0], uIMediaController2);
        zza(findViewById, C0069R.C0071id.button_1, this.zzrs[1], uIMediaController2);
        zza(findViewById, C0069R.C0071id.button_play_pause_toggle, C0069R.C0071id.cast_button_type_play_pause_toggle, uIMediaController2);
        zza(findViewById, C0069R.C0071id.button_2, this.zzrs[2], uIMediaController2);
        zza(findViewById, C0069R.C0071id.button_3, this.zzrs[3], uIMediaController2);
        View findViewById3 = findViewById(C0069R.C0071id.ad_container);
        this.zzru = findViewById3;
        this.zzrv = (ImageView) findViewById3.findViewById(C0069R.C0071id.ad_image_view);
        this.zzrx = (TextView) this.zzru.findViewById(C0069R.C0071id.ad_label);
        this.zzrw = (TextView) this.zzru.findViewById(C0069R.C0071id.ad_in_progress_label);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById.findViewById(C0069R.C0071id.seek_bar_controls);
        zzam zzam = new zzam(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(0, C0069R.C0071id.end_text);
        layoutParams.addRule(1, C0069R.C0071id.start_text);
        layoutParams.addRule(6, C0069R.C0071id.seek_bar);
        layoutParams.addRule(7, C0069R.C0071id.seek_bar);
        layoutParams.addRule(5, C0069R.C0071id.seek_bar);
        layoutParams.addRule(8, C0069R.C0071id.seek_bar);
        zzam.setLayoutParams(layoutParams);
        if (PlatformVersion.isAtLeastJellyBeanMR1()) {
            zzam.setPaddingRelative(this.zzqe.getPaddingStart(), this.zzqe.getPaddingTop(), this.zzqe.getPaddingEnd(), this.zzqe.getPaddingBottom());
        } else {
            zzam.setPadding(this.zzqe.getPaddingLeft(), this.zzqe.getPaddingTop(), this.zzqe.getPaddingRight(), this.zzqe.getPaddingBottom());
        }
        zzam.setContentDescription(getResources().getString(C0069R.string.cast_seek_bar));
        zzam.setBackgroundColor(0);
        relativeLayout.addView(zzam);
        this.zzrr = zzam;
        this.zzrz = (TextView) findViewById(C0069R.C0071id.ad_skip_text);
        TextView textView3 = (TextView) findViewById(C0069R.C0071id.ad_skip_button);
        this.zzry = textView3;
        textView3.setOnClickListener(new zzb(this));
        setSupportActionBar((Toolbar) findViewById(C0069R.C0071id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(C0069R.C0070drawable.quantum_ic_keyboard_arrow_down_white_36);
        }
        zzce();
        zzcd();
        zzx zzx = new zzx(getApplicationContext(), new ImageHints(-1, this.zzrv.getWidth(), this.zzrv.getHeight()));
        this.zzsa = zzx;
        zzx.zza((zzy) new zza(this));
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.zzsa.clear();
        UIMediaController uIMediaController = this.zzsb;
        if (uIMediaController != null) {
            uIMediaController.setPostRemoteMediaClientListener((RemoteMediaClient.Listener) null);
            this.zzsb.dispose();
        }
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return true;
        }
        finish();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        CastContext.getSharedInstance(this).getSessionManager().removeSessionManagerListener(this.zzlq, CastSession.class);
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        CastContext.getSharedInstance(this).getSessionManager().addSessionManagerListener(this.zzlq, CastSession.class);
        CastSession currentCastSession = CastContext.getSharedInstance(this).getSessionManager().getCurrentCastSession();
        if (currentCastSession == null || (!currentCastSession.isConnected() && !currentCastSession.isConnecting())) {
            finish();
        }
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        this.zzsc = remoteMediaClient == null || !remoteMediaClient.hasMediaSession();
        zzce();
        zzcf();
        super.onResume();
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            int systemUiVisibility = getWindow().getDecorView().getSystemUiVisibility() ^ 2;
            if (PlatformVersion.isAtLeastJellyBean()) {
                systemUiVisibility ^= 4;
            }
            if (PlatformVersion.isAtLeastKitKat()) {
                systemUiVisibility ^= 4096;
            }
            getWindow().getDecorView().setSystemUiVisibility(systemUiVisibility);
            if (PlatformVersion.isAtLeastJellyBeanMR2()) {
                setImmersive(true);
            }
        }
    }
}

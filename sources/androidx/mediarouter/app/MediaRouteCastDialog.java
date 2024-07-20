package androidx.mediarouter.app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.p064v4.media.MediaDescriptionCompat;
import android.support.p064v4.media.MediaMetadataCompat;
import android.support.p064v4.media.session.MediaControllerCompat;
import android.support.p064v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import androidx.core.util.ObjectsCompat;
import androidx.mediarouter.C4427R;
import androidx.mediarouter.app.MediaRouteChooserDialog;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.mopub.common.Constants;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MediaRouteCastDialog extends AppCompatDialog {
    static final int CONNECTION_TIMEOUT_MILLIS = ((int) TimeUnit.SECONDS.toMillis(30));
    static final int MSG_UPDATE_ROUTES = 1;
    static final String TAG = "MediaRouteCastDialog";
    private static final long UPDATE_ROUTES_DELAY_MS = 300;
    private RecyclerAdapter mAdapter;
    int mArtIconBackgroundColor;
    Bitmap mArtIconBitmap;
    boolean mArtIconIsLoaded;
    Bitmap mArtIconLoadedBitmap;
    Uri mArtIconUri;
    private ImageView mArtView;
    private boolean mAttachedToWindow;
    private final MediaRouterCallback mCallback;
    private ImageButton mCloseButton;
    Context mContext;
    MediaControllerCallback mControllerCallback;
    private boolean mCreated;
    MediaDescriptionCompat mDescription;
    FetchArtTask mFetchArtTask;
    private final Handler mHandler;
    private long mLastUpdateTime;
    MediaControllerCompat mMediaController;
    private RelativeLayout mMetadataLayout;
    private RecyclerView mRecyclerView;
    final MediaRouter.RouteInfo mRoute;
    final MediaRouter mRouter;
    final List<MediaRouter.RouteInfo> mRoutes;
    private MediaRouteSelector mSelector;
    private Button mStopCastingButton;
    private TextView mSubtitleView;
    private String mTitlePlaceholder;
    private TextView mTitleView;
    VolumeChangeListener mVolumeChangeListener;
    int mVolumeSliderColor;

    public MediaRouteCastDialog(Context context) {
        this(context, 0);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public MediaRouteCastDialog(android.content.Context r2, int r3) {
        /*
            r1 = this;
            r0 = 0
            android.content.Context r2 = androidx.mediarouter.app.MediaRouterThemeHelper.createThemedDialogContext(r2, r3, r0)
            int r3 = androidx.mediarouter.app.MediaRouterThemeHelper.createThemedDialogStyle(r2)
            r1.<init>(r2, r3)
            androidx.mediarouter.media.MediaRouteSelector r2 = androidx.mediarouter.media.MediaRouteSelector.EMPTY
            r1.mSelector = r2
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            r1.mRoutes = r2
            androidx.mediarouter.app.MediaRouteCastDialog$1 r2 = new androidx.mediarouter.app.MediaRouteCastDialog$1
            r2.<init>()
            r1.mHandler = r2
            android.content.Context r2 = r1.getContext()
            r1.mContext = r2
            androidx.mediarouter.media.MediaRouter r2 = androidx.mediarouter.media.MediaRouter.getInstance(r2)
            r1.mRouter = r2
            androidx.mediarouter.app.MediaRouteCastDialog$MediaRouterCallback r2 = new androidx.mediarouter.app.MediaRouteCastDialog$MediaRouterCallback
            r2.<init>()
            r1.mCallback = r2
            androidx.mediarouter.media.MediaRouter r2 = r1.mRouter
            androidx.mediarouter.media.MediaRouter$RouteInfo r2 = r2.getSelectedRoute()
            r1.mRoute = r2
            androidx.mediarouter.app.MediaRouteCastDialog$MediaControllerCallback r2 = new androidx.mediarouter.app.MediaRouteCastDialog$MediaControllerCallback
            r2.<init>()
            r1.mControllerCallback = r2
            androidx.mediarouter.media.MediaRouter r2 = r1.mRouter
            android.support.v4.media.session.MediaSessionCompat$Token r2 = r2.getMediaSessionToken()
            r1.setMediaSession(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.app.MediaRouteCastDialog.<init>(android.content.Context, int):void");
    }

    private void setMediaSession(MediaSessionCompat.Token token) {
        MediaMetadataCompat mediaMetadataCompat;
        MediaControllerCompat mediaControllerCompat = this.mMediaController;
        MediaDescriptionCompat mediaDescriptionCompat = null;
        if (mediaControllerCompat != null) {
            mediaControllerCompat.unregisterCallback(this.mControllerCallback);
            this.mMediaController = null;
        }
        if (token != null && this.mAttachedToWindow) {
            try {
                this.mMediaController = new MediaControllerCompat(this.mContext, token);
            } catch (RemoteException e) {
                Log.e(TAG, "Error creating media controller in setMediaSession.", e);
            }
            MediaControllerCompat mediaControllerCompat2 = this.mMediaController;
            if (mediaControllerCompat2 != null) {
                mediaControllerCompat2.registerCallback(this.mControllerCallback);
            }
            MediaControllerCompat mediaControllerCompat3 = this.mMediaController;
            if (mediaControllerCompat3 == null) {
                mediaMetadataCompat = null;
            } else {
                mediaMetadataCompat = mediaControllerCompat3.getMetadata();
            }
            if (mediaMetadataCompat != null) {
                mediaDescriptionCompat = mediaMetadataCompat.getDescription();
            }
            this.mDescription = mediaDescriptionCompat;
            updateArtIconIfNeeded();
            update();
        }
    }

    public MediaSessionCompat.Token getMediaSession() {
        MediaControllerCompat mediaControllerCompat = this.mMediaController;
        if (mediaControllerCompat == null) {
            return null;
        }
        return mediaControllerCompat.getSessionToken();
    }

    public MediaRouteSelector getRouteSelector() {
        return this.mSelector;
    }

    public void setRouteSelector(MediaRouteSelector mediaRouteSelector) {
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        } else if (!this.mSelector.equals(mediaRouteSelector)) {
            this.mSelector = mediaRouteSelector;
            if (this.mAttachedToWindow) {
                this.mRouter.removeCallback(this.mCallback);
                this.mRouter.addCallback(mediaRouteSelector, this.mCallback, 1);
            }
            refreshRoutes();
        }
    }

    public void onFilterRoutes(List<MediaRouter.RouteInfo> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            if (!onFilterRoute(list.get(size))) {
                list.remove(size);
            }
        }
    }

    public boolean onFilterRoute(MediaRouter.RouteInfo routeInfo) {
        return !routeInfo.isDefaultOrBluetooth() && routeInfo.isEnabled() && routeInfo.matchesSelector(this.mSelector);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(C4427R.layout.mr_cast_dialog);
        ImageButton imageButton = (ImageButton) findViewById(C4427R.C4430id.mr_cast_close_button);
        this.mCloseButton = imageButton;
        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MediaRouteCastDialog.this.dismiss();
            }
        });
        Button button = (Button) findViewById(C4427R.C4430id.mr_cast_stop_button);
        this.mStopCastingButton = button;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MediaRouteCastDialog.this.mRoute.isSelected()) {
                    MediaRouteCastDialog.this.mRouter.unselect(2);
                }
                MediaRouteCastDialog.this.dismiss();
            }
        });
        this.mAdapter = new RecyclerAdapter();
        RecyclerView recyclerView = (RecyclerView) findViewById(C4427R.C4430id.mr_cast_list);
        this.mRecyclerView = recyclerView;
        recyclerView.setAdapter(this.mAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mVolumeChangeListener = new VolumeChangeListener();
        this.mVolumeSliderColor = MediaRouterThemeHelper.getControllerColor(this.mContext, 0);
        this.mMetadataLayout = (RelativeLayout) findViewById(C4427R.C4430id.mr_cast_meta);
        this.mArtView = (ImageView) findViewById(C4427R.C4430id.mr_cast_meta_art);
        this.mTitleView = (TextView) findViewById(C4427R.C4430id.mr_cast_meta_title);
        this.mSubtitleView = (TextView) findViewById(C4427R.C4430id.mr_cast_meta_subtitle);
        this.mTitlePlaceholder = this.mContext.getResources().getString(C4427R.string.mr_cast_dialog_title_view_placeholder);
        this.mCreated = true;
        updateLayout();
    }

    /* access modifiers changed from: package-private */
    public void updateLayout() {
        getWindow().setLayout(-1, -1);
        this.mArtIconBitmap = null;
        this.mArtIconUri = null;
        updateArtIconIfNeeded();
        update();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mAttachedToWindow = true;
        this.mRouter.addCallback(this.mSelector, this.mCallback, 1);
        refreshRoutes();
        setMediaSession(this.mRouter.getMediaSessionToken());
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.mAttachedToWindow = false;
        this.mRouter.removeCallback(this.mCallback);
        this.mHandler.removeMessages(1);
        setMediaSession((MediaSessionCompat.Token) null);
    }

    /* access modifiers changed from: package-private */
    public void update() {
        if (!this.mRoute.isSelected() || this.mRoute.isDefaultOrBluetooth()) {
            dismiss();
        } else if (this.mCreated) {
            if (this.mArtIconIsLoaded) {
                if (isBitmapRecycled(this.mArtIconLoadedBitmap)) {
                    this.mArtView.setVisibility(8);
                    Log.w(TAG, "Can't set artwork image with recycled bitmap: " + this.mArtIconLoadedBitmap);
                } else {
                    this.mArtView.setVisibility(0);
                    this.mArtView.setImageBitmap(this.mArtIconLoadedBitmap);
                    this.mArtView.setBackgroundColor(this.mArtIconBackgroundColor);
                    this.mMetadataLayout.setBackgroundDrawable(new BitmapDrawable(this.mArtIconLoadedBitmap));
                }
                clearLoadedBitmap();
            } else {
                this.mArtView.setVisibility(8);
            }
            updateMetadataLayout();
        }
    }

    static boolean isBitmapRecycled(Bitmap bitmap) {
        return bitmap != null && bitmap.isRecycled();
    }

    /* access modifiers changed from: package-private */
    public int getDesiredArtHeight(int i, int i2) {
        return this.mArtView.getHeight();
    }

    /* access modifiers changed from: package-private */
    public void updateArtIconIfNeeded() {
        if (isIconChanged()) {
            FetchArtTask fetchArtTask = this.mFetchArtTask;
            if (fetchArtTask != null) {
                fetchArtTask.cancel(true);
            }
            FetchArtTask fetchArtTask2 = new FetchArtTask();
            this.mFetchArtTask = fetchArtTask2;
            fetchArtTask2.execute(new Void[0]);
        }
    }

    /* access modifiers changed from: package-private */
    public void clearLoadedBitmap() {
        this.mArtIconIsLoaded = false;
        this.mArtIconLoadedBitmap = null;
        this.mArtIconBackgroundColor = 0;
    }

    private boolean isIconChanged() {
        MediaDescriptionCompat mediaDescriptionCompat = this.mDescription;
        Uri uri = null;
        Bitmap iconBitmap = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.getIconBitmap();
        MediaDescriptionCompat mediaDescriptionCompat2 = this.mDescription;
        if (mediaDescriptionCompat2 != null) {
            uri = mediaDescriptionCompat2.getIconUri();
        }
        FetchArtTask fetchArtTask = this.mFetchArtTask;
        Bitmap iconBitmap2 = fetchArtTask == null ? this.mArtIconBitmap : fetchArtTask.getIconBitmap();
        FetchArtTask fetchArtTask2 = this.mFetchArtTask;
        Uri iconUri = fetchArtTask2 == null ? this.mArtIconUri : fetchArtTask2.getIconUri();
        if (iconBitmap2 != iconBitmap) {
            return true;
        }
        if (iconBitmap2 != null || !ObjectsCompat.equals(iconUri, uri)) {
            return false;
        }
        return true;
    }

    private void updateMetadataLayout() {
        MediaDescriptionCompat mediaDescriptionCompat = this.mDescription;
        CharSequence charSequence = null;
        CharSequence title = mediaDescriptionCompat == null ? null : mediaDescriptionCompat.getTitle();
        boolean z = !TextUtils.isEmpty(title);
        MediaDescriptionCompat mediaDescriptionCompat2 = this.mDescription;
        if (mediaDescriptionCompat2 != null) {
            charSequence = mediaDescriptionCompat2.getSubtitle();
        }
        boolean z2 = !TextUtils.isEmpty(charSequence);
        if (z) {
            this.mTitleView.setText(title);
        } else {
            this.mTitleView.setText(this.mTitlePlaceholder);
        }
        if (z2) {
            this.mSubtitleView.setText(charSequence);
            this.mSubtitleView.setVisibility(0);
            return;
        }
        this.mSubtitleView.setVisibility(8);
    }

    private class VolumeChangeListener implements SeekBar.OnSeekBarChangeListener {
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        }

        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
        }

        VolumeChangeListener() {
        }
    }

    public void refreshRoutes() {
        if (this.mAttachedToWindow) {
            ArrayList arrayList = new ArrayList(this.mRouter.getRoutes());
            onFilterRoutes(arrayList);
            Collections.sort(arrayList, MediaRouteChooserDialog.RouteComparator.sInstance);
            if (SystemClock.uptimeMillis() - this.mLastUpdateTime >= UPDATE_ROUTES_DELAY_MS) {
                updateRoutes(arrayList);
                return;
            }
            this.mHandler.removeMessages(1);
            Handler handler = this.mHandler;
            handler.sendMessageAtTime(handler.obtainMessage(1, arrayList), this.mLastUpdateTime + UPDATE_ROUTES_DELAY_MS);
        }
    }

    /* access modifiers changed from: package-private */
    public void updateRoutes(List<MediaRouter.RouteInfo> list) {
        this.mLastUpdateTime = SystemClock.uptimeMillis();
        this.mRoutes.clear();
        this.mRoutes.addAll(list);
        this.mAdapter.setItems();
    }

    private final class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int ITEM_TYPE_GROUP = 4;
        private static final int ITEM_TYPE_GROUP_VOLUME = 1;
        private static final int ITEM_TYPE_HEADER = 2;
        private static final int ITEM_TYPE_ROUTE = 3;
        private static final String TAG = "RecyclerAdapter";
        private final ArrayList<MediaRouter.RouteInfo> mAvailableGroups = new ArrayList<>();
        private final ArrayList<MediaRouter.RouteInfo> mAvailableRoutes = new ArrayList<>();
        private final Drawable mDefaultIcon;
        private final LayoutInflater mInflater;
        private final ArrayList<Item> mItems = new ArrayList<>();
        private final Drawable mSpeakerGroupIcon;
        private final Drawable mSpeakerIcon;
        private final Drawable mTvIcon;

        RecyclerAdapter() {
            this.mInflater = LayoutInflater.from(MediaRouteCastDialog.this.mContext);
            this.mDefaultIcon = MediaRouterThemeHelper.getDefaultDrawableIcon(MediaRouteCastDialog.this.mContext);
            this.mTvIcon = MediaRouterThemeHelper.getTvDrawableIcon(MediaRouteCastDialog.this.mContext);
            this.mSpeakerIcon = MediaRouterThemeHelper.getSpeakerDrawableIcon(MediaRouteCastDialog.this.mContext);
            this.mSpeakerGroupIcon = MediaRouterThemeHelper.getSpeakerGropuIcon(MediaRouteCastDialog.this.mContext);
            setItems();
        }

        /* access modifiers changed from: package-private */
        public boolean isSelectedRoute(MediaRouter.RouteInfo routeInfo) {
            if (routeInfo.isSelected()) {
                return true;
            }
            if (!(MediaRouteCastDialog.this.mRoute instanceof MediaRouter.RouteGroup)) {
                return false;
            }
            for (MediaRouter.RouteInfo id : ((MediaRouter.RouteGroup) MediaRouteCastDialog.this.mRoute).getRoutes()) {
                if (id.getId().equals(routeInfo.getId())) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: package-private */
        public void setItems() {
            this.mItems.clear();
            if (MediaRouteCastDialog.this.mRoute instanceof MediaRouter.RouteGroup) {
                this.mItems.add(new Item(MediaRouteCastDialog.this.mRoute, 1));
                for (MediaRouter.RouteInfo item : ((MediaRouter.RouteGroup) MediaRouteCastDialog.this.mRoute).getRoutes()) {
                    this.mItems.add(new Item(item, 3));
                }
            } else {
                this.mItems.add(new Item(MediaRouteCastDialog.this.mRoute, 3));
            }
            this.mAvailableRoutes.clear();
            this.mAvailableGroups.clear();
            for (MediaRouter.RouteInfo next : MediaRouteCastDialog.this.mRoutes) {
                if (!isSelectedRoute(next)) {
                    if (next instanceof MediaRouter.RouteGroup) {
                        this.mAvailableGroups.add(next);
                    } else {
                        this.mAvailableRoutes.add(next);
                    }
                }
            }
            if (this.mAvailableRoutes.size() > 0) {
                this.mItems.add(new Item(MediaRouteCastDialog.this.mContext.getString(C4427R.string.mr_dialog_device_header), 2));
                Iterator<MediaRouter.RouteInfo> it = this.mAvailableRoutes.iterator();
                while (it.hasNext()) {
                    this.mItems.add(new Item(it.next(), 3));
                }
            }
            if (this.mAvailableGroups.size() > 0) {
                this.mItems.add(new Item(MediaRouteCastDialog.this.mContext.getString(C4427R.string.mr_dialog_route_header), 2));
                Iterator<MediaRouter.RouteInfo> it2 = this.mAvailableGroups.iterator();
                while (it2.hasNext()) {
                    this.mItems.add(new Item(it2.next(), 4));
                }
            }
            notifyDataSetChanged();
        }

        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (i == 1) {
                return new GroupVolumeViewHolder(this.mInflater.inflate(C4427R.layout.mr_cast_group_volume_item, viewGroup, false));
            }
            if (i == 2) {
                return new HeaderViewHolder(this.mInflater.inflate(C4427R.layout.mr_dialog_header_item, viewGroup, false));
            }
            if (i == 3) {
                return new RouteViewHolder(this.mInflater.inflate(C4427R.layout.mr_cast_route_item, viewGroup, false));
            }
            if (i == 4) {
                return new GroupViewHolder(this.mInflater.inflate(C4427R.layout.mr_cast_group_item, viewGroup, false));
            }
            Log.w(TAG, "Cannot create ViewHolder because of wrong view type");
            return null;
        }

        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
            int itemViewType = getItemViewType(i);
            Item item = getItem(i);
            if (itemViewType == 1) {
                ((GroupVolumeViewHolder) viewHolder).bindGroupVolumeView(item);
            } else if (itemViewType == 2) {
                ((HeaderViewHolder) viewHolder).bindHeaderViewHolder(item);
            } else if (itemViewType == 3) {
                ((RouteViewHolder) viewHolder).bindRouteViewHolder(item);
            } else if (itemViewType != 4) {
                Log.w(TAG, "Cannot bind item to ViewHolder because of wrong view type");
            } else {
                ((GroupViewHolder) viewHolder).bindGroupViewHolder(item);
            }
        }

        public int getItemCount() {
            return this.mItems.size();
        }

        /* access modifiers changed from: package-private */
        public Drawable getIconDrawable(MediaRouter.RouteInfo routeInfo) {
            Uri iconUri = routeInfo.getIconUri();
            if (iconUri != null) {
                try {
                    Drawable createFromStream = Drawable.createFromStream(MediaRouteCastDialog.this.mContext.getContentResolver().openInputStream(iconUri), (String) null);
                    if (createFromStream != null) {
                        return createFromStream;
                    }
                } catch (IOException e) {
                    Log.w(TAG, "Failed to load " + iconUri, e);
                }
            }
            return getDefaultIconDrawable(routeInfo);
        }

        private Drawable getDefaultIconDrawable(MediaRouter.RouteInfo routeInfo) {
            int deviceType = routeInfo.getDeviceType();
            if (deviceType == 1) {
                return this.mTvIcon;
            }
            if (deviceType == 2) {
                return this.mSpeakerIcon;
            }
            if (routeInfo instanceof MediaRouter.RouteGroup) {
                return this.mSpeakerGroupIcon;
            }
            return this.mDefaultIcon;
        }

        public int getItemViewType(int i) {
            return this.mItems.get(i).getType();
        }

        public Item getItem(int i) {
            return this.mItems.get(i);
        }

        private class Item {
            private final Object mData;
            private final int mType;

            Item(Object obj, int i) {
                this.mData = obj;
                this.mType = i;
            }

            public Object getData() {
                return this.mData;
            }

            public int getType() {
                return this.mType;
            }
        }

        private class GroupVolumeViewHolder extends RecyclerView.ViewHolder {
            MediaRouteVolumeSlider mGroupVolumeSlider;
            TextView mTextView;

            GroupVolumeViewHolder(View view) {
                super(view);
                this.mTextView = (TextView) view.findViewById(C4427R.C4430id.mr_group_volume_route_name);
                this.mGroupVolumeSlider = (MediaRouteVolumeSlider) view.findViewById(C4427R.C4430id.mr_group_volume_slider);
            }

            public void bindGroupVolumeView(Item item) {
                MediaRouter.RouteInfo routeInfo = (MediaRouter.RouteInfo) item.getData();
                this.mTextView.setText(routeInfo.getName().toUpperCase());
                this.mGroupVolumeSlider.setColor(MediaRouteCastDialog.this.mVolumeSliderColor);
                this.mGroupVolumeSlider.setTag(routeInfo);
                this.mGroupVolumeSlider.setProgress(MediaRouteCastDialog.this.mRoute.getVolume());
                this.mGroupVolumeSlider.setOnSeekBarChangeListener(MediaRouteCastDialog.this.mVolumeChangeListener);
            }
        }

        private class HeaderViewHolder extends RecyclerView.ViewHolder {
            TextView mTextView;

            HeaderViewHolder(View view) {
                super(view);
                this.mTextView = (TextView) view.findViewById(C4427R.C4430id.mr_dialog_header_name);
            }

            public void bindHeaderViewHolder(Item item) {
                this.mTextView.setText(item.getData().toString().toUpperCase());
            }
        }

        private class RouteViewHolder extends RecyclerView.ViewHolder {
            CheckBox mCheckBox;
            ImageView mImageView;
            TextView mTextView;
            MediaRouteVolumeSlider mVolumeSlider;

            RouteViewHolder(View view) {
                super(view);
                this.mImageView = (ImageView) view.findViewById(C4427R.C4430id.mr_cast_route_icon);
                this.mTextView = (TextView) view.findViewById(C4427R.C4430id.mr_cast_route_name);
                this.mCheckBox = (CheckBox) view.findViewById(C4427R.C4430id.mr_cast_checkbox);
                this.mVolumeSlider = (MediaRouteVolumeSlider) view.findViewById(C4427R.C4430id.mr_cast_volume_slider);
            }

            public void bindRouteViewHolder(Item item) {
                MediaRouter.RouteInfo routeInfo = (MediaRouter.RouteInfo) item.getData();
                this.mImageView.setImageDrawable(RecyclerAdapter.this.getIconDrawable(routeInfo));
                this.mTextView.setText(routeInfo.getName());
                this.mCheckBox.setChecked(RecyclerAdapter.this.isSelectedRoute(routeInfo));
                this.mVolumeSlider.setColor(MediaRouteCastDialog.this.mVolumeSliderColor);
                this.mVolumeSlider.setTag(routeInfo);
                this.mVolumeSlider.setProgress(routeInfo.getVolume());
                this.mVolumeSlider.setOnSeekBarChangeListener(MediaRouteCastDialog.this.mVolumeChangeListener);
            }
        }

        private class GroupViewHolder extends RecyclerView.ViewHolder {
            ImageView mImageView;
            TextView mTextView;

            GroupViewHolder(View view) {
                super(view);
                this.mImageView = (ImageView) view.findViewById(C4427R.C4430id.mr_cast_group_icon);
                this.mTextView = (TextView) view.findViewById(C4427R.C4430id.mr_cast_group_name);
            }

            public void bindGroupViewHolder(Item item) {
                MediaRouter.RouteInfo routeInfo = (MediaRouter.RouteInfo) item.getData();
                this.mImageView.setImageDrawable(RecyclerAdapter.this.getIconDrawable(routeInfo));
                this.mTextView.setText(routeInfo.getName());
            }
        }
    }

    private final class MediaRouterCallback extends MediaRouter.Callback {
        MediaRouterCallback() {
        }

        public void onRouteAdded(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
            MediaRouteCastDialog.this.refreshRoutes();
        }

        public void onRouteRemoved(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
            MediaRouteCastDialog.this.refreshRoutes();
        }

        public void onRouteSelected(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
            MediaRouteCastDialog.this.update();
        }

        public void onRouteUnselected(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
            MediaRouteCastDialog.this.update();
        }

        public void onRouteChanged(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
            MediaRouteCastDialog.this.refreshRoutes();
            MediaRouteCastDialog.this.update();
        }
    }

    private final class MediaControllerCallback extends MediaControllerCompat.Callback {
        MediaControllerCallback() {
        }

        public void onSessionDestroyed() {
            if (MediaRouteCastDialog.this.mMediaController != null) {
                MediaRouteCastDialog.this.mMediaController.unregisterCallback(MediaRouteCastDialog.this.mControllerCallback);
                MediaRouteCastDialog.this.mMediaController = null;
            }
        }

        public void onMetadataChanged(MediaMetadataCompat mediaMetadataCompat) {
            MediaRouteCastDialog.this.mDescription = mediaMetadataCompat == null ? null : mediaMetadataCompat.getDescription();
            MediaRouteCastDialog.this.updateArtIconIfNeeded();
            MediaRouteCastDialog.this.update();
        }
    }

    private class FetchArtTask extends AsyncTask<Void, Void, Bitmap> {
        private int mBackgroundColor;
        private final Bitmap mIconBitmap;
        private final Uri mIconUri;

        FetchArtTask() {
            Uri uri = null;
            Bitmap iconBitmap = MediaRouteCastDialog.this.mDescription == null ? null : MediaRouteCastDialog.this.mDescription.getIconBitmap();
            if (MediaRouteCastDialog.isBitmapRecycled(iconBitmap)) {
                Log.w(MediaRouteCastDialog.TAG, "Can't fetch the given art bitmap because it's already recycled.");
                iconBitmap = null;
            }
            this.mIconBitmap = iconBitmap;
            this.mIconUri = MediaRouteCastDialog.this.mDescription != null ? MediaRouteCastDialog.this.mDescription.getIconUri() : uri;
        }

        public Bitmap getIconBitmap() {
            return this.mIconBitmap;
        }

        public Uri getIconUri() {
            return this.mIconUri;
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            MediaRouteCastDialog.this.clearLoadedBitmap();
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0048 */
        /* JADX WARNING: Removed duplicated region for block: B:24:0x0053 A[Catch:{ IOException -> 0x00a3 }] */
        /* JADX WARNING: Removed duplicated region for block: B:57:0x00bf A[SYNTHETIC, Splitter:B:57:0x00bf] */
        /* JADX WARNING: Removed duplicated region for block: B:62:0x00c7 A[SYNTHETIC, Splitter:B:62:0x00c7] */
        /* JADX WARNING: Removed duplicated region for block: B:70:0x00d2  */
        /* JADX WARNING: Removed duplicated region for block: B:72:0x00e7 A[ADDED_TO_REGION] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public android.graphics.Bitmap doInBackground(java.lang.Void... r10) {
            /*
                r9 = this;
                java.lang.String r10 = "Unable to open: "
                android.graphics.Bitmap r0 = r9.mIconBitmap
                r1 = 0
                r2 = 1
                java.lang.String r3 = "MediaRouteCastDialog"
                r4 = 0
                if (r0 == 0) goto L_0x000d
                goto L_0x00cc
            L_0x000d:
                android.net.Uri r0 = r9.mIconUri
                if (r0 == 0) goto L_0x00cb
                java.io.InputStream r0 = r9.openInputStreamByScheme(r0)     // Catch:{ IOException -> 0x00a7, all -> 0x00a5 }
                if (r0 != 0) goto L_0x0031
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00a3 }
                r5.<init>()     // Catch:{ IOException -> 0x00a3 }
                r5.append(r10)     // Catch:{ IOException -> 0x00a3 }
                android.net.Uri r6 = r9.mIconUri     // Catch:{ IOException -> 0x00a3 }
                r5.append(r6)     // Catch:{ IOException -> 0x00a3 }
                java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x00a3 }
                android.util.Log.w(r3, r5)     // Catch:{ IOException -> 0x00a3 }
                if (r0 == 0) goto L_0x0030
                r0.close()     // Catch:{ IOException -> 0x0030 }
            L_0x0030:
                return r4
            L_0x0031:
                android.graphics.BitmapFactory$Options r5 = new android.graphics.BitmapFactory$Options     // Catch:{ IOException -> 0x00a3 }
                r5.<init>()     // Catch:{ IOException -> 0x00a3 }
                r5.inJustDecodeBounds = r2     // Catch:{ IOException -> 0x00a3 }
                android.graphics.BitmapFactory.decodeStream(r0, r4, r5)     // Catch:{ IOException -> 0x00a3 }
                int r6 = r5.outWidth     // Catch:{ IOException -> 0x00a3 }
                if (r6 == 0) goto L_0x009d
                int r6 = r5.outHeight     // Catch:{ IOException -> 0x00a3 }
                if (r6 != 0) goto L_0x0044
                goto L_0x009d
            L_0x0044:
                r0.reset()     // Catch:{ IOException -> 0x0048 }
                goto L_0x006d
            L_0x0048:
                r0.close()     // Catch:{ IOException -> 0x00a3 }
                android.net.Uri r6 = r9.mIconUri     // Catch:{ IOException -> 0x00a3 }
                java.io.InputStream r0 = r9.openInputStreamByScheme(r6)     // Catch:{ IOException -> 0x00a3 }
                if (r0 != 0) goto L_0x006d
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x00a3 }
                r5.<init>()     // Catch:{ IOException -> 0x00a3 }
                r5.append(r10)     // Catch:{ IOException -> 0x00a3 }
                android.net.Uri r6 = r9.mIconUri     // Catch:{ IOException -> 0x00a3 }
                r5.append(r6)     // Catch:{ IOException -> 0x00a3 }
                java.lang.String r5 = r5.toString()     // Catch:{ IOException -> 0x00a3 }
                android.util.Log.w(r3, r5)     // Catch:{ IOException -> 0x00a3 }
                if (r0 == 0) goto L_0x006c
                r0.close()     // Catch:{ IOException -> 0x006c }
            L_0x006c:
                return r4
            L_0x006d:
                r5.inJustDecodeBounds = r1     // Catch:{ IOException -> 0x00a3 }
                androidx.mediarouter.app.MediaRouteCastDialog r6 = androidx.mediarouter.app.MediaRouteCastDialog.this     // Catch:{ IOException -> 0x00a3 }
                int r7 = r5.outWidth     // Catch:{ IOException -> 0x00a3 }
                int r8 = r5.outHeight     // Catch:{ IOException -> 0x00a3 }
                int r6 = r6.getDesiredArtHeight(r7, r8)     // Catch:{ IOException -> 0x00a3 }
                int r7 = r5.outHeight     // Catch:{ IOException -> 0x00a3 }
                int r7 = r7 / r6
                int r6 = java.lang.Integer.highestOneBit(r7)     // Catch:{ IOException -> 0x00a3 }
                int r6 = java.lang.Math.max(r2, r6)     // Catch:{ IOException -> 0x00a3 }
                r5.inSampleSize = r6     // Catch:{ IOException -> 0x00a3 }
                boolean r6 = r9.isCancelled()     // Catch:{ IOException -> 0x00a3 }
                if (r6 == 0) goto L_0x0092
                if (r0 == 0) goto L_0x0091
                r0.close()     // Catch:{ IOException -> 0x0091 }
            L_0x0091:
                return r4
            L_0x0092:
                android.graphics.Bitmap r10 = android.graphics.BitmapFactory.decodeStream(r0, r4, r5)     // Catch:{ IOException -> 0x00a3 }
                if (r0 == 0) goto L_0x009b
                r0.close()     // Catch:{ IOException -> 0x009b }
            L_0x009b:
                r0 = r10
                goto L_0x00cc
            L_0x009d:
                if (r0 == 0) goto L_0x00a2
                r0.close()     // Catch:{ IOException -> 0x00a2 }
            L_0x00a2:
                return r4
            L_0x00a3:
                r5 = move-exception
                goto L_0x00a9
            L_0x00a5:
                r10 = move-exception
                goto L_0x00c5
            L_0x00a7:
                r5 = move-exception
                r0 = r4
            L_0x00a9:
                java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c3 }
                r6.<init>()     // Catch:{ all -> 0x00c3 }
                r6.append(r10)     // Catch:{ all -> 0x00c3 }
                android.net.Uri r10 = r9.mIconUri     // Catch:{ all -> 0x00c3 }
                r6.append(r10)     // Catch:{ all -> 0x00c3 }
                java.lang.String r10 = r6.toString()     // Catch:{ all -> 0x00c3 }
                android.util.Log.w(r3, r10, r5)     // Catch:{ all -> 0x00c3 }
                if (r0 == 0) goto L_0x00cb
                r0.close()     // Catch:{ IOException -> 0x00cb }
                goto L_0x00cb
            L_0x00c3:
                r10 = move-exception
                r4 = r0
            L_0x00c5:
                if (r4 == 0) goto L_0x00ca
                r4.close()     // Catch:{ IOException -> 0x00ca }
            L_0x00ca:
                throw r10
            L_0x00cb:
                r0 = r4
            L_0x00cc:
                boolean r10 = androidx.mediarouter.app.MediaRouteCastDialog.isBitmapRecycled(r0)
                if (r10 == 0) goto L_0x00e7
                java.lang.StringBuilder r10 = new java.lang.StringBuilder
                r10.<init>()
                java.lang.String r1 = "Can't use recycled bitmap: "
                r10.append(r1)
                r10.append(r0)
                java.lang.String r10 = r10.toString()
                android.util.Log.w(r3, r10)
                return r4
            L_0x00e7:
                if (r0 == 0) goto L_0x011b
                int r10 = r0.getWidth()
                int r3 = r0.getHeight()
                if (r10 >= r3) goto L_0x011b
                androidx.palette.graphics.Palette$Builder r10 = new androidx.palette.graphics.Palette$Builder
                r10.<init>((android.graphics.Bitmap) r0)
                androidx.palette.graphics.Palette$Builder r10 = r10.maximumColorCount(r2)
                androidx.palette.graphics.Palette r10 = r10.generate()
                java.util.List r2 = r10.getSwatches()
                boolean r2 = r2.isEmpty()
                if (r2 == 0) goto L_0x010b
                goto L_0x0119
            L_0x010b:
                java.util.List r10 = r10.getSwatches()
                java.lang.Object r10 = r10.get(r1)
                androidx.palette.graphics.Palette$Swatch r10 = (androidx.palette.graphics.Palette.Swatch) r10
                int r1 = r10.getRgb()
            L_0x0119:
                r9.mBackgroundColor = r1
            L_0x011b:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.app.MediaRouteCastDialog.FetchArtTask.doInBackground(java.lang.Void[]):android.graphics.Bitmap");
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Bitmap bitmap) {
            MediaRouteCastDialog.this.mFetchArtTask = null;
            if (!ObjectsCompat.equals(MediaRouteCastDialog.this.mArtIconBitmap, this.mIconBitmap) || !ObjectsCompat.equals(MediaRouteCastDialog.this.mArtIconUri, this.mIconUri)) {
                MediaRouteCastDialog.this.mArtIconBitmap = this.mIconBitmap;
                MediaRouteCastDialog.this.mArtIconLoadedBitmap = bitmap;
                MediaRouteCastDialog.this.mArtIconUri = this.mIconUri;
                MediaRouteCastDialog.this.mArtIconBackgroundColor = this.mBackgroundColor;
                MediaRouteCastDialog.this.mArtIconIsLoaded = true;
                MediaRouteCastDialog.this.update();
            }
        }

        private InputStream openInputStreamByScheme(Uri uri) throws IOException {
            InputStream inputStream;
            String lowerCase = uri.getScheme().toLowerCase();
            if ("android.resource".equals(lowerCase) || Constants.VAST_TRACKER_CONTENT.equals(lowerCase) || "file".equals(lowerCase)) {
                inputStream = MediaRouteCastDialog.this.mContext.getContentResolver().openInputStream(uri);
            } else {
                URLConnection openConnection = new URL(uri.toString()).openConnection();
                openConnection.setConnectTimeout(MediaRouteCastDialog.CONNECTION_TIMEOUT_MILLIS);
                openConnection.setReadTimeout(MediaRouteCastDialog.CONNECTION_TIMEOUT_MILLIS);
                inputStream = openConnection.getInputStream();
            }
            if (inputStream == null) {
                return null;
            }
            return new BufferedInputStream(inputStream);
        }
    }
}

package androidx.mediarouter.media;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.p064v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import androidx.core.app.ActivityManagerCompat;
import androidx.core.hardware.display.DisplayManagerCompat;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Pair;
import androidx.media.VolumeProviderCompat;
import androidx.media2.MediaPlayerConnector;
import androidx.media2.MediaPlaylistAgent;
import androidx.media2.MediaSession2;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.RegisteredMediaRouteProviderWatcher;
import androidx.mediarouter.media.RemoteControlClientCompat;
import androidx.mediarouter.media.SystemMediaRouteProvider;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class MediaRouter {
    public static final int AVAILABILITY_FLAG_IGNORE_DEFAULT_ROUTE = 1;
    public static final int AVAILABILITY_FLAG_REQUIRE_MATCH = 2;
    public static final int CALLBACK_FLAG_FORCE_DISCOVERY = 8;
    public static final int CALLBACK_FLAG_PERFORM_ACTIVE_SCAN = 1;
    public static final int CALLBACK_FLAG_REQUEST_DISCOVERY = 4;
    public static final int CALLBACK_FLAG_UNFILTERED_EVENTS = 2;
    static final boolean DEBUG = Log.isLoggable(TAG, 3);
    static final String TAG = "MediaRouter";
    public static final int UNSELECT_REASON_DISCONNECTED = 1;
    public static final int UNSELECT_REASON_ROUTE_CHANGED = 3;
    public static final int UNSELECT_REASON_STOPPED = 2;
    public static final int UNSELECT_REASON_UNKNOWN = 0;
    static GlobalMediaRouter sGlobal;
    final ArrayList<CallbackRecord> mCallbackRecords = new ArrayList<>();
    final Context mContext;

    public static abstract class ControlRequestCallback {
        public void onError(String str, Bundle bundle) {
        }

        public void onResult(Bundle bundle) {
        }
    }

    MediaRouter(Context context) {
        this.mContext = context;
    }

    public static MediaRouter getInstance(Context context) {
        if (context != null) {
            checkCallingThread();
            if (sGlobal == null) {
                GlobalMediaRouter globalMediaRouter = new GlobalMediaRouter(context.getApplicationContext());
                sGlobal = globalMediaRouter;
                globalMediaRouter.start();
            }
            return sGlobal.getRouter(context);
        }
        throw new IllegalArgumentException("context must not be null");
    }

    public List<RouteInfo> getRoutes() {
        checkCallingThread();
        return sGlobal.getRoutes();
    }

    public RouteInfo getRoute(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        bundle.setClassLoader(this.mContext.getClassLoader());
        try {
            return sGlobal.getRoute(bundle.getString("id"));
        } catch (Exception unused) {
            return null;
        }
    }

    public List<ProviderInfo> getProviders() {
        checkCallingThread();
        return sGlobal.getProviders();
    }

    public RouteInfo getDefaultRoute() {
        checkCallingThread();
        return sGlobal.getDefaultRoute();
    }

    public RouteInfo getBluetoothRoute() {
        checkCallingThread();
        return sGlobal.getBluetoothRoute();
    }

    public RouteInfo getSelectedRoute() {
        checkCallingThread();
        return sGlobal.getSelectedRoute();
    }

    public RouteInfo updateSelectedRoute(MediaRouteSelector mediaRouteSelector) {
        if (mediaRouteSelector != null) {
            checkCallingThread();
            if (DEBUG) {
                Log.d(TAG, "updateSelectedRoute: " + mediaRouteSelector);
            }
            RouteInfo selectedRoute = sGlobal.getSelectedRoute();
            if (selectedRoute.isDefaultOrBluetooth() || selectedRoute.matchesSelector(mediaRouteSelector)) {
                return selectedRoute;
            }
            RouteInfo chooseFallbackRoute = sGlobal.chooseFallbackRoute();
            sGlobal.selectRoute(chooseFallbackRoute);
            return chooseFallbackRoute;
        }
        throw new IllegalArgumentException("selector must not be null");
    }

    public void selectRoute(RouteInfo routeInfo) {
        if (routeInfo != null) {
            checkCallingThread();
            if (DEBUG) {
                Log.d(TAG, "selectRoute: " + routeInfo);
            }
            sGlobal.selectRoute(routeInfo);
            return;
        }
        throw new IllegalArgumentException("route must not be null");
    }

    public void unselect(int i) {
        if (i < 0 || i > 3) {
            throw new IllegalArgumentException("Unsupported reason to unselect route");
        }
        checkCallingThread();
        RouteInfo chooseFallbackRoute = sGlobal.chooseFallbackRoute();
        if (sGlobal.getSelectedRoute() != chooseFallbackRoute) {
            sGlobal.selectRoute(chooseFallbackRoute, i);
            return;
        }
        GlobalMediaRouter globalMediaRouter = sGlobal;
        globalMediaRouter.selectRoute(globalMediaRouter.getDefaultRoute(), i);
    }

    public boolean isRouteAvailable(MediaRouteSelector mediaRouteSelector, int i) {
        if (mediaRouteSelector != null) {
            checkCallingThread();
            return sGlobal.isRouteAvailable(mediaRouteSelector, i);
        }
        throw new IllegalArgumentException("selector must not be null");
    }

    public void addCallback(MediaRouteSelector mediaRouteSelector, Callback callback) {
        addCallback(mediaRouteSelector, callback, 0);
    }

    public void addCallback(MediaRouteSelector mediaRouteSelector, Callback callback, int i) {
        CallbackRecord callbackRecord;
        if (mediaRouteSelector == null) {
            throw new IllegalArgumentException("selector must not be null");
        } else if (callback != null) {
            checkCallingThread();
            if (DEBUG) {
                Log.d(TAG, "addCallback: selector=" + mediaRouteSelector + ", callback=" + callback + ", flags=" + Integer.toHexString(i));
            }
            int findCallbackRecord = findCallbackRecord(callback);
            if (findCallbackRecord < 0) {
                callbackRecord = new CallbackRecord(this, callback);
                this.mCallbackRecords.add(callbackRecord);
            } else {
                callbackRecord = this.mCallbackRecords.get(findCallbackRecord);
            }
            boolean z = false;
            boolean z2 = true;
            if (((callbackRecord.mFlags ^ -1) & i) != 0) {
                callbackRecord.mFlags |= i;
                z = true;
            }
            if (!callbackRecord.mSelector.contains(mediaRouteSelector)) {
                callbackRecord.mSelector = new MediaRouteSelector.Builder(callbackRecord.mSelector).addSelector(mediaRouteSelector).build();
            } else {
                z2 = z;
            }
            if (z2) {
                sGlobal.updateDiscoveryRequest();
            }
        } else {
            throw new IllegalArgumentException("callback must not be null");
        }
    }

    public void removeCallback(Callback callback) {
        if (callback != null) {
            checkCallingThread();
            if (DEBUG) {
                Log.d(TAG, "removeCallback: callback=" + callback);
            }
            int findCallbackRecord = findCallbackRecord(callback);
            if (findCallbackRecord >= 0) {
                this.mCallbackRecords.remove(findCallbackRecord);
                sGlobal.updateDiscoveryRequest();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("callback must not be null");
    }

    private int findCallbackRecord(Callback callback) {
        int size = this.mCallbackRecords.size();
        for (int i = 0; i < size; i++) {
            if (this.mCallbackRecords.get(i).mCallback == callback) {
                return i;
            }
        }
        return -1;
    }

    public void addProvider(MediaRouteProvider mediaRouteProvider) {
        if (mediaRouteProvider != null) {
            checkCallingThread();
            if (DEBUG) {
                Log.d(TAG, "addProvider: " + mediaRouteProvider);
            }
            sGlobal.addProvider(mediaRouteProvider);
            return;
        }
        throw new IllegalArgumentException("providerInstance must not be null");
    }

    public void removeProvider(MediaRouteProvider mediaRouteProvider) {
        if (mediaRouteProvider != null) {
            checkCallingThread();
            if (DEBUG) {
                Log.d(TAG, "removeProvider: " + mediaRouteProvider);
            }
            sGlobal.removeProvider(mediaRouteProvider);
            return;
        }
        throw new IllegalArgumentException("providerInstance must not be null");
    }

    public void addRemoteControlClient(Object obj) {
        if (obj != null) {
            checkCallingThread();
            if (DEBUG) {
                Log.d(TAG, "addRemoteControlClient: " + obj);
            }
            sGlobal.addRemoteControlClient(obj);
            return;
        }
        throw new IllegalArgumentException("remoteControlClient must not be null");
    }

    public void removeRemoteControlClient(Object obj) {
        if (obj != null) {
            if (DEBUG) {
                Log.d(TAG, "removeRemoteControlClient: " + obj);
            }
            sGlobal.removeRemoteControlClient(obj);
            return;
        }
        throw new IllegalArgumentException("remoteControlClient must not be null");
    }

    public void setMediaSession(Object obj) {
        if (DEBUG) {
            Log.d(TAG, "addMediaSession: " + obj);
        }
        sGlobal.setMediaSession(obj);
    }

    public void setMediaSessionCompat(MediaSessionCompat mediaSessionCompat) {
        if (DEBUG) {
            Log.d(TAG, "addMediaSessionCompat: " + mediaSessionCompat);
        }
        sGlobal.setMediaSessionCompat(mediaSessionCompat);
    }

    public void setMediaSession2(MediaSession2 mediaSession2, RouteMediaPlayerConnector routeMediaPlayerConnector, MediaPlaylistAgent mediaPlaylistAgent) {
        sGlobal.setMediaSession2(mediaSession2, routeMediaPlayerConnector, mediaPlaylistAgent);
    }

    public MediaSessionCompat.Token getMediaSessionToken() {
        return sGlobal.getMediaSessionToken();
    }

    static void checkCallingThread() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalStateException("The media router service must only be accessed on the application's main thread.");
        }
    }

    public static class RouteInfo {
        static final int CHANGE_GENERAL = 1;
        static final int CHANGE_PRESENTATION_DISPLAY = 4;
        static final int CHANGE_VOLUME = 2;
        public static final int CONNECTION_STATE_CONNECTED = 2;
        public static final int CONNECTION_STATE_CONNECTING = 1;
        public static final int CONNECTION_STATE_DISCONNECTED = 0;
        public static final int DEVICE_TYPE_BLUETOOTH = 3;
        public static final int DEVICE_TYPE_SPEAKER = 2;
        public static final int DEVICE_TYPE_TV = 1;
        public static final int DEVICE_TYPE_UNKNOWN = 0;
        public static final int PLAYBACK_TYPE_LOCAL = 0;
        public static final int PLAYBACK_TYPE_REMOTE = 1;
        public static final int PLAYBACK_VOLUME_FIXED = 0;
        public static final int PLAYBACK_VOLUME_VARIABLE = 1;
        public static final int PRESENTATION_DISPLAY_ID_NONE = -1;
        static final String SYSTEM_MEDIA_ROUTE_PROVIDER_PACKAGE_NAME = "android";
        private boolean mCanDisconnect;
        private boolean mConnecting;
        private int mConnectionState;
        private final ArrayList<IntentFilter> mControlFilters = new ArrayList<>();
        private String mDescription;
        MediaRouteDescriptor mDescriptor;
        final String mDescriptorId;
        private int mDeviceType;
        boolean mEnabled;
        private Bundle mExtras;
        private Uri mIconUri;
        private String mName;
        private int mPlaybackStream;
        private int mPlaybackType;
        private Display mPresentationDisplay;
        private int mPresentationDisplayId = -1;
        private final ProviderInfo mProvider;
        private IntentSender mSettingsIntent;
        final String mUniqueId;
        private int mVolume;
        private int mVolumeHandling;
        private int mVolumeMax;

        RouteInfo(ProviderInfo providerInfo, String str, String str2) {
            this.mProvider = providerInfo;
            this.mDescriptorId = str;
            this.mUniqueId = str2;
        }

        public ProviderInfo getProvider() {
            return this.mProvider;
        }

        public String getId() {
            return this.mUniqueId;
        }

        public String getName() {
            return this.mName;
        }

        public String getDescription() {
            return this.mDescription;
        }

        public Uri getIconUri() {
            return this.mIconUri;
        }

        public boolean isEnabled() {
            return this.mEnabled;
        }

        public boolean isConnecting() {
            return this.mConnecting;
        }

        public int getConnectionState() {
            return this.mConnectionState;
        }

        public boolean isSelected() {
            MediaRouter.checkCallingThread();
            return MediaRouter.sGlobal.getSelectedRoute() == this;
        }

        public boolean isDefault() {
            MediaRouter.checkCallingThread();
            return MediaRouter.sGlobal.getDefaultRoute() == this;
        }

        public boolean isBluetooth() {
            MediaRouter.checkCallingThread();
            return MediaRouter.sGlobal.getBluetoothRoute() == this;
        }

        public boolean isDeviceSpeaker() {
            return isDefault() && Resources.getSystem().getText(Resources.getSystem().getIdentifier("default_audio_route_name", "string", "android")).equals(this.mName);
        }

        public List<IntentFilter> getControlFilters() {
            return this.mControlFilters;
        }

        public boolean matchesSelector(MediaRouteSelector mediaRouteSelector) {
            if (mediaRouteSelector != null) {
                MediaRouter.checkCallingThread();
                return mediaRouteSelector.matchesControlFilters(this.mControlFilters);
            }
            throw new IllegalArgumentException("selector must not be null");
        }

        public boolean supportsControlCategory(String str) {
            if (str != null) {
                MediaRouter.checkCallingThread();
                int size = this.mControlFilters.size();
                for (int i = 0; i < size; i++) {
                    if (this.mControlFilters.get(i).hasCategory(str)) {
                        return true;
                    }
                }
                return false;
            }
            throw new IllegalArgumentException("category must not be null");
        }

        public boolean supportsControlAction(String str, String str2) {
            if (str == null) {
                throw new IllegalArgumentException("category must not be null");
            } else if (str2 != null) {
                MediaRouter.checkCallingThread();
                int size = this.mControlFilters.size();
                for (int i = 0; i < size; i++) {
                    IntentFilter intentFilter = this.mControlFilters.get(i);
                    if (intentFilter.hasCategory(str) && intentFilter.hasAction(str2)) {
                        return true;
                    }
                }
                return false;
            } else {
                throw new IllegalArgumentException("action must not be null");
            }
        }

        public boolean supportsControlRequest(Intent intent) {
            if (intent != null) {
                MediaRouter.checkCallingThread();
                ContentResolver contentResolver = MediaRouter.sGlobal.getContentResolver();
                int size = this.mControlFilters.size();
                for (int i = 0; i < size; i++) {
                    if (this.mControlFilters.get(i).match(contentResolver, intent, true, MediaRouter.TAG) >= 0) {
                        return true;
                    }
                }
                return false;
            }
            throw new IllegalArgumentException("intent must not be null");
        }

        public void sendControlRequest(Intent intent, ControlRequestCallback controlRequestCallback) {
            if (intent != null) {
                MediaRouter.checkCallingThread();
                MediaRouter.sGlobal.sendControlRequest(this, intent, controlRequestCallback);
                return;
            }
            throw new IllegalArgumentException("intent must not be null");
        }

        public int getPlaybackType() {
            return this.mPlaybackType;
        }

        public int getPlaybackStream() {
            return this.mPlaybackStream;
        }

        public int getDeviceType() {
            return this.mDeviceType;
        }

        public Bundle getUniqueRouteDescriptorBundle() {
            Bundle asBundle = this.mDescriptor.asBundle();
            asBundle.putString("id", this.mUniqueId);
            return asBundle;
        }

        public boolean isDefaultOrBluetooth() {
            if (isDefault() || this.mDeviceType == 3) {
                return true;
            }
            if (!isSystemMediaRouteProvider(this) || !supportsControlCategory(MediaControlIntent.CATEGORY_LIVE_AUDIO) || supportsControlCategory(MediaControlIntent.CATEGORY_LIVE_VIDEO)) {
                return false;
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public boolean isSelectable() {
            return this.mDescriptor != null && this.mEnabled;
        }

        private static boolean isSystemMediaRouteProvider(RouteInfo routeInfo) {
            return TextUtils.equals(routeInfo.getProviderInstance().getMetadata().getPackageName(), "android");
        }

        public int getVolumeHandling() {
            return this.mVolumeHandling;
        }

        public int getVolume() {
            return this.mVolume;
        }

        public int getVolumeMax() {
            return this.mVolumeMax;
        }

        public boolean canDisconnect() {
            return this.mCanDisconnect;
        }

        public void requestSetVolume(int i) {
            MediaRouter.checkCallingThread();
            MediaRouter.sGlobal.requestSetVolume(this, Math.min(this.mVolumeMax, Math.max(0, i)));
        }

        public void requestUpdateVolume(int i) {
            MediaRouter.checkCallingThread();
            if (i != 0) {
                MediaRouter.sGlobal.requestUpdateVolume(this, i);
            }
        }

        public Display getPresentationDisplay() {
            MediaRouter.checkCallingThread();
            if (this.mPresentationDisplayId >= 0 && this.mPresentationDisplay == null) {
                this.mPresentationDisplay = MediaRouter.sGlobal.getDisplay(this.mPresentationDisplayId);
            }
            return this.mPresentationDisplay;
        }

        public int getPresentationDisplayId() {
            return this.mPresentationDisplayId;
        }

        public Bundle getExtras() {
            return this.mExtras;
        }

        public IntentSender getSettingsIntent() {
            return this.mSettingsIntent;
        }

        public void select() {
            MediaRouter.checkCallingThread();
            MediaRouter.sGlobal.selectRoute(this);
        }

        public String toString() {
            return "MediaRouter.RouteInfo{ uniqueId=" + this.mUniqueId + ", name=" + this.mName + ", description=" + this.mDescription + ", iconUri=" + this.mIconUri + ", enabled=" + this.mEnabled + ", connecting=" + this.mConnecting + ", connectionState=" + this.mConnectionState + ", canDisconnect=" + this.mCanDisconnect + ", playbackType=" + this.mPlaybackType + ", playbackStream=" + this.mPlaybackStream + ", deviceType=" + this.mDeviceType + ", volumeHandling=" + this.mVolumeHandling + ", volume=" + this.mVolume + ", volumeMax=" + this.mVolumeMax + ", presentationDisplayId=" + this.mPresentationDisplayId + ", extras=" + this.mExtras + ", settingsIntent=" + this.mSettingsIntent + ", providerPackageName=" + this.mProvider.getPackageName() + " }";
        }

        /* access modifiers changed from: package-private */
        public int maybeUpdateDescriptor(MediaRouteDescriptor mediaRouteDescriptor) {
            if (this.mDescriptor != mediaRouteDescriptor) {
                return updateDescriptor(mediaRouteDescriptor);
            }
            return 0;
        }

        /* access modifiers changed from: package-private */
        public int updateDescriptor(MediaRouteDescriptor mediaRouteDescriptor) {
            this.mDescriptor = mediaRouteDescriptor;
            int i = 0;
            if (mediaRouteDescriptor == null) {
                return 0;
            }
            if (!ObjectsCompat.equals(this.mName, mediaRouteDescriptor.getName())) {
                this.mName = mediaRouteDescriptor.getName();
                i = 1;
            }
            if (!ObjectsCompat.equals(this.mDescription, mediaRouteDescriptor.getDescription())) {
                this.mDescription = mediaRouteDescriptor.getDescription();
                i |= 1;
            }
            if (!ObjectsCompat.equals(this.mIconUri, mediaRouteDescriptor.getIconUri())) {
                this.mIconUri = mediaRouteDescriptor.getIconUri();
                i |= 1;
            }
            if (this.mEnabled != mediaRouteDescriptor.isEnabled()) {
                this.mEnabled = mediaRouteDescriptor.isEnabled();
                i |= 1;
            }
            if (this.mConnecting != mediaRouteDescriptor.isConnecting()) {
                this.mConnecting = mediaRouteDescriptor.isConnecting();
                i |= 1;
            }
            if (this.mConnectionState != mediaRouteDescriptor.getConnectionState()) {
                this.mConnectionState = mediaRouteDescriptor.getConnectionState();
                i |= 1;
            }
            if (!this.mControlFilters.equals(mediaRouteDescriptor.getControlFilters())) {
                this.mControlFilters.clear();
                this.mControlFilters.addAll(mediaRouteDescriptor.getControlFilters());
                i |= 1;
            }
            if (this.mPlaybackType != mediaRouteDescriptor.getPlaybackType()) {
                this.mPlaybackType = mediaRouteDescriptor.getPlaybackType();
                i |= 1;
            }
            if (this.mPlaybackStream != mediaRouteDescriptor.getPlaybackStream()) {
                this.mPlaybackStream = mediaRouteDescriptor.getPlaybackStream();
                i |= 1;
            }
            if (this.mDeviceType != mediaRouteDescriptor.getDeviceType()) {
                this.mDeviceType = mediaRouteDescriptor.getDeviceType();
                i |= 1;
            }
            if (this.mVolumeHandling != mediaRouteDescriptor.getVolumeHandling()) {
                this.mVolumeHandling = mediaRouteDescriptor.getVolumeHandling();
                i |= 3;
            }
            if (this.mVolume != mediaRouteDescriptor.getVolume()) {
                this.mVolume = mediaRouteDescriptor.getVolume();
                i |= 3;
            }
            if (this.mVolumeMax != mediaRouteDescriptor.getVolumeMax()) {
                this.mVolumeMax = mediaRouteDescriptor.getVolumeMax();
                i |= 3;
            }
            if (this.mPresentationDisplayId != mediaRouteDescriptor.getPresentationDisplayId()) {
                this.mPresentationDisplayId = mediaRouteDescriptor.getPresentationDisplayId();
                this.mPresentationDisplay = null;
                i |= 5;
            }
            if (!ObjectsCompat.equals(this.mExtras, mediaRouteDescriptor.getExtras())) {
                this.mExtras = mediaRouteDescriptor.getExtras();
                i |= 1;
            }
            if (!ObjectsCompat.equals(this.mSettingsIntent, mediaRouteDescriptor.getSettingsActivity())) {
                this.mSettingsIntent = mediaRouteDescriptor.getSettingsActivity();
                i |= 1;
            }
            if (this.mCanDisconnect == mediaRouteDescriptor.canDisconnectAndKeepPlaying()) {
                return i;
            }
            this.mCanDisconnect = mediaRouteDescriptor.canDisconnectAndKeepPlaying();
            return i | 5;
        }

        /* access modifiers changed from: package-private */
        public String getDescriptorId() {
            return this.mDescriptorId;
        }

        public MediaRouteProvider getProviderInstance() {
            return this.mProvider.getProviderInstance();
        }
    }

    public static class RouteGroup extends RouteInfo {
        private List<RouteInfo> mRoutes = new ArrayList();

        RouteGroup(ProviderInfo providerInfo, String str, String str2) {
            super(providerInfo, str, str2);
        }

        public int getRouteCount() {
            return this.mRoutes.size();
        }

        public RouteInfo getRouteAt(int i) {
            return this.mRoutes.get(i);
        }

        public List<RouteInfo> getRoutes() {
            return this.mRoutes;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder(super.toString());
            sb.append('[');
            int size = this.mRoutes.size();
            for (int i = 0; i < size; i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(this.mRoutes.get(i));
            }
            sb.append(']');
            return sb.toString();
        }

        /* access modifiers changed from: package-private */
        public int maybeUpdateDescriptor(MediaRouteDescriptor mediaRouteDescriptor) {
            int i = 0;
            if (this.mDescriptor != mediaRouteDescriptor) {
                this.mDescriptor = mediaRouteDescriptor;
                if (mediaRouteDescriptor != null) {
                    List<String> groupMemberIds = mediaRouteDescriptor.getGroupMemberIds();
                    ArrayList arrayList = new ArrayList();
                    if (groupMemberIds == null) {
                        Log.w(MediaRouter.TAG, "groupMemberIds shouldn't be null.");
                        i = 1;
                    } else {
                        if (groupMemberIds.size() != this.mRoutes.size()) {
                            i = 1;
                        }
                        for (String uniqueId : groupMemberIds) {
                            RouteInfo route = MediaRouter.sGlobal.getRoute(MediaRouter.sGlobal.getUniqueId(getProvider(), uniqueId));
                            if (route != null) {
                                arrayList.add(route);
                                if (i == 0 && !this.mRoutes.contains(route)) {
                                    i = 1;
                                }
                            }
                        }
                    }
                    if (i != 0) {
                        this.mRoutes = arrayList;
                    }
                }
            }
            return super.updateDescriptor(mediaRouteDescriptor) | i;
        }
    }

    public static final class ProviderInfo {
        private MediaRouteProviderDescriptor mDescriptor;
        private final MediaRouteProvider.ProviderMetadata mMetadata;
        final MediaRouteProvider mProviderInstance;
        private Resources mResources;
        private boolean mResourcesNotAvailable;
        final List<RouteInfo> mRoutes = new ArrayList();

        ProviderInfo(MediaRouteProvider mediaRouteProvider) {
            this.mProviderInstance = mediaRouteProvider;
            this.mMetadata = mediaRouteProvider.getMetadata();
        }

        public MediaRouteProvider getProviderInstance() {
            MediaRouter.checkCallingThread();
            return this.mProviderInstance;
        }

        public String getPackageName() {
            return this.mMetadata.getPackageName();
        }

        public ComponentName getComponentName() {
            return this.mMetadata.getComponentName();
        }

        public List<RouteInfo> getRoutes() {
            MediaRouter.checkCallingThread();
            return this.mRoutes;
        }

        /* access modifiers changed from: package-private */
        public Resources getResources() {
            if (this.mResources == null && !this.mResourcesNotAvailable) {
                String packageName = getPackageName();
                Context providerContext = MediaRouter.sGlobal.getProviderContext(packageName);
                if (providerContext != null) {
                    this.mResources = providerContext.getResources();
                } else {
                    Log.w(MediaRouter.TAG, "Unable to obtain resources for route provider package: " + packageName);
                    this.mResourcesNotAvailable = true;
                }
            }
            return this.mResources;
        }

        /* access modifiers changed from: package-private */
        public boolean updateDescriptor(MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
            if (this.mDescriptor == mediaRouteProviderDescriptor) {
                return false;
            }
            this.mDescriptor = mediaRouteProviderDescriptor;
            return true;
        }

        /* access modifiers changed from: package-private */
        public int findRouteByDescriptorId(String str) {
            int size = this.mRoutes.size();
            for (int i = 0; i < size; i++) {
                if (this.mRoutes.get(i).mDescriptorId.equals(str)) {
                    return i;
                }
            }
            return -1;
        }

        public String toString() {
            return "MediaRouter.RouteProviderInfo{ packageName=" + getPackageName() + " }";
        }
    }

    public static abstract class Callback {
        public void onProviderAdded(MediaRouter mediaRouter, ProviderInfo providerInfo) {
        }

        public void onProviderChanged(MediaRouter mediaRouter, ProviderInfo providerInfo) {
        }

        public void onProviderRemoved(MediaRouter mediaRouter, ProviderInfo providerInfo) {
        }

        public void onRouteAdded(MediaRouter mediaRouter, RouteInfo routeInfo) {
        }

        public void onRouteChanged(MediaRouter mediaRouter, RouteInfo routeInfo) {
        }

        public void onRoutePresentationDisplayChanged(MediaRouter mediaRouter, RouteInfo routeInfo) {
        }

        public void onRouteRemoved(MediaRouter mediaRouter, RouteInfo routeInfo) {
        }

        public void onRouteSelected(MediaRouter mediaRouter, RouteInfo routeInfo) {
        }

        public void onRouteUnselected(MediaRouter mediaRouter, RouteInfo routeInfo) {
        }

        public void onRouteVolumeChanged(MediaRouter mediaRouter, RouteInfo routeInfo) {
        }

        public void onRouteUnselected(MediaRouter mediaRouter, RouteInfo routeInfo, int i) {
            onRouteUnselected(mediaRouter, routeInfo);
        }
    }

    private static final class CallbackRecord {
        public final Callback mCallback;
        public int mFlags;
        public final MediaRouter mRouter;
        public MediaRouteSelector mSelector = MediaRouteSelector.EMPTY;

        public CallbackRecord(MediaRouter mediaRouter, Callback callback) {
            this.mRouter = mediaRouter;
            this.mCallback = callback;
        }

        public boolean filterRouteEvent(RouteInfo routeInfo) {
            return (this.mFlags & 2) != 0 || routeInfo.matchesSelector(this.mSelector);
        }
    }

    private static final class GlobalMediaRouter implements SystemMediaRouteProvider.SyncCallback, RegisteredMediaRouteProviderWatcher.Callback {
        final Context mApplicationContext;
        private RouteInfo mBluetoothRoute;
        final CallbackHandler mCallbackHandler = new CallbackHandler();
        private MediaSessionCompat mCompatSession;
        private RouteInfo mDefaultRoute;
        private MediaRouteDiscoveryRequest mDiscoveryRequest;
        private final DisplayManagerCompat mDisplayManager;
        private final boolean mLowRam;
        private MediaSessionRecord mMediaSession;
        final RemoteControlClientCompat.PlaybackInfo mPlaybackInfo = new RemoteControlClientCompat.PlaybackInfo();
        private final ProviderCallback mProviderCallback = new ProviderCallback();
        private final ArrayList<ProviderInfo> mProviders = new ArrayList<>();
        MediaSessionCompat mRccMediaSession;
        private RegisteredMediaRouteProviderWatcher mRegisteredProviderWatcher;
        private final ArrayList<RemoteControlClientRecord> mRemoteControlClients = new ArrayList<>();
        private final Map<String, MediaRouteProvider.RouteController> mRouteControllerMap = new HashMap();
        final ArrayList<WeakReference<MediaRouter>> mRouters = new ArrayList<>();
        private final ArrayList<RouteInfo> mRoutes = new ArrayList<>();
        RouteInfo mSelectedRoute;
        private MediaRouteProvider.RouteController mSelectedRouteController;
        private MediaSessionCompat.OnActiveChangeListener mSessionActiveListener = new MediaSessionCompat.OnActiveChangeListener() {
            public void onActiveChanged() {
                if (GlobalMediaRouter.this.mRccMediaSession == null) {
                    return;
                }
                if (GlobalMediaRouter.this.mRccMediaSession.isActive()) {
                    GlobalMediaRouter globalMediaRouter = GlobalMediaRouter.this;
                    globalMediaRouter.addRemoteControlClient(globalMediaRouter.mRccMediaSession.getRemoteControlClient());
                    return;
                }
                GlobalMediaRouter globalMediaRouter2 = GlobalMediaRouter.this;
                globalMediaRouter2.removeRemoteControlClient(globalMediaRouter2.mRccMediaSession.getRemoteControlClient());
            }
        };
        final SystemMediaRouteProvider mSystemProvider;
        private final Map<Pair<String, String>, String> mUniqueIdMap = new HashMap();

        GlobalMediaRouter(Context context) {
            this.mApplicationContext = context;
            this.mDisplayManager = DisplayManagerCompat.getInstance(context);
            this.mLowRam = ActivityManagerCompat.isLowRamDevice((ActivityManager) context.getSystemService("activity"));
            this.mSystemProvider = SystemMediaRouteProvider.obtain(context, this);
        }

        public void start() {
            addProvider(this.mSystemProvider);
            RegisteredMediaRouteProviderWatcher registeredMediaRouteProviderWatcher = new RegisteredMediaRouteProviderWatcher(this.mApplicationContext, this);
            this.mRegisteredProviderWatcher = registeredMediaRouteProviderWatcher;
            registeredMediaRouteProviderWatcher.start();
        }

        public MediaRouter getRouter(Context context) {
            int size = this.mRouters.size();
            while (true) {
                size--;
                if (size >= 0) {
                    MediaRouter mediaRouter = (MediaRouter) this.mRouters.get(size).get();
                    if (mediaRouter == null) {
                        this.mRouters.remove(size);
                    } else if (mediaRouter.mContext == context) {
                        return mediaRouter;
                    }
                } else {
                    MediaRouter mediaRouter2 = new MediaRouter(context);
                    this.mRouters.add(new WeakReference(mediaRouter2));
                    return mediaRouter2;
                }
            }
        }

        public ContentResolver getContentResolver() {
            return this.mApplicationContext.getContentResolver();
        }

        public Context getProviderContext(String str) {
            if (str.equals(SystemMediaRouteProvider.PACKAGE_NAME)) {
                return this.mApplicationContext;
            }
            try {
                return this.mApplicationContext.createPackageContext(str, 4);
            } catch (PackageManager.NameNotFoundException unused) {
                return null;
            }
        }

        public Display getDisplay(int i) {
            return this.mDisplayManager.getDisplay(i);
        }

        public void sendControlRequest(RouteInfo routeInfo, Intent intent, ControlRequestCallback controlRequestCallback) {
            MediaRouteProvider.RouteController routeController;
            if ((routeInfo != this.mSelectedRoute || (routeController = this.mSelectedRouteController) == null || !routeController.onControlRequest(intent, controlRequestCallback)) && controlRequestCallback != null) {
                controlRequestCallback.onError((String) null, (Bundle) null);
            }
        }

        public void requestSetVolume(RouteInfo routeInfo, int i) {
            MediaRouteProvider.RouteController routeController;
            MediaRouteProvider.RouteController routeController2;
            if (routeInfo == this.mSelectedRoute && (routeController2 = this.mSelectedRouteController) != null) {
                routeController2.onSetVolume(i);
            } else if (!this.mRouteControllerMap.isEmpty() && (routeController = this.mRouteControllerMap.get(routeInfo.mDescriptorId)) != null) {
                routeController.onSetVolume(i);
            }
        }

        public void requestUpdateVolume(RouteInfo routeInfo, int i) {
            MediaRouteProvider.RouteController routeController;
            if (routeInfo == this.mSelectedRoute && (routeController = this.mSelectedRouteController) != null) {
                routeController.onUpdateVolume(i);
            }
        }

        public RouteInfo getRoute(String str) {
            Iterator<RouteInfo> it = this.mRoutes.iterator();
            while (it.hasNext()) {
                RouteInfo next = it.next();
                if (next.mUniqueId.equals(str)) {
                    return next;
                }
            }
            return null;
        }

        public List<RouteInfo> getRoutes() {
            return this.mRoutes;
        }

        /* access modifiers changed from: package-private */
        public List<ProviderInfo> getProviders() {
            return this.mProviders;
        }

        /* access modifiers changed from: package-private */
        public RouteInfo getDefaultRoute() {
            RouteInfo routeInfo = this.mDefaultRoute;
            if (routeInfo != null) {
                return routeInfo;
            }
            throw new IllegalStateException("There is no default route.  The media router has not yet been fully initialized.");
        }

        /* access modifiers changed from: package-private */
        public RouteInfo getBluetoothRoute() {
            return this.mBluetoothRoute;
        }

        /* access modifiers changed from: package-private */
        public RouteInfo getSelectedRoute() {
            RouteInfo routeInfo = this.mSelectedRoute;
            if (routeInfo != null) {
                return routeInfo;
            }
            throw new IllegalStateException("There is no currently selected route.  The media router has not yet been fully initialized.");
        }

        /* access modifiers changed from: package-private */
        public void selectRoute(RouteInfo routeInfo) {
            selectRoute(routeInfo, 3);
        }

        /* access modifiers changed from: package-private */
        public void selectRoute(RouteInfo routeInfo, int i) {
            if (!this.mRoutes.contains(routeInfo)) {
                Log.w(MediaRouter.TAG, "Ignoring attempt to select removed route: " + routeInfo);
            } else if (!routeInfo.mEnabled) {
                Log.w(MediaRouter.TAG, "Ignoring attempt to select disabled route: " + routeInfo);
            } else {
                setSelectedRouteInternal(routeInfo, i);
            }
        }

        public boolean isRouteAvailable(MediaRouteSelector mediaRouteSelector, int i) {
            if (mediaRouteSelector.isEmpty()) {
                return false;
            }
            if ((i & 2) == 0 && this.mLowRam) {
                return true;
            }
            int size = this.mRoutes.size();
            for (int i2 = 0; i2 < size; i2++) {
                RouteInfo routeInfo = this.mRoutes.get(i2);
                if (((i & 1) == 0 || !routeInfo.isDefaultOrBluetooth()) && routeInfo.matchesSelector(mediaRouteSelector)) {
                    return true;
                }
            }
            return false;
        }

        public void updateDiscoveryRequest() {
            MediaRouteSelector.Builder builder = new MediaRouteSelector.Builder();
            int size = this.mRouters.size();
            boolean z = false;
            boolean z2 = false;
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                MediaRouter mediaRouter = (MediaRouter) this.mRouters.get(size).get();
                if (mediaRouter == null) {
                    this.mRouters.remove(size);
                } else {
                    int size2 = mediaRouter.mCallbackRecords.size();
                    for (int i = 0; i < size2; i++) {
                        CallbackRecord callbackRecord = mediaRouter.mCallbackRecords.get(i);
                        builder.addSelector(callbackRecord.mSelector);
                        if ((callbackRecord.mFlags & 1) != 0) {
                            z = true;
                            z2 = true;
                        }
                        if ((callbackRecord.mFlags & 4) != 0 && !this.mLowRam) {
                            z = true;
                        }
                        if ((callbackRecord.mFlags & 8) != 0) {
                            z = true;
                        }
                    }
                }
            }
            MediaRouteSelector build = z ? builder.build() : MediaRouteSelector.EMPTY;
            MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest = this.mDiscoveryRequest;
            if (mediaRouteDiscoveryRequest == null || !mediaRouteDiscoveryRequest.getSelector().equals(build) || this.mDiscoveryRequest.isActiveScan() != z2) {
                if (!build.isEmpty() || z2) {
                    this.mDiscoveryRequest = new MediaRouteDiscoveryRequest(build, z2);
                } else if (this.mDiscoveryRequest != null) {
                    this.mDiscoveryRequest = null;
                } else {
                    return;
                }
                if (MediaRouter.DEBUG) {
                    Log.d(MediaRouter.TAG, "Updated discovery request: " + this.mDiscoveryRequest);
                }
                if (z && !z2 && this.mLowRam) {
                    Log.i(MediaRouter.TAG, "Forcing passive route discovery on a low-RAM device, system performance may be affected.  Please consider using CALLBACK_FLAG_REQUEST_DISCOVERY instead of CALLBACK_FLAG_FORCE_DISCOVERY.");
                }
                int size3 = this.mProviders.size();
                for (int i2 = 0; i2 < size3; i2++) {
                    this.mProviders.get(i2).mProviderInstance.setDiscoveryRequest(this.mDiscoveryRequest);
                }
            }
        }

        public void addProvider(MediaRouteProvider mediaRouteProvider) {
            if (findProviderInfo(mediaRouteProvider) < 0) {
                ProviderInfo providerInfo = new ProviderInfo(mediaRouteProvider);
                this.mProviders.add(providerInfo);
                if (MediaRouter.DEBUG) {
                    Log.d(MediaRouter.TAG, "Provider added: " + providerInfo);
                }
                this.mCallbackHandler.post(513, providerInfo);
                updateProviderContents(providerInfo, mediaRouteProvider.getDescriptor());
                mediaRouteProvider.setCallback(this.mProviderCallback);
                mediaRouteProvider.setDiscoveryRequest(this.mDiscoveryRequest);
            }
        }

        public void removeProvider(MediaRouteProvider mediaRouteProvider) {
            int findProviderInfo = findProviderInfo(mediaRouteProvider);
            if (findProviderInfo >= 0) {
                mediaRouteProvider.setCallback((MediaRouteProvider.Callback) null);
                mediaRouteProvider.setDiscoveryRequest((MediaRouteDiscoveryRequest) null);
                ProviderInfo providerInfo = this.mProviders.get(findProviderInfo);
                updateProviderContents(providerInfo, (MediaRouteProviderDescriptor) null);
                if (MediaRouter.DEBUG) {
                    Log.d(MediaRouter.TAG, "Provider removed: " + providerInfo);
                }
                this.mCallbackHandler.post(CallbackHandler.MSG_PROVIDER_REMOVED, providerInfo);
                this.mProviders.remove(findProviderInfo);
            }
        }

        /* access modifiers changed from: package-private */
        public void updateProviderDescriptor(MediaRouteProvider mediaRouteProvider, MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
            int findProviderInfo = findProviderInfo(mediaRouteProvider);
            if (findProviderInfo >= 0) {
                updateProviderContents(this.mProviders.get(findProviderInfo), mediaRouteProviderDescriptor);
            }
        }

        private int findProviderInfo(MediaRouteProvider mediaRouteProvider) {
            int size = this.mProviders.size();
            for (int i = 0; i < size; i++) {
                if (this.mProviders.get(i).mProviderInstance == mediaRouteProvider) {
                    return i;
                }
            }
            return -1;
        }

        /* JADX WARNING: Removed duplicated region for block: B:64:0x0177 A[LOOP:3: B:63:0x0175->B:64:0x0177, LOOP_END] */
        /* JADX WARNING: Removed duplicated region for block: B:67:0x0198  */
        /* JADX WARNING: Removed duplicated region for block: B:73:0x01c6  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void updateProviderContents(androidx.mediarouter.media.MediaRouter.ProviderInfo r18, androidx.mediarouter.media.MediaRouteProviderDescriptor r19) {
            /*
                r17 = this;
                r0 = r17
                r1 = r18
                r2 = r19
                boolean r3 = r18.updateDescriptor(r19)
                if (r3 == 0) goto L_0x01e1
                java.lang.String r4 = "MediaRouter"
                if (r2 == 0) goto L_0x016b
                boolean r6 = r19.isValid()
                if (r6 == 0) goto L_0x0157
                java.util.List r2 = r19.getRoutes()
                int r6 = r2.size()
                java.util.ArrayList r7 = new java.util.ArrayList
                r7.<init>()
                java.util.ArrayList r8 = new java.util.ArrayList
                r8.<init>()
                r9 = 0
                r10 = 0
                r11 = 0
            L_0x002b:
                java.lang.String r12 = "Route added: "
                if (r9 >= r6) goto L_0x00f6
                java.lang.Object r14 = r2.get(r9)
                androidx.mediarouter.media.MediaRouteDescriptor r14 = (androidx.mediarouter.media.MediaRouteDescriptor) r14
                java.lang.String r15 = r14.getId()
                int r3 = r1.findRouteByDescriptorId(r15)
                java.util.List r16 = r14.getGroupMemberIds()
                if (r16 == 0) goto L_0x0045
                r5 = 1
                goto L_0x0046
            L_0x0045:
                r5 = 0
            L_0x0046:
                if (r3 >= 0) goto L_0x0092
                java.lang.String r3 = r0.assignRouteUniqueId(r1, r15)
                if (r5 == 0) goto L_0x0054
                androidx.mediarouter.media.MediaRouter$RouteGroup r13 = new androidx.mediarouter.media.MediaRouter$RouteGroup
                r13.<init>(r1, r15, r3)
                goto L_0x0059
            L_0x0054:
                androidx.mediarouter.media.MediaRouter$RouteInfo r13 = new androidx.mediarouter.media.MediaRouter$RouteInfo
                r13.<init>(r1, r15, r3)
            L_0x0059:
                java.util.List<androidx.mediarouter.media.MediaRouter$RouteInfo> r3 = r1.mRoutes
                int r15 = r10 + 1
                r3.add(r10, r13)
                java.util.ArrayList<androidx.mediarouter.media.MediaRouter$RouteInfo> r3 = r0.mRoutes
                r3.add(r13)
                if (r5 == 0) goto L_0x0070
                androidx.core.util.Pair r3 = new androidx.core.util.Pair
                r3.<init>(r13, r14)
                r7.add(r3)
                goto L_0x0090
            L_0x0070:
                r13.maybeUpdateDescriptor(r14)
                boolean r3 = androidx.mediarouter.media.MediaRouter.DEBUG
                if (r3 == 0) goto L_0x0089
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                r3.append(r12)
                r3.append(r13)
                java.lang.String r3 = r3.toString()
                android.util.Log.d(r4, r3)
            L_0x0089:
                androidx.mediarouter.media.MediaRouter$GlobalMediaRouter$CallbackHandler r3 = r0.mCallbackHandler
                r5 = 257(0x101, float:3.6E-43)
                r3.post(r5, r13)
            L_0x0090:
                r10 = r15
                goto L_0x00f2
            L_0x0092:
                if (r3 >= r10) goto L_0x00a9
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r5 = "Ignoring route descriptor with duplicate id: "
                r3.append(r5)
                r3.append(r14)
                java.lang.String r3 = r3.toString()
                android.util.Log.w(r4, r3)
                goto L_0x00f2
            L_0x00a9:
                java.util.List<androidx.mediarouter.media.MediaRouter$RouteInfo> r12 = r1.mRoutes
                java.lang.Object r12 = r12.get(r3)
                androidx.mediarouter.media.MediaRouter$RouteInfo r12 = (androidx.mediarouter.media.MediaRouter.RouteInfo) r12
                boolean r13 = r12 instanceof androidx.mediarouter.media.MediaRouter.RouteGroup
                if (r13 == r5) goto L_0x00d0
                if (r5 == 0) goto L_0x00c1
                androidx.mediarouter.media.MediaRouter$RouteGroup r5 = new androidx.mediarouter.media.MediaRouter$RouteGroup
                java.lang.String r12 = r12.getId()
                r5.<init>(r1, r15, r12)
                goto L_0x00ca
            L_0x00c1:
                androidx.mediarouter.media.MediaRouter$RouteInfo r5 = new androidx.mediarouter.media.MediaRouter$RouteInfo
                java.lang.String r12 = r12.getId()
                r5.<init>(r1, r15, r12)
            L_0x00ca:
                r12 = r5
                java.util.List<androidx.mediarouter.media.MediaRouter$RouteInfo> r5 = r1.mRoutes
                r5.set(r3, r12)
            L_0x00d0:
                java.util.List<androidx.mediarouter.media.MediaRouter$RouteInfo> r5 = r1.mRoutes
                int r13 = r10 + 1
                java.util.Collections.swap(r5, r3, r10)
                boolean r3 = r12 instanceof androidx.mediarouter.media.MediaRouter.RouteGroup
                if (r3 == 0) goto L_0x00e4
                androidx.core.util.Pair r3 = new androidx.core.util.Pair
                r3.<init>(r12, r14)
                r8.add(r3)
                goto L_0x00f1
            L_0x00e4:
                int r3 = r0.updateRouteDescriptorAndNotify(r12, r14)
                if (r3 == 0) goto L_0x00f1
                androidx.mediarouter.media.MediaRouter$RouteInfo r3 = r0.mSelectedRoute
                if (r12 != r3) goto L_0x00f1
                r10 = r13
                r11 = 1
                goto L_0x00f2
            L_0x00f1:
                r10 = r13
            L_0x00f2:
                int r9 = r9 + 1
                goto L_0x002b
            L_0x00f6:
                java.util.Iterator r2 = r7.iterator()
            L_0x00fa:
                boolean r3 = r2.hasNext()
                if (r3 == 0) goto L_0x012f
                java.lang.Object r3 = r2.next()
                androidx.core.util.Pair r3 = (androidx.core.util.Pair) r3
                F r5 = r3.first
                androidx.mediarouter.media.MediaRouter$RouteInfo r5 = (androidx.mediarouter.media.MediaRouter.RouteInfo) r5
                S r3 = r3.second
                androidx.mediarouter.media.MediaRouteDescriptor r3 = (androidx.mediarouter.media.MediaRouteDescriptor) r3
                r5.maybeUpdateDescriptor(r3)
                boolean r3 = androidx.mediarouter.media.MediaRouter.DEBUG
                if (r3 == 0) goto L_0x0127
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                r3.append(r12)
                r3.append(r5)
                java.lang.String r3 = r3.toString()
                android.util.Log.d(r4, r3)
            L_0x0127:
                androidx.mediarouter.media.MediaRouter$GlobalMediaRouter$CallbackHandler r3 = r0.mCallbackHandler
                r6 = 257(0x101, float:3.6E-43)
                r3.post(r6, r5)
                goto L_0x00fa
            L_0x012f:
                java.util.Iterator r2 = r8.iterator()
                r3 = r11
            L_0x0134:
                boolean r5 = r2.hasNext()
                if (r5 == 0) goto L_0x0154
                java.lang.Object r5 = r2.next()
                androidx.core.util.Pair r5 = (androidx.core.util.Pair) r5
                F r6 = r5.first
                androidx.mediarouter.media.MediaRouter$RouteInfo r6 = (androidx.mediarouter.media.MediaRouter.RouteInfo) r6
                S r5 = r5.second
                androidx.mediarouter.media.MediaRouteDescriptor r5 = (androidx.mediarouter.media.MediaRouteDescriptor) r5
                int r5 = r0.updateRouteDescriptorAndNotify(r6, r5)
                if (r5 == 0) goto L_0x0134
                androidx.mediarouter.media.MediaRouter$RouteInfo r5 = r0.mSelectedRoute
                if (r6 != r5) goto L_0x0134
                r3 = 1
                goto L_0x0134
            L_0x0154:
                r2 = r3
                r3 = r10
                goto L_0x016d
            L_0x0157:
                java.lang.StringBuilder r3 = new java.lang.StringBuilder
                r3.<init>()
                java.lang.String r5 = "Ignoring invalid provider descriptor: "
                r3.append(r5)
                r3.append(r2)
                java.lang.String r2 = r3.toString()
                android.util.Log.w(r4, r2)
            L_0x016b:
                r2 = 0
                r3 = 0
            L_0x016d:
                java.util.List<androidx.mediarouter.media.MediaRouter$RouteInfo> r5 = r1.mRoutes
                int r5 = r5.size()
                r6 = 1
                int r5 = r5 - r6
            L_0x0175:
                if (r5 < r3) goto L_0x018b
                java.util.List<androidx.mediarouter.media.MediaRouter$RouteInfo> r6 = r1.mRoutes
                java.lang.Object r6 = r6.get(r5)
                androidx.mediarouter.media.MediaRouter$RouteInfo r6 = (androidx.mediarouter.media.MediaRouter.RouteInfo) r6
                r7 = 0
                r6.maybeUpdateDescriptor(r7)
                java.util.ArrayList<androidx.mediarouter.media.MediaRouter$RouteInfo> r7 = r0.mRoutes
                r7.remove(r6)
                int r5 = r5 + -1
                goto L_0x0175
            L_0x018b:
                r0.updateSelectedRouteIfNeeded(r2)
                java.util.List<androidx.mediarouter.media.MediaRouter$RouteInfo> r2 = r1.mRoutes
                int r2 = r2.size()
                r5 = 1
                int r2 = r2 - r5
            L_0x0196:
                if (r2 < r3) goto L_0x01c2
                java.util.List<androidx.mediarouter.media.MediaRouter$RouteInfo> r5 = r1.mRoutes
                java.lang.Object r5 = r5.remove(r2)
                androidx.mediarouter.media.MediaRouter$RouteInfo r5 = (androidx.mediarouter.media.MediaRouter.RouteInfo) r5
                boolean r6 = androidx.mediarouter.media.MediaRouter.DEBUG
                if (r6 == 0) goto L_0x01b8
                java.lang.StringBuilder r6 = new java.lang.StringBuilder
                r6.<init>()
                java.lang.String r7 = "Route removed: "
                r6.append(r7)
                r6.append(r5)
                java.lang.String r6 = r6.toString()
                android.util.Log.d(r4, r6)
            L_0x01b8:
                androidx.mediarouter.media.MediaRouter$GlobalMediaRouter$CallbackHandler r6 = r0.mCallbackHandler
                r7 = 258(0x102, float:3.62E-43)
                r6.post(r7, r5)
                int r2 = r2 + -1
                goto L_0x0196
            L_0x01c2:
                boolean r2 = androidx.mediarouter.media.MediaRouter.DEBUG
                if (r2 == 0) goto L_0x01da
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = "Provider changed: "
                r2.append(r3)
                r2.append(r1)
                java.lang.String r2 = r2.toString()
                android.util.Log.d(r4, r2)
            L_0x01da:
                androidx.mediarouter.media.MediaRouter$GlobalMediaRouter$CallbackHandler r2 = r0.mCallbackHandler
                r3 = 515(0x203, float:7.22E-43)
                r2.post(r3, r1)
            L_0x01e1:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.media.MediaRouter.GlobalMediaRouter.updateProviderContents(androidx.mediarouter.media.MediaRouter$ProviderInfo, androidx.mediarouter.media.MediaRouteProviderDescriptor):void");
        }

        private int updateRouteDescriptorAndNotify(RouteInfo routeInfo, MediaRouteDescriptor mediaRouteDescriptor) {
            int maybeUpdateDescriptor = routeInfo.maybeUpdateDescriptor(mediaRouteDescriptor);
            if (maybeUpdateDescriptor != 0) {
                if ((maybeUpdateDescriptor & 1) != 0) {
                    if (MediaRouter.DEBUG) {
                        Log.d(MediaRouter.TAG, "Route changed: " + routeInfo);
                    }
                    this.mCallbackHandler.post(CallbackHandler.MSG_ROUTE_CHANGED, routeInfo);
                }
                if ((maybeUpdateDescriptor & 2) != 0) {
                    if (MediaRouter.DEBUG) {
                        Log.d(MediaRouter.TAG, "Route volume changed: " + routeInfo);
                    }
                    this.mCallbackHandler.post(CallbackHandler.MSG_ROUTE_VOLUME_CHANGED, routeInfo);
                }
                if ((maybeUpdateDescriptor & 4) != 0) {
                    if (MediaRouter.DEBUG) {
                        Log.d(MediaRouter.TAG, "Route presentation display changed: " + routeInfo);
                    }
                    this.mCallbackHandler.post(CallbackHandler.MSG_ROUTE_PRESENTATION_DISPLAY_CHANGED, routeInfo);
                }
            }
            return maybeUpdateDescriptor;
        }

        private String assignRouteUniqueId(ProviderInfo providerInfo, String str) {
            String flattenToShortString = providerInfo.getComponentName().flattenToShortString();
            String str2 = flattenToShortString + ":" + str;
            if (findRouteByUniqueId(str2) < 0) {
                this.mUniqueIdMap.put(new Pair(flattenToShortString, str), str2);
                return str2;
            }
            Log.w(MediaRouter.TAG, "Either " + str + " isn't unique in " + flattenToShortString + " or we're trying to assign a unique ID for an already added route");
            int i = 2;
            while (true) {
                String format = String.format(Locale.US, "%s_%d", new Object[]{str2, Integer.valueOf(i)});
                if (findRouteByUniqueId(format) < 0) {
                    this.mUniqueIdMap.put(new Pair(flattenToShortString, str), format);
                    return format;
                }
                i++;
            }
        }

        private int findRouteByUniqueId(String str) {
            int size = this.mRoutes.size();
            for (int i = 0; i < size; i++) {
                if (this.mRoutes.get(i).mUniqueId.equals(str)) {
                    return i;
                }
            }
            return -1;
        }

        /* access modifiers changed from: package-private */
        public String getUniqueId(ProviderInfo providerInfo, String str) {
            return this.mUniqueIdMap.get(new Pair(providerInfo.getComponentName().flattenToShortString(), str));
        }

        /* access modifiers changed from: package-private */
        public void updateSelectedRouteIfNeeded(boolean z) {
            RouteInfo routeInfo = this.mDefaultRoute;
            if (routeInfo != null && !routeInfo.isSelectable()) {
                Log.i(MediaRouter.TAG, "Clearing the default route because it is no longer selectable: " + this.mDefaultRoute);
                this.mDefaultRoute = null;
            }
            if (this.mDefaultRoute == null && !this.mRoutes.isEmpty()) {
                Iterator<RouteInfo> it = this.mRoutes.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    RouteInfo next = it.next();
                    if (isSystemDefaultRoute(next) && next.isSelectable()) {
                        this.mDefaultRoute = next;
                        Log.i(MediaRouter.TAG, "Found default route: " + this.mDefaultRoute);
                        break;
                    }
                }
            }
            RouteInfo routeInfo2 = this.mBluetoothRoute;
            if (routeInfo2 != null && !routeInfo2.isSelectable()) {
                Log.i(MediaRouter.TAG, "Clearing the bluetooth route because it is no longer selectable: " + this.mBluetoothRoute);
                this.mBluetoothRoute = null;
            }
            if (this.mBluetoothRoute == null && !this.mRoutes.isEmpty()) {
                Iterator<RouteInfo> it2 = this.mRoutes.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    RouteInfo next2 = it2.next();
                    if (isSystemLiveAudioOnlyRoute(next2) && next2.isSelectable()) {
                        this.mBluetoothRoute = next2;
                        Log.i(MediaRouter.TAG, "Found bluetooth route: " + this.mBluetoothRoute);
                        break;
                    }
                }
            }
            RouteInfo routeInfo3 = this.mSelectedRoute;
            if (routeInfo3 == null || !routeInfo3.isSelectable()) {
                Log.i(MediaRouter.TAG, "Unselecting the current route because it is no longer selectable: " + this.mSelectedRoute);
                setSelectedRouteInternal(chooseFallbackRoute(), 0);
            } else if (z) {
                RouteInfo routeInfo4 = this.mSelectedRoute;
                if (routeInfo4 instanceof RouteGroup) {
                    List<RouteInfo> routes = ((RouteGroup) routeInfo4).getRoutes();
                    HashSet hashSet = new HashSet();
                    for (RouteInfo routeInfo5 : routes) {
                        hashSet.add(routeInfo5.mDescriptorId);
                    }
                    Iterator<Map.Entry<String, MediaRouteProvider.RouteController>> it3 = this.mRouteControllerMap.entrySet().iterator();
                    while (it3.hasNext()) {
                        Map.Entry next3 = it3.next();
                        if (!hashSet.contains(next3.getKey())) {
                            MediaRouteProvider.RouteController routeController = (MediaRouteProvider.RouteController) next3.getValue();
                            routeController.onUnselect();
                            routeController.onRelease();
                            it3.remove();
                        }
                    }
                    for (RouteInfo next4 : routes) {
                        if (!this.mRouteControllerMap.containsKey(next4.mDescriptorId)) {
                            MediaRouteProvider.RouteController onCreateRouteController = next4.getProviderInstance().onCreateRouteController(next4.mDescriptorId, this.mSelectedRoute.mDescriptorId);
                            onCreateRouteController.onSelect();
                            this.mRouteControllerMap.put(next4.mDescriptorId, onCreateRouteController);
                        }
                    }
                }
                updatePlaybackInfoFromSelectedRoute();
            }
        }

        /* access modifiers changed from: package-private */
        public RouteInfo chooseFallbackRoute() {
            Iterator<RouteInfo> it = this.mRoutes.iterator();
            while (it.hasNext()) {
                RouteInfo next = it.next();
                if (next != this.mDefaultRoute && isSystemLiveAudioOnlyRoute(next) && next.isSelectable()) {
                    return next;
                }
            }
            return this.mDefaultRoute;
        }

        private boolean isSystemLiveAudioOnlyRoute(RouteInfo routeInfo) {
            return routeInfo.getProviderInstance() == this.mSystemProvider && routeInfo.supportsControlCategory(MediaControlIntent.CATEGORY_LIVE_AUDIO) && !routeInfo.supportsControlCategory(MediaControlIntent.CATEGORY_LIVE_VIDEO);
        }

        private boolean isSystemDefaultRoute(RouteInfo routeInfo) {
            return routeInfo.getProviderInstance() == this.mSystemProvider && routeInfo.mDescriptorId.equals(SystemMediaRouteProvider.DEFAULT_ROUTE_ID);
        }

        private void setSelectedRouteInternal(RouteInfo routeInfo, int i) {
            if (MediaRouter.sGlobal == null || (this.mBluetoothRoute != null && routeInfo.isDefault())) {
                StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                StringBuilder sb = new StringBuilder();
                for (int i2 = 3; i2 < stackTrace.length; i2++) {
                    StackTraceElement stackTraceElement = stackTrace[i2];
                    sb.append(stackTraceElement.getClassName());
                    sb.append(".");
                    sb.append(stackTraceElement.getMethodName());
                    sb.append(":");
                    sb.append(stackTraceElement.getLineNumber());
                    sb.append("  ");
                }
                if (MediaRouter.sGlobal == null) {
                    Log.w(MediaRouter.TAG, "setSelectedRouteInternal is called while sGlobal is null: pkgName=" + this.mApplicationContext.getPackageName() + ", callers=" + sb.toString());
                } else {
                    Log.w(MediaRouter.TAG, "Default route is selected while a BT route is available: pkgName=" + this.mApplicationContext.getPackageName() + ", callers=" + sb.toString());
                }
            }
            RouteInfo routeInfo2 = this.mSelectedRoute;
            if (routeInfo2 != routeInfo) {
                if (routeInfo2 != null) {
                    if (MediaRouter.DEBUG) {
                        Log.d(MediaRouter.TAG, "Route unselected: " + this.mSelectedRoute + " reason: " + i);
                    }
                    this.mCallbackHandler.post(CallbackHandler.MSG_ROUTE_UNSELECTED, this.mSelectedRoute, i);
                    MediaRouteProvider.RouteController routeController = this.mSelectedRouteController;
                    if (routeController != null) {
                        routeController.onUnselect(i);
                        this.mSelectedRouteController.onRelease();
                        this.mSelectedRouteController = null;
                    }
                    if (!this.mRouteControllerMap.isEmpty()) {
                        for (MediaRouteProvider.RouteController next : this.mRouteControllerMap.values()) {
                            next.onUnselect(i);
                            next.onRelease();
                        }
                        this.mRouteControllerMap.clear();
                    }
                }
                this.mSelectedRoute = routeInfo;
                MediaRouteProvider.RouteController onCreateRouteController = routeInfo.getProviderInstance().onCreateRouteController(routeInfo.mDescriptorId);
                this.mSelectedRouteController = onCreateRouteController;
                if (onCreateRouteController != null) {
                    onCreateRouteController.onSelect();
                }
                if (MediaRouter.DEBUG) {
                    Log.d(MediaRouter.TAG, "Route selected: " + this.mSelectedRoute);
                }
                this.mCallbackHandler.post(CallbackHandler.MSG_ROUTE_SELECTED, this.mSelectedRoute);
                RouteInfo routeInfo3 = this.mSelectedRoute;
                if (routeInfo3 instanceof RouteGroup) {
                    List<RouteInfo> routes = ((RouteGroup) routeInfo3).getRoutes();
                    this.mRouteControllerMap.clear();
                    for (RouteInfo next2 : routes) {
                        MediaRouteProvider.RouteController onCreateRouteController2 = next2.getProviderInstance().onCreateRouteController(next2.mDescriptorId, this.mSelectedRoute.mDescriptorId);
                        onCreateRouteController2.onSelect();
                        this.mRouteControllerMap.put(next2.mDescriptorId, onCreateRouteController2);
                    }
                }
                updatePlaybackInfoFromSelectedRoute();
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x000f, code lost:
            r0 = r2.mProviders.get(r0);
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onSystemRouteSelectedByDescriptorId(java.lang.String r3) {
            /*
                r2 = this;
                androidx.mediarouter.media.MediaRouter$GlobalMediaRouter$CallbackHandler r0 = r2.mCallbackHandler
                r1 = 262(0x106, float:3.67E-43)
                r0.removeMessages(r1)
                androidx.mediarouter.media.SystemMediaRouteProvider r0 = r2.mSystemProvider
                int r0 = r2.findProviderInfo(r0)
                if (r0 < 0) goto L_0x0028
                java.util.ArrayList<androidx.mediarouter.media.MediaRouter$ProviderInfo> r1 = r2.mProviders
                java.lang.Object r0 = r1.get(r0)
                androidx.mediarouter.media.MediaRouter$ProviderInfo r0 = (androidx.mediarouter.media.MediaRouter.ProviderInfo) r0
                int r3 = r0.findRouteByDescriptorId(r3)
                if (r3 < 0) goto L_0x0028
                java.util.List<androidx.mediarouter.media.MediaRouter$RouteInfo> r0 = r0.mRoutes
                java.lang.Object r3 = r0.get(r3)
                androidx.mediarouter.media.MediaRouter$RouteInfo r3 = (androidx.mediarouter.media.MediaRouter.RouteInfo) r3
                r3.select()
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.mediarouter.media.MediaRouter.GlobalMediaRouter.onSystemRouteSelectedByDescriptorId(java.lang.String):void");
        }

        public void addRemoteControlClient(Object obj) {
            if (findRemoteControlClientRecord(obj) < 0) {
                this.mRemoteControlClients.add(new RemoteControlClientRecord(obj));
            }
        }

        public void removeRemoteControlClient(Object obj) {
            int findRemoteControlClientRecord = findRemoteControlClientRecord(obj);
            if (findRemoteControlClientRecord >= 0) {
                this.mRemoteControlClients.remove(findRemoteControlClientRecord).disconnect();
            }
        }

        public void setMediaSession(Object obj) {
            setMediaSessionRecord(obj != null ? new MediaSessionRecord(this, obj) : null);
        }

        public void setMediaSessionCompat(MediaSessionCompat mediaSessionCompat) {
            this.mCompatSession = mediaSessionCompat;
            if (Build.VERSION.SDK_INT >= 21) {
                setMediaSessionRecord(mediaSessionCompat != null ? new MediaSessionRecord(mediaSessionCompat) : null);
            } else if (Build.VERSION.SDK_INT >= 14) {
                MediaSessionCompat mediaSessionCompat2 = this.mRccMediaSession;
                if (mediaSessionCompat2 != null) {
                    removeRemoteControlClient(mediaSessionCompat2.getRemoteControlClient());
                    this.mRccMediaSession.removeOnActiveChangeListener(this.mSessionActiveListener);
                }
                this.mRccMediaSession = mediaSessionCompat;
                if (mediaSessionCompat != null) {
                    mediaSessionCompat.addOnActiveChangeListener(this.mSessionActiveListener);
                    if (mediaSessionCompat.isActive()) {
                        addRemoteControlClient(mediaSessionCompat.getRemoteControlClient());
                    }
                }
            }
        }

        public void setMediaSession2(MediaSession2 mediaSession2, RouteMediaPlayerConnector routeMediaPlayerConnector, MediaPlaylistAgent mediaPlaylistAgent) {
            setMediaSessionRecord(mediaSession2 != null ? new MediaSessionRecord(mediaSession2, routeMediaPlayerConnector, mediaPlaylistAgent) : null);
        }

        private void setMediaSessionRecord(MediaSessionRecord mediaSessionRecord) {
            MediaSessionRecord mediaSessionRecord2 = this.mMediaSession;
            if (mediaSessionRecord2 != null) {
                mediaSessionRecord2.clearVolumeHandling();
            }
            this.mMediaSession = mediaSessionRecord;
            if (mediaSessionRecord != null) {
                updatePlaybackInfoFromSelectedRoute();
            }
        }

        public MediaSessionCompat.Token getMediaSessionToken() {
            MediaSessionRecord mediaSessionRecord = this.mMediaSession;
            if (mediaSessionRecord != null) {
                return mediaSessionRecord.getToken();
            }
            MediaSessionCompat mediaSessionCompat = this.mCompatSession;
            if (mediaSessionCompat != null) {
                return mediaSessionCompat.getSessionToken();
            }
            return null;
        }

        private int findRemoteControlClientRecord(Object obj) {
            int size = this.mRemoteControlClients.size();
            for (int i = 0; i < size; i++) {
                if (this.mRemoteControlClients.get(i).getRemoteControlClient() == obj) {
                    return i;
                }
            }
            return -1;
        }

        private void updatePlaybackInfoFromSelectedRoute() {
            RouteInfo routeInfo = this.mSelectedRoute;
            if (routeInfo != null) {
                this.mPlaybackInfo.volume = routeInfo.getVolume();
                this.mPlaybackInfo.volumeMax = this.mSelectedRoute.getVolumeMax();
                this.mPlaybackInfo.volumeHandling = this.mSelectedRoute.getVolumeHandling();
                this.mPlaybackInfo.playbackStream = this.mSelectedRoute.getPlaybackStream();
                this.mPlaybackInfo.playbackType = this.mSelectedRoute.getPlaybackType();
                int size = this.mRemoteControlClients.size();
                int i = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    this.mRemoteControlClients.get(i2).updatePlaybackInfo();
                }
                if (this.mMediaSession == null) {
                    return;
                }
                if (this.mSelectedRoute == getDefaultRoute() || this.mSelectedRoute == getBluetoothRoute()) {
                    this.mMediaSession.clearVolumeHandling();
                    return;
                }
                if (this.mPlaybackInfo.volumeHandling == 1) {
                    i = 2;
                }
                this.mMediaSession.configureVolume(i, this.mPlaybackInfo.volumeMax, this.mPlaybackInfo.volume);
                return;
            }
            MediaSessionRecord mediaSessionRecord = this.mMediaSession;
            if (mediaSessionRecord != null) {
                mediaSessionRecord.clearVolumeHandling();
            }
        }

        private final class ProviderCallback extends MediaRouteProvider.Callback {
            ProviderCallback() {
            }

            public void onDescriptorChanged(MediaRouteProvider mediaRouteProvider, MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
                GlobalMediaRouter.this.updateProviderDescriptor(mediaRouteProvider, mediaRouteProviderDescriptor);
            }
        }

        private final class MediaSessionRecord {
            private int mControlType;
            private final MediaPlayerConnector mLocalPlayer;
            private int mMaxVolume;
            private final MediaSession2 mMediaSession2;
            private final MediaSessionCompat mMsCompat;
            private final MediaPlaylistAgent mPlaylistAgent;
            private final RouteMediaPlayerConnector mRoutePlayer;
            private VolumeProviderCompat mVpCompat;

            MediaSessionRecord(GlobalMediaRouter globalMediaRouter, Object obj) {
                this(MediaSessionCompat.fromMediaSession(globalMediaRouter.mApplicationContext, obj));
            }

            MediaSessionRecord(MediaSessionCompat mediaSessionCompat) {
                this.mMsCompat = mediaSessionCompat;
                this.mMediaSession2 = null;
                this.mRoutePlayer = null;
                this.mLocalPlayer = null;
                this.mPlaylistAgent = null;
            }

            MediaSessionRecord(MediaSession2 mediaSession2, RouteMediaPlayerConnector routeMediaPlayerConnector, MediaPlaylistAgent mediaPlaylistAgent) {
                this.mMsCompat = null;
                this.mMediaSession2 = mediaSession2;
                this.mRoutePlayer = routeMediaPlayerConnector;
                this.mLocalPlayer = mediaSession2.getPlayerConnector();
                this.mPlaylistAgent = mediaPlaylistAgent;
            }

            public void configureVolume(int i, int i2, int i3) {
                if (this.mMsCompat != null) {
                    VolumeProviderCompat volumeProviderCompat = this.mVpCompat;
                    if (volumeProviderCompat != null && i == this.mControlType && i2 == this.mMaxVolume) {
                        volumeProviderCompat.setCurrentVolume(i3);
                        return;
                    }
                    C44561 r0 = new VolumeProviderCompat(i, i2, i3) {
                        public void onSetVolumeTo(final int i) {
                            GlobalMediaRouter.this.mCallbackHandler.post(new Runnable() {
                                public void run() {
                                    if (GlobalMediaRouter.this.mSelectedRoute != null) {
                                        GlobalMediaRouter.this.mSelectedRoute.requestSetVolume(i);
                                    }
                                }
                            });
                        }

                        public void onAdjustVolume(final int i) {
                            GlobalMediaRouter.this.mCallbackHandler.post(new Runnable() {
                                public void run() {
                                    if (GlobalMediaRouter.this.mSelectedRoute != null) {
                                        GlobalMediaRouter.this.mSelectedRoute.requestUpdateVolume(i);
                                    }
                                }
                            });
                        }
                    };
                    this.mVpCompat = r0;
                    this.mMsCompat.setPlaybackToRemote(r0);
                } else if (this.mMediaSession2 != null) {
                    this.mRoutePlayer.updateRouteInfo(GlobalMediaRouter.this.mSelectedRoute);
                    this.mMediaSession2.updatePlayerConnector(this.mRoutePlayer, this.mPlaylistAgent);
                }
            }

            public void clearVolumeHandling() {
                MediaSessionCompat mediaSessionCompat = this.mMsCompat;
                if (mediaSessionCompat != null) {
                    mediaSessionCompat.setPlaybackToLocal(GlobalMediaRouter.this.mPlaybackInfo.playbackStream);
                    this.mVpCompat = null;
                } else if (this.mMediaSession2 != null) {
                    RouteMediaPlayerConnector routeMediaPlayerConnector = this.mRoutePlayer;
                    if (routeMediaPlayerConnector != null) {
                        routeMediaPlayerConnector.updateRouteInfo((RouteInfo) null);
                    }
                    this.mMediaSession2.updatePlayerConnector(this.mLocalPlayer, this.mPlaylistAgent);
                }
            }

            public MediaSessionCompat.Token getToken() {
                MediaSessionCompat mediaSessionCompat = this.mMsCompat;
                if (mediaSessionCompat != null) {
                    return mediaSessionCompat.getSessionToken();
                }
                MediaSession2 mediaSession2 = this.mMediaSession2;
                if (mediaSession2 != null) {
                    return mediaSession2.getSessionCompat().getSessionToken();
                }
                return null;
            }
        }

        private final class RemoteControlClientRecord implements RemoteControlClientCompat.VolumeCallback {
            private boolean mDisconnected;
            private final RemoteControlClientCompat mRccCompat;

            public RemoteControlClientRecord(Object obj) {
                RemoteControlClientCompat obtain = RemoteControlClientCompat.obtain(GlobalMediaRouter.this.mApplicationContext, obj);
                this.mRccCompat = obtain;
                obtain.setVolumeCallback(this);
                updatePlaybackInfo();
            }

            public Object getRemoteControlClient() {
                return this.mRccCompat.getRemoteControlClient();
            }

            public void disconnect() {
                this.mDisconnected = true;
                this.mRccCompat.setVolumeCallback((RemoteControlClientCompat.VolumeCallback) null);
            }

            public void updatePlaybackInfo() {
                this.mRccCompat.setPlaybackInfo(GlobalMediaRouter.this.mPlaybackInfo);
            }

            public void onVolumeSetRequest(int i) {
                if (!this.mDisconnected && GlobalMediaRouter.this.mSelectedRoute != null) {
                    GlobalMediaRouter.this.mSelectedRoute.requestSetVolume(i);
                }
            }

            public void onVolumeUpdateRequest(int i) {
                if (!this.mDisconnected && GlobalMediaRouter.this.mSelectedRoute != null) {
                    GlobalMediaRouter.this.mSelectedRoute.requestUpdateVolume(i);
                }
            }
        }

        private final class CallbackHandler extends Handler {
            public static final int MSG_PROVIDER_ADDED = 513;
            public static final int MSG_PROVIDER_CHANGED = 515;
            public static final int MSG_PROVIDER_REMOVED = 514;
            public static final int MSG_ROUTE_ADDED = 257;
            public static final int MSG_ROUTE_CHANGED = 259;
            public static final int MSG_ROUTE_PRESENTATION_DISPLAY_CHANGED = 261;
            public static final int MSG_ROUTE_REMOVED = 258;
            public static final int MSG_ROUTE_SELECTED = 262;
            public static final int MSG_ROUTE_UNSELECTED = 263;
            public static final int MSG_ROUTE_VOLUME_CHANGED = 260;
            private static final int MSG_TYPE_MASK = 65280;
            private static final int MSG_TYPE_PROVIDER = 512;
            private static final int MSG_TYPE_ROUTE = 256;
            private final ArrayList<CallbackRecord> mTempCallbackRecords = new ArrayList<>();

            CallbackHandler() {
            }

            public void post(int i, Object obj) {
                obtainMessage(i, obj).sendToTarget();
            }

            public void post(int i, Object obj, int i2) {
                Message obtainMessage = obtainMessage(i, obj);
                obtainMessage.arg1 = i2;
                obtainMessage.sendToTarget();
            }

            public void handleMessage(Message message) {
                int i = message.what;
                Object obj = message.obj;
                int i2 = message.arg1;
                if (i == 259 && GlobalMediaRouter.this.getSelectedRoute().getId().equals(((RouteInfo) obj).getId())) {
                    GlobalMediaRouter.this.updateSelectedRouteIfNeeded(true);
                }
                syncWithSystemProvider(i, obj);
                try {
                    int size = GlobalMediaRouter.this.mRouters.size();
                    while (true) {
                        size--;
                        if (size < 0) {
                            break;
                        }
                        MediaRouter mediaRouter = (MediaRouter) GlobalMediaRouter.this.mRouters.get(size).get();
                        if (mediaRouter == null) {
                            GlobalMediaRouter.this.mRouters.remove(size);
                        } else {
                            this.mTempCallbackRecords.addAll(mediaRouter.mCallbackRecords);
                        }
                    }
                    int size2 = this.mTempCallbackRecords.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        invokeCallback(this.mTempCallbackRecords.get(i3), i, obj, i2);
                    }
                } finally {
                    this.mTempCallbackRecords.clear();
                }
            }

            private void syncWithSystemProvider(int i, Object obj) {
                if (i != 262) {
                    switch (i) {
                        case 257:
                            GlobalMediaRouter.this.mSystemProvider.onSyncRouteAdded((RouteInfo) obj);
                            return;
                        case MSG_ROUTE_REMOVED /*258*/:
                            GlobalMediaRouter.this.mSystemProvider.onSyncRouteRemoved((RouteInfo) obj);
                            return;
                        case MSG_ROUTE_CHANGED /*259*/:
                            GlobalMediaRouter.this.mSystemProvider.onSyncRouteChanged((RouteInfo) obj);
                            return;
                        default:
                            return;
                    }
                } else {
                    GlobalMediaRouter.this.mSystemProvider.onSyncRouteSelected((RouteInfo) obj);
                }
            }

            private void invokeCallback(CallbackRecord callbackRecord, int i, Object obj, int i2) {
                MediaRouter mediaRouter = callbackRecord.mRouter;
                Callback callback = callbackRecord.mCallback;
                int i3 = 65280 & i;
                if (i3 == 256) {
                    RouteInfo routeInfo = (RouteInfo) obj;
                    if (callbackRecord.filterRouteEvent(routeInfo)) {
                        switch (i) {
                            case 257:
                                callback.onRouteAdded(mediaRouter, routeInfo);
                                return;
                            case MSG_ROUTE_REMOVED /*258*/:
                                callback.onRouteRemoved(mediaRouter, routeInfo);
                                return;
                            case MSG_ROUTE_CHANGED /*259*/:
                                callback.onRouteChanged(mediaRouter, routeInfo);
                                return;
                            case MSG_ROUTE_VOLUME_CHANGED /*260*/:
                                callback.onRouteVolumeChanged(mediaRouter, routeInfo);
                                return;
                            case MSG_ROUTE_PRESENTATION_DISPLAY_CHANGED /*261*/:
                                callback.onRoutePresentationDisplayChanged(mediaRouter, routeInfo);
                                return;
                            case MSG_ROUTE_SELECTED /*262*/:
                                callback.onRouteSelected(mediaRouter, routeInfo);
                                return;
                            case MSG_ROUTE_UNSELECTED /*263*/:
                                callback.onRouteUnselected(mediaRouter, routeInfo, i2);
                                return;
                            default:
                                return;
                        }
                    }
                } else if (i3 == 512) {
                    ProviderInfo providerInfo = (ProviderInfo) obj;
                    switch (i) {
                        case 513:
                            callback.onProviderAdded(mediaRouter, providerInfo);
                            return;
                        case MSG_PROVIDER_REMOVED /*514*/:
                            callback.onProviderRemoved(mediaRouter, providerInfo);
                            return;
                        case MSG_PROVIDER_CHANGED /*515*/:
                            callback.onProviderChanged(mediaRouter, providerInfo);
                            return;
                        default:
                            return;
                    }
                }
            }
        }
    }
}

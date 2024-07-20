package androidx.mediarouter.media;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import android.util.SparseArray;
import androidx.core.util.ObjectsCompat;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.MediaRouteProviderDescriptor;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;

public abstract class MediaRouteProviderService extends Service {
    static final boolean DEBUG = Log.isLoggable(TAG, 3);
    static final int PRIVATE_MSG_CLIENT_DIED = 1;
    public static final String SERVICE_INTERFACE = "android.media.MediaRouteProviderService";
    static final String TAG = "MediaRouteProviderSrv";
    private final ArrayList<ClientRecord> mClients = new ArrayList<>();
    private MediaRouteDiscoveryRequest mCompositeDiscoveryRequest;
    final PrivateHandler mPrivateHandler = new PrivateHandler();
    MediaRouteProvider mProvider;
    private final ProviderCallback mProviderCallback = new ProviderCallback();
    private final ReceiveHandler mReceiveHandler = new ReceiveHandler(this);
    private final Messenger mReceiveMessenger = new Messenger(this.mReceiveHandler);

    public abstract MediaRouteProvider onCreateMediaRouteProvider();

    public MediaRouteProvider getMediaRouteProvider() {
        return this.mProvider;
    }

    public IBinder onBind(Intent intent) {
        MediaRouteProvider onCreateMediaRouteProvider;
        if (!intent.getAction().equals("android.media.MediaRouteProviderService")) {
            return null;
        }
        if (this.mProvider == null && (onCreateMediaRouteProvider = onCreateMediaRouteProvider()) != null) {
            String packageName = onCreateMediaRouteProvider.getMetadata().getPackageName();
            if (packageName.equals(getPackageName())) {
                this.mProvider = onCreateMediaRouteProvider;
                onCreateMediaRouteProvider.setCallback(this.mProviderCallback);
            } else {
                throw new IllegalStateException("onCreateMediaRouteProvider() returned a provider whose package name does not match the package name of the service.  A media route provider service can only export its own media route providers.  Provider package name: " + packageName + ".  Service package name: " + getPackageName() + ".");
            }
        }
        if (this.mProvider != null) {
            return this.mReceiveMessenger.getBinder();
        }
        return null;
    }

    public boolean onUnbind(Intent intent) {
        MediaRouteProvider mediaRouteProvider = this.mProvider;
        if (mediaRouteProvider != null) {
            mediaRouteProvider.setCallback((MediaRouteProvider.Callback) null);
        }
        return super.onUnbind(intent);
    }

    /* access modifiers changed from: package-private */
    public boolean onRegisterClient(Messenger messenger, int i, int i2) {
        if (i2 < 1 || findClient(messenger) >= 0) {
            return false;
        }
        ClientRecord clientRecord = new ClientRecord(messenger, i2);
        if (!clientRecord.register()) {
            return false;
        }
        this.mClients.add(clientRecord);
        if (DEBUG) {
            Log.d(TAG, clientRecord + ": Registered, version=" + i2);
        }
        if (i != 0) {
            sendReply(messenger, 2, i, 1, createDescriptorBundleForClientVersion(this.mProvider.getDescriptor(), clientRecord.mVersion), (Bundle) null);
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean onUnregisterClient(Messenger messenger, int i) {
        int findClient = findClient(messenger);
        if (findClient < 0) {
            return false;
        }
        ClientRecord remove = this.mClients.remove(findClient);
        if (DEBUG) {
            Log.d(TAG, remove + ": Unregistered");
        }
        remove.dispose();
        sendGenericSuccess(messenger, i);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void onBinderDied(Messenger messenger) {
        int findClient = findClient(messenger);
        if (findClient >= 0) {
            ClientRecord remove = this.mClients.remove(findClient);
            if (DEBUG) {
                Log.d(TAG, remove + ": Binder died");
            }
            remove.dispose();
        }
    }

    /* access modifiers changed from: package-private */
    public boolean onCreateRouteController(Messenger messenger, int i, int i2, String str, String str2) {
        ClientRecord client = getClient(messenger);
        if (client == null || !client.createRouteController(str, str2, i2)) {
            return false;
        }
        if (DEBUG) {
            Log.d(TAG, client + ": Route controller created, controllerId=" + i2 + ", routeId=" + str + ", routeGroupId=" + str2);
        }
        sendGenericSuccess(messenger, i);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean onReleaseRouteController(Messenger messenger, int i, int i2) {
        ClientRecord client = getClient(messenger);
        if (client == null || !client.releaseRouteController(i2)) {
            return false;
        }
        if (DEBUG) {
            Log.d(TAG, client + ": Route controller released" + ", controllerId=" + i2);
        }
        sendGenericSuccess(messenger, i);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean onSelectRoute(Messenger messenger, int i, int i2) {
        MediaRouteProvider.RouteController routeController;
        ClientRecord client = getClient(messenger);
        if (client == null || (routeController = client.getRouteController(i2)) == null) {
            return false;
        }
        routeController.onSelect();
        if (DEBUG) {
            Log.d(TAG, client + ": Route selected" + ", controllerId=" + i2);
        }
        sendGenericSuccess(messenger, i);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean onUnselectRoute(Messenger messenger, int i, int i2, int i3) {
        MediaRouteProvider.RouteController routeController;
        ClientRecord client = getClient(messenger);
        if (client == null || (routeController = client.getRouteController(i2)) == null) {
            return false;
        }
        routeController.onUnselect(i3);
        if (DEBUG) {
            Log.d(TAG, client + ": Route unselected" + ", controllerId=" + i2);
        }
        sendGenericSuccess(messenger, i);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean onSetRouteVolume(Messenger messenger, int i, int i2, int i3) {
        MediaRouteProvider.RouteController routeController;
        ClientRecord client = getClient(messenger);
        if (client == null || (routeController = client.getRouteController(i2)) == null) {
            return false;
        }
        routeController.onSetVolume(i3);
        if (DEBUG) {
            Log.d(TAG, client + ": Route volume changed" + ", controllerId=" + i2 + ", volume=" + i3);
        }
        sendGenericSuccess(messenger, i);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean onUpdateRouteVolume(Messenger messenger, int i, int i2, int i3) {
        MediaRouteProvider.RouteController routeController;
        ClientRecord client = getClient(messenger);
        if (client == null || (routeController = client.getRouteController(i2)) == null) {
            return false;
        }
        routeController.onUpdateVolume(i3);
        if (DEBUG) {
            Log.d(TAG, client + ": Route volume updated" + ", controllerId=" + i2 + ", delta=" + i3);
        }
        sendGenericSuccess(messenger, i);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean onRouteControlRequest(Messenger messenger, int i, int i2, Intent intent) {
        MediaRouteProvider.RouteController routeController;
        ClientRecord client = getClient(messenger);
        if (client == null || (routeController = client.getRouteController(i2)) == null) {
            return false;
        }
        C44541 r0 = null;
        if (i != 0) {
            final ClientRecord clientRecord = client;
            final int i3 = i2;
            final Intent intent2 = intent;
            final Messenger messenger2 = messenger;
            final int i4 = i;
            r0 = new MediaRouter.ControlRequestCallback() {
                public void onResult(Bundle bundle) {
                    if (MediaRouteProviderService.DEBUG) {
                        Log.d(MediaRouteProviderService.TAG, clientRecord + ": Route control request succeeded" + ", controllerId=" + i3 + ", intent=" + intent2 + ", data=" + bundle);
                    }
                    if (MediaRouteProviderService.this.findClient(messenger2) >= 0) {
                        MediaRouteProviderService.sendReply(messenger2, 3, i4, 0, bundle, (Bundle) null);
                    }
                }

                public void onError(String str, Bundle bundle) {
                    if (MediaRouteProviderService.DEBUG) {
                        Log.d(MediaRouteProviderService.TAG, clientRecord + ": Route control request failed" + ", controllerId=" + i3 + ", intent=" + intent2 + ", error=" + str + ", data=" + bundle);
                    }
                    if (MediaRouteProviderService.this.findClient(messenger2) < 0) {
                        return;
                    }
                    if (str != null) {
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("error", str);
                        MediaRouteProviderService.sendReply(messenger2, 4, i4, 0, bundle, bundle2);
                        return;
                    }
                    MediaRouteProviderService.sendReply(messenger2, 4, i4, 0, bundle, (Bundle) null);
                }
            };
        }
        if (!routeController.onControlRequest(intent, r0)) {
            return false;
        }
        if (!DEBUG) {
            return true;
        }
        Log.d(TAG, client + ": Route control request delivered" + ", controllerId=" + i2 + ", intent=" + intent);
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean onSetDiscoveryRequest(Messenger messenger, int i, MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest) {
        ClientRecord client = getClient(messenger);
        if (client == null) {
            return false;
        }
        boolean discoveryRequest = client.setDiscoveryRequest(mediaRouteDiscoveryRequest);
        if (DEBUG) {
            Log.d(TAG, client + ": Set discovery request, request=" + mediaRouteDiscoveryRequest + ", actuallyChanged=" + discoveryRequest + ", compositeDiscoveryRequest=" + this.mCompositeDiscoveryRequest);
        }
        sendGenericSuccess(messenger, i);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void sendDescriptorChanged(MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
        int size = this.mClients.size();
        for (int i = 0; i < size; i++) {
            ClientRecord clientRecord = this.mClients.get(i);
            sendReply(clientRecord.mMessenger, 5, 0, 0, createDescriptorBundleForClientVersion(mediaRouteProviderDescriptor, clientRecord.mVersion), (Bundle) null);
            if (DEBUG) {
                Log.d(TAG, clientRecord + ": Sent descriptor change event, descriptor=" + mediaRouteProviderDescriptor);
            }
        }
    }

    static Bundle createDescriptorBundleForClientVersion(MediaRouteProviderDescriptor mediaRouteProviderDescriptor, int i) {
        if (mediaRouteProviderDescriptor == null) {
            return null;
        }
        MediaRouteProviderDescriptor.Builder builder = new MediaRouteProviderDescriptor.Builder(mediaRouteProviderDescriptor);
        builder.setRoutes((Collection<MediaRouteDescriptor>) null);
        for (MediaRouteDescriptor next : mediaRouteProviderDescriptor.getRoutes()) {
            if (i >= next.getMinClientVersion() && i <= next.getMaxClientVersion()) {
                builder.addRoute(next);
            }
        }
        return builder.build().asBundle();
    }

    /* access modifiers changed from: package-private */
    public boolean updateCompositeDiscoveryRequest() {
        int size = this.mClients.size();
        MediaRouteSelector.Builder builder = null;
        MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest = null;
        boolean z = false;
        for (int i = 0; i < size; i++) {
            MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest2 = this.mClients.get(i).mDiscoveryRequest;
            if (mediaRouteDiscoveryRequest2 != null && (!mediaRouteDiscoveryRequest2.getSelector().isEmpty() || mediaRouteDiscoveryRequest2.isActiveScan())) {
                z |= mediaRouteDiscoveryRequest2.isActiveScan();
                if (mediaRouteDiscoveryRequest == null) {
                    mediaRouteDiscoveryRequest = mediaRouteDiscoveryRequest2;
                } else {
                    if (builder == null) {
                        builder = new MediaRouteSelector.Builder(mediaRouteDiscoveryRequest.getSelector());
                    }
                    builder.addSelector(mediaRouteDiscoveryRequest2.getSelector());
                }
            }
        }
        if (builder != null) {
            mediaRouteDiscoveryRequest = new MediaRouteDiscoveryRequest(builder.build(), z);
        }
        if (ObjectsCompat.equals(this.mCompositeDiscoveryRequest, mediaRouteDiscoveryRequest)) {
            return false;
        }
        this.mCompositeDiscoveryRequest = mediaRouteDiscoveryRequest;
        this.mProvider.setDiscoveryRequest(mediaRouteDiscoveryRequest);
        return true;
    }

    private ClientRecord getClient(Messenger messenger) {
        int findClient = findClient(messenger);
        if (findClient >= 0) {
            return this.mClients.get(findClient);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public int findClient(Messenger messenger) {
        int size = this.mClients.size();
        for (int i = 0; i < size; i++) {
            if (this.mClients.get(i).hasMessenger(messenger)) {
                return i;
            }
        }
        return -1;
    }

    static void sendGenericFailure(Messenger messenger, int i) {
        if (i != 0) {
            sendReply(messenger, 0, i, 0, (Object) null, (Bundle) null);
        }
    }

    private static void sendGenericSuccess(Messenger messenger, int i) {
        if (i != 0) {
            sendReply(messenger, 1, i, 0, (Object) null, (Bundle) null);
        }
    }

    static void sendReply(Messenger messenger, int i, int i2, int i3, Object obj, Bundle bundle) {
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.arg1 = i2;
        obtain.arg2 = i3;
        obtain.obj = obj;
        obtain.setData(bundle);
        try {
            messenger.send(obtain);
        } catch (DeadObjectException unused) {
        } catch (RemoteException e) {
            Log.e(TAG, "Could not send message to " + getClientId(messenger), e);
        }
    }

    static String getClientId(Messenger messenger) {
        return "Client connection " + messenger.getBinder().toString();
    }

    private final class PrivateHandler extends Handler {
        PrivateHandler() {
        }

        public void handleMessage(Message message) {
            if (message.what == 1) {
                MediaRouteProviderService.this.onBinderDied((Messenger) message.obj);
            }
        }
    }

    private final class ProviderCallback extends MediaRouteProvider.Callback {
        ProviderCallback() {
        }

        public void onDescriptorChanged(MediaRouteProvider mediaRouteProvider, MediaRouteProviderDescriptor mediaRouteProviderDescriptor) {
            MediaRouteProviderService.this.sendDescriptorChanged(mediaRouteProviderDescriptor);
        }
    }

    private final class ClientRecord implements IBinder.DeathRecipient {
        private final SparseArray<MediaRouteProvider.RouteController> mControllers = new SparseArray<>();
        public MediaRouteDiscoveryRequest mDiscoveryRequest;
        public final Messenger mMessenger;
        public final int mVersion;

        public ClientRecord(Messenger messenger, int i) {
            this.mMessenger = messenger;
            this.mVersion = i;
        }

        public boolean register() {
            try {
                this.mMessenger.getBinder().linkToDeath(this, 0);
                return true;
            } catch (RemoteException unused) {
                binderDied();
                return false;
            }
        }

        public void dispose() {
            int size = this.mControllers.size();
            for (int i = 0; i < size; i++) {
                this.mControllers.valueAt(i).onRelease();
            }
            this.mControllers.clear();
            this.mMessenger.getBinder().unlinkToDeath(this, 0);
            setDiscoveryRequest((MediaRouteDiscoveryRequest) null);
        }

        public boolean hasMessenger(Messenger messenger) {
            return this.mMessenger.getBinder() == messenger.getBinder();
        }

        public boolean createRouteController(String str, String str2, int i) {
            MediaRouteProvider.RouteController routeController;
            if (this.mControllers.indexOfKey(i) >= 0) {
                return false;
            }
            if (str2 == null) {
                routeController = MediaRouteProviderService.this.mProvider.onCreateRouteController(str);
            } else {
                routeController = MediaRouteProviderService.this.mProvider.onCreateRouteController(str, str2);
            }
            if (routeController == null) {
                return false;
            }
            this.mControllers.put(i, routeController);
            return true;
        }

        public boolean releaseRouteController(int i) {
            MediaRouteProvider.RouteController routeController = this.mControllers.get(i);
            if (routeController == null) {
                return false;
            }
            this.mControllers.remove(i);
            routeController.onRelease();
            return true;
        }

        public MediaRouteProvider.RouteController getRouteController(int i) {
            return this.mControllers.get(i);
        }

        public boolean setDiscoveryRequest(MediaRouteDiscoveryRequest mediaRouteDiscoveryRequest) {
            if (ObjectsCompat.equals(this.mDiscoveryRequest, mediaRouteDiscoveryRequest)) {
                return false;
            }
            this.mDiscoveryRequest = mediaRouteDiscoveryRequest;
            return MediaRouteProviderService.this.updateCompositeDiscoveryRequest();
        }

        public void binderDied() {
            MediaRouteProviderService.this.mPrivateHandler.obtainMessage(1, this.mMessenger).sendToTarget();
        }

        public String toString() {
            return MediaRouteProviderService.getClientId(this.mMessenger);
        }
    }

    private static final class ReceiveHandler extends Handler {
        private final WeakReference<MediaRouteProviderService> mServiceRef;

        public ReceiveHandler(MediaRouteProviderService mediaRouteProviderService) {
            this.mServiceRef = new WeakReference<>(mediaRouteProviderService);
        }

        public void handleMessage(Message message) {
            Messenger messenger = message.replyTo;
            if (MediaRouteProviderProtocol.isValidRemoteMessenger(messenger)) {
                int i = message.what;
                int i2 = message.arg1;
                int i3 = message.arg2;
                Object obj = message.obj;
                Bundle peekData = message.peekData();
                if (!processMessage(i, messenger, i2, i3, obj, peekData)) {
                    if (MediaRouteProviderService.DEBUG) {
                        Log.d(MediaRouteProviderService.TAG, MediaRouteProviderService.getClientId(messenger) + ": Message failed, what=" + i + ", requestId=" + i2 + ", arg=" + i3 + ", obj=" + obj + ", data=" + peekData);
                    }
                    MediaRouteProviderService.sendGenericFailure(messenger, i2);
                }
            } else if (MediaRouteProviderService.DEBUG) {
                Log.d(MediaRouteProviderService.TAG, "Ignoring message without valid reply messenger.");
            }
        }

        private boolean processMessage(int i, Messenger messenger, int i2, int i3, Object obj, Bundle bundle) {
            MediaRouteProviderService mediaRouteProviderService = (MediaRouteProviderService) this.mServiceRef.get();
            int i4 = 0;
            if (mediaRouteProviderService != null) {
                switch (i) {
                    case 1:
                        return mediaRouteProviderService.onRegisterClient(messenger, i2, i3);
                    case 2:
                        return mediaRouteProviderService.onUnregisterClient(messenger, i2);
                    case 3:
                        String string = bundle.getString(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_ID);
                        String string2 = bundle.getString(MediaRouteProviderProtocol.CLIENT_DATA_ROUTE_LIBRARY_GROUP);
                        if (string != null) {
                            return mediaRouteProviderService.onCreateRouteController(messenger, i2, i3, string, string2);
                        }
                        break;
                    case 4:
                        return mediaRouteProviderService.onReleaseRouteController(messenger, i2, i3);
                    case 5:
                        return mediaRouteProviderService.onSelectRoute(messenger, i2, i3);
                    case 6:
                        if (bundle != null) {
                            i4 = bundle.getInt(MediaRouteProviderProtocol.CLIENT_DATA_UNSELECT_REASON, 0);
                        }
                        return mediaRouteProviderService.onUnselectRoute(messenger, i2, i3, i4);
                    case 7:
                        int i5 = bundle.getInt("volume", -1);
                        if (i5 >= 0) {
                            return mediaRouteProviderService.onSetRouteVolume(messenger, i2, i3, i5);
                        }
                        break;
                    case 8:
                        int i6 = bundle.getInt("volume", 0);
                        if (i6 != 0) {
                            return mediaRouteProviderService.onUpdateRouteVolume(messenger, i2, i3, i6);
                        }
                        break;
                    case 9:
                        if (obj instanceof Intent) {
                            return mediaRouteProviderService.onRouteControlRequest(messenger, i2, i3, (Intent) obj);
                        }
                        break;
                    case 10:
                        if (obj == null || (obj instanceof Bundle)) {
                            MediaRouteDiscoveryRequest fromBundle = MediaRouteDiscoveryRequest.fromBundle((Bundle) obj);
                            if (fromBundle == null || !fromBundle.isValid()) {
                                fromBundle = null;
                            }
                            return mediaRouteProviderService.onSetDiscoveryRequest(messenger, i2, fromBundle);
                        }
                }
            }
            return false;
        }
    }
}

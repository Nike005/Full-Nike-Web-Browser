package com.google.android.gms.cast;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.Display;
import androidx.core.app.NotificationCompat;
import androidx.mediarouter.media.MediaRouter;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.cast.zzdg;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class CastRemoteDisplayLocalService extends Service {
    /* access modifiers changed from: private */
    public static final zzdg zzbd = new zzdg("CastRemoteDisplayLocalService");
    private static final int zzbn = C0066R.C0068id.cast_notification_id;
    /* access modifiers changed from: private */
    public static final Object zzbo = new Object();
    /* access modifiers changed from: private */
    public static AtomicBoolean zzbp = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public static CastRemoteDisplayLocalService zzce;
    private Handler handler;
    /* access modifiers changed from: private */
    public WeakReference<Callbacks> zzbq;
    private zzb zzbr;
    private NotificationSettings zzbs;
    private Notification zzbt;
    private boolean zzbu;
    private PendingIntent zzbv;
    /* access modifiers changed from: private */
    public CastDevice zzbw;
    /* access modifiers changed from: private */
    public Display zzbx;
    /* access modifiers changed from: private */
    public Context zzby;
    /* access modifiers changed from: private */
    public ServiceConnection zzbz;
    private MediaRouter zzca;
    /* access modifiers changed from: private */
    public boolean zzcb = false;
    private CastRemoteDisplayClient zzcc;
    private final MediaRouter.Callback zzcd = new zzu(this);
    private final IBinder zzcf = new zza();
    private String zzy;

    public interface Callbacks {
        void onRemoteDisplaySessionEnded(CastRemoteDisplayLocalService castRemoteDisplayLocalService);

        void onRemoteDisplaySessionError(Status status);

        void onRemoteDisplaySessionStarted(CastRemoteDisplayLocalService castRemoteDisplayLocalService);

        void onServiceCreated(CastRemoteDisplayLocalService castRemoteDisplayLocalService);
    }

    public static final class NotificationSettings {
        /* access modifiers changed from: private */
        public Notification zzbt;
        /* access modifiers changed from: private */
        public PendingIntent zzcn;
        /* access modifiers changed from: private */
        public String zzco;
        /* access modifiers changed from: private */
        public String zzcp;

        public static final class Builder {
            private NotificationSettings zzcq = new NotificationSettings((zzu) null);

            public final NotificationSettings build() {
                if (this.zzcq.zzbt != null) {
                    if (!TextUtils.isEmpty(this.zzcq.zzco)) {
                        throw new IllegalArgumentException("notificationTitle requires using the default notification");
                    } else if (!TextUtils.isEmpty(this.zzcq.zzcp)) {
                        throw new IllegalArgumentException("notificationText requires using the default notification");
                    } else if (this.zzcq.zzcn != null) {
                        throw new IllegalArgumentException("notificationPendingIntent requires using the default notification");
                    }
                } else if (TextUtils.isEmpty(this.zzcq.zzco) && TextUtils.isEmpty(this.zzcq.zzcp) && this.zzcq.zzcn == null) {
                    throw new IllegalArgumentException("At least an argument must be provided");
                }
                return this.zzcq;
            }

            public final Builder setNotification(Notification notification) {
                Notification unused = this.zzcq.zzbt = notification;
                return this;
            }

            public final Builder setNotificationPendingIntent(PendingIntent pendingIntent) {
                PendingIntent unused = this.zzcq.zzcn = pendingIntent;
                return this;
            }

            public final Builder setNotificationText(String str) {
                String unused = this.zzcq.zzcp = str;
                return this;
            }

            public final Builder setNotificationTitle(String str) {
                String unused = this.zzcq.zzco = str;
                return this;
            }
        }

        private NotificationSettings() {
        }

        private NotificationSettings(NotificationSettings notificationSettings) {
            this.zzbt = notificationSettings.zzbt;
            this.zzcn = notificationSettings.zzcn;
            this.zzco = notificationSettings.zzco;
            this.zzcp = notificationSettings.zzcp;
        }

        /* synthetic */ NotificationSettings(NotificationSettings notificationSettings, zzu zzu) {
            this(notificationSettings);
        }

        /* synthetic */ NotificationSettings(zzu zzu) {
            this();
        }
    }

    public static class Options {
        private int zzbb = 2;

        public int getConfigPreset() {
            return this.zzbb;
        }

        public void setConfigPreset(int i) {
            this.zzbb = i;
        }
    }

    class zza extends Binder {
        zza() {
        }
    }

    private static final class zzb extends BroadcastReceiver {
        private zzb() {
        }

        /* synthetic */ zzb(zzu zzu) {
            this();
        }

        public final void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT")) {
                CastRemoteDisplayLocalService.stopService();
            } else if (intent.getAction().equals("com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED")) {
                CastRemoteDisplayLocalService.zzb(false);
            }
        }
    }

    public static CastRemoteDisplayLocalService getInstance() {
        CastRemoteDisplayLocalService castRemoteDisplayLocalService;
        synchronized (zzbo) {
            castRemoteDisplayLocalService = zzce;
        }
        return castRemoteDisplayLocalService;
    }

    protected static void setDebugEnabled() {
        zzbd.zzk(true);
    }

    public static void startService(Context context, Class<? extends CastRemoteDisplayLocalService> cls, String str, CastDevice castDevice, NotificationSettings notificationSettings, Callbacks callbacks) {
        startServiceWithOptions(context, cls, str, castDevice, new Options(), notificationSettings, callbacks);
    }

    public static void startServiceWithOptions(Context context, Class<? extends CastRemoteDisplayLocalService> cls, String str, CastDevice castDevice, Options options, NotificationSettings notificationSettings, Callbacks callbacks) {
        zzbd.mo6870d("Starting Service", new Object[0]);
        synchronized (zzbo) {
            if (zzce != null) {
                zzbd.mo6873w("An existing service had not been stopped before starting one", new Object[0]);
                zzb(true);
            }
        }
        try {
            ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context, cls), 128);
            if (serviceInfo != null) {
                if (serviceInfo.exported) {
                    throw new IllegalStateException("The service must not be exported, verify the manifest configuration");
                }
            }
            Preconditions.checkNotNull(context, "activityContext is required.");
            Preconditions.checkNotNull(cls, "serviceClass is required.");
            Preconditions.checkNotNull(str, "applicationId is required.");
            Preconditions.checkNotNull(castDevice, "device is required.");
            Preconditions.checkNotNull(options, "options is required.");
            Preconditions.checkNotNull(notificationSettings, "notificationSettings is required.");
            Preconditions.checkNotNull(callbacks, "callbacks is required.");
            if (notificationSettings.zzbt == null && notificationSettings.zzcn == null) {
                throw new IllegalArgumentException("notificationSettings: Either the notification or the notificationPendingIntent must be provided");
            } else if (zzbp.getAndSet(true)) {
                zzbd.mo6871e("Service is already being started, startService has been called twice", new Object[0]);
            } else {
                Intent intent = new Intent(context, cls);
                context.startService(intent);
                context.bindService(intent, new zzw(str, castDevice, options, notificationSettings, context, callbacks), 64);
            }
        } catch (PackageManager.NameNotFoundException unused) {
            throw new IllegalStateException("Service not found, did you forget to configure it in the manifest?");
        }
    }

    public static void stopService() {
        zzb(false);
    }

    /* access modifiers changed from: private */
    public final void zza(Display display) {
        this.zzbx = display;
        if (this.zzbu) {
            Notification zzc = zzc(true);
            this.zzbt = zzc;
            startForeground(zzbn, zzc);
        }
        if (this.zzbq.get() != null) {
            ((Callbacks) this.zzbq.get()).onRemoteDisplaySessionStarted(this);
        }
        onCreatePresentation(this.zzbx);
    }

    /* access modifiers changed from: private */
    public final void zza(NotificationSettings notificationSettings) {
        Preconditions.checkMainThread("updateNotificationSettingsInternal must be called on the main thread");
        if (this.zzbs != null) {
            if (!this.zzbu) {
                Preconditions.checkNotNull(notificationSettings.zzbt, "notification is required.");
                Notification zzb2 = notificationSettings.zzbt;
                this.zzbt = zzb2;
                Notification unused = this.zzbs.zzbt = zzb2;
            } else if (notificationSettings.zzbt == null) {
                if (notificationSettings.zzcn != null) {
                    PendingIntent unused2 = this.zzbs.zzcn = notificationSettings.zzcn;
                }
                if (!TextUtils.isEmpty(notificationSettings.zzco)) {
                    String unused3 = this.zzbs.zzco = notificationSettings.zzco;
                }
                if (!TextUtils.isEmpty(notificationSettings.zzcp)) {
                    String unused4 = this.zzbs.zzcp = notificationSettings.zzcp;
                }
                this.zzbt = zzc(true);
            } else {
                throw new IllegalStateException("Current mode is default notification, notification attribute must not be provided");
            }
            startForeground(zzbn, this.zzbt);
            return;
        }
        throw new IllegalStateException("No current notification settings to update");
    }

    /* access modifiers changed from: private */
    public final void zza(boolean z) {
        ServiceConnection serviceConnection;
        zzb("Stopping Service");
        Preconditions.checkMainThread("stopServiceInstanceInternal must be called on the main thread");
        if (!z && this.zzca != null) {
            zzb("Setting default route");
            MediaRouter mediaRouter = this.zzca;
            mediaRouter.selectRoute(mediaRouter.getDefaultRoute());
        }
        if (this.zzbr != null) {
            zzb("Unregistering notification receiver");
            unregisterReceiver(this.zzbr);
        }
        zzb("stopRemoteDisplaySession");
        zzb("stopRemoteDisplay");
        this.zzcc.stopRemoteDisplay().addOnCompleteListener(new zzaa(this));
        if (this.zzbq.get() != null) {
            ((Callbacks) this.zzbq.get()).onRemoteDisplaySessionEnded(this);
        }
        onDismissPresentation();
        zzb("Stopping the remote display Service");
        stopForeground(true);
        stopSelf();
        if (this.zzca != null) {
            Preconditions.checkMainThread("CastRemoteDisplayLocalService calls must be done on the main thread");
            zzb("removeMediaRouterCallback");
            this.zzca.removeCallback(this.zzcd);
        }
        Context context = this.zzby;
        if (!(context == null || (serviceConnection = this.zzbz) == null)) {
            try {
                context.unbindService(serviceConnection);
            } catch (IllegalArgumentException unused) {
                zzb("No need to unbind service, already unbound");
            }
            this.zzbz = null;
            this.zzby = null;
        }
        this.zzy = null;
        this.zzbt = null;
        this.zzbx = null;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0020, code lost:
        r3.zzbq = new java.lang.ref.WeakReference<>(r10);
        r3.zzy = r4;
        r3.zzbw = r5;
        r3.zzby = r8;
        r3.zzbz = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0031, code lost:
        if (r3.zzca != null) goto L_0x003d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0033, code lost:
        r3.zzca = androidx.mediarouter.media.MediaRouter.getInstance(getApplicationContext());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003d, code lost:
        r4 = new androidx.mediarouter.media.MediaRouteSelector.Builder().addControlCategory(com.google.android.gms.cast.CastMediaControlIntent.categoryForCast(r3.zzy)).build();
        zzb("addMediaRouterCallback");
        r3.zzca.addCallback(r4, r3.zzcd, 4);
        r3.zzbt = com.google.android.gms.cast.CastRemoteDisplayLocalService.NotificationSettings.zzb(r7);
        r3.zzbr = new com.google.android.gms.cast.CastRemoteDisplayLocalService.zzb((com.google.android.gms.cast.zzu) null);
        r4 = new android.content.IntentFilter();
        r4.addAction("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT");
        r4.addAction("com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED");
        registerReceiver(r3.zzbr, r4);
        r4 = new com.google.android.gms.cast.CastRemoteDisplayLocalService.NotificationSettings(r7, (com.google.android.gms.cast.zzu) null);
        r3.zzbs = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x008b, code lost:
        if (com.google.android.gms.cast.CastRemoteDisplayLocalService.NotificationSettings.zzb(r4) != null) goto L_0x0094;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x008d, code lost:
        r3.zzbu = true;
        r4 = zzc(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0094, code lost:
        r3.zzbu = false;
        r4 = com.google.android.gms.cast.CastRemoteDisplayLocalService.NotificationSettings.zzb(r3.zzbs);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x009c, code lost:
        r3.zzbt = r4;
        startForeground(zzbn, r3.zzbt);
        zzb("startRemoteDisplay");
        r4 = new android.content.Intent("com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED");
        r4.setPackage(r3.zzby.getPackageName());
        r3.zzcc.startRemoteDisplay(r5, r3.zzy, r6.getConfigPreset(), android.app.PendingIntent.getBroadcast(r3, 0, r4, 0)).addOnCompleteListener(new com.google.android.gms.cast.zzz(r3));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x00d8, code lost:
        if (r3.zzbq.get() == null) goto L_0x00e5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00da, code lost:
        ((com.google.android.gms.cast.CastRemoteDisplayLocalService.Callbacks) r3.zzbq.get()).onServiceCreated(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x00e5, code lost:
        return true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(java.lang.String r4, com.google.android.gms.cast.CastDevice r5, com.google.android.gms.cast.CastRemoteDisplayLocalService.Options r6, com.google.android.gms.cast.CastRemoteDisplayLocalService.NotificationSettings r7, android.content.Context r8, android.content.ServiceConnection r9, com.google.android.gms.cast.CastRemoteDisplayLocalService.Callbacks r10) {
        /*
            r3 = this;
            java.lang.String r0 = "startRemoteDisplaySession"
            r3.zzb((java.lang.String) r0)
            java.lang.String r0 = "Starting the Cast Remote Display must be done on the main thread"
            com.google.android.gms.common.internal.Preconditions.checkMainThread(r0)
            java.lang.Object r0 = zzbo
            monitor-enter(r0)
            com.google.android.gms.cast.CastRemoteDisplayLocalService r1 = zzce     // Catch:{ all -> 0x00e6 }
            r2 = 0
            if (r1 == 0) goto L_0x001d
            com.google.android.gms.internal.cast.zzdg r4 = zzbd     // Catch:{ all -> 0x00e6 }
            java.lang.String r5 = "An existing service had not been stopped before starting one"
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ all -> 0x00e6 }
            r4.mo6873w(r5, r6)     // Catch:{ all -> 0x00e6 }
            monitor-exit(r0)     // Catch:{ all -> 0x00e6 }
            return r2
        L_0x001d:
            zzce = r3     // Catch:{ all -> 0x00e6 }
            monitor-exit(r0)     // Catch:{ all -> 0x00e6 }
            java.lang.ref.WeakReference r0 = new java.lang.ref.WeakReference
            r0.<init>(r10)
            r3.zzbq = r0
            r3.zzy = r4
            r3.zzbw = r5
            r3.zzby = r8
            r3.zzbz = r9
            androidx.mediarouter.media.MediaRouter r4 = r3.zzca
            if (r4 != 0) goto L_0x003d
            android.content.Context r4 = r3.getApplicationContext()
            androidx.mediarouter.media.MediaRouter r4 = androidx.mediarouter.media.MediaRouter.getInstance(r4)
            r3.zzca = r4
        L_0x003d:
            androidx.mediarouter.media.MediaRouteSelector$Builder r4 = new androidx.mediarouter.media.MediaRouteSelector$Builder
            r4.<init>()
            java.lang.String r8 = r3.zzy
            java.lang.String r8 = com.google.android.gms.cast.CastMediaControlIntent.categoryForCast((java.lang.String) r8)
            androidx.mediarouter.media.MediaRouteSelector$Builder r4 = r4.addControlCategory(r8)
            androidx.mediarouter.media.MediaRouteSelector r4 = r4.build()
            java.lang.String r8 = "addMediaRouterCallback"
            r3.zzb((java.lang.String) r8)
            androidx.mediarouter.media.MediaRouter r8 = r3.zzca
            androidx.mediarouter.media.MediaRouter$Callback r9 = r3.zzcd
            r10 = 4
            r8.addCallback(r4, r9, r10)
            android.app.Notification r4 = r7.zzbt
            r3.zzbt = r4
            com.google.android.gms.cast.CastRemoteDisplayLocalService$zzb r4 = new com.google.android.gms.cast.CastRemoteDisplayLocalService$zzb
            r8 = 0
            r4.<init>(r8)
            r3.zzbr = r4
            android.content.IntentFilter r4 = new android.content.IntentFilter
            r4.<init>()
            java.lang.String r9 = "com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT"
            r4.addAction(r9)
            java.lang.String r9 = "com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED"
            r4.addAction(r9)
            com.google.android.gms.cast.CastRemoteDisplayLocalService$zzb r9 = r3.zzbr
            r3.registerReceiver(r9, r4)
            com.google.android.gms.cast.CastRemoteDisplayLocalService$NotificationSettings r4 = new com.google.android.gms.cast.CastRemoteDisplayLocalService$NotificationSettings
            r4.<init>(r7, r8)
            r3.zzbs = r4
            android.app.Notification r4 = r4.zzbt
            r7 = 1
            if (r4 != 0) goto L_0x0094
            r3.zzbu = r7
            android.app.Notification r4 = r3.zzc((boolean) r2)
            goto L_0x009c
        L_0x0094:
            r3.zzbu = r2
            com.google.android.gms.cast.CastRemoteDisplayLocalService$NotificationSettings r4 = r3.zzbs
            android.app.Notification r4 = r4.zzbt
        L_0x009c:
            r3.zzbt = r4
            int r4 = zzbn
            android.app.Notification r8 = r3.zzbt
            r3.startForeground(r4, r8)
            java.lang.String r4 = "startRemoteDisplay"
            r3.zzb((java.lang.String) r4)
            android.content.Intent r4 = new android.content.Intent
            java.lang.String r8 = "com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED"
            r4.<init>(r8)
            android.content.Context r8 = r3.zzby
            java.lang.String r8 = r8.getPackageName()
            r4.setPackage(r8)
            android.app.PendingIntent r4 = android.app.PendingIntent.getBroadcast(r3, r2, r4, r2)
            com.google.android.gms.cast.CastRemoteDisplayClient r8 = r3.zzcc
            java.lang.String r9 = r3.zzy
            int r6 = r6.getConfigPreset()
            com.google.android.gms.tasks.Task r4 = r8.startRemoteDisplay(r5, r9, r6, r4)
            com.google.android.gms.cast.zzz r5 = new com.google.android.gms.cast.zzz
            r5.<init>(r3)
            r4.addOnCompleteListener(r5)
            java.lang.ref.WeakReference<com.google.android.gms.cast.CastRemoteDisplayLocalService$Callbacks> r4 = r3.zzbq
            java.lang.Object r4 = r4.get()
            if (r4 == 0) goto L_0x00e5
            java.lang.ref.WeakReference<com.google.android.gms.cast.CastRemoteDisplayLocalService$Callbacks> r4 = r3.zzbq
            java.lang.Object r4 = r4.get()
            com.google.android.gms.cast.CastRemoteDisplayLocalService$Callbacks r4 = (com.google.android.gms.cast.CastRemoteDisplayLocalService.Callbacks) r4
            r4.onServiceCreated(r3)
        L_0x00e5:
            return r7
        L_0x00e6:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00e6 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.CastRemoteDisplayLocalService.zza(java.lang.String, com.google.android.gms.cast.CastDevice, com.google.android.gms.cast.CastRemoteDisplayLocalService$Options, com.google.android.gms.cast.CastRemoteDisplayLocalService$NotificationSettings, android.content.Context, android.content.ServiceConnection, com.google.android.gms.cast.CastRemoteDisplayLocalService$Callbacks):boolean");
    }

    /* access modifiers changed from: private */
    public final void zzb(String str) {
        zzbd.mo6870d("[Instance: %s] %s", this, str);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0029, code lost:
        if (r1.handler == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0033, code lost:
        if (android.os.Looper.myLooper() == android.os.Looper.getMainLooper()) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0035, code lost:
        r1.handler.post(new com.google.android.gms.cast.zzx(r1, r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003f, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0040, code lost:
        r1.zza(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void zzb(boolean r4) {
        /*
            com.google.android.gms.internal.cast.zzdg r0 = zzbd
            java.lang.String r1 = "Stopping Service"
            r2 = 0
            java.lang.Object[] r3 = new java.lang.Object[r2]
            r0.mo6870d(r1, r3)
            java.util.concurrent.atomic.AtomicBoolean r0 = zzbp
            r0.set(r2)
            java.lang.Object r0 = zzbo
            monitor-enter(r0)
            com.google.android.gms.cast.CastRemoteDisplayLocalService r1 = zzce     // Catch:{ all -> 0x0044 }
            if (r1 != 0) goto L_0x0021
            com.google.android.gms.internal.cast.zzdg r4 = zzbd     // Catch:{ all -> 0x0044 }
            java.lang.String r1 = "Service is already being stopped"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0044 }
            r4.mo6871e(r1, r2)     // Catch:{ all -> 0x0044 }
            monitor-exit(r0)     // Catch:{ all -> 0x0044 }
            return
        L_0x0021:
            com.google.android.gms.cast.CastRemoteDisplayLocalService r1 = zzce     // Catch:{ all -> 0x0044 }
            r2 = 0
            zzce = r2     // Catch:{ all -> 0x0044 }
            monitor-exit(r0)     // Catch:{ all -> 0x0044 }
            android.os.Handler r0 = r1.handler
            if (r0 == 0) goto L_0x0043
            android.os.Looper r0 = android.os.Looper.myLooper()
            android.os.Looper r2 = android.os.Looper.getMainLooper()
            if (r0 == r2) goto L_0x0040
            android.os.Handler r0 = r1.handler
            com.google.android.gms.cast.zzx r2 = new com.google.android.gms.cast.zzx
            r2.<init>(r1, r4)
            r0.post(r2)
            return
        L_0x0040:
            r1.zza((boolean) r4)
        L_0x0043:
            return
        L_0x0044:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0044 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.CastRemoteDisplayLocalService.zzb(boolean):void");
    }

    private final Notification zzc(boolean z) {
        int i;
        int i2;
        zzb("createDefaultNotification");
        String zzc = this.zzbs.zzco;
        String zzd = this.zzbs.zzcp;
        if (z) {
            i = C0066R.string.cast_notification_connected_message;
            i2 = C0066R.C0067drawable.cast_ic_notification_on;
        } else {
            i = C0066R.string.cast_notification_connecting_message;
            i2 = C0066R.C0067drawable.cast_ic_notification_connecting;
        }
        if (TextUtils.isEmpty(zzc)) {
            zzc = (String) getPackageManager().getApplicationLabel(getApplicationInfo());
        }
        if (TextUtils.isEmpty(zzd)) {
            zzd = getString(i, new Object[]{this.zzbw.getFriendlyName()});
        }
        NotificationCompat.Builder ongoing = new NotificationCompat.Builder(this, "cast_remote_display_local_service").setContentTitle(zzc).setContentText(zzd).setContentIntent(this.zzbs.zzcn).setSmallIcon(i2).setOngoing(true);
        String string = getString(C0066R.string.cast_notification_disconnect);
        if (this.zzbv == null) {
            Intent intent = new Intent("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT");
            intent.setPackage(this.zzby.getPackageName());
            this.zzbv = PendingIntent.getBroadcast(this, 0, intent, 134217728);
        }
        return ongoing.addAction(17301560, string, this.zzbv).build();
    }

    /* access modifiers changed from: private */
    public final void zzc(String str) {
        zzbd.mo6871e("[Instance: %s] %s", this, str);
    }

    /* access modifiers changed from: private */
    public final void zzd() {
        if (this.zzbq.get() != null) {
            ((Callbacks) this.zzbq.get()).onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_SERVICE_CREATION_FAILED));
        }
        stopService();
    }

    /* access modifiers changed from: protected */
    public Display getDisplay() {
        return this.zzbx;
    }

    public IBinder onBind(Intent intent) {
        zzb("onBind");
        return this.zzcf;
    }

    public void onCreate() {
        zzb("onCreate");
        super.onCreate();
        Handler handler2 = new Handler(getMainLooper());
        this.handler = handler2;
        handler2.postDelayed(new zzv(this), 100);
        if (this.zzcc == null) {
            this.zzcc = CastRemoteDisplay.getClient(this);
        }
        if (PlatformVersion.isAtLeastO()) {
            NotificationChannel notificationChannel = new NotificationChannel("cast_remote_display_local_service", getString(C0066R.string.cast_notification_default_channel_name), 2);
            notificationChannel.setShowBadge(false);
            ((NotificationManager) getSystemService(NotificationManager.class)).createNotificationChannel(notificationChannel);
        }
    }

    public abstract void onCreatePresentation(Display display);

    public abstract void onDismissPresentation();

    public int onStartCommand(Intent intent, int i, int i2) {
        zzb("onStartCommand");
        this.zzcb = true;
        return 2;
    }

    public void updateNotificationSettings(NotificationSettings notificationSettings) {
        Preconditions.checkNotNull(notificationSettings, "notificationSettings is required.");
        Preconditions.checkNotNull(this.handler, "Service is not ready yet.");
        this.handler.post(new zzy(this, notificationSettings));
    }
}

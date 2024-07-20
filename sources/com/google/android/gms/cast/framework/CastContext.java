package com.google.android.gms.cast.framework;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.KeyEvent;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.cast.zzch;
import com.google.android.gms.internal.cast.zzdg;
import com.google.android.gms.internal.cast.zze;
import com.google.android.gms.internal.cast.zzf;
import com.google.android.gms.internal.cast.zzj;
import com.google.android.gms.internal.cast.zzw;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CastContext {
    public static final String OPTIONS_PROVIDER_CLASS_NAME_KEY = "com.google.android.gms.cast.framework.OPTIONS_PROVIDER_CLASS_NAME";
    private static final zzdg zzbd = new zzdg("CastContext");
    private static CastContext zzgr;
    private final Context zzgs;
    private final zzj zzgt;
    private final SessionManager zzgu;
    private final zze zzgv;
    private final PrecacheManager zzgw;
    private final MediaNotificationManager zzgx;
    private final CastOptions zzgy;
    private zzw zzgz = new zzw(MediaRouter.getInstance(this.zzgs));
    private zzf zzha;
    private final List<SessionProvider> zzhb;

    private CastContext(Context context, CastOptions castOptions, List<SessionProvider> list) {
        zzp zzp;
        zzv zzv;
        this.zzgs = context.getApplicationContext();
        this.zzgy = castOptions;
        this.zzhb = list;
        zzp();
        zzj zza = zze.zza(this.zzgs, castOptions, (zzj) this.zzgz, zzo());
        this.zzgt = zza;
        PrecacheManager precacheManager = null;
        try {
            zzp = zza.zzw();
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "getDiscoveryManagerImpl", zzj.class.getSimpleName());
            zzp = null;
        }
        this.zzgv = zzp == null ? null : new zze(zzp);
        try {
            zzv = this.zzgt.zzv();
        } catch (RemoteException e2) {
            zzbd.zza(e2, "Unable to call %s on %s.", "getSessionManagerImpl", zzj.class.getSimpleName());
            zzv = null;
        }
        SessionManager sessionManager = zzv == null ? null : new SessionManager(zzv, this.zzgs);
        this.zzgu = sessionManager;
        this.zzgx = new MediaNotificationManager(sessionManager);
        SessionManager sessionManager2 = this.zzgu;
        this.zzgw = sessionManager2 != null ? new PrecacheManager(this.zzgy, sessionManager2, new zzch(this.zzgs)) : precacheManager;
    }

    public static CastContext getSharedInstance() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return zzgr;
    }

    public static CastContext getSharedInstance(Context context) throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzgr == null) {
            OptionsProvider zzc = zzc(context.getApplicationContext());
            zzgr = new CastContext(context, zzc.getCastOptions(context.getApplicationContext()), zzc.getAdditionalSessionProviders(context.getApplicationContext()));
        }
        return zzgr;
    }

    private static boolean zza(CastSession castSession, double d, boolean z) {
        if (z) {
            try {
                double volume = castSession.getVolume() + d;
                if (volume > 1.0d) {
                    volume = 1.0d;
                }
                castSession.setVolume(volume);
            } catch (IOException | IllegalStateException e) {
                zzbd.mo6871e("Unable to call CastSession.setVolume(double).", e);
            }
        }
        return true;
    }

    public static CastContext zzb(Context context) throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return getSharedInstance(context);
        } catch (RuntimeException e) {
            zzbd.mo6871e("Failed to load module from Google Play services. Cast will not work properly. Might due to outdated Google Play services. Ignoring this failure silently.", e);
            return null;
        }
    }

    private static OptionsProvider zzc(Context context) throws IllegalStateException {
        try {
            Bundle bundle = Wrappers.packageManager(context).getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null) {
                zzbd.mo6871e("Bundle is null", new Object[0]);
            }
            String string = bundle.getString(OPTIONS_PROVIDER_CLASS_NAME_KEY);
            if (string != null) {
                return (OptionsProvider) Class.forName(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            }
            throw new IllegalStateException("The fully qualified name of the implementation of OptionsProvider must be provided as a metadata in the AndroidManifest.xml with key com.google.android.gms.cast.framework.OPTIONS_PROVIDER_CLASS_NAME.");
        } catch (PackageManager.NameNotFoundException | ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | NullPointerException | InvocationTargetException e) {
            throw new IllegalStateException("Failed to initialize CastContext.", e);
        }
    }

    private final Map<String, IBinder> zzo() {
        HashMap hashMap = new HashMap();
        zzf zzf = this.zzha;
        if (zzf != null) {
            hashMap.put(zzf.getCategory(), this.zzha.zzai());
        }
        List<SessionProvider> list = this.zzhb;
        if (list != null) {
            for (SessionProvider next : list) {
                Preconditions.checkNotNull(next, "Additional SessionProvider must not be null.");
                String checkNotEmpty = Preconditions.checkNotEmpty(next.getCategory(), "Category for SessionProvider must not be null or empty string.");
                Preconditions.checkArgument(!hashMap.containsKey(checkNotEmpty), String.format("SessionProvider for category %s already added", new Object[]{checkNotEmpty}));
                hashMap.put(checkNotEmpty, next.zzai());
            }
        }
        return hashMap;
    }

    private final void zzp() {
        this.zzha = !TextUtils.isEmpty(this.zzgy.getReceiverApplicationId()) ? new zzf(this.zzgs, this.zzgy, this.zzgz) : null;
    }

    @Deprecated
    public void addAppVisibilityListener(AppVisibilityListener appVisibilityListener) throws IllegalStateException, NullPointerException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        Preconditions.checkNotNull(appVisibilityListener);
        try {
            this.zzgt.zza(new zza(appVisibilityListener));
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "addVisibilityChangeListener", zzj.class.getSimpleName());
        }
    }

    public void addCastStateListener(CastStateListener castStateListener) throws IllegalStateException, NullPointerException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        Preconditions.checkNotNull(castStateListener);
        this.zzgu.addCastStateListener(castStateListener);
    }

    public CastOptions getCastOptions() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzgy;
    }

    public int getCastState() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzgu.getCastState();
    }

    public MediaNotificationManager getMediaNotificationManager() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzgx;
    }

    public MediaRouteSelector getMergedSelector() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return MediaRouteSelector.fromBundle(this.zzgt.zzu());
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "getMergedSelectorAsBundle", zzj.class.getSimpleName());
            return null;
        }
    }

    public PrecacheManager getPrecacheManager() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzgw;
    }

    public SessionManager getSessionManager() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzgu;
    }

    public boolean isAppVisible() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return this.zzgt.isAppVisible();
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "isApplicationVisible", zzj.class.getSimpleName());
            return false;
        }
    }

    public boolean onDispatchVolumeKeyEventBeforeJellyBean(KeyEvent keyEvent) {
        CastSession currentCastSession;
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (PlatformVersion.isAtLeastJellyBean() || (currentCastSession = this.zzgu.getCurrentCastSession()) == null || !currentCastSession.isConnected()) {
            return false;
        }
        double volumeDeltaBeforeIceCreamSandwich = getCastOptions().getVolumeDeltaBeforeIceCreamSandwich();
        boolean z = keyEvent.getAction() == 0;
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 24) {
            zza(currentCastSession, volumeDeltaBeforeIceCreamSandwich, z);
            return true;
        } else if (keyCode != 25) {
            return false;
        } else {
            zza(currentCastSession, -volumeDeltaBeforeIceCreamSandwich, z);
            return true;
        }
    }

    @Deprecated
    public void removeAppVisibilityListener(AppVisibilityListener appVisibilityListener) throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (appVisibilityListener != null) {
            try {
                this.zzgt.zzb(new zza(appVisibilityListener));
            } catch (RemoteException e) {
                zzbd.zza(e, "Unable to call %s on %s.", "addVisibilityChangeListener", zzj.class.getSimpleName());
            }
        }
    }

    public void removeCastStateListener(CastStateListener castStateListener) throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (castStateListener != null) {
            this.zzgu.removeCastStateListener(castStateListener);
        }
    }

    public void setReceiverApplicationId(String str) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (!TextUtils.equals(str, this.zzgy.getReceiverApplicationId())) {
            this.zzgy.setReceiverApplicationId(str);
            zzp();
            try {
                this.zzgt.zza(str, zzo());
            } catch (RemoteException e) {
                zzbd.zza(e, "Unable to call %s on %s.", "setReceiverApplicationId", zzj.class.getSimpleName());
            }
            CastButtonFactory.zza(this.zzgs);
        }
    }

    public final zze zzq() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzgv;
    }

    public final IObjectWrapper zzr() {
        try {
            return this.zzgt.zzx();
        } catch (RemoteException e) {
            zzbd.zza(e, "Unable to call %s on %s.", "getWrappedThis", zzj.class.getSimpleName());
            return null;
        }
    }
}

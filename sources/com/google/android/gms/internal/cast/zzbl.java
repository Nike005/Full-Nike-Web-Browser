package com.google.android.gms.internal.cast;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.cast.games.GameManagerState;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzbl extends zzcg {
    private static final String NAMESPACE = zzcu.zzp("com.google.cast.games");
    /* access modifiers changed from: private */
    public static final zzdg zzbd = new zzdg("GameManagerChannel");
    /* access modifiers changed from: private */
    public final Cast.CastApi zzhl;
    /* access modifiers changed from: private */
    public final GoogleApiClient zznm;
    private final Map<String, String> zzsu = new ConcurrentHashMap();
    private final SharedPreferences zzsv;
    private final String zzsw;
    /* access modifiers changed from: private */
    public zzby zzsx;
    private boolean zzsy = false;
    private GameManagerState zzsz;
    private GameManagerState zzta;
    private String zztb;
    private JSONObject zztc;
    private long zztd = 0;
    private GameManagerClient.Listener zzte;
    private final Clock zztf;
    /* access modifiers changed from: private */
    public String zztg;

    public zzbl(GoogleApiClient googleApiClient, String str, Cast.CastApi castApi) throws IllegalArgumentException, IllegalStateException {
        super(NAMESPACE, "CastGameManagerChannel", (String) null);
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("castSessionId cannot be null.");
        } else if (googleApiClient == null || !googleApiClient.isConnected() || !googleApiClient.hasConnectedApi(Cast.API)) {
            throw new IllegalArgumentException("googleApiClient needs to be connected and contain the Cast.API API.");
        } else {
            this.zztf = DefaultClock.getInstance();
            this.zzsw = str;
            this.zzhl = castApi;
            this.zznm = googleApiClient;
            Context applicationContext = googleApiClient.getContext().getApplicationContext();
            this.zzsv = applicationContext.getApplicationContext().getSharedPreferences(String.format(Locale.ROOT, "%s.%s", new Object[]{applicationContext.getPackageName(), "game_manager_channel_data"}), 0);
            this.zzta = null;
            this.zzsz = new zzca(0, 0, "", (JSONObject) null, new ArrayList(), "", -1);
        }
    }

    private final synchronized boolean isInitialized() {
        return this.zzsx != null;
    }

    private final JSONObject zza(long j, String str, int i, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("requestId", j);
            jSONObject2.put("type", i);
            jSONObject2.put("extraMessageData", jSONObject);
            jSONObject2.put("playerId", str);
            jSONObject2.put("playerToken", zzm(str));
            return jSONObject2;
        } catch (JSONException e) {
            zzbd.mo6873w("JSONException when trying to create a message: %s", e.getMessage());
            return null;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0088, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized void zza(com.google.android.gms.internal.cast.zzbz r10) {
        /*
            r9 = this;
            monitor-enter(r9)
            int r0 = r10.zztx     // Catch:{ all -> 0x0089 }
            r1 = 1
            if (r0 != r1) goto L_0x0007
            goto L_0x0008
        L_0x0007:
            r1 = 0
        L_0x0008:
            com.google.android.gms.cast.games.GameManagerState r0 = r9.zzsz     // Catch:{ all -> 0x0089 }
            r9.zzta = r0     // Catch:{ all -> 0x0089 }
            if (r1 == 0) goto L_0x0016
            com.google.android.gms.internal.cast.zzby r0 = r10.zzuj     // Catch:{ all -> 0x0089 }
            if (r0 == 0) goto L_0x0016
            com.google.android.gms.internal.cast.zzby r0 = r10.zzuj     // Catch:{ all -> 0x0089 }
            r9.zzsx = r0     // Catch:{ all -> 0x0089 }
        L_0x0016:
            boolean r0 = r9.isInitialized()     // Catch:{ all -> 0x0089 }
            if (r0 != 0) goto L_0x001e
            monitor-exit(r9)
            return
        L_0x001e:
            java.util.ArrayList r6 = new java.util.ArrayList     // Catch:{ all -> 0x0089 }
            r6.<init>()     // Catch:{ all -> 0x0089 }
            java.util.List<com.google.android.gms.internal.cast.zzcc> r0 = r10.zzud     // Catch:{ all -> 0x0089 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0089 }
        L_0x0029:
            boolean r1 = r0.hasNext()     // Catch:{ all -> 0x0089 }
            if (r1 == 0) goto L_0x0050
            java.lang.Object r1 = r0.next()     // Catch:{ all -> 0x0089 }
            com.google.android.gms.internal.cast.zzcc r1 = (com.google.android.gms.internal.cast.zzcc) r1     // Catch:{ all -> 0x0089 }
            java.lang.String r2 = r1.getPlayerId()     // Catch:{ all -> 0x0089 }
            com.google.android.gms.internal.cast.zzcb r3 = new com.google.android.gms.internal.cast.zzcb     // Catch:{ all -> 0x0089 }
            int r4 = r1.getPlayerState()     // Catch:{ all -> 0x0089 }
            org.json.JSONObject r1 = r1.getPlayerData()     // Catch:{ all -> 0x0089 }
            java.util.Map<java.lang.String, java.lang.String> r5 = r9.zzsu     // Catch:{ all -> 0x0089 }
            boolean r5 = r5.containsKey(r2)     // Catch:{ all -> 0x0089 }
            r3.<init>(r2, r4, r1, r5)     // Catch:{ all -> 0x0089 }
            r6.add(r3)     // Catch:{ all -> 0x0089 }
            goto L_0x0029
        L_0x0050:
            com.google.android.gms.internal.cast.zzca r0 = new com.google.android.gms.internal.cast.zzca     // Catch:{ all -> 0x0089 }
            int r2 = r10.zzuc     // Catch:{ all -> 0x0089 }
            int r3 = r10.zzub     // Catch:{ all -> 0x0089 }
            java.lang.String r4 = r10.zzuf     // Catch:{ all -> 0x0089 }
            org.json.JSONObject r5 = r10.zzue     // Catch:{ all -> 0x0089 }
            com.google.android.gms.internal.cast.zzby r1 = r9.zzsx     // Catch:{ all -> 0x0089 }
            java.lang.String r7 = r1.zzck()     // Catch:{ all -> 0x0089 }
            com.google.android.gms.internal.cast.zzby r1 = r9.zzsx     // Catch:{ all -> 0x0089 }
            int r8 = r1.getMaxPlayers()     // Catch:{ all -> 0x0089 }
            r1 = r0
            r1.<init>(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x0089 }
            r9.zzsz = r0     // Catch:{ all -> 0x0089 }
            java.lang.String r1 = r10.zzug     // Catch:{ all -> 0x0089 }
            com.google.android.gms.cast.games.PlayerInfo r0 = r0.getPlayer(r1)     // Catch:{ all -> 0x0089 }
            if (r0 == 0) goto L_0x0087
            boolean r0 = r0.isControllable()     // Catch:{ all -> 0x0089 }
            if (r0 == 0) goto L_0x0087
            int r0 = r10.zztx     // Catch:{ all -> 0x0089 }
            r1 = 2
            if (r0 != r1) goto L_0x0087
            java.lang.String r0 = r10.zzug     // Catch:{ all -> 0x0089 }
            r9.zztb = r0     // Catch:{ all -> 0x0089 }
            org.json.JSONObject r10 = r10.zzua     // Catch:{ all -> 0x0089 }
            r9.zztc = r10     // Catch:{ all -> 0x0089 }
        L_0x0087:
            monitor-exit(r9)
            return
        L_0x0089:
            r10 = move-exception
            monitor-exit(r9)
            goto L_0x008d
        L_0x008c:
            throw r10
        L_0x008d:
            goto L_0x008c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.zzbl.zza(com.google.android.gms.internal.cast.zzbz):void");
    }

    /* access modifiers changed from: private */
    public final void zza(String str, int i, JSONObject jSONObject, zzdm zzdm) {
        long j = this.zztd + 1;
        this.zztd = j;
        JSONObject zza = zza(j, str, i, jSONObject);
        if (zza == null) {
            zzdm.zza(-1, 2001, (Object) null);
            zzbd.mo6873w("Not sending request because it was invalid.", new Object[0]);
            return;
        }
        zzdn zzdn = new zzdn(30000);
        zzdn.zza(j, zzdm);
        zza(zzdn);
        this.zzhl.sendMessage(this.zznm, getNamespace(), zza.toString()).setResultCallback(new zzbq(this, j));
    }

    private final void zzb(long j, int i, Object obj) {
        List<zzdn> zzcn = zzcn();
        synchronized (zzcn) {
            Iterator<zzdn> it = zzcn.iterator();
            while (it.hasNext()) {
                if (it.next().zzc(j, i, obj)) {
                    it.remove();
                }
            }
        }
    }

    private final synchronized void zzcg() throws IllegalStateException {
        if (!isInitialized()) {
            throw new IllegalStateException("Attempted to perform an operation on the GameManagerChannel before it is initialized.");
        } else if (isDisposed()) {
            throw new IllegalStateException("Attempted to perform an operation on the GameManagerChannel after it has been disposed.");
        }
    }

    /* access modifiers changed from: private */
    public final synchronized void zzch() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("castSessionId", this.zzsw);
            jSONObject.put("playerTokenMap", new JSONObject(this.zzsu));
            this.zzsv.edit().putString("save_data", jSONObject.toString()).commit();
        } catch (JSONException e) {
            zzbd.mo6873w("Error while saving data: %s", e.getMessage());
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0046, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void zzci() {
        /*
            r5 = this;
            monitor-enter(r5)
            android.content.SharedPreferences r0 = r5.zzsv     // Catch:{ all -> 0x005b }
            java.lang.String r1 = "save_data"
            r2 = 0
            java.lang.String r0 = r0.getString(r1, r2)     // Catch:{ all -> 0x005b }
            if (r0 != 0) goto L_0x000e
            monitor-exit(r5)
            return
        L_0x000e:
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0047 }
            r1.<init>(r0)     // Catch:{ JSONException -> 0x0047 }
            java.lang.String r0 = "castSessionId"
            java.lang.String r0 = r1.getString(r0)     // Catch:{ JSONException -> 0x0047 }
            java.lang.String r2 = r5.zzsw     // Catch:{ JSONException -> 0x0047 }
            boolean r0 = r2.equals(r0)     // Catch:{ JSONException -> 0x0047 }
            if (r0 == 0) goto L_0x0045
            java.lang.String r0 = "playerTokenMap"
            org.json.JSONObject r0 = r1.getJSONObject(r0)     // Catch:{ JSONException -> 0x0047 }
            java.util.Iterator r1 = r0.keys()     // Catch:{ JSONException -> 0x0047 }
        L_0x002b:
            boolean r2 = r1.hasNext()     // Catch:{ JSONException -> 0x0047 }
            if (r2 == 0) goto L_0x0041
            java.lang.Object r2 = r1.next()     // Catch:{ JSONException -> 0x0047 }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ JSONException -> 0x0047 }
            java.util.Map<java.lang.String, java.lang.String> r3 = r5.zzsu     // Catch:{ JSONException -> 0x0047 }
            java.lang.String r4 = r0.getString(r2)     // Catch:{ JSONException -> 0x0047 }
            r3.put(r2, r4)     // Catch:{ JSONException -> 0x0047 }
            goto L_0x002b
        L_0x0041:
            r0 = 0
            r5.zztd = r0     // Catch:{ JSONException -> 0x0047 }
        L_0x0045:
            monitor-exit(r5)
            return
        L_0x0047:
            r0 = move-exception
            com.google.android.gms.internal.cast.zzdg r1 = zzbd     // Catch:{ all -> 0x005b }
            java.lang.String r2 = "Error while loading data: %s"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch:{ all -> 0x005b }
            r4 = 0
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x005b }
            r3[r4] = r0     // Catch:{ all -> 0x005b }
            r1.mo6873w(r2, r3)     // Catch:{ all -> 0x005b }
            monitor-exit(r5)
            return
        L_0x005b:
            r0 = move-exception
            monitor-exit(r5)
            goto L_0x005f
        L_0x005e:
            throw r0
        L_0x005f:
            goto L_0x005e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.zzbl.zzci():void");
    }

    private final synchronized String zzm(String str) throws IllegalStateException {
        if (str == null) {
            return null;
        }
        return this.zzsu.get(str);
    }

    public final synchronized void dispose() throws IllegalStateException {
        if (!this.zzsy) {
            this.zzsz = null;
            this.zzta = null;
            this.zztb = null;
            this.zztc = null;
            this.zzsy = true;
            try {
                this.zzhl.removeMessageReceivedCallbacks(this.zznm, getNamespace());
            } catch (IOException e) {
                zzbd.mo6873w("Exception while detaching game manager channel.", e);
            }
        }
    }

    public final synchronized GameManagerState getCurrentState() throws IllegalStateException {
        zzcg();
        return this.zzsz;
    }

    public final synchronized String getLastUsedPlayerId() throws IllegalStateException {
        zzcg();
        return this.zztg;
    }

    public final synchronized boolean isDisposed() {
        return this.zzsy;
    }

    public final synchronized void sendGameMessage(String str, JSONObject jSONObject) throws IllegalStateException {
        zzcg();
        long j = this.zztd + 1;
        this.zztd = j;
        JSONObject zza = zza(j, str, 7, jSONObject);
        if (zza != null) {
            this.zzhl.sendMessage(this.zznm, getNamespace(), zza.toString());
        }
    }

    public final synchronized PendingResult<GameManagerClient.GameManagerResult> sendGameRequest(String str, JSONObject jSONObject) throws IllegalStateException {
        zzcg();
        return this.zznm.execute(new zzbp(this, str, jSONObject));
    }

    public final synchronized void setListener(GameManagerClient.Listener listener) {
        this.zzte = listener;
    }

    public final synchronized PendingResult<GameManagerClient.GameManagerInstanceResult> zza(GameManagerClient gameManagerClient) throws IllegalArgumentException {
        return this.zznm.execute(new zzbm(this, gameManagerClient));
    }

    public final synchronized PendingResult<GameManagerClient.GameManagerResult> zza(String str, int i, JSONObject jSONObject) throws IllegalStateException {
        zzcg();
        return this.zznm.execute(new zzbo(this, i, str, jSONObject));
    }

    public final void zza(long j, int i) {
        zzb(j, i, (Object) null);
    }

    public final void zzn(String str) {
        String str2;
        int i = 0;
        zzbd.mo6870d("message received: %s", str);
        try {
            zzbz zzh = zzbz.zzh(new JSONObject(str));
            if (zzh == null) {
                zzbd.mo6873w("Could not parse game manager message from string: %s", str);
            } else if ((isInitialized() || zzh.zzuj != null) && !isDisposed()) {
                boolean z = zzh.zztx == 1;
                if (z && !TextUtils.isEmpty(zzh.zzui)) {
                    this.zzsu.put(zzh.zzug, zzh.zzui);
                    zzch();
                }
                if (zzh.zzty == 0) {
                    zza(zzh);
                } else {
                    zzbd.mo6873w("Not updating from game message because the message contains error code: %d", Integer.valueOf(zzh.zzty));
                }
                int i2 = zzh.zzty;
                if (i2 != 0) {
                    if (i2 == 1) {
                        i = 2001;
                    } else if (i2 == 2) {
                        i = 2003;
                    } else if (i2 == 3) {
                        i = GameManagerClient.STATUS_INCORRECT_VERSION;
                    } else if (i2 != 4) {
                        zzdg zzdg = zzbd;
                        StringBuilder sb = new StringBuilder(53);
                        sb.append("Unknown GameManager protocol status code: ");
                        sb.append(i2);
                        zzdg.mo6873w(sb.toString(), new Object[0]);
                        i = 13;
                    } else {
                        i = GameManagerClient.STATUS_TOO_MANY_PLAYERS;
                    }
                }
                if (z) {
                    zzb(zzh.zzuh, i, zzh);
                }
                if (isInitialized() && i == 0) {
                    if (this.zzte != null) {
                        GameManagerState gameManagerState = this.zzta;
                        if (gameManagerState != null && !this.zzsz.equals(gameManagerState)) {
                            this.zzte.onStateChanged(this.zzsz, this.zzta);
                        }
                        JSONObject jSONObject = this.zztc;
                        if (!(jSONObject == null || (str2 = this.zztb) == null)) {
                            this.zzte.onGameMessageReceived(str2, jSONObject);
                        }
                    }
                    this.zzta = null;
                    this.zztb = null;
                    this.zztc = null;
                }
            }
        } catch (JSONException e) {
            zzbd.mo6873w("Message is malformed (%s); ignoring: %s", e.getMessage(), str);
        }
    }
}

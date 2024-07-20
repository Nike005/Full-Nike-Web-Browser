package com.google.android.gms.internal.cast;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzbz {
    private static final zzdg zzbd = new zzdg("GameManagerMessage");
    protected final int zztx;
    protected final int zzty;
    protected final String zztz;
    protected final JSONObject zzua;
    protected final int zzub;
    protected final int zzuc;
    protected final List<zzcc> zzud;
    protected final JSONObject zzue;
    protected final String zzuf;
    protected final String zzug;
    protected final long zzuh;
    protected final String zzui;
    protected final zzby zzuj;

    private zzbz(int i, int i2, String str, JSONObject jSONObject, int i3, int i4, List<zzcc> list, JSONObject jSONObject2, String str2, String str3, long j, String str4, zzby zzby) {
        this.zztx = i;
        this.zzty = i2;
        this.zztz = str;
        this.zzua = jSONObject;
        this.zzub = i3;
        this.zzuc = i4;
        this.zzud = list;
        this.zzue = jSONObject2;
        this.zzuf = str2;
        this.zzug = str3;
        this.zzuh = j;
        this.zzui = str4;
        this.zzuj = zzby;
    }

    private static List<zzcc> zza(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                zzcc zzcc = null;
                try {
                    zzcc = new zzcc(optJSONObject);
                } catch (JSONException e) {
                    zzbd.zzb(e, "Exception when attempting to parse PlayerInfoMessageComponent at index %d", Integer.valueOf(i));
                }
                if (zzcc != null) {
                    arrayList.add(zzcc);
                }
            }
        }
        return arrayList;
    }

    protected static zzbz zzh(JSONObject jSONObject) {
        int i;
        JSONObject jSONObject2 = jSONObject;
        int optInt = jSONObject2.optInt("type", -1);
        if (optInt == 1) {
            JSONObject optJSONObject = jSONObject2.optJSONObject("gameManagerConfig");
            zzby zzby = optJSONObject != null ? new zzby(optJSONObject) : null;
            int optInt2 = jSONObject2.optInt("statusCode");
            String optString = jSONObject2.optString("errorDescription");
            JSONObject optJSONObject2 = jSONObject2.optJSONObject("extraMessageData");
            int optInt3 = jSONObject2.optInt("gameplayState");
            int optInt4 = jSONObject2.optInt("lobbyState");
            List<zzcc> zza = zza(jSONObject2.optJSONArray("players"));
            i = 0;
            try {
                zzbz zzbz = new zzbz(optInt, optInt2, optString, optJSONObject2, optInt3, optInt4, zza, jSONObject2.optJSONObject("gameData"), jSONObject2.optString("gameStatusText"), jSONObject2.optString("playerId"), jSONObject2.optLong("requestId"), jSONObject2.optString("playerToken"), zzby);
                return zzbz;
            } catch (JSONException e) {
                e = e;
                zzbd.zzb(e, "Exception while parsing GameManagerMessage from json", new Object[i]);
                return null;
            }
        } else if (optInt != 2) {
            try {
                zzbd.mo6873w("Unrecognized Game Message type %d", Integer.valueOf(optInt));
            } catch (JSONException e2) {
                e = e2;
                i = 0;
                zzbd.zzb(e, "Exception while parsing GameManagerMessage from json", new Object[i]);
                return null;
            }
        } else {
            return new zzbz(optInt, jSONObject2.optInt("statusCode"), jSONObject2.optString("errorDescription"), jSONObject2.optJSONObject("extraMessageData"), jSONObject2.optInt("gameplayState"), jSONObject2.optInt("lobbyState"), zza(jSONObject2.optJSONArray("players")), jSONObject2.optJSONObject("gameData"), jSONObject2.optString("gameStatusText"), jSONObject2.optString("playerId"), -1, (String) null, (zzby) null);
        }
        return null;
    }
}

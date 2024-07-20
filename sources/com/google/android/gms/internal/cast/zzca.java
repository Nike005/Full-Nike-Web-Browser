package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.GameManagerState;
import com.google.android.gms.cast.games.PlayerInfo;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.JsonUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public final class zzca implements GameManagerState {
    private final String zztv;
    private final int zztw;
    private final int zzuk;
    private final int zzul;
    private final String zzum;
    private final JSONObject zzun;
    private final Map<String, PlayerInfo> zzuo;

    public zzca(int i, int i2, String str, JSONObject jSONObject, Collection<PlayerInfo> collection, String str2, int i3) {
        this.zzuk = i;
        this.zzul = i2;
        this.zzum = str;
        this.zzun = jSONObject;
        this.zztv = str2;
        this.zztw = i3;
        this.zzuo = new HashMap(collection.size());
        for (PlayerInfo next : collection) {
            this.zzuo.put(next.getPlayerId(), next);
        }
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof GameManagerState)) {
            GameManagerState gameManagerState = (GameManagerState) obj;
            if (getPlayers().size() != gameManagerState.getPlayers().size()) {
                return false;
            }
            for (PlayerInfo next : getPlayers()) {
                boolean z = false;
                for (PlayerInfo next2 : gameManagerState.getPlayers()) {
                    if (zzcu.zza(next.getPlayerId(), next2.getPlayerId())) {
                        if (!zzcu.zza(next, next2)) {
                            return false;
                        }
                        z = true;
                    }
                }
                if (!z) {
                    return false;
                }
            }
            return this.zzuk == gameManagerState.getLobbyState() && this.zzul == gameManagerState.getGameplayState() && this.zztw == gameManagerState.getMaxPlayers() && zzcu.zza(this.zztv, gameManagerState.getApplicationName()) && zzcu.zza(this.zzum, gameManagerState.getGameStatusText()) && JsonUtils.areJsonValuesEquivalent(this.zzun, gameManagerState.getGameData());
        }
    }

    public final CharSequence getApplicationName() {
        return this.zztv;
    }

    public final List<PlayerInfo> getConnectedControllablePlayers() {
        ArrayList arrayList = new ArrayList();
        for (PlayerInfo next : getPlayers()) {
            if (next.isConnected() && next.isControllable()) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public final List<PlayerInfo> getConnectedPlayers() {
        ArrayList arrayList = new ArrayList();
        for (PlayerInfo next : getPlayers()) {
            if (next.isConnected()) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public final List<PlayerInfo> getControllablePlayers() {
        ArrayList arrayList = new ArrayList();
        for (PlayerInfo next : getPlayers()) {
            if (next.isControllable()) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public final JSONObject getGameData() {
        return this.zzun;
    }

    public final CharSequence getGameStatusText() {
        return this.zzum;
    }

    public final int getGameplayState() {
        return this.zzul;
    }

    public final Collection<String> getListOfChangedPlayers(GameManagerState gameManagerState) {
        HashSet hashSet = new HashSet();
        for (PlayerInfo next : getPlayers()) {
            PlayerInfo player = gameManagerState.getPlayer(next.getPlayerId());
            if (player == null || !next.equals(player)) {
                hashSet.add(next.getPlayerId());
            }
        }
        for (PlayerInfo next2 : gameManagerState.getPlayers()) {
            if (getPlayer(next2.getPlayerId()) == null) {
                hashSet.add(next2.getPlayerId());
            }
        }
        return hashSet;
    }

    public final int getLobbyState() {
        return this.zzuk;
    }

    public final int getMaxPlayers() {
        return this.zztw;
    }

    public final PlayerInfo getPlayer(String str) {
        if (str == null) {
            return null;
        }
        return this.zzuo.get(str);
    }

    public final Collection<PlayerInfo> getPlayers() {
        return Collections.unmodifiableCollection(this.zzuo.values());
    }

    public final List<PlayerInfo> getPlayersInState(int i) {
        ArrayList arrayList = new ArrayList();
        for (PlayerInfo next : getPlayers()) {
            if (next.getPlayerState() == i) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public final boolean hasGameDataChanged(GameManagerState gameManagerState) {
        return !JsonUtils.areJsonValuesEquivalent(this.zzun, gameManagerState.getGameData());
    }

    public final boolean hasGameStatusTextChanged(GameManagerState gameManagerState) {
        return !zzcu.zza(this.zzum, gameManagerState.getGameStatusText());
    }

    public final boolean hasGameplayStateChanged(GameManagerState gameManagerState) {
        return this.zzul != gameManagerState.getGameplayState();
    }

    public final boolean hasLobbyStateChanged(GameManagerState gameManagerState) {
        return this.zzuk != gameManagerState.getLobbyState();
    }

    public final boolean hasPlayerChanged(String str, GameManagerState gameManagerState) {
        return !zzcu.zza(getPlayer(str), gameManagerState.getPlayer(str));
    }

    public final boolean hasPlayerDataChanged(String str, GameManagerState gameManagerState) {
        PlayerInfo player = getPlayer(str);
        PlayerInfo player2 = gameManagerState.getPlayer(str);
        if (player == null && player2 == null) {
            return false;
        }
        return player == null || player2 == null || !JsonUtils.areJsonValuesEquivalent(player.getPlayerData(), player2.getPlayerData());
    }

    public final boolean hasPlayerStateChanged(String str, GameManagerState gameManagerState) {
        PlayerInfo player = getPlayer(str);
        PlayerInfo player2 = gameManagerState.getPlayer(str);
        if (player == null && player2 == null) {
            return false;
        }
        return player == null || player2 == null || player.getPlayerState() != player2.getPlayerState();
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzuk), Integer.valueOf(this.zzul), this.zzuo, this.zzum, this.zzun, this.zztv, Integer.valueOf(this.zztw));
    }
}

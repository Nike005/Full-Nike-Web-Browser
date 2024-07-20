package com.google.android.gms.cast.framework.media;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;
import androidx.fragment.app.DialogFragment;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.cast.framework.C0069R;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class TracksChooserDialogFragment extends DialogFragment {
    private long[] zzdm;
    private RemoteMediaClient zzhp;
    private boolean zzok;
    private List<MediaTrack> zzol;
    private List<MediaTrack> zzom;
    /* access modifiers changed from: private */
    public Dialog zzon;
    private MediaInfo zzoo;
    private long[] zzop;

    @Deprecated
    public TracksChooserDialogFragment() {
    }

    private TracksChooserDialogFragment(MediaInfo mediaInfo, long[] jArr) {
        this.zzoo = mediaInfo;
        this.zzop = jArr;
    }

    public static TracksChooserDialogFragment newInstance() {
        return new TracksChooserDialogFragment();
    }

    @Deprecated
    public static TracksChooserDialogFragment newInstance(MediaInfo mediaInfo, long[] jArr) {
        return new TracksChooserDialogFragment(mediaInfo, jArr);
    }

    private static int zza(List<MediaTrack> list, long[] jArr, int i) {
        if (!(jArr == null || list == null)) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                for (long j : jArr) {
                    if (j == list.get(i2).getId()) {
                        return i2;
                    }
                }
            }
        }
        return i;
    }

    private static ArrayList<MediaTrack> zza(List<MediaTrack> list, int i) {
        ArrayList<MediaTrack> arrayList = new ArrayList<>();
        if (list != null) {
            for (MediaTrack next : list) {
                if (next.getType() == i) {
                    arrayList.add(next);
                }
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public final void zza(zzbb zzbb, zzbb zzbb2) {
        if (!this.zzok || !this.zzhp.hasMediaSession()) {
            zzbr();
            return;
        }
        ArrayList arrayList = new ArrayList();
        MediaTrack zzbs = zzbb.zzbs();
        if (!(zzbs == null || zzbs.getId() == -1)) {
            arrayList.add(Long.valueOf(zzbs.getId()));
        }
        MediaTrack zzbs2 = zzbb2.zzbs();
        if (zzbs2 != null) {
            arrayList.add(Long.valueOf(zzbs2.getId()));
        }
        long[] jArr = this.zzdm;
        if (jArr != null && jArr.length > 0) {
            HashSet hashSet = new HashSet();
            for (MediaTrack id : this.zzom) {
                hashSet.add(Long.valueOf(id.getId()));
            }
            for (MediaTrack id2 : this.zzol) {
                hashSet.add(Long.valueOf(id2.getId()));
            }
            for (long j : this.zzdm) {
                if (!hashSet.contains(Long.valueOf(j))) {
                    arrayList.add(Long.valueOf(j));
                }
            }
        }
        long[] jArr2 = new long[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            jArr2[i] = ((Long) arrayList.get(i)).longValue();
        }
        Arrays.sort(jArr2);
        this.zzhp.setActiveMediaTracks(jArr2);
        zzbr();
    }

    private final void zzbr() {
        Dialog dialog = this.zzon;
        if (dialog != null) {
            dialog.cancel();
            this.zzon = null;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzok = true;
        this.zzom = new ArrayList();
        this.zzol = new ArrayList();
        this.zzdm = new long[0];
        CastSession currentCastSession = CastContext.getSharedInstance(getContext()).getSessionManager().getCurrentCastSession();
        if (currentCastSession == null || !currentCastSession.isConnected()) {
            this.zzok = false;
            return;
        }
        RemoteMediaClient remoteMediaClient = currentCastSession.getRemoteMediaClient();
        this.zzhp = remoteMediaClient;
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || this.zzhp.getMediaInfo() == null) {
            this.zzok = false;
            return;
        }
        long[] jArr = this.zzop;
        if (jArr != null) {
            this.zzdm = jArr;
        } else {
            MediaStatus mediaStatus = this.zzhp.getMediaStatus();
            if (mediaStatus != null) {
                this.zzdm = mediaStatus.getActiveTrackIds();
            }
        }
        MediaInfo mediaInfo = this.zzoo;
        if (mediaInfo == null) {
            mediaInfo = this.zzhp.getMediaInfo();
        }
        if (mediaInfo == null) {
            this.zzok = false;
            return;
        }
        List<MediaTrack> mediaTracks = mediaInfo.getMediaTracks();
        if (mediaTracks == null) {
            this.zzok = false;
            return;
        }
        this.zzom = zza(mediaTracks, 2);
        ArrayList<MediaTrack> zza = zza(mediaTracks, 1);
        this.zzol = zza;
        if (!zza.isEmpty()) {
            this.zzol.add(0, new MediaTrack.Builder(-1, 1).setName(getActivity().getString(C0069R.string.cast_tracks_chooser_dialog_none)).setSubtype(2).setContentId("").build());
        }
    }

    public Dialog onCreateDialog(Bundle bundle) {
        int zza = zza(this.zzol, this.zzdm, 0);
        int zza2 = zza(this.zzom, this.zzdm, -1);
        zzbb zzbb = new zzbb(getActivity(), this.zzol, zza);
        zzbb zzbb2 = new zzbb(getActivity(), this.zzom, zza2);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View inflate = getActivity().getLayoutInflater().inflate(C0069R.layout.cast_tracks_chooser_dialog_layout, (ViewGroup) null);
        ListView listView = (ListView) inflate.findViewById(C0069R.C0071id.text_list_view);
        ListView listView2 = (ListView) inflate.findViewById(C0069R.C0071id.audio_list_view);
        TabHost tabHost = (TabHost) inflate.findViewById(C0069R.C0071id.tab_host);
        tabHost.setup();
        if (zzbb.getCount() == 0) {
            listView.setVisibility(4);
        } else {
            listView.setAdapter(zzbb);
            TabHost.TabSpec newTabSpec = tabHost.newTabSpec("textTab");
            newTabSpec.setContent(C0069R.C0071id.text_list_view);
            newTabSpec.setIndicator(getActivity().getString(C0069R.string.cast_tracks_chooser_dialog_subtitles));
            tabHost.addTab(newTabSpec);
        }
        if (zzbb2.getCount() <= 1) {
            listView2.setVisibility(4);
        } else {
            listView2.setAdapter(zzbb2);
            TabHost.TabSpec newTabSpec2 = tabHost.newTabSpec("audioTab");
            newTabSpec2.setContent(C0069R.C0071id.audio_list_view);
            newTabSpec2.setIndicator(getActivity().getString(C0069R.string.cast_tracks_chooser_dialog_audio));
            tabHost.addTab(newTabSpec2);
        }
        builder.setView(inflate).setPositiveButton(getActivity().getString(C0069R.string.cast_tracks_chooser_dialog_ok), new zzba(this, zzbb, zzbb2)).setNegativeButton(C0069R.string.cast_tracks_chooser_dialog_cancel, new zzaz(this));
        Dialog dialog = this.zzon;
        if (dialog != null) {
            dialog.cancel();
            this.zzon = null;
        }
        AlertDialog create = builder.create();
        this.zzon = create;
        return create;
    }

    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage((Message) null);
        }
        super.onDestroyView();
    }
}

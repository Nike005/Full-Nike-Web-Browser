package com.google.android.gms.cast.framework.media;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.cast.framework.C0069R;
import java.util.ArrayList;
import java.util.List;

public final class zzbb extends ArrayAdapter<MediaTrack> implements View.OnClickListener {
    private final Context zzij;
    private int zzot;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzbb(Context context, List<MediaTrack> list, int i) {
        super(context, C0069R.layout.cast_tracks_chooser_dialog_row_layout, list == null ? new ArrayList<>() : list);
        this.zzot = -1;
        this.zzij = context;
        this.zzot = i;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        zzbd zzbd;
        String str;
        if (view == null) {
            view = ((LayoutInflater) this.zzij.getSystemService("layout_inflater")).inflate(C0069R.layout.cast_tracks_chooser_dialog_row_layout, viewGroup, false);
            zzbd = new zzbd(this, (TextView) view.findViewById(C0069R.C0071id.text), (RadioButton) view.findViewById(C0069R.C0071id.radio));
            view.setTag(zzbd);
        } else {
            zzbd = (zzbd) view.getTag();
        }
        if (zzbd == null) {
            return null;
        }
        zzbd.zzov.setTag(Integer.valueOf(i));
        zzbd.zzov.setChecked(this.zzot == i);
        view.setOnClickListener(this);
        MediaTrack mediaTrack = (MediaTrack) getItem(i);
        String name = mediaTrack.getName();
        if (TextUtils.isEmpty(name)) {
            if (mediaTrack.getSubtype() == 2) {
                str = this.zzij.getString(C0069R.string.cast_tracks_chooser_dialog_closed_captions);
            } else {
                if (!TextUtils.isEmpty(mediaTrack.getLanguage())) {
                    String displayLanguage = MediaUtils.getTrackLanguage(mediaTrack).getDisplayLanguage();
                    if (!TextUtils.isEmpty(displayLanguage)) {
                        str = displayLanguage;
                    }
                }
                name = this.zzij.getString(C0069R.string.cast_tracks_chooser_dialog_default_track_name, new Object[]{Integer.valueOf(i + 1)});
            }
            zzbd.zzou.setText(str);
            return view;
        }
        str = name;
        zzbd.zzou.setText(str);
        return view;
    }

    public final void onClick(View view) {
        this.zzot = ((Integer) ((zzbd) view.getTag()).zzov.getTag()).intValue();
        notifyDataSetChanged();
    }

    public final MediaTrack zzbs() {
        int i = this.zzot;
        if (i < 0 || i >= getCount()) {
            return null;
        }
        return (MediaTrack) getItem(this.zzot);
    }
}

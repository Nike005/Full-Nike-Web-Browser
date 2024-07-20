package com.google.android.gms.cast.framework.media;

import android.content.Context;
import android.widget.ArrayAdapter;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.media.MediaQueue;

public class MediaQueueArrayAdapter extends ArrayAdapter<MediaQueueItem> {
    private final MediaQueue zzlt;
    private final MediaQueue.Callback zzlu;

    private class zza extends MediaQueue.Callback {
        private zza() {
        }

        public final void itemsInsertedInRange(int i, int i2) {
            MediaQueueArrayAdapter.this.notifyDataSetChanged();
        }

        public final void itemsReloaded() {
            MediaQueueArrayAdapter.this.notifyDataSetChanged();
        }

        public final void itemsRemovedAtIndexes(int[] iArr) {
            MediaQueueArrayAdapter.this.notifyDataSetChanged();
        }

        public final void itemsUpdatedAtIndexes(int[] iArr) {
            MediaQueueArrayAdapter.this.notifyDataSetChanged();
        }

        public final void mediaQueueChanged() {
            MediaQueueArrayAdapter.this.notifyDataSetChanged();
        }

        public final void mediaQueueWillChange() {
        }
    }

    public MediaQueueArrayAdapter(MediaQueue mediaQueue, Context context, int i) {
        super(context, i);
        this.zzlt = mediaQueue;
        zza zza2 = new zza();
        this.zzlu = zza2;
        this.zzlt.registerCallback(zza2);
    }

    public boolean areAllItemsEnabled() {
        return false;
    }

    public void dispose() {
        this.zzlt.unregisterCallback(this.zzlu);
    }

    public int getCount() {
        return this.zzlt.getItemCount();
    }

    public MediaQueueItem getItem(int i) {
        return this.zzlt.getItemAtIndex(i);
    }

    public long getItemId(int i) {
        return (long) this.zzlt.itemIdAtIndex(i);
    }

    public MediaQueue getMediaQueue() {
        return this.zzlt;
    }

    public boolean hasStableIds() {
        return true;
    }

    public boolean isEmpty() {
        return this.zzlt.getItemCount() == 0;
    }

    public boolean isEnabled(int i) {
        return this.zzlt.getItemAtIndex(i, false) != null;
    }
}

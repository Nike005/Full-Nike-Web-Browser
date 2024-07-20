package com.google.android.gms.cast.framework.media;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.media.MediaQueue;

public abstract class MediaQueueRecyclerViewAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private final MediaQueue zzlt;
    private final MediaQueue.Callback zzlu;

    private class zza extends MediaQueue.Callback {
        private zza() {
        }

        public final void itemsInsertedInRange(int i, int i2) {
            MediaQueueRecyclerViewAdapter.this.notifyItemRangeInserted(i, i2);
        }

        public final void itemsReloaded() {
            MediaQueueRecyclerViewAdapter.this.notifyDataSetChanged();
        }

        public final void itemsRemovedAtIndexes(int[] iArr) {
            if (iArr.length > 1) {
                MediaQueueRecyclerViewAdapter.this.notifyDataSetChanged();
                return;
            }
            for (int notifyItemRemoved : iArr) {
                MediaQueueRecyclerViewAdapter.this.notifyItemRemoved(notifyItemRemoved);
            }
        }

        public final void itemsUpdatedAtIndexes(int[] iArr) {
            for (int notifyItemChanged : iArr) {
                MediaQueueRecyclerViewAdapter.this.notifyItemChanged(notifyItemChanged);
            }
        }

        public final void mediaQueueChanged() {
        }

        public final void mediaQueueWillChange() {
        }
    }

    public MediaQueueRecyclerViewAdapter(MediaQueue mediaQueue) {
        this.zzlt = mediaQueue;
        zza zza2 = new zza();
        this.zzlu = zza2;
        this.zzlt.registerCallback(zza2);
    }

    public void dispose() {
        this.zzlt.unregisterCallback(this.zzlu);
    }

    public MediaQueueItem getItem(int i) {
        return this.zzlt.getItemAtIndex(i);
    }

    public int getItemCount() {
        return this.zzlt.getItemCount();
    }

    public long getItemId(int i) {
        return (long) this.zzlt.itemIdAtIndex(i);
    }

    public MediaQueue getMediaQueue() {
        return this.zzlt;
    }
}

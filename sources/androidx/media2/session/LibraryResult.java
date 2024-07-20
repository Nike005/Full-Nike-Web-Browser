package androidx.media2.session;

import android.os.SystemClock;
import androidx.media2.common.MediaItem;
import androidx.media2.common.ParcelImplListSlice;
import androidx.media2.session.MediaLibraryService;
import androidx.media2.session.futures.ResolvableFuture;
import androidx.versionedparcelable.CustomVersionedParcelable;
import com.google.common.util.concurrent.ListenableFuture;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class LibraryResult extends CustomVersionedParcelable implements RemoteResult {
    long mCompletionTime;
    MediaItem mItem;
    List<MediaItem> mItemList;
    ParcelImplListSlice mItemListSlice;
    MediaLibraryService.LibraryParams mParams;
    MediaItem mParcelableItem;
    int mResultCode;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ResultCode {
    }

    LibraryResult() {
    }

    public LibraryResult(int i) {
        this(i, (MediaItem) null, (List<MediaItem>) null, (MediaLibraryService.LibraryParams) null);
    }

    public LibraryResult(int i, MediaItem mediaItem, MediaLibraryService.LibraryParams libraryParams) {
        this(i, mediaItem, (List<MediaItem>) null, libraryParams);
    }

    public LibraryResult(int i, List<MediaItem> list, MediaLibraryService.LibraryParams libraryParams) {
        this(i, (MediaItem) null, list, libraryParams);
    }

    private LibraryResult(int i, MediaItem mediaItem, List<MediaItem> list, MediaLibraryService.LibraryParams libraryParams) {
        this.mResultCode = i;
        this.mCompletionTime = SystemClock.elapsedRealtime();
        this.mItem = mediaItem;
        this.mItemList = list;
        this.mParams = libraryParams;
    }

    static ListenableFuture<LibraryResult> createFutureWithResult(int i) {
        ResolvableFuture create = ResolvableFuture.create();
        create.set(new LibraryResult(i));
        return create;
    }

    public int getResultCode() {
        return this.mResultCode;
    }

    public long getCompletionTime() {
        return this.mCompletionTime;
    }

    public MediaItem getMediaItem() {
        return this.mItem;
    }

    public List<MediaItem> getMediaItems() {
        return this.mItemList;
    }

    public MediaLibraryService.LibraryParams getLibraryParams() {
        return this.mParams;
    }

    public void onPreParceling(boolean z) {
        this.mParcelableItem = MediaUtils.upcastForPreparceling(this.mItem);
        this.mItemListSlice = MediaUtils.convertMediaItemListToParcelImplListSlice(this.mItemList);
    }

    public void onPostParceling() {
        this.mItem = this.mParcelableItem;
        this.mParcelableItem = null;
        this.mItemList = MediaUtils.convertParcelImplListSliceToMediaItemList(this.mItemListSlice);
        this.mItemListSlice = null;
    }
}

package acr.browser.lightning.database;

import acr.browser.lightning.utils.Preconditions;
import android.graphics.Bitmap;

public class HistoryItem implements Comparable<HistoryItem> {

    /* renamed from: id */
    private int f4051id;
    private String imageUrl = "";
    private boolean isDeleted;
    private Bitmap mBitmap = null;
    private String mFolder = "";
    private int mImageId = 0;
    private boolean mIsFolder = false;
    private int mPosition = 0;
    private String mTitle = "";
    private String mUrl = "";
    private boolean showOnMainScreen;

    public HistoryItem() {
    }

    public HistoryItem(String str, String str2) {
        Preconditions.checkNonNull(str);
        Preconditions.checkNonNull(str2);
        this.mUrl = str;
        this.mTitle = str2;
        this.mBitmap = null;
    }

    public HistoryItem(String str, String str2, int i) {
        Preconditions.checkNonNull(str);
        Preconditions.checkNonNull(str2);
        this.mUrl = str;
        this.mTitle = str2;
        this.mBitmap = null;
        this.mImageId = i;
    }

    public boolean isShowOnMainScreen() {
        return this.showOnMainScreen;
    }

    public void setShowOnMainScreen(boolean z) {
        this.showOnMainScreen = z;
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }

    public void setDeleted(boolean z) {
        this.isDeleted = z;
    }

    public int getId() {
        return this.f4051id;
    }

    public void setId(int i) {
        this.f4051id = i;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String str) {
        this.imageUrl = str;
    }

    public int getImageId() {
        return this.mImageId;
    }

    public void setImageId(int i) {
        this.mImageId = i;
    }

    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }

    public void setFolder(String str) {
        if (str == null) {
            str = "";
        }
        this.mFolder = str;
    }

    public void setPosition(int i) {
        this.mPosition = i;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public String getFolder() {
        return this.mFolder;
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void setUrl(String str) {
        if (str == null) {
            str = "";
        }
        this.mUrl = str;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        if (str == null) {
            str = "";
        }
        this.mTitle = str;
    }

    public void setIsFolder(boolean z) {
        this.mIsFolder = z;
    }

    public boolean isFolder() {
        return this.mIsFolder;
    }

    public String toString() {
        return this.mTitle;
    }

    public int compareTo(HistoryItem historyItem) {
        int compareTo = this.mTitle.compareTo(historyItem.mTitle);
        return compareTo == 0 ? this.mUrl.compareTo(historyItem.mUrl) : compareTo;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof HistoryItem)) {
            return false;
        }
        HistoryItem historyItem = (HistoryItem) obj;
        if (this.mImageId != historyItem.mImageId || !this.mTitle.equals(historyItem.mTitle) || !this.mUrl.equals(historyItem.mUrl) || !this.mFolder.equals(historyItem.mFolder)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((((((this.mUrl.hashCode() * 31) + this.mImageId) * 31) + this.mTitle.hashCode()) * 32) + this.mFolder.hashCode()) * 31) + this.mImageId;
    }
}

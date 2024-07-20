package acr.browser.lightning.database.downloads;

import acr.browser.lightning.utils.Preconditions;

public class DownloadItem implements Comparable<DownloadItem> {
    private String mContentSize = "";
    private String mTitle = "";
    private String mUrl = "";

    public DownloadItem() {
    }

    public DownloadItem(String str, String str2, String str3) {
        Preconditions.checkNonNull(str);
        Preconditions.checkNonNull(str2);
        Preconditions.checkNonNull(str3);
        this.mUrl = str;
        this.mTitle = str2;
        this.mContentSize = str3;
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

    public String getContentSize() {
        return this.mContentSize;
    }

    public void setContentSize(String str) {
        if (str == null) {
            str = "";
        }
        this.mContentSize = str;
    }

    public String toString() {
        return this.mTitle;
    }

    public int compareTo(DownloadItem downloadItem) {
        int compareTo = this.mTitle.compareTo(downloadItem.mTitle);
        return compareTo == 0 ? this.mUrl.compareTo(downloadItem.mUrl) : compareTo;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DownloadItem)) {
            return false;
        }
        DownloadItem downloadItem = (DownloadItem) obj;
        if (!this.mTitle.equals(downloadItem.mTitle) || !this.mUrl.equals(downloadItem.mUrl) || !this.mContentSize.equals(downloadItem.mContentSize)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (this.mUrl.hashCode() * 31) + this.mTitle.hashCode();
    }
}

package acr.browser.lightning.database.downloads;

import com.anthonycr.bonsai.Completable;
import com.anthonycr.bonsai.Single;
import java.util.List;

public interface DownloadsModel {
    Single<Boolean> addDownloadIfNotExists(DownloadItem downloadItem);

    Completable addDownloadsList(List<DownloadItem> list);

    long count();

    Completable deleteAllDownloads();

    Single<Boolean> deleteDownload(String str);

    Single<DownloadItem> findDownloadForUrl(String str);

    Single<List<DownloadItem>> getAllDownloads();

    Single<Boolean> isDownload(String str);
}

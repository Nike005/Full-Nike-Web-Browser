package acr.browser.lightning.constant;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.database.downloads.DownloadItem;
import acr.browser.lightning.database.downloads.DownloadsModel;
import acr.browser.lightning.preference.PreferenceManager;
import acr.browser.lightning.utils.C3245Utils;
import acr.browser.lightning.utils.Preconditions;
import android.app.Application;
import com.anthonycr.bonsai.Single;
import com.anthonycr.bonsai.SingleAction;
import com.anthonycr.bonsai.SingleOnSubscribe;
import com.anthonycr.bonsai.SingleSubscriber;
import com.wnikebrow_13999769.R;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;

public final class DownloadsPage {
    private static final String END = "</div></body></html>";
    public static final String FILENAME = "downloads.html";
    private static final String HEADING_1 = "<!DOCTYPE html><html xmlns=http://www.w3.org/1999/xhtml>\n<head>\n<meta content=en-us http-equiv=Content-Language />\n<meta content='text/html; charset=utf-8' http-equiv=Content-Type />\n<meta name=viewport content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no'>\n<title>";
    private static final String HEADING_2 = "</title></head><style>body{background:#f5f5f5;}.box{vertical-align:middle;position:relative; display: block; margin: 10px;padding:10px; background-color:#fff;box-shadow: 0px 2px 4px rgba( 0, 0, 0, 0.25 );font-family: Arial;color: #444;font-size: 12px;-moz-border-radius: 2px;-webkit-border-radius: 2px;border-radius: 2px;}.box a { width: 100%; height: 100%; position: absolute; left: 0; top: 0;}.black {color: black;font-size: 15px;font-family: Arial; white-space: nowrap; overflow: hidden;margin:auto; text-overflow: ellipsis; -o-text-overflow: ellipsis; -ms-text-overflow: ellipsis;}.font {color: gray;font-size: 10px;font-family: Arial; white-space: nowrap; overflow: hidden;margin:auto; text-overflow: ellipsis; -o-text-overflow: ellipsis; -ms-text-overflow: ellipsis;}</style><body><div id='content'>";
    private static final String PART1 = "<div class=box><a href='";
    private static final String PART2 = "'></a><p class='black'>";
    private static final String PART3 = "</p><p class='font'>";
    private static final String PART4 = "</p></div>";
    @Inject
    Application mApp;
    /* access modifiers changed from: private */
    public File mFilesDir;
    @Inject
    DownloadsModel mManager;
    @Inject
    PreferenceManager mPreferenceManager;
    /* access modifiers changed from: private */
    public final String mTitle = this.mApp.getString(R.string.action_downloads);

    public DownloadsPage() {
        BrowserApp.getAppComponent().inject(this);
    }

    public Single<String> getDownloadsPage() {
        return Single.create(new SingleAction<String>() {
            public void onSubscribe(SingleSubscriber<String> singleSubscriber) {
                DownloadsPage downloadsPage = DownloadsPage.this;
                File unused = downloadsPage.mFilesDir = downloadsPage.mApp.getFilesDir();
                DownloadsPage.this.buildDownloadsPage();
                File file = new File(DownloadsPage.this.mFilesDir, DownloadsPage.FILENAME);
                singleSubscriber.onItem(Constants.FILE + file);
                singleSubscriber.onComplete();
            }
        });
    }

    /* access modifiers changed from: private */
    public void buildDownloadsPage() {
        this.mManager.getAllDownloads().subscribe(new SingleOnSubscribe<List<DownloadItem>>() {
            public void onItem(List<DownloadItem> list) {
                FileWriter fileWriter;
                IOException e;
                Preconditions.checkNonNull(list);
                String downloadDirectory = DownloadsPage.this.mPreferenceManager.getDownloadDirectory();
                StringBuilder sb = new StringBuilder(DownloadsPage.HEADING_1 + DownloadsPage.this.mTitle + DownloadsPage.HEADING_2);
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    DownloadItem downloadItem = list.get(i);
                    sb.append(DownloadsPage.PART1);
                    sb.append(Constants.FILE);
                    sb.append(downloadDirectory);
                    sb.append("/");
                    sb.append(downloadItem.getTitle());
                    sb.append(DownloadsPage.PART2);
                    sb.append(downloadItem.getTitle());
                    if (!downloadItem.getContentSize().isEmpty()) {
                        sb.append(" [");
                        sb.append(downloadItem.getContentSize());
                        sb.append("]");
                    }
                    sb.append(DownloadsPage.PART3);
                    sb.append(downloadItem.getUrl());
                    sb.append(DownloadsPage.PART4);
                }
                sb.append(DownloadsPage.END);
                try {
                    fileWriter = new FileWriter(new File(DownloadsPage.this.mFilesDir, DownloadsPage.FILENAME), false);
                    try {
                        fileWriter.write(sb.toString());
                    } catch (IOException e2) {
                        e = e2;
                    }
                } catch (IOException e3) {
                    IOException iOException = e3;
                    fileWriter = null;
                    e = iOException;
                    try {
                        e.printStackTrace();
                        C3245Utils.close((Closeable) fileWriter);
                    } catch (Throwable th) {
                        th = th;
                        C3245Utils.close((Closeable) fileWriter);
                        throw th;
                    }
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    fileWriter = null;
                    th = th3;
                    C3245Utils.close((Closeable) fileWriter);
                    throw th;
                }
                C3245Utils.close((Closeable) fileWriter);
            }
        });
    }
}

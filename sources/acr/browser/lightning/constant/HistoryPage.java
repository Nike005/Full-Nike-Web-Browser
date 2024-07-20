package acr.browser.lightning.constant;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.database.HistoryItem;
import acr.browser.lightning.database.history.HistoryModel;
import acr.browser.lightning.utils.C3245Utils;
import acr.browser.lightning.utils.Preconditions;
import android.app.Application;
import android.util.Log;
import com.anthonycr.bonsai.Completable;
import com.anthonycr.bonsai.CompletableAction;
import com.anthonycr.bonsai.CompletableSubscriber;
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

public class HistoryPage {
    private static final String END = "</div></body></html>";
    public static final String FILENAME = "history.html";
    private static final String HEADING_1 = "<!DOCTYPE html><html xmlns=http://www.w3.org/1999/xhtml>\n<head>\n<meta content=en-us http-equiv=Content-Language />\n<meta content='text/html; charset=utf-8' http-equiv=Content-Type />\n<meta name=viewport content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no'>\n<title>";
    private static final String HEADING_2 = "</title></head><style>body,html {margin: 0px; padding: 0px;}.box { vertical-align:middle;position:relative; display: block; margin: 0px;padding-left:14px;padding-right:14px;padding-top:9px;padding-bottom:9px; background-color:#fff;border-bottom: 1px solid #d2d2d2;font-family: Arial;color: #444;font-size: 12px;}.box a { width: 100%; height: 100%; position: absolute; left: 0; top: 0;}.black {color: black;font-size: 15px;font-family: Arial; white-space: nowrap; overflow: hidden;margin:auto; text-overflow: ellipsis; -o-text-overflow: ellipsis; -ms-text-overflow: ellipsis;}.font {color: gray;font-size: 10px;font-family: Arial; white-space: nowrap; overflow: hidden;margin:auto; text-overflow: ellipsis; -o-text-overflow: ellipsis; -ms-text-overflow: ellipsis;}</style><body><div id=\"content\">";
    private static final String PART1 = "<div class=box><a href='";
    private static final String PART2 = "'></a><p class='black'>";
    private static final String PART3 = "</p><p class='font'>";
    private static final String PART4 = "</p></div>";
    private static final String TAG = "HistoryPage";
    @Inject
    Application mApp;
    /* access modifiers changed from: private */
    public final String mTitle = this.mApp.getString(R.string.action_history);

    public HistoryPage() {
        BrowserApp.getAppComponent().inject(this);
    }

    public Single<String> getHistoryPage() {
        return Single.create(new SingleAction<String>() {
            public void onSubscribe(final SingleSubscriber<String> singleSubscriber) {
                final StringBuilder sb = new StringBuilder(HistoryPage.HEADING_1 + HistoryPage.this.mTitle + HistoryPage.HEADING_2);
                HistoryModel.lastHundredVisitedHistoryItems().subscribe(new SingleOnSubscribe<List<HistoryItem>>() {
                    public void onItem(List<HistoryItem> list) {
                        FileWriter fileWriter;
                        IOException e;
                        Preconditions.checkNonNull(list);
                        for (HistoryItem next : list) {
                            sb.append(HistoryPage.PART1);
                            sb.append(next.getUrl());
                            sb.append(HistoryPage.PART2);
                            sb.append(next.getTitle());
                            sb.append(HistoryPage.PART3);
                            sb.append(next.getUrl());
                            sb.append(HistoryPage.PART4);
                        }
                        sb.append(HistoryPage.END);
                        File file = new File(HistoryPage.this.mApp.getFilesDir(), HistoryPage.FILENAME);
                        FileWriter fileWriter2 = null;
                        try {
                            fileWriter = new FileWriter(file, false);
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
                                Log.e(HistoryPage.TAG, "Unable to write history page to disk", e);
                                C3245Utils.close((Closeable) fileWriter);
                                singleSubscriber.onItem(Constants.FILE + file);
                                singleSubscriber.onComplete();
                            } catch (Throwable th) {
                                th = th;
                                fileWriter2 = fileWriter;
                                C3245Utils.close((Closeable) fileWriter2);
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            C3245Utils.close((Closeable) fileWriter2);
                            throw th;
                        }
                        C3245Utils.close((Closeable) fileWriter);
                        singleSubscriber.onItem(Constants.FILE + file);
                        singleSubscriber.onComplete();
                    }
                });
            }
        });
    }

    public static Completable deleteHistoryPage(final Application application) {
        return Completable.create(new CompletableAction() {
            public void onSubscribe(CompletableSubscriber completableSubscriber) {
                File file = new File(application.getFilesDir(), HistoryPage.FILENAME);
                if (file.exists()) {
                    file.delete();
                }
                completableSubscriber.onComplete();
            }
        });
    }
}

package acr.browser.lightning.constant;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.preference.PreferenceManager;
import acr.browser.lightning.utils.C3245Utils;
import android.app.Application;
import com.anthonycr.bonsai.Single;
import com.anthonycr.bonsai.SingleAction;
import com.anthonycr.bonsai.SingleSubscriber;
import com.wnikebrow_13999769.R;
import java.io.Closeable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.inject.Inject;

public class StartPage {
    private static final String END = "\" + document.getElementById(\"search_input\").value;document.getElementById(\"search_input\").value = \"\";}return false;}</script></body></html>";
    public static final String FILENAME = "homepage.html";
    private static final String HEAD_1 = "<!DOCTYPE html><html xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta content=\"en-us\" http-equiv=\"Content-Language\" /><meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" /><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no\"><title>";
    private static final String HEAD_2 = "</title></head><style>body{text-align:center;margin:0px;}#search_input{height:35px; width:100%;outline:none;border:none;font-size: 16px;background-color:transparent;}span { display: block; overflow: hidden; padding-left:5px;vertical-align:middle;}.search_bar{display:table;vertical-align:middle;width:90%;height:35px;max-width:500px;margin:0 auto;background-color:#fff;box-shadow: 0px 2px 3px rgba( 0, 0, 0, 0.25 );font-family: Arial;color: #444;-moz-border-radius: 2px;-webkit-border-radius: 2px;border-radius: 2px;}#search_submit{outline:none;height:37px;float:right;color:#404040;font-size:16px;font-weight:bold;border:none;background-color:transparent;}.outer { background-image:url(";
    private static final String HEAD_3 = "); background-repeat: no-repeat;background-position: center center;\nbackground-attachment: fixed;\nbackground-size: cover; display: table; position: absolute; height: 100%; width: 100%;}.middle { display: table-cell; vertical-align: middle;}.inner { margin-left: auto; margin-right: auto; margin-bottom:10%; width: 100%;}img.smaller{width:50%;max-width:300px;}.box { vertical-align:middle;position:relative; display: block; margin: 10px;padding-left:10px;padding-right:10px;padding-top:5px;padding-bottom:5px; background-color:#fff;box-shadow: 0px 3px rgba( 0, 0, 0, 0.1 );font-family: Arial;color: #444;font-size: 12px;-moz-border-radius: 2px;-webkit-border-radius: 2px;border-radius: 2px;}</style><body></body>";
    private static final String MIDDLE = "\" ></br></br><form onsubmit=\"return search()\" class=\"search_bar\" autocomplete=\"off\"><input type=\"submit\" id=\"search_submit\" value=\"Search\" ><span><input class=\"search\" type=\"text\" value=\"\" id=\"search_input\" ></span></form></br></br></div></div></div><script type=\"text/javascript\">function search(){if(document.getElementById(\"search_input\").value != \"\"){window.location.href = \"";
    @Inject
    Application mApp;
    @Inject
    PreferenceManager mPreferenceManager;
    /* access modifiers changed from: private */
    public final String mTitle = this.mApp.getString(R.string.home);

    public StartPage() {
        BrowserApp.getAppComponent().inject(this);
    }

    public Single<String> getHomepage() {
        return Single.create(new SingleAction<String>() {
            public void onSubscribe(SingleSubscriber<String> singleSubscriber) {
                StringBuilder sb = new StringBuilder(StartPage.HEAD_1 + StartPage.this.mTitle + StartPage.HEAD_2);
                if (StartPage.this.mPreferenceManager.getSearchChoice() == 0) {
                    StartPage.this.mPreferenceManager.getSearchUrl();
                }
                sb.append("file:///android_asset/");
                sb.append(BrowserApp.getConfig().getBackgroundUrl());
                sb.append(StartPage.HEAD_3);
                File file = new File(StartPage.this.mApp.getFilesDir(), StartPage.FILENAME);
                FileWriter fileWriter = null;
                try {
                    FileWriter fileWriter2 = new FileWriter(file, false);
                    try {
                        fileWriter2.write(sb.toString());
                        C3245Utils.close((Closeable) fileWriter2);
                    } catch (IOException e) {
                        e = e;
                        fileWriter = fileWriter2;
                        try {
                            e.printStackTrace();
                            C3245Utils.close((Closeable) fileWriter);
                            singleSubscriber.onItem(Constants.FILE + file);
                        } catch (Throwable th) {
                            th = th;
                            C3245Utils.close((Closeable) fileWriter);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileWriter = fileWriter2;
                        C3245Utils.close((Closeable) fileWriter);
                        throw th;
                    }
                } catch (IOException e2) {
                    e = e2;
                    e.printStackTrace();
                    C3245Utils.close((Closeable) fileWriter);
                    singleSubscriber.onItem(Constants.FILE + file);
                }
                singleSubscriber.onItem(Constants.FILE + file);
            }
        });
    }
}

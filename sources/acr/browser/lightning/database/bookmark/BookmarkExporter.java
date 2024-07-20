package acr.browser.lightning.database.bookmark;

import acr.browser.lightning.database.HistoryItem;
import acr.browser.lightning.utils.C3245Utils;
import acr.browser.lightning.utils.Preconditions;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.anthonycr.bonsai.Completable;
import com.anthonycr.bonsai.CompletableAction;
import com.anthonycr.bonsai.CompletableSubscriber;
import com.anthonycr.bonsai.Single;
import com.anthonycr.bonsai.SingleAction;
import com.anthonycr.bonsai.SingleSubscriber;
import com.wnikebrow_13999769.R;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class BookmarkExporter {
    private static final String KEY_FOLDER = "folder";
    private static final String KEY_ORDER = "order";
    private static final String KEY_TITLE = "title";
    private static final String KEY_URL = "url";
    private static final String TAG = "BookmarkExporter";

    public static List<HistoryItem> importBookmarksFromAssets(Context context) {
        InputStream inputStream;
        BufferedReader bufferedReader;
        IOException e;
        ArrayList arrayList = new ArrayList();
        BufferedReader bufferedReader2 = null;
        try {
            inputStream = context.getResources().openRawResource(R.raw.default_bookmarks);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        try {
                            JSONObject jSONObject = new JSONObject(readLine);
                            HistoryItem historyItem = new HistoryItem();
                            historyItem.setTitle(jSONObject.getString("title"));
                            historyItem.setUrl(jSONObject.getString("url"));
                            historyItem.setFolder(jSONObject.getString(KEY_FOLDER));
                            historyItem.setPosition(jSONObject.getInt(KEY_ORDER));
                            historyItem.setImageId(R.drawable.ic_bookmark);
                            arrayList.add(historyItem);
                        } catch (JSONException e2) {
                            Log.e(TAG, "Can't parse line " + readLine, e2);
                        }
                    } catch (IOException e3) {
                        e = e3;
                    }
                }
            } catch (IOException e4) {
                IOException iOException = e4;
                bufferedReader = null;
                e = iOException;
                try {
                    Log.e(TAG, "Error reading the bookmarks file", e);
                    C3245Utils.close((Closeable) bufferedReader);
                    C3245Utils.close((Closeable) inputStream);
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    bufferedReader2 = bufferedReader;
                    C3245Utils.close((Closeable) bufferedReader2);
                    C3245Utils.close((Closeable) inputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                C3245Utils.close((Closeable) bufferedReader2);
                C3245Utils.close((Closeable) inputStream);
                throw th;
            }
        } catch (IOException e5) {
            bufferedReader = null;
            e = e5;
            inputStream = null;
            Log.e(TAG, "Error reading the bookmarks file", e);
            C3245Utils.close((Closeable) bufferedReader);
            C3245Utils.close((Closeable) inputStream);
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
            inputStream = null;
            C3245Utils.close((Closeable) bufferedReader2);
            C3245Utils.close((Closeable) inputStream);
            throw th;
        }
        C3245Utils.close((Closeable) bufferedReader);
        C3245Utils.close((Closeable) inputStream);
        return arrayList;
    }

    public static Completable exportBookmarksToFile(final List<HistoryItem> list, final File file) {
        return Completable.create(new CompletableAction() {
            public void onSubscribe(CompletableSubscriber completableSubscriber) {
                BufferedWriter bufferedWriter;
                Throwable e;
                Preconditions.checkNonNull(list);
                BufferedWriter bufferedWriter2 = null;
                try {
                    bufferedWriter = new BufferedWriter(new FileWriter(file, false));
                    try {
                        JSONObject jSONObject = new JSONObject();
                        for (HistoryItem historyItem : list) {
                            jSONObject.put("title", historyItem.getTitle());
                            jSONObject.put("url", historyItem.getUrl());
                            jSONObject.put(BookmarkExporter.KEY_FOLDER, historyItem.getFolder());
                            jSONObject.put(BookmarkExporter.KEY_ORDER, historyItem.getPosition());
                            bufferedWriter.write(jSONObject.toString());
                            bufferedWriter.newLine();
                        }
                        completableSubscriber.onComplete();
                    } catch (IOException | JSONException e2) {
                        e = e2;
                        try {
                            completableSubscriber.onError(e);
                            C3245Utils.close((Closeable) bufferedWriter);
                        } catch (Throwable th) {
                            th = th;
                            bufferedWriter2 = bufferedWriter;
                            C3245Utils.close((Closeable) bufferedWriter2);
                            throw th;
                        }
                    }
                } catch (IOException | JSONException e3) {
                    Throwable th2 = e3;
                    bufferedWriter = null;
                    e = th2;
                    completableSubscriber.onError(e);
                    C3245Utils.close((Closeable) bufferedWriter);
                } catch (Throwable th3) {
                    th = th3;
                    C3245Utils.close((Closeable) bufferedWriter2);
                    throw th;
                }
                C3245Utils.close((Closeable) bufferedWriter);
            }
        });
    }

    public static Single<List<HistoryItem>> importBookmarksFromFile(final File file) {
        return Single.create(new SingleAction<List<HistoryItem>>() {
            public void onSubscribe(SingleSubscriber<List<HistoryItem>> singleSubscriber) {
                BufferedReader bufferedReader;
                Throwable e;
                BufferedReader bufferedReader2 = null;
                try {
                    bufferedReader = new BufferedReader(new FileReader(file));
                    try {
                        ArrayList arrayList = new ArrayList();
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine == null) {
                                break;
                            }
                            JSONObject jSONObject = new JSONObject(readLine);
                            HistoryItem historyItem = new HistoryItem();
                            historyItem.setTitle(jSONObject.getString("title"));
                            historyItem.setUrl(jSONObject.getString("url"));
                            historyItem.setFolder(jSONObject.getString(BookmarkExporter.KEY_FOLDER));
                            historyItem.setPosition(jSONObject.getInt(BookmarkExporter.KEY_ORDER));
                            arrayList.add(historyItem);
                        }
                        singleSubscriber.onItem(arrayList);
                        singleSubscriber.onComplete();
                    } catch (IOException | JSONException e2) {
                        e = e2;
                        try {
                            singleSubscriber.onError(e);
                            C3245Utils.close((Closeable) bufferedReader);
                        } catch (Throwable th) {
                            th = th;
                            bufferedReader2 = bufferedReader;
                            C3245Utils.close((Closeable) bufferedReader2);
                            throw th;
                        }
                    }
                } catch (IOException | JSONException e3) {
                    Throwable th2 = e3;
                    bufferedReader = null;
                    e = th2;
                    singleSubscriber.onError(e);
                    C3245Utils.close((Closeable) bufferedReader);
                } catch (Throwable th3) {
                    th = th3;
                    C3245Utils.close((Closeable) bufferedReader2);
                    throw th;
                }
                C3245Utils.close((Closeable) bufferedReader);
            }
        });
    }

    public static File createNewExportFile() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "BookmarksExport.txt");
        int i = 0;
        while (file.exists()) {
            i++;
            File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            file = new File(externalStoragePublicDirectory, "BookmarksExport-" + i + ".txt");
        }
        return file;
    }
}

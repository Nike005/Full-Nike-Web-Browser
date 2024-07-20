package acr.browser.lightning.database.bookmark.legacy;

import acr.browser.lightning.database.HistoryItem;
import acr.browser.lightning.utils.C3245Utils;
import android.app.Application;
import android.util.Log;
import com.wnikebrow_13999769.R;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
public class LegacyBookmarkManager {
    private static final String FILE_BOOKMARKS = "bookmarks.dat";
    private static final String FOLDER = "folder";
    private static final String ORDER = "order";
    private static final String TAG = "LegacyBookmarkManager";
    private static final String TITLE = "title";
    private static final String URL = "url";

    public static List<HistoryItem> destructiveGetBookmarks(Application application) {
        BufferedReader bufferedReader;
        FileInputStream fileInputStream;
        IOException e;
        File filesDir = application.getFilesDir();
        ArrayList arrayList = new ArrayList();
        File file = new File(filesDir, FILE_BOOKMARKS);
        try {
            if (!file.exists() || !file.isFile()) {
                C3245Utils.close((Closeable) null);
                C3245Utils.close((Closeable) null);
                return arrayList;
            }
            fileInputStream = new FileInputStream(file);
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
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
                            historyItem.setFolder(jSONObject.getString(FOLDER));
                            historyItem.setPosition(jSONObject.getInt(ORDER));
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
                    C3245Utils.close((Closeable) fileInputStream);
                    file.delete();
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    C3245Utils.close((Closeable) bufferedReader);
                    C3245Utils.close((Closeable) fileInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
                th = th;
                C3245Utils.close((Closeable) bufferedReader);
                C3245Utils.close((Closeable) fileInputStream);
                throw th;
            }
            C3245Utils.close((Closeable) bufferedReader);
            C3245Utils.close((Closeable) fileInputStream);
            file.delete();
            return arrayList;
        } catch (IOException e5) {
            bufferedReader = null;
            e = e5;
            fileInputStream = null;
            Log.e(TAG, "Error reading the bookmarks file", e);
            C3245Utils.close((Closeable) bufferedReader);
            C3245Utils.close((Closeable) fileInputStream);
            file.delete();
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
            bufferedReader = null;
            th = th;
            C3245Utils.close((Closeable) bufferedReader);
            C3245Utils.close((Closeable) fileInputStream);
            throw th;
        }
    }

    private static class SortIgnoreCase implements Comparator<HistoryItem> {
        private SortIgnoreCase() {
        }

        public int compare(HistoryItem historyItem, HistoryItem historyItem2) {
            if (historyItem == null || historyItem2 == null) {
                return 0;
            }
            if (historyItem.isFolder() == historyItem2.isFolder()) {
                return historyItem.getTitle().toLowerCase(Locale.getDefault()).compareTo(historyItem2.getTitle().toLowerCase(Locale.getDefault()));
            }
            return historyItem.isFolder() ? 1 : -1;
        }
    }
}

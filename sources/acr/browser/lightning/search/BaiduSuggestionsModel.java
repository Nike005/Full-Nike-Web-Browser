package acr.browser.lightning.search;

import acr.browser.lightning.database.HistoryItem;
import acr.browser.lightning.utils.FileUtils;
import android.app.Application;
import com.wnikebrow_13999769.R;
import java.io.InputStream;
import java.util.List;
import kotlin.text.Typography;
import org.json.JSONArray;
import org.xmlpull.v1.XmlPullParser;

class BaiduSuggestionsModel extends BaseSuggestionsModel {
    private static final String ENCODING = "UTF-8";
    private static XmlPullParser sXpp;
    private final String mSearchSubtitle;

    BaiduSuggestionsModel(Application application) {
        super(application, "UTF-8");
        this.mSearchSubtitle = application.getString(R.string.suggestion);
    }

    /* access modifiers changed from: protected */
    public String createQueryUrl(String str, String str2) {
        return "http://suggestion.baidu.com/s?wd=" + str + "&action=opensearch";
    }

    /* access modifiers changed from: protected */
    public void parseResults(InputStream inputStream, List<HistoryItem> list) throws Exception {
        JSONArray jSONArray = new JSONArray(FileUtils.readStringFromStream(inputStream, "GBK")).getJSONArray(1);
        int length = jSONArray.length();
        int i = 0;
        int i2 = 0;
        while (i < length) {
            String string = jSONArray.getString(i);
            list.add(new HistoryItem(this.mSearchSubtitle + " \"" + string + Typography.quote, string, R.drawable.ic_search));
            i2++;
            if (i2 < 5) {
                i++;
            } else {
                return;
            }
        }
    }
}

package acr.browser.lightning.search;

import acr.browser.lightning.database.HistoryItem;
import android.app.Application;
import com.wnikebrow_13999769.R;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.List;
import kotlin.text.Typography;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

class GoogleSuggestionsModel extends BaseSuggestionsModel {
    private static final String ENCODING = "ISO-8859-1";
    private static XmlPullParser sXpp;
    private final String mSearchSubtitle;

    GoogleSuggestionsModel(Application application) {
        super(application, "ISO-8859-1");
        this.mSearchSubtitle = application.getString(R.string.suggestion);
    }

    /* access modifiers changed from: protected */
    public String createQueryUrl(String str, String str2) {
        return "https://suggestqueries.google.com/complete/search?output=toolbar&oe=latin1&hl=" + str2 + "&q=" + str;
    }

    /* access modifiers changed from: protected */
    public void parseResults(InputStream inputStream, List<HistoryItem> list) throws Exception {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        XmlPullParser parser = getParser();
        parser.setInput(bufferedInputStream, "ISO-8859-1");
        int i = 0;
        for (int eventType = parser.getEventType(); eventType != 1; eventType = parser.next()) {
            if (eventType == 2 && "suggestion".equals(parser.getName())) {
                String attributeValue = parser.getAttributeValue((String) null, "data");
                list.add(new HistoryItem(this.mSearchSubtitle + " \"" + attributeValue + Typography.quote, attributeValue, R.drawable.ic_search));
                i++;
                if (i >= 5) {
                    return;
                }
            }
        }
    }

    private static synchronized XmlPullParser getParser() throws XmlPullParserException {
        XmlPullParser xmlPullParser;
        synchronized (GoogleSuggestionsModel.class) {
            if (sXpp == null) {
                XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
                newInstance.setNamespaceAware(true);
                sXpp = newInstance.newPullParser();
            }
            xmlPullParser = sXpp;
        }
        return xmlPullParser;
    }
}

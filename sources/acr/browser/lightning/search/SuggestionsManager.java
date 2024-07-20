package acr.browser.lightning.search;

import acr.browser.lightning.database.HistoryItem;
import android.app.Application;
import com.anthonycr.bonsai.Single;
import com.anthonycr.bonsai.SingleAction;
import com.anthonycr.bonsai.SingleSubscriber;
import java.util.List;

class SuggestionsManager {
    /* access modifiers changed from: private */
    public static volatile boolean sIsTaskExecuting;

    SuggestionsManager() {
    }

    static boolean isRequestInProgress() {
        return sIsTaskExecuting;
    }

    static Single<List<HistoryItem>> createGoogleQueryObservable(final String str, final Application application) {
        return Single.create(new SingleAction<List<HistoryItem>>() {
            public void onSubscribe(SingleSubscriber<List<HistoryItem>> singleSubscriber) {
                boolean unused = SuggestionsManager.sIsTaskExecuting = true;
                singleSubscriber.onItem(new GoogleSuggestionsModel(application).getResults(str));
                singleSubscriber.onComplete();
                boolean unused2 = SuggestionsManager.sIsTaskExecuting = false;
            }
        });
    }

    static Single<List<HistoryItem>> createBaiduQueryObservable(final String str, final Application application) {
        return Single.create(new SingleAction<List<HistoryItem>>() {
            public void onSubscribe(SingleSubscriber<List<HistoryItem>> singleSubscriber) {
                boolean unused = SuggestionsManager.sIsTaskExecuting = true;
                singleSubscriber.onItem(new BaiduSuggestionsModel(application).getResults(str));
                singleSubscriber.onComplete();
                boolean unused2 = SuggestionsManager.sIsTaskExecuting = false;
            }
        });
    }

    static Single<List<HistoryItem>> createDuckQueryObservable(final String str, final Application application) {
        return Single.create(new SingleAction<List<HistoryItem>>() {
            public void onSubscribe(SingleSubscriber<List<HistoryItem>> singleSubscriber) {
                boolean unused = SuggestionsManager.sIsTaskExecuting = true;
                singleSubscriber.onItem(new DuckSuggestionsModel(application).getResults(str));
                singleSubscriber.onComplete();
                boolean unused2 = SuggestionsManager.sIsTaskExecuting = false;
            }
        });
    }
}

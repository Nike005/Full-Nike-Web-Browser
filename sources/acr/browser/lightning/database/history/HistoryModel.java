package acr.browser.lightning.database.history;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.database.HistoryItem;
import com.anthonycr.bonsai.Completable;
import com.anthonycr.bonsai.CompletableAction;
import com.anthonycr.bonsai.CompletableSubscriber;
import com.anthonycr.bonsai.Single;
import com.anthonycr.bonsai.SingleAction;
import com.anthonycr.bonsai.SingleSubscriber;
import java.util.List;

public final class HistoryModel {
    private HistoryModel() {
    }

    public static Completable deleteHistory() {
        return Completable.create(new CompletableAction() {
            public void onSubscribe(CompletableSubscriber completableSubscriber) {
                BrowserApp.getAppComponent().historyDatabase().deleteHistory();
                completableSubscriber.onComplete();
            }
        });
    }

    public static Completable deleteHistoryItem(final String str) {
        return Completable.create(new CompletableAction() {
            public void onSubscribe(CompletableSubscriber completableSubscriber) {
                BrowserApp.getAppComponent().historyDatabase().deleteHistoryItem(str);
                completableSubscriber.onComplete();
            }
        });
    }

    public static Completable visitHistoryItem(final String str, final String str2) {
        return Completable.create(new CompletableAction() {
            public void onSubscribe(CompletableSubscriber completableSubscriber) {
                BrowserApp.getAppComponent().historyDatabase().visitHistoryItem(str, str2);
                completableSubscriber.onComplete();
            }
        });
    }

    public static Single<List<HistoryItem>> findHistoryItemsContaining(final String str) {
        return Single.create(new SingleAction<List<HistoryItem>>() {
            public void onSubscribe(SingleSubscriber<List<HistoryItem>> singleSubscriber) {
                singleSubscriber.onItem(BrowserApp.getAppComponent().historyDatabase().findItemsContaining(str));
                singleSubscriber.onComplete();
            }
        });
    }

    public static Single<List<HistoryItem>> lastHundredVisitedHistoryItems() {
        return Single.create(new SingleAction<List<HistoryItem>>() {
            public void onSubscribe(SingleSubscriber<List<HistoryItem>> singleSubscriber) {
                singleSubscriber.onItem(BrowserApp.getAppComponent().historyDatabase().getLastHundredItems());
                singleSubscriber.onComplete();
            }
        });
    }
}

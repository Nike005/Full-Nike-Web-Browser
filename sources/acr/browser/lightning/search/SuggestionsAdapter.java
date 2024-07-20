package acr.browser.lightning.search;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.database.HistoryItem;
import acr.browser.lightning.database.bookmark.BookmarkModel;
import acr.browser.lightning.database.history.HistoryModel;
import acr.browser.lightning.preference.PreferenceManager;
import acr.browser.lightning.utils.Preconditions;
import acr.browser.lightning.utils.ThemeUtils;
import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.anthonycr.bonsai.Completable;
import com.anthonycr.bonsai.CompletableAction;
import com.anthonycr.bonsai.CompletableSubscriber;
import com.anthonycr.bonsai.Scheduler;
import com.anthonycr.bonsai.Schedulers;
import com.anthonycr.bonsai.Single;
import com.anthonycr.bonsai.SingleAction;
import com.anthonycr.bonsai.SingleOnSubscribe;
import com.anthonycr.bonsai.SingleSubscriber;
import com.wnikebrow_13999769.R;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import javax.inject.Inject;

public class SuggestionsAdapter extends BaseAdapter implements Filterable {
    private static final String CACHE_FILE_TYPE = ".sgg";
    private static final Scheduler FILTER_SCHEDULER = Schedulers.newSingleThreadedScheduler();
    private static final int MAX_SUGGESTIONS = 5;
    /* access modifiers changed from: private */
    public final List<HistoryItem> mAllBookmarks = new ArrayList(5);
    @Inject
    Application mApplication;
    private final Drawable mBookmarkDrawable;
    @Inject
    BookmarkModel mBookmarkManager;
    /* access modifiers changed from: private */
    public final List<HistoryItem> mBookmarks = new ArrayList(5);
    private final Context mContext;
    private final boolean mDarkTheme;
    /* access modifiers changed from: private */
    public final Comparator<HistoryItem> mFilterComparator = new SuggestionsComparator();
    private final List<HistoryItem> mFilteredList = new ArrayList(5);
    /* access modifiers changed from: private */
    public final List<HistoryItem> mHistory = new ArrayList(5);
    private final Drawable mHistoryDrawable;
    private boolean mIsIncognito;
    @Inject
    PreferenceManager mPreferenceManager;
    private final Drawable mSearchDrawable;
    private PreferenceManager.Suggestion mSuggestionChoice;
    /* access modifiers changed from: private */
    public final List<HistoryItem> mSuggestions = new ArrayList(5);

    public long getItemId(int i) {
        return 0;
    }

    public SuggestionsAdapter(Context context, boolean z, boolean z2) {
        boolean z3 = true;
        this.mIsIncognito = true;
        BrowserApp.getAppComponent().inject(this);
        this.mContext = context;
        if (!z && !z2) {
            z3 = false;
        }
        this.mDarkTheme = z3;
        this.mIsIncognito = z2;
        refreshPreferences();
        refreshBookmarks();
        this.mSearchDrawable = ThemeUtils.getThemedDrawable(context, R.drawable.ic_search, this.mDarkTheme);
        this.mBookmarkDrawable = ThemeUtils.getThemedDrawable(context, R.drawable.ic_bookmark, this.mDarkTheme);
        this.mHistoryDrawable = ThemeUtils.getThemedDrawable(context, R.drawable.ic_history, this.mDarkTheme);
    }

    public void refreshPreferences() {
        this.mSuggestionChoice = this.mPreferenceManager.getSearchSuggestionChoice();
    }

    public void clearCache() {
        Schedulers.m6232io().execute(new ClearCacheRunnable(this.mApplication));
    }

    public void refreshBookmarks() {
        this.mBookmarkManager.getAllBookmarks().subscribeOn(Schedulers.m6232io()).subscribe(new SingleOnSubscribe<List<HistoryItem>>() {
            public void onItem(List<HistoryItem> list) {
                Preconditions.checkNonNull(list);
                SuggestionsAdapter.this.mAllBookmarks.clear();
                SuggestionsAdapter.this.mAllBookmarks.addAll(list);
            }
        });
    }

    public int getCount() {
        return this.mFilteredList.size();
    }

    public Object getItem(int i) {
        if (i > this.mFilteredList.size() || i < 0) {
            return null;
        }
        return this.mFilteredList.get(i);
    }

    private static class SuggestionHolder {
        final ImageView mImage;
        final TextView mTitle;
        final TextView mUrl;

        SuggestionHolder(View view) {
            this.mTitle = (TextView) view.findViewById(R.id.title);
            this.mUrl = (TextView) view.findViewById(R.id.url);
            this.mImage = (ImageView) view.findViewById(R.id.suggestionIcon);
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        SuggestionHolder suggestionHolder;
        Drawable drawable;
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(R.layout.two_line_autocomplete, viewGroup, false);
            suggestionHolder = new SuggestionHolder(view);
            view.setTag(suggestionHolder);
        } else {
            suggestionHolder = (SuggestionHolder) view.getTag();
        }
        HistoryItem historyItem = this.mFilteredList.get(i);
        suggestionHolder.mTitle.setText(historyItem.getTitle());
        suggestionHolder.mUrl.setText(historyItem.getUrl());
        if (this.mDarkTheme) {
            suggestionHolder.mTitle.setTextColor(-1);
        }
        int imageId = historyItem.getImageId();
        if (imageId == R.drawable.ic_bookmark) {
            drawable = this.mBookmarkDrawable;
        } else if (imageId == R.drawable.ic_history) {
            drawable = this.mHistoryDrawable;
        } else if (imageId != R.drawable.ic_search) {
            drawable = this.mSearchDrawable;
        } else {
            drawable = this.mSearchDrawable;
        }
        suggestionHolder.mImage.setImageDrawable(drawable);
        return view;
    }

    public Filter getFilter() {
        return new SearchFilter(this);
    }

    /* access modifiers changed from: private */
    public synchronized void publishResults(List<HistoryItem> list) {
        this.mFilteredList.clear();
        this.mFilteredList.addAll(list);
        notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public void clearSuggestions() {
        Completable.create(new CompletableAction() {
            public void onSubscribe(CompletableSubscriber completableSubscriber) {
                SuggestionsAdapter.this.mBookmarks.clear();
                SuggestionsAdapter.this.mHistory.clear();
                SuggestionsAdapter.this.mSuggestions.clear();
                completableSubscriber.onComplete();
            }
        }).subscribeOn(FILTER_SCHEDULER).observeOn(Schedulers.main()).subscribe();
    }

    /* access modifiers changed from: private */
    public void combineResults(final List<HistoryItem> list, final List<HistoryItem> list2, final List<HistoryItem> list3) {
        Single.create(new SingleAction<List<HistoryItem>>() {
            public void onSubscribe(SingleSubscriber<List<HistoryItem>> singleSubscriber) {
                ArrayList arrayList = new ArrayList(5);
                if (list != null) {
                    SuggestionsAdapter.this.mBookmarks.clear();
                    SuggestionsAdapter.this.mBookmarks.addAll(list);
                }
                if (list2 != null) {
                    SuggestionsAdapter.this.mHistory.clear();
                    SuggestionsAdapter.this.mHistory.addAll(list2);
                }
                if (list3 != null) {
                    SuggestionsAdapter.this.mSuggestions.clear();
                    SuggestionsAdapter.this.mSuggestions.addAll(list3);
                }
                Iterator it = SuggestionsAdapter.this.mBookmarks.iterator();
                Iterator it2 = SuggestionsAdapter.this.mHistory.iterator();
                ListIterator listIterator = SuggestionsAdapter.this.mSuggestions.listIterator();
                while (arrayList.size() < 5 && (it.hasNext() || listIterator.hasNext() || it2.hasNext())) {
                    if (it.hasNext()) {
                        arrayList.add(it.next());
                    }
                    if (listIterator.hasNext() && arrayList.size() < 5) {
                        arrayList.add(listIterator.next());
                    }
                    if (it2.hasNext() && arrayList.size() < 5) {
                        arrayList.add(it2.next());
                    }
                }
                Collections.sort(arrayList, SuggestionsAdapter.this.mFilterComparator);
                singleSubscriber.onItem(arrayList);
                singleSubscriber.onComplete();
            }
        }).subscribeOn(FILTER_SCHEDULER).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<List<HistoryItem>>() {
            public void onItem(List<HistoryItem> list) {
                Preconditions.checkNonNull(list);
                SuggestionsAdapter.this.publishResults(list);
            }
        });
    }

    /* access modifiers changed from: private */
    public Single<List<HistoryItem>> getBookmarksForQuery(final String str) {
        return Single.create(new SingleAction<List<HistoryItem>>() {
            public void onSubscribe(SingleSubscriber<List<HistoryItem>> singleSubscriber) {
                ArrayList arrayList = new ArrayList(5);
                int i = 0;
                for (int i2 = 0; i2 < SuggestionsAdapter.this.mAllBookmarks.size() && i < 5; i2++) {
                    if (((HistoryItem) SuggestionsAdapter.this.mAllBookmarks.get(i2)).getTitle().toLowerCase(Locale.getDefault()).startsWith(str)) {
                        arrayList.add(SuggestionsAdapter.this.mAllBookmarks.get(i2));
                    } else if (((HistoryItem) SuggestionsAdapter.this.mAllBookmarks.get(i2)).getUrl().contains(str)) {
                        arrayList.add(SuggestionsAdapter.this.mAllBookmarks.get(i2));
                    }
                    i++;
                }
                singleSubscriber.onItem(arrayList);
                singleSubscriber.onComplete();
            }
        });
    }

    /* access modifiers changed from: private */
    public Single<List<HistoryItem>> getSuggestionsForQuery(String str) {
        if (this.mSuggestionChoice == PreferenceManager.Suggestion.SUGGESTION_GOOGLE) {
            return SuggestionsManager.createGoogleQueryObservable(str, this.mApplication);
        }
        if (this.mSuggestionChoice == PreferenceManager.Suggestion.SUGGESTION_DUCK) {
            return SuggestionsManager.createDuckQueryObservable(str, this.mApplication);
        }
        if (this.mSuggestionChoice == PreferenceManager.Suggestion.SUGGESTION_BAIDU) {
            return SuggestionsManager.createBaiduQueryObservable(str, this.mApplication);
        }
        return Single.empty();
    }

    /* access modifiers changed from: private */
    public boolean shouldRequestNetwork() {
        return !this.mIsIncognito && this.mSuggestionChoice != PreferenceManager.Suggestion.SUGGESTION_NONE;
    }

    private static class SearchFilter extends Filter {
        /* access modifiers changed from: private */
        public final SuggestionsAdapter mSuggestionsAdapter;

        SearchFilter(SuggestionsAdapter suggestionsAdapter) {
            this.mSuggestionsAdapter = suggestionsAdapter;
        }

        /* access modifiers changed from: protected */
        public Filter.FilterResults performFiltering(CharSequence charSequence) {
            Filter.FilterResults filterResults = new Filter.FilterResults();
            if (charSequence == null || charSequence.length() == 0) {
                this.mSuggestionsAdapter.clearSuggestions();
                return filterResults;
            }
            String trim = charSequence.toString().toLowerCase(Locale.getDefault()).trim();
            if (this.mSuggestionsAdapter.shouldRequestNetwork() && !SuggestionsManager.isRequestInProgress()) {
                this.mSuggestionsAdapter.getSuggestionsForQuery(trim).subscribeOn(Schedulers.worker()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<List<HistoryItem>>() {
                    public void onItem(List<HistoryItem> list) {
                        SearchFilter.this.mSuggestionsAdapter.combineResults((List<HistoryItem>) null, (List<HistoryItem>) null, list);
                    }
                });
            }
            this.mSuggestionsAdapter.getBookmarksForQuery(trim).subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<List<HistoryItem>>() {
                public void onItem(List<HistoryItem> list) {
                    SearchFilter.this.mSuggestionsAdapter.combineResults(list, (List<HistoryItem>) null, (List<HistoryItem>) null);
                }
            });
            HistoryModel.findHistoryItemsContaining(trim).subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<List<HistoryItem>>() {
                public void onItem(List<HistoryItem> list) {
                    SearchFilter.this.mSuggestionsAdapter.combineResults((List<HistoryItem>) null, list, (List<HistoryItem>) null);
                }
            });
            filterResults.count = 1;
            return filterResults;
        }

        public CharSequence convertResultToString(Object obj) {
            return ((HistoryItem) obj).getUrl();
        }

        /* access modifiers changed from: protected */
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            this.mSuggestionsAdapter.combineResults((List<HistoryItem>) null, (List<HistoryItem>) null, (List<HistoryItem>) null);
        }
    }

    private static class ClearCacheRunnable implements Runnable {
        private final Application app;

        ClearCacheRunnable(Application application) {
            this.app = application;
        }

        public void run() {
            File file = new File(this.app.getCacheDir().toString());
            for (String str : file.list(new NameFilter())) {
                new File(file.getPath() + str).delete();
            }
        }

        private static class NameFilter implements FilenameFilter {
            private NameFilter() {
            }

            public boolean accept(File file, String str) {
                return str.endsWith(SuggestionsAdapter.CACHE_FILE_TYPE);
            }
        }
    }

    private static class SuggestionsComparator implements Comparator<HistoryItem> {
        private SuggestionsComparator() {
        }

        public int compare(HistoryItem historyItem, HistoryItem historyItem2) {
            if (historyItem.getImageId() == historyItem2.getImageId()) {
                return 0;
            }
            if (historyItem.getImageId() == R.drawable.ic_bookmark) {
                return -1;
            }
            if (historyItem2.getImageId() != R.drawable.ic_bookmark && historyItem.getImageId() == R.drawable.ic_history) {
                return -1;
            }
            return 1;
        }
    }
}

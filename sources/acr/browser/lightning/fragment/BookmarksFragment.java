package acr.browser.lightning.fragment;

import acr.browser.lightning.activity.BookmarkUiModel;
import acr.browser.lightning.activity.ReadingActivity;
import acr.browser.lightning.activity.TabsManager;
import acr.browser.lightning.animation.AnimationUtils;
import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.browser.BookmarksView;
import acr.browser.lightning.constant.Constants;
import acr.browser.lightning.controller.UIController;
import acr.browser.lightning.database.HistoryItem;
import acr.browser.lightning.database.bookmark.BookmarkModel;
import acr.browser.lightning.dialog.LightningDialogBuilder;
import acr.browser.lightning.favicon.FaviconModel;
import acr.browser.lightning.preference.PreferenceManager;
import acr.browser.lightning.utils.ImageLoader;
import acr.browser.lightning.utils.Preconditions;
import acr.browser.lightning.utils.SubscriptionUtils;
import acr.browser.lightning.view.LightningView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.C4621Utils;
import com.anthonycr.bonsai.Schedulers;
import com.anthonycr.bonsai.SingleOnSubscribe;
import com.anthonycr.bonsai.Subscription;
import com.wnikebrow_13999769.R;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Inject;

public class BookmarksFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener, BookmarksView {
    private static final String INCOGNITO_MODE = "BookmarksFragment.INCOGNITO_MODE";
    private static final String TAG = "BookmarksFragment";
    /* access modifiers changed from: private */
    public static int theme;
    private BookmarkListAdapter mBookmarkAdapter;
    @BindView(2131296560)
    ImageView mBookmarkImage;
    @Inject
    BookmarkModel mBookmarkManager;
    @BindView(2131296769)
    ImageView mBookmarkTitleImage;
    /* access modifiers changed from: private */
    public Subscription mBookmarkUpdateSubscription;
    @Inject
    LightningDialogBuilder mBookmarksDialogBuilder;
    @BindView(2131296720)
    RecyclerView mBookmarksListView;
    /* access modifiers changed from: private */
    public Subscription mBookmarksSubscription;
    @Inject
    FaviconModel mFaviconModel;
    private Bitmap mFolderBitmap;
    /* access modifiers changed from: private */
    public Subscription mFoldersSubscription;
    /* access modifiers changed from: private */
    public int mIconColor;
    private boolean mIsIncognito;
    private final OnItemClickListener mItemClickListener = new OnItemClickListener() {
        public void onItemClick(HistoryItem historyItem) {
            if (historyItem.isFolder()) {
                BookmarksFragment bookmarksFragment = BookmarksFragment.this;
                int unused = bookmarksFragment.mScrollIndex = ((LinearLayoutManager) bookmarksFragment.mBookmarksListView.getLayoutManager()).findFirstVisibleItemPosition();
                BookmarksFragment.this.setBookmarksShown(historyItem.getTitle(), true);
                return;
            }
            BookmarksFragment.this.mUiController.bookmarkItemClicked(historyItem);
        }
    };
    private final OnItemLongClickListener mItemLongClickListener = new OnItemLongClickListener() {
        public boolean onItemLongClick(HistoryItem historyItem) {
            BookmarksFragment.this.handleLongPress(historyItem);
            return true;
        }
    };
    @Inject
    PreferenceManager mPreferenceManager;
    /* access modifiers changed from: private */
    public int mScrollIndex;
    private TabsManager mTabsManager;
    /* access modifiers changed from: private */
    public UIController mUiController;
    /* access modifiers changed from: private */
    public final BookmarkUiModel mUiModel = new BookmarkUiModel();
    private Unbinder mUnbinder;
    private Bitmap mWebpageBitmap;

    interface OnItemClickListener {
        void onItemClick(HistoryItem historyItem);
    }

    interface OnItemLongClickListener {
        boolean onItemLongClick(HistoryItem historyItem);
    }

    public boolean onLongClick(View view) {
        return false;
    }

    public class BookmarkViewHolder_ViewBinding implements Unbinder {
        private BookmarkViewHolder target;

        public BookmarkViewHolder_ViewBinding(BookmarkViewHolder bookmarkViewHolder, View view) {
            this.target = bookmarkViewHolder;
            bookmarkViewHolder.txtTitle = (TextView) C4621Utils.findRequiredViewAsType(view, R.id.textBookmark, "field 'txtTitle'", TextView.class);
            bookmarkViewHolder.favicon = (ImageView) C4621Utils.findRequiredViewAsType(view, R.id.faviconBookmark, "field 'favicon'", ImageView.class);
        }

        public void unbind() {
            BookmarkViewHolder bookmarkViewHolder = this.target;
            if (bookmarkViewHolder != null) {
                this.target = null;
                bookmarkViewHolder.txtTitle = null;
                bookmarkViewHolder.favicon = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public static BookmarksFragment createFragment(boolean z) {
        BookmarksFragment bookmarksFragment = new BookmarksFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(INCOGNITO_MODE, z);
        bookmarksFragment.setArguments(bundle);
        return bookmarksFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BrowserApp.getAppComponent().inject(this);
        Bundle arguments = getArguments();
        Context context = getContext();
        UIController uIController = (UIController) context;
        this.mUiController = uIController;
        this.mTabsManager = uIController.getTabModel();
        boolean z = arguments.getBoolean(INCOGNITO_MODE, false);
        this.mIsIncognito = z;
        if (z) {
            theme = 2;
        } else {
            theme = this.mPreferenceManager.getUseTheme();
        }
        this.mWebpageBitmap = BrowserApp.getThemeManager().getThemedBitmap(context, R.drawable.bookmark_outline, theme);
        this.mFolderBitmap = BrowserApp.getThemeManager().getThemedBitmap(context, R.drawable.ic_folder, theme);
        this.mIconColor = BrowserApp.getThemeManager().getIconColor(theme);
    }

    private TabsManager getTabsManager() {
        if (this.mTabsManager == null) {
            this.mTabsManager = this.mUiController.getTabModel();
        }
        return this.mTabsManager;
    }

    public void onResume() {
        super.onResume();
        if (this.mBookmarkAdapter != null) {
            setBookmarksShown((String) null, false);
        }
    }

    public void update() {
        if (this.mBookmarkAdapter != null) {
            setBookmarksShown((String) null, false);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.bookmark_drawer, viewGroup, false);
        this.mUnbinder = ButterKnife.bind((Object) this, inflate);
        this.mBookmarkTitleImage.setColorFilter(this.mIconColor, PorterDuff.Mode.SRC_IN);
        inflate.findViewById(R.id.bookmark_back_button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!BookmarksFragment.this.mUiModel.isRootFolder()) {
                    BookmarksFragment.this.setBookmarksShown((String) null, true);
                    BookmarksFragment.this.mBookmarksListView.getLayoutManager().scrollToPosition(BookmarksFragment.this.mScrollIndex);
                }
            }
        });
        inflate.findViewById(R.id.bookmark_title).setBackgroundColor(BrowserApp.getThemeManager().getPrimarydarkColor(theme));
        ((TextView) inflate.findViewById(R.id.bookmarksDrawerTitle)).setTextColor(BrowserApp.getThemeManager().getIconColor(theme));
        inflate.findViewById(R.id.right_drawer_list).setBackgroundColor(BrowserApp.getThemeManager().getPrimaryColor(theme));
        inflate.findViewById(R.id.bottomPanel).setBackgroundColor(BrowserApp.getThemeManager().getPrimarydarkColor(theme));
        setupNavigationButton(inflate, R.id.action_add_bookmark, R.id.icon_star);
        setupNavigationButton(inflate, R.id.action_reading, R.id.icon_reading);
        setupNavigationButton(inflate, R.id.action_toggle_desktop, R.id.icon_desktop);
        BookmarkListAdapter bookmarkListAdapter = new BookmarkListAdapter(getActivity(), this.mFaviconModel, this.mFolderBitmap, this.mWebpageBitmap);
        this.mBookmarkAdapter = bookmarkListAdapter;
        bookmarkListAdapter.setOnItemClickListener(this.mItemClickListener);
        this.mBookmarkAdapter.setOnItemLongClickListener(this.mItemLongClickListener);
        this.mBookmarksListView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.mBookmarksListView.setAdapter(this.mBookmarkAdapter);
        setBookmarksShown((String) null, true);
        return inflate;
    }

    public void onDestroyView() {
        super.onDestroyView();
        SubscriptionUtils.safeUnsubscribe(this.mBookmarksSubscription);
        SubscriptionUtils.safeUnsubscribe(this.mFoldersSubscription);
        SubscriptionUtils.safeUnsubscribe(this.mBookmarkUpdateSubscription);
        BookmarkListAdapter bookmarkListAdapter = this.mBookmarkAdapter;
        if (bookmarkListAdapter != null) {
            bookmarkListAdapter.cleanupSubscriptions();
        }
        Unbinder unbinder = this.mUnbinder;
        if (unbinder != null) {
            unbinder.unbind();
            this.mUnbinder = null;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        SubscriptionUtils.safeUnsubscribe(this.mBookmarksSubscription);
        SubscriptionUtils.safeUnsubscribe(this.mFoldersSubscription);
        SubscriptionUtils.safeUnsubscribe(this.mBookmarkUpdateSubscription);
        BookmarkListAdapter bookmarkListAdapter = this.mBookmarkAdapter;
        if (bookmarkListAdapter != null) {
            bookmarkListAdapter.cleanupSubscriptions();
        }
    }

    public void reinitializePreferences() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            boolean z = getArguments().getBoolean(INCOGNITO_MODE, false);
            this.mIsIncognito = z;
            if (z) {
                theme = 2;
            } else {
                theme = this.mPreferenceManager.getUseTheme();
            }
            this.mWebpageBitmap = BrowserApp.getThemeManager().getThemedBitmap(activity, R.drawable.bookmark_outline, theme);
            this.mFolderBitmap = BrowserApp.getThemeManager().getThemedBitmap(activity, R.drawable.ic_folder, theme);
            this.mIconColor = BrowserApp.getThemeManager().getIconColor(theme);
        }
    }

    private void updateBookmarkIndicator(String str) {
        SubscriptionUtils.safeUnsubscribe(this.mBookmarkUpdateSubscription);
        this.mBookmarkUpdateSubscription = this.mBookmarkManager.isBookmark(str).subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<Boolean>() {
            public void onItem(Boolean bool) {
                Subscription unused = BookmarksFragment.this.mBookmarkUpdateSubscription = null;
                Preconditions.checkNonNull(bool);
                FragmentActivity activity = BookmarksFragment.this.getActivity();
                if (BookmarksFragment.this.mBookmarkImage != null && activity != null) {
                    if (!bool.booleanValue()) {
                        BookmarksFragment.this.mBookmarkImage.setImageResource(R.drawable.ic_action_star);
                        BookmarksFragment.this.mBookmarkImage.setColorFilter(BookmarksFragment.this.mIconColor, PorterDuff.Mode.SRC_IN);
                        return;
                    }
                    BookmarksFragment.this.mBookmarkImage.setImageResource(R.drawable.ic_bookmark);
                    BookmarksFragment.this.mBookmarkImage.setColorFilter(BrowserApp.getThemeManager().getAccentColor(BookmarksFragment.theme), PorterDuff.Mode.SRC_IN);
                }
            }
        });
    }

    public void handleBookmarkDeleted(HistoryItem historyItem) {
        if (historyItem.isFolder()) {
            setBookmarksShown((String) null, false);
        } else {
            this.mBookmarkAdapter.deleteItem(historyItem);
        }
    }

    /* access modifiers changed from: private */
    public void setBookmarksShown(final String str, final boolean z) {
        SubscriptionUtils.safeUnsubscribe(this.mBookmarksSubscription);
        this.mBookmarksSubscription = this.mBookmarkManager.getBookmarksFromFolderSorted(str).subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<List<HistoryItem>>() {
            public void onItem(final List<HistoryItem> list) {
                Subscription unused = BookmarksFragment.this.mBookmarksSubscription = null;
                Preconditions.checkNonNull(list);
                BookmarksFragment.this.mUiModel.setCurrentFolder(str);
                if (str == null) {
                    SubscriptionUtils.safeUnsubscribe(BookmarksFragment.this.mFoldersSubscription);
                    BookmarksFragment bookmarksFragment = BookmarksFragment.this;
                    Subscription unused2 = bookmarksFragment.mFoldersSubscription = bookmarksFragment.mBookmarkManager.getFoldersSorted().subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<List<HistoryItem>>() {
                        public void onItem(List<HistoryItem> list) {
                            Subscription unused = BookmarksFragment.this.mFoldersSubscription = null;
                            Preconditions.checkNonNull(list);
                            list.addAll(list);
                            BookmarksFragment.this.setBookmarkDataSet(list, z);
                        }
                    });
                    return;
                }
                BookmarksFragment.this.setBookmarkDataSet(list, z);
            }
        });
    }

    /* access modifiers changed from: private */
    public void setBookmarkDataSet(List<HistoryItem> list, boolean z) {
        this.mBookmarkAdapter.updateItems(list);
        int i = this.mUiModel.isRootFolder() ? R.drawable.ic_action_star : R.drawable.ic_action_back;
        if (z) {
            this.mBookmarkTitleImage.startAnimation(AnimationUtils.createRotationTransitionAnimation(this.mBookmarkTitleImage, i));
        } else {
            this.mBookmarkTitleImage.setImageResource(i);
        }
    }

    private void setupNavigationButton(View view, int i, int i2) {
        FrameLayout frameLayout = (FrameLayout) view.findViewById(i);
        frameLayout.setOnClickListener(this);
        frameLayout.setOnLongClickListener(this);
        ((ImageView) view.findViewById(i2)).setColorFilter(this.mIconColor, PorterDuff.Mode.SRC_IN);
    }

    /* access modifiers changed from: private */
    public void handleLongPress(HistoryItem historyItem) {
        if (historyItem.isFolder()) {
            this.mBookmarksDialogBuilder.showBookmarkFolderLongPressedDialog(getActivity(), this.mUiController, historyItem);
        } else {
            this.mBookmarksDialogBuilder.showLongPressedDialogForBookmarkUrl((Activity) getActivity(), this.mUiController, historyItem);
        }
    }

    public void onClick(View view) {
        LightningView currentTab;
        int id = view.getId();
        if (id == R.id.action_add_bookmark) {
            this.mUiController.bookmarkButtonClicked();
        } else if (id == R.id.action_reading) {
            LightningView currentTab2 = getTabsManager().getCurrentTab();
            if (currentTab2 != null) {
                Intent intent = new Intent(getActivity(), ReadingActivity.class);
                intent.putExtra(Constants.LOAD_READING_URL, currentTab2.getUrl());
                startActivity(intent);
            }
        } else if (id == R.id.action_toggle_desktop && (currentTab = getTabsManager().getCurrentTab()) != null) {
            currentTab.toggleDesktopUA(getActivity());
            currentTab.reload();
        }
    }

    public void navigateBack() {
        if (this.mUiModel.isRootFolder()) {
            this.mUiController.closeBookmarksDrawer();
            return;
        }
        setBookmarksShown((String) null, true);
        this.mBookmarksListView.getLayoutManager().scrollToPosition(this.mScrollIndex);
    }

    public void handleUpdatedUrl(String str) {
        updateBookmarkIndicator(str);
        setBookmarksShown(this.mUiModel.getCurrentFolder(), false);
    }

    static class BookmarkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private final BookmarkListAdapter adapter;
        @BindView(2131296526)
        ImageView favicon;
        private final OnItemClickListener onItemClickListener;
        private final OnItemLongClickListener onItemLongClickListener;
        @BindView(2131296812)
        TextView txtTitle;

        BookmarkViewHolder(View view, BookmarkListAdapter bookmarkListAdapter, OnItemLongClickListener onItemLongClickListener2, OnItemClickListener onItemClickListener2) {
            super(view);
            ButterKnife.bind((Object) this, view);
            this.adapter = bookmarkListAdapter;
            this.onItemClickListener = onItemClickListener2;
            this.onItemLongClickListener = onItemLongClickListener2;
            view.setOnLongClickListener(this);
            view.setOnClickListener(this);
        }

        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            OnItemClickListener onItemClickListener2 = this.onItemClickListener;
            if (onItemClickListener2 != null && ((long) adapterPosition) != -1) {
                onItemClickListener2.onItemClick(this.adapter.itemAt(adapterPosition));
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:2:0x0007, code lost:
            r0 = r2.onItemLongClickListener;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onLongClick(android.view.View r3) {
            /*
                r2 = this;
                int r3 = r2.getAdapterPosition()
                r0 = -1
                if (r3 == r0) goto L_0x0019
                acr.browser.lightning.fragment.BookmarksFragment$OnItemLongClickListener r0 = r2.onItemLongClickListener
                if (r0 == 0) goto L_0x0019
                acr.browser.lightning.fragment.BookmarksFragment$BookmarkListAdapter r1 = r2.adapter
                acr.browser.lightning.database.HistoryItem r3 = r1.itemAt(r3)
                boolean r3 = r0.onItemLongClick(r3)
                if (r3 == 0) goto L_0x0019
                r3 = 1
                goto L_0x001a
            L_0x0019:
                r3 = 0
            L_0x001a:
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.fragment.BookmarksFragment.BookmarkViewHolder.onLongClick(android.view.View):boolean");
        }
    }

    private static class BookmarkListAdapter extends RecyclerView.Adapter<BookmarkViewHolder> {
        private Context context;
        /* access modifiers changed from: private */
        public List<HistoryItem> mBookmarks = new ArrayList();
        /* access modifiers changed from: private */
        public final Map<String, Subscription> mFaviconFetchSubscriptions = new ConcurrentHashMap();
        private final FaviconModel mFaviconModel;
        private final Bitmap mFolderBitmap;
        private OnItemClickListener mOnItemClickListener;
        private OnItemLongClickListener mOnItemLongCLickListener;
        private final Bitmap mWebpageBitmap;

        BookmarkListAdapter(Context context2, FaviconModel faviconModel, Bitmap bitmap, Bitmap bitmap2) {
            this.context = context2;
            this.mFaviconModel = faviconModel;
            this.mFolderBitmap = bitmap;
            this.mWebpageBitmap = bitmap2;
        }

        /* access modifiers changed from: package-private */
        public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
            this.mOnItemLongCLickListener = onItemLongClickListener;
        }

        /* access modifiers changed from: package-private */
        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        /* access modifiers changed from: package-private */
        public HistoryItem itemAt(int i) {
            return this.mBookmarks.get(i);
        }

        /* access modifiers changed from: package-private */
        public void deleteItem(HistoryItem historyItem) {
            ArrayList arrayList = new ArrayList(this.mBookmarks);
            arrayList.remove(historyItem);
            updateItems(arrayList);
        }

        /* access modifiers changed from: package-private */
        public void updateItems(List<HistoryItem> list) {
            final List<HistoryItem> list2 = this.mBookmarks;
            this.mBookmarks = list;
            DiffUtil.calculateDiff(new DiffUtil.Callback() {
                public int getOldListSize() {
                    return list2.size();
                }

                public int getNewListSize() {
                    return BookmarkListAdapter.this.mBookmarks.size();
                }

                public boolean areItemsTheSame(int i, int i2) {
                    return ((HistoryItem) list2.get(i)).equals(BookmarkListAdapter.this.mBookmarks.get(i2));
                }

                public boolean areContentsTheSame(int i, int i2) {
                    return ((HistoryItem) list2.get(i)).equals(BookmarkListAdapter.this.mBookmarks.get(i2));
                }
            }).dispatchUpdatesTo((RecyclerView.Adapter) this);
        }

        /* access modifiers changed from: package-private */
        public void cleanupSubscriptions() {
            for (Subscription unsubscribe : this.mFaviconFetchSubscriptions.values()) {
                unsubscribe.unsubscribe();
            }
            this.mFaviconFetchSubscriptions.clear();
        }

        public BookmarkViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new BookmarkViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bookmark_list_item, viewGroup, false), this, this.mOnItemLongCLickListener, this.mOnItemClickListener);
        }

        public void onViewRecycled(BookmarkViewHolder bookmarkViewHolder) {
            super.onViewRecycled(bookmarkViewHolder);
        }

        public void onBindViewHolder(final BookmarkViewHolder bookmarkViewHolder, int i) {
            ViewCompat.jumpDrawablesToCurrentState(bookmarkViewHolder.itemView);
            final HistoryItem historyItem = this.mBookmarks.get(i);
            bookmarkViewHolder.txtTitle.setTextColor(BrowserApp.getThemeManager().getIconColor(BookmarksFragment.theme));
            bookmarkViewHolder.txtTitle.setText(historyItem.getTitle());
            if (historyItem.isFolder()) {
                bookmarkViewHolder.favicon.setImageBitmap(this.mFolderBitmap);
            } else if (historyItem.getImageUrl() == null || historyItem.getImageUrl().equals("")) {
                if (historyItem.getBitmap() == null) {
                    bookmarkViewHolder.favicon.setImageBitmap(this.mWebpageBitmap);
                    bookmarkViewHolder.favicon.setTag(Integer.valueOf(historyItem.getUrl().hashCode()));
                    final String url = historyItem.getUrl();
                    SubscriptionUtils.safeUnsubscribe(this.mFaviconFetchSubscriptions.get(url));
                    this.mFaviconFetchSubscriptions.put(url, this.mFaviconModel.faviconForUrl(url, this.mWebpageBitmap, true).subscribeOn(Schedulers.worker()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<Bitmap>() {
                        public void onItem(Bitmap bitmap) {
                            BookmarkListAdapter.this.mFaviconFetchSubscriptions.remove(url);
                            Object tag = bookmarkViewHolder.favicon.getTag();
                            if (tag != null && tag.equals(Integer.valueOf(url.hashCode()))) {
                                bookmarkViewHolder.favicon.setImageBitmap(bitmap);
                            }
                            historyItem.setBitmap(bitmap);
                        }
                    }));
                    return;
                }
                bookmarkViewHolder.favicon.setImageBitmap(historyItem.getBitmap());
            } else if (historyItem.getImageUrl().startsWith(com.mopub.common.Constants.HTTP)) {
                ImageLoader.getInstance().loadImage(historyItem.getImageUrl(), new ImageLoader.ImageLoadedListener() {
                    public void onImageLoaded(Bitmap bitmap) {
                        bookmarkViewHolder.favicon.setImageBitmap(bitmap);
                    }
                });
            } else {
                try {
                    Bitmap decodeStream = BitmapFactory.decodeStream(this.context.getAssets().open(historyItem.getImageUrl()));
                    decodeStream.setDensity(0);
                    bookmarkViewHolder.favicon.setImageDrawable(new BitmapDrawable(this.context.getResources(), decodeStream));
                } catch (IOException unused) {
                    Log.w("favicon", "Can't get icon");
                }
            }
        }

        public int getItemCount() {
            return this.mBookmarks.size();
        }
    }
}

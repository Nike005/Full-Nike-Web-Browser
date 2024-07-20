package acr.browser.lightning.view;

import acr.browser.lightning.activity.BrowserActivity;
import acr.browser.lightning.activity.ThemableBrowserActivity;
import acr.browser.lightning.adapter.HistoryAdapter;
import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.config.Config;
import acr.browser.lightning.controller.UIController;
import acr.browser.lightning.database.HistoryItem;
import acr.browser.lightning.database.bookmark.BookmarkModel;
import acr.browser.lightning.database.downloads.DownloadItem;
import acr.browser.lightning.database.downloads.DownloadsModel;
import acr.browser.lightning.database.history.HistoryModel;
import acr.browser.lightning.domain.GeoData;
import acr.browser.lightning.domain.NewsCategory;
import acr.browser.lightning.domain.WeatherData;
import acr.browser.lightning.notifiction.WeatherNotification;
import acr.browser.lightning.preference.PreferenceManager;
import acr.browser.lightning.utils.BookmarkTouchHelper;
import acr.browser.lightning.utils.ColorPicker;
import acr.browser.lightning.utils.HomePageWidget;
import acr.browser.lightning.utils.ImageLoader;
import acr.browser.lightning.utils.ItemClickSupport;
import acr.browser.lightning.utils.NewsApi;
import acr.browser.lightning.utils.StartPageLoader;
import acr.browser.lightning.view.HomepageView;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.C4621Utils;
import com.anthonycr.bonsai.CompletableOnSubscribe;
import com.anthonycr.bonsai.Schedulers;
import com.anthonycr.bonsai.SingleOnSubscribe;
import com.mopub.common.Constants;
import com.wnikebrow_13999769.R;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomePageMainTab {
    private static final long WEATHER_UPDATE_PERIOD = 1200000;
    private Activity activity;
    private View view;
    /* access modifiers changed from: private */
    public WeatherData weatherData;
    @BindView(2131296872)
    LinearLayout widgetsPanel;

    public interface BookmarkHelper {
        void itemRemoved(HistoryItem historyItem);
    }

    public interface ItemTouchHelperAdapter {
        void onItemDismiss(int i);

        boolean onItemMove(int i, int i2);
    }

    public static class DownloadsAdapter extends RecyclerView.Adapter<ViewHolder> {
        private Context context;
        private final List<DownloadItem> downloadItemList = new ArrayList();
        private int theme;

        public long getItemId(int i) {
            return (long) i;
        }

        public class ViewHolder_ViewBinding implements Unbinder {
            private ViewHolder target;

            public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
                this.target = viewHolder;
                viewHolder.name = (TextView) C4621Utils.findRequiredViewAsType(view, R.id.name, "field 'name'", TextView.class);
                viewHolder.url = (TextView) C4621Utils.findRequiredViewAsType(view, R.id.url, "field 'url'", TextView.class);
            }

            public void unbind() {
                ViewHolder viewHolder = this.target;
                if (viewHolder != null) {
                    this.target = null;
                    viewHolder.name = null;
                    viewHolder.url = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        public DownloadsAdapter(Context context2, int i) {
            this.context = context2;
            this.theme = i;
        }

        public void setBookmarks(List<DownloadItem> list) {
            this.downloadItemList.clear();
            this.downloadItemList.addAll(list);
        }

        public List<DownloadItem> getDownloadItemList() {
            return this.downloadItemList;
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.downloads_list_item, viewGroup, false));
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            DownloadItem downloadItem = this.downloadItemList.get(i);
            viewHolder.name.setText(downloadItem.getTitle());
            viewHolder.name.setTextColor(BrowserApp.getThemeManager().getIconColor(this.theme));
            viewHolder.url.setText(downloadItem.getUrl());
            viewHolder.url.setTextColor(BrowserApp.getThemeManager().getDisabledIconColor(this.theme));
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(2131296669)
            TextView name;
            @BindView(2131296856)
            TextView url;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind((Object) this, view);
            }
        }

        public int getItemCount() {
            return this.downloadItemList.size();
        }
    }

    public static class BookmarkAdapter extends RecyclerView.Adapter<ViewHolder> implements ItemTouchHelperAdapter {
        private BookmarkHelper bookmarkHelper;
        BookmarkModel bookmarkModel;
        private Context context;
        private final List<HistoryItem> historyItemList = new ArrayList();
        private int theme;

        public class ViewHolder_ViewBinding implements Unbinder {
            private ViewHolder target;

            public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
                this.target = viewHolder;
                viewHolder.nameBook = (TextView) C4621Utils.findRequiredViewAsType(view, R.id.textBookmark, "field 'nameBook'", TextView.class);
                viewHolder.favicon = (ImageView) C4621Utils.findRequiredViewAsType(view, R.id.faviconBookmark, "field 'favicon'", ImageView.class);
                viewHolder.letter = (TextView) C4621Utils.findRequiredViewAsType(view, R.id.letter, "field 'letter'", TextView.class);
                viewHolder.background = (ViewGroup) C4621Utils.findRequiredViewAsType(view, R.id.background, "field 'background'", ViewGroup.class);
            }

            public void unbind() {
                ViewHolder viewHolder = this.target;
                if (viewHolder != null) {
                    this.target = null;
                    viewHolder.nameBook = null;
                    viewHolder.favicon = null;
                    viewHolder.letter = null;
                    viewHolder.background = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        public BookmarkAdapter(Context context2, int i, BookmarkModel bookmarkModel2) {
            this.context = context2;
            this.theme = i;
            this.bookmarkModel = bookmarkModel2;
        }

        public void setBookmarkHelper(BookmarkHelper bookmarkHelper2) {
            this.bookmarkHelper = bookmarkHelper2;
        }

        public void setBookmarks(List<HistoryItem> list) {
            this.historyItemList.clear();
            this.historyItemList.addAll(list);
            for (int i = 0; i < this.historyItemList.size(); i++) {
                this.historyItemList.get(i).setId(i);
            }
        }

        public void onItemDismiss(int i) {
            this.historyItemList.remove(i);
            notifyItemRemoved(i);
            this.bookmarkHelper.itemRemoved(this.historyItemList.get(i));
        }

        public boolean onItemMove(int i, int i2) {
            if (i < i2) {
                int i3 = i;
                while (i3 < i2) {
                    int i4 = i3 + 1;
                    Collections.swap(this.historyItemList, i3, i4);
                    i3 = i4;
                }
            } else {
                for (int i5 = i; i5 > i2; i5--) {
                    Collections.swap(this.historyItemList, i5, i5 - 1);
                }
            }
            for (int i6 = 0; i6 < this.historyItemList.size(); i6++) {
                HistoryItem historyItem = new HistoryItem();
                HistoryItem historyItem2 = this.historyItemList.get(i6);
                historyItem.setTitle(historyItem2.getTitle());
                historyItem.setImageUrl(historyItem2.getImageUrl());
                historyItem.setShowOnMainScreen(historyItem2.isShowOnMainScreen());
                historyItem.setUrl(historyItem2.getUrl());
                historyItem.setPosition(i6);
                historyItem.setFolder(historyItem2.getFolder());
                this.bookmarkModel.editBookmark(historyItem2, historyItem).subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new CompletableOnSubscribe() {
                    public void onComplete() {
                    }
                });
            }
            notifyItemMoved(i, i2);
            return true;
        }

        public List<HistoryItem> getHistoryItemList() {
            return this.historyItemList;
        }

        /* JADX WARNING: Can't fix incorrect switch cases order */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public acr.browser.lightning.view.HomePageMainTab.BookmarkAdapter.ViewHolder onCreateViewHolder(android.view.ViewGroup r7, int r8) {
            /*
                r6 = this;
                android.content.Context r8 = r7.getContext()
                android.view.LayoutInflater r8 = android.view.LayoutInflater.from(r8)
                acr.browser.lightning.config.Config r0 = acr.browser.lightning.app.BrowserApp.getConfig()
                java.lang.String r0 = r0.getBookmarkWidgetType()
                int r1 = r0.hashCode()
                r2 = 3
                r3 = 2
                r4 = 1
                r5 = 0
                switch(r1) {
                    case -952103499: goto L_0x003a;
                    case 1412759103: goto L_0x0030;
                    case 1950882204: goto L_0x0026;
                    case 1951022836: goto L_0x001c;
                    default: goto L_0x001b;
                }
            L_0x001b:
                goto L_0x0044
            L_0x001c:
                java.lang.String r1 = "bookmarkList"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x0044
                r0 = 2
                goto L_0x0045
            L_0x0026:
                java.lang.String r1 = "bookmarkGrid"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x0044
                r0 = 0
                goto L_0x0045
            L_0x0030:
                java.lang.String r1 = "bookmarkList2Columns"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x0044
                r0 = 3
                goto L_0x0045
            L_0x003a:
                java.lang.String r1 = "bookmarkFlatGrid"
                boolean r0 = r0.equals(r1)
                if (r0 == 0) goto L_0x0044
                r0 = 1
                goto L_0x0045
            L_0x0044:
                r0 = -1
            L_0x0045:
                r1 = 2131492922(0x7f0c003a, float:1.860931E38)
                if (r0 == 0) goto L_0x0074
                if (r0 == r4) goto L_0x0067
                if (r0 == r3) goto L_0x005a
                if (r0 == r2) goto L_0x005a
                acr.browser.lightning.view.HomePageMainTab$BookmarkAdapter$ViewHolder r0 = new acr.browser.lightning.view.HomePageMainTab$BookmarkAdapter$ViewHolder
                android.view.View r7 = r8.inflate(r1, r7, r5)
                r0.<init>(r7)
                return r0
            L_0x005a:
                acr.browser.lightning.view.HomePageMainTab$BookmarkAdapter$ViewHolder r0 = new acr.browser.lightning.view.HomePageMainTab$BookmarkAdapter$ViewHolder
                r1 = 2131492923(0x7f0c003b, float:1.8609312E38)
                android.view.View r7 = r8.inflate(r1, r7, r5)
                r0.<init>(r7)
                return r0
            L_0x0067:
                acr.browser.lightning.view.HomePageMainTab$BookmarkAdapter$ViewHolder r0 = new acr.browser.lightning.view.HomePageMainTab$BookmarkAdapter$ViewHolder
                r1 = 2131492920(0x7f0c0038, float:1.8609306E38)
                android.view.View r7 = r8.inflate(r1, r7, r5)
                r0.<init>(r7)
                return r0
            L_0x0074:
                acr.browser.lightning.view.HomePageMainTab$BookmarkAdapter$ViewHolder r0 = new acr.browser.lightning.view.HomePageMainTab$BookmarkAdapter$ViewHolder
                android.view.View r7 = r8.inflate(r1, r7, r5)
                r0.<init>(r7)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.view.HomePageMainTab.BookmarkAdapter.onCreateViewHolder(android.view.ViewGroup, int):acr.browser.lightning.view.HomePageMainTab$BookmarkAdapter$ViewHolder");
        }

        public void onBindViewHolder(final ViewHolder viewHolder, int i) {
            HistoryItem historyItem = this.historyItemList.get(i);
            viewHolder.nameBook.setText(historyItem.getTitle());
            viewHolder.nameBook.setTextColor(BrowserApp.getThemeManager().getIconColor(this.theme));
            viewHolder.letter.setVisibility(4);
            if (!historyItem.isFolder()) {
                if (historyItem.getImageUrl() == null || historyItem.getImageUrl().equals("")) {
                    if (historyItem.getBitmap() != null) {
                        viewHolder.favicon.setImageBitmap(historyItem.getBitmap());
                    } else if (BrowserApp.getConfig().getBookmarkWidgetType().equals(BookmarkWidget.LIST) || BrowserApp.getConfig().getBookmarkWidgetType().equals(BookmarkWidget.LIST_2_COLUMNS)) {
                        viewHolder.favicon.setImageBitmap(BrowserApp.getThemeManager().getThemedBitmap(this.context, R.drawable.bookmark_outline, this.theme));
                    } else {
                        createIcon(viewHolder, historyItem);
                    }
                } else if (historyItem.getImageUrl().startsWith(Constants.HTTP)) {
                    createIcon(viewHolder, historyItem);
                    ImageLoader.getInstance().loadImage(historyItem.getImageUrl(), new ImageLoader.ImageLoadedListener() {
                        public void onImageLoaded(Bitmap bitmap) {
                            viewHolder.favicon.setImageBitmap(bitmap);
                            viewHolder.letter.setVisibility(4);
                        }
                    });
                } else {
                    try {
                        Bitmap decodeStream = BitmapFactory.decodeStream(this.context.getAssets().open(historyItem.getImageUrl()));
                        decodeStream.setDensity(0);
                        viewHolder.favicon.setImageDrawable(new BitmapDrawable(this.context.getResources(), decodeStream));
                    } catch (IOException unused) {
                        Log.w("favicon", "Can't get icon");
                    }
                }
            }
            if (BrowserApp.getConfig().getBookmarkWidgetType().equals(BookmarkWidget.BIG_GRID)) {
                viewHolder.background.setBackgroundColor(ColorPicker.getColor((long) (this.context.getString(R.string.widgetID).hashCode() + i)));
            }
        }

        private void createIcon(ViewHolder viewHolder, HistoryItem historyItem) {
            TypedArray obtainTypedArray = this.context.getResources().obtainTypedArray(R.array.background);
            if (BrowserApp.getConfig().getBookmarkWidgetType().equals(BookmarkWidget.GRID)) {
                viewHolder.favicon.setImageDrawable(obtainTypedArray.getDrawable(Math.abs((historyItem.hashCode() % obtainTypedArray.length()) - 1)));
            } else if (BrowserApp.getConfig().getBookmarkWidgetType().equals(BookmarkWidget.BIG_GRID)) {
                viewHolder.favicon.setVisibility(4);
            }
            viewHolder.letter.setVisibility(0);
            Uri parse = Uri.parse(historyItem.getUrl());
            if (parse != null) {
                viewHolder.letter.setText((parse.getHost().startsWith("www.") ? parse.getHost().substring(4) : parse.getHost()).substring(0, 1).toUpperCase());
            } else {
                viewHolder.letter.setText(historyItem.getTitle().substring(0, 1).toUpperCase());
            }
            obtainTypedArray.recycle();
        }

        public int getItemCount() {
            return this.historyItemList.size();
        }

        public long getItemId(int i) {
            return (long) this.historyItemList.get(i).getId();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(2131296375)
            ViewGroup background;
            @BindView(2131296526)
            ImageView favicon;
            @BindView(2131296580)
            TextView letter;
            @BindView(2131296812)
            TextView nameBook;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind((Object) this, view);
            }
        }
    }

    public HomePageMainTab(Activity activity2, int i, BookmarkModel bookmarkModel, DownloadsModel downloadsModel) {
        final Activity activity3 = activity2;
        int i2 = i;
        View inflate = activity2.getLayoutInflater().inflate(R.layout.home_page_main, (ViewGroup) null);
        this.view = inflate;
        ButterKnife.bind((Object) this, inflate);
        this.activity = activity3;
        final PreferenceManager preferenceManager = ((ThemableBrowserActivity) activity3).getmPreferences();
        Config config = BrowserApp.getConfig();
        ArrayList<HomePageWidget> arrayList = new ArrayList<>();
        if (config.isBookmarkWidgetEnabled()) {
            final BookmarkWidget bookmarkWidget = new BookmarkWidget(activity3, BrowserApp.getConfig().getBookmarkWidgetType());
            final BookmarkAdapter bookmarkAdapter = new BookmarkAdapter(activity3, i2, bookmarkModel);
            final BookmarkModel bookmarkModel2 = bookmarkModel;
            final Activity activity4 = activity2;
            final BookmarkWidget bookmarkWidget2 = bookmarkWidget;
            final BookmarkAdapter bookmarkAdapter2 = bookmarkAdapter;
            bookmarkAdapter.setBookmarkHelper(new BookmarkHelper() {
                public void itemRemoved(HistoryItem historyItem) {
                    HistoryItem historyItem2 = new HistoryItem();
                    historyItem2.setTitle(historyItem.getTitle());
                    historyItem2.setImageUrl(historyItem.getImageUrl());
                    historyItem2.setShowOnMainScreen(false);
                    historyItem2.setUrl(historyItem.getUrl());
                    historyItem2.setPosition(historyItem.getPosition());
                    historyItem2.setFolder(historyItem.getFolder());
                    bookmarkModel2.editBookmark(historyItem, historyItem2).subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new CompletableOnSubscribe() {
                        public void onComplete() {
                            ((UIController) activity4).updateBookmarks();
                        }
                    });
                    ViewGroup.LayoutParams layoutParams = bookmarkWidget2.bookmarksGrid.getLayoutParams();
                    layoutParams.height = HomePageMainTab.this.calculateHeight(BrowserApp.getConfig().getBookmarkWidgetType(), bookmarkAdapter2.getItemCount());
                    bookmarkWidget2.bookmarksGrid.setLayoutParams(layoutParams);
                }
            });
            bookmarkModel.getBookmarksForMainScreen().subscribeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<List<HistoryItem>>() {
                public void onItem(List<HistoryItem> list) {
                    if (list != null) {
                        bookmarkAdapter.setBookmarks(list);
                        bookmarkAdapter.notifyDataSetChanged();
                        ViewGroup.LayoutParams layoutParams = bookmarkWidget.bookmarksGrid.getLayoutParams();
                        layoutParams.height = HomePageMainTab.this.calculateHeight(BrowserApp.getConfig().getBookmarkWidgetType(), list.size());
                        bookmarkWidget.bookmarksGrid.setLayoutParams(layoutParams);
                    }
                }
            });
            bookmarkWidget.bookmarksGrid.setLayoutManager(new GridLayoutManager(activity3, calculateNoOfColumns(BrowserApp.getConfig().getBookmarkWidgetType(), activity3)) {
                public boolean canScrollVertically() {
                    return false;
                }
            });
            bookmarkWidget.bookmarksGrid.setAdapter(bookmarkAdapter);
            new ItemTouchHelper(new BookmarkTouchHelper(bookmarkAdapter)).attachToRecyclerView(bookmarkWidget.bookmarksGrid);
            ItemClickSupport.addTo(bookmarkWidget.bookmarksGrid).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                public void onItemClicked(RecyclerView recyclerView, int i, View view) {
                    ((UIController) activity3).loadUrl(bookmarkAdapter.getHistoryItemList().get(i).getUrl());
                }
            });
            arrayList.add(bookmarkWidget);
        }
        if (config.isDownloadsWidgetEnabled()) {
            final DownloadsWidget downloadsWidget = new DownloadsWidget(activity3, BrowserApp.getConfig().getDownloadsWidgetType());
            downloadsWidget.downloadsCard.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ((BrowserActivity) activity3).openDownloads();
                }
            });
            downloadsWidget.title.setTextColor(BrowserApp.getThemeManager().getIconColor(i2));
            final DownloadsAdapter downloadsAdapter = new DownloadsAdapter(activity3, i2);
            downloadsModel.getAllDownloads().subscribeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<List<DownloadItem>>() {
                public void onItem(List<DownloadItem> list) {
                    if (list == null || list.size() <= 0) {
                        downloadsWidget.getView().setVisibility(8);
                        return;
                    }
                    Collections.reverse(list);
                    if (list.size() > 10) {
                        list = list.subList(0, 10);
                    }
                    downloadsAdapter.setBookmarks(list);
                    downloadsAdapter.notifyDataSetChanged();
                    ViewGroup.LayoutParams layoutParams = downloadsWidget.downloadsGrid.getLayoutParams();
                    layoutParams.height = HomePageMainTab.this.calculateHeight(BrowserApp.getConfig().getDownloadsWidgetType(), list.size());
                    downloadsWidget.downloadsGrid.setLayoutParams(layoutParams);
                    downloadsWidget.getView().setVisibility(0);
                }
            });
            downloadsWidget.downloadsGrid.setLayoutManager(new GridLayoutManager(activity3, calculateNoOfColumns(BrowserApp.getConfig().getDownloadsWidgetType(), activity3)) {
                public boolean canScrollVertically() {
                    return false;
                }
            });
            downloadsWidget.downloadsGrid.setAdapter(downloadsAdapter);
            ItemClickSupport.addTo(downloadsWidget.downloadsGrid).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                public void onItemClicked(RecyclerView recyclerView, int i, View view) {
                    MimeTypeMap singleton = MimeTypeMap.getSingleton();
                    Intent intent = new Intent("android.intent.action.VIEW");
                    String mimeTypeFromExtension = singleton.getMimeTypeFromExtension(HomePageMainTab.this.fileExt(acr.browser.lightning.constant.Constants.FILE + preferenceManager.getDownloadDirectory() + "/" + downloadsAdapter.getDownloadItemList().get(i).getTitle()));
                    StringBuilder sb = new StringBuilder();
                    sb.append(preferenceManager.getDownloadDirectory());
                    sb.append("/");
                    sb.append(downloadsAdapter.getDownloadItemList().get(i).getTitle());
                    intent.setDataAndType(Uri.fromFile(new File(sb.toString())), mimeTypeFromExtension);
                    intent.setFlags(268435456);
                    try {
                        activity3.startActivity(intent);
                    } catch (ActivityNotFoundException unused) {
                        Toast.makeText(activity3, "No handler for this type of file.", 1).show();
                    }
                }
            });
            arrayList.add(downloadsWidget);
        }
        if (config.isHistoryWidgetEnabled()) {
            final HistoryWidget historyWidget = new HistoryWidget(activity3, BrowserApp.getConfig().getHistoryWidgetType(), i2);
            historyWidget.historyCard.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ((BrowserActivity) activity3).openHistory();
                }
            });
            historyWidget.title.setTextColor(BrowserApp.getThemeManager().getIconColor(i2));
            final HistoryAdapter historyAdapter = new HistoryAdapter(activity3, i2);
            HistoryModel.lastHundredVisitedHistoryItems().subscribeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<List<HistoryItem>>() {
                public void onItem(List<HistoryItem> list) {
                    if (list == null || list.size() <= 0) {
                        historyWidget.getView().setVisibility(8);
                        return;
                    }
                    if (list.size() > 10) {
                        list = list.subList(0, 10);
                    }
                    historyAdapter.setBookmarks(list);
                    historyAdapter.notifyDataSetChanged();
                    ViewGroup.LayoutParams layoutParams = historyWidget.historyGrid.getLayoutParams();
                    layoutParams.height = HomePageMainTab.this.calculateHeight(BrowserApp.getConfig().getDownloadsWidgetType(), list.size());
                    historyWidget.historyGrid.setLayoutParams(layoutParams);
                    historyWidget.getView().setVisibility(0);
                }
            });
            historyWidget.historyGrid.setLayoutManager(new GridLayoutManager(activity3, calculateNoOfColumns(BrowserApp.getConfig().getDownloadsWidgetType(), activity3)) {
                public boolean canScrollVertically() {
                    return false;
                }
            });
            historyWidget.historyGrid.setAdapter(historyAdapter);
            ItemClickSupport.addTo(historyWidget.historyGrid).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                public void onItemClicked(RecyclerView recyclerView, int i, View view) {
                    ((UIController) activity3).loadUrl(historyAdapter.getHistoryItemList().get(i).getUrl());
                }
            });
            arrayList.add(historyWidget);
        }
        if (config.isWeatherWidgetEnabled()) {
            final WeatherWidget weatherWidget = new WeatherWidget(activity3, BrowserApp.getConfig().getWeatherWidgetType(), i2);
            weatherWidget.celsiusButton.setText(preferenceManager.getWeatherData().isCecius() ? "C" : "F");
            weatherWidget.celsiusButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    WeatherData weatherData = preferenceManager.getWeatherData();
                    boolean z = !weatherData.isCecius();
                    weatherWidget.celsiusButton.setText(z ? "C" : "F");
                    weatherData.setCecius(z);
                    preferenceManager.setWeatherDataData(weatherData);
                    if (preferenceManager.getNotificationWeatherEnabled()) {
                        WeatherNotification weatherNotification = new WeatherNotification(activity3, weatherData, preferenceManager);
                        weatherNotification.remove();
                        weatherNotification.show();
                    }
                    if (HomePageMainTab.this.weatherData == null) {
                        return;
                    }
                    if (z) {
                        double temp = (double) (HomePageMainTab.this.weatherData.getTemp() - 32);
                        Double.isNaN(temp);
                        weatherWidget.temperature.setText(String.valueOf((int) (temp * 0.5555555555555556d)) + "°");
                        return;
                    }
                    weatherWidget.temperature.setText(String.valueOf(HomePageMainTab.this.weatherData.getTemp()) + "°");
                }
            });
            final Activity activity5 = activity2;
            final WeatherWidget weatherWidget2 = weatherWidget;
            final int i3 = i;
            final PreferenceManager preferenceManager2 = preferenceManager;
            final C325414 r0 = new StartPageLoader.WeatherCallback() {
                public void onWeatherResult(final WeatherData weatherData) {
                    activity5.runOnUiThread(new Runnable() {
                        public void run() {
                            double d;
                            if (weatherData != null) {
                                weatherWidget2.temperaturePanel.setVisibility(0);
                                weatherWidget2.imagePanel.setVisibility(0);
                                WeatherData unused = HomePageMainTab.this.weatherData = weatherData;
                                boolean isCecius = weatherData.isCecius();
                                weatherWidget2.celsiusButton.setTextColor(BrowserApp.getThemeManager().getIconColor(i3));
                                if (isCecius) {
                                    double temp = (double) (weatherData.getTemp() - 32);
                                    Double.isNaN(temp);
                                    d = temp * 0.5555555555555556d;
                                } else {
                                    d = (double) weatherData.getTemp();
                                }
                                TextView textView = weatherWidget2.temperature;
                                textView.setText(String.valueOf((int) d) + "°");
                                Drawable drawable = activity5.getResources().getDrawable(StartPageLoader.getIconId(BrowserApp.getConfig().getWeatherWidgetType(), weatherData.getCode()));
                                if (BrowserApp.getConfig().getWeatherWidgetType().equals(WeatherWidget.SIMPLE)) {
                                    drawable = DrawableCompat.wrap(drawable);
                                    DrawableCompat.setTint(drawable, BrowserApp.getThemeManager().getIconColor(i3));
                                }
                                weatherWidget2.weatherIcon.setImageDrawable(drawable);
                                weatherWidget2.location.setText(weatherData.getLocation());
                                weatherWidget2.weatherText.setText(weatherData.getText());
                                if (preferenceManager2.getNotificationWeatherEnabled()) {
                                    new WeatherNotification(activity5.getBaseContext(), weatherData, preferenceManager2).show();
                                }
                            } else {
                                weatherWidget2.temperaturePanel.setVisibility(8);
                                weatherWidget2.imagePanel.setVisibility(8);
                                weatherWidget2.location.setText(activity5.getResources().getString(R.string.noWeatherData));
                            }
                            ((UIController) activity5).updateProgress(100);
                        }
                    });
                }
            };
            StartPageLoader.getWeather(activity3, preferenceManager, r0, false);
            weatherWidget.location.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity3);
                    final EditText editText = new EditText(activity3);
                    FrameLayout frameLayout = new FrameLayout(activity3);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
                    layoutParams.setMargins(16, 0, 16, 0);
                    editText.setLayoutParams(layoutParams);
                    frameLayout.addView(editText);
                    builder.setTitle((CharSequence) activity3.getResources().getString(R.string.enterCity));
                    builder.setView((View) frameLayout);
                    builder.setPositiveButton(17039370, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            preferenceManager.setCity(editText.getText().toString());
                            StartPageLoader.getWeather(activity3, preferenceManager, r0, true);
                        }
                    });
                    builder.setNegativeButton(17039360, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.setNeutralButton((int) R.string.detectLocation, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            preferenceManager.removeCity();
                            StartPageLoader.getWeather(activity3, preferenceManager, r0, true);
                        }
                    });
                    builder.show();
                }
            });
            arrayList.add(weatherWidget);
        }
        if (config.getNewsTabsPosition().equals(BrowserActivity.TAB_POSITION_WIDGET)) {
            NewsWidget newsWidget = new NewsWidget(activity3, config.getNewsWidgetType());
            final HomepageView.NewsAdapter newsAdapter = new HomepageView.NewsAdapter(activity3, i2, config.getNewsWidgetType().equals(NewsWidget.LIST_REVERSED) || config.getNewsWidgetType().equals(NewsWidget.LIST_TRANSPARENT_REVERSED));
            newsWidget.newsList.setLayoutManager(new GridLayoutManager(activity3, calculateNoOfColumns(BrowserApp.getConfig().getNewsWidgetType(), activity3)) {
                public boolean canScrollVertically() {
                    return false;
                }
            });
            newsWidget.newsList.setAdapter(newsAdapter);
            newsWidget.newsList.addItemDecoration(new DividerItemDecoration(activity3, R.drawable.divider));
            newsWidget.newsList.setHasFixedSize(true);
            newsWidget.newsList.setItemViewCacheSize(20);
            newsWidget.newsList.setDrawingCacheEnabled(true);
            newsWidget.newsList.setDrawingCacheQuality(1048576);
            ItemClickSupport.addTo(newsWidget.newsList).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                public void onItemClicked(RecyclerView recyclerView, int i, View view) {
                    ((UIController) activity3).loadUrl(newsAdapter.getNewsList().get(i).getLink());
                }
            });
            final String str = "wid=" + activity2.getResources().getString(R.string.widgetID) + "&advid=" + preferenceManager.getAdvertisingId() + "$pn=" + activity2.getPackageName() + "&v=" + activity2.getResources().getString(R.string.platformVersion) + "apiKey=" + activity2.getResources().getString(R.string.api_key);
            final Activity activity6 = activity2;
            final NewsWidget newsWidget2 = newsWidget;
            StartPageLoader.requestGeoData(preferenceManager, new StartPageLoader.GeoDataHandler() {
                public void onResult(GeoData geoData) {
                    NewsApi.getInstance().getTopStoriesNews(str, geoData.getCountryCode(), new NewsApi.NewsCallback() {
                        public void onNewsResult(final NewsCategory newsCategory) {
                            activity6.runOnUiThread(new Runnable() {
                                public void run() {
                                    newsAdapter.setNews(newsCategory.getNewsList());
                                    newsAdapter.notifyDataSetChanged();
                                    Double.valueOf((double) newsAdapter.getNewsList().size()).intValue();
                                    ViewGroup.LayoutParams layoutParams = newsWidget2.newsList.getLayoutParams();
                                    layoutParams.height = HomePageMainTab.this.calculateHeight(BrowserApp.getConfig().getNewsWidgetType(), newsAdapter.getNewsList().size());
                                    newsWidget2.newsList.setLayoutParams(layoutParams);
                                }
                            });
                        }
                    });
                }
            });
            arrayList.add(newsWidget);
        }
        Collections.sort(arrayList);
        for (HomePageWidget homePageWidget : arrayList) {
            this.widgetsPanel.addView(homePageWidget.getView());
            if (BrowserApp.getConfig().isWidgetsMargins()) {
                homePageWidget.setMargins(getPx(8), getPx(4));
            } else {
                homePageWidget.setMargins(0, 0);
            }
        }
    }

    /* access modifiers changed from: private */
    public String fileExt(String str) {
        if (str.indexOf("?") > -1) {
            str = str.substring(0, str.indexOf("?"));
        }
        if (str.lastIndexOf(".") == -1) {
            return null;
        }
        String substring = str.substring(str.lastIndexOf(".") + 1);
        if (substring.indexOf("%") > -1) {
            substring = substring.substring(0, substring.indexOf("%"));
        }
        if (substring.indexOf("/") > -1) {
            substring = substring.substring(0, substring.indexOf("/"));
        }
        return substring.toLowerCase();
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int calculateHeight(java.lang.String r5, int r6) {
        /*
            r4 = this;
            int r0 = r5.hashCode()
            r1 = 0
            switch(r0) {
                case -1354943871: goto L_0x007e;
                case -1270120087: goto L_0x0073;
                case -1110189325: goto L_0x0069;
                case -952103499: goto L_0x005f;
                case 49011409: goto L_0x0054;
                case 49152041: goto L_0x0049;
                case 395701697: goto L_0x003e;
                case 712871779: goto L_0x0034;
                case 1394609681: goto L_0x002a;
                case 1412759103: goto L_0x0020;
                case 1950882204: goto L_0x0015;
                case 1951022836: goto L_0x000a;
                default: goto L_0x0008;
            }
        L_0x0008:
            goto L_0x0088
        L_0x000a:
            java.lang.String r0 = "bookmarkList"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0088
            r0 = 6
            goto L_0x0089
        L_0x0015:
            java.lang.String r0 = "bookmarkGrid"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0088
            r0 = 5
            goto L_0x0089
        L_0x0020:
            java.lang.String r0 = "bookmarkList2Columns"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0088
            r0 = 7
            goto L_0x0089
        L_0x002a:
            java.lang.String r0 = "newsList"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0088
            r0 = 0
            goto L_0x0089
        L_0x0034:
            java.lang.String r0 = "newsListTransparentReversed"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0088
            r0 = 3
            goto L_0x0089
        L_0x003e:
            java.lang.String r0 = "downloadsGridTransparent"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0088
            r0 = 9
            goto L_0x0089
        L_0x0049:
            java.lang.String r0 = "downloadsList"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0088
            r0 = 10
            goto L_0x0089
        L_0x0054:
            java.lang.String r0 = "downloadsGrid"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0088
            r0 = 8
            goto L_0x0089
        L_0x005f:
            java.lang.String r0 = "bookmarkFlatGrid"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0088
            r0 = 4
            goto L_0x0089
        L_0x0069:
            java.lang.String r0 = "newsListReversed"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0088
            r0 = 2
            goto L_0x0089
        L_0x0073:
            java.lang.String r0 = "downloadsListTransparent"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0088
            r0 = 11
            goto L_0x0089
        L_0x007e:
            java.lang.String r0 = "newsListTransparent"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x0088
            r0 = 1
            goto L_0x0089
        L_0x0088:
            r0 = -1
        L_0x0089:
            switch(r0) {
                case 0: goto L_0x0093;
                case 1: goto L_0x0093;
                case 2: goto L_0x0093;
                case 3: goto L_0x0093;
                case 4: goto L_0x0090;
                case 5: goto L_0x0090;
                case 6: goto L_0x008d;
                case 7: goto L_0x008d;
                case 8: goto L_0x008d;
                case 9: goto L_0x008d;
                case 10: goto L_0x008d;
                case 11: goto L_0x008d;
                default: goto L_0x008c;
            }
        L_0x008c:
            goto L_0x0095
        L_0x008d:
            r1 = 56
            goto L_0x0095
        L_0x0090:
            r1 = 120(0x78, float:1.68E-43)
            goto L_0x0095
        L_0x0093:
            r1 = 100
        L_0x0095:
            android.app.Activity r0 = r4.activity
            android.content.res.Resources r0 = r0.getResources()
            android.util.DisplayMetrics r0 = r0.getDisplayMetrics()
            float r0 = r0.density
            double r2 = (double) r6
            android.app.Activity r6 = r4.activity
            int r5 = calculateNoOfColumns(r5, r6)
            double r5 = (double) r5
            java.lang.Double.isNaN(r2)
            java.lang.Double.isNaN(r5)
            double r2 = r2 / r5
            double r5 = java.lang.Math.ceil(r2)
            java.lang.Double r5 = java.lang.Double.valueOf(r5)
            int r5 = r5.intValue()
            int r5 = r5 * r1
            float r5 = (float) r5
            float r5 = r5 * r0
            r6 = 1056964608(0x3f000000, float:0.5)
            float r5 = r5 + r6
            int r5 = (int) r5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.view.HomePageMainTab.calculateHeight(java.lang.String, int):int");
    }

    private int getPx(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, this.activity.getResources().getDisplayMetrics());
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int calculateNoOfColumns(java.lang.String r3, android.content.Context r4) {
        /*
            int r0 = r3.hashCode()
            r1 = 2
            r2 = 1
            switch(r0) {
                case -1354943871: goto L_0x0073;
                case -1270120087: goto L_0x0069;
                case -1110189325: goto L_0x005f;
                case -952103499: goto L_0x0055;
                case 49011409: goto L_0x004a;
                case 49152041: goto L_0x0040;
                case 395701697: goto L_0x0035;
                case 712871779: goto L_0x002b;
                case 1394609681: goto L_0x0021;
                case 1412759103: goto L_0x0016;
                case 1951022836: goto L_0x000b;
                default: goto L_0x0009;
            }
        L_0x0009:
            goto L_0x007d
        L_0x000b:
            java.lang.String r0 = "bookmarkList"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x007d
            r3 = 0
            goto L_0x007e
        L_0x0016:
            java.lang.String r0 = "bookmarkList2Columns"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x007d
            r3 = 8
            goto L_0x007e
        L_0x0021:
            java.lang.String r0 = "newsList"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x007d
            r3 = 3
            goto L_0x007e
        L_0x002b:
            java.lang.String r0 = "newsListTransparentReversed"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x007d
            r3 = 6
            goto L_0x007e
        L_0x0035:
            java.lang.String r0 = "downloadsGridTransparent"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x007d
            r3 = 10
            goto L_0x007e
        L_0x0040:
            java.lang.String r0 = "downloadsList"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x007d
            r3 = 1
            goto L_0x007e
        L_0x004a:
            java.lang.String r0 = "downloadsGrid"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x007d
            r3 = 9
            goto L_0x007e
        L_0x0055:
            java.lang.String r0 = "bookmarkFlatGrid"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x007d
            r3 = 7
            goto L_0x007e
        L_0x005f:
            java.lang.String r0 = "newsListReversed"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x007d
            r3 = 5
            goto L_0x007e
        L_0x0069:
            java.lang.String r0 = "downloadsListTransparent"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x007d
            r3 = 2
            goto L_0x007e
        L_0x0073:
            java.lang.String r0 = "newsListTransparent"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x007d
            r3 = 4
            goto L_0x007e
        L_0x007d:
            r3 = -1
        L_0x007e:
            switch(r3) {
                case 0: goto L_0x0095;
                case 1: goto L_0x0095;
                case 2: goto L_0x0095;
                case 3: goto L_0x0095;
                case 4: goto L_0x0095;
                case 5: goto L_0x0095;
                case 6: goto L_0x0095;
                case 7: goto L_0x0094;
                case 8: goto L_0x0094;
                case 9: goto L_0x0094;
                case 10: goto L_0x0094;
                default: goto L_0x0081;
            }
        L_0x0081:
            android.content.res.Resources r3 = r4.getResources()
            android.util.DisplayMetrics r3 = r3.getDisplayMetrics()
            int r4 = r3.widthPixels
            float r4 = (float) r4
            float r3 = r3.density
            float r4 = r4 / r3
            r3 = 1123024896(0x42f00000, float:120.0)
            float r4 = r4 / r3
            int r3 = (int) r4
            return r3
        L_0x0094:
            return r1
        L_0x0095:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.view.HomePageMainTab.calculateNoOfColumns(java.lang.String, android.content.Context):int");
    }

    public View getView() {
        return this.view;
    }
}

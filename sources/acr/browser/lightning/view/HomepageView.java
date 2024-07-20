package acr.browser.lightning.view;

import acr.browser.lightning.activity.BrowserActivity;
import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.config.Config;
import acr.browser.lightning.controller.UIController;
import acr.browser.lightning.database.bookmark.BookmarkModel;
import acr.browser.lightning.database.downloads.DownloadsModel;
import acr.browser.lightning.domain.GeoData;
import acr.browser.lightning.domain.News;
import acr.browser.lightning.domain.NewsCategory;
import acr.browser.lightning.preference.PreferenceManager;
import acr.browser.lightning.utils.ImageLoader;
import acr.browser.lightning.utils.ItemClickSupport;
import acr.browser.lightning.utils.NewsApi;
import acr.browser.lightning.utils.StartPageLoader;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.C4621Utils;
import com.appnext.ads.fullscreen.RewardedVideo;
import com.google.android.material.tabs.TabLayout;
import com.wnikebrow_13999769.R;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class HomepageView {
    @BindView(2131296376)
    ImageView backgroundImage;
    /* access modifiers changed from: private */
    public List<NewsCategory> newsCategoryList;
    /* access modifiers changed from: private */
    public final NewsPageAdapter newsPageAdapter;
    private String newsType = RewardedVideo.VIDEO_MODE_DEFAULT;
    @BindView(2131296690)
    ViewPager pager;
    private PreferenceManager preferenceManager;
    @BindView(2131296787)
    TabLayout tabLayout;
    /* access modifiers changed from: private */
    public List<String> tabNamesList;
    /* access modifiers changed from: private */
    public int theme;
    private UrlClickedListener urlClickedListener;
    private View view;

    interface UrlClickedListener {
        void onUrlClicked(String str);
    }

    public static class NewsAdapter extends RecyclerView.Adapter<ViewHolder> {
        private Context context;
        private final List<News> newsList;
        private boolean reversed;
        private int theme;

        public class ViewHolder_ViewBinding implements Unbinder {
            private ViewHolder target;

            public ViewHolder_ViewBinding(ViewHolder viewHolder, View view) {
                this.target = viewHolder;
                viewHolder.title = (TextView) C4621Utils.findRequiredViewAsType(view, R.id.title, "field 'title'", TextView.class);
                viewHolder.text = (TextView) C4621Utils.findRequiredViewAsType(view, R.id.text, "field 'text'", TextView.class);
                viewHolder.source = (TextView) C4621Utils.findRequiredViewAsType(view, R.id.source, "field 'source'", TextView.class);
                viewHolder.date = (TextView) C4621Utils.findRequiredViewAsType(view, R.id.date, "field 'date'", TextView.class);
                viewHolder.image = (ImageView) C4621Utils.findRequiredViewAsType(view, R.id.image, "field 'image'", ImageView.class);
            }

            public void unbind() {
                ViewHolder viewHolder = this.target;
                if (viewHolder != null) {
                    this.target = null;
                    viewHolder.title = null;
                    viewHolder.text = null;
                    viewHolder.source = null;
                    viewHolder.date = null;
                    viewHolder.image = null;
                    return;
                }
                throw new IllegalStateException("Bindings already cleared.");
            }
        }

        public NewsAdapter(Context context2, int i) {
            this(context2, i, false);
        }

        public NewsAdapter(Context context2, int i, boolean z) {
            this.newsList = new ArrayList();
            this.context = context2;
            this.theme = i;
            this.reversed = z;
        }

        public void setNews(List<News> list) {
            this.newsList.addAll(list);
            for (int i = 0; i < this.newsList.size(); i++) {
                this.newsList.get(i).setId(i);
            }
        }

        public List<News> getNewsList() {
            return this.newsList;
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(this.reversed ? R.layout.news_layout_reversed : R.layout.news_layout, viewGroup, false));
        }

        public void onBindViewHolder(final ViewHolder viewHolder, int i) {
            News news = this.newsList.get(i);
            viewHolder.title.setText(news.getTitle());
            viewHolder.title.setTextColor(BrowserApp.getThemeManager().getIconColor(this.theme));
            viewHolder.text.setText(news.getText());
            viewHolder.text.setTextColor(BrowserApp.getThemeManager().getIconColor(this.theme));
            viewHolder.date.setText(news.getDate());
            viewHolder.date.setTextColor(BrowserApp.getThemeManager().getIconColor(this.theme));
            viewHolder.source.setText(news.getSource());
            viewHolder.source.setTextColor(BrowserApp.getThemeManager().getIconColor(this.theme));
            ImageLoader.getInstance().loadImage(news.getImageLink(), new ImageLoader.ImageLoadedListener() {
                public void onImageLoaded(Bitmap bitmap) {
                    viewHolder.image.setImageBitmap(bitmap);
                }
            });
        }

        public int getItemCount() {
            return this.newsList.size();
        }

        public long getItemId(int i) {
            return (long) this.newsList.get(i).getId();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(2131296458)
            TextView date;
            @BindView(2131296563)
            ImageView image;
            @BindView(2131296761)
            TextView source;
            @BindView(2131296810)
            TextView text;
            @BindView(2131296834)
            TextView title;

            public ViewHolder(View view) {
                super(view);
                ButterKnife.bind((Object) this, view);
            }
        }
    }

    public HomepageView(final FragmentActivity fragmentActivity, boolean z, PreferenceManager preferenceManager2) {
        if (BrowserApp.getConfig().getNewsTabsPosition().equals(BrowserActivity.TAB_POSITION_BOTTOM)) {
            this.view = fragmentActivity.getLayoutInflater().inflate(R.layout.home_page_bottom_tabs, (ViewGroup) null);
        } else if (BrowserApp.getConfig().getNewsTabsPosition().equals(BrowserActivity.TAB_POSITION_WIDGET)) {
            this.newsType = BrowserActivity.TAB_POSITION_WIDGET;
            this.view = fragmentActivity.getLayoutInflater().inflate(R.layout.home_page, (ViewGroup) null);
        } else {
            this.view = fragmentActivity.getLayoutInflater().inflate(R.layout.home_page, (ViewGroup) null);
        }
        ButterKnife.bind((Object) this, this.view);
        Config config = BrowserApp.getConfig();
        this.preferenceManager = preferenceManager2;
        if (z) {
            this.theme = 2;
        } else {
            this.theme = preferenceManager2.getUseTheme();
        }
        this.newsCategoryList = new ArrayList();
        if (preferenceManager2.getBackgroundUrl() != null) {
            try {
                this.backgroundImage.setImageBitmap(BitmapFactory.decodeStream(fragmentActivity.openFileInput("back.jpg")));
                this.backgroundImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else if (config.getBackground() != null) {
            this.backgroundImage.setImageDrawable(config.getBackground());
            this.backgroundImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        ArrayList arrayList = new ArrayList();
        this.tabNamesList = arrayList;
        arrayList.add(fragmentActivity.getResources().getString(R.string.express_panel));
        this.newsPageAdapter = new NewsPageAdapter(fragmentActivity.getSupportFragmentManager());
        for (String text : this.tabNamesList) {
            TabLayout tabLayout2 = this.tabLayout;
            tabLayout2.addTab(tabLayout2.newTab().setText((CharSequence) text));
        }
        this.pager.setAdapter(this.newsPageAdapter);
        this.pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(this.tabLayout));
        this.tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            public void onTabReselected(TabLayout.Tab tab) {
            }

            public void onTabUnselected(TabLayout.Tab tab) {
            }

            public void onTabSelected(TabLayout.Tab tab) {
                HomepageView.this.pager.setCurrentItem(tab.getPosition());
            }
        });
        this.tabLayout.setBackgroundColor(BrowserApp.getThemeManager().getTransparentPrimaryColor(this.theme));
        this.tabLayout.setTabTextColors(BrowserApp.getThemeManager().getDisabledIconColor(this.theme), BrowserApp.getThemeManager().getIconColor(this.theme));
        if (!this.newsType.equals(BrowserActivity.TAB_POSITION_WIDGET)) {
            final String str = "wid=" + fragmentActivity.getResources().getString(R.string.widgetID) + "&advid=" + preferenceManager2.getAdvertisingId() + "$pn=" + fragmentActivity.getPackageName() + "&v=" + fragmentActivity.getResources().getString(R.string.platformVersion) + "apiKey=" + fragmentActivity.getResources().getString(R.string.api_key);
            StartPageLoader.requestGeoData(preferenceManager2, new StartPageLoader.GeoDataHandler() {
                public void onResult(GeoData geoData) {
                    NewsApi.getInstance().getNews(str, geoData.getCountryCode(), new NewsApi.NewsCallback() {
                        public void onNewsResult(final NewsCategory newsCategory) {
                            fragmentActivity.runOnUiThread(new Runnable() {
                                public void run() {
                                    HomepageView.this.newsCategoryList.add(newsCategory);
                                    HomepageView.this.tabNamesList.add(newsCategory.getName());
                                    HomepageView.this.tabLayout.addTab(HomepageView.this.tabLayout.newTab().setText((CharSequence) newsCategory.getName()));
                                    HomepageView.this.newsPageAdapter.notifyDataSetChanged();
                                }
                            });
                        }
                    });
                }
            });
            return;
        }
        this.tabLayout.setVisibility(8);
    }

    public void setUrlClickedListener(UrlClickedListener urlClickedListener2) {
        this.urlClickedListener = urlClickedListener2;
    }

    public View getView() {
        return this.view;
    }

    public static class HomePageViewPagerFragment extends Fragment {
        public static final String ARG_NUM = "num";
        public static final String ARG_OBJECT = "object";
        public static final String ARG_THEME = "theme";
        @Inject
        DownloadsModel downloadsModel;
        private FrameLayout frameLayout;
        @Inject
        BookmarkModel mBookmarkManager;

        public HomePageViewPagerFragment() {
            BrowserApp.getAppComponent().inject(this);
        }

        public void onConfigurationChanged(Configuration configuration) {
            super.onConfigurationChanged(configuration);
            Bundle arguments = getArguments();
            int i = arguments.getInt(ARG_NUM, 0);
            int i2 = arguments.getInt(ARG_THEME, 0);
            if (i == 0) {
                View view = new HomePageMainTab(getActivity(), i2, this.mBookmarkManager, this.downloadsModel).getView();
                FrameLayout frameLayout2 = this.frameLayout;
                if (frameLayout2 != null) {
                    frameLayout2.removeAllViews();
                    this.frameLayout.addView(view);
                }
            }
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            View view;
            Bundle arguments = getArguments();
            boolean z = false;
            int i = arguments.getInt(ARG_NUM, 0);
            int i2 = arguments.getInt(ARG_THEME, 0);
            if (i == 0) {
                view = new HomePageMainTab(getActivity(), i2, this.mBookmarkManager, this.downloadsModel).getView();
            } else {
                view = layoutInflater.inflate(R.layout.news_list, viewGroup, false);
                CardView cardView = (CardView) view.findViewById(R.id.card);
                cardView.setCardBackgroundColor(BrowserApp.getThemeManager().getTransparentPrimaryColor(i2));
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) cardView.getLayoutParams();
                if (BrowserApp.getConfig().isWidgetsMargins()) {
                    int px = getPx(8);
                    layoutParams.setMargins(px, px, px, px);
                    cardView.setRadius((float) getPx(4));
                } else {
                    layoutParams.setMargins(0, 0, 0, 0);
                    cardView.setRadius(0.0f);
                }
                String newsWidgetType = BrowserApp.getConfig().getNewsWidgetType();
                char c = 65535;
                switch (newsWidgetType.hashCode()) {
                    case -1354943871:
                        if (newsWidgetType.equals(NewsWidget.LIST_TRANSPARENT)) {
                            c = 2;
                            break;
                        }
                        break;
                    case -1110189325:
                        if (newsWidgetType.equals(NewsWidget.LIST_REVERSED)) {
                            c = 1;
                            break;
                        }
                        break;
                    case 712871779:
                        if (newsWidgetType.equals(NewsWidget.LIST_TRANSPARENT_REVERSED)) {
                            c = 3;
                            break;
                        }
                        break;
                    case 1394609681:
                        if (newsWidgetType.equals(NewsWidget.LIST)) {
                            c = 0;
                            break;
                        }
                        break;
                }
                if (c == 0 || c == 1) {
                    cardView.setCardBackgroundColor(BrowserApp.getConfig().getNewsWidgetColor());
                } else if (c == 2 || c == 3) {
                    cardView.setCardBackgroundColor(BrowserApp.getThemeManager().getTransparentColor(BrowserApp.getConfig().getNewsWidgetColor()));
                }
                RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.news_list);
                NewsCategory newsCategory = (NewsCategory) arguments.getSerializable(ARG_OBJECT);
                if (newsCategory != null) {
                    FragmentActivity activity = getActivity();
                    if (newsWidgetType.equals(NewsWidget.LIST_REVERSED) || newsWidgetType.equals(NewsWidget.LIST_TRANSPARENT_REVERSED)) {
                        z = true;
                    }
                    final NewsAdapter newsAdapter = new NewsAdapter(activity, i2, z);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(newsAdapter);
                    recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.divider));
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setItemViewCacheSize(20);
                    recyclerView.setDrawingCacheEnabled(true);
                    recyclerView.setDrawingCacheQuality(1048576);
                    newsAdapter.setNews(newsCategory.getNewsList());
                    newsAdapter.notifyDataSetChanged();
                    ItemClickSupport.addTo(recyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                        public void onItemClicked(RecyclerView recyclerView, int i, View view) {
                            ((UIController) HomePageViewPagerFragment.this.getActivity()).loadUrl(newsAdapter.getNewsList().get(i).getLink());
                        }
                    });
                    ViewGroup.LayoutParams layoutParams2 = recyclerView.getLayoutParams();
                    layoutParams2.height = (int) ((((float) (Double.valueOf((double) newsCategory.getNewsList().size()).intValue() * 100)) * getActivity().getResources().getDisplayMetrics().density) + 0.5f);
                    recyclerView.setLayoutParams(layoutParams2);
                }
            }
            FrameLayout frameLayout2 = new FrameLayout(getActivity());
            this.frameLayout = frameLayout2;
            frameLayout2.addView(view);
            return this.frameLayout;
        }

        private int getPx(int i) {
            return (int) TypedValue.applyDimension(1, (float) i, getActivity().getResources().getDisplayMetrics());
        }
    }

    public class NewsPageAdapter extends FragmentStatePagerAdapter {
        public NewsPageAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public Fragment getItem(int i) {
            HomePageViewPagerFragment homePageViewPagerFragment = new HomePageViewPagerFragment();
            Bundle bundle = new Bundle();
            if (i > 0) {
                bundle.putSerializable(HomePageViewPagerFragment.ARG_OBJECT, (Serializable) HomepageView.this.newsCategoryList.get(i - 1));
            }
            bundle.putInt(HomePageViewPagerFragment.ARG_NUM, i);
            bundle.putInt(HomePageViewPagerFragment.ARG_THEME, HomepageView.this.theme);
            homePageViewPagerFragment.setArguments(bundle);
            return homePageViewPagerFragment;
        }

        public int getCount() {
            return HomepageView.this.tabNamesList.size();
        }

        public CharSequence getPageTitle(int i) {
            return (CharSequence) HomepageView.this.tabNamesList.get(i);
        }
    }
}

package acr.browser.lightning.fragment;

import acr.browser.lightning.activity.TabsManager;
import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.browser.TabsView;
import acr.browser.lightning.controller.UIController;
import acr.browser.lightning.fragment.anim.HorizontalItemAnimator;
import acr.browser.lightning.fragment.anim.VerticalItemAnimator;
import acr.browser.lightning.preference.PreferenceManager;
import acr.browser.lightning.utils.C3245Utils;
import acr.browser.lightning.utils.DrawableUtils;
import acr.browser.lightning.view.BackgroundDrawable;
import acr.browser.lightning.view.LightningView;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.core.widget.TextViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.wnikebrow_13999769.R;
import javax.inject.Inject;

public class TabsFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener, TabsView {
    private static final String IS_INCOGNITO = "TabsFragment.IS_INCOGNITO";
    private static final String TAG = "TabsFragment";
    private static final String VERTICAL_MODE = "TabsFragment.VERTICAL_MODE";
    /* access modifiers changed from: private */
    public boolean mColorMode = true;
    /* access modifiers changed from: private */
    public int mIconColor;
    /* access modifiers changed from: private */
    public boolean mIsIncognito;
    @Inject
    PreferenceManager mPreferences;
    @BindView(2131296791)
    RecyclerView mRecyclerView;
    private boolean mShowInNavigationDrawer;
    /* access modifiers changed from: private */
    public LightningViewAdapter mTabsAdapter;
    private TabsManager mTabsManager;
    /* access modifiers changed from: private */
    public UIController mUiController;
    private Unbinder mUnbinder;
    @BindView(2131296790)
    LinearLayout tabsBackground;
    /* access modifiers changed from: private */
    public int theme;

    public static TabsFragment createTabsFragment(boolean z, boolean z2) {
        TabsFragment tabsFragment = new TabsFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(IS_INCOGNITO, z);
        bundle.putBoolean(VERTICAL_MODE, z2);
        tabsFragment.setArguments(bundle);
        return tabsFragment;
    }

    public TabsFragment() {
        BrowserApp.getAppComponent().inject(this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        getContext();
        UIController uIController = (UIController) getActivity();
        this.mUiController = uIController;
        this.mTabsManager = uIController.getTabModel();
        boolean z = false;
        this.mIsIncognito = arguments.getBoolean(IS_INCOGNITO, false);
        this.mShowInNavigationDrawer = arguments.getBoolean(VERTICAL_MODE, true);
        this.theme = this.mPreferences.getUseTheme();
        if (this.mIsIncognito) {
            this.theme = 2;
        }
        boolean colorModeEnabled = this.mPreferences.getColorModeEnabled();
        this.mColorMode = colorModeEnabled;
        if (this.theme <= 1) {
            z = true;
        }
        this.mColorMode = colorModeEnabled & z;
        this.mIconColor = BrowserApp.getThemeManager().getIconColor(this.theme);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LinearLayoutManager linearLayoutManager;
        View view;
        SimpleItemAnimator simpleItemAnimator;
        if (this.mShowInNavigationDrawer) {
            view = layoutInflater.inflate(R.layout.tab_drawer, viewGroup, false);
            linearLayoutManager = new LinearLayoutManager(getContext(), 1, false);
            view.findViewById(R.id.tab_title_panel).setBackgroundColor(BrowserApp.getThemeManager().getPrimarydarkColor(this.theme));
            ((TextView) view.findViewById(R.id.tab_title)).setTextColor(BrowserApp.getThemeManager().getIconColor(this.theme));
            view.findViewById(R.id.tabs_list).setBackgroundColor(BrowserApp.getThemeManager().getPrimaryColor(this.theme));
            view.findViewById(R.id.bottomPanel).setBackgroundColor(BrowserApp.getThemeManager().getPrimarydarkColor(this.theme));
            setupFrameLayoutButton(view, R.id.tab_header_button, R.id.plusIcon);
            setupFrameLayoutButton(view, R.id.new_tab_button, R.id.icon_plus);
            setupFrameLayoutButton(view, R.id.action_back, R.id.icon_back);
            setupFrameLayoutButton(view, R.id.action_forward, R.id.icon_forward);
            setupFrameLayoutButton(view, R.id.action_home, R.id.icon_home);
        } else {
            view = layoutInflater.inflate(R.layout.tab_strip, viewGroup, false);
            linearLayoutManager = new LinearLayoutManager(getContext(), 0, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.new_tab_button);
            imageView.setColorFilter(BrowserApp.getThemeManager().getIconColor(0));
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    TabsFragment.this.mUiController.newTabButtonClicked();
                }
            });
        }
        this.mUnbinder = ButterKnife.bind((Object) this, view);
        if (this.mShowInNavigationDrawer) {
            simpleItemAnimator = new VerticalItemAnimator();
        } else {
            simpleItemAnimator = new HorizontalItemAnimator();
        }
        simpleItemAnimator.setSupportsChangeAnimations(false);
        simpleItemAnimator.setAddDuration(200);
        simpleItemAnimator.setChangeDuration(0);
        simpleItemAnimator.setRemoveDuration(200);
        simpleItemAnimator.setMoveDuration(200);
        this.mRecyclerView.setItemAnimator(simpleItemAnimator);
        this.mRecyclerView.setLayoutManager(linearLayoutManager);
        LightningViewAdapter lightningViewAdapter = new LightningViewAdapter(this.mShowInNavigationDrawer);
        this.mTabsAdapter = lightningViewAdapter;
        this.mRecyclerView.setAdapter(lightningViewAdapter);
        LinearLayout linearLayout = this.tabsBackground;
        if (linearLayout != null) {
            linearLayout.setBackgroundColor(BrowserApp.getThemeManager().getStatusBarColor(this.theme));
        }
        return view;
    }

    public void onDestroyView() {
        super.onDestroyView();
        Unbinder unbinder = this.mUnbinder;
        if (unbinder != null) {
            unbinder.unbind();
            this.mUnbinder = null;
        }
        this.mTabsAdapter = null;
    }

    /* access modifiers changed from: private */
    public TabsManager getTabsManager() {
        if (this.mTabsManager == null) {
            this.mTabsManager = this.mUiController.getTabModel();
        }
        return this.mTabsManager;
    }

    private void setupFrameLayoutButton(View view, int i, int i2) {
        View findViewById = view.findViewById(i);
        findViewById.setOnClickListener(this);
        findViewById.setOnLongClickListener(this);
        ((ImageView) view.findViewById(i2)).setColorFilter(this.mIconColor, PorterDuff.Mode.SRC_IN);
    }

    public void onResume() {
        super.onResume();
        LightningViewAdapter lightningViewAdapter = this.mTabsAdapter;
        if (lightningViewAdapter != null) {
            lightningViewAdapter.notifyDataSetChanged();
        }
    }

    public void tabsInitialized() {
        LightningViewAdapter lightningViewAdapter = this.mTabsAdapter;
        if (lightningViewAdapter != null) {
            lightningViewAdapter.notifyDataSetChanged();
        }
    }

    public void reinitializePreferences() {
        if (getActivity() != null) {
            this.mColorMode = this.mPreferences.getColorModeEnabled();
            this.theme = this.mPreferences.getUseTheme();
            if (this.mIsIncognito) {
                this.theme = 2;
            }
            boolean colorModeEnabled = this.mPreferences.getColorModeEnabled();
            this.mColorMode = colorModeEnabled;
            boolean z = true;
            if (this.theme > 1) {
                z = false;
            }
            this.mColorMode = colorModeEnabled & z;
            this.mIconColor = BrowserApp.getThemeManager().getIconColor(this.theme);
            LightningViewAdapter lightningViewAdapter = this.mTabsAdapter;
            if (lightningViewAdapter != null) {
                lightningViewAdapter.notifyDataSetChanged();
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.action_back:
                this.mUiController.onBackButtonPressed();
                return;
            case R.id.action_forward:
                this.mUiController.onForwardButtonPressed();
                return;
            case R.id.action_home:
                this.mUiController.onHomeButtonPressed();
                return;
            case R.id.new_tab_button:
                this.mUiController.newTabButtonClicked();
                return;
            case R.id.tab_header_button:
                this.mUiController.showCloseDialog(getTabsManager().indexOfCurrentTab());
                return;
            default:
                return;
        }
    }

    public boolean onLongClick(View view) {
        if (view.getId() != R.id.action_new_tab) {
            return true;
        }
        this.mUiController.newTabButtonLongClicked();
        return true;
    }

    public void tabAdded() {
        LightningViewAdapter lightningViewAdapter = this.mTabsAdapter;
        if (lightningViewAdapter != null) {
            lightningViewAdapter.notifyItemInserted(getTabsManager().last());
            this.mRecyclerView.postDelayed(new Runnable() {
                public void run() {
                    TabsFragment.this.mRecyclerView.smoothScrollToPosition(TabsFragment.this.mTabsAdapter.getItemCount() - 1);
                }
            }, 500);
        }
    }

    public void tabRemoved(int i) {
        LightningViewAdapter lightningViewAdapter = this.mTabsAdapter;
        if (lightningViewAdapter != null) {
            lightningViewAdapter.notifyItemRemoved(i);
        }
    }

    public void tabChanged(int i) {
        LightningViewAdapter lightningViewAdapter = this.mTabsAdapter;
        if (lightningViewAdapter != null) {
            lightningViewAdapter.notifyItemChanged(i);
        }
    }

    private class LightningViewAdapter extends RecyclerView.Adapter<LightningViewHolder> {
        private static final float DESATURATED = 0.5f;
        private final Drawable mBackgroundTabDrawable;
        private ColorMatrix mColorMatrix;
        private final boolean mDrawerTabs;
        private ColorFilter mFilter;
        private final Bitmap mForegroundTabBitmap;
        private final int mLayoutResourceId;
        private Paint mPaint;

        public LightningViewAdapter(boolean z) {
            this.mLayoutResourceId = z ? R.layout.tab_list_item : R.layout.tab_list_item_horizontal;
            this.mDrawerTabs = z;
            if (z) {
                this.mBackgroundTabDrawable = null;
                this.mForegroundTabBitmap = null;
                return;
            }
            C3245Utils.drawTrapezoid(new Canvas(Bitmap.createBitmap(C3245Utils.dpToPx(175.0f), C3245Utils.dpToPx(30.0f), Bitmap.Config.ARGB_8888)), C3245Utils.mixTwoColors(BrowserApp.getThemeManager().getPrimaryColor(TabsFragment.this.theme), -16777216, 0.75f), TabsFragment.this.getActivity().getResources().getColor(R.color.tabBorder), true);
            this.mBackgroundTabDrawable = new ColorDrawable(C3245Utils.mixTwoColors(BrowserApp.getThemeManager().getPrimaryColor(TabsFragment.this.theme), -16777216, 0.75f));
            int primaryColor = BrowserApp.getThemeManager().getPrimaryColor(TabsFragment.this.theme);
            this.mForegroundTabBitmap = Bitmap.createBitmap(C3245Utils.dpToPx(175.0f), C3245Utils.dpToPx(30.0f), Bitmap.Config.ARGB_8888);
            C3245Utils.drawTrapezoid(new Canvas(this.mForegroundTabBitmap), primaryColor, TabsFragment.this.getActivity().getResources().getColor(R.color.tabBorder), false);
        }

        public LightningViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(this.mLayoutResourceId, viewGroup, false);
            if (this.mDrawerTabs) {
                DrawableUtils.setBackground(inflate, new BackgroundDrawable(inflate.getContext()));
            }
            return new LightningViewHolder(inflate);
        }

        public void onBindViewHolder(LightningViewHolder lightningViewHolder, int i) {
            lightningViewHolder.exitButton.setTag(Integer.valueOf(i));
            ViewCompat.jumpDrawablesToCurrentState(lightningViewHolder.exitButton);
            LightningView tabAtPosition = TabsFragment.this.getTabsManager().getTabAtPosition(i);
            if (tabAtPosition != null) {
                lightningViewHolder.txtTitle.setText(tabAtPosition.getTitle());
                lightningViewHolder.txtTitle.setTextColor(BrowserApp.getThemeManager().getIconColor(TabsFragment.this.theme));
                Bitmap favicon = tabAtPosition.getFavicon();
                if (tabAtPosition.isForegroundTab()) {
                    ColorDrawable colorDrawable = null;
                    if (!this.mDrawerTabs) {
                        colorDrawable = new ColorDrawable(BrowserApp.getThemeManager().getPrimaryColor(TabsFragment.this.theme));
                        if (!TabsFragment.this.mIsIncognito && TabsFragment.this.mColorMode) {
                            colorDrawable.setColorFilter(TabsFragment.this.mUiController.getUiColor(), PorterDuff.Mode.SRC_IN);
                        }
                    }
                    if (!TabsFragment.this.mIsIncognito && TabsFragment.this.mColorMode) {
                        TabsFragment.this.mUiController.changeToolbarBackground(favicon, colorDrawable);
                    }
                    TextViewCompat.setTextAppearance(lightningViewHolder.txtTitle, R.style.boldText);
                    if (!this.mDrawerTabs) {
                        DrawableUtils.setBackground(lightningViewHolder.layout, colorDrawable);
                    }
                    lightningViewHolder.favicon.setImageBitmap(favicon);
                } else {
                    TextViewCompat.setTextAppearance(lightningViewHolder.txtTitle, R.style.normalText);
                    if (!this.mDrawerTabs) {
                        DrawableUtils.setBackground(lightningViewHolder.layout, this.mBackgroundTabDrawable);
                    }
                    lightningViewHolder.favicon.setImageBitmap(getDesaturatedBitmap(favicon));
                }
                if (this.mDrawerTabs) {
                    BackgroundDrawable backgroundDrawable = (BackgroundDrawable) lightningViewHolder.layout.getBackground();
                    backgroundDrawable.setCrossFadeEnabled(false);
                    if (tabAtPosition.isForegroundTab()) {
                        backgroundDrawable.startTransition(200);
                    } else {
                        backgroundDrawable.reverseTransition(200);
                    }
                }
            }
        }

        public int getItemCount() {
            return TabsFragment.this.getTabsManager().size();
        }

        public Bitmap getDesaturatedBitmap(Bitmap bitmap) {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            if (this.mColorMatrix == null || this.mFilter == null || this.mPaint == null) {
                this.mPaint = new Paint();
                ColorMatrix colorMatrix = new ColorMatrix();
                this.mColorMatrix = colorMatrix;
                colorMatrix.setSaturation(DESATURATED);
                ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(this.mColorMatrix);
                this.mFilter = colorMatrixColorFilter;
                this.mPaint.setColorFilter(colorMatrixColorFilter);
            }
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, this.mPaint);
            return createBitmap;
        }

        public class LightningViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
            final ImageView exit;
            final FrameLayout exitButton;
            final ImageView favicon;
            final LinearLayout layout;
            final TextView txtTitle;

            public LightningViewHolder(View view) {
                super(view);
                this.txtTitle = (TextView) view.findViewById(R.id.textTab);
                this.favicon = (ImageView) view.findViewById(R.id.faviconTab);
                this.exit = (ImageView) view.findViewById(R.id.deleteButton);
                this.layout = (LinearLayout) view.findViewById(R.id.tab_item_background);
                this.exitButton = (FrameLayout) view.findViewById(R.id.deleteAction);
                this.exit.setColorFilter(TabsFragment.this.mIconColor, PorterDuff.Mode.SRC_IN);
                this.exitButton.setOnClickListener(this);
                this.layout.setOnClickListener(this);
                this.layout.setOnLongClickListener(this);
            }

            public void onClick(View view) {
                if (view == this.exitButton) {
                    TabsFragment.this.mUiController.tabCloseClicked(getAdapterPosition());
                }
                if (view == this.layout) {
                    TabsFragment.this.mUiController.tabClicked(getAdapterPosition());
                }
            }

            public boolean onLongClick(View view) {
                TabsFragment.this.mUiController.showCloseDialog(getAdapterPosition());
                return true;
            }
        }
    }
}

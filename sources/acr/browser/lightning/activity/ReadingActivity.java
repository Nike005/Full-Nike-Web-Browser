package acr.browser.lightning.activity;

import acr.browser.lightning.C2972R;
import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.constant.Constants;
import acr.browser.lightning.dialog.BrowserDialog;
import acr.browser.lightning.preference.PreferenceManager;
import acr.browser.lightning.reading.HtmlFetcher;
import acr.browser.lightning.reading.JResult;
import acr.browser.lightning.utils.C3245Utils;
import acr.browser.lightning.utils.ThemeUtils;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.anthonycr.bonsai.Schedulers;
import com.anthonycr.bonsai.Single;
import com.anthonycr.bonsai.SingleAction;
import com.anthonycr.bonsai.SingleOnSubscribe;
import com.anthonycr.bonsai.SingleSubscriber;
import com.anthonycr.bonsai.Subscription;
import com.wnikebrow_13999769.R;
import javax.inject.Inject;

public class ReadingActivity extends AppCompatActivity {
    private static final float LARGE = 22.0f;
    private static final float MEDIUM = 18.0f;
    private static final float SMALL = 14.0f;
    private static final String TAG = "ReadingActivity";
    private static final float XLARGE = 26.0f;
    private static final float XSMALL = 10.0f;
    private static final float XXLARGE = 30.0f;
    @BindView(2131296818)
    TextView mBody;
    private boolean mInvert;
    private Subscription mPageLoaderSubscription;
    @Inject
    PreferenceManager mPreferences;
    /* access modifiers changed from: private */
    public ProgressDialog mProgressDialog;
    /* access modifiers changed from: private */
    public int mTextSize;
    @BindView(2131296819)
    TextView mTitle;
    private String mUrl = null;

    /* access modifiers changed from: private */
    public static float getTextSize(int i) {
        if (i == 0) {
            return XSMALL;
        }
        if (i == 1) {
            return SMALL;
        }
        if (i == 3) {
            return LARGE;
        }
        if (i == 4) {
            return XLARGE;
        }
        if (i != 5) {
            return 18.0f;
        }
        return XXLARGE;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        BrowserApp.getAppComponent().inject(this);
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.fade_out_scale);
        boolean invertColors = this.mPreferences.getInvertColors();
        this.mInvert = invertColors;
        if (invertColors) {
            setTheme(C2972R.style.Theme_SettingsTheme_Dark);
            getWindow().setBackgroundDrawable(new ColorDrawable(ThemeUtils.getPrimaryColorDark(this)));
        } else {
            setTheme(C2972R.style.Theme_SettingsTheme);
            getWindow().setBackgroundDrawable(new ColorDrawable(BrowserApp.getThemeManager().getPrimaryColor(this.mPreferences.getUseTheme())));
        }
        super.onCreate(bundle);
        setContentView((int) R.layout.reading_view);
        ButterKnife.bind((Activity) this);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        int readingTextSize = this.mPreferences.getReadingTextSize();
        this.mTextSize = readingTextSize;
        this.mBody.setTextSize(getTextSize(readingTextSize));
        this.mTitle.setText(getString(R.string.untitled));
        this.mBody.setText(getString(R.string.loading));
        this.mTitle.setVisibility(4);
        this.mBody.setVisibility(4);
        if (!loadPage(getIntent())) {
            setText(getString(R.string.untitled), getString(R.string.loading_failed));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reading, menu);
        MenuItem findItem = menu.findItem(R.id.invert_item);
        MenuItem findItem2 = menu.findItem(R.id.text_size_item);
        int iconThemeColor = ThemeUtils.getIconThemeColor(this, this.mInvert);
        if (!(findItem == null || findItem.getIcon() == null)) {
            findItem.getIcon().mutate().setColorFilter(iconThemeColor, PorterDuff.Mode.SRC_IN);
        }
        if (!(findItem2 == null || findItem2.getIcon() == null)) {
            findItem2.getIcon().mutate().setColorFilter(iconThemeColor, PorterDuff.Mode.SRC_IN);
        }
        return super.onCreateOptionsMenu(menu);
    }

    private boolean loadPage(Intent intent) {
        if (intent == null) {
            return false;
        }
        String stringExtra = intent.getStringExtra(Constants.LOAD_READING_URL);
        this.mUrl = stringExtra;
        if (stringExtra == null) {
            return false;
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle((CharSequence) C3245Utils.getDomainName(this.mUrl));
        }
        this.mPageLoaderSubscription = loadPage(this.mUrl).subscribeOn(Schedulers.worker()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<ReaderInfo>() {
            public void onStart() {
                ProgressDialog unused = ReadingActivity.this.mProgressDialog = new ProgressDialog(ReadingActivity.this);
                ReadingActivity.this.mProgressDialog.setProgressStyle(0);
                ReadingActivity.this.mProgressDialog.setCancelable(false);
                ReadingActivity.this.mProgressDialog.setIndeterminate(true);
                ReadingActivity.this.mProgressDialog.setMessage(ReadingActivity.this.getString(R.string.loading));
                ReadingActivity.this.mProgressDialog.show();
                ReadingActivity readingActivity = ReadingActivity.this;
                BrowserDialog.setDialogSize(readingActivity, readingActivity.mProgressDialog);
            }

            public void onItem(ReaderInfo readerInfo) {
                if (readerInfo == null || readerInfo.getTitle().isEmpty() || readerInfo.getBody().isEmpty()) {
                    ReadingActivity readingActivity = ReadingActivity.this;
                    readingActivity.setText(readingActivity.getString(R.string.untitled), ReadingActivity.this.getString(R.string.loading_failed));
                    return;
                }
                ReadingActivity.this.setText(readerInfo.getTitle(), readerInfo.getBody());
            }

            public void onError(Throwable th) {
                ReadingActivity readingActivity = ReadingActivity.this;
                readingActivity.setText(readingActivity.getString(R.string.untitled), ReadingActivity.this.getString(R.string.loading_failed));
                if (ReadingActivity.this.mProgressDialog != null && ReadingActivity.this.mProgressDialog.isShowing()) {
                    ReadingActivity.this.mProgressDialog.dismiss();
                    ProgressDialog unused = ReadingActivity.this.mProgressDialog = null;
                }
            }

            public void onComplete() {
                if (ReadingActivity.this.mProgressDialog != null && ReadingActivity.this.mProgressDialog.isShowing()) {
                    ReadingActivity.this.mProgressDialog.dismiss();
                    ProgressDialog unused = ReadingActivity.this.mProgressDialog = null;
                }
            }
        });
        return true;
    }

    private static Single<ReaderInfo> loadPage(final String str) {
        return Single.create(new SingleAction<ReaderInfo>() {
            public void onSubscribe(SingleSubscriber<ReaderInfo> singleSubscriber) {
                try {
                    JResult fetchAndExtract = new HtmlFetcher().fetchAndExtract(str, 2500, true);
                    singleSubscriber.onItem(new ReaderInfo(fetchAndExtract.getTitle(), fetchAndExtract.getText()));
                } catch (Exception e) {
                    singleSubscriber.onError(new Throwable("Encountered exception"));
                    Log.e(ReadingActivity.TAG, "Error parsing page", e);
                } catch (OutOfMemoryError e2) {
                    System.gc();
                    singleSubscriber.onError(new Throwable("Out of memory"));
                    Log.e(ReadingActivity.TAG, "Out of memory", e2);
                }
                singleSubscriber.onComplete();
            }
        });
    }

    private static class ReaderInfo {
        private final String mBodyText;
        private final String mTitleText;

        public ReaderInfo(String str, String str2) {
            this.mTitleText = str;
            this.mBodyText = str2;
        }

        public String getTitle() {
            return this.mTitleText;
        }

        public String getBody() {
            return this.mBodyText;
        }
    }

    /* access modifiers changed from: private */
    public void setText(String str, String str2) {
        TextView textView = this.mTitle;
        if (textView != null && this.mBody != null) {
            if (textView.getVisibility() == 4) {
                this.mTitle.setAlpha(0.0f);
                this.mTitle.setVisibility(0);
                this.mTitle.setText(str);
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mTitle, "alpha", new float[]{1.0f});
                ofFloat.setDuration(300);
                ofFloat.start();
            } else {
                this.mTitle.setText(str);
            }
            if (this.mBody.getVisibility() == 4) {
                this.mBody.setAlpha(0.0f);
                this.mBody.setVisibility(0);
                this.mBody.setText(str2);
                ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.mBody, "alpha", new float[]{1.0f});
                ofFloat2.setDuration(300);
                ofFloat2.start();
                return;
            }
            this.mBody.setText(str2);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.mPageLoaderSubscription.unsubscribe();
        ProgressDialog progressDialog = this.mProgressDialog;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.mProgressDialog.dismiss();
            this.mProgressDialog = null;
        }
        super.onDestroy();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (isFinishing()) {
            overridePendingTransition(R.anim.fade_in_scale, R.anim.slide_out_to_right);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == R.id.invert_item) {
            this.mPreferences.setInvertColors(!this.mInvert);
            Intent intent = new Intent(this, ReadingActivity.class);
            intent.putExtra(Constants.LOAD_READING_URL, this.mUrl);
            startActivity(intent);
            finish();
        } else if (itemId != R.id.text_size_item) {
            finish();
        } else {
            View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_seek_bar, (ViewGroup) null);
            final SeekBar seekBar = (SeekBar) inflate.findViewById(R.id.text_size_seekbar);
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                }

                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                    ReadingActivity.this.mBody.setTextSize(ReadingActivity.getTextSize(i));
                }
            });
            seekBar.setMax(5);
            seekBar.setProgress(this.mTextSize);
            BrowserDialog.setDialogSize(this, new AlertDialog.Builder(this).setView(inflate).setTitle((int) R.string.size).setPositiveButton(17039370, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    int unused = ReadingActivity.this.mTextSize = seekBar.getProgress();
                    ReadingActivity.this.mBody.setTextSize(ReadingActivity.getTextSize(ReadingActivity.this.mTextSize));
                    ReadingActivity.this.mPreferences.setReadingTextSize(seekBar.getProgress());
                }
            }).show());
        }
        return super.onOptionsItemSelected(menuItem);
    }
}

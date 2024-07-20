package acr.browser.lightning.fragment;

import acr.browser.lightning.app.BrowserApp;
import acr.browser.lightning.database.HistoryItem;
import acr.browser.lightning.database.bookmark.BookmarkExporter;
import acr.browser.lightning.database.bookmark.BookmarkLocalSync;
import acr.browser.lightning.database.bookmark.BookmarkModel;
import acr.browser.lightning.dialog.BrowserDialog;
import acr.browser.lightning.utils.C3245Utils;
import acr.browser.lightning.utils.Preconditions;
import acr.browser.lightning.utils.SubscriptionUtils;
import android.app.Activity;
import android.app.Application;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.util.Log;
import android.widget.ArrayAdapter;
import androidx.appcompat.app.AlertDialog;
import com.anthonycr.bonsai.CompletableOnSubscribe;
import com.anthonycr.bonsai.Schedulers;
import com.anthonycr.bonsai.SingleOnSubscribe;
import com.anthonycr.bonsai.Subscription;
import com.anthonycr.grant.PermissionsManager;
import com.anthonycr.grant.PermissionsResultAction;
import com.wnikebrow_13999769.R;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;

public class BookmarkSettingsFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener {
    private static final String[] REQUIRED_PERMISSIONS = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private static final String SETTINGS_DELETE_BOOKMARKS = "delete_bookmarks";
    private static final String SETTINGS_EXPORT = "export_bookmark";
    private static final String SETTINGS_IMPORT = "import_bookmark";
    private static final String SETTINGS_IMPORT_BROWSER = "import_browser";
    private static final String TAG = "BookmarkSettingsFrag";
    private static final File mPath = new File(Environment.getExternalStorageDirectory().toString());
    /* access modifiers changed from: private */
    public Activity mActivity;
    @Inject
    Application mApplication;
    @Inject
    BookmarkModel mBookmarkManager;
    /* access modifiers changed from: private */
    public Subscription mExportSubscription;
    /* access modifiers changed from: private */
    public File[] mFileList;
    /* access modifiers changed from: private */
    public String[] mFileNameList;
    /* access modifiers changed from: private */
    public Subscription mImportSubscription;
    private BookmarkLocalSync mSync;

    private class ImportBookmarksTask extends AsyncTask<Void, Void, Integer> {
        private final WeakReference<Activity> mActivityReference;
        private final BookmarkLocalSync.Source mSource;

        public ImportBookmarksTask(Activity activity, BookmarkLocalSync.Source source) {
            this.mActivityReference = new WeakReference<>(activity);
            this.mSource = source;
        }

        /* access modifiers changed from: protected */
        public Integer doInBackground(Void... voidArr) {
            List list;
            Log.d(BookmarkSettingsFragment.TAG, "Loading bookmarks from: " + this.mSource.name());
            int i = C31528.f4053xa44ab6be[this.mSource.ordinal()];
            int i2 = 0;
            if (i == 1) {
                list = BookmarkSettingsFragment.this.getSync().getBookmarksFromStockBrowser();
            } else if (i == 2) {
                list = BookmarkSettingsFragment.this.getSync().getBookmarksFromChrome();
            } else if (i == 3) {
                list = BookmarkSettingsFragment.this.getSync().getBookmarksFromChromeBeta();
            } else if (i != 4) {
                list = new ArrayList(0);
            } else {
                list = BookmarkSettingsFragment.this.getSync().getBookmarksFromChromeDev();
            }
            if (!list.isEmpty()) {
                BookmarkSettingsFragment.this.mBookmarkManager.addBookmarkList(list);
                i2 = list.size();
            }
            return Integer.valueOf(i2);
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Integer num) {
            super.onPostExecute(num);
            Activity activity = (Activity) this.mActivityReference.get();
            if (activity != null) {
                int intValue = num.intValue();
                String string = activity.getResources().getString(R.string.message_import);
                C3245Utils.showSnackbar(activity, intValue + StringUtils.SPACE + string);
            }
        }
    }

    /* renamed from: acr.browser.lightning.fragment.BookmarkSettingsFragment$8 */
    static /* synthetic */ class C31528 {

        /* renamed from: $SwitchMap$acr$browser$lightning$database$bookmark$BookmarkLocalSync$Source */
        static final /* synthetic */ int[] f4053xa44ab6be;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                acr.browser.lightning.database.bookmark.BookmarkLocalSync$Source[] r0 = acr.browser.lightning.database.bookmark.BookmarkLocalSync.Source.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f4053xa44ab6be = r0
                acr.browser.lightning.database.bookmark.BookmarkLocalSync$Source r1 = acr.browser.lightning.database.bookmark.BookmarkLocalSync.Source.STOCK     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = f4053xa44ab6be     // Catch:{ NoSuchFieldError -> 0x001d }
                acr.browser.lightning.database.bookmark.BookmarkLocalSync$Source r1 = acr.browser.lightning.database.bookmark.BookmarkLocalSync.Source.CHROME_STABLE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = f4053xa44ab6be     // Catch:{ NoSuchFieldError -> 0x0028 }
                acr.browser.lightning.database.bookmark.BookmarkLocalSync$Source r1 = acr.browser.lightning.database.bookmark.BookmarkLocalSync.Source.CHROME_BETA     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = f4053xa44ab6be     // Catch:{ NoSuchFieldError -> 0x0033 }
                acr.browser.lightning.database.bookmark.BookmarkLocalSync$Source r1 = acr.browser.lightning.database.bookmark.BookmarkLocalSync.Source.CHROME_DEV     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.fragment.BookmarkSettingsFragment.C31528.<clinit>():void");
        }
    }

    /* access modifiers changed from: private */
    public BookmarkLocalSync getSync() {
        Preconditions.checkNonNull(this.mActivity);
        if (this.mSync == null) {
            this.mSync = new BookmarkLocalSync(this.mActivity);
        }
        return this.mSync;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BrowserApp.getAppComponent().inject(this);
        addPreferencesFromResource(R.xml.preference_bookmarks);
        Activity activity = getActivity();
        this.mActivity = activity;
        this.mSync = new BookmarkLocalSync(activity);
        initPrefs();
        PermissionsManager instance = PermissionsManager.getInstance();
        if (Build.VERSION.SDK_INT >= 16) {
            instance.requestPermissionsIfNecessaryForResult(getActivity(), REQUIRED_PERMISSIONS, (PermissionsResultAction) null);
        }
    }

    public void onDestroyView() {
        super.onDestroyView();
        SubscriptionUtils.safeUnsubscribe(this.mExportSubscription);
        SubscriptionUtils.safeUnsubscribe(this.mImportSubscription);
    }

    public void onDestroy() {
        super.onDestroy();
        SubscriptionUtils.safeUnsubscribe(this.mExportSubscription);
        SubscriptionUtils.safeUnsubscribe(this.mImportSubscription);
        this.mActivity = null;
    }

    private void initPrefs() {
        Preference findPreference = findPreference(SETTINGS_EXPORT);
        Preference findPreference2 = findPreference(SETTINGS_IMPORT);
        Preference findPreference3 = findPreference(SETTINGS_DELETE_BOOKMARKS);
        findPreference.setOnPreferenceClickListener(this);
        findPreference2.setOnPreferenceClickListener(this);
        findPreference3.setOnPreferenceClickListener(this);
        getSync().isBrowserImportSupported().subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<Boolean>() {
            public void onItem(Boolean bool) {
                Preconditions.checkNonNull(bool);
                Preference findPreference = BookmarkSettingsFragment.this.findPreference(BookmarkSettingsFragment.SETTINGS_IMPORT_BROWSER);
                findPreference.setEnabled(bool.booleanValue());
                findPreference.setOnPreferenceClickListener(BookmarkSettingsFragment.this);
            }
        });
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onPreferenceClick(android.preference.Preference r6) {
        /*
            r5 = this;
            java.lang.String r6 = r6.getKey()
            int r0 = r6.hashCode()
            r1 = 0
            r2 = 3
            r3 = 2
            r4 = 1
            switch(r0) {
                case -1410805618: goto L_0x002e;
                case 835890320: goto L_0x0024;
                case 1328916585: goto L_0x001a;
                case 2129440097: goto L_0x0010;
                default: goto L_0x000f;
            }
        L_0x000f:
            goto L_0x0038
        L_0x0010:
            java.lang.String r0 = "export_bookmark"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0038
            r6 = 0
            goto L_0x0039
        L_0x001a:
            java.lang.String r0 = "delete_bookmarks"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0038
            r6 = 3
            goto L_0x0039
        L_0x0024:
            java.lang.String r0 = "import_bookmark"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0038
            r6 = 1
            goto L_0x0039
        L_0x002e:
            java.lang.String r0 = "import_browser"
            boolean r6 = r6.equals(r0)
            if (r6 == 0) goto L_0x0038
            r6 = 2
            goto L_0x0039
        L_0x0038:
            r6 = -1
        L_0x0039:
            if (r6 == 0) goto L_0x007a
            if (r6 == r4) goto L_0x0067
            if (r6 == r3) goto L_0x0046
            if (r6 == r2) goto L_0x0042
            return r1
        L_0x0042:
            r5.showDeleteBookmarksDialog()
            return r4
        L_0x0046:
            acr.browser.lightning.database.bookmark.BookmarkLocalSync r6 = r5.getSync()
            com.anthonycr.bonsai.Single r6 = r6.getSupportedBrowsers()
            com.anthonycr.bonsai.Scheduler r0 = com.anthonycr.bonsai.Schedulers.worker()
            com.anthonycr.bonsai.Single r6 = r6.subscribeOn(r0)
            com.anthonycr.bonsai.Scheduler r0 = com.anthonycr.bonsai.Schedulers.main()
            com.anthonycr.bonsai.Single r6 = r6.observeOn(r0)
            acr.browser.lightning.fragment.BookmarkSettingsFragment$4 r0 = new acr.browser.lightning.fragment.BookmarkSettingsFragment$4
            r0.<init>()
            r6.subscribe(r0)
            return r4
        L_0x0067:
            com.anthonycr.grant.PermissionsManager r6 = com.anthonycr.grant.PermissionsManager.getInstance()
            android.app.Activity r0 = r5.getActivity()
            java.lang.String[] r1 = REQUIRED_PERMISSIONS
            acr.browser.lightning.fragment.BookmarkSettingsFragment$3 r2 = new acr.browser.lightning.fragment.BookmarkSettingsFragment$3
            r2.<init>()
            r6.requestPermissionsIfNecessaryForResult((android.app.Activity) r0, (java.lang.String[]) r1, (com.anthonycr.grant.PermissionsResultAction) r2)
            return r4
        L_0x007a:
            com.anthonycr.grant.PermissionsManager r6 = com.anthonycr.grant.PermissionsManager.getInstance()
            android.app.Activity r0 = r5.getActivity()
            java.lang.String[] r1 = REQUIRED_PERMISSIONS
            acr.browser.lightning.fragment.BookmarkSettingsFragment$2 r2 = new acr.browser.lightning.fragment.BookmarkSettingsFragment$2
            r2.<init>()
            r6.requestPermissionsIfNecessaryForResult((android.app.Activity) r0, (java.lang.String[]) r1, (com.anthonycr.grant.PermissionsResultAction) r2)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: acr.browser.lightning.fragment.BookmarkSettingsFragment.onPreferenceClick(android.preference.Preference):boolean");
    }

    private void showDeleteBookmarksDialog() {
        Activity activity = getActivity();
        if (activity != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle((int) R.string.action_delete);
            builder.setMessage((int) R.string.action_delete_all_bookmarks);
            builder.setNegativeButton((int) R.string.no, (DialogInterface.OnClickListener) null);
            builder.setPositiveButton((int) R.string.yes, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    BookmarkSettingsFragment.this.mBookmarkManager.deleteAllBookmarks().subscribeOn(Schedulers.m6232io()).subscribe();
                }
            });
            BrowserDialog.setDialogSize(activity, builder.show());
        }
    }

    /* access modifiers changed from: private */
    public List<String> buildTitleList(Activity activity, List<BookmarkLocalSync.Source> list) {
        String title;
        ArrayList arrayList = new ArrayList();
        for (BookmarkLocalSync.Source ordinal : list) {
            int i = C31528.f4053xa44ab6be[ordinal.ordinal()];
            if (i == 1) {
                arrayList.add(getString(R.string.stock_browser));
            } else if (i == 2) {
                String title2 = getTitle(activity, "com.android.chrome");
                if (title2 != null) {
                    arrayList.add(title2);
                }
            } else if (i == 3) {
                String title3 = getTitle(activity, "com.chrome.beta");
                if (title3 != null) {
                    arrayList.add(title3);
                }
            } else if (i == 4 && (title = getTitle(activity, "com.chrome.beta")) != null) {
                arrayList.add(title);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void showChooserDialog(final Activity activity, List<String> list) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final ArrayAdapter arrayAdapter = new ArrayAdapter(activity, 17367043);
        for (String add : list) {
            arrayAdapter.add(add);
        }
        builder.setTitle((int) R.string.supported_browsers_title);
        builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                BookmarkLocalSync.Source source;
                String str = (String) arrayAdapter.getItem(i);
                Preconditions.checkNonNull(str);
                if (str.equals(BookmarkSettingsFragment.this.getString(R.string.stock_browser))) {
                    source = BookmarkLocalSync.Source.STOCK;
                } else if (str.equals(BookmarkSettingsFragment.getTitle(activity, "com.android.chrome"))) {
                    source = BookmarkLocalSync.Source.CHROME_STABLE;
                } else if (str.equals(BookmarkSettingsFragment.getTitle(activity, "com.android.beta"))) {
                    source = BookmarkLocalSync.Source.CHROME_BETA;
                } else {
                    source = str.equals(BookmarkSettingsFragment.getTitle(activity, "com.android.dev")) ? BookmarkLocalSync.Source.CHROME_DEV : null;
                }
                if (source != null) {
                    new ImportBookmarksTask(activity, source).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                }
            }
        });
        BrowserDialog.setDialogSize(activity, builder.show());
    }

    /* access modifiers changed from: private */
    public static String getTitle(Activity activity, String str) {
        PackageManager packageManager = activity.getPackageManager();
        try {
            CharSequence applicationLabel = packageManager.getApplicationLabel(packageManager.getApplicationInfo(str, 128));
            if (applicationLabel != null) {
                return applicationLabel.toString();
            }
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void loadFileList(File file) {
        if (file == null) {
            file = mPath;
        }
        try {
            file.mkdirs();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        int i = 0;
        if (file.exists()) {
            this.mFileList = file.listFiles();
        } else {
            this.mFileList = new File[0];
        }
        File[] fileArr = this.mFileList;
        if (fileArr == null) {
            this.mFileNameList = new String[0];
            this.mFileList = new File[0];
        } else {
            Arrays.sort(fileArr, new SortName());
            this.mFileNameList = new String[this.mFileList.length];
        }
        while (true) {
            File[] fileArr2 = this.mFileList;
            if (i < fileArr2.length) {
                this.mFileNameList[i] = fileArr2[i].getName();
                i++;
            } else {
                return;
            }
        }
    }

    private static class SortName implements Comparator<File> {
        private SortName() {
        }

        public int compare(File file, File file2) {
            if (file.isDirectory() && file2.isDirectory()) {
                return file.getName().compareTo(file2.getName());
            }
            if (file.isDirectory()) {
                return -1;
            }
            if (!file2.isDirectory() && file.isFile() && file2.isFile()) {
                return file.getName().compareTo(file2.getName());
            }
            return 1;
        }
    }

    /* access modifiers changed from: private */
    public void createDialog() {
        Activity activity = this.mActivity;
        if (activity != null) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            final String string = getString(R.string.title_chooser);
            builder.setTitle((CharSequence) string + ": " + Environment.getExternalStorageDirectory());
            if (this.mFileList == null) {
                BrowserDialog.setDialogSize(this.mActivity, builder.show());
                return;
            }
            builder.setItems((CharSequence[]) this.mFileNameList, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (BookmarkSettingsFragment.this.mFileList[i].isDirectory()) {
                        AlertDialog.Builder builder = builder;
                        builder.setTitle((CharSequence) string + ": " + BookmarkSettingsFragment.this.mFileList[i]);
                        BookmarkSettingsFragment bookmarkSettingsFragment = BookmarkSettingsFragment.this;
                        bookmarkSettingsFragment.loadFileList(bookmarkSettingsFragment.mFileList[i]);
                        builder.setItems((CharSequence[]) BookmarkSettingsFragment.this.mFileNameList, (DialogInterface.OnClickListener) this);
                        BrowserDialog.setDialogSize(BookmarkSettingsFragment.this.mActivity, builder.show());
                        return;
                    }
                    SubscriptionUtils.safeUnsubscribe(BookmarkSettingsFragment.this.mImportSubscription);
                    BookmarkSettingsFragment bookmarkSettingsFragment2 = BookmarkSettingsFragment.this;
                    Subscription unused = bookmarkSettingsFragment2.mImportSubscription = BookmarkExporter.importBookmarksFromFile(bookmarkSettingsFragment2.mFileList[i]).subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new SingleOnSubscribe<List<HistoryItem>>() {
                        public void onItem(final List<HistoryItem> list) {
                            Subscription unused = BookmarkSettingsFragment.this.mImportSubscription = null;
                            Preconditions.checkNonNull(list);
                            BookmarkSettingsFragment.this.mBookmarkManager.addBookmarkList(list).subscribeOn(Schedulers.m6232io()).observeOn(Schedulers.main()).subscribe(new CompletableOnSubscribe() {
                                public void onComplete() {
                                    Activity activity = BookmarkSettingsFragment.this.getActivity();
                                    if (activity != null) {
                                        String string = activity.getString(R.string.message_import);
                                        C3245Utils.showSnackbar(activity, list.size() + StringUtils.SPACE + string);
                                    }
                                }
                            });
                        }

                        public void onError(Throwable th) {
                            Subscription unused = BookmarkSettingsFragment.this.mImportSubscription = null;
                            Log.e(BookmarkSettingsFragment.TAG, "onError: importing bookmarks", th);
                            Activity activity = BookmarkSettingsFragment.this.getActivity();
                            if (activity == null || activity.isFinishing() || !BookmarkSettingsFragment.this.isAdded()) {
                                C3245Utils.showToast(BookmarkSettingsFragment.this.mApplication, R.string.import_bookmark_error);
                            } else {
                                C3245Utils.createInformativeDialog(activity, R.string.title_error, R.string.import_bookmark_error);
                            }
                        }
                    });
                }
            });
            BrowserDialog.setDialogSize(this.mActivity, builder.show());
        }
    }
}

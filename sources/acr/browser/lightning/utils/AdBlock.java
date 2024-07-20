package acr.browser.lightning.utils;

import acr.browser.lightning.BuildConfig;
import acr.browser.lightning.preference.PreferenceManager;
import android.app.Application;
import android.util.Log;
import com.anthonycr.bonsai.Completable;
import com.anthonycr.bonsai.CompletableAction;
import com.anthonycr.bonsai.CompletableSubscriber;
import com.anthonycr.bonsai.Schedulers;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AdBlock {
    private static final String BLOCKED_DOMAINS_LIST_FILE_NAME = "hosts.txt";
    private static final String COMMENT = "#";
    private static final String EMPTY = "";
    private static final String LOCALHOST = "localhost";
    private static final String LOCAL_IP_V4 = "127.0.0.1";
    private static final String LOCAL_IP_V4_ALT = "0.0.0.0";
    private static final String LOCAL_IP_V6 = "::1";
    private static final String SPACE = " ";
    private static final String TAB = "\t";
    private static final String TAG = "AdBlock";
    /* access modifiers changed from: private */
    public final Application mApplication;
    private boolean mBlockAds;
    /* access modifiers changed from: private */
    public final Set<String> mBlockedDomainsList;
    private final PreferenceManager mPreferenceManager;

    @Inject
    AdBlock(Application application, PreferenceManager preferenceManager) {
        HashSet hashSet = new HashSet();
        this.mBlockedDomainsList = hashSet;
        this.mApplication = application;
        this.mPreferenceManager = preferenceManager;
        if (hashSet.isEmpty() && BuildConfig.FULL_VERSION) {
            loadHostsFile().subscribeOn(Schedulers.m6232io()).subscribe();
        }
        this.mBlockAds = this.mPreferenceManager.getAdBlockEnabled();
    }

    public void updatePreference() {
        this.mBlockAds = this.mPreferenceManager.getAdBlockEnabled();
    }

    public boolean isAd(String str) {
        if (this.mBlockAds && str != null) {
            try {
                boolean contains = this.mBlockedDomainsList.contains(getDomainName(str));
                if (contains) {
                    Log.d(TAG, "URL '" + str + "' is an ad");
                }
                return contains;
            } catch (URISyntaxException e) {
                Log.d(TAG, "URL '" + str + "' is invalid", e);
            }
        }
        return false;
    }

    private static String getDomainName(String str) throws URISyntaxException {
        int indexOf = str.indexOf(47, 8);
        if (indexOf != -1) {
            str = str.substring(0, indexOf);
        }
        String host = new URI(str).getHost();
        if (host == null) {
            return str;
        }
        return host.startsWith("www.") ? host.substring(4) : host;
    }

    private Completable loadHostsFile() {
        return Completable.create(new CompletableAction() {
            public void onSubscribe(CompletableSubscriber completableSubscriber) {
                BufferedReader bufferedReader = null;
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(AdBlock.this.mApplication.getAssets().open(AdBlock.BLOCKED_DOMAINS_LIST_FILE_NAME)));
                    try {
                        StringBuilder sb = new StringBuilder();
                        long currentTimeMillis = System.currentTimeMillis();
                        while (true) {
                            String readLine = bufferedReader2.readLine();
                            if (readLine != null) {
                                sb.append(readLine);
                                if (!StringBuilderUtils.isEmpty(sb) && !StringBuilderUtils.startsWith(sb, AdBlock.COMMENT)) {
                                    StringBuilderUtils.replace(sb, AdBlock.LOCAL_IP_V4, "");
                                    StringBuilderUtils.replace(sb, AdBlock.LOCAL_IP_V4_ALT, "");
                                    StringBuilderUtils.replace(sb, AdBlock.LOCAL_IP_V6, "");
                                    StringBuilderUtils.replace(sb, AdBlock.TAB, "");
                                    int indexOf = sb.indexOf(AdBlock.COMMENT);
                                    if (indexOf >= 0) {
                                        sb.replace(indexOf, sb.length(), "");
                                    }
                                    StringBuilderUtils.trim(sb);
                                    if (!StringBuilderUtils.isEmpty(sb) && !StringBuilderUtils.equals(sb, AdBlock.LOCALHOST)) {
                                        while (StringBuilderUtils.contains(sb, " ")) {
                                            StringBuilder substring = StringBuilderUtils.substring(sb, 0, sb.indexOf(" "));
                                            StringBuilderUtils.trim(substring);
                                            String sb2 = substring.toString();
                                            AdBlock.this.mBlockedDomainsList.add(sb2);
                                            StringBuilderUtils.replace(sb, sb2, "");
                                            StringBuilderUtils.trim(sb);
                                        }
                                        if (sb.length() > 0) {
                                            AdBlock.this.mBlockedDomainsList.add(sb.toString());
                                        }
                                    }
                                }
                                sb.setLength(0);
                            } else {
                                Log.d(AdBlock.TAG, "Loaded ad list in: " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
                                C3245Utils.close((Closeable) bufferedReader2);
                                return;
                            }
                        }
                    } catch (IOException e) {
                        e = e;
                        bufferedReader = bufferedReader2;
                        try {
                            Log.wtf(AdBlock.TAG, "Reading blocked domains list from file 'hosts.txt' failed.", e);
                            C3245Utils.close((Closeable) bufferedReader);
                        } catch (Throwable th) {
                            th = th;
                            C3245Utils.close((Closeable) bufferedReader);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader = bufferedReader2;
                        C3245Utils.close((Closeable) bufferedReader);
                        throw th;
                    }
                } catch (IOException e2) {
                    e = e2;
                    Log.wtf(AdBlock.TAG, "Reading blocked domains list from file 'hosts.txt' failed.", e);
                    C3245Utils.close((Closeable) bufferedReader);
                }
            }
        });
    }
}

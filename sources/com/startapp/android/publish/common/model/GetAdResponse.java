package com.startapp.android.publish.common.model;

import com.startapp.android.publish.adsCommon.BaseResponse;
import com.startapp.android.publish.adsCommon.adinformation.C1093c;
import com.startapp.common.p046c.C5303f;
import java.util.ArrayList;
import java.util.List;

/* compiled from: StartAppSDK */
public class GetAdResponse extends BaseResponse {
    private static final long serialVersionUID = 1;
    @C5303f(mo45477a = true)
    private C1093c adInfoOverrides = C1093c.m1277a();
    @C5303f(mo45478b = ArrayList.class, mo45479c = AdDetails.class)
    private List<AdDetails> adsDetails = new ArrayList();
    private boolean inAppBrowser;
    @C5303f(mo45478b = inAppBrowserPreLoad.class)
    private inAppBrowserPreLoad inAppBrowserPreLoad;
    private String productId;
    private String publisherId;

    /* compiled from: StartAppSDK */
    public enum ResponseType {
        HTML,
        JSON
    }

    /* compiled from: StartAppSDK */
    private enum inAppBrowserPreLoad {
        DISABLED,
        CONTENT,
        FULL
    }

    public String getPublisherId() {
        return this.publisherId;
    }

    public void setPublisherId(String str) {
        this.publisherId = str;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public List<AdDetails> getAdsDetails() {
        return this.adsDetails;
    }

    public void setAdsDetails(List<AdDetails> list) {
        this.adsDetails = list;
    }

    public C1093c getAdInfoOverride() {
        return this.adInfoOverrides;
    }

    public boolean isInAppBrowser() {
        return this.inAppBrowser;
    }

    public void setInAppBrowser(boolean z) {
        this.inAppBrowser = z;
    }

    public inAppBrowserPreLoad getInAppBrowserPreLoad() {
        return this.inAppBrowserPreLoad;
    }

    public void setInAppBrowserPreLoad(inAppBrowserPreLoad inappbrowserpreload) {
        this.inAppBrowserPreLoad = inappbrowserpreload;
    }
}

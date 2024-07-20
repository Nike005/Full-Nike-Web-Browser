package com.appnext.ads.fullscreen;

import android.content.Context;
import info.guardianproject.netcipher.proxy.TorServiceUtils;

public class FullScreenVideo extends Video {
    private long closeDelay;
    private boolean showClose;

    public String getAUID() {
        return TorServiceUtils.CHMOD_EXE_VALUE;
    }

    protected FullScreenVideo(Context context, FullScreenVideo fullScreenVideo) {
        super(context, fullScreenVideo);
        this.showClose = true;
        setShowClose(fullScreenVideo.isShowClose());
        setCloseDelay(fullScreenVideo.getCloseDelay());
        setBackButtonCanClose(fullScreenVideo.isBackButtonCanClose());
    }

    public FullScreenVideo(Context context, String str) {
        super(context, 1, str);
        this.showClose = true;
        this.closeDelay = (long) Integer.parseInt(getConfig().get("show_close_time"));
    }

    public FullScreenVideo(Context context, String str, FullscreenConfig fullscreenConfig) {
        super(context, 1, str, fullscreenConfig);
        this.showClose = true;
        if (fullscreenConfig != null) {
            setBackButtonCanClose(fullscreenConfig.isBackButtonCanClose());
            if (fullscreenConfig.showClose != null) {
                setShowClose(fullscreenConfig.isShowClose());
            }
            setCloseDelay(fullscreenConfig.closeDelay);
        }
    }

    public boolean isShowClose() {
        return this.showClose;
    }

    public void setShowClose(boolean z) {
        this.showClose = z;
    }

    public void setShowClose(boolean z, long j) {
        this.showClose = z;
        if (j >= 100 && j <= 7500) {
            this.closeDelay = j;
        }
    }

    public long getCloseDelay() {
        return this.closeDelay;
    }

    /* access modifiers changed from: protected */
    public C4727c getConfig() {
        return C4727c.m6296m();
    }

    /* access modifiers changed from: protected */
    public void setCloseDelay(long j) {
        this.closeDelay = j;
    }
}

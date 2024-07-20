package com.integralads.avid.library.mopub.session.internal;

import com.google.firebase.messaging.Constants;

public enum SessionType {
    DISPLAY(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION),
    VIDEO("video"),
    MANAGED_DISPLAY("managedDisplay"),
    MANAGED_VIDEO("managedVideo");
    
    private final String value;

    private SessionType(String str) {
        this.value = str;
    }

    public String toString() {
        return this.value;
    }
}

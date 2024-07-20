package com.integralads.avid.library.mopub.session.internal;

import com.google.firebase.messaging.Constants;

public enum MediaType {
    DISPLAY(Constants.ScionAnalytics.MessageType.DISPLAY_NOTIFICATION),
    VIDEO("video");
    
    private final String value;

    private MediaType(String str) {
        this.value = str;
    }

    public String toString() {
        return this.value;
    }
}

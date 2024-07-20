package com.mopub.common.privacy;

import androidx.core.p067os.EnvironmentCompat;

public enum ConsentStatus {
    EXPLICIT_YES("explicit_yes"),
    EXPLICIT_NO("explicit_no"),
    UNKNOWN(EnvironmentCompat.MEDIA_UNKNOWN),
    POTENTIAL_WHITELIST("potential_whitelist"),
    DNT("dnt");
    
    private final String mValue;

    private ConsentStatus(String str) {
        this.mValue = str;
    }

    public String getValue() {
        return this.mValue;
    }

    public static ConsentStatus fromString(String str) {
        if (str == null) {
            return UNKNOWN;
        }
        for (ConsentStatus consentStatus : values()) {
            if (str.equals(consentStatus.name())) {
                return consentStatus;
            }
        }
        return UNKNOWN;
    }
}

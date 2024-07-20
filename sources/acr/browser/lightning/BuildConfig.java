package acr.browser.lightning;

import org.htmlcleaner.CleanerProperties;

public final class BuildConfig {
    public static final String APPLICATION_ID = "acr.browser.lightning";
    public static final String BUILD_TYPE = "release";
    public static final boolean DEBUG = false;
    public static final String FLAVOR = "lightningPlus";
    public static final boolean FULL_VERSION = Boolean.parseBoolean(CleanerProperties.BOOL_ATT_TRUE);
    public static final int VERSION_CODE = 95;
    public static final String VERSION_NAME = "1.0.0";
}

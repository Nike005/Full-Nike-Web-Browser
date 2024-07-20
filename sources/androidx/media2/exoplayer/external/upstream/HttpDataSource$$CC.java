package androidx.media2.exoplayer.external.upstream;

import android.text.TextUtils;
import androidx.media2.exoplayer.external.util.Predicate;
import androidx.media2.exoplayer.external.util.Util;
import com.mopub.common.AdType;
import org.jdom2.JDOMConstants;

public abstract /* synthetic */ class HttpDataSource$$CC {
    public static void $$triggerInterfaceInit() {
        Predicate<String> predicate = HttpDataSource.REJECT_PAYWALL_TYPES;
    }

    static /* synthetic */ boolean lambda$static$0$HttpDataSource$$CC(String str) {
        String lowerInvariant = Util.toLowerInvariant(str);
        return !TextUtils.isEmpty(lowerInvariant) && (!lowerInvariant.contains("text") || lowerInvariant.contains("text/vtt")) && !lowerInvariant.contains(AdType.HTML) && !lowerInvariant.contains(JDOMConstants.NS_PREFIX_XML);
    }
}

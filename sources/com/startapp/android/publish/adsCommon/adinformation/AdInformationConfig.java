package com.startapp.android.publish.adsCommon.adinformation;

import android.content.Context;
import com.startapp.android.publish.adsCommon.C1166k;
import com.startapp.android.publish.adsCommon.adinformation.AdInformationPositions;
import com.startapp.android.publish.common.model.AdPreferences;
import com.startapp.common.p046c.C5303f;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/* compiled from: StartAppSDK */
public class AdInformationConfig implements Serializable {
    private static final long serialVersionUID = 1;
    @C5303f(mo45478b = ArrayList.class, mo45479c = C1096e.class)
    private List<C1096e> ImageResources = new ArrayList();
    @C5303f(mo45478b = HashMap.class, mo45479c = AdInformationPositions.Position.class, mo45480d = AdPreferences.Placement.class)
    protected HashMap<AdPreferences.Placement, AdInformationPositions.Position> Positions = new HashMap<>();

    /* renamed from: a */
    private transient EnumMap<ImageResourceType, C1096e> f1038a = new EnumMap<>(ImageResourceType.class);
    private String dialogUrlSecured = "https://d1byvlfiet2h9q.cloudfront.net/InApp/resources/adInformationDialog3.html";
    private boolean enabled = true;
    private String eulaUrlSecured = "https://www.com.startapp.com/policy/sdk-policy/";
    private float fatFingersFactor = 200.0f;

    /* compiled from: StartAppSDK */
    public enum ImageResourceType {
        INFO_S(17, 14),
        INFO_EX_S(88, 14),
        INFO_L(25, 21),
        INFO_EX_L(130, 21);
        
        private int height;
        private int width;

        private ImageResourceType(int i, int i2) {
            this.width = i;
            this.height = i2;
        }

        public int getDefaultWidth() {
            return this.width;
        }

        public int getDefaultHeight() {
            return this.height;
        }

        public static ImageResourceType getByName(String str) {
            ImageResourceType imageResourceType = INFO_S;
            ImageResourceType[] values = values();
            for (int i = 0; i < values.length; i++) {
                if (values[i].name().toLowerCase().compareTo(str.toLowerCase()) == 0) {
                    imageResourceType = values[i];
                }
            }
            return imageResourceType;
        }
    }

    private AdInformationConfig() {
    }

    /* renamed from: a */
    public static AdInformationConfig m1237a() {
        AdInformationConfig adInformationConfig = new AdInformationConfig();
        m1238a(adInformationConfig);
        return adInformationConfig;
    }

    /* renamed from: a */
    public static void m1238a(AdInformationConfig adInformationConfig) {
        adInformationConfig.mo14697i();
        adInformationConfig.mo14696h();
    }

    /* renamed from: b */
    public String mo14690b() {
        String str = this.eulaUrlSecured;
        return (str == null || str.equals("")) ? "https://www.com.startapp.com/policy/sdk-policy/" : this.eulaUrlSecured;
    }

    /* renamed from: c */
    public String mo14691c() {
        return (!this.f1038a.containsKey(ImageResourceType.INFO_L) || this.f1038a.get(ImageResourceType.INFO_L).mo14743d().equals("")) ? "https://info.startappservice.com/InApp/resources/info_l.png" : this.f1038a.get(ImageResourceType.INFO_L).mo14743d();
    }

    /* renamed from: d */
    public boolean mo14692d() {
        return this.enabled;
    }

    /* renamed from: a */
    public boolean mo14689a(Context context) {
        return !C1166k.m1606a(context, "userDisabledAdInformation", (Boolean) false).booleanValue() && mo14692d();
    }

    /* renamed from: a */
    public void mo14687a(Context context, boolean z) {
        C1166k.m1613b(context, "userDisabledAdInformation", Boolean.valueOf(!z));
    }

    /* renamed from: e */
    public float mo14693e() {
        return this.fatFingersFactor / 100.0f;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void mo14688a(ImageResourceType imageResourceType, C1096e eVar) {
        mo14698j().put(imageResourceType, eVar);
    }

    /* renamed from: f */
    public String mo14694f() {
        String str = this.dialogUrlSecured;
        return str != null ? str : "https://d1byvlfiet2h9q.cloudfront.net/InApp/resources/adInformationDialog3.html";
    }

    /* renamed from: a */
    public AdInformationPositions.Position mo14685a(AdPreferences.Placement placement) {
        AdInformationPositions.Position position = this.Positions.get(placement);
        if (position != null) {
            return position;
        }
        AdInformationPositions.Position position2 = AdInformationPositions.Position.BOTTOM_LEFT;
        this.Positions.put(placement, position2);
        return position2;
    }

    /* renamed from: a */
    public C1096e mo14686a(ImageResourceType imageResourceType) {
        return mo14698j().get(imageResourceType);
    }

    /* renamed from: g */
    public void mo14695g() {
        for (C1096e next : this.ImageResources) {
            mo14688a(ImageResourceType.getByName(next.mo14734a()), next);
            next.mo14744e();
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: h */
    public void mo14696h() {
        ImageResourceType[] values = ImageResourceType.values();
        int length = values.length;
        int i = 0;
        while (i < length) {
            ImageResourceType imageResourceType = values[i];
            if (mo14698j().get(imageResourceType) != null) {
                i++;
            } else {
                throw new IllegalArgumentException("AdInformation error in ImageResource [" + imageResourceType + "] cannot be found in MetaData");
            }
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public void mo14697i() {
        for (ImageResourceType imageResourceType : ImageResourceType.values()) {
            C1096e eVar = mo14698j().get(imageResourceType);
            Boolean bool = true;
            if (eVar == null) {
                eVar = C1096e.m1285c(imageResourceType.name());
                Iterator<C1096e> it = this.ImageResources.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (ImageResourceType.getByName(it.next().mo14734a()).equals(imageResourceType)) {
                            bool = false;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                mo14698j().put(imageResourceType, eVar);
                if (bool.booleanValue()) {
                    this.ImageResources.add(eVar);
                }
            }
            eVar.mo14735a(imageResourceType.getDefaultWidth());
            eVar.mo14740b(imageResourceType.getDefaultHeight());
            eVar.mo14737a(imageResourceType.name().toLowerCase() + ".png");
        }
    }

    /* renamed from: j */
    public EnumMap<ImageResourceType, C1096e> mo14698j() {
        return this.f1038a;
    }

    /* renamed from: k */
    public void mo14699k() {
        this.f1038a = new EnumMap<>(ImageResourceType.class);
    }
}

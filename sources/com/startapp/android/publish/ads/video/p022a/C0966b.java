package com.startapp.android.publish.ads.video.p022a;

import android.text.TextUtils;
import com.startapp.android.publish.ads.video.p024c.p025a.C0989a;
import com.startapp.android.publish.ads.video.tracking.VideoTrackingLink;
import com.startapp.android.publish.ads.video.tracking.VideoTrackingParams;
import com.startapp.android.publish.adsCommon.C1103c;
import com.startapp.common.p043a.C1253a;
import com.startapp.common.p043a.C1270g;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* renamed from: com.startapp.android.publish.ads.video.a.b */
/* compiled from: StartAppSDK */
public class C0966b {

    /* renamed from: a */
    private VideoTrackingLink[] f679a;

    /* renamed from: b */
    private VideoTrackingParams f680b;

    /* renamed from: c */
    private String f681c;

    /* renamed from: d */
    private int f682d;

    /* renamed from: e */
    private String f683e = "";

    /* renamed from: f */
    private C0989a f684f;

    public C0966b(VideoTrackingLink[] videoTrackingLinkArr, VideoTrackingParams videoTrackingParams, String str, int i) {
        this.f679a = videoTrackingLinkArr;
        this.f680b = videoTrackingParams;
        this.f681c = str;
        this.f682d = i;
    }

    /* renamed from: a */
    public C0966b mo14257a(C0989a aVar) {
        this.f684f = aVar;
        return this;
    }

    /* renamed from: a */
    public C0966b mo14258a(String str) {
        this.f683e = str;
        return this;
    }

    /* renamed from: a */
    public C0965a mo14256a() {
        if (!m828b()) {
            C1270g.m2078a("VideoEventBuilder", 3, "Some fields have illegal values");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (VideoTrackingLink videoTrackingLink : this.f679a) {
            if (videoTrackingLink.getTrackingUrl() == null) {
                C1270g.m2078a("VideoEventBuilder", 5, "Ignoring tracking link - tracking url is null: tracking link = " + videoTrackingLink);
            } else if (this.f680b.getOffset() <= 0 || videoTrackingLink.shouldAppendReplay()) {
                arrayList.add(m825a(videoTrackingLink));
            } else {
                C1270g.m2078a("VideoEventBuilder", 3, "Ignoring tracking link - external replay event: tracking link = " + videoTrackingLink);
            }
        }
        return new C0965a(arrayList, this.f683e);
    }

    /* renamed from: b */
    private boolean m828b() {
        return (this.f679a == null || this.f680b == null) ? false : true;
    }

    /* renamed from: a */
    private String m825a(VideoTrackingLink videoTrackingLink) {
        StringBuilder sb = new StringBuilder();
        VideoTrackingParams b = m826b(videoTrackingLink);
        String trackingUrl = videoTrackingLink.getTrackingUrl();
        sb.append(m827b(trackingUrl));
        sb.append(b.getQueryString());
        if (b.getInternalTrackingParamsIndicator()) {
            sb.append(C1253a.m1982a(C1103c.m1404e(trackingUrl)));
        }
        return sb.toString();
    }

    /* renamed from: b */
    private VideoTrackingParams m826b(VideoTrackingLink videoTrackingLink) {
        VideoTrackingLink.TrackingSource trackingSource = videoTrackingLink.getTrackingSource();
        return this.f680b.setInternalTrackingParamsIndicator(trackingSource != null && trackingSource == VideoTrackingLink.TrackingSource.STARTAPP).setShouldAppendOffset(videoTrackingLink.shouldAppendReplay()).setReplayParameter(videoTrackingLink.getReplayParameter());
    }

    /* renamed from: b */
    private String m827b(String str) {
        String str2 = this.f681c;
        String replace = str.replace("[ASSETURI]", str2 != null ? TextUtils.htmlEncode(str2) : "").replace("[CONTENTPLAYHEAD]", TextUtils.htmlEncode(m824a(this.f682d))).replace("[CACHEBUSTING]", TextUtils.htmlEncode(m829c())).replace("[TIMESTAMP]", TextUtils.htmlEncode(m830d()));
        C0989a aVar = this.f684f;
        return aVar != null ? replace.replace("[ERRORCODE]", String.valueOf(aVar.mo14298a())) : replace;
    }

    /* renamed from: c */
    private static String m829c() {
        return String.valueOf(new SecureRandom().nextInt(90000000) + 10000000);
    }

    /* renamed from: a */
    private static String m824a(int i) {
        long convert = TimeUnit.SECONDS.convert((long) i, TimeUnit.MILLISECONDS);
        long j = convert / 3600;
        long j2 = (long) (i % 1000);
        return String.format(Locale.US, "%02d:%02d:%02d.%03d", new Object[]{Long.valueOf(j), Long.valueOf((convert % 3600) / 60), Long.valueOf(convert % 60), Long.valueOf(j2)});
    }

    /* renamed from: d */
    private static String m830d() {
        String format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US).format(new Date());
        int length = format.length() - 2;
        return format.substring(0, length) + ":" + format.substring(length);
    }
}

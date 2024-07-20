package com.startapp.android.publish.ads.video.tracking;

import com.startapp.android.publish.ads.video.p024c.p025a.C0995b;
import com.startapp.android.publish.ads.video.p024c.p025a.C1001e;
import com.startapp.android.publish.ads.video.p024c.p025a.p026a.C0992c;
import com.startapp.common.p046c.C5303f;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/* compiled from: StartAppSDK */
public class VideoTrackingDetails implements Serializable {
    private static final long serialVersionUID = 1;
    @C5303f(mo45478b = AbsoluteTrackingLink.class)
    private AbsoluteTrackingLink[] absoluteTrackingUrls;
    @C5303f(mo45478b = ActionTrackingLink.class)
    private ActionTrackingLink[] creativeViewUrls;
    @C5303f(mo45478b = FractionTrackingLink.class)
    private FractionTrackingLink[] fractionTrackingUrls;
    @C5303f(mo45478b = ActionTrackingLink.class)
    private ActionTrackingLink[] impressionUrls;
    @C5303f(mo45478b = ActionTrackingLink.class)
    private ActionTrackingLink[] inlineErrorTrackingUrls;
    @C5303f(mo45478b = ActionTrackingLink.class)
    private ActionTrackingLink[] soundMuteUrls;
    @C5303f(mo45478b = ActionTrackingLink.class)
    private ActionTrackingLink[] soundUnmuteUrls;
    @C5303f(mo45478b = ActionTrackingLink.class)
    private ActionTrackingLink[] videoClickTrackingUrls;
    @C5303f(mo45478b = ActionTrackingLink.class)
    private ActionTrackingLink[] videoClosedUrls;
    @C5303f(mo45478b = ActionTrackingLink.class)
    private ActionTrackingLink[] videoPausedUrls;
    @C5303f(mo45478b = ActionTrackingLink.class)
    private ActionTrackingLink[] videoPostRollClosedUrls;
    @C5303f(mo45478b = ActionTrackingLink.class)
    private ActionTrackingLink[] videoPostRollImpressionUrls;
    @C5303f(mo45478b = ActionTrackingLink.class)
    private ActionTrackingLink[] videoResumedUrls;
    @C5303f(mo45478b = ActionTrackingLink.class)
    private ActionTrackingLink[] videoRewardedUrls;
    @C5303f(mo45478b = ActionTrackingLink.class)
    private ActionTrackingLink[] videoSkippedUrls;

    public VideoTrackingDetails() {
    }

    public VideoTrackingDetails(C1001e eVar) {
        if (eVar != null) {
            HashMap<C0995b, List<C0992c>> a = eVar.mo14352a();
            ArrayList arrayList = new ArrayList();
            addFractionUrls(a.get(C0995b.start), 0, arrayList);
            addFractionUrls(a.get(C0995b.firstQuartile), 25, arrayList);
            addFractionUrls(a.get(C0995b.midpoint), 50, arrayList);
            addFractionUrls(a.get(C0995b.thirdQuartile), 75, arrayList);
            addFractionUrls(a.get(C0995b.complete), 100, arrayList);
            this.fractionTrackingUrls = (FractionTrackingLink[]) arrayList.toArray(new FractionTrackingLink[arrayList.size()]);
            this.impressionUrls = urlToTrackingList(eVar.mo14356d());
            this.soundMuteUrls = trackingToTrackingList(a.get(C0995b.mute));
            this.soundUnmuteUrls = trackingToTrackingList(a.get(C0995b.unmute));
            this.videoPausedUrls = trackingToTrackingList(a.get(C0995b.pause));
            this.videoResumedUrls = trackingToTrackingList(a.get(C0995b.resume));
            this.videoSkippedUrls = trackingToTrackingList(a.get(C0995b.skip));
            this.videoPausedUrls = trackingToTrackingList(a.get(C0995b.pause));
            this.videoClosedUrls = trackingToTrackingList(a.get(C0995b.close));
            this.inlineErrorTrackingUrls = urlToTrackingList(eVar.mo14357e());
            this.videoClickTrackingUrls = urlToTrackingList(eVar.mo14355c().mo14341b());
            this.absoluteTrackingUrls = toAbsoluteTrackingList(a.get(C0995b.progress));
        }
    }

    public FractionTrackingLink[] getFractionTrackingUrls() {
        return this.fractionTrackingUrls;
    }

    public AbsoluteTrackingLink[] getAbsoluteTrackingUrls() {
        return this.absoluteTrackingUrls;
    }

    public ActionTrackingLink[] getImpressionUrls() {
        return this.impressionUrls;
    }

    public ActionTrackingLink[] getSoundUnmuteUrls() {
        return this.soundUnmuteUrls;
    }

    public ActionTrackingLink[] getCreativeViewUrls() {
        return this.creativeViewUrls;
    }

    public ActionTrackingLink[] getSoundMuteUrls() {
        return this.soundMuteUrls;
    }

    public ActionTrackingLink[] getVideoPausedUrls() {
        return this.videoPausedUrls;
    }

    public ActionTrackingLink[] getVideoResumedUrls() {
        return this.videoResumedUrls;
    }

    public ActionTrackingLink[] getVideoSkippedUrls() {
        return this.videoSkippedUrls;
    }

    public ActionTrackingLink[] getVideoClosedUrls() {
        return this.videoClosedUrls;
    }

    public ActionTrackingLink[] getVideoPostRollImpressionUrls() {
        return this.videoPostRollImpressionUrls;
    }

    public ActionTrackingLink[] getVideoPostRollClosedUrls() {
        return this.videoPostRollClosedUrls;
    }

    public ActionTrackingLink[] getVideoRewardedUrls() {
        return this.videoRewardedUrls;
    }

    public ActionTrackingLink[] getVideoClickTrackingUrls() {
        return this.videoClickTrackingUrls;
    }

    public ActionTrackingLink[] getInlineErrorTrackingUrls() {
        return this.inlineErrorTrackingUrls;
    }

    public String toString() {
        return "VideoTrackingDetails [fractionTrackingUrls=" + printTrackingLinks(this.fractionTrackingUrls) + ", absoluteTrackingUrls=" + printTrackingLinks(this.absoluteTrackingUrls) + ", impressionUrls=" + printTrackingLinks(this.impressionUrls) + ", creativeViewUrls=" + printTrackingLinks(this.creativeViewUrls) + ", soundMuteUrls=" + printTrackingLinks(this.soundMuteUrls) + ", soundUnmuteUrls=" + printTrackingLinks(this.soundUnmuteUrls) + ", videoPausedUrls=" + printTrackingLinks(this.videoPausedUrls) + ", videoResumedUrls=" + printTrackingLinks(this.videoResumedUrls) + ", videoSkippedUrls=" + printTrackingLinks(this.videoSkippedUrls) + ", videoClosedUrls=" + printTrackingLinks(this.videoClosedUrls) + ", videoPostRollImpressionUrls=" + printTrackingLinks(this.videoPostRollImpressionUrls) + ", videoPostRollClosedUrls=" + printTrackingLinks(this.videoPostRollClosedUrls) + ", videoRewardedUrls=" + printTrackingLinks(this.videoRewardedUrls) + ", videoClickTrackingUrls=" + printTrackingLinks(this.videoClickTrackingUrls) + ", inlineErrorTrackingUrls=" + printTrackingLinks(this.inlineErrorTrackingUrls) + "]";
    }

    private String printTrackingLinks(VideoTrackingLink[] videoTrackingLinkArr) {
        return videoTrackingLinkArr != null ? Arrays.toString(videoTrackingLinkArr) : "";
    }

    private static void addFractionUrls(List<C0992c> list, int i, List<FractionTrackingLink> list2) {
        if (list != null) {
            for (C0992c a : list) {
                FractionTrackingLink fractionTrackingLink = new FractionTrackingLink();
                fractionTrackingLink.setTrackingUrl(a.mo14334a());
                fractionTrackingLink.setFraction(i);
                fractionTrackingLink.setAppendReplayParameter(true);
                fractionTrackingLink.setReplayParameter("");
                list2.add(fractionTrackingLink);
            }
        }
    }

    private static ActionTrackingLink[] trackingToTrackingList(List<C0992c> list) {
        if (list == null) {
            return new ActionTrackingLink[0];
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (C0992c a : list) {
            ActionTrackingLink actionTrackingLink = new ActionTrackingLink();
            actionTrackingLink.setTrackingUrl(a.mo14334a());
            arrayList.add(actionTrackingLink);
        }
        return (ActionTrackingLink[]) arrayList.toArray(new ActionTrackingLink[arrayList.size()]);
    }

    private static ActionTrackingLink[] urlToTrackingList(List<String> list) {
        if (list == null) {
            return new ActionTrackingLink[0];
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (String trackingUrl : list) {
            ActionTrackingLink actionTrackingLink = new ActionTrackingLink();
            actionTrackingLink.setTrackingUrl(trackingUrl);
            arrayList.add(actionTrackingLink);
        }
        return (ActionTrackingLink[]) arrayList.toArray(new ActionTrackingLink[arrayList.size()]);
    }

    private AbsoluteTrackingLink[] toAbsoluteTrackingList(List<C0992c> list) {
        if (list == null) {
            return new AbsoluteTrackingLink[0];
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (C0992c next : list) {
            AbsoluteTrackingLink absoluteTrackingLink = new AbsoluteTrackingLink();
            absoluteTrackingLink.setTrackingUrl(next.mo14334a());
            if (next.mo14335b() != -1) {
                absoluteTrackingLink.setVideoOffsetMillis(next.mo14335b());
            }
            arrayList.add(absoluteTrackingLink);
        }
        return (AbsoluteTrackingLink[]) arrayList.toArray(new AbsoluteTrackingLink[arrayList.size()]);
    }
}

package com.google.android.exoplayer2.source.dash.manifest;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Pair;
import android.util.Xml;
import com.appnext.base.p078a.p081c.C4892d;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.emsg.EventMessage;
import com.google.android.exoplayer2.source.dash.manifest.SegmentBase;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.Assertions;
import com.google.android.exoplayer2.util.Log;
import com.google.android.exoplayer2.util.MimeTypes;
import com.google.android.exoplayer2.util.UriUtil;
import com.google.android.exoplayer2.util.Util;
import com.google.android.exoplayer2.util.XmlPullParserUtil;
import com.google.android.gms.plus.PlusShare;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionContext;
import com.mopub.mobileads.VastIconXmlManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

public class DashManifestParser extends DefaultHandler implements ParsingLoadable.Parser<DashManifest> {
    private static final Pattern CEA_608_ACCESSIBILITY_PATTERN = Pattern.compile("CC([1-4])=.*");
    private static final Pattern CEA_708_ACCESSIBILITY_PATTERN = Pattern.compile("([1-9]|[1-5][0-9]|6[0-3])=.*");
    private static final Pattern FRAME_RATE_PATTERN = Pattern.compile("(\\d+)(?:/(\\d+))?");
    private static final String TAG = "MpdParser";
    private final XmlPullParserFactory xmlParserFactory;

    public DashManifestParser() {
        try {
            this.xmlParserFactory = XmlPullParserFactory.newInstance();
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    public DashManifest parse(Uri uri, InputStream inputStream) throws IOException {
        try {
            XmlPullParser newPullParser = this.xmlParserFactory.newPullParser();
            newPullParser.setInput(inputStream, (String) null);
            if (newPullParser.next() == 2 && "MPD".equals(newPullParser.getName())) {
                return parseMediaPresentationDescription(newPullParser, uri.toString());
            }
            throw new ParserException("inputStream does not contain a valid media presentation description");
        } catch (XmlPullParserException e) {
            throw new ParserException((Throwable) e);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0177 A[LOOP:0: B:15:0x0069->B:62:0x0177, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0133 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.exoplayer2.source.dash.manifest.DashManifest parseMediaPresentationDescription(org.xmlpull.v1.XmlPullParser r34, java.lang.String r35) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r33 = this;
            r14 = r33
            r0 = r34
            java.lang.String r1 = "availabilityStartTime"
            r2 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            long r4 = parseDateTime(r0, r1, r2)
            java.lang.String r1 = "mediaPresentationDuration"
            long r6 = parseDuration(r0, r1, r2)
            java.lang.String r1 = "minBufferTime"
            long r8 = parseDuration(r0, r1, r2)
            r1 = 0
            java.lang.String r10 = "type"
            java.lang.String r10 = r0.getAttributeValue(r1, r10)
            java.lang.String r11 = "dynamic"
            boolean r10 = r11.equals(r10)
            if (r10 == 0) goto L_0x0031
            java.lang.String r11 = "minimumUpdatePeriod"
            long r11 = parseDuration(r0, r11, r2)
            goto L_0x0032
        L_0x0031:
            r11 = r2
        L_0x0032:
            if (r10 == 0) goto L_0x003b
            java.lang.String r13 = "timeShiftBufferDepth"
            long r15 = parseDuration(r0, r13, r2)
            goto L_0x003c
        L_0x003b:
            r15 = r2
        L_0x003c:
            if (r10 == 0) goto L_0x0045
            java.lang.String r13 = "suggestedPresentationDelay"
            long r17 = parseDuration(r0, r13, r2)
            goto L_0x0047
        L_0x0045:
            r17 = r2
        L_0x0047:
            java.lang.String r13 = "publishTime"
            long r19 = parseDateTime(r0, r13, r2)
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            if (r10 == 0) goto L_0x0057
            r21 = r2
            goto L_0x0059
        L_0x0057:
            r21 = 0
        L_0x0059:
            r23 = 0
            r23 = r1
            r2 = r21
            r26 = 0
            r27 = 0
            r1 = r35
            r21 = r23
            r22 = r21
        L_0x0069:
            r34.next()
            r28 = r15
            java.lang.String r15 = "BaseURL"
            boolean r15 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r15)
            r16 = 1
            if (r15 == 0) goto L_0x0088
            if (r26 != 0) goto L_0x0082
            java.lang.String r1 = r14.parseBaseUrl(r0, r1)
            r26 = 1
            goto L_0x012b
        L_0x0082:
            r35 = r1
            r30 = r2
            goto L_0x0127
        L_0x0088:
            java.lang.String r15 = "ProgramInformation"
            boolean r15 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r15)
            if (r15 == 0) goto L_0x0098
            com.google.android.exoplayer2.source.dash.manifest.ProgramInformation r15 = r33.parseProgramInformation(r34)
            r21 = r15
            goto L_0x012b
        L_0x0098:
            java.lang.String r15 = "UTCTiming"
            boolean r15 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r15)
            if (r15 == 0) goto L_0x00a8
            com.google.android.exoplayer2.source.dash.manifest.UtcTimingElement r15 = r33.parseUtcTiming(r34)
            r22 = r15
            goto L_0x012b
        L_0x00a8:
            java.lang.String r15 = "Location"
            boolean r15 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r15)
            if (r15 == 0) goto L_0x00bc
            java.lang.String r15 = r34.nextText()
            android.net.Uri r15 = android.net.Uri.parse(r15)
            r23 = r15
            goto L_0x012b
        L_0x00bc:
            java.lang.String r15 = "Period"
            boolean r15 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r15)
            if (r15 == 0) goto L_0x0120
            if (r27 != 0) goto L_0x0120
            android.util.Pair r15 = r14.parsePeriod(r0, r1, r2)
            r35 = r1
            java.lang.Object r1 = r15.first
            com.google.android.exoplayer2.source.dash.manifest.Period r1 = (com.google.android.exoplayer2.source.dash.manifest.Period) r1
            r30 = r2
            long r2 = r1.startMs
            r24 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r32 = (r2 > r24 ? 1 : (r2 == r24 ? 0 : -1))
            if (r32 != 0) goto L_0x00ff
            if (r10 == 0) goto L_0x00e4
            r2 = r30
            r27 = 1
            goto L_0x011d
        L_0x00e4:
            com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Unable to determine start of period "
            r1.append(r2)
            int r2 = r13.size()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x00ff:
            java.lang.Object r2 = r15.second
            java.lang.Long r2 = (java.lang.Long) r2
            long r2 = r2.longValue()
            r15 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r30 = (r2 > r15 ? 1 : (r2 == r15 ? 0 : -1))
            if (r30 != 0) goto L_0x0116
            r2 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            goto L_0x011a
        L_0x0116:
            long r14 = r1.startMs
            long r14 = r14 + r2
            r2 = r14
        L_0x011a:
            r13.add(r1)
        L_0x011d:
            r1 = r35
            goto L_0x012b
        L_0x0120:
            r35 = r1
            r30 = r2
            maybeSkipTag(r34)
        L_0x0127:
            r1 = r35
            r2 = r30
        L_0x012b:
            java.lang.String r14 = "MPD"
            boolean r14 = com.google.android.exoplayer2.util.XmlPullParserUtil.isEndTag(r0, r14)
            if (r14 == 0) goto L_0x0177
            r14 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r0 = (r6 > r14 ? 1 : (r6 == r14 ? 0 : -1))
            if (r0 != 0) goto L_0x014d
            int r0 = (r2 > r14 ? 1 : (r2 == r14 ? 0 : -1))
            if (r0 == 0) goto L_0x0142
            r6 = r2
            goto L_0x014d
        L_0x0142:
            if (r10 == 0) goto L_0x0145
            goto L_0x014d
        L_0x0145:
            com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException
            java.lang.String r1 = "Unable to determine duration of static manifest."
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x014d:
            boolean r0 = r13.isEmpty()
            if (r0 != 0) goto L_0x016f
            r0 = r33
            r1 = r4
            r3 = r6
            r5 = r8
            r7 = r10
            r8 = r11
            r10 = r28
            r24 = r13
            r12 = r17
            r14 = r19
            r16 = r21
            r17 = r22
            r18 = r23
            r19 = r24
            com.google.android.exoplayer2.source.dash.manifest.DashManifest r0 = r0.buildMediaPresentationDescription(r1, r3, r5, r7, r8, r10, r12, r14, r16, r17, r18, r19)
            return r0
        L_0x016f:
            com.google.android.exoplayer2.ParserException r0 = new com.google.android.exoplayer2.ParserException
            java.lang.String r1 = "No periods found."
            r0.<init>((java.lang.String) r1)
            throw r0
        L_0x0177:
            r14 = r33
            r15 = r28
            goto L_0x0069
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.parseMediaPresentationDescription(org.xmlpull.v1.XmlPullParser, java.lang.String):com.google.android.exoplayer2.source.dash.manifest.DashManifest");
    }

    /* access modifiers changed from: protected */
    public DashManifest buildMediaPresentationDescription(long j, long j2, long j3, boolean z, long j4, long j5, long j6, long j7, ProgramInformation programInformation, UtcTimingElement utcTimingElement, Uri uri, List<Period> list) {
        return new DashManifest(j, j2, j3, z, j4, j5, j6, j7, programInformation, utcTimingElement, uri, list);
    }

    /* access modifiers changed from: protected */
    public UtcTimingElement parseUtcTiming(XmlPullParser xmlPullParser) {
        return buildUtcTimingElement(xmlPullParser.getAttributeValue((String) null, "schemeIdUri"), xmlPullParser.getAttributeValue((String) null, "value"));
    }

    /* access modifiers changed from: protected */
    public UtcTimingElement buildUtcTimingElement(String str, String str2) {
        return new UtcTimingElement(str, str2);
    }

    /* access modifiers changed from: protected */
    public Pair<Period, Long> parsePeriod(XmlPullParser xmlPullParser, String str, long j) throws XmlPullParserException, IOException {
        String str2;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        String attributeValue = xmlPullParser2.getAttributeValue((String) null, "id");
        long parseDuration = parseDuration(xmlPullParser2, "start", j);
        long parseDuration2 = parseDuration(xmlPullParser2, VastIconXmlManager.DURATION, -9223372036854775807L);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        String str3 = str;
        SegmentBase segmentBase = null;
        boolean z = false;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser2, "BaseURL")) {
                if (!z) {
                    str3 = parseBaseUrl(xmlPullParser2, str3);
                    z = true;
                } else {
                    str2 = str3;
                }
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "AdaptationSet")) {
                str2 = str3;
                arrayList.add(parseAdaptationSet(xmlPullParser, str3, segmentBase, parseDuration2));
            } else {
                str2 = str3;
                if (XmlPullParserUtil.isStartTag(xmlPullParser2, "EventStream")) {
                    arrayList2.add(parseEventStream(xmlPullParser));
                } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentBase")) {
                    segmentBase = parseSegmentBase(xmlPullParser2, (SegmentBase.SingleSegmentBase) null);
                } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentList")) {
                    segmentBase = parseSegmentList(xmlPullParser2, (SegmentBase.SegmentList) null, parseDuration2);
                } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentTemplate")) {
                    segmentBase = parseSegmentTemplate(xmlPullParser, (SegmentBase.SegmentTemplate) null, Collections.emptyList(), parseDuration2);
                } else {
                    maybeSkipTag(xmlPullParser);
                }
            }
            str3 = str2;
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser2, "Period"));
        return Pair.create(buildPeriod(attributeValue, parseDuration, arrayList, arrayList2), Long.valueOf(parseDuration2));
    }

    /* access modifiers changed from: protected */
    public Period buildPeriod(String str, long j, List<AdaptationSet> list, List<EventStream> list2) {
        return new Period(str, j, list, list2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v17, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r29v4, resolved type: java.lang.String} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x0266 A[LOOP:0: B:1:0x006e->B:63:0x0266, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x022d A[EDGE_INSN: B:64:0x022d->B:57:0x022d ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.exoplayer2.source.dash.manifest.AdaptationSet parseAdaptationSet(org.xmlpull.v1.XmlPullParser r42, java.lang.String r43, com.google.android.exoplayer2.source.dash.manifest.SegmentBase r44, long r45) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r41 = this;
            r15 = r41
            r14 = r42
            java.lang.String r0 = "id"
            r1 = -1
            int r17 = parseInt(r14, r0, r1)
            int r0 = r41.parseContentType(r42)
            r13 = 0
            java.lang.String r2 = "mimeType"
            java.lang.String r18 = r14.getAttributeValue(r13, r2)
            java.lang.String r2 = "codecs"
            java.lang.String r19 = r14.getAttributeValue(r13, r2)
            java.lang.String r2 = "width"
            int r20 = parseInt(r14, r2, r1)
            java.lang.String r2 = "height"
            int r21 = parseInt(r14, r2, r1)
            r2 = -1082130432(0xffffffffbf800000, float:-1.0)
            float r22 = parseFrameRate(r14, r2)
            java.lang.String r2 = "audioSamplingRate"
            int r23 = parseInt(r14, r2, r1)
            java.lang.String r12 = "lang"
            java.lang.String r2 = r14.getAttributeValue(r13, r12)
            java.lang.String r3 = "label"
            java.lang.String r3 = r14.getAttributeValue(r13, r3)
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            r24 = 0
            r5 = r43
            r25 = r44
            r4 = r0
            r27 = r3
            r29 = r13
            r26 = -1
            r28 = 0
            r3 = r2
        L_0x006e:
            r42.next()
            java.lang.String r0 = "BaseURL"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r14, r0)
            if (r0 == 0) goto L_0x00b0
            if (r28 != 0) goto L_0x0097
            java.lang.String r0 = r15.parseBaseUrl(r14, r5)
            r28 = 1
            r5 = r0
        L_0x0082:
            r34 = r7
            r35 = r8
            r36 = r9
            r38 = r11
            r39 = r12
            r40 = r13
            r9 = r14
            r7 = r4
        L_0x0090:
            r8 = r6
            r12 = r10
            r6 = r15
            r10 = r45
            goto L_0x0225
        L_0x0097:
            r30 = r3
            r32 = r5
            r34 = r7
            r35 = r8
            r36 = r9
            r38 = r11
            r39 = r12
            r40 = r13
            r9 = r14
            r7 = r4
            r8 = r6
            r12 = r10
            r6 = r15
            r10 = r45
            goto L_0x0221
        L_0x00b0:
            java.lang.String r0 = "ContentProtection"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r14, r0)
            if (r0 == 0) goto L_0x00d0
            android.util.Pair r0 = r41.parseContentProtection(r42)
            java.lang.Object r1 = r0.first
            if (r1 == 0) goto L_0x00c6
            java.lang.Object r1 = r0.first
            r29 = r1
            java.lang.String r29 = (java.lang.String) r29
        L_0x00c6:
            java.lang.Object r1 = r0.second
            if (r1 == 0) goto L_0x0082
            java.lang.Object r0 = r0.second
            r11.add(r0)
            goto L_0x0082
        L_0x00d0:
            java.lang.String r0 = "ContentComponent"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r14, r0)
            if (r0 == 0) goto L_0x00f8
            java.lang.String r0 = r14.getAttributeValue(r13, r12)
            java.lang.String r0 = checkLanguageConsistency(r3, r0)
            int r1 = r41.parseContentType(r42)
            int r1 = checkContentTypeConsistency(r4, r1)
            r3 = r0
            r34 = r7
            r35 = r8
            r36 = r9
            r38 = r11
            r39 = r12
            r40 = r13
            r9 = r14
            r7 = r1
            goto L_0x0090
        L_0x00f8:
            java.lang.String r0 = "Role"
            boolean r1 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r14, r0)
            if (r1 == 0) goto L_0x0108
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r0 = parseDescriptor(r14, r0)
            r8.add(r0)
            goto L_0x0097
        L_0x0108:
            java.lang.String r0 = "AudioChannelConfiguration"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r14, r0)
            if (r0 == 0) goto L_0x0118
            int r0 = r41.parseAudioChannelConfiguration(r42)
            r26 = r0
            goto L_0x0082
        L_0x0118:
            java.lang.String r0 = "Accessibility"
            boolean r1 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r14, r0)
            if (r1 == 0) goto L_0x0129
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r0 = parseDescriptor(r14, r0)
            r9.add(r0)
            goto L_0x0097
        L_0x0129:
            java.lang.String r0 = "SupplementalProperty"
            boolean r1 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r14, r0)
            if (r1 == 0) goto L_0x013a
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r0 = parseDescriptor(r14, r0)
            r7.add(r0)
            goto L_0x0097
        L_0x013a:
            java.lang.String r0 = "Representation"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r14, r0)
            if (r0 == 0) goto L_0x019b
            r0 = r41
            r1 = r42
            r2 = r5
            r30 = r3
            r3 = r18
            r31 = r4
            r4 = r19
            r32 = r5
            r5 = r20
            r33 = r6
            r6 = r21
            r34 = r7
            r7 = r22
            r35 = r8
            r8 = r26
            r36 = r9
            r9 = r23
            r37 = r10
            r10 = r30
            r38 = r11
            r11 = r35
            r39 = r12
            r12 = r36
            r40 = r13
            r13 = r34
            r14 = r25
            r15 = r45
            com.google.android.exoplayer2.source.dash.manifest.DashManifestParser$RepresentationInfo r0 = r0.parseRepresentation(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            com.google.android.exoplayer2.Format r1 = r0.format
            r6 = r41
            int r1 = r6.getContentType(r1)
            r7 = r31
            int r1 = checkContentTypeConsistency(r7, r1)
            r8 = r33
            r8.add(r0)
            r9 = r42
            r10 = r45
            r7 = r1
        L_0x0193:
            r3 = r30
            r5 = r32
            r12 = r37
            goto L_0x0225
        L_0x019b:
            r30 = r3
            r32 = r5
            r34 = r7
            r35 = r8
            r36 = r9
            r37 = r10
            r38 = r11
            r39 = r12
            r40 = r13
            r7 = r4
            r8 = r6
            r6 = r15
            java.lang.String r0 = "SegmentBase"
            r9 = r42
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r9, r0)
            if (r0 == 0) goto L_0x01c7
            r0 = r25
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r0 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SingleSegmentBase) r0
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r0 = r6.parseSegmentBase(r9, r0)
            r10 = r45
        L_0x01c4:
            r25 = r0
            goto L_0x0193
        L_0x01c7:
            java.lang.String r0 = "SegmentList"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r9, r0)
            if (r0 == 0) goto L_0x01da
            r0 = r25
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentList r0 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentList) r0
            r10 = r45
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentList r0 = r6.parseSegmentList(r9, r0, r10)
            goto L_0x01c4
        L_0x01da:
            r10 = r45
            java.lang.String r0 = "SegmentTemplate"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r9, r0)
            if (r0 == 0) goto L_0x01f5
            r2 = r25
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentTemplate r2 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentTemplate) r2
            r0 = r41
            r1 = r42
            r3 = r34
            r4 = r45
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentTemplate r0 = r0.parseSegmentTemplate(r1, r2, r3, r4)
            goto L_0x01c4
        L_0x01f5:
            java.lang.String r0 = "InbandEventStream"
            boolean r1 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r9, r0)
            if (r1 == 0) goto L_0x0207
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r0 = parseDescriptor(r9, r0)
            r12 = r37
            r12.add(r0)
            goto L_0x0221
        L_0x0207:
            r12 = r37
            java.lang.String r0 = "Label"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r9, r0)
            if (r0 == 0) goto L_0x0218
            java.lang.String r0 = r41.parseLabel(r42)
            r27 = r0
            goto L_0x0221
        L_0x0218:
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r42)
            if (r0 == 0) goto L_0x0221
            r41.parseAdaptationSetChild(r42)
        L_0x0221:
            r3 = r30
            r5 = r32
        L_0x0225:
            java.lang.String r0 = "AdaptationSet"
            boolean r0 = com.google.android.exoplayer2.util.XmlPullParserUtil.isEndTag(r9, r0)
            if (r0 == 0) goto L_0x0266
            java.util.ArrayList r9 = new java.util.ArrayList
            int r0 = r8.size()
            r9.<init>(r0)
            r10 = 0
        L_0x0237:
            int r0 = r8.size()
            if (r10 >= r0) goto L_0x0257
            java.lang.Object r0 = r8.get(r10)
            r1 = r0
            com.google.android.exoplayer2.source.dash.manifest.DashManifestParser$RepresentationInfo r1 = (com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.RepresentationInfo) r1
            r0 = r41
            r2 = r27
            r3 = r29
            r4 = r38
            r5 = r12
            com.google.android.exoplayer2.source.dash.manifest.Representation r0 = r0.buildRepresentation(r1, r2, r3, r4, r5)
            r9.add(r0)
            int r10 = r10 + 1
            goto L_0x0237
        L_0x0257:
            r0 = r41
            r1 = r17
            r2 = r7
            r3 = r9
            r4 = r36
            r5 = r34
            com.google.android.exoplayer2.source.dash.manifest.AdaptationSet r0 = r0.buildAdaptationSet(r1, r2, r3, r4, r5)
            return r0
        L_0x0266:
            r15 = r6
            r4 = r7
            r6 = r8
            r14 = r9
            r10 = r12
            r7 = r34
            r8 = r35
            r9 = r36
            r11 = r38
            r12 = r39
            r13 = r40
            goto L_0x006e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.parseAdaptationSet(org.xmlpull.v1.XmlPullParser, java.lang.String, com.google.android.exoplayer2.source.dash.manifest.SegmentBase, long):com.google.android.exoplayer2.source.dash.manifest.AdaptationSet");
    }

    /* access modifiers changed from: protected */
    public AdaptationSet buildAdaptationSet(int i, int i2, List<Representation> list, List<Descriptor> list2, List<Descriptor> list3) {
        return new AdaptationSet(i, i2, list, list2, list3);
    }

    /* access modifiers changed from: protected */
    public int parseContentType(XmlPullParser xmlPullParser) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, "contentType");
        if (TextUtils.isEmpty(attributeValue)) {
            return -1;
        }
        if ("audio".equals(attributeValue)) {
            return 1;
        }
        if ("video".equals(attributeValue)) {
            return 2;
        }
        if ("text".equals(attributeValue)) {
            return 3;
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    public int getContentType(Format format) {
        String str = format.sampleMimeType;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (MimeTypes.isVideo(str)) {
            return 2;
        }
        if (MimeTypes.isAudio(str)) {
            return 1;
        }
        if (mimeTypeIsRawText(str)) {
            return 3;
        }
        return -1;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x010c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.util.Pair<java.lang.String, com.google.android.exoplayer2.drm.DrmInitData.SchemeData> parseContentProtection(org.xmlpull.v1.XmlPullParser r10) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r9 = this;
            r0 = 0
            java.lang.String r1 = "schemeIdUri"
            java.lang.String r1 = r10.getAttributeValue(r0, r1)
            r2 = 0
            if (r1 == 0) goto L_0x0095
            java.lang.String r1 = com.google.android.exoplayer2.util.Util.toLowerInvariant(r1)
            r3 = -1
            int r4 = r1.hashCode()
            r5 = 489446379(0x1d2c5beb, float:2.281153E-21)
            r6 = 2
            r7 = 1
            if (r4 == r5) goto L_0x0039
            r5 = 755418770(0x2d06c692, float:7.66111E-12)
            if (r4 == r5) goto L_0x002f
            r5 = 1812765994(0x6c0c9d2a, float:6.799672E26)
            if (r4 == r5) goto L_0x0025
            goto L_0x0042
        L_0x0025:
            java.lang.String r4 = "urn:mpeg:dash:mp4protection:2011"
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x0042
            r3 = 0
            goto L_0x0042
        L_0x002f:
            java.lang.String r4 = "urn:uuid:edef8ba9-79d6-4ace-a3c8-27dcd51d21ed"
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x0042
            r3 = 2
            goto L_0x0042
        L_0x0039:
            java.lang.String r4 = "urn:uuid:9a04f079-9840-4286-ab92-e65be0885f95"
            boolean r1 = r1.equals(r4)
            if (r1 == 0) goto L_0x0042
            r3 = 1
        L_0x0042:
            if (r3 == 0) goto L_0x0050
            if (r3 == r7) goto L_0x004c
            if (r3 == r6) goto L_0x0049
            goto L_0x0095
        L_0x0049:
            java.util.UUID r1 = com.google.android.exoplayer2.C5211C.WIDEVINE_UUID
            goto L_0x004e
        L_0x004c:
            java.util.UUID r1 = com.google.android.exoplayer2.C5211C.PLAYREADY_UUID
        L_0x004e:
            r3 = r0
            goto L_0x0097
        L_0x0050:
            java.lang.String r1 = "value"
            java.lang.String r1 = r10.getAttributeValue(r0, r1)
            java.lang.String r3 = "default_KID"
            java.lang.String r3 = com.google.android.exoplayer2.util.XmlPullParserUtil.getAttributeValueIgnorePrefix(r10, r3)
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L_0x0090
            java.lang.String r4 = "00000000-0000-0000-0000-000000000000"
            boolean r4 = r4.equals(r3)
            if (r4 != 0) goto L_0x0090
            java.lang.String r4 = "\\s+"
            java.lang.String[] r3 = r3.split(r4)
            int r4 = r3.length
            java.util.UUID[] r4 = new java.util.UUID[r4]
            r5 = 0
        L_0x0074:
            int r6 = r3.length
            if (r5 >= r6) goto L_0x0082
            r6 = r3[r5]
            java.util.UUID r6 = java.util.UUID.fromString(r6)
            r4[r5] = r6
            int r5 = r5 + 1
            goto L_0x0074
        L_0x0082:
            java.util.UUID r3 = com.google.android.exoplayer2.C5211C.COMMON_PSSH_UUID
            byte[] r3 = com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil.buildPsshAtom(r3, r4, r0)
            java.util.UUID r4 = com.google.android.exoplayer2.C5211C.COMMON_PSSH_UUID
            r5 = r0
            r8 = r3
            r3 = r1
            r1 = r4
            r4 = r8
            goto L_0x0099
        L_0x0090:
            r4 = r0
            r5 = r4
            r3 = r1
            r1 = r5
            goto L_0x0099
        L_0x0095:
            r1 = r0
            r3 = r1
        L_0x0097:
            r4 = r3
            r5 = r4
        L_0x0099:
            r10.next()
            java.lang.String r6 = "ms:laurl"
            boolean r6 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r10, r6)
            if (r6 == 0) goto L_0x00ab
            java.lang.String r5 = "licenseUrl"
            java.lang.String r5 = r10.getAttributeValue(r0, r5)
            goto L_0x0102
        L_0x00ab:
            r6 = 4
            if (r4 != 0) goto L_0x00d8
            java.lang.String r7 = "pssh"
            boolean r7 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTagIgnorePrefix(r10, r7)
            if (r7 == 0) goto L_0x00d8
            int r7 = r10.next()
            if (r7 != r6) goto L_0x00d8
            java.lang.String r1 = r10.getText()
            byte[] r1 = android.util.Base64.decode(r1, r2)
            java.util.UUID r4 = com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil.parseUuid(r1)
            if (r4 != 0) goto L_0x00d4
            java.lang.String r1 = "MpdParser"
            java.lang.String r6 = "Skipping malformed cenc:pssh data"
            com.google.android.exoplayer2.util.Log.m9w(r1, r6)
            r1 = r4
            r4 = r0
            goto L_0x0102
        L_0x00d4:
            r8 = r4
            r4 = r1
            r1 = r8
            goto L_0x0102
        L_0x00d8:
            if (r4 != 0) goto L_0x00ff
            java.util.UUID r7 = com.google.android.exoplayer2.C5211C.PLAYREADY_UUID
            boolean r7 = r7.equals(r1)
            if (r7 == 0) goto L_0x00ff
            java.lang.String r7 = "mspr:pro"
            boolean r7 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r10, r7)
            if (r7 == 0) goto L_0x00ff
            int r7 = r10.next()
            if (r7 != r6) goto L_0x00ff
            java.util.UUID r4 = com.google.android.exoplayer2.C5211C.PLAYREADY_UUID
            java.lang.String r6 = r10.getText()
            byte[] r6 = android.util.Base64.decode(r6, r2)
            byte[] r4 = com.google.android.exoplayer2.extractor.mp4.PsshAtomUtil.buildPsshAtom(r4, r6)
            goto L_0x0102
        L_0x00ff:
            maybeSkipTag(r10)
        L_0x0102:
            java.lang.String r6 = "ContentProtection"
            boolean r6 = com.google.android.exoplayer2.util.XmlPullParserUtil.isEndTag(r10, r6)
            if (r6 == 0) goto L_0x0099
            if (r1 == 0) goto L_0x0113
            com.google.android.exoplayer2.drm.DrmInitData$SchemeData r0 = new com.google.android.exoplayer2.drm.DrmInitData$SchemeData
            java.lang.String r10 = "video/mp4"
            r0.<init>(r1, r5, r10, r4)
        L_0x0113:
            android.util.Pair r10 = android.util.Pair.create(r3, r0)
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.parseContentProtection(org.xmlpull.v1.XmlPullParser):android.util.Pair");
    }

    /* access modifiers changed from: protected */
    public void parseAdaptationSetChild(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        maybeSkipTag(xmlPullParser);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r16v8, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v5, resolved type: com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentTemplate} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r17v6, resolved type: com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0185 A[LOOP:0: B:1:0x005a->B:44:0x0185, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0149 A[EDGE_INSN: B:45:0x0149->B:38:0x0149 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.RepresentationInfo parseRepresentation(org.xmlpull.v1.XmlPullParser r24, java.lang.String r25, java.lang.String r26, java.lang.String r27, int r28, int r29, float r30, int r31, int r32, java.lang.String r33, java.util.List<com.google.android.exoplayer2.source.dash.manifest.Descriptor> r34, java.util.List<com.google.android.exoplayer2.source.dash.manifest.Descriptor> r35, java.util.List<com.google.android.exoplayer2.source.dash.manifest.Descriptor> r36, com.google.android.exoplayer2.source.dash.manifest.SegmentBase r37, long r38) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            r23 = this;
            r14 = r23
            r0 = r24
            r1 = 0
            java.lang.String r2 = "id"
            java.lang.String r2 = r0.getAttributeValue(r1, r2)
            java.lang.String r3 = "bandwidth"
            r4 = -1
            int r8 = parseInt(r0, r3, r4)
            java.lang.String r3 = "mimeType"
            r4 = r26
            java.lang.String r3 = parseString(r0, r3, r4)
            java.lang.String r4 = "codecs"
            r5 = r27
            java.lang.String r12 = parseString(r0, r4, r5)
            java.lang.String r4 = "width"
            r5 = r28
            int r4 = parseInt(r0, r4, r5)
            java.lang.String r5 = "height"
            r6 = r29
            int r5 = parseInt(r0, r5, r6)
            r6 = r30
            float r6 = parseFrameRate(r0, r6)
            java.lang.String r7 = "audioSamplingRate"
            r9 = r32
            int r7 = parseInt(r0, r7, r9)
            java.util.ArrayList r15 = new java.util.ArrayList
            r15.<init>()
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            r9 = 0
            r9 = r31
            r10 = r37
            r16 = r1
            r17 = 0
            r1 = r25
        L_0x005a:
            r24.next()
            r31 = r9
            java.lang.String r9 = "BaseURL"
            boolean r9 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r9)
            if (r9 == 0) goto L_0x0081
            if (r17 != 0) goto L_0x007b
            java.lang.String r1 = r14.parseBaseUrl(r0, r1)
            r9 = 1
            r9 = r31
            r18 = r8
            r17 = r10
            r19 = r16
            r16 = r1
            r1 = 1
            goto L_0x0141
        L_0x007b:
            r32 = r1
            r18 = r8
            goto L_0x0137
        L_0x0081:
            java.lang.String r9 = "AudioChannelConfiguration"
            boolean r9 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r9)
            if (r9 == 0) goto L_0x0097
            int r9 = r23.parseAudioChannelConfiguration(r24)
            r18 = r8
        L_0x008f:
            r19 = r16
            r16 = r1
            r1 = r17
            goto L_0x013f
        L_0x0097:
            java.lang.String r9 = "SegmentBase"
            boolean r9 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r9)
            if (r9 == 0) goto L_0x00b3
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r10 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SingleSegmentBase) r10
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r9 = r14.parseSegmentBase(r0, r10)
            r18 = r8
            r19 = r16
            r16 = r1
            r1 = r17
            r17 = r9
            r9 = r31
            goto L_0x0141
        L_0x00b3:
            java.lang.String r9 = "SegmentList"
            boolean r9 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r9)
            if (r9 == 0) goto L_0x00c8
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentList r10 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentList) r10
            r18 = r8
            r8 = r38
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentList r10 = r14.parseSegmentList(r0, r10, r8)
            r9 = r31
            goto L_0x008f
        L_0x00c8:
            r32 = r1
            r18 = r8
            r8 = r38
            java.lang.String r1 = "SegmentTemplate"
            boolean r1 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r1)
            if (r1 == 0) goto L_0x00f4
            r1 = r10
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentTemplate r1 = (com.google.android.exoplayer2.source.dash.manifest.SegmentBase.SegmentTemplate) r1
            r25 = r23
            r26 = r24
            r27 = r1
            r28 = r36
            r29 = r38
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SegmentTemplate r1 = r25.parseSegmentTemplate(r26, r27, r28, r29)
            r9 = r31
            r19 = r16
            r16 = r32
            r22 = r17
            r17 = r1
            r1 = r22
            goto L_0x0141
        L_0x00f4:
            java.lang.String r1 = "ContentProtection"
            boolean r1 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r1)
            if (r1 == 0) goto L_0x0114
            android.util.Pair r1 = r23.parseContentProtection(r24)
            java.lang.Object r8 = r1.first
            if (r8 == 0) goto L_0x010a
            java.lang.Object r8 = r1.first
            r16 = r8
            java.lang.String r16 = (java.lang.String) r16
        L_0x010a:
            java.lang.Object r8 = r1.second
            if (r8 == 0) goto L_0x0137
            java.lang.Object r1 = r1.second
            r15.add(r1)
            goto L_0x0137
        L_0x0114:
            java.lang.String r1 = "InbandEventStream"
            boolean r8 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r1)
            if (r8 == 0) goto L_0x0124
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r1 = parseDescriptor(r0, r1)
            r13.add(r1)
            goto L_0x0137
        L_0x0124:
            java.lang.String r1 = "SupplementalProperty"
            boolean r8 = com.google.android.exoplayer2.util.XmlPullParserUtil.isStartTag(r0, r1)
            if (r8 == 0) goto L_0x0134
            com.google.android.exoplayer2.source.dash.manifest.Descriptor r1 = parseDescriptor(r0, r1)
            r11.add(r1)
            goto L_0x0137
        L_0x0134:
            maybeSkipTag(r24)
        L_0x0137:
            r9 = r31
            r19 = r16
            r1 = r17
            r16 = r32
        L_0x013f:
            r17 = r10
        L_0x0141:
            java.lang.String r8 = "Representation"
            boolean r8 = com.google.android.exoplayer2.util.XmlPullParserUtil.isEndTag(r0, r8)
            if (r8 == 0) goto L_0x0185
            r0 = r23
            r1 = r2
            r2 = r3
            r3 = r4
            r4 = r5
            r5 = r6
            r6 = r9
            r8 = r18
            r9 = r33
            r10 = r34
            r20 = r11
            r11 = r35
            r21 = r13
            r13 = r20
            com.google.android.exoplayer2.Format r0 = r0.buildFormat(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
            if (r17 == 0) goto L_0x0168
            r1 = r17
            goto L_0x016d
        L_0x0168:
            com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase r1 = new com.google.android.exoplayer2.source.dash.manifest.SegmentBase$SingleSegmentBase
            r1.<init>()
        L_0x016d:
            com.google.android.exoplayer2.source.dash.manifest.DashManifestParser$RepresentationInfo r2 = new com.google.android.exoplayer2.source.dash.manifest.DashManifestParser$RepresentationInfo
            r3 = -1
            r24 = r2
            r25 = r0
            r26 = r16
            r27 = r1
            r28 = r19
            r29 = r15
            r30 = r21
            r31 = r3
            r24.<init>(r25, r26, r27, r28, r29, r30, r31)
            return r2
        L_0x0185:
            r10 = r17
            r8 = r18
            r17 = r1
            r1 = r16
            r16 = r19
            goto L_0x005a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.parseRepresentation(org.xmlpull.v1.XmlPullParser, java.lang.String, java.lang.String, java.lang.String, int, int, float, int, int, java.lang.String, java.util.List, java.util.List, java.util.List, com.google.android.exoplayer2.source.dash.manifest.SegmentBase, long):com.google.android.exoplayer2.source.dash.manifest.DashManifestParser$RepresentationInfo");
    }

    /* access modifiers changed from: protected */
    public Format buildFormat(String str, String str2, int i, int i2, float f, int i3, int i4, int i5, String str3, List<Descriptor> list, List<Descriptor> list2, String str4, List<Descriptor> list3) {
        String str5;
        int i6;
        int parseCea708AccessibilityChannel;
        List<Descriptor> list4 = list;
        String sampleMimeType = getSampleMimeType(str2, str4);
        int parseSelectionFlagsFromRoleDescriptors = parseSelectionFlagsFromRoleDescriptors(list4);
        int parseRoleFlagsFromRoleDescriptors = parseRoleFlagsFromRoleDescriptors(list4) | parseRoleFlagsFromAccessibilityDescriptors(list2);
        if (sampleMimeType != null) {
            String parseEac3SupplementalProperties = "audio/eac3".equals(sampleMimeType) ? parseEac3SupplementalProperties(list3) : sampleMimeType;
            if (MimeTypes.isVideo(parseEac3SupplementalProperties)) {
                return Format.createVideoContainerFormat(str, (String) null, str2, parseEac3SupplementalProperties, str4, (Metadata) null, i5, i, i2, f, (List<byte[]>) null, parseSelectionFlagsFromRoleDescriptors, parseRoleFlagsFromRoleDescriptors);
            }
            if (MimeTypes.isAudio(parseEac3SupplementalProperties)) {
                return Format.createAudioContainerFormat(str, (String) null, str2, parseEac3SupplementalProperties, str4, (Metadata) null, i5, i3, i4, (List<byte[]>) null, parseSelectionFlagsFromRoleDescriptors, parseRoleFlagsFromRoleDescriptors, str3);
            }
            if (mimeTypeIsRawText(parseEac3SupplementalProperties)) {
                if ("application/cea-608".equals(parseEac3SupplementalProperties)) {
                    parseCea708AccessibilityChannel = parseCea608AccessibilityChannel(list2);
                } else if ("application/cea-708".equals(parseEac3SupplementalProperties)) {
                    parseCea708AccessibilityChannel = parseCea708AccessibilityChannel(list2);
                } else {
                    i6 = -1;
                    return Format.createTextContainerFormat(str, (String) null, str2, parseEac3SupplementalProperties, str4, i5, parseSelectionFlagsFromRoleDescriptors, parseRoleFlagsFromRoleDescriptors, str3, i6);
                }
                i6 = parseCea708AccessibilityChannel;
                return Format.createTextContainerFormat(str, (String) null, str2, parseEac3SupplementalProperties, str4, i5, parseSelectionFlagsFromRoleDescriptors, parseRoleFlagsFromRoleDescriptors, str3, i6);
            }
            str5 = parseEac3SupplementalProperties;
        } else {
            str5 = sampleMimeType;
        }
        return Format.createContainerFormat(str, (String) null, str2, str5, str4, i5, parseSelectionFlagsFromRoleDescriptors, parseRoleFlagsFromRoleDescriptors, str3);
    }

    /* access modifiers changed from: protected */
    public Representation buildRepresentation(RepresentationInfo representationInfo, String str, String str2, ArrayList<DrmInitData.SchemeData> arrayList, ArrayList<Descriptor> arrayList2) {
        Format format = representationInfo.format;
        if (str != null) {
            format = format.copyWithLabel(str);
        }
        if (representationInfo.drmSchemeType != null) {
            str2 = representationInfo.drmSchemeType;
        }
        ArrayList<DrmInitData.SchemeData> arrayList3 = representationInfo.drmSchemeDatas;
        arrayList3.addAll(arrayList);
        if (!arrayList3.isEmpty()) {
            filterRedundantIncompleteSchemeDatas(arrayList3);
            format = format.copyWithDrmInitData(new DrmInitData(str2, (List<DrmInitData.SchemeData>) arrayList3));
        }
        ArrayList<Descriptor> arrayList4 = representationInfo.inbandEventStreams;
        arrayList4.addAll(arrayList2);
        return Representation.newInstance(representationInfo.revisionId, format, representationInfo.baseUrl, representationInfo.segmentBase, arrayList4);
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SingleSegmentBase parseSegmentBase(XmlPullParser xmlPullParser, SegmentBase.SingleSegmentBase singleSegmentBase) throws XmlPullParserException, IOException {
        long j;
        long j2;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        SegmentBase.SingleSegmentBase singleSegmentBase2 = singleSegmentBase;
        long parseLong = parseLong(xmlPullParser2, "timescale", singleSegmentBase2 != null ? singleSegmentBase2.timescale : 1);
        long j3 = 0;
        long parseLong2 = parseLong(xmlPullParser2, "presentationTimeOffset", singleSegmentBase2 != null ? singleSegmentBase2.presentationTimeOffset : 0);
        long j4 = singleSegmentBase2 != null ? singleSegmentBase2.indexStart : 0;
        if (singleSegmentBase2 != null) {
            j3 = singleSegmentBase2.indexLength;
        }
        RangedUri rangedUri = null;
        String attributeValue = xmlPullParser2.getAttributeValue((String) null, "indexRange");
        if (attributeValue != null) {
            String[] split = attributeValue.split("-");
            long parseLong3 = Long.parseLong(split[0]);
            j = (Long.parseLong(split[1]) - parseLong3) + 1;
            j2 = parseLong3;
        } else {
            j = j3;
            j2 = j4;
        }
        if (singleSegmentBase2 != null) {
            rangedUri = singleSegmentBase2.initialization;
        }
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser2, "Initialization")) {
                rangedUri = parseInitialization(xmlPullParser);
            } else {
                maybeSkipTag(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser2, "SegmentBase"));
        return buildSingleSegmentBase(rangedUri, parseLong, parseLong2, j2, j);
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SingleSegmentBase buildSingleSegmentBase(RangedUri rangedUri, long j, long j2, long j3, long j4) {
        return new SegmentBase.SingleSegmentBase(rangedUri, j, j2, j3, j4);
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SegmentList parseSegmentList(XmlPullParser xmlPullParser, SegmentBase.SegmentList segmentList, long j) throws XmlPullParserException, IOException {
        XmlPullParser xmlPullParser2 = xmlPullParser;
        SegmentBase.SegmentList segmentList2 = segmentList;
        long j2 = 1;
        long parseLong = parseLong(xmlPullParser2, "timescale", segmentList2 != null ? segmentList2.timescale : 1);
        long parseLong2 = parseLong(xmlPullParser2, "presentationTimeOffset", segmentList2 != null ? segmentList2.presentationTimeOffset : 0);
        long parseLong3 = parseLong(xmlPullParser2, VastIconXmlManager.DURATION, segmentList2 != null ? segmentList2.duration : -9223372036854775807L);
        if (segmentList2 != null) {
            j2 = segmentList2.startNumber;
        }
        long parseLong4 = parseLong(xmlPullParser2, "startNumber", j2);
        List<SegmentBase.SegmentTimelineElement> list = null;
        List list2 = null;
        RangedUri rangedUri = null;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser2, "Initialization")) {
                rangedUri = parseInitialization(xmlPullParser);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentTimeline")) {
                list = parseSegmentTimeline(xmlPullParser, parseLong, j);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentURL")) {
                if (list2 == null) {
                    list2 = new ArrayList();
                }
                list2.add(parseSegmentUrl(xmlPullParser));
            } else {
                maybeSkipTag(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser2, "SegmentList"));
        if (segmentList2 != null) {
            if (rangedUri == null) {
                rangedUri = segmentList2.initialization;
            }
            if (list == null) {
                list = segmentList2.segmentTimeline;
            }
            if (list2 == null) {
                list2 = segmentList2.mediaSegments;
            }
        }
        return buildSegmentList(rangedUri, parseLong, parseLong2, parseLong4, parseLong3, list, list2);
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SegmentList buildSegmentList(RangedUri rangedUri, long j, long j2, long j3, long j4, List<SegmentBase.SegmentTimelineElement> list, List<RangedUri> list2) {
        return new SegmentBase.SegmentList(rangedUri, j, j2, j3, j4, list, list2);
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SegmentTemplate parseSegmentTemplate(XmlPullParser xmlPullParser, SegmentBase.SegmentTemplate segmentTemplate, List<Descriptor> list, long j) throws XmlPullParserException, IOException {
        XmlPullParser xmlPullParser2 = xmlPullParser;
        SegmentBase.SegmentTemplate segmentTemplate2 = segmentTemplate;
        long j2 = 1;
        long parseLong = parseLong(xmlPullParser2, "timescale", segmentTemplate2 != null ? segmentTemplate2.timescale : 1);
        long parseLong2 = parseLong(xmlPullParser2, "presentationTimeOffset", segmentTemplate2 != null ? segmentTemplate2.presentationTimeOffset : 0);
        long parseLong3 = parseLong(xmlPullParser2, VastIconXmlManager.DURATION, segmentTemplate2 != null ? segmentTemplate2.duration : -9223372036854775807L);
        if (segmentTemplate2 != null) {
            j2 = segmentTemplate2.startNumber;
        }
        long parseLong4 = parseLong(xmlPullParser2, "startNumber", j2);
        long parseLastSegmentNumberSupplementalProperty = parseLastSegmentNumberSupplementalProperty(list);
        List<SegmentBase.SegmentTimelineElement> list2 = null;
        UrlTemplate parseUrlTemplate = parseUrlTemplate(xmlPullParser2, "media", segmentTemplate2 != null ? segmentTemplate2.mediaTemplate : null);
        UrlTemplate parseUrlTemplate2 = parseUrlTemplate(xmlPullParser2, "initialization", segmentTemplate2 != null ? segmentTemplate2.initializationTemplate : null);
        RangedUri rangedUri = null;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser2, "Initialization")) {
                rangedUri = parseInitialization(xmlPullParser);
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser2, "SegmentTimeline")) {
                list2 = parseSegmentTimeline(xmlPullParser, parseLong, j);
            } else {
                maybeSkipTag(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser2, "SegmentTemplate"));
        if (segmentTemplate2 != null) {
            if (rangedUri == null) {
                rangedUri = segmentTemplate2.initialization;
            }
            if (list2 == null) {
                list2 = segmentTemplate2.segmentTimeline;
            }
        }
        return buildSegmentTemplate(rangedUri, parseLong, parseLong2, parseLong4, parseLastSegmentNumberSupplementalProperty, parseLong3, list2, parseUrlTemplate2, parseUrlTemplate);
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SegmentTemplate buildSegmentTemplate(RangedUri rangedUri, long j, long j2, long j3, long j4, long j5, List<SegmentBase.SegmentTimelineElement> list, UrlTemplate urlTemplate, UrlTemplate urlTemplate2) {
        return new SegmentBase.SegmentTemplate(rangedUri, j, j2, j3, j4, j5, list, urlTemplate, urlTemplate2);
    }

    /* access modifiers changed from: protected */
    public EventStream parseEventStream(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String parseString = parseString(xmlPullParser, "schemeIdUri", "");
        String parseString2 = parseString(xmlPullParser, "value", "");
        long parseLong = parseLong(xmlPullParser, "timescale", 1);
        ArrayList arrayList = new ArrayList();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "Event")) {
                arrayList.add(parseEvent(xmlPullParser, parseString, parseString2, parseLong, byteArrayOutputStream));
            } else {
                maybeSkipTag(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "EventStream"));
        long[] jArr = new long[arrayList.size()];
        EventMessage[] eventMessageArr = new EventMessage[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            Pair pair = (Pair) arrayList.get(i);
            jArr[i] = ((Long) pair.first).longValue();
            eventMessageArr[i] = (EventMessage) pair.second;
        }
        return buildEventStream(parseString, parseString2, parseLong, jArr, eventMessageArr);
    }

    /* access modifiers changed from: protected */
    public EventStream buildEventStream(String str, String str2, long j, long[] jArr, EventMessage[] eventMessageArr) {
        return new EventStream(str, str2, j, jArr, eventMessageArr);
    }

    /* access modifiers changed from: protected */
    public Pair<Long, EventMessage> parseEvent(XmlPullParser xmlPullParser, String str, String str2, long j, ByteArrayOutputStream byteArrayOutputStream) throws IOException, XmlPullParserException {
        XmlPullParser xmlPullParser2 = xmlPullParser;
        long parseLong = parseLong(xmlPullParser2, "id", 0);
        long parseLong2 = parseLong(xmlPullParser2, VastIconXmlManager.DURATION, -9223372036854775807L);
        long parseLong3 = parseLong(xmlPullParser2, "presentationTime", 0);
        long scaleLargeTimestamp = Util.scaleLargeTimestamp(parseLong2, 1000, j);
        long scaleLargeTimestamp2 = Util.scaleLargeTimestamp(parseLong3, 1000000, j);
        String parseString = parseString(xmlPullParser2, "messageData", (String) null);
        byte[] parseEventObject = parseEventObject(xmlPullParser2, byteArrayOutputStream);
        Long valueOf = Long.valueOf(scaleLargeTimestamp2);
        if (parseString != null) {
            parseEventObject = Util.getUtf8Bytes(parseString);
        }
        return Pair.create(valueOf, buildEvent(str, str2, parseLong, scaleLargeTimestamp, parseEventObject));
    }

    /* access modifiers changed from: protected */
    public byte[] parseEventObject(XmlPullParser xmlPullParser, ByteArrayOutputStream byteArrayOutputStream) throws XmlPullParserException, IOException {
        byteArrayOutputStream.reset();
        XmlSerializer newSerializer = Xml.newSerializer();
        newSerializer.setOutput(byteArrayOutputStream, "UTF-8");
        xmlPullParser.nextToken();
        while (!XmlPullParserUtil.isEndTag(xmlPullParser, "Event")) {
            switch (xmlPullParser.getEventType()) {
                case 0:
                    newSerializer.startDocument((String) null, false);
                    break;
                case 1:
                    newSerializer.endDocument();
                    break;
                case 2:
                    newSerializer.startTag(xmlPullParser.getNamespace(), xmlPullParser.getName());
                    for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                        newSerializer.attribute(xmlPullParser.getAttributeNamespace(i), xmlPullParser.getAttributeName(i), xmlPullParser.getAttributeValue(i));
                    }
                    break;
                case 3:
                    newSerializer.endTag(xmlPullParser.getNamespace(), xmlPullParser.getName());
                    break;
                case 4:
                    newSerializer.text(xmlPullParser.getText());
                    break;
                case 5:
                    newSerializer.cdsect(xmlPullParser.getText());
                    break;
                case 6:
                    newSerializer.entityRef(xmlPullParser.getText());
                    break;
                case 7:
                    newSerializer.ignorableWhitespace(xmlPullParser.getText());
                    break;
                case 8:
                    newSerializer.processingInstruction(xmlPullParser.getText());
                    break;
                case 9:
                    newSerializer.comment(xmlPullParser.getText());
                    break;
                case 10:
                    newSerializer.docdecl(xmlPullParser.getText());
                    break;
            }
            xmlPullParser.nextToken();
        }
        newSerializer.flush();
        return byteArrayOutputStream.toByteArray();
    }

    /* access modifiers changed from: protected */
    public EventMessage buildEvent(String str, String str2, long j, long j2, byte[] bArr) {
        return new EventMessage(str, str2, j2, j, bArr);
    }

    /* access modifiers changed from: protected */
    public List<SegmentBase.SegmentTimelineElement> parseSegmentTimeline(XmlPullParser xmlPullParser, long j, long j2) throws XmlPullParserException, IOException {
        XmlPullParser xmlPullParser2 = xmlPullParser;
        ArrayList arrayList = new ArrayList();
        long j3 = 0;
        long j4 = -9223372036854775807L;
        boolean z = false;
        int i = 0;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser2, "S")) {
                long parseLong = parseLong(xmlPullParser2, C4892d.COLUMN_TYPE, -9223372036854775807L);
                if (z) {
                    j3 = addSegmentTimelineElementsToList(arrayList, j3, j4, i, parseLong);
                }
                if (parseLong == -9223372036854775807L) {
                    parseLong = j3;
                }
                j4 = parseLong(xmlPullParser2, "d", -9223372036854775807L);
                i = parseInt(xmlPullParser2, "r", 0);
                j3 = parseLong;
                z = true;
            } else {
                maybeSkipTag(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser2, "SegmentTimeline"));
        if (z) {
            addSegmentTimelineElementsToList(arrayList, j3, j4, i, Util.scaleLargeTimestamp(j2, j, 1000));
        }
        return arrayList;
    }

    private long addSegmentTimelineElementsToList(List<SegmentBase.SegmentTimelineElement> list, long j, long j2, int i, long j3) {
        int ceilDivide = i >= 0 ? i + 1 : (int) Util.ceilDivide(j3 - j, j2);
        for (int i2 = 0; i2 < ceilDivide; i2++) {
            list.add(buildSegmentTimelineElement(j, j2));
            j += j2;
        }
        return j;
    }

    /* access modifiers changed from: protected */
    public SegmentBase.SegmentTimelineElement buildSegmentTimelineElement(long j, long j2) {
        return new SegmentBase.SegmentTimelineElement(j, j2);
    }

    /* access modifiers changed from: protected */
    public UrlTemplate parseUrlTemplate(XmlPullParser xmlPullParser, String str, UrlTemplate urlTemplate) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        return attributeValue != null ? UrlTemplate.compile(attributeValue) : urlTemplate;
    }

    /* access modifiers changed from: protected */
    public RangedUri parseInitialization(XmlPullParser xmlPullParser) {
        return parseRangedUrl(xmlPullParser, "sourceURL", "range");
    }

    /* access modifiers changed from: protected */
    public RangedUri parseSegmentUrl(XmlPullParser xmlPullParser) {
        return parseRangedUrl(xmlPullParser, "media", "mediaRange");
    }

    /* access modifiers changed from: protected */
    public RangedUri parseRangedUrl(XmlPullParser xmlPullParser, String str, String str2) {
        long j;
        long j2;
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        String attributeValue2 = xmlPullParser.getAttributeValue((String) null, str2);
        if (attributeValue2 != null) {
            String[] split = attributeValue2.split("-");
            j2 = Long.parseLong(split[0]);
            if (split.length == 2) {
                j = (Long.parseLong(split[1]) - j2) + 1;
                return buildRangedUri(attributeValue, j2, j);
            }
        } else {
            j2 = 0;
        }
        j = -1;
        return buildRangedUri(attributeValue, j2, j);
    }

    /* access modifiers changed from: protected */
    public RangedUri buildRangedUri(String str, long j, long j2) {
        return new RangedUri(str, j, j2);
    }

    /* access modifiers changed from: protected */
    public ProgramInformation parseProgramInformation(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        String str = null;
        String parseString = parseString(xmlPullParser, "moreInformationURL", (String) null);
        String parseString2 = parseString(xmlPullParser, "lang", (String) null);
        String str2 = null;
        String str3 = null;
        while (true) {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "Title")) {
                str = xmlPullParser.nextText();
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "Source")) {
                str2 = xmlPullParser.nextText();
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "Copyright")) {
                str3 = xmlPullParser.nextText();
            } else {
                maybeSkipTag(xmlPullParser);
            }
            String str4 = str3;
            if (XmlPullParserUtil.isEndTag(xmlPullParser, "ProgramInformation")) {
                return new ProgramInformation(str, str2, str4, parseString, parseString2);
            }
            str3 = str4;
        }
    }

    /* access modifiers changed from: protected */
    public String parseLabel(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return parseText(xmlPullParser, "Label");
    }

    /* access modifiers changed from: protected */
    public String parseBaseUrl(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        return UriUtil.resolve(str, parseText(xmlPullParser, "BaseURL"));
    }

    /* access modifiers changed from: protected */
    public int parseAudioChannelConfiguration(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        String parseString = parseString(xmlPullParser, "schemeIdUri", (String) null);
        int i = -1;
        if ("urn:mpeg:dash:23003:3:audio_channel_configuration:2011".equals(parseString)) {
            i = parseInt(xmlPullParser, "value", -1);
        } else if ("tag:dolby.com,2014:dash:audio_channel_configuration:2011".equals(parseString) || "urn:dolby:dash:audio_channel_configuration:2011".equals(parseString)) {
            i = parseDolbyChannelConfiguration(xmlPullParser);
        }
        do {
            xmlPullParser.next();
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "AudioChannelConfiguration"));
        return i;
    }

    /* access modifiers changed from: protected */
    public int parseSelectionFlagsFromRoleDescriptors(List<Descriptor> list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = list.get(i);
            if ("urn:mpeg:dash:role:2011".equalsIgnoreCase(descriptor.schemeIdUri) && "main".equals(descriptor.value)) {
                return 1;
            }
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    public int parseRoleFlagsFromRoleDescriptors(List<Descriptor> list) {
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            Descriptor descriptor = list.get(i2);
            if ("urn:mpeg:dash:role:2011".equalsIgnoreCase(descriptor.schemeIdUri)) {
                i |= parseDashRoleSchemeValue(descriptor.value);
            }
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public int parseRoleFlagsFromAccessibilityDescriptors(List<Descriptor> list) {
        int parseTvaAudioPurposeCsValue;
        int i = 0;
        for (int i2 = 0; i2 < list.size(); i2++) {
            Descriptor descriptor = list.get(i2);
            if ("urn:mpeg:dash:role:2011".equalsIgnoreCase(descriptor.schemeIdUri)) {
                parseTvaAudioPurposeCsValue = parseDashRoleSchemeValue(descriptor.value);
            } else if ("urn:tva:metadata:cs:AudioPurposeCS:2007".equalsIgnoreCase(descriptor.schemeIdUri)) {
                parseTvaAudioPurposeCsValue = parseTvaAudioPurposeCsValue(descriptor.value);
            }
            i |= parseTvaAudioPurposeCsValue;
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public int parseDashRoleSchemeValue(String str) {
        if (str == null) {
            return 0;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -2060497896:
                if (str.equals("subtitle")) {
                    c = 7;
                    break;
                }
                break;
            case -1724546052:
                if (str.equals(PlusShare.KEY_CONTENT_DEEP_LINK_METADATA_DESCRIPTION)) {
                    c = 9;
                    break;
                }
                break;
            case -1580883024:
                if (str.equals("enhanced-audio-intelligibility")) {
                    c = 10;
                    break;
                }
                break;
            case -1408024454:
                if (str.equals("alternate")) {
                    c = 1;
                    break;
                }
                break;
            case 99825:
                if (str.equals("dub")) {
                    c = 4;
                    break;
                }
                break;
            case 3343801:
                if (str.equals("main")) {
                    c = 0;
                    break;
                }
                break;
            case 3530173:
                if (str.equals("sign")) {
                    c = 8;
                    break;
                }
                break;
            case 552573414:
                if (str.equals("caption")) {
                    c = 6;
                    break;
                }
                break;
            case 899152809:
                if (str.equals("commentary")) {
                    c = 3;
                    break;
                }
                break;
            case 1629013393:
                if (str.equals("emergency")) {
                    c = 5;
                    break;
                }
                break;
            case 1855372047:
                if (str.equals("supplementary")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 4;
            case 3:
                return 8;
            case 4:
                return 16;
            case 5:
                return 32;
            case 6:
                return 64;
            case 7:
                return 128;
            case 8:
                return 256;
            case 9:
                return 512;
            case 10:
                return 2048;
            default:
                return 0;
        }
    }

    /* access modifiers changed from: protected */
    public int parseTvaAudioPurposeCsValue(String str) {
        if (str == null) {
            return 0;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case 49:
                if (str.equals("1")) {
                    c = 0;
                    break;
                }
                break;
            case 50:
                if (str.equals(InternalAvidAdSessionContext.AVID_API_LEVEL)) {
                    c = 1;
                    break;
                }
                break;
            case 51:
                if (str.equals("3")) {
                    c = 2;
                    break;
                }
                break;
            case 52:
                if (str.equals("4")) {
                    c = 3;
                    break;
                }
                break;
            case 54:
                if (str.equals("6")) {
                    c = 4;
                    break;
                }
                break;
        }
        if (c == 0) {
            return 512;
        }
        if (c == 1) {
            return 2048;
        }
        if (c == 2) {
            return 4;
        }
        if (c != 3) {
            return c != 4 ? 0 : 1;
        }
        return 8;
    }

    public static void maybeSkipTag(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        if (XmlPullParserUtil.isStartTag(xmlPullParser)) {
            int i = 1;
            while (i != 0) {
                xmlPullParser.next();
                if (XmlPullParserUtil.isStartTag(xmlPullParser)) {
                    i++;
                } else if (XmlPullParserUtil.isEndTag(xmlPullParser)) {
                    i--;
                }
            }
        }
    }

    private static void filterRedundantIncompleteSchemeDatas(ArrayList<DrmInitData.SchemeData> arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            DrmInitData.SchemeData schemeData = arrayList.get(size);
            if (!schemeData.hasData()) {
                int i = 0;
                while (true) {
                    if (i >= arrayList.size()) {
                        break;
                    } else if (arrayList.get(i).canReplace(schemeData)) {
                        arrayList.remove(size);
                        break;
                    } else {
                        i++;
                    }
                }
            }
        }
    }

    private static String getSampleMimeType(String str, String str2) {
        if (MimeTypes.isAudio(str)) {
            return MimeTypes.getAudioMediaMimeType(str2);
        }
        if (MimeTypes.isVideo(str)) {
            return MimeTypes.getVideoMediaMimeType(str2);
        }
        if (mimeTypeIsRawText(str)) {
            return str;
        }
        if ("application/mp4".equals(str)) {
            if (str2 != null) {
                if (str2.startsWith("stpp")) {
                    return "application/ttml+xml";
                }
                if (str2.startsWith("wvtt")) {
                    return "application/x-mp4-vtt";
                }
            }
        } else if ("application/x-rawcc".equals(str) && str2 != null) {
            if (str2.contains("cea708")) {
                return "application/cea-708";
            }
            if (str2.contains("eia608") || str2.contains("cea608")) {
                return "application/cea-608";
            }
        }
        return null;
    }

    private static boolean mimeTypeIsRawText(String str) {
        return MimeTypes.isText(str) || "application/ttml+xml".equals(str) || "application/x-mp4-vtt".equals(str) || "application/cea-708".equals(str) || "application/cea-608".equals(str);
    }

    private static String checkLanguageConsistency(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 == null) {
            return str;
        }
        Assertions.checkState(str.equals(str2));
        return str;
    }

    private static int checkContentTypeConsistency(int i, int i2) {
        if (i == -1) {
            return i2;
        }
        if (i2 == -1) {
            return i;
        }
        Assertions.checkState(i == i2);
        return i;
    }

    protected static Descriptor parseDescriptor(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        String parseString = parseString(xmlPullParser, "schemeIdUri", "");
        String parseString2 = parseString(xmlPullParser, "value", (String) null);
        String parseString3 = parseString(xmlPullParser, "id", (String) null);
        do {
            xmlPullParser.next();
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, str));
        return new Descriptor(parseString, parseString2, parseString3);
    }

    protected static int parseCea608AccessibilityChannel(List<Descriptor> list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = list.get(i);
            if ("urn:scte:dash:cc:cea-608:2015".equals(descriptor.schemeIdUri) && descriptor.value != null) {
                Matcher matcher = CEA_608_ACCESSIBILITY_PATTERN.matcher(descriptor.value);
                if (matcher.matches()) {
                    return Integer.parseInt(matcher.group(1));
                }
                Log.m9w(TAG, "Unable to parse CEA-608 channel number from: " + descriptor.value);
            }
        }
        return -1;
    }

    protected static int parseCea708AccessibilityChannel(List<Descriptor> list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = list.get(i);
            if ("urn:scte:dash:cc:cea-708:2015".equals(descriptor.schemeIdUri) && descriptor.value != null) {
                Matcher matcher = CEA_708_ACCESSIBILITY_PATTERN.matcher(descriptor.value);
                if (matcher.matches()) {
                    return Integer.parseInt(matcher.group(1));
                }
                Log.m9w(TAG, "Unable to parse CEA-708 service block number from: " + descriptor.value);
            }
        }
        return -1;
    }

    protected static String parseEac3SupplementalProperties(List<Descriptor> list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = list.get(i);
            String str = descriptor.schemeIdUri;
            if ("tag:dolby.com,2018:dash:EC3_ExtensionType:2018".equals(str) && "JOC".equals(descriptor.value)) {
                return "audio/eac3-joc";
            }
            if ("tag:dolby.com,2014:dash:DolbyDigitalPlusExtensionType:2014".equals(str) && "ec+3".equals(descriptor.value)) {
                return "audio/eac3-joc";
            }
        }
        return "audio/eac3";
    }

    protected static float parseFrameRate(XmlPullParser xmlPullParser, float f) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, "frameRate");
        if (attributeValue == null) {
            return f;
        }
        Matcher matcher = FRAME_RATE_PATTERN.matcher(attributeValue);
        if (!matcher.matches()) {
            return f;
        }
        int parseInt = Integer.parseInt(matcher.group(1));
        String group = matcher.group(2);
        return !TextUtils.isEmpty(group) ? ((float) parseInt) / ((float) Integer.parseInt(group)) : (float) parseInt;
    }

    protected static long parseDuration(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        if (attributeValue == null) {
            return j;
        }
        return Util.parseXsDuration(attributeValue);
    }

    protected static long parseDateTime(XmlPullParser xmlPullParser, String str, long j) throws ParserException {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        if (attributeValue == null) {
            return j;
        }
        return Util.parseXsDateTime(attributeValue);
    }

    protected static String parseText(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        String str2 = "";
        do {
            xmlPullParser.next();
            if (xmlPullParser.getEventType() == 4) {
                str2 = xmlPullParser.getText();
            } else {
                maybeSkipTag(xmlPullParser);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, str));
        return str2;
    }

    protected static int parseInt(XmlPullParser xmlPullParser, String str, int i) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        return attributeValue == null ? i : Integer.parseInt(attributeValue);
    }

    protected static long parseLong(XmlPullParser xmlPullParser, String str, long j) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        return attributeValue == null ? j : Long.parseLong(attributeValue);
    }

    protected static String parseString(XmlPullParser xmlPullParser, String str, String str2) {
        String attributeValue = xmlPullParser.getAttributeValue((String) null, str);
        return attributeValue == null ? str2 : attributeValue;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static int parseDolbyChannelConfiguration(org.xmlpull.v1.XmlPullParser r5) {
        /*
            r0 = 0
            java.lang.String r1 = "value"
            java.lang.String r5 = r5.getAttributeValue(r0, r1)
            java.lang.String r5 = com.google.android.exoplayer2.util.Util.toLowerInvariant(r5)
            r0 = -1
            if (r5 != 0) goto L_0x000f
            return r0
        L_0x000f:
            int r1 = r5.hashCode()
            r2 = 3
            r3 = 2
            r4 = 1
            switch(r1) {
                case 1596796: goto L_0x0038;
                case 2937391: goto L_0x002e;
                case 3094035: goto L_0x0024;
                case 3133436: goto L_0x001a;
                default: goto L_0x0019;
            }
        L_0x0019:
            goto L_0x0042
        L_0x001a:
            java.lang.String r1 = "fa01"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x0042
            r5 = 3
            goto L_0x0043
        L_0x0024:
            java.lang.String r1 = "f801"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x0042
            r5 = 2
            goto L_0x0043
        L_0x002e:
            java.lang.String r1 = "a000"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x0042
            r5 = 1
            goto L_0x0043
        L_0x0038:
            java.lang.String r1 = "4000"
            boolean r5 = r5.equals(r1)
            if (r5 == 0) goto L_0x0042
            r5 = 0
            goto L_0x0043
        L_0x0042:
            r5 = -1
        L_0x0043:
            if (r5 == 0) goto L_0x0052
            if (r5 == r4) goto L_0x0051
            if (r5 == r3) goto L_0x004f
            if (r5 == r2) goto L_0x004c
            return r0
        L_0x004c:
            r5 = 8
            return r5
        L_0x004f:
            r5 = 6
            return r5
        L_0x0051:
            return r3
        L_0x0052:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.manifest.DashManifestParser.parseDolbyChannelConfiguration(org.xmlpull.v1.XmlPullParser):int");
    }

    protected static long parseLastSegmentNumberSupplementalProperty(List<Descriptor> list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = list.get(i);
            if ("http://dashif.org/guidelines/last-segment-number".equalsIgnoreCase(descriptor.schemeIdUri)) {
                return Long.parseLong(descriptor.value);
            }
        }
        return -1;
    }

    protected static final class RepresentationInfo {
        public final String baseUrl;
        public final ArrayList<DrmInitData.SchemeData> drmSchemeDatas;
        public final String drmSchemeType;
        public final Format format;
        public final ArrayList<Descriptor> inbandEventStreams;
        public final long revisionId;
        public final SegmentBase segmentBase;

        public RepresentationInfo(Format format2, String str, SegmentBase segmentBase2, String str2, ArrayList<DrmInitData.SchemeData> arrayList, ArrayList<Descriptor> arrayList2, long j) {
            this.format = format2;
            this.baseUrl = str;
            this.segmentBase = segmentBase2;
            this.drmSchemeType = str2;
            this.drmSchemeDatas = arrayList;
            this.inbandEventStreams = arrayList2;
            this.revisionId = j;
        }
    }
}

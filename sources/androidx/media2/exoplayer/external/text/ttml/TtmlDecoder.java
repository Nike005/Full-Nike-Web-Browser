package androidx.media2.exoplayer.external.text.ttml;

import androidx.media2.exoplayer.external.text.SimpleSubtitleDecoder;
import androidx.media2.exoplayer.external.text.SubtitleDecoderException;
import androidx.media2.exoplayer.external.util.Log;
import androidx.media2.exoplayer.external.util.Util;
import androidx.media2.exoplayer.external.util.XmlPullParserUtil;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public final class TtmlDecoder extends SimpleSubtitleDecoder {
    private static final String ATTR_BEGIN = "begin";
    private static final String ATTR_DURATION = "dur";
    private static final String ATTR_END = "end";
    private static final String ATTR_IMAGE = "backgroundImage";
    private static final String ATTR_REGION = "region";
    private static final String ATTR_STYLE = "style";
    private static final Pattern CELL_RESOLUTION = Pattern.compile("^(\\d+) (\\d+)$");
    private static final Pattern CLOCK_TIME = Pattern.compile("^([0-9][0-9]+):([0-9][0-9]):([0-9][0-9])(?:(\\.[0-9]+)|:([0-9][0-9])(?:\\.([0-9]+))?)?$");
    private static final CellResolution DEFAULT_CELL_RESOLUTION = new CellResolution(32, 15);
    private static final FrameAndTickRate DEFAULT_FRAME_AND_TICK_RATE = new FrameAndTickRate(30.0f, 1, 1);
    private static final int DEFAULT_FRAME_RATE = 30;
    private static final Pattern FONT_SIZE = Pattern.compile("^(([0-9]*.)?[0-9]+)(px|em|%)$");
    private static final Pattern OFFSET_TIME = Pattern.compile("^([0-9]+(?:\\.[0-9]+)?)(h|m|s|ms|f|t)$");
    private static final Pattern PERCENTAGE_COORDINATES = Pattern.compile("^(\\d+\\.?\\d*?)% (\\d+\\.?\\d*?)%$");
    private static final Pattern PIXEL_COORDINATES = Pattern.compile("^(\\d+\\.?\\d*?)px (\\d+\\.?\\d*?)px$");
    private static final String TAG = "TtmlDecoder";
    private static final String TTP = "http://www.w3.org/ns/ttml#parameter";
    private final XmlPullParserFactory xmlParserFactory;

    public TtmlDecoder() {
        super(TAG);
        try {
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            this.xmlParserFactory = newInstance;
            newInstance.setNamespaceAware(true);
        } catch (XmlPullParserException e) {
            throw new RuntimeException("Couldn't create XmlPullParserFactory instance", e);
        }
    }

    /* access modifiers changed from: protected */
    public TtmlSubtitle decode(byte[] bArr, int i, boolean z) throws SubtitleDecoderException {
        TtmlSubtitle ttmlSubtitle;
        FrameAndTickRate frameAndTickRate;
        try {
            XmlPullParser newPullParser = this.xmlParserFactory.newPullParser();
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            HashMap hashMap3 = new HashMap();
            TtsExtent ttsExtent = null;
            hashMap2.put("", new TtmlRegion((String) null));
            newPullParser.setInput(new ByteArrayInputStream(bArr, 0, i), (String) null);
            ArrayDeque arrayDeque = new ArrayDeque();
            FrameAndTickRate frameAndTickRate2 = DEFAULT_FRAME_AND_TICK_RATE;
            CellResolution cellResolution = DEFAULT_CELL_RESOLUTION;
            TtmlSubtitle ttmlSubtitle2 = null;
            int i2 = 0;
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.getEventType()) {
                TtmlNode ttmlNode = (TtmlNode) arrayDeque.peek();
                if (i2 == 0) {
                    String name = newPullParser.getName();
                    if (eventType == 2) {
                        if ("tt".equals(name)) {
                            frameAndTickRate2 = parseFrameAndTickRates(newPullParser);
                            cellResolution = parseCellResolution(newPullParser, DEFAULT_CELL_RESOLUTION);
                            ttsExtent = parseTtsExtent(newPullParser);
                        }
                        TtsExtent ttsExtent2 = ttsExtent;
                        FrameAndTickRate frameAndTickRate3 = frameAndTickRate2;
                        CellResolution cellResolution2 = cellResolution;
                        if (!isSupportedTag(name)) {
                            String valueOf = String.valueOf(newPullParser.getName());
                            Log.m6208i(TAG, valueOf.length() != 0 ? "Ignoring unsupported tag: ".concat(valueOf) : new String("Ignoring unsupported tag: "));
                            i2++;
                            frameAndTickRate2 = frameAndTickRate3;
                            ttsExtent = ttsExtent2;
                            cellResolution = cellResolution2;
                            newPullParser.next();
                        } else {
                            if ("head".equals(name)) {
                                ttmlSubtitle = ttmlSubtitle2;
                                frameAndTickRate = frameAndTickRate3;
                                parseHeader(newPullParser, hashMap, cellResolution2, ttsExtent2, hashMap2, hashMap3);
                            } else {
                                ttmlSubtitle = ttmlSubtitle2;
                                frameAndTickRate = frameAndTickRate3;
                                try {
                                    TtmlNode parseNode = parseNode(newPullParser, ttmlNode, hashMap2, frameAndTickRate);
                                    arrayDeque.push(parseNode);
                                    if (ttmlNode != null) {
                                        ttmlNode.addChild(parseNode);
                                    }
                                } catch (SubtitleDecoderException e) {
                                    Log.m6211w(TAG, "Suppressing parser error", e);
                                    i2++;
                                }
                            }
                            frameAndTickRate2 = frameAndTickRate;
                            ttsExtent = ttsExtent2;
                            cellResolution = cellResolution2;
                        }
                    } else {
                        ttmlSubtitle = ttmlSubtitle2;
                        if (eventType == 4) {
                            ttmlNode.addChild(TtmlNode.buildTextNode(newPullParser.getText()));
                        } else if (eventType == 3) {
                            ttmlSubtitle2 = newPullParser.getName().equals("tt") ? new TtmlSubtitle((TtmlNode) arrayDeque.peek(), hashMap, hashMap2, hashMap3) : ttmlSubtitle;
                            arrayDeque.pop();
                            newPullParser.next();
                        }
                    }
                } else {
                    ttmlSubtitle = ttmlSubtitle2;
                    if (eventType == 2) {
                        i2++;
                    } else if (eventType == 3) {
                        i2--;
                    }
                }
                ttmlSubtitle2 = ttmlSubtitle;
                newPullParser.next();
            }
            return ttmlSubtitle2;
        } catch (XmlPullParserException e2) {
            throw new SubtitleDecoderException("Unable to decode source", e2);
        } catch (IOException e3) {
            throw new IllegalStateException("Unexpected error when reading input.", e3);
        }
    }

    private FrameAndTickRate parseFrameAndTickRates(XmlPullParser xmlPullParser) throws SubtitleDecoderException {
        String attributeValue = xmlPullParser.getAttributeValue(TTP, "frameRate");
        int parseInt = attributeValue != null ? Integer.parseInt(attributeValue) : 30;
        float f = 1.0f;
        String attributeValue2 = xmlPullParser.getAttributeValue(TTP, "frameRateMultiplier");
        if (attributeValue2 != null) {
            String[] split = Util.split(attributeValue2, StringUtils.SPACE);
            if (split.length == 2) {
                f = ((float) Integer.parseInt(split[0])) / ((float) Integer.parseInt(split[1]));
            } else {
                throw new SubtitleDecoderException("frameRateMultiplier doesn't have 2 parts");
            }
        }
        int i = DEFAULT_FRAME_AND_TICK_RATE.subFrameRate;
        String attributeValue3 = xmlPullParser.getAttributeValue(TTP, "subFrameRate");
        if (attributeValue3 != null) {
            i = Integer.parseInt(attributeValue3);
        }
        int i2 = DEFAULT_FRAME_AND_TICK_RATE.tickRate;
        String attributeValue4 = xmlPullParser.getAttributeValue(TTP, "tickRate");
        if (attributeValue4 != null) {
            i2 = Integer.parseInt(attributeValue4);
        }
        return new FrameAndTickRate(((float) parseInt) * f, i, i2);
    }

    private CellResolution parseCellResolution(XmlPullParser xmlPullParser, CellResolution cellResolution) throws SubtitleDecoderException {
        String attributeValue = xmlPullParser.getAttributeValue(TTP, "cellResolution");
        if (attributeValue == null) {
            return cellResolution;
        }
        Matcher matcher = CELL_RESOLUTION.matcher(attributeValue);
        if (!matcher.matches()) {
            String valueOf = String.valueOf(attributeValue);
            Log.m6210w(TAG, valueOf.length() != 0 ? "Ignoring malformed cell resolution: ".concat(valueOf) : new String("Ignoring malformed cell resolution: "));
            return cellResolution;
        }
        try {
            int parseInt = Integer.parseInt(matcher.group(1));
            int parseInt2 = Integer.parseInt(matcher.group(2));
            if (parseInt != 0 && parseInt2 != 0) {
                return new CellResolution(parseInt, parseInt2);
            }
            StringBuilder sb = new StringBuilder(47);
            sb.append("Invalid cell resolution ");
            sb.append(parseInt);
            sb.append(StringUtils.SPACE);
            sb.append(parseInt2);
            throw new SubtitleDecoderException(sb.toString());
        } catch (NumberFormatException unused) {
            String valueOf2 = String.valueOf(attributeValue);
            Log.m6210w(TAG, valueOf2.length() != 0 ? "Ignoring malformed cell resolution: ".concat(valueOf2) : new String("Ignoring malformed cell resolution: "));
            return cellResolution;
        }
    }

    private TtsExtent parseTtsExtent(XmlPullParser xmlPullParser) {
        String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, "extent");
        if (attributeValue == null) {
            return null;
        }
        Matcher matcher = PIXEL_COORDINATES.matcher(attributeValue);
        if (!matcher.matches()) {
            String valueOf = String.valueOf(attributeValue);
            Log.m6210w(TAG, valueOf.length() != 0 ? "Ignoring non-pixel tts extent: ".concat(valueOf) : new String("Ignoring non-pixel tts extent: "));
            return null;
        }
        try {
            return new TtsExtent(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)));
        } catch (NumberFormatException unused) {
            String valueOf2 = String.valueOf(attributeValue);
            Log.m6210w(TAG, valueOf2.length() != 0 ? "Ignoring malformed tts extent: ".concat(valueOf2) : new String("Ignoring malformed tts extent: "));
            return null;
        }
    }

    private Map<String, TtmlStyle> parseHeader(XmlPullParser xmlPullParser, Map<String, TtmlStyle> map, CellResolution cellResolution, TtsExtent ttsExtent, Map<String, TtmlRegion> map2, Map<String, String> map3) throws IOException, XmlPullParserException {
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "style")) {
                String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, "style");
                TtmlStyle parseStyleAttributes = parseStyleAttributes(xmlPullParser, new TtmlStyle());
                if (attributeValue != null) {
                    for (String str : parseStyleIds(attributeValue)) {
                        parseStyleAttributes.chain(map.get(str));
                    }
                }
                if (parseStyleAttributes.getId() != null) {
                    map.put(parseStyleAttributes.getId(), parseStyleAttributes);
                }
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "region")) {
                TtmlRegion parseRegionAttributes = parseRegionAttributes(xmlPullParser, cellResolution, ttsExtent);
                if (parseRegionAttributes != null) {
                    map2.put(parseRegionAttributes.f4102id, parseRegionAttributes);
                }
            } else if (XmlPullParserUtil.isStartTag(xmlPullParser, "metadata")) {
                parseMetadata(xmlPullParser, map3);
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "head"));
        return map;
    }

    private void parseMetadata(XmlPullParser xmlPullParser, Map<String, String> map) throws IOException, XmlPullParserException {
        String attributeValue;
        do {
            xmlPullParser.next();
            if (XmlPullParserUtil.isStartTag(xmlPullParser, "image") && (attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser, "id")) != null) {
                map.put(attributeValue, xmlPullParser.nextText());
            }
        } while (!XmlPullParserUtil.isEndTag(xmlPullParser, "metadata"));
    }

    private TtmlRegion parseRegionAttributes(XmlPullParser xmlPullParser, CellResolution cellResolution, TtsExtent ttsExtent) {
        float f;
        float f2;
        float f3;
        float f4;
        int i;
        XmlPullParser xmlPullParser2 = xmlPullParser;
        TtsExtent ttsExtent2 = ttsExtent;
        String attributeValue = XmlPullParserUtil.getAttributeValue(xmlPullParser2, "id");
        if (attributeValue == null) {
            return null;
        }
        String attributeValue2 = XmlPullParserUtil.getAttributeValue(xmlPullParser2, "origin");
        if (attributeValue2 != null) {
            Matcher matcher = PERCENTAGE_COORDINATES.matcher(attributeValue2);
            Matcher matcher2 = PIXEL_COORDINATES.matcher(attributeValue2);
            if (matcher.matches()) {
                try {
                    float parseFloat = Float.parseFloat(matcher.group(1)) / 100.0f;
                    f = Float.parseFloat(matcher.group(2)) / 100.0f;
                    f2 = parseFloat;
                } catch (NumberFormatException unused) {
                    String valueOf = String.valueOf(attributeValue2);
                    Log.m6210w(TAG, valueOf.length() != 0 ? "Ignoring region with malformed origin: ".concat(valueOf) : new String("Ignoring region with malformed origin: "));
                    return null;
                }
            } else if (!matcher2.matches()) {
                String valueOf2 = String.valueOf(attributeValue2);
                Log.m6210w(TAG, valueOf2.length() != 0 ? "Ignoring region with unsupported origin: ".concat(valueOf2) : new String("Ignoring region with unsupported origin: "));
                return null;
            } else if (ttsExtent2 == null) {
                String valueOf3 = String.valueOf(attributeValue2);
                Log.m6210w(TAG, valueOf3.length() != 0 ? "Ignoring region with missing tts:extent: ".concat(valueOf3) : new String("Ignoring region with missing tts:extent: "));
                return null;
            } else {
                try {
                    int parseInt = Integer.parseInt(matcher2.group(1));
                    int parseInt2 = Integer.parseInt(matcher2.group(2));
                    f2 = ((float) parseInt) / ((float) ttsExtent2.width);
                    f = ((float) parseInt2) / ((float) ttsExtent2.height);
                } catch (NumberFormatException unused2) {
                    String valueOf4 = String.valueOf(attributeValue2);
                    Log.m6210w(TAG, valueOf4.length() != 0 ? "Ignoring region with malformed origin: ".concat(valueOf4) : new String("Ignoring region with malformed origin: "));
                    return null;
                }
            }
            String attributeValue3 = XmlPullParserUtil.getAttributeValue(xmlPullParser2, "extent");
            if (attributeValue3 != null) {
                Matcher matcher3 = PERCENTAGE_COORDINATES.matcher(attributeValue3);
                Matcher matcher4 = PIXEL_COORDINATES.matcher(attributeValue3);
                if (matcher3.matches()) {
                    try {
                        float parseFloat2 = Float.parseFloat(matcher3.group(1)) / 100.0f;
                        f4 = Float.parseFloat(matcher3.group(2)) / 100.0f;
                        f3 = parseFloat2;
                    } catch (NumberFormatException unused3) {
                        String valueOf5 = String.valueOf(attributeValue2);
                        Log.m6210w(TAG, valueOf5.length() != 0 ? "Ignoring region with malformed extent: ".concat(valueOf5) : new String("Ignoring region with malformed extent: "));
                        return null;
                    }
                } else if (!matcher4.matches()) {
                    String valueOf6 = String.valueOf(attributeValue2);
                    Log.m6210w(TAG, valueOf6.length() != 0 ? "Ignoring region with unsupported extent: ".concat(valueOf6) : new String("Ignoring region with unsupported extent: "));
                    return null;
                } else if (ttsExtent2 == null) {
                    String valueOf7 = String.valueOf(attributeValue2);
                    Log.m6210w(TAG, valueOf7.length() != 0 ? "Ignoring region with missing tts:extent: ".concat(valueOf7) : new String("Ignoring region with missing tts:extent: "));
                    return null;
                } else {
                    try {
                        int parseInt3 = Integer.parseInt(matcher4.group(1));
                        int parseInt4 = Integer.parseInt(matcher4.group(2));
                        f3 = ((float) parseInt3) / ((float) ttsExtent2.width);
                        f4 = ((float) parseInt4) / ((float) ttsExtent2.height);
                    } catch (NumberFormatException unused4) {
                        String valueOf8 = String.valueOf(attributeValue2);
                        Log.m6210w(TAG, valueOf8.length() != 0 ? "Ignoring region with malformed extent: ".concat(valueOf8) : new String("Ignoring region with malformed extent: "));
                        return null;
                    }
                }
                String attributeValue4 = XmlPullParserUtil.getAttributeValue(xmlPullParser2, "displayAlign");
                if (attributeValue4 != null) {
                    String lowerInvariant = Util.toLowerInvariant(attributeValue4);
                    char c = 65535;
                    int hashCode = lowerInvariant.hashCode();
                    if (hashCode != -1364013995) {
                        if (hashCode == 92734940 && lowerInvariant.equals("after")) {
                            c = 1;
                        }
                    } else if (lowerInvariant.equals("center")) {
                        c = 0;
                    }
                    if (c == 0) {
                        f += f4 / 2.0f;
                        i = 1;
                    } else if (c == 1) {
                        f += f4;
                        i = 2;
                    }
                    return new TtmlRegion(attributeValue, f2, f, 0, i, f3, 1, 1.0f / ((float) cellResolution.rows));
                }
                i = 0;
                return new TtmlRegion(attributeValue, f2, f, 0, i, f3, 1, 1.0f / ((float) cellResolution.rows));
            }
            Log.m6210w(TAG, "Ignoring region without an extent");
            return null;
        }
        Log.m6210w(TAG, "Ignoring region without an origin");
        return null;
    }

    private String[] parseStyleIds(String str) {
        String trim = str.trim();
        return trim.isEmpty() ? new String[0] : Util.split(trim, "\\s+");
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private androidx.media2.exoplayer.external.text.ttml.TtmlStyle parseStyleAttributes(org.xmlpull.v1.XmlPullParser r12, androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13) {
        /*
            r11 = this;
            int r0 = r12.getAttributeCount()
            r1 = 0
            r2 = 0
        L_0x0006:
            if (r2 >= r0) goto L_0x021d
            java.lang.String r3 = r12.getAttributeValue(r2)
            java.lang.String r4 = r12.getAttributeName(r2)
            int r5 = r4.hashCode()
            r6 = 4
            r7 = -1
            r8 = 2
            r9 = 3
            r10 = 1
            switch(r5) {
                case -1550943582: goto L_0x006e;
                case -1224696685: goto L_0x0064;
                case -1065511464: goto L_0x005a;
                case -879295043: goto L_0x004f;
                case -734428249: goto L_0x0045;
                case 3355: goto L_0x003b;
                case 94842723: goto L_0x0031;
                case 365601008: goto L_0x0027;
                case 1287124693: goto L_0x001d;
                default: goto L_0x001c;
            }
        L_0x001c:
            goto L_0x0078
        L_0x001d:
            java.lang.String r5 = "backgroundColor"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0078
            r4 = 1
            goto L_0x0079
        L_0x0027:
            java.lang.String r5 = "fontSize"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0078
            r4 = 4
            goto L_0x0079
        L_0x0031:
            java.lang.String r5 = "color"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0078
            r4 = 2
            goto L_0x0079
        L_0x003b:
            java.lang.String r5 = "id"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0078
            r4 = 0
            goto L_0x0079
        L_0x0045:
            java.lang.String r5 = "fontWeight"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0078
            r4 = 5
            goto L_0x0079
        L_0x004f:
            java.lang.String r5 = "textDecoration"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0078
            r4 = 8
            goto L_0x0079
        L_0x005a:
            java.lang.String r5 = "textAlign"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0078
            r4 = 7
            goto L_0x0079
        L_0x0064:
            java.lang.String r5 = "fontFamily"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0078
            r4 = 3
            goto L_0x0079
        L_0x006e:
            java.lang.String r5 = "fontStyle"
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x0078
            r4 = 6
            goto L_0x0079
        L_0x0078:
            r4 = -1
        L_0x0079:
            java.lang.String r5 = "TtmlDecoder"
            switch(r4) {
                case 0: goto L_0x0205;
                case 1: goto L_0x01de;
                case 2: goto L_0x01b7;
                case 3: goto L_0x01ae;
                case 4: goto L_0x018a;
                case 5: goto L_0x017a;
                case 6: goto L_0x016a;
                case 7: goto L_0x00e5;
                case 8: goto L_0x0080;
                default: goto L_0x007e;
            }
        L_0x007e:
            goto L_0x0219
        L_0x0080:
            java.lang.String r3 = androidx.media2.exoplayer.external.util.Util.toLowerInvariant(r3)
            int r4 = r3.hashCode()
            switch(r4) {
                case -1461280213: goto L_0x00aa;
                case -1026963764: goto L_0x00a0;
                case 913457136: goto L_0x0096;
                case 1679736913: goto L_0x008c;
                default: goto L_0x008b;
            }
        L_0x008b:
            goto L_0x00b3
        L_0x008c:
            java.lang.String r4 = "linethrough"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x00b3
            r7 = 0
            goto L_0x00b3
        L_0x0096:
            java.lang.String r4 = "nolinethrough"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x00b3
            r7 = 1
            goto L_0x00b3
        L_0x00a0:
            java.lang.String r4 = "underline"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x00b3
            r7 = 2
            goto L_0x00b3
        L_0x00aa:
            java.lang.String r4 = "nounderline"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x00b3
            r7 = 3
        L_0x00b3:
            if (r7 == 0) goto L_0x00db
            if (r7 == r10) goto L_0x00d1
            if (r7 == r8) goto L_0x00c7
            if (r7 == r9) goto L_0x00bd
            goto L_0x0219
        L_0x00bd:
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r13.setUnderline(r1)
            goto L_0x0219
        L_0x00c7:
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r13.setUnderline(r10)
            goto L_0x0219
        L_0x00d1:
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r13.setLinethrough(r1)
            goto L_0x0219
        L_0x00db:
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r13.setLinethrough(r10)
            goto L_0x0219
        L_0x00e5:
            java.lang.String r3 = androidx.media2.exoplayer.external.util.Util.toLowerInvariant(r3)
            int r4 = r3.hashCode()
            switch(r4) {
                case -1364013995: goto L_0x0119;
                case 100571: goto L_0x010f;
                case 3317767: goto L_0x0105;
                case 108511772: goto L_0x00fb;
                case 109757538: goto L_0x00f1;
                default: goto L_0x00f0;
            }
        L_0x00f0:
            goto L_0x0122
        L_0x00f1:
            java.lang.String r4 = "start"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0122
            r7 = 1
            goto L_0x0122
        L_0x00fb:
            java.lang.String r4 = "right"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0122
            r7 = 2
            goto L_0x0122
        L_0x0105:
            java.lang.String r4 = "left"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0122
            r7 = 0
            goto L_0x0122
        L_0x010f:
            java.lang.String r4 = "end"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0122
            r7 = 3
            goto L_0x0122
        L_0x0119:
            java.lang.String r4 = "center"
            boolean r3 = r3.equals(r4)
            if (r3 == 0) goto L_0x0122
            r7 = 4
        L_0x0122:
            if (r7 == 0) goto L_0x015e
            if (r7 == r10) goto L_0x0152
            if (r7 == r8) goto L_0x0146
            if (r7 == r9) goto L_0x013a
            if (r7 == r6) goto L_0x012e
            goto L_0x0219
        L_0x012e:
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_CENTER
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r13.setTextAlign(r3)
            goto L_0x0219
        L_0x013a:
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_OPPOSITE
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r13.setTextAlign(r3)
            goto L_0x0219
        L_0x0146:
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_OPPOSITE
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r13.setTextAlign(r3)
            goto L_0x0219
        L_0x0152:
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_NORMAL
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r13.setTextAlign(r3)
            goto L_0x0219
        L_0x015e:
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            android.text.Layout$Alignment r3 = android.text.Layout.Alignment.ALIGN_NORMAL
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r13.setTextAlign(r3)
            goto L_0x0219
        L_0x016a:
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            java.lang.String r4 = "italic"
            boolean r3 = r4.equalsIgnoreCase(r3)
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r13.setItalic(r3)
            goto L_0x0219
        L_0x017a:
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            java.lang.String r4 = "bold"
            boolean r3 = r4.equalsIgnoreCase(r3)
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r13.setBold(r3)
            goto L_0x0219
        L_0x018a:
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)     // Catch:{ SubtitleDecoderException -> 0x0193 }
            parseFontSize(r3, r13)     // Catch:{ SubtitleDecoderException -> 0x0193 }
            goto L_0x0219
        L_0x0193:
            java.lang.String r4 = "Failed parsing fontSize value: "
            java.lang.String r3 = java.lang.String.valueOf(r3)
            int r6 = r3.length()
            if (r6 == 0) goto L_0x01a5
            java.lang.String r3 = r4.concat(r3)
            goto L_0x01aa
        L_0x01a5:
            java.lang.String r3 = new java.lang.String
            r3.<init>(r4)
        L_0x01aa:
            androidx.media2.exoplayer.external.util.Log.m6210w(r5, r3)
            goto L_0x0219
        L_0x01ae:
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r13.setFontFamily(r3)
            goto L_0x0219
        L_0x01b7:
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            int r4 = androidx.media2.exoplayer.external.util.ColorParser.parseTtmlColor(r3)     // Catch:{ IllegalArgumentException -> 0x01c3 }
            r13.setFontColor(r4)     // Catch:{ IllegalArgumentException -> 0x01c3 }
            goto L_0x0219
        L_0x01c3:
            java.lang.String r4 = "Failed parsing color value: "
            java.lang.String r3 = java.lang.String.valueOf(r3)
            int r6 = r3.length()
            if (r6 == 0) goto L_0x01d5
            java.lang.String r3 = r4.concat(r3)
            goto L_0x01da
        L_0x01d5:
            java.lang.String r3 = new java.lang.String
            r3.<init>(r4)
        L_0x01da:
            androidx.media2.exoplayer.external.util.Log.m6210w(r5, r3)
            goto L_0x0219
        L_0x01de:
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            int r4 = androidx.media2.exoplayer.external.util.ColorParser.parseTtmlColor(r3)     // Catch:{ IllegalArgumentException -> 0x01ea }
            r13.setBackgroundColor(r4)     // Catch:{ IllegalArgumentException -> 0x01ea }
            goto L_0x0219
        L_0x01ea:
            java.lang.String r4 = "Failed parsing background value: "
            java.lang.String r3 = java.lang.String.valueOf(r3)
            int r6 = r3.length()
            if (r6 == 0) goto L_0x01fc
            java.lang.String r3 = r4.concat(r3)
            goto L_0x0201
        L_0x01fc:
            java.lang.String r3 = new java.lang.String
            r3.<init>(r4)
        L_0x0201:
            androidx.media2.exoplayer.external.util.Log.m6210w(r5, r3)
            goto L_0x0219
        L_0x0205:
            java.lang.String r4 = r12.getName()
            java.lang.String r5 = "style"
            boolean r4 = r5.equals(r4)
            if (r4 == 0) goto L_0x0219
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r11.createIfNull(r13)
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r13 = r13.setId(r3)
        L_0x0219:
            int r2 = r2 + 1
            goto L_0x0006
        L_0x021d:
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.text.ttml.TtmlDecoder.parseStyleAttributes(org.xmlpull.v1.XmlPullParser, androidx.media2.exoplayer.external.text.ttml.TtmlStyle):androidx.media2.exoplayer.external.text.ttml.TtmlStyle");
    }

    private TtmlStyle createIfNull(TtmlStyle ttmlStyle) {
        return ttmlStyle == null ? new TtmlStyle() : ttmlStyle;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private androidx.media2.exoplayer.external.text.ttml.TtmlNode parseNode(org.xmlpull.v1.XmlPullParser r21, androidx.media2.exoplayer.external.text.ttml.TtmlNode r22, java.util.Map<java.lang.String, androidx.media2.exoplayer.external.text.ttml.TtmlRegion> r23, androidx.media2.exoplayer.external.text.ttml.TtmlDecoder.FrameAndTickRate r24) throws androidx.media2.exoplayer.external.text.SubtitleDecoderException {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r2 = r22
            r3 = r24
            int r4 = r21.getAttributeCount()
            r5 = 0
            androidx.media2.exoplayer.external.text.ttml.TtmlStyle r11 = r0.parseStyleAttributes(r1, r5)
            java.lang.String r9 = ""
            r12 = r5
            r14 = r12
            r13 = r9
            r5 = 0
            r9 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r15 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r17 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x0026:
            if (r5 >= r4) goto L_0x00c5
            java.lang.String r6 = r1.getAttributeName(r5)
            java.lang.String r7 = r1.getAttributeValue(r5)
            int r19 = r6.hashCode()
            switch(r19) {
                case -934795532: goto L_0x006a;
                case 99841: goto L_0x0060;
                case 100571: goto L_0x0056;
                case 93616297: goto L_0x004c;
                case 109780401: goto L_0x0042;
                case 1292595405: goto L_0x0038;
                default: goto L_0x0037;
            }
        L_0x0037:
            goto L_0x0074
        L_0x0038:
            java.lang.String r8 = "backgroundImage"
            boolean r6 = r6.equals(r8)
            if (r6 == 0) goto L_0x0074
            r8 = 5
            goto L_0x0075
        L_0x0042:
            java.lang.String r8 = "style"
            boolean r6 = r6.equals(r8)
            if (r6 == 0) goto L_0x0074
            r8 = 3
            goto L_0x0075
        L_0x004c:
            java.lang.String r8 = "begin"
            boolean r6 = r6.equals(r8)
            if (r6 == 0) goto L_0x0074
            r8 = 0
            goto L_0x0075
        L_0x0056:
            java.lang.String r8 = "end"
            boolean r6 = r6.equals(r8)
            if (r6 == 0) goto L_0x0074
            r8 = 1
            goto L_0x0075
        L_0x0060:
            java.lang.String r8 = "dur"
            boolean r6 = r6.equals(r8)
            if (r6 == 0) goto L_0x0074
            r8 = 2
            goto L_0x0075
        L_0x006a:
            java.lang.String r8 = "region"
            boolean r6 = r6.equals(r8)
            if (r6 == 0) goto L_0x0074
            r8 = 4
            goto L_0x0075
        L_0x0074:
            r8 = -1
        L_0x0075:
            if (r8 == 0) goto L_0x00bb
            r6 = 1
            if (r8 == r6) goto L_0x00b4
            r6 = 2
            if (r8 == r6) goto L_0x00ad
            r6 = 3
            if (r8 == r6) goto L_0x00a2
            r6 = 4
            if (r8 == r6) goto L_0x0098
            r6 = 5
            if (r8 == r6) goto L_0x0087
            goto L_0x0095
        L_0x0087:
            java.lang.String r6 = "#"
            boolean r6 = r7.startsWith(r6)
            if (r6 == 0) goto L_0x0095
            r6 = 1
            java.lang.String r6 = r7.substring(r6)
            r14 = r6
        L_0x0095:
            r6 = r23
            goto L_0x00c1
        L_0x0098:
            r6 = r23
            boolean r8 = r6.containsKey(r7)
            if (r8 == 0) goto L_0x00c1
            r13 = r7
            goto L_0x00c1
        L_0x00a2:
            r6 = r23
            java.lang.String[] r7 = r0.parseStyleIds(r7)
            int r8 = r7.length
            if (r8 <= 0) goto L_0x00c1
            r12 = r7
            goto L_0x00c1
        L_0x00ad:
            r6 = r23
            long r17 = parseTimeExpression(r7, r3)
            goto L_0x00c1
        L_0x00b4:
            r6 = r23
            long r15 = parseTimeExpression(r7, r3)
            goto L_0x00c1
        L_0x00bb:
            r6 = r23
            long r9 = parseTimeExpression(r7, r3)
        L_0x00c1:
            int r5 = r5 + 1
            goto L_0x0026
        L_0x00c5:
            if (r2 == 0) goto L_0x00e1
            long r3 = r2.startTimeUs
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x00e6
            int r3 = (r9 > r5 ? 1 : (r9 == r5 ? 0 : -1))
            if (r3 == 0) goto L_0x00d9
            long r3 = r2.startTimeUs
            long r9 = r9 + r3
        L_0x00d9:
            int r3 = (r15 > r5 ? 1 : (r15 == r5 ? 0 : -1))
            if (r3 == 0) goto L_0x00e6
            long r3 = r2.startTimeUs
            long r15 = r15 + r3
            goto L_0x00e6
        L_0x00e1:
            r5 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
        L_0x00e6:
            r7 = r9
            int r3 = (r15 > r5 ? 1 : (r15 == r5 ? 0 : -1))
            if (r3 != 0) goto L_0x0100
            int r3 = (r17 > r5 ? 1 : (r17 == r5 ? 0 : -1))
            if (r3 == 0) goto L_0x00f4
            long r17 = r7 + r17
            r9 = r17
            goto L_0x0101
        L_0x00f4:
            if (r2 == 0) goto L_0x0100
            long r3 = r2.endTimeUs
            int r9 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r9 == 0) goto L_0x0100
            long r2 = r2.endTimeUs
            r9 = r2
            goto L_0x0101
        L_0x0100:
            r9 = r15
        L_0x0101:
            java.lang.String r6 = r21.getName()
            androidx.media2.exoplayer.external.text.ttml.TtmlNode r1 = androidx.media2.exoplayer.external.text.ttml.TtmlNode.buildNode(r6, r7, r9, r11, r12, r13, r14)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.text.ttml.TtmlDecoder.parseNode(org.xmlpull.v1.XmlPullParser, androidx.media2.exoplayer.external.text.ttml.TtmlNode, java.util.Map, androidx.media2.exoplayer.external.text.ttml.TtmlDecoder$FrameAndTickRate):androidx.media2.exoplayer.external.text.ttml.TtmlNode");
    }

    private static boolean isSupportedTag(String str) {
        return str.equals("tt") || str.equals("head") || str.equals("body") || str.equals("div") || str.equals("p") || str.equals("span") || str.equals("br") || str.equals("style") || str.equals("styling") || str.equals("layout") || str.equals("region") || str.equals("metadata") || str.equals("image") || str.equals("data") || str.equals("information");
    }

    private static void parseFontSize(String str, TtmlStyle ttmlStyle) throws SubtitleDecoderException {
        Matcher matcher;
        String[] split = Util.split(str, "\\s+");
        if (split.length == 1) {
            matcher = FONT_SIZE.matcher(str);
        } else if (split.length == 2) {
            matcher = FONT_SIZE.matcher(split[1]);
            Log.m6210w(TAG, "Multiple values in fontSize attribute. Picking the second value for vertical font size and ignoring the first.");
        } else {
            int length = split.length;
            StringBuilder sb = new StringBuilder(52);
            sb.append("Invalid number of entries for fontSize: ");
            sb.append(length);
            sb.append(".");
            throw new SubtitleDecoderException(sb.toString());
        }
        if (matcher.matches()) {
            String group = matcher.group(3);
            char c = 65535;
            int hashCode = group.hashCode();
            if (hashCode != 37) {
                if (hashCode != 3240) {
                    if (hashCode == 3592 && group.equals("px")) {
                        c = 0;
                    }
                } else if (group.equals("em")) {
                    c = 1;
                }
            } else if (group.equals("%")) {
                c = 2;
            }
            if (c == 0) {
                ttmlStyle.setFontSizeUnit(1);
            } else if (c == 1) {
                ttmlStyle.setFontSizeUnit(2);
            } else if (c == 2) {
                ttmlStyle.setFontSizeUnit(3);
            } else {
                StringBuilder sb2 = new StringBuilder(String.valueOf(group).length() + 30);
                sb2.append("Invalid unit for fontSize: '");
                sb2.append(group);
                sb2.append("'.");
                throw new SubtitleDecoderException(sb2.toString());
            }
            ttmlStyle.setFontSize(Float.valueOf(matcher.group(1)).floatValue());
            return;
        }
        StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 36);
        sb3.append("Invalid expression for fontSize: '");
        sb3.append(str);
        sb3.append("'.");
        throw new SubtitleDecoderException(sb3.toString());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x00ce, code lost:
        if (r14.equals("s") != false) goto L_0x00fa;
     */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x011d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static long parseTimeExpression(java.lang.String r14, androidx.media2.exoplayer.external.text.ttml.TtmlDecoder.FrameAndTickRate r15) throws androidx.media2.exoplayer.external.text.SubtitleDecoderException {
        /*
            java.util.regex.Pattern r0 = CLOCK_TIME
            java.util.regex.Matcher r0 = r0.matcher(r14)
            boolean r1 = r0.matches()
            r2 = 4696837146684686336(0x412e848000000000, double:1000000.0)
            r4 = 5
            r5 = 4
            r6 = 3
            r7 = 2
            r8 = 1
            if (r1 == 0) goto L_0x0088
            java.lang.String r14 = r0.group(r8)
            long r8 = java.lang.Long.parseLong(r14)
            r10 = 3600(0xe10, double:1.7786E-320)
            long r8 = r8 * r10
            double r8 = (double) r8
            java.lang.String r14 = r0.group(r7)
            long r10 = java.lang.Long.parseLong(r14)
            r12 = 60
            long r10 = r10 * r12
            double r10 = (double) r10
            java.lang.Double.isNaN(r8)
            java.lang.Double.isNaN(r10)
            double r8 = r8 + r10
            java.lang.String r14 = r0.group(r6)
            long r6 = java.lang.Long.parseLong(r14)
            double r6 = (double) r6
            java.lang.Double.isNaN(r6)
            double r8 = r8 + r6
            java.lang.String r14 = r0.group(r5)
            r5 = 0
            if (r14 == 0) goto L_0x0051
            double r10 = java.lang.Double.parseDouble(r14)
            goto L_0x0052
        L_0x0051:
            r10 = r5
        L_0x0052:
            double r8 = r8 + r10
            java.lang.String r14 = r0.group(r4)
            if (r14 == 0) goto L_0x0063
            long r10 = java.lang.Long.parseLong(r14)
            float r14 = (float) r10
            float r1 = r15.effectiveFrameRate
            float r14 = r14 / r1
            double r10 = (double) r14
            goto L_0x0064
        L_0x0063:
            r10 = r5
        L_0x0064:
            double r8 = r8 + r10
            r14 = 6
            java.lang.String r14 = r0.group(r14)
            if (r14 == 0) goto L_0x0083
            long r0 = java.lang.Long.parseLong(r14)
            double r0 = (double) r0
            int r14 = r15.subFrameRate
            double r4 = (double) r14
            java.lang.Double.isNaN(r0)
            java.lang.Double.isNaN(r4)
            double r0 = r0 / r4
            float r14 = r15.effectiveFrameRate
            double r14 = (double) r14
            java.lang.Double.isNaN(r14)
            double r5 = r0 / r14
        L_0x0083:
            double r8 = r8 + r5
            double r8 = r8 * r2
            long r14 = (long) r8
            return r14
        L_0x0088:
            java.util.regex.Pattern r0 = OFFSET_TIME
            java.util.regex.Matcher r0 = r0.matcher(r14)
            boolean r1 = r0.matches()
            if (r1 == 0) goto L_0x0128
            java.lang.String r14 = r0.group(r8)
            double r9 = java.lang.Double.parseDouble(r14)
            java.lang.String r14 = r0.group(r7)
            r0 = -1
            int r1 = r14.hashCode()
            r11 = 102(0x66, float:1.43E-43)
            if (r1 == r11) goto L_0x00ef
            r11 = 104(0x68, float:1.46E-43)
            if (r1 == r11) goto L_0x00e5
            r11 = 109(0x6d, float:1.53E-43)
            if (r1 == r11) goto L_0x00db
            r11 = 3494(0xda6, float:4.896E-42)
            if (r1 == r11) goto L_0x00d1
            r11 = 115(0x73, float:1.61E-43)
            if (r1 == r11) goto L_0x00c8
            r7 = 116(0x74, float:1.63E-43)
            if (r1 == r7) goto L_0x00be
            goto L_0x00f9
        L_0x00be:
            java.lang.String r1 = "t"
            boolean r14 = r14.equals(r1)
            if (r14 == 0) goto L_0x00f9
            r7 = 5
            goto L_0x00fa
        L_0x00c8:
            java.lang.String r1 = "s"
            boolean r14 = r14.equals(r1)
            if (r14 == 0) goto L_0x00f9
            goto L_0x00fa
        L_0x00d1:
            java.lang.String r1 = "ms"
            boolean r14 = r14.equals(r1)
            if (r14 == 0) goto L_0x00f9
            r7 = 3
            goto L_0x00fa
        L_0x00db:
            java.lang.String r1 = "m"
            boolean r14 = r14.equals(r1)
            if (r14 == 0) goto L_0x00f9
            r7 = 1
            goto L_0x00fa
        L_0x00e5:
            java.lang.String r1 = "h"
            boolean r14 = r14.equals(r1)
            if (r14 == 0) goto L_0x00f9
            r7 = 0
            goto L_0x00fa
        L_0x00ef:
            java.lang.String r1 = "f"
            boolean r14 = r14.equals(r1)
            if (r14 == 0) goto L_0x00f9
            r7 = 4
            goto L_0x00fa
        L_0x00f9:
            r7 = -1
        L_0x00fa:
            if (r7 == 0) goto L_0x011d
            if (r7 == r8) goto L_0x011a
            if (r7 == r6) goto L_0x0113
            if (r7 == r5) goto L_0x010c
            if (r7 == r4) goto L_0x0105
            goto L_0x0124
        L_0x0105:
            int r14 = r15.tickRate
            double r14 = (double) r14
            java.lang.Double.isNaN(r14)
            goto L_0x0118
        L_0x010c:
            float r14 = r15.effectiveFrameRate
            double r14 = (double) r14
            java.lang.Double.isNaN(r14)
            goto L_0x0118
        L_0x0113:
            r14 = 4652007308841189376(0x408f400000000000, double:1000.0)
        L_0x0118:
            double r9 = r9 / r14
            goto L_0x0124
        L_0x011a:
            r14 = 4633641066610819072(0x404e000000000000, double:60.0)
            goto L_0x0122
        L_0x011d:
            r14 = 4660134898793709568(0x40ac200000000000, double:3600.0)
        L_0x0122:
            double r9 = r9 * r14
        L_0x0124:
            double r9 = r9 * r2
            long r14 = (long) r9
            return r14
        L_0x0128:
            androidx.media2.exoplayer.external.text.SubtitleDecoderException r15 = new androidx.media2.exoplayer.external.text.SubtitleDecoderException
            java.lang.String r0 = "Malformed time expression: "
            java.lang.String r14 = java.lang.String.valueOf(r14)
            int r1 = r14.length()
            if (r1 == 0) goto L_0x013b
            java.lang.String r14 = r0.concat(r14)
            goto L_0x0140
        L_0x013b:
            java.lang.String r14 = new java.lang.String
            r14.<init>(r0)
        L_0x0140:
            r15.<init>((java.lang.String) r14)
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.media2.exoplayer.external.text.ttml.TtmlDecoder.parseTimeExpression(java.lang.String, androidx.media2.exoplayer.external.text.ttml.TtmlDecoder$FrameAndTickRate):long");
    }

    private static final class FrameAndTickRate {
        final float effectiveFrameRate;
        final int subFrameRate;
        final int tickRate;

        FrameAndTickRate(float f, int i, int i2) {
            this.effectiveFrameRate = f;
            this.subFrameRate = i;
            this.tickRate = i2;
        }
    }

    private static final class CellResolution {
        final int columns;
        final int rows;

        CellResolution(int i, int i2) {
            this.columns = i;
            this.rows = i2;
        }
    }

    private static final class TtsExtent {
        final int height;
        final int width;

        TtsExtent(int i, int i2) {
            this.width = i;
            this.height = i2;
        }
    }
}

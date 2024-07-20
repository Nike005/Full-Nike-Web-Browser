package org.htmlcleaner;

import com.facebook.ads.AdError;
import java.util.HashMap;
import java.util.Map;

public class SpecialEntities {
    public static final SpecialEntities INSTANCE = new SpecialEntities(true, true) {
        public void put(SpecialEntity specialEntity) {
            throw new UnsupportedOperationException("cannot add to this instance");
        }
    };
    public static final char NON_BREAKABLE_SPACE = ' ';
    private Map<String, SpecialEntity> entities = new HashMap();
    private Map<Integer, SpecialEntity> entitiesByUnicodeCharcode = new HashMap();
    private boolean greek;
    private boolean math;
    private int maxEntityLength;

    public SpecialEntities(boolean z, boolean z2) {
        this.greek = z;
        this.math = z2;
        _put(new SpecialEntity("nbsp", 160, (String) null, true));
        _put(new SpecialEntity("iexcl", 161, (String) null, true));
        _put(new SpecialEntity("cent", 162, (String) null, true));
        _put(new SpecialEntity("pound", 163, (String) null, true));
        _put(new SpecialEntity("curren", 164, (String) null, true));
        _put(new SpecialEntity("yen", 165, (String) null, true));
        _put(new SpecialEntity("brvbar", 166, (String) null, true));
        _put(new SpecialEntity("sect", 167, (String) null, true));
        _put(new SpecialEntity("uml", 168, (String) null, true));
        _put(new SpecialEntity("copy", 169, (String) null, true));
        _put(new SpecialEntity("ordf", 170, (String) null, true));
        _put(new SpecialEntity("laquo", 171, (String) null, true));
        _put(new SpecialEntity("not", 172, (String) null, true));
        _put(new SpecialEntity("shy", 173, (String) null, true));
        _put(new SpecialEntity("reg", 174, (String) null, true));
        _put(new SpecialEntity("macr", 175, (String) null, true));
        _put(new SpecialEntity("deg", 176, (String) null, true));
        _put(new SpecialEntity("plusmn", 177, (String) null, true));
        _put(new SpecialEntity("sup2", 178, (String) null, true));
        _put(new SpecialEntity("sup3", 179, (String) null, true));
        _put(new SpecialEntity("acute", 180, (String) null, true));
        _put(new SpecialEntity("micro", 181, (String) null, true));
        _put(new SpecialEntity("para", 182, (String) null, true));
        _put(new SpecialEntity("middot", 183, (String) null, true));
        _put(new SpecialEntity("cedil", 184, (String) null, true));
        _put(new SpecialEntity("sup1", 185, (String) null, true));
        _put(new SpecialEntity("ordm", 186, (String) null, true));
        _put(new SpecialEntity("raquo", 187, (String) null, true));
        _put(new SpecialEntity("frac14", 188, (String) null, true));
        _put(new SpecialEntity("frac12", 189, (String) null, true));
        _put(new SpecialEntity("frac34", 190, (String) null, true));
        _put(new SpecialEntity("iquest", 191, (String) null, true));
        _put(new SpecialEntity("Agrave", 192, (String) null, true));
        _put(new SpecialEntity("Aacute", 193, (String) null, true));
        _put(new SpecialEntity("Acirc", 194, (String) null, true));
        _put(new SpecialEntity("Atilde", 195, (String) null, true));
        _put(new SpecialEntity("Auml", 196, (String) null, true));
        _put(new SpecialEntity("Aring", 197, (String) null, true));
        _put(new SpecialEntity("AElig", 198, (String) null, true));
        _put(new SpecialEntity("Ccedil", 199, (String) null, true));
        _put(new SpecialEntity("Egrave", 200, (String) null, true));
        _put(new SpecialEntity("Eacute", 201, (String) null, true));
        _put(new SpecialEntity("Ecirc", 202, (String) null, true));
        _put(new SpecialEntity("Euml", 203, (String) null, true));
        _put(new SpecialEntity("Igrave", 204, (String) null, true));
        _put(new SpecialEntity("Iacute", 205, (String) null, true));
        _put(new SpecialEntity("Icirc", 206, (String) null, true));
        _put(new SpecialEntity("Iuml", 207, (String) null, true));
        _put(new SpecialEntity("ETH", 208, (String) null, true));
        _put(new SpecialEntity("Ntilde", 209, (String) null, true));
        _put(new SpecialEntity("Ograve", 210, (String) null, true));
        _put(new SpecialEntity("Oacute", 211, (String) null, true));
        _put(new SpecialEntity("Ocirc", 212, (String) null, true));
        _put(new SpecialEntity("Otilde", 213, (String) null, true));
        _put(new SpecialEntity("Ouml", 214, (String) null, true));
        _put(new SpecialEntity("times", 215, (String) null, true));
        _put(new SpecialEntity("Oslash", 216, (String) null, true));
        _put(new SpecialEntity("Ugrave", 217, (String) null, true));
        _put(new SpecialEntity("Uacute", 218, (String) null, true));
        _put(new SpecialEntity("Ucirc", 219, (String) null, true));
        _put(new SpecialEntity("Uuml", 220, (String) null, true));
        _put(new SpecialEntity("Yacute", 221, (String) null, true));
        _put(new SpecialEntity("THORN", 222, (String) null, true));
        _put(new SpecialEntity("szlig", 223, (String) null, true));
        _put(new SpecialEntity("agrave", 224, (String) null, true));
        _put(new SpecialEntity("aacute", 225, (String) null, true));
        _put(new SpecialEntity("acirc", 226, (String) null, true));
        _put(new SpecialEntity("atilde", 227, (String) null, true));
        _put(new SpecialEntity("auml", 228, (String) null, true));
        _put(new SpecialEntity("aring", 229, (String) null, true));
        _put(new SpecialEntity("aelig", 230, (String) null, true));
        _put(new SpecialEntity("ccedil", 231, (String) null, true));
        _put(new SpecialEntity("egrave", 232, (String) null, true));
        _put(new SpecialEntity("eacute", 233, (String) null, true));
        _put(new SpecialEntity("ecirc", 234, (String) null, true));
        _put(new SpecialEntity("euml", 235, (String) null, true));
        _put(new SpecialEntity("igrave", 236, (String) null, true));
        _put(new SpecialEntity("iacute", 237, (String) null, true));
        _put(new SpecialEntity("icirc", 238, (String) null, true));
        _put(new SpecialEntity("iuml", 239, (String) null, true));
        _put(new SpecialEntity("eth", 240, (String) null, true));
        _put(new SpecialEntity("ntilde", 241, (String) null, true));
        _put(new SpecialEntity("ograve", 242, (String) null, true));
        _put(new SpecialEntity("oacute", 243, (String) null, true));
        _put(new SpecialEntity("ocirc", 244, (String) null, true));
        _put(new SpecialEntity("otilde", 245, (String) null, true));
        _put(new SpecialEntity("ouml", 246, (String) null, true));
        _put(new SpecialEntity("divide", 247, (String) null, true));
        _put(new SpecialEntity("oslash", 248, (String) null, true));
        _put(new SpecialEntity("ugrave", 249, (String) null, true));
        _put(new SpecialEntity("uacute", 250, (String) null, true));
        _put(new SpecialEntity("ucirc", 251, (String) null, true));
        _put(new SpecialEntity("uuml", 252, (String) null, true));
        _put(new SpecialEntity("yacute", 253, (String) null, true));
        _put(new SpecialEntity("thorn", 254, (String) null, true));
        _put(new SpecialEntity("yuml", 255, (String) null, true));
        _put(new SpecialEntity("OElig", 338, (String) null, true));
        _put(new SpecialEntity("oelig", 339, (String) null, true));
        _put(new SpecialEntity("Scaron", 352, (String) null, true));
        _put(new SpecialEntity("scaron", 353, (String) null, true));
        _put(new SpecialEntity("Yuml", 376, (String) null, true));
        _put(new SpecialEntity("fnof", 402, (String) null, true));
        _put(new SpecialEntity("circ", 710, (String) null, true));
        _put(new SpecialEntity("tilde", 732, (String) null, true));
        if (this.greek) {
            _put(new SpecialEntity("Alpha", 913, (String) null, true));
            _put(new SpecialEntity("Beta", 914, (String) null, true));
            _put(new SpecialEntity("Gamma", 915, (String) null, true));
            _put(new SpecialEntity("Delta", 916, (String) null, true));
            _put(new SpecialEntity("Epsilon", 917, (String) null, true));
            _put(new SpecialEntity("Zeta", 918, (String) null, true));
            _put(new SpecialEntity("Eta", 919, (String) null, true));
            _put(new SpecialEntity("Theta", 920, (String) null, true));
            _put(new SpecialEntity("Iota", 921, (String) null, true));
            _put(new SpecialEntity("Kappa", 922, (String) null, true));
            _put(new SpecialEntity("Lambda", 923, (String) null, true));
            _put(new SpecialEntity("Mu", 924, (String) null, true));
            _put(new SpecialEntity("Nu", 925, (String) null, true));
            _put(new SpecialEntity("Xi", 926, (String) null, true));
            _put(new SpecialEntity("Omicron", 927, (String) null, true));
            _put(new SpecialEntity("Pi", 928, (String) null, true));
            _put(new SpecialEntity("Rho", 929, (String) null, true));
            _put(new SpecialEntity("Sigma", 931, (String) null, true));
            _put(new SpecialEntity("Tau", 932, (String) null, true));
            _put(new SpecialEntity("Upsilon", 933, (String) null, true));
            _put(new SpecialEntity("Phi", 934, (String) null, true));
            _put(new SpecialEntity("Chi", 935, (String) null, true));
            _put(new SpecialEntity("Psi", 936, (String) null, true));
            _put(new SpecialEntity("Omega", 937, (String) null, true));
            _put(new SpecialEntity("alpha", 945, (String) null, true));
            _put(new SpecialEntity("beta", 946, (String) null, true));
            _put(new SpecialEntity("gamma", 947, (String) null, true));
            _put(new SpecialEntity("delta", 948, (String) null, true));
            _put(new SpecialEntity("epsilon", 949, (String) null, true));
            _put(new SpecialEntity("zeta", 950, (String) null, true));
            _put(new SpecialEntity("eta", 951, (String) null, true));
            _put(new SpecialEntity("theta", 952, (String) null, true));
            _put(new SpecialEntity("iota", 953, (String) null, true));
            _put(new SpecialEntity("kappa", 954, (String) null, true));
            _put(new SpecialEntity("lambda", 955, (String) null, true));
            _put(new SpecialEntity("mu", 956, (String) null, true));
            _put(new SpecialEntity("nu", 957, (String) null, true));
            _put(new SpecialEntity("xi", 958, (String) null, true));
            _put(new SpecialEntity("omicron", 959, (String) null, true));
            _put(new SpecialEntity("pi", 960, (String) null, true));
            _put(new SpecialEntity("rho", 961, (String) null, true));
            _put(new SpecialEntity("sigmaf", 962, (String) null, true));
            _put(new SpecialEntity("sigma", 963, (String) null, true));
            _put(new SpecialEntity("tau", 964, (String) null, true));
            _put(new SpecialEntity("upsilon", 965, (String) null, true));
            _put(new SpecialEntity("phi", 966, (String) null, true));
            _put(new SpecialEntity("chi", 967, (String) null, true));
            _put(new SpecialEntity("psi", 968, (String) null, true));
            _put(new SpecialEntity("omega", 969, (String) null, true));
            _put(new SpecialEntity("thetasym", 977, (String) null, true));
            _put(new SpecialEntity("upsih", 978, (String) null, true));
            _put(new SpecialEntity("piv", 982, (String) null, true));
        }
        _put(new SpecialEntity("ensp", 8194, (String) null, true));
        _put(new SpecialEntity("emsp", 8195, (String) null, true));
        _put(new SpecialEntity("thinsp", 8201, (String) null, true));
        _put(new SpecialEntity("zwnj", 8204, (String) null, true));
        _put(new SpecialEntity("zwj", 8205, (String) null, true));
        _put(new SpecialEntity("lrm", 8206, (String) null, true));
        _put(new SpecialEntity("rlm", 8207, (String) null, true));
        _put(new SpecialEntity("ndash", 8211, (String) null, true));
        _put(new SpecialEntity("mdash", 8212, (String) null, true));
        _put(new SpecialEntity("lsquo", 8216, (String) null, true));
        _put(new SpecialEntity("rsquo", 8217, (String) null, true));
        _put(new SpecialEntity("sbquo", 8218, (String) null, true));
        _put(new SpecialEntity("ldquo", 8220, (String) null, true));
        _put(new SpecialEntity("rdquo", 8221, (String) null, true));
        _put(new SpecialEntity("bdquo", 8222, (String) null, true));
        _put(new SpecialEntity("dagger", 8224, (String) null, true));
        _put(new SpecialEntity("Dagger", 8225, (String) null, true));
        _put(new SpecialEntity("bull", 8226, (String) null, true));
        _put(new SpecialEntity("hellip", 8230, (String) null, true));
        _put(new SpecialEntity("permil", 8240, (String) null, true));
        _put(new SpecialEntity("prime", 8242, (String) null, true));
        _put(new SpecialEntity("Prime", 8243, (String) null, true));
        _put(new SpecialEntity("lsaquo", 8249, (String) null, true));
        _put(new SpecialEntity("rsaquo", 8250, (String) null, true));
        _put(new SpecialEntity("oline", 8254, (String) null, true));
        _put(new SpecialEntity("frasl", 8260, (String) null, true));
        _put(new SpecialEntity("euro", 8364, (String) null, true));
        _put(new SpecialEntity("image", 8465, (String) null, true));
        _put(new SpecialEntity("weierp", 8472, (String) null, true));
        _put(new SpecialEntity("real", 8476, (String) null, true));
        _put(new SpecialEntity("trade", 8482, (String) null, true));
        _put(new SpecialEntity("alefsym", 8501, (String) null, true));
        _put(new SpecialEntity("larr", 8592, (String) null, true));
        _put(new SpecialEntity("uarr", 8593, (String) null, true));
        _put(new SpecialEntity("rarr", 8594, (String) null, true));
        _put(new SpecialEntity("darr", 8595, (String) null, true));
        _put(new SpecialEntity("harr", 8596, (String) null, true));
        _put(new SpecialEntity("crarr", 8629, (String) null, true));
        _put(new SpecialEntity("lArr", 8656, (String) null, true));
        _put(new SpecialEntity("uArr", 8657, (String) null, true));
        _put(new SpecialEntity("rArr", 8658, (String) null, true));
        _put(new SpecialEntity("dArr", 8659, (String) null, true));
        _put(new SpecialEntity("hArr", 8660, (String) null, true));
        if (this.math) {
            _put(new SpecialEntity("forall", 8704, (String) null, true));
            _put(new SpecialEntity("part", 8706, (String) null, true));
            _put(new SpecialEntity("exist", 8707, (String) null, true));
            _put(new SpecialEntity(CleanerProperties.BOOL_ATT_EMPTY, 8709, (String) null, true));
            _put(new SpecialEntity("nabla", 8711, (String) null, true));
            _put(new SpecialEntity("isin", 8712, (String) null, true));
            _put(new SpecialEntity("notin", 8713, (String) null, true));
            _put(new SpecialEntity("ni", 8715, (String) null, true));
            _put(new SpecialEntity("prod", 8719, (String) null, true));
            _put(new SpecialEntity("sum", 8721, (String) null, true));
            _put(new SpecialEntity("minus", 8722, (String) null, true));
            _put(new SpecialEntity("lowast", 8727, (String) null, true));
            _put(new SpecialEntity("radic", 8730, (String) null, true));
            _put(new SpecialEntity("prop", 8733, (String) null, true));
            _put(new SpecialEntity("infin", 8734, (String) null, true));
            _put(new SpecialEntity("ang", 8736, (String) null, true));
            _put(new SpecialEntity("and", 8743, (String) null, true));
            _put(new SpecialEntity("or", 8744, (String) null, true));
            _put(new SpecialEntity("cap", 8745, (String) null, true));
            _put(new SpecialEntity("cup", 8746, (String) null, true));
            _put(new SpecialEntity("int", 8747, (String) null, true));
            _put(new SpecialEntity("there4", 8756, (String) null, true));
            _put(new SpecialEntity("sim", 8764, (String) null, true));
            _put(new SpecialEntity("cong", 8773, (String) null, true));
            _put(new SpecialEntity("asymp", 8776, (String) null, true));
            _put(new SpecialEntity("ne", 8800, (String) null, true));
            _put(new SpecialEntity("equiv", 8801, (String) null, true));
            _put(new SpecialEntity("le", 8804, (String) null, true));
            _put(new SpecialEntity("ge", 8805, (String) null, true));
            _put(new SpecialEntity("sub", 8834, (String) null, true));
            _put(new SpecialEntity("sup", 8835, (String) null, true));
            _put(new SpecialEntity("nsub", 8836, (String) null, true));
            _put(new SpecialEntity("sube", 8838, (String) null, true));
            _put(new SpecialEntity("supe", 8839, (String) null, true));
            _put(new SpecialEntity("oplus", 8853, (String) null, true));
            _put(new SpecialEntity("otimes", 8855, (String) null, true));
            _put(new SpecialEntity("perp", 8869, (String) null, true));
            _put(new SpecialEntity("sdot", 8901, (String) null, true));
            _put(new SpecialEntity("lceil", 8968, (String) null, true));
            _put(new SpecialEntity("rceil", 8969, (String) null, true));
            _put(new SpecialEntity("lfloor", 8970, (String) null, true));
            _put(new SpecialEntity("rfloor", 8971, (String) null, true));
            _put(new SpecialEntity("lang", AdError.AD_PRESENTATION_ERROR_CODE, (String) null, true));
            _put(new SpecialEntity("rang", 9002, (String) null, true));
            _put(new SpecialEntity("loz", 9674, (String) null, true));
            _put(new SpecialEntity("spades", 9824, (String) null, true));
            _put(new SpecialEntity("clubs", 9827, (String) null, true));
            _put(new SpecialEntity("hearts", 9829, (String) null, true));
            _put(new SpecialEntity("diams", 9830, (String) null, true));
        }
        _put(new SpecialEntity("amp", 38, (String) null, false));
        _put(new SpecialEntity("lt", 60, (String) null, false));
        _put(new SpecialEntity("gt", 62, (String) null, false));
        _put(new SpecialEntity("quot", 34, (String) null, false));
        _put(new SpecialEntity("apos", 39, "'", false));
    }

    public SpecialEntity getSpecialEntity(String str) {
        String str2;
        int i = 0;
        if (str.charAt(0) == '&') {
            i = 1;
        }
        int indexOf = str.indexOf(59);
        if (indexOf < 0) {
            str2 = str.substring(i);
        } else {
            str2 = str.substring(i, indexOf);
        }
        return this.entities.get(str2);
    }

    public SpecialEntity getSpecialEntityByUnicode(int i) {
        return this.entitiesByUnicodeCharcode.get(Integer.valueOf(i));
    }

    public void put(SpecialEntity specialEntity) {
        _put(specialEntity);
    }

    private void _put(SpecialEntity specialEntity) {
        SpecialEntity put = this.entities.put(specialEntity.getKey(), specialEntity);
        if (put == null) {
            SpecialEntity put2 = this.entitiesByUnicodeCharcode.put(Integer.valueOf(specialEntity.intValue()), specialEntity);
            if (put2 == null) {
                this.maxEntityLength = Math.max(this.maxEntityLength, specialEntity.getKey().length());
                return;
            }
            throw new HtmlCleanerException("replaced " + put2 + " with " + specialEntity);
        }
        throw new HtmlCleanerException("replaced " + put + " with " + specialEntity);
    }

    public int getMaxEntityLength() {
        return this.maxEntityLength;
    }
}

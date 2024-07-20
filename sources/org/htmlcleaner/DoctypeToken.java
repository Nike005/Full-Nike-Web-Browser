package org.htmlcleaner;

import com.mopub.common.AdType;
import java.io.IOException;
import java.io.Writer;
import kotlin.text.Typography;
import org.apache.commons.lang3.StringUtils;

public class DoctypeToken extends BaseTokenImpl {
    public static final int HTML4_0 = 10;
    public static final int HTML4_01 = 20;
    public static final int HTML4_01_FRAMESET = 23;
    public static final int HTML4_01_STRICT = 21;
    public static final int HTML4_01_TRANSITIONAL = 22;
    public static final int HTML5 = 60;
    public static final int HTML5_LEGACY_TOOL_COMPATIBLE = 61;
    public static final int UNKNOWN = 0;
    public static final int XHTML1_0_FRAMESET = 33;
    public static final int XHTML1_0_STRICT = 31;
    public static final int XHTML1_0_TRANSITIONAL = 32;
    public static final int XHTML1_1 = 40;
    public static final int XHTML1_1_BASIC = 41;
    private String part1;
    private String part2;
    private String part3;
    private String part4;
    private Integer type = null;
    private Boolean valid = null;

    public String getName() {
        return "";
    }

    public DoctypeToken(String str, String str2, String str3, String str4) {
        this.part1 = str;
        this.part2 = str2 != null ? str2.toUpperCase() : str2;
        this.part3 = clean(str3);
        this.part4 = clean(str4);
        validate();
    }

    public DoctypeToken(String str, String str2, String str3, String str4, String str5) {
        this.part1 = str;
        this.part2 = str2 != null ? str2.toUpperCase() : str2;
        this.part3 = clean(str3);
        this.part4 = clean(str5);
        validate();
    }

    private String clean(String str) {
        return str != null ? str.replace(Typography.greater, ' ').replace(Typography.less, ' ').replace(Typography.amp, ' ').replace('\'', ' ').replace(Typography.quote, ' ') : str;
    }

    public boolean isValid() {
        return this.valid.booleanValue();
    }

    private void validate() {
        if (!"public".equalsIgnoreCase(this.part2) && !"system".equalsIgnoreCase(this.part2) && AdType.HTML.equalsIgnoreCase(this.part1) && this.part2 == null) {
            this.type = 60;
            this.valid = true;
        }
        if ("public".equalsIgnoreCase(this.part2)) {
            if ("-//W3C//DTD HTML 4.0//EN".equals(getPublicId())) {
                this.type = 10;
                if ("http://www.w3.org/TR/REC-html40/strict.dtd".equals(this.part4) || "".equals(getSystemId())) {
                    this.valid = true;
                } else {
                    this.valid = false;
                }
            }
            if ("-//W3C//DTD HTML 4.01//EN".equals(getPublicId())) {
                this.type = 21;
                if ("http://www.w3.org/TR/html4/strict.dtd".equals(this.part4) || "".equals(getSystemId())) {
                    this.valid = true;
                } else {
                    this.valid = false;
                }
            }
            if ("-//W3C//DTD HTML 4.01 Transitional//EN".equals(getPublicId())) {
                this.type = 22;
                if ("http://www.w3.org/TR/html4/loose.dtd".equals(getSystemId())) {
                    this.valid = true;
                } else {
                    this.valid = false;
                }
            }
            if ("-//W3C//DTD HTML 4.01 Frameset//EN".equals(getPublicId())) {
                this.type = 23;
                if ("http://www.w3.org/TR/html4/frameset.dtd".equals(getSystemId())) {
                    this.valid = true;
                } else {
                    this.valid = false;
                }
            }
            if ("-//W3C//DTD XHTML 1.0 Strict//EN".equals(getPublicId())) {
                this.type = 31;
                if ("http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd".equals(getSystemId())) {
                    this.valid = true;
                } else {
                    this.valid = false;
                }
            }
            if ("-//W3C//DTD XHTML 1.0 Transitional//EN".equals(getPublicId())) {
                this.type = 32;
                if ("http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd".equals(getSystemId())) {
                    this.valid = true;
                } else {
                    this.valid = false;
                }
            }
            if ("-//W3C//DTD XHTML 1.0 Frameset//EN".equals(getPublicId())) {
                this.type = 33;
                if ("http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd".equals(getSystemId())) {
                    this.valid = true;
                } else {
                    this.valid = false;
                }
            }
            if ("-//W3C//DTD XHTML 1.1//EN".equals(getPublicId())) {
                this.type = 40;
                if ("http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd".equals(getSystemId())) {
                    this.valid = true;
                } else {
                    this.valid = false;
                }
            }
            if ("-//W3C//DTD XHTML Basic 1.1//EN".equals(getPublicId())) {
                this.type = 41;
                if ("http://www.w3.org/TR/xhtml11/DTD/xhtml-basic11.dtd".equals(getSystemId())) {
                    this.valid = true;
                } else {
                    this.valid = false;
                }
            }
        }
        if ("system".equalsIgnoreCase(this.part2) && "about:legacy-compat".equals(getPublicId())) {
            this.type = 61;
            this.valid = true;
        }
        if (this.type == null) {
            this.type = 0;
            this.valid = false;
        }
    }

    public String getContent() {
        String str;
        if (this.type.intValue() != 0) {
            str = this.type.intValue() >= 30 ? "<!DOCTYPE html" : "<!DOCTYPE HTML";
        } else {
            str = "<!DOCTYPE " + this.part1;
        }
        if (this.part2 != null) {
            String str2 = str + StringUtils.SPACE + this.part2 + " \"" + this.part3 + "\"";
            if (!"".equals(this.part4)) {
                str = str2 + " \"" + this.part4 + "\"";
            } else {
                str = str2;
            }
        }
        return str + ">";
    }

    public String toString() {
        return getContent();
    }

    public int getType() {
        return this.type.intValue();
    }

    public void serialize(Serializer serializer, Writer writer) throws IOException {
        writer.write(getContent() + StringUtils.f3949LF);
    }

    public String getPublicId() {
        return this.part3;
    }

    public String getSystemId() {
        return this.part4;
    }

    public String getPart1() {
        return this.part1;
    }

    public String getPart2() {
        return this.part2;
    }

    @Deprecated
    public String getPart3() {
        return this.part3;
    }

    @Deprecated
    public String getPart4() {
        return this.part4;
    }
}

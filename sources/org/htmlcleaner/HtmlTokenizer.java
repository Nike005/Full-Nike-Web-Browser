package org.htmlcleaner;

import com.mopub.common.AdType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import kotlin.text.Typography;

public class HtmlTokenizer {
    private static final int WORKING_BUFFER_SIZE = 1024;
    private boolean _asExpected = true;
    private transient int _col = 1;
    private transient TagToken _currentTagToken;
    private transient DoctypeToken _docType;
    private transient boolean _isLateForDoctype;
    private boolean _isScriptContext;
    private transient int _len = -1;
    private transient Set<String> _namespacePrefixes = new HashSet();
    private transient int _pos;
    private BufferedReader _reader;
    private transient int _row = 1;
    private transient StringBuffer _saved = new StringBuffer(512);
    private transient List<BaseToken> _tokenList = new ArrayList();
    private char[] _working = new char[1024];
    private CleanTimeValues cleanTimeValues;
    private HtmlCleaner cleaner;
    private CleanerProperties props;
    private CleanerTransformations transformations;

    public HtmlTokenizer(HtmlCleaner htmlCleaner, Reader reader, CleanTimeValues cleanTimeValues2) {
        this._reader = new BufferedReader(reader);
        this.cleaner = htmlCleaner;
        this.props = htmlCleaner.getProperties();
        this.transformations = htmlCleaner.getTransformations();
        this.cleanTimeValues = cleanTimeValues2;
    }

    private void addToken(BaseToken baseToken) {
        baseToken.setRow(this._row);
        baseToken.setCol(this._col);
        this._tokenList.add(baseToken);
        HtmlCleaner htmlCleaner = this.cleaner;
        List<BaseToken> list = this._tokenList;
        htmlCleaner.makeTree(list, list.listIterator(list.size() - 1), this.cleanTimeValues);
    }

    private void readIfNeeded(int i) throws IOException {
        if (this._len == -1) {
            int i2 = this._pos;
            if (i + i2 >= 1024) {
                int i3 = 1024 - i2;
                char[] cArr = this._working;
                int i4 = 0;
                System.arraycopy(cArr, i2, cArr, 0, i3);
                this._pos = 0;
                int i5 = 1024 - i3;
                int i6 = i3;
                int i7 = 0;
                do {
                    int read = this._reader.read(this._working, i6, i5);
                    if (read >= 0) {
                        i7 += read;
                        i6 += read;
                        i5 -= read;
                    }
                    if (read < 0) {
                        break;
                    }
                } while (i5 > 0);
                if (i5 > 0) {
                    this._len = i7 + i3;
                }
                while (true) {
                    int i8 = this._len;
                    if (i8 < 0) {
                        i8 = 1024;
                    }
                    if (i4 < i8) {
                        char[] cArr2 = this._working;
                        char c = cArr2[i4];
                        if (c >= 1 && c <= ' ' && c != 10 && c != 13) {
                            cArr2[i4] = ' ';
                        }
                        i4++;
                    } else {
                        return;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public List<BaseToken> getTokenList() {
        return this._tokenList;
    }

    /* access modifiers changed from: package-private */
    public Set<String> getNamespacePrefixes() {
        return this._namespacePrefixes;
    }

    /* renamed from: go */
    private void m6099go() throws IOException {
        m6100go(1);
    }

    /* renamed from: go */
    private void m6100go(int i) throws IOException {
        this._pos += i;
        readIfNeeded(i - 1);
    }

    private boolean startsWith(String str) throws IOException {
        int length = str.length();
        readIfNeeded(length);
        int i = this._len;
        if (i >= 0 && this._pos + length > i) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (Character.toLowerCase(str.charAt(i2)) != Character.toLowerCase(this._working[this._pos + i2])) {
                return false;
            }
        }
        return true;
    }

    private boolean isWhitespace(int i) {
        int i2 = this._len;
        if (i2 < 0 || i < i2) {
            return Character.isWhitespace(this._working[i]);
        }
        return false;
    }

    private boolean isWhitespace() {
        return isWhitespace(this._pos);
    }

    private boolean isChar(int i, char c) {
        int i2 = this._len;
        if ((i2 < 0 || i < i2) && Character.toLowerCase(c) == Character.toLowerCase(this._working[i])) {
            return true;
        }
        return false;
    }

    private boolean isChar(char c) {
        return isChar(this._pos, c);
    }

    private boolean isIdentifierStartChar(int i) {
        int i2 = this._len;
        if (i2 < 0 || i < i2) {
            return Character.isUnicodeIdentifierStart(this._working[i]);
        }
        return false;
    }

    private boolean isIdentifierStartChar() {
        return isIdentifierStartChar(this._pos);
    }

    private boolean isIdentifierChar() {
        int i = this._len;
        if (i >= 0 && this._pos >= i) {
            return false;
        }
        char c = this._working[this._pos];
        if (Character.isUnicodeIdentifierStart(c) || Character.isDigit(c) || C2401Utils.isIdentifierHelperChar(c)) {
            return true;
        }
        return false;
    }

    private boolean isAllRead() {
        int i = this._len;
        return i >= 0 && this._pos >= i;
    }

    private void save(char c) {
        updateCoordinates(c);
        this._saved.append(c);
    }

    private void updateCoordinates(char c) {
        if (c == 10) {
            this._row++;
            this._col = 1;
            return;
        }
        this._col++;
    }

    private void saveCurrent() {
        if (!isAllRead()) {
            save(this._working[this._pos]);
        }
    }

    private void saveCurrent(int i) throws IOException {
        readIfNeeded(i);
        int i2 = this._pos;
        while (!isAllRead() && i > 0) {
            save(this._working[i2]);
            i2++;
            i--;
        }
    }

    private void skipWhitespaces() throws IOException {
        while (!isAllRead() && isWhitespace()) {
            saveCurrent();
            m6099go();
        }
    }

    private boolean addSavedAsContent() {
        if (this._saved.length() <= 0) {
            return false;
        }
        addToken(new ContentNode(this._saved.toString()));
        StringBuffer stringBuffer = this._saved;
        stringBuffer.delete(0, stringBuffer.length());
        return true;
    }

    /* access modifiers changed from: package-private */
    public void start() throws IOException {
        String obj;
        this._currentTagToken = null;
        this._tokenList.clear();
        this._asExpected = true;
        this._isScriptContext = false;
        this._isLateForDoctype = false;
        this._namespacePrefixes.clear();
        this._pos = 1024;
        readIfNeeded(0);
        while (true) {
            boolean z = true;
            while (true) {
                if (!isAllRead()) {
                    StringBuffer stringBuffer = this._saved;
                    stringBuffer.delete(0, stringBuffer.length());
                    this._currentTagToken = null;
                    this._asExpected = true;
                    readIfNeeded(10);
                    if (this._isScriptContext) {
                        if (startsWith("</script") && (isWhitespace(this._pos + 8) || isChar(this._pos + 8, Typography.greater))) {
                            tagEnd();
                        } else if (!z || !startsWith("<!--")) {
                            boolean content = content();
                            if (z && content) {
                                List<BaseToken> list = this._tokenList;
                                BaseToken baseToken = list.get(list.size() - 1);
                                if (!(baseToken == null || (obj = baseToken.toString()) == null || obj.trim().length() <= 0)) {
                                    z = false;
                                }
                            }
                        } else {
                            comment();
                        }
                        if (!this._isScriptContext) {
                        }
                    } else if (startsWith("<!doctype")) {
                        if (!this._isLateForDoctype) {
                            doctype();
                            this._isLateForDoctype = true;
                        } else {
                            ignoreUntil(Typography.less);
                        }
                    } else if (startsWith("</") && isIdentifierStartChar(this._pos + 2)) {
                        this._isLateForDoctype = true;
                        tagEnd();
                    } else if (startsWith("<!--")) {
                        comment();
                    } else if (startsWith("<") && isIdentifierStartChar(this._pos + 1)) {
                        this._isLateForDoctype = true;
                        tagStart();
                    } else if (this.props.isIgnoreQuestAndExclam() && (startsWith("<!") || startsWith("<?"))) {
                        ignoreUntil(Typography.greater);
                        if (isChar(Typography.greater)) {
                            m6099go();
                        }
                    } else if (startsWith("<?xml")) {
                        ignoreUntil(Typography.less);
                    } else {
                        content();
                    }
                } else {
                    this._reader.close();
                    return;
                }
            }
        }
    }

    private boolean isReservedTag(String str) {
        return AdType.HTML.equalsIgnoreCase(str) || "head".equalsIgnoreCase(str) || "body".equalsIgnoreCase(str);
    }

    private void tagStart() throws IOException {
        TagInfo tagInfo;
        saveCurrent();
        m6099go();
        if (!isAllRead()) {
            String identifier = identifier();
            String tagName = this.transformations.getTagName(identifier);
            if (tagName == null || (((tagInfo = this.cleaner.getTagInfoProvider().getTagInfo(tagName)) != null || this.props.isOmitUnknownTags() || !this.props.isTreatUnknownTagsAsContent() || isReservedTag(tagName)) && (tagInfo == null || !tagInfo.isDeprecated() || this.props.isOmitDeprecatedTags() || !this.props.isTreatDeprecatedTagsAsContent()))) {
                TagNode tagNode = new TagNode(tagName);
                this._currentTagToken = tagNode;
                if (this._asExpected) {
                    skipWhitespaces();
                    tagAttributes();
                    if (tagName != null) {
                        CleanerTransformations cleanerTransformations = this.transformations;
                        if (cleanerTransformations != null) {
                            tagNode.setAttributes(cleanerTransformations.transformAttributes(identifier, tagNode.getAttributes()));
                        }
                        addToken(this._currentTagToken);
                    }
                    if (isChar(Typography.greater)) {
                        m6099go();
                        if ("script".equalsIgnoreCase(tagName)) {
                            this._isScriptContext = true;
                        }
                    } else if (startsWith("/>")) {
                        m6100go(2);
                    }
                    this._currentTagToken = null;
                    return;
                }
                addSavedAsContent();
                return;
            }
            content();
        }
    }

    private void tagEnd() throws IOException {
        TagInfo tagInfo;
        TagTransformation transformation;
        saveCurrent(2);
        m6100go(2);
        this._col += 2;
        if (!isAllRead()) {
            String identifier = identifier();
            CleanerTransformations cleanerTransformations = this.transformations;
            if (!(cleanerTransformations == null || !cleanerTransformations.hasTransformationForTag(identifier) || (transformation = this.transformations.getTransformation(identifier)) == null)) {
                identifier = transformation.getDestTag();
            }
            if (identifier == null || (((tagInfo = this.cleaner.getTagInfoProvider().getTagInfo(identifier)) != null || this.props.isOmitUnknownTags() || !this.props.isTreatUnknownTagsAsContent() || isReservedTag(identifier)) && (tagInfo == null || !tagInfo.isDeprecated() || this.props.isOmitDeprecatedTags() || !this.props.isTreatDeprecatedTagsAsContent()))) {
                this._currentTagToken = new EndTagToken(identifier);
                if (this._asExpected) {
                    skipWhitespaces();
                    tagAttributes();
                    if (identifier != null) {
                        addToken(this._currentTagToken);
                    }
                    if (isChar(Typography.greater)) {
                        m6099go();
                    }
                    if ("script".equalsIgnoreCase(identifier)) {
                        this._isScriptContext = false;
                    }
                    this._currentTagToken = null;
                    return;
                }
                addSavedAsContent();
                return;
            }
            content();
        }
    }

    private String identifier() throws IOException {
        this._asExpected = true;
        if (!isIdentifierStartChar()) {
            this._asExpected = false;
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (!isAllRead() && isIdentifierChar()) {
            saveCurrent();
            stringBuffer.append(this._working[this._pos]);
            m6099go();
        }
        while (stringBuffer.length() > 0 && C2401Utils.isIdentifierHelperChar(stringBuffer.charAt(stringBuffer.length() - 1))) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        if (stringBuffer.length() == 0) {
            return null;
        }
        String stringBuffer2 = stringBuffer.toString();
        int indexOf = stringBuffer2.indexOf(58);
        if (indexOf >= 0) {
            String substring = stringBuffer2.substring(0, indexOf);
            String substring2 = stringBuffer2.substring(indexOf + 1);
            int indexOf2 = substring2.indexOf(58);
            if (indexOf2 >= 0) {
                substring2 = substring2.substring(0, indexOf2);
            }
            stringBuffer2 = substring2;
            if (this.props.isNamespacesAware()) {
                stringBuffer2 = substring + ":" + stringBuffer2;
                if (!"xmlns".equalsIgnoreCase(substring)) {
                    this._namespacePrefixes.add(substring.toLowerCase());
                }
            }
        }
        return stringBuffer2;
    }

    private void tagAttributes() throws IOException {
        while (!isAllRead() && this._asExpected && !isChar(Typography.greater) && !startsWith("/>")) {
            skipWhitespaces();
            String identifier = identifier();
            if (!this._asExpected) {
                if (!isChar(Typography.less) && !isChar(Typography.greater) && !startsWith("/>")) {
                    saveCurrent();
                    m6099go();
                }
                if (!isChar(Typography.less)) {
                    this._asExpected = true;
                }
            } else {
                skipWhitespaces();
                boolean isChar = isChar('=');
                String str = CleanerProperties.BOOL_ATT_TRUE;
                if (isChar) {
                    saveCurrent();
                    m6099go();
                    str = attributeValue();
                } else if (CleanerProperties.BOOL_ATT_EMPTY.equals(this.props.getBooleanAttributeValues())) {
                    str = "";
                } else if (!str.equals(this.props.getBooleanAttributeValues())) {
                    str = identifier;
                }
                if (this._asExpected) {
                    this._currentTagToken.addAttribute(identifier, str);
                }
            }
        }
    }

    private String attributeValue() throws IOException {
        skipWhitespaces();
        if (isChar(Typography.less) || isChar(Typography.greater) || startsWith("/>")) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = true;
        boolean z2 = false;
        if (isChar('\'')) {
            saveCurrent();
            m6099go();
        } else if (isChar(Typography.quote)) {
            saveCurrent();
            m6099go();
            z = false;
            z2 = true;
        } else {
            z = false;
        }
        boolean isAllowMultiWordAttributes = this.props.isAllowMultiWordAttributes();
        boolean isAllowHtmlInsideAttributes = this.props.isAllowHtmlInsideAttributes();
        while (!isAllRead() && ((z && !isChar('\'') && ((isAllowHtmlInsideAttributes || (!isChar(Typography.greater) && !isChar(Typography.less))) && (isAllowMultiWordAttributes || !isWhitespace()))) || ((z2 && !isChar(Typography.quote) && ((isAllowHtmlInsideAttributes || (!isChar(Typography.greater) && !isChar(Typography.less))) && (isAllowMultiWordAttributes || !isWhitespace()))) || (!z && !z2 && !isWhitespace() && !isChar(Typography.greater) && !isChar(Typography.less))))) {
            stringBuffer.append(this._working[this._pos]);
            saveCurrent();
            m6099go();
        }
        if (isChar('\'') && z) {
            saveCurrent();
            m6099go();
        } else if (isChar(Typography.quote) && z2) {
            saveCurrent();
            m6099go();
        }
        return stringBuffer.toString();
    }

    private boolean content() throws IOException {
        while (!isAllRead()) {
            saveCurrent();
            m6099go();
            if (isTagStartOrEnd()) {
                break;
            }
        }
        return addSavedAsContent();
    }

    private boolean isTagStartOrEnd() throws IOException {
        if (startsWith("</") || startsWith("<!") || startsWith("<?")) {
            return true;
        }
        return startsWith("<") && isIdentifierStartChar(this._pos + 1);
    }

    private void ignoreUntil(char c) throws IOException {
        while (!isAllRead()) {
            m6099go();
            updateCoordinates(this._working[this._pos]);
            if (isChar(c)) {
                return;
            }
        }
    }

    private void comment() throws IOException {
        m6100go(4);
        while (!isAllRead() && !startsWith("-->")) {
            saveCurrent();
            m6099go();
        }
        if (startsWith("-->")) {
            m6100go(3);
        }
        if (this._saved.length() > 0) {
            if (!this.props.isOmitComments()) {
                String hyphenReplacementInComment = this.props.getHyphenReplacementInComment();
                String replaceAll = this._saved.toString().replaceAll("--", hyphenReplacementInComment + hyphenReplacementInComment);
                if (replaceAll.length() > 0 && replaceAll.charAt(0) == '-') {
                    replaceAll = hyphenReplacementInComment + replaceAll.substring(1);
                }
                int length = replaceAll.length();
                if (length > 0) {
                    int i = length - 1;
                    if (replaceAll.charAt(i) == '-') {
                        replaceAll = replaceAll.substring(0, i) + hyphenReplacementInComment;
                    }
                }
                addToken(new CommentNode(replaceAll));
            }
            StringBuffer stringBuffer = this._saved;
            stringBuffer.delete(0, stringBuffer.length());
        }
    }

    private void doctype() throws IOException {
        m6100go(9);
        skipWhitespaces();
        String identifier = identifier();
        skipWhitespaces();
        String identifier2 = identifier();
        skipWhitespaces();
        String attributeValue = attributeValue();
        skipWhitespaces();
        String attributeValue2 = attributeValue();
        skipWhitespaces();
        String attributeValue3 = attributeValue();
        ignoreUntil(Typography.less);
        if (attributeValue3 == null || attributeValue3.length() == 0) {
            this._docType = new DoctypeToken(identifier, identifier2, attributeValue, attributeValue2);
        } else {
            this._docType = new DoctypeToken(identifier, identifier2, attributeValue, attributeValue2, attributeValue3);
        }
    }

    public DoctypeToken getDocType() {
        return this._docType;
    }
}

package org.jsoup.parser;

import java.util.List;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Token;

public class XmlTreeBuilder extends TreeBuilder {
    public /* bridge */ /* synthetic */ boolean processStartTag(String str, Attributes attributes) {
        return super.processStartTag(str, attributes);
    }

    /* access modifiers changed from: package-private */
    public ParseSettings defaultSettings() {
        return ParseSettings.preserveCase;
    }

    /* access modifiers changed from: package-private */
    public Document parse(String str, String str2) {
        return parse(str, str2, ParseErrorList.noTracking(), ParseSettings.preserveCase);
    }

    /* access modifiers changed from: protected */
    public void initialiseParse(String str, String str2, ParseErrorList parseErrorList, ParseSettings parseSettings) {
        super.initialiseParse(str, str2, parseErrorList, parseSettings);
        this.stack.add(this.doc);
        this.doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
    }

    /* renamed from: org.jsoup.parser.XmlTreeBuilder$1 */
    static /* synthetic */ class C25271 {
        static final /* synthetic */ int[] $SwitchMap$org$jsoup$parser$Token$TokenType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                org.jsoup.parser.Token$TokenType[] r0 = org.jsoup.parser.Token.TokenType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$jsoup$parser$Token$TokenType = r0
                org.jsoup.parser.Token$TokenType r1 = org.jsoup.parser.Token.TokenType.StartTag     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$org$jsoup$parser$Token$TokenType     // Catch:{ NoSuchFieldError -> 0x001d }
                org.jsoup.parser.Token$TokenType r1 = org.jsoup.parser.Token.TokenType.EndTag     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$org$jsoup$parser$Token$TokenType     // Catch:{ NoSuchFieldError -> 0x0028 }
                org.jsoup.parser.Token$TokenType r1 = org.jsoup.parser.Token.TokenType.Comment     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$org$jsoup$parser$Token$TokenType     // Catch:{ NoSuchFieldError -> 0x0033 }
                org.jsoup.parser.Token$TokenType r1 = org.jsoup.parser.Token.TokenType.Character     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$org$jsoup$parser$Token$TokenType     // Catch:{ NoSuchFieldError -> 0x003e }
                org.jsoup.parser.Token$TokenType r1 = org.jsoup.parser.Token.TokenType.Doctype     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$org$jsoup$parser$Token$TokenType     // Catch:{ NoSuchFieldError -> 0x0049 }
                org.jsoup.parser.Token$TokenType r1 = org.jsoup.parser.Token.TokenType.EOF     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.XmlTreeBuilder.C25271.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public boolean process(Token token) {
        switch (C25271.$SwitchMap$org$jsoup$parser$Token$TokenType[token.type.ordinal()]) {
            case 1:
                insert(token.asStartTag());
                return true;
            case 2:
                popStackToClose(token.asEndTag());
                return true;
            case 3:
                insert(token.asComment());
                return true;
            case 4:
                insert(token.asCharacter());
                return true;
            case 5:
                insert(token.asDoctype());
                return true;
            case 6:
                return true;
            default:
                Validate.fail("Unexpected token type: " + token.type);
                return true;
        }
    }

    private void insertNode(Node node) {
        currentElement().appendChild(node);
    }

    /* access modifiers changed from: package-private */
    public Element insert(Token.StartTag startTag) {
        Tag valueOf = Tag.valueOf(startTag.name(), this.settings);
        Element element = new Element(valueOf, this.baseUri, this.settings.normalizeAttributes(startTag.attributes));
        insertNode(element);
        if (startTag.isSelfClosing()) {
            this.tokeniser.acknowledgeSelfClosingFlag();
            if (!valueOf.isKnownTag()) {
                valueOf.setSelfClosing();
            }
        } else {
            this.stack.add(element);
        }
        return element;
    }

    /* JADX WARNING: type inference failed for: r3v4, types: [org.jsoup.nodes.Node, org.jsoup.nodes.XmlDeclaration] */
    /* access modifiers changed from: package-private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void insert(org.jsoup.parser.Token.Comment r7) {
        /*
            r6 = this;
            org.jsoup.nodes.Comment r0 = new org.jsoup.nodes.Comment
            java.lang.String r1 = r7.getData()
            java.lang.String r2 = r6.baseUri
            r0.<init>(r1, r2)
            boolean r7 = r7.bogus
            if (r7 == 0) goto L_0x007b
            java.lang.String r7 = r0.getData()
            int r1 = r7.length()
            r2 = 1
            if (r1 <= r2) goto L_0x007b
            java.lang.String r1 = "!"
            boolean r3 = r7.startsWith(r1)
            if (r3 != 0) goto L_0x002a
            java.lang.String r3 = "?"
            boolean r3 = r7.startsWith(r3)
            if (r3 == 0) goto L_0x007b
        L_0x002a:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "<"
            r3.append(r4)
            int r4 = r7.length()
            int r4 = r4 - r2
            java.lang.String r2 = r7.substring(r2, r4)
            r3.append(r2)
            java.lang.String r2 = ">"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            java.lang.String r3 = r6.baseUri
            org.jsoup.parser.Parser r4 = org.jsoup.parser.Parser.xmlParser()
            org.jsoup.nodes.Document r2 = org.jsoup.Jsoup.parse((java.lang.String) r2, (java.lang.String) r3, (org.jsoup.parser.Parser) r4)
            r3 = 0
            org.jsoup.nodes.Element r2 = r2.child(r3)
            org.jsoup.nodes.XmlDeclaration r3 = new org.jsoup.nodes.XmlDeclaration
            org.jsoup.parser.ParseSettings r4 = r6.settings
            java.lang.String r5 = r2.tagName()
            java.lang.String r4 = r4.normalizeTag(r5)
            java.lang.String r0 = r0.baseUri()
            boolean r7 = r7.startsWith(r1)
            r3.<init>(r4, r0, r7)
            org.jsoup.nodes.Attributes r7 = r3.attributes()
            org.jsoup.nodes.Attributes r0 = r2.attributes()
            r7.addAll(r0)
            r0 = r3
        L_0x007b:
            r6.insertNode(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jsoup.parser.XmlTreeBuilder.insert(org.jsoup.parser.Token$Comment):void");
    }

    /* access modifiers changed from: package-private */
    public void insert(Token.Character character) {
        insertNode(new TextNode(character.getData(), this.baseUri));
    }

    /* access modifiers changed from: package-private */
    public void insert(Token.Doctype doctype) {
        insertNode(new DocumentType(this.settings.normalizeTag(doctype.getName()), doctype.getPubSysKey(), doctype.getPublicIdentifier(), doctype.getSystemIdentifier(), this.baseUri));
    }

    private void popStackToClose(Token.EndTag endTag) {
        Element element;
        String name = endTag.name();
        int size = this.stack.size() - 1;
        while (true) {
            if (size < 0) {
                element = null;
                break;
            }
            element = (Element) this.stack.get(size);
            if (element.nodeName().equals(name)) {
                break;
            }
            size--;
        }
        if (element != null) {
            int size2 = this.stack.size() - 1;
            while (size2 >= 0) {
                Element element2 = (Element) this.stack.get(size2);
                this.stack.remove(size2);
                if (element2 != element) {
                    size2--;
                } else {
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public List<Node> parseFragment(String str, String str2, ParseErrorList parseErrorList, ParseSettings parseSettings) {
        initialiseParse(str, str2, parseErrorList, parseSettings);
        runParser();
        return this.doc.childNodes();
    }
}

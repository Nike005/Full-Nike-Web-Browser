package org.jdom2;

public class IllegalAddException extends IllegalArgumentException {
    private static final long serialVersionUID = 200;

    IllegalAddException(Element element, Attribute attribute, String str) {
        super("The attribute \"" + attribute.getQualifiedName() + "\" could not be added to the element \"" + element.getQualifiedName() + "\": " + str);
    }

    IllegalAddException(Element element, Element element2, String str) {
        super("The element \"" + element2.getQualifiedName() + "\" could not be added as a child of \"" + element.getQualifiedName() + "\": " + str);
    }

    IllegalAddException(Element element, String str) {
        super("The element \"" + element.getQualifiedName() + "\" could not be added as the root of the document: " + str);
    }

    IllegalAddException(Element element, ProcessingInstruction processingInstruction, String str) {
        super("The PI \"" + processingInstruction.getTarget() + "\" could not be added as content to \"" + element.getQualifiedName() + "\": " + str);
    }

    IllegalAddException(ProcessingInstruction processingInstruction, String str) {
        super("The PI \"" + processingInstruction.getTarget() + "\" could not be added to the top level of the document: " + str);
    }

    IllegalAddException(Element element, Comment comment, String str) {
        super("The comment \"" + comment.getText() + "\" could not be added as content to \"" + element.getQualifiedName() + "\": " + str);
    }

    IllegalAddException(Element element, CDATA cdata, String str) {
        super("The CDATA \"" + cdata.getText() + "\" could not be added as content to \"" + element.getQualifiedName() + "\": " + str);
    }

    IllegalAddException(Element element, Text text, String str) {
        super("The Text \"" + text.getText() + "\" could not be added as content to \"" + element.getQualifiedName() + "\": " + str);
    }

    IllegalAddException(Comment comment, String str) {
        super("The comment \"" + comment.getText() + "\" could not be added to the top level of the document: " + str);
    }

    IllegalAddException(Element element, EntityRef entityRef, String str) {
        super("The entity reference\"" + entityRef.getName() + "\" could not be added as content to \"" + element.getQualifiedName() + "\": " + str);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    IllegalAddException(org.jdom2.Element r5, org.jdom2.Namespace r6, java.lang.String r7) {
        /*
            r4 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "The namespace xmlns"
            r0.append(r1)
            java.lang.String r1 = r6.getPrefix()
            java.lang.String r2 = ""
            boolean r1 = r1.equals(r2)
            java.lang.String r2 = "="
            if (r1 == 0) goto L_0x0019
            goto L_0x0031
        L_0x0019:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r3 = ":"
            r1.append(r3)
            java.lang.String r3 = r6.getPrefix()
            r1.append(r3)
            r1.append(r2)
            java.lang.String r2 = r1.toString()
        L_0x0031:
            r0.append(r2)
            java.lang.String r1 = "\""
            r0.append(r1)
            java.lang.String r6 = r6.getURI()
            r0.append(r6)
            java.lang.String r6 = "\" could not be added as a namespace to \""
            r0.append(r6)
            java.lang.String r5 = r5.getQualifiedName()
            r0.append(r5)
            java.lang.String r5 = "\": "
            r0.append(r5)
            r0.append(r7)
            java.lang.String r5 = r0.toString()
            r4.<init>(r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jdom2.IllegalAddException.<init>(org.jdom2.Element, org.jdom2.Namespace, java.lang.String):void");
    }

    IllegalAddException(DocType docType, String str) {
        super("The DOCTYPE " + docType.toString() + " could not be added to the document: " + str);
    }

    public IllegalAddException(String str) {
        super(str);
    }
}

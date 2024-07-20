package org.htmlcleaner;

import com.onesignal.shortcutbadger.impl.NewHtcHomeBadger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.StringTokenizer;
import org.slf4j.Marker;

public class XPather {
    private String[] tokenArray;

    public XPather(String str) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, "/()[]\"'=<>", true);
        this.tokenArray = new String[stringTokenizer.countTokens()];
        int i = 0;
        while (stringTokenizer.hasMoreTokens()) {
            this.tokenArray[i] = stringTokenizer.nextToken();
            i++;
        }
    }

    public Object[] evaluateAgainstNode(TagNode tagNode) throws XPatherException {
        if (tagNode != null) {
            Collection<Object> evaluateAgainst = evaluateAgainst(singleton(tagNode), 0, this.tokenArray.length - 1, false, 1, 0, false, (Collection) null);
            Object[] objArr = new Object[evaluateAgainst.size()];
            int i = 0;
            for (Object obj : evaluateAgainst) {
                objArr[i] = obj;
                i++;
            }
            return objArr;
        }
        throw new XPatherException("Cannot evaluate XPath expression against null value!");
    }

    private void throwStandardException() throws XPatherException {
        throw new XPatherException();
    }

    private Collection evaluateAgainst(Collection collection, int i, int i2, boolean z, int i3, int i4, boolean z2, Collection collection2) throws XPatherException {
        boolean z3;
        Collection collection3 = collection;
        int i5 = i;
        int i6 = i2;
        if (i5 >= 0) {
            String[] strArr = this.tokenArray;
            if (i6 < strArr.length && i5 <= i6) {
                if ("".equals(strArr[i5].trim())) {
                    return evaluateAgainst(collection, i5 + 1, i2, z, i3, i4, z2, collection2);
                }
                if (isToken("(", i5)) {
                    int findClosingIndex = findClosingIndex(i5, i6);
                    if (findClosingIndex > 0) {
                        int i7 = i3;
                        int i8 = i4;
                        boolean z4 = z2;
                        Collection collection4 = collection2;
                        return evaluateAgainst(evaluateAgainst(collection, i5 + 1, findClosingIndex - 1, false, i7, i8, z4, collection4), findClosingIndex + 1, i2, false, i7, i8, z4, collection4);
                    }
                    throwStandardException();
                } else if (isToken("[", i5)) {
                    int findClosingIndex2 = findClosingIndex(i5, i6);
                    if (findClosingIndex2 <= 0 || collection3 == null) {
                        throwStandardException();
                    } else {
                        return evaluateAgainst(filterByCondition(collection, i5 + 1, findClosingIndex2 - 1), findClosingIndex2 + 1, i2, false, i3, i4, z2, collection2);
                    }
                } else if (isToken("\"", i5) || isToken("'", i5)) {
                    int findClosingIndex3 = findClosingIndex(i5, i6);
                    if (findClosingIndex3 > i5) {
                        try {
                            return evaluateAgainst(singleton(flatten(i5 + 1, findClosingIndex3 - 1)), findClosingIndex3 + 1, i2, false, i3, i4, z2, collection2);
                        } catch (Throwable th) {
                            throw th;
                        }
                    } else {
                        throwStandardException();
                    }
                } else if ((isToken("=", i5) || isToken("<", i5) || isToken(">", i5)) && z2) {
                    int i9 = i5 + 1;
                    if (!isToken("=", i9) || (!isToken("<", i5) && !isToken(">", i5))) {
                        z3 = evaluateLogic(collection, evaluateAgainst(collection2, i9, i2, false, i3, i4, z2, collection2), this.tokenArray[i5]);
                    } else {
                        z3 = evaluateLogic(collection, evaluateAgainst(collection2, i5 + 2, i2, false, i3, i4, z2, collection2), this.tokenArray[i5] + this.tokenArray[i9]);
                    }
                    return singleton(new Boolean(z3));
                } else if (isToken("/", i5)) {
                    int i10 = i5 + 1;
                    boolean isToken = isToken("/", i10);
                    if (isToken) {
                        i5 = i10;
                    }
                    if (i5 < i6) {
                        int findClosingIndex4 = findClosingIndex(i5, i6) - 1;
                        int i11 = findClosingIndex4 <= i5 ? i6 : findClosingIndex4;
                        int i12 = i4;
                        boolean z5 = z2;
                        Collection collection5 = collection2;
                        return evaluateAgainst(evaluateAgainst(collection, i5 + 1, i11, isToken, 1, i12, z5, collection5), i11 + 1, i2, false, 1, i12, z5, collection5);
                    }
                    throwStandardException();
                } else if (isFunctionCall(i5, i6)) {
                    int i13 = i2;
                    return evaluateAgainst(evaluateFunction(collection, i, i13, i3, i4, z2), findClosingIndex(i5 + 1, i6) + 1, i13, false, 1, i4, z2, collection2);
                } else if (isValidInteger(this.tokenArray[i5])) {
                    return evaluateAgainst(singleton(Integer.valueOf(this.tokenArray[i5])), i5 + 1, i2, false, i3, i4, z2, collection2);
                } else if (!isValidDouble(this.tokenArray[i5])) {
                    return getElementsByName(collection, i, i2, z, z2);
                } else {
                    return evaluateAgainst(singleton(Double.valueOf(this.tokenArray[i5])), i5 + 1, i2, false, i3, i4, z2, collection2);
                }
                throw new XPatherException();
            }
        }
        return collection3;
    }

    private String flatten(int i, int i2) {
        if (i > i2) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (i <= i2) {
            stringBuffer.append(this.tokenArray[i]);
            i++;
        }
        return stringBuffer.toString();
    }

    private boolean isValidInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    private boolean isValidDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    private boolean isIdentifier(String str) {
        if (str == null) {
            return false;
        }
        String trim = str.trim();
        if (trim.length() <= 0 || !Character.isLetter(trim.charAt(0))) {
            return false;
        }
        for (int i = 1; i < trim.length(); i++) {
            char charAt = trim.charAt(i);
            if (charAt != '_' && charAt != '-' && !Character.isLetterOrDigit(charAt)) {
                return false;
            }
        }
        return false;
    }

    private boolean isFunctionCall(int i, int i2) {
        if (!isIdentifier(this.tokenArray[i]) && !isToken("(", i + 1)) {
            return false;
        }
        int i3 = i + 1;
        if (findClosingIndex(i3, i2) > i3) {
            return true;
        }
        return false;
    }

    private Collection evaluateFunction(Collection collection, int i, int i2, int i3, int i4, boolean z) throws XPatherException {
        String trim = this.tokenArray[i].trim();
        ArrayList arrayList = new ArrayList();
        int size = collection.size();
        int i5 = 0;
        for (Object next : collection) {
            int i6 = i5 + 1;
            if ("last".equals(trim)) {
                arrayList.add(Integer.valueOf(z ? i4 : size));
            } else if ("position".equals(trim)) {
                arrayList.add(Integer.valueOf(z ? i3 : i6));
            } else if ("text".equals(trim)) {
                if (next instanceof TagNode) {
                    arrayList.add(((TagNode) next).getText());
                } else if (next instanceof String) {
                    arrayList.add(next.toString());
                }
            } else if (NewHtcHomeBadger.COUNT.equals(trim)) {
                arrayList.add(Integer.valueOf(evaluateAgainst(collection, i + 2, i2 - 1, false, i3, 0, z, (Collection) null).size()));
            } else if ("data".equals(trim)) {
                for (Object next2 : evaluateAgainst(collection, i + 2, i2 - 1, false, i3, 0, z, (Collection) null)) {
                    if (next2 instanceof TagNode) {
                        arrayList.add(((TagNode) next2).getText());
                    } else if (next2 instanceof String) {
                        arrayList.add(next2.toString());
                    }
                }
            } else {
                throw new XPatherException("Unknown function " + trim + "!");
            }
            i5 = i6;
        }
        return arrayList;
    }

    private Collection filterByCondition(Collection collection, int i, int i2) throws XPatherException {
        int i3;
        ArrayList arrayList = new ArrayList();
        int size = collection.size();
        int i4 = 0;
        for (Object next : collection) {
            int i5 = i4 + 1;
            int i6 = i5;
            ArrayList arrayList2 = new ArrayList(evaluateAgainst(singleton(next), i, i2, false, i5, size, true, singleton(next)));
            if (arrayList2.size() >= 1) {
                Object obj = arrayList2.get(0);
                if (obj instanceof Boolean) {
                    if (((Boolean) obj).booleanValue()) {
                        arrayList.add(next);
                    }
                    i3 = i6;
                } else if (obj instanceof Integer) {
                    i3 = i6;
                    if (((Integer) obj).intValue() == i3) {
                        arrayList.add(next);
                    }
                } else {
                    i3 = i6;
                    arrayList.add(next);
                }
            } else {
                i3 = i6;
            }
            i4 = i3;
        }
        return arrayList;
    }

    private boolean isToken(String str, int i) {
        String[] strArr = this.tokenArray;
        return i >= 0 && i < strArr.length && strArr[i].trim().equals(str.trim());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v2, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v3, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v5, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v8, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v9, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v10, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v11, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v12, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v13, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v14, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v6, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v7, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v8, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v9, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v10, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v11, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v12, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v13, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v14, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v15, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v15, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v16, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v16, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v17, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v17, resolved type: boolean} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v18, resolved type: boolean} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int findClosingIndex(int r13, int r14) {
        /*
            r12 = this;
            if (r13 >= r14) goto L_0x00e9
            java.lang.String[] r0 = r12.tokenArray
            r0 = r0[r13]
            java.lang.String r1 = "\""
            boolean r2 = r1.equals(r0)
            r3 = 1
            if (r2 == 0) goto L_0x0020
            int r13 = r13 + r3
        L_0x0010:
            if (r13 > r14) goto L_0x00e9
            java.lang.String[] r0 = r12.tokenArray
            r0 = r0[r13]
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L_0x001d
            return r13
        L_0x001d:
            int r13 = r13 + 1
            goto L_0x0010
        L_0x0020:
            java.lang.String r2 = "'"
            boolean r4 = r2.equals(r0)
            if (r4 == 0) goto L_0x0039
            int r13 = r13 + r3
        L_0x0029:
            if (r13 > r14) goto L_0x00e9
            java.lang.String[] r0 = r12.tokenArray
            r0 = r0[r13]
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L_0x0036
            return r13
        L_0x0036:
            int r13 = r13 + 1
            goto L_0x0029
        L_0x0039:
            java.lang.String r4 = "("
            boolean r5 = r4.equals(r0)
            java.lang.String r6 = "/"
            java.lang.String r7 = "["
            if (r5 != 0) goto L_0x0051
            boolean r5 = r7.equals(r0)
            if (r5 != 0) goto L_0x0051
            boolean r5 = r6.equals(r0)
            if (r5 == 0) goto L_0x00e9
        L_0x0051:
            boolean r5 = r4.equals(r0)
            boolean r8 = r7.equals(r0)
            boolean r0 = r6.equals(r0)
            int r13 = r13 + r3
            r9 = r8
            r8 = r5
            r5 = 1
        L_0x0061:
            if (r13 > r14) goto L_0x00e9
            java.lang.String[] r10 = r12.tokenArray
            r10 = r10[r13]
            boolean r10 = r1.equals(r10)
            if (r10 == 0) goto L_0x0071
            r3 = r3 ^ 1
            goto L_0x00da
        L_0x0071:
            java.lang.String[] r10 = r12.tokenArray
            r10 = r10[r13]
            boolean r10 = r2.equals(r10)
            if (r10 == 0) goto L_0x007e
            r5 = r5 ^ 1
            goto L_0x00da
        L_0x007e:
            java.lang.String[] r10 = r12.tokenArray
            r10 = r10[r13]
            boolean r10 = r4.equals(r10)
            if (r10 == 0) goto L_0x008f
            if (r3 == 0) goto L_0x008f
            if (r5 == 0) goto L_0x008f
            int r8 = r8 + 1
            goto L_0x00da
        L_0x008f:
            java.lang.String[] r10 = r12.tokenArray
            r10 = r10[r13]
            java.lang.String r11 = ")"
            boolean r10 = r11.equals(r10)
            if (r10 == 0) goto L_0x00a2
            if (r3 == 0) goto L_0x00a2
            if (r5 == 0) goto L_0x00a2
            int r8 = r8 + -1
            goto L_0x00da
        L_0x00a2:
            java.lang.String[] r10 = r12.tokenArray
            r10 = r10[r13]
            boolean r10 = r7.equals(r10)
            if (r10 == 0) goto L_0x00b3
            if (r3 == 0) goto L_0x00b3
            if (r5 == 0) goto L_0x00b3
            int r9 = r9 + 1
            goto L_0x00da
        L_0x00b3:
            java.lang.String[] r10 = r12.tokenArray
            r10 = r10[r13]
            java.lang.String r11 = "]"
            boolean r10 = r11.equals(r10)
            if (r10 == 0) goto L_0x00c6
            if (r3 == 0) goto L_0x00c6
            if (r5 == 0) goto L_0x00c6
            int r9 = r9 + -1
            goto L_0x00da
        L_0x00c6:
            java.lang.String[] r10 = r12.tokenArray
            r10 = r10[r13]
            boolean r10 = r6.equals(r10)
            if (r10 == 0) goto L_0x00da
            if (r3 == 0) goto L_0x00da
            if (r5 == 0) goto L_0x00da
            if (r8 != 0) goto L_0x00da
            if (r9 != 0) goto L_0x00da
            int r0 = r0 + -1
        L_0x00da:
            if (r3 == 0) goto L_0x00e5
            if (r5 == 0) goto L_0x00e5
            if (r8 != 0) goto L_0x00e5
            if (r9 != 0) goto L_0x00e5
            if (r0 != 0) goto L_0x00e5
            return r13
        L_0x00e5:
            int r13 = r13 + 1
            goto L_0x0061
        L_0x00e9:
            r13 = -1
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.htmlcleaner.XPather.findClosingIndex(int, int):int");
    }

    private boolean isAtt(String str) {
        return str != null && str.length() > 1 && str.startsWith("@");
    }

    private Collection singleton(Object obj) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(obj);
        return arrayList;
    }

    private Collection getElementsByName(Collection collection, int i, int i2, boolean z, boolean z2) throws XPatherException {
        Collection collection2;
        Collection collection3;
        String trim = this.tokenArray[i].trim();
        if (isAtt(trim)) {
            String substring = trim.substring(1);
            ArrayList arrayList = new ArrayList();
            if (z) {
                collection3 = new LinkedHashSet();
                for (Object next : collection) {
                    if (next instanceof TagNode) {
                        collection3.addAll(((TagNode) next).getAllElementsList(true));
                    }
                }
            } else {
                collection3 = collection;
            }
            for (Object next2 : collection3) {
                if (next2 instanceof TagNode) {
                    TagNode tagNode = (TagNode) next2;
                    if (Marker.ANY_MARKER.equals(substring)) {
                        arrayList.addAll(evaluateAgainst(tagNode.getAttributes().values(), i + 1, i2, false, 1, 1, z2, (Collection) null));
                    } else {
                        String attributeByName = tagNode.getAttributeByName(substring);
                        if (attributeByName != null) {
                            arrayList.addAll(evaluateAgainst(singleton(attributeByName), i + 1, i2, false, 1, 1, z2, (Collection) null));
                        }
                    }
                } else {
                    throwStandardException();
                }
            }
            return arrayList;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        int i3 = 0;
        for (Object next3 : collection) {
            if (next3 instanceof TagNode) {
                TagNode tagNode2 = (TagNode) next3;
                int i4 = i3 + 1;
                boolean equals = ".".equals(trim);
                boolean equals2 = "..".equals(trim);
                boolean equals3 = Marker.ANY_MARKER.equals(trim);
                if (equals) {
                    collection2 = singleton(tagNode2);
                } else if (equals2) {
                    TagNode parent = tagNode2.getParent();
                    collection2 = parent != null ? singleton(parent) : new ArrayList();
                } else {
                    collection2 = equals3 ? tagNode2.getChildTagList() : tagNode2.getElementListByName(trim, false);
                }
                LinkedHashSet linkedHashSet2 = new LinkedHashSet(collection2);
                Collection evaluateAgainst = evaluateAgainst(linkedHashSet2, i + 1, i2, false, i4, linkedHashSet2.size(), z2, (Collection) null);
                if (z) {
                    List<TagNode> childTagList = tagNode2.getChildTagList();
                    if (equals || equals2 || equals3) {
                        linkedHashSet.addAll(evaluateAgainst);
                    }
                    for (TagNode next4 : childTagList) {
                        Collection elementsByName = getElementsByName(singleton(next4), i, i2, z, z2);
                        if (!equals && !equals2 && !equals3 && evaluateAgainst.contains(next4)) {
                            linkedHashSet.add(next4);
                        }
                        linkedHashSet.addAll(elementsByName);
                    }
                } else {
                    linkedHashSet.addAll(evaluateAgainst);
                }
                i3 = i4;
            } else {
                throwStandardException();
            }
        }
        return linkedHashSet;
    }

    private boolean evaluateLogic(Collection collection, Collection collection2, String str) {
        if (collection == null || collection.size() == 0 || collection2 == null || collection2.size() == 0) {
            return false;
        }
        Object next = collection.iterator().next();
        Object next2 = collection2.iterator().next();
        if (!(next instanceof Number) || !(next2 instanceof Number)) {
            int compareTo = toText(next).compareTo(toText(next2));
            if ("=".equals(str)) {
                if (compareTo == 0) {
                    return true;
                }
                return false;
            } else if ("<".equals(str)) {
                if (compareTo < 0) {
                    return true;
                }
                return false;
            } else if (">".equals(str)) {
                if (compareTo > 0) {
                    return true;
                }
                return false;
            } else if ("<=".equals(str)) {
                if (compareTo <= 0) {
                    return true;
                }
                return false;
            } else if (!">=".equals(str) || compareTo < 0) {
                return false;
            } else {
                return true;
            }
        } else {
            double doubleValue = ((Number) next).doubleValue();
            double doubleValue2 = ((Number) next2).doubleValue();
            if ("=".equals(str)) {
                if (doubleValue == doubleValue2) {
                    return true;
                }
                return false;
            } else if ("<".equals(str)) {
                if (doubleValue < doubleValue2) {
                    return true;
                }
                return false;
            } else if (">".equals(str)) {
                if (doubleValue > doubleValue2) {
                    return true;
                }
                return false;
            } else if ("<=".equals(str)) {
                if (doubleValue <= doubleValue2) {
                    return true;
                }
                return false;
            } else if (!">=".equals(str) || doubleValue < doubleValue2) {
                return false;
            } else {
                return true;
            }
        }
    }

    private String toText(Object obj) {
        if (obj == null) {
            return "";
        }
        if (obj instanceof TagNode) {
            return ((TagNode) obj).getText().toString();
        }
        return obj.toString();
    }
}

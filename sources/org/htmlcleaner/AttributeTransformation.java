package org.htmlcleaner;

public interface AttributeTransformation {
    String getTemplate();

    boolean satisfy(String str, String str2);
}

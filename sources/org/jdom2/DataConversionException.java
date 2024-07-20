package org.jdom2;

public class DataConversionException extends JDOMException {
    private static final long serialVersionUID = 200;

    public DataConversionException(String str, String str2) {
        super("The XML construct " + str + " could not be converted to a " + str2);
    }
}

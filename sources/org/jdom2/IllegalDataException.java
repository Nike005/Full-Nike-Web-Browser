package org.jdom2;

public class IllegalDataException extends IllegalArgumentException {
    private static final long serialVersionUID = 200;

    IllegalDataException(String str, String str2, String str3) {
        super("The data \"" + str + "\" is not legal for a JDOM " + str2 + ": " + str3 + ".");
    }

    IllegalDataException(String str, String str2) {
        super("The data \"" + str + "\" is not legal for a JDOM " + str2 + ".");
    }

    public IllegalDataException(String str) {
        super(str);
    }
}

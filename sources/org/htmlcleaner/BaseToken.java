package org.htmlcleaner;

import java.io.IOException;
import java.io.Writer;

public interface BaseToken {
    int getCol();

    int getRow();

    void serialize(Serializer serializer, Writer writer) throws IOException;

    void setCol(int i);

    void setRow(int i);
}

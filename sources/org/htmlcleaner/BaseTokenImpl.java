package org.htmlcleaner;

public abstract class BaseTokenImpl implements BaseToken {
    private int col;
    private int row;

    protected BaseTokenImpl() {
    }

    protected BaseTokenImpl(int i, int i2) {
        this.row = i;
        this.col = i2;
    }

    public int getRow() {
        return this.row;
    }

    public void setRow(int i) {
        this.row = i;
    }

    public int getCol() {
        return this.col;
    }

    public void setCol(int i) {
        this.col = i;
    }

    public String toString() {
        return "(line=" + getRow() + ", col=" + getCol() + ")";
    }
}

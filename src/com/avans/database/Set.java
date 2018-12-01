package com.avans.database;

public class Set {

    protected final Column column;
    protected final String value;

    public Set(Column column, boolean value) {
        this(column, value ? "1" : "0");
    }

    public Set(Column column, int value) {
        this(column, String.valueOf(value));
    }

    public Set(Column column, long value) {
        this(column, String.valueOf(value));
    }

    public Set(Column column, String value) {
        this.column = column;
        this.value = value;
    }

    /* GETTERS */
    public Column getColumn() {
        return column;
    }

    public String getValue() {
        return value;
    }

    /* OVERRIDABLE */
    @Override
    public String toString() {
        return "`" + column + "`='" + value + "'";
    }
}

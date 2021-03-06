package com.avans.database;

/*
    Created By Robin Egberts On 12/18/2018
    Copyrighted By OrbitMines ©2018
*/

public class Where<T> {

    private final Operator operator;
    private T[] values;
    private Column column;

    public Where(Column column, T value) {
        this(Operator.EQUALS, column, value);
    }

    @SafeVarargs
    public Where(Operator operator, Column column, T... value) {

        if (value.length == 0)
            throw new IllegalArgumentException("Cannot search on 0 values!");


        if (value.length > 1 && operator != Operator.IN)
            throw new IllegalArgumentException("Cannot search into an array of values without IN operator!");

        this.values = value;
        this.column = column;
        this.operator = operator;
    }

    /* OVERRIDABLE */
    @Override
    public String toString() {
        StringBuilder values = new StringBuilder();

        if (this.values.length > 1)
            values.append(" (");

        for (int i = 0; i < this.values.length; i++) {
            if (i != 0)
                values.append(",");

            values.append(this.values[i]);
        }

        if (this.values.length > 1)
            values.append(")");

        return String.format("%s.%s%s'%s'", Table.getTable(column), column, operator.getOperator(), values.toString());
    }

    /* SUB ENUM */
    public enum Operator {

        EQUALS("="),
        GREATER_THAN(">"),
        GREATER_THAN_OR_EQUAL(">="),
        IS(" IS "),
        IS_NOT(" IS NOT "),
        LESSER_THAN("<"),
        LESSER_THAN_OR_EQUAL("<="),
        LIKE(" LIKE "),
        NOT_EQUAL("!="),
        IN(" IN ");

        private final String operator;

        Operator(String operator) {
            this.operator = operator;
        }

        public String getOperator() {
            return operator;
        }
    }
}

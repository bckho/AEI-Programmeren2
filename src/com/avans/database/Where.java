package com.avans.database;

public class Where extends Set {

    private final Operator operator;

    public Where(Column column, boolean value) {
        this(Operator.EQUALS, column, value);
    }

    public Where(Column column, int value) {
        this(Operator.EQUALS, column, value);
    }

    public Where(Column column, long value) {
        this(Operator.EQUALS, column, value);
    }

    public Where(Column column, String value) {
        this(Operator.EQUALS, column, value);
    }

    public Where(Operator operator, Column column, boolean value) {
        super(column, value);

        this.operator = operator;
    }

    public Where(Operator operator, Column column, int value) {
        super(column, value);

        this.operator = operator;
    }

    public Where(Operator operator, Column column, long value) {
        super(column, value);

        this.operator = operator;
    }

    public Where(Operator operator, Column column, String value) {
        super(column, value);

        this.operator = operator;
    }

    /* OVERRIDABLE */
    @Override
    public String toString() {
        return String.format("`%s`%s'%s'", column, operator.getOperator(), value);
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
        NOT_EQUAL("!=");

        private final String operator;

        Operator(String operator) {
            this.operator = operator;
        }

        public String getOperator() {
            return operator;
        }
    }
}
package com.avans.database.tables;

/*
    Created By Robin Egberts On 12/23/2018
    Copyrighted By OrbitMines ©2018
*/

import com.avans.database.Column;
import com.avans.database.ColumnKey;
import com.avans.database.Constraint;
import com.avans.database.Table;

public class ProgramTable extends Table {

    public static final ColumnKey ID;

    public static final Column TITLE;
    public static final Column DURATION;

    static {
        ID = new ColumnKey("ID", Column.Type.INT, ColumnKey.Key.PRIMARY);

        TITLE = new Column("Title", Column.Type.VARCHAR, 50);
        DURATION = new Column("Duration", Column.Type.INT);
    }

    public ProgramTable() {
        super("Program", ID, TITLE, DURATION);

        this.addConstraint(new Constraint("Table", Constraint.Type.PRIMARY, ID));
    }
}
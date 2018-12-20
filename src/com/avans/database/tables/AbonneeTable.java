package com.avans.database.tables;

import com.avans.database.Column;
import com.avans.database.ColumnKey;
import com.avans.database.Constraint;
import com.avans.database.Table;

/*
    Created By Robin Egberts On 12/18/2018
    Copyrighted By OrbitMines ©2018
*/

public class AbonneeTable extends Table {

    public static final ColumnKey NAME;

    public static final Column LAST_NAME;
    public static final Column STREET;
    public static final Column HOUSE_NUMBER;
    public static final Column POSTCODE;
    public static final Column CITY;

    static {
        /* initialisation of primary keys */
        NAME = new ColumnKey("Name", Column.Type.VARCHAR, ColumnKey.Key.PRIMARY, 50);

        /* initialisation of columns */
        LAST_NAME = new Column("LastName", Column.Type.VARCHAR, 50);
        STREET = new Column("Street", Column.Type.VARCHAR, 50);
        HOUSE_NUMBER = new Column("HouseNumber", Column.Type.TINYINT);
        POSTCODE = new Column("Postcode", Column.Type.VARCHAR, 6);
        CITY = new Column("City", Column.Type.VARCHAR, 50);

        /* initialisation of foreign keys if any!*/

        /* initialisation of responses! */
    }

    public AbonneeTable() {
        super("Abonnee", NAME, LAST_NAME, STREET, HOUSE_NUMBER, POSTCODE, CITY);

        this.addConstraint(new Constraint("Abonnee", Constraint.Type.PRIMARY, NAME));
    }
}

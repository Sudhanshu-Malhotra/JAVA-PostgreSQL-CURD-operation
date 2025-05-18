package com.company;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DbFunctions db = new DbFunctions();
        Connection con = db.connect("tdb","postgres","Password");
        //db.createTable(con, "employee");
        //db.insert_row(con, "employee", "Sam", "India");
        db.delete_row_by_id(con,"employee",5);
        db.read_data(con, "employee");
        //db.update_name(con,"employee", "Rohan","Sam");
        //db.search_by_name(con, "employee", "Sam");
//        db.search_by_id(con,"employee", 2);
//        db.delete_row_by_name(con,"employee","Rohan");

    }

}

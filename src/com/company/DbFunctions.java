package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbFunctions {
    public Connection connect(String dbname, String user, String pass) {
        Connection con = null;
        try{
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+dbname,user,pass);
            if(con!=null){
                System.out.println("Connected to database");
            }
            else{
                System.out.println("Not connected to database");
            }

        }catch(Exception e){
            System.out.println(e);
        }
        return con;
    }
    public void createTable(Connection con, String tableName) {
        Statement statement;
        try{
            String querry = "CREATE TABLE IF NOT EXISTS "+tableName+"(empid SERIAL,name varchar(200), address varchar(200),primary key(empid));";
            statement = con.createStatement();
            statement.executeUpdate(querry);
            System.out.println("Table created");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void insert_row(Connection con,String table_name,String name, String address) {
        Statement statement;
        try{
            String querry = String.format("insert into %s(name,address) values('%s','%s')",table_name,name,address);
            statement = con.createStatement();
            statement.executeUpdate(querry);
            System.out.println("Row inserted");

        }catch(Exception e){
            System.out.println(e);
        }
    }
    public void read_data(Connection con,String table_name) {
        Statement statement;
        try{
            String querry = String.format("select * from %s", table_name);
            statement = con.createStatement();
            statement.executeQuery(querry);
            ResultSet rs = statement.executeQuery(querry);
            while(rs.next()){
                System.out.print(rs.getString("empid") + " ");
                System.out.print(rs.getString("name") + " ");
                System.out.println(rs.getString("address") + " ");
            }

        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void update_name(Connection con,String table_name, String old_name, String new_name){
        Statement statement;
        try{
            String querry = String.format("update %s set name = '%s' where name = '%s'", table_name, new_name, old_name);
            statement = con.createStatement();
            statement.executeUpdate(querry);
            System.out.println("Name updated");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public void search_by_name(Connection con,String table_name,String name) {
        Statement statement;
        try {
            String querry = String.format("select * from %s where name = '%s'", table_name, name);
            statement = con.createStatement();
            statement.executeQuery(querry);
            ResultSet rs = con.createStatement().executeQuery(querry);
            while (rs.next()) {
                System.out.print(rs.getString("empid") + " ");
                System.out.print(rs.getString("name") + " ");
                System.out.println(rs.getString("address") + " ");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public void search_by_id(Connection con,String table_name,int id){
            Statement statement;
            try{
                String querry = String.format("select * from %s where empid = %s", table_name, id);
                statement = con.createStatement();
                statement.executeQuery(querry);
                ResultSet rs = con.createStatement().executeQuery(querry);
                while(rs.next()){
                    System.out.print(rs.getString("empid") + " ");
                    System.out.print(rs.getString("name") + " ");
                    System.out.println(rs.getString("address") + " ");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
    }

    public void delete_row_by_name(Connection con,String table_name,String name){
        Statement statement;
        try{
            String querry = String.format("delete from %s where name = '%s'", table_name, name);
            statement = con.createStatement();
            statement.executeUpdate(querry);
            System.out.println("Row deleted");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void delete_row_by_id(Connection con,String table_name,int id){
        Statement statement;
        try{
            String querry = String.format("delete from %s where empid = %s", table_name, id);
            statement = con.createStatement();
            statement.executeUpdate(querry);
            System.out.println("Row deleted");
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void delete_table(Connection con,String table_name){
        Statement statement;
        try{
            String querry = String.format("drop table if exists %s", table_name);
            statement = con.createStatement();
            statement.executeUpdate(querry);
            System.out.println("Table dropped");
        }catch (Exception e){
            System.out.println(e);
        }
    }
}

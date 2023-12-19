package com.java.features.jdbc;
import java.sql.*;

import org.apache.commons.compress.compressors.snappy.FramedSnappyDialect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ScrollableResultSet {
    private static final Logger logger = LoggerFactory.getLogger(ScrollableResultSet.class);
    public static void scrollRs(){
        try(Connection conn = JdbcCRUDStatements.createConnection();
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE)){
            conn.setAutoCommit(false);
            String selectStr = "SELECT * FROM books";
            ResultSet rs = stmt.executeQuery(selectStr);
            System.out.println("-----Last Row---");
            rs.last();
            System.out.println(rs.getRow() +":"+rs.getInt(1)+","+
                    rs.getString("title")+","+rs.getString("author")+","+
                    rs.getDouble("price")+","+rs.getInt("qty"));

            System.out.println("-----Absolute Row 3------");
            rs.absolute(3);
            System.out.println(rs.getRow() +":"+rs.getInt(1)+","+
                    rs.getString("title")+","+rs.getString("author")+","+
                    rs.getDouble("price")+","+rs.getInt("qty"));
            System.out.println("------Relative Row 3");
            rs.relative(3);
            System.out.println(rs.getRow() +":"+rs.getInt(1)+","+
                    rs.getString("title")+","+rs.getString("author")+","+
                    rs.getDouble("price")+","+rs.getInt("qty"));
            System.out.println("------Relative Row -3 -----");
            rs.relative(-3);
            System.out.println(rs.getRow() +":"+rs.getInt(1)+","+
                    rs.getString("title")+","+rs.getString("author")+","+
                    rs.getDouble("price")+","+rs.getInt("qty"));

            System.out.println("------All Rows-----");
            rs.beforeFirst();
            System.out.println(rs.getRow() +":"+rs.getInt(1)+","+
                    rs.getString("title")+","+rs.getString("author")+","+
                    rs.getDouble("price")+","+rs.getInt("qty"));


        }
        catch (SQLException ex){
            logger.error("Error ",ex);
            //conn.rollback();
        }
    }
    public static void updateRS(){
        try(Connection conn = JdbcCRUDStatements.createConnection();
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE,ResultSet.CLOSE_CURSORS_AT_COMMIT)) {
            conn.setAutoCommit(false);
            ResultSet rs = stmt.executeQuery("SELECT * FROM books");
            //1.Update last row {price and quantity}
            rs.last();
            rs.updateDouble("price", 90.00);
            rs.updateInt(5, 89);
            System.out.println("Update last row");
            System.out.println(rs.getRow() + ":" + rs.getInt(1) + "," +
                    rs.getString("title") + "," + rs.getString("author") + "," +
                    rs.getDouble("price") + "," + rs.getInt("qty"));
            //Delete first Row
            rs.beforeFirst();
            System.out.println(rs.getRow() + ":" + rs.getInt(1) + "," +
                    rs.getString("title") + "," + rs.getString("author") + "," +
                    rs.getDouble("price") + "," + rs.getInt("qty"));

            rs.deleteRow();
            //Insert a row
            rs.moveToInsertRow();
            rs.updateInt(1, 5001);
            rs.updateString("title", "Even More Programming");
            rs.updateString("author", "Kumar ");
            rs.updateDouble("price", 67.00);
            rs.updateInt("qty", 150);
            rs.beforeFirst();
            ResultSetMetaData rsmd = rs.getMetaData();
            int numCols= rsmd.getColumnCount();
            while (rs.next()){
                for (int i = 1; i <=numCols ; i++) {
                    System.out.printf("%-30s",rs.getString(i));
                }
                System.out.println();
            }
        }
         catch (SQLException ex){
            logger.error("Error",ex);
            //conn.rollback();
        }
    }
}

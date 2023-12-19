package com.java.features.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.spark.sql.catalyst.expressions.StringDecode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class JdbcCRUDStatements {
    private static final Logger logger = LoggerFactory.getLogger(JdbcCRUDStatements.class);
    static Connection createConnection() throws SQLException {
       Connection conn=null;
       Statement stmt = null;
        try{
            conn = DriverManager.getConnection("" +
                "jdbc:mysql://localhost:3306/ebookshop?allowPublicKeyRetrieval=true&useSSL=false&severTimezone=UTC",
                "myuser","xxxx");
            stmt = conn.createStatement();

                    String selectStmt = "SELECT id,title,author,price,qty FROM books";
            ResultSet rs = stmt.executeQuery(selectStmt);
            int cnt=0;
            System.out.println("Contents of book table \t");
            while (rs.next()){
                String title =rs.getString("title");
                var author =rs.getString(3);
                double price =rs.getDouble("price");
                int qty = rs.getInt(5);
                ++cnt;
            }
            System.out.println("Total number of records :"+cnt);
            ResultSetMetaData rsmd = rs.getMetaData();
            /***Print column names & column contents ***/
            int numCols = rsmd.getColumnCount();
            for (int i = 1; i <=numCols ; i++) {
                System.out.printf("%-30s","("+rsmd.getColumnName(i));
            }
            System.out.println();
            while (rs.next()){
                for (int i = 1; i <=numCols ; i++) {
                    System.out.printf("%-30s",rs.getString(i));
                }
                System.out.println();
            }
        }
        catch (SQLException ex){
            logger.error("Error ",ex);
        }
        finally {
            if (stmt !=null){
               stmt.close();
            }
            if (conn !=null){
                conn.close();
            }
        }
        return conn;
    }

    public static void statements() throws SQLException{
        try(Connection conn= createConnection();
           Statement stmt = conn.createStatement()){
            /****
             * 1.Execute a SQL UPDATE via executeUpdate()->int
             * Increase the price by 7% & qty by 1 for id=1001
             ******/
            String updStr ="UPDATE books SET price=price*1.07 ,qty=qty+1 WHERE id=1001";
            System.out.println("1.Executing the following statement \n:"+updStr);
            int updCount = stmt.executeUpdate(updStr);
            System.out.println(updCount +" records affected");
            /******
             * 2.Execute a SQL INSERT via executeUpdate()->int
             *  Insert 1 record, multiple records and a partial record
             *****/
            //(i) Insert a single row
            String insertStr = "INSERT INTO books VALUES (3001, 'Gone Fishing', 'Kumar', 11.11, 11)";
            int insCnt = stmt.executeUpdate(insertStr);
            System.out.printf("Executing the following statement :%n %s %n",insertStr);
            System.out.println(insCnt+ "rows affected");
            //(ii)Insert multiple records
            insertStr= "INSERT INTO books VALUES (3002, 'Gone Fishing 2', 'Kumar', 22.22, 22),"
                    + "(3003, 'Gone Fishing 3', 'Kumar', 33.33, 33)";
            insCnt = stmt.executeUpdate(insertStr);
            System.out.println("The statement is :\n"+insertStr);
            System.out.println(insCnt +" rows inserted");
            //(iii)Insert a partial record
            insertStr ="INSERT INTO books (id,title,author) VALUES (3004, 'Fishing 101', 'Kumar')";
            insCnt = stmt.executeUpdate(insertStr);
            System.out.println("The statement is :\n"+insertStr);
            System.out.println(insCnt+" rows affected");
            /***3.Execute an SQL DELETE via stmt.executeUpdate()->int
             * Delete records with id >=3000
             ****/
            String deleteStr = "DELETE FROM books WHERE id >=3000 AND id<4000";
            int delCnt = stmt.executeUpdate(deleteStr);
            System.out.println("The statement is : \n"+deleteStr);
            System.out.println(delCnt+ " records deleted");
            /****4.Issue an SQL SELECT via executeQuery()->ResultSet
             *
             */
            String selectStr = "SELECT * FROM books";
            ResultSet rs = stmt.executeQuery(selectStr);
            while (rs.next()){
                System.out.println(rs.getInt(1)+","+rs.getString("author")+","+
                        rs.getString("title")+","+rs.getDouble("price")+","+
                        rs.getInt("qty"));
            }
        }
        catch (SQLException ex){
            logger.error("Error in CRUD operation",ex);
        }
    }

    public static void batchProcessing(){
        try(Connection conn =createConnection();
           Statement stmt = conn.createStatement()) {
            /****************************************************************
             * Issue 2 SQL INSERT & 1 SQL UPDATE & add them to the batch
             *
             ***************************************************************/
            String insertStr = "INSERT INTO books VALUES (8001, 'Java ABC', 'Kevin Jones', 1.1, 99)";
            String updStr = "UPDATE books SET price =11.1 WHERE id=8001 OR id=8002";
            stmt.addBatch(insertStr);
            stmt.addBatch("INSERT INTO books VALUES (8002, 'Java XYZ', 'Kevin Jones', 1.1, 99)");
            stmt.addBatch(updStr);
            int[] returnCodes = stmt.executeBatch();
            for (int code:returnCodes){
                System.out.println(code+" ,");
            }
            System.out.println();
        }

        catch (SQLException ex){
            logger.error("SQL Error ",ex);
        }
    }

}

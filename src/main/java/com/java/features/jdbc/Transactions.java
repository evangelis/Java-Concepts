package com.java.features.jdbc;
import org.slf4j.ILoggerFactory;

import java.sql.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
public class Transactions {
    private final static Logger logger =LoggerFactory.getLogger(Transactions.class);
    public static void groupStatements(){
        try(Connection conn = JdbcCRUDStatements.createConnection();
            Statement stmt = conn.createStatement()){
            conn.setAutoCommit(false);
            /***1 Before changes :Issue a SQL SELECT
             *
             */
            ResultSet rs = stmt.executeQuery("SELECT id,qty FROM books WHERE id IN (1001,1002");
            System.out.println("Before Update :");
            while (rs.next()){
                System.out.println(rs.getInt(1)+","+rs.getInt("qty"));
            }
            conn.commit();
            /********************************************************
             * 2.Issue 2 SQL UPDATE statements
             *  Commit and then issue 2 more SQL UPDATE statements
             *********************************************************/
            stmt.executeUpdate("UPDATE books SET qty= qty+30 WHERE id =1001");
            stmt.executeUpdate("UPDATE books SET qty=qty+100 WHERE id=1002");
            conn.commit();

            stmt.executeUpdate("UPDATE books SET qty= qty-10 WHERE id =1001");
            stmt.executeUpdate("UPDATE books SET qty= qty-10 WHERE id =1001");
            conn.commit();

            rs = stmt.executeQuery("SELECT id,qty WHERE id IN (1001,1002");
            System.out.println("After the (4) updates");
            while (rs.next()){
                System.out.println(rs.getInt(1)+","+rs.getInt("qty"));
            }
            conn.commit();
            /******
             * 3.Issue a DELETE stmt & roll back
             *
             */
            stmt.executeUpdate("DELETE FROM books WHERE id =1001");
            conn.rollback();

        }
        catch (SQLException ex){
            logger.error("Error",ex);
            conn.rollback();
        }
    }
}

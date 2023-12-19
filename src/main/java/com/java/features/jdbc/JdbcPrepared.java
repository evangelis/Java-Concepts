package com.java.features.jdbc;
import java.sql.*;
import org.slf4j.*;
public class JdbcPrepared {
    private static final Logger logger = LoggerFactory.getLogger(JdbcPrepared.class.getName());

    public static void runPreparedStmt() throws SQLException {
        try (Connection conn = JdbcCRUDStatements.createConnection();
             PreparedStatement pstmtInsert = conn.prepareStatement(
                     "INSERT INTO books VALUES (?,?,?,?,?)")) { //five parameters
            pstmtInsert.setInt(1, 7001);
            pstmtInsert.setString(2, "Mahjong 101");
            pstmtInsert.setString(3, "Kumar");
            pstmtInsert.setDouble(4, 88.8);
            pstmtInsert.setInt(5, 150);
            int insertCnt = pstmtInsert.executeUpdate();
            System.out.println(insertCnt + " rows inserted");
            //change values for parameters 1& 2 only
            pstmtInsert.setInt(1, 7002);
            pstmtInsert.setString(2, "Mahjong 102");
            insertCnt = pstmtInsert.executeUpdate();
            System.out.println(insertCnt + " rows affected");
        } catch (SQLException ex) {
            logger.error("SQL exception ",ex);
        }
    }
    public static void batchPrepare(){
        try(Connection conn = JdbcCRUDStatements.createConnection();
           PreparedStatement pstmtInsert = conn.prepareStatement(
                   "INSERT INTO books VALUES (?,?,?,?,?)");){
            conn.setAutoCommit(false);
            pstmtInsert.setInt(1, 8003);
            pstmtInsert.setString(2,"Java 123");
            pstmtInsert.setString(3,"Kevin Jones");
            pstmtInsert.setDouble(4, 55.7);
            pstmtInsert.setInt(5,88);
            pstmtInsert.addBatch();

            pstmtInsert.setInt(1, 8004);
            pstmtInsert.setString(2,"Java Advanced II");
            pstmtInsert.setString(3,"Kevin Jones");
            pstmtInsert.setDouble(4, 46.50);
            pstmtInsert.setInt(5,120);
            pstmtInsert.addBatch();

            pstmtInsert.setInt(1, 8005);
            pstmtInsert.setString(2,"SQL  123");
            pstmtInsert.setString(3,"Andrew Matte");
            pstmtInsert.setDouble(4, 30.00);
            pstmtInsert.setInt(5,1500);
            pstmtInsert.addBatch();

            pstmtInsert.setInt(1, 8006);
            pstmtInsert.setString(2,"Python 123");
            pstmtInsert.setString(3,"Kevin Jones");
            pstmtInsert.setDouble(4, 38.00);
            pstmtInsert.setInt(5,130);
            pstmtInsert.addBatch();

           int[] returnCodes = pstmtInsert.executeBatch();
            System.out.println("Return codes are :");
            for (int code :returnCodes)
                System.out.print(code +" ,");
            System.out.println();
            //commit changes
            conn.commit();
        }
        catch (SQLException ex){
            logger.error("Error",ex);
        }

    }
}


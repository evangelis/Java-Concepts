package com.java.features.jdbc;
import java.sql.*;
import javax.sql.rowset.*;

import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.kafka.connect.runtime.Connect;
import org.slf4j.*;
import java.util.Properties;
public class RowSets {
    private static final Logger logger = LoggerFactory.getLogger(RowSets.class);
    private final static String dbUrl = "jdbc:mysql/localhost:8088/ebookshop";
    private static Properties props(){
        String user ="myuser";
        String pwd ="xxxx";
        props().put("username",user);
        props().put("password",pwd);
        return props();
    }
    public static void jdbcRowSet(){
        RowSetFactory rsf = null;
        try {
            rsf = RowSetProvider.newFactory();
        }
        catch (SQLException exc){
            logger.error("Error",exc);
        }
        try(JdbcRowSet jrs = rsf.createJdbcRowSet();) {
            jrs.setUrl(dbUrl);
            jrs.setUsername(props().getProperty("username"));
            jrs.setPassword(props().getProperty("password"));
            jrs.setCommand("SELECT * FROM books");
            jrs.execute();
            System.out.println("Update last Row");
            jrs.last();
            //Update last row
            jrs.updateDouble("price", 64.90);
            jrs.updateInt(5, 90);
            //Delete 1st row
            jrs.first();
            System.out.println("-----Delete first Row");
            System.out.println(jrs.getRow()+":"+ jrs.getInt(1)+","+jrs.getString("title")+","+
                    jrs.getString("author")+","+jrs.getDouble("price")+","+jrs.getInt("qty"));

            jrs.deleteRow();
            //Insert a row
            System.out.println("Insert a Row");
            jrs.moveToInsertRow();
            jrs.updateInt(1,3701);
            jrs.updateString("title","Advanced Programming Techniques");
            jrs.updateString("author","Kumar");
            jrs.updateDouble("price",85);
            jrs.updateInt("qty",130);
            jrs.insertRow();
            jrs.beforeFirst();
            ResultSetMetaData rsmd = jrs.getMetaData();
            int numCols = rsmd.getColumnCount();
            while (jrs.next()){
                for (int i = 1; i <=numCols ; i++) {
                    System.out.printf("%-30s",jrs.getString(i));
                }
            }
        }

        catch (SQLException ex){
            logger.error("Error",ex);
        }
    }
    public static void cachedRowSet(){
        Connection conn =null;
        CachedRowSet crs = null;
        RowSetFactory rsF = null;
        try{
            conn = DriverManager.getConnection(dbUrl,props());
            conn.setAutoCommit(false);
            rsF =RowSetProvider.newFactory();
            crs = rsF.createCachedRowSet();
            crs.setUrl(dbUrl);
            crs.setUsername(props().getProperty("username"));
            crs.setPassword(props().getProperty("password"));
            crs.setCommand("SELECT * FROM books");

            //Update 1st row
            crs.beforeFirst();
            System.out.println("-----Update first Row");
            System.out.println(crs.getRow()+":"+ crs.getInt(1)+","+crs.getString("title")+","+
                    crs.getString("author")+","+crs.getDouble("price")+","+crs.getInt("qty"));
            crs.updateDouble("price",88);
            crs.updateInt(5,50);
            crs.updateRow();
            System.out.println(crs.getRow()+":"+ crs.getInt(1)+","+crs.getString("title")+","+
                    crs.getString("author")+","+crs.getDouble("price")+","+crs.getInt("qty"));
           //Insert a row
            crs.moveToInsertRow();
            crs.updateInt(1,6001);
            crs.updateString("title","More Programming II");
            crs.updateString(3,"Kumar");
            crs.updateDouble(4,65.30);
            crs.updateInt(5,130);
            crs.insertRow();

                //Need to move away from the inserted row before applying changes
            crs.moveToCurrentRow();
            crs.acceptChanges();

        }
        catch(SQLException exc){
            logger.error("Error",exc);
        }
        finally {
            try{
                if(crs != null) crs.close();
                if(conn != null) conn.close();
            }catch (SQLException ex){
                logger.error("Error",ex);
            }
        }
    }
}

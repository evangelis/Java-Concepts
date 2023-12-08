package com.java.features;
import org.datanucleus.asm.Handle;

import java.io.OutputStream;
import java.util.logging.*;
import java.io.IOException;
import java.io.PrintStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class SimpleLogger {
    public static final Logger logger = Logger.getLogger(SimpleLogger.class.getName());
    public static void printLogInfo(){
        System.out.printf("The logger's level is:%s,and the logger filter is: %s %n" +
                "Parent logger classname is: %s ",logger.getLevel(),logger.getFilter(),
                logger.getParent().getName());
        Logger rootLogger = Logger.getLogger("");
        System.out.printf("Root logger's level is :%s, and filter is :%s",
                rootLogger.getLevel(),rootLogger.getFilter());

            Handler[] fhandlers = rootLogger.getHandlers();
            for (Handler fh : fhandlers)
                System.out.printf("Handler is :%s, Handler's filter is :%s %n ," +
                                "Handler's level is :%s, Handler's formatter is :%s",
                        fh, fh.getFilter(), fh.getLevel(), fh.getFormatter());
    }
    public static void rotatingFiles(){
        /****Construct a set of rotating files
         * %t :is for system's /tmp dir
         * %g: denotes the generation number
         */
        try {
            Handler fh = new FileHandler("%t/test%g.log", 1024, 3, true);
            fh.setFormatter(new SimpleFormatter());
            Logger.getLogger("").addHandler(fh);
            for (int i = 0; i < 100;i++) {
                logger.info("Testing log message :" + i);
            }

        }
        catch (IOException ex){
            logger.severe(ex.getMessage());
        }
    }

    public static void logFile() throws IOException{
        //Set a File Handler ,simulate exceptions and log messages
        String fname = "/home/vangelis/Desktop/Java-Concepts/src/main/resources/%g/ttest.log";
        Handler fh = new FileHandler(fname,true);
        fh.setFormatter(new SimpleFormatter());

        logger.addHandler(fh);
        logger.setLevel(Level.FINE);
        try{
            throw new Exception("Simulating an Exception");
        }catch (Exception ex){
            logger.log(Level.SEVERE,ex.getLocalizedMessage());
        }
        logger.config("A config message");
        logger.finest("A finest message");//it is below logger's level
        logger.info("An info message");
        fh.flush();
        fh.close();
    }
    public static void logRedirect() throws IOException{
        /***
         *Redirecting System.err and System.out to log files :
         *      System.setErr(PrintStream),System.setOut(PrintStream)
         * Set logger level to produce logs at FINE level and above
         *Construct a file handler and add it to the logger
         * Redirect System.err and System.out
         * Simulate exception & log some messages of various levels
         *
        ***/
        logger.setLevel(Level.FINE);
        Handler fh = new FileHandler("test.log",true);
        logger.addHandler(fh);
        PrintStream ps = new PrintStream(new BufferedOutputStream(
                new FileOutputStream("~/Desktop/Java-Concepts/src/main/resources/test.log",true)));
        System.setErr(ps);
        System.setOut(ps);
        try{
            throw new Exception("Simulating an Exception");

        }
        catch (Exception ex){
            logger.log(Level.SEVERE,ex.getMessage());
        }
        logger.info("An info message");
        logger.finer("A finer message");
        logger.config("A cofnig message");
        System.out.println("Writing to System.out");
        System.err.println("Writing to System.err");
    }

}


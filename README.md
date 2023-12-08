 I.Java Logging Framework
 A.package java.util.logging 
  
  class Logger:The main entity;we typically use 1 logger per class
    //Methods
    public void severe(String msg),warning(String msg),info(String msg),config(String msg),
            fine(String msg),finer(String msg),finest(String msg)
            setLevel(Level.xxx)
            log(Level lvl,String msg)
            logp()
            logrb()
  class Level:Logging levels are used to control the output
            [SEVER,WARNING,INFO,CONFIG,FINE,FINER,FINEST,OFF] 
       Level.SEVERE: A serious failure that prevents normal program execution
       Level.WARNING:Potential problems for end users and system admins
       Level.INFO:Information messages for end users and sys admins
       Level.CONFIG:Hardware configuration 
       Level.FINE, Level.FINER,Level.FINEST: 
  class Handler:Export LogRecords  (passed from Loggers) to various destinations
    We can assign a Level to the Handler to control what is written out to the destination
    The Handlers are:
        ConsoleHandler:Writing to Syste.err
        FileHandler:Writing to a single,or multiple rotating log files
        MemoryHandler:>> to memory buffers
        SocketHandler:Writing to a TCP port
        StreamHandler:Writing to an OutputStream
  class Formatter:[SimpleFormatter,XMLFormatter]
    The Handler passes the LogRecord to the (attached) Formatter to format the
    LogRecord into a String
    
  Loggers are organized in hierarchical tree structure;the root logger is denoted by an 
 empty string "" while usually they are named affter the fully qualified class name in which 
they are defined
 The root logger ,by default has a ConsoleHandler that writes to 
 B.org.slf4j Logging framework 
 It is a logging facade for diff logging frameworks in Java, offering a generic API
 SLF4J standardizes the logging levels ,which are different for the various implementations 
 (Log4j,Logback,java.util.logging etc)
 Logging Levels:[ERROR,WARN,INFO,DEBUG,TRACE]
  
    public class Slf4jExample{
       private static Logger logger= LoggerFactory.getLogger(Slf4jExample.class);
       public static void main(String[] args){
          String var = "Hello logging";
          logger.debug("Printing variable value :{}",var);
          logger.debug("A debug message");
          logger.info("An info message");
          logger.error("Error msg");  
       }
    }

     

  
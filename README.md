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

 II.Date & Time

    System.nanoTime() :Current value of the inner system timer in
       nanoseconds ,in long
    System.currentTimeMillis(): Current time in millis 
              since January 1,1970 00:00:00 GMT
  class java.util.Date  
   Most of its methods are deprecated
   Constructors :Date([long millissinceEpoch])
   //Methods 
    long getTime()
    
 3.package [java.text]
   abstract class DateFormat :Internationalization,formatting dates and times
   class NumberFormat :Used to format numbers and currencies for any Locale
   DateFormat# getDateTimeInstance([int dtStyle,int timeStyle,Locale loc])
             # getInstance() //DateFormat{FULL,LONG,MEDIUM,SHORT},defaults to SHORT
             #getDateInstance([int style,Locale loc])
             #getTimeInstance([int style,Locale loc])
   Parsing a string into a Date instance :
     
    DateFormat fmt = ...;
    Date dt = fmt.parse(dtString);
    
    String str =NumberFormat.getInstance().format(anumber);  
 NumberFormat # getInstance([Locale l]),getNumberInstance([Locale]),
              # getIntegerInstance([Locale ])
              # getCurrencyInstance([Locale]),getPercentInstance([Locale])
              

   
 4.package [java.time]
   {LocalDate,LocalDateTime,LocalTime,ZoneDateTime,Instant,Clock,Period,Duration}
   Notes:
   The API's methods have standardized prefixes.Consider class LocalDateTime :
   static LocalDateTime of(int y,{Month m|int m},int dom,int hr,int min,..int sec,int nano)
                        of(LocalDate,LocalTime)
               ofInstant(Instant ,ZoneId),ofEpochSecond(long sec,int nano ,ZoneOffset)  
               now(),now(Clock ),now(ZoneId)
               parse(CharSequence txt,..DateFormatter fmt)
   int getYear(),getDayOfMonth(),getDayOfYear(),getHour(),getMonthValue(),getMinute(), 
       getSecond(),getNano(),hashCode(),compareTo(ChronoLocalDateTime<?> other)
   Month getMonth()
   DayOfWeek getDayOfWeek()
   boolean isBefore(ChronoLocalDateTime<?> other),isAfter(ChronoLocalDateTime<?>),equals(Object)
          isEqual(ChronoLocalDateTime<?>), isSupported({TemporalUnit|TemporalField}) 
   String format(DateTimeFormatter fmt) 
   LocalDateTime plusYYY(long val),plus(long amt,TemporalUnit ),minus(long amt,TemporalUnit)
          minusYYY(long val) // YYY={days,years,months,hours,minutes,seconds,nanos,weeks}
          withYYY(int val)// YYY={month,year,nano,second,hour,dayOfYear,dayOfMonth}
          with(TemporalAdjuster ),with(TemporalField)
   LocalDate toLocalDate()
   LocalTime toLocalTime()
   
      
   [java.time.format]:Formatting and parsing dates and times
     class DateTimeFormatter :Print and parse date-time objects

    LocalDate dt = LocalDate.now();
    String text = dt.format(fmt);
    LocalDate parsedDate = LocalDate.parse(txt,fmt);
   
  

   [java.time.chrono]:Useful for calendar systems other than the default ISO-8601
   [java.time.temporal]: Interoperations between date and time classes
 
III.Basics OOP ,variables and initialization
 A.Scope & Lifetime & Types of variables 
 Scope :Refers to the portion of the code that can access the variable
 Lifetime:Refers to the span from the time the var is created in memory until is 
 garbage collected (destroyed).Available lifetimes:
   Automatic (Local) var:Include method parameters and methods' local variables.They are
created on entry to the method and destroyed when the method exits.Their scope is the method/block
 inside which they were defined.They cannot have access modifiers;only [final] is allowed
   Instance (member) vars:Are created when an instance is created and destroyed when the object
 is destroyed
   Static (class) vars:Are created when the class is loaded (by JVM class loader)and destroyed when
 the class is unloaded  
 Types of variables
   Primitives:[byte,boolean,char,short,int,long,float,double].They hold a simple value
   Reference type:[class,interface,enum,array].Reference type variables hold a reference to
                   an object or array
   Null type:Is assigned to a reference variable that does not reference an object
 Stack ,Heap & GC
  Primitives {boolean,byte,char,short,int,long,float,double} :Are created in the method stack 
 during compilation for efficiency:Fast access,less storage
  Reference types [objects,arrays]:Are created in the "Heap" at runtime via operator new & are 
 accessed via a reference.Heap is less efficient than the stack and requires allocation,management
  Objects are created via the new operator and the constructor.The new operator :
     Creates a new instance of the given class & allocates memory dynamically from the heap
     It then calls one of the overloaded constructors to initialize the object created and 
     Returns the reference.
  Automatic (local) vars of reference type :The reference is local,allocated in the method stack ,
 but the object is created in the heap
  Stack and Heap are located at the opposite ends of data memory ,facilitating expansion.

 1.Polymorphism:A reference variable can hold a reference of the type or its sub-type
 2.Java implicitly defines a reference type for each possible array type
 3.A static var (and method) belongs to the class and is shared by all instances .It is 
  referenced via [ClassName.staticVar] (ClassName.staticMethod() for methods).The purpose 
  of a "static" vars/methods is to provide constants and utility methods and a "global" var
  being applicable to all the instances of the class
   A non-static var belongs to a specific instance of a class
 4.Variable iniitialization:All class members & static variables that are not explicitly assigned 
 a value upon declaration they are assigned a default value:
     Integer types:{byte,short,int,long}:[0]
     Floating-point types:{float,double}:[0.0]
     Character type {char} :['\u0000']
     Booleans :{boolean} :[false]
     Reference types: {objects,arrays}:[null]
     Note that automatic vars must be initialized explicitly
 5.A "final" kew is applicable to classes,methods and variables:
     final variable:It cannot be re-assigned a new value
     final method cannot be overridden in subclasses
     final class cannot be subclassed or extended
 6."abstract" applies to classes and methods 
   An abstract class is one that defines at least 1 abstract  method.It cannot be instantiated
   while extending concrete classes can 
   An abstract method has no body;Concrete extending classes need to provide an implementation body
   overriding the abstract method in the superclass
 
  Initialization Process:
 Every JVM has a class loader [java.lang.ClassLoader] being responsible for loading classes into 
 the memory of a Java program.It searches the classpath foe the class file
 When a new object is created via operator new the sequence of events is :
  1.JVM allocates memory for the instance in the heap 
  2.JVM initializes the instance variables to their assigned values or default values  
  3.It then invokes the constructor;the 1st statement in the constructor is always a call to its
    immediate superclass constructor
  4.JVM executes the instance initializers in the order of appearance
  5.JVM executes the body of the constructor
  6.The new operator returns a reference to the newly created object
   
  Inheritance ,Composition,Polymorphism
  
 Inheritance:A mechanism in OOP that allows a new class (derived or subclass) to inherit properties &
behaviors (fields and methods) from an existing  class (parent or super class)  
   Derived subclasses can reuse & extend the functionality of the super class ,creating a class hierarchy
   and promoting code re-usability
   A subclass object is-a superclass object [is-a relationship]
   Java supports only single inheritance ,ie a subclass can only be derived from a single superclass
 Composition is a design mechanism in OOP where a class is composed of objects of other classes rather 
than inheriting from a superclass.Objects of different types are combined to achieve more complex behavior
  In composition a class retains references to other classes ,known as [has-a relationship]
 Polymorphism:Is the ability of derived type objects to be treated as objects of the base class
 It is achieved through [compile-time polymorphism,runtime polymorphism]
   Compile-time polymorphism [static polymorphism]:It is achieved through [method overloading]
   Method overloading allows us to have more than 1 functions with the same name but with diff method 
  signature
   Runtime polymorphism:[Dynamic(late)binding]An object is bound with the functionality  at runtime ,
  as opposed to objects bound at compile-time.
   It is achieved through [method overriding]
   Method overriding is a mechanism by which a subclass provides a different implementation of a method with
    same name and signature that is already defined in base class

 1.At compile-time JVM knows which method to call by checking at the method signatures
 2.Substitutability:Mechanism which allows to substitute a subclass instance when a superclass instance
is expected.Subclasses possess all attributes and operations of its superclass
 3.Upcasting:The process of casting an object to one of its super-types
 4.Downcasting:The process of casting an object from a super type to an object to a subtype object
    It requires explicit casting and is subject to runtime type checking [ClassCastException]
 5.Encapsulation keeping the data inside a class safe so that users cannot access the data directly
 6.Coupling refers to the degree to which one class relies on knowledge of the internals of others 
 7.Cohesion refers to the degree to which a class or a method cannot be broken down to smaller pieces
  
    class Animal {..};
    class Dog extends Animal {..};
    Dog dog = new Dog();
    Animal an1 = dog; //Upcasting
    Animal an2 = new Dog(); //
    Dog dog2 = (Dog)an2; //Downcasting
    Polymorphism:
    1.A reference to a class may hold an instance of that class or its 
      subclasses
    2.If a subclass instance is assigned to a superclass reference, you
     can only invoke methods defined in the superclass.The substituted
     instance retains its own identity it terms of overridden methods and 
     hiding variables
    Desired Properties
    Loose coupling & high cohesion & tight encapsulation
    Tight encapsulation:Declare all the variables in a class private and 
    provide public setter & getter methods to access these variables
    A highly cohesive class has fewer interactions with other classes ,implying 
    that high cohesion and loose coupling work together

  B.Overloading & Overriding Methods
 An overloading method :
   Can exist in the original class or its subclasses & must be differentiated by its parameter list
   It is not differentiated by the return type,exception list or access modifiers
 An overriding method:
   Must have the same parameter list with the original & the same return type or sub-type of its 
 original return type[covariant return types]
   It can throw exceptions that are declared in the original or their subclasses;It cannot throw 
 more exceptions than that declared in the original but can throw less exceptions
   Final or private methods cannot be overriden ;moreover, you cannot override a static with a
 not-static method.Static methods are not overridden,they are hidden
   Abstract methods must be overridden by the subclasses
  
 C.Passing Arguments into Methods [Pass-by-Value vs Pass-by-Reference]
  Formal Parameters:Placeholders,are used in method definition 
  Actual Parameters (arguments):The actual values passed into the method ,they replace the 
 formal parameters
  A method receives arguments from the caller,performs operations defined in the method body &
 returns a result or void
  1.Primitives:[Pass-by-Value] A copy of the value is created and passed into the method & the 
 method operates on the cloned copy,not having access to the original value.
  The original value is not affected ,Pass by Value =passing a cloned copy into the method
  2.Reference Types :[Pass-by-Value] For arrays and object instances, a clone of the reference
 is created & passed into the method
  
 D.Nested Classes
  Is a class defined inside another class and has the following properties:
  1.A nested class is a proper class that can contain constructors,member variables & 
    member methods.We can create instances of the nested classes via operator new
  2.Nested classes are members of the outer (enclosing ) class,just like any member var/method.
    Therefore, it can access private members of the enclosing class ,being the same level as 
  the private members
  3.Nested class can have [private,protected,default,public] access modifiers,just line any member
    variable and method.Recall that top level classes cannot be declared as private
  4.Nested class can be declared [static,final,abstract] ,just like an ordinary class
  5.Nested class is an ordinary self-contained class,but it is not s subclass of the outer class 
    Thus,it does not inherit the members of the outer class.
    However, nested classes can be declared as subclasses,extending the outer ,enclosing class
  6.Nested classes are used for namespace management,and to control visibilities of the member 
     variables and methods between inner & outer class
     
    public class MyOuterClass{
       private class MyNestedClass {...}
       public static class MyStaticClass{...}
        ...
    }
  
  7.Array Initializer 
 Elements of primitive type are initialized to zero or false while references are initialized to null
      
    String[] strArr = new String[3];
    for(String str :strArr)
       System.out.println(str); //null, null
    //Initialize array elements during declaration
    int[] numArr = {11,22,33};
    float[][] table = {{}};
    int[][] data = {{1,4,8},{2,3},{4,8,1,5}};
 
 IV.Wrapper & utility Classes
 ![img.png](img.png)

  constants :[MIN_VALUE,MAX_VALUE,SIZE] ,static final xxx variables
             [MIN_EXPONENT,MAX_EXPONENT] for Double,Float only

    //Parse Strings into primitive values ,except Character
      public static XXX parseXXX(String str) throws NumberFormatException
             XXX={Byte,Booleab,Short,Integer,Long,Float,Double}
    //Create Wrapper objects
      public static XXX valueOf(xxx value)
    //Unwrap 
      public xxx xxxValue()
    

 //Wrap via constructors
 Integer anIntObj =new Integer(55);//wrap an int primitive into an Integer object
 Character aChar = new Character('z');
 Long aLong = (Long)anIntObj;
 //Factory method valueOf()
 Integer intObj2 = Integer.valueOf(33);
 Integer intObj3 = 44;//auto-boxing from int to Integer
 

 

  
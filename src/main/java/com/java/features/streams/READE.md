I.Functional Programming [FP]
 OOP is based on the concept of objects which encapsulate data and code (behavior) in the same box
 FP is a programming paradigm that treats computation as applying a series of mathematical functions
to the data (transformations)
 FP resolves around pure functions,which are functions that:
  there is no side effect,they do not alter the data that was passed into it and the return value
  depends solely on  the input
 Concepts:
  Components :Functions and code
  Immutability:Data and objects are immutable ,their state cannot be changed once created
  First-Class Functions:We can store functions in a var,pass them as parameters or return them 
    from another function
  No Side Effects:Pure functions always return the same result if given the same input 
  Safe Multithreading:Immutable data and pure functions ensure that unsafe modifications won't happen
  Ease in testing:Pure functions can be tested in isolations ,without the need to setup the 
    environment they depend upon
  Conciseness:Anonymous functions can be written in concise syntax 
  FP is good when you have a fixed set of data;as the app evolves ,we add new functions to operate on 
  the existing data or in multithreading environments
  Adding new data may require modifying many existing functions
 
 A.Functional Interfaces [@FunctionalInterface]:
An interface containing a single abstract method interface

Lambda Expressions
Lambda Expression is an expression that evaluates to a value
It is a shorthand to creating an instance of an anonymous inner class that implements
a Functional interface
It consists of the method parameters & the method body
The parameters-type and return-type of the method can also be deduced as there is only
a single method in the functional interface
Syntax

    (T1 arg1,T2 arg2,..)->{  //Type parameters are optional
       method-block;
       return value;}
    (arg1,arg2,arg3,..)->{body-block; return val;}
    ()->statement; //No argument with 1 statement method body
    arg->statement;//1 arg ,omitting parenthesis (only for an 1 arg lambda expr)

Method Reference: Reference a method without invoking it,using operator '::'
Integer::new; //
int[]::new;//
System.out::println;// static method println of System.out
Person::getName;//Instance method, same as p->

Reduction Operations (folds): An operation that takes a sequence of input elements & combines them into a
single summary result by repeatedly applying a combining operation such as finding the sum

    int sum = 0;                 int sum = numbers.stream().reduce(0,(x,y)->x+y);
    for (int x : numbers) {      int sum = numbers.stream().reduce(0,Integer::sum);
       sum += x;
    }
Mutable Reduction Operation:Accumulates input elements into a result container as it processes the sequence
of input elements
  
 B.Streams 
 Stream API is introduced to process collections of objects
 Stream:Is a sequence of elements that supports sequential & parallel aggregate operations which can be pipelined 
 Pipeline:A sequence of operations on a Collection composed of:

    1.A Source:
    2.stream(),or .paralleStram():Produce a (parallell)Stream 
    3.Zero or more intermediate operations  
    4.A terminal operation whic produces a result
 Stream Characteristics   
    
    1.Stream is not a data structure for storing elements;A Stream conveys elements from
      their source through a pipeline of operations
    2.Possibly unbounted source:Streams may not have a finite size.Short-circuiting 
      operations can allow computations on infinite streams 
    3.Consumable:Stream elements are visited only once;revisiting the same elem requires 
      creating a new stream
    4.Laziness :Streams are lazy as the intermediate operations are not evaluated until a 
      terminal operation is invoked 
    5.Stateless vs Stateful Operations
         Stateless Intermediate operations:Do not require knowlege of previous elements when 
          they are about to process a new element
         Stateful Intermediate Operations:

    Reduction Operations
    Optional<T> reduce(BinaryOperator<T> accumulator)
    T reduce(T identity,BinaryOperator<T> accumulator)
    <U> U reduce(U id,BiFunction<Ts,Ue> accumulator,BinaryOperator<U> combiner) 

   Mutable Reduction Operation

    <R> R collect(Collector<Ts,A,R> collector)
    <R> R collect(Supplier<R> sup,BiConsumer<> accumulator,BiConsumer<> combiner)
  
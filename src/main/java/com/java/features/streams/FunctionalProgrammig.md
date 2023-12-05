1.Functional Interface [@FunctionalInterface]:
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
  
 A. package java.util.function
 
    Pattern      Functional Interface  Lambda Expression
    Predicate    Predicate<T>,         t->boolean 
                 BiPredicate<T,U>      (t,u)->boolean
    Supplier     Supplier<T>           ()->t
                 BooleanSupplier       ()-> boolean
    Consumer     Consumer<T>           t-> void
                 BiConsumer<T,U>       (t,u)-> void
    Function     Function<T,R>         t->r    
                 BiFunction<T,U,R>     (t,u)->R 
                 UnaryOperator<T>      t->t 
                 BinaryOperator<T>     (t,t)->t
  
  Specialized primitive type specializations are defined for primitives of type [int,long,double]

    Pattern      Functional Interface  [Lambda Expression]
    Consumer     LongConsumer [long->void],ObjLongConsumer<T> [(t,long)->void]
    Predicate    LongPredicate [long->boolean]
    Supplier     LongSupplier [()->long]
    Function     LongFunction<R> [long->r],ToLongFunction<R> [r->long]
                 IntToLongFunction [int->long],DoubleToLongFucntion [double->long]
                 ToLongBiFunction<T,U> [(t,u)->long]
                 LongUnaryOperator [long->long],LongBinaryOperator [(long,long)->long]
    

B. package java.util.stream
  
    Intermediate Operations 
     Stream<T> filter(Predicate<Ts> pr),sorted([Comparator<Ts> cmp)],
               skip(long n),limit(long maxSize)
    <R> Stream<R> map(Function<Ts,Re>mapper)
     IntStream    mapToInt(ToIntFunction<Ts> mapper)
     DoubleStream mapToDouble(ToDoubleFunction<Ts>mapper)
     LongStream   mapToLong(ToLongFunction<Ts> mapper)
 
     Terminal Operations
     void forEach(Consumer<Ts> action)
     Stream<T> peek(Consumer<Ts>)
     boolean allMatch(Predicate<Ts>),anyMatch(Predicate<Ts>),noneMatch(Predicate<Ts>)
     Optional<T> max(Comparator<Ts> cmp), min(Comparator<Ts> cmp)
                 findAny(),findAll()
     long  count()
     Object[] toArray()
     <A> A[] toArray(IntFunction<A[]> generator)
     //reduce
     Optional<T> reduce(BinaryOperator<T> accumulator)
     T reduce(T id,BinaryOperator<T> accumulator)
     <U> U reduce(U id,BiFunction<> accumulator,BinaryOperator<>combiner)
     //collect() 
     <R,A> R collect(Collector<Ts,A,R>collector)
     <R>   collect(Supplier<R> sup,BiConsumer,BiConsumer)

    interface java.util.stream.Collector<T,A,R> :
 A mutable reduction operation that accumulates input elements into a mutable
result container ,optionally transforming the inout elements into a final 
representation after all input elements have been transformed
  A Collector is characterized by 4 functions:
 Supplier:Creating a new result container
 Accumulator:Incorporating a  new data element into a result container
 Combiner:Combining 2 result container into one
 Finisher:Applying an optional transformation on the result container


class java.util.Collectors 
    
    Collector<T,?,List<T>> toList(), toUnmodifiableList()
    Collector<T,?,Set<T>> toSet(), toUnmodifiableSet()

    
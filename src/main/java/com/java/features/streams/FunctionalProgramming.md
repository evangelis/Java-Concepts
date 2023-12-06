
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
  
 B.package java.util.stream 
 interface Stream<T>:A sequence of elements suppoprting sequential and parallel aggregate operations
    //Methods
  
    //Create 
    static <T> Stream<T> empty(),of(T t),of(T ..values),ofNullable(T t),
                   genetate(Supplier<Ts>sup), iterate(T seed ,[],)
    //Other methods
    static <T> Stream<T> concat(Stream<Te> s1,Stream<Te> s2)
    default Stream<T> takeWhile(Predicate<Ts>), dropWhile(Predicate<Ts>)
    //Terminal Operations
    Stream<T> peek(Consumer<Ts> action)
    Object[] toArray()
    <A> A[] toArray(IntFunction<A[]>generator)
    void forEach(Consumer<Ts>),forEachOrdered(Consumer<Ts>)
    Optional<T> findFirst(),findAny(),max(Comparator<Ts>),min(Comparator<Ts>)
    long count()
    boolean anyMatch(),noneMatch(Predicate<Ts>),allMatch(Predicate<Ts>)
    
    
    
    


 Primitive Streams [IntStream,LongStream,DoubleStream]
  
 interface IntStream: A sequence of int-valued elements supporting sequential & parallel aggregate operations 

    //Methods
    //Create 
    LongStream asLongStream(), mapToLong(IntToLongFunction)
    DoubleSream aDoubleStream(),mapToDouble(IntToDoubleFunction)
    static IntStream of(int ...values), range(int st,int endEx),empty()
                     rangeClosed(int startIn,int endIn),
                     generate(IntSupplier s) //Infinite stream
                     iterate(int seed,[],)
    //Other methods
    default IntStream dropWhile(IntPredicate),takeWhile(IntPredicate )
    static IntStream concat(IntStream a,IntStream b)
    //Intermediate Operations
    IntStream filter(IntPredicate p),distinct(),limit(long size),skip(long n)
              sorted([Comparator<])
    LongStream mapToLong(IntToLongFunction mapper)
    DoubleStream mapToDouble(IntToDoubleFunction m)
    <U> Stream<U> mapToObj(IntFunction<Ue> mpapper)
    //Terminal Operations
    void forEach(IntConsumer action),forEachOrdered(IntConsumer)
    OptionalInt findAny(),findFirst(),max(),min()
    boolean anyMatch(IntPredicate), allMatch(IntPredicate),noneMatch(IntPredicate)
    long count()
    int[] toArray()
    IntStream peek(IntConsumer action)


 interface java.util.stream.Collector<T,A,R> :

    //Methods 
    Supplier<A> supplier()
    BiConsumer<A,T> accumulator()
    BinaryOperator<A> combiner()
    Function<A,R> finisher()
    static <T,A,R> of(Supplier<A> sup,BiConsumer<A,T>accum,BinaryOperator<> ,
                    Function<A<R> finisher,..Collector.Characteristics chars)
                 of(Supplier<R> sup,BiConsumer<R,T> accum,BinaryOperator<R> cmb,
                     Collector.Characteristics ..chars)

 A mutable reduction operation that accumulates input elements into a mutable
result container ,optionally transforming the inout elements into a final 
representation after all input elements have been transformed
  A Collector is characterized by 4 functions:
 Supplier:Creating a new result container
 Accumulator:Incorporating a  new data element into a result container
 Combiner:Combining 2 result container into one
 Finisher:Applying an optional transformation on the result container


class java.util.Collectors 
    
    static <T> Collector<T,?,List<T>> toList(), toUnmodifiableList()
    static <T> Collector<T,?,Set<T>> toSet(), toUnmodifiableSet()
    static <T,C extends Collection<T>
             Collector<T,?,Collection<C>> toCollection(Supplier<C>)
    static <T,K,V> Collector<T,?,[Concurrent]Map<K,V>>
             to[Concurrent]Map(Function<Ts,Ke>km,Function<Ts,Ve>vm,
             toUnmodifiableMap(Function<?super T,? extends K> km,
                                            Function<?super T,? extends V>vm)
    static <T,K,U> to[Concurrent]Map()
    static <T> Collector<T,?,Double> averagingDouble(ToDoubleFunction<Ts>),averagingInt(ToIntFunction<?super T>),
                             averagingLong(ToLongFunction<Ts> mapper)
    static <T> Collector<T,?,Optional<T>> minBy(Comparator<Ts>),maxBy(COmparator<Ts>)
    static <T> Collector<T,?,YYYStatistics> summarizingYYY(ToYYYFunction<Ts>mapper)
    static <T> Collector<T,?,YYY> summingYYY(ToYYYFunction<Ts>)
    static Collector<T,?,String> joining([CharSequence delim,..CharSequence pref,..CharSequence suff])
    static <T,U,A,R> Collector<T,?,R> mapping(Function<Ts,Ue> mapper,Collector<Us,A,R> ds)
              flatMapping(Function<Ts,? extends Stream<U>>m,Collector<Us,A,R>ds
    static <T>Collector<T,?,Map<Boolean,List<T>> partitioningBy(Predicate<Ts>)
    static <T,D,A> Collector<
    static <T,K> Collector<T,?,Map<K,List<T>> groupingBy(Function<>)
    

   

C.Collection classes & Interfaces :[package java.util]

class Optional<T>  :Container class which may or may not contain a value
 
    //Methods 
    static <T> Optional<T> of(T val) ,empty(), ofNullable(T val)
    boolean isEmpty(), isPresent()
    T get() ,orEsle(T other), orEsleGet() ,orElseThrow()
    Stream<T> stream()
    Optional<T> filter(Predicate<Ts>),or(Supplier<Ts>)
    <U> Optional<U> map(Function<Ts,Ue>mapper)
             flatMap(Function<Rs,? extends Optional<Ue>>mapper)
    void ifPresent(Consumer<Ts>), ifPresentOrElse(Consumer<Ts>,Runnable emptyAction)

 public interface Iterable<E> :Objects of type E can be the target of the enhanced for statement
    
    Iterator<E> iterator()
    default Spliterator<E> spliterator()
    default void forEach(Consumer<? super E> action)
 public interface java.util.Iterator:
    
    boolean hasNext()
    E next()
    void remove()
    default void forEachRemaining(Consumer<Es> action)

 public interface Collection<E>  

    boolean adde(E e) ,addAll(Collection<? extends E>coll)
            equals(Object object),contains(Object object)
            containsAll(Collection<?> col),isEmpty();remove(Object o)
            removeAll(Collection<?> coll),retainAll(Collection<?> coll)
    void clear()
    int hashCode(); size()
    Iterator<E> iterator()
    Object[] toArray();
    <T> T[] toArray(T[] arr)
    //JDK 8+ Methods
    default Stream<E> stream();parallelStream()
    default Spliterator<E> spliterator(){}
    default boolean removeIf(Predicate<? super E>pred){}

public interface List<E> 
    Implementing classes:{ArrayList,LinkedList,Stack,Vector,CopyOnWriteArrayList}

    static <E> List<E> of(E e1),of(E e1,...E e10)
    ListIterator<E> listIterator(),listIterator(int index),
    Iterator<E> iterator()
    Object[] toArray()
    <T> T[] toArray(T[] arr)
    int hashCode(),size(), indexOf(Object o),lastIndexOf(Object o),
    boolean remove(Object o),removeAll(Collection<?>c),retainAll(Collection<?>c)
            add(E e),addAll(Collection<? extends E> c),addAll(int index,Collection<?>c)
            contains(Object o), equals(Object o),containsAll(Collection<?> c)
    void clear(),add(int index,E element)
    E remove(int i)
    List<E> sublisr(int fromIdx,int toIndex)
    E get(int idx),set(int index,E element)
    //JDK 8+ Methods
    <static <T> of(E e1), of(E e1,..E e10), copyOf(Collection<? extends E>col) 
    default Spliterator<E> spliterator(),
    default void sort(Comparator<? super E> cmp), replaceAll(UnaryOperator<E> op)

public interface Set<E>
    Sub-Interfaces :{SortedSet,NavigableSet,EventSet}
    Implementing classes:{TreeSet,LinkedHashSet,HashSet,CopyOnWriteArraySet,ConcurrentSkipListSet}
    
    boolean add(E e),addAll(Collection<?> c), remove(Object o),removeAll(Collection<?>c),
            retainAll(Collection<?>coll),isEmpty()
            contains(Object o) ,containsALl(Collection<?>c),equals(Object o)
    int size(),hashCode()
    Object[] toArray()
    <T> T[] toArray(T[] arr)
    void clear()
    Iterator<E> iterator()
    //JDK 8+ Methods
    static <E> Set<E> of(E e1), of(E e1,..E e10)
    default Spliterator<E> spliterator()
    //SortedSet Methods
    E first(),last(),
    SortedSet<E> headSet(E toEl) ,tailSet(E from),subSet(E from,E to)
    Comparator<? super E> comparator()
    //NavigableSet Methods
    Iterator<E> iterator(),descendingIterator(),
    E lower(E e),floor(E),higher(E),ceiling(E)
      pollFirst(),pollLast() //Retrieve & remove the 1st/last element 
    NavigableSet<E> descendingSet(),subSet(E from,boolen in,E to,boolen in),
                 headSet(E toEl,boolean in),tailSet(E from,boolean in)

interface Queue<E>
 Sub-Interfaces: Deque,TransferQueue,BlockingQueue,BLockingDeque,DelayQueue,SynchronousQueue
 Implementing Classes:ArrayDeque,LinkedList,LinkedTransferQueue, PriorityQueue,
                      PriorityBlockingQueue,ConcurrentLinkedQueue,ConcurrentLinkedDeque
                       LinkedBlockingDeque, LinkedBlockingQueue,ArrayBlockingQueue
 Bellow is a summary of Queue & Deque methods 

            Throws Exception   Returns special value          
    Insert  boolean add(E)     boolean offer(E)           
    Remove  E remove()         E poll()                  
    Examine E element()        E peek()   

                                                        Throws Exception   Special value
                            Head                                          Tail              
    Insert  void addFirst(E)   boolean offerFirst(E)    void addLast(E) 
    Remove  E removeFirst(),   
            E remove()
    Examine E getFirst(),
            E element()

interface Map<K,V>:An object that maps keys to values
   A map cannot contain duplicate keys;each map can map at most 1 value
  Sub-Interfaces: SortedMap,NavigableMap, ConcurrentMap,ConcurrentNavigableMap
  Implementing Classes: HashMap,LinkedHashMap,Hashtable,
                        ConcurrentHashMap,ConcurrentSkipListMap
Summary of Methods
    
    V put(K ,V),get(Object ),remove(Object key),
    boolean containsKey(Object),containsValue(Object),isEmpty()
    int hashCode(),size(),
    Set<K> keySet()
    Set<Map.Entry<K,V>> entrySet()
    Collection<V> values()
    void clear(),putAll(Map<Ks,Vs>)
    int size(),hashCode() 
    //JDK 8+ Methods
    static <K,V> Map.Entry<K<V>> entry(K k,V v)
    static <K,V> Map<K,V> ofEntries(Map.Entry<Ks,Vs> e1,...)
    static <K,V> Map<K,V> of(),of(K k1,V v1,..K k10, V v10)
                         copyOf(Map<? extends K,? extends V>map1)
    default void forEach(BiConsumer<Ks,Vs>action),
                replaceAll(BiFunction<Ks,Vs,Ve>func)
    default boolean replace(K key,V oldV,V newV),remove(Object k,Object v)
    default V putIfAbsent(K k,V v),replace(K,V), getOrDefault(Object k,V defV)
             merge(K key,V val,BiFunction<Ks,Vs,Ve>) //merge the value based on the key
             compute(K k,BiFunction<Ks,Vs,Ve>remapF)
             computeIfAbsent(K k,Function<Ks,Ve> map),
             computeIfPresent(K key,V val,BiFunction<Ks,Vs,Ve>remapF)
    //SortedMap Methods
    Comparator<Ks> comparator()
    K firstKey(),lastKey()
    SortedMap<K,V> subMap(K from,K toK),tailMap(K from),headMap(K toK)
    Set<K> keySet()
    Set<Map.Entry<K,V>> entrySet()
    //NavigableMap Methods

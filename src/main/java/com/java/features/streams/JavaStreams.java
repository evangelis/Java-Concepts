package com.java.features.streams;

import java.util.*;
import java.util.stream.Stream;

public class JavaStreams {
    public static void createStreams(){
        //Consider a Sequential and a Parallel Stream<Integer>
        List<Integer> intLst = new ArrayList<>();
        for (int i = 0; i <=100; i++) {
            intLst.add(i);
        }
        System.out.println(intLst);
        Stream<Integer> intStr = intLst.stream();
        Stream<Integer> parallelIntStr =intLst.parallelStream();

        intStr.filter((i->i%10==0))
                .forEach(System.out::println);
        parallelIntStr.filter((integer -> integer%10==0))
                .forEach(System.out::println);
        //Again, Stream<Person> for sequential and parallel processing
        List<Person> plist = Person.createPersons();
        plist.stream().forEachOrdered(System.out::println);
        plist.stream().parallel().forEachOrdered(Person::sayHello);

        Stream<Person> personStream = Person.createStream();

    }
    public static void reductions(){
        /***<T> reduce(T id,BinaryOperator<T>accumulator)
         *   Optional<T>reduce(BinaryOperator<T> accum)
         *  <U> U reduce(U id,BiFunction<Ts,Ue>accumulator,
         *              BinaryOperator<U> combiner)
         *
         */
        List<Person> plist = Person.createPersons();
        System.out.println(plist);
        //Lets compute the sum of ages [reduction]
        int sumAge;
        sumAge=plist.stream().reduce(0,(sum, person)->sum+person.getAge(),(x,y)->x+y);
        sumAge=plist.stream().mapToInt(Person::getAge)//ToIntFunction
                .sum();
        sumAge=plist.stream().mapToInt(Person::getAge)
                .reduce(0,Integer::sum);
        sumAge = plist.parallelStream().map(Person::getAge)
                .reduce(0,Integer::sum);
        System.out.printf("sumAge="+sumAge);

        List<String> fruits = List.of("apple","orange","banana");
        int totalLen = 0;
        totalLen = fruits.stream().parallel().map(String::length)
                .reduce(0,Integer::sum);
        totalLen = fruits.stream().mapToInt(String::length).sum();
        totalLen = fruits.stream().reduce(0,(sum,x)->sum+=x,Integer::sum);
        System.out.println();
        String concatenated =fruits.stream().reduce("",String::concat);
        System.out.printf("The concatenated String is :",concatenated);

    }

}

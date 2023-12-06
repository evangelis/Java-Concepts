package com.java.features.streams;
import org.apache.kerby.kerberos.kerb.crypto.enc.Camellia256CtsCmacEnc;

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
}

package com.java.features.streams;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class JavaCollectors {
    private static Logger logger =LoggerFactory.getLogger(JavaCollectors.class.getName());
    public static void cityCollectors(){
        List<City> cities = City.prepareCities();
        Supplier<Stream<City>> cityStreamSup =City.getCityStreams();
        System.out.println("Cities\n:"+cities);
        System.out.println("Filter out city names having temperature less than 10 degrees Celsius");
        cities.stream().filter(c->c.getTemprerature()>10)
                .map(City::getName)
                .forEachOrdered(System.out::println);
        System.out.println("Collect city names that have >10C in a List and a Set");
        var namesLst =cities.stream().filter(c->c.getTemprerature()>10)
                .map(City::getName)//Stream<String>
                .collect(Collectors::toList);

        Set<String> namesSet =cities.stream().filter(c->c.getTemprerature()>10)
                .map(City::getName).collect(Collectors.toSet());
        //Collect city names & temperatures in a map keeping those cities with>10C
        Map<String,Double> cityMap = cities.stream().filter(c->c.getTemprerature()>10)
                .collect(Collectors.toMap(City::getName,City::getTemprerature));
        System.out.println(namesSet);
        System.out.println(namesSet);
        System.out.println(cityMap);

    }
}

/********************************************************************************************
 * Collector<T,?,List<T>>  toList(),toUnmodifiableList()
 * Collector<T,?,Set<T>>   toSet(),toUnmodifiableSet()
 * Collector<T,?,Map<K,V>> toMap(Function<Ts,Ke>,Function<Ts,Ve>,BinaryOperator<V>)
 * Collector<T,?,C>        toCollection(Supplier<C>collFactory)
 * Collector<T,?,>         mapping(Function<Ts,>,Collector<> ds)
 * Collector<T,?,Map<Boolean,List<T>>> partitioningBy(Predicate<Ts>,[Collector<> ds])
 * Collector<T,?,Map<K,List<K>>> groupingBy()
 *
 *
 *********************************************************************************************/
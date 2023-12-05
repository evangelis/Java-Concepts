package com.java.features.streams;
import org.apache.spark.internal.config.R;

import java.util.List;
public class ProcessPerson {
    //Filter-Reduce
    public static <E> void process(List<E> lst,MyPredicate<E> pred, MyConsumer<E> cons){
        for (E elem:lst) {
            if(pred.test(elem))
                cons.accept(elem);
        }
    }
    //Filter-Map-Reduce
    public static <T> void process2(List<T> lst,MyPredicate<T> pred,
                                    MyFunction<T,R> mapper,MyConsumer<R> cons){
        for(T elem:lst){
            if (pred.test(elem)){
                R r = mapper.apply(elem);
                cons.accept(r);
            }
        }
    }
}


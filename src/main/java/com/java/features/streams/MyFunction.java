package com.java.features.streams;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;
public interface MyFunction<T,R>  extends Function<T,R>{
    R apply(T t);
    default R MyFunction<T,R>

    @NotNull
    @Override
    default  MyFunction<T, V> andThen(@NotNull Function<? superT,U> after){
        Objects.requireNonNull(after);

    }
    default MyFunction<> compose(MyFunction<> before){
        Objects.requireNonNull(before);

    }
    static <T> MyFunction<T,T> identity() {
        return (T t)->t;
    }

}

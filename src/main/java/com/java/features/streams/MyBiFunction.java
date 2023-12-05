package com.java.features.streams;

import java.util.function.BiFunction;

public interface MyBiFunction<T,U,R> extends BiFunction<T,U,R> {
    R apply(T t,U u);
}

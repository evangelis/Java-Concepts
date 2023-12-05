package com.java.features.streams;
import java.util.function.Supplier;
@FunctionalInterface
public interface MySupplier<T> extends Supplier<T>{
    T get();
}

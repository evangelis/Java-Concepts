package com.java.features.streams;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

@FunctionalInterface
public interface MyConsumer<T> extends Consumer<T> {
    void accept(T t);//Run the side effect on the given object

    default Consumer<T> andThen(MyConsumer<? super T> after){
        Objects.requireNonNull(after);
        return (T t)-> {
            this.accept(t);
            after.accept(t);
        };
    }
}


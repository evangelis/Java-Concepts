package com.java.features.streams;
import java.util.function.BiConsumer;
import java.util.Objects;
public interface MyBiConsumer<T,U> extends BiConsumer<T,U> {
    @Override
    void accept(T t, U u);

    @Override
    public default BiConsumer<T,U> andThen(BiConsumer<? super T,? super U> after){
        Objects.requireNonNull(after);
        return  (T t, U u) -> {
            this.accept(t, u);
            after.accept(t, u);
        };
    }
}

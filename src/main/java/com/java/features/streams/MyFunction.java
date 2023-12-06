package com.java.features.streams;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;
public interface MyFunction<T,R>  extends Function<T,R>{
    R apply(T t);
    @NotNull
    @Override
     default <U> MyFunction<R, U> andThen(@NotNull Function<? super R,? extends U> after){
        Objects.requireNonNull(after);
         return (T t)->after.apply(this.apply(t));
    }
    default MyFunction<> compose(MyFunction<> before){
        Objects.requireNonNull(before);
        return (T t)-> {this.apply(before.apply(t));}
    }
    static <T> MyFunction<T,T> identity() {
        return (T t)->t;
    }

}

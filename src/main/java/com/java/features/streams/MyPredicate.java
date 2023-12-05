package com.java.features.streams;
import java.util.Objects;
import java.util.function.Predicate;
@FunctionalInterface
public interface MyPredicate<T> extends Predicate<T> {
    boolean test(T t);//perform this boolean test on the give object

    default MyPredicate<T> and(MyPredicate<? super T> other){
        Objects.requireNonNull(other);
        return (T t)->test(t) && other.test(t);
    }
    default MyPredicate<T> or(MyPredicate<? super T> other){
        Objects.requireNonNull(other);
        return (T t)->this.test(t) || other.test(t);
    }
    default MyPredicate<T> negate(){
        return (T t)-> !this.test(t);
    }
    public static <T>MyPredicate<T> isEqual(Object target){
        return (target == null) ? Objects::isNull :
                (T obj)->obj.equals(target);
    }

}

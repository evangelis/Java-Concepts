package com.java.features.streams;
import org.jetbrains.annotations.NotNull;
import java.util.Comparator;
import java.util.Objects;
public class Person implements Comparator<Person>{
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName().toLowerCase(),age);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        return obj!=null && obj instanceof Person &&
                this.age == ((Person) obj).age &&
                this.name.equalsIgnoreCase(((Person) obj).getName());
    }

    @Override
    public int compare(Person person1, Person person2) {
        //Implements the natural ordering
        return Integer.compare(person1.age, person2.age);
    }

    public void sayHello(){
        System.out.println(name +" says hello");
    }
}

/************************************************************************************************
 * 1.Functional Interface [@FunctionalInterface]:
 *   An interface containing a single abstract method interface
 *
 * Lambda Expressions
 *  Lambda Expression is an expression that evaluates to a value
 *  It is a shorthand to creating an instance of an anonymous inner class that implements
 * a Functional interface
 *  It consists of the method parameters & the method body
 *  The parameters-type and return-type of the method can also be deduced as there is only
 *a single method in the functional interface
 *
 *  Syntax
 *  (T1 arg1,T2 arg2,..)->{  //Type parameters are optional
 *      method-block;
 *      return value;
 *  }
 * (arg1,arg2,arg3,..)->{body-block; return val;}
 * ()->statement; //No argument with 1 statement method body
 * arg->statement;//1 arg ,omitting parenthesis (only for an 1 arg lambda expr)
 *
 *Method Reference: Reference a method without invoking it,using operator '::'
 *   Integer::new; //
 *   int[]::new;//
 *   System.out::println;// static method println of System.out
 *   Person::getName;//Instance method, same as p->
 *
 *
 ************************************************************************************************/
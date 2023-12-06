package com.java.features.streams;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Stream;

public class Person implements Comparator<Person>{
    private String name;
    private int age;

    public Person(String n,int age){
        name=n;
        this.age=age;
    }

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
    public static List<Person> createPersons() {

        List<Person> pList = List.of(
                new Person("Peter", 21), new Person("John", 60), new Person("Paul", 15),
                new Person("Condy", 59),new Person("Pedro", 13),new Person("Manolo", 45),
                new Person("Martha", 11),new Person("Angelina", 22),new Person("Kendra", 38),
                new Person("Helen", 67),
                new Person("Geena", 14),new Person("Bill", 72),new Person("", 21),
                new Person("Jason", 16), new Person("George", 40),new Person("Jim", 89),
                new Person("Jin", 82),new Person("Pennie", 12),
                new Person("Jonathan", 89),new Person("Mina", 33),new Person("", 79),
                new Person("James", 34),new Person("Bob", 11),new Person("Soula", 56),
                new Person("Cathrine", 32) ,new Person("Jasmine", 28));
        List<Person> pl = new ArrayList<Person>(pList);
        return pl;
    }
    public static Stream<Person> createStream(){
        Stream<Person> pStream = Stream.of(Person.createPersons()
                .toArray(Person[]::new));
        return pStream;
    }
}


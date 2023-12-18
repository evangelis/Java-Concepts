package com.java.features;

import scala.reflect.internal.Trees;

import java.util.concurrent.Callable;

class A{
    public A(){
        System.out.println("Constructed an instance of A");
    }

    @Override
    public String toString() {
        return "This is an instance of A";

    }
}
class B extends A{
    public B(){
        super();
        System.out.println("Constructed an instance of B");
    }
    @Override
    public String toString(){
        return "This is an instance of B";

    }
}

class C extends B{
    public C(){
        super();
        System.out.println("Constructed an instance of C");
    }

    @Override
    public String toString() {
        return "This is an instance of C";
    }
}

public class ClassHierarchy {
    public static void checkOOP() {
        A a1 = new C();
        System.out.printf("a1 = %s", a1);
        B b1 = (B) a1;//downcasting
        System.out.println("b1 = " + b1);
        C c1 = (C) b1;//downcast
        System.out.println("c1 = " + c1);
        A a2 = new B();
        System.out.println("a2 = " + a2);
        B b2 = (B) a2; //downcast is ok
        C c2 = (C) a2; //Compiles ok ,but ClassCastException
    }
}

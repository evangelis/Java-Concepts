package com.java.features.testing;
im
public class Calculator {
    public static int add(int a,int b){
        return a+b;
    }
    public static int sub(int a ,int b){
        return a-b;
    }
    public static int mul(int a,int b){
        return a*b;
    }
    public static int divInt(int a,int b){
        if(b !=0)
            return a/b;
        throw new IllegalArgumentException("Cannot divide by zero");
    }
    public static double divReal(int a, int b){
        if (b !=0){
            return (double) a/b;
        }
        throw new IllegalArgumentException("Cannot divide by zero");
    }
}

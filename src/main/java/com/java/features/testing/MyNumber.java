package com.java.features.testing;

public class MyNumber {
    private int number;
    public MyNumber(){
        number=0;
    }
    public MyNumber(int n){
        number=n;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public MyNumber add(MyNumber rhs){
        this.number +=rhs.number;
        return this;
    }
    public MyNumber div(MyNumber rhs){
        if( rhs.number ==0) throw new IllegalArgumentException("");
        number /=rhs.number;
        return this;
    }
}



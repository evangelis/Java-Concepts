package com.java.features.io;
import java.io.Serial;
import java.io.Serializable;
public class MySerializedObject implements Serializable {
    @Serial
    private static final long serialVersionUID =123L;

    private int number;
    public MySerializedObject(int n){
        number =n;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}

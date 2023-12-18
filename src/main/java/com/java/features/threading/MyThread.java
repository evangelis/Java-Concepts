package com.java.features.threading;

public class MyThread extends  Thread {
    private String name;

    public  MyThread(String n){
        this.name = n;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 5; i++) {
            System.out.println(name +" : "+i);
            yield();
        }
    }
}

package com.java.features.threading;

public class SynchronizationExample {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();
    private int number;
    public SynchronizationExample(int n{
        number=n;
    }
    public SynchronizationExample(){number =0;}

    public void meth1(){
        System.out.println("Inside meth1()");
        synchronized (lock1){
            SynchronizationExample[] ex = new SynchronizationExample[5];
            for (int i = 0; i <ex.length ; i++) {
                ex[i] = new SynchronizationExample(i+1);
            }
        }
    }
    public void meth2(){
        System.out.println("Inside meth2()");
        SynchronizationExample ex[]  = new SynchronizationExample[];
        synchronized (lock2){


        }
    }

}

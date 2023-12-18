package com.java.features.threading;
/**** Synchronizing static methods *****/
public class SynchronizedCounter {
    private static int count =0;

    public synchronized static int getCount() {
        return count;
    }
    public static  synchronized  void increment(){
        ++count;
            System.out.println("Count is :"+count);
    }

    public static synchronized void derement(){
        --count;
        System.out.println("Count isd :"+ count);
    }

    public static void counting(){
        Thread incrT1 = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i <100 ; i++) {
                    SynchronizedCounter.increment();
                    try{
                        sleep(100);
                    }catch (InterruptedException ex){
                        System.err.println(ex.getMessage());
                    }
                }
            }
        };
        Thread decrT1 = new Thread(){
            @Override
            public void run() {
                for (int i = 0; i <100 ; i++) {
                    SynchronizedCounter.derement();
                    try {
                        sleep(100);
                    }catch (InterruptedException ex){
                        System.err.println(ex.getMessage());
                    }
                }

            }
        };
        incrT1.start();
        decrT1.start();
    }

}

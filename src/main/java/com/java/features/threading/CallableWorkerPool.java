package com.java.features.threading;
import org.apache.spark.sql.execution.columnar.INT;

import javax.swing.*;
import java.util.concurrent.*;

class CallableWorker implements  Callable<String>{
    private int number;
    CallableWorker(int n){
        number=n;
    }

    @Override
    public String call() throws Exception {
         for (int i = 1; i <= 5; i++) {
             System.out.printf("Worker %d : %d %n",
                     number, i);
             try {
                 Thread.sleep(100);
             } catch (InterruptedException ex) {
                 System.err.println("Error :" + ex.getMessage());
             }
         }
         return "Worker : " + number;
    }
    public static void CallableWorkerPool() {
        int numWorkers = 5;
        ExecutorService service = Executors.newCachedThreadPool();
        CallableWorker[] workers = new CallableWorker([numWorkers]);
        Future[] futures = new Future[numWorkers];
        for (int i = 0; i < ; i++) {
            
        }
    }
}

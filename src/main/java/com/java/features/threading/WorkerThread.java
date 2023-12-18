package com.java.features.threading;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class WorkerThread implements Runnable {
    private int workerNumber;

    public WorkerThread(int i){
        workerNumber =i;
    }

    @Override
    public void run() {
        //Prints numbers 1 to 5
        for (int i = 1; i <=5 ; i++) {
            System.out.printf("Worker %d :%d %n",workerNumber,i);
        }
        try{
            Thread.sleep((int) (Math.random()*500));
        }
        catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
   public static void runTask(int numWorkers,int threadPoolSz) {
        ExecutorService exserv = Executors.newFixedThreadPool(threadPoolSz);
        WorkerThread[] workers = new WorkerThread[numWorkers];
       for (int i = 0; i <numWorkers ; i++) {
           workers[i] = new WorkerThread(i+1);
           exserv.execute(workers[i]);
       }
   }
}

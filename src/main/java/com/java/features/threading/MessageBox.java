package com.java.features.threading;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
/*****************************************************************
 *Producer-Consumer pattern
 * A producer thread produces messages through putMessage(String).
 * A consumer thread consumes messages through getMessage()
 * A message needs to be consumed before a producer thread can
 * produce another message
 * A thread can suspend itself using wait() to release the lock
 *
 *Here,we create 1 producer thread that produces 6 messages and
 * 2 consumer threads each reading half of the messages
 *
 ******************************************************************/
public class MessageBox {
    private static Logger logger = LoggerFactory.getLogger(MessageBox.class);
    private String message;
    private boolean hasMessage;
    private synchronized void putMessage(String msg){
        while (hasMessage){
            try{ //no room for new message;release the lock of this object
                wait();
            }
            catch (InterruptedException ex){
                logger.error(ex.getMessage());
            }
            //acquire the lock and continue
            hasMessage =true;
            this.message = msg +" Put @ "+System.nanoTime();
            notifyAll();
        }
    }
    private synchronized String getMessage(){
        while (!hasMessage){
            try{
                wait();
            }
            catch (InterruptedException ex){
                logger.error("Error %s consuming message ",ex);
            }
            //acquire the lock and continue
        }
        notifyAll();
        return message +" Get @ "+System.nanoTime();
    }
    public static void runThreads(){
        MessageBox box = new MessageBox();
        Thread prodThread = new Thread(){
            @Override
            public void run(){
                System.out.println("Producer thread started ...");
                for (int i = 1; i <=6 ; i++) {
                    box.putMessage("Message "+i);
                }
            }
        };
        Thread consThread1 =new Thread(){
            @Override
            public void run(){
                System.out.println("Consumer thread 1 started ...");
                for (int i = 1; i <=3 ; i++) {
                    System.out.printf("Consumer thread 1 got :%s",
                            box.getMessage());
                }
                System.out.println();
            }
        };
        Thread consThread2 = new Thread(){
            @Override
            public void run(){
                System.out.println("Consumer thread 2 started ...");
                for (int i = 1; i <=3 ; i++) {
                    System.out.println("Consumer thread 2 got :"+box.getMessage());

                }
            }
        };
        consThread1.start();consThread2.start();
        prodThread.start();
    }

}

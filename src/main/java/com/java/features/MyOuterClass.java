package com.java.features;

public class MyOuterClass {
   private static  int outer_stat=10;
   int outer_y=20;
   protected static class StaticNestedClass {
       public void displayStat() {
           System.out.printf("In class %s", getClass().getName());
           System.out.printf("outer_stat = %d", outer_stat);
           MyOuterClass mc = new MyOuterClass();
           System.out.printf("Accessing  outer instance var (requires instantiations of the outer class)." +
                   "outer_y = %d %n", mc.outer_y);
       }
   }
   private class InnerClass{
       void displatInner(){
           System.out.printf("In class %s",getClass().getName());
           System.out.printf("outer_stat = %d",outer_stat);
           System.out.printf("outer_y = %d %n",outer_y);
       }
   }
   public void instantiate(){
       MyOuterClass mycl = new MyOuterClass();
       MyOuterClass.StaticNestedClass sttObject = new MyOuterClass.StaticNestedClass();
       sttObject.displayStat();
       MyOuterClass.InnerClass innereObj = mycl.new InnerClass();
       innereObj.displatInner();
   }
   /******** Define a Local Inner class ****/
    private void getOuter_y(){
        int outer_y= 1;
        class LocalInner{
            private int divisor,remainder;

            public LocalInner(){
                divisor = 4;
                remainder = outer_y%divisor;

            }
            private int getDivisor(){
                return  divisor;
            }

            public int getRemainder() {
                return remainder;
            }

            private int getQuotient(){
                System.out.println();
                return outer_y/divisor;
            }
        }
    }
    public static  void run(){

        MyOuterClass cl = new MyOuterClass();
        cl.getOuter_y();
    }

}



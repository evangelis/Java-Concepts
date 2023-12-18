package com.java.features;

public class ClassInitialization {
    public int number1=11; //explicit instance initializer

    {
        number1 = 33;
        number2 = 44;//Instance initializer
        System.out.printf("Runs instance initializer\n.number1 =%d= %n",
                number1); //Illegal forward reference if you refer number2
    }
    public int number2 =22;
    static int numberStat ; //static variable
    static {
        numberStat =88;
        System.out.println("Runs static initializer ,numberStat ="+numberStat);
    }
}

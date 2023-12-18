package com.java.features;

/****************************************************************
 *Passing arguments into methods
 * Primitives Pass-by-Value :A copy of identical value is created
 *  and passed into the method,leaving the original primitive value
 *  unaffected
 * References  Pass-by-Value:A copy of the reference is passed
 * into the method.However, the caller's object & the method's
 * parameter have the same reference ,if the method changes the
 * member variables of the object these changes will be permanent
 * and take effect outside the method
 *
 ****************************************************************/
public class PassRefValue {
    private static StringBuffer sb = new StringBuffer("Hello");
    public static void methRefernce(StringBuffer sb1){
        System.out.printf("Initiating methReference(StringBuffer sb1) "+
        "the object is sb1 =%s",sb1);
        sb1.append(", world");
        System.out.printf("In methReference(StringBuffer sb1),after the change ,sb1= %s",sb1);

    }

    public static void main(String[] args) {
        System.out.println("In caller method main() ,the object is sb=%s "+sb);
        PassRefValue.methRefernce(sb);
        System.out.println("In caller after calling the method ,the object is sb ="+sb);

    }
}

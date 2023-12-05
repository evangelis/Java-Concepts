package com.java.features.streams;

@FunctionalInterface
public interface MyInterface {
    void foo();//abstract public
    default void methDefault(){
        System.out.println("interface MyInterface runs default method methDefault()");
    }
    static void methStatic(){//public static void
        System.out.println("interface MyInterface runs public static void methStatic() ");
        showMsgPrivateStatic("Hello","John");
    }
    private void showMsgPrivate(String greet,String name){
        System.out.println("Runs (instance) private void showMsgPrivate()");
    }
    private static void showMsgPrivateStatic(String greet,String name){
        System.out.println("Runs private static void showMsgPrivateStatic() ");
        System.out.println(greet+","+name);
    }
    /***public instance method calls private instance method ***/
    default void sayHelloDefault(String name){
        showMsgPrivate("Hello",name);
    }



}

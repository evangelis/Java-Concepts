package com.java.features.testing;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class MyNumberTest {
    //Provide the text fixtures
    private MyNumber number1, number2;//Texty fixtures

    @BeforeEach
    public void setUp(){
        System.out.println("Run @BeforeEach");
        number1 = new MyNumber(11);
        number2 = new MyNumber(22);
    }

    @AfterEach
    public void tearDown(){
        System.out.println("Run @AfterEach");

    }

    @Test
    public void testAdd(){
        System.out.println("Run @Tes testAdd()");
        Assertions.assertEquals(33,number1.add(number2).getNumber(),
                "Error in MyNumber add(MyNumber )");
        Assertions.assertEquals(55,number1.add(number2).getNumber(),
                "Error in MyNumber add(MyNumber )");
    }

    @Test
    public void testDiv(){
        System.out.println("Run @Test testDiv()");
        Assertions.assertEquals();
        Assertions.assertEquals();
    }


    @Test
    public void testGetterSetter(){
        System.out.println("Run @Test testGetterSetter()");
        int val1 = 33;
        number1.setNumber(val1);
        Assertions.assertEquals(val1,number1.setNumber(val1),"Error in getter/sertter methods");
    }



}

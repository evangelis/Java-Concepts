package com.java.features.testing;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
public class CalculatorTest {
    @Test
    public  void testAddPas(){
        assertEquals(3, Calculator.add(1,2),"Error in add(int,int");
        assertEquals(-3,Calculator.add(-1,-2),"Error in add(int ,int)");
        assertEquals(9,Calculator.add(9,0),"Error in add(in,int)");
    }
    @Test
    public void testSubPass(){
        assertEquals(  );
    }

    @Test
    public void testDIvIntPass(){

    }

     @Test
     public void testDivIntFail(){

     }
     @Test
    public void testDivRealPass(){

     }


}

/*********************************************************************************************************
 *1.JUint 5 Annotations
 * @BeforeEach, @AfterEach :Denotes a method should be executed before/after each test method
 * @BeforeAll, @AfterAll: >> >> that should >>  once before/after all test methods in the class
 *
 * class org.junit.jupiter.api.Assertions
 *
 * public static void assert[Not]Equals(xxx expected, xxx actual,[String message])
 *                    assertTrue(boolean condition,[String msg]),assertFalse(boolean con,[String msg])
 *
 *******************************************************************************************************/

package com.java.features.testing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
public class MyNumberParameterizedAddTest {
    private MyNumber n1,n2;
    private int result;

    @ValueSource //feeds inputs to constructor
    public static Iterable<Object[]>data(){
        System.out.println("");
        return Arrays.asList(new Object[][]{
                {new MyNumber(1), new MyNumber(2), 3},
                {new MyNumber(-1), new MyNumber(-2), -3},
                {new MyNumber(3), new MyNumber(-3), 0}
        });
    }
    /*****Constructor: gets input from @Source
     *
     *
     */
    public MyNumberParameterizedAddTest(MyNumber n1, MyNumber n2, int res){
        this.n1=n1;
        this.n2=n2;
        this.result = res;
        System.out.println("number1 = "+n1.getNumber()+", number2="+n2.getNumber()+
                ", result="+result);
    }

    @BeforeEach
    public void setUp() throws Exception{
        System.out.println("Run @BeforeEach");
        System.out.println("number1 ="+n1.getNumber()+", number2 = "+n2.getNumber());

    }
    @AfterEach
    public void tearDown() throws Exception{

    }

}

package com.cloud.commons.test;

import org.junit.jupiter.api.Test;

public class JavaCommonTest {

    class TestOne{



        private int i=0;

        //与private int 级别一样
        {
            i=2;
            //System.out.println("{i="+i+"}");
        }
        //在变量赋值后执行
        public TestOne(int i){
            //i=1;
            System.out.println("testOne,i="+i);
        }









        public void printI(){
            System.out.println("i="+i);
        }
    }
    @Test
    public void testOne(){
        TestOne testOne=new TestOne
                (10){

            {
                System.out.println("suppp");
            }

           


            @Override
            public void printI() {
                super.printI();
            }
        };
        testOne.printI();
    }
}

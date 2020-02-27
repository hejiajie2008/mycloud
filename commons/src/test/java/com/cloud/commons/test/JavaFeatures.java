package com.cloud.commons.test;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Java8新特性练习
 */
public class JavaFeatures {

    public static void testTwoFun(String uu){
        System.out.println("uu="+uu);
    }
    public static void testThreeFun(Runnable runnable){
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch ( Exception e){

        }
        System.out.println(runnable);
        //这里执行countDownLatch：countdown方法
        runnable.run();
        System.out.println("runnable");
    }

    @Test
    public void testOne(){
        new Thread(()->System.out.println("new runnable start one")).start();
        new Thread(()->
        {
            System.out.println("new runnable start two");
            System.out.println("other new runnable start two");
        }
        ).start();
    }



    @Test
    public void testTwo(){
        List<String> list=new ArrayList<>();
        list.addAll(Arrays.asList(new String[]{"aa", "bb","cc"}));
        list.forEach(JavaFeatures::testTwoFun);

        List<Runnable> list2=new ArrayList<>();
        list2.addAll(Arrays.asList(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                }catch (Exception e){

                }

                System.out.println("run1");
            }
        },new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                }catch (Exception e){

                }
                System.out.println("run2");
            }
        }));

        list2.forEach(Runnable::run);
        System.out.println("end");
    }

    @Test
    public void testThree() throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(1);
        JavaFeatures.testThreeFun(countDownLatch::countDown);
        countDownLatch.await ();
        System.out.println("okk");
    }


}

package com.cloud.commons.test;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DesignTest {

    class TestOne extends Observable{

        @Override
        protected  void setChanged() {
            super.setChanged();
        }
    }

    @Test
    public void testOne(){
        TestOne observer=new TestOne();
        observer.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                System.out.println(arg);
            }
        });
        observer.setChanged();
        observer.notifyObservers("okk");
    }

    static Map<String,Object> map=new ConcurrentHashMap<String,Object>(8){{
        put("one","one");
        put("two","one");
        put("three","one");
    }};
    private static void print(String s, Object o) {
        System.out.println("key="+s+",value="+o);
        map.remove("three");
    }


    @Test
    public void testTwo(){

        map.forEach(DesignTest::print);


    }




}

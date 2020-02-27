package com.cloud.commons.test;

import org.junit.jupiter.api.Test;

/**
 * 线程练习
 */
public class ThreadTest {

     class ThreadTestOne extends Thread{
        private  int count = 0;
        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
        public void run() {
            while(true){
                System.out.println(count);
                count++;
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class ThreadTestTwo{
       public  int i=0;
    }

     //程序可见性
     @Test
    public  void testOne() throws InterruptedException {
         ThreadTestOne threadTestOne=new ThreadTestOne();
         threadTestOne.start();

         while(true){
             if(threadTestOne.getCount()==2){
                 threadTestOne.setCount(100);
             }
         }
    }

    //字节码重组
    @Test
    public void testTwo(){
         for( int i=0;i<1000_1000;i++){
             ThreadTestTwo two=new ThreadTestTwo();
             Thread t1= new Thread(()->{
                 two.i=1;
             });
             final int j=i;
             Thread t2=new Thread(()->{
                if(two.i==0){
                    System.out.println("diffent:"+j+","+two.i);
                }

             });
             t1.start();
             t2.start();
         }
    }
}

package com.cloud.commons;

public class Three {
    {

        try{
            Class.forName("com.cloud.commons.Two");
        }catch (Exception e){

        }
        System.out.println("three start");
    }

    public static void main(String[] args) {
        new Three();
    }
}

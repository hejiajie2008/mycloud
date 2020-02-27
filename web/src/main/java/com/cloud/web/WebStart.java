package com.cloud.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication(scanBasePackages = "com.cloud.web.*")
public class WebStart {

    public static void main(String[] args) {

         SpringApplication springApplication=new SpringApplication(WebStart.class);


         springApplication.addListeners(new ApplicationListener
                 <ApplicationEvent>(){

             @Override
             public void onApplicationEvent(ApplicationEvent event) {
                 System.out.println("onApplicationEvent");
             }
         });


         springApplication.run(args);
    }
}

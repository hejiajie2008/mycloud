package com.cloud.web.controller;

import com.cloud.api.vo.UserVo;
import com.cloud.web.vo.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {


    @Autowired
    private Environment environment;
    @PostMapping(value="/person/toProperties",produces="application/test+person",consumes = "application/json")
    public Person toProperties(@RequestBody @Validated Person person){
        return person;
    }

    @PostMapping(value="/person/toJson",consumes="application/test+person",produces = "application/json")
    public Person toJson(@RequestBody Person person){
        return person;
    }




   @GetMapping(value = "test")
    public String test(Model model){
        String author=String.valueOf(model.getAttribute("author"));
        return author;
   }

    @GetMapping(value = "test2")
    @ResponseBody
    public String test2(Model model){
        String author=String.valueOf(model.getAttribute("author"));
        int n=1/0;
        return author;
    }

    @GetMapping(value = "test3")

    public String test3(){
        return environment.getProperty("server.port");
    }
}

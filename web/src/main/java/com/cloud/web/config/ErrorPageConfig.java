package com.cloud.web.config;

import org.springframework.context.i18n.LocaleContext;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ControllerAdvice
public class ErrorPageConfig {

    @InitBinder
    public void initBiding(WebDataBinder webDataBinder){
        System.out.println("webDataBinder="+webDataBinder);
    }

    @ModelAttribute
    public void bidingModelAttribute(Model model){
        model.addAttribute("author","hejiajie");
    }
    @ExceptionHandler
    @ResponseBody
    public Map<String,Object> errorHandler(Exception e, ServerWebExchange serverWebExchange){
        Locale locale=serverWebExchange.getLocaleContext().getLocale();
        Map<String,Object> result=new HashMap<>();
        result.put("code",1000);
        result.put("message",e.getMessage());
        return result;
    }
}

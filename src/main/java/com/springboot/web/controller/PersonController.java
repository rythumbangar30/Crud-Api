package com.springboot.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


public class PersonController {
    @RequestMapping("/")
    public String home(){
        System.out.println("This is home page");
        return "home";
    }
}

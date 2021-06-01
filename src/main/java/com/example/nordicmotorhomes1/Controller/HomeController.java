package com.example.nordicmotorhomes1.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /* The method calls for the front HTML page of the application. */
    @GetMapping("/")
    public String index(){
        return "home/index";
    }
}

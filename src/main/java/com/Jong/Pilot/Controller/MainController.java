package com.Jong.Pilot.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/Home")
    public String returnHome(){
        return "Home";
    }
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }



}

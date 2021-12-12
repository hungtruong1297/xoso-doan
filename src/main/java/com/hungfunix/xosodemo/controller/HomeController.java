package com.hungfunix.xosodemo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class HomeController {

    @GetMapping
    public String home(Model model) {
        model.addAttribute("title", "Hello Thymeleaf from Java");
        return "home";
    }

}

// https://www.youtube.com/watch?v=J8I3s0sSP0c&list=PLVz2XdJiJQxynOpTm0DuufOkfWHNamJsF&ab_channel=JavaTechie
package com.hungfunix.xosodemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// localhost:8080/rest/auth/getMsg
@RestController
@RequestMapping("/rest/auth")
public class ApplicationController {

    @GetMapping("/getMsg")
    public String greeting() {
        return "Spring security example.";
    }
}

// https://www.youtube.com/watch?v=J8I3s0sSP0c&list=PLVz2XdJiJQxynOpTm0DuufOkfWHNamJsF&ab_channel=JavaTechie
package com.hungfunix.xosodemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// localhost:8080/noAuth/rest/sayHi
@RestController
@RequestMapping("/noAuth/rest")
public class NoAuthController {

    @GetMapping("/sayHi")
    public String sayHi() {
        return "Hello from No Auth Controller";
    }
}

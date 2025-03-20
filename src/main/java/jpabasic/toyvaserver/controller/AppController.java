package jpabasic.toyvaserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/")
    public String home(){
        return "hello, home";
    }

    @GetMapping("/test")
    public String home2(){
        return "test";
    }
}

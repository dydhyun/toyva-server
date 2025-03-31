package jpabasic.toyvaserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/test")
    public String home2(){
        return "hello docker test";
    }
}

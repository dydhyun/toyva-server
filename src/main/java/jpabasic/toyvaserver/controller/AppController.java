package jpabasic.toyvaserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/")
    public String home(){
        return "hello, home";
    }

    @GetMapping("/2")
    public String home2(){
        return "hello,hello, home";
    }
}

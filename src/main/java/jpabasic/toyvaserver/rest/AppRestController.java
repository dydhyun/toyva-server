package jpabasic.toyvaserver.rest;

import org.springframework.stereotype.Controller;

@Controller
public class AppRestController {

    public String index(){
        return "index";
    }

}

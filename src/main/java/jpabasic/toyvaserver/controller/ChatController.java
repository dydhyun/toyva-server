package jpabasic.toyvaserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @GetMapping("testchatserver")
    public String test() {
        return "forward:/chat/test.jsp";
    }

    @GetMapping("chat")
    public String chatPage(){
        System.out.println("connect /chat");
        return "forward:/chat/chat.jsp";
    }

}

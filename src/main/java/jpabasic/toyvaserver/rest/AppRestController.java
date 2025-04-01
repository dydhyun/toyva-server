package jpabasic.toyvaserver.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AppRestController {

    @GetMapping("/test")
    public String home2(){
        return "hello docker test";
    }

    @GetMapping("v0/chat")
    public Map<String, String> getChatData() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "채팅 API입니다!");
        return response; // JSON 반환
    }

}

package com.asuscomm.yangyinetwork.index;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jaeyoung on 2017. 5. 5..
 */
@RestController
public class IndexController {
    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}

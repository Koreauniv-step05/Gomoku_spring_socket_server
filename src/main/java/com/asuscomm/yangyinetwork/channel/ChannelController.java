package com.asuscomm.yangyinetwork.channel;

/**
 * Created by jaeyoung on 2017. 5. 5..
 */
@RestController
public class ChannelController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}

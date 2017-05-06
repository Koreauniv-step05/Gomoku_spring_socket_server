package com.asuscomm.yangyinetwork.websocket.controller;

import com.asuscomm.yangyinetwork.websocket.domain.Command;
import com.asuscomm.yangyinetwork.websocket.domain.CommandReply;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by jaeyoung on 2017. 5. 5..
 */

@Slf4j
@Controller
public class CommandReplyController {
    @MessageMapping("/general/command")
    @SendTo("/topic/command/general")
    public CommandReply commandReply(Command<Object> command) throws Exception {
        log.info("CommandReplyController/commandReply: [{}]",command.toString());

        return new CommandReply("testCommand", "test", "hi");
    }
}

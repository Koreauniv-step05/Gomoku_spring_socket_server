package com.asuscomm.yangyinetwork.websocket.channel.controller;

import com.asuscomm.yangyinetwork.config.Commands;
import com.asuscomm.yangyinetwork.websocket.channel.domain.Command;
import com.asuscomm.yangyinetwork.websocket.channel.domain.CommandReply;
import com.asuscomm.yangyinetwork.websocket.channel.service.GeneralCommandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by jaeyoung on 2017. 5. 5..
 */

@Slf4j
@Controller
public class GeneralSocketController {
    @MessageMapping("/general/command")
    @SendTo("/topic/command/general")
    public CommandReply commandReply(Command<Object> command) throws Exception {
        log.info("CommandReplyController/commandReply: [{}]",command.toString());
        CommandReply commandReply = null;

        switch (command.getCommand()) {
            case Commands.GENERAL.TO_SERVER.JOIN_SOMEWHERE:
                commandReply = GeneralCommandService.getInstance().joinSomewhere();
                break;
            case Commands.GENERAL.TO_SERVER.JOIN_CHANNEL:
                String channelId = (String)command.getContent();
                commandReply = GeneralCommandService.getInstance().joinChannel(channelId);
                break;
            case Commands.GENERAL.TO_SERVER.GET_CHANNELS:
                break;
            default:
                commandReply = new CommandReply("testCommand", "test", "hi");
                break;
        }


        return commandReply;
    }

}

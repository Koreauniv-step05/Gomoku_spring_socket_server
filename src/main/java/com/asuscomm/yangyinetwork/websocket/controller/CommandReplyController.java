package com.asuscomm.yangyinetwork.websocket.controller;

import com.asuscomm.yangyinetwork.websocket.controller.consts.Commands;
import com.asuscomm.yangyinetwork.websocket.domain.Command;
import com.asuscomm.yangyinetwork.websocket.domain.CommandReply;
import com.asuscomm.yangyinetwork.websocket.service.GeneralCommandService;
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
public class CommandReplyController {
    @MessageMapping("/general/command")
    @SendTo("/topic/command/general")
    public CommandReply commandReply(Command<Object> command) throws Exception {
        log.info("CommandReplyController/commandReply: [{}]",command.toString());
        CommandReply commandReply = null;

        switch (command.getCommand()) {
            case Commands.TO_SERVER.JOIN_SOMEWHERE:
                commandReply = GeneralCommandService.getInstance().joinSomewhere();
                break;
            case Commands.TO_SERVER.JOIN_CHANNEL:
                break;
            case Commands.TO_SERVER.GET_CHANNELS:
                break;
            default:
                commandReply = new CommandReply("testCommand", "test", "hi");
                break;
        }


        return commandReply;
    }

    @MessageMapping("/{channel}/command")
    @SendTo("/topic/command/{channel}")
    public CommandReply channelCommandReply(@DestinationVariable String channel, Command<Object> command) throws Exception {
        log.info("CommandReplyController/channelCommandReply: [{}]",channel);
        CommandReply commandReply = null;
        commandReply = new CommandReply("testCommand", "test", "hi");
        return commandReply;
    }

}

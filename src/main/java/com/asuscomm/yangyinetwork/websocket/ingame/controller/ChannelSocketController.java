package com.asuscomm.yangyinetwork.websocket.ingame.controller;

import com.asuscomm.yangyinetwork.websocket.channel.controller.consts.Commands;
import com.asuscomm.yangyinetwork.websocket.channel.domain.Command;
import com.asuscomm.yangyinetwork.websocket.channel.domain.CommandReply;
import com.asuscomm.yangyinetwork.websocket.channel.service.GeneralCommandService;
import com.asuscomm.yangyinetwork.websocket.ingame.domain.SocketMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
@Slf4j
public class ChannelSocketController {
    public static ChannelSocketController mInstance;

    public static ChannelSocketController getInstance() {
        if (mInstance == null) {
            mInstance = new ChannelSocketController();
        }
        return mInstance;
    }

    @MessageMapping("/{channel}/to_server")
    @SendToUser("/topic/to_server/{channel}")
    public SocketMessage toServer(@DestinationVariable String channel, SocketMessage<Object> socketMessage) throws Exception {
        log.info("CommandReplyController/commandReply: [{}]",socketMessage.toString());
        SocketMessage replySocketMessage = null;

        replySocketMessage = new SocketMessage("testCommand", "test", "only you can listen.");

        return replySocketMessage;
    }


    @SendTo("/topic/to_client/{channel}")
    public SocketMessage toClient(@DestinationVariable String channel, SocketMessage<Object> socketMessage) throws Exception {
        log.info("ChannelSocketController/toClient: [{}]",socketMessage.toString());
        return socketMessage;
    }
}

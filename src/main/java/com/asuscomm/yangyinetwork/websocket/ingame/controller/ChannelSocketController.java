package com.asuscomm.yangyinetwork.websocket.ingame.controller;

import com.asuscomm.yangyinetwork.websocket.ingame.controller.socket.SpringClient;
import com.asuscomm.yangyinetwork.websocket.ingame.controller.socket.client.SocketClient;
import com.asuscomm.yangyinetwork.websocket.ingame.domain.SocketMessage;
import com.asuscomm.yangyinetwork.websocket.ingame.domain.SocketStonePoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
@Slf4j
@Controller
public class ChannelSocketController {
    public List<SpringClient> mSpringClients = new ArrayList<>();
    public static ChannelSocketController mInstance;

    public static ChannelSocketController getInstance() {
        if (mInstance == null) {
            mInstance = new ChannelSocketController();
        }
        return mInstance;
    }

    public void addSpringClient(SpringClient springClient) {
        mSpringClients.add(springClient);
        log.info("ChannelSocketController/addSpringClient: [{}]",mSpringClients.toString());
    }

    @MessageMapping("/{channel}/to_server")
    @SendToUser("/topic/to_server/{channel}")
    public SocketMessage toServer(@DestinationVariable String channel, SocketStonePoint socketMessage) throws Exception {
        log.info("CommandReplyController/commandReply: [{}]",socketMessage.toString());
        SpringClient receiver = null;
        SocketMessage replySocketMessage = null;

        for (SpringClient springClient:
                ChannelSocketController.getInstance().mSpringClients) {
//            log.info("ChannelSocketController/toServer: ChannelId : [{}]",springClient.getChannelId());
            if (channel.equals(springClient.getChannelId())) {
                receiver = springClient;
                break;
            }
        }

        if(receiver == null) {
            log.info("ChannelSocketController/toServer: Receiver is null");
        } else {
            receiver.toServer(socketMessage);
        }
        return null;
    }

    public SocketMessage toClient(String channel, SocketMessage<Object> socketMessage) throws Exception {
        log.info("ChannelSocketController/toClient: channel = [{}], message = [{}]",channel, socketMessage.toString());
        SocketClient.getInstance().sendChannelSocketMessage(channel, socketMessage);
        return socketMessage;
    }
}

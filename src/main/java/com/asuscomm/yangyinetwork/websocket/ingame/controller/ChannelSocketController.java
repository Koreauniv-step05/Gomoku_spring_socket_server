package com.asuscomm.yangyinetwork.websocket.ingame.controller;

import com.asuscomm.yangyinetwork.websocket.channel.controller.consts.Commands;
import com.asuscomm.yangyinetwork.websocket.channel.domain.Command;
import com.asuscomm.yangyinetwork.websocket.channel.domain.CommandReply;
import com.asuscomm.yangyinetwork.websocket.channel.service.GeneralCommandService;
import com.asuscomm.yangyinetwork.websocket.ingame.controller.socket.SpringClient;
import com.asuscomm.yangyinetwork.websocket.ingame.controller.socket.client.SocketClient;
import com.asuscomm.yangyinetwork.websocket.ingame.domain.SocketMessage;
import com.asuscomm.yangyinetwork.websocket.ingame.domain.SocketStonePoint;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import org.springframework.web.socket.sockjs.frame.Jackson2SockJsMessageCodec;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.SocketHandler;

import static com.asuscomm.yangyinetwork.websocket.commons.config.SocketClientConfig.SOCKET_HOST;
import static com.asuscomm.yangyinetwork.websocket.commons.config.SocketClientConfig.SOCKET_PORT;
import static com.asuscomm.yangyinetwork.websocket.commons.config.SocketClientConfig.SOCKET_URL;

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
    public SocketMessage toServer(@DestinationVariable String channel, SocketMessage<Object> socketMessage) throws Exception {
        log.info("CommandReplyController/commandReply: [{}]",socketMessage.toString());
        SpringClient receiver = null;
        SocketMessage replySocketMessage = null;

        for (SpringClient springClient:
                ChannelSocketController.getInstance().mSpringClients) {
            log.info("ChannelSocketController/toServer: ChannelId : [{}]",springClient.getChannelId());
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
        log.info("ChannelSocketController/toClient: channel = [{}], message = ",channel, socketMessage.toString());
        SocketClient.getInstance().sendChannelSocketMessage(channel, socketMessage);
        return socketMessage;
    }
}

package com.asuscomm.yangyinetwork.websocket.commons.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

/**
 * Created by jaeyoung on 2017. 5. 5..
 */

@Slf4j
public class CustomChannelInterceptor extends ChannelInterceptorAdapter {
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

//        log.info("preSend message=[{}], channel=[{}]", message, channel);

//		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
//
//		if(StompCommand.CONNECT.equals(accessor.getCommand())){
//
//		}

        return message;
    }

    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {

//        log.info("postSend message=[{}], channel=[{}], sent=[{}]", message, channel, sent);

//		StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
//
//		if(StompCommand.CONNECT.equals(accessor.getCommand())){
//
//		}
    }
}
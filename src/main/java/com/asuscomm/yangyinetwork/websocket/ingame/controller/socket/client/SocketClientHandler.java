package com.asuscomm.yangyinetwork.websocket.ingame.controller.socket.client;

import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

/**
 * Created by jaeyoung on 2017. 5. 8..
 */
public class SocketClientHandler extends StompSessionHandlerAdapter {
    public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
    }
}
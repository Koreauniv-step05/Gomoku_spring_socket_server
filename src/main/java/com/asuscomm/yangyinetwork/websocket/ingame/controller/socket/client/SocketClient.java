package com.asuscomm.yangyinetwork.websocket.ingame.controller.socket.client;

import com.asuscomm.yangyinetwork.websocket.ingame.domain.SocketMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import org.springframework.web.socket.sockjs.frame.Jackson2SockJsMessageCodec;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.asuscomm.yangyinetwork.config.SocketClientConfig.*;

/**
 * Created by jaeyoung on 2017. 5. 8..
 */
@Slf4j
public class SocketClient {
    private static SocketClient mInstance;
    ListenableFuture<StompSession> f;
    private final WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
    private StompSession stompSession;

    String url = SOCKET_URL;
    String host = SOCKET_HOST;
    int port = SOCKET_PORT;

    public static SocketClient getInstance() {
        if (mInstance == null) {
            mInstance = new SocketClient();
        }
        return mInstance;
    }


    public SocketClient() {
        this.f = this.connect();
        try {
            this.stompSession = this.f.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private ListenableFuture<StompSession> connect() {
        Transport webSocketTransport = new WebSocketTransport(new StandardWebSocketClient());
        List<Transport> transports = Collections.singletonList(webSocketTransport);

        SockJsClient sockJsClient = new SockJsClient(transports);
        sockJsClient.setMessageCodec(new Jackson2SockJsMessageCodec());

        WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
        ListenableFuture<StompSession> result = null;

        result = stompClient.connect(url, headers, new SocketClientHandler(), host, port);
        return result;
    }

    public void sendChannelSocketMessage(String channelId, SocketMessage socketMessage) {
        log.info("SocketClient/sendChannelSocketMessage: [{}]");
        sendSocketMessage("/topic/to_client/" + channelId, socketMessage);
    }

    private void sendSocketMessage(String topic, SocketMessage socketMessage) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(socketMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        stompSession.send(topic, json.getBytes());
    }
}
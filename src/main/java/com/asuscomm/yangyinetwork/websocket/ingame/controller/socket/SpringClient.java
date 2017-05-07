package com.asuscomm.yangyinetwork.websocket.ingame.controller.socket;

import com.asuscomm.yangyinetwork.websocket.ingame.domain.SocketMessage;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
public interface SpringClient {
    void toClient(SocketMessage<Object> command);
}

package com.asuscomm.yangyinetwork.websocket.ingame.controller.socket;

import com.asuscomm.yangyinetwork.websocket.ingame.domain.SocketMessage;
import com.asuscomm.yangyinetwork.websocket.ingame.domain.SocketStonePoint;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
public interface SpringClient {
    interface OnToServerListener {
        void toServer(SocketMessage<Object> socketMessage);
    }
    void setListener(OnToServerListener listener);
    void toClient(SocketMessage<Object> socketMessage);
    void toServer(SocketMessage<Object> socketMessage);
    String getChannelId();
}

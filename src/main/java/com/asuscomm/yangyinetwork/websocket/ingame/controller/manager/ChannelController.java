package com.asuscomm.yangyinetwork.websocket.ingame.controller.manager;

import com.asuscomm.yangyinetwork.websocket.ingame.domain.SocketMessage;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
public interface ChannelController {
    void onToServer(SocketMessage socketMessage);
    String getChannelId();
}

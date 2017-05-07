package com.asuscomm.yangyinetwork.websocket.ingame.controller.manager;


/**
 * Created by jaeyoung on 2017. 5. 7..
 */
public interface ChannelControllerManager {
    ChannelController findControllerByChannelId(String channelId);
    void makeNewChannelController(String channelId);
}

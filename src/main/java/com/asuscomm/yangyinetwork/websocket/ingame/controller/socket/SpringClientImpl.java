package com.asuscomm.yangyinetwork.websocket.ingame.controller.socket;

import com.asuscomm.yangyinetwork.websocket.ingame.controller.ChannelSocketController;
import com.asuscomm.yangyinetwork.websocket.ingame.domain.SocketMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
@Slf4j
public class SpringClientImpl implements SpringClient {
    private String mChannel;

    public SpringClientImpl(String mChannel) {
        this.mChannel = mChannel;
    }

    @Override
    public void toClient(SocketMessage<Object> command) {
        log.info("SpringClientImpl/toClient: [{}]",command.toString());
        try {
            ChannelSocketController.getInstance().toClient(mChannel, command);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

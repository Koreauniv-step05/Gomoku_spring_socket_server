package com.asuscomm.yangyinetwork.websocket.ingame.controller.socket;

import com.asuscomm.yangyinetwork.websocket.ingame.controller.ChannelSocketController;
import com.asuscomm.yangyinetwork.websocket.ingame.domain.SocketMessage;
import com.asuscomm.yangyinetwork.websocket.ingame.domain.SocketStonePoint;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
@Slf4j
public class SpringClientImpl implements SpringClient {
    private String mChannel;
    private OnToServerListener mListener;

    public String getChannelId() {
        return mChannel;
    }

    public SpringClientImpl(String mChannel) {
        this.mChannel = mChannel;
        log.info("SpringClientImpl/SpringClientImpl: ");
        ChannelSocketController.getInstance().addSpringClient(this);
    }

    @Override
    public void setListener(OnToServerListener listener) {
        mListener = listener;
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

    public void toServer(SocketMessage<Object> socketMessage) {
        log.info("SpringClientImpl/toServer: [{}]", socketMessage.toString());
        mListener.toServer(socketMessage);
    }
}

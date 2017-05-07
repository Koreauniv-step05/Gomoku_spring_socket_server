package com.asuscomm.yangyinetwork.websocket.ingame.controller.manager;

import com.asuscomm.yangyinetwork.websocket.ingame.domain.SocketMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
public class ChannelControllerManagerImpl implements ChannelControllerManager {
    private List<ChannelController> mChannelControllers;
    private static ChannelControllerManagerImpl mInstance;

    private ChannelControllerManagerImpl() {
        this.mChannelControllers = new ArrayList<>();
    }

    public static ChannelControllerManagerImpl getInstance() {
        if (mInstance == null) {
            mInstance = new ChannelControllerManagerImpl();
        }
        return mInstance;
    }


    public void sendToServerMessageByChannelId(String channelId, SocketMessage socketMessage) {
        this.findControllerByChannelId(channelId).onToServer(socketMessage);
    }

    @Override
    public ChannelController findControllerByChannelId(String channelId) {
        for (ChannelController controller:
             mChannelControllers) {
            if (channelId.equals(controller.getChannelId())) {
                return controller;
            }
        }
        // exception
        return null;
    }

    @Override
    public void makeNewChannelController(String channelId) {
        this.mChannelControllers.add(new ChannelControllerImpl(channelId));
    }
}

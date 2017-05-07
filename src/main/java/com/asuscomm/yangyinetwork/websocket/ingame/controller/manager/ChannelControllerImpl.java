package com.asuscomm.yangyinetwork.websocket.ingame.controller.manager;

import com.asuscomm.yangyinetwork.game.controller.GameController;
import com.asuscomm.yangyinetwork.game.controller.GameControllerImpl;
import com.asuscomm.yangyinetwork.websocket.ingame.controller.adapter.GameAndSocketAdapter;
import com.asuscomm.yangyinetwork.websocket.ingame.controller.socket.SpringClient;
import com.asuscomm.yangyinetwork.websocket.ingame.controller.socket.SpringClientImpl;
import com.asuscomm.yangyinetwork.websocket.ingame.domain.SocketMessage;
import com.asuscomm.yangyinetwork.websocket.ingame.domain.StonePoint;

import static com.asuscomm.yangyinetwork.websocket.ingame.consts.Commands.ON_NEW_STONE_FROM_CLIENT;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
public class ChannelControllerImpl implements ChannelController {
    private String mChannelId;
    private GameAndSocketAdapter mGameAndSocketAdapter;
    private GameController mGameController;
    private SpringClient mSpringClient;

    public ChannelControllerImpl(String channelId) {
        this.mChannelId = channelId;
        this.mSpringClient = new SpringClientImpl(channelId);
        this.mGameController = new GameControllerImpl();
        this.mGameAndSocketAdapter = new GameAndSocketAdapter(this.mGameController, this.mSpringClient);

    }



    public String getChannelId() {
        return mChannelId;
    }

    public void onToServer(SocketMessage socketMessage) {
        String command = socketMessage.getCommand();
        switch(command) {
            case ON_NEW_STONE_FROM_CLIENT:
                StonePoint stonePoint = (StonePoint) socketMessage.getContent();
                this.mGameAndSocketAdapter.onNewStoneFromClient(stonePoint);
                break;
        }
    }
}

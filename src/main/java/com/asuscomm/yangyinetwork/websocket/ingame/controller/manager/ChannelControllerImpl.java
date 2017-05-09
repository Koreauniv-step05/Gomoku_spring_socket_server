package com.asuscomm.yangyinetwork.websocket.ingame.controller.manager;

import com.asuscomm.yangyinetwork.game.controller.GameController;
import com.asuscomm.yangyinetwork.game.controller.GameControllerImpl;
import com.asuscomm.yangyinetwork.websocket.channel.domain.OnYourTurn;
import com.asuscomm.yangyinetwork.websocket.ingame.controller.socket.SpringClient;
import com.asuscomm.yangyinetwork.websocket.ingame.controller.socket.SpringClientImpl;
import com.asuscomm.yangyinetwork.websocket.ingame.domain.SocketMessage;
import com.asuscomm.yangyinetwork.websocket.ingame.domain.SocketStonePoint;
import com.asuscomm.yangyinetwork.websocket.ingame.domain.StonePoint;
import lombok.extern.slf4j.Slf4j;

import static com.asuscomm.yangyinetwork.config.Commands.CHANNEL.ON_NEW_STONE_FROM_CLIENT;
import static com.asuscomm.yangyinetwork.config.Commands.CHANNEL.ON_YOUR_TURN;
import static com.asuscomm.yangyinetwork.config.GAME_BOARD.DEFAULT_BOARD_SIZE;
import static com.asuscomm.yangyinetwork.config.GAME_DELAY.REPEAT_DELAY;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
@Slf4j
public class ChannelControllerImpl implements ChannelController, GameController.GameControllerListener, SpringClient.OnToServerListener{
    private String mChannelId;
    private GameController mGameController;
    private SpringClient mSpringClient;
    boolean repeatFlag = true;

    private SocketMessage lastMessage = null;

    public ChannelControllerImpl(String channelId) {
        log.info("ChannelControllerImpl/ChannelControllerImpl: ");
        this.mChannelId = channelId;
        this.mSpringClient = new SpringClientImpl(channelId);
        this.mSpringClient.setListener(this);
        this.mGameController = new GameControllerImpl(DEFAULT_BOARD_SIZE,this);
        initLastMessage();
        new Thread(repeatLastMessage).start();
    }
    
    private void initLastMessage() {
        
    }

    private Runnable repeatLastMessage = new Runnable() {
        @Override
        public void run() {
            log.info("ChannelControllerImpl/run: ");
            while(true) {
//                if(repeatFlag) {
//                    if (lastMessage != null) {
//                        log.info("ChannelControllerImpl/run: Call toClient");
//                        mSpringClient.toClient(lastMessage);
//                    } else {
//                        log.info("ChannelControllerImpl/run: null message");
//                    }
//                } else {
//                    repeatFlag = true;
//                }

                try {
                    Thread.sleep(REPEAT_DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };


    public String getChannelId() {
        return mChannelId;
    }

    @Override
    public void onYourTurn(int stoneType, int[][] board) {
        SocketMessage socketMessage = new SocketMessage(ON_YOUR_TURN, ON_YOUR_TURN, new OnYourTurn(stoneType, board));
        log.info("ChannelControllerImpl/onYourTurn: [{}]", socketMessage);
        sendToClient(socketMessage);
    }

    private void sendToClient(SocketMessage socketMessage) {
        repeatFlag = false;
        lastMessage = socketMessage;
        mSpringClient.toClient(socketMessage);
    }

    @Override
    public void onNewStone(int[] newStonePoint, int stoneType) {

    }

    @Override
    public void showMsg(String msg) {

    }

    @Override
    public void toServer(SocketStonePoint socketMessage) {
        log.info("ChannelControllerImpl/toServer: [{}]");
        String command = socketMessage.getCommand();
        switch(command) {
            case ON_NEW_STONE_FROM_CLIENT:
                log.info("ChannelControllerImpl/toServer: ON_NEW_STONE_FROM_CLIENT "+socketMessage.getContent().toString());
                StonePoint stonePoint = (StonePoint) socketMessage.getContent();
                this.mGameController.onNewStone(stonePoint);
                break;
        }
    }
}

package com.asuscomm.yangyinetwork.websocket.ingame.controller.adapter;

import com.asuscomm.yangyinetwork.game.controller.GameController;
import com.asuscomm.yangyinetwork.websocket.ingame.controller.socket.GameClientListener;
import com.asuscomm.yangyinetwork.websocket.ingame.controller.socket.SpringClient;
import com.asuscomm.yangyinetwork.websocket.ingame.domain.SocketMessage;
import com.asuscomm.yangyinetwork.websocket.ingame.domain.StonePoint;

import static com.asuscomm.yangyinetwork.game.consts.GAME_BOARD.BLACK_STONE;
import static com.asuscomm.yangyinetwork.game.consts.GAME_BOARD.WHITE_STONE;
import static com.asuscomm.yangyinetwork.websocket.ingame.consts.Commands.*;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
//public class GameAndSocketAdapter implements GameController.GameControllerListener, GameClientListener {
//    private GameController mGameController;
//    private SpringClient mSpringClient;
//
//    public GameAndSocketAdapter(GameController gameController, SpringClient springClient) {
//        this.mGameController = gameController;
//        this.mGameController.addGameControllerListener(this);
//        this.mSpringClient = springClient;
//    }
//
//    @Override
//    public void onYourTurn(int stoneType, int[][] board) {
//        String command = ON_YOUR_TURN;
//        Integer content;
//        if (stoneType == BLACK_STONE) {
//            content = BLACK_STONE;
//        } else {
//            content = WHITE_STONE;
//        }
//        SocketMessage socketMessage = new SocketMessage(command,command,content);
//        this.mSpringClient.toClient(socketMessage);
//    }
//
//    @Override
//    public void onNewStone(int[] newStonePoint, int stoneType) {
//        String command = ON_NEW_STONE;
//        StonePoint content = new StonePoint(newStonePoint, stoneType);
//
//        SocketMessage socketMessage = new SocketMessage(command,command,content);
//        this.mSpringClient.toClient(socketMessage);
//    }
//
//    @Override
//    public void showMsg(String msg) {
//        String command = ON_SYSTEM_MESSAGE;
//        String content = msg;
//
//        SocketMessage socketMessage = new SocketMessage(command,command,content);
//        this.mSpringClient.toClient(socketMessage);
//    }
//
//    @Override
//    public void onNewStoneFromClient(StonePoint stonePoint) {
//        this.onNewStoneFromClient(stonePoint.getStonePoint(),stonePoint.getStoneType());
//    }
//
//    @Override
//    public void onNewStoneFromClient(int[] newStonePoint, int stoneType) {
//        this.mGameController.onNewStone(newStonePoint,stoneType);
//    }
//}

package com.asuscomm.yangyinetwork.game.controller;

import com.asuscomm.yangyinetwork.websocket.ingame.domain.StonePoint;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
public interface GameController {
    interface GameControllerListener {
        void onYourTurn(int stoneType, int[][] board);
        void onNewStone(int newStonePoint[], int stoneType);
        void showMsg(String msg);
    }

    void addGameControllerListener(GameControllerListener listener);
    void removeGameControllerListener(GameControllerListener listener);
    void onNewStone(int[] newStonePoint, int stoneType);
    void onNewStone(StonePoint stonePoint);
}

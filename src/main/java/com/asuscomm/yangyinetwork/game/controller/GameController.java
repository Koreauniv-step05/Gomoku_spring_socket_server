package com.asuscomm.yangyinetwork.game.controller;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
public interface GameController {
    interface GameControllerListener {
        void onYourTurn(int stoneType);
        void onNewStone(int newStonePoint[], int stoneType);
        void showMsg(String msg);
    }

    void addGameControllerListener(GameControllerListener listener);
    void removeGameControllerListener(GameControllerListener listener);
    void onNewStone(int[] newStonePoint, int stoneType);
}

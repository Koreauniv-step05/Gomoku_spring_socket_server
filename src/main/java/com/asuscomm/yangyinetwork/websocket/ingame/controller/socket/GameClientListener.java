package com.asuscomm.yangyinetwork.websocket.ingame.controller.socket;

import com.asuscomm.yangyinetwork.websocket.ingame.domain.StonePoint;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
public interface GameClientListener {
    void onNewStoneFromClient(int[] newStonePoint, int stoneType);
    void onNewStoneFromClient(StonePoint stonePoint);
}

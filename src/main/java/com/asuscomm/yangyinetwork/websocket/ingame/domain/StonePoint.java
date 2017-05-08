package com.asuscomm.yangyinetwork.websocket.ingame.domain;

import java.util.Arrays;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
public class StonePoint {
    private int[] stonePoint;
    private int stoneType;

    public StonePoint() {
    }

    public StonePoint(int[] stonePoint, int stoneType) {
        this.stonePoint = stonePoint;
        this.stoneType = stoneType;
    }

    public int[] getStonePoint() {
        return stonePoint;
    }

    public void setStonePoint(int[] stonePoint) {
        this.stonePoint = stonePoint;
    }

    public int getStoneType() {
        return stoneType;
    }

    public void setStoneType(int stoneType) {
        this.stoneType = stoneType;
    }

    @Override
    public String toString() {
        return "StonePoint{" +
                "stonePoint=" + Arrays.toString(stonePoint) +
                ", stoneType=" + stoneType +
                '}';
    }
}

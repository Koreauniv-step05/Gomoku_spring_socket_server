package com.asuscomm.yangyinetwork.websocket.ingame.domain;

import java.util.Arrays;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
public class StonePoint {
    private int[][] stonePoint;
    private int remainStones;
    private int stoneType;

    public StonePoint() {
    }

    public StonePoint(int[][] stonePoint, int remainStones, int stoneType) {
        this.stonePoint = stonePoint;
        this.remainStones = remainStones;
        this.stoneType = stoneType;
    }

    public int[][] getStonePoint() {
        return stonePoint;
    }

    public void setStonePoint(int[][] stonePoint) {
        this.stonePoint = stonePoint;
    }

    public int getStoneType() {
        return stoneType;
    }

    public void setStoneType(int stoneType) {
        this.stoneType = stoneType;
    }
}

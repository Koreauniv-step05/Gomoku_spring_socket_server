package com.asuscomm.yangyinetwork.repo;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jaeyoung on 2017. 5. 9..
 */
public class StonePointForDB {
    private List<Integer> stonePoint;
    private int stoneType;

    public StonePointForDB() {
    }

    public StonePointForDB(int[] stonePoint, int stoneType) {
        this.stonePoint = Arrays.asList(ArrayUtils.toObject(stonePoint));
        this.stoneType = stoneType;
    }

    public List<Integer> getStonePoint() {
        return stonePoint;
    }

    public void setStonePoint(List<Integer> stonePoint) {
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
        return "StonePointForDB{" +
                "stonePoint=" + stonePoint +
                ", stoneType=" + stoneType +
                '}';
    }
}


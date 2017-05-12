package com.asuscomm.yangyinetwork.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * Created by jaeyoung on 2017. 5. 12..
 */
@Slf4j
public class PrintUtils {
    public static void printStonePointPairs(int[][] stonePointPairs) {
        log.info("PrintUtils/printStonePointPairs: stonePointPairs.length=[{}]",stonePointPairs.length);
        for (int i = 0; i < stonePointPairs.length; i++) {
            log.info("printStonePointPairs: [{}]",Arrays.toString(stonePointPairs[i]));
        }

    }
}

package com.asuscomm.yangyinetwork.game.controller;

import java.util.ArrayList;

import static com.asuscomm.yangyinetwork.game.consts.GAME_BOARD.NONE_STONE;
import static com.asuscomm.yangyinetwork.game.consts.GAME_BOARD.X;
import static com.asuscomm.yangyinetwork.game.consts.GAME_BOARD.Y;

/**
 * Created by jaeyoung on 2017. 5. 8..
 */
public class RuleChecker {
    public static boolean isValidStone(int[][] board, int[] stonePoint) {
        int BOARD_SIZE = board.length;
        if (stonePoint[X] >= BOARD_SIZE || stonePoint[X] < 0 ||
                stonePoint[Y] >= BOARD_SIZE || stonePoint[Y] < 0) {
            return false;
        }
        if (board[stonePoint[X]][stonePoint[Y]] == NONE_STONE) {
            // System.out.println("GameController: isValidStone true");
            return true;
        }
        else {
            // System.out.println("GameController: isValidStone false");
            return false;
        }
    }

    public static boolean isGameEnd(int[][] board, int[] stonePoint) {

        return false;
    }
}

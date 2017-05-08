package com.asuscomm.yangyinetwork.game.controller;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static com.asuscomm.yangyinetwork.game.consts.GAME_BOARD.*;
import static com.asuscomm.yangyinetwork.game.controller.RuleChecker.UPANDDOWN.DOWN;
import static com.asuscomm.yangyinetwork.game.controller.RuleChecker.UPANDDOWN.END;
import static com.asuscomm.yangyinetwork.game.controller.RuleChecker.UPANDDOWN.UP;

/**
 * Created by jaeyoung on 2017. 5. 8..
 */
@Slf4j
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
        } else {
            // System.out.println("GameController: isValidStone false");
            return false;
        }
    }

    interface UPANDDOWN {
        int UP = 0;
        int DOWN = 1;
        int END = 2;
    }

    public static List<int[]> isGameEnd(int[][] board, int[] stonePoint) {
        int[][] DIRS = {
                {1, +1},

                {1, 0},

                {0, +1},

                {1, -1},
        };

        int stoneType = board[stonePoint[X]][stonePoint[Y]];
        List<int[]> trace = new ArrayList<int[]>();

        for (int[] DIR :
                DIRS) {
            int count = 0;
            trace.clear();
            for (int upanddownFlag = UP; upanddownFlag != END; upanddownFlag++) {
                int[] SDIR = DIR.clone();
                int[] i = stonePoint.clone();

                if (upanddownFlag == DOWN) {
                    SDIR[X] = -SDIR[X];
                    SDIR[Y] = -SDIR[Y];
//                    System.out.println("DOWN "+SDIR[X]+", "+SDIR[Y]);
                } else {
//                    System.out.println("UP "+SDIR[X]+", "+SDIR[Y]);
                }

                int iter = 0;
                while (true) {
                    i[X] += SDIR[X];
                    i[Y] += SDIR[Y];
                    iter++;
                    if (!(i[X] >= 0 && i[X] < DEFAULT_BOARD_SIZE
                            && i[Y] >= 0 && i[Y] < DEFAULT_BOARD_SIZE)) {
                        break;
                    }
                    if (stoneType == board[i[X]][i[Y]]) {
                        trace.add(i.clone());
                        count++;
//                        System.out.println("count="+count);
                    } else {
                        break;
                    }

                    if (count >= (NUM_CONNECT - 1)) {
                        trace.add(stonePoint.clone());
                        return trace;
                    }
                    if (iter > NUM_CONNECT) {
                        break;
                    }
                }
            }
        }
        return null;
    }
}

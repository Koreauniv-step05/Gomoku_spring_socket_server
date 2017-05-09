package com.asuscomm.yangyinetwork.game.controller;

import com.asuscomm.yangyinetwork.repo.Firebase;
import com.asuscomm.yangyinetwork.websocket.ingame.domain.StonePoint;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static com.asuscomm.yangyinetwork.config.GAME_BOARD.*;
import static com.asuscomm.yangyinetwork.config.GAME_DELAY.NEW_GAME_DELAY;
import static com.asuscomm.yangyinetwork.game.controller.RuleChecker.isGameEnd;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
@Slf4j
public class GameControllerImpl implements GameController {
    private int mTurn;
    private boolean mIsProcessing;
    private GameControllerListener mListener;
    private int mBoardSize;
    private int[][] mBoard;
    private String firebaseKey;
    private int remainStones;
    
    void sendListenersNewStone(int[] newStonePoint, int stoneType) {
        this.mListener.onNewStone(newStonePoint, stoneType);
    }


    public GameControllerImpl(int board_size, GameControllerListener listener) {
        this.mIsProcessing = true;
        this.mBoardSize = board_size;
        addGameControllerListener(listener);
        newGameStart();
    }

    private void newGameStart() {
        log.info("GameControllerImpl/newGameStart: ");
        this.mTurn = BLACK_STONE;
        this.mIsProcessing=false;
        this.initBoard();
        firebaseKey = Firebase.getInstance().saveGame();
        this.remainStones = 2;
        rotateTurn();
    }

    private void initBoard() {
        this.mBoard = new int[this.mBoardSize][this.mBoardSize];
        for(int i=0; i<this.mBoardSize; i++)
            for(int j=0; j<this.mBoardSize; j++) this.mBoard[i][j] = NONE_STONE;
    }

    
    public void rotateTurn() {
        log.info("GameControllerImpl/rotateTurn: ");
        this.remainStones--;
        if(this.remainStones==0) {
            this.remainStones = 2;
            if (mTurn == BLACK_STONE) {
                mTurn = WHITE_STONE;
            } else {
                mTurn = BLACK_STONE;
            }
        }
        this.mIsProcessing = false;
        this.mListener.onYourTurn(this.mTurn, this.mBoard);
    }

    @Override
    public void addGameControllerListener(GameControllerListener listener) {
        mListener = listener;
    }

    @Override
    public void removeGameControllerListener(GameControllerListener listener) {

    }

    @Override
    public void onNewStone(int[] newStonePoint, int stoneType) {
        log.info("GameControllerImpl/onNewStone: [{}]", newStonePoint[X]+","+newStonePoint[Y]);
        if(stoneType == mTurn) {
            if (!this.mIsProcessing) {
                this.mIsProcessing = true;
                if (RuleChecker.isValidStone(mBoard, newStonePoint)) { // isvalid?
                    updateBoard(newStonePoint, stoneType);
                    List<int[]> connectTrace = isGameEnd(mBoard, newStonePoint);
                    if (connectTrace != null) {
                        // todo save trace connectTrace
                        updateBoardWithTrace(connectTrace);
                        log.info("GameControllerImpl/updateBoard: gameEnd");
                        printBoard(mBoard);
                        try {
                            Thread.sleep(NEW_GAME_DELAY);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        newGameStart();
                    } else {

                        rotateTurn();
                    }
                } else {
                    this.mIsProcessing = false;
                    // send invalid
                }
            } else {
                log.info("GameControllerImpl/onNewStone: IsProcessing");
            }
        } else {
            log.info("GameControllerImpl/onNewStone: NotYourTurn");
        }
    }

    private  void updateBoardWithTrace(List<int[]> connectTrace){
        for (int[] eachTrace:
        connectTrace) {
            mBoard[eachTrace[X]][eachTrace[Y]] += 2;
        }
        Firebase.getInstance().saveBoard(firebaseKey,mBoard);
    }

    private void updateBoard(int[] newStonePoint, int stoneType) {
        mBoard[newStonePoint[X]][newStonePoint[Y]] = stoneType;
        Firebase.getInstance().saveStonePoint(firebaseKey, newStonePoint, stoneType);
        Firebase.getInstance().saveBoard(firebaseKey, mBoard);
    }

    @Override
    public void onNewStone(StonePoint stonePoint) {
        onNewStone(stonePoint.getStonePoint(), stonePoint.getStoneType());
    }

    private void printBoard(int[][] board) {
        String output = "";
        for (int[] row:
             board) {
            output += "\n";
            for (int each:
                 row) {
                output += each;
            }
        }
        log.info("GameControllerImpl/printBoard: [{}]",output);
    }
}

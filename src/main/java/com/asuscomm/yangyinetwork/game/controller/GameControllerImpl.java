package com.asuscomm.yangyinetwork.game.controller;

import com.asuscomm.yangyinetwork.websocket.ingame.domain.StonePoint;
import lombok.extern.slf4j.Slf4j;

import static com.asuscomm.yangyinetwork.game.consts.GAME_BOARD.*;
import static com.asuscomm.yangyinetwork.game.controller.RuleChecker.isGameEnd;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
@Slf4j
public class GameControllerImpl implements GameController {
//    private Player mPlayerBlack;
//    private Player mPlayerWhite;
    private int mTurn;
    private boolean mIsProcessing;
    private GameControllerListener mListener;
    private int mBoardSize;
    private int[][] mBoard;
    
    void sendListenersNewStone(int[] newStonePoint, int stoneType) {
        this.mListener.onNewStone(newStonePoint, stoneType);
    }


    public GameControllerImpl(int board_size, GameControllerListener listener) {
        this.mBoardSize = board_size;
        this.mTurn = WHITE_STONE;
        this.mIsProcessing=false;
        this.initBoard();
        addGameControllerListener(listener);
        rotateTurn();
    }

    private void initBoard() {
        this.mBoard = new int[this.mBoardSize][this.mBoardSize];
        for(int i=0; i<this.mBoardSize; i++)
            for(int j=0; j<this.mBoardSize; j++) this.mBoard[i][j] = NONE_STONE;
    }

    
    public void rotateTurn() {
        log.info("GameControllerImpl/rotateTurn: ");
        if(mTurn == BLACK_STONE) {
            mTurn = WHITE_STONE;
        } else {
            mTurn = BLACK_STONE;
        }
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
        log.info("GameControllerImpl/onNewStone: [{}]",newStonePoint.toString());
        if(RuleChecker.isValidStone(mBoard,newStonePoint)) { // isvalid?
            updateBoard(newStonePoint, stoneType);
            if(isGameEnd(mBoard,newStonePoint)) {
                log.info("GameControllerImpl/updateBoard: gameEnd");
            } else {
                rotateTurn();
            }
        } else {
            // send invalid
        }
    }

    private void updateBoard(int[] newStonePoint, int stoneType) {
        mBoard[newStonePoint[X]][newStonePoint[Y]] = stoneType;
    }

    @Override
    public void onNewStone(StonePoint stonePoint) {
        onNewStone(stonePoint.getStonePoint(), stonePoint.getStoneType());
    }
}

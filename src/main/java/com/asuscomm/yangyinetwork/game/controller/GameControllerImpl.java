package com.asuscomm.yangyinetwork.game.controller;

import com.asuscomm.yangyinetwork.websocket.ingame.domain.StonePoint;

import static com.asuscomm.yangyinetwork.game.consts.GAME_BOARD.*;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
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
        if(true) { // isvalid?
            // updateBoard
            rotateTurn();
        } else {
            // send invalid
        }
    }

    @Override
    public void onNewStone(StonePoint stonePoint) {
        onNewStone(stonePoint.getStonePoint(), stonePoint.getStoneType());
    }
}

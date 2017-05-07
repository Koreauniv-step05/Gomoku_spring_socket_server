package com.asuscomm.yangyinetwork.game.controller;

import static com.asuscomm.yangyinetwork.game.consts.GAME_BOARD.*;

/**
 * Created by jaeyoung on 2017. 5. 7..
 */
public class GameControllerImpl implements GameController {
//    private Player mPlayerBlack;
//    private Player mPlayerWhite;
    private int mTurn;
    private boolean mIsProcessing;
    private GameControllerListener gameControllerListener;
    private GameControllerListener whitePlayerListener;
    private int mBoardSize;
    private int[][] mBoard;
    
    void sendListenersNewStone(int[] newStonePoint, int stoneType) {
        this.gameControllerListener.onNewStone(newStonePoint, stoneType);
    }


    public GameControllerImpl(int board_size) {
        this.mBoardSize = board_size;
        this.mTurn = BLACK_STONE;
        this.mIsProcessing=false;
        this.initBoard();
    }

    public GameControllerImpl() {
        this.mBoardSize = DEFAULT_BOARD_SIZE;
        this.initBoard();
    }

    private void initBoard() {
        this.mBoard = new int[this.mBoardSize][this.mBoardSize];
        for(int i=0; i<this.mBoardSize; i++)
            for(int j=0; j<this.mBoardSize; j++) this.mBoard[i][j] = NONE_STONE;
    }

    
    public void rotateTurn() {
        this.gameControllerListener.onYourTurn(this.mTurn);
    }

    @Override
    public void addGameControllerListener(GameControllerListener listener) {

    }

    @Override
    public void removeGameControllerListener(GameControllerListener listener) {

    }

    @Override
    public void onNewStone(int[] newStonePoint, int stoneType) {

    }
}

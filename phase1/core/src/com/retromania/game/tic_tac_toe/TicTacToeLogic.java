package com.retromania.game.tic_tac_toe;

import com.badlogic.gdx.graphics.Texture;

public class TicTacToeLogic {
    Cell[] board;
    int index;
    boolean nowinner = false;

    public TicTacToeLogic(Cell[] board){
        this.board = board;
    }

    public boolean isEnd(){
        for (int i = 0; i < 3; i++) {
          if (this.board[i].equal(this.board[i+3]) && this.board[i].equal(this.board[i+6])) {
            System.out.println("yes row check");
            this.index = i;
            return true;
          }
          else if (this.board[3*i].equal(this.board[3*i+1]) && this.board[3*i].equal(this.board[3*i+2])){
              this.index = 3*i;
              return true;
          }
          else if (this.board[0].equal(this.board[4]) && this.board[0].equal(this.board[8])){
              index = 0;
              return true;
          }
          else if (this.board[2].equal(this.board[4]) && this.board[2].equal(this.board[6])){
              index = 2;
              return true;
          }
        }
        for (int i = 0; i < 9; i++){
            if (this.board[i].isTouched == false){
                return false;
            }
        }
        nowinner = true;
        return true;
    }

    public Texture winner(){
        return this.board[index].getCell();
    }
}

package com.retromania.game.tic_tac_toe;

import com.badlogic.gdx.graphics.Texture;
import com.retromania.game.shared_abstractions.Individual;
import com.retromania.game.shared_abstractions.RetroManiaScreen;

public class Cell implements Individual {
    public boolean isTouched;
    private String shape = "None";
    public String player;
    int X,Y,W,H;

  public Cell(int cellWidth, int cellHeight, int cellX, int cellY) {
      isTouched = false;
      this.W = cellWidth;
      this.H = cellHeight;
      this.X = cellX;
      this.Y = cellY;
        System.out.println("W" + this.W + "H" + this.H + "X" + this.X + "Y" + this.Y);
    }
    public void setCell(String shape){
        this.shape = shape;
    }

    public void setPlayer(String player) {this.player = player;}

    public String getCell(){
        return this.shape;
    }

    protected void setCellWidth(int width){
      this.W = width;
    }

    protected void setCellHeight(int height){
      this.H = height;
    }

    protected void setCellX(int x){
      this.X = x;
    }

    protected void setCellY(int y){
      this.Y = y;
    }

    public boolean inCell(float X, float Y){
        if( X >= this.X && X < this.X + this.W){
            if(Y >= this.Y && Y < this.Y + this.H){
                return true;
            }
        }
        return false;
    }

    public boolean equal(Cell other){
        if (!this.getCell().equals("None")){
            if (this.getCell().equals(other.getCell())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void update(Object... args) {
        setCell((String)args[0]);
    }

}

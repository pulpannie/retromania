package com.retromania.game.tic_tac_toe;

import com.badlogic.gdx.graphics.Texture;
import com.retromania.game.shared_abstractions.Individual;
import com.retromania.game.shared_abstractions.RetroManiaScreen;

public class Cell extends Individual {
    public boolean isTouched;
    RetroManiaScreen screen;
    int type;
    private Texture shape;
    public String player;
    int X,Y,W,H;

  public Cell(RetroManiaScreen screen, int type, float gameWidth, float gameHeight) {
        isTouched = false;
        this.screen = screen;
        this.type = type;
        this.W = (int)(gameWidth/3);
        this.H = (int)(gameHeight/3);
        if(type % 3 == 0){
            this.X = 0;
        }
        else if (type % 3 == 1){
            this.X = (int)gameWidth/3;
        }
        else{
            this.X = (int)gameWidth*2/3;
        }
        if(type/3 == 0){
            this.Y = 0;
        }
        else if(type/3 == 1){
            this.Y = (int)gameHeight*2/3;
        }
        else{
            this.Y = (int)gameHeight/3;;
        }
    System.out.println("made!");
    }
    public void setCell(Texture shape){
        this.shape = shape;
    }

    public void setPlayer(String player) {this.player = player;}

    public Texture getCell(){
        return shape;
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
      if (this.getCell() != null && other.getCell() != null){
          if (this.getCell() == other.getCell()){
              return true;
          }
      }
      return false;
    }

    @Override
    public void update(Object... args) {
        setCell((Texture)args[0]);
    }

}

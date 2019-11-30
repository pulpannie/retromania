package com.retromania.game.tic_tac_toe.individuals;

import com.retromania.game.shared_abstractions.Individual;

public class Cell implements Individual {
    private boolean isTouched;
    private String shape;

  public Cell() {
      isTouched = false;
      shape = "None";
    }
    public void setCell(String shape){
        this.shape = shape;
    }


    public String getCell(){
        return this.shape;
    }

    public boolean getIsTouched(){
      return isTouched;
    }

    public void setIsTouched(boolean bool){
      isTouched = bool;
    }

    protected Cell copyCell(){
      Cell tmpCell = new Cell();
      tmpCell.isTouched = this.isTouched;
      tmpCell.shape = this.shape;
      return tmpCell;
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

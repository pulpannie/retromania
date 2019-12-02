package com.retromania.game.mario.models.player;
/**
 *
 * A wrapper for the information the main player needs before it can be updated
 *
 * **/
public class MainPlayerInput {

    private int worldWidth;
    private int worldHeight;
    private int X;
    private int Y;
    private boolean hasBeenTouched;
    private boolean hasBeenHeldDown;

    public MainPlayerInput(int worldWidth,
                    int worldHeight,
                    int X,
                    int Y,
                    boolean hasBeenTouched,
                    boolean hasBeenHeldDown){
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;
        this.X = X;
        this.Y = Y;
        this.hasBeenTouched = hasBeenTouched;
        this.hasBeenHeldDown = hasBeenHeldDown;
    }



    public int getWorldWidth() {
        return worldWidth;
    }

    public int getWorldHeight() {
        return worldHeight;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public boolean hasBeenTouched() {
        return hasBeenTouched;
    }

    public boolean hasBeenHeldDown() {
        return hasBeenHeldDown;
    }
}
package com.retromania.game.spaceship_shooter;

import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;

import java.util.List;

public class SpaceShipShooterStarter extends RetroManiaInnerGame {


    public SpaceShipShooterStarter(RetroManiaGame game){
        super(game, "Spaceship shooter", RetroManiaGame.Orientation.HORIZONTAL);
    }

    public void save(Object... args) {

    }

    @Override
    public List<Object> retreave() {
        return null;
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

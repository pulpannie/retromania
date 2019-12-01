package com.retromania.game.special_mario.views.mission;

import com.badlogic.gdx.Gdx;
import com.retromania.game.GameLister;
import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.special_mario.abstractions.DeathObserver;
import com.retromania.game.special_mario.abstractions.FinisherObserver;
import com.retromania.game.special_mario.models.player.MainPlayer;
import com.retromania.game.special_mario.models.player.MainPlayerInput;
import com.retromania.game.special_mario.presenter.MarioGamePresenter;
import com.retromania.game.special_mario.views.renderables.UserRenderPreference;


public abstract class MissionView extends RetroManiaScreen implements DeathObserver, FinisherObserver {
    private UserRenderPreference userRenderPreference;
    private MarioGamePresenter marioGamePresenter;
    MainPlayer mainPlayer;
    public MissionView(
            MarioGamePresenter marioGamePresenter,
            UserRenderPreference userRenderPreference, MainPlayer mainPlayer) {
//        This is to redo any position or state of the player for the game
        mainPlayer.createMainPlayer();

        this.marioGamePresenter = marioGamePresenter;
        this.marioGamePresenter.reloadLevel();
        this.marioGamePresenter.addDeathObserver(this);
        this.marioGamePresenter.addFinisherObserver(this);
        this.mainPlayer = mainPlayer;
        setUpMainWorldRenderer(userRenderPreference);
    }


    @Override
    public void handleInput() {
        setUpMainPlayerInput();
    }

    @Override
    public void update() {
        handleInput();
        updateModels();
        marioGamePresenter.present();
    }

    private void setUpMainPlayerInput() {
        marioGamePresenter.setMainPlayerInput(
                new MainPlayerInput(
                        userRenderPreference.getRenderable().getGamePort().getScreenWidth(),
                        userRenderPreference.getRenderable().getGamePort().getScreenHeight(),
                        Gdx.input.getX(),
                        Gdx.input.getY(),
                        Gdx.input.justTouched(),
                        Gdx.input.isTouched()));
    }

    @Override
    public void resize(int width, int height) {
        userRenderPreference.getRenderable().resize(width, height);
    }

    @Override
    public void show() {
    }


    @Override
    public void render(float delta) {
        update();
        userRenderPreference.getRenderable().render(delta);
    }

    private void setUpMainWorldRenderer(UserRenderPreference userRenderPreference) {
        this.userRenderPreference = userRenderPreference;
    }

    @Override
    public void deathSeen() {
        dispose();
    }

    @Override
    public void finishSeen() {
        dispose();
    }

    @Override
    public void dispose() {
//        This is to redo any position or state of the player for the next game
        mainPlayer.createMainPlayer();
        game.setScreen(new GameLister());
    }
}

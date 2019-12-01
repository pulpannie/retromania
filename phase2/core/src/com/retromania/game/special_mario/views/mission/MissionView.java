package com.retromania.game.special_mario.views.mission;

import com.badlogic.gdx.Gdx;
import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.special_mario.models.map.MainPlayerInput;
import com.retromania.game.special_mario.presenter.MarioGamePresenter;
import com.retromania.game.special_mario.presenter.SpecialMarioStarterPresenter;
import com.retromania.game.special_mario.views.renderables.UserRenderPreference;


public abstract class MissionView extends RetroManiaScreen {
    private UserRenderPreference userRenderPreference;
    private MarioGamePresenter marioGamePresenter;

    public MissionView(
            MarioGamePresenter marioGamePresenter,
            UserRenderPreference userRenderPreference) {
        this.marioGamePresenter = marioGamePresenter;
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
}

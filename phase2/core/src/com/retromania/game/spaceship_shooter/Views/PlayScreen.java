package com.retromania.game.spaceship_shooter.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.spaceship_shooter.Models.ImageButtonBuilder;
import com.retromania.game.spaceship_shooter.Presenters.PlayScreenPresenter;


public class PlayScreen extends RetroManiaScreen {
    private Stage stage;
    private ImageButton leftButton;
    private ImageButton rightButton;
    private ImageButton pauseButton;
    private PlayScreenPresenter presenter;

    public PlayScreen(MainScreenInterface mainScreen) {
        presenter = new PlayScreenPresenter("stretch", 4, mainScreen);
        stage = new Stage(presenter.getGamePort(), RetroMania.getRetroManiaInstance().sb);


        leftButton = (new ImageButtonBuilder()).buildTexture("left_button_big.png").buildButton();
        leftButton.setPosition(0, 0);
        leftButton.setSize(250, 250);
        stage.addActor(leftButton);

        rightButton = (new ImageButtonBuilder()).buildTexture("right_button_big.png").buildButton();
        rightButton.setPosition(Gdx.graphics.getWidth()- 250, 0);
        rightButton.setSize(250, 250);
        stage.addActor(rightButton);

        pauseButton = (new ImageButtonBuilder()).buildTexture("pause.png").buildButton();
        pauseButton.setPosition(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 350);
        pauseButton.setSize(180, 180);
        stage.addActor(pauseButton);
        Gdx.input.setInputProcessor(stage);


    }
    @Override
    public void show() {
        stage.addActor(pauseButton);
        stage.addActor(rightButton);
        stage.addActor(leftButton);
        Gdx.input.setInputProcessor(stage);
    }

    public void handleInput(){
        if (rightButton.isPressed()) {
            presenter.moveCarRight();
        }
        else if(leftButton.isPressed()){
            presenter.moveCarLeft();
        }
        else if(pauseButton.isPressed()){
            pause();
        }
        else if(Gdx.input.isTouched())
            presenter.shoot();

    }

    public void update(float dt){
        handleInput();
        stage.act();
        presenter.update(dt);
        if (presenter.isFinished())
            endGame();
    }

    @Override
    public void render(final float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        RetroMania.getRetroManiaInstance().sb.begin();
        presenter.getBackground().draw(RetroMania.getRetroManiaInstance().sb, delta);
        for (Actor actor: presenter.getRenderableActors())
            actor.draw(RetroMania.getRetroManiaInstance().sb, delta);
        RetroMania.getRetroManiaInstance().sb.end();
        stage.draw();
        presenter.getHudStage().draw();

    }

    @Override
    public void resize(int width, int height) {
        presenter.resize(width, height);
    }

    @Override
    public void pause() {
        dispose();
        presenter.pause();
    }

    @Override
    public void resume() {

    }

    private void endGame() {
        dispose();
        presenter.endGame();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}


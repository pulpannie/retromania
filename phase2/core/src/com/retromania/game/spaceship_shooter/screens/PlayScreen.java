package com.retromania.game.spaceship_shooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.spaceship_shooter.SpaceShipShooterStarter;
import com.retromania.game.spaceship_shooter.individuals.Car;
import com.retromania.game.spaceship_shooter.individuals.Hud;
import com.retromania.game.spaceship_shooter.individuals.ImageButtonBuilder;
import com.retromania.game.spaceship_shooter.individuals.UfoManager;
import com.retromania.game.spaceship_shooter.utils.PlayerScreenRenderer;


public class PlayScreen extends RetroManiaScreen {
    private Stage stage;
    private ImageButton leftButton;
    private ImageButton rightButton;
    private ImageButton pauseButton;
    private MainScreenInterface mainscreen;
    PlayerScreenRenderer renderer;

    public PlayScreen(MainScreenInterface mainscreen) {
        renderer = new PlayerScreenRenderer("stretch", new Hud(RetroMania.getRetroManiaInstance().sb), new Car(), new UfoManager(4));
        this.mainscreen = mainscreen;
        stage = new Stage(renderer.getGamePort(), RetroMania.getRetroManiaInstance().sb);


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
            renderer.getPlayer().moveRight();
        }
        else if(leftButton.isPressed()){
            renderer.getPlayer().moveLeft();
        }
        else if(pauseButton.isPressed()){
            pause();
        }

        else if(Gdx.input.isTouched())
            renderer.getPlayer().shoot();

    }

    public void update(float dt){
        handleInput();
        stage.act();
        if (renderer.isFinished())
            endGame();
    }

    @Override
    public void render(final float delta) {
        update(delta);
        renderer.render(delta);
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }

    @Override
    public void pause() {
        dispose();
        mainscreen.pause();
    }

    @Override
    public void resume() {

    }

    public void endGame() {
        dispose();
        SpaceShipShooterStarter.getGameStats().update(renderer.getScore());
        this.mainscreen.getUser().setScore(SpaceShipShooterStarter.getGameStats().getHighScore());
        this.mainscreen.returnMenu();
        mainscreen.save();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}


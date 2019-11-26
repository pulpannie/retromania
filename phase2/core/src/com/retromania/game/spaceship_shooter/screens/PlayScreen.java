package com.retromania.game.spaceship_shooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.spaceship_shooter.SpaceShipShooterStarter;
import com.retromania.game.spaceship_shooter.individuals.Background;
import com.retromania.game.spaceship_shooter.individuals.Car;
import com.retromania.game.spaceship_shooter.individuals.Hud;
import com.retromania.game.spaceship_shooter.individuals.ImageButtonBuilder;
import com.retromania.game.spaceship_shooter.individuals.UfoManager;


public class PlayScreen extends RetroManiaScreen {
    private RetroManiaGame game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;
    private Car player;
    private Background background;
    private UfoManager ufoManager;

    static Stage stage;
    static ImageButton leftButton;
    static ImageButton rightButton;
    static ImageButton pauseButton;
    MainScreenInterface mainscreen;

    public PlayScreen(RetroManiaGame game, MainScreenInterface mainscreen){
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gamecam);
        hud = new Hud(game.sb);
        player = new Car();
        this.mainscreen = mainscreen;

        stage = new Stage(gamePort, game.sb);


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

        background = new Background();
        ufoManager = new UfoManager(4);

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
            player.moveRight();
            if (!player.goingRight)
                player.turnSide();
        }
        else if(leftButton.isPressed()){
            player.moveLeft();
            if (player.goingRight)
                player.turnSide();


        }
        else if(pauseButton.isPressed()){
            pause();
        }

        else if(Gdx.input.isTouched())
            player.shoot();

    }

    public void update(float dt){
        handleInput();

        ufoManager.update(player.getiRocket(), hud);
        gamecam.update();

        if (hud.countDown(dt))
            endGame();
    }

    @Override
    public void render(final float delta) {
        update(delta);

        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.sb.setProjectionMatrix(hud.stage.getCamera().combined);
        game.sb.begin();
        background.draw(game.sb, delta);
        if (player.shooted())
            player.getiRocket().draw(game.sb, delta);
        player.draw(game.sb, delta);
        ufoManager.draw(game.sb, delta);
        game.sb.end();
        hud.stage.draw();
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {
        stage.dispose();
        mainscreen.pause();
    }

    @Override
    public void resume() {

    }

    public void endGame() {
        stage.dispose();
        SpaceShipShooterStarter.getGameStats().update(hud.getScore());
        this.mainscreen.getUser().setScore(SpaceShipShooterStarter.getGameStats().getHighScore());
        game.setScreen(SpaceShipShooterStarter.getMenuScreen());
        mainscreen.save();
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}


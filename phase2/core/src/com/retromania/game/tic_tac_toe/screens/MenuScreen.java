package com.retromania.game.tic_tac_toe.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.shared_abstractions.RetroManiaScreen;

public class MenuScreen extends RetroManiaScreen {
    public Stage stage;
    public String currentTurn;
    public SpriteBatch batch;
    public OrthographicCamera gamecam;
    BitmapFont font = new BitmapFont();
    public float gameWidth;
    public float gameHeight;
    ImageButton playButton;
    Viewport viewport;

    public MenuScreen(){
        gamecam = new OrthographicCamera();
        gameWidth = Gdx.graphics.getWidth();
        gameHeight = Gdx.graphics.getHeight();
        viewport = new FitViewport(gameWidth, gameHeight, gamecam);
        stage = new Stage(viewport, game.sb);
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
        Table table = new Table();
        table.setBounds(0, 0, gameWidth, gameHeight);
        playButton = new ImageButton( new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("play_tictactoe.png")))));
        playButton.setSize(500,500);
        playButton.setPosition(gameWidth/2 - 250, gameHeight/2-200);
        stage.addActor(playButton);

    }

    @Override
    public void handleInput() {
        if (Gdx.input.isTouched()) {
            Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            System.out.println(mousePos);
            Vector3 worldCoordinates = gamecam.unproject(mousePos);
            if (worldCoordinates.x >= gameWidth/2 - 250 && worldCoordinates.x <= gameWidth/2 +250){
                if (worldCoordinates.y >= gameHeight/2-200 && worldCoordinates.y <=gameWidth/2+300){
                    System.out.println("CLICKED!!!");
                    game.setScreen(new PlayScreen());
                }
            }
        }
    }

    @Override
    public void show() {


    }

    @Override
    public void render(float delta) {
        handleInput();
        Gdx.gl.glClearColor(1, 1, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        game.sb.setProjectionMatrix(stage.getCamera().combined);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public void dispose(){
        stage.dispose();
    }
}

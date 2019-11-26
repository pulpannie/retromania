package com.retromania.game.colour_shooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.colour_shooter.ColourShooterStarter;
import com.retromania.game.colour_shooter.individuals.Background;
import com.retromania.game.colour_shooter.individuals.Square;
import com.retromania.game.colour_shooter.individuals.Tank;
import com.retromania.game.shared_abstractions.RetroManiaGame;

public class PlayScreen implements Screen {
    private RetroManiaGame game;
    static Stage stage;
    ColourShooterStarter mainscreen;

    public OrthographicCamera camera;
    public SpriteBatch batch;
    private int width;
    private int height;
    Square square;
    int i = 0;

    public PlayScreen(RetroManiaGame game, ColourShooterStarter mainscreen) {
        this.game = game;
        this.mainscreen = mainscreen;
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight(), camera));
        Gdx.input.setInputProcessor(stage);
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

    }

    @Override
    public void show() {
        Background background = new Background("play_screen");
        Image back_img = new Image(background.getBackgroundTexture());
        back_img.setSize(width, height);
        stage.addActor(back_img);

        Tank tank = new Tank(mainscreen.getTankPreference());
        Image tank_img = new Image(tank.getTankTexture());
        tank_img.setSize((float) (width * 0.3), (float) (height * 0.2));
        tank_img.setPosition((float)(width / 2) - (tank_img.getWidth() / 2), 0);
        stage.addActor(tank_img);

//        square = new Image(new Texture(Gdx.files.internal("colour_shooter/square.png")));
//        square.setSize((float) (width* 0.6), (float) (height * 0.3));
//        square.setPosition((float) (width / 2) - square.getWidth() / 2,
//                (float) (height * 0.6) - square.getHeight() / 2);
//        square.setScaling(Scaling.fit);
//        square.setOrigin(Align.center);
//        stage.addActor(square);
        World world = new World(new Vector2(0, -10), true);

        TextureRegion squareRegion = new TextureRegion();
        square = new Square(squareRegion, 0, 0, (int) (width* 0.4), (int) (height * 0.3), 1, world);
        square.setPosition((float) (width / 2) - square.getWidth() / 2,
                (float) (height * 0.6) - square.getHeight() / 2);
    }

    public void update(float dt) {
        stage.act(dt);
    }

    @Override
    public void render(final float delta) {
        update(delta);
        stage.draw();

//        square.rotateBy(2f);
        batch.begin();
        square.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    public void endGame() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
    }
}

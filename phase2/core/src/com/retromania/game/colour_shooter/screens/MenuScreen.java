package com.retromania.game.colour_shooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.colour_shooter.ColourShooterStarter;
import com.retromania.game.colour_shooter.individuals.Background;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class MenuScreen implements Screen {
    private RetroManiaGame game;
    static Stage stage;
    ColourShooterStarter mainscreen;

    public OrthographicCamera camera;
    public SpriteBatch batch;
    private int width;
    private int height;

    public MenuScreen(RetroManiaGame game, ColourShooterStarter mainscreen) {
        this.game = game;
        this.mainscreen = mainscreen;
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight(), camera));
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
    }


    @Override
    public void show() {
        Background background = new Background("menu screen");
        Image back_img = new Image(background.getBackgroundTexture());
        back_img.setSize(width, height);
        stage.addActor(back_img);

        Image title = new Image(new Texture(Gdx.files.internal("colour_shooter/title.png")));
        title.setSize((float) (width * 0.9), (float) (height * 0.16));
        title.setPosition((float)(width / 2) - (title.getWidth() / 2), (float) (height * 0.80));
        stage.addActor(title);

        Image play = new Image(new Texture(Gdx.files.internal("colour_shooter/play_wth_text.png")));
        play.setSize((float) (width * 0.4), (float) (height * 0.18));
        play.setPosition((float)(width / 2) - (title.getWidth() / 2), (float) (height * 0.6));
        play.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(mainscreen.getPlayScreen());
            }
        });

        stage.addActor(play);

        Image rules = new Image(new Texture(Gdx.files.internal("colour_shooter/rules_wth_txt.png")));
        rules.setSize((float) (width * 0.5), (float) (height * 0.18));
        rules.setPosition((float)(width / 2) - (title.getWidth() / 2), (float) (height * 0.4));
        stage.addActor(rules);

        Image skins = new Image(new Texture(Gdx.files.internal("colour_shooter/skins_wth_txt.png")));
        skins.setSize((float) (width * 0.5), (float) (height * 0.18));
        skins.setPosition((float)(width / 2) - (title.getWidth() / 2), (float) (height * 0.2));
        stage.addActor(skins);

//        Image back = new Image(new Texture(Gdx.files.internal("colour_shooter/back_wth_txt.png")));
//        skins.setSize((float) (width * 0.4), (float) (height * 0.18));
//        skins.setPosition((float)(width / 2) - (title.getWidth() / 2), (float) (height * 0.25));
//        stage.addActor(back);
        Gdx.input.setInputProcessor(stage);
    }

    public void update(float dt) {
        stage.act(dt);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        stage.draw();
        batch.begin();
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

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        batch.dispose();
    }
}

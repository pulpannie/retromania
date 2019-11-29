package com.retromania.game.spaceship_shooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.spaceship_shooter.individuals.Background;
import com.retromania.game.spaceship_shooter.individuals.Hud;
import com.retromania.game.spaceship_shooter.individuals.ImageButtonBuilder;
import com.retromania.game.spaceship_shooter.individuals.LabelBuilder;

public class SettingScreen extends RetroManiaScreen {
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;
    private Background background;
    Label gameModeLabel;
    Table table;

    private ImageButton exitButton;
    private Stage stage;
    private MainScreenInterface mainscreen;
    SelectBox<String> gameModeBox;

    public SettingScreen(MainScreenInterface mainscreen){
        gamecam = new OrthographicCamera();
        gamePort = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gamecam);
        this.mainscreen = mainscreen;
        stage = new Stage(gamePort, RetroMania.getRetroManiaInstance().sb);
        background = new Background();

        Skin skin = new Skin(Gdx.files.internal("spaceship_shooter/glassy/skin/glassy-ui.json"));
        gameModeLabel = (new LabelBuilder()).buildFont(3f).buildColor().buildLabelStyle().buildText("Choose game mode").buildLabel();
        gameModeBox = new SelectBox<String>(skin);
        gameModeBox.setItems("Independence day", "Halloween", "Christmas");
        gameModeBox.getStyle().font.getData().setScale(3f, 2f);
        gameModeBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameModeBox.setSelected(((SelectBox<String>) actor).getSelected());
            }
        });

        table = new Table();
        table.center();
        table.setFillParent(true);
        table.add(gameModeLabel).expandX().pad(5);
        table.row();
        table.add(gameModeBox).expandX().pad(10);
        Gdx.input.setInputProcessor(stage);

        exitButton = (new ImageButtonBuilder()).buildTexture("exit.png").buildButton();
        exitButton.setPosition(Gdx.graphics.getWidth()/2-100, -1);
        exitButton.setSize(200, 200);
        stage.addActor(exitButton);

    }
    @Override
    public void handleInput() {
        if (exitButton.isPressed()){
            returnMenu();
        }
    }

    @Override
    public void show() {
        stage.addActor(table);
        stage.addActor(exitButton);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(256,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        RetroMania.getRetroManiaInstance().sb.setProjectionMatrix(stage.getCamera().combined);
        RetroMania.getRetroManiaInstance().sb.begin();
        background.draw(game.sb, delta);

        RetroMania.getRetroManiaInstance().sb.end();
        stage.draw();
    }

    public void update(float dt){
        handleInput();

        gamecam.update();
        stage.act();
    }


    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    public void restart(){

    }

    public void returnMenu(){
        stage.dispose();
        mainscreen.returnMenu(gameModeBox.getSelected());
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

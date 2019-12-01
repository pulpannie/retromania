package com.retromania.game.spaceship_shooter.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.spaceship_shooter.Models.ImageButtonBuilder;
import com.retromania.game.spaceship_shooter.Models.LabelBuilder;
import com.retromania.game.spaceship_shooter.Presenters.SettingsScreenPresenter;

public class SettingScreen extends RetroManiaScreen {
    private Table table;
    private SettingsScreenPresenter presenter;

    private Viewport gamePort;
    private OrthographicCamera gameCam;

    private ImageButton exitButton;
    private Stage stage;
    private SelectBox<String> gameModeBox;
    private CheckBox musicBox;

    public SettingScreen(MainScreenInterface mainScreen){
        Label gameModeLabel;
        presenter = new SettingsScreenPresenter(mainScreen);

        gameCam = new OrthographicCamera();
        gamePort = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gameCam);

        stage = new Stage(gamePort, RetroMania.getRetroManiaInstance().sb);
        Skin skin = new Skin(Gdx.files.internal("spaceship_shooter/glassy/skin/glassy-ui.json"));

        gameModeLabel = (new LabelBuilder()).buildFont(3f).buildColor().buildLabelStyle().buildText("Choose game mode").buildLabel();
        gameModeBox = new SelectBox<>(skin);
        gameModeBox.setItems("Independence day", "Halloween", "Christmas");
        gameModeBox.getStyle().font.getData().setScale(3f, 2f);

        musicBox = new CheckBox("music", skin);

        table = new Table();
        table.center();
        table.setFillParent(true);
        table.add(gameModeLabel).expandX().pad(5);
        table.row();
        table.add(gameModeBox).expandX().pad(10);
        table.row();
        table.row();
        table.add(musicBox).expandX().pad(20);
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

    public void update(float dt){
        gameCam.update();
        handleInput();

        stage.act();
        presenter.update(dt);
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        RetroMania.getRetroManiaInstance().sb.begin();
        presenter.getBackground().draw(RetroMania.getRetroManiaInstance().sb, delta);

        RetroMania.getRetroManiaInstance().sb.end();
        stage.draw();
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

    private void returnMenu(){
        dispose();
        presenter.returnMenu(gameModeBox.getSelected(), musicBox.isChecked());
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

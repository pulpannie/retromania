package com.retromania.game.spaceship_shooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.spaceship_shooter.individuals.ImageButtonBuilder;
import com.retromania.game.spaceship_shooter.individuals.LabelBuilder;
import com.retromania.game.spaceship_shooter.utils.SettingScreenRenderer;

public class SettingScreen extends RetroManiaScreen {
    Label gameModeLabel;
    Table table;
    SettingScreenRenderer renderer;

    private ImageButton exitButton;
    private Stage stage;
    private MainScreenInterface mainscreen;
    SelectBox<String> gameModeBox;

    public SettingScreen(MainScreenInterface mainscreen){
        renderer = new SettingScreenRenderer("fill");
        stage = new Stage(renderer.getGamePort(), RetroMania.getRetroManiaInstance().sb);
        this.mainscreen = mainscreen;
        Skin skin = new Skin(Gdx.files.internal("spaceship_shooter/glassy/skin/glassy-ui.json"));
        gameModeLabel = (new LabelBuilder()).buildFont(3f).buildColor().buildLabelStyle().buildText("Choose game mode").buildLabel();
        gameModeBox = new SelectBox<String>(skin);
        gameModeBox.setItems("Independence day", "Halloween", "Christmas");
        gameModeBox.getStyle().font.getData().setScale(3f, 2f);

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
        renderer.render(delta);
        stage.draw();
    }

    public void update(float dt){
        handleInput();
        renderer.update(dt);
        stage.act();
    }


    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
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

package com.retromania.game.tic_tac_toe.presenters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.User;
import com.retromania.game.tic_tac_toe.individuals.ImageButtonBuilder;
import com.retromania.game.tic_tac_toe.individuals.ImageButtonBuilder;
import com.retromania.game.tic_tac_toe.screens.PlayScreen;
import com.retromania.game.tic_tac_toe.utils.UserPrefrence;

import java.util.ArrayList;

public class MenuPresenter{
    PlayScreen playScreen;
    ImageButtonBuilder imageButtonBuilder;
    ImageButton playButton, catButton, upButton, downButton;
    static UserPrefrence userPrefrence = new UserPrefrence();


    public MenuPresenter(PlayScreen playScreen) {
        this.playScreen = playScreen;
        imageButtonBuilder = new ImageButtonBuilder();
    }

    public ImageButton getPlayButton(){
        return playButton;
    }

    public UserPrefrence getUserPrefrence(){
        return userPrefrence;
    }


    public void returnPlayScreen(){
        RetroMania.getRetroManiaInstance().setScreen(playScreen);
    }


    public void buildButtons(){
        buildPlayButton();
        buildCatButton();
        buildSizeButtons();
    }

    public ImageButton getCatButton(){
        return catButton;
    }
    public ImageButton getUpButton(){
        return upButton;
    }
    public ImageButton getDownButton(){
        return downButton;
    }

    private void buildPlayButton(){
        playButton = imageButtonBuilder.buildButton(new Texture(Gdx.files.internal("tic_tac_toe/play_tictactoe.png")), 200, 200, Gdx.graphics.getWidth() / 2 - 110, Gdx.graphics.getHeight() / 2 - 70);
    }

    public void handleInput() {
        if (playButton.isPressed()) {
            RetroMania.getRetroManiaInstance().setScreen(playScreen);
        }
    }


    /**Create cat buttons, tutorial from "https://alvinalexander.com/source-code/how-create-libgdx-scene2d-imagebutton" */
    private void buildCatButton(){
        Texture catTexture = new Texture(Gdx.files.internal("tic_tac_toe/radio-off-button.png"));
        Texture catTexturePressed = new Texture(Gdx.files.internal("tic_tac_toe/radio-on-button.png"));
        catButton = imageButtonBuilder.buildButton(catTexture, catTexturePressed, 60, 60, Gdx.graphics.getWidth() / 2 - 220, Gdx.graphics.getHeight() / 3);
        catButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                userPrefrence.setCat(!userPrefrence.getCat());
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
    }

    private void buildSizeButtons(){
        upButton = imageButtonBuilder.buildButton(new Texture(Gdx.files.internal("tic_tac_toe/up.png")), 50, 50, Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 3 + 40);
        upButton.addListener(new ClickListener() {
                                 public void clicked(InputEvent event, float x, float y) {
                                     userPrefrence.addSize();
                                 }
                             }
        );

        downButton = imageButtonBuilder.buildButton(new Texture(Gdx.files.internal("tic_tac_toe/down.png")), 50, 50, Gdx.graphics.getWidth() / 2 - 50, Gdx.graphics.getHeight() / 3 - 40);
        downButton.addListener(new ClickListener() {
                                   public void clicked(InputEvent event, float x, float y) {
                                       if (userPrefrence.getGameSize() > 3) {
                                           userPrefrence.decreaseSize();
                                       }
                                   }
                               }
        );
    }


}

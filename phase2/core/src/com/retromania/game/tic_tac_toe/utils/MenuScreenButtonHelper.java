package com.retromania.game.tic_tac_toe.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retromania.game.tic_tac_toe.presenters.MenuPresenter;

import java.util.ArrayList;

public class MenuScreenButtonHelper {
    private ArrayList<ImageButton> imageButtons = new ArrayList<>();
    private ImageButton playButton, catButton, upButton, downButton;
    private ImageButtonBuilder imageButtonBuilder = new ImageButtonBuilder();
    MenuPresenter menuPresenter;

    public MenuScreenButtonHelper(MenuPresenter menuPresenter){
        this.menuPresenter = menuPresenter;
    }

    public ArrayList<ImageButton> makeButtons(){
        buildButtons();
        configureButtons();
        return imageButtons;
    }

    public ImageButton getPlayButton(){
        return playButton;
    }

    private void buildButtons() {
        buildPlayButton();
        buildCatButton();
        buildSizeButtons();
    }

    private void configureButtons(){
        playButton.setSize(250, 250);
        playButton.setPosition(Gdx.graphics.getWidth() / 2 - 130, Gdx.graphics.getHeight() / 3 + 30);
        catButton.setSize(60, 60);
        catButton.setPosition(Gdx.graphics.getWidth() / 2 - 120, Gdx.graphics.getHeight() / 4 - 50);
        upButton.setSize(50, 50);
        upButton.setPosition(Gdx.graphics.getWidth() / 2 + 50, Gdx.graphics.getHeight() / 4);
        downButton.setSize(50, 50);
        downButton.setPosition(Gdx.graphics.getWidth() / 2 + 50, Gdx.graphics.getHeight() / 4 - 100);
    }

    private void buildPlayButton() {
        playButton = imageButtonBuilder.buildButton("play_tictactoe.png");
        imageButtons.add(playButton);
    }

    /**
     * Create cat buttons, tutorial from "https://alvinalexander.com/source-code/how-create-libgdx-scene2d-imagebutton"
     */
    private void buildCatButton() {
        catButton = imageButtonBuilder.buildOnOffButton("radio-off-button.png", "radio-on-button.png");
        catButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                menuPresenter.reverseCat();
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        imageButtons.add(catButton);
    }

    private void buildSizeButtons() {
        upButton = imageButtonBuilder.buildButton("up.png");
        upButton.addListener(new ClickListener() {
                                 public void clicked(InputEvent event, float x, float y) {
                                     menuPresenter.addSize();
                                 }
                             }
        );
        imageButtons.add(upButton);

        downButton = imageButtonBuilder.buildButton("down.png");
        downButton.addListener(new ClickListener() {
                                   public void clicked(InputEvent event, float x, float y) {
                                       if (menuPresenter.getGameSize() > 3) {
                                           menuPresenter.decreaseSize();
                                       }
                                   }
                               }
        );
        imageButtons.add(downButton);
    }
}

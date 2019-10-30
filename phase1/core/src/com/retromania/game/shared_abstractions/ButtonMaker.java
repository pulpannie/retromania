package com.retromania.game.shared_abstractions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class ButtonMaker {

    public static TextButton makeButton(String text, int padding){
        TextureAtlas atlas = new TextureAtlas("buttons.pack");
        Skin skin = new Skin(atlas);


        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button.down");
        textButtonStyle.down = skin.getDrawable("button.up");
        textButtonStyle.checkedOffsetX = 1;
        textButtonStyle.checkedOffsetX = -1;
        textButtonStyle.font = new BitmapFont(Gdx.files.internal("black.fnt"), false);


        TextButton buttonExit = new TextButton(text, textButtonStyle);
        buttonExit.pad(padding);
        return buttonExit;

    }

    public static TextButton makeButton(String text){
        return makeButton(text, 0);
    }
}

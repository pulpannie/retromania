package com.retromania.game.spaceship_shooter.individuals;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;


public class LabelBuilder {
    CharSequence text;
    BitmapFont bitmapFont;
    Color color;
    Label.LabelStyle labelStyle;

    public LabelBuilder buildText(CharSequence text){
        this.text = text;
        return this;
    }

    public LabelBuilder buildFont(float size){
        bitmapFont = new BitmapFont();
        bitmapFont.getData().setScale(size);

        return this;
    }

    public LabelBuilder buildColor(){
        color = Color.WHITE;
        return this;
    }

    public LabelBuilder buildLabelStyle(){
        labelStyle = new Label.LabelStyle(bitmapFont, color);
        return this;
    }

    public Label buildLabel(){
        return new Label(text, labelStyle);
    }

}

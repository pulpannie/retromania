package com.retromania.game.spaceship_shooter.individuals;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Builder class of Label
 * */
public class LabelBuilder {
    /**
     * Variables:
     * text: string you want to show up in GUI
     * bitmapFont: font of text(including size)
     * color: color of text
     * labelStyle: display style of text
     * */
    private CharSequence text;
    private BitmapFont bitmapFont;
    private Color color;
    private Label.LabelStyle labelStyle;

    /**
     * Method that builds text
     *
     * @return instance of builder class to finish building label in 1 line
     * */
    public LabelBuilder buildText(CharSequence text){
        this.text = text;
        return this;
    }
    /**
     * Method that build font of text
     *
     * @return instance of builder class to finish building label in 1 line
     * */
    public LabelBuilder buildFont(float size){
        bitmapFont = new BitmapFont();
        bitmapFont.getData().setScale(size);

        return this;
    }
    /**
     * Method that builds color of text
     *
     * @return instance of builder class to finish building label in 1 line
     * */
    public LabelBuilder buildColor(){
        color = Color.WHITE;
        return this;
    }
    /**
     * Method that builds labelStyle of text with given font and color
     *
     * @return instance of builder class to finish building label in 1 line
     * */
    public LabelBuilder buildLabelStyle(){
        labelStyle = new Label.LabelStyle(bitmapFont, color);
        return this;
    }
    /**
     * Method that builds Label with given builder parameters
     *
     * @return label which is final product
     * */

    public Label buildLabel(){
        return new Label(text, labelStyle);
    }

}

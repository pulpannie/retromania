package com.retromania.game.spaceship_shooter.individuals;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
/**
 * Builder class for ImageButtons, just requires path of texture starting from
 * "spaceship_shooter/"
 *
 * @author Umid, Thuy
 * */
public class ImageButtonBuilder {
    /**
     * Variables:
     * texture: texture of image
     * */

    Texture texture;

    /**
     * Method that builds texture of image
     *
     * @return instance of class so that builder can be one-line
     * */
    public ImageButtonBuilder buildTexture(String path){
        path = "spaceship_shooter/" + path;
        texture = new Texture(path);
        return this;
    }

    /**
     * Method that builds button
     *
     * @return ImageButton which is final product of builder
     * */
    public ImageButton buildButton(){
        return new ImageButton(new TextureRegionDrawable(new TextureRegion(texture)));
    }
}

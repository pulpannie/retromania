package com.retromania.game.spaceship_shooter.individuals;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ImageButtonBuilder {
    Texture texture;
    TextureRegion textureRegion;
    TextureRegionDrawable textureRegionDrawable;

    public ImageButtonBuilder buildTexture(String path){
        path = "spaceship_shooter/" + path;
        System.out.println(path);
        texture = new Texture(path);
        return this;
    }


    public ImageButton buildButton(){
        return new ImageButton(new TextureRegionDrawable(new TextureRegion(texture)));
    }
}

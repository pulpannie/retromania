package com.retromania.game.tic_tac_toe.individuals;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ImageButtonBuilder {
    ImageButton imageButton;
    boolean pressed = false;

    public ImageButton buildButton(Texture texture, float width, float height, float posx, float posy) {
        imageButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(texture)));
        imageButton.setSize(width, height);
        imageButton.setPosition(posx, posy);
        return imageButton;
    }

    public ImageButton buildButton(Texture texture1, Texture texture2, float width, float height, float posx, float posy) {
        imageButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(texture1)),
                new TextureRegionDrawable(new TextureRegion(texture2)),
                new TextureRegionDrawable(new TextureRegion(texture2))
        );
        imageButton.setSize(width, height);
        imageButton.setPosition(posx, posy);
        imageButton.addListener(
                new InputListener() {
                    @Override
                    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                        System.out.println("CATS PRESSED");
                    }

                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        System.out.println("CATS...");
                        return true;
                    }
                });
        return imageButton;
    }


}

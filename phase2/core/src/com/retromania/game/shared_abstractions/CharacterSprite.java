package com.retromania.game.shared_abstractions;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

class CharacterSprite extends Sprite {

    private float pixelToMeterRate;

    protected float convertPixelToMeter(float pixel) {
        return pixelToMeterRate * pixel;
    }

    CharacterSprite(
            TextureRegion textureRegion, int x, int y, int width, int height, float pixelToMeterRate) {
        super(textureRegion);
        this.pixelToMeterRate = pixelToMeterRate;
        createTexture(textureRegion.getRegionWidth(), textureRegion.getRegionHeight(), textureRegion.getRegionWidth(), textureRegion.getRegionHeight());

    }

    private void createTexture(int x, int y, int width, int height) {
        TextureRegion playerIdle = new TextureRegion(getTexture(), x, y, width, height);
        setBounds(x, y, convertPixelToMeter(width), convertPixelToMeter(height));
        setRegion(playerIdle);
    }
}

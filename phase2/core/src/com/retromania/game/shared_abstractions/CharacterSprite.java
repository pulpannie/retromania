package com.retromania.game.shared_abstractions;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.retromania.game.special_mario.utils.WorldInformation;


class CharacterSprite extends Sprite {

  CharacterSprite(TextureRegion textureRegion, int x, int y, int width, int height, float pixelToMeterRate) {
    super(textureRegion);
    createMainPlayerView(x, y, width, height, pixelToMeterRate);
  }

  private void createMainPlayerView(int x, int y, int width, int height, float pixelToMeterRate) {
    TextureRegion playerIdle = new TextureRegion(getTexture(), x, y, width, height);
    setBounds(x, y, width * pixelToMeterRate, height * pixelToMeterRate);
    setRegion(playerIdle);
  }

}

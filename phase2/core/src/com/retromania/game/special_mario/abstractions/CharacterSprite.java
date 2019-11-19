package com.retromania.game.special_mario.abstractions;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.retromania.game.special_mario.SpecialMarioStarter;

import static com.retromania.game.special_mario.SpecialMarioStarter.convertPixelToMeter;

class CharacterSprite extends Sprite {

  CharacterSprite() {
    super(SpecialMarioStarter.getSpecialMarioStarter().getTextureAtlas().findRegion("mario_small"));
    createMainPlayerView();
  }

  private void createMainPlayerView() {
    TextureRegion playerIdle = new TextureRegion(getTexture(), 0, 0, 16, 16);
    setBounds(0, 0, convertPixelToMeter(16), convertPixelToMeter(16));
    setRegion(playerIdle);
  }

}

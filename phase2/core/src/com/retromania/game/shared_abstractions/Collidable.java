package com.retromania.game.shared_abstractions;

import com.badlogic.gdx.physics.box2d.FixtureDef;

public interface Collidable {
  FixtureDef getFixtureDef();

  short getDefaultMask();

  short getDefaultTarget();

  default void setCollidableWith(short othersCategory) {
    getFixtureDef().filter.maskBits = othersCategory;
  }

  default void setDefaultCollidableWith() {
    setCollidableWith(getDefaultTarget());
  }

  default void setCategoryMask(short selfCategory) {
    getFixtureDef().filter.categoryBits = selfCategory;
  }

  default void setDefaultCategoryMask() {
    setCategoryMask(getDefaultMask());
  }
}

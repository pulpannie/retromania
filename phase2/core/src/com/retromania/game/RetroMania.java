package com.retromania.game;

import com.badlogic.gdx.Gdx;
import com.retromania.game.shared_abstractions.Creatable;
import com.retromania.game.shared_abstractions.RetroManiaGame;

public class RetroMania extends RetroManiaGame {

  public static RetroMania getRetroManiaInstance() {
    return retroManiaInstance;
  }

  private static RetroMania retroManiaInstance = new RetroMania();

  private Creatable <RetroMania> creatable = new Creatable<RetroMania>() {
    @Override
    public void create(RetroMania r) {
    }
  };

  @Override
  public void create() {
    creatable.create(retroManiaInstance);
  }

  private RetroMania() {
    Car c = DaggerComponent.create().getCar();
    System.out.println("Car : " + c.testClass);
  }

  @Override
  public void render() {
    Gdx.gl.glClear(0);
    super.render();
  }

  @Override
  public void dispose() {
    sb.dispose();
    if (img != null)
    img.dispose();
  }

  @Override
  public void pause() {
    super.pause();
  }

  @Override
  public void resume() {
    super.resume();
  }

  public RetroManiaGame setCreatable(Creatable creatable) {
    this.creatable = creatable;
    return this;
  }
}

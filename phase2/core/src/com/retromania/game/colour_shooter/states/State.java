package com.retromania.game.colour_shooter.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    protected State(GameStateManager gsm) {
        this.gsm = gsm;
        cam = new OrthographicCamera();
        cam.setToOrtho(false,800,400);
        mouse = new Vector3();

    }

    protected abstract void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}

package com.retromania.game.colour_shooter.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SkinsState extends State{
    private Texture back;

    public SkinsState(GameStateManager gsm) {
        super(gsm);
        back = new Texture("m3.png");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(back, 50, 50);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}

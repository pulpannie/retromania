package com.retromania.game.colour_shooter.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.awt.Menu;

public class InstructionState extends State{
    private Texture back;
    private Texture text_instructions;
    private Texture background;

    public InstructionState(GameStateManager gsm) {
        super(gsm);
        back = new Texture("rules.png");
        text_instructions = new Texture("text_instructions.png");
        background = new Texture("background.jpg");
    }

    private float double_float(double double_val) {
        float float_value = (float) double_val;
        return float_value;
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
        double s_h = Gdx.graphics.getHeight();
        double s_w = Gdx.graphics.getWidth();
        float screen_h = (float) double_float(s_h / 1.87);
        float screen_w = (float) double_float(s_w * 1.88);
        sb.draw(background, 0, 0, screen_w, screen_h);
        sb.draw(text_instructions, 0, double_float(screen_h * 0.1),
                double_float(screen_w * 0.95), double_float(screen_h * 0.8));
//        sb.draw(back, 50, 50);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}

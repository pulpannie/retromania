package com.retromania.game.colour_shooter.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

import java.awt.Label;
import java.util.ArrayList;
import java.util.List;

public class PlayState extends State{
    private Texture back;
    private Texture background;
    private Texture cannon;
    private Texture square1;
    private Texture square2;
    private Texture square3;
    private Texture square4;
    private Boolean first_shot;
    private Texture bullet;
    private int bullet_pos;
    private Texture current_sq;
    private ArrayList<Texture> all_sq;
    private int timer;
    private int rotate_counter;

    public PlayState(GameStateManager gsm) {

        super(gsm);
        cannon = new Texture("cannon.png");
        background = new Texture("gray_background.png");
        square1 = new Texture("s1.png");
        square2 = new Texture("s2.png");
        square3 = new Texture("s3.png");
        square4 = new Texture("s4.png");
        current_sq = square1;
        first_shot = true;
        bullet = new Texture("bullet.png");
        bullet_pos = cannon.getWidth() / 5;
        timer = 0;
        all_sq = new ArrayList<Texture>();
        rotate_counter = 0;
        all_sq.add(square1);
        all_sq.add(square2);
        all_sq.add(square3);
        all_sq.add(square4);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isTouched()) {
            double s_h = Gdx.graphics.getHeight();
            float screen_h = double_float(s_h / 1.87);
            if (first_shot) {
                first_shot = false;
            }
            else if (bullet_pos > double_float(screen_h * 0.5) +
                    double_float(3.2 / 2.8)) {
                bullet_pos = cannon.getWidth() / 5;
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    private float double_float(double double_val) {
        return (float) double_val;
    }

    @Override
    public void render(SpriteBatch sb) {

        sb.begin();
        double s_h = Gdx.graphics.getHeight();
        double s_w = Gdx.graphics.getWidth();
        float screen_h = double_float(s_h / 1.87);
        float screen_w = double_float(s_w * 1.88);
        sb.draw(background, 0, 0, screen_w, screen_h);
        sb.draw(cannon, screen_w/2 - double_float(cannon.getWidth() / 2), 0,
                cannon.getWidth(),
                double_float(cannon.getWidth() / 5));
        if (timer % 5 == 0) {
            rotate_counter += 1;
            current_sq = all_sq.get(rotate_counter % 4);
            sb.draw(current_sq, screen_w/2 - double_float(current_sq.getWidth()),
                    double_float(screen_h * 0.5),
                    double_float(current_sq.getWidth() * 2),
                    double_float(current_sq.getHeight() / 2) *
                            double_float(3.2 / 2.8));
            timer += 1;
        } else {
            sb.draw(current_sq, screen_w/2 - double_float(current_sq.getWidth()),
                    double_float(screen_h * 0.5),
                    double_float(current_sq.getWidth() * 2),
                    double_float(current_sq.getHeight() / 2) *
                            double_float(3.2 / 2.8));
        }
        if (! first_shot && bullet_pos <= double_float(screen_h * 0.5) +
                double_float(3.2 / 2.8) ) {
                sb.draw(bullet, screen_w/2 - double_float(bullet.getWidth() / 2),
                    bullet_pos, double_float(screen_h * 0.1),
                    double_float(screen_h * 0.03));
                bullet_pos += 5;
            }
        sb.end();
    }

    @Override
    public void dispose() {

    }
}

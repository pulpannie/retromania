package com.retromania.game.colour_shooter.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MenuState extends State{
    private Texture background;
    private Texture title;
    private Texture playMenu;
    private Texture instrMenu;
    private Texture skinsMenu;
    private Texture returnMenu;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("background.jpg");
        title = new Texture("title.png");
        playMenu = new Texture("m1.png");
        instrMenu = new Texture("rules.png");
        skinsMenu = new Texture("m3.png");
        returnMenu = new Texture("back_text.png");
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    private float double_float(double double_val) {
        float float_value = (float) double_val;
        return float_value;
    }

    @Override
    public void render(SpriteBatch sb) {
        double s_h = Gdx.graphics.getHeight();
        double s_w = Gdx.graphics.getWidth();
        float screen_h = (float) double_float(s_h / 1.87);
        float screen_w = (float) double_float(s_w * 1.88);
        sb.begin();
        sb.draw(background, 0, 0, screen_w, screen_h);
        sb.draw(title, double_float(screen_w * 0.02),
                double_float(screen_h / 2) + (screen_h / 3),
                double_float(screen_w * 0.97), double_float(screen_h * 0.12));

        float x_start = double_float(screen_w * 0.15);
        float y_start = screen_h - double_float(screen_h * 0.35);
        float x_width = double_float(screen_w * 0.55);
        float y_height = double_float(screen_h * 0.148);
        sb.draw(playMenu, x_start, y_start, x_width, y_height);

//        if (Gdx.input.isTouched()) {
//            System.out.println("X input value is " +Gdx.input.getX() +
//                    " Y input value is " +Gdx.input.getY());
//            System.out.println("X start is " + x_start + " Y start is " + y_start);
//            System.out.println("Width is " + x_width + " Height is " + y_height);
//        }
//
//        if (x_start / 1.9 <= Gdx.input.getX() && Gdx.input.getX() <= x_start + x_width * 0.45 &&
//                y_start - y_height * 0.15 <= Gdx.input.getY() &&
//                Gdx.input.getY() <= y_start - y_height * 1.9) {
//            if (Gdx.input.isTouched()) {
//                Gdx.gl.glClearColor(1, 1, 1, 1);
//            }
//        } else {
//            Gdx.gl.glClearColor(0, 0, 0, 1);
//        }

        sb.draw(skinsMenu, x_start, screen_h - double_float(screen_h * 0.55),
                double_float(screen_w * 0.6), double_float(screen_h * 0.148));
        sb.draw(instrMenu, x_start, screen_h - double_float(screen_h * 0.75),
                double_float(screen_w * 0.635), double_float(screen_h * 0.148));
        sb.draw(returnMenu, x_start, screen_h - double_float(screen_h * 0.95),
                double_float(screen_w * 0.573), double_float(screen_h * 0.148));
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playMenu.dispose();
    }
}

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
        instrMenu = new Texture("m2.png");
        skinsMenu = new Texture("m3.png");
        returnMenu = new Texture("m4.png");
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
        float screen_h = (float) s_h;
        float screen_w = (float) s_w;
        sb.begin();
        sb.draw(background, 0, 0, screen_w, screen_h);
        sb.draw(title, (screen_w / 2) - (screen_w / 2),
                (screen_h / 2) + (screen_h / 3),
                double_float(screen_w * 0.98), double_float(screen_h * 0.146));
        sb.draw(playMenu, (screen_w / 2) - (screen_w / 2),
                (screen_h / 2) - (screen_h / 2),
                double_float(screen_w * 0.98), double_float(screen_h * 0.148));
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playMenu.dispose();
    }
}

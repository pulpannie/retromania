package com.retromania.game.colour_shooter.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MenuState extends State{
    private Texture background;
    private Texture title;
    private Texture playMenu;
    private Texture instrMenu;
    private Texture skinsMenu;
    private Texture returnMenu;
    private Button playMenuBtn;
    private Button instrMenuBtn;
    private Button skinsMenuBtn;
    private Button returnMenuBtn;

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
        if (Gdx.input.isTouched()) {
            float screen_w = double_float(Gdx.graphics.getWidth() * 1.88);
            float x_width = double_float(screen_w * 0.8);
            if (checkAction(163, x_width, 1)) {
                gsm.set(new PlayState(gsm));
                dispose();
            } else if (checkAction(163, x_width, 2)) {
                gsm.set(new SkinsState(gsm));
                dispose();
            } else if (checkAction(163, x_width, 3)) {
                gsm.set(new InstructionState(gsm));
                dispose();
            } else if (checkAction(163, x_width, 4)) {
                gsm.set(new InstructionState(gsm));
                dispose();
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    private float double_float(double double_val) {
        float float_value = (float) double_val;
        return float_value;
    }

    private boolean checkAction(float x_start, float x_width, int num) {
        int mid_num = 400 * num + 150;
        if (x_start / 1.9 <= Gdx.input.getX() && Gdx.input.getX() <= x_start + x_width * 0.45 &&
        mid_num - 150 < Gdx.input.getY() && Gdx.input.getY() < mid_num + 150) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        double s_h = Gdx.graphics.getHeight();
        double s_w = Gdx.graphics.getWidth();
        float screen_h = double_float(s_h / 1.87);
        float screen_w = double_float(s_w * 1.88);
        sb.begin();
        sb.draw(background, 0, 0, screen_w, screen_h);
        sb.draw(title, double_float(screen_w * 0.02),
                double_float(screen_h / 2) + (screen_h / 3),
                double_float(screen_w * 0.97), double_float(screen_h * 0.12));

        float x_all_start = double_float(screen_w * 0.15);
        float y_all_height = double_float(screen_h * 0.148);

        float y_play_start = screen_h - double_float(screen_h * 0.35);
        float x_play_width = double_float(screen_w * 0.55);
        sb.draw(playMenu, x_all_start, y_play_start, x_play_width, y_all_height);

        float y_skin_start = screen_h - double_float(screen_h * 0.55);
        float x_skin_width = double_float(screen_w * 0.6);
        sb.draw(skinsMenu, x_all_start, y_skin_start, x_skin_width, y_all_height);

        float y_rules_start = screen_h - double_float(screen_h * 0.75);
        float x_rules_width = double_float(screen_w * 0.635);
        sb.draw(instrMenu, x_all_start, y_rules_start, x_rules_width, y_all_height);

        float y_return_start = screen_h - double_float(screen_h * 0.95);
        float x_return_width = double_float(screen_w * 0.573);
        sb.draw(returnMenu, x_all_start, y_return_start, x_return_width, y_all_height);

        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playMenu.dispose();
    }
}

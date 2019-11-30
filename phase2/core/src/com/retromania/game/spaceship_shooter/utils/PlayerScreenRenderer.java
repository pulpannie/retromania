package com.retromania.game.spaceship_shooter.utils;
import com.retromania.game.RetroMania;
import com.retromania.game.spaceship_shooter.individuals.Car;
import com.retromania.game.spaceship_shooter.individuals.Hud;
import com.retromania.game.spaceship_shooter.individuals.UfoManager;

public class PlayerScreenRenderer extends  GameRenderer{
    private Hud hud;
    private Car player;
    private UfoManager ufoManager;
    private boolean finished = false;

    public PlayerScreenRenderer(String screenType, Hud hud, Car player, UfoManager ufoManager) {
        super(screenType);
        this.hud = hud;
        this.player = player;
        this.ufoManager = ufoManager;
    }

    @Override
    public void update(float dt){
        super.update(dt);

        ufoManager.update(player.getiRocket(), hud);
        if (hud.countDown(dt))
            finished = true;
    }

    public boolean isFinished(){return finished;}

    @Override
    public void render(float delta){
        super.render(delta);

        RetroMania.getRetroManiaInstance().sb.setProjectionMatrix(hud.stage.getCamera().combined);
        RetroMania.getRetroManiaInstance().sb.begin();

        if (player.shot())
            player.getiRocket().draw(RetroMania.getRetroManiaInstance().sb, delta);

        player.draw(RetroMania.getRetroManiaInstance().sb, delta);
        ufoManager.draw(RetroMania.getRetroManiaInstance().sb, delta);
        RetroMania.getRetroManiaInstance().sb.end();
        hud.stage.draw();
    }

    public int getScore(){return hud.getScore();}
    public Car getPlayer(){return player;}

}

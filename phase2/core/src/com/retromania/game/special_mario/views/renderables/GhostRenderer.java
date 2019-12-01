package com.retromania.game.special_mario.views.renderables;

import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.special_mario.models.player.MainPlayer;
import com.retromania.game.special_mario.presenter.SpecialMarioStarterPresenter;

import javax.inject.Inject;

public class GhostRenderer extends MarioRenderable {

    @Inject
    GhostRenderer(MainPlayer mainPlayer, World world, SpecialMarioStarterPresenter marioGamePresenter) {
        super(mainPlayer, world, marioGamePresenter);
    }

    @Override
    public void render(float delta) {

    }
}

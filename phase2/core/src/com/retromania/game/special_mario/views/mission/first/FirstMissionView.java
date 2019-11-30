package com.retromania.game.special_mario.views.mission.first;

import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.special_mario.views.renderables.UserRenderPreference;

import javax.inject.Inject;

public class FirstMissionView extends RetroManiaScreen {

    UserRenderPreference userRenderPreference;

    @Inject
    FirstMissionView(UserRenderPreference userRenderPreference){
        this.userRenderPreference = userRenderPreference;
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void show() {
        addRenderable(userRenderPreference.getRenderable());
    }

    @Override
    public void resize(int width, int height) {

    }
}

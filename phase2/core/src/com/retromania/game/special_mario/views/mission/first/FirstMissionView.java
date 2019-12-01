package com.retromania.game.special_mario.views.mission.first;

import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.special_mario.presenter.mission.first.FirstMissionPresenter;
import com.retromania.game.special_mario.views.mission.MissionView;
import com.retromania.game.special_mario.views.renderables.UserRenderPreference;

import javax.inject.Inject;

public class FirstMissionView extends MissionView {

    @Inject
    FirstMissionView(
            FirstMissionPresenter firstMissionPresenter,
            UserRenderPreference userRenderPreference) {
        super(firstMissionPresenter, userRenderPreference);
    }
}

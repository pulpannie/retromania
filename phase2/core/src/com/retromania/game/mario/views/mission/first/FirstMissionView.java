package com.retromania.game.mario.views.mission.first;

import com.retromania.game.mario.presenter.mission.first.FirstMissionPresenter;
import com.retromania.game.mario.views.mission.MissionView;
import com.retromania.game.mario.views.renderables.UserRenderPreference;

import javax.inject.Inject;

/**
 * The view responsible for the output of first of mission
 *
 * */
public class FirstMissionView extends MissionView {

    @Inject
    FirstMissionView(
            FirstMissionPresenter firstMissionPresenter,
            UserRenderPreference userRenderPreference) {
        super(firstMissionPresenter, userRenderPreference);
    }
}

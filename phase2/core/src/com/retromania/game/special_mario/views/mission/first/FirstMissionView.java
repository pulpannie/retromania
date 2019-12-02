package com.retromania.game.special_mario.views.mission.first;

import com.retromania.game.special_mario.models.player.MainPlayer;
import com.retromania.game.special_mario.presenter.mission.first.FirstMissionPresenter;
import com.retromania.game.special_mario.views.mission.MissionView;
import com.retromania.game.special_mario.views.renderables.UserRenderPreference;

import javax.inject.Inject;

/**
 * The view responsible for the output of first of mission
 *
 * */
public class FirstMissionView extends MissionView {

    @Inject
    FirstMissionView(
            FirstMissionPresenter firstMissionPresenter,
            UserRenderPreference userRenderPreference, MainPlayer mainPlayer) {
        super(firstMissionPresenter, userRenderPreference, mainPlayer);
    }
}

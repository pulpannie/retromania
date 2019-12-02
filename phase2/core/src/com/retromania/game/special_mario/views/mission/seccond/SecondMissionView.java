package com.retromania.game.special_mario.views.mission.seccond;

import com.retromania.game.special_mario.models.player.MainPlayer;
import com.retromania.game.special_mario.presenter.mission.second.SecondMissionPresenter;
import com.retromania.game.special_mario.views.mission.MissionView;
import com.retromania.game.special_mario.views.renderables.UserRenderPreference;

import javax.inject.Inject;

/**
 *
 * The view responsible for showing the second mission
 *
 * */
public class SecondMissionView extends MissionView {
    @Inject
    SecondMissionView(
            SecondMissionPresenter secondMissionPresenter,
            UserRenderPreference userRenderPreference, MainPlayer mainPlayer) {
        super(secondMissionPresenter, userRenderPreference, mainPlayer);
    }

}

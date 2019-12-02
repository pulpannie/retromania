package com.retromania.game.mario.views.mission.seccond;

import com.retromania.game.mario.presenter.mission.second.SecondMissionPresenter;
import com.retromania.game.mario.views.mission.MissionView;
import com.retromania.game.mario.views.renderables.UserRenderPreference;

import javax.inject.Inject;

/** The view responsible for showing the second mission */
public class SecondMissionView extends MissionView {
  @Inject
  SecondMissionView(
      SecondMissionPresenter secondMissionPresenter, UserRenderPreference userRenderPreference) {
    super(secondMissionPresenter, userRenderPreference);
  }
}

package com.retromania.game;

import android.app.Activity;
import android.content.pm.ActivityInfo;

import com.retromania.game.shared_abstractions.OrientationManager;

public class OrientationManagerAndroid implements OrientationManager {
    Activity activity;
    OrientationManagerAndroid(Activity activity){
        this.activity = activity;
    }
    @Override
    public void makeHorizontal() {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    @Override
    public void makeVertical() {
        activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}

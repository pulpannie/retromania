package com.retromania.game.special_mario.models.player;

import com.retromania.game.shared_abstractions.Character;
import com.retromania.game.special_mario.models.player.BodyPart;

public class MainPlayerCollisionInfo {
    private MainPlayer mainPlayer;
    private BodyPart bodyPart;

    public MainPlayerCollisionInfo(MainPlayer mainPlayer, BodyPart bodyPart){
        this.mainPlayer = mainPlayer;
        this.bodyPart = bodyPart;
    }


    public BodyPart getBodyPart() {
        return bodyPart;
    }

    public MainPlayer getMainPlayer() {
        return mainPlayer;
    }
}

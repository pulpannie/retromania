package com.retromania.game.special_mario.models.player;

import com.retromania.game.shared_abstractions.Character;
import com.retromania.game.special_mario.models.player.BodyPart;

public class MainPlayerCollisionInfo {
    private Character character;
    private BodyPart bodyPart;

    public MainPlayerCollisionInfo(Character character, BodyPart bodyPart){
        this.character = character;
        this.bodyPart = bodyPart;
    }


    public BodyPart getBodyPart() {
        return bodyPart;
    }

    public Character getCharacter() {
        return character;
    }
}

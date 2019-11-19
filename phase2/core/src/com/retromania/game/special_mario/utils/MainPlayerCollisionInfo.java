package com.retromania.game.special_mario.utils;

import com.retromania.game.special_mario.abstractions.Character;
import com.retromania.game.special_mario.utils.BodyPart;

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

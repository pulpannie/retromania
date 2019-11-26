package com.retromania.game.special_mario.utils;

import com.retromania.game.shared_abstractions.Character;

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

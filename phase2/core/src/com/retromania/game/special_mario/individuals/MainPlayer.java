package com.retromania.game.special_mario.individuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.retromania.game.shared_abstractions.Individual;
import com.retromania.game.special_mario.SpecialMarioStarter;
import com.retromania.game.special_mario.abstractions.Character;



public class MainPlayer extends Character implements Individual{

    public MainPlayer(){
        super();
    }

    public void handleInput(){
        SpecialMarioStarter innerGame = SpecialMarioStarter.getSpecialMarioStarter();
        if (Gdx.input.isTouched() && Math.abs(body.getLinearVelocity().x) < 2) {
            if (Gdx.input.getX()<=innerGame.gamePort.getScreenWidth()/2)
                body.applyLinearImpulse(new Vector2(-.1f, 0), body.getWorldCenter(), true);
            else
                body.applyLinearImpulse(new Vector2(.1f, 0), body.getWorldCenter(), true);
        }
        if (Gdx.input.justTouched()){
            if (Gdx.input.getY()<=innerGame.gamePort.getScreenHeight()/2)
                body.applyLinearImpulse(new Vector2(0, 4f), body.getWorldCenter(), true);
        }
    }

    @Override
    public FixtureDef getFixtureDef() {
        return fixtureDef;
    }

    @Override
    public short getDefaultMask() {
        return 2;
    }

    @Override
    public short getDefaultTarget() {
        return 4;
    }

    @Override
    public void update(Object ...args){
        handleInput();
        setPosition(body.getPosition().x - getWidth()/2, body.getPosition().y - getHeight()/2);
    }

}

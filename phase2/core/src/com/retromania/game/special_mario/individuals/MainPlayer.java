package com.retromania.game.special_mario.individuals;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.shared_abstractions.Individual;
import com.retromania.game.special_mario.SpecialMarioStarter;

public class MainPlayer extends Sprite implements Individual {
    private World world;
    private Body body;
    private TextureRegion playerIdle;


    public void createMainPlayer(){

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(SpecialMarioStarter.convertPixelToMeter(32), SpecialMarioStarter.convertPixelToMeter(256));
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(SpecialMarioStarter.convertPixelToMeter(7));

        fixtureDef.shape = circleShape;

        body.createFixture(fixtureDef);
    }

//  TODO figure out a way to omit the pass of the inner game
    public MainPlayer(SpecialMarioStarter specialMarioStarter){
        super(specialMarioStarter.getTextureAtlas().findRegion("mario_small"));
        this.world = specialMarioStarter.getWorld();
        createMainPlayer();
        createMainPlayerView();
    }

    private void createMainPlayerView(){
        playerIdle = new TextureRegion(getTexture(), 0, 0, 16, 16);
        setBounds(0, 0, SpecialMarioStarter.convertPixelToMeter(16), SpecialMarioStarter.convertPixelToMeter(16));
        setRegion(playerIdle);
    }

    @Override
    public void update(Object ...args){
        setPosition(body.getPosition().x - getWidth()/2, body.getPosition().y - getHeight()/2);
    }


}

package com.retromania.game.special_mario.individuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.shared_abstractions.Individual;
import com.retromania.game.special_mario.SpecialMarioStarter;

import static com.retromania.game.special_mario.SpecialMarioStarter.convertPixelToMeter;

public class MainPlayer extends Sprite implements Individual {
    private World world;
    private Body body;
    private TextureRegion playerIdle;

    public final static String HAT = "HAT";


    public void createMainPlayer(){

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(convertPixelToMeter(32), convertPixelToMeter(256));
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        CircleShape circleShape = new CircleShape();
        float radius = 7;
        circleShape.setRadius(convertPixelToMeter(radius));


        fixtureDef.shape = circleShape;

        body.createFixture(fixtureDef);

        float lengthHat = 4;

        EdgeShape hat = new EdgeShape();
        hat.set(new Vector2(convertPixelToMeter(-lengthHat/2), convertPixelToMeter(radius)),
                new Vector2(convertPixelToMeter(lengthHat/2), convertPixelToMeter(radius)));

        fixtureDef.shape = hat;
        fixtureDef.isSensor = true;
        body.createFixture(fixtureDef).setUserData(HAT);




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
        setBounds(0, 0, convertPixelToMeter(16), convertPixelToMeter(16));
        setRegion(playerIdle);
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
    public void update(Object ...args){
        handleInput();
        setPosition(body.getPosition().x - getWidth()/2, body.getPosition().y - getHeight()/2);
    }


}

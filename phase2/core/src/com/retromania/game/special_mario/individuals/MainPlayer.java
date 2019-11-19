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
import com.retromania.game.special_mario.abstractions.Collidable;


import static com.retromania.game.special_mario.SpecialMarioStarter.convertPixelToMeter;
import static com.retromania.game.special_mario.individuals.MainPlayer.BodyPart.HEAD;

public class MainPlayer extends Sprite implements Individual, Collidable {


    private World world;
    private Body body;
    private FixtureDef fixtureDef;



//  TODO figure out a way to omit the pass of the inner game
    public MainPlayer(){
        super(SpecialMarioStarter.getSpecialMarioStarter().getTextureAtlas().findRegion("mario_small"));
        SpecialMarioStarter innerGame = SpecialMarioStarter.getSpecialMarioStarter();
        this.world = innerGame.getWorld();
        createMainPlayer();
        createMainPlayerView();
    }

    private void createMainPlayer(){

        BodyDef bodyDef = setUpBodyDef();
        body = setUpBody(bodyDef);
        setUpFixture(7, 4);
        body.createFixture(fixtureDef).setUserData(new MainPlayerCollisionInfo(this, HEAD));


    }



    private void createMainPlayerView(){
        TextureRegion playerIdle = new TextureRegion(getTexture(), 0, 0, 16, 16);
        setBounds(0, 0, convertPixelToMeter(16), convertPixelToMeter(16));
        setRegion(playerIdle);
    }


    private BodyDef setUpBodyDef(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(convertPixelToMeter(32), convertPixelToMeter(256));
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        return bodyDef;
    }

    private Body setUpBody(BodyDef bodyDef){
        return world.createBody(bodyDef);
    }

    private void setUpFixture(float circleShapeRadius, float headLength){
        fixtureDef = new FixtureDef();
        setDefaultCategoryMask();
        setDefaultCollidableWith();
        setUpFixtureDefShapes(circleShapeRadius, headLength);
    }


    private void setUpFixtureDefShapes(float circleShapeRadius, float headLength){
        fixtureDef.shape = setUpCircleBody(circleShapeRadius);
        body.createFixture(fixtureDef);
        fixtureDef.shape = setUpEdgeShapeBody(circleShapeRadius, headLength);
        fixtureDef.isSensor = true;
    }

    private CircleShape setUpCircleBody(float circleShapeRadius){
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(convertPixelToMeter(circleShapeRadius));
        return circleShape;
    }

    private EdgeShape setUpEdgeShapeBody(float circleShapeRadius, float headLength){
        EdgeShape head = new EdgeShape();
        head.set(new Vector2(convertPixelToMeter(-headLength/2), convertPixelToMeter(circleShapeRadius)),
                new Vector2(convertPixelToMeter(headLength/2), convertPixelToMeter(circleShapeRadius)));
        return head;
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


    public class MainPlayerCollisionInfo{
        MainPlayer player;
        BodyPart bodyPart;
        MainPlayerCollisionInfo(MainPlayer player, BodyPart bodyPart){
            this.player = player;
            this.bodyPart = bodyPart;
        }
    }

    public enum BodyPart{
        HEAD,
    }



}

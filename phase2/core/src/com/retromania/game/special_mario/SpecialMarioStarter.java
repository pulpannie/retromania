package com.retromania.game.special_mario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.retromania.game.shared_abstractions.Configuration;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaGeneralUser;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.shared_abstractions.User;

import java.util.List;

import static com.badlogic.gdx.Gdx.gl;

public class SpecialMarioStarter extends RetroManiaInnerGame {



    private TmxMapLoader mapLoader;
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera gamecam;

    private World world;
    private Box2DDebugRenderer b2ddr;


    private void initWorld(){
        world = new World(new Vector2(0, 0), true);
        b2ddr = new Box2DDebugRenderer();

        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();

        Body body;


        for (MapObject object : tiledMap.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
            bodyDef.type = BodyDef.BodyType.StaticBody;
            bodyDef.position.set(rectangle.getX() + rectangle.getWidth()/2, rectangle.getY() +rectangle.getHeight()/2);
            body = world.createBody(bodyDef);

            shape.setAsBox(rectangle.getWidth()/2, rectangle.getHeight()/2);
            fixtureDef.shape = shape;
            body.createFixture(fixtureDef);
        }



    }

    public SpecialMarioStarter(RetroManiaGame game) {
        super(game, "MarioSpec", RetroManiaGame.Orientation.HORIZONTAL);
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(RetroManiaGame.V_WIDTH, RetroManiaGame.V_HEIGHT, gamecam);

        mapLoader = new TmxMapLoader();
        tiledMap = mapLoader.load("special_mario/firstLevel.tmx");
        renderer = new OrthogonalTiledMapRenderer(tiledMap);

        gamecam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);
        initWorld();
    }

    @Override
    public void setCurrentUser(String name) {
        Preferences preferences = game.getPrefrences("Mario");
        this.currentUser = new RetroManiaGeneralUser(name);
        this.currentUser.setScore(preferences.getInteger(currentUser.getUserName()));
    }

    @Override
    public void setBestUser() {
        Preferences preferences = game.getPrefrences(Configuration.tictactoePreference);
        User user = new RetroManiaGeneralUser(preferences.getString(Configuration.bestUserUserName));
        user.setScore(preferences.getInteger(Configuration.bestUserScore));
        bestUser = user;
    }

    @Override
    public Integer getBestUserScore() {
        return bestUser.getScore();
    }

    @Override
    public void save(Object... args) {

    }

    @Override
    public List<Object> retrieve() {
        return null;
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isTouched()){
            gamecam.position.x += 10;
        }
    }

    @Override
    public void show() {
    }

    @Override
    public void update(){
        gamecam.update();
        renderer.setView(gamecam);
        handleInput();
    }

    @Override
    public void render(float delta) {

        update();

        Gdx.gl.glClearColor(0,0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.render();

        b2ddr.render(world, gamecam.combined);

        game.sb.setProjectionMatrix(gamecam.combined);

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

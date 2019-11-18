package com.retromania.game.special_mario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.retromania.game.shared_abstractions.Configuration;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaGeneralUser;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.shared_abstractions.User;
import com.retromania.game.special_mario.individuals.MainPlayer;

import java.util.List;

public class SpecialMarioStarter extends RetroManiaInnerGame {

  private TextureAtlas textureAtlas = new TextureAtlas("special_mario/mario_small.pack");

  private static SpecialMarioStarter specialMarioStarter = new SpecialMarioStarter();

  public static float convertPixelToMeter(float meter) {
    return meter * getPixelToMeterConversionRate();
  }

  public static float getPixelToMeterConversionRate() {
    return 1 / 100f;
  }

  private MainPlayer mainPlayer;

  private TmxMapLoader mapLoader;
  private TiledMap tiledMap;
  private OrthogonalTiledMapRenderer renderer;
  private OrthographicCamera gamecam;

  public World getWorld() {
    return world;
  }

  private World world;
  private Box2DDebugRenderer b2ddr;

  public static SpecialMarioStarter getSpecialMarioStarter() {
    return specialMarioStarter;
  }

  private void initWorld() {
    world = new World(new Vector2(0, -10), true);
    b2ddr = new Box2DDebugRenderer();

    BodyDef bodyDef = new BodyDef();
    PolygonShape shape = new PolygonShape();
    FixtureDef fixtureDef = new FixtureDef();

    Body body;

    for (MapObject object :
        tiledMap.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)) {
      Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
      bodyDef.type = BodyDef.BodyType.StaticBody;
      bodyDef.position.set(
          convertPixelToMeter(rectangle.getX() + rectangle.getWidth() / 2),
          convertPixelToMeter(rectangle.getY() + rectangle.getHeight() / 2));
      body = world.createBody(bodyDef);

      shape.setAsBox(
          convertPixelToMeter(rectangle.getWidth() / 2),
          convertPixelToMeter(rectangle.getHeight() / 2));
      fixtureDef.shape = shape;
      body.createFixture(fixtureDef).setUserData("something");
    }
    mainPlayer = new MainPlayer(this);
  }

  private SpecialMarioStarter() {
    super("MarioSpec", RetroManiaGame.Orientation.HORIZONTAL);
    gamecam = new OrthographicCamera();
    gamePort =
        new FitViewport(
            convertPixelToMeter(RetroManiaGame.V_WIDTH),
            convertPixelToMeter(RetroManiaGame.V_HEIGHT),
            gamecam);

    mapLoader = new TmxMapLoader();
    tiledMap = mapLoader.load("special_mario/firstLevel.tmx");
    renderer = new OrthogonalTiledMapRenderer(tiledMap, getPixelToMeterConversionRate());

    gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
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
    Preferences preferences = game.getPrefrences("Mario");
    User user = new RetroManiaGeneralUser(preferences.getString(Configuration.bestUserUserName));
    user.setScore(preferences.getInteger(Configuration.bestUserScore));
    bestUser = user;
  }

  @Override
  public Integer getBestUserScore() {
    return bestUser.getScore();
  }

  @Override
  public void save(Object... args) {}

  @Override
  public List<Object> retrieve() {
    return null;
  }

  @Override
  public void handleInput() {
    gamecam.position.x = gamePort.getWorldWidth()/2 > mainPlayer.getX() ?  gamecam.position.x : mainPlayer.getX();
  }

  @Override
  public void show() {}

  @Override
  public void update() {
    gamecam.update();
    world.step(1 / 60f, 6, 2);
    renderer.setView(gamecam);
    mainPlayer.update();
    handleInput();
  }

  @Override
  public void render(float delta) {

    update();

    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    renderer.render();

    b2ddr.render(world, gamecam.combined);

    game.sb.setProjectionMatrix(gamecam.combined);

    game.sb.begin();
    mainPlayer.draw(game.sb);
    game.sb.end();
  }

  @Override
  public void resize(int width, int height) {
    gamePort.update(width, height);
  }

  @Override
  public void pause() {}

  @Override
  public void resume() {}

  @Override
  public void hide() {}

  @Override
  public void dispose() {}

  public TextureAtlas getTextureAtlas() {
    return textureAtlas;
  }
}

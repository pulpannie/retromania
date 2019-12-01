package com.retromania.game.colour_shooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.RetroMania;
import com.retromania.game.colour_shooter.ColourShooterStarter;
import com.retromania.game.colour_shooter.individuals.Background;
import com.retromania.game.colour_shooter.individuals.BulletCharacter;
import com.retromania.game.colour_shooter.individuals.Header;
import com.retromania.game.colour_shooter.individuals.Rectangle;
import com.retromania.game.colour_shooter.individuals.Tank;
import com.retromania.game.colour_shooter.individuals.Square;
import com.retromania.game.colour_shooter.utils.ColourShooterListener;
import com.retromania.game.shared_abstractions.RetroManiaGame;

public class PlayScreen implements Screen {
  static Stage stage;
  ColourShooterStarter mainscreen;
  private World world;
  private Box2DDebugRenderer b2ddr;
  private Square square;
  private Header header;
  private float bullet_starting_pos;
  private boolean bullet_in_motion = false;
  public static Viewport viewport =
      new FitViewport(RetroManiaGame.V_HEIGHT, RetroManiaGame.V_WIDTH, new OrthographicCamera());
  BulletCharacter bullet = null;

  public PlayScreen(RetroManiaGame game, ColourShooterStarter mainscreen) {
    this.mainscreen = mainscreen;
    stage = new Stage(viewport, game.sb);
    Gdx.input.setInputProcessor(stage);

    Background background = new Background("play_screen");
    Image back_img = new Image(background.getBackgroundTexture());
    back_img.setSize(RetroMania.V_HEIGHT, RetroMania.V_WIDTH);
    back_img.setFillParent(true);
    stage.addActor(back_img);

    this.world = new World(new Vector2(0, 0), true);
    this.world.setContactListener(new ColourShooterListener());
    this.b2ddr = new Box2DDebugRenderer();
  }

  @Override
  public void show() {

    Tank tank = new Tank(mainscreen.getTankPreference());
    Image tank_img = new Image(tank.getTankTexture());
    tank_img.setSize((float) (RetroMania.V_HEIGHT * 0.3), (float) (RetroMania.V_WIDTH * 0.2));
    tank_img.setPosition((float) (RetroMania.V_HEIGHT / 2) - (tank_img.getWidth() / 2), 0);
    stage.addActor(tank_img);
    bullet_starting_pos = tank_img.getHeight();
    header = new Header(stage, Gdx.graphics.getHeight(), Gdx.graphics.getWidth());

    square = makeSquare();
//    square.rotateSquare(1.5f);
//    square.increaseWeight(1000f);
//    Square rotating_square =
//        new Square(
//            world,
//            "SQUARE",
//            viewport.getWorldWidth() / 2,
//            (float) (viewport.getWorldHeight() * 0.6),
//            32,
//            32);
//    rotating_square.body.setAngularVelocity(1.5f);
//    rotating_square.body.setLinearDamping(1000f);

    // THE OLD SQUARE LEAVE IT COMMENTED

    //        square = new Image(new Texture(Gdx.files.internal("colour_shooter/square.png")));
    //        square.setSize((float) (width* 0.6), (float) (height * 0.3));
    //        square.setPosition((float) (width / 2) - square.getWidth() / 2,
    //                (float) (height * 0.6) - square.getHeight() / 2);
    //        square.setScaling(Scaling.fit);
    //        square.setOrigin(Align.center);
    //        stage.addActor(square);

    //        world = new World(new Vector2(0, 0), true);
    //        world.setContactListener(new ColourShooterListener());
    //        TextureRegion squareRegion = new TextureRegion(new Texture(
    // Gdx.files.internal("colour_shooter/square.png")));
    //        square = new Rectangle(squareRegion, 0, 0, 64, 64, 1, world,
    //                Constants.BIT_SQUARE, Constants.BIT_BULLET, (short) 0);
    //        square.setPosition(viewport.getWorldWidth()/2 - square.getWidth()/2,
    //                (float) (viewport.getWorldHeight() * 0.6));
    //
    //        TextureRegion blueRegion = new TextureRegion(new Texture(
    // Gdx.files.internal("colour_shooter/blueEdge.png")));
    //        blue = new Rectangle(blueRegion, 0, 0, 64, 10, 1, world,
    //                Constants.BIT_BLUE, Constants.BIT_BULLET, (short) 0);
    //        blue.setPosition(viewport.getWorldWidth()/2 - blue.getWidth()/2,
    //                (float) (viewport.getWorldHeight() * 0.6));
    //
    //        TextureRegion yellowRegion = new TextureRegion(new Texture(
    // Gdx.files.internal("colour_shooter/yellowEdge.png")));
    //        yellow = new Rectangle(yellowRegion, 0, 0, 64, 10, 1, world,
    //                Constants.BIT_YELLOW, Constants.BIT_BULLET, (short) 0);
    //        yellow.setPosition(viewport.getWorldWidth()/2 - yellow.getWidth()/2,
    //                (float) (viewport.getWorldHeight() * 0.6) + square.getHeight() -
    // yellow.getHeight());
    //
    //        TextureRegion redRegion = new TextureRegion(new Texture(
    // Gdx.files.internal("colour_shooter/redEdge.png")));
    //        red = new Rectangle(redRegion, 0, 0, 10, 64, 1, world,
    //                Constants.BIT_RED, Constants.BIT_BULLET, (short) 0);
    //        red.setPosition(viewport.getWorldWidth()/2 - square.getWidth()/2,
    //                (float) (viewport.getWorldHeight() * 0.6));
    //
    //        TextureRegion greenRegion = new TextureRegion(new Texture(
    // Gdx.files.internal("colour_shooter/greenEdge.png")));
    //        green = new Rectangle(greenRegion, 0, 0, 10, 64, 1, world,
    //                Constants.BIT_GREEN, Constants.BIT_BULLET, (short) 0);
    //        green.setPosition(viewport.getWorldWidth()/2 + square.getWidth()/2 - green.getWidth(),
    //                (float) (viewport.getWorldHeight() * 0.6));;
    //        red.setOrigin(square.getWidth() / 2,square.getHeight() / 2);
    //        green.setOrigin(-square.getWidth() / 2 + green.getWidth(),square.getHeight() / 2);
    //        blue.setOrigin(square.getWidth() / 2,square.getHeight() / 2);
    //        yellow.setOrigin(square.getWidth() / 2,-square.getHeight() / 2 + yellow.getHeight());

    //        b2ddr =  new Box2DDebugRenderer();
  }

  private BulletCharacter makeBullet() {
    TextureRegion bulletRegion =
        new TextureRegion(new Texture(Gdx.files.internal("colour_shooter/newBullet.png")));

    int n_x = Math.round((viewport.getWorldWidth() / 2));
    int n_y = Math.round(bullet_starting_pos);

    BulletCharacter bullet = new BulletCharacter(world, bulletRegion, n_x, n_y);
    return bullet;
  }

  private Square makeSquare() {
    TextureRegion squareRegion =
            new TextureRegion(new Texture(Gdx.files.internal("colour_shooter/square.png")));

    int n_x = Math.round(viewport.getWorldWidth()/2 - 4);
    int n_y = Math.round((float) (viewport.getWorldHeight() * 0.6));
    square = new Square(world, squareRegion, n_x, n_y, 16);
    square.setPosition(square.body.getPosition().x - square.getWidth() / 2, square.body.getPosition().y - square.getHeight() / 2);
    return square;
  }

  //    private Bullet makeBullet() {
  //        TextureRegion bulletRegion = new TextureRegion(new
  // Texture(Gdx.files.internal("colour_shooter/bullet.png")));
  //
  //        int n_x = Math.round((viewport.getWorldWidth() / 2));
  //        int n_y = Math.round(bullet_starting_pos);
  //
  //        Bullet bullet = new Bullet(world, "BULLET", n_x, n_y, 6);
  //        return bullet;
  //    }

  public void update(float dt) {
    stage.act(dt);

    if (Gdx.input.isTouched() & !bullet_in_motion) {
      bullet_in_motion = true;
      bullet = makeBullet();
      bullet.move(0, 300);
    }
    world.step(1 / 60f, 6, 2);
    //        square.update();
    //        red.update();
    //        green.update();
    //        blue.update();
    //        yellow.update();
//    square.setRotation(square.body.getAngle());
//    square.setOriginCenter();
//    square.rotateSquare();
    square.rotateSquare();

    if (bullet != null) {
      bullet.update();
    }


  }

  public void bulletCollided(float degreeRotation) {
    bullet_in_motion = false;
  }

  @Override
  public void render(final float delta) {
    update(delta);

    //        float curr = square.getRotation();
    //        curr  = curr >= 360 ? 0 : curr + 1.5f;
    //        square.setOriginCenter();
    //        square.setRotation(curr);
    //        red.setRotation(curr);
    //        green.setRotation(curr);
    //        blue.setRotation(curr);
    //        yellow.setRotation(curr);

    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.draw();
    //        b2ddr.render(world, viewport.getCamera().combined);
    RetroMania.getRetroManiaInstance().sb.setProjectionMatrix(viewport.getCamera().combined);
    //        square.draw(RetroMania.getRetroManiaInstance().sb);
    //        blue.draw(RetroMania.getRetroManiaInstance().sb);
    //        red.draw(RetroMania.getRetroManiaInstance().sb);
    //        green.draw(RetroMania.getRetroManiaInstance().sb);
    //        yellow.draw(RetroMania.getRetroManiaInstance().sb);
    //        if (bullet_in_motion) {
    //            bullet.setPosition(bullet.getX(), bullet.getY() + 2);
    //            bullet.draw(RetroMania.getRetroManiaInstance().sb);
    //        }

    RetroMania.getRetroManiaInstance().sb.begin();

    square.draw(RetroMania.getRetroManiaInstance().sb);

    RetroMania.getRetroManiaInstance().sb.end();

    RetroMania.getRetroManiaInstance().sb.begin();
    if (bullet!=null)
    bullet.draw(RetroMania.getRetroManiaInstance().sb);

    RetroMania.getRetroManiaInstance().sb.end();

    b2ddr.render(world, viewport.getCamera().combined);



  }

  @Override
  public void resize(int width, int height) {
    viewport.update(width, height);
  }

  @Override
  public void pause() {}

  @Override
  public void resume() {}

  public void endGame() {}

  @Override
  public void hide() {}

  @Override
  public void dispose() {
    stage.dispose();
  }
}

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
import com.retromania.game.colour_shooter.individuals.Bullet;
import com.retromania.game.colour_shooter.individuals.Header;
import com.retromania.game.colour_shooter.individuals.Tank;
import com.retromania.game.colour_shooter.individuals.Square;
import com.retromania.game.colour_shooter.utils.ColourShooterListener;
import com.retromania.game.shared_abstractions.RetroManiaGame;

/**
 * THIS CLASS IS RESPONSIBLE FOR MAKING THE PLAY SCREEN. THIS CLASS IS MAKING THE VARIOUS FEATURES
 * OF THE GAME SUCH AS DRAWING NECESSARY COMPONENTS OF THE GAME SUCH AS THE ROTATING SQUARE, BULLET,
 * ETC.
 */
public class PlayScreen implements Screen {
  private static Stage stage;
  private ColourShooterStarter mainscreen;
  private World world;
  private Box2DDebugRenderer b2ddr;
  private Square square;
  private Header header;
  private float bullet_starting_pos;
  private boolean bullet_in_motion = false;
  public static Viewport viewport =
          new FitViewport(RetroManiaGame.V_HEIGHT, RetroManiaGame.V_WIDTH, new OrthographicCamera());
  private Bullet bullet = null;

  /**
   * Constructor of play screen.
   *
   * @param game:       a retromania game instance
   * @param mainscreen: instance from Colour Shooter Starter
   */
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

  /**
   * DISPLAYS THE TANK AND SETS UP THE INITIAL POSITION OF THE BULLET.
   */
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
  }

  /**
   * Returning of type Bullet.
   * This method is responsible for making a bullet
   */
  private Bullet makeBullet() {
    TextureRegion bulletRegion =
            new TextureRegion(new Texture(Gdx.files.internal("colour_shooter/newBullet.png")));

    int n_x = Math.round((viewport.getWorldWidth() / 2));
    int n_y = Math.round(bullet_starting_pos);

    bullet = new Bullet(world, bulletRegion, n_x, n_y);
    return bullet;
  }

  /**
   * @return of type Square
   * This method is responsible for making the Square.
   */
  private Square makeSquare() {
    TextureRegion squareRegion =
            new TextureRegion(new Texture(Gdx.files.internal("colour_shooter/square.png")));

    int n_x = Math.round(viewport.getWorldWidth() / 2 - 4);
    int n_y = Math.round((float) (viewport.getWorldHeight() * 0.6));
    square = new Square(world, squareRegion, n_x, n_y, 16);
    square.setPosition(
            square.body.getPosition().x - square.getWidth() / 2,
            square.body.getPosition().y - square.getHeight() / 2);
    return square;
  }

  /**
   * UPDATING the STAGE AND rotates the square and shoots the bullet when screen is touched and the
   * bullet collides.
   *
   * @param dt: delta time - time between frames.
   */
  public void update(float dt) {
    stage.act(dt);

    if (Gdx.input.isTouched() & !bullet_in_motion) {
      bullet_in_motion = true;
      bullet = makeBullet();
      bullet.move(0, 300);
    }
    world.step(1 / 60f, 6, 2);

    square.rotateSquare();

    header.update(dt);
    if (header.checkGameOver()) {
      endGame();
    }

    if (bullet != null) {
      bullet.update();
      if (bullet.getIsFinished()) {
        bulletCollided();
      }
    }
  }

  /**
   * This class is responsible for updating the score when the bullet collides with the proper
   * section of the square.
   *
   * @param angle: angle formed of the rotating square.
   */
  private void updateScore(float angle) {
    String colour = header.getColour();
    if (angle == 45 || angle == 135 || angle == 225 || angle == 315) {
      header.addScore(0);
    } else if ((315 < angle & angle < 360) || (0 < angle & angle < 45)) {
      if (colour.equalsIgnoreCase("BLUE")) {
        header.addScore(10);
      } else {
        header.addScore(-5);
      }
    } else if (45 < angle & angle < 135) {
      if (colour.equalsIgnoreCase("RED")) {
        header.addScore(10);
      } else {
        header.addScore(-5);
      }
    } else if (135 < angle & angle < 225) {
      if (colour.equalsIgnoreCase("YELLOW")) {
        header.addScore(10);
      } else {
        header.addScore(-5);
      }
    } else if (225 < angle & angle < 315) {
      if (colour.equalsIgnoreCase("GREEN")) {
        header.addScore(10);
      } else {
        header.addScore(-5);
      }
    }
  }

  /**
   * Bullet collisions.
   */
  private void bulletCollided() {
    float angle = bullet.getAngleFinished();
    updateScore(angle);
    world.destroyBody(bullet.body);
    bullet = null;
    bullet_in_motion = false;
    header.setRandomColour();
  }

  /**
   * RENDERING BULLET COLLISIONS.
   *
   * @param delta
   */
  @Override
  public void render(final float delta) {
    update(delta);

    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.draw();
    RetroMania.getRetroManiaInstance().sb.setProjectionMatrix(viewport.getCamera().combined);

    RetroMania.getRetroManiaInstance().sb.begin();

    square.draw(RetroMania.getRetroManiaInstance().sb);

    RetroMania.getRetroManiaInstance().sb.end();

    RetroMania.getRetroManiaInstance().sb.begin();
    if (bullet != null) bullet.draw(RetroMania.getRetroManiaInstance().sb);

    RetroMania.getRetroManiaInstance().sb.end();

    b2ddr.render(world, viewport.getCamera().combined);
  }

  @Override
  public void resize(int width, int height) {
    viewport.update(width, height);
  }

  @Override
  public void pause() {
  }

  @Override
  public void resume() {
  }

  private void endGame() {
  }

  @Override
  public void hide() {
  }

  @Override
  public void dispose() {
    stage.dispose();
  }
}

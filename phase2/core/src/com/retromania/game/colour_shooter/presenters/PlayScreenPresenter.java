package com.retromania.game.colour_shooter.presenters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.retromania.game.colour_shooter.ColourShooterStarter;
import com.retromania.game.colour_shooter.models.Background;
import com.retromania.game.colour_shooter.models.Bullet;
import com.retromania.game.colour_shooter.models.Header;
import com.retromania.game.colour_shooter.models.Square;
import com.retromania.game.colour_shooter.models.Tank;
import com.retromania.game.colour_shooter.utils.ColourShooterListener;

public class PlayScreenPresenter {

  private Square square;
  private Header header;
  private float bullet_starting_pos;
  private boolean bullet_in_motion = false;
  private World world;
  private ColourShooterStarter mainScreen;
  public Box2DDebugRenderer b2ddr;
  private Bullet bullet = null;
  private Boolean collision = false;
  private float gameWidth, gameHeight;

  public PlayScreenPresenter(ColourShooterStarter mainScreen, float gameWidth, float gameHeight) {
    this.mainScreen = mainScreen;
    this.gameHeight = gameHeight;
    this.gameWidth = gameWidth;
    setupWorld();

    header = new Header(60);
  }

  public boolean checkCollided() {
    if (bullet != null) {
      boolean collided = bullet.getIsFinished();
      if (collided) {
        setCollision(true);
        collisionActions();
      }
      return collided;
    }
    return false;
  }

  public void justTouched() {
    if (!bullet_in_motion) {
      bullet = makeBullet();
    }
    bullet.move(0, 300);
    bullet_in_motion = true;
  }

  private void setupWorld() {
    this.world = new World(new Vector2(0, 0), true);
    this.world.setContactListener(new ColourShooterListener());
    this.b2ddr = new Box2DDebugRenderer();
  }

  public Square makeSquare() {
    TextureRegion squareRegion =
            new TextureRegion(new Texture(Gdx.files.internal("colour_shooter/square.png")));

    int n_x = Math.round(gameWidth / 2 - 4);
    int n_y = Math.round((float) (gameHeight * 0.6));
    square = new Square(world, squareRegion, n_x, n_y, 8);
    square.setPosition(
            square.body.getPosition().x - square.getWidth() / 2,
            square.body.getPosition().y - square.getHeight() / 2);
    return square;
  }

  public boolean getBulletInMotion() {
    return bullet_in_motion;
  }

  public void bulletNull() {
    getWorld().destroyBody(getBullet().body);
    bullet = null;
  }

  public Image getTank() {
    Tank tank = new Tank(mainScreen.getTankPreference());
    Image tank_img = new Image(tank.getTankTexture());
    tank_img.setSize((float) (gameWidth * 0.3), (float) (gameHeight * 0.2));
    tank_img.setPosition((gameWidth / 2) - (tank_img.getWidth() / 2), 0);
    bullet_starting_pos = tank_img.getHeight();
    return tank_img;
  }

  public Image setUpBackground() {
    Background background = new Background("play_screen");
    Image back_img = new Image(background.getBackgroundTexture());
    back_img.setSize(gameWidth, gameHeight);
    back_img.setFillParent(true);
    return back_img;
  }

  private Bullet makeBullet() {
    TextureRegion bulletRegion =
            new TextureRegion(new Texture(Gdx.files.internal("colour_shooter/newBullet.png")));
    new TextureRegion(new Texture(Gdx.files.internal("colour_shooter/newBullet.png")));

    int n_x = Math.round((gameWidth / 2) - 4);
    int n_y = Math.round(bullet_starting_pos);

    bullet = new Bullet(world, bulletRegion, n_x, n_y);
    return bullet;
  }

  public String getColourText() {
    return header.getColorText();
  }

  public String getCurrentColourText() {
    return header.getCurrentColourText();
  }

  public Color getCurrentColourObject() {
    return header.getCurrentColourObject();
  }

  public Color getColorObject() {
    return header.getColourObject();
  }

  public void collisionActions() {
    header.updateScore(square.getAngleFinished(),
            getCurrentColourText());
    getColourText();
    getColorObject();
    bulletNull();
    bullet_in_motion = false;
  }

  public int getScore() {
    return header.getCurrentScore();
  }

  public int getTime() {
    return header.getCurrentTime();
  }

  public void updateTime(float dt) {
    header.countDown(dt);
  }

  public void setCollision(Boolean collided) {
    this.collision = collided;
  }

  public boolean getCollided() {
    return this.collision;
  }

  public Bullet getBullet() {
    return bullet;
  }

  public Square getSquare() {
    return square;
  }

  public World getWorld() {
    return world;
  }
}

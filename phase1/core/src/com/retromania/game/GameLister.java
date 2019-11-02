package com.retromania.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.colour_shooter.ColourShooterStarter;
import com.retromania.game.shared_abstractions.ButtonMaker;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.shared_abstractions.UserNameTextInputListener;
import com.retromania.game.spaceship_shooter.SpaceShipShooterStarter;
import com.retromania.game.tic_tac_toe.TicTacToe;

import java.util.ArrayList;


public class GameLister extends RetroManiaScreen {

  Stage stage;
  Viewport viewport;
  List<String> list;
  private ArrayList<RetroManiaInnerGame> gameList;
  private RetroManiaInnerGame selectedGame;
  private Label bestScore;


  public GameLister(RetroManiaGame game) {

    super(game);
    gameList = new ArrayList<>();
    gameList.add(new TicTacToe(game));
    gameList.add(new SpaceShipShooterStarter(game));
    gameList.add(new ColourShooterStarter(game));


  }

  private String getStringBestUserScore(){
    if (selectedGame!= null){
      return this.selectedGame.getBestUserName() + " : " + this.selectedGame.getBestUserScore();
    }
    return "";
  }

  @Override
  public void show() {
    TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("selection.pack"));
    Skin skin = new Skin(Gdx.files.internal("list_skin.json"), atlas);

    list = new List<>(skin);
    String[] gameAndScores = new String[gameList.size()];
    for (int i = 0; i < gameList.size(); i+=1) {
      gameAndScores[i] = gameList.get(i).getName() ;
    }
    list.setItems(gameAndScores);
    ScrollPane scrollPane = new ScrollPane(list);
    scrollPane.setSmoothScrolling(true);

    viewport =
            new FitViewport(RetroManiaGame.V_WIDTH, RetroManiaGame.V_HEIGHT, new OrthographicCamera());
    stage = new Stage(viewport, game.sb);

    Table table = new Table();
    stage.addActor(table);

    table.setFillParent(true);
    Label brand =
            new Label("The RetroMania", new Label.LabelStyle(new BitmapFont(), Color.RED));

    table.pack();
    table.top();
    table.setFillParent(true);
    table.padTop(10);
    table.add().width(table.getWidth() / 3);
    table.add(brand).width(table.getWidth() / 3).expandX().row();


//    scrollPane.setPosition(
//            table.getWidth() / 2 - scrollPane.getWidth() / 4,
//            table.getWidth() / 2 - scrollPane.getHeight() / 4);
//    scrollPane.setTransform(true);
    table.padTop(20);
    table.add(scrollPane);
    TextButton button = ButtonMaker.makeButton("Play Game", 10);


    Table table2 = new Table();
    button.addListener( new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        UserNameTextInputListener userNameTextInputListener = new UserNameTextInputListener(game, selectedGame);
        Gdx.input.getTextInput(userNameTextInputListener, "User Name", "", "");
      }
    } );
    bestScore =
            new Label(getStringBestUserScore(), new Label.LabelStyle(new BitmapFont(), Color.RED));
    table2.add(button).expandX().center().row();
    table2.add(bestScore).expandX().center().row();
    table.add(table2).expandX().center().row();

//    table.add(brand1);
//    table.debug();
    Gdx.input.setInputProcessor(stage);
  }

  @Override
  public void handleInput() {}


  @Override
  public void update() {
    selectedGame = gameList.get(list.getSelectedIndex());
    bestScore.setText(getStringBestUserScore());
  }

  @Override
  public void render(float delta) {
    update();
    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    stage.act(delta);
    game.sb.setProjectionMatrix(stage.getCamera().combined);
    stage.draw();
  }

  @Override
  public void resize(int width, int height) {}

  @Override
  public void pause() {}

  @Override
  public void resume() {}

  @Override
  public void hide() {}

  @Override
  public void dispose() {
    stage.dispose();
  }
}

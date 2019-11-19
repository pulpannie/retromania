package com.retromania.game.tic_tac_toe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.retromania.game.shared_abstractions.Configuration;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaGeneralUser;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.shared_abstractions.User;

import java.util.List;

public class TicTacToeStarter extends RetroManiaInnerGame {
    public Stage stage;
    public static ShapeRenderer boardRenderer=new ShapeRenderer();
    public String currentTurn;
    public SpriteBatch batch;
    public Texture cross;
    public Texture circle;
    public OrthographicCamera gamecam;
    BitmapFont font = new BitmapFont();
    TicTacToe ticTacToe;
    public float gameWidth, gameHeight;
    CellManager cellManager;
    String winner;

    @Override
    public void handleInput() {

    }

    public TicTacToeStarter(RetroManiaGame game) {
        super(game, "Tic Tac Toe Game", RetroManiaGame.Orientation.VERTICAL);
    }

    @Override
    public void show() {
        gamecam = new OrthographicCamera();
        gamecam.setToOrtho(false, gameWidth, gameHeight);
        gameWidth = Gdx.graphics.getWidth();
        gameHeight = Gdx.graphics.getHeight();
        ticTacToe = new TicTacToe((int)gameWidth, (int)gameHeight);
        cellManager = ticTacToe.getCellManager();
        stage = new Stage(new FitViewport(gameWidth, gameHeight, gamecam));
        Gdx.input.setInputProcessor(stage);
        cross = new Texture(Gdx.files.internal("cross.jpg"));
        circle = new Texture(Gdx.files.internal("circle.png"));
        currentTurn = "Cross";
        batch = new SpriteBatch();
        batch.setProjectionMatrix(gamecam.combined);
    }

  @Override
  public void render(float delta) {
      Cell[][] cellArray = ticTacToe.getCellStates();
      Gdx.gl.glClearColor(1, 1, 1, 0);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      stage.act(delta);
      stage.draw();

    DrawBoardLine(new Vector2(gameWidth / 3, 0), new Vector2(gameWidth / 3, gameHeight), gamecam.combined);
    DrawBoardLine(new Vector2(gameWidth * 2 / 3, 0), new Vector2(gameWidth * 2 / 3, gameHeight), gamecam.combined);
    DrawBoardLine(new Vector2(0, gameHeight / 3), new Vector2(gameWidth, gameHeight / 3), gamecam.combined);
    DrawBoardLine(new Vector2(0, gameHeight * 2 / 3), new Vector2(gameWidth, gameHeight * 2 / 3), gamecam.combined);

    for(int i = 0; i < 3; i++){
        for (int j = 0; j <3; j++){
            batch.begin();
            font.getData().setScale(5.0f);
            font.draw(batch, Integer.toString(i) + Integer.toString(j), gameWidth*i/3, gameHeight*j/3 + 50);
            batch.end();
        }
    }

    if (Gdx.input.isTouched()) {
      Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
      System.out.println(mousePos);
      Vector3 worldCoordinates = gamecam.unproject(mousePos);
      ticTacToe.selectCell((int) worldCoordinates.x, (int) worldCoordinates.y);
      this.currentTurn = ticTacToe.currentTurn;
      }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                if (!cellArray[i][j].getCell().equals("None")) {
                    batch.begin();
                    batch.draw(this.getCell(cellArray[i][j].getCell()), gameWidth*i/3, gameHeight*j/3, gameWidth / 3, gameHeight / 3);
                    batch.end();
                }
            }
        }
      if(ticTacToe.isEnd()){
          winner = ticTacToe.getWinner();
          if(winner.equals("None")){
              game.setScreen(new GameOverScreen(game, "No one"));
              }
          else if(winner.equals("Cross")){
              Preferences preferences = game.getPrefrences(Configuration.tictactoePreference);
              int currScore =  preferences.getInteger(currentUser.getUserName());
              currScore += 1;
              currentUser.setScore(currScore);
              preferences.putInteger(currentUser.getUserName(), currScore);
              int bestUserScore = preferences.getInteger(Configuration.bestUserScore);
              if (bestUserScore <= currScore){
                  System.out.println(currScore);
                  preferences.putInteger(currentUser.getUserName(), currScore);
                  preferences.putString(Configuration.bestUserUserName, currentUser.getUserName());
                  preferences.putInteger(Configuration.bestUserScore, currScore);
                  bestUser = currentUser;
              }
              preferences.flush();
              game.setScreen(new GameOverScreen(game, "Cross"));

          }
          else if (winner.equals("Circle")){
              game.setScreen(new GameOverScreen(game, "Circle"));
          }
      }
    }


    public static void DrawBoardLine(Vector2 start, Vector2 end, Matrix4 projectionMatrix){
        Gdx.gl.glLineWidth(15);
        boardRenderer.setProjectionMatrix(projectionMatrix);
        boardRenderer.begin(ShapeRenderer.ShapeType.Line);
        boardRenderer.setColor(Color.BLACK);
        boardRenderer.line(start, end);
        boardRenderer.end();

    }

    public Texture getCell(String string){
          if (string == "Cross"){
              return cross;
          }
          else if (string == "Circle"){
              return circle;
          }
          return null;
      }

    //TODO Override setCurrentUser : Which sets your user and you should use this for checking whether or not you have a personal best
    @Override
    public void setCurrentUser(String name) {
        Preferences preferences = game.getPrefrences(Configuration.tictactoePreference);
        this.currentUser = new RetroManiaGeneralUser(name);
        this.currentUser.setScore(preferences.getInteger(currentUser.getUserName()));
    }
    //	TODO Override setBestUser : this is where you should try and retrieve information for your best user, look at save and retrieve functions
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
    public void resize(int width, int height) {

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

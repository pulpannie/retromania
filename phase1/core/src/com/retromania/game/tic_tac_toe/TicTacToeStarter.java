package com.retromania.game.tic_tac_toe;

import com.badlogic.gdx.Gdx;
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
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;

import java.util.List;

public class TicTacToeStarter extends RetroManiaInnerGame {
    public Stage stage;
    public static ShapeRenderer boardRenderer=new ShapeRenderer();
    public Texture currentTurn;
    public SpriteBatch batch;
    public Texture cross;
    public Texture circle;
    public OrthographicCamera gamecam;
    BitmapFont font = new BitmapFont();

    public float gameWidth, gameHeight;
    Cell[] board = new Cell[9];
    public TicTacToeLogic logic = new TicTacToeLogic(board);

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
        for(int i = 0; i < 9; i++){
            board[i] = new Cell(this, i, (int)gameWidth, (int)gameHeight);
        }
        stage = new Stage(new FitViewport(gameWidth, gameHeight, gamecam));
        Gdx.input.setInputProcessor(stage);
        cross = new Texture(Gdx.files.internal("cross.jpg"));
        circle = new Texture(Gdx.files.internal("circle.png"));
        currentTurn = cross;
        batch = new SpriteBatch();
        batch.setProjectionMatrix(gamecam.combined);
    }

  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(1, 1, 1, 0);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.act(delta);
    stage.draw();

    DrawBoardLine(new Vector2(gameWidth / 3, 0), new Vector2(gameWidth / 3, gameHeight), gamecam.combined);
    DrawBoardLine(new Vector2(gameWidth * 2 / 3, 0), new Vector2(gameWidth * 2 / 3, gameHeight), gamecam.combined);
    DrawBoardLine(new Vector2(0, gameHeight / 3), new Vector2(gameWidth, gameHeight / 3), gamecam.combined);
    DrawBoardLine(new Vector2(0, gameHeight * 2 / 3), new Vector2(gameWidth, gameHeight * 2 / 3), gamecam.combined);

    for (int i = 0; i < 9; i++) {
      batch.begin();
      font.getData().setScale(5, 5);
      font.draw(batch, String.valueOf(i), board[i].X, board[i].Y);
      batch.end();
    }

    if (Gdx.input.isTouched()) {
      Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
      Vector3 worldCoordinates = gamecam.unproject(mousePos);
      for (int i = 0; i < 9; i++) {
        if (board[i].inCell(worldCoordinates.x, worldCoordinates.y)) {
          if (!board[i].isTouched) {
            board[i].isTouched = true;
            board[i].setCell(currentTurn);
            currentTurn = switchTurns(currentTurn);
          }
        }
      }

    }
    for (int i = 0; i < 9; i++) {
      if (board[i].isTouched) {
        batch.begin();
        batch.draw(board[i].getCell(), board[i].X, board[i].Y, gameWidth / 3, gameHeight / 3);
        batch.end();
      }
    }
      if(logic.isEnd()){
          System.out.println("END");
          if(logic.nowinner){
              System.out.println("NOWIN");
              game.setScreen(new GameOverScreen(game, "No one"));
              }
          else if(logic.winner() == cross){
              System.out.println("CROSS");
              game.setScreen(new GameOverScreen(game, "Cross"));

          }
          else if (logic.winner() == circle){
              System.out.println("CIRCLE");
              game.setScreen(new GameOverScreen(game, "Circle"));
          }
      }
}

    public Texture switchTurns(Texture currentTurn){
        if (currentTurn == cross){
            currentTurn = circle;
        }
        else{
            currentTurn = cross;
        }
        return currentTurn;
    }

    public static void DrawBoardLine(Vector2 start, Vector2 end, Matrix4 projectionMatrix){
        Gdx.gl.glLineWidth(15);
        boardRenderer.setProjectionMatrix(projectionMatrix);
        boardRenderer.begin(ShapeRenderer.ShapeType.Line);
        boardRenderer.setColor(Color.BLACK);
        boardRenderer.line(start, end);
        boardRenderer.end();

    }

    @Override
    public void save(Object... args) {

    }

    @Override
    public List<Object> retreave() {
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

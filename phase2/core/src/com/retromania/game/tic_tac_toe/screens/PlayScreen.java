package com.retromania.game.tic_tac_toe.screens;

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
import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.tic_tac_toe.individuals.Cell;
import com.retromania.game.tic_tac_toe.individuals.CellManager;
import com.retromania.game.tic_tac_toe.individuals.TicTacToe;

public class PlayScreen extends RetroManiaScreen {
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

    public PlayScreen(Boolean cats){
        System.out.println(cats);
        if (cats){
            cross = new Texture(Gdx.files.internal("tic_tac_toe/cat2.png"));
            circle = new Texture(Gdx.files.internal("tic_tac_toe/cat3.png"));
        }
        else {
            cross = new Texture(Gdx.files.internal("tic_tac_toe/cross.jpg"));
            circle = new Texture(Gdx.files.internal("tic_tac_toe/circle.png"));
        }
        gameWidth = Gdx.graphics.getWidth();
        gameHeight = Gdx.graphics.getHeight();
        ticTacToe = new TicTacToe((int)gameWidth, (int)gameHeight);
        cellManager = ticTacToe.getCellManager();
        currentTurn = "Cross";
    }

    @Override
    public void show() {
        gamecam = new OrthographicCamera();
        gamecam.setToOrtho(false, gameWidth, gameHeight);
        stage = new Stage(new FitViewport(gameWidth, gameHeight, gamecam));
        Gdx.input.setInputProcessor(stage);
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


        /*DEBUGGING PURPOSE*/
        for(int i = 0; i < 3; i++){
            for (int j = 0; j <3; j++){
                batch.begin();
                font.getData().setScale(5.0f);
                font.draw(batch, Integer.toString(i) + Integer.toString(j), gameWidth*i/3, gameHeight*j/3 + 50);
                batch.end();
            }
        }
        /*END*/

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

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void handleInput() {

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

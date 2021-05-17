package controller;

import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.tinylog.Logger;
import sudoku.model.Coordinates;
import sudoku.model.SudokuGame;
import sudoku.utility.SudokuTextField;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import sudoku.enums.GameState;
import sudoku.utility.GameLogic;
import sudoku.utility.Stopwatch;

import javax.inject.Inject;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import sudoku.utility.IOclass;

public class GameController {
    @FXML private GridPane Game;
    @FXML private Button Finish;
    @FXML private Label StopwatcLabel;
    @FXML private Label PlayerNameLabel;

    @Inject private FXMLLoader fxmlLoader;

    private final Stopwatch stopwatch = new Stopwatch();
    private SudokuGame current_game=GameLogic.getNewGame();
    private HashMap<Coordinates, SudokuTextField> textFieldCoordinates;
    private final int[][] current_grid=current_game.getCopyOfGridState();
    private Instant startime;
    private String player;

    public void setPlayerName(String playerName) {
        this.player=playerName;
    }

    public void lockTable(){
        for(int x=0; x<9; x++){
            for(int y=0; y<9; y++){
                SudokuTextField tile = textFieldCoordinates.get(new Coordinates(x,y));
                tile.setDisable(true);
            }
        }
    }

    public void updateSquare(int x, int y, int input){
        SudokuTextField tile = textFieldCoordinates.get(new Coordinates(x,y));

        String value = Integer.toString(input);

        tile.textProperty().setValue(value);
    }

    public void handle(KeyEvent event){
        if(event.getEventType()==KeyEvent.KEY_PRESSED){
            if(event.getText().matches("[0-9]")){
                int value = Integer.parseInt(event.getText());
                handleInput(value, event.getSource());
            }else{
                handleInput(0,event.getSource());
            }
        }
        event.consume();
    }

    private void handleInput(int value, Object source) {
        onSudokuInput(
                ((SudokuTextField) source).getX(),
                ((SudokuTextField) source).getY(),
                value,current_game
        );
        Logger.info("cell ({} {}) is updated, value is {}",((SudokuTextField) source).getX(),((SudokuTextField) source).getY(),value);
    }
    public void onSudokuInput(int x, int y, int input, SudokuGame state) {
        SudokuGame gameData = state;
        current_game.InsertToTable(x,y,input);
        int[][] newGridState = gameData.getCopyOfGridState();
        newGridState[x][y] = input;

        gameData = new SudokuGame(GameLogic.checkForCompletion(newGridState),newGridState);
        updateSquare(x,y,input);

        Logger.info("Current state is {}", gameData.getGameState());
        if(gameData.getGameState() == GameState.COMPLETE){
            System.out.println("Complete");
            var text = textFieldCoordinates.get(new Coordinates(x,y));
            text.setDisable(true);
            Finish.setDisable(false);
            stopwatch.stop();
            lockTable();
        }
    }

    public void handleFinishButton(javafx.event.ActionEvent actionEvent) throws IOException {
        Logger.info("Finish button is pressed, saving leaderboard");
        IOclass.updateBoard(player, Duration.between(startime, Instant.now()).getSeconds());
        fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/leadboard.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void filltable(){
        for (int x=0; x<9; x++)
        {
            for(int y=0; y<9; y++)
            {
                SudokuTextField tile = new SudokuTextField(x,y);
                if(current_grid[x][y]==0){
                    tile.setText(" ");
                    tile.setDisable(false);
                }
                else {
                    tile.setText(Integer.toString(current_grid[x][y]));
                    tile.setDisable(true);
                }
                textFieldCoordinates.put(new Coordinates(x,y),tile);
                Game.add(tile,x,y);
                tile.setOnKeyPressed((event)->{handle(event);});
            }
        }
    }
    private void cleartable(){
        for (int x=0; x<9; x++)
        {
            for(int y=0; y<9; y++)
            {
                SudokuTextField field = textFieldCoordinates.get(new Coordinates(x,y));
                Game.getChildren().remove(field);
            }
        }
        textFieldCoordinates.clear();
    }
    @FXML
    public void initialize(){
        StopwatcLabel.textProperty().bind(stopwatch.hhmmssProperty());
        if(stopwatch.getStatus()== Animation.Status.PAUSED){
            stopwatch.reset();
        }
        stopwatch.start();
        textFieldCoordinates=new HashMap<>();
        filltable();
        Platform.runLater(()->PlayerNameLabel.setText(player));
        startime=Instant.now();
    }
}

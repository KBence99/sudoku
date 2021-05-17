package controller;

import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sudoku.SudokuTextfield;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import sudoku.*;
import sudoku.constants.GameState;
import sudoku.utility.GameLogic;
import sudoku.utility.Stopwatch;

import javax.inject.Inject;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import sudoku.utility.IOclass;

public class gamecontroller{
    @FXML private GridPane Game;
    @FXML private Button Restart;
    @FXML private Button Finish;
    @FXML private Label StopwatcLabel;
    @FXML private Label PlayerNameLabel;

    @Inject private FXMLLoader fxmlLoader;

    private final Stopwatch stopwatch = new Stopwatch();
    private SudokuGame current_game=GameLogic.getNewGame();
    private HashMap<Coordinates, SudokuTextfield> textFieldCoordinates;
    private final int[][] current_grid=current_game.getCopyOfGridState();
    private Instant startime;
    private String player;

    public void setPlayerName(String playerName) {
        this.player=playerName;
    }

    public void startGame(){
        current_game = GameLogic.getNewGame();
        filltable();
    }

    public void updateSquare(int x, int y, int input){
        SudokuTextfield tile = textFieldCoordinates.get(new Coordinates(x,y));

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
                ((SudokuTextfield) source).getX(),
                ((SudokuTextfield) source).getY(),
                value,current_game
        );
    }
    public void onSudokuInput(int x, int y, int input, SudokuGame state) {
        SudokuGame gameData = state;
        int[][] newGridState = gameData.getCopyOfGridState();
        newGridState[x][y] = input;

        gameData = new SudokuGame(GameLogic.checkForCompletion(newGridState),newGridState);
        updateSquare(x,y,input);

        if(gameData.getGameState() == GameState.COMPLETE){
            System.out.println("Complete");
            var text = textFieldCoordinates.get(new Coordinates(x,y));
            text.setDisable(true);
            Restart.setVisible(false);
            Finish.setVisible(true);
            stopwatch.stop();
        }
    }

    public void handleRestartButton( javafx.event.ActionEvent actionEvent) throws IOException{
        stopwatch.stop();
        stopwatch.reset();
        stopwatch.start();
    }

    public void handleFinishButton(javafx.event.ActionEvent actionEvent) throws IOException {
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
                SudokuTextfield tile = new SudokuTextfield(x,y);
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
                SudokuTextfield field = textFieldCoordinates.get(new Coordinates(x,y));
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

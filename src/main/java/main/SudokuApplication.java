package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.tinylog.Logger;


public class SudokuApplication extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        Logger.info("Starting application");
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/start.fxml"));
        primaryStage.setTitle("Sudoku Game");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}

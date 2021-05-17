package controller;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javax.inject.Inject;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.tinylog.Logger;
import sudoku.leaderboard.GameResult;
import sudoku.leaderboard.LeaderBoard;
import sudoku.utility.IOclass;


public class LeadboardController {
    @Inject private FXMLLoader fxmlLoader;

    @FXML private TableView<GameResult> leadboard;

    @FXML private TableColumn<GameResult, String> player;

    @FXML private TableColumn<GameResult, Long> time;

    @FXML private Button restart;

    private LeaderBoard board;

    public void newGame(ActionEvent actionEvent) throws IOException {
        fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/start.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        Logger.info("Starting a new game");
    }

    @FXML
    private void initialize() throws IOException {
        Logger.debug("Loading scoreboard...");
        board = IOclass.getBoard();

        ObservableList<GameResult> list = FXCollections.observableArrayList(board.leaderboard);

        player.setCellValueFactory(new PropertyValueFactory<GameResult, String>("Player"));
        time.setCellValueFactory(new PropertyValueFactory<GameResult, Long>("Time"));
        leadboard.setItems(list);
    }
}

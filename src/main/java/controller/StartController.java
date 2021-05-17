package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.tinylog.Logger;

import javax.inject.Inject;
import java.io.IOException;

public class StartController {

    @Inject private FXMLLoader fxmlLoader;

    @FXML private TextField playerNameTextField;
    @FXML private Label errorLabel;

    private String playername;

    public void initialize(){
        playerNameTextField.setText(System.getProperty("user.name"));
    }

    public void StartAction(ActionEvent actionEvent) throws IOException{
        if (playerNameTextField.getText().isEmpty()){
            errorLabel.setText("* No username given");
        }else
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
            Parent root = fxmlLoader.load();
            playername=playerNameTextField.getText();
            GameController controller = fxmlLoader.<GameController>getController();
            controller.setPlayerName(playername);
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            Logger.info("The username is set to {}, loading game scene", playername);
        }
    }
}

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

import java.io.IOException;

public class startcontroller {

    @FXML private TextField usernamefield;
    @FXML private Label errorLabel;

    public void StartAction(ActionEvent actionEvent) throws IOException{
        if (usernamefield.getText().isEmpty())
        {
            errorLabel.setText("* Username is empty!");
        }
    }
}

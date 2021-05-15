package controller;

import interfaces.SudokuTextfield;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class gamecontroller {
    @FXML private GridPane Game;

    private void filltable(){
        for (int row=0; row<9; row++)
        {
            for(int column=0; column<9; column++)
            {
                Game.add(new SudokuTextfield(row,column),row,column);
            }
        }
    }

    public void initialize(){
        filltable();
    }
}

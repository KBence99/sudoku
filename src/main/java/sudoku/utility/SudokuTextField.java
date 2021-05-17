package sudoku.utility;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import org.tinylog.Logger;

/**
 * Custom TextField class for greater control over the Textfield.
 */
public class SudokuTextField extends TextField {

    private final int x;

    private final int y;

    /**
     * Initializes the Sudoku game with its coordinates as well as giving it some other properties.
     * @param x x location of TextField.
     * @param y y location of TextField.
     */
    public SudokuTextField(int x, int y) {
        this.x = x;
        this.y = y;
        this.setPrefWidth(30);
        this.setPrefHeight(30);
        this.setAlignment(Pos.CENTER);
        if((x+y)%2==0)
            this.setStyle("-fx-background-color: LightBlue;");
        else
            this.setStyle("-fx-background-color: LightCyan;");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Replaces the TextFields current value with a new one, if its between 0 and 9.
     * @param i first index of string, unused here.
     * @param i1 last index of string, unused here.
     * @param s text the previous one is being replaced with.
     */
    @Override
    public void replaceText(int i, int i1, String s) {
        if (s.matches("[1-9]")) {
            this.setText(s);
        }else if (s.matches("0")) {
            this.setText(" ");
        }else {
            Logger.info("Invalid key {}",s);
        }
    }
}

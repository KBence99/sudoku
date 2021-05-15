package interfaces;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

public class SudokuTextfield extends TextField {
    private final int x;
    private final int y;

    public SudokuTextfield(int x, int y) {
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

    @Override
    public void replaceText(int i, int i1, String s) {
        if (s.matches("[1-9]")) {
            this.setText(s);
        }
    }
    @Override
    public void replaceSelection(String s){
        if(s.matches("[1-9]")){
            this.setText(s);
        }
    }
}

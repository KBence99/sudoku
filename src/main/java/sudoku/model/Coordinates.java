package sudoku.model;

import java.util.Objects;

/**
 * Class for representing Coordinates.
 */
public class Coordinates {
    private final int x;
    private final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Determines if two coordinates are equal based on their x and y values.
     * @param o target object that the current one is being compared to.
     * @return boolean value.
     */
    @Override
    public boolean equals(Object o){
        Coordinates that = (Coordinates) o;
        return x==that.x && y==that.y;
    }

    /**
     * Generates a hashCode from the coordinates.
     * @return a hashCode used for identifying objects.
     */
    @Override
    public int hashCode(){
        return Objects.hash(x,y);
    }
}

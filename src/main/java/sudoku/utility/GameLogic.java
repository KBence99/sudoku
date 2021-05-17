package sudoku.utility;

import sudoku.model.SudokuGame;
import sudoku.enums.GameState;
import sudoku.enums.Rows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static sudoku.model.SudokuGame.GRID_BOUNDARY;

/**
 * Class for programming logic.
 */
public class GameLogic {
    /**
     * Generates a new SudokuGame.
     * @return a new SudokuGame object with New as a state and a new grid.
     */
    public static SudokuGame getNewGame() {
        return new SudokuGame(
                GameState.NEW,
                GameGenerator.getNewGameGrid()
        );
    }

    /**
     * Checks if a grid is complete and returns a GameState.
     * @param grid grid being checked.
     * @return Gamestate, COMPLETE if true, ACTIVE if false.
     */
   public static GameState checkForCompletion(int[][] grid) {
        if (sudokuIsInvalid(grid)) return GameState.ACTIVE;
        if (tilesAreNotFilled(grid)) return GameState.ACTIVE;
        return GameState.COMPLETE;
    }

    /**
     * Checks if there are missing tiles in the Sudoku grid.
     * @param grid grid being checked.
     * @return Ture if there's at least one value missing, false otherwise.
     */
    public static boolean tilesAreNotFilled(int[][] grid) {
        for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
            for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
                if (grid[xIndex][yIndex] == 0) return true;
            }
        }
        return false;
    }

    /**
     * Checks if the Sudoku grid abides by the rules of Sudoku.
     * @param grid grid being tested.
     * @return True if the grid is correct, False otherwise.
     */
    public static boolean sudokuIsInvalid(int[][] grid) {
        if (rowsAreInvalid(grid)) return true;
        if (columnsAreInvalid(grid)) return true;
        if (squaresAreInvalid(grid)) return true;
        else return false;
    }

    /**
     * Checks the 9 3x3 squares are valid.
     * @param grid grid being checked.
     * @return boolean value.
     */
    public static boolean squaresAreInvalid(int[][] grid) {
        if (rowOfSquaresIsInvalid(Rows.TOP, grid)) return true;

        if (rowOfSquaresIsInvalid(Rows.MIDDLE, grid)) return true;

        if (rowOfSquaresIsInvalid(Rows.BOTTOM, grid)) return true;

        return false;
    }

    private static boolean rowOfSquaresIsInvalid(Rows value, int[][] grid) {
        switch (value) {
            case TOP:
                if (squareIsInvalid(0, 0, grid)) return true;

                if (squareIsInvalid(0, 3, grid)) return true;

                if (squareIsInvalid(0, 6, grid)) return true;

                return false;

            case MIDDLE:
                if (squareIsInvalid(3, 0, grid)) return true;

                if (squareIsInvalid(3, 3, grid)) return true;

                if (squareIsInvalid(3, 6, grid)) return true;
                return false;

            case BOTTOM:
                if (squareIsInvalid(6, 0, grid)) return true;

                if (squareIsInvalid(6, 3, grid)) return true;

                if (squareIsInvalid(6, 6, grid)) return true;
                return false;

            default:
                return false;
        }
    }

    /**
     * Checks if a grids squares are valid.
     * @param yIndex yIndex of square.
     * @param xIndex xIndex of square.
     * @param grid grid being checked.
     * @return boolean value
     */
    public static boolean squareIsInvalid(int yIndex, int xIndex, int[][] grid) {
        int yIndexEnd = yIndex + 3;
        int xIndexEnd = xIndex + 3;

        List<Integer> square = new ArrayList<>();

        while (yIndex < yIndexEnd) {

            while (xIndex < xIndexEnd) {
                square.add(
                        grid[xIndex][yIndex]
                );
                xIndex++;
            }

            xIndex -= 3;

            yIndex++;
        }

        if (collectionHasRepeats(square)) return true;
        return false;
    }

    /**
     * Checks if the grids columns are valid.
     * @param grid grid being checked.
     * @return boolean value.
     */
    public static boolean columnsAreInvalid(int[][] grid) {
        for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
            List<Integer> row = new ArrayList<>();
            for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
                row.add(grid[xIndex][yIndex]);
            }

            if (collectionHasRepeats(row)) return true;
        }

        return false;
    }

    /**
     * Checking if the grid rows are valid.
     * @param grid grid being checked.
     * @return boolean value.
     */
    public static boolean rowsAreInvalid(int[][] grid) {
        for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
            List<Integer> row = new ArrayList<>();
            for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
                row.add(grid[xIndex][yIndex]);
            }

            if (collectionHasRepeats(row)) return true;
        }

        return false;
    }

    /**
     * Checking if a collection has repeats.
     * @param collection collection being checked.
     * @return boolean value.
     */
    public static boolean collectionHasRepeats(List<Integer> collection) {
        for (int index = 1; index <= GRID_BOUNDARY; index++) {
            if (Collections.frequency(collection, index) > 1) return true;
        }

        return false;
    }
}

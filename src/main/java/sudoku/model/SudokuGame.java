package sudoku.model;

import sudoku.enums.GameState;
import sudoku.utility.SudokuUtilities;

/**
 *Class for representing a game of Sudoku.
 */
public class SudokuGame{

    private final GameState gameState;

    private final int[][] gridState;

    /**
     * The amount of rows and columns, always the same.
     */
    public static final int GRID_BOUNDARY = 9;
    /**
     * The among of numbers removed in each game.
     */
    public static final int MISSING_TILES = 3;

    public SudokuGame(GameState gameState, int[][] gridState) {
        this.gameState = gameState;
        this.gridState = gridState;
    }

    public GameState getGameState() {
        return gameState;
    }

    /**
     * Inserts a number into the current Sudoku table.
     * @param x x location.
     * @param y y location.
     * @param v value.
     */
    public void InsertToTable(int x, int y, int v){
        gridState[x][y]=v;
    }
    /**
     * Returns a copy of the current games Grid state.
     * @return a grid with the same values as the current games grid.
     */
    public int[][] getCopyOfGridState() {
        return SudokuUtilities.copyToNewArray(gridState);
    }

}

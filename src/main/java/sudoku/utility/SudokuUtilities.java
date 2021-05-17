package sudoku.utility;

import sudoku.model.SudokuGame;

/**
 * Classes for copying arrays.
 */
public class SudokuUtilities {
    /**
     * Copies one grids value to another one.
     * @param oldArray array being copied from.
     * @param newArray array being copied to.
     */
    public static void copySudokuArrayValues(int[][] oldArray, int[][] newArray){
        for(int xIndex = 0; xIndex< SudokuGame.GRID_BOUNDARY; xIndex++) {
            for(int yIndex=0; yIndex<SudokuGame.GRID_BOUNDARY;yIndex++) {
                newArray[xIndex][yIndex] = oldArray[xIndex][yIndex];
            }
        }
    }

    /**
     * Returns an Sudoku grid.
     * @param oldArray Sudoku grid copied from.
     * @return oldArrays value.
     */
    public static int[][] copyToNewArray(int[][] oldArray){
        int[][] newArray = new int[SudokuGame.GRID_BOUNDARY][SudokuGame.GRID_BOUNDARY];

        for(int xIndex=0; xIndex<SudokuGame.GRID_BOUNDARY;xIndex++) {
            for(int yIndex=0; yIndex<SudokuGame.GRID_BOUNDARY;yIndex++) {
                newArray[xIndex][yIndex] = oldArray[xIndex][yIndex];
            }
        }
        return newArray;
    }

}

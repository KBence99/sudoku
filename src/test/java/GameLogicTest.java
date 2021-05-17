import org.junit.jupiter.api.Test;
import sudoku.enums.GameState;
import sudoku.utility.GameLogic;

import java.util.Arrays;
import java.util.List;


public class GameLogicTest {

    @Test
    public void onValidateValidPuzzle() {
        assert (GameState.COMPLETE ==
                GameLogic.checkForCompletion(
                        TestData.getSolved().getCopyOfGridState()
                )
        );
    }

    @Test
    public void onValidateActivePuzzle() {
        assert (GameState.ACTIVE ==
                GameLogic.checkForCompletion(
                        TestData.getValidStart().getCopyOfGridState()
                )
        );
    }

    @Test
    public void gameSquaresAreNotFilled() {
        assert (GameLogic.tilesAreNotFilled(TestData.getValidStart().getCopyOfGridState()));
    }

    @Test
    public void gameSquaresAreFilled() {
        assert (!GameLogic.tilesAreNotFilled(TestData.getSolved().getCopyOfGridState()));
    }

    @Test
    public void gameSquaresAreInvalid() {
        int[][] invalid = TestData.getInvalid().getCopyOfGridState();

        boolean isInvalid = GameLogic.squaresAreInvalid(invalid);
        assert (isInvalid);
    }

    @Test
    public void gameSquaresAreValid() {
        int[][] valid = TestData.getSolved()
                .getCopyOfGridState();

        boolean isInvalid = GameLogic.squaresAreInvalid(
                valid
        );

        assert (!isInvalid);
    }

    @Test
    public void gameColumnsAreInvalid() {
        int[][] invalid = TestData.getInvalid()
                .getCopyOfGridState();

        boolean isInvalid = GameLogic.columnsAreInvalid(
                invalid
        );
        assert (isInvalid);
    }

    @Test
    public void gameColumnsAreValid() {
        int[][] valid = TestData.getSolved().getCopyOfGridState();

        boolean isInvalid = GameLogic.columnsAreInvalid(valid);
        assert (!isInvalid);
    }

    @Test
    public void gameRowsAreInvalid() {
        int[][] invalid = TestData.getInvalid().getCopyOfGridState();

        boolean isInvalid = GameLogic.rowsAreInvalid(invalid);
        assert (isInvalid);
    }

    @Test
    public void gameRowsAreValid() {
        int[][] valid = TestData.getSolved().getCopyOfGridState();

        boolean isInvalid = GameLogic.rowsAreInvalid(valid);
        assert (!isInvalid);
    }

    @Test
    public void collectionHasRepeats() {
        List<Integer> testList = Arrays.asList(0, 0, 0, 1, 1, 0, 0, 0, 0);
        boolean hasRepeats = GameLogic.collectionHasRepeats(testList);

        assert (hasRepeats);

    }

    @Test
    public void collectionHasNoRepeats() {
        List<Integer> testListOne = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0);
        List<Integer> testListTwo = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        boolean hasRepeatsOne = GameLogic.collectionHasRepeats(testListOne);
        boolean hasRepeatsTwo = GameLogic.collectionHasRepeats(testListTwo);

        assert (!hasRepeatsOne);
        assert (!hasRepeatsTwo);
    }
}

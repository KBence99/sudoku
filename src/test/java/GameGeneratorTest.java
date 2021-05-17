import org.junit.jupiter.api.Test;
import sudoku.utility.GameLogic;

public class GameGeneratorTest {

    @Test
    public void onGenerateNewPuzzle() {
        int[][] newPuzzle = GameLogic.getNewGame().getCopyOfGridState();

        assert (!GameLogic.rowsAreInvalid(newPuzzle));
        assert (!GameLogic.columnsAreInvalid(newPuzzle));
        assert (!GameLogic.squaresAreInvalid(newPuzzle));

    }


    @Test
    public void test20NewPuzzles(){
        for (int testIndex = 0; testIndex < 20; testIndex++){

            int[][] newPuzzle = GameLogic.getNewGame().getCopyOfGridState();

            assert (!GameLogic.rowsAreInvalid(newPuzzle));
            assert (!GameLogic.columnsAreInvalid(newPuzzle));
            assert (!GameLogic.squaresAreInvalid(newPuzzle));
        }
    }
}

package sudoku.leaderboard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Class for containing GameResult objects.
 */
@lombok.Data
public class LeaderBoard {

    /**
     * A list of GameResults.
     */
    public List<GameResult> leaderboard;

    public LeaderBoard(){
        this.leaderboard =new ArrayList<>();
    }

    /**
     * Sorts the results by time then selects the first 10 to save.
     */
    public void sortBoard(){
        this.leaderboard.sort(Comparator.comparing(GameResult::getTime));
        this.leaderboard = this.leaderboard.stream().limit(10).collect(Collectors.toList());
    }
}

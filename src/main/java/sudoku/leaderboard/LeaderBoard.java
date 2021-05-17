package sudoku.leaderboard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for containing several Game results
 */
@lombok.Data
public class LeadBoard {
    /**
     * A list for containing gameresults
     */
    public List<GameResult> leaderboard;

    /**
     * Initializing the leaderboard with an empty arraylist
     */
    public LeadBoard(){
        this.leaderboard =new ArrayList<>();
    }


    public void sortBoard(){
        this.leaderboard.sort(Comparator.comparing(GameResult::getTime));
        this.leaderboard = this.leaderboard.stream().limit(10).collect(Collectors.toList());
    }
}

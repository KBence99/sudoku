package sudoku.leaderboard;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 *Class to represent a games score.
 */
@lombok.Data
public class GameResult {

    private String player;

    private long time;
}
package sudoku;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Duration;

@lombok.Data
public class GameResult {

    @JsonProperty("player")
    private String player;

    @JsonProperty("time")
    private long time;
}
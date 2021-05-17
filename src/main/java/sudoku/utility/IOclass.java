package sudoku.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import sudoku.leaderboard.GameResult;
import sudoku.leaderboard.LeaderBoard;

import java.io.*;

/**
 * Class to read and write the JSON file used for storing <code>LeaderBoard</code> data.
 */
public class IOclass {

    private static File GAME_DATA = new File("leaderboard.json");

    private static ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    private static LeaderBoard board;

    /**
     * Updates the Leaderboard with the current games score.
     * @param name The players name.
     * @param time The time it took for the player to finish the game, counted in seconds
     * @throws IOException if an I/O error occurs.
     */
    public static void updateBoard(String name, long time) throws IOException{
       board=getBoard();
       GameResult result = new GameResult();
       result.setPlayer(name);
       result.setTime(time);
       board.leaderboard.add(result);
       board.sortBoard();
        if(!GAME_DATA.exists()){
            GAME_DATA.createNewFile();
        }

        try(FileWriter write = new FileWriter(GAME_DATA)){
            objectMapper.writeValue(write,board);
            write.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Loads in the Leaderboard from the JSON file.
     * @return Leaderboard.
     * @throws IOException  if I/O exception occurs.
     */
    public static LeaderBoard getBoard() throws IOException{
        LeaderBoard board;
        try(FileReader read = new FileReader(GAME_DATA)){
            board = objectMapper.readValue(GAME_DATA, LeaderBoard.class);
        }catch(IOException e){
            board = new LeaderBoard();
        }
        return board;
    }
}

package sudoku;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@lombok.Data
public class LeadBoard {
    public List<GameResult> leadboard;

    public LeadBoard(){
        this.leadboard=new ArrayList<>();
    }
    public void sortBoard(){
        this.leadboard.sort(Comparator.comparing(GameResult::getTime));
        this.leadboard = this.leadboard.stream().limit(10).collect(Collectors.toList());
    }
}

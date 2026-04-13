//Jingzu Ge

import java.util.Stack;
import java.util.Random;

public class Gomokulogic {
    private int size = Constants.BOARD_SIZE;
    private int[][] board;
    private int currentPlayer = Constants.BLACK;
    private MergeSystem mergeSystem = new MergeSystem();
    private HistoryManager history = new HistoryManager();

    public Gomokulogic() {
        board = new int[size][size];
    }

    public int[][] getBoard() {
        return board;
    }
}







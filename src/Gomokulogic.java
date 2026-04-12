//Jingzu Ge

import java.util.Stack;
import java.util.Random;

public class Gomokulogic {

        // Define the size of the chessboard
        private int size = 15;
        private int[][] board;
        public int gameMode = 0; // 0: Classic, 1: Merging Mode
        private int currentTurn = 1; // 1: Black, 2: White

        // Add a stack to record the history of playing chess for repentance. Array save: [row, col, color, type] (type 0=normal drop, 1=fusion drop)
        private Stack<int[]> moveHistory;
    // Constructor
    public Gomokulogic() {
        board = new int[size][size];
        moveHistory = new Stack<>();
    }

    public int[][] getBoard() {
        return board;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }



}

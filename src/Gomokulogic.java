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
    // Method to put a piece on the board
    public boolean placePiece(int r, int c, int color) {
        // Check if the position is inside the 15x15 board
        if (r >= 0 && r < 15 && c >= 0 && c < 15) {
            // Only place if the spot is empty (0)
            if (board[r][c] == 0) {
                board[r][c] = color;
                moveHistory.push(new int[]{r, c, color, 0}); // Record common drop
                switchTurn(); // Switch round
                return true;
            } else if (gameMode == 1 && board[r][c] == color) {
                board[r][c] = color + 10;
                moveHistory.push(new int[]{r, c, color, 1}); // Record fusion drop
                switchTurn(); // Switch round
                System.out.println("Success: Merged.");
                return true;
            } else {
                System.out.println("Error: Spot already taken.");
            }
        } else {
            System.out.println("Error: Out of bounds.");
        }
        return false;
    }
    // Switch current round
    private void switchTurn() {
        currentTurn = (currentTurn == 1) ? 2 : 1;
    }

    // Repentance method
    public boolean undo() {
        if (moveHistory.isEmpty()) {
            return false; // You can't repent if you haven't played chess
        }
        int[] lastMove = moveHistory.pop();
        int r = lastMove[0];
        int c = lastMove[1];
        int type = lastMove[3];

        if (type == 0) {
            board[r][c] = 0;
        } else if (type == 1) {
            board[r][c] -= 10;
        }
        switchTurn(); // Turn backward
        return true;
    }

    // To assist in judging whether a piece belongs to a certain party (including the fused pieces)
    private boolean isPlayer(int val, int color) {
        return val == color || val == (color + 10);
    }

    // Check if the game is over
    public boolean checkWin(int r, int c, int color) {
        int count;
        // Horizontal
        count = 1;
        for (int i = c + 1; i < 15; i++) {
            if (isPlayer(board[r][i], color)) count++;
            else break;
        }
        for (int i = c - 1; i >= 0; i--) {
            if (isPlayer(board[r][i], color)) count++;
            else break;
        }
        if (count >= 5) return true;

        // Vertical
        count = 1;
        for (int i = r + 1; i < 15; i++) {
            if (isPlayer(board[i][c], color)) count++;
            else break;
        }
        for (int i = r - 1; i >= 0; i--) {
            if (isPlayer(board[i][c], color)) count++;
            else break;
        }
        if (count >= 5) return true;
        // Diagonal \
        count = 1;
        for (int i = 1; r + i < 15 && c + i < 15; i++) {
            if (isPlayer(board[r + i][c + i], color)) count++;
            else break;
        }
        for (int i = 1; r - i >= 0 && c - i >= 0; i--) {
            if (isPlayer(board[r - i][c - i], color)) count++;
            else break;
        }
        if (count >= 5) return true;

        // Diagonal /
        count = 1;
        for (int i = 1; r + i < 15 && c - i >= 0; i++) {
            if (isPlayer(board[r + i][c - i], color)) count++;
            else break;
        }
        for (int i = 1; r - i >= 0 && c + i < 15; i++) {
            if (isPlayer(board[r - i][c + i], color)) count++;
            else break;
        }
        if (count >= 5) return true;

        return false;
    }

    // Easy AI: find a random empty spot to move
    public int[] easyAIMove(int aiColor) {
        Random rand = new Random();
        int r, c;
        if (isFull()) return null;
        while (true) {
            r = rand.nextInt(15);
            c = rand.nextInt(15);
            if (board[r][c] == 0) {
                placePiece(r, c, aiColor);
                return new int[]{r, c};
            }
        }
    }

    // Get what is at (r, c)
    public int getPiece(int r, int c) {
        if (r >= 0 && r < 15 && c >= 0 && c < 15) {
            return board[r][c];
        }
        return -1;
    }

    // Check if the board is full
    public boolean isFull() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }


    public void reset() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                board[i][j] = 0;
            }
        }
        currentTurn = 1;
        moveHistory.clear();
    }
}







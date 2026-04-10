//Jingzu Ge

public class Gomokulogic {
    //Define the size of the chessboard
    private int size = 15;
    private int[][] board;

    public int gameMode = 0;// 0: Classic, 1: Merging Mode
    //Consteuctor
    public Gomokulogic() {
        board = new int[size][size];
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + "");
            }
            System.out.println();
        }
    }

    // Method to put a piece on the board
    public boolean placePiece(int r, int c, int color) {
        // Check if the position is inside the 15x15 board
        if (r >= 0 && r < 15 && c >= 0 && c < 15) {

            // Only place if the spot is empty (0)
            if (board[r][c] == 0) {
                board[r][c] = color;
                return true;
            } else {
                System.out.println("Error: Spot already taken.");
            }
        } else {
            System.out.println("Error: Out of bounds.");
        }

        return false; // Failed to place
    }
    private boolean isPlayer(int val, int color) {
        // If color is 1, this returns true for 1 and 11
        // If color is 2, this returns true for 2 and 12
        return val == color || val == (color + 10);
    }
    // Check if the game is over
    public boolean checkWin(int r, int c, int color) {
        int count;
        //Horizontal
        count = 1;
        for (int i = c + 1; i < 15; i++) {
            if (isPlayer(board[r][i], color) count++;
            else break;
        }
        for (int i = c - 1; i >= 0; i--) {
            if (isPlayer(board[r][i], color) count++;
            else break;
        }
        if (count >= 5) return true;

        //Vertical
        count = 1;
        for (int i = r + 1; i < 15; i++) {
            if (isPlayer(board[i][c], color) count++;
            else break;
        }
        for (int i = r - 1; i >= 0; i--) {
            if (isPlayer(board[i][c], color) count++;
            else break;
        }
        if (count >= 5) return true;
        //Diagonal
        count = 1;
        for (int i = 1; r + i < 15 && c + i < 15; i++) {
            if (isPlayer(board[r + i][c - i], color) count++;
            else break;
        }
        for (int i = 1; r - i >= 0 && c - i >= 0; i--) {
            if (isPlayer(board[r - i][c + i], color) count++;
            else break;
        }
        if (count >= 5) return true;

        //Diagonal
        count = 1;
        for (int i = 1; r + i < 15 && c - i >= 0; i++) {
            if (board[r + i][c - i] == color) count++;
            else break;
        }
        for (int i = 1; r - i >= 0 && c + i < 15; i++) {
            if (board[r - i][c + i] == color) count++;
            else break;
        }
        if (count >= 5) return true;
        return false;
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
                    return false; // Found an empty spot, not full
                }
            }
        }
        return true;
    }
    // Clear the board for a new game
    public void resetBoard() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                board[i][j] = 0;
            }
        }
    }
}

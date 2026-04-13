public class Gomokulogic {
    //Define the size of the chessboard
    private int size = 15;
    private int[][] board;

    private int currentPlayer = 1;

    //Consteuctor
    public Gomokulogic() {
        board = new int[size][size];
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
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
        if (r >= 0 && r < 15 && c >= 0 && c < 15) {

            if (board[r][c] == 0) {
                board[r][c] = color;

                // ⭐ 自动切换玩家（轻微补充）
                switchPlayer();

                return true;
            }
        }
        return false;
    }

    // Check if the game is over
    public boolean checkWin(int r, int c, int color) {
        int count;

        //Horizontal
        count = 1;
        for (int i = c + 1; i < 15; i++) {
            if (board[r][i] == color) count++;
            else break;
        }
        for (int i = c - 1; i >= 0; i--) {
            if (board[r][i] == color) count++;
            else break;
        }
        if (count >= 5) return true;

        //Vertical
        count = 1;
        for (int i = r + 1; i < 15; i++) {
            if (board[i][c] == color) count++;
            else break;
        }
        for (int i = r - 1; i >= 0; i--) {
            if (board[i][c] == color) count++;
            else break;
        }
        if (count >= 5) return true;
        //Diagonal
        count = 1;
        for (int i = 1; r + i < 15 && c + i < 15; i++) {
            if (board[r + i][c + i] == color) count++;
            else break;
        }
        for (int i = 1; r - i >= 0 && c - i >= 0; i--) {
            if (board[r - i][c - i] == color) count++;
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
}

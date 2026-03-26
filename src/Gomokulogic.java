public class Gomokulogic {
    //Define the size of the chessboard
    private int size = 15;
    private int[][] board;

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
        return false;
    }
}

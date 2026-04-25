//Yutian Wang: Implementation of the "Piece Merging" mechanic.
//This logic checks for 3 connected pieces and upgrades them.

public class MergeManager {

    // 0=Empty, 1=Black, 2=White, 3=Merged Black, 4=Merged White
    private int[][] board = new int[15][15];

     //Call this method after every move to check for merge conditions.
    public void checkAndExecuteMerge(int x, int y, int playerRole) {
        // Horizontal check: count same-colored pieces
        int count = 1;

        // Count to the left
        for (int i = x - 1; i >= 0 && board[i][y] == playerRole; i--) {
            count++;
        }
        // Count to the right
        for (int i = x + 1; i < 15 && board[i][y] == playerRole; i++) {
            count++;
        }

        // If 3 pieces are aligned, trigger the merge
        if (count >= 3) {
            System.out.println("Log: Merge triggered at [" + x + "," + y + "]");

            // Step 1: Clear the basic pieces in this row
            for (int i = x - 2; i <= x + 2; i++) {
                if (i >= 0 && i < 15 && board[i][y] == playerRole) {
                    board[i][y] = 0;
                    // Set to Empty
                    // 设为空
                }
            }

            // Step 2: Place the "Merged Piece" at the current spot
            board[x][y] = playerRole + 2;
        }
    }
}
